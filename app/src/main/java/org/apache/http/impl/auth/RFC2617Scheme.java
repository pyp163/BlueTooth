package org.apache.http.impl.auth;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HeaderElement;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.HeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

@NotThreadSafe
public abstract class RFC2617Scheme extends AuthSchemeBase
{
  private Map<String, String> params;

  public String getParameter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter name may not be null");
    if (this.params == null)
      return null;
    return (String)this.params.get(paramString.toLowerCase(Locale.ENGLISH));
  }

  protected Map<String, String> getParameters()
  {
    if (this.params == null)
      this.params = new HashMap();
    return this.params;
  }

  public String getRealm()
  {
    return getParameter("realm");
  }

  protected void parseChallenge(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
    throws MalformedChallengeException
  {
    paramCharArrayBuffer = BasicHeaderValueParser.DEFAULT.parseElements(paramCharArrayBuffer, new ParserCursor(paramInt1, paramCharArrayBuffer.length()));
    if (paramCharArrayBuffer.length == 0)
      throw new MalformedChallengeException("Authentication challenge is empty");
    this.params = new HashMap(paramCharArrayBuffer.length);
    paramInt2 = paramCharArrayBuffer.length;
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      Object localObject = paramCharArrayBuffer[paramInt1];
      this.params.put(localObject.getName(), localObject.getValue());
      paramInt1 += 1;
    }
  }
}