package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class InflaterSource
  implements Source
{
  private int bufferBytesHeldByInflater;
  private boolean closed;
  private final Inflater inflater;
  private final BufferedSource source;

  InflaterSource(BufferedSource paramBufferedSource, Inflater paramInflater)
  {
    if (paramBufferedSource == null)
      throw new IllegalArgumentException("source == null");
    if (paramInflater == null)
      throw new IllegalArgumentException("inflater == null");
    this.source = paramBufferedSource;
    this.inflater = paramInflater;
  }

  public InflaterSource(Source paramSource, Inflater paramInflater)
  {
    this(Okio.buffer(paramSource), paramInflater);
  }

  private void releaseInflatedBytes()
    throws IOException
  {
    if (this.bufferBytesHeldByInflater == 0)
      return;
    int i = this.bufferBytesHeldByInflater - this.inflater.getRemaining();
    this.bufferBytesHeldByInflater -= i;
    this.source.skip(i);
  }

  public void close()
    throws IOException
  {
    if (this.closed)
      return;
    this.inflater.end();
    this.closed = true;
    this.source.close();
  }

  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    boolean bool1 = paramLong < 0L;
    if (bool1)
    {
      paramBuffer = new StringBuilder();
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    if (this.closed)
      throw new IllegalStateException("closed");
    if (!bool1)
      return 0L;
    while (true)
    {
      boolean bool2 = refill();
      try
      {
        Segment localSegment = paramBuffer.writableSegment(1);
        int i = (int)Math.min(paramLong, 8192 - localSegment.limit);
        i = this.inflater.inflate(localSegment.data, localSegment.limit, i);
        if (i > 0)
        {
          localSegment.limit += i;
          paramLong = paramBuffer.size;
          long l = i;
          paramBuffer.size = (paramLong + l);
          return l;
        }
        if ((!this.inflater.finished()) && (!this.inflater.needsDictionary()))
        {
          if (bool2)
            throw new EOFException("source exhausted prematurely");
        }
        else
        {
          releaseInflatedBytes();
          if (localSegment.pos == localSegment.limit)
          {
            paramBuffer.head = localSegment.pop();
            SegmentPool.recycle(localSegment);
          }
          return -1L;
        }
      }
      catch (DataFormatException paramBuffer)
      {
      }
    }
    throw new IOException(paramBuffer);
  }

  public final boolean refill()
    throws IOException
  {
    if (!this.inflater.needsInput())
      return false;
    releaseInflatedBytes();
    if (this.inflater.getRemaining() != 0)
      throw new IllegalStateException("?");
    if (this.source.exhausted())
      return true;
    Segment localSegment = this.source.buffer().head;
    this.bufferBytesHeldByInflater = (localSegment.limit - localSegment.pos);
    this.inflater.setInput(localSegment.data, localSegment.pos, this.bufferBytesHeldByInflater);
    return false;
  }

  public Timeout timeout()
  {
    return this.source.timeout();
  }
}