package okhttp3.internal.http;

import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.connection.RealConnection;
import okio.Sink;
import okio.Source;

public abstract interface ExchangeCodec
{
  public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

  public abstract void cancel();

  public abstract RealConnection connection();

  public abstract Sink createRequestBody(Request paramRequest, long paramLong)
    throws IOException;

  public abstract void finishRequest()
    throws IOException;

  public abstract void flushRequest()
    throws IOException;

  public abstract Source openResponseBodySource(Response paramResponse)
    throws IOException;

  @Nullable
  public abstract Response.Builder readResponseHeaders(boolean paramBoolean)
    throws IOException;

  public abstract long reportedContentLength(Response paramResponse)
    throws IOException;

  public abstract Headers trailers()
    throws IOException;

  public abstract void writeRequestHeaders(Request paramRequest)
    throws IOException;
}