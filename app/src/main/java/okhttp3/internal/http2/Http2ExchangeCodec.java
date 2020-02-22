package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2ExchangeCodec
  implements ExchangeCodec
{
  private static final String CONNECTION = "connection";
  private static final String ENCODING = "encoding";
  private static final String HOST = "host";
  private static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(new String[] { "connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority" });
  private static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(new String[] { "connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade" });
  private static final String KEEP_ALIVE = "keep-alive";
  private static final String PROXY_CONNECTION = "proxy-connection";
  private static final String TE = "te";
  private static final String TRANSFER_ENCODING = "transfer-encoding";
  private static final String UPGRADE = "upgrade";
  private volatile boolean canceled;
  private final Interceptor.Chain chain;
  private final Http2Connection connection;
  private final Protocol protocol;
  private final RealConnection realConnection;
  private volatile Http2Stream stream;

  public Http2ExchangeCodec(OkHttpClient paramOkHttpClient, RealConnection paramRealConnection, Interceptor.Chain paramChain, Http2Connection paramHttp2Connection)
  {
    this.realConnection = paramRealConnection;
    this.chain = paramChain;
    this.connection = paramHttp2Connection;
    if (paramOkHttpClient.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE))
      paramOkHttpClient = Protocol.H2_PRIOR_KNOWLEDGE;
    else
      paramOkHttpClient = Protocol.HTTP_2;
    this.protocol = paramOkHttpClient;
  }

  public static List<Header> http2HeadersList(Request paramRequest)
  {
    Headers localHeaders = paramRequest.headers();
    ArrayList localArrayList = new ArrayList(localHeaders.size() + 4);
    localArrayList.add(new Header(Header.TARGET_METHOD, paramRequest.method()));
    localArrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(paramRequest.url())));
    String str = paramRequest.header("Host");
    if (str != null)
      localArrayList.add(new Header(Header.TARGET_AUTHORITY, str));
    localArrayList.add(new Header(Header.TARGET_SCHEME, paramRequest.url().scheme()));
    int i = 0;
    int j = localHeaders.size();
    while (i < j)
    {
      paramRequest = localHeaders.name(i).toLowerCase(Locale.US);
      if ((!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(paramRequest)) || ((paramRequest.equals("te")) && (localHeaders.value(i).equals("trailers"))))
        localArrayList.add(new Header(paramRequest, localHeaders.value(i)));
      i += 1;
    }
    return localArrayList;
  }

  public static Response.Builder readHttp2HeadersList(Headers paramHeaders, Protocol paramProtocol)
    throws IOException
  {
    Headers.Builder localBuilder = new Headers.Builder();
    int j = paramHeaders.size();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      String str2 = paramHeaders.name(i);
      String str1 = paramHeaders.value(i);
      Object localObject2;
      if (str2.equals(":status"))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("HTTP/1.1 ");
        ((StringBuilder)localObject1).append(str1);
        localObject2 = StatusLine.parse(((StringBuilder)localObject1).toString());
      }
      else
      {
        localObject2 = localObject1;
        if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(str2))
        {
          Internal.instance.addLenient(localBuilder, str2, str1);
          localObject2 = localObject1;
        }
      }
      i += 1;
      localObject1 = localObject2;
    }
    if (localObject1 == null)
      throw new ProtocolException("Expected ':status' header not present");
    return new Response.Builder().protocol(paramProtocol).code(((StatusLine)localObject1).code).message(((StatusLine)localObject1).message).headers(localBuilder.build());
  }

  public void cancel()
  {
    this.canceled = true;
    if (this.stream != null)
      this.stream.closeLater(ErrorCode.CANCEL);
  }

  public RealConnection connection()
  {
    return this.realConnection;
  }

  public Sink createRequestBody(Request paramRequest, long paramLong)
  {
    return this.stream.getSink();
  }

  public void finishRequest()
    throws IOException
  {
    this.stream.getSink().close();
  }

  public void flushRequest()
    throws IOException
  {
    this.connection.flush();
  }

  public Source openResponseBodySource(Response paramResponse)
  {
    return this.stream.getSource();
  }

  public Response.Builder readResponseHeaders(boolean paramBoolean)
    throws IOException
  {
    Response.Builder localBuilder = readHttp2HeadersList(this.stream.takeHeaders(), this.protocol);
    if ((paramBoolean) && (Internal.instance.code(localBuilder) == 100))
      return null;
    return localBuilder;
  }

  public long reportedContentLength(Response paramResponse)
  {
    return HttpHeaders.contentLength(paramResponse);
  }

  public Headers trailers()
    throws IOException
  {
    return this.stream.trailers();
  }

  public void writeRequestHeaders(Request paramRequest)
    throws IOException
  {
    if (this.stream != null)
      return;
    boolean bool;
    if (paramRequest.body() != null)
      bool = true;
    else
      bool = false;
    paramRequest = http2HeadersList(paramRequest);
    this.stream = this.connection.newStream(paramRequest, bool);
    if (this.canceled)
    {
      this.stream.closeLater(ErrorCode.CANCEL);
      throw new IOException("Canceled");
    }
    this.stream.readTimeout().timeout(this.chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
    this.stream.writeTimeout().timeout(this.chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
  }
}