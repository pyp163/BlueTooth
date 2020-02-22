package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.zip.CRC32;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.HexFileValidationException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

abstract class BaseCustomDfuImpl extends BaseDfuImpl
{
  protected boolean mFirmwareUploadInProgress;
  private boolean mInitPacketInProgress;
  protected int mPacketsBeforeNotification;
  protected int mPacketsSentSinceNotification;
  protected boolean mRemoteErrorOccurred;

  BaseCustomDfuImpl(Intent paramIntent, DfuBaseService paramDfuBaseService)
  {
    super(paramIntent, paramDfuBaseService);
    boolean bool3 = paramIntent.hasExtra("no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED");
    boolean bool2 = true;
    boolean bool1 = true;
    int j;
    int i;
    if (bool3)
    {
      if (Build.VERSION.SDK_INT >= 23)
        bool1 = false;
      bool1 = paramIntent.getBooleanExtra("no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED", bool1);
      j = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE", 12);
      if (j >= 0)
      {
        i = j;
        if (j <= 65535);
      }
      else
      {
        i = 12;
      }
      if (!bool1)
        i = 0;
      this.mPacketsBeforeNotification = i;
      return;
    }
    paramIntent = PreferenceManager.getDefaultSharedPreferences(paramDfuBaseService);
    if (Build.VERSION.SDK_INT < 23)
      bool1 = bool2;
    else
      bool1 = false;
    bool1 = paramIntent.getBoolean("settings_packet_receipt_notification_enabled", bool1);
    paramIntent = paramIntent.getString("settings_number_of_packets", String.valueOf(12));
    try
    {
      j = Integer.parseInt(paramIntent);
      if (j >= 0)
      {
        i = j;
        if (j <= 65535);
      }
      else
      {
        label160: i = 12;
      }
      if (!bool1)
        i = 0;
      this.mPacketsBeforeNotification = i;
      return;
    }
    catch (NumberFormatException paramIntent)
    {
      break label160;
    }
  }

  private void writeInitPacket(BluetoothGattCharacteristic arg1, byte[] paramArrayOfByte, int paramInt)
    throws DeviceDisconnectedException, DfuException, UploadAbortedException
  {
    if (this.mAborted)
      throw new UploadAbortedException();
    Object localObject = paramArrayOfByte;
    if (paramArrayOfByte.length != paramInt)
    {
      localObject = new byte[paramInt];
      System.arraycopy(paramArrayOfByte, 0, localObject, 0, paramInt);
    }
    this.mReceivedData = null;
    this.mError = 0;
    this.mInitPacketInProgress = true;
    ???.setWriteType(1);
    ???.setValue((byte[])localObject);
    paramArrayOfByte = new StringBuilder();
    paramArrayOfByte.append("Sending init packet (Value = ");
    paramArrayOfByte.append(parse((byte[])localObject));
    paramArrayOfByte.append(")");
    logi(paramArrayOfByte.toString());
    paramArrayOfByte = this.mService;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Writing to characteristic ");
    ((StringBuilder)localObject).append(???.getUuid());
    paramArrayOfByte.sendLogBroadcast(1, ((StringBuilder)localObject).toString());
    paramArrayOfByte = this.mService;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("gatt.writeCharacteristic(");
    ((StringBuilder)localObject).append(???.getUuid());
    ((StringBuilder)localObject).append(")");
    paramArrayOfByte.sendLogBroadcast(0, ((StringBuilder)localObject).toString());
    this.mGatt.writeCharacteristic(???);
    try
    {
      synchronized (this.mLock)
      {
        while (((this.mInitPacketInProgress) && (this.mConnected) && (this.mError == 0)) || (this.mPaused))
          this.mLock.wait();
      }
    }
    catch (InterruptedException )
    {
      loge("Sleeping interrupted", ???);
      if (this.mError != 0)
        throw new DfuException("Unable to write Init DFU Parameters", this.mError);
      if (!this.mConnected)
        throw new DeviceDisconnectedException("Unable to write Init DFU Parameters: device disconnected");
    }
  }

  private void writePacket(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt <= 0)
      return;
    byte[] arrayOfByte = paramArrayOfByte;
    if (paramArrayOfByte.length != paramInt)
    {
      arrayOfByte = new byte[paramInt];
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramInt);
    }
    paramBluetoothGattCharacteristic.setWriteType(1);
    paramBluetoothGattCharacteristic.setValue(arrayOfByte);
    paramBluetoothGatt.writeCharacteristic(paramBluetoothGattCharacteristic);
  }

  protected void finalize(Intent paramIntent, boolean paramBoolean)
  {
    int j = 0;
    boolean bool = paramIntent.getBooleanExtra("no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND", false);
    Object localObject = this.mService;
    BluetoothGatt localBluetoothGatt = this.mGatt;
    if ((!paramBoolean) && (bool))
      paramBoolean = false;
    else
      paramBoolean = true;
    ((DfuBaseService)localObject).refreshDeviceCache(localBluetoothGatt, paramBoolean);
    this.mService.close(this.mGatt);
    int i = j;
    if (this.mGatt.getDevice().getBondState() == 12)
    {
      paramBoolean = paramIntent.getBooleanExtra("no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND", false);
      if ((!paramBoolean) && (bool))
      {
        i = 0;
      }
      else
      {
        removeBond();
        this.mService.waitFor(2000);
        i = 1;
      }
      if ((paramBoolean) && ((this.mFileType & 0x4) > 0))
      {
        createBond();
        i = j;
      }
    }
    if (this.mProgressInfo.isLastPart())
    {
      if (i == 0)
        this.mService.waitFor(1400);
      this.mProgressInfo.setProgress(-6);
      return;
    }
    logi("Starting service that will upload application");
    localObject = new Intent();
    ((Intent)localObject).fillIn(paramIntent, 24);
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE", "application/zip");
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE", 4);
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT", this.mProgressInfo.getCurrentPart() + 1);
    ((Intent)localObject).putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL", this.mProgressInfo.getTotalParts());
    restartService((Intent)localObject, true);
  }

  protected abstract UUID getControlPointCharacteristicUUID();

  protected abstract UUID getDfuServiceUUID();

  protected abstract UUID getPacketCharacteristicUUID();

  protected void uploadFirmwareImage(BluetoothGattCharacteristic arg1)
    throws DeviceDisconnectedException, DfuException, UploadAbortedException
  {
    if (this.mAborted)
      throw new UploadAbortedException();
    this.mReceivedData = null;
    this.mError = 0;
    this.mFirmwareUploadInProgress = true;
    this.mPacketsSentSinceNotification = 0;
    byte[] arrayOfByte = this.mBuffer;
    try
    {
      int i = this.mFirmwareStream.read(arrayOfByte);
      DfuBaseService localDfuBaseService = this.mService;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Sending firmware to characteristic ");
      localStringBuilder.append(???.getUuid());
      localStringBuilder.append("...");
      localDfuBaseService.sendLogBroadcast(1, localStringBuilder.toString());
      writePacket(this.mGatt, ???, arrayOfByte, i);
      try
      {
        synchronized (this.mLock)
        {
          while (((this.mFirmwareUploadInProgress) && (this.mReceivedData == null) && (this.mConnected) && (this.mError == 0)) || (this.mPaused))
            this.mLock.wait();
        }
      }
      catch (InterruptedException )
      {
        loge("Sleeping interrupted", ???);
        if (this.mError != 0)
          throw new DfuException("Uploading Firmware Image failed", this.mError);
        if (!this.mConnected)
          throw new DeviceDisconnectedException("Uploading Firmware Image failed: device disconnected");
        return;
      }
      label225: throw new DfuException("Error while reading file", 4100);
      label239: throw new DfuException("HEX file not valid", 4099);
    }
    catch (HexFileValidationException )
    {
      break label239;
    }
    catch (IOException )
    {
      break label225;
    }
  }

  protected void writeInitData(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, CRC32 paramCRC32)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException
  {
    try
    {
      byte[] arrayOfByte = this.mBuffer;
      while (true)
      {
        int i = this.mInitPacketStream.read(arrayOfByte, 0, arrayOfByte.length);
        if (i == -1)
          break;
        writeInitPacket(paramBluetoothGattCharacteristic, arrayOfByte, i);
        if (paramCRC32 != null)
          paramCRC32.update(arrayOfByte, 0, i);
      }
      return;
    }
    catch (IOException paramBluetoothGattCharacteristic)
    {
      loge("Error while reading Init packet file", paramBluetoothGattCharacteristic);
    }
    throw new DfuException("Error while reading Init packet file", 4098);
  }

  protected class BaseCustomBluetoothCallback extends BaseDfuImpl.BaseBluetoothGattCallback
  {
    protected BaseCustomBluetoothCallback()
    {
      super();
    }

    protected void handleNotification(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
    {
      paramBluetoothGatt = BaseCustomDfuImpl.this.mService;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Notification received from ");
      localStringBuilder.append(paramBluetoothGattCharacteristic.getUuid());
      localStringBuilder.append(", value (0x): ");
      localStringBuilder.append(parse(paramBluetoothGattCharacteristic));
      paramBluetoothGatt.sendLogBroadcast(5, localStringBuilder.toString());
      BaseCustomDfuImpl.this.mReceivedData = paramBluetoothGattCharacteristic.getValue();
      BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
    }

    protected void handlePacketReceiptNotification(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
    {
      if (!BaseCustomDfuImpl.this.mFirmwareUploadInProgress)
      {
        handleNotification(paramBluetoothGatt, paramBluetoothGattCharacteristic);
        return;
      }
      BluetoothGattCharacteristic localBluetoothGattCharacteristic = paramBluetoothGatt.getService(BaseCustomDfuImpl.this.getDfuServiceUUID()).getCharacteristic(BaseCustomDfuImpl.this.getPacketCharacteristicUUID());
      try
      {
        BaseCustomDfuImpl.this.mPacketsSentSinceNotification = 0;
        BaseCustomDfuImpl.this.waitIfPaused();
        if ((!BaseCustomDfuImpl.this.mAborted) && (BaseCustomDfuImpl.this.mError == 0) && (!BaseCustomDfuImpl.this.mRemoteErrorOccurred) && (!BaseCustomDfuImpl.this.mResetRequestSent))
        {
          boolean bool1 = BaseCustomDfuImpl.this.mProgressInfo.isComplete();
          boolean bool2 = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
          if ((!bool1) && (!bool2))
          {
            int i = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
            byte[] arrayOfByte = BaseCustomDfuImpl.this.mBuffer;
            paramBluetoothGattCharacteristic = arrayOfByte;
            if (i < arrayOfByte.length)
              paramBluetoothGattCharacteristic = new byte[i];
            i = BaseCustomDfuImpl.this.mFirmwareStream.read(paramBluetoothGattCharacteristic);
            BaseCustomDfuImpl.this.writePacket(paramBluetoothGatt, localBluetoothGattCharacteristic, paramBluetoothGattCharacteristic, i);
            return;
          }
          BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
          BaseCustomDfuImpl.this.notifyLock();
          return;
        }
        BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
        BaseCustomDfuImpl.this.mService.sendLogBroadcast(15, "Upload terminated");
        return;
      }
      catch (IOException paramBluetoothGatt)
      {
        BaseCustomDfuImpl.this.loge("Error while reading the input stream", paramBluetoothGatt);
        BaseCustomDfuImpl.this.mError = 4100;
        return;
        BaseCustomDfuImpl.this.loge("Invalid HEX file");
        BaseCustomDfuImpl.this.mError = 4099;
        return;
      }
      catch (HexFileValidationException paramBluetoothGatt)
      {
        label255: break label255;
      }
    }

    public void onCharacteristicWrite(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
    {
      int i = 1;
      Object localObject;
      if (paramInt == 0)
        if (paramBluetoothGattCharacteristic.getUuid().equals(BaseCustomDfuImpl.this.getPacketCharacteristicUUID()))
        {
          if (BaseCustomDfuImpl.this.mInitPacketInProgress)
          {
            paramBluetoothGatt = BaseCustomDfuImpl.this.mService;
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Data written to ");
            ((StringBuilder)localObject).append(paramBluetoothGattCharacteristic.getUuid());
            ((StringBuilder)localObject).append(", value (0x): ");
            ((StringBuilder)localObject).append(parse(paramBluetoothGattCharacteristic));
            paramBluetoothGatt.sendLogBroadcast(5, ((StringBuilder)localObject).toString());
            BaseCustomDfuImpl.access$002(BaseCustomDfuImpl.this, false);
            break label586;
          }
          if (BaseCustomDfuImpl.this.mFirmwareUploadInProgress)
          {
            BaseCustomDfuImpl.this.mProgressInfo.addBytesSent(paramBluetoothGattCharacteristic.getValue().length);
            localObject = BaseCustomDfuImpl.this;
            ((BaseCustomDfuImpl)localObject).mPacketsSentSinceNotification += 1;
            if ((BaseCustomDfuImpl.this.mPacketsBeforeNotification > 0) && (BaseCustomDfuImpl.this.mPacketsSentSinceNotification >= BaseCustomDfuImpl.this.mPacketsBeforeNotification))
              paramInt = i;
            else
              paramInt = 0;
            boolean bool1 = BaseCustomDfuImpl.this.mProgressInfo.isComplete();
            boolean bool2 = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
            if (paramInt != 0)
              return;
            if ((bool1) || (bool2));
          }
        }
      try
      {
        BaseCustomDfuImpl.this.waitIfPaused();
        if ((!BaseCustomDfuImpl.this.mAborted) && (BaseCustomDfuImpl.this.mError == 0) && (!BaseCustomDfuImpl.this.mRemoteErrorOccurred) && (!BaseCustomDfuImpl.this.mResetRequestSent))
        {
          paramInt = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
          byte[] arrayOfByte = BaseCustomDfuImpl.this.mBuffer;
          localObject = arrayOfByte;
          if (paramInt < arrayOfByte.length)
            localObject = new byte[paramInt];
          paramInt = BaseCustomDfuImpl.this.mFirmwareStream.read((byte[])localObject);
          BaseCustomDfuImpl.this.writePacket(paramBluetoothGatt, paramBluetoothGattCharacteristic, (byte[])localObject, paramInt);
          return;
        }
        BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
        BaseCustomDfuImpl.this.mService.sendLogBroadcast(15, "Upload terminated");
        BaseCustomDfuImpl.this.notifyLock();
        return;
      }
      catch (IOException paramBluetoothGatt)
      {
        BaseCustomDfuImpl.this.loge("Error while reading the input stream", paramBluetoothGatt);
        BaseCustomDfuImpl.this.mError = 4100;
        break label586;
        BaseCustomDfuImpl.this.loge("Invalid HEX file");
        BaseCustomDfuImpl.this.mError = 4099;
        break label586;
        BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
        BaseCustomDfuImpl.this.notifyLock();
        return;
        onPacketCharacteristicWrite(paramBluetoothGatt, paramBluetoothGattCharacteristic, paramInt);
        break label586;
        paramBluetoothGatt = BaseCustomDfuImpl.this.mService;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Data written to ");
        ((StringBuilder)localObject).append(paramBluetoothGattCharacteristic.getUuid());
        ((StringBuilder)localObject).append(", value (0x): ");
        ((StringBuilder)localObject).append(parse(paramBluetoothGattCharacteristic));
        paramBluetoothGatt.sendLogBroadcast(5, ((StringBuilder)localObject).toString());
        BaseCustomDfuImpl.this.mRequestCompleted = true;
        break label586;
        if (BaseCustomDfuImpl.this.mResetRequestSent)
        {
          BaseCustomDfuImpl.this.mRequestCompleted = true;
        }
        else
        {
          paramBluetoothGatt = BaseCustomDfuImpl.this;
          paramBluetoothGattCharacteristic = new StringBuilder();
          paramBluetoothGattCharacteristic.append("Characteristic write error: ");
          paramBluetoothGattCharacteristic.append(paramInt);
          paramBluetoothGatt.loge(paramBluetoothGattCharacteristic.toString());
          BaseCustomDfuImpl.this.mError = (paramInt | 0x4000);
        }
        BaseCustomDfuImpl.this.notifyLock();
        return;
      }
      catch (HexFileValidationException paramBluetoothGatt)
      {
        label396: label586: break label396;
      }
    }

    protected void onPacketCharacteristicWrite(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
    {
    }
  }
}