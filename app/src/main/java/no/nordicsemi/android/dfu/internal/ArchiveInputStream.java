package no.nordicsemi.android.dfu.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import no.nordicsemi.android.dfu.internal.manifest.FileInfo;
import no.nordicsemi.android.dfu.internal.manifest.Manifest;
import no.nordicsemi.android.dfu.internal.manifest.ManifestFile;
import no.nordicsemi.android.dfu.internal.manifest.SoftDeviceBootloaderFileInfo;

public class ArchiveInputStream extends InputStream
{
  private static final String APPLICATION_BIN = "application.bin";
  private static final String APPLICATION_HEX = "application.hex";
  private static final String APPLICATION_INIT = "application.dat";
  private static final String BOOTLOADER_BIN = "bootloader.bin";
  private static final String BOOTLOADER_HEX = "bootloader.hex";
  private static final String MANIFEST = "manifest.json";
  private static final String SOFTDEVICE_BIN = "softdevice.bin";
  private static final String SOFTDEVICE_HEX = "softdevice.hex";
  private static final String SYSTEM_INIT = "system.dat";
  private static final String TAG = "DfuArchiveInputStream";
  private byte[] applicationBytes;
  private byte[] applicationInitBytes;
  private int applicationSize;
  private byte[] bootloaderBytes;
  private int bootloaderSize;
  private int bytesRead;
  private int bytesReadFromCurrentSource;
  private int bytesReadFromMarkedSource;
  private CRC32 crc32;
  private byte[] currentSource;
  private Map<String, byte[]> entries;
  private Manifest manifest;
  private byte[] markedSource;
  private byte[] softDeviceAndBootloaderBytes;
  private byte[] softDeviceBytes;
  private int softDeviceSize;
  private byte[] systemInitBytes;
  private int type;
  private final ZipInputStream zipInputStream;

  public ArchiveInputStream(InputStream paramInputStream, int paramInt1, int paramInt2)
    throws IOException
  {
    this.zipInputStream = new ZipInputStream(paramInputStream);
    this.crc32 = new CRC32();
    this.entries = new HashMap();
    this.bytesRead = 0;
    this.bytesReadFromCurrentSource = 0;
    while (true)
    {
      try
      {
        parseZip(paramInt1);
        if (this.manifest == null)
          break label1050;
        if ((this.manifest.getApplicationInfo() != null) && ((paramInt2 == 0) || ((paramInt2 & 0x4) > 0)))
        {
          paramInputStream = this.manifest.getApplicationInfo();
          this.applicationBytes = ((byte[])this.entries.get(paramInputStream.getBinFileName()));
          this.applicationInitBytes = ((byte[])this.entries.get(paramInputStream.getDatFileName()));
          StringBuilder localStringBuilder;
          if (this.applicationBytes == null)
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Application file ");
            localStringBuilder.append(paramInputStream.getBinFileName());
            localStringBuilder.append(" not found.");
            throw new IOException(localStringBuilder.toString());
          }
          this.applicationSize = this.applicationBytes.length;
          this.currentSource = this.applicationBytes;
          i = 1;
          paramInt1 = i;
          if (this.manifest.getBootloaderInfo() != null)
            if (paramInt2 != 0)
            {
              paramInt1 = i;
              if ((paramInt2 & 0x2) <= 0);
            }
            else
            {
              if (this.systemInitBytes != null)
                throw new IOException("Manifest: softdevice and bootloader specified. Use softdevice_bootloader instead.");
              paramInputStream = this.manifest.getBootloaderInfo();
              this.bootloaderBytes = ((byte[])this.entries.get(paramInputStream.getBinFileName()));
              this.systemInitBytes = ((byte[])this.entries.get(paramInputStream.getDatFileName()));
              if (this.bootloaderBytes == null)
              {
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("Bootloader file ");
                localStringBuilder.append(paramInputStream.getBinFileName());
                localStringBuilder.append(" not found.");
                throw new IOException(localStringBuilder.toString());
              }
              this.bootloaderSize = this.bootloaderBytes.length;
              this.currentSource = this.bootloaderBytes;
              paramInt1 = 1;
            }
          i = paramInt1;
          if (this.manifest.getSoftdeviceInfo() != null)
            if (paramInt2 != 0)
            {
              i = paramInt1;
              if ((paramInt2 & 0x1) <= 0);
            }
            else
            {
              paramInputStream = this.manifest.getSoftdeviceInfo();
              this.softDeviceBytes = ((byte[])this.entries.get(paramInputStream.getBinFileName()));
              this.systemInitBytes = ((byte[])this.entries.get(paramInputStream.getDatFileName()));
              if (this.softDeviceBytes == null)
              {
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("SoftDevice file ");
                localStringBuilder.append(paramInputStream.getBinFileName());
                localStringBuilder.append(" not found.");
                throw new IOException(localStringBuilder.toString());
              }
              this.softDeviceSize = this.softDeviceBytes.length;
              this.currentSource = this.softDeviceBytes;
              i = 1;
            }
          paramInt1 = i;
          if (this.manifest.getSoftdeviceBootloaderInfo() != null)
            if (paramInt2 != 0)
            {
              paramInt1 = i;
              if ((paramInt2 & 0x1) > 0)
              {
                paramInt1 = i;
                if ((paramInt2 & 0x2) <= 0);
              }
            }
            else
            {
              if (this.systemInitBytes != null)
                throw new IOException("Manifest: The softdevice_bootloader may not be used together with softdevice or bootloader.");
              paramInputStream = this.manifest.getSoftdeviceBootloaderInfo();
              this.softDeviceAndBootloaderBytes = ((byte[])this.entries.get(paramInputStream.getBinFileName()));
              this.systemInitBytes = ((byte[])this.entries.get(paramInputStream.getDatFileName()));
              if (this.softDeviceAndBootloaderBytes == null)
              {
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("File ");
                localStringBuilder.append(paramInputStream.getBinFileName());
                localStringBuilder.append(" not found.");
                throw new IOException(localStringBuilder.toString());
              }
              this.softDeviceSize = paramInputStream.getSoftdeviceSize();
              this.bootloaderSize = paramInputStream.getBootloaderSize();
              this.currentSource = this.softDeviceAndBootloaderBytes;
              paramInt1 = 1;
            }
          if (paramInt1 == 0)
          {
            throw new IOException("Manifest file must specify at least one file.");
            this.applicationBytes = ((byte[])this.entries.get("application.hex"));
            if (this.applicationBytes == null)
              this.applicationBytes = ((byte[])this.entries.get("application.bin"));
            if (this.applicationBytes == null)
              break label1063;
            this.applicationSize = this.applicationBytes.length;
            this.applicationInitBytes = ((byte[])this.entries.get("application.dat"));
            this.currentSource = this.applicationBytes;
            i = 1;
            break label1066;
            this.bootloaderBytes = ((byte[])this.entries.get("bootloader.hex"));
            if (this.bootloaderBytes == null)
              this.bootloaderBytes = ((byte[])this.entries.get("bootloader.bin"));
            paramInt1 = i;
            if (this.bootloaderBytes == null)
              break label1082;
            this.bootloaderSize = this.bootloaderBytes.length;
            this.systemInitBytes = ((byte[])this.entries.get("system.dat"));
            this.currentSource = this.bootloaderBytes;
            paramInt1 = 1;
            break label1082;
            this.softDeviceBytes = ((byte[])this.entries.get("softdevice.hex"));
            if (this.softDeviceBytes == null)
              this.softDeviceBytes = ((byte[])this.entries.get("softdevice.bin"));
            i = paramInt1;
            if (this.softDeviceBytes != null)
            {
              this.softDeviceSize = this.softDeviceBytes.length;
              this.systemInitBytes = ((byte[])this.entries.get("system.dat"));
              this.currentSource = this.softDeviceBytes;
              i = 1;
            }
            if (i == 0)
              throw new IOException("The ZIP file must contain an Application, a Soft Device and/or a Bootloader.");
          }
          mark(0);
          return;
        }
      }
      finally
      {
        this.type = getContentType();
        this.zipInputStream.close();
      }
      int i = 0;
      continue;
      label1050: if ((paramInt2 != 0) && ((paramInt2 & 0x4) <= 0))
      {
        label1063: i = 0;
        label1066: if (paramInt2 != 0)
        {
          paramInt1 = i;
          if ((paramInt2 & 0x2) <= 0)
            label1082: if (paramInt2 != 0)
            {
              i = paramInt1;
              if ((paramInt2 & 0x1) <= 0);
            }
        }
      }
    }
  }

  private void parseZip(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[1024];
    String str1 = null;
    while (true)
    {
      Object localObject1 = this.zipInputStream.getNextEntry();
      if (localObject1 == null)
        break;
      String str2 = ((ZipEntry)localObject1).getName();
      if (((ZipEntry)localObject1).isDirectory())
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("A directory found in the ZIP: ");
        ((StringBuilder)localObject1).append(str2);
        ((StringBuilder)localObject1).append("!");
        Log.w("DfuArchiveInputStream", ((StringBuilder)localObject1).toString());
      }
      else
      {
        localObject1 = new ByteArrayOutputStream();
        while (true)
        {
          int i = this.zipInputStream.read(arrayOfByte);
          if (i == -1)
            break;
          ((ByteArrayOutputStream)localObject1).write(arrayOfByte, 0, i);
        }
        Object localObject2 = ((ByteArrayOutputStream)localObject1).toByteArray();
        localObject1 = localObject2;
        if (str2.toLowerCase(Locale.US).endsWith("hex"))
        {
          localObject2 = new HexInputStream((byte[])localObject2, paramInt);
          localObject1 = new byte[((HexInputStream)localObject2).available()];
          ((HexInputStream)localObject2).read((byte[])localObject1);
          ((HexInputStream)localObject2).close();
        }
        if ("manifest.json".equals(str2))
          str1 = new String((byte[])localObject1, "UTF-8");
        else
          this.entries.put(str2, localObject1);
      }
    }
    if (this.entries.isEmpty())
      throw new FileNotFoundException("No files found in the ZIP. Check if the URI provided is valid and the ZIP contains required files on root level, not in a directory.");
    if (str1 != null)
    {
      this.manifest = ((ManifestFile)new Gson().fromJson(str1, ManifestFile.class)).getManifest();
      if (this.manifest == null)
        Log.w("DfuArchiveInputStream", "Manifest failed to be parsed. Did you add \n-keep class no.nordicsemi.android.dfu.** { *; }\nto your proguard rules?");
    }
    else
    {
      Log.w("DfuArchiveInputStream", "Manifest not found in the ZIP. It is recommended to use a distribution file created with: https://github.com/NordicSemiconductor/pc-nrfutil/ (for Legacy DFU use version 0.5.x)");
    }
  }

  private int rawRead(@NonNull byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = this.currentSource.length - this.bytesReadFromCurrentSource;
    if (paramInt2 > i)
      paramInt2 = i;
    System.arraycopy(this.currentSource, this.bytesReadFromCurrentSource, paramArrayOfByte, paramInt1, paramInt2);
    this.bytesReadFromCurrentSource += paramInt2;
    this.bytesRead += paramInt2;
    this.crc32.update(paramArrayOfByte, paramInt1, paramInt2);
    return paramInt2;
  }

  private byte[] startNextFile()
  {
    byte[] arrayOfByte;
    if ((this.currentSource == this.softDeviceBytes) && (this.bootloaderBytes != null) && ((this.type & 0x2) > 0))
    {
      arrayOfByte = this.bootloaderBytes;
      this.currentSource = arrayOfByte;
    }
    else if ((this.currentSource != this.applicationBytes) && (this.applicationBytes != null) && ((this.type & 0x4) > 0))
    {
      arrayOfByte = this.applicationBytes;
      this.currentSource = arrayOfByte;
    }
    else
    {
      arrayOfByte = null;
      this.currentSource = null;
    }
    this.bytesReadFromCurrentSource = 0;
    return arrayOfByte;
  }

  public int applicationImageSize()
  {
    if ((this.type & 0x4) > 0)
      return this.applicationSize;
    return 0;
  }

  public int available()
  {
    if ((this.softDeviceAndBootloaderBytes != null) && (this.softDeviceSize == 0) && (this.bootloaderSize == 0) && ((this.type & 0x3) > 0))
      return this.softDeviceAndBootloaderBytes.length + applicationImageSize() - this.bytesRead;
    return softDeviceImageSize() + bootloaderImageSize() + applicationImageSize() - this.bytesRead;
  }

  public int bootloaderImageSize()
  {
    if ((this.type & 0x2) > 0)
      return this.bootloaderSize;
    return 0;
  }

  public void close()
    throws IOException
  {
    this.softDeviceBytes = null;
    this.bootloaderBytes = null;
    this.softDeviceBytes = null;
    this.softDeviceAndBootloaderBytes = null;
    this.applicationSize = 0;
    this.bootloaderSize = 0;
    this.softDeviceSize = 0;
    this.currentSource = null;
    this.bytesReadFromCurrentSource = 0;
    this.bytesRead = 0;
    this.zipInputStream.close();
  }

  public void fullReset()
  {
    if ((this.softDeviceBytes != null) && (this.bootloaderBytes != null) && (this.currentSource == this.bootloaderBytes))
      this.currentSource = this.softDeviceBytes;
    this.bytesReadFromCurrentSource = 0;
    mark(0);
    reset();
  }

  public byte[] getApplicationInit()
  {
    return this.applicationInitBytes;
  }

  public int getBytesRead()
  {
    return this.bytesRead;
  }

  public int getContentType()
  {
    this.type = 0;
    if (this.softDeviceAndBootloaderBytes != null)
      this.type |= 3;
    if (this.softDeviceSize > 0)
      this.type |= 1;
    if (this.bootloaderSize > 0)
      this.type |= 2;
    if (this.applicationSize > 0)
      this.type |= 4;
    return this.type;
  }

  public long getCrc32()
  {
    return this.crc32.getValue();
  }

  public byte[] getSystemInit()
  {
    return this.systemInitBytes;
  }

  public boolean isSecureDfuRequired()
  {
    return (this.manifest != null) && (this.manifest.isSecureDfuRequired());
  }

  public void mark(int paramInt)
  {
    this.markedSource = this.currentSource;
    this.bytesReadFromMarkedSource = this.bytesReadFromCurrentSource;
  }

  public boolean markSupported()
  {
    return true;
  }

  public int read()
  {
    byte[] arrayOfByte = new byte[1];
    if (read(arrayOfByte) == -1)
      return -1;
    return arrayOfByte[0] & 0xFF;
  }

  public int read(@NonNull byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public int read(@NonNull byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = rawRead(paramArrayOfByte, paramInt1, paramInt2);
    if ((paramInt2 > i) && (startNextFile() != null))
      return i + rawRead(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
    return i;
  }

  public void reset()
  {
    this.currentSource = this.markedSource;
    int i = this.bytesReadFromMarkedSource;
    this.bytesReadFromCurrentSource = i;
    this.bytesRead = i;
    this.crc32.reset();
    if ((this.currentSource == this.bootloaderBytes) && (this.softDeviceBytes != null))
    {
      this.crc32.update(this.softDeviceBytes);
      this.bytesRead += this.softDeviceSize;
    }
    this.crc32.update(this.currentSource, 0, this.bytesReadFromCurrentSource);
  }

  public int setContentType(int paramInt)
  {
    this.type = paramInt;
    int i = paramInt & 0x4;
    if ((i > 0) && (this.applicationBytes == null))
      this.type &= -5;
    int j = paramInt & 0x3;
    if (j == 3)
    {
      if ((this.softDeviceBytes == null) && (this.softDeviceAndBootloaderBytes == null))
        this.type &= -2;
      if ((this.bootloaderBytes == null) && (this.softDeviceAndBootloaderBytes == null))
        this.type &= -2;
    }
    else if (this.softDeviceAndBootloaderBytes != null)
    {
      this.type &= -4;
    }
    if ((j > 0) && (this.softDeviceAndBootloaderBytes != null))
      this.currentSource = this.softDeviceAndBootloaderBytes;
    else if ((paramInt & 0x1) > 0)
      this.currentSource = this.softDeviceBytes;
    else if ((paramInt & 0x2) > 0)
      this.currentSource = this.bootloaderBytes;
    else if (i > 0)
      this.currentSource = this.applicationBytes;
    this.bytesReadFromCurrentSource = 0;
    mark(0);
    reset();
    return this.type;
  }

  public long skip(long paramLong)
  {
    return 0L;
  }

  public int softDeviceImageSize()
  {
    if ((this.type & 0x1) > 0)
      return this.softDeviceSize;
    return 0;
  }
}