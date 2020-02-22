package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import okio.Buffer;

final class FileOperator
{
  private final FileChannel fileChannel;

  FileOperator(FileChannel paramFileChannel)
  {
    this.fileChannel = paramFileChannel;
  }

  public void read(long paramLong1, Buffer paramBuffer, long paramLong2)
    throws IOException
  {
    long l = paramLong2;
    if (paramLong2 < 0L)
      throw new IndexOutOfBoundsException();
    while (l > 0L)
    {
      paramLong2 = this.fileChannel.transferTo(paramLong1, l, paramBuffer);
      paramLong1 += paramLong2;
      l -= paramLong2;
    }
  }

  public void write(long paramLong1, Buffer paramBuffer, long paramLong2)
    throws IOException
  {
    if (paramLong2 >= 0L)
    {
      long l = paramLong2;
      if (paramLong2 <= paramBuffer.size())
      {
        while (l > 0L)
        {
          paramLong2 = this.fileChannel.transferFrom(paramBuffer, paramLong1, l);
          paramLong1 += paramLong2;
          l -= paramLong2;
        }
        return;
      }
    }
    throw new IndexOutOfBoundsException();
  }
}