package com.qx.qgbox.utils;

import android.os.Handler;
import android.os.Message;
import com.tencent.mmkv.MMKV;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

public class OkHttpUtil
{
  public static final String TAG = "OkHttpUtil";

  public static void downLoadFile(final Handler paramHandler, final int paramInt1, final int paramInt2, String paramString1, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        new OkHttpClient().newCall(new Request.Builder().url(this.val$path).build()).enqueue(new Callback()
        {
          public void onFailure(Call paramAnonymous2Call, IOException paramAnonymous2IOException)
          {
            OkHttpUtil.3.this.val$handler.sendEmptyMessage(OkHttpUtil.3.this.val$failWhat);
          }

          // ERROR //
          public void onResponse(Call paramAnonymous2Call, Response paramAnonymous2Response)
            throws IOException
          {
            // Byte code:
            //   0: new 44	java/lang/StringBuilder
            //   3: dup
            //   4: invokespecial 45	java/lang/StringBuilder:<init>	()V
            //   7: astore_1
            //   8: aload_1
            //   9: ldc 47
            //   11: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   14: pop
            //   15: aload_1
            //   16: aload_0
            //   17: getfield 17	com/qx/qgbox/utils/OkHttpUtil$3$1:this$0	Lcom/qx/qgbox/utils/OkHttpUtil$3;
            //   20: getfield 55	com/qx/qgbox/utils/OkHttpUtil$3:val$newPath	Ljava/lang/String;
            //   23: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   26: pop
            //   27: ldc 57
            //   29: aload_1
            //   30: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
            //   33: invokestatic 67	com/qx/qgbox/utils/MyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
            //   36: aconst_null
            //   37: astore 4
            //   39: aconst_null
            //   40: astore 6
            //   42: aload_2
            //   43: invokevirtual 73	okhttp3/Response:body	()Lokhttp3/ResponseBody;
            //   46: invokevirtual 79	okhttp3/ResponseBody:byteStream	()Ljava/io/InputStream;
            //   49: astore_1
            //   50: aload_1
            //   51: astore_2
            //   52: new 81	java/io/FileOutputStream
            //   55: dup
            //   56: aload_0
            //   57: getfield 17	com/qx/qgbox/utils/OkHttpUtil$3$1:this$0	Lcom/qx/qgbox/utils/OkHttpUtil$3;
            //   60: getfield 55	com/qx/qgbox/utils/OkHttpUtil$3:val$newPath	Ljava/lang/String;
            //   63: invokespecial 84	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
            //   66: astore 5
            //   68: sipush 2048
            //   71: newarray byte
            //   73: astore_2
            //   74: aload_1
            //   75: aload_2
            //   76: invokevirtual 90	java/io/InputStream:read	([B)I
            //   79: istore_3
            //   80: iload_3
            //   81: iconst_m1
            //   82: if_icmpeq +14 -> 96
            //   85: aload 5
            //   87: aload_2
            //   88: iconst_0
            //   89: iload_3
            //   90: invokevirtual 94	java/io/FileOutputStream:write	([BII)V
            //   93: goto -19 -> 74
            //   96: aload 5
            //   98: invokevirtual 97	java/io/FileOutputStream:flush	()V
            //   101: aload_0
            //   102: getfield 17	com/qx/qgbox/utils/OkHttpUtil$3$1:this$0	Lcom/qx/qgbox/utils/OkHttpUtil$3;
            //   105: getfield 26	com/qx/qgbox/utils/OkHttpUtil$3:val$handler	Landroid/os/Handler;
            //   108: aload_0
            //   109: getfield 17	com/qx/qgbox/utils/OkHttpUtil$3$1:this$0	Lcom/qx/qgbox/utils/OkHttpUtil$3;
            //   112: getfield 100	com/qx/qgbox/utils/OkHttpUtil$3:val$successWhat	I
            //   115: invokevirtual 36	android/os/Handler:sendEmptyMessage	(I)Z
            //   118: pop
            //   119: aload 5
            //   121: ifnull +8 -> 129
            //   124: aload 5
            //   126: invokevirtual 103	java/io/FileOutputStream:close	()V
            //   129: aload_1
            //   130: ifnull +84 -> 214
            //   133: aload_1
            //   134: invokevirtual 104	java/io/InputStream:close	()V
            //   137: return
            //   138: astore 4
            //   140: aload 5
            //   142: astore_2
            //   143: goto +85 -> 228
            //   146: goto +18 -> 164
            //   149: astore 4
            //   151: aconst_null
            //   152: astore_2
            //   153: aload_2
            //   154: astore_1
            //   155: goto +73 -> 228
            //   158: aconst_null
            //   159: astore_1
            //   160: aload 6
            //   162: astore 5
            //   164: aload 5
            //   166: astore 4
            //   168: aload_1
            //   169: astore_2
            //   170: aload_0
            //   171: getfield 17	com/qx/qgbox/utils/OkHttpUtil$3$1:this$0	Lcom/qx/qgbox/utils/OkHttpUtil$3;
            //   174: getfield 26	com/qx/qgbox/utils/OkHttpUtil$3:val$handler	Landroid/os/Handler;
            //   177: aload_0
            //   178: getfield 17	com/qx/qgbox/utils/OkHttpUtil$3$1:this$0	Lcom/qx/qgbox/utils/OkHttpUtil$3;
            //   181: getfield 30	com/qx/qgbox/utils/OkHttpUtil$3:val$failWhat	I
            //   184: invokevirtual 36	android/os/Handler:sendEmptyMessage	(I)Z
            //   187: pop
            //   188: aload 5
            //   190: ifnull +11 -> 201
            //   193: aload 5
            //   195: invokevirtual 103	java/io/FileOutputStream:close	()V
            //   198: goto +3 -> 201
            //   201: aload_1
            //   202: ifnull +12 -> 214
            //   205: aload_1
            //   206: invokevirtual 104	java/io/InputStream:close	()V
            //   209: return
            //   210: aload_1
            //   211: invokevirtual 107	java/io/IOException:printStackTrace	()V
            //   214: return
            //   215: astore_1
            //   216: aload 4
            //   218: astore 5
            //   220: aload_1
            //   221: astore 4
            //   223: aload_2
            //   224: astore_1
            //   225: aload 5
            //   227: astore_2
            //   228: aload_2
            //   229: ifnull +10 -> 239
            //   232: aload_2
            //   233: invokevirtual 103	java/io/FileOutputStream:close	()V
            //   236: goto +3 -> 239
            //   239: aload_1
            //   240: ifnull +14 -> 254
            //   243: aload_1
            //   244: invokevirtual 104	java/io/InputStream:close	()V
            //   247: goto +7 -> 254
            //   250: aload_1
            //   251: invokevirtual 107	java/io/IOException:printStackTrace	()V
            //   254: aload 4
            //   256: athrow
            //   257: astore_1
            //   258: goto -100 -> 158
            //   261: astore_2
            //   262: aload 6
            //   264: astore 5
            //   266: goto -102 -> 164
            //   269: astore_2
            //   270: goto -124 -> 146
            //   273: astore_1
            //   274: goto -64 -> 210
            //   277: astore_1
            //   278: goto -28 -> 250
            //
            // Exception table:
            //   from	to	target	type
            //   68	74	138	finally
            //   74	80	138	finally
            //   85	93	138	finally
            //   96	119	138	finally
            //   42	50	149	finally
            //   52	68	215	finally
            //   170	188	215	finally
            //   42	50	257	java/lang/Exception
            //   52	68	261	java/lang/Exception
            //   68	74	269	java/lang/Exception
            //   74	80	269	java/lang/Exception
            //   85	93	269	java/lang/Exception
            //   96	119	269	java/lang/Exception
            //   124	129	273	java/io/IOException
            //   133	137	273	java/io/IOException
            //   193	198	273	java/io/IOException
            //   205	209	273	java/io/IOException
            //   232	236	277	java/io/IOException
            //   243	247	277	java/io/IOException
          }
        });
      }
    }).start();
  }

  public static void get(final Handler paramHandler, String paramString, final int paramInt1, final int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("-----http get url = ");
    localStringBuilder.append(paramString);
    MyLog.i("OkHttpUtil", localStringBuilder.toString());
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Object localObject = new OkHttpClient().newCall(new Request.Builder().url(this.val$url).build()).execute();
          if (((Response)localObject).isSuccessful())
          {
            localObject = new JSONObject(((Response)localObject).body().string());
            Message localMessage = new Message();
            localMessage.obj = localObject;
            localMessage.what = paramInt1;
            paramHandler.sendMessage(localMessage);
            return;
          }
          localObject = new Message();
          ((Message)localObject).what = paramInt2;
          paramHandler.sendMessage((Message)localObject);
          return;
          label112: localObject = new Message();
          ((Message)localObject).what = paramInt2;
          paramHandler.sendMessage((Message)localObject);
          return;
        }
        catch (Exception localException)
        {
          break label112;
        }
      }
    }).start();
  }

  private void getDataAsync(final Handler paramHandler, String paramString, final int paramInt1, final int paramInt2)
  {
    new OkHttpClient().newCall(new Request.Builder().url(paramString).build()).enqueue(new Callback()
    {
      public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException)
      {
        paramAnonymousCall = new Message();
        paramAnonymousCall.what = paramInt2;
        paramHandler.sendMessage(paramAnonymousCall);
      }

      public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
        throws IOException
      {
        try
        {
          if (paramAnonymousResponse.isSuccessful())
          {
            MyLog.i("OkHttpUtil", "获取数据成功了");
            paramAnonymousCall = new StringBuilder();
            paramAnonymousCall.append("response.code()==");
            paramAnonymousCall.append(paramAnonymousResponse.code());
            MyLog.i("OkHttpUtil", paramAnonymousCall.toString());
            paramAnonymousCall = new StringBuilder();
            paramAnonymousCall.append("response.body().string()==");
            paramAnonymousCall.append(paramAnonymousResponse.body().string());
            MyLog.i("OkHttpUtil", paramAnonymousCall.toString());
            paramAnonymousCall = new JSONObject(paramAnonymousResponse.body().string());
            paramAnonymousResponse = new Message();
            paramAnonymousResponse.obj = paramAnonymousCall;
            paramAnonymousResponse.what = paramInt1;
            paramHandler.sendMessage(paramAnonymousResponse);
            return;
          }
          paramAnonymousCall = new Message();
          paramAnonymousCall.what = paramInt2;
          paramHandler.sendMessage(paramAnonymousCall);
          return;
          label155: paramAnonymousCall = new Message();
          paramAnonymousCall.what = paramInt2;
          paramHandler.sendMessage(paramAnonymousCall);
          return;
        }
        catch (JSONException paramAnonymousCall)
        {
          break label155;
        }
      }
    });
  }

  public static void postDataWithParame(String paramString, HashMap<String, String> paramHashMap)
  {
    OkHttpClient localOkHttpClient = new OkHttpClient();
    FormBody.Builder localBuilder = new FormBody.Builder();
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localBuilder.add(str, (String)paramHashMap.get(str));
    }
    paramHashMap = localBuilder.build();
    localOkHttpClient.newCall(new Request.Builder().url(paramString).post(paramHashMap).build()).enqueue(new Callback()
    {
      public void onFailure(Call paramAnonymousCall, IOException paramAnonymousIOException)
      {
        MyLog.i("OkHttpUtil", "提交数据失败了");
      }

      public void onResponse(Call paramAnonymousCall, Response paramAnonymousResponse)
        throws IOException
      {
        MyLog.i("my_tag", "提交数据成功了");
        MMKV.mmkvWithID("MyID", 2).encode("is_post_phone_info", true);
      }
    });
  }
}
