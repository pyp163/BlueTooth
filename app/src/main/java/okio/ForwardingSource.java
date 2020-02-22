package okio;

import java.io.IOException;

public abstract class ForwardingSource
  implements Source
{
  private final Source delegate;

  public ForwardingSource(Source paramSource)
  {
    if (paramSource == null)
      throw new IllegalArgumentException("delegate == null");
    this.delegate = paramSource;
  }

  public void close()
    throws IOException
  {
    this.delegate.close();
  }

  public final Source delegate()
  {
    return this.delegate;
  }

  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    return this.delegate.read(paramBuffer, paramLong);
  }

  public Timeout timeout()
  {
    return this.delegate.timeout();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append("(");
    localStringBuilder.append(this.delegate.toString());
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}