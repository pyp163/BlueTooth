package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandSupport;
import org.apache.commons.net.SocketClient;

public class FTP extends SocketClient
{
  public static final int ASCII_FILE_TYPE = 0;
  public static final int BINARY_FILE_TYPE = 2;
  public static final int BLOCK_TRANSFER_MODE = 11;
  public static final int CARRIAGE_CONTROL_TEXT_FORMAT = 6;
  public static final int COMPRESSED_TRANSFER_MODE = 12;
  public static final String DEFAULT_CONTROL_ENCODING = "ISO-8859-1";
  public static final int DEFAULT_DATA_PORT = 20;
  public static final int DEFAULT_PORT = 21;
  public static final int EBCDIC_FILE_TYPE = 1;
  public static final int FILE_STRUCTURE = 7;
  public static final int LOCAL_FILE_TYPE = 3;
  public static final int NON_PRINT_TEXT_FORMAT = 4;
  public static final int PAGE_STRUCTURE = 9;
  public static final int RECORD_STRUCTURE = 8;
  public static final int STREAM_TRANSFER_MODE = 10;
  public static final int TELNET_TEXT_FORMAT = 5;
  private static final String __modes = "AEILNTCFRPSBC";
  protected ProtocolCommandSupport _commandSupport_;
  protected String _controlEncoding;
  protected BufferedReader _controlInput_;
  protected BufferedWriter _controlOutput_;
  protected boolean _newReplyString;
  protected int _replyCode;
  protected ArrayList<String> _replyLines;
  protected String _replyString;
  protected boolean strictMultilineParsing = false;

  public FTP()
  {
    setDefaultPort(21);
    this._replyLines = new ArrayList();
    this._newReplyString = false;
    this._replyString = null;
    this._controlEncoding = "ISO-8859-1";
    this._commandSupport_ = new ProtocolCommandSupport(this);
  }

  private String __buildMessage(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    if (paramString2 != null)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(paramString2);
    }
    localStringBuilder.append("\r\n");
    return localStringBuilder.toString();
  }

  private void __getReply()
    throws IOException
  {
    __getReply(true);
  }

  private void __getReply(boolean paramBoolean)
    throws IOException
  {
    this._newReplyString = true;
    this._replyLines.clear();
    String str = this._controlInput_.readLine();
    if (str == null)
      throw new FTPConnectionClosedException("Connection closed without indication.");
    int i = str.length();
    Object localObject;
    if (i < 3)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Truncated server reply: ");
      ((StringBuilder)localObject).append(str);
      throw new MalformedServerReplyException(((StringBuilder)localObject).toString());
    }
    try
    {
      localObject = str.substring(0, 3);
      this._replyCode = Integer.parseInt((String)localObject);
      this._replyLines.add(str);
      if ((i > 3) && (str.charAt(3) == '-'))
        do
        {
          str = this._controlInput_.readLine();
          if (str == null)
            throw new FTPConnectionClosedException("Connection closed without indication.");
          this._replyLines.add(str);
        }
        while (isStrictMultilineParsing() ? __strictCheck(str, (String)localObject) : __lenientCheck(str));
      fireReplyReceived(this._replyCode, getReplyString());
      if (this._replyCode == 421)
        throw new FTPConnectionClosedException("FTP response 421 received.  Server closed connection.");
      return;
      label214: localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Could not parse response code.\nServer Reply: ");
      ((StringBuilder)localObject).append(str);
      throw new MalformedServerReplyException(((StringBuilder)localObject).toString());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label214;
    }
  }

  private boolean __lenientCheck(String paramString)
  {
    int i = paramString.length();
    boolean bool = false;
    if ((i < 4) || (paramString.charAt(3) == '-') || (!Character.isDigit(paramString.charAt(0))))
      bool = true;
    return bool;
  }

  private void __send(String paramString)
    throws IOException, FTPConnectionClosedException, SocketException
  {
    try
    {
      this._controlOutput_.write(paramString);
      this._controlOutput_.flush();
      return;
    }
    catch (SocketException paramString)
    {
      if (!isConnected())
        throw new FTPConnectionClosedException("Connection unexpectedly closed.");
    }
    throw paramString;
  }

  private boolean __strictCheck(String paramString1, String paramString2)
  {
    return (!paramString1.startsWith(paramString2)) || (paramString1.charAt(3) != ' ');
  }

  protected void __getReplyNoReport()
    throws IOException
  {
    __getReply(false);
  }

  protected void __noop()
    throws IOException
  {
    __send(__buildMessage(FTPCommand.getCommand(32), null));
    __getReplyNoReport();
  }

  // ERROR //
  protected void _connectAction_()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 230	org/apache/commons/net/SocketClient:_connectAction_	()V
    //   4: aload_0
    //   5: new 232	org/apache/commons/net/io/CRLFLineReader
    //   8: dup
    //   9: new 234	java/io/InputStreamReader
    //   12: dup
    //   13: aload_0
    //   14: getfield 238	org/apache/commons/net/ftp/FTP:_input_	Ljava/io/InputStream;
    //   17: aload_0
    //   18: invokevirtual 241	org/apache/commons/net/ftp/FTP:getControlEncoding	()Ljava/lang/String;
    //   21: invokespecial 244	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   24: invokespecial 247	org/apache/commons/net/io/CRLFLineReader:<init>	(Ljava/io/Reader;)V
    //   27: putfield 118	org/apache/commons/net/ftp/FTP:_controlInput_	Ljava/io/BufferedReader;
    //   30: aload_0
    //   31: new 197	java/io/BufferedWriter
    //   34: dup
    //   35: new 249	java/io/OutputStreamWriter
    //   38: dup
    //   39: aload_0
    //   40: getfield 253	org/apache/commons/net/ftp/FTP:_output_	Ljava/io/OutputStream;
    //   43: aload_0
    //   44: invokevirtual 241	org/apache/commons/net/ftp/FTP:getControlEncoding	()Ljava/lang/String;
    //   47: invokespecial 256	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   50: invokespecial 259	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   53: putfield 195	org/apache/commons/net/ftp/FTP:_controlOutput_	Ljava/io/BufferedWriter;
    //   56: aload_0
    //   57: getfield 262	org/apache/commons/net/ftp/FTP:connectTimeout	I
    //   60: ifle +83 -> 143
    //   63: aload_0
    //   64: getfield 266	org/apache/commons/net/ftp/FTP:_socket_	Ljava/net/Socket;
    //   67: invokevirtual 271	java/net/Socket:getSoTimeout	()I
    //   70: istore_1
    //   71: aload_0
    //   72: getfield 266	org/apache/commons/net/ftp/FTP:_socket_	Ljava/net/Socket;
    //   75: aload_0
    //   76: getfield 262	org/apache/commons/net/ftp/FTP:connectTimeout	I
    //   79: invokevirtual 274	java/net/Socket:setSoTimeout	(I)V
    //   82: aload_0
    //   83: invokespecial 276	org/apache/commons/net/ftp/FTP:__getReply	()V
    //   86: aload_0
    //   87: getfield 153	org/apache/commons/net/ftp/FTP:_replyCode	I
    //   90: invokestatic 282	org/apache/commons/net/ftp/FTPReply:isPositivePreliminary	(I)Z
    //   93: ifeq +7 -> 100
    //   96: aload_0
    //   97: invokespecial 276	org/apache/commons/net/ftp/FTP:__getReply	()V
    //   100: aload_0
    //   101: getfield 266	org/apache/commons/net/ftp/FTP:_socket_	Ljava/net/Socket;
    //   104: iload_1
    //   105: invokevirtual 274	java/net/Socket:setSoTimeout	(I)V
    //   108: return
    //   109: astore_2
    //   110: goto +23 -> 133
    //   113: astore_2
    //   114: new 107	java/io/IOException
    //   117: dup
    //   118: ldc_w 284
    //   121: invokespecial 285	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   124: astore_3
    //   125: aload_3
    //   126: aload_2
    //   127: invokevirtual 289	java/io/IOException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   130: pop
    //   131: aload_3
    //   132: athrow
    //   133: aload_0
    //   134: getfield 266	org/apache/commons/net/ftp/FTP:_socket_	Ljava/net/Socket;
    //   137: iload_1
    //   138: invokevirtual 274	java/net/Socket:setSoTimeout	(I)V
    //   141: aload_2
    //   142: athrow
    //   143: aload_0
    //   144: invokespecial 276	org/apache/commons/net/ftp/FTP:__getReply	()V
    //   147: aload_0
    //   148: getfield 153	org/apache/commons/net/ftp/FTP:_replyCode	I
    //   151: invokestatic 282	org/apache/commons/net/ftp/FTPReply:isPositivePreliminary	(I)Z
    //   154: ifeq +7 -> 161
    //   157: aload_0
    //   158: invokespecial 276	org/apache/commons/net/ftp/FTP:__getReply	()V
    //   161: return
    //
    // Exception table:
    //   from	to	target	type
    //   82	100	109	finally
    //   114	133	109	finally
    //   82	100	113	java/net/SocketTimeoutException
  }

  public int abor()
    throws IOException
  {
    return sendCommand(21);
  }

  public int acct(String paramString)
    throws IOException
  {
    return sendCommand(2, paramString);
  }

  public int allo(int paramInt)
    throws IOException
  {
    return sendCommand(17, Integer.toString(paramInt));
  }

  public int allo(int paramInt1, int paramInt2)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Integer.toString(paramInt1));
    localStringBuilder.append(" R ");
    localStringBuilder.append(Integer.toString(paramInt2));
    return sendCommand(17, localStringBuilder.toString());
  }

  public int appe(String paramString)
    throws IOException
  {
    return sendCommand(16, paramString);
  }

  public int cdup()
    throws IOException
  {
    return sendCommand(4);
  }

  public int cwd(String paramString)
    throws IOException
  {
    return sendCommand(3, paramString);
  }

  public int dele(String paramString)
    throws IOException
  {
    return sendCommand(22, paramString);
  }

  public void disconnect()
    throws IOException
  {
    super.disconnect();
    this._controlInput_ = null;
    this._controlOutput_ = null;
    this._newReplyString = false;
    this._replyString = null;
  }

  public int eprt(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str2 = paramInetAddress.getHostAddress();
    int i = str2.indexOf("%");
    String str1 = str2;
    if (i > 0)
      str1 = str2.substring(0, i);
    localStringBuilder.append("|");
    if ((paramInetAddress instanceof Inet4Address))
      localStringBuilder.append("1");
    else if ((paramInetAddress instanceof Inet6Address))
      localStringBuilder.append("2");
    localStringBuilder.append("|");
    localStringBuilder.append(str1);
    localStringBuilder.append("|");
    localStringBuilder.append(paramInt);
    localStringBuilder.append("|");
    return sendCommand(37, localStringBuilder.toString());
  }

  public int epsv()
    throws IOException
  {
    return sendCommand(36);
  }

  public int feat()
    throws IOException
  {
    return sendCommand(34);
  }

  protected ProtocolCommandSupport getCommandSupport()
  {
    return this._commandSupport_;
  }

  public String getControlEncoding()
  {
    return this._controlEncoding;
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
    Object localObject = new StringBuilder(256);
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

  public int help()
    throws IOException
  {
    return sendCommand(31);
  }

  public int help(String paramString)
    throws IOException
  {
    return sendCommand(31, paramString);
  }

  public boolean isStrictMultilineParsing()
  {
    return this.strictMultilineParsing;
  }

  public int list()
    throws IOException
  {
    return sendCommand(26);
  }

  public int list(String paramString)
    throws IOException
  {
    return sendCommand(26, paramString);
  }

  public int mdtm(String paramString)
    throws IOException
  {
    return sendCommand(33, paramString);
  }

  public int mfmt(String paramString1, String paramString2)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString2);
    localStringBuilder.append(" ");
    localStringBuilder.append(paramString1);
    return sendCommand(35, localStringBuilder.toString());
  }

  public int mkd(String paramString)
    throws IOException
  {
    return sendCommand(24, paramString);
  }

  public int mlsd()
    throws IOException
  {
    return sendCommand(38);
  }

  public int mlsd(String paramString)
    throws IOException
  {
    return sendCommand(38, paramString);
  }

  public int mlst()
    throws IOException
  {
    return sendCommand(39);
  }

  public int mlst(String paramString)
    throws IOException
  {
    return sendCommand(39, paramString);
  }

  public int mode(int paramInt)
    throws IOException
  {
    return sendCommand(12, "AEILNTCFRPSBC".substring(paramInt, paramInt + 1));
  }

  public int nlst()
    throws IOException
  {
    return sendCommand(27);
  }

  public int nlst(String paramString)
    throws IOException
  {
    return sendCommand(27, paramString);
  }

  public int noop()
    throws IOException
  {
    return sendCommand(32);
  }

  public int pass(String paramString)
    throws IOException
  {
    return sendCommand(1, paramString);
  }

  public int pasv()
    throws IOException
  {
    return sendCommand(9);
  }

  public int port(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(24);
    localStringBuilder.append(paramInetAddress.getHostAddress().replace('.', ','));
    localStringBuilder.append(',');
    localStringBuilder.append(paramInt >>> 8);
    localStringBuilder.append(',');
    localStringBuilder.append(paramInt & 0xFF);
    return sendCommand(8, localStringBuilder.toString());
  }

  public int pwd()
    throws IOException
  {
    return sendCommand(25);
  }

  public int quit()
    throws IOException
  {
    return sendCommand(7);
  }

  public int rein()
    throws IOException
  {
    return sendCommand(6);
  }

  public int rest(String paramString)
    throws IOException
  {
    return sendCommand(18, paramString);
  }

  public int retr(String paramString)
    throws IOException
  {
    return sendCommand(13, paramString);
  }

  public int rmd(String paramString)
    throws IOException
  {
    return sendCommand(23, paramString);
  }

  public int rnfr(String paramString)
    throws IOException
  {
    return sendCommand(19, paramString);
  }

  public int rnto(String paramString)
    throws IOException
  {
    return sendCommand(20, paramString);
  }

  public int sendCommand(int paramInt)
    throws IOException
  {
    return sendCommand(paramInt, null);
  }

  public int sendCommand(int paramInt, String paramString)
    throws IOException
  {
    return sendCommand(FTPCommand.getCommand(paramInt), paramString);
  }

  public int sendCommand(String paramString)
    throws IOException
  {
    return sendCommand(paramString, null);
  }

  public int sendCommand(String paramString1, String paramString2)
    throws IOException
  {
    if (this._controlOutput_ == null)
      throw new IOException("Connection is not open");
    paramString2 = __buildMessage(paramString1, paramString2);
    __send(paramString2);
    fireCommandSent(paramString1, paramString2);
    __getReply();
    return this._replyCode;
  }

  public void setControlEncoding(String paramString)
  {
    this._controlEncoding = paramString;
  }

  public void setStrictMultilineParsing(boolean paramBoolean)
  {
    this.strictMultilineParsing = paramBoolean;
  }

  public int site(String paramString)
    throws IOException
  {
    return sendCommand(28, paramString);
  }

  public int smnt(String paramString)
    throws IOException
  {
    return sendCommand(5, paramString);
  }

  public int stat()
    throws IOException
  {
    return sendCommand(30);
  }

  public int stat(String paramString)
    throws IOException
  {
    return sendCommand(30, paramString);
  }

  public int stor(String paramString)
    throws IOException
  {
    return sendCommand(14, paramString);
  }

  public int stou()
    throws IOException
  {
    return sendCommand(15);
  }

  public int stou(String paramString)
    throws IOException
  {
    return sendCommand(15, paramString);
  }

  public int stru(int paramInt)
    throws IOException
  {
    return sendCommand(11, "AEILNTCFRPSBC".substring(paramInt, paramInt + 1));
  }

  public int syst()
    throws IOException
  {
    return sendCommand(29);
  }

  public int type(int paramInt)
    throws IOException
  {
    return sendCommand(10, "AEILNTCFRPSBC".substring(paramInt, paramInt + 1));
  }

  public int type(int paramInt1, int paramInt2)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("AEILNTCFRPSBC".charAt(paramInt1));
    localStringBuilder.append(' ');
    if (paramInt1 == 3)
      localStringBuilder.append(paramInt2);
    else
      localStringBuilder.append("AEILNTCFRPSBC".charAt(paramInt2));
    return sendCommand(10, localStringBuilder.toString());
  }

  public int user(String paramString)
    throws IOException
  {
    return sendCommand(0, paramString);
  }
}