package org.apache.commons.net.whois;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.finger.FingerClient;

public final class WhoisClient extends FingerClient
{
  public static final String DEFAULT_HOST = "whois.internic.net";
  public static final int DEFAULT_PORT = 43;

  public WhoisClient()
  {
    setDefaultPort(43);
  }

  public InputStream getInputStream(String paramString)
    throws IOException
  {
    return getInputStream(false, paramString);
  }

  public String query(String paramString)
    throws IOException
  {
    return query(false, paramString);
  }
}