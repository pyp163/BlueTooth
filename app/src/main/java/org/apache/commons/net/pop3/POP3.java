package org.apache.commons.net.pop3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.io.CRLFLineReader;

public class POP3 extends SocketClient
{
  public static final int AUTHORIZATION_STATE = 0;
  public static final int DEFAULT_PORT = 110;
  public static final int DISCONNECTED_STATE = -1;
  public static final int TRANSACTION_STATE = 1;
  public static final int UPDATE_STATE = 2;
  static final String _ERROR = "-ERR";
  static final String _OK = "+OK";
  static final String _OK_INT = "+ ";
  private static final String __DEFAULT_ENCODING = "ISO-8859-1";
  private int __popState;
  private BufferedWriter __writer;
  protected ProtocolCommandSupport _commandSupport_;
  String _lastReplyLine;
  BufferedReader _reader;
  int _replyCode;
  List<String> _replyLines;

  public POP3()
  {
    setDefaultPort(110);
    this.__popState = -1;
    this._reader = null;
    this.__writer = null;
    this._replyLines = new ArrayList();
    this._commandSupport_ = new ProtocolCommandSupport(this);
  }

  private void __getReply()
    throws IOException
  {
    this._replyLines.clear();
    String str = this._reader.readLine();
    if (str == null)
      throw new EOFException("Connection closed without indication.");
    if (str.startsWith("+OK"))
    {
      this._replyCode = 0;
    }
    else if (str.startsWith("-ERR"))
    {
      this._replyCode = 1;
    }
    else
    {
      if (!str.startsWith("+ "))
        break label108;
      this._replyCode = 2;
    }
    this._replyLines.add(str);
    this._lastReplyLine = str;
    fireReplyReceived(this._replyCode, getReplyString());
    return;
    label108: StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Received invalid POP3 protocol response from server.");
    localStringBuilder.append(str);
    throw new MalformedServerReplyException(localStringBuilder.toString());
  }

  protected void _connectAction_()
    throws IOException
  {
    super._connectAction_();
    this._reader = new CRLFLineReader(new InputStreamReader(this._input_, "ISO-8859-1"));
    this.__writer = new BufferedWriter(new OutputStreamWriter(this._output_, "ISO-8859-1"));
    __getReply();
    setState(0);
  }

  public void disconnect()
    throws IOException
  {
    super.disconnect();
    this._reader = null;
    this.__writer = null;
    this._lastReplyLine = null;
    this._replyLines.clear();
    setState(-1);
  }

  public void getAdditionalReply()
    throws IOException
  {
    for (String str = this._reader.readLine(); str != null; str = this._reader.readLine())
    {
      this._replyLines.add(str);
      if (str.equals("."))
        return;
    }
  }

  protected ProtocolCommandSupport getCommandSupport()
  {
    return this._commandSupport_;
  }

  public String getReplyString()
  {
    StringBuilder localStringBuilder = new StringBuilder(256);
    Iterator localIterator = this._replyLines.iterator();
    while (localIterator.hasNext())
    {
      localStringBuilder.append((String)localIterator.next());
      localStringBuilder.append("\r\n");
    }
    return localStringBuilder.toString();
  }

  public String[] getReplyStrings()
  {
    return (String[])this._replyLines.toArray(new String[this._replyLines.size()]);
  }

  public int getState()
  {
    return this.__popState;
  }

  public void removeProtocolCommandistener(ProtocolCommandListener paramProtocolCommandListener)
  {
    removeProtocolCommandListener(paramProtocolCommandListener);
  }

  public int sendCommand(int paramInt)
    throws IOException
  {
    return sendCommand(POP3Command._commands[paramInt], null);
  }

  public int sendCommand(int paramInt, String paramString)
    throws IOException
  {
    return sendCommand(POP3Command._commands[paramInt], paramString);
  }

  public int sendCommand(String paramString)
    throws IOException
  {
    return sendCommand(paramString, null);
  }

  public int sendCommand(String paramString1, String paramString2)
    throws IOException
  {
    if (this.__writer == null)
      throw new IllegalStateException("Socket is not connected");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    if (paramString2 != null)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(paramString2);
    }
    localStringBuilder.append("\r\n");
    paramString2 = localStringBuilder.toString();
    this.__writer.write(paramString2);
    this.__writer.flush();
    fireCommandSent(paramString1, paramString2);
    __getReply();
    return this._replyCode;
  }

  public void setState(int paramInt)
  {
    this.__popState = paramInt;
  }
}