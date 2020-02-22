package org.apache.http.params;

import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;

public final class HttpProtocolParams
  implements CoreProtocolPNames
{
  public static String getContentCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.protocol.content-charset");
    paramHttpParams = str;
    if (str == null)
      paramHttpParams = "ISO-8859-1";
    return paramHttpParams;
  }

  public static String getHttpElementCharset(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    String str = (String)paramHttpParams.getParameter("http.protocol.element-charset");
    paramHttpParams = str;
    if (str == null)
      paramHttpParams = "US-ASCII";
    return paramHttpParams;
  }

  public static String getUserAgent(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return (String)paramHttpParams.getParameter("http.useragent");
  }

  public static ProtocolVersion getVersion(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams = paramHttpParams.getParameter("http.protocol.version");
    if (paramHttpParams == null)
      return HttpVersion.HTTP_1_1;
    return (ProtocolVersion)paramHttpParams;
  }

  public static void setContentCharset(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.content-charset", paramString);
  }

  public static void setHttpElementCharset(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.element-charset", paramString);
  }

  public static void setUseExpectContinue(HttpParams paramHttpParams, boolean paramBoolean)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setBooleanParameter("http.protocol.expect-continue", paramBoolean);
  }

  public static void setUserAgent(HttpParams paramHttpParams, String paramString)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.useragent", paramString);
  }

  public static void setVersion(HttpParams paramHttpParams, ProtocolVersion paramProtocolVersion)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    paramHttpParams.setParameter("http.protocol.version", paramProtocolVersion);
  }

  public static boolean useExpectContinue(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("HTTP parameters may not be null");
    return paramHttpParams.getBooleanParameter("http.protocol.expect-continue", false);
  }
}