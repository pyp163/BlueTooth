package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<FetchState>
{
  public static final int HTTP_DEFAULT_TIMEOUT = 30000;
  public static final int HTTP_PERMANENT_REDIRECT = 308;
  public static final int HTTP_TEMPORARY_REDIRECT = 307;
  private static final int MAX_REDIRECTS = 5;
  private static final int NUM_NETWORK_THREADS = 3;
  private final ExecutorService mExecutorService;
  private int mHttpConnectionTimeout;

  public HttpUrlConnectionNetworkFetcher()
  {
    this(Executors.newFixedThreadPool(3));
  }

  public HttpUrlConnectionNetworkFetcher(int paramInt)
  {
    this(Executors.newFixedThreadPool(3));
    this.mHttpConnectionTimeout = paramInt;
  }

  @VisibleForTesting
  HttpUrlConnectionNetworkFetcher(ExecutorService paramExecutorService)
  {
    this.mExecutorService = paramExecutorService;
  }

  private HttpURLConnection downloadFrom(Uri paramUri, int paramInt)
    throws IOException
  {
    Object localObject = openConnectionTo(paramUri);
    ((HttpURLConnection)localObject).setConnectTimeout(this.mHttpConnectionTimeout);
    int i = ((HttpURLConnection)localObject).getResponseCode();
    if (isHttpSuccess(i))
      return localObject;
    if (isHttpRedirect(i))
    {
      String str = ((HttpURLConnection)localObject).getHeaderField("Location");
      ((HttpURLConnection)localObject).disconnect();
      if (str == null)
        localObject = null;
      else
        localObject = Uri.parse(str);
      str = paramUri.getScheme();
      if ((paramInt > 0) && (localObject != null) && (!((Uri)localObject).getScheme().equals(str)))
        return downloadFrom((Uri)localObject, paramInt - 1);
      if (paramInt == 0)
        paramUri = error("URL %s follows too many redirects", new Object[] { paramUri.toString() });
      else
        paramUri = error("URL %s returned %d without a valid redirect", new Object[] { paramUri.toString(), Integer.valueOf(i) });
      throw new IOException(paramUri);
    }
    ((HttpURLConnection)localObject).disconnect();
    throw new IOException(String.format("Image URL %s returned HTTP code %d", new Object[] { paramUri.toString(), Integer.valueOf(i) }));
  }

  private static String error(String paramString, Object[] paramArrayOfObject)
  {
    return String.format(Locale.getDefault(), paramString, paramArrayOfObject);
  }

  private static boolean isHttpRedirect(int paramInt)
  {
    switch (paramInt)
    {
    case 304:
    case 305:
    case 306:
    default:
      return false;
    case 300:
    case 301:
    case 302:
    case 303:
    case 307:
    case 308:
    }
    return true;
  }

  private static boolean isHttpSuccess(int paramInt)
  {
    return (paramInt >= 200) && (paramInt < 300);
  }

  @VisibleForTesting
  static HttpURLConnection openConnectionTo(Uri paramUri)
    throws IOException
  {
    return (HttpURLConnection)UriUtil.uriToUrl(paramUri).openConnection();
  }

  public FetchState createFetchState(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    return new FetchState(paramConsumer, paramProducerContext);
  }

  public void fetch(final FetchState paramFetchState, final NetworkFetcher.Callback paramCallback)
  {
    final Future localFuture = this.mExecutorService.submit(new Runnable()
    {
      public void run()
      {
        HttpUrlConnectionNetworkFetcher.this.fetchSync(paramFetchState, paramCallback);
      }
    });
    paramFetchState.getContext().addCallbacks(new BaseProducerContextCallbacks()
    {
      public void onCancellationRequested()
      {
        if (localFuture.cancel(false))
          paramCallback.onCancellation();
      }
    });
  }

  // ERROR //
  @VisibleForTesting
  void fetchSync(FetchState paramFetchState, NetworkFetcher.Callback paramCallback)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: aconst_null
    //   7: astore_3
    //   8: aload_0
    //   9: aload_1
    //   10: invokevirtual 182	com/facebook/imagepipeline/producers/FetchState:getUri	()Landroid/net/Uri;
    //   13: iconst_5
    //   14: invokespecial 95	com/facebook/imagepipeline/producers/HttpUrlConnectionNetworkFetcher:downloadFrom	(Landroid/net/Uri;I)Ljava/net/HttpURLConnection;
    //   17: astore_1
    //   18: aload_1
    //   19: ifnull +55 -> 74
    //   22: aload_1
    //   23: invokevirtual 186	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   26: astore_3
    //   27: aload_2
    //   28: aload_3
    //   29: iconst_m1
    //   30: invokeinterface 192 3 0
    //   35: goto +39 -> 74
    //   38: astore 4
    //   40: aload_1
    //   41: astore_2
    //   42: aload 4
    //   44: astore_1
    //   45: goto +94 -> 139
    //   48: astore 4
    //   50: goto +57 -> 107
    //   53: astore 5
    //   55: aload 4
    //   57: astore_3
    //   58: aload_1
    //   59: astore_2
    //   60: aload 5
    //   62: astore_1
    //   63: goto +76 -> 139
    //   66: astore 4
    //   68: aload 5
    //   70: astore_3
    //   71: goto +36 -> 107
    //   74: aload_3
    //   75: ifnull +7 -> 82
    //   78: aload_3
    //   79: invokevirtual 197	java/io/InputStream:close	()V
    //   82: aload_1
    //   83: ifnull +48 -> 131
    //   86: aload_1
    //   87: invokevirtual 77	java/net/HttpURLConnection:disconnect	()V
    //   90: return
    //   91: astore_1
    //   92: aconst_null
    //   93: astore_2
    //   94: aload 4
    //   96: astore_3
    //   97: goto +42 -> 139
    //   100: astore 4
    //   102: aconst_null
    //   103: astore_1
    //   104: aload 5
    //   106: astore_3
    //   107: aload_2
    //   108: aload 4
    //   110: invokeinterface 201 2 0
    //   115: aload_3
    //   116: ifnull +7 -> 123
    //   119: aload_3
    //   120: invokevirtual 197	java/io/InputStream:close	()V
    //   123: aload_1
    //   124: ifnull +7 -> 131
    //   127: aload_1
    //   128: invokevirtual 77	java/net/HttpURLConnection:disconnect	()V
    //   131: return
    //   132: astore 4
    //   134: aload_1
    //   135: astore_2
    //   136: aload 4
    //   138: astore_1
    //   139: aload_3
    //   140: ifnull +7 -> 147
    //   143: aload_3
    //   144: invokevirtual 197	java/io/InputStream:close	()V
    //   147: aload_2
    //   148: ifnull +7 -> 155
    //   151: aload_2
    //   152: invokevirtual 77	java/net/HttpURLConnection:disconnect	()V
    //   155: aload_1
    //   156: athrow
    //   157: astore_2
    //   158: goto -76 -> 82
    //   161: astore_2
    //   162: goto -39 -> 123
    //   165: astore_3
    //   166: goto -19 -> 147
    //
    // Exception table:
    //   from	to	target	type
    //   27	35	38	finally
    //   27	35	48	java/io/IOException
    //   22	27	53	finally
    //   22	27	66	java/io/IOException
    //   8	18	91	finally
    //   8	18	100	java/io/IOException
    //   107	115	132	finally
    //   78	82	157	java/io/IOException
    //   119	123	161	java/io/IOException
    //   143	147	165	java/io/IOException
  }
}