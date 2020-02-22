package org.apache.commons.net.smtp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.io.CRLFLineReader;

public class SMTP extends SocketClient
{
  public static final int DEFAULT_PORT = 25;
  private static final String __DEFAULT_ENCODING = "ISO-8859-1";
  protected ProtocolCommandSupport _commandSupport_;
  private boolean _newReplyString;
  BufferedReader _reader;
  private int _replyCode;
  private final ArrayList<String> _replyLines;
  private String _replyString;
  BufferedWriter _writer;
  private final String encoding;

  public SMTP()
  {
    this("ISO-8859-1");
  }

  public SMTP(String paramString)
  {
    setDefaultPort(25);
    this._replyLines = new ArrayList();
    this._newReplyString = false;
    this._replyString = null;
    this._commandSupport_ = new ProtocolCommandSupport(this);
    this.encoding = paramString;
  }

  private void __getReply()
    throws IOException
  {
    this._newReplyString = true;
    this._replyLines.clear();
    String str = this._reader.readLine();
    if (str == null)
      throw new SMTPConnectionClosedException("Connection closed without indication.");
    int i = str.length();
    StringBuilder localStringBuilder;
    if (i < 3)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Truncated server reply: ");
      localStringBuilder.append(str);
      throw new MalformedServerReplyException(localStringBuilder.toString());
    }
    try
    {
      this._replyCode = Integer.parseInt(str.substring(0, 3));
      this._replyLines.add(str);
      if ((i > 3) && (str.charAt(3) == '-'))
        do
        {
          str = this._reader.readLine();
          if (str == null)
            throw new SMTPConnectionClosedException("Connection closed without indication.");
          this._replyLines.add(str);
        }
        while ((str.length() < 4) || (str.charAt(3) == '-') || (!Character.isDigit(str.charAt(0))));
      fireReplyReceived(this._replyCode, getReplyString());
      if (this._replyCode == 421)
        throw new SMTPConnectionClosedException("SMTP response 421 received.  Server closed connection.");
      return;
      label207: localStringBuilder = new StringBuilder();
      localStringBuilder.append("Could not parse response code.\nServer Reply: ");
      localStringBuilder.append(str);
      throw new MalformedServerReplyException(localStringBuilder.toString());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label207;
    }
  }

  private int __sendCommand(int paramInt, String paramString, boolean paramBoolean)
    throws IOException
  {
    return __sendCommand(SMTPCommand.getCommand(paramInt), paramString, paramBoolean);
  }

  private int __sendCommand(String paramString1, String paramString2, boolean paramBoolean)
    throws IOException
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString1);
    if (paramString2 != null)
    {
      if (paramBoolean)
        ((StringBuilder)localObject).append(' ');
      ((StringBuilder)localObject).append(paramString2);
    }
    ((StringBuilder)localObject).append("\r\n");
    paramString2 = this._writer;
    localObject = ((StringBuilder)localObject).toString();
    paramString2.write((String)localObject);
    this._writer.flush();
    fireCommandSent(paramString1, (String)localObject);
    __getReply();
    return this._replyCode;
  }

  protected void _connectAction_()
    throws IOException
  {
    super._connectAction_();
    this._reader = new CRLFLineReader(new InputStreamReader(this._input_, this.encoding));
    this._writer = new BufferedWriter(new OutputStreamWriter(this._output_, this.encoding));
    __getReply();
  }

  public int data()
    throws IOException
  {
    return sendCommand(3);
  }

  public void disconnect()
    throws IOException
  {
    super.disconnect();
    this._reader = null;
    this._writer = null;
    this._replyString = null;
    this._replyLines.clear();
    this._newReplyString = false;
  }

  public int expn(String paramString)
    throws IOException
  {
    return sendCommand(9, paramString);
  }

  protected ProtocolCommandSupport getCommandSupport()
  {
    return this._commandSupport_;
  }

  public int getReply()
    throws IOException
  {
    __getReply();
    return this._replyCode;
  }

  public int getReplyCode()
  {
    return this._replyCode;
  }

  public String getReplyString()
  {
    if (!this._newReplyString)
      return this._replyString;
    Object localObject = new StringBuilder();
    Iterator localIterator = this._replyLines.iterator();
    while (localIterator.hasNext())
    {
      ((StringBuilder)localObject).append((String)localIterator.next());
      ((StringBuilder)localObject).append("\r\n");
    }
    this._newReplyString = false;
    localObject = ((StringBuilder)localObject).toString();
    this._replyString = ((String)localObject);
    return localObject;
  }

  public String[] getReplyStrings()
  {
    return (String[])this._replyLines.toArray(new String[this._replyLines.size()]);
  }

  public int helo(String paramString)
    throws IOException
  {
    return sendCommand(0, paramString);
  }

  public int help()
    throws IOException
  {
    return sendCommand(10);
  }

  public int help(String paramString)
    throws IOException
  {
    return sendCommand(10, paramString);
  }

  public int mail(String paramString)
    throws IOException
  {
    return __sendCommand(1, paramString, false);
  }

  public int noop()
    throws IOException
  {
    return sendCommand(11);
  }

  public int quit()
    throws IOException
  {
    return sendCommand(13);
  }

  public int rcpt(String paramString)
    throws IOException
  {
    return __sendCommand(2, paramString, false);
  }

  public void removeProtocolCommandistener(ProtocolCommandListener paramProtocolCommandListener)
  {
    removeProtocolCommandListener(paramProtocolCommandListener);
  }

  public int rset()
    throws IOException
  {
    return sendCommand(7);
  }

  public int saml(String paramString)
    throws IOException
  {
    return sendCommand(6, paramString);
  }

  public int send(String paramString)
    throws IOException
  {
    return sendCommand(4, paramString);
  }

  public int sendCommand(int paramInt)
    throws IOException
  {
    return sendCommand(paramInt, null);
  }

  public int sendCommand(int paramInt, String paramString)
    throws IOException
  {
    return sendCommand(SMTPCommand.getCommand(paramInt), paramString);
  }

  public int sendCommand(String paramString)
    throws IOException
  {
    return sendCommand(paramString, null);
  }

  public int sendCommand(String paramString1, String paramString2)
    throws IOException
  {
    return __sendCommand(paramString1, paramString2, true);
  }

  public int soml(String paramString)
    throws IOException
  {
    return sendCommand(5, paramString);
  }

  public int turn()
    throws IOException
  {
    return sendCommand(12);
  }

  public int vrfy(String paramString)
    throws IOException
  {
    return sendCommand(8, paramString);
  }
}