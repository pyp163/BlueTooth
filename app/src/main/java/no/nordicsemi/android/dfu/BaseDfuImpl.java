package no.nordicsemi.android.dfu;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.UUID;
import no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScannerFactory;

abstract class BaseDfuImpl
  implements DfuService
{
  protected static final UUID CLIENT_CHARACTERISTIC_CONFIG = new UUID(45088566677504L, -9223371485494954757L);
  protected static final UUID GENERIC_ATTRIBUTE_SERVICE_UUID = new UUID(26392574038016L, -9223371485494954757L);
  protected static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
  protected static final int INDICATIONS = 2;
  protected static final int MAX_PACKET_SIZE_DEFAULT = 20;
  protected static final int NOTIFICATIONS = 1;
  protected static final UUID SERVICE_CHANGED_UUID = new UUID(46200963207168L, -9223371485494954757L);
  private static final String TAG = "DfuImpl";
  protected boolean mAborted;
  protected byte[] mBuffer = new byte[20];
  protected boolean mConnected;
  private int mCurrentMtu;
  protected int mError;
  protected int mFileType;
  protected InputStream mFirmwareStream;
  protected BluetoothGatt mGatt;
  protected int mImageSizeInBytes;
  protected int mInitPacketSizeInBytes;
  protected InputStream mInitPacketStream;
  protected final Object mLock = new Object();
  protected boolean mPaused;
  protected DfuProgressInfo mProgressInfo;
  protected byte[] mReceivedData = null;
  protected boolean mRequestCompleted;
  protected boolean mResetRequestSent;
  protected DfuBaseService mService;

  BaseDfuImpl(Intent paramIntent, DfuBaseService paramDfuBaseService)
  {
    this.mService = paramDfuBaseService;
    this.mProgressInfo = paramDfuBaseService.mProgressInfo;
    this.mConnected = true;
  }

  private boolean createBondApi18(BluetoothDevice paramBluetoothDevice)
  {
    try
    {
      Method localMethod = paramBluetoothDevice.getClass().getMethod("createBond", new Class[0]);
      if (localMethod != null)
      {
        this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond() (hidden)");
        boolean bool = ((Boolean)localMethod.invoke(paramBluetoothDevice, new Object[0])).booleanValue();
        return bool;
      }
    }
    catch (Exception paramBluetoothDevice)
    {
      Log.w("DfuImpl", "An exception occurred while creating bond", paramBluetoothDevice);
    }
    return false;
  }

  private boolean isServiceChangedCCCDEnabled()
    throws DeviceDisconnectedException, DfuException, UploadAbortedException
  {
    if (!this.mConnected)
      throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
    if (this.mAborted)
      throw new UploadAbortedException();
    ??? = this.mGatt;
    Object localObject1 = ((BluetoothGatt)???).getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
    boolean bool2 = false;
    if (localObject1 == null)
      return false;
    localObject1 = ((BluetoothGattService)localObject1).getCharacteristic(SERVICE_CHANGED_UUID);
    if (localObject1 == null)
      return false;
    localObject1 = ((BluetoothGattCharacteristic)localObject1).getDescriptor(CLIENT_CHARACTERISTIC_CONFIG);
    if (localObject1 == null)
      return false;
    this.mRequestCompleted = false;
    this.mError = 0;
    logi("Reading Service Changed CCCD value...");
    this.mService.sendLogBroadcast(1, "Reading Service Changed CCCD value...");
    DfuBaseService localDfuBaseService = this.mService;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gatt.readDescriptor(");
    localStringBuilder.append(((BluetoothGattDescriptor)localObject1).getUuid());
    localStringBuilder.append(")");
    localDfuBaseService.sendLogBroadcast(0, localStringBuilder.toString());
    ((BluetoothGatt)???).readDescriptor((BluetoothGattDescriptor)localObject1);
    try
    {
      synchronized (this.mLock)
      {
        while (((!this.mRequestCompleted) && (this.mConnected) && (this.mError == 0)) || (this.mPaused))
          this.mLock.wait();
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      loge("Sleeping interrupted", localInterruptedException);
      if (this.mError != 0)
        throw new DfuException("Unable to read Service Changed CCCD", this.mError);
      if (!this.mConnected)
        throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
      boolean bool1 = bool2;
      if (((BluetoothGattDescriptor)localObject1).getValue() != null)
      {
        bool1 = bool2;
        if (((BluetoothGattDescriptor)localObject1).getValue().length == 2)
        {
          bool1 = bool2;
          if (localObject1.getValue()[0] == BluetoothGattDescriptor.ENABLE_INDICATION_VALUE[0])
          {
            bool1 = bool2;
            if (localObject1.getValue()[1] == BluetoothGattDescriptor.ENABLE_INDICATION_VALUE[1])
              bool1 = true;
          }
        }
      }
      return bool1;
    }
  }

  public void abort()
  {
    this.mPaused = false;
    this.mAborted = true;
    notifyLock();
  }

  @SuppressLint({"NewApi"})
  protected boolean createBond()
  {
    ??? = this.mGatt.getDevice();
    if (((BluetoothDevice)???).getBondState() == 12)
      return true;
    this.mRequestCompleted = false;
    this.mService.sendLogBroadcast(1, "Starting pairing...");
    boolean bool;
    if (Build.VERSION.SDK_INT >= 19)
    {
      this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond()");
      bool = ((BluetoothDevice)???).createBond();
    }
    else
    {
      bool = createBondApi18((BluetoothDevice)???);
    }
    try
    {
      synchronized (this.mLock)
      {
        while ((!this.mRequestCompleted) && (!this.mAborted))
          this.mLock.wait();
        return bool;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      loge("Sleeping interrupted", localInterruptedException);
    }
    return bool;
  }

  // ERROR //
  protected void enableCCCD(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
    throws DeviceDisconnectedException, DfuException, UploadAbortedException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 167	no/nordicsemi/android/dfu/BaseDfuImpl:mGatt	Landroid/bluetooth/BluetoothGatt;
    //   4: astore 5
    //   6: iload_2
    //   7: iconst_1
    //   8: if_icmpne +11 -> 19
    //   11: ldc_w 281
    //   14: astore 4
    //   16: goto +8 -> 24
    //   19: ldc_w 283
    //   22: astore 4
    //   24: aload_0
    //   25: getfield 102	no/nordicsemi/android/dfu/BaseDfuImpl:mConnected	Z
    //   28: ifne +46 -> 74
    //   31: new 196	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   38: astore_1
    //   39: aload_1
    //   40: ldc_w 285
    //   43: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload_1
    //   48: aload 4
    //   50: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload_1
    //   55: ldc_w 287
    //   58: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: new 151	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   65: dup
    //   66: aload_1
    //   67: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokespecial 162	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException:<init>	(Ljava/lang/String;)V
    //   73: athrow
    //   74: aload_0
    //   75: getfield 164	no/nordicsemi/android/dfu/BaseDfuImpl:mAborted	Z
    //   78: ifeq +11 -> 89
    //   81: new 155	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   84: dup
    //   85: invokespecial 165	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException:<init>	()V
    //   88: athrow
    //   89: aload_0
    //   90: aconst_null
    //   91: putfield 91	no/nordicsemi/android/dfu/BaseDfuImpl:mReceivedData	[B
    //   94: aload_0
    //   95: iconst_0
    //   96: putfield 189	no/nordicsemi/android/dfu/BaseDfuImpl:mError	I
    //   99: aload_1
    //   100: getstatic 73	no/nordicsemi/android/dfu/BaseDfuImpl:CLIENT_CHARACTERISTIC_CONFIG	Ljava/util/UUID;
    //   103: invokevirtual 185	android/bluetooth/BluetoothGattCharacteristic:getDescriptor	(Ljava/util/UUID;)Landroid/bluetooth/BluetoothGattDescriptor;
    //   106: astore 6
    //   108: aload 6
    //   110: invokevirtual 242	android/bluetooth/BluetoothGattDescriptor:getValue	()[B
    //   113: ifnull +38 -> 151
    //   116: aload 6
    //   118: invokevirtual 242	android/bluetooth/BluetoothGattDescriptor:getValue	()[B
    //   121: arraylength
    //   122: iconst_2
    //   123: if_icmpne +28 -> 151
    //   126: aload 6
    //   128: invokevirtual 242	android/bluetooth/BluetoothGattDescriptor:getValue	()[B
    //   131: iconst_0
    //   132: baload
    //   133: ifle +18 -> 151
    //   136: aload 6
    //   138: invokevirtual 242	android/bluetooth/BluetoothGattDescriptor:getValue	()[B
    //   141: iconst_1
    //   142: baload
    //   143: ifne +8 -> 151
    //   146: iconst_1
    //   147: istore_3
    //   148: goto +5 -> 153
    //   151: iconst_0
    //   152: istore_3
    //   153: iload_3
    //   154: ifeq +4 -> 158
    //   157: return
    //   158: new 196	java/lang/StringBuilder
    //   161: dup
    //   162: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   165: astore 7
    //   167: aload 7
    //   169: ldc_w 289
    //   172: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: pop
    //   176: aload 7
    //   178: aload 4
    //   180: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: pop
    //   184: aload 7
    //   186: ldc_w 291
    //   189: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: pop
    //   193: aload_0
    //   194: aload 7
    //   196: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokevirtual 194	no/nordicsemi/android/dfu/BaseDfuImpl:logi	(Ljava/lang/String;)V
    //   202: aload_0
    //   203: getfield 95	no/nordicsemi/android/dfu/BaseDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   206: astore 7
    //   208: new 196	java/lang/StringBuilder
    //   211: dup
    //   212: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   215: astore 8
    //   217: aload 8
    //   219: ldc_w 289
    //   222: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: pop
    //   226: aload 8
    //   228: aload 4
    //   230: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: pop
    //   234: aload 8
    //   236: ldc_w 293
    //   239: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: pop
    //   243: aload 8
    //   245: aload_1
    //   246: invokevirtual 294	android/bluetooth/BluetoothGattCharacteristic:getUuid	()Ljava/util/UUID;
    //   249: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   252: pop
    //   253: aload 7
    //   255: iconst_1
    //   256: aload 8
    //   258: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   261: invokevirtual 128	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   264: aload_0
    //   265: getfield 95	no/nordicsemi/android/dfu/BaseDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   268: astore 7
    //   270: new 196	java/lang/StringBuilder
    //   273: dup
    //   274: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   277: astore 8
    //   279: aload 8
    //   281: ldc_w 296
    //   284: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: pop
    //   288: aload 8
    //   290: aload_1
    //   291: invokevirtual 294	android/bluetooth/BluetoothGattCharacteristic:getUuid	()Ljava/util/UUID;
    //   294: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: aload 8
    //   300: ldc_w 298
    //   303: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: pop
    //   307: aload 7
    //   309: iconst_0
    //   310: aload 8
    //   312: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   315: invokevirtual 128	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   318: aload 5
    //   320: aload_1
    //   321: iconst_1
    //   322: invokevirtual 302	android/bluetooth/BluetoothGatt:setCharacteristicNotification	(Landroid/bluetooth/BluetoothGattCharacteristic;Z)Z
    //   325: pop
    //   326: iload_2
    //   327: iconst_1
    //   328: if_icmpne +10 -> 338
    //   331: getstatic 305	android/bluetooth/BluetoothGattDescriptor:ENABLE_NOTIFICATION_VALUE	[B
    //   334: astore_1
    //   335: goto +7 -> 342
    //   338: getstatic 245	android/bluetooth/BluetoothGattDescriptor:ENABLE_INDICATION_VALUE	[B
    //   341: astore_1
    //   342: aload 6
    //   344: aload_1
    //   345: invokevirtual 309	android/bluetooth/BluetoothGattDescriptor:setValue	([B)Z
    //   348: pop
    //   349: aload_0
    //   350: getfield 95	no/nordicsemi/android/dfu/BaseDfuImpl:mService	Lno/nordicsemi/android/dfu/DfuBaseService;
    //   353: astore 7
    //   355: new 196	java/lang/StringBuilder
    //   358: dup
    //   359: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   362: astore 8
    //   364: aload 8
    //   366: ldc_w 311
    //   369: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: pop
    //   373: aload 8
    //   375: aload 6
    //   377: invokevirtual 209	android/bluetooth/BluetoothGattDescriptor:getUuid	()Ljava/util/UUID;
    //   380: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   383: pop
    //   384: iload_2
    //   385: iconst_1
    //   386: if_icmpne +10 -> 396
    //   389: ldc_w 313
    //   392: astore_1
    //   393: goto +7 -> 400
    //   396: ldc_w 315
    //   399: astore_1
    //   400: aload 8
    //   402: aload_1
    //   403: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: pop
    //   407: aload 7
    //   409: iconst_0
    //   410: aload 8
    //   412: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   415: invokevirtual 128	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   418: aload 5
    //   420: aload 6
    //   422: invokevirtual 318	android/bluetooth/BluetoothGatt:writeDescriptor	(Landroid/bluetooth/BluetoothGattDescriptor;)Z
    //   425: pop
    //   426: aload_0
    //   427: getfield 89	no/nordicsemi/android/dfu/BaseDfuImpl:mLock	Ljava/lang/Object;
    //   430: astore_1
    //   431: aload_1
    //   432: monitorenter
    //   433: iload_3
    //   434: ifne +20 -> 454
    //   437: aload_0
    //   438: getfield 102	no/nordicsemi/android/dfu/BaseDfuImpl:mConnected	Z
    //   441: ifeq +13 -> 454
    //   444: aload_0
    //   445: getfield 189	no/nordicsemi/android/dfu/BaseDfuImpl:mError	I
    //   448: ifeq +13 -> 461
    //   451: goto +3 -> 454
    //   454: aload_0
    //   455: getfield 224	no/nordicsemi/android/dfu/BaseDfuImpl:mPaused	Z
    //   458: ifeq +53 -> 511
    //   461: aload_0
    //   462: getfield 89	no/nordicsemi/android/dfu/BaseDfuImpl:mLock	Ljava/lang/Object;
    //   465: invokevirtual 227	java/lang/Object:wait	()V
    //   468: aload 6
    //   470: invokevirtual 242	android/bluetooth/BluetoothGattDescriptor:getValue	()[B
    //   473: ifnull +166 -> 639
    //   476: aload 6
    //   478: invokevirtual 242	android/bluetooth/BluetoothGattDescriptor:getValue	()[B
    //   481: arraylength
    //   482: iconst_2
    //   483: if_icmpne +156 -> 639
    //   486: aload 6
    //   488: invokevirtual 242	android/bluetooth/BluetoothGattDescriptor:getValue	()[B
    //   491: iconst_0
    //   492: baload
    //   493: ifle +146 -> 639
    //   496: aload 6
    //   498: invokevirtual 242	android/bluetooth/BluetoothGattDescriptor:getValue	()[B
    //   501: iconst_1
    //   502: baload
    //   503: ifne +136 -> 639
    //   506: iconst_1
    //   507: istore_3
    //   508: goto -75 -> 433
    //   511: aload_1
    //   512: monitorexit
    //   513: goto +16 -> 529
    //   516: aload_1
    //   517: monitorexit
    //   518: aload 5
    //   520: athrow
    //   521: astore_1
    //   522: aload_0
    //   523: ldc 229
    //   525: aload_1
    //   526: invokevirtual 233	no/nordicsemi/android/dfu/BaseDfuImpl:loge	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   529: aload_0
    //   530: getfield 189	no/nordicsemi/android/dfu/BaseDfuImpl:mError	I
    //   533: ifeq +50 -> 583
    //   536: new 196	java/lang/StringBuilder
    //   539: dup
    //   540: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   543: astore_1
    //   544: aload_1
    //   545: ldc_w 285
    //   548: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   551: pop
    //   552: aload_1
    //   553: aload 4
    //   555: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: pop
    //   559: aload_1
    //   560: ldc_w 320
    //   563: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: pop
    //   567: new 153	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   570: dup
    //   571: aload_1
    //   572: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   575: aload_0
    //   576: getfield 189	no/nordicsemi/android/dfu/BaseDfuImpl:mError	I
    //   579: invokespecial 238	no/nordicsemi/android/dfu/internal/exception/DfuException:<init>	(Ljava/lang/String;I)V
    //   582: athrow
    //   583: aload_0
    //   584: getfield 102	no/nordicsemi/android/dfu/BaseDfuImpl:mConnected	Z
    //   587: ifne +46 -> 633
    //   590: new 196	java/lang/StringBuilder
    //   593: dup
    //   594: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   597: astore_1
    //   598: aload_1
    //   599: ldc_w 285
    //   602: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   605: pop
    //   606: aload_1
    //   607: aload 4
    //   609: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   612: pop
    //   613: aload_1
    //   614: ldc_w 287
    //   617: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   620: pop
    //   621: new 151	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   624: dup
    //   625: aload_1
    //   626: invokevirtual 218	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   629: invokespecial 162	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException:<init>	(Ljava/lang/String;)V
    //   632: athrow
    //   633: return
    //   634: astore 5
    //   636: goto -120 -> 516
    //   639: iconst_0
    //   640: istore_3
    //   641: goto -208 -> 433
    //
    // Exception table:
    //   from	to	target	type
    //   426	433	521	java/lang/InterruptedException
    //   518	521	521	java/lang/InterruptedException
    //   437	451	634	finally
    //   454	461	634	finally
    //   461	506	634	finally
    //   511	513	634	finally
    //   516	518	634	finally
  }

  public boolean initialize(Intent paramIntent, BluetoothGatt paramBluetoothGatt, int paramInt, InputStream paramInputStream1, InputStream paramInputStream2)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException
  {
    this.mGatt = paramBluetoothGatt;
    this.mFileType = paramInt;
    this.mFirmwareStream = paramInputStream1;
    this.mInitPacketStream = paramInputStream2;
    int k = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT", 1);
    int i = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL", 1);
    this.mCurrentMtu = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU", 23);
    if (paramInt > 4)
    {
      logw("DFU target does not support (SD/BL)+App update, splitting into 2 parts");
      this.mService.sendLogBroadcast(15, "Sending system components");
      this.mFileType &= -5;
      ((ArchiveInputStream)this.mFirmwareStream).setContentType(this.mFileType);
      i = 2;
    }
    if (k == 2)
      this.mService.sendLogBroadcast(15, "Sending application");
    int j = 0;
    try
    {
      if (paramInputStream2.markSupported())
        paramInputStream2.reset();
      paramInt = paramInputStream2.available();
      break label153;
      paramInt = 0;
      label153: this.mInitPacketSizeInBytes = paramInt;
    }
    catch (Exception paramIntent)
    {
      try
      {
        if (paramInputStream1.markSupported())
          if ((paramInputStream1 instanceof ArchiveInputStream))
            ((ArchiveInputStream)paramInputStream1).fullReset();
          else
            paramInputStream1.reset();
        paramInt = paramInputStream1.available();
        this.mImageSizeInBytes = paramInt;
        this.mProgressInfo.init(paramInt, k, i);
        if (paramBluetoothGatt.getDevice().getBondState() == 12)
        {
          paramIntent = paramBluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
          if (paramIntent != null)
          {
            paramIntent = paramIntent.getCharacteristic(SERVICE_CHANGED_UUID);
            if (paramIntent != null)
            {
              if (!isServiceChangedCCCDEnabled())
                enableCCCD(paramIntent, 2);
              this.mService.sendLogBroadcast(10, "Service Changed indications enabled");
            }
          }
        }
        return true;
        paramIntent = paramIntent;
      }
      catch (Exception paramIntent)
      {
        while (true)
          paramInt = j;
      }
    }
  }

  protected boolean isBonded()
  {
    return this.mGatt.getDevice().getBondState() == 12;
  }

  void loge(String paramString)
  {
    Log.e("DfuImpl", paramString);
  }

  void loge(String paramString, Throwable paramThrowable)
  {
    Log.e("DfuImpl", paramString, paramThrowable);
  }

  void logi(String paramString)
  {
    if (DfuBaseService.DEBUG)
      Log.i("DfuImpl", paramString);
  }

  void logw(String paramString)
  {
    if (DfuBaseService.DEBUG)
      Log.w("DfuImpl", paramString);
  }

  protected void notifyLock()
  {
    synchronized (this.mLock)
    {
      this.mLock.notifyAll();
      return;
    }
  }

  public void onBondStateChanged(int paramInt)
  {
    this.mRequestCompleted = true;
    notifyLock();
  }

  protected String parse(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return "";
    int j = paramArrayOfByte.length;
    if (j == 0)
      return "";
    char[] arrayOfChar = new char[j * 3 - 1];
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfByte[i] & 0xFF;
      int m = i * 3;
      arrayOfChar[m] = HEX_ARRAY[(k >>> 4)];
      arrayOfChar[(m + 1)] = HEX_ARRAY[(k & 0xF)];
      if (i != j - 1)
        arrayOfChar[(m + 2)] = '-';
      i += 1;
    }
    return new String(arrayOfChar);
  }

  public void pause()
  {
    this.mPaused = true;
  }

  protected byte[] readNotificationResponse()
    throws DeviceDisconnectedException, DfuException, UploadAbortedException
  {
    try
    {
      synchronized (this.mLock)
      {
        while (((this.mReceivedData == null) && (this.mConnected) && (this.mError == 0) && (!this.mAborted)) || (this.mPaused))
          this.mLock.wait();
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      loge("Sleeping interrupted", localInterruptedException);
      if (this.mAborted)
        throw new UploadAbortedException();
      if (this.mError != 0)
        throw new DfuException("Unable to write Op Code", this.mError);
      if (!this.mConnected)
        throw new DeviceDisconnectedException("Unable to write Op Code: device disconnected");
    }
    return this.mReceivedData;
  }

  public void release()
  {
    this.mService = null;
  }

  protected boolean removeBond()
  {
    ??? = this.mGatt.getDevice();
    int i = ((BluetoothDevice)???).getBondState();
    boolean bool = true;
    if (i == 10)
      return true;
    this.mService.sendLogBroadcast(1, "Removing bond information...");
    try
    {
      Method localMethod = ???.getClass().getMethod("removeBond", new Class[0]);
      if (localMethod != null)
      {
        this.mRequestCompleted = false;
        this.mService.sendLogBroadcast(0, "gatt.getDevice().removeBond() (hidden)");
        bool = ((Boolean)localMethod.invoke(???, new Object[0])).booleanValue();
        try
        {
          synchronized (this.mLock)
          {
            while ((!this.mRequestCompleted) && (!this.mAborted))
              this.mLock.wait();
            return true;
          }
        }
        catch (Exception localException1)
        {
        }
        catch (InterruptedException localInterruptedException)
        {
          loge("Sleeping interrupted", localInterruptedException);
          return true;
        }
      }
    }
    catch (Exception localException2)
    {
      bool = false;
      Log.w("DfuImpl", "An exception occurred while removing bond information", localException2);
    }
    return bool;
  }

  @RequiresApi(api=21)
  protected void requestMtu(int paramInt)
    throws DeviceDisconnectedException, UploadAbortedException
  {
    if (this.mAborted)
      throw new UploadAbortedException();
    this.mRequestCompleted = false;
    this.mService.sendLogBroadcast(1, "Requesting new MTU...");
    ??? = this.mService;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gatt.requestMtu(");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(")");
    ((DfuBaseService)???).sendLogBroadcast(0, localStringBuilder.toString());
    if (!this.mGatt.requestMtu(paramInt))
      return;
    try
    {
      synchronized (this.mLock)
      {
        while (((!this.mRequestCompleted) && (this.mConnected) && (this.mError == 0)) || (this.mPaused))
          this.mLock.wait();
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      loge("Sleeping interrupted", localInterruptedException);
      if (!this.mConnected)
        throw new DeviceDisconnectedException("Unable to read Service Changed CCCD: device disconnected");
    }
  }

  protected void restartService(Intent paramIntent, boolean paramBoolean)
  {
    String str;
    if (paramBoolean)
    {
      this.mService.sendLogBroadcast(1, "Scanning for the DFU Bootloader...");
      str = BootloaderScannerFactory.getScanner().searchFor(this.mGatt.getDevice().getAddress());
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Scanning for new address finished with: ");
      ((StringBuilder)localObject).append(str);
      logi(((StringBuilder)localObject).toString());
      if (str != null)
      {
        localObject = this.mService;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("DFU Bootloader found with address ");
        localStringBuilder.append(str);
        ((DfuBaseService)localObject).sendLogBroadcast(5, localStringBuilder.toString());
      }
      else
      {
        this.mService.sendLogBroadcast(5, "DFU Bootloader not found. Trying the same address...");
      }
    }
    else
    {
      str = null;
    }
    if (str != null)
      paramIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", str);
    this.mService.startService(paramIntent);
  }

  public void resume()
  {
    this.mPaused = false;
    notifyLock();
  }

  protected void waitIfPaused()
  {
    try
    {
      synchronized (this.mLock)
      {
        while (this.mPaused)
          this.mLock.wait();
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      loge("Sleeping interrupted", localInterruptedException);
    }
  }

  protected void writeOpCode(BluetoothGattCharacteristic arg1, byte[] paramArrayOfByte, boolean paramBoolean)
    throws DeviceDisconnectedException, DfuException, UploadAbortedException
  {
    if (this.mAborted)
      throw new UploadAbortedException();
    this.mReceivedData = null;
    this.mError = 0;
    this.mRequestCompleted = false;
    this.mResetRequestSent = paramBoolean;
    ???.setWriteType(2);
    ???.setValue(paramArrayOfByte);
    DfuBaseService localDfuBaseService = this.mService;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Writing to characteristic ");
    localStringBuilder.append(???.getUuid());
    localDfuBaseService.sendLogBroadcast(1, localStringBuilder.toString());
    localDfuBaseService = this.mService;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("gatt.writeCharacteristic(");
    localStringBuilder.append(???.getUuid());
    localStringBuilder.append(")");
    localDfuBaseService.sendLogBroadcast(0, localStringBuilder.toString());
    this.mGatt.writeCharacteristic(???);
    try
    {
      synchronized (this.mLock)
      {
        while (((!this.mRequestCompleted) && (this.mConnected) && (this.mError == 0)) || (this.mPaused))
          this.mLock.wait();
      }
    }
    catch (InterruptedException )
    {
      loge("Sleeping interrupted", ???);
      if ((!this.mResetRequestSent) && (this.mError != 0))
      {
        ??? = new StringBuilder();
        ???.append("Unable to write Op Code ");
        ???.append(paramArrayOfByte[0]);
        throw new DfuException(???.toString(), this.mError);
      }
      if ((!this.mResetRequestSent) && (!this.mConnected))
      {
        ??? = new StringBuilder();
        ???.append("Unable to write Op Code ");
        ???.append(paramArrayOfByte[0]);
        ???.append(": device disconnected");
        throw new DeviceDisconnectedException(???.toString());
      }
    }
  }

  protected class BaseBluetoothGattCallback extends DfuCallback.DfuGattCallback
  {
    protected BaseBluetoothGattCallback()
    {
    }

    private String parse(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte == null)
        return "";
      int j = paramArrayOfByte.length;
      if (j == 0)
        return "";
      char[] arrayOfChar = new char[j * 3 - 1];
      int i = 0;
      while (i < j)
      {
        int k = paramArrayOfByte[i] & 0xFF;
        int m = i * 3;
        arrayOfChar[m] = BaseDfuImpl.HEX_ARRAY[(k >>> 4)];
        arrayOfChar[(m + 1)] = BaseDfuImpl.HEX_ARRAY[(k & 0xF)];
        if (i != j - 1)
          arrayOfChar[(m + 2)] = '-';
        i += 1;
      }
      return new String(arrayOfChar);
    }

    private String phyToString(int paramInt)
    {
      switch (paramInt)
      {
      default:
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("UNKNOWN (");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(")");
        return localStringBuilder.toString();
      case 3:
        return "LE Coded";
      case 2:
        return "LE 2M";
      case 1:
      }
      return "LE 1M";
    }

    public void onCharacteristicRead(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
    {
      if (paramInt == 0)
      {
        paramBluetoothGatt = BaseDfuImpl.this.mService;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Read Response received from ");
        localStringBuilder.append(paramBluetoothGattCharacteristic.getUuid());
        localStringBuilder.append(", value (0x): ");
        localStringBuilder.append(parse(paramBluetoothGattCharacteristic));
        paramBluetoothGatt.sendLogBroadcast(5, localStringBuilder.toString());
        BaseDfuImpl.this.mReceivedData = paramBluetoothGattCharacteristic.getValue();
        BaseDfuImpl.this.mRequestCompleted = true;
      }
      else
      {
        paramBluetoothGatt = BaseDfuImpl.this;
        paramBluetoothGattCharacteristic = new StringBuilder();
        paramBluetoothGattCharacteristic.append("Characteristic read error: ");
        paramBluetoothGattCharacteristic.append(paramInt);
        paramBluetoothGatt.loge(paramBluetoothGattCharacteristic.toString());
        BaseDfuImpl.this.mError = (paramInt | 0x4000);
      }
      BaseDfuImpl.this.notifyLock();
    }

    public void onDescriptorRead(BluetoothGatt paramBluetoothGatt, BluetoothGattDescriptor paramBluetoothGattDescriptor, int paramInt)
    {
      if (paramInt == 0)
      {
        if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(paramBluetoothGattDescriptor.getUuid()))
        {
          paramBluetoothGatt = BaseDfuImpl.this.mService;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Read Response received from descr.");
          localStringBuilder.append(paramBluetoothGattDescriptor.getCharacteristic().getUuid());
          localStringBuilder.append(", value (0x): ");
          localStringBuilder.append(parse(paramBluetoothGattDescriptor));
          paramBluetoothGatt.sendLogBroadcast(5, localStringBuilder.toString());
          if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(paramBluetoothGattDescriptor.getCharacteristic().getUuid()))
            BaseDfuImpl.this.mRequestCompleted = true;
          else
            BaseDfuImpl.this.loge("Unknown descriptor read");
        }
      }
      else
      {
        paramBluetoothGatt = BaseDfuImpl.this;
        paramBluetoothGattDescriptor = new StringBuilder();
        paramBluetoothGattDescriptor.append("Descriptor read error: ");
        paramBluetoothGattDescriptor.append(paramInt);
        paramBluetoothGatt.loge(paramBluetoothGattDescriptor.toString());
        BaseDfuImpl.this.mError = (paramInt | 0x4000);
      }
      BaseDfuImpl.this.notifyLock();
    }

    public void onDescriptorWrite(BluetoothGatt paramBluetoothGatt, BluetoothGattDescriptor paramBluetoothGattDescriptor, int paramInt)
    {
      if (paramInt == 0)
      {
        if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(paramBluetoothGattDescriptor.getUuid()))
        {
          paramBluetoothGatt = BaseDfuImpl.this.mService;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Data written to descr.");
          localStringBuilder.append(paramBluetoothGattDescriptor.getCharacteristic().getUuid());
          localStringBuilder.append(", value (0x): ");
          localStringBuilder.append(parse(paramBluetoothGattDescriptor));
          paramBluetoothGatt.sendLogBroadcast(5, localStringBuilder.toString());
          if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(paramBluetoothGattDescriptor.getCharacteristic().getUuid()))
          {
            paramBluetoothGatt = BaseDfuImpl.this.mService;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Indications enabled for ");
            localStringBuilder.append(paramBluetoothGattDescriptor.getCharacteristic().getUuid());
            paramBluetoothGatt.sendLogBroadcast(1, localStringBuilder.toString());
          }
          else
          {
            paramBluetoothGatt = BaseDfuImpl.this.mService;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Notifications enabled for ");
            localStringBuilder.append(paramBluetoothGattDescriptor.getCharacteristic().getUuid());
            paramBluetoothGatt.sendLogBroadcast(1, localStringBuilder.toString());
          }
        }
      }
      else
      {
        paramBluetoothGatt = BaseDfuImpl.this;
        paramBluetoothGattDescriptor = new StringBuilder();
        paramBluetoothGattDescriptor.append("Descriptor write error: ");
        paramBluetoothGattDescriptor.append(paramInt);
        paramBluetoothGatt.loge(paramBluetoothGattDescriptor.toString());
        BaseDfuImpl.this.mError = (paramInt | 0x4000);
      }
      BaseDfuImpl.this.notifyLock();
    }

    public void onDisconnected()
    {
      BaseDfuImpl.this.mConnected = false;
      BaseDfuImpl.this.notifyLock();
    }

    public void onMtuChanged(BluetoothGatt paramBluetoothGatt, int paramInt1, int paramInt2)
    {
      StringBuilder localStringBuilder;
      if (paramInt2 == 0)
      {
        paramBluetoothGatt = BaseDfuImpl.this.mService;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("MTU changed to: ");
        localStringBuilder.append(paramInt1);
        paramBluetoothGatt.sendLogBroadcast(5, localStringBuilder.toString());
        paramInt2 = paramInt1 - 3;
        if (paramInt2 > BaseDfuImpl.this.mBuffer.length)
          BaseDfuImpl.this.mBuffer = new byte[paramInt2];
        paramBluetoothGatt = BaseDfuImpl.this;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("MTU changed to: ");
        localStringBuilder.append(paramInt1);
        paramBluetoothGatt.logi(localStringBuilder.toString());
      }
      else
      {
        paramBluetoothGatt = BaseDfuImpl.this;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Changing MTU failed: ");
        localStringBuilder.append(paramInt2);
        localStringBuilder.append(" (mtu: ");
        localStringBuilder.append(paramInt1);
        localStringBuilder.append(")");
        paramBluetoothGatt.logw(localStringBuilder.toString());
        if ((paramInt2 == 4) && (BaseDfuImpl.this.mCurrentMtu > 23) && (BaseDfuImpl.this.mCurrentMtu - 3 > BaseDfuImpl.this.mBuffer.length))
        {
          BaseDfuImpl.this.mBuffer = new byte[BaseDfuImpl.this.mCurrentMtu - 3];
          paramBluetoothGatt = BaseDfuImpl.this;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("MTU restored to: ");
          localStringBuilder.append(BaseDfuImpl.this.mCurrentMtu);
          paramBluetoothGatt.logi(localStringBuilder.toString());
        }
      }
      BaseDfuImpl.this.mRequestCompleted = true;
      BaseDfuImpl.this.notifyLock();
    }

    public void onPhyUpdate(BluetoothGatt paramBluetoothGatt, int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt3 == 0)
      {
        paramBluetoothGatt = BaseDfuImpl.this.mService;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("PHY updated (TX: ");
        localStringBuilder.append(phyToString(paramInt1));
        localStringBuilder.append(", RX: ");
        localStringBuilder.append(phyToString(paramInt2));
        localStringBuilder.append(")");
        paramBluetoothGatt.sendLogBroadcast(5, localStringBuilder.toString());
        paramBluetoothGatt = BaseDfuImpl.this;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("PHY updated (TX: ");
        localStringBuilder.append(phyToString(paramInt1));
        localStringBuilder.append(", RX: ");
        localStringBuilder.append(phyToString(paramInt2));
        localStringBuilder.append(")");
        paramBluetoothGatt.logi(localStringBuilder.toString());
        return;
      }
      paramBluetoothGatt = BaseDfuImpl.this;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Updating PHY failed: ");
      localStringBuilder.append(paramInt3);
      localStringBuilder.append(" (txPhy: ");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(", rxPhy: ");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(")");
      paramBluetoothGatt.logw(localStringBuilder.toString());
    }

    protected String parse(BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
    {
      return parse(paramBluetoothGattCharacteristic.getValue());
    }

    protected String parse(BluetoothGattDescriptor paramBluetoothGattDescriptor)
    {
      return parse(paramBluetoothGattDescriptor.getValue());
    }
  }
}