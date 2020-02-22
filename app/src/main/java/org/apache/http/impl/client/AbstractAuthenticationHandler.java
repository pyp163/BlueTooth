package org.apache.http.impl.client;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;

@Immutable
public abstract class AbstractAuthenticationHandler
  implements AuthenticationHandler
{
  private static final List<String> DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(new String[] { "negotiate", "NTLM", "Digest", "Basic" }));
  private final Log log = LogFactory.getLog(getClass());

  protected List<String> getAuthPreferences()
  {
    return DEFAULT_SCHEME_PRIORITY;
  }

  protected List<String> getAuthPreferences(HttpResponse paramHttpResponse, HttpContext paramHttpContext)
  {
    return getAuthPreferences();
  }

  protected Map<String, Header> parseChallenges(Header[] paramArrayOfHeader)
    throws MalformedChallengeException
  {
    HashMap localHashMap = new HashMap(paramArrayOfHeader.length);
    int m = paramArrayOfHeader.length;
    int j = 0;
    while (j < m)
    {
      Header localHeader = paramArrayOfHeader[j];
      Object localObject;
      CharArrayBuffer localCharArrayBuffer;
      int i;
      if ((localHeader instanceof FormattedHeader))
      {
        localObject = (FormattedHeader)localHeader;
        localCharArrayBuffer = ((FormattedHeader)localObject).getBuffer();
        i = ((FormattedHeader)localObject).getValuePos();
      }
      else
      {
        localObject = localHeader.getValue();
        if (localObject == null)
          throw new MalformedChallengeException("Header value is null");
        localCharArrayBuffer = new CharArrayBuffer(((String)localObject).length());
        localCharArrayBuffer.append((String)localObject);
        i = 0;
      }
      while ((i < localCharArrayBuffer.length()) && (HTTP.isWhitespace(localCharArrayBuffer.charAt(i))))
        i += 1;
      int k = i;
      while ((k < localCharArrayBuffer.length()) && (!HTTP.isWhitespace(localCharArrayBuffer.charAt(k))))
        k += 1;
      localHashMap.put(localCharArrayBuffer.substring(i, k).toLowerCase(Locale.ENGLISH), localHeader);
      j += 1;
    }
    return localHashMap;
  }

  public AuthScheme selectScheme(Map<String, Header> paramMap, HttpResponse paramHttpResponse, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    AuthSchemeRegistry localAuthSchemeRegistry = (AuthSchemeRegistry)paramHttpContext.getAttribute("http.authscheme-registry");
    if (localAuthSchemeRegistry == null)
      throw new IllegalStateException("AuthScheme registry not set in HTTP context");
    Object localObject1 = getAuthPreferences(paramHttpResponse, paramHttpContext);
    paramHttpContext = (HttpContext)localObject1;
    if (localObject1 == null)
      paramHttpContext = DEFAULT_SCHEME_PRIORITY;
    if (this.log.isDebugEnabled())
    {
      localObject1 = this.log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Authentication schemes in the order of preference: ");
      ((StringBuilder)localObject2).append(paramHttpContext);
      ((Log)localObject1).debug(((StringBuilder)localObject2).toString());
    }
    localObject1 = null;
    Object localObject2 = paramHttpContext.iterator();
    while (true)
    {
      paramHttpContext = (HttpContext)localObject1;
      String str;
      StringBuilder localStringBuilder;
      if (((Iterator)localObject2).hasNext())
      {
        str = (String)((Iterator)localObject2).next();
        if ((Header)paramMap.get(str.toLowerCase(Locale.ENGLISH)) != null)
          if (this.log.isDebugEnabled())
          {
            paramHttpContext = this.log;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(str);
            localStringBuilder.append(" authentication scheme selected");
            paramHttpContext.debug(localStringBuilder.toString());
          }
      }
      try
      {
        paramHttpContext = localAuthSchemeRegistry.getAuthScheme(str, paramHttpResponse.getParams());
        break label356;
        label228: if (!this.log.isWarnEnabled())
          continue;
        paramHttpContext = this.log;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Authentication scheme ");
        localStringBuilder.append(str);
        localStringBuilder.append(" not supported");
        paramHttpContext.warn(localStringBuilder.toString());
        continue;
        if (!this.log.isDebugEnabled())
          continue;
        paramHttpContext = this.log;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Challenge for ");
        localStringBuilder.append(str);
        localStringBuilder.append(" authentication scheme not available");
        paramHttpContext.debug(localStringBuilder.toString());
        continue;
        label356: if (paramHttpContext == null)
        {
          paramHttpResponse = new StringBuilder();
          paramHttpResponse.append("Unable to respond to any of these challenges: ");
          paramHttpResponse.append(paramMap);
          throw new AuthenticationException(paramHttpResponse.toString());
        }
        return paramHttpContext;
      }
      catch (IllegalStateException paramHttpContext)
      {
        break label228;
      }
    }
  }
}