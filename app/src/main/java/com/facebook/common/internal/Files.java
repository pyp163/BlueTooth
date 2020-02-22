package com.facebook.common.internal;

import java.io.IOException;
import java.io.InputStream;

public class Files
{
  static byte[] readFile(InputStream paramInputStream, long paramLong)
    throws IOException
  {
    if (paramLong > 2147483647L)
    {
      paramInputStream = new StringBuilder();
      paramInputStream.append("file is too large to fit in a byte array: ");
      paramInputStream.append(paramLong);
      paramInputStream.append(" bytes");
      throw new OutOfMemoryError(paramInputStream.toString());
    }
    if (paramLong == 0L)
      return ByteStreams.toByteArray(paramInputStream);
    return ByteStreams.toByteArray(paramInputStream, (int)paramLong);
  }

  // ERROR //
  public static byte[] toByteArray(java.io.File paramFile)
    throws IOException
  {
    // Byte code:
    //   0: new 51	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 54	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore_0
    //   9: aload_0
    //   10: aload_0
    //   11: invokevirtual 58	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   14: invokevirtual 64	java/nio/channels/FileChannel:size	()J
    //   17: invokestatic 66	com/facebook/common/internal/Files:readFile	(Ljava/io/InputStream;J)[B
    //   20: astore_1
    //   21: aload_0
    //   22: ifnull +7 -> 29
    //   25: aload_0
    //   26: invokevirtual 69	java/io/FileInputStream:close	()V
    //   29: aload_1
    //   30: areturn
    //   31: astore_1
    //   32: goto +6 -> 38
    //   35: astore_1
    //   36: aconst_null
    //   37: astore_0
    //   38: aload_0
    //   39: ifnull +7 -> 46
    //   42: aload_0
    //   43: invokevirtual 69	java/io/FileInputStream:close	()V
    //   46: aload_1
    //   47: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   9	21	31	finally
    //   0	9	35	finally
  }
}