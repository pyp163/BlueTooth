package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

class DfuServiceProvider
  implements DfuCallback
{
  private boolean mAborted;
  private BaseDfuImpl mImpl;
  private boolean mPaused;

  public void abort()
  {
    this.mAborted = true;
    if (this.mImpl != null)
      this.mImpl.abort();
  }

  public DfuCallback.DfuGattCallback getGattCallback()
  {
    if (this.mImpl != null)
      return this.mImpl.getGattCallback();
    return null;
  }

  DfuService getServiceImpl(Intent paramIntent, DfuBaseService paramDfuBaseService, BluetoothGatt paramBluetoothGatt)
    throws DfuException, DeviceDisconnectedException, UploadAbortedException
  {
    try
    {
      this.mImpl = new ButtonlessDfuWithBondSharingImpl(paramIntent, paramDfuBaseService);
      if (this.mImpl.isClientCompatible(paramIntent, paramBluetoothGatt))
      {
        paramIntent = this.mImpl;
        return paramIntent;
      }
      this.mImpl = new ButtonlessDfuWithoutBondSharingImpl(paramIntent, paramDfuBaseService);
      if (this.mImpl.isClientCompatible(paramIntent, paramBluetoothGatt))
      {
        paramIntent = this.mImpl;
        return paramIntent;
      }
      this.mImpl = new SecureDfuImpl(paramIntent, paramDfuBaseService);
      if (this.mImpl.isClientCompatible(paramIntent, paramBluetoothGatt))
      {
        paramIntent = this.mImpl;
        return paramIntent;
      }
      this.mImpl = new LegacyButtonlessDfuImpl(paramIntent, paramDfuBaseService);
      if (this.mImpl.isClientCompatible(paramIntent, paramBluetoothGatt))
      {
        paramIntent = this.mImpl;
        return paramIntent;
      }
      this.mImpl = new LegacyDfuImpl(paramIntent, paramDfuBaseService);
      if (this.mImpl.isClientCompatible(paramIntent, paramBluetoothGatt))
      {
        paramIntent = this.mImpl;
        return paramIntent;
      }
      if (paramIntent.getBooleanExtra("no.nordicsemi.android.dfu.extra.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU", false))
      {
        this.mImpl = new ExperimentalButtonlessDfuImpl(paramIntent, paramDfuBaseService);
        if (this.mImpl.isClientCompatible(paramIntent, paramBluetoothGatt))
        {
          paramIntent = this.mImpl;
          return paramIntent;
        }
      }
      return null;
    }
    finally
    {
      if (this.mImpl != null)
      {
        if (this.mPaused)
          this.mImpl.pause();
        if (this.mAborted)
          this.mImpl.abort();
      }
    }
    throw paramIntent;
  }

  public void onBondStateChanged(int paramInt)
  {
    if (this.mImpl != null)
      this.mImpl.onBondStateChanged(paramInt);
  }

  public void pause()
  {
    this.mPaused = true;
  }

  public void resume()
  {
    this.mPaused = false;
  }
}