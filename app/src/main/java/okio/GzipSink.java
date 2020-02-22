package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

public final class GzipSink
  implements Sink
{
  private boolean closed;
  private final CRC32 crc = new CRC32();
  private final Deflater deflater;
  private final DeflaterSink deflaterSink;
  private final BufferedSink sink;

  public GzipSink(Sink paramSink)
  {
    if (paramSink == null)
      throw new IllegalArgumentException("sink == null");
    this.deflater = new Deflater(-1, true);
    this.sink = Okio.buffer(paramSink);
    this.deflaterSink = new DeflaterSink(this.sink, this.deflater);
    writeHeader();
  }

  private void updateCrc(Buffer paramBuffer, long paramLong)
  {
    for (paramBuffer = paramBuffer.head; paramLong > 0L; paramBuffer = paramBuffer.next)
    {
      int i = (int)Math.min(paramLong, paramBuffer.limit - paramBuffer.pos);
      this.crc.update(paramBuffer.data, paramBuffer.pos, i);
      paramLong -= i;
    }
  }

  private void writeFooter()
    throws IOException
  {
    this.sink.writeIntLe((int)this.crc.getValue());
    this.sink.writeIntLe((int)this.deflater.getBytesRead());
  }

  private void writeHeader()
  {
    Buffer localBuffer = this.sink.buffer();
    localBuffer.writeShort(8075);
    localBuffer.writeByte(8);
    localBuffer.writeByte(0);
    localBuffer.writeInt(0);
    localBuffer.writeByte(0);
    localBuffer.writeByte(0);
  }

  public void close()
    throws IOException
  {
    if (this.closed)
      return;
    Object localObject2 = null;
    try
    {
      this.deflaterSink.finishDeflate();
      writeFooter();
    }
    catch (Throwable localThrowable1)
    {
    }
    Object localObject1;
    try
    {
      this.deflater.end();
      localObject1 = localThrowable1;
    }
    catch (Throwable localThrowable2)
    {
      localObject1 = localThrowable1;
      if (localThrowable1 == null)
        localObject1 = localThrowable2;
    }
    Object localObject3;
    try
    {
      this.sink.close();
      localObject3 = localObject1;
    }
    catch (Throwable localThrowable3)
    {
      localObject3 = localObject1;
      if (localObject1 == null)
        localObject3 = localThrowable3;
    }
    this.closed = true;
    if (localObject3 != null)
      Util.sneakyRethrow(localObject3);
  }

  public final Deflater deflater()
  {
    return this.deflater;
  }

  public void flush()
    throws IOException
  {
    this.deflaterSink.flush();
  }

  public Timeout timeout()
  {
    return this.sink.timeout();
  }

  public void write(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    boolean bool = paramLong < 0L;
    if (bool)
    {
      paramBuffer = new StringBuilder();
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    if (!bool)
      return;
    updateCrc(paramBuffer, paramLong);
    this.deflaterSink.write(paramBuffer, paramLong);
  }
}