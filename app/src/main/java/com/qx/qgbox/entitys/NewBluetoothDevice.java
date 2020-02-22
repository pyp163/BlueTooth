package com.qx.qgbox.entitys;

import android.bluetooth.BluetoothDevice;
import java.io.Serializable;

public class NewBluetoothDevice
  implements Serializable
{
  private static final long serialVersionUID = -5286528597517915057L;
  private int bondState;
  private String macAddress;
  private String name;
  private int rssi;
  private int type;

  public NewBluetoothDevice(BluetoothDevice paramBluetoothDevice, int paramInt)
  {
    this.name = paramBluetoothDevice.getName();
    this.macAddress = paramBluetoothDevice.getAddress();
    this.bondState = paramBluetoothDevice.getBondState();
    this.type = paramBluetoothDevice.getType();
    this.rssi = paramInt;
  }

  public int getBondState()
  {
    return this.bondState;
  }

  public String getMacAddress()
  {
    return this.macAddress;
  }

  public String getName()
  {
    return this.name;
  }

  public int getRssi()
  {
    return this.rssi;
  }

  public int getType()
  {
    return this.type;
  }

  public void setBondState(int paramInt)
  {
    this.bondState = paramInt;
  }

  public void setMacAddress(String paramString)
  {
    this.macAddress = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setRssi(int paramInt)
  {
    this.rssi = paramInt;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }
}
