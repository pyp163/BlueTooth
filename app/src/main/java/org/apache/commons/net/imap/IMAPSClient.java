package org.apache.commons.net.imap;

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
import org.apache.commons.net.io.CRLFLineReader;
import org.apache.commons.net.util.SSLContextUtils;

public class IMAPSClient extends IMAPClient
{
  public static final int DEFAULT_IMAPS_PORT = 993;
  public static final String DEFAULT_PROTOCOL = "TLS";
  private SSLContext context = null;
  private final boolean isImplicit;
  private KeyManager keyManager = null;
  private final String protocol;
  private String[] protocols = null;
  private String[] suites = null;
  private TrustManager trustManager = null;

  public IMAPSClient()
  {
    this("TLS", false);
  }

  public IMAPSClient(String paramString)
  {
    this(paramString, false);
  }

  public IMAPSClient(String paramString, boolean paramBoolean)
  {
    this(paramString, paramBoolean, null);
  }

  public IMAPSClient(String paramString, boolean paramBoolean, SSLContext paramSSLContext)
  {
    setDefaultPort(993);
    this.protocol = paramString;
    this.isImplicit = paramBoolean;
    this.context = paramSSLContext;
  }

  public IMAPSClient(SSLContext paramSSLContext)
  {
    this(false, paramSSLContext);
  }

  public IMAPSClient(boolean paramBoolean)
  {
    this("TLS", paramBoolean);
  }

  public IMAPSClient(boolean paramBoolean, SSLContext paramSSLContext)
  {
    this("TLS", paramBoolean, paramSSLContext);
  }

  private KeyManager getKeyManager()
  {
    return this.keyManager;
  }

  private void initSSLContext()
    throws IOException
  {
    if (this.context == null)
      this.context = SSLContextUtils.createSSLContext(this.protocol, getKeyManager(), getTrustManager());
  }

  private void performSSLNegotiation()
    throws IOException
  {
    initSSLContext();
    Object localObject = this.context.getSocketFactory();
    String str = getRemoteAddress().getHostAddress();
    int i = getRemotePort();
    localObject = (SSLSocket)((SSLSocketFactory)localObject).createSocket(this._socket_, str, i, true);
    ((SSLSocket)localObject).setEnableSessionCreation(true);
    ((SSLSocket)localObject).setUseClientMode(true);
    if (this.protocols != null)
      ((SSLSocket)localObject).setEnabledProtocols(this.protocols);
    if (this.suites != null)
      ((SSLSocket)localObject).setEnabledCipherSuites(this.suites);
    ((SSLSocket)localObject).startHandshake();
    this._socket_ = ((Socket)localObject);
    this._input_ = ((SSLSocket)localObject).getInputStream();
    this._output_ = ((SSLSocket)localObject).getOutputStream();
    this._reader = new CRLFLineReader(new InputStreamReader(this._input_, "ISO-8859-1"));
    this.__writer = new BufferedWriter(new OutputStreamWriter(this._output_, "ISO-8859-1"));
  }

  protected void _connectAction_()
    throws IOException
  {
    if (this.isImplicit)
      performSSLNegotiation();
    super._connectAction_();
  }

  public boolean execTLS()
    throws SSLException, IOException
  {
    if (sendCommand(IMAPCommand.getCommand(IMAPCommand.STARTTLS)) != 0)
      return false;
    performSSLNegotiation();
    return true;
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

  public TrustManager getTrustManager()
  {
    return this.trustManager;
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

  public void setKeyManager(KeyManager paramKeyManager)
  {
    this.keyManager = paramKeyManager;
  }

  public void setTrustManager(TrustManager paramTrustManager)
  {
    this.trustManager = paramTrustManager;
  }
}