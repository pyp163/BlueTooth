package org.apache.http.impl.auth;

import java.security.MessageDigest;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.params.AuthParams;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;

@NotThreadSafe
public class DigestScheme extends RFC2617Scheme
{
  private static final char[] HEXADECIMAL = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static final int QOP_AUTH = 2;
  private static final int QOP_AUTH_INT = 1;
  private static final int QOP_MISSING = 0;
  private static final int QOP_UNKNOWN = -1;
  private String a1;
  private String a2;
  private String cnonce;
  private boolean complete = false;
  private String lastNonce;
  private long nounceCount;

  public static String createCnonce()
  {
    SecureRandom localSecureRandom = new SecureRandom();
    byte[] arrayOfByte = new byte[8];
    localSecureRandom.nextBytes(arrayOfByte);
    return encode(arrayOfByte);
  }

  private Header createDigestHeader(Credentials paramCredentials)
    throws AuthenticationException
  {
    String str2 = getParameter("uri");
    String str3 = getParameter("realm");
    String str4 = getParameter("nonce");
    String str1 = getParameter("opaque");
    String str7 = getParameter("methodname");
    Object localObject2 = getParameter("algorithm");
    if (str2 == null)
      throw new IllegalStateException("URI may not be null");
    if (str3 == null)
      throw new IllegalStateException("Realm may not be null");
    if (str4 == null)
      throw new IllegalStateException("Nonce may not be null");
    Object localObject1 = getParameter("qop");
    int i;
    if (localObject1 != null)
    {
      localObject3 = new StringTokenizer((String)localObject1, ",");
      while (((StringTokenizer)localObject3).hasMoreTokens())
        if (((StringTokenizer)localObject3).nextToken().trim().equals("auth"))
        {
          i = 2;
          break label155;
        }
      i = -1;
    }
    else
    {
      i = 0;
    }
    label155: if (i == -1)
    {
      paramCredentials = new StringBuilder();
      paramCredentials.append("None of the qop methods is supported: ");
      paramCredentials.append((String)localObject1);
      throw new AuthenticationException(paramCredentials.toString());
    }
    localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = "MD5";
    Object localObject3 = getParameter("charset");
    localObject2 = localObject3;
    if (localObject3 == null)
      localObject2 = "ISO-8859-1";
    if (((String)localObject1).equalsIgnoreCase("MD5-sess"))
      localObject3 = "MD5";
    else
      localObject3 = localObject1;
    try
    {
      Object localObject4 = createMessageDigest((String)localObject3);
      String str6 = paramCredentials.getUserPrincipal().getName();
      paramCredentials = paramCredentials.getPassword();
      if (str4.equals(this.lastNonce))
      {
        this.nounceCount += 1L;
      }
      else
      {
        this.nounceCount = 1L;
        this.cnonce = null;
        this.lastNonce = str4;
      }
      StringBuilder localStringBuilder = new StringBuilder(256);
      new Formatter(localStringBuilder, Locale.US).format("%08x", new Object[] { Long.valueOf(this.nounceCount) });
      String str5 = localStringBuilder.toString();
      if (this.cnonce == null)
        this.cnonce = createCnonce();
      this.a1 = null;
      this.a2 = null;
      if (((String)localObject1).equalsIgnoreCase("MD5-sess"))
      {
        localStringBuilder.setLength(0);
        localStringBuilder.append(str6);
        localStringBuilder.append(':');
        localStringBuilder.append(str3);
        localStringBuilder.append(':');
        localStringBuilder.append(paramCredentials);
        paramCredentials = encode(((MessageDigest)localObject4).digest(EncodingUtils.getBytes(localStringBuilder.toString(), (String)localObject2)));
        localStringBuilder.setLength(0);
        localStringBuilder.append(paramCredentials);
        localStringBuilder.append(':');
        localStringBuilder.append(str4);
        localStringBuilder.append(':');
        localStringBuilder.append(this.cnonce);
        this.a1 = localStringBuilder.toString();
      }
      else
      {
        localStringBuilder.setLength(0);
        localStringBuilder.append(str6);
        localStringBuilder.append(':');
        localStringBuilder.append(str3);
        localStringBuilder.append(':');
        localStringBuilder.append(paramCredentials);
        this.a1 = localStringBuilder.toString();
      }
      localObject3 = localObject4;
      paramCredentials = encode(((MessageDigest)localObject3).digest(EncodingUtils.getBytes(this.a1, (String)localObject2)));
      if (i == 2)
      {
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append(str7);
        ((StringBuilder)localObject4).append(':');
        ((StringBuilder)localObject4).append(str2);
        this.a2 = ((StringBuilder)localObject4).toString();
      }
      else
      {
        if (i == 1)
          throw new AuthenticationException("qop-int method is not suppported");
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append(str7);
        ((StringBuilder)localObject4).append(':');
        ((StringBuilder)localObject4).append(str2);
        this.a2 = ((StringBuilder)localObject4).toString();
      }
      localObject2 = encode(((MessageDigest)localObject3).digest(EncodingUtils.getBytes(this.a2, (String)localObject2)));
      if (i == 0)
      {
        localStringBuilder.setLength(0);
        localStringBuilder.append(paramCredentials);
        localStringBuilder.append(':');
        localStringBuilder.append(str4);
        localStringBuilder.append(':');
        localStringBuilder.append((String)localObject2);
        paramCredentials = localStringBuilder.toString();
      }
      else
      {
        localStringBuilder.setLength(0);
        localStringBuilder.append(paramCredentials);
        localStringBuilder.append(':');
        localStringBuilder.append(str4);
        localStringBuilder.append(':');
        localStringBuilder.append(str5);
        localStringBuilder.append(':');
        localStringBuilder.append(this.cnonce);
        localStringBuilder.append(':');
        if (i == 1)
          paramCredentials = "auth-int";
        else
          paramCredentials = "auth";
        localStringBuilder.append(paramCredentials);
        localStringBuilder.append(':');
        localStringBuilder.append((String)localObject2);
        paramCredentials = localStringBuilder.toString();
      }
      paramCredentials = encode(((MessageDigest)localObject3).digest(EncodingUtils.getAsciiBytes(paramCredentials)));
      localObject2 = new CharArrayBuffer(128);
      if (isProxy())
        ((CharArrayBuffer)localObject2).append("Proxy-Authorization");
      else
        ((CharArrayBuffer)localObject2).append("Authorization");
      ((CharArrayBuffer)localObject2).append(": Digest ");
      localObject3 = new ArrayList(20);
      ((List)localObject3).add(new BasicNameValuePair("username", str6));
      ((List)localObject3).add(new BasicNameValuePair("realm", str3));
      ((List)localObject3).add(new BasicNameValuePair("nonce", str4));
      ((List)localObject3).add(new BasicNameValuePair("uri", str2));
      ((List)localObject3).add(new BasicNameValuePair("response", paramCredentials));
      if (i != 0)
      {
        if (i == 1)
          paramCredentials = "auth-int";
        else
          paramCredentials = "auth";
        ((List)localObject3).add(new BasicNameValuePair("qop", paramCredentials));
        ((List)localObject3).add(new BasicNameValuePair("nc", str5));
        ((List)localObject3).add(new BasicNameValuePair("cnonce", this.cnonce));
      }
      if (localObject1 != null)
        ((List)localObject3).add(new BasicNameValuePair("algorithm", (String)localObject1));
      if (str1 != null)
        ((List)localObject3).add(new BasicNameValuePair("opaque", str1));
      i = 0;
      while (i < ((List)localObject3).size())
      {
        paramCredentials = (BasicNameValuePair)((List)localObject3).get(i);
        if (i > 0)
          ((CharArrayBuffer)localObject2).append(", ");
        int j;
        if ((!"nc".equals(paramCredentials.getName())) && (!"qop".equals(paramCredentials.getName())))
          j = 0;
        else
          j = 1;
        BasicHeaderValueFormatter.DEFAULT.formatNameValuePair((CharArrayBuffer)localObject2, paramCredentials, j ^ 0x1);
        i += 1;
      }
      return new BufferedHeader((CharArrayBuffer)localObject2);
      label1289: paramCredentials = new StringBuilder();
      paramCredentials.append("Unsuppported digest algorithm: ");
      paramCredentials.append((String)localObject3);
      throw new AuthenticationException(paramCredentials.toString());
    }
    catch (UnsupportedDigestAlgorithmException paramCredentials)
    {
      break label1289;
    }
  }

  private static MessageDigest createMessageDigest(String paramString)
    throws UnsupportedDigestAlgorithmException
  {
    try
    {
      Object localObject = MessageDigest.getInstance(paramString);
      return localObject;
      label7: localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unsupported algorithm in HTTP Digest authentication: ");
      ((StringBuilder)localObject).append(paramString);
      throw new UnsupportedDigestAlgorithmException(((StringBuilder)localObject).toString());
    }
    catch (Exception localException)
    {
      break label7;
    }
  }

  private static String encode(byte[] paramArrayOfByte)
  {
    int j = paramArrayOfByte.length;
    char[] arrayOfChar = new char[j * 2];
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfByte[i];
      int m = paramArrayOfByte[i];
      int n = i * 2;
      arrayOfChar[n] = HEXADECIMAL[((m & 0xF0) >> 4)];
      arrayOfChar[(n + 1)] = HEXADECIMAL[(k & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }

  public Header authenticate(Credentials paramCredentials, HttpRequest paramHttpRequest)
    throws AuthenticationException
  {
    if (paramCredentials == null)
      throw new IllegalArgumentException("Credentials may not be null");
    if (paramHttpRequest == null)
      throw new IllegalArgumentException("HTTP request may not be null");
    getParameters().put("methodname", paramHttpRequest.getRequestLine().getMethod());
    getParameters().put("uri", paramHttpRequest.getRequestLine().getUri());
    if (getParameter("charset") == null)
    {
      paramHttpRequest = AuthParams.getCredentialCharset(paramHttpRequest.getParams());
      getParameters().put("charset", paramHttpRequest);
    }
    return createDigestHeader(paramCredentials);
  }

  String getA1()
  {
    return this.a1;
  }

  String getA2()
  {
    return this.a2;
  }

  String getCnonce()
  {
    return this.cnonce;
  }

  public String getSchemeName()
  {
    return "digest";
  }

  public boolean isComplete()
  {
    if ("true".equalsIgnoreCase(getParameter("stale")))
      return false;
    return this.complete;
  }

  public boolean isConnectionBased()
  {
    return false;
  }

  public void overrideParamter(String paramString1, String paramString2)
  {
    getParameters().put(paramString1, paramString2);
  }

  public void processChallenge(Header paramHeader)
    throws MalformedChallengeException
  {
    super.processChallenge(paramHeader);
    if (getParameter("realm") == null)
      throw new MalformedChallengeException("missing realm in challenge");
    if (getParameter("nonce") == null)
      throw new MalformedChallengeException("missing nonce in challenge");
    this.complete = true;
  }
}