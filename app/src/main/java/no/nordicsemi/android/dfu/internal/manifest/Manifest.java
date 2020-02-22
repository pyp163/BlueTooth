package no.nordicsemi.android.dfu.internal.manifest;

import com.google.gson.annotations.SerializedName;

public class Manifest
{
  private FileInfo application;
  private FileInfo bootloader;

  @SerializedName("bootloader_application")
  private FileInfo bootloaderApplication;
  private FileInfo softdevice;

  @SerializedName("softdevice_application")
  private FileInfo softdeviceApplication;

  @SerializedName("softdevice_bootloader")
  private SoftDeviceBootloaderFileInfo softdeviceBootloader;

  @SerializedName("softdevice_bootloader_application")
  private FileInfo softdeviceBootloaderApplication;

  public FileInfo getApplicationInfo()
  {
    if (this.application != null)
      return this.application;
    if (this.softdeviceApplication != null)
      return this.softdeviceApplication;
    if (this.bootloaderApplication != null)
      return this.bootloaderApplication;
    return this.softdeviceBootloaderApplication;
  }

  public FileInfo getBootloaderInfo()
  {
    return this.bootloader;
  }

  public SoftDeviceBootloaderFileInfo getSoftdeviceBootloaderInfo()
  {
    return this.softdeviceBootloader;
  }

  public FileInfo getSoftdeviceInfo()
  {
    return this.softdevice;
  }

  public boolean isSecureDfuRequired()
  {
    return (this.bootloaderApplication != null) || (this.softdeviceApplication != null) || (this.softdeviceBootloaderApplication != null);
  }
}