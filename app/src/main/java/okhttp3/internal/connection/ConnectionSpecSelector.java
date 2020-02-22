package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Internal;

final class ConnectionSpecSelector
{
  private final List<ConnectionSpec> connectionSpecs;
  private boolean isFallback;
  private boolean isFallbackPossible;
  private int nextModeIndex = 0;

  ConnectionSpecSelector(List<ConnectionSpec> paramList)
  {
    this.connectionSpecs = paramList;
  }

  private boolean isFallbackPossible(SSLSocket paramSSLSocket)
  {
    int i = this.nextModeIndex;
    while (i < this.connectionSpecs.size())
    {
      if (((ConnectionSpec)this.connectionSpecs.get(i)).isCompatible(paramSSLSocket))
        return true;
      i += 1;
    }
    return false;
  }

  ConnectionSpec configureSecureSocket(SSLSocket paramSSLSocket)
    throws IOException
  {
    int i = this.nextModeIndex;
    int j = this.connectionSpecs.size();
    while (i < j)
    {
      localObject = (ConnectionSpec)this.connectionSpecs.get(i);
      if (((ConnectionSpec)localObject).isCompatible(paramSSLSocket))
      {
        this.nextModeIndex = (i + 1);
        break label64;
      }
      i += 1;
    }
    Object localObject = null;
    label64: if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unable to find acceptable protocols. isFallback=");
      ((StringBuilder)localObject).append(this.isFallback);
      ((StringBuilder)localObject).append(", modes=");
      ((StringBuilder)localObject).append(this.connectionSpecs);
      ((StringBuilder)localObject).append(", supported protocols=");
      ((StringBuilder)localObject).append(Arrays.toString(paramSSLSocket.getEnabledProtocols()));
      throw new UnknownServiceException(((StringBuilder)localObject).toString());
    }
    this.isFallbackPossible = isFallbackPossible(paramSSLSocket);
    Internal.instance.apply((ConnectionSpec)localObject, paramSSLSocket, this.isFallback);
    return localObject;
  }

  boolean connectionFailed(IOException paramIOException)
  {
    this.isFallback = true;
    if (!this.isFallbackPossible)
      return false;
    if ((paramIOException instanceof ProtocolException))
      return false;
    if ((paramIOException instanceof InterruptedIOException))
      return false;
    if (((paramIOException instanceof SSLHandshakeException)) && ((paramIOException.getCause() instanceof CertificateException)))
      return false;
    if ((paramIOException instanceof SSLPeerUnverifiedException))
      return false;
    return paramIOException instanceof SSLException;
  }
}