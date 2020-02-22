package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;

abstract class BaseButtonlessDfuImpl extends BaseDfuImpl
{
  private final ButtonlessBluetoothCallback mBluetoothCallback = new ButtonlessBluetoothCallback();

  BaseButtonlessDfuImpl(Intent paramIntent, DfuBaseService paramDfuBaseService)
  {
    super(paramIntent, paramDfuBaseService);
  }

  protected void finalize(Intent paramIntent, boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = paramIntent.getBooleanExtra("no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND", false);
    Object localObject = this.mService;
    BluetoothGatt localBluetoothGatt = this.mGatt;
    if ((!paramBoolean1) && (bool))
      paramBoolean1 = false;
    else
      paramBoolean1 = true;
    ((DfuBaseService)localObject).refreshDeviceCache(localBluetoothGatt, paramBoolean1);
    this.mService.close(this.mGatt);
    if ((this.mGatt.getDevice().getBondState() == 12) && ((paramIntent.getBooleanExtra("no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND", false)) || (!bool)))
    {
      removeBond();
      this.mService.waitFor(2000);
    }
    logi("Restarting to bootloader mode");
    localObject = new Intent();
    ((Intent)localObject).fillIn(paramIntent, 24);
    restartService((Intent)localObject, paramBoolean2);
  }

  public BaseDfuImpl.BaseBluetoothGattCallback getGattCallback()
  {
    return this.mBluetoothCallback;
  }

  protected class ButtonlessBluetoothCallback extends BaseDfuImpl.BaseBluetoothGattCallback
  {
    protected ButtonlessBluetoothCallback()
    {
      super();
    }

    public void onCharacteristicChanged(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
    {
      paramBluetoothGatt = BaseButtonlessDfuImpl.this.mService;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Notification received from ");
      localStringBuilder.append(paramBluetoothGattCharacteristic.getUuid());
      localStringBuilder.append(", value (0x): ");
      localStringBuilder.append(parse(paramBluetoothGattCharacteristic));
      paramBluetoothGatt.sendLogBroadcast(5, localStringBuilder.toString());
      BaseButtonlessDfuImpl.this.mReceivedData = paramBluetoothGattCharacteristic.getValue();
      BaseButtonlessDfuImpl.this.notifyLock();
    }

    public void onCharacteristicWrite(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
    {
      BaseButtonlessDfuImpl.this.mRequestCompleted = true;
      BaseButtonlessDfuImpl.this.notifyLock();
    }
  }
}