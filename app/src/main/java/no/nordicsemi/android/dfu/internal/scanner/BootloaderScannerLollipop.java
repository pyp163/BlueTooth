package no.nordicsemi.android.dfu.internal.scanner;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings.Builder;
import java.util.Locale;

@TargetApi(21)
public class BootloaderScannerLollipop extends ScanCallback
  implements BootloaderScanner
{
  private String mBootloaderAddress;
  private String mDeviceAddress;
  private String mDeviceAddressIncremented;
  private boolean mFound;
  private final Object mLock = new Object();

  public void onScanResult(int paramInt, ScanResult arg2)
  {
    ??? = ???.getDevice().getAddress();
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
          label6: if (BootloaderScannerLollipop.this.mFound)
            return;
          BootloaderScannerLollipop.access$102(BootloaderScannerLollipop.this, null);
          BootloaderScannerLollipop.access$002(BootloaderScannerLollipop.this, true);
          synchronized (BootloaderScannerLollipop.this.mLock)
          {
            BootloaderScannerLollipop.this.mLock.notifyAll();
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
      paramString = paramString.getBluetoothLeScanner();
      if (paramString == null)
        return null;
      paramString.startScan(null, new ScanSettings.Builder().setScanMode(2).build(), this);
    }
    try
    {
      synchronized (this.mLock)
      {
        while (!this.mFound)
          this.mLock.wait();
      }
      label196: paramString.stopScan(this);
      return this.mBootloaderAddress;
      return null;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label196;
    }
  }
}