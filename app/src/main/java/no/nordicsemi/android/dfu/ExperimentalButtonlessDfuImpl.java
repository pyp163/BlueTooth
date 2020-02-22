package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.util.UUID;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

class ExperimentalButtonlessDfuImpl extends ButtonlessDfuImpl
{
  protected static final UUID DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID = new UUID(-8196551313441075360L, -6937650605005804976L);
  protected static final UUID DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_UUID = new UUID(-8196551313441075360L, -6937650605005804976L);
  protected static UUID EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID = DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID;
  protected static UUID EXPERIMENTAL_BUTTONLESS_DFU_UUID = DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_UUID;
  private BluetoothGattCharacteristic mButtonlessDfuCharacteristic;

  ExperimentalButtonlessDfuImpl(Intent paramIntent, DfuBaseService paramDfuBaseService)
  {
    super(paramIntent, paramDfuBaseService);
  }

  protected BluetoothGattCharacteristic getButtonlessDfuCharacteristic()
  {
    return this.mButtonlessDfuCharacteristic;
  }

  protected int getResponseType()
  {
    return 1;
  }

  public boolean isClientCompatible(Intent paramIntent, BluetoothGatt paramBluetoothGatt)
  {
    paramIntent = paramBluetoothGatt.getService(EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID);
    if (paramIntent == null)
      return false;
    paramIntent = paramIntent.getCharacteristic(EXPERIMENTAL_BUTTONLESS_DFU_UUID);
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
    logi("Experimental buttonless service found -> SDK 12.x");
    super.performDfu(paramIntent);
  }

  protected boolean shouldScanForBootloader()
  {
    return true;
  }
}