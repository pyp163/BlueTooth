package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.util.UUID;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

class ButtonlessDfuWithBondSharingImpl extends ButtonlessDfuImpl
{
  protected static UUID BUTTONLESS_DFU_SERVICE_UUID = DEFAULT_BUTTONLESS_DFU_SERVICE_UUID;
  protected static UUID BUTTONLESS_DFU_UUID = DEFAULT_BUTTONLESS_DFU_UUID;
  protected static final UUID DEFAULT_BUTTONLESS_DFU_SERVICE_UUID = SecureDfuImpl.DEFAULT_DFU_SERVICE_UUID;
  protected static final UUID DEFAULT_BUTTONLESS_DFU_UUID = new UUID(-8157989228746813600L, -6937650605005804976L);
  private BluetoothGattCharacteristic mButtonlessDfuCharacteristic;

  ButtonlessDfuWithBondSharingImpl(Intent paramIntent, DfuBaseService paramDfuBaseService)
  {
    super(paramIntent, paramDfuBaseService);
  }

  protected BluetoothGattCharacteristic getButtonlessDfuCharacteristic()
  {
    return this.mButtonlessDfuCharacteristic;
  }

  protected int getResponseType()
  {
    return 2;
  }

  public boolean isClientCompatible(Intent paramIntent, BluetoothGatt paramBluetoothGatt)
  {
    paramIntent = paramBluetoothGatt.getService(BUTTONLESS_DFU_SERVICE_UUID);
    if (paramIntent == null)
      return false;
    paramIntent = paramIntent.getCharacteristic(BUTTONLESS_DFU_UUID);
    if (paramIntent != null)
    {
      if (paramIntent.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG) == null)
        return false;
      this.mButtonlessDfuCharacteristic = paramIntent;
      return true;
    }
    return false;
  }

  public void performDfu(Intent paramIntent)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException
  {
    logi("Buttonless service with bond sharing found -> SDK 14 or newer");
    if (!isBonded())
    {
      logw("Device is not paired, cancelling DFU");
      this.mService.sendLogBroadcast(15, "Device is not bonded");
      this.mService.terminateConnection(this.mGatt, 4110);
      return;
    }
    paramIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND", true);
    paramIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND", false);
    super.performDfu(paramIntent);
  }

  protected boolean shouldScanForBootloader()
  {
    return false;
  }
}