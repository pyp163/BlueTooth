package com.facebook.cache.disk;

import android.os.Environment;
import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.common.WriterCallback;
import com.facebook.common.file.FileTree;
import com.facebook.common.file.FileTreeVisitor;
import com.facebook.common.file.FileUtils;
import com.facebook.common.file.FileUtils.CreateDirectoryException;
import com.facebook.common.file.FileUtils.ParentDirNotFoundException;
import com.facebook.common.file.FileUtils.RenameException;
import com.facebook.common.internal.CountingOutputStream;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class DefaultDiskStorage
  implements DiskStorage
{
  private static final String CONTENT_FILE_EXTENSION = ".cnt";
  private static final String DEFAULT_DISK_STORAGE_VERSION_PREFIX = "v2";
  private static final int SHARDING_BUCKET_COUNT = 100;
  private static final Class<?> TAG = DefaultDiskStorage.class;
  private static final String TEMP_FILE_EXTENSION = ".tmp";
  static final long TEMP_FILE_LIFETIME_MS = TimeUnit.MINUTES.toMillis(30L);
  private final CacheErrorLogger mCacheErrorLogger;
  private final Clock mClock;
  private final boolean mIsExternal;
  private final File mRootDirectory;
  private final File mVersionDirectory;

  public DefaultDiskStorage(File paramFile, int paramInt, CacheErrorLogger paramCacheErrorLogger)
  {
    Preconditions.checkNotNull(paramFile);
    this.mRootDirectory = paramFile;
    this.mIsExternal = isExternal(paramFile, paramCacheErrorLogger);
    this.mVersionDirectory = new File(this.mRootDirectory, getVersionSubdirectoryName(paramInt));
    this.mCacheErrorLogger = paramCacheErrorLogger;
    recreateDirectoryIfVersionChanges();
    this.mClock = SystemClock.get();
  }

  private long doRemove(File paramFile)
  {
    if (!paramFile.exists())
      return 0L;
    long l = paramFile.length();
    if (paramFile.delete())
      return l;
    return -1L;
  }

  private DiskStorage.DiskDumpInfoEntry dumpCacheEntry(DiskStorage.Entry paramEntry)
    throws IOException
  {
    EntryImpl localEntryImpl = (EntryImpl)paramEntry;
    String str1 = "";
    byte[] arrayOfByte = localEntryImpl.getResource().read();
    String str2 = typeOfBytes(arrayOfByte);
    paramEntry = str1;
    if (str2.equals("undefined"))
    {
      paramEntry = str1;
      if (arrayOfByte.length >= 4)
        paramEntry = String.format((Locale)null, "0x%02X 0x%02X 0x%02X 0x%02X", new Object[] { Byte.valueOf(arrayOfByte[0]), Byte.valueOf(arrayOfByte[1]), Byte.valueOf(arrayOfByte[2]), Byte.valueOf(arrayOfByte[3]) });
    }
    return new DiskStorage.DiskDumpInfoEntry(localEntryImpl.getResource().getFile().getPath(), str2, (float)localEntryImpl.getSize(), paramEntry);
  }

  @Nullable
  @FileType
  private static String getFileTypefromExtension(String paramString)
  {
    if (".cnt".equals(paramString))
      return ".cnt";
    if (".tmp".equals(paramString))
      return ".tmp";
    return null;
  }

  private String getFilename(String paramString)
  {
    paramString = new FileInfo(".cnt", paramString, null);
    return paramString.toPath(getSubdirectoryPath(paramString.resourceId));
  }

  private FileInfo getShardFileInfo(File paramFile)
  {
    FileInfo localFileInfo = FileInfo.fromFile(paramFile);
    if (localFileInfo == null)
      return null;
    if (getSubdirectory(localFileInfo.resourceId).equals(paramFile.getParentFile()))
      return localFileInfo;
    return null;
  }

  private File getSubdirectory(String paramString)
  {
    return new File(getSubdirectoryPath(paramString));
  }

  private String getSubdirectoryPath(String paramString)
  {
    int i = Math.abs(paramString.hashCode() % 100);
    paramString = new StringBuilder();
    paramString.append(this.mVersionDirectory);
    paramString.append(File.separator);
    paramString.append(String.valueOf(i));
    return paramString.toString();
  }

  @VisibleForTesting
  static String getVersionSubdirectoryName(int paramInt)
  {
    return String.format((Locale)null, "%s.ols%d.%d", new Object[] { "v2", Integer.valueOf(100), Integer.valueOf(paramInt) });
  }

  private static boolean isExternal(File paramFile, CacheErrorLogger paramCacheErrorLogger)
  {
    try
    {
      Object localObject1 = Environment.getExternalStorageDirectory();
      if (localObject1 != null)
      {
        Object localObject2 = ((File)localObject1).toString();
        try
        {
          localObject1 = paramFile.getCanonicalPath();
          try
          {
            boolean bool = ((String)localObject1).contains((CharSequence)localObject2);
            return bool;
          }
          catch (IOException paramFile)
          {
          }
        }
        catch (IOException paramFile)
        {
          localObject1 = null;
        }
        localObject2 = CacheErrorLogger.CacheErrorCategory.OTHER;
        Class localClass = TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("failed to read folder to check if external: ");
        localStringBuilder.append((String)localObject1);
        paramCacheErrorLogger.logError((CacheErrorLogger.CacheErrorCategory)localObject2, localClass, localStringBuilder.toString(), paramFile);
        return false;
      }
    }
    catch (Exception paramFile)
    {
      paramCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.OTHER, TAG, "failed to get the external storage directory!", paramFile);
    }
    return false;
  }

  private void mkdirs(File paramFile, String paramString)
    throws IOException
  {
    try
    {
      FileUtils.mkdirs(paramFile);
      return;
    }
    catch (FileUtils.CreateDirectoryException paramFile)
    {
      this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR, TAG, paramString, paramFile);
    }
    throw paramFile;
  }

  private boolean query(String paramString, boolean paramBoolean)
  {
    paramString = getContentFileFor(paramString);
    boolean bool = paramString.exists();
    if ((paramBoolean) && (bool))
      paramString.setLastModified(this.mClock.now());
    return bool;
  }

  private void recreateDirectoryIfVersionChanges()
  {
    boolean bool = this.mRootDirectory.exists();
    int i = 1;
    if (bool)
      if (!this.mVersionDirectory.exists())
        FileTree.deleteRecursively(this.mRootDirectory);
      else
        i = 0;
    if (i != 0);
    try
    {
      FileUtils.mkdirs(this.mVersionDirectory);
      return;
      label52: CacheErrorLogger localCacheErrorLogger = this.mCacheErrorLogger;
      CacheErrorLogger.CacheErrorCategory localCacheErrorCategory = CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR;
      Class localClass = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("version directory could not be created: ");
      localStringBuilder.append(this.mVersionDirectory);
      localCacheErrorLogger.logError(localCacheErrorCategory, localClass, localStringBuilder.toString(), null);
      return;
    }
    catch (FileUtils.CreateDirectoryException localCreateDirectoryException)
    {
      break label52;
    }
  }

  private String typeOfBytes(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length >= 2)
    {
      if ((paramArrayOfByte[0] == -1) && (paramArrayOfByte[1] == -40))
        return "jpg";
      if ((paramArrayOfByte[0] == -119) && (paramArrayOfByte[1] == 80))
        return "png";
      if ((paramArrayOfByte[0] == 82) && (paramArrayOfByte[1] == 73))
        return "webp";
      if ((paramArrayOfByte[0] == 71) && (paramArrayOfByte[1] == 73))
        return "gif";
    }
    return "undefined";
  }

  public void clearAll()
  {
    FileTree.deleteContents(this.mRootDirectory);
  }

  public boolean contains(String paramString, Object paramObject)
  {
    return query(paramString, false);
  }

  @VisibleForTesting
  File getContentFileFor(String paramString)
  {
    return new File(getFilename(paramString));
  }

  public DiskStorage.DiskDumpInfo getDumpInfo()
    throws IOException
  {
    Object localObject = getEntries();
    DiskStorage.DiskDumpInfo localDiskDumpInfo = new DiskStorage.DiskDumpInfo();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      DiskStorage.DiskDumpInfoEntry localDiskDumpInfoEntry = dumpCacheEntry((DiskStorage.Entry)((Iterator)localObject).next());
      String str = localDiskDumpInfoEntry.type;
      if (!localDiskDumpInfo.typeCounts.containsKey(str))
        localDiskDumpInfo.typeCounts.put(str, Integer.valueOf(0));
      localDiskDumpInfo.typeCounts.put(str, Integer.valueOf(((Integer)localDiskDumpInfo.typeCounts.get(str)).intValue() + 1));
      localDiskDumpInfo.entries.add(localDiskDumpInfoEntry);
    }
    return localDiskDumpInfo;
  }

  public List<DiskStorage.Entry> getEntries()
    throws IOException
  {
    EntriesCollector localEntriesCollector = new EntriesCollector(null);
    FileTree.walkFileTree(this.mVersionDirectory, localEntriesCollector);
    return localEntriesCollector.getEntries();
  }

  public BinaryResource getResource(String paramString, Object paramObject)
  {
    paramString = getContentFileFor(paramString);
    if (paramString.exists())
    {
      paramString.setLastModified(this.mClock.now());
      return FileBinaryResource.createOrNull(paramString);
    }
    return null;
  }

  public String getStorageName()
  {
    String str = this.mRootDirectory.getAbsolutePath();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("_");
    localStringBuilder.append(str.substring(str.lastIndexOf('/') + 1, str.length()));
    localStringBuilder.append("_");
    localStringBuilder.append(str.hashCode());
    return localStringBuilder.toString();
  }

  public DiskStorage.Inserter insert(String paramString, Object paramObject)
    throws IOException
  {
    paramObject = new FileInfo(".tmp", paramString, null);
    File localFile = getSubdirectory(paramObject.resourceId);
    if (!localFile.exists())
      mkdirs(localFile, "insert");
    try
    {
      paramString = new InserterImpl(paramString, paramObject.createTempFile(localFile));
      return paramString;
    }
    catch (IOException paramString)
    {
      this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_TEMPFILE, TAG, "insert", paramString);
    }
    throw paramString;
  }

  public boolean isEnabled()
  {
    return true;
  }

  public boolean isExternal()
  {
    return this.mIsExternal;
  }

  public void purgeUnexpectedResources()
  {
    FileTree.walkFileTree(this.mRootDirectory, new PurgingVisitor(null));
  }

  public long remove(DiskStorage.Entry paramEntry)
  {
    return doRemove(((EntryImpl)paramEntry).getResource().getFile());
  }

  public long remove(String paramString)
  {
    return doRemove(getContentFileFor(paramString));
  }

  public boolean touch(String paramString, Object paramObject)
  {
    return query(paramString, true);
  }

  private class EntriesCollector
    implements FileTreeVisitor
  {
    private final List<DiskStorage.Entry> result = new ArrayList();

    private EntriesCollector()
    {
    }

    public List<DiskStorage.Entry> getEntries()
    {
      return Collections.unmodifiableList(this.result);
    }

    public void postVisitDirectory(File paramFile)
    {
    }

    public void preVisitDirectory(File paramFile)
    {
    }

    public void visitFile(File paramFile)
    {
      DefaultDiskStorage.FileInfo localFileInfo = DefaultDiskStorage.this.getShardFileInfo(paramFile);
      if ((localFileInfo != null) && (localFileInfo.type == ".cnt"))
        this.result.add(new DefaultDiskStorage.EntryImpl(localFileInfo.resourceId, paramFile, null));
    }
  }

  @VisibleForTesting
  static class EntryImpl
    implements DiskStorage.Entry
  {
    private final String id;
    private final FileBinaryResource resource;
    private long size;
    private long timestamp;

    private EntryImpl(String paramString, File paramFile)
    {
      Preconditions.checkNotNull(paramFile);
      this.id = ((String)Preconditions.checkNotNull(paramString));
      this.resource = FileBinaryResource.createOrNull(paramFile);
      this.size = -1L;
      this.timestamp = -1L;
    }

    public String getId()
    {
      return this.id;
    }

    public FileBinaryResource getResource()
    {
      return this.resource;
    }

    public long getSize()
    {
      if (this.size < 0L)
        this.size = this.resource.size();
      return this.size;
    }

    public long getTimestamp()
    {
      if (this.timestamp < 0L)
        this.timestamp = this.resource.getFile().lastModified();
      return this.timestamp;
    }
  }

  private static class FileInfo
  {
    public final String resourceId;

    @DefaultDiskStorage.FileType
    public final String type;

    private FileInfo(@DefaultDiskStorage.FileType String paramString1, String paramString2)
    {
      this.type = paramString1;
      this.resourceId = paramString2;
    }

    @Nullable
    public static FileInfo fromFile(File paramFile)
    {
      paramFile = paramFile.getName();
      int i = paramFile.lastIndexOf('.');
      if (i <= 0)
        return null;
      String str2 = DefaultDiskStorage.getFileTypefromExtension(paramFile.substring(i));
      if (str2 == null)
        return null;
      String str1 = paramFile.substring(0, i);
      paramFile = str1;
      if (str2.equals(".tmp"))
      {
        i = str1.lastIndexOf('.');
        if (i <= 0)
          return null;
        paramFile = str1.substring(0, i);
      }
      return new FileInfo(str2, paramFile);
    }

    public File createTempFile(File paramFile)
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.resourceId);
      localStringBuilder.append(".");
      return File.createTempFile(localStringBuilder.toString(), ".tmp", paramFile);
    }

    public String toPath(String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append(File.separator);
      localStringBuilder.append(this.resourceId);
      localStringBuilder.append(this.type);
      return localStringBuilder.toString();
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.type);
      localStringBuilder.append("(");
      localStringBuilder.append(this.resourceId);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
    }
  }

  public static @interface FileType
  {
    public static final String CONTENT = ".cnt";
    public static final String TEMP = ".tmp";
  }

  private static class IncompleteFileException extends IOException
  {
    public final long actual;
    public final long expected;

    public IncompleteFileException(long paramLong1, long paramLong2)
    {
      super();
      this.expected = paramLong1;
      this.actual = paramLong2;
    }
  }

  @VisibleForTesting
  class InserterImpl
    implements DiskStorage.Inserter
  {
    private final String mResourceId;

    @VisibleForTesting
    final File mTemporaryFile;

    public InserterImpl(String paramFile, File arg3)
    {
      this.mResourceId = paramFile;
      Object localObject;
      this.mTemporaryFile = localObject;
    }

    public boolean cleanUp()
    {
      return (!this.mTemporaryFile.exists()) || (this.mTemporaryFile.delete());
    }

    public BinaryResource commit(Object paramObject)
      throws IOException
    {
      paramObject = DefaultDiskStorage.this.getContentFileFor(this.mResourceId);
      try
      {
        FileUtils.rename(this.mTemporaryFile, paramObject);
        if (paramObject.exists())
          paramObject.setLastModified(DefaultDiskStorage.this.mClock.now());
        return FileBinaryResource.createOrNull(paramObject);
      }
      catch (FileUtils.RenameException localRenameException)
      {
        paramObject = localRenameException.getCause();
        if (paramObject == null)
          paramObject = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
        else if ((paramObject instanceof FileUtils.ParentDirNotFoundException))
          paramObject = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_PARENT_NOT_FOUND;
        else if ((paramObject instanceof FileNotFoundException))
          paramObject = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_TEMPFILE_NOT_FOUND;
        else
          paramObject = CacheErrorLogger.CacheErrorCategory.WRITE_RENAME_FILE_OTHER;
        DefaultDiskStorage.this.mCacheErrorLogger.logError(paramObject, DefaultDiskStorage.TAG, "commit", localRenameException);
        throw localRenameException;
      }
    }

    public void writeData(WriterCallback paramWriterCallback, Object paramObject)
      throws IOException
    {
      try
      {
        paramObject = new FileOutputStream(this.mTemporaryFile);
        try
        {
          CountingOutputStream localCountingOutputStream = new CountingOutputStream(paramObject);
          paramWriterCallback.write(localCountingOutputStream);
          localCountingOutputStream.flush();
          long l = localCountingOutputStream.getCount();
          paramObject.close();
          if (this.mTemporaryFile.length() != l)
            throw new DefaultDiskStorage.IncompleteFileException(l, this.mTemporaryFile.length());
          return;
        }
        finally
        {
          paramObject.close();
        }
      }
      catch (FileNotFoundException paramWriterCallback)
      {
        DefaultDiskStorage.this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_UPDATE_FILE_NOT_FOUND, DefaultDiskStorage.TAG, "updateResource", paramWriterCallback);
      }
      throw paramWriterCallback;
    }
  }

  private class PurgingVisitor
    implements FileTreeVisitor
  {
    private boolean insideBaseDirectory;

    private PurgingVisitor()
    {
    }

    private boolean isExpectedFile(File paramFile)
    {
      DefaultDiskStorage.FileInfo localFileInfo = DefaultDiskStorage.this.getShardFileInfo(paramFile);
      boolean bool = false;
      if (localFileInfo == null)
        return false;
      if (localFileInfo.type == ".tmp")
        return isRecentFile(paramFile);
      if (localFileInfo.type == ".cnt")
        bool = true;
      Preconditions.checkState(bool);
      return true;
    }

    private boolean isRecentFile(File paramFile)
    {
      return paramFile.lastModified() > DefaultDiskStorage.this.mClock.now() - DefaultDiskStorage.TEMP_FILE_LIFETIME_MS;
    }

    public void postVisitDirectory(File paramFile)
    {
      if ((!DefaultDiskStorage.this.mRootDirectory.equals(paramFile)) && (!this.insideBaseDirectory))
        paramFile.delete();
      if ((this.insideBaseDirectory) && (paramFile.equals(DefaultDiskStorage.this.mVersionDirectory)))
        this.insideBaseDirectory = false;
    }

    public void preVisitDirectory(File paramFile)
    {
      if ((!this.insideBaseDirectory) && (paramFile.equals(DefaultDiskStorage.this.mVersionDirectory)))
        this.insideBaseDirectory = true;
    }

    public void visitFile(File paramFile)
    {
      if ((!this.insideBaseDirectory) || (!isExpectedFile(paramFile)))
        paramFile.delete();
    }
  }
}