package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.net.util.Base64;

public class FTPHTTPClient extends FTPClient
{
  private final byte[] CRLF;
  private final Base64 base64 = new Base64();
  private String host;
  private int port;
  private final String proxyHost;
  private final String proxyPassword;
  private final int proxyPort;
  private final String proxyUsername;

  public FTPHTTPClient(String paramString, int paramInt)
  {
    this(paramString, paramInt, null, null);
  }

  public FTPHTTPClient(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    this.proxyHost = paramString1;
    this.proxyPort = paramInt;
    this.proxyUsername = paramString2;
    this.proxyPassword = paramString3;
    try
    {
      this.CRLF = "\r\n".getBytes(getControlEncoding());
      return;
    }
    catch (UnsupportedEncodingException paramString1)
    {
    }
    throw new RuntimeException(paramString1);
  }

  private void tunnelHandshake(String paramString, int paramInt, InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException, UnsupportedEncodingException
  {
    paramInputStream = new StringBuilder();
    paramInputStream.append("CONNECT ");
    paramInputStream.append(paramString);
    paramInputStream.append(":");
    paramInputStream.append(paramInt);
    paramInputStream.append(" HTTP/1.1");
    paramString = paramInputStream.toString();
    this._output_.write(paramString.getBytes(getControlEncoding()));
    this._output_.write(this.CRLF);
    if ((this.proxyUsername != null) && (this.proxyPassword != null))
    {
      paramString = new StringBuilder();
      paramString.append("Proxy-Authorization: Basic ");
      paramInputStream = this.base64;
      paramOutputStream = new StringBuilder();
      paramOutputStream.append(this.proxyUsername);
      paramOutputStream.append(":");
      paramOutputStream.append(this.proxyPassword);
      paramString.append(paramInputStream.encode(paramOutputStream.toString()));
      paramString.append("\r\n");
      paramString = paramString.toString();
      this._output_.write(paramString.getBytes("UTF-8"));
      this._output_.write(this.CRLF);
      paramInputStream = new ArrayList();
      paramOutputStream = new BufferedReader(new InputStreamReader(this._input_));
      for (paramString = paramOutputStream.readLine(); (paramString != null) && (paramString.length() > 0); paramString = paramOutputStream.readLine())
        paramInputStream.add(paramString);
      if (paramInputStream.size() == 0)
        throw new IOException("No response from proxy");
      paramString = (String)paramInputStream.get(0);
      if ((paramString.startsWith("HTTP/")) && (paramString.length() >= 12))
      {
        if (!"200".equals(paramString.substring(9, 12)))
        {
          paramString = new StringBuilder();
          paramString.append("HTTPTunnelConnector: connection failed\r\n");
          paramString.append("Response received from the proxy:\r\n");
          paramInputStream = paramInputStream.iterator();
          while (paramInputStream.hasNext())
          {
            paramString.append((String)paramInputStream.next());
            paramString.append("\r\n");
          }
          throw new IOException(paramString.toString());
        }
      }
      else
      {
        paramInputStream = new StringBuilder();
        paramInputStream.append("Invalid response from proxy: ");
        paramInputStream.append(paramString);
        throw new IOException(paramInputStream.toString());
      }
    }
  }

  protected Socket _openDataConnection_(int paramInt, String paramString)
    throws IOException
  {
    paramString = new Socket(this.host, this.port);
    InputStream localInputStream = paramString.getInputStream();
    OutputStream localOutputStream = paramString.getOutputStream();
    tunnelHandshake(this.host, this.port, localInputStream, localOutputStream);
    return paramString;
  }

  public void connect(String paramString, int paramInt)
    throws SocketException, IOException
  {
    this.host = paramString;
    this.port = paramInt;
    this._socket_ = new Socket(this.proxyHost, this.proxyPort);
    this._input_ = this._socket_.getInputStream();
    this._output_ = this._socket_.getOutputStream();
    try
    {
      tunnelHandshake(paramString, paramInt, this._input_, this._output_);
      return;
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Could not connect to ");
      localStringBuilder.append(paramString);
      paramString = new IOException(localStringBuilder.toString());
      paramString.initCause(localException);
    }
    throw paramString;
  }
}