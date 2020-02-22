package com.facebook.soloader;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ExoSoSource extends UnpackingSoSource
{
  public ExoSoSource(Context paramContext, String paramString)
  {
    super(paramContext, paramString);
  }

  protected UnpackingSoSource.Unpacker makeUnpacker()
    throws IOException
  {
    return new ExoUnpacker(this);
  }

  private final class ExoUnpacker extends UnpackingSoSource.Unpacker
  {
    private final ExoSoSource.FileDso[] mDsos;

    // ERROR //
    ExoUnpacker(UnpackingSoSource arg2)
      throws IOException
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: putfield 22	com/facebook/soloader/ExoSoSource$ExoUnpacker:this$0	Lcom/facebook/soloader/ExoSoSource;
      //   5: aload_0
      //   6: invokespecial 25	com/facebook/soloader/UnpackingSoSource$Unpacker:<init>	()V
      //   9: aload_1
      //   10: getfield 29	com/facebook/soloader/ExoSoSource:mContext	Landroid/content/Context;
      //   13: astore_1
      //   14: new 31	java/lang/StringBuilder
      //   17: dup
      //   18: invokespecial 32	java/lang/StringBuilder:<init>	()V
      //   21: astore 8
      //   23: aload 8
      //   25: ldc 34
      //   27: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   30: pop
      //   31: aload 8
      //   33: aload_1
      //   34: invokevirtual 44	android/content/Context:getPackageName	()Ljava/lang/String;
      //   37: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   40: pop
      //   41: aload 8
      //   43: ldc 46
      //   45: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   48: pop
      //   49: new 48	java/io/File
      //   52: dup
      //   53: aload 8
      //   55: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   58: invokespecial 54	java/io/File:<init>	(Ljava/lang/String;)V
      //   61: astore_1
      //   62: new 56	java/util/ArrayList
      //   65: dup
      //   66: invokespecial 57	java/util/ArrayList:<init>	()V
      //   69: astore 11
      //   71: new 59	java/util/LinkedHashSet
      //   74: dup
      //   75: invokespecial 60	java/util/LinkedHashSet:<init>	()V
      //   78: astore 12
      //   80: invokestatic 66	com/facebook/soloader/SysUtil:getSupportedAbis	()[Ljava/lang/String;
      //   83: astore 13
      //   85: aload 13
      //   87: arraylength
      //   88: istore 5
      //   90: iconst_0
      //   91: istore_3
      //   92: iload_3
      //   93: iload 5
      //   95: if_icmpge +417 -> 512
      //   98: aload 13
      //   100: iload_3
      //   101: aaload
      //   102: astore 9
      //   104: new 48	java/io/File
      //   107: dup
      //   108: aload_1
      //   109: aload 9
      //   111: invokespecial 69	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   114: astore 8
      //   116: aload 8
      //   118: invokevirtual 73	java/io/File:isDirectory	()Z
      //   121: ifne +6 -> 127
      //   124: goto +291 -> 415
      //   127: aload 12
      //   129: aload 9
      //   131: invokeinterface 79 2 0
      //   136: pop
      //   137: new 48	java/io/File
      //   140: dup
      //   141: aload 8
      //   143: ldc 81
      //   145: invokespecial 69	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   148: astore 9
      //   150: aload 9
      //   152: invokevirtual 84	java/io/File:isFile	()Z
      //   155: ifne +6 -> 161
      //   158: goto -34 -> 124
      //   161: new 86	java/io/FileReader
      //   164: dup
      //   165: aload 9
      //   167: invokespecial 89	java/io/FileReader:<init>	(Ljava/io/File;)V
      //   170: astore 9
      //   172: new 91	java/io/BufferedReader
      //   175: dup
      //   176: aload 9
      //   178: invokespecial 94	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   181: astore 10
      //   183: iconst_0
      //   184: istore 4
      //   186: aload 10
      //   188: invokevirtual 97	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   191: astore 14
      //   193: aload 14
      //   195: ifnull +191 -> 386
      //   198: aload 14
      //   200: invokevirtual 103	java/lang/String:length	()I
      //   203: ifne +6 -> 209
      //   206: goto -23 -> 183
      //   209: aload 14
      //   211: bipush 32
      //   213: invokevirtual 107	java/lang/String:indexOf	(I)I
      //   216: istore 6
      //   218: iload 6
      //   220: iconst_m1
      //   221: if_icmpne +44 -> 265
      //   224: new 31	java/lang/StringBuilder
      //   227: dup
      //   228: invokespecial 32	java/lang/StringBuilder:<init>	()V
      //   231: astore_1
      //   232: aload_1
      //   233: ldc 109
      //   235: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   238: pop
      //   239: aload_1
      //   240: aload 14
      //   242: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   245: pop
      //   246: aload_1
      //   247: ldc 111
      //   249: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   252: pop
      //   253: new 113	java/lang/RuntimeException
      //   256: dup
      //   257: aload_1
      //   258: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   261: invokespecial 114	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
      //   264: athrow
      //   265: new 31	java/lang/StringBuilder
      //   268: dup
      //   269: invokespecial 32	java/lang/StringBuilder:<init>	()V
      //   272: astore 15
      //   274: aload 15
      //   276: aload 14
      //   278: iconst_0
      //   279: iload 6
      //   281: invokevirtual 118	java/lang/String:substring	(II)Ljava/lang/String;
      //   284: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   287: pop
      //   288: aload 15
      //   290: ldc 120
      //   292: invokevirtual 38	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   295: pop
      //   296: aload 15
      //   298: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   301: astore 15
      //   303: aload 11
      //   305: invokevirtual 123	java/util/ArrayList:size	()I
      //   308: istore 7
      //   310: iload 4
      //   312: iload 7
      //   314: if_icmpge +252 -> 566
      //   317: aload 11
      //   319: iload 4
      //   321: invokevirtual 127	java/util/ArrayList:get	(I)Ljava/lang/Object;
      //   324: checkcast 129	com/facebook/soloader/ExoSoSource$FileDso
      //   327: getfield 133	com/facebook/soloader/ExoSoSource$FileDso:name	Ljava/lang/String;
      //   330: aload 15
      //   332: invokevirtual 136	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   335: ifeq +222 -> 557
      //   338: iconst_1
      //   339: istore 4
      //   341: goto +228 -> 569
      //   344: aload 14
      //   346: iload 6
      //   348: iconst_1
      //   349: iadd
      //   350: invokevirtual 139	java/lang/String:substring	(I)Ljava/lang/String;
      //   353: astore 14
      //   355: aload 11
      //   357: new 129	com/facebook/soloader/ExoSoSource$FileDso
      //   360: dup
      //   361: aload 15
      //   363: aload 14
      //   365: new 48	java/io/File
      //   368: dup
      //   369: aload 8
      //   371: aload 14
      //   373: invokespecial 69	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   376: invokespecial 142	com/facebook/soloader/ExoSoSource$FileDso:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V
      //   379: invokevirtual 143	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   382: pop
      //   383: goto +191 -> 574
      //   386: aload_1
      //   387: astore 8
      //   389: aload 10
      //   391: ifnull +8 -> 399
      //   394: aload 10
      //   396: invokevirtual 146	java/io/BufferedReader:close	()V
      //   399: aload 8
      //   401: astore_1
      //   402: aload 9
      //   404: ifnull +11 -> 415
      //   407: aload 9
      //   409: invokevirtual 147	java/io/FileReader:close	()V
      //   412: aload 8
      //   414: astore_1
      //   415: iload_3
      //   416: iconst_1
      //   417: iadd
      //   418: istore_3
      //   419: goto -327 -> 92
      //   422: astore_1
      //   423: aconst_null
      //   424: astore_2
      //   425: goto +7 -> 432
      //   428: astore_2
      //   429: aload_2
      //   430: athrow
      //   431: astore_1
      //   432: aload 10
      //   434: ifnull +31 -> 465
      //   437: aload_2
      //   438: ifnull +22 -> 460
      //   441: aload 10
      //   443: invokevirtual 146	java/io/BufferedReader:close	()V
      //   446: goto +19 -> 465
      //   449: astore 8
      //   451: aload_2
      //   452: aload 8
      //   454: invokevirtual 151	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
      //   457: goto +8 -> 465
      //   460: aload 10
      //   462: invokevirtual 146	java/io/BufferedReader:close	()V
      //   465: aload_1
      //   466: athrow
      //   467: astore_1
      //   468: aconst_null
      //   469: astore_2
      //   470: goto +7 -> 477
      //   473: astore_2
      //   474: aload_2
      //   475: athrow
      //   476: astore_1
      //   477: aload 9
      //   479: ifnull +31 -> 510
      //   482: aload_2
      //   483: ifnull +22 -> 505
      //   486: aload 9
      //   488: invokevirtual 147	java/io/FileReader:close	()V
      //   491: goto +19 -> 510
      //   494: astore 8
      //   496: aload_2
      //   497: aload 8
      //   499: invokevirtual 151	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
      //   502: goto +8 -> 510
      //   505: aload 9
      //   507: invokevirtual 147	java/io/FileReader:close	()V
      //   510: aload_1
      //   511: athrow
      //   512: aload_2
      //   513: aload 12
      //   515: aload 12
      //   517: invokeinterface 152 1 0
      //   522: anewarray 99	java/lang/String
      //   525: invokeinterface 156 2 0
      //   530: checkcast 158	[Ljava/lang/String;
      //   533: invokevirtual 164	com/facebook/soloader/UnpackingSoSource:setSoSourceAbis	([Ljava/lang/String;)V
      //   536: aload_0
      //   537: aload 11
      //   539: aload 11
      //   541: invokevirtual 123	java/util/ArrayList:size	()I
      //   544: anewarray 129	com/facebook/soloader/ExoSoSource$FileDso
      //   547: invokevirtual 165	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
      //   550: checkcast 166	[Lcom/facebook/soloader/ExoSoSource$FileDso;
      //   553: putfield 168	com/facebook/soloader/ExoSoSource$ExoUnpacker:mDsos	[Lcom/facebook/soloader/ExoSoSource$FileDso;
      //   556: return
      //   557: iload 4
      //   559: iconst_1
      //   560: iadd
      //   561: istore 4
      //   563: goto -253 -> 310
      //   566: iconst_0
      //   567: istore 4
      //   569: iload 4
      //   571: ifeq -227 -> 344
      //   574: goto -391 -> 183
      //
      // Exception table:
      //   from	to	target	type
      //   186	193	422	finally
      //   198	206	422	finally
      //   209	218	422	finally
      //   224	265	422	finally
      //   265	310	422	finally
      //   317	338	422	finally
      //   344	383	422	finally
      //   186	193	428	java/lang/Throwable
      //   198	206	428	java/lang/Throwable
      //   209	218	428	java/lang/Throwable
      //   224	265	428	java/lang/Throwable
      //   265	310	428	java/lang/Throwable
      //   317	338	428	java/lang/Throwable
      //   344	383	428	java/lang/Throwable
      //   429	431	431	finally
      //   441	446	449	java/lang/Throwable
      //   172	183	467	finally
      //   394	399	467	finally
      //   441	446	467	finally
      //   451	457	467	finally
      //   460	465	467	finally
      //   465	467	467	finally
      //   172	183	473	java/lang/Throwable
      //   394	399	473	java/lang/Throwable
      //   451	457	473	java/lang/Throwable
      //   460	465	473	java/lang/Throwable
      //   465	467	473	java/lang/Throwable
      //   474	476	476	finally
      //   486	491	494	java/lang/Throwable
    }

    protected UnpackingSoSource.DsoManifest getDsoManifest()
      throws IOException
    {
      return new UnpackingSoSource.DsoManifest(this.mDsos);
    }

    protected UnpackingSoSource.InputDsoIterator openDsoIterator()
      throws IOException
    {
      return new FileBackedInputDsoIterator(null);
    }

    private final class FileBackedInputDsoIterator extends UnpackingSoSource.InputDsoIterator
    {
      private int mCurrentDso;

      private FileBackedInputDsoIterator()
      {
      }

      public boolean hasNext()
      {
        return this.mCurrentDso < ExoSoSource.ExoUnpacker.this.mDsos.length;
      }

      public UnpackingSoSource.InputDso next()
        throws IOException
      {
        Object localObject1 = ExoSoSource.ExoUnpacker.this.mDsos;
        int i = this.mCurrentDso;
        this.mCurrentDso = (i + 1);
        Object localObject2 = localObject1[i];
        localObject1 = new FileInputStream(((ExoSoSource.FileDso)localObject2).backingFile);
        try
        {
          localObject2 = new UnpackingSoSource.InputDso((UnpackingSoSource.Dso)localObject2, (InputStream)localObject1);
          return localObject2;
        }
        finally
        {
          if (localObject1 != null)
            ((FileInputStream)localObject1).close();
        }
      }
    }
  }

  private static final class FileDso extends UnpackingSoSource.Dso
  {
    final File backingFile;

    FileDso(String paramString1, String paramString2, File paramFile)
    {
      super(paramString2);
      this.backingFile = paramFile;
    }
  }
}