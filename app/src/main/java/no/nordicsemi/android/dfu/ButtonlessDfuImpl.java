package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;

abstract class ButtonlessDfuImpl extends BaseButtonlessDfuImpl
{
  private static final int DFU_STATUS_SUCCESS = 1;
  private static final byte[] OP_CODE_ENTER_BOOTLOADER = { 1 };
  private static final int OP_CODE_ENTER_BOOTLOADER_KEY = 1;
  private static final int OP_CODE_RESPONSE_CODE_KEY = 32;

  ButtonlessDfuImpl(Intent paramIntent, DfuBaseService paramDfuBaseService)
  {
    super(paramIntent, paramDfuBaseService);
  }

  private int getStatusCode(byte[] paramArrayOfByte, int paramInt)
    throws UnknownResponseException
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length >= 3) && (paramArrayOfByte[0] == 32) && (paramArrayOfByte[1] == paramInt) && ((paramArrayOfByte[2] == 1) || (paramArrayOfByte[2] == 2) || (paramArrayOfByte[2] == 4)))
      return paramArrayOfByte[2];
    throw new UnknownResponseException("Invalid response received", paramArrayOfByte, 32, paramInt);
  }

  protected abstract BluetoothGattCharacteristic getButtonlessDfuCharacteristic();

  protected abstract int getResponseType();

  // ERROR //
  public void performDfu(Intent paramIntent)
    throws no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 49	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mProgressInfo	Lno/nordicsemi/android/dfu/DfuProgressInfo;
    //   4: bipush 254
    //   6: invokevirtual 55	no/nordicsemi/android/dfu/DfuProgressInfo:setProgress	(I)V
    //   9: aload_0
    //   10: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   13: sipush 1000
    //   16: invokevirtual 64	no/nordicsemi/android/dfu/DfuBaseService:waitFor	(I)V
    //   19: aload_0
    //   20: getfield 68	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mGatt	Landroid/bluetooth/BluetoothGatt;
    //   23: astore 4
    //   25: aload_0
    //   26: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   29: bipush 15
    //   31: ldc 70
    //   33: invokevirtual 74	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   36: aload_0
    //   37: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   40: iconst_1
    //   41: ldc 76
    //   43: invokevirtual 74	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   46: aload_0
    //   47: invokevirtual 78	no/nordicsemi/android/dfu/ButtonlessDfuImpl:getButtonlessDfuCharacteristic	()Landroid/bluetooth/BluetoothGattCharacteristic;
    //   50: astore 5
    //   52: aload_0
    //   53: invokevirtual 80	no/nordicsemi/android/dfu/ButtonlessDfuImpl:getResponseType	()I
    //   56: istore_2
    //   57: aload_0
    //   58: aload 5
    //   60: aload_0
    //   61: invokevirtual 80	no/nordicsemi/android/dfu/ButtonlessDfuImpl:getResponseType	()I
    //   64: invokevirtual 84	no/nordicsemi/android/dfu/ButtonlessDfuImpl:enableCCCD	(Landroid/bluetooth/BluetoothGattCharacteristic;I)V
    //   67: aload_0
    //   68: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   71: astore 6
    //   73: new 86	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   80: astore 7
    //   82: iload_2
    //   83: iconst_2
    //   84: if_icmpne +9 -> 93
    //   87: ldc 90
    //   89: astore_3
    //   90: goto +6 -> 96
    //   93: ldc 92
    //   95: astore_3
    //   96: aload 7
    //   98: aload_3
    //   99: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: pop
    //   103: aload 7
    //   105: ldc 98
    //   107: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: aload 6
    //   113: bipush 10
    //   115: aload 7
    //   117: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   120: invokevirtual 74	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   123: aload_0
    //   124: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   127: sipush 1000
    //   130: invokevirtual 64	no/nordicsemi/android/dfu/DfuBaseService:waitFor	(I)V
    //   133: aload_0
    //   134: getfield 49	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mProgressInfo	Lno/nordicsemi/android/dfu/DfuProgressInfo;
    //   137: bipush 253
    //   139: invokevirtual 55	no/nordicsemi/android/dfu/DfuProgressInfo:setProgress	(I)V
    //   142: aload_0
    //   143: ldc 104
    //   145: invokevirtual 108	no/nordicsemi/android/dfu/ButtonlessDfuImpl:logi	(Ljava/lang/String;)V
    //   148: aload_0
    //   149: aload 5
    //   151: getstatic 16	no/nordicsemi/android/dfu/ButtonlessDfuImpl:OP_CODE_ENTER_BOOTLOADER	[B
    //   154: iconst_1
    //   155: invokevirtual 112	no/nordicsemi/android/dfu/ButtonlessDfuImpl:writeOpCode	(Landroid/bluetooth/BluetoothGattCharacteristic;[BZ)V
    //   158: aload_0
    //   159: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   162: bipush 10
    //   164: ldc 114
    //   166: invokevirtual 74	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   169: aload_0
    //   170: invokevirtual 118	no/nordicsemi/android/dfu/ButtonlessDfuImpl:readNotificationResponse	()[B
    //   173: astore_3
    //   174: goto +8 -> 182
    //   177: aload_0
    //   178: getfield 121	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mReceivedData	[B
    //   181: astore_3
    //   182: aload_3
    //   183: ifnull +161 -> 344
    //   186: aload_0
    //   187: aload_3
    //   188: iconst_1
    //   189: invokespecial 123	no/nordicsemi/android/dfu/ButtonlessDfuImpl:getStatusCode	([BI)I
    //   192: istore_2
    //   193: new 86	java/lang/StringBuilder
    //   196: dup
    //   197: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   200: astore 5
    //   202: aload 5
    //   204: ldc 125
    //   206: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: pop
    //   210: aload 5
    //   212: aload_3
    //   213: iconst_1
    //   214: baload
    //   215: invokevirtual 128	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   218: pop
    //   219: aload 5
    //   221: ldc 130
    //   223: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload 5
    //   229: iload_2
    //   230: invokevirtual 128	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   233: pop
    //   234: aload 5
    //   236: ldc 132
    //   238: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   241: pop
    //   242: aload_0
    //   243: aload 5
    //   245: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   248: invokevirtual 108	no/nordicsemi/android/dfu/ButtonlessDfuImpl:logi	(Ljava/lang/String;)V
    //   251: aload_0
    //   252: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   255: astore 5
    //   257: new 86	java/lang/StringBuilder
    //   260: dup
    //   261: invokespecial 88	java/lang/StringBuilder:<init>	()V
    //   264: astore 6
    //   266: aload 6
    //   268: ldc 125
    //   270: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: pop
    //   274: aload 6
    //   276: aload_3
    //   277: iconst_1
    //   278: baload
    //   279: invokevirtual 128	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   282: pop
    //   283: aload 6
    //   285: ldc 130
    //   287: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: pop
    //   291: aload 6
    //   293: iload_2
    //   294: invokevirtual 128	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: aload 6
    //   300: ldc 132
    //   302: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: pop
    //   306: aload 5
    //   308: bipush 10
    //   310: aload 6
    //   312: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   315: invokevirtual 74	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   318: iload_2
    //   319: iconst_1
    //   320: if_icmpeq +14 -> 334
    //   323: new 45	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   326: dup
    //   327: ldc 134
    //   329: iload_2
    //   330: invokespecial 137	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException:<init>	(Ljava/lang/String;I)V
    //   333: athrow
    //   334: aload_0
    //   335: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   338: invokevirtual 140	no/nordicsemi/android/dfu/DfuBaseService:waitUntilDisconnected	()V
    //   341: goto +9 -> 350
    //   344: aload_0
    //   345: ldc 142
    //   347: invokevirtual 108	no/nordicsemi/android/dfu/ButtonlessDfuImpl:logi	(Ljava/lang/String;)V
    //   350: aload_0
    //   351: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   354: iconst_5
    //   355: ldc 144
    //   357: invokevirtual 74	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   360: aload_0
    //   361: aload_1
    //   362: iconst_0
    //   363: aload_0
    //   364: invokevirtual 148	no/nordicsemi/android/dfu/ButtonlessDfuImpl:shouldScanForBootloader	()Z
    //   367: invokevirtual 152	no/nordicsemi/android/dfu/ButtonlessDfuImpl:finalize	(Landroid/content/Intent;ZZ)V
    //   370: return
    //   371: astore_1
    //   372: aload_1
    //   373: invokevirtual 155	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException:getErrorNumber	()I
    //   376: sipush 2048
    //   379: ior
    //   380: istore_2
    //   381: aload_0
    //   382: aload_1
    //   383: invokevirtual 158	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException:getMessage	()Ljava/lang/String;
    //   386: invokevirtual 161	no/nordicsemi/android/dfu/ButtonlessDfuImpl:loge	(Ljava/lang/String;)V
    //   389: aload_0
    //   390: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   393: bipush 20
    //   395: getstatic 167	java/util/Locale:US	Ljava/util/Locale;
    //   398: ldc 169
    //   400: iconst_1
    //   401: anewarray 171	java/lang/Object
    //   404: dup
    //   405: iconst_0
    //   406: iload_2
    //   407: invokestatic 177	no/nordicsemi/android/error/SecureDfuError:parseButtonlessError	(I)Ljava/lang/String;
    //   410: aastore
    //   411: invokestatic 183	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   414: invokevirtual 74	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   417: aload_0
    //   418: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   421: aload 4
    //   423: iload_2
    //   424: sipush 8192
    //   427: ior
    //   428: invokevirtual 187	no/nordicsemi/android/dfu/DfuBaseService:terminateConnection	(Landroid/bluetooth/BluetoothGatt;I)V
    //   431: return
    //   432: astore_1
    //   433: aload_0
    //   434: aload_1
    //   435: invokevirtual 188	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException:getMessage	()Ljava/lang/String;
    //   438: invokevirtual 161	no/nordicsemi/android/dfu/ButtonlessDfuImpl:loge	(Ljava/lang/String;)V
    //   441: aload_0
    //   442: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   445: bipush 20
    //   447: aload_1
    //   448: invokevirtual 188	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException:getMessage	()Ljava/lang/String;
    //   451: invokevirtual 74	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   454: aload_0
    //   455: getfield 59	no/nordicsemi/android/dfu/ButtonlessDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   458: aload 4
    //   460: sipush 4104
    //   463: invokevirtual 187	no/nordicsemi/android/dfu/DfuBaseService:terminateConnection	(Landroid/bluetooth/BluetoothGatt;I)V
    //   466: return
    //   467: astore_3
    //   468: goto -291 -> 177
    //
    // Exception table:
    //   from	to	target	type
    //   133	169	371	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   169	174	371	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   177	182	371	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   186	318	371	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   323	334	371	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   334	341	371	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   344	350	371	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   350	370	371	no/nordicsemi/android/dfu/internal/exception/RemoteDfuException
    //   133	169	432	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException
    //   169	174	432	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException
    //   177	182	432	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException
    //   186	318	432	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException
    //   323	334	432	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException
    //   334	341	432	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException
    //   344	350	432	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException
    //   350	370	432	no/nordicsemi/android/dfu/internal/exception/UnknownResponseException
    //   169	174	467	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
  }

  protected abstract boolean shouldScanForBootloader();
}