package org.apache.http.util;

import java.io.Serializable;
import org.apache.http.protocol.HTTP;

public final class CharArrayBuffer
  implements Serializable
{
  private static final long serialVersionUID = -6208952725094867135L;
  private char[] buffer;
  private int len;

  public CharArrayBuffer(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Buffer capacity may not be negative");
    this.buffer = new char[paramInt];
  }

  private void expand(int paramInt)
  {
    char[] arrayOfChar = new char[Math.max(this.buffer.length << 1, paramInt)];
    System.arraycopy(this.buffer, 0, arrayOfChar, 0, this.len);
    this.buffer = arrayOfChar;
  }

  public void append(char paramChar)
  {
    int i = this.len + 1;
    if (i > this.buffer.length)
      expand(i);
    this.buffer[this.len] = paramChar;
    this.len = i;
  }

  public void append(Object paramObject)
  {
    append(String.valueOf(paramObject));
  }

  public void append(String paramString)
  {
    String str = paramString;
    if (paramString == null)
      str = "null";
    int i = str.length();
    int j = this.len + i;
    if (j > this.buffer.length)
      expand(j);
    str.getChars(0, i, this.buffer, this.len);
    this.len = j;
  }

  public void append(ByteArrayBuffer paramByteArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramByteArrayBuffer == null)
      return;
    append(paramByteArrayBuffer.buffer(), paramInt1, paramInt2);
  }

  public void append(CharArrayBuffer paramCharArrayBuffer)
  {
    if (paramCharArrayBuffer == null)
      return;
    append(paramCharArrayBuffer.buffer, 0, paramCharArrayBuffer.len);
  }

  public void append(CharArrayBuffer paramCharArrayBuffer, int paramInt1, int paramInt2)
  {
    if (paramCharArrayBuffer == null)
      return;
    append(paramCharArrayBuffer.buffer, paramInt1, paramInt2);
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
          this.buffer[paramInt2] = ((char)(paramArrayOfByte[i] & 0xFF));
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
        i = this.len + paramInt2;
        if (i > this.buffer.length)
          expand(i);
        System.arraycopy(paramArrayOfChar, paramInt1, this.buffer, this.len, paramInt2);
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
    localStringBuffer.append(paramArrayOfChar.length);
    throw new IndexOutOfBoundsException(localStringBuffer.toString());
  }

  public char[] buffer()
  {
    return this.buffer;
  }

  public int capacity()
  {
    return this.buffer.length;
  }

  public char charAt(int paramInt)
  {
    return this.buffer[paramInt];
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

  public int indexOf(int paramInt)
  {
    return indexOf(paramInt, 0, this.len);
  }

  public int indexOf(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt2;
    if (paramInt2 < 0)
      i = 0;
    paramInt2 = paramInt3;
    if (paramInt3 > this.len)
      paramInt2 = this.len;
    paramInt3 = i;
    if (i > paramInt2)
      return -1;
    while (paramInt3 < paramInt2)
    {
      if (this.buffer[paramInt3] == paramInt1)
        return paramInt3;
      paramInt3 += 1;
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

  public String substring(int paramInt1, int paramInt2)
  {
    return new String(this.buffer, paramInt1, paramInt2 - paramInt1);
  }

  public String substringTrimmed(int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer;
    if (paramInt1 < 0)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("Negative beginIndex: ");
      localStringBuffer.append(paramInt1);
      throw new IndexOutOfBoundsException(localStringBuffer.toString());
    }
    if (paramInt2 > this.len)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("endIndex: ");
      localStringBuffer.append(paramInt2);
      localStringBuffer.append(" > length: ");
      localStringBuffer.append(this.len);
      throw new IndexOutOfBoundsException(localStringBuffer.toString());
    }
    int i = paramInt1;
    if (paramInt1 > paramInt2)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("beginIndex: ");
      localStringBuffer.append(paramInt1);
      localStringBuffer.append(" > endIndex: ");
      localStringBuffer.append(paramInt2);
      throw new IndexOutOfBoundsException(localStringBuffer.toString());
    }
    while (true)
    {
      paramInt1 = paramInt2;
      if (i >= paramInt2)
        break;
      paramInt1 = paramInt2;
      if (!HTTP.isWhitespace(this.buffer[i]))
        break;
      i += 1;
    }
    while ((paramInt1 > i) && (HTTP.isWhitespace(this.buffer[(paramInt1 - 1)])))
      paramInt1 -= 1;
    return new String(this.buffer, i, paramInt1 - i);
  }

  public char[] toCharArray()
  {
    char[] arrayOfChar = new char[this.len];
    if (this.len > 0)
      System.arraycopy(this.buffer, 0, arrayOfChar, 0, this.len);
    return arrayOfChar;
  }

  public String toString()
  {
    return new String(this.buffer, 0, this.len);
  }
}