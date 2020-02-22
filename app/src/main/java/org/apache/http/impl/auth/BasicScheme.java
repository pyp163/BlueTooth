package org.apache.http.impl.auth;

import java.security.Principal;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.params.AuthParams;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;

@NotThreadSafe
public class BasicScheme extends RFC2617Scheme
{
  private boolean complete = false;

  public static Header authenticate(Credentials paramCredentials, String paramString, boolean paramBoolean)
  {
    if (paramCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    if (paramString == null)
      throw new IllegalArgumentException("charset may not be null");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramCredentials.getUserPrincipal().getName());
    localStringBuilder.append(":");
    if (paramCredentials.getPassword() == null)
      paramCredentials = "null";
    else
      paramCredentials = paramCredentials.getPassword();
    localStringBuilder.append(paramCredentials);
    paramCredentials = Base64.encodeBase64(EncodingUtils.getBytes(localStringBuilder.toString(), paramString));
    paramString = new CharArrayBuffer(32);
    if (paramBoolean)
      paramString.append("Proxy-Authorization");
    else
      paramString.append("Authorization");
    paramString.append(": Basic ");
    paramString.append(paramCredentials, 0, paramCredentials.length);
    return new BufferedHeader(paramString);
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    if (paramCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    return authenticate(paramCredentials, AuthParams.getCredentialCharset(paramHttpRequest.getParams()), isProxy());
  }

  public String getSchemeName()
  {
    return "basic";
  }

  public boolean isComplete()
  {
    return this.complete;
  }

  public boolean isConnectionBased()
  {
    return false;
  }

  public void processChallenge(Header paramHeader)
    throws MalformedChallengeException
  {
    super.processChallenge(paramHeader);
    this.complete = true;
  }
}