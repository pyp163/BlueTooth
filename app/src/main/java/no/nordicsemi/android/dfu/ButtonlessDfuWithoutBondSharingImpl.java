package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.util.UUID;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

class ButtonlessDfuWithoutBondSharingImpl extends ButtonlessDfuImpl
{
  protected static UUID BUTTONLESS_DFU_SERVICE_UUID = DEFAULT_BUTTONLESS_DFU_SERVICE_UUID;
  protected static UUID BUTTONLESS_DFU_UUID = DEFAULT_BUTTONLESS_DFU_UUID;
  protected static final UUID DEFAULT_BUTTONLESS_DFU_SERVICE_UUID = SecureDfuImpl.DEFAULT_DFU_SERVICE_UUID;
  protected static final UUID DEFAULT_BUTTONLESS_DFU_UUID = new UUID(-8157989233041780896L, -6937650605005804976L);
  private BluetoothGattCharacteristic mButtonlessDfuCharacteristic;

  ButtonlessDfuWithoutBondSharingImpl(Intent paramIntent, DfuBaseService paramDfuBaseService)
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
    logi("Buttonless service without bond sharing found -> SDK 13 or newer");
    if (isBonded())
      logw("Device is paired! Use Buttonless DFU with Bond Sharing instead (SDK 14 or newer)");
    super.performDfu(paramIntent);
  }

  protected boolean shouldScanForBootloader()
  {
    return true;
  }
}