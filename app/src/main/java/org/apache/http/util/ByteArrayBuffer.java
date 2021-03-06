package org.apache.http.util;

import java.io.Serializable;

public final class ByteArrayBuffer
  implements Serializable
{
  private static final long serialVersionUID = 4359112959524048036L;
  private byte[] buffer;
  private int len;

  public ByteArrayBuffer(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Buffer capacity may not be negative");
    this.buffer = new byte[paramInt];
  }

  private void expand(int paramInt)
  {
    byte[] arrayOfByte = new byte[Math.max(this.buffer.length << 1, paramInt)];
    System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.len);
    this.buffer = arrayOfByte;
  }

  public void append(int paramInt)
  {
    int i = this.len + 1;
    if (i > this.buffer.length)
      expand(i);
    this.buffer[this.len] = ((byte)paramInt);
    this.len = i;
  }

  public void append(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramCharArrayBuffer == null)
      return;
    append(paramCharArrayBuffer.buffer(), paramInt1, paramInt2);
  }

  public void append(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      return;
    if ((paramInt1 >= 0) && (paramInt1 <= paramArrayOfByte.length) && (paramInt2 >= 0))
    {
      int i = paramInt1 + paramInt2;
      if ((i >= 0) && (i <= paramArrayOfByte.length))
      {
        if (paramInt2 == 0)
          return;
        i = this.len + paramInt2;
        if (i > this.buffer.length)
          expand(i);
        System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.len, paramInt2);
        this.len = i;
        return;
      }
    }
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("off: ");
    localStringBuffer.append(paramInt1);
    localStringBuffer.append(" len: ");
    localStringBuffer.append(paramInt2);
    localStringBuffer.append(" b.length: ");
    localStringBuffer.append(paramArrayOfByte.length);
    throw new IndexOutOfBoundsException(localStringBuffer.toString());
  }

  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (paramArrayOfChar == null)
      return;
    if ((paramInt1 >= 0) && (paramInt1 <= paramArrayOfChar.length) && (paramInt2 >= 0))
    {
      int i = paramInt1 + paramInt2;
      if ((i >= 0) && (i <= paramArrayOfChar.length))
      {
        if (paramInt2 == 0)
          return;
        int j = this.len;
        int k = paramInt2 + j;
        paramInt2 = j;
        i = paramInt1;
        if (k > this.buffer.length)
        {
          expand(k);
          i = paramInt1;
          paramInt2 = j;
        }
        while (paramInt2 < k)
        {
          this.buffer[paramInt2] = ((byte)paramArrayOfChar[i]);
          i += 1;
          paramInt2 += 1;
        }
        this.len = k;
        return;
      }
    }
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("off: ");
    localStringBuffer.append(paramInt1);
    localStringBuffer.append(" len: ");
    localStringBuffer.append(paramInt2);
    localStringBuffer.append(" b.length: ");
    localStringBuffer.append(paramArrayOfChar.length);
    throw new IndexOutOfBoundsException(localStringBuffer.toString());
  }

  public byte[] buffer()
  {
    return this.buffer;
  }

  public int byteAt(int paramInt)
  {
    return this.buffer[paramInt];
  }

  public int capacity()
  {
    return this.buffer.length;
  }

  public void clear()
  {
    this.len = 0;
  }

  public void ensureCapacity(int paramInt)
  {
    if (paramInt <= 0)
      return;
    if (paramInt > this.buffer.length - this.len)
      expand(this.len + paramInt);
  }

  public int indexOf(byte paramByte)
  {
    return indexOf(paramByte, 0, this.len);
  }

  public int indexOf(byte paramByte, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 < 0)
      i = 0;
    paramInt1 = paramInt2;
    if (paramInt2 > this.len)
      paramInt1 = this.len;
    paramInt2 = i;
    if (i > paramInt1)
      return -1;
    while (paramInt2 < paramInt1)
    {
      if (this.buffer[paramInt2] == paramByte)
        return paramInt2;
      paramInt2 += 1;
    }
    return -1;
  }

  public boolean isEmpty()
  {
    return this.len == 0;
  }

  public boolean isFull()
  {
    return this.len == this.buffer.length;
  }

  public int length()
  {
    return this.len;
  }

  public void setLength(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt <= this.buffer.length))
    {
      this.len = paramInt;
      return;
    }
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("len: ");
    localStringBuffer.append(paramInt);
    localStringBuffer.append(" < 0 or > buffer len: ");
    localStringBuffer.append(this.buffer.length);
    throw new IndexOutOfBoundsException(localStringBuffer.toString());
  }

  public byte[] toByteArray()
  {
    byte[] arrayOfByte = new byte[this.len];
    if (this.len > 0)
      System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.len);
    return arrayOfByte;
  }
}