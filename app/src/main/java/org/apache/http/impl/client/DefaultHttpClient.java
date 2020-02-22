package org.apache.http.impl.client;

import org.apache.http.HttpVersion;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.protocol.RequestAddCookies;
import org.apache.http.client.protocol.RequestAuthCache;
import org.apache.http.client.protocol.RequestClientConnControl;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.apache.http.client.protocol.RequestProxyAuthentication;
import org.apache.http.client.protocol.RequestTargetAuthentication;
import org.apache.http.client.protocol.ResponseAuthCache;
import org.apache.http.client.protocol.ResponseProcessCookies;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.VersionInfo;

@ThreadSafe
public class DefaultHttpClient extends AbstractHttpClient
{
  public DefaultHttpClient()
  {
    super(null, null);
  }

  public DefaultHttpClient(ClientConnectionManager paramClientConnectionManager)
  {
    super(paramClientConnectionManager, null);
  }

  public DefaultHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    super(paramClientConnectionManager, paramHttpParams);
  }

  public DefaultHttpClient(HttpParams paramHttpParams)
  {
    super(null, paramHttpParams);
  }

  public static void setDefaultHttpParams(HttpParams paramHttpParams)
  {
    HttpProtocolParams.setVersion(paramHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(paramHttpParams, "ISO-8859-1");
    HttpConnectionParams.setTcpNoDelay(paramHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(paramHttpParams, 8192);
    Object localObject = VersionInfo.loadVersionInfo("org.apache.http.client", DefaultHttpClient.class.getClassLoader());
    if (localObject != null)
      localObject = ((VersionInfo)localObject).getRelease();
    else
      localObject = "UNAVAILABLE";
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Apache-HttpClient/");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(" (java 1.5)");
    HttpProtocolParams.setUserAgent(paramHttpParams, localStringBuilder.toString());
  }

  protected HttpParams createHttpParams()
  {
    SyncBasicHttpParams localSyncBasicHttpParams = new SyncBasicHttpParams();
    setDefaultHttpParams(localSyncBasicHttpParams);
    return localSyncBasicHttpParams;
  }

  protected BasicHttpProcessor createHttpProcessor()
  {
    BasicHttpProcessor localBasicHttpProcessor = new BasicHttpProcessor();
    localBasicHttpProcessor.addInterceptor(new RequestDefaultHeaders());
    localBasicHttpProcessor.addInterceptor(new RequestContent());
    localBasicHttpProcessor.addInterceptor(new RequestTargetHost());
    localBasicHttpProcessor.addInterceptor(new RequestClientConnControl());
    localBasicHttpProcessor.addInterceptor(new RequestUserAgent());
    localBasicHttpProcessor.addInterceptor(new RequestExpectContinue());
    localBasicHttpProcessor.addInterceptor(new RequestAddCookies());
    localBasicHttpProcessor.addInterceptor(new ResponseProcessCookies());
    localBasicHttpProcessor.addInterceptor(new RequestAuthCache());
    localBasicHttpProcessor.addInterceptor(new ResponseAuthCache());
    localBasicHttpProcessor.addInterceptor(new RequestTargetAuthentication());
    localBasicHttpProcessor.addInterceptor(new RequestProxyAuthentication());
    return localBasicHttpProcessor;
  }
}