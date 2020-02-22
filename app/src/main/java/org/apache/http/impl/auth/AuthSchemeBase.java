package org.apache.http.impl.auth;

import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.ContextAwareAuthScheme;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class AuthSchemeBase
  implements ContextAwareAuthScheme
{
  private boolean proxy;

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest, HttpContext paramHttpContext)
    throws AuthenticationException
  {
    return authenticate(paramCredentials, paramHttpRequest);
  }

  public boolean isProxy()
  {
    return this.proxy;
  }

  protected abstract void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException;

  public void processChallenge(Header paramHeader)
    throws MalformedChallengeException
  {
    if (paramHeader == null)
      throw new IllegalArgumentException("Header may not be null");
    Object localObject = paramHeader.getName();
    boolean bool = ((String)localObject).equalsIgnoreCase("WWW-Authenticate");
    int i = 0;
    if (bool)
    {
      this.proxy = false;
    }
    else
    {
      if (!((String)localObject).equalsIgnoreCase("Proxy-Authenticate"))
        break label254;
      this.proxy = true;
    }
    if ((paramHeader instanceof FormattedHeader))
    {
      localObject = (FormattedHeader)paramHeader;
      paramHeader = ((FormattedHeader)localObject).getBuffer();
      i = ((FormattedHeader)localObject).getValuePos();
    }
    else
    {
      localObject = paramHeader.getValue();
      if (localObject == null)
        throw new MalformedChallengeException("Header value is null");
      paramHeader = new CharArrayBuffer(((String)localObject).length());
      paramHeader.append((String)localObject);
    }
    while ((i < paramHeader.length()) && (HTTP.isWhitespace(paramHeader.charAt(i))))
      i += 1;
    int j = i;
    while ((j < paramHeader.length()) && (!HTTP.isWhitespace(paramHeader.charAt(j))))
      j += 1;
    localObject = paramHeader.substring(i, j);
    if (!((String)localObject).equalsIgnoreCase(getSchemeName()))
    {
      paramHeader = new StringBuilder();
      paramHeader.append("Invalid scheme identifier: ");
      paramHeader.append((String)localObject);
      throw new MalformedChallengeException(paramHeader.toString());
    }
    parseChallenge(paramHeader, j, paramHeader.length());
    return;
    label254: paramHeader = new StringBuilder();
    paramHeader.append("Unexpected header name: ");
    paramHeader.append((String)localObject);
    throw new MalformedChallengeException(paramHeader.toString());
  }

  public String toString()
  {
    return getSchemeName();
  }
}