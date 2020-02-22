package org.apache.commons.net.nntp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.io.CRLFLineReader;

public class NNTP extends SocketClient
{
  public static final int DEFAULT_PORT = 119;
  private static final String __DEFAULT_ENCODING = "ISO-8859-1";
  protected ProtocolCommandSupport _commandSupport_;
  boolean _isAllowedToPost;
  protected BufferedReader _reader_;
  int _replyCode;
  String _replyString;
  protected BufferedWriter _writer_;

  public NNTP()
  {
    setDefaultPort(119);
    this._replyString = null;
    this._reader_ = null;
    this._writer_ = null;
    this._isAllowedToPost = false;
    this._commandSupport_ = new ProtocolCommandSupport(this);
  }

  private void __getReply()
    throws IOException
  {
    this._replyString = this._reader_.readLine();
    if (this._replyString == null)
      throw new NNTPConnectionClosedException("Connection closed without indication.");
    StringBuilder localStringBuilder;
    if (this._replyString.length() < 3)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Truncated server reply: ");
      localStringBuilder.append(this._replyString);
      throw new MalformedServerReplyException(localStringBuilder.toString());
    }
    try
    {
      this._replyCode = Integer.parseInt(this._replyString.substring(0, 3));
      int i = this._replyCode;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(this._replyString);
      localStringBuilder.append("\r\n");
      fireReplyReceived(i, localStringBuilder.toString());
      if (this._replyCode == 400)
        throw new NNTPConnectionClosedException("NNTP response 400 received.  Server closed connection.");
      return;
      label150: localStringBuilder = new StringBuilder();
      localStringBuilder.append("Could not parse response code.\nServer Reply: ");
      localStringBuilder.append(this._replyString);
      throw new MalformedServerReplyException(localStringBuilder.toString());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label150;
    }
  }

  protected void _connectAction_()
    throws IOException
  {
    super._connectAction_();
    this._reader_ = new CRLFLineReader(new InputStreamReader(this._input_, "ISO-8859-1"));
    this._writer_ = new BufferedWriter(new OutputStreamWriter(this._output_, "ISO-8859-1"));
    __getReply();
    boolean bool;
    if (this._replyCode == 200)
      bool = true;
    else
      bool = false;
    this._isAllowedToPost = bool;
  }

  public int article()
    throws IOException
  {
    return sendCommand(0);
  }

  @Deprecated
  public int article(int paramInt)
    throws IOException
  {
    return article(paramInt);
  }

  public int article(long paramLong)
    throws IOException
  {
    return sendCommand(0, Long.toString(paramLong));
  }

  public int article(String paramString)
    throws IOException
  {
    return sendCommand(0, paramString);
  }

  public int authinfoPass(String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PASS ");
    localStringBuilder.append(paramString);
    return sendCommand(15, localStringBuilder.toString());
  }

  public int authinfoUser(String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("USER ");
    localStringBuilder.append(paramString);
    return sendCommand(15, localStringBuilder.toString());
  }

  public int body()
    throws IOException
  {
    return sendCommand(1);
  }

  @Deprecated
  public int body(int paramInt)
    throws IOException
  {
    return body(paramInt);
  }

  public int body(long paramLong)
    throws IOException
  {
    return sendCommand(1, Long.toString(paramLong));
  }

  public int body(String paramString)
    throws IOException
  {
    return sendCommand(1, paramString);
  }

  public void disconnect()
    throws IOException
  {
    super.disconnect();
    this._reader_ = null;
    this._writer_ = null;
    this._replyString = null;
    this._isAllowedToPost = false;
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
    return this._replyString;
  }

  public int group(String paramString)
    throws IOException
  {
    return sendCommand(2, paramString);
  }

  public int head()
    throws IOException
  {
    return sendCommand(3);
  }

  @Deprecated
  public int head(int paramInt)
    throws IOException
  {
    return head(paramInt);
  }

  public int head(long paramLong)
    throws IOException
  {
    return sendCommand(3, Long.toString(paramLong));
  }

  public int head(String paramString)
    throws IOException
  {
    return sendCommand(3, paramString);
  }

  public int help()
    throws IOException
  {
    return sendCommand(4);
  }

  public int ihave(String paramString)
    throws IOException
  {
    return sendCommand(5, paramString);
  }

  public boolean isAllowedToPost()
  {
    return this._isAllowedToPost;
  }

  public int last()
    throws IOException
  {
    return sendCommand(6);
  }

  public int list()
    throws IOException
  {
    return sendCommand(7);
  }

  public int listActive(String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder("ACTIVE ");
    localStringBuilder.append(paramString);
    return sendCommand(7, localStringBuilder.toString());
  }

  public int newgroups(String paramString1, String paramString2, boolean paramBoolean, String paramString3)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append(' ');
    localStringBuilder.append(paramString2);
    if (paramBoolean)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append("GMT");
    }
    if (paramString3 != null)
    {
      localStringBuilder.append(" <");
      localStringBuilder.append(paramString3);
      localStringBuilder.append('>');
    }
    return sendCommand(8, localStringBuilder.toString());
  }

  public int newnews(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append(' ');
    localStringBuilder.append(paramString2);
    localStringBuilder.append(' ');
    localStringBuilder.append(paramString3);
    if (paramBoolean)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append("GMT");
    }
    if (paramString4 != null)
    {
      localStringBuilder.append(" <");
      localStringBuilder.append(paramString4);
      localStringBuilder.append('>');
    }
    return sendCommand(9, localStringBuilder.toString());
  }

  public int next()
    throws IOException
  {
    return sendCommand(10);
  }

  public int post()
    throws IOException
  {
    return sendCommand(11);
  }

  public int quit()
    throws IOException
  {
    return sendCommand(12);
  }

  public int sendCommand(int paramInt)
    throws IOException
  {
    return sendCommand(paramInt, null);
  }

  public int sendCommand(int paramInt, String paramString)
    throws IOException
  {
    return sendCommand(NNTPCommand.getCommand(paramInt), paramString);
  }

  public int sendCommand(String paramString)
    throws IOException
  {
    return sendCommand(paramString, null);
  }

  public int sendCommand(String paramString1, String paramString2)
    throws IOException
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramString1);
    if (paramString2 != null)
    {
      ((StringBuilder)localObject).append(' ');
      ((StringBuilder)localObject).append(paramString2);
    }
    ((StringBuilder)localObject).append("\r\n");
    paramString2 = this._writer_;
    localObject = ((StringBuilder)localObject).toString();
    paramString2.write((String)localObject);
    this._writer_.flush();
    fireCommandSent(paramString1, (String)localObject);
    __getReply();
    return this._replyCode;
  }

  public int stat()
    throws IOException
  {
    return sendCommand(14);
  }

  @Deprecated
  public int stat(int paramInt)
    throws IOException
  {
    return stat(paramInt);
  }

  public int stat(long paramLong)
    throws IOException
  {
    return sendCommand(14, Long.toString(paramLong));
  }

  public int stat(String paramString)
    throws IOException
  {
    return sendCommand(14, paramString);
  }

  public int xhdr(String paramString1, String paramString2)
    throws IOException
  {
    paramString1 = new StringBuilder(paramString1);
    paramString1.append(" ");
    paramString1.append(paramString2);
    return sendCommand(17, paramString1.toString());
  }

  public int xover(String paramString)
    throws IOException
  {
    return sendCommand(16, paramString);
  }
}