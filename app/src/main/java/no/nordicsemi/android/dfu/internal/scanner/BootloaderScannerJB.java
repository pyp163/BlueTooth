package no.nordicsemi.android.dfu.internal.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import java.util.Locale;

public class BootloaderScannerJB
  implements BootloaderScanner, BluetoothAdapter.LeScanCallback
{
  private String mBootloaderAddress;
  private String mDeviceAddress;
  private String mDeviceAddressIncremented;
  private boolean mFound;
  private final Object mLock = new Object();

  public void onLeScan(BluetoothDevice arg1, int paramInt, byte[] paramArrayOfByte)
  {
    ??? = ???.getAddress();
    if ((this.mDeviceAddress.equals(???)) || (this.mDeviceAddressIncremented.equals(???)))
    {
      this.mBootloaderAddress = ???;
      this.mFound = true;
      synchronized (this.mLock)
      {
        this.mLock.notifyAll();
        return;
      }
    }
  }

  public String searchFor(String paramString)
  {
    ??? = paramString.substring(0, 15);
    String str = paramString.substring(15);
    str = String.format(Locale.US, "%02X", new Object[] { Integer.valueOf(Integer.valueOf(str, 16).intValue() + 1 & 0xFF) });
    this.mDeviceAddress = paramString;
    paramString = new StringBuilder();
    paramString.append((String)???);
    paramString.append(str);
    this.mDeviceAddressIncremented = paramString.toString();
    this.mBootloaderAddress = null;
    this.mFound = false;
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(5000L);
          label6: if (BootloaderScannerJB.this.mFound)
            return;
          BootloaderScannerJB.access$102(BootloaderScannerJB.this, null);
          BootloaderScannerJB.access$002(BootloaderScannerJB.this, true);
          synchronized (BootloaderScannerJB.this.mLock)
          {
            BootloaderScannerJB.this.mLock.notifyAll();
            return;
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          break label6;
        }
      }
    }
    , "Scanner timer").start();
    paramString = BluetoothAdapter.getDefaultAdapter();
    if (paramString != null)
    {
      if (paramString.getState() != 12)
        return null;
      paramString.startLeScan(this);
    }
    try
    {
      synchronized (this.mLock)
      {
        while (!this.mFound)
          this.mLock.wait();
      }
      label171: paramString.stopLeScan(this);
      return this.mBootloaderAddress;
      return null;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label171;
    }
  }
}