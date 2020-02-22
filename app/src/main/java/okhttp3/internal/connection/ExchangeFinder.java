package okhttp3.internal.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.http.ExchangeCodec;

final class ExchangeFinder
{
  private final Address address;
  private final Call call;
  private RealConnection connectingConnection;
  private final RealConnectionPool connectionPool;
  private final EventListener eventListener;
  private boolean hasStreamFailure;
  private RouteSelector.Selection routeSelection;
  private final RouteSelector routeSelector;
  private final Transmitter transmitter;

  ExchangeFinder(Transmitter paramTransmitter, RealConnectionPool paramRealConnectionPool, Address paramAddress, Call paramCall, EventListener paramEventListener)
  {
    this.transmitter = paramTransmitter;
    this.connectionPool = paramRealConnectionPool;
    this.address = paramAddress;
    this.call = paramCall;
    this.eventListener = paramEventListener;
    this.routeSelector = new RouteSelector(paramAddress, paramRealConnectionPool.routeDatabase, paramCall, paramEventListener);
  }

  private RealConnection findConnection(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
    throws IOException
  {
    while (true)
    {
      synchronized (this.connectionPool)
      {
        if (this.transmitter.isCanceled())
          throw new IOException("Canceled");
        this.hasStreamFailure = false;
        boolean bool = retryCurrentRoute();
        Object localObject9 = null;
        if (bool)
        {
          Object localObject1 = this.transmitter.connection.route();
          ??? = this.transmitter.connection;
          if ((this.transmitter.connection == null) || (!this.transmitter.connection.noNewExchanges))
            break label595;
          localObject7 = this.transmitter.releaseConnectionNoEvents();
          if (this.transmitter.connection == null)
            break label601;
          localObject6 = this.transmitter.connection;
          ??? = null;
          if (localObject6 != null)
            break label610;
          if (!this.connectionPool.transmitterAcquirePooledConnection(this.address, this.transmitter, null, false))
            break label607;
          localObject6 = this.transmitter.connection;
          localObject1 = null;
          i = 1;
          Util.closeQuietly((Socket)localObject7);
          if (??? != null)
            this.eventListener.connectionReleased(this.call, (Connection)???);
          if (i != 0)
            this.eventListener.connectionAcquired(this.call, (Connection)localObject6);
          if (localObject6 != null)
            return localObject6;
          int j;
          if ((localObject1 == null) && ((this.routeSelection == null) || (!this.routeSelection.hasNext())))
          {
            this.routeSelection = this.routeSelector.next();
            j = 1;
          }
          else
          {
            j = 0;
          }
          synchronized (this.connectionPool)
          {
            if (this.transmitter.isCanceled())
              throw new IOException("Canceled");
            if (j == 0)
              break label619;
            ??? = this.routeSelection.getAll();
            localObject7 = ???;
            if (this.connectionPool.transmitterAcquirePooledConnection(this.address, this.transmitter, (List)???, false))
            {
              localObject6 = this.transmitter.connection;
              i = 1;
              localObject7 = ???;
            }
            if (i == 0)
            {
              localObject6 = localObject1;
              if (localObject1 == null)
                localObject6 = this.routeSelection.next();
              localObject6 = new RealConnection(this.connectionPool, (Route)localObject6);
              this.connectingConnection = ((RealConnection)localObject6);
            }
            if (i != 0)
            {
              this.eventListener.connectionAcquired(this.call, (Connection)localObject6);
              return localObject6;
            }
            ((RealConnection)localObject6).connect(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean, this.call, this.eventListener);
            this.connectionPool.routeDatabase.connected(((RealConnection)localObject6).route());
            synchronized (this.connectionPool)
            {
              this.connectingConnection = null;
              if (this.connectionPool.transmitterAcquirePooledConnection(this.address, this.transmitter, (List)localObject7, true))
              {
                ((RealConnection)localObject6).noNewExchanges = true;
                localObject1 = ((RealConnection)localObject6).socket();
                localObject6 = this.transmitter.connection;
              }
              else
              {
                this.connectionPool.put((RealConnection)localObject6);
                this.transmitter.acquireConnectionNoEvents((RealConnection)localObject6);
                localObject1 = localObject9;
              }
              Util.closeQuietly((Socket)localObject1);
              this.eventListener.connectionAcquired(this.call, (Connection)localObject6);
              return localObject6;
            }
          }
        }
      }
      Object localObject5 = null;
      continue;
      label595: Object localObject7 = null;
      continue;
      label601: Object localObject6 = null;
      continue;
      label607: break label613;
      label610: localObject5 = null;
      label613: int i = 0;
      continue;
      label619: localObject7 = null;
    }
  }

  private RealConnection findHealthyConnection(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    while (true)
    {
      RealConnection localRealConnection = findConnection(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1);
      synchronized (this.connectionPool)
      {
        if (localRealConnection.successCount == 0)
          return localRealConnection;
        if (!localRealConnection.isHealthy(paramBoolean2))
        {
          localRealConnection.noNewExchanges();
          continue;
        }
        return localRealConnection;
      }
    }
  }

  private boolean retryCurrentRoute()
  {
    return (this.transmitter.connection != null) && (this.transmitter.connection.routeFailureCount == 0) && (Util.sameConnection(this.transmitter.connection.route().address().url(), this.address.url()));
  }

  RealConnection connectingConnection()
  {
    return this.connectingConnection;
  }

  public ExchangeCodec find(OkHttpClient paramOkHttpClient, Interceptor.Chain paramChain, boolean paramBoolean)
  {
    int i = paramChain.connectTimeoutMillis();
    int j = paramChain.readTimeoutMillis();
    int k = paramChain.writeTimeoutMillis();
    int m = paramOkHttpClient.pingIntervalMillis();
    boolean bool = paramOkHttpClient.retryOnConnectionFailure();
    try
    {
      paramOkHttpClient = findHealthyConnection(i, j, k, m, bool, paramBoolean).newCodec(paramOkHttpClient, paramChain);
      return paramOkHttpClient;
    }
    catch (IOException paramOkHttpClient)
    {
      trackFailure();
      throw new RouteException(paramOkHttpClient);
    }
    catch (RouteException paramOkHttpClient)
    {
      trackFailure();
    }
    throw paramOkHttpClient;
  }

  boolean hasRouteToTry()
  {
    while (true)
    {
      synchronized (this.connectionPool)
      {
        if ((retryCurrentRoute()) || ((this.routeSelection != null) && (this.routeSelection.hasNext())))
          break label58;
        if (this.routeSelector.hasNext())
        {
          break label58;
          return bool;
        }
      }
      boolean bool = false;
      continue;
      label58: bool = true;
    }
  }

  boolean hasStreamFailure()
  {
    synchronized (this.connectionPool)
    {
      boolean bool = this.hasStreamFailure;
      return bool;
    }
  }

  void trackFailure()
  {
    synchronized (this.connectionPool)
    {
      this.hasStreamFailure = true;
      return;
    }
  }
}