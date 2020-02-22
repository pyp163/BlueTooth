package okhttp3.internal;

import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Headers.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealConnectionPool;

public abstract class Internal
{
  public static Internal instance;

  public static void initializeInstanceForTests()
  {
    new OkHttpClient();
  }

  public abstract void addLenient(Headers.Builder paramBuilder, String paramString);

  public abstract void addLenient(Headers.Builder paramBuilder, String paramString1, String paramString2);

  public abstract void apply(ConnectionSpec paramConnectionSpec, SSLSocket paramSSLSocket, boolean paramBoolean);

  public abstract int code(Response.Builder paramBuilder);

  public abstract boolean equalsNonHost(Address paramAddress1, Address paramAddress2);

  @Nullable
  public abstract Exchange exchange(Response paramResponse);

  public abstract void initExchange(Response.Builder paramBuilder, Exchange paramExchange);

  public abstract Call newWebSocketCall(OkHttpClient paramOkHttpClient, Request paramRequest);

  public abstract RealConnectionPool realConnectionPool(ConnectionPool paramConnectionPool);
}