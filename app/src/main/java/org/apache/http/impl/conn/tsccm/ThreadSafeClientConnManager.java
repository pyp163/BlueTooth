package org.apache.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.DefaultClientConnectionOperator;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.params.HttpParams;

@ThreadSafe
public class ThreadSafeClientConnManager
  implements ClientConnectionManager
{
  protected final ClientConnectionOperator connOperator;
  protected final ConnPerRouteBean connPerRoute;

  @Deprecated
  protected final AbstractConnPool connectionPool;
  private final Log log;
  protected final ConnPoolByRoute pool;
  protected final SchemeRegistry schemeRegistry;

  public ThreadSafeClientConnManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public ThreadSafeClientConnManager(SchemeRegistry paramSchemeRegistry)
  {
    this(paramSchemeRegistry, -1L, TimeUnit.MILLISECONDS);
  }

  public ThreadSafeClientConnManager(SchemeRegistry paramSchemeRegistry, long paramLong, TimeUnit paramTimeUnit)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.log = LogFactory.getLog(getClass());
    this.schemeRegistry = paramSchemeRegistry;
    this.connPerRoute = new ConnPerRouteBean();
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.pool = createConnectionPool(paramLong, paramTimeUnit);
    this.connectionPool = this.pool;
  }

  @Deprecated
  public ThreadSafeClientConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry may not be null");
    this.log = LogFactory.getLog(getClass());
    this.schemeRegistry = paramSchemeRegistry;
    this.connPerRoute = new ConnPerRouteBean();
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.pool = ((ConnPoolByRoute)createConnectionPool(paramHttpParams));
    this.connectionPool = this.pool;
  }

  public void closeExpiredConnections()
  {
    this.log.debug("Closing expired connections");
    this.pool.closeExpiredConnections();
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.log.isDebugEnabled())
    {
      Log localLog = this.log;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Closing connections idle longer than ");
      localStringBuilder.append(paramLong);
      localStringBuilder.append(" ");
      localStringBuilder.append(paramTimeUnit);
      localLog.debug(localStringBuilder.toString());
    }
    this.pool.closeIdleConnections(paramLong, paramTimeUnit);
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
  }

  @Deprecated
  protected AbstractConnPool createConnectionPool(HttpParams paramHttpParams)
  {
    return new ConnPoolByRoute(this.connOperator, paramHttpParams);
  }

  protected ConnPoolByRoute createConnectionPool(long paramLong, TimeUnit paramTimeUnit)
  {
    return new ConnPoolByRoute(this.connOperator, this.connPerRoute, 20, paramLong, paramTimeUnit);
  }

  protected void finalize()
    throws Throwable
  {
    try
    {
      shutdown();
      return;
    }
    finally
    {
      super.finalize();
    }
  }

  public int getConnectionsInPool()
  {
    return this.pool.getConnectionsInPool();
  }

  public int getConnectionsInPool(HttpRoute paramHttpRoute)
  {
    return this.pool.getConnectionsInPool(paramHttpRoute);
  }

  public int getDefaultMaxPerRoute()
  {
    return this.connPerRoute.getDefaultMaxPerRoute();
  }

  public int getMaxForRoute(HttpRoute paramHttpRoute)
  {
    return this.connPerRoute.getMaxForRoute(paramHttpRoute);
  }

  public int getMaxTotal()
  {
    return this.pool.getMaxTotalConnections();
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  // ERROR //
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: aload_1
    //   1: instanceof 178
    //   4: ifne +13 -> 17
    //   7: new 49	java/lang/IllegalArgumentException
    //   10: dup
    //   11: ldc 180
    //   13: invokespecial 54	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_1
    //   18: checkcast 178	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter
    //   21: astore 6
    //   23: aload 6
    //   25: invokevirtual 184	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getPoolEntry	()Lorg/apache/http/impl/conn/AbstractPoolEntry;
    //   28: ifnull +22 -> 50
    //   31: aload 6
    //   33: invokevirtual 188	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   36: aload_0
    //   37: if_acmpeq +13 -> 50
    //   40: new 49	java/lang/IllegalArgumentException
    //   43: dup
    //   44: ldc 190
    //   46: invokespecial 54	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   49: athrow
    //   50: aload 6
    //   52: monitorenter
    //   53: aload 6
    //   55: invokevirtual 184	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:getPoolEntry	()Lorg/apache/http/impl/conn/AbstractPoolEntry;
    //   58: checkcast 192	org/apache/http/impl/conn/tsccm/BasicPoolEntry
    //   61: astore 7
    //   63: aload 7
    //   65: ifnonnull +7 -> 72
    //   68: aload 6
    //   70: monitorexit
    //   71: return
    //   72: aload 6
    //   74: invokevirtual 195	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isOpen	()Z
    //   77: ifeq +16 -> 93
    //   80: aload 6
    //   82: invokevirtual 198	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   85: ifne +8 -> 93
    //   88: aload 6
    //   90: invokevirtual 199	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:shutdown	()V
    //   93: aload 6
    //   95: invokevirtual 198	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   98: istore 5
    //   100: aload_0
    //   101: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   104: invokeinterface 113 1 0
    //   109: ifeq +33 -> 142
    //   112: iload 5
    //   114: ifeq +17 -> 131
    //   117: aload_0
    //   118: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   121: ldc 201
    //   123: invokeinterface 105 2 0
    //   128: goto +14 -> 142
    //   131: aload_0
    //   132: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   135: ldc 203
    //   137: invokeinterface 105 2 0
    //   142: aload 6
    //   144: invokevirtual 206	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   147: aload_0
    //   148: getfield 85	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lorg/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   151: astore_1
    //   152: aload_1
    //   153: aload 7
    //   155: iload 5
    //   157: lload_2
    //   158: aload 4
    //   160: invokevirtual 210	org/apache/http/impl/conn/tsccm/ConnPoolByRoute:freeEntry	(Lorg/apache/http/impl/conn/tsccm/BasicPoolEntry;ZJLjava/util/concurrent/TimeUnit;)V
    //   163: goto +94 -> 257
    //   166: astore_1
    //   167: goto +94 -> 261
    //   170: astore_1
    //   171: aload_0
    //   172: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   175: invokeinterface 113 1 0
    //   180: ifeq +15 -> 195
    //   183: aload_0
    //   184: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   187: ldc 212
    //   189: aload_1
    //   190: invokeinterface 215 3 0
    //   195: aload 6
    //   197: invokevirtual 198	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   200: istore 5
    //   202: aload_0
    //   203: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   206: invokeinterface 113 1 0
    //   211: ifeq +33 -> 244
    //   214: iload 5
    //   216: ifeq +17 -> 233
    //   219: aload_0
    //   220: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   223: ldc 201
    //   225: invokeinterface 105 2 0
    //   230: goto +14 -> 244
    //   233: aload_0
    //   234: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   237: ldc 203
    //   239: invokeinterface 105 2 0
    //   244: aload 6
    //   246: invokevirtual 206	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   249: aload_0
    //   250: getfield 85	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lorg/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   253: astore_1
    //   254: goto -102 -> 152
    //   257: aload 6
    //   259: monitorexit
    //   260: return
    //   261: aload 6
    //   263: invokevirtual 198	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:isMarkedReusable	()Z
    //   266: istore 5
    //   268: aload_0
    //   269: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   272: invokeinterface 113 1 0
    //   277: ifeq +33 -> 310
    //   280: iload 5
    //   282: ifeq +17 -> 299
    //   285: aload_0
    //   286: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   289: ldc 201
    //   291: invokeinterface 105 2 0
    //   296: goto +14 -> 310
    //   299: aload_0
    //   300: getfield 66	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   303: ldc 203
    //   305: invokeinterface 105 2 0
    //   310: aload 6
    //   312: invokevirtual 206	org/apache/http/impl/conn/tsccm/BasicPooledConnAdapter:detach	()V
    //   315: aload_0
    //   316: getfield 85	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:pool	Lorg/apache/http/impl/conn/tsccm/ConnPoolByRoute;
    //   319: aload 7
    //   321: iload 5
    //   323: lload_2
    //   324: aload 4
    //   326: invokevirtual 210	org/apache/http/impl/conn/tsccm/ConnPoolByRoute:freeEntry	(Lorg/apache/http/impl/conn/tsccm/BasicPoolEntry;ZJLjava/util/concurrent/TimeUnit;)V
    //   329: aload_1
    //   330: athrow
    //   331: astore_1
    //   332: aload 6
    //   334: monitorexit
    //   335: aload_1
    //   336: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   72	93	166	finally
    //   171	195	166	finally
    //   72	93	170	java/io/IOException
    //   53	63	331	finally
    //   68	71	331	finally
    //   93	112	331	finally
    //   117	128	331	finally
    //   131	142	331	finally
    //   142	152	331	finally
    //   152	163	331	finally
    //   195	214	331	finally
    //   219	230	331	finally
    //   233	244	331	finally
    //   244	254	331	finally
    //   257	260	331	finally
    //   261	280	331	finally
    //   285	296	331	finally
    //   299	310	331	finally
    //   310	331	331	finally
    //   332	335	331	finally
  }

  public ClientConnectionRequest requestConnection(final HttpRoute paramHttpRoute, Object paramObject)
  {
    return new ClientConnectionRequest()
    {
      public void abortRequest()
      {
        this.val$poolRequest.abortRequest();
      }

      public ManagedClientConnection getConnection(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, ConnectionPoolTimeoutException
      {
        if (paramHttpRoute == null)
          throw new IllegalArgumentException("Route may not be null.");
        if (ThreadSafeClientConnManager.this.log.isDebugEnabled())
        {
          Log localLog = ThreadSafeClientConnManager.this.log;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Get connection: ");
          localStringBuilder.append(paramHttpRoute);
          localStringBuilder.append(", timeout = ");
          localStringBuilder.append(paramAnonymousLong);
          localLog.debug(localStringBuilder.toString());
        }
        paramAnonymousTimeUnit = this.val$poolRequest.getPoolEntry(paramAnonymousLong, paramAnonymousTimeUnit);
        return new BasicPooledConnAdapter(ThreadSafeClientConnManager.this, paramAnonymousTimeUnit);
      }
    };
  }

  public void setDefaultMaxPerRoute(int paramInt)
  {
    this.connPerRoute.setDefaultMaxPerRoute(paramInt);
  }

  public void setMaxForRoute(HttpRoute paramHttpRoute, int paramInt)
  {
    this.connPerRoute.setMaxForRoute(paramHttpRoute, paramInt);
  }

  public void setMaxTotal(int paramInt)
  {
    this.pool.setMaxTotalConnections(paramInt);
  }

  public void shutdown()
  {
    this.log.debug("Shutting down");
    this.pool.shutdown();
  }
}