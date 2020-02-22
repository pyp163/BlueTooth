package okhttp3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import javax.annotation.Nullable;

public abstract class EventListener
{
  public static final EventListener NONE = new EventListener()
  {
  };

  static Factory factory(EventListener paramEventListener)
  {
    return new EventListener..Lambda.0(paramEventListener);
  }

  public void callEnd(Call paramCall)
  {
  }

  public void callFailed(Call paramCall, IOException paramIOException)
  {
  }

  public void callStart(Call paramCall)
  {
  }

  public void connectEnd(Call paramCall, InetSocketAddress paramInetSocketAddress, Proxy paramProxy, @Nullable Protocol paramProtocol)
  {
  }

  public void connectFailed(Call paramCall, InetSocketAddress paramInetSocketAddress, Proxy paramProxy, @Nullable Protocol paramProtocol, IOException paramIOException)
  {
  }

  public void connectStart(Call paramCall, InetSocketAddress paramInetSocketAddress, Proxy paramProxy)
  {
  }

  public void connectionAcquired(Call paramCall, Connection paramConnection)
  {
  }

  public void connectionReleased(Call paramCall, Connection paramConnection)
  {
  }

  public void dnsEnd(Call paramCall, String paramString, List<InetAddress> paramList)
  {
  }

  public void dnsStart(Call paramCall, String paramString)
  {
  }

  public void requestBodyEnd(Call paramCall, long paramLong)
  {
  }

  public void requestBodyStart(Call paramCall)
  {
  }

  public void requestFailed(Call paramCall, IOException paramIOException)
  {
  }

  public void requestHeadersEnd(Call paramCall, Request paramRequest)
  {
  }

  public void requestHeadersStart(Call paramCall)
  {
  }

  public void responseBodyEnd(Call paramCall, long paramLong)
  {
  }

  public void responseBodyStart(Call paramCall)
  {
  }

  public void responseFailed(Call paramCall, IOException paramIOException)
  {
  }

  public void responseHeadersEnd(Call paramCall, Response paramResponse)
  {
  }

  public void responseHeadersStart(Call paramCall)
  {
  }

  public void secureConnectEnd(Call paramCall, @Nullable Handshake paramHandshake)
  {
  }

  public void secureConnectStart(Call paramCall)
  {
  }

  public static abstract interface Factory
  {
    public abstract EventListener create(Call paramCall);
  }
}