package no.nordicsemi.android.dfu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;
import no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import no.nordicsemi.android.dfu.internal.HexInputStream;

public abstract class DfuBaseService extends IntentService
  implements DfuProgressInfo.ProgressListener
{
  public static final int ACTION_ABORT = 2;
  public static final int ACTION_PAUSE = 0;
  public static final int ACTION_RESUME = 1;
  public static final String BROADCAST_ACTION = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION";
  public static final String BROADCAST_ERROR = "no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR";
  public static final String BROADCAST_LOG = "no.nordicsemi.android.dfu.broadcast.BROADCAST_LOG";
  public static final String BROADCAST_PROGRESS = "no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS";
  static boolean DEBUG = false;
  public static final int ERROR_BLUETOOTH_DISABLED = 4106;
  public static final int ERROR_CONNECTION_MASK = 16384;
  public static final int ERROR_CONNECTION_STATE_MASK = 32768;
  public static final int ERROR_CRC_ERROR = 4109;
  public static final int ERROR_DEVICE_DISCONNECTED = 4096;
  public static final int ERROR_DEVICE_NOT_BONDED = 4110;
  public static final int ERROR_FILE_ERROR = 4098;
  public static final int ERROR_FILE_INVALID = 4099;
  public static final int ERROR_FILE_IO_EXCEPTION = 4100;
  public static final int ERROR_FILE_NOT_FOUND = 4097;
  public static final int ERROR_FILE_SIZE_INVALID = 4108;
  public static final int ERROR_FILE_TYPE_UNSUPPORTED = 4105;
  public static final int ERROR_INIT_PACKET_REQUIRED = 4107;
  public static final int ERROR_INVALID_RESPONSE = 4104;
  public static final int ERROR_MASK = 4096;
  public static final int ERROR_REMOTE_MASK = 8192;
  public static final int ERROR_REMOTE_TYPE_LEGACY = 256;
  public static final int ERROR_REMOTE_TYPE_SECURE = 512;
  public static final int ERROR_REMOTE_TYPE_SECURE_BUTTONLESS = 2048;
  public static final int ERROR_REMOTE_TYPE_SECURE_EXTENDED = 1024;
  public static final int ERROR_SERVICE_DISCOVERY_NOT_STARTED = 4101;
  public static final int ERROR_SERVICE_NOT_FOUND = 4102;
  public static final int ERROR_TYPE_COMMUNICATION = 2;
  public static final int ERROR_TYPE_COMMUNICATION_STATE = 1;
  public static final int ERROR_TYPE_DFU_REMOTE = 3;
  public static final int ERROR_TYPE_OTHER = 0;
  public static final String EXTRA_ACTION = "no.nordicsemi.android.dfu.extra.EXTRA_ACTION";
  private static final String EXTRA_ATTEMPT = "no.nordicsemi.android.dfu.extra.EXTRA_ATTEMPT";
  public static final String EXTRA_AVG_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_AVG_SPEED_B_PER_MS";
  public static final String EXTRA_CURRENT_MTU = "no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU";
  public static final String EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING";
  public static final String EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING";
  public static final String EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU";
  public static final String EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU";
  public static final String EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU";
  public static final String EXTRA_DATA = "no.nordicsemi.android.dfu.extra.EXTRA_DATA";
  public static final String EXTRA_DEVICE_ADDRESS = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS";
  public static final String EXTRA_DEVICE_NAME = "no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME";
  public static final String EXTRA_DISABLE_NOTIFICATION = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION";
  public static final String EXTRA_DISABLE_RESUME = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME";
  public static final String EXTRA_ERROR_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE";
  public static final String EXTRA_FILE_MIME_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE";
  public static final String EXTRA_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH";
  public static final String EXTRA_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID";
  public static final String EXTRA_FILE_TYPE = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE";
  public static final String EXTRA_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI";
  public static final String EXTRA_FORCE_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_FORCE_DFU";
  public static final String EXTRA_FOREGROUND_SERVICE = "no.nordicsemi.android.dfu.extra.EXTRA_FOREGROUND_SERVICE";
  public static final String EXTRA_INIT_FILE_PATH = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH";
  public static final String EXTRA_INIT_FILE_RES_ID = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID";
  public static final String EXTRA_INIT_FILE_URI = "no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI";
  public static final String EXTRA_KEEP_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND";
  public static final String EXTRA_LOG_LEVEL = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_LEVEL";
  public static final String EXTRA_LOG_MESSAGE = "no.nordicsemi.android.dfu.extra.EXTRA_LOG_INFO";
  public static final String EXTRA_MTU = "no.nordicsemi.android.dfu.extra.EXTRA_MTU";
  public static final String EXTRA_PACKET_RECEIPT_NOTIFICATIONS_ENABLED = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED";
  public static final String EXTRA_PACKET_RECEIPT_NOTIFICATIONS_VALUE = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE";
  public static final String EXTRA_PARTS_TOTAL = "no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL";
  public static final String EXTRA_PART_CURRENT = "no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT";
  public static final String EXTRA_PROGRESS = "no.nordicsemi.android.dfu.extra.EXTRA_PROGRESS";
  public static final String EXTRA_RESTORE_BOND = "no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND";
  public static final String EXTRA_SPEED_B_PER_MS = "no.nordicsemi.android.dfu.extra.EXTRA_SPEED_B_PER_MS";
  public static final String EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU = "no.nordicsemi.android.dfu.extra.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU";
  public static final int LOG_LEVEL_APPLICATION = 10;
  public static final int LOG_LEVEL_DEBUG = 0;
  public static final int LOG_LEVEL_ERROR = 20;
  public static final int LOG_LEVEL_INFO = 5;
  public static final int LOG_LEVEL_VERBOSE = 1;
  public static final int LOG_LEVEL_WARNING = 15;
  public static final String MIME_TYPE_OCTET_STREAM = "application/octet-stream";
  public static final String MIME_TYPE_ZIP = "application/zip";
  public static final String NOTIFICATION_CHANNEL_DFU = "dfu";
  public static final int NOTIFICATION_ID = 283;
  public static final int PROGRESS_ABORTED = -7;
  public static final int PROGRESS_COMPLETED = -6;
  public static final int PROGRESS_CONNECTING = -1;
  public static final int PROGRESS_DISCONNECTING = -5;
  public static final int PROGRESS_ENABLING_DFU_MODE = -3;
  public static final int PROGRESS_STARTING = -2;
  public static final int PROGRESS_VALIDATING = -4;
  protected static final int STATE_CLOSED = -5;
  protected static final int STATE_CONNECTED = -2;
  protected static final int STATE_CONNECTED_AND_READY = -3;
  protected static final int STATE_CONNECTING = -1;
  protected static final int STATE_DISCONNECTED = 0;
  protected static final int STATE_DISCONNECTING = -4;
  private static final String TAG = "DfuBaseService";
  public static final int TYPE_APPLICATION = 4;
  public static final int TYPE_AUTO = 0;
  public static final int TYPE_BOOTLOADER = 2;
  public static final int TYPE_SOFT_DEVICE = 1;
  private boolean mAborted;
  private BluetoothAdapter mBluetoothAdapter;
  private final BroadcastReceiver mBluetoothStateBroadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = paramAnonymousIntent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
      int j = paramAnonymousIntent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 12);
      paramAnonymousContext = DfuBaseService.this;
      paramAnonymousIntent = new StringBuilder();
      paramAnonymousIntent.append("Action received: android.bluetooth.adapter.action.STATE_CHANGED [state: ");
      paramAnonymousIntent.append(i);
      paramAnonymousIntent.append(", previous state: ");
      paramAnonymousIntent.append(j);
      paramAnonymousIntent.append("]");
      paramAnonymousContext.logw(paramAnonymousIntent.toString());
      if ((j == 12) && ((i == 13) || (i == 10)))
      {
        DfuBaseService.this.sendLogBroadcast(15, "Bluetooth adapter disabled");
        DfuBaseService.this.mConnectionState = 0;
        if (DfuBaseService.this.mDfuServiceImpl != null)
          DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
      }
    }
  };
  private final BroadcastReceiver mBondStateBroadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (!((BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(DfuBaseService.this.mDeviceAddress))
        return;
      int i = paramAnonymousIntent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
      if (i == 11)
        return;
      if (DfuBaseService.this.mDfuServiceImpl != null)
        DfuBaseService.this.mDfuServiceImpl.onBondStateChanged(i);
    }
  };
  protected int mConnectionState;
  private final BroadcastReceiver mConnectionStateBroadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (!((BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getAddress().equals(DfuBaseService.this.mDeviceAddress))
        return;
      paramAnonymousContext = paramAnonymousIntent.getAction();
      paramAnonymousIntent = DfuBaseService.this;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Action received: ");
      localStringBuilder.append(paramAnonymousContext);
      paramAnonymousIntent.logi(localStringBuilder.toString());
      paramAnonymousIntent = DfuBaseService.this;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("[Broadcast] Action received: ");
      localStringBuilder.append(paramAnonymousContext);
      paramAnonymousIntent.sendLogBroadcast(0, localStringBuilder.toString());
    }
  };
  private String mDeviceAddress;
  private String mDeviceName;
  private final BroadcastReceiver mDfuActionReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = paramAnonymousIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_ACTION", 0);
      paramAnonymousContext = DfuBaseService.this;
      paramAnonymousIntent = new StringBuilder();
      paramAnonymousIntent.append("User action received: ");
      paramAnonymousIntent.append(i);
      paramAnonymousContext.logi(paramAnonymousIntent.toString());
      switch (i)
      {
      default:
      case 2:
        DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Abort action received");
        DfuBaseService.access$202(DfuBaseService.this, true);
        if (DfuBaseService.this.mDfuServiceImpl != null)
        {
          DfuBaseService.this.mDfuServiceImpl.abort();
          return;
        }
        break;
      case 1:
        DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Resume action received");
        if (DfuBaseService.this.mDfuServiceImpl != null)
        {
          DfuBaseService.this.mDfuServiceImpl.resume();
          return;
        }
        break;
      case 0:
        DfuBaseService.this.sendLogBroadcast(15, "[Broadcast] Pause action received");
        if (DfuBaseService.this.mDfuServiceImpl != null)
          DfuBaseService.this.mDfuServiceImpl.pause();
        break;
      }
    }
  };
  private DfuCallback mDfuServiceImpl;
  private boolean mDisableNotification;
  private int mError;
  private InputStream mFirmwareInputStream;
  private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback()
  {
    public void onCharacteristicChanged(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic)
    {
      if (DfuBaseService.this.mDfuServiceImpl != null)
        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicChanged(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattCharacteristic);
    }

    public void onCharacteristicRead(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic, int paramAnonymousInt)
    {
      if (DfuBaseService.this.mDfuServiceImpl != null)
        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicRead(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattCharacteristic, paramAnonymousInt);
    }

    public void onCharacteristicWrite(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic, int paramAnonymousInt)
    {
      if (DfuBaseService.this.mDfuServiceImpl != null)
        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onCharacteristicWrite(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattCharacteristic, paramAnonymousInt);
    }

    public void onConnectionStateChange(BluetoothGatt arg1, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Object localObject1;
      if (paramAnonymousInt1 == 0)
      {
        if (paramAnonymousInt2 == 2)
        {
          DfuBaseService.this.logi("Connected to GATT server");
          localObject1 = DfuBaseService.this;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Connected to ");
          localStringBuilder.append(DfuBaseService.this.mDeviceAddress);
          ((DfuBaseService)localObject1).sendLogBroadcast(5, localStringBuilder.toString());
          DfuBaseService.this.mConnectionState = -2;
          if (???.getDevice().getBondState() == 12)
          {
            DfuBaseService.this.logi("Waiting 1600 ms for a possible Service Changed indication...");
            DfuBaseService.this.waitFor(1600);
          }
          DfuBaseService.this.sendLogBroadcast(1, "Discovering services...");
          DfuBaseService.this.sendLogBroadcast(0, "gatt.discoverServices()");
          boolean bool = ???.discoverServices();
          localObject1 = DfuBaseService.this;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Attempting to start service discovery... ");
          if (bool)
            ??? = "succeed";
          else
            ??? = "failed";
          localStringBuilder.append(???);
          ((DfuBaseService)localObject1).logi(localStringBuilder.toString());
          if (!bool)
            DfuBaseService.access$502(DfuBaseService.this, 4101);
        }
        else if (paramAnonymousInt2 == 0)
        {
          DfuBaseService.this.logi("Disconnected from GATT server");
          DfuBaseService.this.mConnectionState = 0;
          if (DfuBaseService.this.mDfuServiceImpl != null)
            DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
        }
      }
      else
      {
        if ((paramAnonymousInt1 != 8) && (paramAnonymousInt1 != 19))
        {
          ??? = DfuBaseService.this;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Connection state change error: ");
          ((StringBuilder)localObject1).append(paramAnonymousInt1);
          ((StringBuilder)localObject1).append(" newState: ");
          ((StringBuilder)localObject1).append(paramAnonymousInt2);
          ???.loge(((StringBuilder)localObject1).toString());
        }
        else
        {
          ??? = DfuBaseService.this;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Target device disconnected with status: ");
          ((StringBuilder)localObject1).append(paramAnonymousInt1);
          ???.logw(((StringBuilder)localObject1).toString());
        }
        DfuBaseService.access$502(DfuBaseService.this, paramAnonymousInt1 | 0x8000);
        if (paramAnonymousInt2 == 0)
        {
          DfuBaseService.this.mConnectionState = 0;
          if (DfuBaseService.this.mDfuServiceImpl != null)
            DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDisconnected();
        }
      }
      synchronized (DfuBaseService.this.mLock)
      {
        DfuBaseService.this.mLock.notifyAll();
        return;
      }
    }

    public void onDescriptorRead(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattDescriptor paramAnonymousBluetoothGattDescriptor, int paramAnonymousInt)
    {
      if (DfuBaseService.this.mDfuServiceImpl != null)
        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDescriptorRead(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattDescriptor, paramAnonymousInt);
    }

    public void onDescriptorWrite(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattDescriptor paramAnonymousBluetoothGattDescriptor, int paramAnonymousInt)
    {
      if (DfuBaseService.this.mDfuServiceImpl != null)
        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onDescriptorWrite(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattDescriptor, paramAnonymousInt);
    }

    @SuppressLint({"NewApi"})
    public void onMtuChanged(BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      if (DfuBaseService.this.mDfuServiceImpl != null)
        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onMtuChanged(paramAnonymousBluetoothGatt, paramAnonymousInt1, paramAnonymousInt2);
    }

    @SuppressLint({"NewApi"})
    public void onPhyUpdate(BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      if (DfuBaseService.this.mDfuServiceImpl != null)
        DfuBaseService.this.mDfuServiceImpl.getGattCallback().onPhyUpdate(paramAnonymousBluetoothGatt, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
    }

    public void onServicesDiscovered(BluetoothGatt arg1, int paramAnonymousInt)
    {
      if (paramAnonymousInt == 0)
      {
        DfuBaseService.this.logi("Services discovered");
        DfuBaseService.this.mConnectionState = -3;
      }
      else
      {
        ??? = DfuBaseService.this;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Service discovery error: ");
        localStringBuilder.append(paramAnonymousInt);
        ???.loge(localStringBuilder.toString());
        DfuBaseService.access$502(DfuBaseService.this, paramAnonymousInt | 0x4000);
      }
      synchronized (DfuBaseService.this.mLock)
      {
        DfuBaseService.this.mLock.notifyAll();
        return;
      }
    }
  };
  private InputStream mInitFileInputStream;
  private long mLastNotificationTime;
  private int mLastProgress = -1;
  private final Object mLock = new Object();
  DfuProgressInfo mProgressInfo;

  public DfuBaseService()
  {
    super("DfuBaseService");
  }

  private boolean initialize()
  {
    BluetoothManager localBluetoothManager = (BluetoothManager)getSystemService("bluetooth");
    if (localBluetoothManager == null)
    {
      loge("Unable to initialize BluetoothManager.");
      return false;
    }
    this.mBluetoothAdapter = localBluetoothManager.getAdapter();
    if (this.mBluetoothAdapter == null)
    {
      loge("Unable to obtain a BluetoothAdapter.");
      return false;
    }
    return true;
  }

  private void loge(String paramString)
  {
    Log.e("DfuBaseService", paramString);
  }

  private void loge(String paramString, Throwable paramThrowable)
  {
    Log.e("DfuBaseService", paramString, paramThrowable);
  }

  private void logi(String paramString)
  {
    if (DEBUG)
      Log.i("DfuBaseService", paramString);
  }

  private void logw(String paramString)
  {
    if (DEBUG)
      Log.w("DfuBaseService", paramString);
  }

  private static IntentFilter makeDfuActionIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION");
    return localIntentFilter;
  }

  private InputStream openInputStream(int paramInt1, String paramString, int paramInt2, int paramInt3)
    throws IOException
  {
    InputStream localInputStream = getResources().openRawResource(paramInt1);
    if ("application/zip".equals(paramString))
      return new ArchiveInputStream(localInputStream, paramInt2, paramInt3);
    localInputStream.mark(2);
    paramInt1 = localInputStream.read();
    localInputStream.reset();
    if (paramInt1 == 58)
      return new HexInputStream(localInputStream, paramInt2);
    return localInputStream;
  }

  private InputStream openInputStream(Uri paramUri, String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    InputStream localInputStream = getContentResolver().openInputStream(paramUri);
    if ("application/zip".equals(paramString))
      return new ArchiveInputStream(localInputStream, paramInt1, paramInt2);
    paramUri = getContentResolver().query(paramUri, new String[] { "_display_name" }, null, null, null);
    try
    {
      if ((paramUri.moveToNext()) && (paramUri.getString(0).toLowerCase(Locale.US).endsWith("hex")))
      {
        paramString = new HexInputStream(localInputStream, paramInt1);
        return paramString;
      }
      return localInputStream;
    }
    finally
    {
      paramUri.close();
    }
    throw paramString;
  }

  private InputStream openInputStream(String paramString1, String paramString2, int paramInt1, int paramInt2)
    throws IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(paramString1);
    if ("application/zip".equals(paramString2))
      return new ArchiveInputStream(localFileInputStream, paramInt1, paramInt2);
    if (paramString1.toLowerCase(Locale.US).endsWith("hex"))
      return new HexInputStream(localFileInputStream, paramInt1);
    return localFileInputStream;
  }

  private void report(int paramInt)
  {
    sendErrorBroadcast(paramInt);
    if (this.mDisableNotification)
      return;
    String str2 = this.mDeviceAddress;
    String str1;
    if (this.mDeviceName != null)
      str1 = this.mDeviceName;
    else
      str1 = getString(R.string.dfu_unknown_name);
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this, "dfu").setSmallIcon(17301640).setOnlyAlertOnce(true).setColor(-65536).setOngoing(false).setContentTitle(getString(R.string.dfu_status_error)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_error_msg)).setAutoCancel(true);
    Intent localIntent = new Intent(this, getNotificationTarget());
    localIntent.addFlags(268435456);
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", str2);
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME", str1);
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PROGRESS", paramInt);
    localBuilder.setContentIntent(PendingIntent.getActivity(this, 0, localIntent, 134217728));
    updateErrorNotification(localBuilder);
    ((NotificationManager)getSystemService("notification")).notify(283, localBuilder.build());
  }

  private void sendErrorBroadcast(int paramInt)
  {
    Intent localIntent = new Intent("no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR");
    if ((paramInt & 0x4000) > 0)
    {
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", paramInt & 0xFFFFBFFF);
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE", 2);
    }
    else if ((0x8000 & paramInt) > 0)
    {
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", paramInt & 0xFFFF7FFF);
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE", 1);
    }
    else if ((paramInt & 0x2000) > 0)
    {
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", paramInt & 0xFFFFDFFF);
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE", 3);
    }
    else
    {
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", paramInt);
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE", 0);
    }
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", this.mDeviceAddress);
    LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
  }

  private void sendProgressBroadcast(DfuProgressInfo paramDfuProgressInfo)
  {
    Intent localIntent = new Intent("no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS");
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", paramDfuProgressInfo.getProgress());
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", this.mDeviceAddress);
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT", paramDfuProgressInfo.getCurrentPart());
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL", paramDfuProgressInfo.getTotalParts());
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_SPEED_B_PER_MS", paramDfuProgressInfo.getSpeed());
    localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_AVG_SPEED_B_PER_MS", paramDfuProgressInfo.getAverageSpeed());
    LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
  }

  private void startForeground()
  {
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this, "dfu").setSmallIcon(17301640).setContentTitle(getString(R.string.dfu_status_foreground_title)).setContentText(getString(R.string.dfu_status_foreground_content)).setColor(-7829368).setPriority(-1).setOngoing(true);
    Object localObject = getNotificationTarget();
    if (localObject != null)
    {
      localObject = new Intent(this, (Class)localObject);
      ((Intent)localObject).addFlags(268435456);
      ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", this.mDeviceAddress);
      ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME", this.mDeviceName);
      localBuilder.setContentIntent(PendingIntent.getActivity(this, 0, (Intent)localObject, 134217728));
    }
    else
    {
      logw("getNotificationTarget() should not return null if the service is to be started as a foreground service");
    }
    updateForegroundNotification(localBuilder);
    startForeground(283, localBuilder.build());
  }

  protected void close(BluetoothGatt paramBluetoothGatt)
  {
    logi("Cleaning up...");
    sendLogBroadcast(0, "gatt.close()");
    paramBluetoothGatt.close();
    this.mConnectionState = -5;
  }

  protected BluetoothGatt connect(String paramString)
  {
    if (!this.mBluetoothAdapter.isEnabled())
      return null;
    this.mConnectionState = -1;
    logi("Connecting to the device...");
    paramString = this.mBluetoothAdapter.getRemoteDevice(paramString);
    sendLogBroadcast(0, "gatt = device.connectGatt(autoConnect = false)");
    paramString = paramString.connectGatt(this, false, this.mGattCallback);
    try
    {
      synchronized (this.mLock)
      {
        while (((this.mConnectionState == -1) || (this.mConnectionState == -2)) && (this.mError == 0))
          this.mLock.wait();
        return paramString;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      loge("Sleeping interrupted", localInterruptedException);
    }
    return paramString;
  }

  protected void disconnect(BluetoothGatt paramBluetoothGatt)
  {
    if (this.mConnectionState == 0)
      return;
    sendLogBroadcast(1, "Disconnecting...");
    this.mProgressInfo.setProgress(-5);
    this.mConnectionState = -4;
    logi("Disconnecting from the device...");
    sendLogBroadcast(0, "gatt.disconnect()");
    paramBluetoothGatt.disconnect();
    waitUntilDisconnected();
    sendLogBroadcast(5, "Disconnected");
  }

  protected abstract Class<? extends Activity> getNotificationTarget();

  protected boolean isDebug()
  {
    return false;
  }

  public void onCreate()
  {
    super.onCreate();
    DEBUG = isDebug();
    logi("DFU service created. Version: 1.8.1");
    initialize();
    Object localObject = LocalBroadcastManager.getInstance(this);
    IntentFilter localIntentFilter = makeDfuActionIntentFilter();
    ((LocalBroadcastManager)localObject).registerReceiver(this.mDfuActionReceiver, localIntentFilter);
    registerReceiver(this.mDfuActionReceiver, localIntentFilter);
    localObject = new IntentFilter();
    ((IntentFilter)localObject).addAction("android.bluetooth.device.action.ACL_CONNECTED");
    ((IntentFilter)localObject).addAction("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED");
    ((IntentFilter)localObject).addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
    registerReceiver(this.mConnectionStateBroadcastReceiver, (IntentFilter)localObject);
    localObject = new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED");
    registerReceiver(this.mBondStateBroadcastReceiver, (IntentFilter)localObject);
    localObject = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
    registerReceiver(this.mBluetoothStateBroadcastReceiver, (IntentFilter)localObject);
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (this.mDfuServiceImpl != null)
      this.mDfuServiceImpl.abort();
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mDfuActionReceiver);
    unregisterReceiver(this.mDfuActionReceiver);
    unregisterReceiver(this.mConnectionStateBroadcastReceiver);
    unregisterReceiver(this.mBondStateBroadcastReceiver);
    unregisterReceiver(this.mBluetoothStateBroadcastReceiver);
    try
    {
      try
      {
        if (this.mFirmwareInputStream != null)
          this.mFirmwareInputStream.close();
        if (this.mInitFileInputStream != null)
          this.mInitFileInputStream.close();
      }
      finally
      {
        this.mFirmwareInputStream = null;
        this.mInitFileInputStream = null;
      }
      label107: this.mFirmwareInputStream = null;
      this.mInitFileInputStream = null;
      logi("DFU service destroyed");
      return;
    }
    catch (IOException localIOException)
    {
      break label107;
    }
  }

  // ERROR //
  protected void onHandleIntent(Intent paramIntent)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc 119
    //   3: invokevirtual 762	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   6: astore 13
    //   8: aload_1
    //   9: ldc 122
    //   11: invokevirtual 762	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   14: astore 9
    //   16: aload_1
    //   17: ldc 125
    //   19: iconst_0
    //   20: invokevirtual 766	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   23: istore 7
    //   25: aload_1
    //   26: ldc 152
    //   28: iconst_1
    //   29: invokevirtual 766	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   32: istore 8
    //   34: aload_1
    //   35: ldc 137
    //   37: invokevirtual 762	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   40: astore 15
    //   42: aload_1
    //   43: ldc 146
    //   45: invokevirtual 770	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   48: checkcast 772	android/net/Uri
    //   51: astore 16
    //   53: aload_1
    //   54: ldc 140
    //   56: iconst_0
    //   57: invokevirtual 776	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   60: istore 5
    //   62: aload_1
    //   63: ldc 155
    //   65: invokevirtual 762	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   68: astore 12
    //   70: aload_1
    //   71: ldc 161
    //   73: invokevirtual 770	android/content/Intent:getParcelableExtra	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   76: checkcast 772	android/net/Uri
    //   79: astore 14
    //   81: aload_1
    //   82: ldc 158
    //   84: iconst_0
    //   85: invokevirtual 776	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   88: istore 6
    //   90: aload_1
    //   91: ldc 143
    //   93: iconst_0
    //   94: invokevirtual 776	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   97: istore_3
    //   98: iload_3
    //   99: istore_2
    //   100: aload 15
    //   102: ifnull +33 -> 135
    //   105: iload_3
    //   106: istore_2
    //   107: iload_3
    //   108: ifne +27 -> 135
    //   111: aload 15
    //   113: getstatic 457	java/util/Locale:US	Ljava/util/Locale;
    //   116: invokevirtual 461	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   119: ldc_w 778
    //   122: invokevirtual 467	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   125: ifeq +8 -> 133
    //   128: iconst_0
    //   129: istore_2
    //   130: goto +5 -> 135
    //   133: iconst_4
    //   134: istore_2
    //   135: aload_1
    //   136: ldc 134
    //   138: invokevirtual 762	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   141: astore 11
    //   143: aload 11
    //   145: ifnull +6 -> 151
    //   148: goto +18 -> 166
    //   151: iload_2
    //   152: ifne +10 -> 162
    //   155: ldc 213
    //   157: astore 11
    //   159: goto +7 -> 166
    //   162: ldc 210
    //   164: astore 11
    //   166: iload_2
    //   167: bipush 248
    //   169: iand
    //   170: ifgt +1940 -> 2110
    //   173: ldc 213
    //   175: aload 11
    //   177: invokevirtual 402	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   180: ifne +16 -> 196
    //   183: ldc 210
    //   185: aload 11
    //   187: invokevirtual 402	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifne +6 -> 196
    //   193: goto +1917 -> 2110
    //   196: ldc 210
    //   198: aload 11
    //   200: invokevirtual 402	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   203: ifeq +42 -> 245
    //   206: iload_2
    //   207: iconst_1
    //   208: if_icmpeq +37 -> 245
    //   211: iload_2
    //   212: iconst_2
    //   213: if_icmpeq +32 -> 245
    //   216: iload_2
    //   217: iconst_4
    //   218: if_icmpeq +27 -> 245
    //   221: aload_0
    //   222: ldc_w 780
    //   225: invokespecial 322	no/nordicsemi/android/dfu/DfuBaseService:logw	(Ljava/lang/String;)V
    //   228: aload_0
    //   229: bipush 15
    //   231: ldc_w 780
    //   234: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   237: aload_0
    //   238: sipush 4105
    //   241: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   244: return
    //   245: iload 7
    //   247: ifne +21 -> 268
    //   250: aload_0
    //   251: invokevirtual 532	no/nordicsemi/android/dfu/DfuBaseService:getNotificationTarget	()Ljava/lang/Class;
    //   254: ifnonnull +14 -> 268
    //   257: new 784	java/lang/NullPointerException
    //   260: dup
    //   261: ldc_w 786
    //   264: invokespecial 787	java/lang/NullPointerException:<init>	(Ljava/lang/String;)V
    //   267: athrow
    //   268: iload 8
    //   270: ifne +18 -> 288
    //   273: getstatic 792	android/os/Build$VERSION:SDK_INT	I
    //   276: bipush 26
    //   278: if_icmplt +10 -> 288
    //   281: aload_0
    //   282: ldc_w 794
    //   285: invokespecial 322	no/nordicsemi/android/dfu/DfuBaseService:logw	(Ljava/lang/String;)V
    //   288: aload_1
    //   289: invokestatic 799	no/nordicsemi/android/dfu/UuidHelper:assignCustomUuids	(Landroid/content/Intent;)V
    //   292: aload_0
    //   293: aload 13
    //   295: putfield 326	no/nordicsemi/android/dfu/DfuBaseService:mDeviceAddress	Ljava/lang/String;
    //   298: aload_0
    //   299: aload 9
    //   301: putfield 482	no/nordicsemi/android/dfu/DfuBaseService:mDeviceName	Ljava/lang/String;
    //   304: aload_0
    //   305: iload 7
    //   307: putfield 480	no/nordicsemi/android/dfu/DfuBaseService:mDisableNotification	Z
    //   310: aload_0
    //   311: iconst_0
    //   312: putfield 641	no/nordicsemi/android/dfu/DfuBaseService:mConnectionState	I
    //   315: aload_0
    //   316: iconst_0
    //   317: putfield 330	no/nordicsemi/android/dfu/DfuBaseService:mError	I
    //   320: aload_0
    //   321: invokestatic 805	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   324: ldc_w 807
    //   327: sipush 4096
    //   330: invokestatic 810	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   333: invokeinterface 815 3 0
    //   338: astore 9
    //   340: aload 9
    //   342: invokestatic 821	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   345: istore 4
    //   347: iload 4
    //   349: istore_3
    //   350: iload 4
    //   352: ifge +12 -> 364
    //   355: iconst_0
    //   356: istore_3
    //   357: goto +7 -> 364
    //   360: sipush 4096
    //   363: istore_3
    //   364: iload 8
    //   366: ifeq +7 -> 373
    //   369: aload_0
    //   370: invokespecial 823	no/nordicsemi/android/dfu/DfuBaseService:startForeground	()V
    //   373: aload_0
    //   374: iconst_1
    //   375: ldc_w 825
    //   378: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   381: aload_0
    //   382: getfield 735	no/nordicsemi/android/dfu/DfuBaseService:mFirmwareInputStream	Ljava/io/InputStream;
    //   385: astore 9
    //   387: aload_0
    //   388: getfield 738	no/nordicsemi/android/dfu/DfuBaseService:mInitFileInputStream	Ljava/io/InputStream;
    //   391: astore 10
    //   393: aload_0
    //   394: getfield 735	no/nordicsemi/android/dfu/DfuBaseService:mFirmwareInputStream	Ljava/io/InputStream;
    //   397: astore 17
    //   399: aload 17
    //   401: ifnonnull +9 -> 410
    //   404: iconst_1
    //   405: istore 4
    //   407: goto +6 -> 413
    //   410: iconst_0
    //   411: istore 4
    //   413: iload 4
    //   415: ifeq +1777 -> 2192
    //   418: aload_0
    //   419: iconst_1
    //   420: ldc_w 827
    //   423: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   426: aload 16
    //   428: ifnull +18 -> 446
    //   431: aload_0
    //   432: aload 16
    //   434: aload 11
    //   436: iload_3
    //   437: iload_2
    //   438: invokespecial 829	no/nordicsemi/android/dfu/DfuBaseService:openInputStream	(Landroid/net/Uri;Ljava/lang/String;II)Ljava/io/InputStream;
    //   441: astore 9
    //   443: goto +43 -> 486
    //   446: aload 15
    //   448: ifnull +18 -> 466
    //   451: aload_0
    //   452: aload 15
    //   454: aload 11
    //   456: iload_3
    //   457: iload_2
    //   458: invokespecial 831	no/nordicsemi/android/dfu/DfuBaseService:openInputStream	(Ljava/lang/String;Ljava/lang/String;II)Ljava/io/InputStream;
    //   461: astore 9
    //   463: goto +23 -> 486
    //   466: iload 5
    //   468: ifle +1682 -> 2150
    //   471: aload_0
    //   472: iload 5
    //   474: aload 11
    //   476: iload_3
    //   477: iload_2
    //   478: invokespecial 833	no/nordicsemi/android/dfu/DfuBaseService:openInputStream	(ILjava/lang/String;II)Ljava/io/InputStream;
    //   481: astore 9
    //   483: goto +3 -> 486
    //   486: aload 14
    //   488: ifnull +17 -> 505
    //   491: aload_0
    //   492: invokevirtual 431	no/nordicsemi/android/dfu/DfuBaseService:getContentResolver	()Landroid/content/ContentResolver;
    //   495: aload 14
    //   497: invokevirtual 436	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   500: astore 10
    //   502: goto +1651 -> 2153
    //   505: aload 12
    //   507: ifnull +17 -> 524
    //   510: new 473	java/io/FileInputStream
    //   513: dup
    //   514: aload 12
    //   516: invokespecial 474	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   519: astore 10
    //   521: goto +1632 -> 2153
    //   524: iload 6
    //   526: ifle +1630 -> 2156
    //   529: aload_0
    //   530: invokevirtual 390	no/nordicsemi/android/dfu/DfuBaseService:getResources	()Landroid/content/res/Resources;
    //   533: iload 6
    //   535: invokevirtual 396	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   538: astore 10
    //   540: goto +1613 -> 2153
    //   543: aload 9
    //   545: invokevirtual 836	java/io/InputStream:available	()I
    //   548: iconst_4
    //   549: irem
    //   550: ifeq +1609 -> 2159
    //   553: new 750	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   556: dup
    //   557: ldc_w 838
    //   560: invokespecial 839	no/nordicsemi/android/dfu/internal/exception/SizeValidationException:<init>	(Ljava/lang/String;)V
    //   563: athrow
    //   564: ldc 213
    //   566: aload 11
    //   568: invokevirtual 402	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   571: ifeq +1642 -> 2213
    //   574: aload 12
    //   576: checkcast 404	no/nordicsemi/android/dfu/internal/ArchiveInputStream
    //   579: astore 10
    //   581: iload_2
    //   582: ifne +12 -> 594
    //   585: aload 10
    //   587: invokevirtual 842	no/nordicsemi/android/dfu/internal/ArchiveInputStream:getContentType	()I
    //   590: istore_2
    //   591: goto +10 -> 601
    //   594: aload 10
    //   596: iload_2
    //   597: invokevirtual 846	no/nordicsemi/android/dfu/internal/ArchiveInputStream:setContentType	(I)I
    //   600: istore_2
    //   601: iload_2
    //   602: iconst_4
    //   603: iand
    //   604: ifle +24 -> 628
    //   607: aload 10
    //   609: invokevirtual 849	no/nordicsemi/android/dfu/internal/ArchiveInputStream:applicationImageSize	()I
    //   612: iconst_4
    //   613: irem
    //   614: ifeq +14 -> 628
    //   617: new 750	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   620: dup
    //   621: ldc_w 851
    //   624: invokespecial 839	no/nordicsemi/android/dfu/internal/exception/SizeValidationException:<init>	(Ljava/lang/String;)V
    //   627: athrow
    //   628: iload_2
    //   629: iconst_2
    //   630: iand
    //   631: ifle +24 -> 655
    //   634: aload 10
    //   636: invokevirtual 854	no/nordicsemi/android/dfu/internal/ArchiveInputStream:bootloaderImageSize	()I
    //   639: iconst_4
    //   640: irem
    //   641: ifeq +14 -> 655
    //   644: new 750	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   647: dup
    //   648: ldc_w 856
    //   651: invokespecial 839	no/nordicsemi/android/dfu/internal/exception/SizeValidationException:<init>	(Ljava/lang/String;)V
    //   654: athrow
    //   655: iload_2
    //   656: iconst_1
    //   657: iand
    //   658: ifle +24 -> 682
    //   661: aload 10
    //   663: invokevirtual 859	no/nordicsemi/android/dfu/internal/ArchiveInputStream:softDeviceImageSize	()I
    //   666: iconst_4
    //   667: irem
    //   668: ifeq +14 -> 682
    //   671: new 750	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   674: dup
    //   675: ldc_w 861
    //   678: invokespecial 839	no/nordicsemi/android/dfu/internal/exception/SizeValidationException:<init>	(Ljava/lang/String;)V
    //   681: athrow
    //   682: iload_2
    //   683: iconst_4
    //   684: if_icmpne +28 -> 712
    //   687: aload 10
    //   689: invokevirtual 865	no/nordicsemi/android/dfu/internal/ArchiveInputStream:getApplicationInit	()[B
    //   692: ifnull +1518 -> 2210
    //   695: new 867	java/io/ByteArrayInputStream
    //   698: dup
    //   699: aload 10
    //   701: invokevirtual 865	no/nordicsemi/android/dfu/internal/ArchiveInputStream:getApplicationInit	()[B
    //   704: invokespecial 870	java/io/ByteArrayInputStream:<init>	([B)V
    //   707: astore 9
    //   709: goto +1494 -> 2203
    //   712: aload 10
    //   714: invokevirtual 873	no/nordicsemi/android/dfu/internal/ArchiveInputStream:getSystemInit	()[B
    //   717: ifnull +1493 -> 2210
    //   720: new 867	java/io/ByteArrayInputStream
    //   723: dup
    //   724: aload 10
    //   726: invokevirtual 873	no/nordicsemi/android/dfu/internal/ArchiveInputStream:getSystemInit	()[B
    //   729: invokespecial 870	java/io/ByteArrayInputStream:<init>	([B)V
    //   732: astore 9
    //   734: goto +1469 -> 2203
    //   737: iload 4
    //   739: ifeq +28 -> 767
    //   742: aload 12
    //   744: aload 12
    //   746: invokevirtual 836	java/io/InputStream:available	()I
    //   749: invokevirtual 413	java/io/InputStream:mark	(I)V
    //   752: aload 11
    //   754: ifnull +13 -> 767
    //   757: aload 11
    //   759: aload 11
    //   761: invokevirtual 836	java/io/InputStream:available	()I
    //   764: invokevirtual 413	java/io/InputStream:mark	(I)V
    //   767: aload_0
    //   768: aload 12
    //   770: putfield 735	no/nordicsemi/android/dfu/DfuBaseService:mFirmwareInputStream	Ljava/io/InputStream;
    //   773: aload_0
    //   774: aload 11
    //   776: putfield 738	no/nordicsemi/android/dfu/DfuBaseService:mInitFileInputStream	Ljava/io/InputStream;
    //   779: aload_0
    //   780: iconst_5
    //   781: ldc_w 875
    //   784: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   787: iload 4
    //   789: ifne +17 -> 806
    //   792: aload_0
    //   793: sipush 1000
    //   796: invokevirtual 878	no/nordicsemi/android/dfu/DfuBaseService:waitFor	(I)V
    //   799: aload_0
    //   800: sipush 1000
    //   803: invokevirtual 878	no/nordicsemi/android/dfu/DfuBaseService:waitFor	(I)V
    //   806: aload_0
    //   807: new 590	no/nordicsemi/android/dfu/DfuProgressInfo
    //   810: dup
    //   811: aload_0
    //   812: invokespecial 881	no/nordicsemi/android/dfu/DfuProgressInfo:<init>	(Lno/nordicsemi/android/dfu/DfuProgressInfo$ProgressListener;)V
    //   815: putfield 676	no/nordicsemi/android/dfu/DfuBaseService:mProgressInfo	Lno/nordicsemi/android/dfu/DfuProgressInfo;
    //   818: aload_0
    //   819: getfield 318	no/nordicsemi/android/dfu/DfuBaseService:mAborted	Z
    //   822: ifeq +40 -> 862
    //   825: aload_0
    //   826: ldc_w 883
    //   829: invokespecial 322	no/nordicsemi/android/dfu/DfuBaseService:logw	(Ljava/lang/String;)V
    //   832: aload_0
    //   833: bipush 15
    //   835: ldc_w 883
    //   838: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   841: aload_0
    //   842: getfield 676	no/nordicsemi/android/dfu/DfuBaseService:mProgressInfo	Lno/nordicsemi/android/dfu/DfuProgressInfo;
    //   845: bipush 249
    //   847: invokevirtual 679	no/nordicsemi/android/dfu/DfuProgressInfo:setProgress	(I)V
    //   850: iload 8
    //   852: ifeq +9 -> 861
    //   855: aload_0
    //   856: iload 7
    //   858: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   861: return
    //   862: aload_0
    //   863: iconst_1
    //   864: ldc_w 889
    //   867: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   870: aload_0
    //   871: getfield 676	no/nordicsemi/android/dfu/DfuBaseService:mProgressInfo	Lno/nordicsemi/android/dfu/DfuProgressInfo;
    //   874: iconst_m1
    //   875: invokevirtual 679	no/nordicsemi/android/dfu/DfuProgressInfo:setProgress	(I)V
    //   878: aload_0
    //   879: aload 13
    //   881: invokevirtual 891	no/nordicsemi/android/dfu/DfuBaseService:connect	(Ljava/lang/String;)Landroid/bluetooth/BluetoothGatt;
    //   884: astore 14
    //   886: aload 14
    //   888: ifnonnull +38 -> 926
    //   891: aload_0
    //   892: ldc_w 893
    //   895: invokespecial 334	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;)V
    //   898: aload_0
    //   899: bipush 20
    //   901: ldc_w 893
    //   904: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   907: aload_0
    //   908: sipush 4106
    //   911: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   914: iload 8
    //   916: ifeq +9 -> 925
    //   919: aload_0
    //   920: iload 7
    //   922: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   925: return
    //   926: aload_0
    //   927: getfield 641	no/nordicsemi/android/dfu/DfuBaseService:mConnectionState	I
    //   930: ifne +101 -> 1031
    //   933: aload_0
    //   934: getfield 330	no/nordicsemi/android/dfu/DfuBaseService:mError	I
    //   937: ldc_w 894
    //   940: if_icmpne +54 -> 994
    //   943: new 896	java/lang/StringBuilder
    //   946: dup
    //   947: invokespecial 897	java/lang/StringBuilder:<init>	()V
    //   950: astore_1
    //   951: aload_1
    //   952: ldc_w 899
    //   955: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   958: pop
    //   959: aload_1
    //   960: aload 13
    //   962: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   965: pop
    //   966: aload_1
    //   967: ldc_w 905
    //   970: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   973: pop
    //   974: aload_0
    //   975: aload_1
    //   976: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   979: invokespecial 334	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;)V
    //   982: aload_0
    //   983: bipush 20
    //   985: ldc_w 911
    //   988: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   991: goto +19 -> 1010
    //   994: aload_0
    //   995: ldc_w 913
    //   998: invokespecial 334	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;)V
    //   1001: aload_0
    //   1002: bipush 20
    //   1004: ldc_w 690
    //   1007: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1010: aload_0
    //   1011: aload 14
    //   1013: sipush 4096
    //   1016: invokevirtual 917	no/nordicsemi/android/dfu/DfuBaseService:terminateConnection	(Landroid/bluetooth/BluetoothGatt;I)V
    //   1019: iload 8
    //   1021: ifeq +9 -> 1030
    //   1024: aload_0
    //   1025: iload 7
    //   1027: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   1030: return
    //   1031: aload_0
    //   1032: getfield 330	no/nordicsemi/android/dfu/DfuBaseService:mError	I
    //   1035: ifle +288 -> 1323
    //   1038: aload_0
    //   1039: getfield 330	no/nordicsemi/android/dfu/DfuBaseService:mError	I
    //   1042: ldc 44
    //   1044: iand
    //   1045: ifle +82 -> 1127
    //   1048: aload_0
    //   1049: getfield 330	no/nordicsemi/android/dfu/DfuBaseService:mError	I
    //   1052: ldc_w 576
    //   1055: iand
    //   1056: istore_2
    //   1057: new 896	java/lang/StringBuilder
    //   1060: dup
    //   1061: invokespecial 897	java/lang/StringBuilder:<init>	()V
    //   1064: astore 9
    //   1066: aload 9
    //   1068: ldc_w 919
    //   1071: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1074: pop
    //   1075: aload 9
    //   1077: iload_2
    //   1078: invokevirtual 922	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1081: pop
    //   1082: aload_0
    //   1083: aload 9
    //   1085: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1088: invokespecial 334	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;)V
    //   1091: aload_0
    //   1092: bipush 20
    //   1094: getstatic 457	java/util/Locale:US	Ljava/util/Locale;
    //   1097: ldc_w 924
    //   1100: iconst_2
    //   1101: anewarray 282	java/lang/Object
    //   1104: dup
    //   1105: iconst_0
    //   1106: iload_2
    //   1107: invokestatic 927	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1110: aastore
    //   1111: dup
    //   1112: iconst_1
    //   1113: iload_2
    //   1114: invokestatic 932	no/nordicsemi/android/error/GattError:parseConnectionError	(I)Ljava/lang/String;
    //   1117: aastore
    //   1118: invokestatic 936	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1121: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1124: goto +79 -> 1203
    //   1127: aload_0
    //   1128: getfield 330	no/nordicsemi/android/dfu/DfuBaseService:mError	I
    //   1131: sipush -16385
    //   1134: iand
    //   1135: istore_2
    //   1136: new 896	java/lang/StringBuilder
    //   1139: dup
    //   1140: invokespecial 897	java/lang/StringBuilder:<init>	()V
    //   1143: astore 9
    //   1145: aload 9
    //   1147: ldc_w 938
    //   1150: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1153: pop
    //   1154: aload 9
    //   1156: iload_2
    //   1157: invokevirtual 922	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1160: pop
    //   1161: aload_0
    //   1162: aload 9
    //   1164: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1167: invokespecial 334	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;)V
    //   1170: aload_0
    //   1171: bipush 20
    //   1173: getstatic 457	java/util/Locale:US	Ljava/util/Locale;
    //   1176: ldc_w 924
    //   1179: iconst_2
    //   1180: anewarray 282	java/lang/Object
    //   1183: dup
    //   1184: iconst_0
    //   1185: iload_2
    //   1186: invokestatic 927	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1189: aastore
    //   1190: dup
    //   1191: iconst_1
    //   1192: iload_2
    //   1193: invokestatic 941	no/nordicsemi/android/error/GattError:parse	(I)Ljava/lang/String;
    //   1196: aastore
    //   1197: invokestatic 936	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1200: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1203: aload_1
    //   1204: ldc 92
    //   1206: iconst_0
    //   1207: invokevirtual 776	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   1210: ifne +91 -> 1301
    //   1213: aload_0
    //   1214: bipush 15
    //   1216: ldc_w 943
    //   1219: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1222: aload_0
    //   1223: getfield 641	no/nordicsemi/android/dfu/DfuBaseService:mConnectionState	I
    //   1226: ifeq +9 -> 1235
    //   1229: aload_0
    //   1230: aload 14
    //   1232: invokevirtual 945	no/nordicsemi/android/dfu/DfuBaseService:disconnect	(Landroid/bluetooth/BluetoothGatt;)V
    //   1235: aload_0
    //   1236: aload 14
    //   1238: iconst_1
    //   1239: invokevirtual 949	no/nordicsemi/android/dfu/DfuBaseService:refreshDeviceCache	(Landroid/bluetooth/BluetoothGatt;Z)V
    //   1242: aload_0
    //   1243: aload 14
    //   1245: invokevirtual 951	no/nordicsemi/android/dfu/DfuBaseService:close	(Landroid/bluetooth/BluetoothGatt;)V
    //   1248: aload_0
    //   1249: ldc_w 953
    //   1252: invokespecial 310	no/nordicsemi/android/dfu/DfuBaseService:logi	(Ljava/lang/String;)V
    //   1255: new 528	android/content/Intent
    //   1258: dup
    //   1259: invokespecial 954	android/content/Intent:<init>	()V
    //   1262: astore 9
    //   1264: aload 9
    //   1266: aload_1
    //   1267: bipush 24
    //   1269: invokevirtual 958	android/content/Intent:fillIn	(Landroid/content/Intent;I)I
    //   1272: pop
    //   1273: aload 9
    //   1275: ldc 92
    //   1277: iconst_1
    //   1278: invokevirtual 547	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   1281: pop
    //   1282: aload_0
    //   1283: aload 9
    //   1285: invokevirtual 962	no/nordicsemi/android/dfu/DfuBaseService:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   1288: pop
    //   1289: iload 8
    //   1291: ifeq +9 -> 1300
    //   1294: aload_0
    //   1295: iload 7
    //   1297: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   1300: return
    //   1301: aload_0
    //   1302: aload 14
    //   1304: aload_0
    //   1305: getfield 330	no/nordicsemi/android/dfu/DfuBaseService:mError	I
    //   1308: invokevirtual 917	no/nordicsemi/android/dfu/DfuBaseService:terminateConnection	(Landroid/bluetooth/BluetoothGatt;I)V
    //   1311: iload 8
    //   1313: ifeq +9 -> 1322
    //   1316: aload_0
    //   1317: iload 7
    //   1319: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   1322: return
    //   1323: aload_0
    //   1324: getfield 318	no/nordicsemi/android/dfu/DfuBaseService:mAborted	Z
    //   1327: ifeq +47 -> 1374
    //   1330: aload_0
    //   1331: ldc_w 883
    //   1334: invokespecial 322	no/nordicsemi/android/dfu/DfuBaseService:logw	(Ljava/lang/String;)V
    //   1337: aload_0
    //   1338: bipush 15
    //   1340: ldc_w 883
    //   1343: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1346: aload_0
    //   1347: aload 14
    //   1349: iconst_0
    //   1350: invokevirtual 917	no/nordicsemi/android/dfu/DfuBaseService:terminateConnection	(Landroid/bluetooth/BluetoothGatt;I)V
    //   1353: aload_0
    //   1354: getfield 676	no/nordicsemi/android/dfu/DfuBaseService:mProgressInfo	Lno/nordicsemi/android/dfu/DfuProgressInfo;
    //   1357: bipush 249
    //   1359: invokevirtual 679	no/nordicsemi/android/dfu/DfuProgressInfo:setProgress	(I)V
    //   1362: iload 8
    //   1364: ifeq +9 -> 1373
    //   1367: aload_0
    //   1368: iload 7
    //   1370: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   1373: return
    //   1374: aload_0
    //   1375: iconst_5
    //   1376: ldc_w 964
    //   1379: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1382: aload_1
    //   1383: ldc 92
    //   1385: iconst_0
    //   1386: invokevirtual 547	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   1389: pop
    //   1390: aconst_null
    //   1391: astore 13
    //   1393: aconst_null
    //   1394: astore 9
    //   1396: aload 9
    //   1398: astore 10
    //   1400: new 966	no/nordicsemi/android/dfu/DfuServiceProvider
    //   1403: dup
    //   1404: invokespecial 967	no/nordicsemi/android/dfu/DfuServiceProvider:<init>	()V
    //   1407: astore 15
    //   1409: aload 9
    //   1411: astore 10
    //   1413: aload_0
    //   1414: aload 15
    //   1416: putfield 314	no/nordicsemi/android/dfu/DfuBaseService:mDfuServiceImpl	Lno/nordicsemi/android/dfu/DfuCallback;
    //   1419: aload 9
    //   1421: astore 10
    //   1423: aload 15
    //   1425: aload_1
    //   1426: aload_0
    //   1427: aload 14
    //   1429: invokevirtual 971	no/nordicsemi/android/dfu/DfuServiceProvider:getServiceImpl	(Landroid/content/Intent;Lno/nordicsemi/android/dfu/DfuBaseService;Landroid/bluetooth/BluetoothGatt;)Lno/nordicsemi/android/dfu/DfuService;
    //   1432: astore 9
    //   1434: aload 9
    //   1436: astore 10
    //   1438: aload_0
    //   1439: aload 9
    //   1441: putfield 314	no/nordicsemi/android/dfu/DfuBaseService:mDfuServiceImpl	Lno/nordicsemi/android/dfu/DfuCallback;
    //   1444: aload 9
    //   1446: ifnonnull +66 -> 1512
    //   1449: aload 9
    //   1451: astore 10
    //   1453: ldc 241
    //   1455: ldc_w 973
    //   1458: invokestatic 374	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   1461: pop
    //   1462: aload 9
    //   1464: astore 10
    //   1466: aload_0
    //   1467: bipush 15
    //   1469: ldc_w 975
    //   1472: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1475: aload 9
    //   1477: astore 10
    //   1479: aload_0
    //   1480: aload 14
    //   1482: sipush 4102
    //   1485: invokevirtual 917	no/nordicsemi/android/dfu/DfuBaseService:terminateConnection	(Landroid/bluetooth/BluetoothGatt;I)V
    //   1488: aload 9
    //   1490: ifnull +10 -> 1500
    //   1493: aload 9
    //   1495: invokeinterface 980 1 0
    //   1500: iload 8
    //   1502: ifeq +9 -> 1511
    //   1505: aload_0
    //   1506: iload 7
    //   1508: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   1511: return
    //   1512: aload 9
    //   1514: astore 10
    //   1516: aload 9
    //   1518: aload_1
    //   1519: aload 14
    //   1521: iload_2
    //   1522: aload 12
    //   1524: aload 11
    //   1526: invokeinterface 983 6 0
    //   1531: ifeq +15 -> 1546
    //   1534: aload 9
    //   1536: astore 10
    //   1538: aload 9
    //   1540: aload_1
    //   1541: invokeinterface 986 2 0
    //   1546: aload 9
    //   1548: ifnull +286 -> 1834
    //   1551: aload 9
    //   1553: invokeinterface 980 1 0
    //   1558: goto +276 -> 1834
    //   1561: astore_1
    //   1562: goto +21 -> 1583
    //   1565: astore_1
    //   1566: goto +156 -> 1722
    //   1569: aload 9
    //   1571: astore_1
    //   1572: goto +208 -> 1780
    //   1575: astore_1
    //   1576: goto +270 -> 1846
    //   1579: astore_1
    //   1580: aconst_null
    //   1581: astore 9
    //   1583: aload 9
    //   1585: astore 10
    //   1587: aload_1
    //   1588: invokevirtual 989	no/nordicsemi/android/dfu/internal/exception/DfuException:getErrorNumber	()I
    //   1591: istore_2
    //   1592: iload_2
    //   1593: ldc 44
    //   1595: iand
    //   1596: ifle +49 -> 1645
    //   1599: iload_2
    //   1600: ldc_w 576
    //   1603: iand
    //   1604: istore_2
    //   1605: aload 9
    //   1607: astore 10
    //   1609: aload_0
    //   1610: bipush 20
    //   1612: getstatic 457	java/util/Locale:US	Ljava/util/Locale;
    //   1615: ldc_w 991
    //   1618: iconst_2
    //   1619: anewarray 282	java/lang/Object
    //   1622: dup
    //   1623: iconst_0
    //   1624: iload_2
    //   1625: invokestatic 927	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1628: aastore
    //   1629: dup
    //   1630: iconst_1
    //   1631: iload_2
    //   1632: invokestatic 932	no/nordicsemi/android/error/GattError:parseConnectionError	(I)Ljava/lang/String;
    //   1635: aastore
    //   1636: invokestatic 936	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1639: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1642: goto +46 -> 1688
    //   1645: iload_2
    //   1646: sipush -16385
    //   1649: iand
    //   1650: istore_2
    //   1651: aload 9
    //   1653: astore 10
    //   1655: aload_0
    //   1656: bipush 20
    //   1658: getstatic 457	java/util/Locale:US	Ljava/util/Locale;
    //   1661: ldc_w 991
    //   1664: iconst_2
    //   1665: anewarray 282	java/lang/Object
    //   1668: dup
    //   1669: iconst_0
    //   1670: iload_2
    //   1671: invokestatic 927	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1674: aastore
    //   1675: dup
    //   1676: iconst_1
    //   1677: iload_2
    //   1678: invokestatic 941	no/nordicsemi/android/error/GattError:parse	(I)Ljava/lang/String;
    //   1681: aastore
    //   1682: invokestatic 936	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1685: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1688: aload 9
    //   1690: astore 10
    //   1692: aload_0
    //   1693: aload_1
    //   1694: invokevirtual 994	no/nordicsemi/android/dfu/internal/exception/DfuException:getMessage	()Ljava/lang/String;
    //   1697: invokespecial 334	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;)V
    //   1700: aload 9
    //   1702: astore 10
    //   1704: aload_0
    //   1705: aload 14
    //   1707: aload_1
    //   1708: invokevirtual 989	no/nordicsemi/android/dfu/internal/exception/DfuException:getErrorNumber	()I
    //   1711: invokevirtual 917	no/nordicsemi/android/dfu/DfuBaseService:terminateConnection	(Landroid/bluetooth/BluetoothGatt;I)V
    //   1714: aload 9
    //   1716: ifnull +118 -> 1834
    //   1719: goto -168 -> 1551
    //   1722: aload 9
    //   1724: astore 10
    //   1726: aload_0
    //   1727: bipush 20
    //   1729: ldc_w 996
    //   1732: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1735: aload 9
    //   1737: astore 10
    //   1739: aload_0
    //   1740: aload_1
    //   1741: invokevirtual 997	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException:getMessage	()Ljava/lang/String;
    //   1744: invokespecial 334	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;)V
    //   1747: aload 9
    //   1749: astore 10
    //   1751: aload_0
    //   1752: aload 14
    //   1754: invokevirtual 951	no/nordicsemi/android/dfu/DfuBaseService:close	(Landroid/bluetooth/BluetoothGatt;)V
    //   1757: aload 9
    //   1759: astore 10
    //   1761: aload_0
    //   1762: sipush 4096
    //   1765: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   1768: aload 9
    //   1770: ifnull +64 -> 1834
    //   1773: goto -222 -> 1551
    //   1776: astore_1
    //   1777: goto -201 -> 1576
    //   1780: aload_1
    //   1781: astore 10
    //   1783: aload_0
    //   1784: ldc_w 883
    //   1787: invokespecial 322	no/nordicsemi/android/dfu/DfuBaseService:logw	(Ljava/lang/String;)V
    //   1790: aload_1
    //   1791: astore 10
    //   1793: aload_0
    //   1794: bipush 15
    //   1796: ldc_w 883
    //   1799: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1802: aload_1
    //   1803: astore 10
    //   1805: aload_0
    //   1806: aload 14
    //   1808: iconst_0
    //   1809: invokevirtual 917	no/nordicsemi/android/dfu/DfuBaseService:terminateConnection	(Landroid/bluetooth/BluetoothGatt;I)V
    //   1812: aload_1
    //   1813: astore 10
    //   1815: aload_0
    //   1816: getfield 676	no/nordicsemi/android/dfu/DfuBaseService:mProgressInfo	Lno/nordicsemi/android/dfu/DfuProgressInfo;
    //   1819: bipush 249
    //   1821: invokevirtual 679	no/nordicsemi/android/dfu/DfuProgressInfo:setProgress	(I)V
    //   1824: aload_1
    //   1825: ifnull +9 -> 1834
    //   1828: aload_1
    //   1829: invokeinterface 980 1 0
    //   1834: iload 8
    //   1836: ifeq +9 -> 1845
    //   1839: aload_0
    //   1840: iload 7
    //   1842: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   1845: return
    //   1846: aload 10
    //   1848: ifnull +10 -> 1858
    //   1851: aload 10
    //   1853: invokeinterface 980 1 0
    //   1858: aload_1
    //   1859: athrow
    //   1860: aload_0
    //   1861: ldc_w 999
    //   1864: aload 9
    //   1866: invokespecial 671	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1869: new 896	java/lang/StringBuilder
    //   1872: dup
    //   1873: invokespecial 897	java/lang/StringBuilder:<init>	()V
    //   1876: astore_1
    //   1877: aload_1
    //   1878: ldc_w 1001
    //   1881: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1884: pop
    //   1885: aload_1
    //   1886: aload 9
    //   1888: invokevirtual 1004	java/lang/Exception:getLocalizedMessage	()Ljava/lang/String;
    //   1891: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1894: pop
    //   1895: aload_0
    //   1896: bipush 20
    //   1898: aload_1
    //   1899: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1902: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1905: aload_0
    //   1906: sipush 4098
    //   1909: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   1912: iload 8
    //   1914: ifeq +9 -> 1923
    //   1917: aload_0
    //   1918: iload 7
    //   1920: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   1923: return
    //   1924: aload_0
    //   1925: ldc_w 1006
    //   1928: aload 9
    //   1930: invokespecial 671	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1933: new 896	java/lang/StringBuilder
    //   1936: dup
    //   1937: invokespecial 897	java/lang/StringBuilder:<init>	()V
    //   1940: astore_1
    //   1941: aload_1
    //   1942: ldc_w 1001
    //   1945: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1948: pop
    //   1949: aload_1
    //   1950: aload 9
    //   1952: invokevirtual 1007	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   1955: invokevirtual 903	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1958: pop
    //   1959: aload_0
    //   1960: bipush 20
    //   1962: aload_1
    //   1963: invokevirtual 909	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1966: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   1969: aload_0
    //   1970: sipush 4098
    //   1973: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   1976: iload 8
    //   1978: ifeq +9 -> 1987
    //   1981: aload_0
    //   1982: iload 7
    //   1984: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   1987: return
    //   1988: aload_0
    //   1989: ldc_w 1009
    //   1992: aload_1
    //   1993: invokespecial 671	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   1996: aload_0
    //   1997: bipush 20
    //   1999: ldc_w 1011
    //   2002: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   2005: aload_0
    //   2006: sipush 4108
    //   2009: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   2012: iload 8
    //   2014: ifeq +9 -> 2023
    //   2017: aload_0
    //   2018: iload 7
    //   2020: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   2023: return
    //   2024: aload_0
    //   2025: ldc_w 1013
    //   2028: aload_1
    //   2029: invokespecial 671	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   2032: aload_0
    //   2033: bipush 20
    //   2035: ldc_w 1015
    //   2038: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   2041: aload_0
    //   2042: sipush 4097
    //   2045: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   2048: iload 8
    //   2050: ifeq +9 -> 2059
    //   2053: aload_0
    //   2054: iload 7
    //   2056: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   2059: return
    //   2060: aload_0
    //   2061: ldc_w 1017
    //   2064: aload_1
    //   2065: invokespecial 671	no/nordicsemi/android/dfu/DfuBaseService:loge	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   2068: aload_0
    //   2069: bipush 20
    //   2071: ldc_w 1019
    //   2074: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   2077: aload_0
    //   2078: sipush 4097
    //   2081: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   2084: iload 8
    //   2086: ifeq +9 -> 2095
    //   2089: aload_0
    //   2090: iload 7
    //   2092: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   2095: return
    //   2096: astore_1
    //   2097: iload 8
    //   2099: ifeq +9 -> 2108
    //   2102: aload_0
    //   2103: iload 7
    //   2105: invokevirtual 887	no/nordicsemi/android/dfu/DfuBaseService:stopForeground	(Z)V
    //   2108: aload_1
    //   2109: athrow
    //   2110: aload_0
    //   2111: ldc_w 1021
    //   2114: invokespecial 322	no/nordicsemi/android/dfu/DfuBaseService:logw	(Ljava/lang/String;)V
    //   2117: aload_0
    //   2118: bipush 15
    //   2120: ldc_w 1021
    //   2123: invokevirtual 636	no/nordicsemi/android/dfu/DfuBaseService:sendLogBroadcast	(ILjava/lang/String;)V
    //   2126: aload_0
    //   2127: sipush 4105
    //   2130: invokespecial 782	no/nordicsemi/android/dfu/DfuBaseService:report	(I)V
    //   2133: return
    //   2134: astore 9
    //   2136: goto -1776 -> 360
    //   2139: astore_1
    //   2140: aload 13
    //   2142: astore_1
    //   2143: goto -363 -> 1780
    //   2146: astore_1
    //   2147: goto -578 -> 1569
    //   2150: goto -1664 -> 486
    //   2153: goto -1610 -> 543
    //   2156: goto -1613 -> 543
    //   2159: aload 9
    //   2161: astore 12
    //   2163: aload 10
    //   2165: astore 9
    //   2167: goto -1603 -> 564
    //   2170: astore 9
    //   2172: goto -312 -> 1860
    //   2175: astore 9
    //   2177: goto -253 -> 1924
    //   2180: astore_1
    //   2181: goto -193 -> 1988
    //   2184: astore_1
    //   2185: goto -161 -> 2024
    //   2188: astore_1
    //   2189: goto -129 -> 2060
    //   2192: aload 9
    //   2194: astore 12
    //   2196: aload 10
    //   2198: astore 9
    //   2200: goto -1636 -> 564
    //   2203: aload 9
    //   2205: astore 11
    //   2207: goto -1470 -> 737
    //   2210: goto +3 -> 2213
    //   2213: aload 9
    //   2215: astore 11
    //   2217: goto -1480 -> 737
    //   2220: astore_1
    //   2221: aconst_null
    //   2222: astore 9
    //   2224: goto -502 -> 1722
    //
    // Exception table:
    //   from	to	target	type
    //   1438	1444	1561	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1453	1462	1561	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1466	1475	1561	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1479	1488	1561	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1516	1534	1561	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1538	1546	1561	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1438	1444	1565	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   1453	1462	1565	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   1466	1475	1565	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   1479	1488	1565	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   1516	1534	1565	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   1538	1546	1565	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   1400	1409	1575	finally
    //   1413	1419	1575	finally
    //   1423	1434	1575	finally
    //   1783	1790	1575	finally
    //   1793	1802	1575	finally
    //   1805	1812	1575	finally
    //   1815	1824	1575	finally
    //   1400	1409	1579	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1413	1419	1579	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1423	1434	1579	no/nordicsemi/android/dfu/internal/exception/DfuException
    //   1438	1444	1776	finally
    //   1453	1462	1776	finally
    //   1466	1475	1776	finally
    //   1479	1488	1776	finally
    //   1516	1534	1776	finally
    //   1538	1546	1776	finally
    //   1587	1592	1776	finally
    //   1609	1642	1776	finally
    //   1655	1688	1776	finally
    //   1692	1700	1776	finally
    //   1704	1714	1776	finally
    //   1726	1735	1776	finally
    //   1739	1747	1776	finally
    //   1751	1757	1776	finally
    //   1761	1768	1776	finally
    //   393	399	2096	finally
    //   418	426	2096	finally
    //   431	443	2096	finally
    //   451	463	2096	finally
    //   471	483	2096	finally
    //   491	502	2096	finally
    //   510	521	2096	finally
    //   529	540	2096	finally
    //   543	564	2096	finally
    //   564	581	2096	finally
    //   585	591	2096	finally
    //   594	601	2096	finally
    //   607	628	2096	finally
    //   634	655	2096	finally
    //   661	682	2096	finally
    //   687	709	2096	finally
    //   712	734	2096	finally
    //   742	752	2096	finally
    //   757	767	2096	finally
    //   767	787	2096	finally
    //   792	806	2096	finally
    //   806	850	2096	finally
    //   862	886	2096	finally
    //   891	914	2096	finally
    //   926	991	2096	finally
    //   994	1010	2096	finally
    //   1010	1019	2096	finally
    //   1031	1124	2096	finally
    //   1127	1203	2096	finally
    //   1203	1235	2096	finally
    //   1235	1289	2096	finally
    //   1301	1311	2096	finally
    //   1323	1362	2096	finally
    //   1374	1390	2096	finally
    //   1493	1500	2096	finally
    //   1551	1558	2096	finally
    //   1828	1834	2096	finally
    //   1851	1858	2096	finally
    //   1858	1860	2096	finally
    //   1860	1912	2096	finally
    //   1924	1976	2096	finally
    //   1988	2012	2096	finally
    //   2024	2048	2096	finally
    //   2060	2084	2096	finally
    //   340	347	2134	java/lang/NumberFormatException
    //   1400	1409	2139	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   1413	1419	2139	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   1423	1434	2139	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   1438	1444	2146	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   1453	1462	2146	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   1466	1475	2146	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   1479	1488	2146	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   1516	1534	2146	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   1538	1546	2146	no/nordicsemi/android/dfu/internal/exception/UploadAbortedException
    //   418	426	2170	java/lang/Exception
    //   431	443	2170	java/lang/Exception
    //   451	463	2170	java/lang/Exception
    //   471	483	2170	java/lang/Exception
    //   491	502	2170	java/lang/Exception
    //   510	521	2170	java/lang/Exception
    //   529	540	2170	java/lang/Exception
    //   543	564	2170	java/lang/Exception
    //   564	581	2170	java/lang/Exception
    //   585	591	2170	java/lang/Exception
    //   594	601	2170	java/lang/Exception
    //   607	628	2170	java/lang/Exception
    //   634	655	2170	java/lang/Exception
    //   661	682	2170	java/lang/Exception
    //   687	709	2170	java/lang/Exception
    //   712	734	2170	java/lang/Exception
    //   742	752	2170	java/lang/Exception
    //   757	767	2170	java/lang/Exception
    //   767	787	2170	java/lang/Exception
    //   418	426	2175	java/io/IOException
    //   431	443	2175	java/io/IOException
    //   451	463	2175	java/io/IOException
    //   471	483	2175	java/io/IOException
    //   491	502	2175	java/io/IOException
    //   510	521	2175	java/io/IOException
    //   529	540	2175	java/io/IOException
    //   543	564	2175	java/io/IOException
    //   564	581	2175	java/io/IOException
    //   585	591	2175	java/io/IOException
    //   594	601	2175	java/io/IOException
    //   607	628	2175	java/io/IOException
    //   634	655	2175	java/io/IOException
    //   661	682	2175	java/io/IOException
    //   687	709	2175	java/io/IOException
    //   712	734	2175	java/io/IOException
    //   742	752	2175	java/io/IOException
    //   757	767	2175	java/io/IOException
    //   767	787	2175	java/io/IOException
    //   418	426	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   431	443	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   451	463	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   471	483	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   491	502	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   510	521	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   529	540	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   543	564	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   564	581	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   585	591	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   594	601	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   607	628	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   634	655	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   661	682	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   687	709	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   712	734	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   742	752	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   757	767	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   767	787	2180	no/nordicsemi/android/dfu/internal/exception/SizeValidationException
    //   418	426	2184	java/io/FileNotFoundException
    //   431	443	2184	java/io/FileNotFoundException
    //   451	463	2184	java/io/FileNotFoundException
    //   471	483	2184	java/io/FileNotFoundException
    //   491	502	2184	java/io/FileNotFoundException
    //   510	521	2184	java/io/FileNotFoundException
    //   529	540	2184	java/io/FileNotFoundException
    //   543	564	2184	java/io/FileNotFoundException
    //   564	581	2184	java/io/FileNotFoundException
    //   585	591	2184	java/io/FileNotFoundException
    //   594	601	2184	java/io/FileNotFoundException
    //   607	628	2184	java/io/FileNotFoundException
    //   634	655	2184	java/io/FileNotFoundException
    //   661	682	2184	java/io/FileNotFoundException
    //   687	709	2184	java/io/FileNotFoundException
    //   712	734	2184	java/io/FileNotFoundException
    //   742	752	2184	java/io/FileNotFoundException
    //   757	767	2184	java/io/FileNotFoundException
    //   767	787	2184	java/io/FileNotFoundException
    //   418	426	2188	java/lang/SecurityException
    //   431	443	2188	java/lang/SecurityException
    //   451	463	2188	java/lang/SecurityException
    //   471	483	2188	java/lang/SecurityException
    //   491	502	2188	java/lang/SecurityException
    //   510	521	2188	java/lang/SecurityException
    //   529	540	2188	java/lang/SecurityException
    //   543	564	2188	java/lang/SecurityException
    //   564	581	2188	java/lang/SecurityException
    //   585	591	2188	java/lang/SecurityException
    //   594	601	2188	java/lang/SecurityException
    //   607	628	2188	java/lang/SecurityException
    //   634	655	2188	java/lang/SecurityException
    //   661	682	2188	java/lang/SecurityException
    //   687	709	2188	java/lang/SecurityException
    //   712	734	2188	java/lang/SecurityException
    //   742	752	2188	java/lang/SecurityException
    //   757	767	2188	java/lang/SecurityException
    //   767	787	2188	java/lang/SecurityException
    //   1400	1409	2220	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   1413	1419	2220	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
    //   1423	1434	2220	no/nordicsemi/android/dfu/internal/exception/DeviceDisconnectedException
  }

  public void onTaskRemoved(Intent paramIntent)
  {
    super.onTaskRemoved(paramIntent);
    ((NotificationManager)getSystemService("notification")).cancel(283);
    stopSelf();
  }

  protected void refreshDeviceCache(BluetoothGatt paramBluetoothGatt, boolean paramBoolean)
  {
    if ((paramBoolean) || (paramBluetoothGatt.getDevice().getBondState() == 10))
    {
      sendLogBroadcast(0, "gatt.refresh() (hidden)");
      try
      {
        Method localMethod = paramBluetoothGatt.getClass().getMethod("refresh", new Class[0]);
        if (localMethod != null)
        {
          paramBoolean = ((Boolean)localMethod.invoke(paramBluetoothGatt, new Object[0])).booleanValue();
          paramBluetoothGatt = new StringBuilder();
          paramBluetoothGatt.append("Refreshing result: ");
          paramBluetoothGatt.append(paramBoolean);
          logi(paramBluetoothGatt.toString());
          return;
        }
      }
      catch (Exception paramBluetoothGatt)
      {
        loge("An exception occurred while refreshing device", paramBluetoothGatt);
        sendLogBroadcast(15, "Refreshing failed");
      }
    }
  }

  void sendLogBroadcast(int paramInt, String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("[DFU] ");
    ((StringBuilder)localObject).append(paramString);
    paramString = ((StringBuilder)localObject).toString();
    localObject = new Intent("no.nordicsemi.android.dfu.broadcast.BROADCAST_LOG");
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_LOG_INFO", paramString);
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_LOG_LEVEL", paramInt);
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", this.mDeviceAddress);
    LocalBroadcastManager.getInstance(this).sendBroadcast((Intent)localObject);
  }

  protected void terminateConnection(BluetoothGatt paramBluetoothGatt, int paramInt)
  {
    if (this.mConnectionState != 0)
      disconnect(paramBluetoothGatt);
    refreshDeviceCache(paramBluetoothGatt, false);
    close(paramBluetoothGatt);
    waitFor(600);
    if (paramInt != 0)
      report(paramInt);
  }

  protected void updateErrorNotification(NotificationCompat.Builder paramBuilder)
  {
  }

  protected void updateForegroundNotification(NotificationCompat.Builder paramBuilder)
  {
  }

  public void updateProgressNotification()
  {
    Object localObject = this.mProgressInfo;
    int i = ((DfuProgressInfo)localObject).getProgress();
    if (this.mLastProgress == i)
      return;
    this.mLastProgress = i;
    sendProgressBroadcast((DfuProgressInfo)localObject);
    if (this.mDisableNotification)
      return;
    long l = SystemClock.elapsedRealtime();
    if ((l - this.mLastNotificationTime < 250L) && (-6 != i) && (-7 != i))
      return;
    this.mLastNotificationTime = l;
    String str2 = this.mDeviceAddress;
    String str1;
    if (this.mDeviceName != null)
      str1 = this.mDeviceName;
    else
      str1 = getString(R.string.dfu_unknown_name);
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this, "dfu").setSmallIcon(17301640).setOnlyAlertOnce(true);
    localBuilder.setColor(-7829368);
    switch (i)
    {
    default:
      if (((DfuProgressInfo)localObject).getTotalParts() == 1)
        localObject = getString(R.string.dfu_status_uploading);
      break;
    case -1:
      localBuilder.setOngoing(true).setContentTitle(getString(R.string.dfu_status_connecting)).setContentText(getString(R.string.dfu_status_connecting_msg, new Object[] { str1 })).setProgress(100, 0, true);
      break;
    case -2:
      localBuilder.setOngoing(true).setContentTitle(getString(R.string.dfu_status_starting)).setContentText(getString(R.string.dfu_status_starting_msg)).setProgress(100, 0, true);
      break;
    case -3:
      localBuilder.setOngoing(true).setContentTitle(getString(R.string.dfu_status_switching_to_dfu)).setContentText(getString(R.string.dfu_status_switching_to_dfu_msg)).setProgress(100, 0, true);
      break;
    case -4:
      localBuilder.setOngoing(true).setContentTitle(getString(R.string.dfu_status_validating)).setContentText(getString(R.string.dfu_status_validating_msg)).setProgress(100, 0, true);
      break;
    case -5:
      localBuilder.setOngoing(true).setContentTitle(getString(R.string.dfu_status_disconnecting)).setContentText(getString(R.string.dfu_status_disconnecting_msg, new Object[] { str1 })).setProgress(100, 0, true);
      break;
    case -6:
      localBuilder.setOngoing(false).setContentTitle(getString(R.string.dfu_status_completed)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_completed_msg)).setAutoCancel(true).setColor(-16730086);
      break;
    case -7:
      localBuilder.setOngoing(false).setContentTitle(getString(R.string.dfu_status_aborted)).setSmallIcon(17301641).setContentText(getString(R.string.dfu_status_aborted_msg)).setAutoCancel(true);
      break;
    }
    localObject = getString(R.string.dfu_status_uploading_part, new Object[] { Integer.valueOf(((DfuProgressInfo)localObject).getCurrentPart()), Integer.valueOf(((DfuProgressInfo)localObject).getTotalParts()) });
    String str3 = getString(R.string.dfu_status_uploading_msg, new Object[] { str1 });
    localBuilder.setOngoing(true).setContentTitle((CharSequence)localObject).setContentText(str3).setProgress(100, i, false);
    localObject = new Intent(this, getNotificationTarget());
    ((Intent)localObject).addFlags(268435456);
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", str2);
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME", str1);
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PROGRESS", i);
    localBuilder.setContentIntent(PendingIntent.getActivity(this, 0, (Intent)localObject, 134217728));
    updateProgressNotification(localBuilder, i);
    ((NotificationManager)getSystemService("notification")).notify(283, localBuilder.build());
  }

  protected void updateProgressNotification(NotificationCompat.Builder paramBuilder, int paramInt)
  {
    if ((paramInt != -7) && (paramInt != -6))
    {
      Object localObject = new Intent("no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION");
      ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_ACTION", 2);
      localObject = PendingIntent.getBroadcast(this, 1, (Intent)localObject, 134217728);
      paramBuilder.addAction(R.drawable.ic_action_notify_cancel, getString(R.string.dfu_action_abort), (PendingIntent)localObject);
    }
  }

  protected void waitFor(int paramInt)
  {
    try
    {
      synchronized (this.mLock)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("wait(");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(")");
        sendLogBroadcast(0, localStringBuilder.toString());
        this.mLock.wait(paramInt);
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      loge("Sleeping interrupted", localInterruptedException);
      return;
    }
  }

  protected void waitUntilDisconnected()
  {
    try
    {
      synchronized (this.mLock)
      {
        while ((this.mConnectionState != 0) && (this.mError == 0))
          this.mLock.wait();
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      loge("Sleeping interrupted", localInterruptedException);
    }
  }
}