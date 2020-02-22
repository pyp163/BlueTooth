package org.apache.commons.net.daytime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.net.SocketClient;

public final class DaytimeTCPClient extends SocketClient
{
  public static final int DEFAULT_PORT = 13;
  private final char[] __buffer = new char[64];

  public DaytimeTCPClient()
  {
    setDefaultPort(13);
  }

  public String getTime()
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(this.__buffer.length);
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(this._input_));
    while (true)
    {
      int i = localBufferedReader.read(this.__buffer, 0, this.__buffer.length);
      if (i <= 0)
        return localStringBuilder.toString();
      localStringBuilder.append(this.__buffer, 0, i);
    }
  }
}