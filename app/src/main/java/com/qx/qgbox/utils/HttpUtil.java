package com.qx.qgbox.utils;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class HttpUtil {
    public static final String TAG = "HttpUtil";

    public static void HttpURLConnectionGet(final Handler paramHandler, final String path, final int paramInt1, final int paramInt2) {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("-----http get url = ");
        localStringBuilder.append(path);
        MyLog.i("HttpUtil", localStringBuilder.toString());
        new Thread(new Runnable() {
            public void run() {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(path).openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        try {
                            JSONObject strJson = new JSONObject(CommonUtils.ReadStream(inputStream));
                            Message localMessage4 = new Message();
                            localMessage4.obj = strJson;
                            localMessage4.what = paramInt1;
                            paramHandler.sendMessage(localMessage4);
                            return;
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message message = new Message();
                            message.what = paramInt2;
                            paramHandler.sendMessage(message);
                            return;
                        }
                    }
                    Message localMessage1 = new Message();
                    localMessage1.what = paramInt2;
                    paramHandler.sendMessage(localMessage1);
                    return;
                } catch (IOException localIOException) {
                    localIOException.printStackTrace();
                    Message localMessage2 = new Message();
                    localMessage2.what = paramInt2;
                    paramHandler.sendMessage(localMessage2);
                    return;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    Message localMessage3 = new Message();
                    localMessage3.what = paramInt2;
                    paramHandler.sendMessage(localMessage3);
                }
            }
        }).start();
    }

    public static void HttpURLConnectionPost(final Handler paramHandler, final int paramInt1, final int paramInt2, String paramString1, final String paramString2, final String paramString3) {
        new Thread(new Runnable() {
            // ERROR //
            public void run() {
                // Byte code:
                //   0: new 43	java/net/URL
                //   3: dup
                //   4: aload_0
                //   5: getfield 24	com/qx/qgbox/utils/HttpUtil$2:val$postUrl	Ljava/lang/String;
                //   8: invokespecial 46	java/net/URL:<init>	(Ljava/lang/String;)V
                //   11: invokevirtual 50	java/net/URL:openConnection	()Ljava/net/URLConnection;
                //   14: checkcast 52	java/net/HttpURLConnection
                //   17: astore_2
                //   18: aload_2
                //   19: astore_3
                //   20: aload_2
                //   21: ldc 54
                //   23: invokevirtual 57	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
                //   26: aload_2
                //   27: astore_3
                //   28: aload_2
                //   29: iconst_1
                //   30: invokevirtual 61	java/net/HttpURLConnection:setDoOutput	(Z)V
                //   33: aload_2
                //   34: astore_3
                //   35: aload_2
                //   36: iconst_1
                //   37: invokevirtual 64	java/net/HttpURLConnection:setDoInput	(Z)V
                //   40: aload_2
                //   41: astore_3
                //   42: aload_2
                //   43: iconst_0
                //   44: invokevirtual 67	java/net/HttpURLConnection:setUseCaches	(Z)V
                //   47: aload_2
                //   48: astore_3
                //   49: aload_2
                //   50: ldc 69
                //   52: ldc 71
                //   54: invokevirtual 75	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   57: aload_2
                //   58: astore_3
                //   59: aload_2
                //   60: ldc 77
                //   62: ldc 79
                //   64: invokevirtual 75	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   67: aload_2
                //   68: astore_3
                //   69: aload_2
                //   70: ldc 81
                //   72: ldc 83
                //   74: invokevirtual 75	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   77: aload_2
                //   78: astore_3
                //   79: aload_2
                //   80: sipush 30000
                //   83: invokevirtual 87	java/net/HttpURLConnection:setConnectTimeout	(I)V
                //   86: aload_2
                //   87: astore_3
                //   88: aload_2
                //   89: sipush 30000
                //   92: invokevirtual 90	java/net/HttpURLConnection:setReadTimeout	(I)V
                //   95: aload_2
                //   96: astore_3
                //   97: new 92	java/lang/StringBuilder
                //   100: dup
                //   101: invokespecial 93	java/lang/StringBuilder:<init>	()V
                //   104: astore 4
                //   106: aload_2
                //   107: astore_3
                //   108: aload 4
                //   110: ldc 95
                //   112: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   115: pop
                //   116: aload_2
                //   117: astore_3
                //   118: aload 4
                //   120: aload_0
                //   121: getfield 26	com/qx/qgbox/utils/HttpUtil$2:val$pwd	Ljava/lang/String;
                //   124: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   127: pop
                //   128: aload_2
                //   129: astore_3
                //   130: aload 4
                //   132: ldc 101
                //   134: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   137: pop
                //   138: aload_2
                //   139: astore_3
                //   140: aload 4
                //   142: aload_0
                //   143: getfield 28	com/qx/qgbox/utils/HttpUtil$2:val$loadFileUrl	Ljava/lang/String;
                //   146: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   149: pop
                //   150: aload_2
                //   151: astore_3
                //   152: aload 4
                //   154: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   157: astore 4
                //   159: aload_2
                //   160: astore_3
                //   161: aload_2
                //   162: invokevirtual 109	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
                //   165: astore 5
                //   167: aload_2
                //   168: astore_3
                //   169: aload 5
                //   171: aload 4
                //   173: invokevirtual 115	java/lang/String:getBytes	()[B
                //   176: invokevirtual 121	java/io/OutputStream:write	([B)V
                //   179: aload_2
                //   180: astore_3
                //   181: aload 5
                //   183: invokevirtual 124	java/io/OutputStream:flush	()V
                //   186: aload_2
                //   187: astore_3
                //   188: aload 5
                //   190: invokevirtual 127	java/io/OutputStream:close	()V
                //   193: aload_2
                //   194: astore_3
                //   195: aload_2
                //   196: invokevirtual 131	java/net/HttpURLConnection:getResponseCode	()I
                //   199: sipush 200
                //   202: if_icmpne +107 -> 309
                //   205: aload_2
                //   206: astore_3
                //   207: aload_2
                //   208: invokevirtual 135	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
                //   211: astore 4
                //   213: aload_2
                //   214: astore_3
                //   215: new 137	java/lang/StringBuffer
                //   218: dup
                //   219: invokespecial 138	java/lang/StringBuffer:<init>	()V
                //   222: astore 5
                //   224: aload_2
                //   225: astore_3
                //   226: aload 4
                //   228: invokevirtual 143	java/io/InputStream:read	()I
                //   231: istore_1
                //   232: iload_1
                //   233: iconst_m1
                //   234: if_icmpeq +16 -> 250
                //   237: aload_2
                //   238: astore_3
                //   239: aload 5
                //   241: iload_1
                //   242: i2c
                //   243: invokevirtual 146	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
                //   246: pop
                //   247: goto -23 -> 224
                //   250: aload_2
                //   251: astore_3
                //   252: new 148	org/json/JSONObject
                //   255: dup
                //   256: aload 5
                //   258: invokevirtual 149	java/lang/StringBuffer:toString	()Ljava/lang/String;
                //   261: invokespecial 150	org/json/JSONObject:<init>	(Ljava/lang/String;)V
                //   264: ldc 152
                //   266: invokevirtual 156	org/json/JSONObject:getInt	(Ljava/lang/String;)I
                //   269: sipush 1000
                //   272: if_icmpne +20 -> 292
                //   275: aload_2
                //   276: astore_3
                //   277: aload_0
                //   278: getfield 30	com/qx/qgbox/utils/HttpUtil$2:val$handler	Landroid/os/Handler;
                //   281: aload_0
                //   282: getfield 32	com/qx/qgbox/utils/HttpUtil$2:val$successWhat	I
                //   285: invokevirtual 162	android/os/Handler:sendEmptyMessage	(I)Z
                //   288: pop
                //   289: goto +34 -> 323
                //   292: aload_2
                //   293: astore_3
                //   294: aload_0
                //   295: getfield 30	com/qx/qgbox/utils/HttpUtil$2:val$handler	Landroid/os/Handler;
                //   298: aload_0
                //   299: getfield 34	com/qx/qgbox/utils/HttpUtil$2:val$failWhat	I
                //   302: invokevirtual 162	android/os/Handler:sendEmptyMessage	(I)Z
                //   305: pop
                //   306: goto +17 -> 323
                //   309: aload_2
                //   310: astore_3
                //   311: aload_0
                //   312: getfield 30	com/qx/qgbox/utils/HttpUtil$2:val$handler	Landroid/os/Handler;
                //   315: aload_0
                //   316: getfield 34	com/qx/qgbox/utils/HttpUtil$2:val$failWhat	I
                //   319: invokevirtual 162	android/os/Handler:sendEmptyMessage	(I)Z
                //   322: pop
                //   323: aload_2
                //   324: ifnull +50 -> 374
                //   327: goto +43 -> 370
                //   330: astore 4
                //   332: goto +13 -> 345
                //   335: astore_2
                //   336: aconst_null
                //   337: astore_3
                //   338: goto +38 -> 376
                //   341: astore 4
                //   343: aconst_null
                //   344: astore_2
                //   345: aload_2
                //   346: astore_3
                //   347: aload 4
                //   349: invokevirtual 165	java/lang/Exception:printStackTrace	()V
                //   352: aload_2
                //   353: astore_3
                //   354: aload_0
                //   355: getfield 30	com/qx/qgbox/utils/HttpUtil$2:val$handler	Landroid/os/Handler;
                //   358: aload_0
                //   359: getfield 34	com/qx/qgbox/utils/HttpUtil$2:val$failWhat	I
                //   362: invokevirtual 162	android/os/Handler:sendEmptyMessage	(I)Z
                //   365: pop
                //   366: aload_2
                //   367: ifnull +7 -> 374
                //   370: aload_2
                //   371: invokevirtual 168	java/net/HttpURLConnection:disconnect	()V
                //   374: return
                //   375: astore_2
                //   376: aload_3
                //   377: ifnull +7 -> 384
                //   380: aload_3
                //   381: invokevirtual 168	java/net/HttpURLConnection:disconnect	()V
                //   384: aload_2
                //   385: athrow
                //
                // Exception table:
                //   from	to	target	type
                //   20	26	330	java/lang/Exception
                //   28	33	330	java/lang/Exception
                //   35	40	330	java/lang/Exception
                //   42	47	330	java/lang/Exception
                //   49	57	330	java/lang/Exception
                //   59	67	330	java/lang/Exception
                //   69	77	330	java/lang/Exception
                //   79	86	330	java/lang/Exception
                //   88	95	330	java/lang/Exception
                //   97	106	330	java/lang/Exception
                //   108	116	330	java/lang/Exception
                //   118	128	330	java/lang/Exception
                //   130	138	330	java/lang/Exception
                //   140	150	330	java/lang/Exception
                //   152	159	330	java/lang/Exception
                //   161	167	330	java/lang/Exception
                //   169	179	330	java/lang/Exception
                //   181	186	330	java/lang/Exception
                //   188	193	330	java/lang/Exception
                //   195	205	330	java/lang/Exception
                //   207	213	330	java/lang/Exception
                //   215	224	330	java/lang/Exception
                //   226	232	330	java/lang/Exception
                //   239	247	330	java/lang/Exception
                //   252	275	330	java/lang/Exception
                //   277	289	330	java/lang/Exception
                //   294	306	330	java/lang/Exception
                //   311	323	330	java/lang/Exception
                //   0	18	335	finally
                //   0	18	341	java/lang/Exception
                //   20	26	375	finally
                //   28	33	375	finally
                //   35	40	375	finally
                //   42	47	375	finally
                //   49	57	375	finally
                //   59	67	375	finally
                //   69	77	375	finally
                //   79	86	375	finally
                //   88	95	375	finally
                //   97	106	375	finally
                //   108	116	375	finally
                //   118	128	375	finally
                //   130	138	375	finally
                //   140	150	375	finally
                //   152	159	375	finally
                //   161	167	375	finally
                //   169	179	375	finally
                //   181	186	375	finally
                //   188	193	375	finally
                //   195	205	375	finally
                //   207	213	375	finally
                //   215	224	375	finally
                //   226	232	375	finally
                //   239	247	375	finally
                //   252	275	375	finally
                //   277	289	375	finally
                //   294	306	375	finally
                //   311	323	375	finally
                //   347	352	375	finally
                //   354	366	375	finally
            }
        }).start();
    }

    public static void HttpURLConnectionPost(final Handler paramHandler, final int paramInt1, final int paramInt2, String paramString1, final String paramString2, final String paramString3, final String paramString4) {
        new Thread(new Runnable() {
            // ERROR //
            public void run() {
                // Byte code:
                //   0: new 46	java/net/URL
                //   3: dup
                //   4: aload_0
                //   5: getfield 25	com/qx/qgbox/utils/HttpUtil$3:val$postUrl	Ljava/lang/String;
                //   8: invokespecial 49	java/net/URL:<init>	(Ljava/lang/String;)V
                //   11: invokevirtual 53	java/net/URL:openConnection	()Ljava/net/URLConnection;
                //   14: checkcast 55	java/net/HttpURLConnection
                //   17: astore_2
                //   18: aload_2
                //   19: astore_3
                //   20: aload_2
                //   21: ldc 57
                //   23: invokevirtual 60	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
                //   26: aload_2
                //   27: astore_3
                //   28: aload_2
                //   29: iconst_1
                //   30: invokevirtual 64	java/net/HttpURLConnection:setDoOutput	(Z)V
                //   33: aload_2
                //   34: astore_3
                //   35: aload_2
                //   36: iconst_1
                //   37: invokevirtual 67	java/net/HttpURLConnection:setDoInput	(Z)V
                //   40: aload_2
                //   41: astore_3
                //   42: aload_2
                //   43: iconst_0
                //   44: invokevirtual 70	java/net/HttpURLConnection:setUseCaches	(Z)V
                //   47: aload_2
                //   48: astore_3
                //   49: aload_2
                //   50: ldc 72
                //   52: ldc 74
                //   54: invokevirtual 78	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   57: aload_2
                //   58: astore_3
                //   59: aload_2
                //   60: ldc 80
                //   62: ldc 82
                //   64: invokevirtual 78	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   67: aload_2
                //   68: astore_3
                //   69: aload_2
                //   70: ldc 84
                //   72: ldc 86
                //   74: invokevirtual 78	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   77: aload_2
                //   78: astore_3
                //   79: aload_2
                //   80: sipush 30000
                //   83: invokevirtual 90	java/net/HttpURLConnection:setConnectTimeout	(I)V
                //   86: aload_2
                //   87: astore_3
                //   88: aload_2
                //   89: sipush 30000
                //   92: invokevirtual 93	java/net/HttpURLConnection:setReadTimeout	(I)V
                //   95: aload_2
                //   96: astore_3
                //   97: new 95	java/lang/StringBuilder
                //   100: dup
                //   101: invokespecial 96	java/lang/StringBuilder:<init>	()V
                //   104: astore 4
                //   106: aload_2
                //   107: astore_3
                //   108: aload 4
                //   110: ldc 98
                //   112: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   115: pop
                //   116: aload_2
                //   117: astore_3
                //   118: aload 4
                //   120: aload_0
                //   121: getfield 27	com/qx/qgbox/utils/HttpUtil$3:val$pwd	Ljava/lang/String;
                //   124: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   127: pop
                //   128: aload_2
                //   129: astore_3
                //   130: aload 4
                //   132: ldc 104
                //   134: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   137: pop
                //   138: aload_2
                //   139: astore_3
                //   140: aload 4
                //   142: aload_0
                //   143: getfield 29	com/qx/qgbox/utils/HttpUtil$3:val$loadIniFileUrl	Ljava/lang/String;
                //   146: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   149: pop
                //   150: aload_2
                //   151: astore_3
                //   152: aload 4
                //   154: ldc 106
                //   156: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   159: pop
                //   160: aload_2
                //   161: astore_3
                //   162: aload 4
                //   164: aload_0
                //   165: getfield 31	com/qx/qgbox/utils/HttpUtil$3:val$loadTxtFileUrl	Ljava/lang/String;
                //   168: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   171: pop
                //   172: aload_2
                //   173: astore_3
                //   174: aload 4
                //   176: invokevirtual 110	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   179: astore 4
                //   181: aload_2
                //   182: astore_3
                //   183: aload_2
                //   184: invokevirtual 114	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
                //   187: astore 5
                //   189: aload_2
                //   190: astore_3
                //   191: aload 5
                //   193: aload 4
                //   195: invokevirtual 120	java/lang/String:getBytes	()[B
                //   198: invokevirtual 126	java/io/OutputStream:write	([B)V
                //   201: aload_2
                //   202: astore_3
                //   203: aload 5
                //   205: invokevirtual 129	java/io/OutputStream:flush	()V
                //   208: aload_2
                //   209: astore_3
                //   210: aload 5
                //   212: invokevirtual 132	java/io/OutputStream:close	()V
                //   215: aload_2
                //   216: astore_3
                //   217: aload_2
                //   218: invokevirtual 136	java/net/HttpURLConnection:getResponseCode	()I
                //   221: sipush 200
                //   224: if_icmpne +107 -> 331
                //   227: aload_2
                //   228: astore_3
                //   229: aload_2
                //   230: invokevirtual 140	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
                //   233: astore 4
                //   235: aload_2
                //   236: astore_3
                //   237: new 142	java/lang/StringBuffer
                //   240: dup
                //   241: invokespecial 143	java/lang/StringBuffer:<init>	()V
                //   244: astore 5
                //   246: aload_2
                //   247: astore_3
                //   248: aload 4
                //   250: invokevirtual 148	java/io/InputStream:read	()I
                //   253: istore_1
                //   254: iload_1
                //   255: iconst_m1
                //   256: if_icmpeq +16 -> 272
                //   259: aload_2
                //   260: astore_3
                //   261: aload 5
                //   263: iload_1
                //   264: i2c
                //   265: invokevirtual 151	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
                //   268: pop
                //   269: goto -23 -> 246
                //   272: aload_2
                //   273: astore_3
                //   274: new 153	org/json/JSONObject
                //   277: dup
                //   278: aload 5
                //   280: invokevirtual 154	java/lang/StringBuffer:toString	()Ljava/lang/String;
                //   283: invokespecial 155	org/json/JSONObject:<init>	(Ljava/lang/String;)V
                //   286: ldc 157
                //   288: invokevirtual 161	org/json/JSONObject:getInt	(Ljava/lang/String;)I
                //   291: sipush 1000
                //   294: if_icmpne +20 -> 314
                //   297: aload_2
                //   298: astore_3
                //   299: aload_0
                //   300: getfield 33	com/qx/qgbox/utils/HttpUtil$3:val$handler	Landroid/os/Handler;
                //   303: aload_0
                //   304: getfield 35	com/qx/qgbox/utils/HttpUtil$3:val$successWhat	I
                //   307: invokevirtual 167	android/os/Handler:sendEmptyMessage	(I)Z
                //   310: pop
                //   311: goto +34 -> 345
                //   314: aload_2
                //   315: astore_3
                //   316: aload_0
                //   317: getfield 33	com/qx/qgbox/utils/HttpUtil$3:val$handler	Landroid/os/Handler;
                //   320: aload_0
                //   321: getfield 37	com/qx/qgbox/utils/HttpUtil$3:val$failWhat	I
                //   324: invokevirtual 167	android/os/Handler:sendEmptyMessage	(I)Z
                //   327: pop
                //   328: goto +17 -> 345
                //   331: aload_2
                //   332: astore_3
                //   333: aload_0
                //   334: getfield 33	com/qx/qgbox/utils/HttpUtil$3:val$handler	Landroid/os/Handler;
                //   337: aload_0
                //   338: getfield 37	com/qx/qgbox/utils/HttpUtil$3:val$failWhat	I
                //   341: invokevirtual 167	android/os/Handler:sendEmptyMessage	(I)Z
                //   344: pop
                //   345: aload_2
                //   346: ifnull +50 -> 396
                //   349: goto +43 -> 392
                //   352: astore 4
                //   354: goto +13 -> 367
                //   357: astore_2
                //   358: aconst_null
                //   359: astore_3
                //   360: goto +38 -> 398
                //   363: astore 4
                //   365: aconst_null
                //   366: astore_2
                //   367: aload_2
                //   368: astore_3
                //   369: aload 4
                //   371: invokevirtual 170	java/lang/Exception:printStackTrace	()V
                //   374: aload_2
                //   375: astore_3
                //   376: aload_0
                //   377: getfield 33	com/qx/qgbox/utils/HttpUtil$3:val$handler	Landroid/os/Handler;
                //   380: aload_0
                //   381: getfield 37	com/qx/qgbox/utils/HttpUtil$3:val$failWhat	I
                //   384: invokevirtual 167	android/os/Handler:sendEmptyMessage	(I)Z
                //   387: pop
                //   388: aload_2
                //   389: ifnull +7 -> 396
                //   392: aload_2
                //   393: invokevirtual 173	java/net/HttpURLConnection:disconnect	()V
                //   396: return
                //   397: astore_2
                //   398: aload_3
                //   399: ifnull +7 -> 406
                //   402: aload_3
                //   403: invokevirtual 173	java/net/HttpURLConnection:disconnect	()V
                //   406: aload_2
                //   407: athrow
                //
                // Exception table:
                //   from	to	target	type
                //   20	26	352	java/lang/Exception
                //   28	33	352	java/lang/Exception
                //   35	40	352	java/lang/Exception
                //   42	47	352	java/lang/Exception
                //   49	57	352	java/lang/Exception
                //   59	67	352	java/lang/Exception
                //   69	77	352	java/lang/Exception
                //   79	86	352	java/lang/Exception
                //   88	95	352	java/lang/Exception
                //   97	106	352	java/lang/Exception
                //   108	116	352	java/lang/Exception
                //   118	128	352	java/lang/Exception
                //   130	138	352	java/lang/Exception
                //   140	150	352	java/lang/Exception
                //   152	160	352	java/lang/Exception
                //   162	172	352	java/lang/Exception
                //   174	181	352	java/lang/Exception
                //   183	189	352	java/lang/Exception
                //   191	201	352	java/lang/Exception
                //   203	208	352	java/lang/Exception
                //   210	215	352	java/lang/Exception
                //   217	227	352	java/lang/Exception
                //   229	235	352	java/lang/Exception
                //   237	246	352	java/lang/Exception
                //   248	254	352	java/lang/Exception
                //   261	269	352	java/lang/Exception
                //   274	297	352	java/lang/Exception
                //   299	311	352	java/lang/Exception
                //   316	328	352	java/lang/Exception
                //   333	345	352	java/lang/Exception
                //   0	18	357	finally
                //   0	18	363	java/lang/Exception
                //   20	26	397	finally
                //   28	33	397	finally
                //   35	40	397	finally
                //   42	47	397	finally
                //   49	57	397	finally
                //   59	67	397	finally
                //   69	77	397	finally
                //   79	86	397	finally
                //   88	95	397	finally
                //   97	106	397	finally
                //   108	116	397	finally
                //   118	128	397	finally
                //   130	138	397	finally
                //   140	150	397	finally
                //   152	160	397	finally
                //   162	172	397	finally
                //   174	181	397	finally
                //   183	189	397	finally
                //   191	201	397	finally
                //   203	208	397	finally
                //   210	215	397	finally
                //   217	227	397	finally
                //   229	235	397	finally
                //   237	246	397	finally
                //   248	254	397	finally
                //   261	269	397	finally
                //   274	297	397	finally
                //   299	311	397	finally
                //   316	328	397	finally
                //   333	345	397	finally
                //   369	374	397	finally
                //   376	388	397	finally
            }
        }).start();
    }

    public static void addOfficialGamePresetPost(final Handler paramHandler, final int paramInt1, final int paramInt2, String paramString1, final int paramInt3, final int paramInt4, final int paramInt5, final String paramString2, final String paramString3) {
        new Thread(new Runnable() {
            // ERROR //
            public void run() {
                // Byte code:
                //   0: new 52	java/net/URL
                //   3: dup
                //   4: aload_0
                //   5: getfield 27	com/qx/qgbox/utils/HttpUtil$6:val$postUrl	Ljava/lang/String;
                //   8: invokespecial 55	java/net/URL:<init>	(Ljava/lang/String;)V
                //   11: invokevirtual 59	java/net/URL:openConnection	()Ljava/net/URLConnection;
                //   14: checkcast 61	java/net/HttpURLConnection
                //   17: astore_2
                //   18: aload_2
                //   19: astore_3
                //   20: aload_2
                //   21: ldc 63
                //   23: invokevirtual 66	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
                //   26: aload_2
                //   27: astore_3
                //   28: aload_2
                //   29: iconst_1
                //   30: invokevirtual 70	java/net/HttpURLConnection:setDoOutput	(Z)V
                //   33: aload_2
                //   34: astore_3
                //   35: aload_2
                //   36: iconst_1
                //   37: invokevirtual 73	java/net/HttpURLConnection:setDoInput	(Z)V
                //   40: aload_2
                //   41: astore_3
                //   42: aload_2
                //   43: iconst_0
                //   44: invokevirtual 76	java/net/HttpURLConnection:setUseCaches	(Z)V
                //   47: aload_2
                //   48: astore_3
                //   49: aload_2
                //   50: ldc 78
                //   52: ldc 80
                //   54: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   57: aload_2
                //   58: astore_3
                //   59: aload_2
                //   60: ldc 86
                //   62: ldc 88
                //   64: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   67: aload_2
                //   68: astore_3
                //   69: aload_2
                //   70: ldc 90
                //   72: ldc 92
                //   74: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   77: aload_2
                //   78: astore_3
                //   79: aload_2
                //   80: sipush 30000
                //   83: invokevirtual 96	java/net/HttpURLConnection:setConnectTimeout	(I)V
                //   86: aload_2
                //   87: astore_3
                //   88: aload_2
                //   89: sipush 30000
                //   92: invokevirtual 99	java/net/HttpURLConnection:setReadTimeout	(I)V
                //   95: aload_2
                //   96: astore_3
                //   97: new 101	java/lang/StringBuilder
                //   100: dup
                //   101: invokespecial 102	java/lang/StringBuilder:<init>	()V
                //   104: astore 4
                //   106: aload_2
                //   107: astore_3
                //   108: aload 4
                //   110: ldc 104
                //   112: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   115: pop
                //   116: aload_2
                //   117: astore_3
                //   118: aload 4
                //   120: aload_0
                //   121: getfield 29	com/qx/qgbox/utils/HttpUtil$6:val$platform	I
                //   124: invokevirtual 111	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   127: pop
                //   128: aload_2
                //   129: astore_3
                //   130: aload 4
                //   132: ldc 113
                //   134: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   137: pop
                //   138: aload_2
                //   139: astore_3
                //   140: aload 4
                //   142: aload_0
                //   143: getfield 31	com/qx/qgbox/utils/HttpUtil$6:val$type	I
                //   146: invokevirtual 111	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   149: pop
                //   150: aload_2
                //   151: astore_3
                //   152: aload 4
                //   154: ldc 115
                //   156: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   159: pop
                //   160: aload_2
                //   161: astore_3
                //   162: aload 4
                //   164: aload_0
                //   165: getfield 33	com/qx/qgbox/utils/HttpUtil$6:val$gameId	I
                //   168: invokevirtual 111	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   171: pop
                //   172: aload_2
                //   173: astore_3
                //   174: aload 4
                //   176: ldc 117
                //   178: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   181: pop
                //   182: aload_2
                //   183: astore_3
                //   184: aload 4
                //   186: aload_0
                //   187: getfield 35	com/qx/qgbox/utils/HttpUtil$6:val$resolution	Ljava/lang/String;
                //   190: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   193: pop
                //   194: aload_2
                //   195: astore_3
                //   196: aload 4
                //   198: ldc 119
                //   200: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   203: pop
                //   204: aload_2
                //   205: astore_3
                //   206: aload 4
                //   208: aload_0
                //   209: getfield 37	com/qx/qgbox/utils/HttpUtil$6:val$fileUrl	Ljava/lang/String;
                //   212: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   215: pop
                //   216: aload_2
                //   217: astore_3
                //   218: aload 4
                //   220: invokevirtual 123	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   223: astore 4
                //   225: aload_2
                //   226: astore_3
                //   227: aload_2
                //   228: invokevirtual 127	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
                //   231: astore 5
                //   233: aload_2
                //   234: astore_3
                //   235: aload 5
                //   237: aload 4
                //   239: invokevirtual 133	java/lang/String:getBytes	()[B
                //   242: invokevirtual 139	java/io/OutputStream:write	([B)V
                //   245: aload_2
                //   246: astore_3
                //   247: aload 5
                //   249: invokevirtual 142	java/io/OutputStream:flush	()V
                //   252: aload_2
                //   253: astore_3
                //   254: aload 5
                //   256: invokevirtual 145	java/io/OutputStream:close	()V
                //   259: aload_2
                //   260: astore_3
                //   261: aload_2
                //   262: invokevirtual 149	java/net/HttpURLConnection:getResponseCode	()I
                //   265: sipush 200
                //   268: if_icmpne +107 -> 375
                //   271: aload_2
                //   272: astore_3
                //   273: aload_2
                //   274: invokevirtual 153	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
                //   277: astore 4
                //   279: aload_2
                //   280: astore_3
                //   281: new 155	java/lang/StringBuffer
                //   284: dup
                //   285: invokespecial 156	java/lang/StringBuffer:<init>	()V
                //   288: astore 5
                //   290: aload_2
                //   291: astore_3
                //   292: aload 4
                //   294: invokevirtual 161	java/io/InputStream:read	()I
                //   297: istore_1
                //   298: iload_1
                //   299: iconst_m1
                //   300: if_icmpeq +16 -> 316
                //   303: aload_2
                //   304: astore_3
                //   305: aload 5
                //   307: iload_1
                //   308: i2c
                //   309: invokevirtual 164	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
                //   312: pop
                //   313: goto -23 -> 290
                //   316: aload_2
                //   317: astore_3
                //   318: new 166	org/json/JSONObject
                //   321: dup
                //   322: aload 5
                //   324: invokevirtual 167	java/lang/StringBuffer:toString	()Ljava/lang/String;
                //   327: invokespecial 168	org/json/JSONObject:<init>	(Ljava/lang/String;)V
                //   330: ldc 170
                //   332: invokevirtual 174	org/json/JSONObject:getInt	(Ljava/lang/String;)I
                //   335: sipush 1000
                //   338: if_icmpne +20 -> 358
                //   341: aload_2
                //   342: astore_3
                //   343: aload_0
                //   344: getfield 39	com/qx/qgbox/utils/HttpUtil$6:val$handler	Landroid/os/Handler;
                //   347: aload_0
                //   348: getfield 41	com/qx/qgbox/utils/HttpUtil$6:val$successWhat	I
                //   351: invokevirtual 180	android/os/Handler:sendEmptyMessage	(I)Z
                //   354: pop
                //   355: goto +34 -> 389
                //   358: aload_2
                //   359: astore_3
                //   360: aload_0
                //   361: getfield 39	com/qx/qgbox/utils/HttpUtil$6:val$handler	Landroid/os/Handler;
                //   364: aload_0
                //   365: getfield 43	com/qx/qgbox/utils/HttpUtil$6:val$failWhat	I
                //   368: invokevirtual 180	android/os/Handler:sendEmptyMessage	(I)Z
                //   371: pop
                //   372: goto +17 -> 389
                //   375: aload_2
                //   376: astore_3
                //   377: aload_0
                //   378: getfield 39	com/qx/qgbox/utils/HttpUtil$6:val$handler	Landroid/os/Handler;
                //   381: aload_0
                //   382: getfield 43	com/qx/qgbox/utils/HttpUtil$6:val$failWhat	I
                //   385: invokevirtual 180	android/os/Handler:sendEmptyMessage	(I)Z
                //   388: pop
                //   389: aload_2
                //   390: ifnull +50 -> 440
                //   393: goto +43 -> 436
                //   396: astore 4
                //   398: goto +13 -> 411
                //   401: astore_2
                //   402: aconst_null
                //   403: astore_3
                //   404: goto +38 -> 442
                //   407: astore 4
                //   409: aconst_null
                //   410: astore_2
                //   411: aload_2
                //   412: astore_3
                //   413: aload 4
                //   415: invokevirtual 183	java/lang/Exception:printStackTrace	()V
                //   418: aload_2
                //   419: astore_3
                //   420: aload_0
                //   421: getfield 39	com/qx/qgbox/utils/HttpUtil$6:val$handler	Landroid/os/Handler;
                //   424: aload_0
                //   425: getfield 43	com/qx/qgbox/utils/HttpUtil$6:val$failWhat	I
                //   428: invokevirtual 180	android/os/Handler:sendEmptyMessage	(I)Z
                //   431: pop
                //   432: aload_2
                //   433: ifnull +7 -> 440
                //   436: aload_2
                //   437: invokevirtual 186	java/net/HttpURLConnection:disconnect	()V
                //   440: return
                //   441: astore_2
                //   442: aload_3
                //   443: ifnull +7 -> 450
                //   446: aload_3
                //   447: invokevirtual 186	java/net/HttpURLConnection:disconnect	()V
                //   450: aload_2
                //   451: athrow
                //
                // Exception table:
                //   from	to	target	type
                //   20	26	396	java/lang/Exception
                //   28	33	396	java/lang/Exception
                //   35	40	396	java/lang/Exception
                //   42	47	396	java/lang/Exception
                //   49	57	396	java/lang/Exception
                //   59	67	396	java/lang/Exception
                //   69	77	396	java/lang/Exception
                //   79	86	396	java/lang/Exception
                //   88	95	396	java/lang/Exception
                //   97	106	396	java/lang/Exception
                //   108	116	396	java/lang/Exception
                //   118	128	396	java/lang/Exception
                //   130	138	396	java/lang/Exception
                //   140	150	396	java/lang/Exception
                //   152	160	396	java/lang/Exception
                //   162	172	396	java/lang/Exception
                //   174	182	396	java/lang/Exception
                //   184	194	396	java/lang/Exception
                //   196	204	396	java/lang/Exception
                //   206	216	396	java/lang/Exception
                //   218	225	396	java/lang/Exception
                //   227	233	396	java/lang/Exception
                //   235	245	396	java/lang/Exception
                //   247	252	396	java/lang/Exception
                //   254	259	396	java/lang/Exception
                //   261	271	396	java/lang/Exception
                //   273	279	396	java/lang/Exception
                //   281	290	396	java/lang/Exception
                //   292	298	396	java/lang/Exception
                //   305	313	396	java/lang/Exception
                //   318	341	396	java/lang/Exception
                //   343	355	396	java/lang/Exception
                //   360	372	396	java/lang/Exception
                //   377	389	396	java/lang/Exception
                //   0	18	401	finally
                //   0	18	407	java/lang/Exception
                //   20	26	441	finally
                //   28	33	441	finally
                //   35	40	441	finally
                //   42	47	441	finally
                //   49	57	441	finally
                //   59	67	441	finally
                //   69	77	441	finally
                //   79	86	441	finally
                //   88	95	441	finally
                //   97	106	441	finally
                //   108	116	441	finally
                //   118	128	441	finally
                //   130	138	441	finally
                //   140	150	441	finally
                //   152	160	441	finally
                //   162	172	441	finally
                //   174	182	441	finally
                //   184	194	441	finally
                //   196	204	441	finally
                //   206	216	441	finally
                //   218	225	441	finally
                //   227	233	441	finally
                //   235	245	441	finally
                //   247	252	441	finally
                //   254	259	441	finally
                //   261	271	441	finally
                //   273	279	441	finally
                //   281	290	441	finally
                //   292	298	441	finally
                //   305	313	441	finally
                //   318	341	441	finally
                //   343	355	441	finally
                //   360	372	441	finally
                //   377	389	441	finally
                //   413	418	441	finally
                //   420	432	441	finally
            }
        }).start();
    }

    public static void addOfficialGamePresetPost(final Handler paramHandler, final int paramInt1, final int paramInt2, String paramString1, final int paramInt3, final int paramInt4, final int paramInt5, final String paramString2, final String paramString3, final String paramString4) {
        new Thread(new Runnable() {
            // ERROR //
            public void run() {
                // Byte code:
                //   0: new 55	java/net/URL
                //   3: dup
                //   4: aload_0
                //   5: getfield 28	com/qx/qgbox/utils/HttpUtil$7:val$postUrl	Ljava/lang/String;
                //   8: invokespecial 58	java/net/URL:<init>	(Ljava/lang/String;)V
                //   11: invokevirtual 62	java/net/URL:openConnection	()Ljava/net/URLConnection;
                //   14: checkcast 64	java/net/HttpURLConnection
                //   17: astore_2
                //   18: aload_2
                //   19: astore_3
                //   20: aload_2
                //   21: ldc 66
                //   23: invokevirtual 69	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
                //   26: aload_2
                //   27: astore_3
                //   28: aload_2
                //   29: iconst_1
                //   30: invokevirtual 73	java/net/HttpURLConnection:setDoOutput	(Z)V
                //   33: aload_2
                //   34: astore_3
                //   35: aload_2
                //   36: iconst_1
                //   37: invokevirtual 76	java/net/HttpURLConnection:setDoInput	(Z)V
                //   40: aload_2
                //   41: astore_3
                //   42: aload_2
                //   43: iconst_0
                //   44: invokevirtual 79	java/net/HttpURLConnection:setUseCaches	(Z)V
                //   47: aload_2
                //   48: astore_3
                //   49: aload_2
                //   50: ldc 81
                //   52: ldc 83
                //   54: invokevirtual 87	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   57: aload_2
                //   58: astore_3
                //   59: aload_2
                //   60: ldc 89
                //   62: ldc 91
                //   64: invokevirtual 87	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   67: aload_2
                //   68: astore_3
                //   69: aload_2
                //   70: ldc 93
                //   72: ldc 95
                //   74: invokevirtual 87	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   77: aload_2
                //   78: astore_3
                //   79: aload_2
                //   80: sipush 30000
                //   83: invokevirtual 99	java/net/HttpURLConnection:setConnectTimeout	(I)V
                //   86: aload_2
                //   87: astore_3
                //   88: aload_2
                //   89: sipush 30000
                //   92: invokevirtual 102	java/net/HttpURLConnection:setReadTimeout	(I)V
                //   95: aload_2
                //   96: astore_3
                //   97: new 104	java/lang/StringBuilder
                //   100: dup
                //   101: invokespecial 105	java/lang/StringBuilder:<init>	()V
                //   104: astore 4
                //   106: aload_2
                //   107: astore_3
                //   108: aload 4
                //   110: ldc 107
                //   112: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   115: pop
                //   116: aload_2
                //   117: astore_3
                //   118: aload 4
                //   120: aload_0
                //   121: getfield 30	com/qx/qgbox/utils/HttpUtil$7:val$platform	I
                //   124: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   127: pop
                //   128: aload_2
                //   129: astore_3
                //   130: aload 4
                //   132: ldc 116
                //   134: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   137: pop
                //   138: aload_2
                //   139: astore_3
                //   140: aload 4
                //   142: aload_0
                //   143: getfield 32	com/qx/qgbox/utils/HttpUtil$7:val$type	I
                //   146: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   149: pop
                //   150: aload_2
                //   151: astore_3
                //   152: aload 4
                //   154: ldc 118
                //   156: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   159: pop
                //   160: aload_2
                //   161: astore_3
                //   162: aload 4
                //   164: aload_0
                //   165: getfield 34	com/qx/qgbox/utils/HttpUtil$7:val$gameId	I
                //   168: invokevirtual 114	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   171: pop
                //   172: aload_2
                //   173: astore_3
                //   174: aload 4
                //   176: ldc 120
                //   178: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   181: pop
                //   182: aload_2
                //   183: astore_3
                //   184: aload 4
                //   186: aload_0
                //   187: getfield 36	com/qx/qgbox/utils/HttpUtil$7:val$resolution	Ljava/lang/String;
                //   190: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   193: pop
                //   194: aload_2
                //   195: astore_3
                //   196: aload 4
                //   198: ldc 122
                //   200: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   203: pop
                //   204: aload_2
                //   205: astore_3
                //   206: aload 4
                //   208: aload_0
                //   209: getfield 38	com/qx/qgbox/utils/HttpUtil$7:val$fileUrl	Ljava/lang/String;
                //   212: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   215: pop
                //   216: aload_2
                //   217: astore_3
                //   218: aload 4
                //   220: ldc 124
                //   222: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   225: pop
                //   226: aload_2
                //   227: astore_3
                //   228: aload 4
                //   230: aload_0
                //   231: getfield 40	com/qx/qgbox/utils/HttpUtil$7:val$txtfileUrl	Ljava/lang/String;
                //   234: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   237: pop
                //   238: aload_2
                //   239: astore_3
                //   240: aload 4
                //   242: invokevirtual 128	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   245: astore 4
                //   247: aload_2
                //   248: astore_3
                //   249: aload_2
                //   250: invokevirtual 132	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
                //   253: astore 5
                //   255: aload_2
                //   256: astore_3
                //   257: aload 5
                //   259: aload 4
                //   261: invokevirtual 138	java/lang/String:getBytes	()[B
                //   264: invokevirtual 144	java/io/OutputStream:write	([B)V
                //   267: aload_2
                //   268: astore_3
                //   269: aload 5
                //   271: invokevirtual 147	java/io/OutputStream:flush	()V
                //   274: aload_2
                //   275: astore_3
                //   276: aload 5
                //   278: invokevirtual 150	java/io/OutputStream:close	()V
                //   281: aload_2
                //   282: astore_3
                //   283: aload_2
                //   284: invokevirtual 154	java/net/HttpURLConnection:getResponseCode	()I
                //   287: sipush 200
                //   290: if_icmpne +107 -> 397
                //   293: aload_2
                //   294: astore_3
                //   295: aload_2
                //   296: invokevirtual 158	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
                //   299: astore 4
                //   301: aload_2
                //   302: astore_3
                //   303: new 160	java/lang/StringBuffer
                //   306: dup
                //   307: invokespecial 161	java/lang/StringBuffer:<init>	()V
                //   310: astore 5
                //   312: aload_2
                //   313: astore_3
                //   314: aload 4
                //   316: invokevirtual 166	java/io/InputStream:read	()I
                //   319: istore_1
                //   320: iload_1
                //   321: iconst_m1
                //   322: if_icmpeq +16 -> 338
                //   325: aload_2
                //   326: astore_3
                //   327: aload 5
                //   329: iload_1
                //   330: i2c
                //   331: invokevirtual 169	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
                //   334: pop
                //   335: goto -23 -> 312
                //   338: aload_2
                //   339: astore_3
                //   340: new 171	org/json/JSONObject
                //   343: dup
                //   344: aload 5
                //   346: invokevirtual 172	java/lang/StringBuffer:toString	()Ljava/lang/String;
                //   349: invokespecial 173	org/json/JSONObject:<init>	(Ljava/lang/String;)V
                //   352: ldc 175
                //   354: invokevirtual 179	org/json/JSONObject:getInt	(Ljava/lang/String;)I
                //   357: sipush 1000
                //   360: if_icmpne +20 -> 380
                //   363: aload_2
                //   364: astore_3
                //   365: aload_0
                //   366: getfield 42	com/qx/qgbox/utils/HttpUtil$7:val$handler	Landroid/os/Handler;
                //   369: aload_0
                //   370: getfield 44	com/qx/qgbox/utils/HttpUtil$7:val$successWhat	I
                //   373: invokevirtual 185	android/os/Handler:sendEmptyMessage	(I)Z
                //   376: pop
                //   377: goto +34 -> 411
                //   380: aload_2
                //   381: astore_3
                //   382: aload_0
                //   383: getfield 42	com/qx/qgbox/utils/HttpUtil$7:val$handler	Landroid/os/Handler;
                //   386: aload_0
                //   387: getfield 46	com/qx/qgbox/utils/HttpUtil$7:val$failWhat	I
                //   390: invokevirtual 185	android/os/Handler:sendEmptyMessage	(I)Z
                //   393: pop
                //   394: goto +17 -> 411
                //   397: aload_2
                //   398: astore_3
                //   399: aload_0
                //   400: getfield 42	com/qx/qgbox/utils/HttpUtil$7:val$handler	Landroid/os/Handler;
                //   403: aload_0
                //   404: getfield 46	com/qx/qgbox/utils/HttpUtil$7:val$failWhat	I
                //   407: invokevirtual 185	android/os/Handler:sendEmptyMessage	(I)Z
                //   410: pop
                //   411: aload_2
                //   412: ifnull +50 -> 462
                //   415: goto +43 -> 458
                //   418: astore 4
                //   420: goto +13 -> 433
                //   423: astore_2
                //   424: aconst_null
                //   425: astore_3
                //   426: goto +38 -> 464
                //   429: astore 4
                //   431: aconst_null
                //   432: astore_2
                //   433: aload_2
                //   434: astore_3
                //   435: aload 4
                //   437: invokevirtual 188	java/lang/Exception:printStackTrace	()V
                //   440: aload_2
                //   441: astore_3
                //   442: aload_0
                //   443: getfield 42	com/qx/qgbox/utils/HttpUtil$7:val$handler	Landroid/os/Handler;
                //   446: aload_0
                //   447: getfield 46	com/qx/qgbox/utils/HttpUtil$7:val$failWhat	I
                //   450: invokevirtual 185	android/os/Handler:sendEmptyMessage	(I)Z
                //   453: pop
                //   454: aload_2
                //   455: ifnull +7 -> 462
                //   458: aload_2
                //   459: invokevirtual 191	java/net/HttpURLConnection:disconnect	()V
                //   462: return
                //   463: astore_2
                //   464: aload_3
                //   465: ifnull +7 -> 472
                //   468: aload_3
                //   469: invokevirtual 191	java/net/HttpURLConnection:disconnect	()V
                //   472: aload_2
                //   473: athrow
                //
                // Exception table:
                //   from	to	target	type
                //   20	26	418	java/lang/Exception
                //   28	33	418	java/lang/Exception
                //   35	40	418	java/lang/Exception
                //   42	47	418	java/lang/Exception
                //   49	57	418	java/lang/Exception
                //   59	67	418	java/lang/Exception
                //   69	77	418	java/lang/Exception
                //   79	86	418	java/lang/Exception
                //   88	95	418	java/lang/Exception
                //   97	106	418	java/lang/Exception
                //   108	116	418	java/lang/Exception
                //   118	128	418	java/lang/Exception
                //   130	138	418	java/lang/Exception
                //   140	150	418	java/lang/Exception
                //   152	160	418	java/lang/Exception
                //   162	172	418	java/lang/Exception
                //   174	182	418	java/lang/Exception
                //   184	194	418	java/lang/Exception
                //   196	204	418	java/lang/Exception
                //   206	216	418	java/lang/Exception
                //   218	226	418	java/lang/Exception
                //   228	238	418	java/lang/Exception
                //   240	247	418	java/lang/Exception
                //   249	255	418	java/lang/Exception
                //   257	267	418	java/lang/Exception
                //   269	274	418	java/lang/Exception
                //   276	281	418	java/lang/Exception
                //   283	293	418	java/lang/Exception
                //   295	301	418	java/lang/Exception
                //   303	312	418	java/lang/Exception
                //   314	320	418	java/lang/Exception
                //   327	335	418	java/lang/Exception
                //   340	363	418	java/lang/Exception
                //   365	377	418	java/lang/Exception
                //   382	394	418	java/lang/Exception
                //   399	411	418	java/lang/Exception
                //   0	18	423	finally
                //   0	18	429	java/lang/Exception
                //   20	26	463	finally
                //   28	33	463	finally
                //   35	40	463	finally
                //   42	47	463	finally
                //   49	57	463	finally
                //   59	67	463	finally
                //   69	77	463	finally
                //   79	86	463	finally
                //   88	95	463	finally
                //   97	106	463	finally
                //   108	116	463	finally
                //   118	128	463	finally
                //   130	138	463	finally
                //   140	150	463	finally
                //   152	160	463	finally
                //   162	172	463	finally
                //   174	182	463	finally
                //   184	194	463	finally
                //   196	204	463	finally
                //   206	216	463	finally
                //   218	226	463	finally
                //   228	238	463	finally
                //   240	247	463	finally
                //   249	255	463	finally
                //   257	267	463	finally
                //   269	274	463	finally
                //   276	281	463	finally
                //   283	293	463	finally
                //   295	301	463	finally
                //   303	312	463	finally
                //   314	320	463	finally
                //   327	335	463	finally
                //   340	363	463	finally
                //   365	377	463	finally
                //   382	394	463	finally
                //   399	411	463	finally
                //   435	440	463	finally
                //   442	454	463	finally
            }
        }).start();
    }

    public static void addOfficialPresetPost(final Handler paramHandler, final int paramInt1, final int paramInt2, String paramString1, final int paramInt3, final int paramInt4, final String paramString2, final String paramString3, final String paramString4, final int paramInt5, final String paramString5) {
        new Thread(new Runnable() {
            // ERROR //
            public void run() {
                // Byte code:
                //   0: new 58	java/net/URL
                //   3: dup
                //   4: aload_0
                //   5: getfield 29	com/qx/qgbox/utils/HttpUtil$4:val$postUrl	Ljava/lang/String;
                //   8: invokespecial 61	java/net/URL:<init>	(Ljava/lang/String;)V
                //   11: invokevirtual 65	java/net/URL:openConnection	()Ljava/net/URLConnection;
                //   14: checkcast 67	java/net/HttpURLConnection
                //   17: astore_2
                //   18: aload_2
                //   19: astore_3
                //   20: aload_2
                //   21: ldc 69
                //   23: invokevirtual 72	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
                //   26: aload_2
                //   27: astore_3
                //   28: aload_2
                //   29: iconst_1
                //   30: invokevirtual 76	java/net/HttpURLConnection:setDoOutput	(Z)V
                //   33: aload_2
                //   34: astore_3
                //   35: aload_2
                //   36: iconst_1
                //   37: invokevirtual 79	java/net/HttpURLConnection:setDoInput	(Z)V
                //   40: aload_2
                //   41: astore_3
                //   42: aload_2
                //   43: iconst_0
                //   44: invokevirtual 82	java/net/HttpURLConnection:setUseCaches	(Z)V
                //   47: aload_2
                //   48: astore_3
                //   49: aload_2
                //   50: ldc 84
                //   52: ldc 86
                //   54: invokevirtual 90	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   57: aload_2
                //   58: astore_3
                //   59: aload_2
                //   60: ldc 92
                //   62: ldc 94
                //   64: invokevirtual 90	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   67: aload_2
                //   68: astore_3
                //   69: aload_2
                //   70: ldc 96
                //   72: ldc 98
                //   74: invokevirtual 90	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   77: aload_2
                //   78: astore_3
                //   79: aload_2
                //   80: sipush 30000
                //   83: invokevirtual 102	java/net/HttpURLConnection:setConnectTimeout	(I)V
                //   86: aload_2
                //   87: astore_3
                //   88: aload_2
                //   89: sipush 30000
                //   92: invokevirtual 105	java/net/HttpURLConnection:setReadTimeout	(I)V
                //   95: aload_2
                //   96: astore_3
                //   97: new 107	java/lang/StringBuilder
                //   100: dup
                //   101: invokespecial 108	java/lang/StringBuilder:<init>	()V
                //   104: astore 4
                //   106: aload_2
                //   107: astore_3
                //   108: aload 4
                //   110: ldc 110
                //   112: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   115: pop
                //   116: aload_2
                //   117: astore_3
                //   118: aload 4
                //   120: aload_0
                //   121: getfield 31	com/qx/qgbox/utils/HttpUtil$4:val$platform	I
                //   124: invokevirtual 117	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   127: pop
                //   128: aload_2
                //   129: astore_3
                //   130: aload 4
                //   132: ldc 119
                //   134: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   137: pop
                //   138: aload_2
                //   139: astore_3
                //   140: aload 4
                //   142: aload_0
                //   143: getfield 33	com/qx/qgbox/utils/HttpUtil$4:val$type	I
                //   146: invokevirtual 117	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   149: pop
                //   150: aload_2
                //   151: astore_3
                //   152: aload 4
                //   154: ldc 121
                //   156: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   159: pop
                //   160: aload_2
                //   161: astore_3
                //   162: aload 4
                //   164: aload_0
                //   165: getfield 35	com/qx/qgbox/utils/HttpUtil$4:val$model	Ljava/lang/String;
                //   168: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   171: pop
                //   172: aload_2
                //   173: astore_3
                //   174: aload 4
                //   176: ldc 123
                //   178: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   181: pop
                //   182: aload_2
                //   183: astore_3
                //   184: aload 4
                //   186: aload_0
                //   187: getfield 37	com/qx/qgbox/utils/HttpUtil$4:val$location	Ljava/lang/String;
                //   190: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   193: pop
                //   194: aload_2
                //   195: astore_3
                //   196: aload 4
                //   198: ldc 125
                //   200: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   203: pop
                //   204: aload_2
                //   205: astore_3
                //   206: aload 4
                //   208: aload_0
                //   209: getfield 39	com/qx/qgbox/utils/HttpUtil$4:val$resolution	Ljava/lang/String;
                //   212: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   215: pop
                //   216: aload_2
                //   217: astore_3
                //   218: aload 4
                //   220: ldc 127
                //   222: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   225: pop
                //   226: aload_2
                //   227: astore_3
                //   228: aload 4
                //   230: aload_0
                //   231: getfield 41	com/qx/qgbox/utils/HttpUtil$4:val$gameId	I
                //   234: invokevirtual 117	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   237: pop
                //   238: aload_2
                //   239: astore_3
                //   240: aload 4
                //   242: ldc 129
                //   244: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   247: pop
                //   248: aload_2
                //   249: astore_3
                //   250: aload 4
                //   252: aload_0
                //   253: getfield 43	com/qx/qgbox/utils/HttpUtil$4:val$fileUrl	Ljava/lang/String;
                //   256: invokevirtual 114	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   259: pop
                //   260: aload_2
                //   261: astore_3
                //   262: aload 4
                //   264: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   267: astore 4
                //   269: aload_2
                //   270: astore_3
                //   271: aload_2
                //   272: invokevirtual 137	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
                //   275: astore 5
                //   277: aload_2
                //   278: astore_3
                //   279: aload 5
                //   281: aload 4
                //   283: invokevirtual 143	java/lang/String:getBytes	()[B
                //   286: invokevirtual 149	java/io/OutputStream:write	([B)V
                //   289: aload_2
                //   290: astore_3
                //   291: aload 5
                //   293: invokevirtual 152	java/io/OutputStream:flush	()V
                //   296: aload_2
                //   297: astore_3
                //   298: aload 5
                //   300: invokevirtual 155	java/io/OutputStream:close	()V
                //   303: aload_2
                //   304: astore_3
                //   305: aload_2
                //   306: invokevirtual 159	java/net/HttpURLConnection:getResponseCode	()I
                //   309: sipush 200
                //   312: if_icmpne +107 -> 419
                //   315: aload_2
                //   316: astore_3
                //   317: aload_2
                //   318: invokevirtual 163	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
                //   321: astore 4
                //   323: aload_2
                //   324: astore_3
                //   325: new 165	java/lang/StringBuffer
                //   328: dup
                //   329: invokespecial 166	java/lang/StringBuffer:<init>	()V
                //   332: astore 5
                //   334: aload_2
                //   335: astore_3
                //   336: aload 4
                //   338: invokevirtual 171	java/io/InputStream:read	()I
                //   341: istore_1
                //   342: iload_1
                //   343: iconst_m1
                //   344: if_icmpeq +16 -> 360
                //   347: aload_2
                //   348: astore_3
                //   349: aload 5
                //   351: iload_1
                //   352: i2c
                //   353: invokevirtual 174	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
                //   356: pop
                //   357: goto -23 -> 334
                //   360: aload_2
                //   361: astore_3
                //   362: new 176	org/json/JSONObject
                //   365: dup
                //   366: aload 5
                //   368: invokevirtual 177	java/lang/StringBuffer:toString	()Ljava/lang/String;
                //   371: invokespecial 178	org/json/JSONObject:<init>	(Ljava/lang/String;)V
                //   374: ldc 180
                //   376: invokevirtual 184	org/json/JSONObject:getInt	(Ljava/lang/String;)I
                //   379: sipush 1000
                //   382: if_icmpne +20 -> 402
                //   385: aload_2
                //   386: astore_3
                //   387: aload_0
                //   388: getfield 45	com/qx/qgbox/utils/HttpUtil$4:val$handler	Landroid/os/Handler;
                //   391: aload_0
                //   392: getfield 47	com/qx/qgbox/utils/HttpUtil$4:val$successWhat	I
                //   395: invokevirtual 190	android/os/Handler:sendEmptyMessage	(I)Z
                //   398: pop
                //   399: goto +34 -> 433
                //   402: aload_2
                //   403: astore_3
                //   404: aload_0
                //   405: getfield 45	com/qx/qgbox/utils/HttpUtil$4:val$handler	Landroid/os/Handler;
                //   408: aload_0
                //   409: getfield 49	com/qx/qgbox/utils/HttpUtil$4:val$failWhat	I
                //   412: invokevirtual 190	android/os/Handler:sendEmptyMessage	(I)Z
                //   415: pop
                //   416: goto +17 -> 433
                //   419: aload_2
                //   420: astore_3
                //   421: aload_0
                //   422: getfield 45	com/qx/qgbox/utils/HttpUtil$4:val$handler	Landroid/os/Handler;
                //   425: aload_0
                //   426: getfield 49	com/qx/qgbox/utils/HttpUtil$4:val$failWhat	I
                //   429: invokevirtual 190	android/os/Handler:sendEmptyMessage	(I)Z
                //   432: pop
                //   433: aload_2
                //   434: ifnull +50 -> 484
                //   437: goto +43 -> 480
                //   440: astore 4
                //   442: goto +13 -> 455
                //   445: astore_2
                //   446: aconst_null
                //   447: astore_3
                //   448: goto +38 -> 486
                //   451: astore 4
                //   453: aconst_null
                //   454: astore_2
                //   455: aload_2
                //   456: astore_3
                //   457: aload 4
                //   459: invokevirtual 193	java/lang/Exception:printStackTrace	()V
                //   462: aload_2
                //   463: astore_3
                //   464: aload_0
                //   465: getfield 45	com/qx/qgbox/utils/HttpUtil$4:val$handler	Landroid/os/Handler;
                //   468: aload_0
                //   469: getfield 49	com/qx/qgbox/utils/HttpUtil$4:val$failWhat	I
                //   472: invokevirtual 190	android/os/Handler:sendEmptyMessage	(I)Z
                //   475: pop
                //   476: aload_2
                //   477: ifnull +7 -> 484
                //   480: aload_2
                //   481: invokevirtual 196	java/net/HttpURLConnection:disconnect	()V
                //   484: return
                //   485: astore_2
                //   486: aload_3
                //   487: ifnull +7 -> 494
                //   490: aload_3
                //   491: invokevirtual 196	java/net/HttpURLConnection:disconnect	()V
                //   494: aload_2
                //   495: athrow
                //
                // Exception table:
                //   from	to	target	type
                //   20	26	440	java/lang/Exception
                //   28	33	440	java/lang/Exception
                //   35	40	440	java/lang/Exception
                //   42	47	440	java/lang/Exception
                //   49	57	440	java/lang/Exception
                //   59	67	440	java/lang/Exception
                //   69	77	440	java/lang/Exception
                //   79	86	440	java/lang/Exception
                //   88	95	440	java/lang/Exception
                //   97	106	440	java/lang/Exception
                //   108	116	440	java/lang/Exception
                //   118	128	440	java/lang/Exception
                //   130	138	440	java/lang/Exception
                //   140	150	440	java/lang/Exception
                //   152	160	440	java/lang/Exception
                //   162	172	440	java/lang/Exception
                //   174	182	440	java/lang/Exception
                //   184	194	440	java/lang/Exception
                //   196	204	440	java/lang/Exception
                //   206	216	440	java/lang/Exception
                //   218	226	440	java/lang/Exception
                //   228	238	440	java/lang/Exception
                //   240	248	440	java/lang/Exception
                //   250	260	440	java/lang/Exception
                //   262	269	440	java/lang/Exception
                //   271	277	440	java/lang/Exception
                //   279	289	440	java/lang/Exception
                //   291	296	440	java/lang/Exception
                //   298	303	440	java/lang/Exception
                //   305	315	440	java/lang/Exception
                //   317	323	440	java/lang/Exception
                //   325	334	440	java/lang/Exception
                //   336	342	440	java/lang/Exception
                //   349	357	440	java/lang/Exception
                //   362	385	440	java/lang/Exception
                //   387	399	440	java/lang/Exception
                //   404	416	440	java/lang/Exception
                //   421	433	440	java/lang/Exception
                //   0	18	445	finally
                //   0	18	451	java/lang/Exception
                //   20	26	485	finally
                //   28	33	485	finally
                //   35	40	485	finally
                //   42	47	485	finally
                //   49	57	485	finally
                //   59	67	485	finally
                //   69	77	485	finally
                //   79	86	485	finally
                //   88	95	485	finally
                //   97	106	485	finally
                //   108	116	485	finally
                //   118	128	485	finally
                //   130	138	485	finally
                //   140	150	485	finally
                //   152	160	485	finally
                //   162	172	485	finally
                //   174	182	485	finally
                //   184	194	485	finally
                //   196	204	485	finally
                //   206	216	485	finally
                //   218	226	485	finally
                //   228	238	485	finally
                //   240	248	485	finally
                //   250	260	485	finally
                //   262	269	485	finally
                //   271	277	485	finally
                //   279	289	485	finally
                //   291	296	485	finally
                //   298	303	485	finally
                //   305	315	485	finally
                //   317	323	485	finally
                //   325	334	485	finally
                //   336	342	485	finally
                //   349	357	485	finally
                //   362	385	485	finally
                //   387	399	485	finally
                //   404	416	485	finally
                //   421	433	485	finally
                //   457	462	485	finally
                //   464	476	485	finally
            }
        }).start();
    }

    public static void addOfficialPresetPost(final Handler paramHandler, final int paramInt1, final int paramInt2, String paramString1, final int paramInt3, final int paramInt4, final String paramString2, final String paramString3, final String paramString4, final int paramInt5, final String paramString5, final String paramString6) {
        new Thread(new Runnable() {
            // ERROR //
            public void run() {
                // Byte code:
                //   0: new 61	java/net/URL
                //   3: dup
                //   4: aload_0
                //   5: getfield 30	com/qx/qgbox/utils/HttpUtil$5:val$postUrl	Ljava/lang/String;
                //   8: invokespecial 64	java/net/URL:<init>	(Ljava/lang/String;)V
                //   11: invokevirtual 68	java/net/URL:openConnection	()Ljava/net/URLConnection;
                //   14: checkcast 70	java/net/HttpURLConnection
                //   17: astore_2
                //   18: aload_2
                //   19: astore_3
                //   20: aload_2
                //   21: ldc 72
                //   23: invokevirtual 75	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
                //   26: aload_2
                //   27: astore_3
                //   28: aload_2
                //   29: iconst_1
                //   30: invokevirtual 79	java/net/HttpURLConnection:setDoOutput	(Z)V
                //   33: aload_2
                //   34: astore_3
                //   35: aload_2
                //   36: iconst_1
                //   37: invokevirtual 82	java/net/HttpURLConnection:setDoInput	(Z)V
                //   40: aload_2
                //   41: astore_3
                //   42: aload_2
                //   43: iconst_0
                //   44: invokevirtual 85	java/net/HttpURLConnection:setUseCaches	(Z)V
                //   47: aload_2
                //   48: astore_3
                //   49: aload_2
                //   50: ldc 87
                //   52: ldc 89
                //   54: invokevirtual 93	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   57: aload_2
                //   58: astore_3
                //   59: aload_2
                //   60: ldc 95
                //   62: ldc 97
                //   64: invokevirtual 93	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   67: aload_2
                //   68: astore_3
                //   69: aload_2
                //   70: ldc 99
                //   72: ldc 101
                //   74: invokevirtual 93	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   77: aload_2
                //   78: astore_3
                //   79: aload_2
                //   80: sipush 30000
                //   83: invokevirtual 105	java/net/HttpURLConnection:setConnectTimeout	(I)V
                //   86: aload_2
                //   87: astore_3
                //   88: aload_2
                //   89: sipush 30000
                //   92: invokevirtual 108	java/net/HttpURLConnection:setReadTimeout	(I)V
                //   95: aload_2
                //   96: astore_3
                //   97: new 110	java/lang/StringBuilder
                //   100: dup
                //   101: invokespecial 111	java/lang/StringBuilder:<init>	()V
                //   104: astore 4
                //   106: aload_2
                //   107: astore_3
                //   108: aload 4
                //   110: ldc 113
                //   112: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   115: pop
                //   116: aload_2
                //   117: astore_3
                //   118: aload 4
                //   120: aload_0
                //   121: getfield 32	com/qx/qgbox/utils/HttpUtil$5:val$platform	I
                //   124: invokevirtual 120	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   127: pop
                //   128: aload_2
                //   129: astore_3
                //   130: aload 4
                //   132: ldc 122
                //   134: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   137: pop
                //   138: aload_2
                //   139: astore_3
                //   140: aload 4
                //   142: aload_0
                //   143: getfield 34	com/qx/qgbox/utils/HttpUtil$5:val$type	I
                //   146: invokevirtual 120	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   149: pop
                //   150: aload_2
                //   151: astore_3
                //   152: aload 4
                //   154: ldc 124
                //   156: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   159: pop
                //   160: aload_2
                //   161: astore_3
                //   162: aload 4
                //   164: aload_0
                //   165: getfield 36	com/qx/qgbox/utils/HttpUtil$5:val$model	Ljava/lang/String;
                //   168: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   171: pop
                //   172: aload_2
                //   173: astore_3
                //   174: aload 4
                //   176: ldc 126
                //   178: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   181: pop
                //   182: aload_2
                //   183: astore_3
                //   184: aload 4
                //   186: aload_0
                //   187: getfield 38	com/qx/qgbox/utils/HttpUtil$5:val$location	Ljava/lang/String;
                //   190: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   193: pop
                //   194: aload_2
                //   195: astore_3
                //   196: aload 4
                //   198: ldc 128
                //   200: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   203: pop
                //   204: aload_2
                //   205: astore_3
                //   206: aload 4
                //   208: aload_0
                //   209: getfield 40	com/qx/qgbox/utils/HttpUtil$5:val$resolution	Ljava/lang/String;
                //   212: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   215: pop
                //   216: aload_2
                //   217: astore_3
                //   218: aload 4
                //   220: ldc 130
                //   222: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   225: pop
                //   226: aload_2
                //   227: astore_3
                //   228: aload 4
                //   230: aload_0
                //   231: getfield 42	com/qx/qgbox/utils/HttpUtil$5:val$gameId	I
                //   234: invokevirtual 120	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
                //   237: pop
                //   238: aload_2
                //   239: astore_3
                //   240: aload 4
                //   242: ldc 132
                //   244: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   247: pop
                //   248: aload_2
                //   249: astore_3
                //   250: aload 4
                //   252: aload_0
                //   253: getfield 44	com/qx/qgbox/utils/HttpUtil$5:val$fileUrl	Ljava/lang/String;
                //   256: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   259: pop
                //   260: aload_2
                //   261: astore_3
                //   262: aload 4
                //   264: ldc 134
                //   266: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   269: pop
                //   270: aload_2
                //   271: astore_3
                //   272: aload 4
                //   274: aload_0
                //   275: getfield 46	com/qx/qgbox/utils/HttpUtil$5:val$txtfileUrl	Ljava/lang/String;
                //   278: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   281: pop
                //   282: aload_2
                //   283: astore_3
                //   284: aload 4
                //   286: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   289: astore 4
                //   291: aload_2
                //   292: astore_3
                //   293: aload_2
                //   294: invokevirtual 142	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
                //   297: astore 5
                //   299: aload_2
                //   300: astore_3
                //   301: aload 5
                //   303: aload 4
                //   305: invokevirtual 148	java/lang/String:getBytes	()[B
                //   308: invokevirtual 154	java/io/OutputStream:write	([B)V
                //   311: aload_2
                //   312: astore_3
                //   313: aload 5
                //   315: invokevirtual 157	java/io/OutputStream:flush	()V
                //   318: aload_2
                //   319: astore_3
                //   320: aload 5
                //   322: invokevirtual 160	java/io/OutputStream:close	()V
                //   325: aload_2
                //   326: astore_3
                //   327: aload_2
                //   328: invokevirtual 164	java/net/HttpURLConnection:getResponseCode	()I
                //   331: sipush 200
                //   334: if_icmpne +153 -> 487
                //   337: aload_2
                //   338: astore_3
                //   339: aload_2
                //   340: invokevirtual 168	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
                //   343: astore 5
                //   345: aload_2
                //   346: astore_3
                //   347: new 170	java/lang/StringBuffer
                //   350: dup
                //   351: invokespecial 171	java/lang/StringBuffer:<init>	()V
                //   354: astore 4
                //   356: aload_2
                //   357: astore_3
                //   358: aload 5
                //   360: invokevirtual 176	java/io/InputStream:read	()I
                //   363: istore_1
                //   364: iload_1
                //   365: iconst_m1
                //   366: if_icmpeq +16 -> 382
                //   369: aload_2
                //   370: astore_3
                //   371: aload 4
                //   373: iload_1
                //   374: i2c
                //   375: invokevirtual 179	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
                //   378: pop
                //   379: goto -23 -> 356
                //   382: aload_2
                //   383: astore_3
                //   384: new 181	org/json/JSONObject
                //   387: dup
                //   388: aload 4
                //   390: invokevirtual 182	java/lang/StringBuffer:toString	()Ljava/lang/String;
                //   393: invokespecial 183	org/json/JSONObject:<init>	(Ljava/lang/String;)V
                //   396: ldc 185
                //   398: invokevirtual 189	org/json/JSONObject:getInt	(Ljava/lang/String;)I
                //   401: sipush 1000
                //   404: if_icmpne +66 -> 470
                //   407: aload_2
                //   408: astore_3
                //   409: new 110	java/lang/StringBuilder
                //   412: dup
                //   413: invokespecial 111	java/lang/StringBuilder:<init>	()V
                //   416: astore 5
                //   418: aload_2
                //   419: astore_3
                //   420: aload 5
                //   422: ldc 191
                //   424: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   427: pop
                //   428: aload_2
                //   429: astore_3
                //   430: aload 5
                //   432: aload 4
                //   434: invokevirtual 182	java/lang/StringBuffer:toString	()Ljava/lang/String;
                //   437: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   440: pop
                //   441: aload_2
                //   442: astore_3
                //   443: ldc 193
                //   445: aload 5
                //   447: invokevirtual 138	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   450: invokestatic 198	com/qx/qgbox/utils/MyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
                //   453: aload_2
                //   454: astore_3
                //   455: aload_0
                //   456: getfield 48	com/qx/qgbox/utils/HttpUtil$5:val$handler	Landroid/os/Handler;
                //   459: aload_0
                //   460: getfield 50	com/qx/qgbox/utils/HttpUtil$5:val$successWhat	I
                //   463: invokevirtual 204	android/os/Handler:sendEmptyMessage	(I)Z
                //   466: pop
                //   467: goto +34 -> 501
                //   470: aload_2
                //   471: astore_3
                //   472: aload_0
                //   473: getfield 48	com/qx/qgbox/utils/HttpUtil$5:val$handler	Landroid/os/Handler;
                //   476: aload_0
                //   477: getfield 52	com/qx/qgbox/utils/HttpUtil$5:val$failWhat	I
                //   480: invokevirtual 204	android/os/Handler:sendEmptyMessage	(I)Z
                //   483: pop
                //   484: goto +17 -> 501
                //   487: aload_2
                //   488: astore_3
                //   489: aload_0
                //   490: getfield 48	com/qx/qgbox/utils/HttpUtil$5:val$handler	Landroid/os/Handler;
                //   493: aload_0
                //   494: getfield 52	com/qx/qgbox/utils/HttpUtil$5:val$failWhat	I
                //   497: invokevirtual 204	android/os/Handler:sendEmptyMessage	(I)Z
                //   500: pop
                //   501: aload_2
                //   502: ifnull +50 -> 552
                //   505: goto +43 -> 548
                //   508: astore 4
                //   510: goto +13 -> 523
                //   513: astore_2
                //   514: aconst_null
                //   515: astore_3
                //   516: goto +38 -> 554
                //   519: astore 4
                //   521: aconst_null
                //   522: astore_2
                //   523: aload_2
                //   524: astore_3
                //   525: aload 4
                //   527: invokevirtual 207	java/lang/Exception:printStackTrace	()V
                //   530: aload_2
                //   531: astore_3
                //   532: aload_0
                //   533: getfield 48	com/qx/qgbox/utils/HttpUtil$5:val$handler	Landroid/os/Handler;
                //   536: aload_0
                //   537: getfield 52	com/qx/qgbox/utils/HttpUtil$5:val$failWhat	I
                //   540: invokevirtual 204	android/os/Handler:sendEmptyMessage	(I)Z
                //   543: pop
                //   544: aload_2
                //   545: ifnull +7 -> 552
                //   548: aload_2
                //   549: invokevirtual 210	java/net/HttpURLConnection:disconnect	()V
                //   552: return
                //   553: astore_2
                //   554: aload_3
                //   555: ifnull +7 -> 562
                //   558: aload_3
                //   559: invokevirtual 210	java/net/HttpURLConnection:disconnect	()V
                //   562: aload_2
                //   563: athrow
                //
                // Exception table:
                //   from	to	target	type
                //   20	26	508	java/lang/Exception
                //   28	33	508	java/lang/Exception
                //   35	40	508	java/lang/Exception
                //   42	47	508	java/lang/Exception
                //   49	57	508	java/lang/Exception
                //   59	67	508	java/lang/Exception
                //   69	77	508	java/lang/Exception
                //   79	86	508	java/lang/Exception
                //   88	95	508	java/lang/Exception
                //   97	106	508	java/lang/Exception
                //   108	116	508	java/lang/Exception
                //   118	128	508	java/lang/Exception
                //   130	138	508	java/lang/Exception
                //   140	150	508	java/lang/Exception
                //   152	160	508	java/lang/Exception
                //   162	172	508	java/lang/Exception
                //   174	182	508	java/lang/Exception
                //   184	194	508	java/lang/Exception
                //   196	204	508	java/lang/Exception
                //   206	216	508	java/lang/Exception
                //   218	226	508	java/lang/Exception
                //   228	238	508	java/lang/Exception
                //   240	248	508	java/lang/Exception
                //   250	260	508	java/lang/Exception
                //   262	270	508	java/lang/Exception
                //   272	282	508	java/lang/Exception
                //   284	291	508	java/lang/Exception
                //   293	299	508	java/lang/Exception
                //   301	311	508	java/lang/Exception
                //   313	318	508	java/lang/Exception
                //   320	325	508	java/lang/Exception
                //   327	337	508	java/lang/Exception
                //   339	345	508	java/lang/Exception
                //   347	356	508	java/lang/Exception
                //   358	364	508	java/lang/Exception
                //   371	379	508	java/lang/Exception
                //   384	407	508	java/lang/Exception
                //   409	418	508	java/lang/Exception
                //   420	428	508	java/lang/Exception
                //   430	441	508	java/lang/Exception
                //   443	453	508	java/lang/Exception
                //   455	467	508	java/lang/Exception
                //   472	484	508	java/lang/Exception
                //   489	501	508	java/lang/Exception
                //   0	18	513	finally
                //   0	18	519	java/lang/Exception
                //   20	26	553	finally
                //   28	33	553	finally
                //   35	40	553	finally
                //   42	47	553	finally
                //   49	57	553	finally
                //   59	67	553	finally
                //   69	77	553	finally
                //   79	86	553	finally
                //   88	95	553	finally
                //   97	106	553	finally
                //   108	116	553	finally
                //   118	128	553	finally
                //   130	138	553	finally
                //   140	150	553	finally
                //   152	160	553	finally
                //   162	172	553	finally
                //   174	182	553	finally
                //   184	194	553	finally
                //   196	204	553	finally
                //   206	216	553	finally
                //   218	226	553	finally
                //   228	238	553	finally
                //   240	248	553	finally
                //   250	260	553	finally
                //   262	270	553	finally
                //   272	282	553	finally
                //   284	291	553	finally
                //   293	299	553	finally
                //   301	311	553	finally
                //   313	318	553	finally
                //   320	325	553	finally
                //   327	337	553	finally
                //   339	345	553	finally
                //   347	356	553	finally
                //   358	364	553	finally
                //   371	379	553	finally
                //   384	407	553	finally
                //   409	418	553	finally
                //   420	428	553	finally
                //   430	441	553	finally
                //   443	453	553	finally
                //   455	467	553	finally
                //   472	484	553	finally
                //   489	501	553	finally
                //   525	530	553	finally
                //   532	544	553	finally
            }
        }).start();
    }

    public static String changeSpecialCharacter(String paramString) {
        paramString = paramString.replace(" ", "%20").replace("[", "%5B").replace("]", "%5D").replace("{", "%7B").replace("}", "%7D").replace("^", "%5E").replace("`", "%60").replace("#", "%23").replace("&", "%26").replace("=", "%3D").replace("+", "%2B").replace("\"", "%22").replace("|", "%7C").replace("<", "%3C").replace(">", "%3E").replace("?", "%3F");
        PrintStream localPrintStream = System.out;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("最后的路径=");
        localStringBuilder.append(paramString);
        localPrintStream.println(localStringBuilder.toString());
        return paramString;
    }

    public static void downloadFile(final Handler paramHandler, final int paramInt1, final int paramInt2, final String file, final String path) {
        new Thread() {
            public void run() {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("down load File Path--->");
                    stringBuilder.append(path);
                    MyLog.i("my_tag", stringBuilder.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(path).openConnection();
                    httpURLConnection.setConnectTimeout(4000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Charset", "utf-8");
                    httpURLConnection.connect();
                    httpURLConnection.getURL().getFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream localInputStream = httpURLConnection.getInputStream();
                        httpURLConnection.getContentLength();
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(localInputStream);
                        byte[] arrayOfByte = new byte[1024];
                        while (true) {
                            int i = bufferedInputStream.read(arrayOfByte);
                            if (i == -1)
                                break;
                            fileOutputStream.write(arrayOfByte, 0, i);
                        }
                        fileOutputStream.close();
                        localInputStream.close();
                        bufferedInputStream.close();
                        paramHandler.sendEmptyMessage(paramInt1);
                        return;
                    }
                    paramHandler.sendEmptyMessage(paramInt2);
                    return;

                } catch (Exception localException) {
                    localException.printStackTrace();
                }
            }
        }
                .start();
    }

    public static void uploadLogFile(final Handler paramHandler, final int paramInt1, final int paramInt2, String paramString1, final String paramString2, final String paramString3) {
        new Thread(new Runnable() {
            // ERROR //
            public void run() {
                // Byte code:
                //   0: aconst_null
                //   1: astore 6
                //   3: aconst_null
                //   4: astore 5
                //   6: new 45	java/net/URL
                //   9: dup
                //   10: aload_0
                //   11: getfield 24	com/qx/qgbox/utils/HttpUtil$8:val$urlstr	Ljava/lang/String;
                //   14: invokespecial 48	java/net/URL:<init>	(Ljava/lang/String;)V
                //   17: invokevirtual 52	java/net/URL:openConnection	()Ljava/net/URLConnection;
                //   20: checkcast 54	java/net/HttpURLConnection
                //   23: astore_2
                //   24: aload_2
                //   25: sipush 10000
                //   28: invokevirtual 58	java/net/HttpURLConnection:setReadTimeout	(I)V
                //   31: aload_2
                //   32: sipush 10000
                //   35: invokevirtual 61	java/net/HttpURLConnection:setConnectTimeout	(I)V
                //   38: aload_2
                //   39: iconst_1
                //   40: invokevirtual 65	java/net/HttpURLConnection:setDoInput	(Z)V
                //   43: aload_2
                //   44: iconst_1
                //   45: invokevirtual 68	java/net/HttpURLConnection:setDoOutput	(Z)V
                //   48: aload_2
                //   49: iconst_0
                //   50: invokevirtual 71	java/net/HttpURLConnection:setUseCaches	(Z)V
                //   53: aload_2
                //   54: ldc 73
                //   56: invokevirtual 76	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
                //   59: aload_2
                //   60: ldc 78
                //   62: ldc 80
                //   64: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   67: aload_2
                //   68: ldc 86
                //   70: ldc 88
                //   72: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   75: new 90	java/lang/StringBuilder
                //   78: dup
                //   79: invokespecial 91	java/lang/StringBuilder:<init>	()V
                //   82: astore_3
                //   83: aload_3
                //   84: ldc 93
                //   86: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   89: pop
                //   90: aload_3
                //   91: ldc 99
                //   93: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   96: pop
                //   97: aload_2
                //   98: ldc 101
                //   100: aload_3
                //   101: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   104: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
                //   107: new 107	java/io/DataOutputStream
                //   110: dup
                //   111: aload_2
                //   112: invokevirtual 111	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
                //   115: invokespecial 114	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
                //   118: astore 4
                //   120: new 90	java/lang/StringBuilder
                //   123: dup
                //   124: invokespecial 91	java/lang/StringBuilder:<init>	()V
                //   127: astore_3
                //   128: aload_3
                //   129: ldc 116
                //   131: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   134: pop
                //   135: aload_3
                //   136: ldc 99
                //   138: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   141: pop
                //   142: aload_3
                //   143: ldc 118
                //   145: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   148: pop
                //   149: aload 4
                //   151: aload_3
                //   152: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   155: invokevirtual 121	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
                //   158: new 90	java/lang/StringBuilder
                //   161: dup
                //   162: invokespecial 91	java/lang/StringBuilder:<init>	()V
                //   165: astore_3
                //   166: aload_3
                //   167: ldc 123
                //   169: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   172: pop
                //   173: aload_3
                //   174: aload_0
                //   175: getfield 26	com/qx/qgbox/utils/HttpUtil$8:val$newName	Ljava/lang/String;
                //   178: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   181: pop
                //   182: aload_3
                //   183: ldc 125
                //   185: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   188: pop
                //   189: aload_3
                //   190: ldc 118
                //   192: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   195: pop
                //   196: aload 4
                //   198: aload_3
                //   199: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   202: invokevirtual 121	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
                //   205: aload 4
                //   207: ldc 118
                //   209: invokevirtual 121	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
                //   212: new 127	java/io/FileInputStream
                //   215: dup
                //   216: aload_0
                //   217: getfield 28	com/qx/qgbox/utils/HttpUtil$8:val$uploadFile	Ljava/lang/String;
                //   220: invokespecial 128	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
                //   223: astore_3
                //   224: sipush 1024
                //   227: newarray byte
                //   229: astore 5
                //   231: aload_3
                //   232: aload 5
                //   234: invokevirtual 132	java/io/FileInputStream:read	([B)I
                //   237: istore_1
                //   238: iload_1
                //   239: iconst_m1
                //   240: if_icmpeq +15 -> 255
                //   243: aload 4
                //   245: aload 5
                //   247: iconst_0
                //   248: iload_1
                //   249: invokevirtual 136	java/io/DataOutputStream:write	([BII)V
                //   252: goto -21 -> 231
                //   255: aload 4
                //   257: ldc 118
                //   259: invokevirtual 121	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
                //   262: new 90	java/lang/StringBuilder
                //   265: dup
                //   266: invokespecial 91	java/lang/StringBuilder:<init>	()V
                //   269: astore 5
                //   271: aload 5
                //   273: ldc 116
                //   275: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   278: pop
                //   279: aload 5
                //   281: ldc 99
                //   283: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   286: pop
                //   287: aload 5
                //   289: ldc 116
                //   291: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   294: pop
                //   295: aload 5
                //   297: ldc 118
                //   299: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   302: pop
                //   303: aload 4
                //   305: aload 5
                //   307: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
                //   310: invokevirtual 121	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
                //   313: aload_3
                //   314: invokevirtual 139	java/io/FileInputStream:close	()V
                //   317: aload 4
                //   319: invokevirtual 142	java/io/DataOutputStream:flush	()V
                //   322: aload_2
                //   323: invokevirtual 146	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
                //   326: astore_3
                //   327: new 148	java/lang/StringBuffer
                //   330: dup
                //   331: invokespecial 149	java/lang/StringBuffer:<init>	()V
                //   334: astore 5
                //   336: aload_3
                //   337: invokevirtual 154	java/io/InputStream:read	()I
                //   340: istore_1
                //   341: iload_1
                //   342: iconst_m1
                //   343: if_icmpeq +14 -> 357
                //   346: aload 5
                //   348: iload_1
                //   349: i2c
                //   350: invokevirtual 157	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
                //   353: pop
                //   354: goto -18 -> 336
                //   357: aload_2
                //   358: invokevirtual 160	java/net/HttpURLConnection:getResponseCode	()I
                //   361: sipush 200
                //   364: if_icmpne +55 -> 419
                //   367: new 162	org/json/JSONObject
                //   370: dup
                //   371: aload 5
                //   373: invokevirtual 163	java/lang/StringBuffer:toString	()Ljava/lang/String;
                //   376: invokespecial 164	org/json/JSONObject:<init>	(Ljava/lang/String;)V
                //   379: astore 5
                //   381: new 166	android/os/Message
                //   384: dup
                //   385: invokespecial 167	android/os/Message:<init>	()V
                //   388: astore 6
                //   390: aload 6
                //   392: aload 5
                //   394: putfield 171	android/os/Message:obj	Ljava/lang/Object;
                //   397: aload 6
                //   399: aload_0
                //   400: getfield 30	com/qx/qgbox/utils/HttpUtil$8:val$successWhat	I
                //   403: putfield 174	android/os/Message:what	I
                //   406: aload_0
                //   407: getfield 32	com/qx/qgbox/utils/HttpUtil$8:val$handler	Landroid/os/Handler;
                //   410: aload 6
                //   412: invokevirtual 180	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
                //   415: pop
                //   416: goto +40 -> 456
                //   419: new 166	android/os/Message
                //   422: dup
                //   423: invokespecial 167	android/os/Message:<init>	()V
                //   426: astore 5
                //   428: aload 5
                //   430: aload_0
                //   431: getfield 28	com/qx/qgbox/utils/HttpUtil$8:val$uploadFile	Ljava/lang/String;
                //   434: putfield 171	android/os/Message:obj	Ljava/lang/Object;
                //   437: aload 5
                //   439: aload_0
                //   440: getfield 34	com/qx/qgbox/utils/HttpUtil$8:val$failWhat	I
                //   443: putfield 174	android/os/Message:what	I
                //   446: aload_0
                //   447: getfield 32	com/qx/qgbox/utils/HttpUtil$8:val$handler	Landroid/os/Handler;
                //   450: aload 5
                //   452: invokevirtual 180	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
                //   455: pop
                //   456: aload 4
                //   458: ifnull +18 -> 476
                //   461: aload 4
                //   463: invokevirtual 181	java/io/DataOutputStream:close	()V
                //   466: goto +10 -> 476
                //   469: astore 4
                //   471: aload 4
                //   473: invokevirtual 184	java/io/IOException:printStackTrace	()V
                //   476: aload_3
                //   477: ifnull +15 -> 492
                //   480: aload_3
                //   481: invokevirtual 185	java/io/InputStream:close	()V
                //   484: goto +8 -> 492
                //   487: astore_3
                //   488: aload_3
                //   489: invokevirtual 184	java/io/IOException:printStackTrace	()V
                //   492: aload_2
                //   493: ifnull +150 -> 643
                //   496: goto +143 -> 639
                //   499: astore 6
                //   501: aload_2
                //   502: astore 5
                //   504: goto +145 -> 649
                //   507: astore_3
                //   508: aload_2
                //   509: astore 5
                //   511: aload 4
                //   513: astore_2
                //   514: goto +148 -> 662
                //   517: aconst_null
                //   518: astore_3
                //   519: goto +43 -> 562
                //   522: astore_3
                //   523: aconst_null
                //   524: astore 4
                //   526: aload_2
                //   527: astore 5
                //   529: aload 4
                //   531: astore_2
                //   532: goto +130 -> 662
                //   535: aconst_null
                //   536: astore_3
                //   537: aload 5
                //   539: astore 4
                //   541: goto +21 -> 562
                //   544: astore_3
                //   545: aconst_null
                //   546: astore 5
                //   548: aload 5
                //   550: astore_2
                //   551: goto +111 -> 662
                //   554: aconst_null
                //   555: astore_3
                //   556: aload_3
                //   557: astore_2
                //   558: aload 5
                //   560: astore 4
                //   562: new 166	android/os/Message
                //   565: dup
                //   566: invokespecial 167	android/os/Message:<init>	()V
                //   569: astore 5
                //   571: aload 5
                //   573: aload_0
                //   574: getfield 28	com/qx/qgbox/utils/HttpUtil$8:val$uploadFile	Ljava/lang/String;
                //   577: putfield 171	android/os/Message:obj	Ljava/lang/Object;
                //   580: aload 5
                //   582: aload_0
                //   583: getfield 34	com/qx/qgbox/utils/HttpUtil$8:val$failWhat	I
                //   586: putfield 174	android/os/Message:what	I
                //   589: aload_0
                //   590: getfield 32	com/qx/qgbox/utils/HttpUtil$8:val$handler	Landroid/os/Handler;
                //   593: aload 5
                //   595: invokevirtual 180	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
                //   598: pop
                //   599: aload 4
                //   601: ifnull +18 -> 619
                //   604: aload 4
                //   606: invokevirtual 181	java/io/DataOutputStream:close	()V
                //   609: goto +10 -> 619
                //   612: astore 4
                //   614: aload 4
                //   616: invokevirtual 184	java/io/IOException:printStackTrace	()V
                //   619: aload_3
                //   620: ifnull +15 -> 635
                //   623: aload_3
                //   624: invokevirtual 185	java/io/InputStream:close	()V
                //   627: goto +8 -> 635
                //   630: astore_3
                //   631: aload_3
                //   632: invokevirtual 184	java/io/IOException:printStackTrace	()V
                //   635: aload_2
                //   636: ifnull +7 -> 643
                //   639: aload_2
                //   640: invokevirtual 188	java/net/HttpURLConnection:disconnect	()V
                //   643: return
                //   644: astore 6
                //   646: aload_2
                //   647: astore 5
                //   649: aload 6
                //   651: astore 7
                //   653: aload 4
                //   655: astore_2
                //   656: aload_3
                //   657: astore 6
                //   659: aload 7
                //   661: astore_3
                //   662: aload_2
                //   663: ifnull +15 -> 678
                //   666: aload_2
                //   667: invokevirtual 181	java/io/DataOutputStream:close	()V
                //   670: goto +8 -> 678
                //   673: astore_2
                //   674: aload_2
                //   675: invokevirtual 184	java/io/IOException:printStackTrace	()V
                //   678: aload 6
                //   680: ifnull +16 -> 696
                //   683: aload 6
                //   685: invokevirtual 185	java/io/InputStream:close	()V
                //   688: goto +8 -> 696
                //   691: astore_2
                //   692: aload_2
                //   693: invokevirtual 184	java/io/IOException:printStackTrace	()V
                //   696: aload 5
                //   698: ifnull +8 -> 706
                //   701: aload 5
                //   703: invokevirtual 188	java/net/HttpURLConnection:disconnect	()V
                //   706: aload_3
                //   707: athrow
                //   708: astore_2
                //   709: goto -155 -> 554
                //   712: astore_3
                //   713: goto -178 -> 535
                //   716: astore_3
                //   717: goto -200 -> 517
                //   720: astore 5
                //   722: goto -203 -> 519
                //
                // Exception table:
                //   from	to	target	type
                //   461	466	469	java/io/IOException
                //   480	484	487	java/io/IOException
                //   327	336	499	finally
                //   336	341	499	finally
                //   346	354	499	finally
                //   357	416	499	finally
                //   419	456	499	finally
                //   120	231	507	finally
                //   231	238	507	finally
                //   243	252	507	finally
                //   255	327	507	finally
                //   24	120	522	finally
                //   6	24	544	finally
                //   604	609	612	java/io/IOException
                //   623	627	630	java/io/IOException
                //   562	599	644	finally
                //   666	670	673	java/io/IOException
                //   683	688	691	java/io/IOException
                //   6	24	708	java/lang/Exception
                //   24	120	712	java/lang/Exception
                //   120	231	716	java/lang/Exception
                //   231	238	716	java/lang/Exception
                //   243	252	716	java/lang/Exception
                //   255	327	716	java/lang/Exception
                //   327	336	720	java/lang/Exception
                //   336	341	720	java/lang/Exception
                //   346	354	720	java/lang/Exception
                //   357	416	720	java/lang/Exception
                //   419	456	720	java/lang/Exception
            }
        }).start();
    }
}
