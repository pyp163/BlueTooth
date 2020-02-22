package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import java.io.InputStream;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

abstract interface DfuService extends DfuCallback
{
  public abstract boolean initialize(Intent paramIntent, BluetoothGatt paramBluetoothGatt, int paramInt, InputStream paramInputStream1, InputStream paramInputStream2)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException;

  public abstract boolean isClientCompatible(Intent paramIntent, BluetoothGatt paramBluetoothGatt)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException;

  public abstract void performDfu(Intent paramIntent)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException;

  public abstract void release();
}