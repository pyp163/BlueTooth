package org.apache.commons.net.finger;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.net.SocketClient;

public class FingerClient extends SocketClient
{
  public static final int DEFAULT_PORT = 79;
  private static final String __LONG_FLAG = "/W ";
  private transient char[] __buffer = new char[1024];

  public FingerClient()
  {
    setDefaultPort(79);
  }

  public InputStream getInputStream(boolean paramBoolean)
    throws IOException
  {
    return getInputStream(paramBoolean, "");
  }

  public InputStream getInputStream(boolean paramBoolean, String paramString)
    throws IOException
  {
    return getInputStream(paramBoolean, paramString, null);
  }

  public InputStream getInputStream(boolean paramBoolean, String paramString1, String paramString2)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    if (paramBoolean)
      localStringBuilder.append("/W ");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("\r\n");
    if (paramString2 == null)
      paramString1 = localStringBuilder.toString().getBytes();
    else
      paramString1 = localStringBuilder.toString().getBytes(paramString2);
    paramString2 = new DataOutputStream(new BufferedOutputStream(this._output_, 1024));
    paramString2.write(paramString1, 0, paramString1.length);
    paramString2.flush();
    return this._input_;
  }

  public String query(boolean paramBoolean)
    throws IOException
  {
    return query(paramBoolean, "");
  }

  public String query(boolean paramBoolean, String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(this.__buffer.length);
    paramString = new BufferedReader(new InputStreamReader(getInputStream(paramBoolean, paramString)));
    try
    {
      while (true)
      {
        int i = paramString.read(this.__buffer, 0, this.__buffer.length);
        if (i <= 0)
          return localStringBuilder.toString();
        localStringBuilder.append(this.__buffer, 0, i);
      }
    }
    finally
    {
      paramString.close();
    }
  }
}