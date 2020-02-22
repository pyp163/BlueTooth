package okio;

import java.io.IOException;

final class PeekSource
  implements Source
{
  private final Buffer buffer;
  private boolean closed;
  private int expectedPos;
  private Segment expectedSegment;
  private long pos;
  private final BufferedSource upstream;

  PeekSource(BufferedSource paramBufferedSource)
  {
    this.upstream = paramBufferedSource;
    this.buffer = paramBufferedSource.buffer();
    this.expectedSegment = this.buffer.head;
    int i;
    if (this.expectedSegment != null)
      i = this.expectedSegment.pos;
    else
      i = -1;
    this.expectedPos = i;
  }

  public void close()
    throws IOException
  {
    this.closed = true;
  }

  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    if (this.closed)
      throw new IllegalStateException("closed");
    if ((this.expectedSegment != null) && ((this.expectedSegment != this.buffer.head) || (this.expectedPos != this.buffer.head.pos)))
      throw new IllegalStateException("Peek source is invalid because upstream source was used");
    this.upstream.request(this.pos + paramLong);
    if ((this.expectedSegment == null) && (this.buffer.head != null))
    {
      this.expectedSegment = this.buffer.head;
      this.expectedPos = this.buffer.head.pos;
    }
    paramLong = Math.min(paramLong, this.buffer.size - this.pos);
    if (paramLong <= 0L)
      return -1L;
    this.buffer.copyTo(paramBuffer, this.pos, paramLong);
    this.pos += paramLong;
    return paramLong;
  }

  public Timeout timeout()
  {
    return this.upstream.timeout();
  }
}