package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.commons.net.util.Base64;
import org.apache.commons.net.util.SSLContextUtils;
import org.apache.commons.net.util.TrustManagerUtils;

public class FTPSClient extends FTPClient
{
  private static final String CMD_ADAT = "ADAT";
  private static final String CMD_AUTH = "AUTH";
  private static final String CMD_CCC = "CCC";
  private static final String CMD_CONF = "CONF";
  private static final String CMD_ENC = "ENC";
  private static final String CMD_MIC = "MIC";
  private static final String CMD_PBSZ = "PBSZ";
  private static final String CMD_PROT = "PROT";
  public static final int DEFAULT_FTPS_DATA_PORT = 989;
  public static final int DEFAULT_FTPS_PORT = 990;
  private static final String DEFAULT_PROT = "C";
  private static final String DEFAULT_PROTOCOL = "TLS";

  @Deprecated
  public static String KEYSTORE_ALGORITHM;
  private static final String[] PROT_COMMAND_VALUE = { "C", "E", "S", "P" };

  @Deprecated
  public static String PROVIDER;

  @Deprecated
  public static String STORE_TYPE;

  @Deprecated
  public static String TRUSTSTORE_ALGORITHM;
  private String auth = "TLS";
  private SSLContext context;
  private boolean isClientMode = true;
  private boolean isCreation = true;
  private final boolean isImplicit;
  private boolean isNeedClientAuth = false;
  private boolean isWantClientAuth = false;
  private KeyManager keyManager = null;
  private Socket plainSocket;
  private final String protocol;
  private String[] protocols = null;
  private String[] suites = null;
  private TrustManager trustManager = TrustManagerUtils.getValidateServerCertificateTrustManager();

  public FTPSClient()
  {
    this("TLS", false);
  }

  public FTPSClient(String paramString)
  {
    this(paramString, false);
  }

  public FTPSClient(String paramString, boolean paramBoolean)
  {
    this.protocol = paramString;
    this.isImplicit = paramBoolean;
    if (paramBoolean)
      setDefaultPort(990);
  }

  public FTPSClient(SSLContext paramSSLContext)
  {
    this(false, paramSSLContext);
  }

  public FTPSClient(boolean paramBoolean)
  {
    this("TLS", paramBoolean);
  }

  public FTPSClient(boolean paramBoolean, SSLContext paramSSLContext)
  {
    this("TLS", paramBoolean);
    this.context = paramSSLContext;
  }

  private boolean checkPROTValue(String paramString)
  {
    int i = 0;
    while (i < PROT_COMMAND_VALUE.length)
    {
      if (PROT_COMMAND_VALUE[i].equals(paramString))
        return true;
      i += 1;
    }
    return false;
  }

  private String extractPrefixedData(String paramString1, String paramString2)
  {
    int i = paramString2.indexOf(paramString1);
    if (i == -1)
      return null;
    return paramString2.substring(i + paramString1.length()).trim();
  }

  private KeyManager getKeyManager()
  {
    return this.keyManager;
  }

  private void initSslContext()
    throws IOException
  {
    if (this.context == null)
      this.context = SSLContextUtils.createSSLContext(this.protocol, getKeyManager(), getTrustManager());
  }

  protected void _connectAction_()
    throws IOException
  {
    if (this.isImplicit)
      sslNegotiation();
    super._connectAction_();
    if (!this.isImplicit)
    {
      execAUTH();
      sslNegotiation();
    }
  }

  protected Socket _openDataConnection_(int paramInt, String paramString)
    throws IOException
  {
    paramString = super._openDataConnection_(paramInt, paramString);
    if ((paramString instanceof SSLSocket))
    {
      SSLSocket localSSLSocket = (SSLSocket)paramString;
      localSSLSocket.setUseClientMode(this.isClientMode);
      localSSLSocket.setEnableSessionCreation(this.isCreation);
      if (!this.isClientMode)
      {
        localSSLSocket.setNeedClientAuth(this.isNeedClientAuth);
        localSSLSocket.setWantClientAuth(this.isWantClientAuth);
      }
      if (this.suites != null)
        localSSLSocket.setEnabledCipherSuites(this.suites);
      if (this.protocols != null)
        localSSLSocket.setEnabledProtocols(this.protocols);
      localSSLSocket.startHandshake();
    }
    return paramString;
  }

  public void disconnect()
    throws IOException
  {
    super.disconnect();
    setSocketFactory(null);
    setServerSocketFactory(null);
  }

  public int execADAT(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte != null)
      return sendCommand("ADAT", new String(Base64.encodeBase64(paramArrayOfByte)));
    return sendCommand("ADAT");
  }

  public int execAUTH(String paramString)
    throws IOException
  {
    return sendCommand("AUTH", paramString);
  }

  protected void execAUTH()
    throws SSLException, IOException
  {
    int i = sendCommand("AUTH", this.auth);
    if (334 == i)
      return;
    if (234 != i)
      throw new SSLException(getReplyString());
  }

  public int execCCC()
    throws IOException
  {
    return sendCommand("CCC");
  }

  public int execCONF(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte != null)
      return sendCommand("CONF", new String(Base64.encodeBase64(paramArrayOfByte)));
    return sendCommand("CONF", "");
  }

  public int execENC(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte != null)
      return sendCommand("ENC", new String(Base64.encodeBase64(paramArrayOfByte)));
    return sendCommand("ENC", "");
  }

  public int execMIC(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte != null)
      return sendCommand("MIC", new String(Base64.encodeBase64(paramArrayOfByte)));
    return sendCommand("MIC", "");
  }

  public void execPBSZ(long paramLong)
    throws SSLException, IOException
  {
    if ((paramLong >= 0L) && (4294967295L >= paramLong))
    {
      if (200 != sendCommand("PBSZ", String.valueOf(paramLong)))
        throw new SSLException(getReplyString());
      return;
    }
    throw new IllegalArgumentException();
  }

  public void execPROT(String paramString)
    throws SSLException, IOException
  {
    String str = paramString;
    if (paramString == null)
      str = "C";
    if (!checkPROTValue(str))
      throw new IllegalArgumentException();
    if (200 != sendCommand("PROT", str))
      throw new SSLException(getReplyString());
    if ("C".equals(str))
    {
      setSocketFactory(null);
      setServerSocketFactory(null);
      return;
    }
    setSocketFactory(new FTPSSocketFactory(this.context));
    setServerSocketFactory(new FTPSServerSocketFactory(this.context));
    initSslContext();
  }

  public String getAuthValue()
  {
    return this.auth;
  }

  public boolean getEnableSessionCreation()
  {
    if ((this._socket_ instanceof SSLSocket))
      return ((SSLSocket)this._socket_).getEnableSessionCreation();
    return false;
  }

  public String[] getEnabledCipherSuites()
  {
    if ((this._socket_ instanceof SSLSocket))
      return ((SSLSocket)this._socket_).getEnabledCipherSuites();
    return null;
  }

  public String[] getEnabledProtocols()
  {
    if ((this._socket_ instanceof SSLSocket))
      return ((SSLSocket)this._socket_).getEnabledProtocols();
    return null;
  }

  public boolean getNeedClientAuth()
  {
    if ((this._socket_ instanceof SSLSocket))
      return ((SSLSocket)this._socket_).getNeedClientAuth();
    return false;
  }

  public TrustManager getTrustManager()
  {
    return this.trustManager;
  }

  public boolean getUseClientMode()
  {
    if ((this._socket_ instanceof SSLSocket))
      return ((SSLSocket)this._socket_).getUseClientMode();
    return false;
  }

  public boolean getWantClientAuth()
  {
    if ((this._socket_ instanceof SSLSocket))
      return ((SSLSocket)this._socket_).getWantClientAuth();
    return false;
  }

  public byte[] parseADATReply(String paramString)
  {
    if (paramString == null)
      return null;
    return Base64.decodeBase64(extractPrefixedData("ADAT=", paramString));
  }

  public long parsePBSZ(long paramLong)
    throws SSLException, IOException
  {
    execPBSZ(paramLong);
    String str = extractPrefixedData("PBSZ=", getReplyString());
    long l1 = paramLong;
    if (str != null)
    {
      long l2 = Long.parseLong(str);
      l1 = paramLong;
      if (l2 < paramLong)
        l1 = l2;
    }
    return l1;
  }

  public int sendCommand(String paramString1, String paramString2)
    throws IOException
  {
    int i = super.sendCommand(paramString1, paramString2);
    if ("CCC".equals(paramString1))
    {
      if (200 == i)
      {
        this._socket_.close();
        this._socket_ = this.plainSocket;
        this._controlInput_ = new BufferedReader(new InputStreamReader(this._socket_.getInputStream(), getControlEncoding()));
        this._controlOutput_ = new BufferedWriter(new OutputStreamWriter(this._socket_.getOutputStream(), getControlEncoding()));
        return i;
      }
      throw new SSLException(getReplyString());
    }
    return i;
  }

  public void setAuthValue(String paramString)
  {
    this.auth = paramString;
  }

  public void setEnabledCipherSuites(String[] paramArrayOfString)
  {
    this.suites = new String[paramArrayOfString.length];
    System.arraycopy(paramArrayOfString, 0, this.suites, 0, paramArrayOfString.length);
  }

  public void setEnabledProtocols(String[] paramArrayOfString)
  {
    this.protocols = new String[paramArrayOfString.length];
    System.arraycopy(paramArrayOfString, 0, this.protocols, 0, paramArrayOfString.length);
  }

  public void setEnabledSessionCreation(boolean paramBoolean)
  {
    this.isCreation = paramBoolean;
  }

  public void setKeyManager(KeyManager paramKeyManager)
  {
    this.keyManager = paramKeyManager;
  }

  public void setNeedClientAuth(boolean paramBoolean)
  {
    this.isNeedClientAuth = paramBoolean;
  }

  public void setTrustManager(TrustManager paramTrustManager)
  {
    this.trustManager = paramTrustManager;
  }

  public void setUseClientMode(boolean paramBoolean)
  {
    this.isClientMode = paramBoolean;
  }

  public void setWantClientAuth(boolean paramBoolean)
  {
    this.isWantClientAuth = paramBoolean;
  }

  protected void sslNegotiation()
    throws IOException
  {
    this.plainSocket = this._socket_;
    initSslContext();
    Object localObject = this.context.getSocketFactory();
    String str = this._socket_.getInetAddress().getHostAddress();
    int i = this._socket_.getPort();
    localObject = (SSLSocket)((SSLSocketFactory)localObject).createSocket(this._socket_, str, i, false);
    ((SSLSocket)localObject).setEnableSessionCreation(this.isCreation);
    ((SSLSocket)localObject).setUseClientMode(this.isClientMode);
    if (!this.isClientMode)
    {
      ((SSLSocket)localObject).setNeedClientAuth(this.isNeedClientAuth);
      ((SSLSocket)localObject).setWantClientAuth(this.isWantClientAuth);
    }
    if (this.protocols != null)
      ((SSLSocket)localObject).setEnabledProtocols(this.protocols);
    if (this.suites != null)
      ((SSLSocket)localObject).setEnabledCipherSuites(this.suites);
    ((SSLSocket)localObject).startHandshake();
    this._socket_ = ((Socket)localObject);
    this._controlInput_ = new BufferedReader(new InputStreamReader(((SSLSocket)localObject).getInputStream(), getControlEncoding()));
    this._controlOutput_ = new BufferedWriter(new OutputStreamWriter(((SSLSocket)localObject).getOutputStream(), getControlEncoding()));
  }
}