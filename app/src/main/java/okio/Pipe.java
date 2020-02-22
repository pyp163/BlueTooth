package okio;

import java.io.IOException;
import javax.annotation.Nullable;

public final class Pipe
{
  final Buffer buffer = new Buffer();

  @Nullable
  private Sink foldedSink;
  final long maxBufferSize;
  private final Sink sink = new PipeSink();
  boolean sinkClosed;
  private final Source source = new PipeSource();
  boolean sourceClosed;

  public Pipe(long paramLong)
  {
    if (paramLong < 1L)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("maxBufferSize < 1: ");
      localStringBuilder.append(paramLong);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    this.maxBufferSize = paramLong;
  }

  public void fold(Sink arg1)
    throws IOException
  {
    while (true)
    {
      Buffer localBuffer2;
      synchronized (this.buffer)
      {
        if (this.foldedSink != null)
          throw new IllegalStateException("sink already folded");
        if (this.buffer.exhausted())
        {
          this.sourceClosed = true;
          this.foldedSink = ???;
          return;
        }
        localBuffer2 = new Buffer();
        localBuffer2.write(this.buffer, this.buffer.size);
        this.buffer.notifyAll();
      }
      try
      {
        ???.write(localBuffer2, localBuffer2.size);
        ???.flush();
      }
      finally
      {
        synchronized (this.buffer)
        {
          this.sourceClosed = true;
          this.buffer.notifyAll();
        }
      }
    }
  }

  public final Sink sink()
  {
    return this.sink;
  }

  public final Source source()
  {
    return this.source;
  }

  final class PipeSink
    implements Sink
  {
    final PushableTimeout timeout = new PushableTimeout();

    PipeSink()
    {
    }

    public void close()
      throws IOException
    {
      synchronized (Pipe.this.buffer)
      {
        if (Pipe.this.sinkClosed)
          return;
        Sink localSink;
        if (Pipe.this.foldedSink != null)
        {
          localSink = Pipe.this.foldedSink;
        }
        else
        {
          if ((Pipe.this.sourceClosed) && (Pipe.this.buffer.size() > 0L))
            throw new IOException("source is closed");
          Pipe.this.sinkClosed = true;
          Pipe.this.buffer.notifyAll();
          localSink = null;
        }
        if (localSink != null)
        {
          this.timeout.push(localSink.timeout());
          try
          {
            localSink.close();
            return;
          }
          finally
          {
            this.timeout.pop();
          }
        }
        return;
      }
    }

    public void flush()
      throws IOException
    {
      while (true)
      {
        synchronized (Pipe.this.buffer)
        {
          if (Pipe.this.sinkClosed)
            throw new IllegalStateException("closed");
          Sink localSink;
          if (Pipe.this.foldedSink != null)
          {
            localSink = Pipe.this.foldedSink;
          }
          else
          {
            if ((!Pipe.this.sourceClosed) || (Pipe.this.buffer.size() <= 0L))
              break label135;
            throw new IOException("source is closed");
          }
          if (localSink != null)
          {
            this.timeout.push(localSink.timeout());
            try
            {
              localSink.flush();
              return;
            }
            finally
            {
              this.timeout.pop();
            }
          }
          return;
        }
        label135: Object localObject3 = null;
      }
    }

    public Timeout timeout()
    {
      return this.timeout;
    }

    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      while (true)
      {
        synchronized (Pipe.this.buffer)
        {
          if (Pipe.this.sinkClosed)
            throw new IllegalStateException("closed");
          if (paramLong > 0L)
          {
            if (Pipe.this.foldedSink != null)
            {
              localSink = Pipe.this.foldedSink;
            }
            else
            {
              if (Pipe.this.sourceClosed)
                throw new IOException("source is closed");
              long l = Pipe.this.maxBufferSize - Pipe.this.buffer.size();
              if (l == 0L)
              {
                this.timeout.waitUntilNotified(Pipe.this.buffer);
                continue;
              }
              l = Math.min(l, paramLong);
              Pipe.this.buffer.write(paramBuffer, l);
              paramLong -= l;
              Pipe.this.buffer.notifyAll();
              continue;
            }
            if (localSink != null)
            {
              this.timeout.push(localSink.timeout());
              try
              {
                localSink.write(paramBuffer, paramLong);
                return;
              }
              finally
              {
                this.timeout.pop();
              }
            }
            return;
          }
        }
        Sink localSink = null;
      }
    }
  }

  final class PipeSource
    implements Source
  {
    final Timeout timeout = new Timeout();

    PipeSource()
    {
    }

    public void close()
      throws IOException
    {
      synchronized (Pipe.this.buffer)
      {
        Pipe.this.sourceClosed = true;
        Pipe.this.buffer.notifyAll();
        return;
      }
    }

    public long read(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      synchronized (Pipe.this.buffer)
      {
        if (Pipe.this.sourceClosed)
          throw new IllegalStateException("closed");
        while (Pipe.this.buffer.size() == 0L)
        {
          if (Pipe.this.sinkClosed)
            return -1L;
          this.timeout.waitUntilNotified(Pipe.this.buffer);
        }
        paramLong = Pipe.this.buffer.read(paramBuffer, paramLong);
        Pipe.this.buffer.notifyAll();
        return paramLong;
      }
    }

    public Timeout timeout()
    {
      return this.timeout;
    }
  }
}