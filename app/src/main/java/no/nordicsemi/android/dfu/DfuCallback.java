package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGattCallback;

abstract interface DfuCallback extends DfuController
{
  public abstract DfuGattCallback getGattCallback();

  public abstract void onBondStateChanged(int paramInt);

  public static class DfuGattCallback extends BluetoothGattCallback
  {
    public void onDisconnected()
    {
    }
  }
}