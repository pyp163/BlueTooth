package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class DiskLruCache
  implements Closeable, Flushable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  static final String JOURNAL_FILE_TEMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Runnable cleanupRunnable = new Runnable()
  {
    public void run()
    {
      synchronized (DiskLruCache.this)
      {
        if ((DiskLruCache.this.initialized ^ true | DiskLruCache.this.closed))
          return;
      }
      try
      {
        DiskLruCache.this.trimToSize();
        break label48;
        DiskLruCache.this.mostRecentTrimFailed = true;
      }
      catch (IOException localIOException1)
      {
        try
        {
          label48: if (DiskLruCache.this.journalRebuildRequired())
          {
            DiskLruCache.this.rebuildJournal();
            DiskLruCache.this.redundantOpCount = 0;
            break label97;
            label76: DiskLruCache.this.mostRecentRebuildFailed = true;
            DiskLruCache.this.journalWriter = Okio.buffer(Okio.blackhole());
          }
          label97: return;
          localObject = finally;
          throw localObject;
          localIOException1 = localIOException1;
        }
        catch (IOException localIOException2)
        {
          break label76;
        }
      }
    }
  };
  boolean closed;
  final File directory;
  private final Executor executor;
  final FileSystem fileSystem;
  boolean hasJournalErrors;
  boolean initialized;
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  BufferedSink journalWriter;
  final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private long maxSize;
  boolean mostRecentRebuildFailed;
  boolean mostRecentTrimFailed;
  private long nextSequenceNumber = 0L;
  int redundantOpCount;
  private long size = 0L;
  final int valueCount;

  DiskLruCache(FileSystem paramFileSystem, File paramFile, int paramInt1, int paramInt2, long paramLong, Executor paramExecutor)
  {
    this.fileSystem = paramFileSystem;
    this.directory = paramFile;
    this.appVersion = paramInt1;
    this.journalFile = new File(paramFile, "journal");
    this.journalFileTmp = new File(paramFile, "journal.tmp");
    this.journalFileBackup = new File(paramFile, "journal.bkp");
    this.valueCount = paramInt2;
    this.maxSize = paramLong;
    this.executor = paramExecutor;
  }

  private void checkNotClosed()
  {
    try
    {
      if (isClosed())
        throw new IllegalStateException("cache is closed");
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static DiskLruCache create(FileSystem paramFileSystem, File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    if (paramLong <= 0L)
      throw new IllegalArgumentException("maxSize <= 0");
    if (paramInt2 <= 0)
      throw new IllegalArgumentException("valueCount <= 0");
    return new DiskLruCache(paramFileSystem, paramFile, paramInt1, paramInt2, paramLong, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
  }

  private BufferedSink newJournalWriter()
    throws FileNotFoundException
  {
    return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile))
    {
      protected void onException(IOException paramAnonymousIOException)
      {
        DiskLruCache.this.hasJournalErrors = true;
      }
    });
  }

  private void processJournal()
    throws IOException
  {
    this.fileSystem.delete(this.journalFileTmp);
    Iterator localIterator = this.lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      Editor localEditor = localEntry.currentEditor;
      int j = 0;
      int i = 0;
      if (localEditor == null)
      {
        while (i < this.valueCount)
        {
          this.size += localEntry.lengths[i];
          i += 1;
        }
      }
      else
      {
        localEntry.currentEditor = null;
        i = j;
        while (i < this.valueCount)
        {
          this.fileSystem.delete(localEntry.cleanFiles[i]);
          this.fileSystem.delete(localEntry.dirtyFiles[i]);
          i += 1;
        }
        localIterator.remove();
      }
    }
  }

  // ERROR //
  private void readJournal()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 135	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   4: aload_0
    //   5: getfield 146	okhttp3/internal/cache/DiskLruCache:journalFile	Ljava/io/File;
    //   8: invokeinterface 274 2 0
    //   13: invokestatic 277	okio/Okio:buffer	(Lokio/Source;)Lokio/BufferedSource;
    //   16: astore 5
    //   18: aconst_null
    //   19: astore 4
    //   21: aload 4
    //   23: astore_3
    //   24: aload 5
    //   26: invokeinterface 283 1 0
    //   31: astore 6
    //   33: aload 4
    //   35: astore_3
    //   36: aload 5
    //   38: invokeinterface 283 1 0
    //   43: astore 7
    //   45: aload 4
    //   47: astore_3
    //   48: aload 5
    //   50: invokeinterface 283 1 0
    //   55: astore 10
    //   57: aload 4
    //   59: astore_3
    //   60: aload 5
    //   62: invokeinterface 283 1 0
    //   67: astore 8
    //   69: aload 4
    //   71: astore_3
    //   72: aload 5
    //   74: invokeinterface 283 1 0
    //   79: astore 9
    //   81: aload 4
    //   83: astore_3
    //   84: ldc 51
    //   86: aload 6
    //   88: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   91: ifeq +156 -> 247
    //   94: aload 4
    //   96: astore_3
    //   97: ldc 58
    //   99: aload 7
    //   101: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   104: ifeq +143 -> 247
    //   107: aload 4
    //   109: astore_3
    //   110: aload_0
    //   111: getfield 139	okhttp3/internal/cache/DiskLruCache:appVersion	I
    //   114: invokestatic 295	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   117: aload 10
    //   119: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   122: ifeq +125 -> 247
    //   125: aload 4
    //   127: astore_3
    //   128: aload_0
    //   129: getfield 152	okhttp3/internal/cache/DiskLruCache:valueCount	I
    //   132: invokestatic 295	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   135: aload 8
    //   137: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   140: ifeq +107 -> 247
    //   143: aload 4
    //   145: astore_3
    //   146: ldc_w 297
    //   149: aload 9
    //   151: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   154: istore_2
    //   155: iload_2
    //   156: ifne +6 -> 162
    //   159: goto +88 -> 247
    //   162: iconst_0
    //   163: istore_1
    //   164: aload 4
    //   166: astore_3
    //   167: aload_0
    //   168: aload 5
    //   170: invokeinterface 283 1 0
    //   175: invokespecial 300	okhttp3/internal/cache/DiskLruCache:readJournalLine	(Ljava/lang/String;)V
    //   178: iload_1
    //   179: iconst_1
    //   180: iadd
    //   181: istore_1
    //   182: goto -18 -> 164
    //   185: aload 4
    //   187: astore_3
    //   188: aload_0
    //   189: iload_1
    //   190: aload_0
    //   191: getfield 126	okhttp3/internal/cache/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   194: invokevirtual 303	java/util/LinkedHashMap:size	()I
    //   197: isub
    //   198: putfield 305	okhttp3/internal/cache/DiskLruCache:redundantOpCount	I
    //   201: aload 4
    //   203: astore_3
    //   204: aload 5
    //   206: invokeinterface 308 1 0
    //   211: ifne +13 -> 224
    //   214: aload 4
    //   216: astore_3
    //   217: aload_0
    //   218: invokevirtual 311	okhttp3/internal/cache/DiskLruCache:rebuildJournal	()V
    //   221: goto +14 -> 235
    //   224: aload 4
    //   226: astore_3
    //   227: aload_0
    //   228: aload_0
    //   229: invokespecial 313	okhttp3/internal/cache/DiskLruCache:newJournalWriter	()Lokio/BufferedSink;
    //   232: putfield 315	okhttp3/internal/cache/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   235: aload 5
    //   237: ifnull +9 -> 246
    //   240: aconst_null
    //   241: aload 5
    //   243: invokestatic 317	okhttp3/internal/cache/DiskLruCache:$closeResource	(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    //   246: return
    //   247: aload 4
    //   249: astore_3
    //   250: new 319	java/lang/StringBuilder
    //   253: dup
    //   254: invokespecial 320	java/lang/StringBuilder:<init>	()V
    //   257: astore 10
    //   259: aload 4
    //   261: astore_3
    //   262: aload 10
    //   264: ldc_w 322
    //   267: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: pop
    //   271: aload 4
    //   273: astore_3
    //   274: aload 10
    //   276: aload 6
    //   278: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: pop
    //   282: aload 4
    //   284: astore_3
    //   285: aload 10
    //   287: ldc_w 328
    //   290: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: pop
    //   294: aload 4
    //   296: astore_3
    //   297: aload 10
    //   299: aload 7
    //   301: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: pop
    //   305: aload 4
    //   307: astore_3
    //   308: aload 10
    //   310: ldc_w 328
    //   313: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   316: pop
    //   317: aload 4
    //   319: astore_3
    //   320: aload 10
    //   322: aload 8
    //   324: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   327: pop
    //   328: aload 4
    //   330: astore_3
    //   331: aload 10
    //   333: ldc_w 328
    //   336: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: pop
    //   340: aload 4
    //   342: astore_3
    //   343: aload 10
    //   345: aload 9
    //   347: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: pop
    //   351: aload 4
    //   353: astore_3
    //   354: aload 10
    //   356: ldc_w 330
    //   359: invokevirtual 326	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: pop
    //   363: aload 4
    //   365: astore_3
    //   366: new 226	java/io/IOException
    //   369: dup
    //   370: aload 10
    //   372: invokevirtual 332	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   375: invokespecial 333	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   378: athrow
    //   379: astore 4
    //   381: goto +11 -> 392
    //   384: astore 4
    //   386: aload 4
    //   388: astore_3
    //   389: aload 4
    //   391: athrow
    //   392: aload 5
    //   394: ifnull +9 -> 403
    //   397: aload_3
    //   398: aload 5
    //   400: invokestatic 317	okhttp3/internal/cache/DiskLruCache:$closeResource	(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    //   403: aload 4
    //   405: athrow
    //   406: astore_3
    //   407: goto -222 -> 185
    //
    // Exception table:
    //   from	to	target	type
    //   24	33	379	finally
    //   36	45	379	finally
    //   48	57	379	finally
    //   60	69	379	finally
    //   72	81	379	finally
    //   84	94	379	finally
    //   97	107	379	finally
    //   110	125	379	finally
    //   128	143	379	finally
    //   146	155	379	finally
    //   167	178	379	finally
    //   188	201	379	finally
    //   204	214	379	finally
    //   217	221	379	finally
    //   227	235	379	finally
    //   250	259	379	finally
    //   262	271	379	finally
    //   274	282	379	finally
    //   285	294	379	finally
    //   297	305	379	finally
    //   308	317	379	finally
    //   320	328	379	finally
    //   331	340	379	finally
    //   343	351	379	finally
    //   354	363	379	finally
    //   366	379	379	finally
    //   389	392	379	finally
    //   24	33	384	java/lang/Throwable
    //   36	45	384	java/lang/Throwable
    //   48	57	384	java/lang/Throwable
    //   60	69	384	java/lang/Throwable
    //   72	81	384	java/lang/Throwable
    //   84	94	384	java/lang/Throwable
    //   97	107	384	java/lang/Throwable
    //   110	125	384	java/lang/Throwable
    //   128	143	384	java/lang/Throwable
    //   146	155	384	java/lang/Throwable
    //   167	178	384	java/lang/Throwable
    //   188	201	384	java/lang/Throwable
    //   204	214	384	java/lang/Throwable
    //   217	221	384	java/lang/Throwable
    //   227	235	384	java/lang/Throwable
    //   250	259	384	java/lang/Throwable
    //   262	271	384	java/lang/Throwable
    //   274	282	384	java/lang/Throwable
    //   285	294	384	java/lang/Throwable
    //   297	305	384	java/lang/Throwable
    //   308	317	384	java/lang/Throwable
    //   320	328	384	java/lang/Throwable
    //   331	340	384	java/lang/Throwable
    //   343	351	384	java/lang/Throwable
    //   354	363	384	java/lang/Throwable
    //   366	379	384	java/lang/Throwable
    //   167	178	406	java/io/EOFException
  }

  private void readJournalLine(String paramString)
    throws IOException
  {
    int i = paramString.indexOf(' ');
    if (i == -1)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("unexpected journal line: ");
      ((StringBuilder)localObject1).append(paramString);
      throw new IOException(((StringBuilder)localObject1).toString());
    }
    int j = i + 1;
    int k = paramString.indexOf(' ', j);
    if (k == -1)
    {
      localObject2 = paramString.substring(j);
      localObject1 = localObject2;
      if (i == "REMOVE".length())
      {
        localObject1 = localObject2;
        if (paramString.startsWith("REMOVE"))
          this.lruEntries.remove(localObject2);
      }
    }
    else
    {
      localObject1 = paramString.substring(j, k);
    }
    Entry localEntry = (Entry)this.lruEntries.get(localObject1);
    Object localObject2 = localEntry;
    if (localEntry == null)
    {
      localObject2 = new Entry((String)localObject1);
      this.lruEntries.put(localObject1, localObject2);
    }
    if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
    {
      paramString = paramString.substring(k + 1).split(" ");
      ((Entry)localObject2).readable = true;
      ((Entry)localObject2).currentEditor = null;
      ((Entry)localObject2).setLengths(paramString);
      return;
    }
    if ((k == -1) && (i == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
    {
      ((Entry)localObject2).currentEditor = new Editor((Entry)localObject2);
      return;
    }
    if ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ")))
      return;
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("unexpected journal line: ");
    ((StringBuilder)localObject1).append(paramString);
    throw new IOException(((StringBuilder)localObject1).toString());
  }

  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("keys must match regex [a-z0-9_-]{1,120}: \"");
      localStringBuilder.append(paramString);
      localStringBuilder.append("\"");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }

  public void close()
    throws IOException
  {
    while (true)
    {
      int i;
      try
      {
        if ((this.initialized) && (!this.closed))
        {
          Entry[] arrayOfEntry = (Entry[])this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
          int j = arrayOfEntry.length;
          i = 0;
          if (i < j)
          {
            Entry localEntry = arrayOfEntry[i];
            if (localEntry.currentEditor != null)
              localEntry.currentEditor.abort();
          }
          else
          {
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
            this.closed = true;
          }
        }
        else
        {
          this.closed = true;
          return;
        }
      }
      finally
      {
      }
      i += 1;
    }
  }

  void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    while (true)
    {
      int j;
      try
      {
        Entry localEntry = paramEditor.entry;
        if (localEntry.currentEditor != paramEditor)
          throw new IllegalStateException();
        int k = 0;
        j = k;
        if (paramBoolean)
        {
          j = k;
          if (!localEntry.readable)
          {
            int i = 0;
            j = k;
            if (i < this.valueCount)
            {
              if (paramEditor.written[i] == 0)
              {
                paramEditor.abort();
                paramEditor = new StringBuilder();
                paramEditor.append("Newly created entry didn't create value for index ");
                paramEditor.append(i);
                throw new IllegalStateException(paramEditor.toString());
              }
              if (!this.fileSystem.exists(localEntry.dirtyFiles[i]))
              {
                paramEditor.abort();
                return;
              }
              i += 1;
              continue;
            }
          }
        }
        long l1;
        if (j < this.valueCount)
        {
          paramEditor = localEntry.dirtyFiles[j];
          if (paramBoolean)
          {
            if (this.fileSystem.exists(paramEditor))
            {
              File localFile = localEntry.cleanFiles[j];
              this.fileSystem.rename(paramEditor, localFile);
              l1 = localEntry.lengths[j];
              long l2 = this.fileSystem.size(localFile);
              localEntry.lengths[j] = l2;
              this.size = (this.size - l1 + l2);
            }
          }
          else
            this.fileSystem.delete(paramEditor);
        }
        else
        {
          this.redundantOpCount += 1;
          localEntry.currentEditor = null;
          if ((localEntry.readable | paramBoolean))
          {
            localEntry.readable = true;
            this.journalWriter.writeUtf8("CLEAN").writeByte(32);
            this.journalWriter.writeUtf8(localEntry.key);
            localEntry.writeLengths(this.journalWriter);
            this.journalWriter.writeByte(10);
            if (paramBoolean)
            {
              l1 = this.nextSequenceNumber;
              this.nextSequenceNumber = (1L + l1);
              localEntry.sequenceNumber = l1;
            }
          }
          else
          {
            this.lruEntries.remove(localEntry.key);
            this.journalWriter.writeUtf8("REMOVE").writeByte(32);
            this.journalWriter.writeUtf8(localEntry.key);
            this.journalWriter.writeByte(10);
          }
          this.journalWriter.flush();
          if ((this.size > this.maxSize) || (journalRebuildRequired()))
            this.executor.execute(this.cleanupRunnable);
          return;
        }
      }
      finally
      {
      }
      j += 1;
    }
  }

  public void delete()
    throws IOException
  {
    close();
    this.fileSystem.deleteContents(this.directory);
  }

  @Nullable
  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }

  Editor edit(String paramString, long paramLong)
    throws IOException
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      Entry localEntry = (Entry)this.lruEntries.get(paramString);
      if (paramLong != -1L)
        if (localEntry != null)
        {
          long l = localEntry.sequenceNumber;
          if (l == paramLong);
        }
        else
        {
          return null;
        }
      Object localObject;
      if (localEntry != null)
      {
        localObject = localEntry.currentEditor;
        if (localObject != null)
          return null;
      }
      if ((!this.mostRecentTrimFailed) && (!this.mostRecentRebuildFailed))
      {
        this.journalWriter.writeUtf8("DIRTY").writeByte(32).writeUtf8(paramString).writeByte(10);
        this.journalWriter.flush();
        boolean bool = this.hasJournalErrors;
        if (bool)
          return null;
        localObject = localEntry;
        if (localEntry == null)
        {
          localObject = new Entry(paramString);
          this.lruEntries.put(paramString, localObject);
        }
        paramString = new Editor((Entry)localObject);
        ((Entry)localObject).currentEditor = paramString;
        return paramString;
      }
      this.executor.execute(this.cleanupRunnable);
      return null;
    }
    finally
    {
    }
    throw paramString;
  }

  public void evictAll()
    throws IOException
  {
    try
    {
      initialize();
      Entry[] arrayOfEntry = (Entry[])this.lruEntries.values().toArray(new Entry[this.lruEntries.size()]);
      int j = arrayOfEntry.length;
      int i = 0;
      while (i < j)
      {
        removeEntry(arrayOfEntry[i]);
        i += 1;
      }
      this.mostRecentTrimFailed = false;
      return;
    }
    finally
    {
    }
  }

  public void flush()
    throws IOException
  {
    try
    {
      boolean bool = this.initialized;
      if (!bool)
        return;
      checkNotClosed();
      trimToSize();
      this.journalWriter.flush();
      return;
    }
    finally
    {
    }
  }

  public Snapshot get(String paramString)
    throws IOException
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      Object localObject = (Entry)this.lruEntries.get(paramString);
      if ((localObject != null) && (((Entry)localObject).readable))
      {
        localObject = ((Entry)localObject).snapshot();
        if (localObject == null)
          return null;
        this.redundantOpCount += 1;
        this.journalWriter.writeUtf8("READ").writeByte(32).writeUtf8(paramString).writeByte(10);
        if (journalRebuildRequired())
          this.executor.execute(this.cleanupRunnable);
        return localObject;
      }
      return null;
    }
    finally
    {
    }
    throw paramString;
  }

  public File getDirectory()
  {
    return this.directory;
  }

  public long getMaxSize()
  {
    try
    {
      long l = this.maxSize;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void initialize()
    throws IOException
  {
    try
    {
      boolean bool = this.initialized;
      if (bool)
        return;
      if (this.fileSystem.exists(this.journalFileBackup))
        if (this.fileSystem.exists(this.journalFile))
          this.fileSystem.delete(this.journalFileBackup);
        else
          this.fileSystem.rename(this.journalFileBackup, this.journalFile);
      bool = this.fileSystem.exists(this.journalFile);
      if (bool)
        try
        {
          readJournal();
          processJournal();
          this.initialized = true;
          return;
        }
        catch (IOException localIOException)
        {
          Platform localPlatform = Platform.get();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("DiskLruCache ");
          localStringBuilder.append(this.directory);
          localStringBuilder.append(" is corrupt: ");
          localStringBuilder.append(localIOException.getMessage());
          localStringBuilder.append(", removing");
          localPlatform.log(5, localStringBuilder.toString(), localIOException);
        }
      try
      {
        delete();
        this.closed = false;
      }
      finally
      {
        this.closed = false;
      }
      this.initialized = true;
      return;
    }
    finally
    {
    }
  }

  public boolean isClosed()
  {
    try
    {
      boolean bool = this.closed;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  boolean journalRebuildRequired()
  {
    return (this.redundantOpCount >= 2000) && (this.redundantOpCount >= this.lruEntries.size());
  }

  // ERROR //
  void rebuildJournal()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 315	okhttp3/internal/cache/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   6: ifnull +12 -> 18
    //   9: aload_0
    //   10: getfield 315	okhttp3/internal/cache/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   13: invokeinterface 417 1 0
    //   18: aload_0
    //   19: getfield 135	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   22: aload_0
    //   23: getfield 148	okhttp3/internal/cache/DiskLruCache:journalFileTmp	Ljava/io/File;
    //   26: invokeinterface 542 2 0
    //   31: invokestatic 222	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   34: astore_3
    //   35: aconst_null
    //   36: astore_2
    //   37: aload_2
    //   38: astore_1
    //   39: aload_3
    //   40: ldc 51
    //   42: invokeinterface 448 2 0
    //   47: bipush 10
    //   49: invokeinterface 452 2 0
    //   54: pop
    //   55: aload_2
    //   56: astore_1
    //   57: aload_3
    //   58: ldc 58
    //   60: invokeinterface 448 2 0
    //   65: bipush 10
    //   67: invokeinterface 452 2 0
    //   72: pop
    //   73: aload_2
    //   74: astore_1
    //   75: aload_3
    //   76: aload_0
    //   77: getfield 139	okhttp3/internal/cache/DiskLruCache:appVersion	I
    //   80: i2l
    //   81: invokeinterface 546 3 0
    //   86: bipush 10
    //   88: invokeinterface 452 2 0
    //   93: pop
    //   94: aload_2
    //   95: astore_1
    //   96: aload_3
    //   97: aload_0
    //   98: getfield 152	okhttp3/internal/cache/DiskLruCache:valueCount	I
    //   101: i2l
    //   102: invokeinterface 546 3 0
    //   107: bipush 10
    //   109: invokeinterface 452 2 0
    //   114: pop
    //   115: aload_2
    //   116: astore_1
    //   117: aload_3
    //   118: bipush 10
    //   120: invokeinterface 452 2 0
    //   125: pop
    //   126: aload_2
    //   127: astore_1
    //   128: aload_0
    //   129: getfield 126	okhttp3/internal/cache/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   132: invokevirtual 234	java/util/LinkedHashMap:values	()Ljava/util/Collection;
    //   135: invokeinterface 240 1 0
    //   140: astore 4
    //   142: aload_2
    //   143: astore_1
    //   144: aload 4
    //   146: invokeinterface 245 1 0
    //   151: ifeq +127 -> 278
    //   154: aload_2
    //   155: astore_1
    //   156: aload 4
    //   158: invokeinterface 249 1 0
    //   163: checkcast 21	okhttp3/internal/cache/DiskLruCache$Entry
    //   166: astore 5
    //   168: aload_2
    //   169: astore_1
    //   170: aload 5
    //   172: getfield 253	okhttp3/internal/cache/DiskLruCache$Entry:currentEditor	Lokhttp3/internal/cache/DiskLruCache$Editor;
    //   175: ifnull +49 -> 224
    //   178: aload_2
    //   179: astore_1
    //   180: aload_3
    //   181: ldc 37
    //   183: invokeinterface 448 2 0
    //   188: bipush 32
    //   190: invokeinterface 452 2 0
    //   195: pop
    //   196: aload_2
    //   197: astore_1
    //   198: aload_3
    //   199: aload 5
    //   201: getfield 455	okhttp3/internal/cache/DiskLruCache$Entry:key	Ljava/lang/String;
    //   204: invokeinterface 448 2 0
    //   209: pop
    //   210: aload_2
    //   211: astore_1
    //   212: aload_3
    //   213: bipush 10
    //   215: invokeinterface 452 2 0
    //   220: pop
    //   221: goto -79 -> 142
    //   224: aload_2
    //   225: astore_1
    //   226: aload_3
    //   227: ldc 35
    //   229: invokeinterface 448 2 0
    //   234: bipush 32
    //   236: invokeinterface 452 2 0
    //   241: pop
    //   242: aload_2
    //   243: astore_1
    //   244: aload_3
    //   245: aload 5
    //   247: getfield 455	okhttp3/internal/cache/DiskLruCache$Entry:key	Ljava/lang/String;
    //   250: invokeinterface 448 2 0
    //   255: pop
    //   256: aload_2
    //   257: astore_1
    //   258: aload 5
    //   260: aload_3
    //   261: invokevirtual 459	okhttp3/internal/cache/DiskLruCache$Entry:writeLengths	(Lokio/BufferedSink;)V
    //   264: aload_2
    //   265: astore_1
    //   266: aload_3
    //   267: bipush 10
    //   269: invokeinterface 452 2 0
    //   274: pop
    //   275: goto -133 -> 142
    //   278: aload_3
    //   279: ifnull +8 -> 287
    //   282: aconst_null
    //   283: aload_3
    //   284: invokestatic 317	okhttp3/internal/cache/DiskLruCache:$closeResource	(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    //   287: aload_0
    //   288: getfield 135	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   291: aload_0
    //   292: getfield 146	okhttp3/internal/cache/DiskLruCache:journalFile	Ljava/io/File;
    //   295: invokeinterface 437 2 0
    //   300: ifeq +20 -> 320
    //   303: aload_0
    //   304: getfield 135	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   307: aload_0
    //   308: getfield 146	okhttp3/internal/cache/DiskLruCache:journalFile	Ljava/io/File;
    //   311: aload_0
    //   312: getfield 150	okhttp3/internal/cache/DiskLruCache:journalFileBackup	Ljava/io/File;
    //   315: invokeinterface 441 3 0
    //   320: aload_0
    //   321: getfield 135	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   324: aload_0
    //   325: getfield 148	okhttp3/internal/cache/DiskLruCache:journalFileTmp	Ljava/io/File;
    //   328: aload_0
    //   329: getfield 146	okhttp3/internal/cache/DiskLruCache:journalFile	Ljava/io/File;
    //   332: invokeinterface 441 3 0
    //   337: aload_0
    //   338: getfield 135	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   341: aload_0
    //   342: getfield 150	okhttp3/internal/cache/DiskLruCache:journalFileBackup	Ljava/io/File;
    //   345: invokeinterface 230 2 0
    //   350: aload_0
    //   351: aload_0
    //   352: invokespecial 313	okhttp3/internal/cache/DiskLruCache:newJournalWriter	()Lokio/BufferedSink;
    //   355: putfield 315	okhttp3/internal/cache/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   358: aload_0
    //   359: iconst_0
    //   360: putfield 498	okhttp3/internal/cache/DiskLruCache:hasJournalErrors	Z
    //   363: aload_0
    //   364: iconst_0
    //   365: putfield 496	okhttp3/internal/cache/DiskLruCache:mostRecentRebuildFailed	Z
    //   368: aload_0
    //   369: monitorexit
    //   370: return
    //   371: astore_2
    //   372: goto +8 -> 380
    //   375: astore_2
    //   376: aload_2
    //   377: astore_1
    //   378: aload_2
    //   379: athrow
    //   380: aload_3
    //   381: ifnull +8 -> 389
    //   384: aload_1
    //   385: aload_3
    //   386: invokestatic 317	okhttp3/internal/cache/DiskLruCache:$closeResource	(Ljava/lang/Throwable;Ljava/lang/AutoCloseable;)V
    //   389: aload_2
    //   390: athrow
    //   391: astore_1
    //   392: aload_0
    //   393: monitorexit
    //   394: aload_1
    //   395: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   39	55	371	finally
    //   57	73	371	finally
    //   75	94	371	finally
    //   96	115	371	finally
    //   117	126	371	finally
    //   128	142	371	finally
    //   144	154	371	finally
    //   156	168	371	finally
    //   170	178	371	finally
    //   180	196	371	finally
    //   198	210	371	finally
    //   212	221	371	finally
    //   226	242	371	finally
    //   244	256	371	finally
    //   258	264	371	finally
    //   266	275	371	finally
    //   378	380	371	finally
    //   39	55	375	java/lang/Throwable
    //   57	73	375	java/lang/Throwable
    //   75	94	375	java/lang/Throwable
    //   96	115	375	java/lang/Throwable
    //   117	126	375	java/lang/Throwable
    //   128	142	375	java/lang/Throwable
    //   144	154	375	java/lang/Throwable
    //   156	168	375	java/lang/Throwable
    //   170	178	375	java/lang/Throwable
    //   180	196	375	java/lang/Throwable
    //   198	210	375	java/lang/Throwable
    //   212	221	375	java/lang/Throwable
    //   226	242	375	java/lang/Throwable
    //   244	256	375	java/lang/Throwable
    //   258	264	375	java/lang/Throwable
    //   266	275	375	java/lang/Throwable
    //   2	18	391	finally
    //   18	35	391	finally
    //   282	287	391	finally
    //   287	320	391	finally
    //   320	368	391	finally
    //   384	389	391	finally
    //   389	391	391	finally
  }

  public boolean remove(String paramString)
    throws IOException
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      paramString = (Entry)this.lruEntries.get(paramString);
      if (paramString == null)
        return false;
      boolean bool = removeEntry(paramString);
      if ((bool) && (this.size <= this.maxSize))
        this.mostRecentTrimFailed = false;
      return bool;
    }
    finally
    {
    }
    throw paramString;
  }

  boolean removeEntry(Entry paramEntry)
    throws IOException
  {
    if (paramEntry.currentEditor != null)
      paramEntry.currentEditor.detach();
    int i = 0;
    while (i < this.valueCount)
    {
      this.fileSystem.delete(paramEntry.cleanFiles[i]);
      this.size -= paramEntry.lengths[i];
      paramEntry.lengths[i] = 0L;
      i += 1;
    }
    this.redundantOpCount += 1;
    this.journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(paramEntry.key).writeByte(10);
    this.lruEntries.remove(paramEntry.key);
    if (journalRebuildRequired())
      this.executor.execute(this.cleanupRunnable);
    return true;
  }

  public void setMaxSize(long paramLong)
  {
    try
    {
      this.maxSize = paramLong;
      if (this.initialized)
        this.executor.execute(this.cleanupRunnable);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long size()
    throws IOException
  {
    try
    {
      initialize();
      long l = this.size;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Iterator<Snapshot> snapshots()
    throws IOException
  {
    try
    {
      initialize();
      Iterator local3 = new Iterator()
      {
        final Iterator<DiskLruCache.Entry> delegate = new ArrayList(DiskLruCache.this.lruEntries.values()).iterator();
        DiskLruCache.Snapshot nextSnapshot;
        DiskLruCache.Snapshot removeSnapshot;

        public boolean hasNext()
        {
          if (this.nextSnapshot != null)
            return true;
          synchronized (DiskLruCache.this)
          {
            if (DiskLruCache.this.closed)
              return false;
            while (this.delegate.hasNext())
            {
              DiskLruCache.Snapshot localSnapshot = ((DiskLruCache.Entry)this.delegate.next()).snapshot();
              if (localSnapshot != null)
              {
                this.nextSnapshot = localSnapshot;
                return true;
              }
            }
            return false;
          }
        }

        public DiskLruCache.Snapshot next()
        {
          if (!hasNext())
            throw new NoSuchElementException();
          this.removeSnapshot = this.nextSnapshot;
          this.nextSnapshot = null;
          return this.removeSnapshot;
        }

        public void remove()
        {
          if (this.removeSnapshot == null)
            throw new IllegalStateException("remove() before next()");
          try
          {
            label43: 
            try
            {
              DiskLruCache.this.remove(DiskLruCache.Snapshot.access$000(this.removeSnapshot));
            }
            finally
            {
              this.removeSnapshot = null;
            }
            return;
          }
          catch (IOException localIOException)
          {
            break label43;
          }
        }
      };
      return local3;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  void trimToSize()
    throws IOException
  {
    while (this.size > this.maxSize)
      removeEntry((Entry)this.lruEntries.values().iterator().next());
    this.mostRecentTrimFailed = false;
  }

  public final class Editor
  {
    private boolean done;
    final DiskLruCache.Entry entry;
    final boolean[] written;

    Editor(DiskLruCache.Entry arg2)
    {
      Object localObject;
      this.entry = localObject;
      if (localObject.readable)
        this$1 = null;
      else
        this$1 = new boolean[DiskLruCache.this.valueCount];
      this.written = DiskLruCache.this;
    }

    public void abort()
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (this.done)
          throw new IllegalStateException();
        if (this.entry.currentEditor == this)
          DiskLruCache.this.completeEdit(this, false);
        this.done = true;
        return;
      }
    }

    public void abortUnlessCommitted()
    {
      synchronized (DiskLruCache.this)
      {
        if (!this.done)
        {
          Editor localEditor = this.entry.currentEditor;
          if (localEditor != this);
        }
      }
      try
      {
        DiskLruCache.this.completeEdit(this, false);
        label36: return;
        localObject = finally;
        throw localObject;
      }
      catch (IOException localIOException)
      {
        break label36;
      }
    }

    public void commit()
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (this.done)
          throw new IllegalStateException();
        if (this.entry.currentEditor == this)
          DiskLruCache.this.completeEdit(this, true);
        this.done = true;
        return;
      }
    }

    void detach()
    {
      int i;
      if (this.entry.currentEditor == this)
        i = 0;
      while (true)
      {
        if (i < DiskLruCache.this.valueCount);
        try
        {
          DiskLruCache.this.fileSystem.delete(this.entry.dirtyFiles[i]);
          label45: i += 1;
          continue;
          this.entry.currentEditor = null;
          return;
        }
        catch (IOException localIOException)
        {
          break label45;
        }
      }
    }

    public Sink newSink(int paramInt)
    {
      Object localObject1;
      synchronized (DiskLruCache.this)
      {
        if (this.done)
          throw new IllegalStateException();
        if (this.entry.currentEditor != this)
        {
          localObject1 = Okio.blackhole();
          return localObject1;
        }
        if (!this.entry.readable)
          this.written[paramInt] = true;
        localObject1 = this.entry.dirtyFiles[paramInt];
      }
      try
      {
        localObject1 = DiskLruCache.this.fileSystem.sink((File)localObject1);
        localObject1 = new FaultHidingSink((Sink)localObject1)
        {
          protected void onException(IOException arg1)
          {
            synchronized (DiskLruCache.this)
            {
              DiskLruCache.Editor.this.detach();
              return;
            }
          }
        };
        return localObject1;
        label96: localObject1 = Okio.blackhole();
        return localObject1;
        localObject2 = finally;
        throw localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        break label96;
      }
    }

    public Source newSource(int paramInt)
    {
      Object localObject1;
      synchronized (DiskLruCache.this)
      {
        if (this.done)
          throw new IllegalStateException();
        if (this.entry.readable)
        {
          localObject1 = this.entry.currentEditor;
          if (localObject1 == this);
        }
      }
      try
      {
        localObject1 = DiskLruCache.this.fileSystem.source(this.entry.cleanFiles[paramInt]);
        return localObject1;
        label74: return null;
        return null;
        localObject2 = finally;
        throw localObject2;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        break label74;
      }
    }
  }

  private final class Entry
  {
    final File[] cleanFiles;
    DiskLruCache.Editor currentEditor;
    final File[] dirtyFiles;
    final String key;
    final long[] lengths;
    boolean readable;
    long sequenceNumber;

    Entry(String arg2)
    {
      this.key = ((String)localObject);
      this.lengths = new long[DiskLruCache.this.valueCount];
      this.cleanFiles = new File[DiskLruCache.this.valueCount];
      this.dirtyFiles = new File[DiskLruCache.this.valueCount];
      Object localObject = new StringBuilder((String)localObject);
      ((StringBuilder)localObject).append('.');
      int j = ((StringBuilder)localObject).length();
      int i = 0;
      while (i < DiskLruCache.this.valueCount)
      {
        ((StringBuilder)localObject).append(i);
        this.cleanFiles[i] = new File(DiskLruCache.this.directory, ((StringBuilder)localObject).toString());
        ((StringBuilder)localObject).append(".tmp");
        this.dirtyFiles[i] = new File(DiskLruCache.this.directory, ((StringBuilder)localObject).toString());
        ((StringBuilder)localObject).setLength(j);
        i += 1;
      }
    }

    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("unexpected journal line: ");
      localStringBuilder.append(Arrays.toString(paramArrayOfString));
      throw new IOException(localStringBuilder.toString());
    }

    void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != DiskLruCache.this.valueCount)
        throw invalidLengths(paramArrayOfString);
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          this.lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i += 1;
        }
        return;
        label46: throw invalidLengths(paramArrayOfString);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        break label46;
      }
    }

    DiskLruCache.Snapshot snapshot()
    {
      if (!Thread.holdsLock(DiskLruCache.this))
        throw new AssertionError();
      Source[] arrayOfSource = new Source[DiskLruCache.this.valueCount];
      Object localObject = (long[])this.lengths.clone();
      int j = 0;
      int i = 0;
      try
      {
        while (i < DiskLruCache.this.valueCount)
        {
          arrayOfSource[i] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i]);
          i += 1;
        }
        localObject = new DiskLruCache.Snapshot(DiskLruCache.this, this.key, this.sequenceNumber, arrayOfSource, (long[])localObject);
        return localObject;
        while ((i < DiskLruCache.this.valueCount) && (arrayOfSource[i] != null))
        {
          Util.closeQuietly(arrayOfSource[i]);
          i += 1;
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        try
        {
          DiskLruCache.this.removeEntry(this);
          label150: return null;
          localFileNotFoundException = localFileNotFoundException;
          i = j;
        }
        catch (IOException localIOException)
        {
          break label150;
        }
      }
    }

    void writeLengths(BufferedSink paramBufferedSink)
      throws IOException
    {
      long[] arrayOfLong = this.lengths;
      int j = arrayOfLong.length;
      int i = 0;
      while (i < j)
      {
        long l = arrayOfLong[i];
        paramBufferedSink.writeByte(32).writeDecimalLong(l);
        i += 1;
      }
    }
  }

  public final class Snapshot
    implements Closeable
  {
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;
    private final Source[] sources;

    Snapshot(String paramLong, long arg3, Source[] paramArrayOfLong, long[] arg6)
    {
      this.key = paramLong;
      this.sequenceNumber = ???;
      this.sources = paramArrayOfLong;
      Object localObject;
      this.lengths = localObject;
    }

    public void close()
    {
      Source[] arrayOfSource = this.sources;
      int j = arrayOfSource.length;
      int i = 0;
      while (i < j)
      {
        Util.closeQuietly(arrayOfSource[i]);
        i += 1;
      }
    }

    @Nullable
    public DiskLruCache.Editor edit()
      throws IOException
    {
      return DiskLruCache.this.edit(this.key, this.sequenceNumber);
    }

    public long getLength(int paramInt)
    {
      return this.lengths[paramInt];
    }

    public Source getSource(int paramInt)
    {
      return this.sources[paramInt];
    }

    public String key()
    {
      return this.key;
    }
  }
}