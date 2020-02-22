package org.apache.commons.net.bsd;

import java.io.IOException;

public class RLoginClient extends RCommandClient
{
  public static final int DEFAULT_PORT = 513;

  public RLoginClient()
  {
    setDefaultPort(513);
  }

  public void rlogin(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    rexec(paramString1, paramString2, paramString3, false);
  }

  public void rlogin(String paramString1, String paramString2, String paramString3, int paramInt)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString3);
    localStringBuilder.append("/");
    localStringBuilder.append(paramInt);
    rexec(paramString1, paramString2, localStringBuilder.toString(), false);
  }
}