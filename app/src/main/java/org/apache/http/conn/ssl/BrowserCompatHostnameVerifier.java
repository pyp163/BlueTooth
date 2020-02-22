package org.apache.http.conn.ssl;

import javax.net.ssl.SSLException;
import org.apache.http.annotation.Immutable;

@Immutable
public class BrowserCompatHostnameVerifier extends AbstractVerifier
{
  public final String toString()
  {
    return "BROWSER_COMPATIBLE";
  }

  public final void verify(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2)
    throws SSLException
  {
    verify(paramString, paramArrayOfString1, paramArrayOfString2, false);
  }
}