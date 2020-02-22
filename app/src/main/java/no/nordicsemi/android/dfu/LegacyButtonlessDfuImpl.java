package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

class LegacyButtonlessDfuImpl extends BaseButtonlessDfuImpl
{
  protected static UUID DFU_CONTROL_POINT_UUID = LegacyDfuImpl.DEFAULT_DFU_CONTROL_POINT_UUID;
  protected static UUID DFU_SERVICE_UUID = LegacyDfuImpl.DEFAULT_DFU_SERVICE_UUID;
  protected static UUID DFU_VERSION_UUID = LegacyDfuImpl.DEFAULT_DFU_VERSION_UUID;
  private static final byte[] OP_CODE_ENTER_BOOTLOADER = { 1, 4 };
  private BluetoothGattCharacteristic mControlPointCharacteristic;
  private int mVersion;

  LegacyButtonlessDfuImpl(Intent paramIntent, DfuBaseService paramDfuBaseService)
  {
    super(paramIntent, paramDfuBaseService);
  }

  private String getVersionFeatures(int paramInt)
  {
    switch (paramInt)
    {
    case 2:
    case 3:
    case 4:
    default:
      return "Unknown version";
    case 8:
      return "Bootloader from SDK 9.0 or newer. Signature supported";
    case 7:
      return "Bootloader from SDK 8.0 or newer. SHA-256 used instead of CRC-16 in the Init Packet";
    case 6:
      return "Bootloader from SDK 8.0 or newer. Bond sharing supported";
    case 5:
      return "Bootloader from SDK 7.0 or newer. No bond sharing";
    case 1:
      return "Application with Legacy buttonless update from SDK 7.0 or newer";
    case 0:
    }
    return "Bootloader from SDK 6.1 or older";
  }

  private int readVersion(BluetoothGatt arg1, BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
    throws DeviceDisconnectedException, DfuException, UploadAbortedException
  {
    if (!this.mConnected)
      throw new DeviceDisconnectedException("Unable to read version number: device disconnected");
    if (this.mAborted)
      throw new UploadAbortedException();
    if (paramBluetoothGattCharacteristic == null)
      return 0;
    this.mReceivedData = null;
    this.mError = 0;
    logi("Reading DFU version number...");
    this.mService.sendLogBroadcast(1, "Reading DFU version number...");
    paramBluetoothGattCharacteristic.setValue((byte[])null);
    DfuBaseService localDfuBaseService = this.mService;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("gatt.readCharacteristic(");
    localStringBuilder.append(paramBluetoothGattCharacteristic.getUuid());
    localStringBuilder.append(")");
    localDfuBaseService.sendLogBroadcast(0, localStringBuilder.toString());
    ???.readCharacteristic(paramBluetoothGattCharacteristic);
    try
    {
      synchronized (this.mLock)
      {
        while (((this.mRequestCompleted) && (paramBluetoothGattCharacteristic.getValue() != null)) || (((this.mConnected) && (this.mError == 0) && (!this.mAborted)) || (this.mPaused)))
        {
          this.mRequestCompleted = false;
          this.mLock.wait();
        }
      }
    }
    catch (InterruptedException )
    {
      loge("Sleeping interrupted", ???);
      if (this.mError != 0)
        throw new DfuException("Unable to read version number", this.mError);
      if (!this.mConnected)
        throw new DeviceDisconnectedException("Unable to read version number: device disconnected");
    }
    return paramBluetoothGattCharacteristic.getIntValue(18, 0).intValue();
  }

  public boolean isClientCompatible(Intent paramIntent, BluetoothGatt paramBluetoothGatt)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException
  {
    Object localObject1 = paramBluetoothGatt.getService(DFU_SERVICE_UUID);
    boolean bool3 = false;
    if (localObject1 == null)
      return false;
    Object localObject2 = ((BluetoothGattService)localObject1).getCharacteristic(DFU_CONTROL_POINT_UUID);
    if (localObject2 != null)
    {
      if (((BluetoothGattCharacteristic)localObject2).getDescriptor(CLIENT_CHARACTERISTIC_CONFIG) == null)
        return false;
      this.mControlPointCharacteristic = ((BluetoothGattCharacteristic)localObject2);
      this.mProgressInfo.setProgress(-2);
      this.mService.waitFor(1000);
      localObject1 = ((BluetoothGattService)localObject1).getCharacteristic(DFU_VERSION_UUID);
      int i;
      int j;
      if (localObject1 != null)
      {
        i = readVersion(paramBluetoothGatt, (BluetoothGattCharacteristic)localObject1);
        this.mVersion = i;
        j = i & 0xF;
        int k = i >> 8;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Version number read: ");
        ((StringBuilder)localObject1).append(k);
        ((StringBuilder)localObject1).append(".");
        ((StringBuilder)localObject1).append(j);
        ((StringBuilder)localObject1).append(" -> ");
        ((StringBuilder)localObject1).append(getVersionFeatures(i));
        logi(((StringBuilder)localObject1).toString());
        localObject1 = this.mService;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Version number read: ");
        ((StringBuilder)localObject2).append(k);
        ((StringBuilder)localObject2).append(".");
        ((StringBuilder)localObject2).append(j);
        ((DfuBaseService)localObject1).sendLogBroadcast(10, ((StringBuilder)localObject2).toString());
      }
      else
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("No DFU Version characteristic found -> ");
        ((StringBuilder)localObject1).append(getVersionFeatures(0));
        logi(((StringBuilder)localObject1).toString());
        this.mService.sendLogBroadcast(10, "DFU Version characteristic not found");
        i = 0;
      }
      boolean bool1 = PreferenceManager.getDefaultSharedPreferences(this.mService).getBoolean("settings_assume_dfu_mode", false);
      if (paramIntent.hasExtra("no.nordicsemi.android.dfu.extra.EXTRA_FORCE_DFU"))
        bool1 = paramIntent.getBooleanExtra("no.nordicsemi.android.dfu.extra.EXTRA_FORCE_DFU", false);
      if (paramBluetoothGatt.getServices().size() > 3)
        j = 1;
      else
        j = 0;
      if ((i == 0) && (j != 0))
        logi("Additional services found -> Bootloader from SDK 6.1. Updating SD and BL supported, extended init packet not supported");
      boolean bool2;
      if (i != 1)
      {
        bool2 = bool3;
        if (!bool1)
        {
          bool2 = bool3;
          if (i == 0)
          {
            bool2 = bool3;
            if (j == 0);
          }
        }
      }
      else
      {
        bool2 = true;
      }
      return bool2;
    }
    return false;
  }

  public void performDfu(Intent paramIntent)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException
  {
    logw("Application with legacy buttonless update found");
    this.mService.sendLogBroadcast(15, "Application with buttonless update found");
    Object localObject = this.mService;
    boolean bool = true;
    ((DfuBaseService)localObject).sendLogBroadcast(1, "Jumping to the DFU Bootloader...");
    enableCCCD(this.mControlPointCharacteristic, 1);
    this.mService.sendLogBroadcast(10, "Notifications enabled");
    this.mService.waitFor(1000);
    this.mProgressInfo.setProgress(-3);
    logi("Sending Start DFU command (Op Code = 1, Upload Mode = 4)");
    writeOpCode(this.mControlPointCharacteristic, OP_CODE_ENTER_BOOTLOADER, true);
    this.mService.sendLogBroadcast(10, "Jump to bootloader sent (Op Code = 1, Upload Mode = 4)");
    this.mService.waitUntilDisconnected();
    this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
    localObject = this.mGatt;
    BluetoothGattService localBluetoothGattService = ((BluetoothGatt)localObject).getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
    int i;
    if ((localBluetoothGattService != null) && (localBluetoothGattService.getCharacteristic(SERVICE_CHANGED_UUID) != null))
      i = 1;
    else
      i = 0;
    this.mService.refreshDeviceCache((BluetoothGatt)localObject, i ^ 0x1);
    this.mService.close((BluetoothGatt)localObject);
    logi("Starting service that will connect to the DFU bootloader");
    localObject = new Intent();
    ((Intent)localObject).fillIn(paramIntent, 24);
    if (this.mVersion != 0)
      bool = false;
    restartService((Intent)localObject, bool);
  }
}