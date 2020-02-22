package no.nordicsemi.android.dfu.internal;

import android.support.annotation.NonNull;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import no.nordicsemi.android.dfu.internal.exception.HexFileValidationException;

public class HexInputStream extends FilterInputStream
{
  private final int LINE_LENGTH = 128;
  private final int MBRSize;
  private int available;
  private int bytesRead;
  private int lastAddress = 0;
  private final byte[] localBuf = new byte[''];
  private int localPos = 128;
  private int pos;
  private int size = this.localBuf.length;

  public HexInputStream(@NonNull InputStream paramInputStream, int paramInt)
    throws HexFileValidationException, IOException
  {
    super(new BufferedInputStream(paramInputStream));
    this.MBRSize = paramInt;
    this.available = calculateBinSize(paramInt);
  }

  public HexInputStream(@NonNull byte[] paramArrayOfByte, int paramInt)
    throws HexFileValidationException, IOException
  {
    super(new ByteArrayInputStream(paramArrayOfByte));
    this.MBRSize = paramInt;
    this.available = calculateBinSize(paramInt);
  }

  private int asciiToInt(int paramInt)
  {
    if (paramInt >= 65)
      return paramInt - 55;
    if (paramInt >= 48)
      return paramInt - 48;
    return -1;
  }

  private int calculateBinSize(int paramInt)
    throws IOException
  {
    InputStream localInputStream = this.in;
    localInputStream.mark(localInputStream.available());
    while (true)
    {
      int i;
      try
      {
        j = localInputStream.read();
        int k = 0;
        i = 0;
        checkComma(j);
        int m = readByte(localInputStream);
        int n = readAddress(localInputStream);
        j = readByte(localInputStream);
        if (j != 4);
        switch (j)
        {
        case 2:
          j = readAddress(localInputStream);
          j <<= 4;
          if ((i > 0) && (j >> 16 != (k >> 16) + 1))
            return i;
          skip(localInputStream, 2L);
          k = j;
          break;
        case 1:
          return i;
        case 0:
          j = i;
          if (n + k >= paramInt)
            j = i + m;
          long l = m * 2 + 2;
          skip(localInputStream, l);
          i = j;
          continue;
          j = readAddress(localInputStream);
          if ((i > 0) && (j != (k >> 16) + 1))
            return i;
          j <<= 16;
          skip(localInputStream, 2L);
          continue;
          j = localInputStream.read();
          if ((j == 10) || (j == 13))
            continue;
          continue;
        }
      }
      finally
      {
        localInputStream.reset();
      }
      int j = i;
    }
  }

  private void checkComma(int paramInt)
    throws HexFileValidationException
  {
    if (paramInt != 58)
      throw new HexFileValidationException("Not a HEX file");
  }

  private int readAddress(@NonNull InputStream paramInputStream)
    throws IOException
  {
    int i = readByte(paramInputStream);
    return readByte(paramInputStream) | i << 8;
  }

  private int readByte(@NonNull InputStream paramInputStream)
    throws IOException
  {
    int i = asciiToInt(paramInputStream.read());
    return asciiToInt(paramInputStream.read()) | i << 4;
  }

  private int readLine()
    throws IOException
  {
    if (this.pos == -1)
      return 0;
    InputStream localInputStream = this.in;
    int j;
    int k;
    do
    {
      do
      {
        i = localInputStream.read();
        this.pos += 1;
      }
      while ((i == 10) || (i == 13));
      checkComma(i);
      j = readByte(localInputStream);
      this.pos += 2;
      k = readAddress(localInputStream);
      this.pos += 4;
      i = readByte(localInputStream);
      this.pos += 2;
      if (i != 4)
      {
        switch (i)
        {
        default:
          long l = j * 2 + 2;
          this.pos = ((int)(this.pos + skip(localInputStream, l)));
          break;
        case 2:
          k = readAddress(localInputStream) << 4;
          this.pos += 4;
          if ((this.bytesRead > 0) && (k >> 16 != (this.lastAddress >> 16) + 1))
            return 0;
          this.lastAddress = k;
          this.pos = ((int)(this.pos + skip(localInputStream, 2L)));
          break;
        case 1:
          this.pos = -1;
          return 0;
        case 0:
          if (this.lastAddress + k >= this.MBRSize)
            continue;
          this.pos = ((int)(this.pos + skip(localInputStream, j * 2 + 2)));
          i = -1;
          break;
        }
      }
      else
      {
        k = readAddress(localInputStream);
        this.pos += 4;
        if ((this.bytesRead > 0) && (k != (this.lastAddress >> 16) + 1))
          return 0;
        this.lastAddress = (k << 16);
        this.pos = ((int)(this.pos + skip(localInputStream, 2L)));
      }
    }
    while (i != 0);
    int i = 0;
    while ((i < this.localBuf.length) && (i < j))
    {
      k = readByte(localInputStream);
      this.pos += 2;
      this.localBuf[i] = ((byte)k);
      i += 1;
    }
    this.pos = ((int)(this.pos + skip(localInputStream, 2L)));
    this.localPos = 0;
    return j;
  }

  private long skip(@NonNull InputStream paramInputStream, long paramLong)
    throws IOException
  {
    long l2 = paramInputStream.skip(paramLong);
    long l1 = l2;
    if (l2 < paramLong)
      l1 = l2 + paramInputStream.skip(paramLong - l2);
    return l1;
  }

  public int available()
  {
    return this.available - this.bytesRead;
  }

  public int read()
  {
    throw new UnsupportedOperationException("Please, use readPacket() method instead");
  }

  public int read(@NonNull byte[] paramArrayOfByte)
    throws IOException
  {
    return readPacket(paramArrayOfByte);
  }

  public int read(@NonNull byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    throw new UnsupportedOperationException("Please, use readPacket() method instead");
  }

  public int readPacket(@NonNull byte[] paramArrayOfByte)
    throws IOException
  {
    int i = 0;
    do
    {
      while (true)
      {
        if (i >= paramArrayOfByte.length)
          break label83;
        if (this.localPos >= this.size)
          break;
        byte[] arrayOfByte = this.localBuf;
        j = this.localPos;
        this.localPos = (j + 1);
        paramArrayOfByte[i] = arrayOfByte[j];
        i += 1;
      }
      int j = this.bytesRead;
      int k = readLine();
      this.size = k;
      this.bytesRead = (j + k);
    }
    while (this.size != 0);
    label83: return i;
  }

  public void reset()
    throws IOException
  {
    try
    {
      super.reset();
      this.pos = 0;
      this.bytesRead = 0;
      this.localPos = 128;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int sizeInBytes()
  {
    return this.available;
  }

  public int sizeInPackets(int paramInt)
  {
    int j = sizeInBytes();
    int i = j / paramInt;
    if (j % paramInt > 0)
      paramInt = 1;
    else
      paramInt = 0;
    return i + paramInt;
  }
}