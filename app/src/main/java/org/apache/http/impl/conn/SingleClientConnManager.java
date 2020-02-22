package org.apache.http.impl.conn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.routing.RouteTracker;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.params.HttpParams;

@ThreadSafe
public class SingleClientConnManager
  implements ClientConnectionManager
{
  public static final String MISUSE_MESSAGE = "Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.";
  protected final boolean alwaysShutDown;
  protected final ClientConnectionOperator connOperator;

  @GuardedBy("this")
  protected long connectionExpiresTime;
  protected volatile boolean isShutDown;

  @GuardedBy("this")
  protected long lastReleaseTime;
  private final Log log = LogFactory.getLog(getClass());

  @GuardedBy("this")
  protected ConnAdapter managedConn;
  protected final SchemeRegistry schemeRegistry;

  @GuardedBy("this")
  protected PoolEntry uniquePoolEntry;

  public SingleClientConnManager()
  {
    this(SchemeRegistryFactory.createDefault());
  }

  public SingleClientConnManager(SchemeRegistry paramSchemeRegistry)
  {
    if (paramSchemeRegistry == null)
      throw new IllegalArgumentException("Scheme registry must not be null.");
    this.schemeRegistry = paramSchemeRegistry;
    this.connOperator = createConnectionOperator(paramSchemeRegistry);
    this.uniquePoolEntry = new PoolEntry();
    this.managedConn = null;
    this.lastReleaseTime = -1L;
    this.alwaysShutDown = false;
    this.isShutDown = false;
  }

  @Deprecated
  public SingleClientConnManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
  {
    this(paramSchemeRegistry);
  }

  protected final void assertStillUp()
    throws IllegalStateException
  {
    if (this.isShutDown)
      throw new IllegalStateException("Manager is shut down.");
  }

  public void closeExpiredConnections()
  {
    try
    {
      if (System.currentTimeMillis() >= this.connectionExpiresTime)
        closeIdleConnections(0L, TimeUnit.MILLISECONDS);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void closeIdleConnections(long paramLong, TimeUnit paramTimeUnit)
  {
    try
    {
      assertStillUp();
      if (paramTimeUnit == null)
        throw new IllegalArgumentException("Time unit must not be null.");
      if ((this.managedConn == null) && (this.uniquePoolEntry.connection.isOpen()))
      {
        long l1 = System.currentTimeMillis();
        paramLong = paramTimeUnit.toMillis(paramLong);
        long l2 = this.lastReleaseTime;
        if (l2 <= l1 - paramLong)
          try
          {
            this.uniquePoolEntry.close();
          }
          catch (IOException paramTimeUnit)
          {
            this.log.debug("Problem closing idle connection.", paramTimeUnit);
          }
      }
      return;
    }
    finally
    {
    }
    throw paramTimeUnit;
  }

  protected ClientConnectionOperator createConnectionOperator(SchemeRegistry paramSchemeRegistry)
  {
    return new DefaultClientConnectionOperator(paramSchemeRegistry);
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

  public ManagedClientConnection getConnection(HttpRoute paramHttpRoute, Object paramObject)
  {
    if (paramHttpRoute == null);
    try
    {
      throw new IllegalArgumentException("Route may not be null.");
      assertStillUp();
      if (this.log.isDebugEnabled())
      {
        paramObject = this.log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Get connection for route ");
        localStringBuilder.append(paramHttpRoute);
        paramObject.debug(localStringBuilder.toString());
      }
      if (this.managedConn != null)
        throw new IllegalStateException("Invalid use of SingleClientConnManager: connection still allocated.\nMake sure to release the connection before allocating another one.");
      closeExpiredConnections();
      boolean bool = this.uniquePoolEntry.connection.isOpen();
      int k = 1;
      int i = 0;
      int j;
      if (bool)
      {
        paramObject = this.uniquePoolEntry.tracker;
        if (paramObject != null)
        {
          bool = paramObject.toRoute().equals(paramHttpRoute);
          if (bool)
          {
            j = 0;
            break label164;
          }
        }
        j = 1;
      }
      else
      {
        j = 0;
        i = 1;
      }
      label164: if (j != 0)
      {
        try
        {
          this.uniquePoolEntry.shutdown();
          i = k;
        }
        catch (IOException paramObject)
        {
          this.log.debug("Problem shutting down connection.", paramObject);
          i = k;
        }
        if (i != 0)
          this.uniquePoolEntry = new PoolEntry();
        this.managedConn = new ConnAdapter(this.uniquePoolEntry, paramHttpRoute);
        paramHttpRoute = this.managedConn;
        return paramHttpRoute;
        throw paramHttpRoute;
      }
    }
    finally
    {
      while (true);
    }
  }

  public SchemeRegistry getSchemeRegistry()
  {
    return this.schemeRegistry;
  }

  // ERROR //
  public void releaseConnection(ManagedClientConnection paramManagedClientConnection, long paramLong, TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 127	org/apache/http/impl/conn/SingleClientConnManager:assertStillUp	()V
    //   6: aload_1
    //   7: instanceof 10
    //   10: ifne +13 -> 23
    //   13: new 66	java/lang/IllegalArgumentException
    //   16: dup
    //   17: ldc 220
    //   19: invokespecial 71	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   22: athrow
    //   23: aload_0
    //   24: getfield 64	org/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   27: invokeinterface 172 1 0
    //   32: ifeq +45 -> 77
    //   35: aload_0
    //   36: getfield 64	org/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   39: astore 5
    //   41: new 174	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   48: astore 6
    //   50: aload 6
    //   52: ldc 222
    //   54: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: aload 6
    //   60: aload_1
    //   61: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: aload 5
    //   67: aload 6
    //   69: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokeinterface 191 2 0
    //   77: aload_1
    //   78: checkcast 10	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter
    //   81: astore_1
    //   82: aload_1
    //   83: getfield 226	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:poolEntry	Lorg/apache/http/impl/conn/AbstractPoolEntry;
    //   86: astore 5
    //   88: aload 5
    //   90: ifnonnull +6 -> 96
    //   93: aload_0
    //   94: monitorexit
    //   95: return
    //   96: aload_1
    //   97: invokevirtual 230	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:getManager	()Lorg/apache/http/conn/ClientConnectionManager;
    //   100: astore 5
    //   102: aload 5
    //   104: ifnull +19 -> 123
    //   107: aload 5
    //   109: aload_0
    //   110: if_acmpeq +13 -> 123
    //   113: new 66	java/lang/IllegalArgumentException
    //   116: dup
    //   117: ldc 232
    //   119: invokespecial 71	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   122: athrow
    //   123: aload_1
    //   124: invokevirtual 233	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:isOpen	()Z
    //   127: ifeq +44 -> 171
    //   130: aload_0
    //   131: getfield 92	org/apache/http/impl/conn/SingleClientConnManager:alwaysShutDown	Z
    //   134: ifne +10 -> 144
    //   137: aload_1
    //   138: invokevirtual 236	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:isMarkedReusable	()Z
    //   141: ifne +30 -> 171
    //   144: aload_0
    //   145: getfield 64	org/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   148: invokeinterface 172 1 0
    //   153: ifeq +14 -> 167
    //   156: aload_0
    //   157: getfield 64	org/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   160: ldc 238
    //   162: invokeinterface 191 2 0
    //   167: aload_1
    //   168: invokevirtual 239	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:shutdown	()V
    //   171: aload_1
    //   172: invokevirtual 242	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   175: aload_0
    //   176: aconst_null
    //   177: putfield 86	org/apache/http/impl/conn/SingleClientConnManager:managedConn	Lorg/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   180: aload_0
    //   181: invokestatic 111	java/lang/System:currentTimeMillis	()J
    //   184: putfield 90	org/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   187: lload_2
    //   188: lconst_0
    //   189: lcmp
    //   190: ifle +21 -> 211
    //   193: aload_0
    //   194: aload 4
    //   196: lload_2
    //   197: invokevirtual 143	java/util/concurrent/TimeUnit:toMillis	(J)J
    //   200: aload_0
    //   201: getfield 90	org/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   204: ladd
    //   205: putfield 113	org/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   208: goto +82 -> 290
    //   211: aload_0
    //   212: ldc2_w 243
    //   215: putfield 113	org/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   218: goto +72 -> 290
    //   221: astore 5
    //   223: goto +70 -> 293
    //   226: astore 5
    //   228: aload_0
    //   229: getfield 64	org/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   232: invokeinterface 172 1 0
    //   237: ifeq +16 -> 253
    //   240: aload_0
    //   241: getfield 64	org/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   244: ldc 246
    //   246: aload 5
    //   248: invokeinterface 154 3 0
    //   253: aload_1
    //   254: invokevirtual 242	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   257: aload_0
    //   258: aconst_null
    //   259: putfield 86	org/apache/http/impl/conn/SingleClientConnManager:managedConn	Lorg/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   262: aload_0
    //   263: invokestatic 111	java/lang/System:currentTimeMillis	()J
    //   266: putfield 90	org/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   269: lload_2
    //   270: lconst_0
    //   271: lcmp
    //   272: ifle -61 -> 211
    //   275: aload_0
    //   276: aload 4
    //   278: lload_2
    //   279: invokevirtual 143	java/util/concurrent/TimeUnit:toMillis	(J)J
    //   282: aload_0
    //   283: getfield 90	org/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   286: ladd
    //   287: putfield 113	org/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   290: aload_0
    //   291: monitorexit
    //   292: return
    //   293: aload_1
    //   294: invokevirtual 242	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   297: aload_0
    //   298: aconst_null
    //   299: putfield 86	org/apache/http/impl/conn/SingleClientConnManager:managedConn	Lorg/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   302: aload_0
    //   303: invokestatic 111	java/lang/System:currentTimeMillis	()J
    //   306: putfield 90	org/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   309: lload_2
    //   310: lconst_0
    //   311: lcmp
    //   312: ifle +21 -> 333
    //   315: aload_0
    //   316: aload 4
    //   318: lload_2
    //   319: invokevirtual 143	java/util/concurrent/TimeUnit:toMillis	(J)J
    //   322: aload_0
    //   323: getfield 90	org/apache/http/impl/conn/SingleClientConnManager:lastReleaseTime	J
    //   326: ladd
    //   327: putfield 113	org/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   330: goto +10 -> 340
    //   333: aload_0
    //   334: ldc2_w 243
    //   337: putfield 113	org/apache/http/impl/conn/SingleClientConnManager:connectionExpiresTime	J
    //   340: aload 5
    //   342: athrow
    //   343: astore_1
    //   344: aload_0
    //   345: monitorexit
    //   346: aload_1
    //   347: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   123	144	221	finally
    //   144	167	221	finally
    //   167	171	221	finally
    //   228	253	221	finally
    //   123	144	226	java/io/IOException
    //   144	167	226	java/io/IOException
    //   167	171	226	java/io/IOException
    //   2	23	343	finally
    //   23	77	343	finally
    //   77	88	343	finally
    //   96	102	343	finally
    //   113	123	343	finally
    //   171	187	343	finally
    //   193	208	343	finally
    //   211	218	343	finally
    //   253	269	343	finally
    //   275	290	343	finally
    //   293	309	343	finally
    //   315	330	343	finally
    //   333	340	343	finally
    //   340	343	343	finally
  }

  public final ClientConnectionRequest requestConnection(final HttpRoute paramHttpRoute, final Object paramObject)
  {
    return new ClientConnectionRequest()
    {
      public void abortRequest()
      {
      }

      public ManagedClientConnection getConnection(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
      {
        return SingleClientConnManager.this.getConnection(paramHttpRoute, paramObject);
      }
    };
  }

  @Deprecated
  protected void revokeConnection()
  {
    try
    {
      ConnAdapter localConnAdapter = this.managedConn;
      if (localConnAdapter == null)
        return;
      this.managedConn.detach();
      try
      {
        this.uniquePoolEntry.shutdown();
      }
      catch (IOException localIOException)
      {
        this.log.debug("Problem while shutting down connection.", localIOException);
      }
      return;
    }
    finally
    {
    }
  }

  // ERROR //
  public void shutdown()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield 94	org/apache/http/impl/conn/SingleClientConnManager:isShutDown	Z
    //   7: aload_0
    //   8: getfield 86	org/apache/http/impl/conn/SingleClientConnManager:managedConn	Lorg/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   11: ifnull +10 -> 21
    //   14: aload_0
    //   15: getfield 86	org/apache/http/impl/conn/SingleClientConnManager:managedConn	Lorg/apache/http/impl/conn/SingleClientConnManager$ConnAdapter;
    //   18: invokevirtual 242	org/apache/http/impl/conn/SingleClientConnManager$ConnAdapter:detach	()V
    //   21: aload_0
    //   22: getfield 84	org/apache/http/impl/conn/SingleClientConnManager:uniquePoolEntry	Lorg/apache/http/impl/conn/SingleClientConnManager$PoolEntry;
    //   25: ifnull +10 -> 35
    //   28: aload_0
    //   29: getfield 84	org/apache/http/impl/conn/SingleClientConnManager:uniquePoolEntry	Lorg/apache/http/impl/conn/SingleClientConnManager$PoolEntry;
    //   32: invokevirtual 210	org/apache/http/impl/conn/SingleClientConnManager$PoolEntry:shutdown	()V
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield 84	org/apache/http/impl/conn/SingleClientConnManager:uniquePoolEntry	Lorg/apache/http/impl/conn/SingleClientConnManager$PoolEntry;
    //   40: goto +24 -> 64
    //   43: astore_1
    //   44: goto +23 -> 67
    //   47: astore_1
    //   48: aload_0
    //   49: getfield 64	org/apache/http/impl/conn/SingleClientConnManager:log	Lorg/apache/commons/logging/Log;
    //   52: ldc_w 256
    //   55: aload_1
    //   56: invokeinterface 154 3 0
    //   61: goto -26 -> 35
    //   64: aload_0
    //   65: monitorexit
    //   66: return
    //   67: aload_0
    //   68: aconst_null
    //   69: putfield 84	org/apache/http/impl/conn/SingleClientConnManager:uniquePoolEntry	Lorg/apache/http/impl/conn/SingleClientConnManager$PoolEntry;
    //   72: aload_1
    //   73: athrow
    //   74: astore_1
    //   75: aload_0
    //   76: monitorexit
    //   77: aload_1
    //   78: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   21	35	43	finally
    //   48	61	43	finally
    //   21	35	47	java/io/IOException
    //   2	21	74	finally
    //   35	40	74	finally
    //   67	74	74	finally
  }

  protected class ConnAdapter extends AbstractPooledConnAdapter
  {
    protected ConnAdapter(SingleClientConnManager.PoolEntry paramHttpRoute, HttpRoute arg3)
    {
      super(paramHttpRoute);
      markReusable();
      Object localObject;
      paramHttpRoute.route = localObject;
    }
  }

  protected class PoolEntry extends AbstractPoolEntry
  {
    protected PoolEntry()
    {
      super(null);
    }

    protected void close()
      throws IOException
    {
      shutdownEntry();
      if (this.connection.isOpen())
        this.connection.close();
    }

    protected void shutdown()
      throws IOException
    {
      shutdownEntry();
      if (this.connection.isOpen())
        this.connection.shutdown();
    }
  }
}