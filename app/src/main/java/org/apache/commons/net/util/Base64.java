package org.apache.commons.net.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Base64
{
  static final byte[] CHUNK_SEPARATOR = { 13, 10 };
  static final int CHUNK_SIZE = 76;
  private static final byte[] DECODE_TABLE = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
  private static final int DEFAULT_BUFFER_RESIZE_FACTOR = 2;
  private static final int DEFAULT_BUFFER_SIZE = 8192;
  private static final int MASK_6BITS = 63;
  private static final int MASK_8BITS = 255;
  private static final byte PAD = 61;
  private static final byte[] STANDARD_ENCODE_TABLE = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final byte[] URL_SAFE_ENCODE_TABLE = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
  private byte[] buffer;
  private int currentLinePos;
  private final int decodeSize;
  private final int encodeSize;
  private final byte[] encodeTable;
  private boolean eof;
  private final int lineLength;
  private final byte[] lineSeparator;
  private int modulus;
  private int pos;
  private int readPos;
  private int x;

  public Base64()
  {
    this(false);
  }

  public Base64(int paramInt)
  {
    this(paramInt, CHUNK_SEPARATOR);
  }

  public Base64(int paramInt, byte[] paramArrayOfByte)
  {
    this(paramInt, paramArrayOfByte, false);
  }

  public Base64(int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    Object localObject = paramArrayOfByte;
    if (paramArrayOfByte == null)
    {
      localObject = CHUNK_SEPARATOR;
      paramInt = 0;
    }
    int i;
    if (paramInt > 0)
      i = paramInt / 4 * 4;
    else
      i = 0;
    this.lineLength = i;
    this.lineSeparator = new byte[localObject.length];
    System.arraycopy(localObject, 0, this.lineSeparator, 0, localObject.length);
    if (paramInt > 0)
      this.encodeSize = (localObject.length + 4);
    else
      this.encodeSize = 4;
    this.decodeSize = (this.encodeSize - 1);
    if (containsBase64Byte((byte[])localObject))
    {
      paramArrayOfByte = newStringUtf8((byte[])localObject);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("lineSeperator must not contain base64 characters: [");
      ((StringBuilder)localObject).append(paramArrayOfByte);
      ((StringBuilder)localObject).append("]");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    if (paramBoolean)
      paramArrayOfByte = URL_SAFE_ENCODE_TABLE;
    else
      paramArrayOfByte = STANDARD_ENCODE_TABLE;
    this.encodeTable = paramArrayOfByte;
  }

  public Base64(boolean paramBoolean)
  {
    this(76, CHUNK_SEPARATOR, paramBoolean);
  }

  private static boolean containsBase64Byte(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      if (isBase64(paramArrayOfByte[i]))
        return true;
      i += 1;
    }
    return false;
  }

  public static byte[] decodeBase64(String paramString)
  {
    return new Base64().decode(paramString);
  }

  public static byte[] decodeBase64(byte[] paramArrayOfByte)
  {
    return new Base64().decode(paramArrayOfByte);
  }

  public static BigInteger decodeInteger(byte[] paramArrayOfByte)
  {
    return new BigInteger(1, decodeBase64(paramArrayOfByte));
  }

  public static byte[] encodeBase64(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, false);
  }

  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    return encodeBase64(paramArrayOfByte, paramBoolean, false);
  }

  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean1, boolean paramBoolean2)
  {
    return encodeBase64(paramArrayOfByte, paramBoolean1, paramBoolean2, 2147483647);
  }

  public static byte[] encodeBase64(byte[] paramArrayOfByte, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    if (paramArrayOfByte != null)
    {
      if (paramArrayOfByte.length == 0)
        return paramArrayOfByte;
      long l = getEncodeLength(paramArrayOfByte, 76, CHUNK_SEPARATOR);
      if (l > paramInt)
      {
        paramArrayOfByte = new StringBuilder();
        paramArrayOfByte.append("Input array too big, the output array would be bigger (");
        paramArrayOfByte.append(l);
        paramArrayOfByte.append(") than the specified maxium size of ");
        paramArrayOfByte.append(paramInt);
        throw new IllegalArgumentException(paramArrayOfByte.toString());
      }
      Base64 localBase64;
      if (paramBoolean1)
        localBase64 = new Base64(paramBoolean2);
      else
        localBase64 = new Base64(0, CHUNK_SEPARATOR, paramBoolean2);
      return localBase64.encode(paramArrayOfByte);
    }
    return paramArrayOfByte;
  }

  public static byte[] encodeBase64Chunked(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, true);
  }

  public static String encodeBase64String(byte[] paramArrayOfByte)
  {
    return newStringUtf8(encodeBase64(paramArrayOfByte, true));
  }

  public static byte[] encodeBase64URLSafe(byte[] paramArrayOfByte)
  {
    return encodeBase64(paramArrayOfByte, false, true);
  }

  public static String encodeBase64URLSafeString(byte[] paramArrayOfByte)
  {
    return newStringUtf8(encodeBase64(paramArrayOfByte, false, true));
  }

  public static byte[] encodeInteger(BigInteger paramBigInteger)
  {
    if (paramBigInteger == null)
      throw new NullPointerException("encodeInteger called with null parameter");
    return encodeBase64(toIntegerBytes(paramBigInteger), false);
  }

  private byte[] getBytesUtf8(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF8");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
    }
    throw new RuntimeException(paramString);
  }

  private static long getEncodeLength(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    paramInt = paramInt / 4 * 4;
    long l2 = paramArrayOfByte1.length * 4 / 3;
    long l3 = l2 % 4L;
    long l1 = l2;
    if (l3 != 0L)
      l1 = l2 + (4L - l3);
    l2 = l1;
    if (paramInt > 0)
    {
      l2 = paramInt;
      if (l1 % l2 == 0L)
        paramInt = 1;
      else
        paramInt = 0;
      l1 += l1 / l2 * paramArrayOfByte2.length;
      l2 = l1;
      if (paramInt == 0)
        l2 = l1 + paramArrayOfByte2.length;
    }
    return l2;
  }

  public static boolean isArrayByteBase64(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      if ((!isBase64(paramArrayOfByte[i])) && (!isWhiteSpace(paramArrayOfByte[i])))
        return false;
      i += 1;
    }
    return true;
  }

  public static boolean isBase64(byte paramByte)
  {
    return (paramByte == 61) || ((paramByte >= 0) && (paramByte < DECODE_TABLE.length) && (DECODE_TABLE[paramByte] != -1));
  }

  private static boolean isWhiteSpace(byte paramByte)
  {
    if ((paramByte != 13) && (paramByte != 32))
      switch (paramByte)
      {
      default:
        return false;
      case 9:
      case 10:
      }
    return true;
  }

  private static String newStringUtf8(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, "UTF8");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    throw new RuntimeException(paramArrayOfByte);
  }

  private void reset()
  {
    this.buffer = null;
    this.pos = 0;
    this.readPos = 0;
    this.currentLinePos = 0;
    this.modulus = 0;
    this.eof = false;
  }

  private void resizeBuffer()
  {
    if (this.buffer == null)
    {
      this.buffer = new byte[8192];
      this.pos = 0;
      this.readPos = 0;
      return;
    }
    byte[] arrayOfByte = new byte[this.buffer.length * 2];
    System.arraycopy(this.buffer, 0, arrayOfByte, 0, this.buffer.length);
    this.buffer = arrayOfByte;
  }

  static byte[] toIntegerBytes(BigInteger paramBigInteger)
  {
    int m = paramBigInteger.bitLength() + 7 >> 3 << 3;
    byte[] arrayOfByte = paramBigInteger.toByteArray();
    if ((paramBigInteger.bitLength() % 8 != 0) && (paramBigInteger.bitLength() / 8 + 1 == m / 8))
      return arrayOfByte;
    int j = 0;
    int k = arrayOfByte.length;
    int i = k;
    if (paramBigInteger.bitLength() % 8 == 0)
    {
      i = k - 1;
      j = 1;
    }
    k = m / 8;
    paramBigInteger = new byte[k];
    System.arraycopy(arrayOfByte, j, paramBigInteger, k - i, i);
    return paramBigInteger;
  }

  int avail()
  {
    if (this.buffer != null)
      return this.pos - this.readPos;
    return 0;
  }

  public Object decode(Object paramObject)
  {
    if ((paramObject instanceof byte[]))
      return decode((byte[])paramObject);
    if ((paramObject instanceof String))
      return decode((String)paramObject);
    throw new RuntimeException("Parameter supplied to Base64 decode is not a byte[] or a String");
  }

  void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.eof)
      return;
    if (paramInt2 < 0)
      this.eof = true;
    int i = 0;
    while (i < paramInt2)
    {
      if ((this.buffer == null) || (this.buffer.length - this.pos < this.decodeSize))
        resizeBuffer();
      int j = paramArrayOfByte[paramInt1];
      if (j == 61)
      {
        this.eof = true;
        break;
      }
      if ((j >= 0) && (j < DECODE_TABLE.length))
      {
        j = DECODE_TABLE[j];
        if (j >= 0)
        {
          int k = this.modulus + 1;
          this.modulus = k;
          this.modulus = (k % 4);
          this.x = ((this.x << 6) + j);
          if (this.modulus == 0)
          {
            byte[] arrayOfByte = this.buffer;
            j = this.pos;
            this.pos = (j + 1);
            arrayOfByte[j] = ((byte)(this.x >> 16 & 0xFF));
            arrayOfByte = this.buffer;
            j = this.pos;
            this.pos = (j + 1);
            arrayOfByte[j] = ((byte)(this.x >> 8 & 0xFF));
            arrayOfByte = this.buffer;
            j = this.pos;
            this.pos = (j + 1);
            arrayOfByte[j] = ((byte)(this.x & 0xFF));
          }
        }
      }
      i += 1;
      paramInt1 += 1;
    }
    if ((this.eof) && (this.modulus != 0))
    {
      this.x <<= 6;
      switch (this.modulus)
      {
      default:
        return;
      case 3:
        paramArrayOfByte = this.buffer;
        paramInt1 = this.pos;
        this.pos = (paramInt1 + 1);
        paramArrayOfByte[paramInt1] = ((byte)(this.x >> 16 & 0xFF));
        paramArrayOfByte = this.buffer;
        paramInt1 = this.pos;
        this.pos = (paramInt1 + 1);
        paramArrayOfByte[paramInt1] = ((byte)(this.x >> 8 & 0xFF));
        return;
      case 2:
      }
      this.x <<= 6;
      paramArrayOfByte = this.buffer;
      paramInt1 = this.pos;
      this.pos = (paramInt1 + 1);
      paramArrayOfByte[paramInt1] = ((byte)(this.x >> 16 & 0xFF));
    }
  }

  public byte[] decode(String paramString)
  {
    return decode(getBytesUtf8(paramString));
  }

  public byte[] decode(byte[] paramArrayOfByte)
  {
    reset();
    if (paramArrayOfByte != null)
    {
      if (paramArrayOfByte.length == 0)
        return paramArrayOfByte;
      byte[] arrayOfByte = new byte[(int)(paramArrayOfByte.length * 3 / 4)];
      setInitialBuffer(arrayOfByte, 0, arrayOfByte.length);
      decode(paramArrayOfByte, 0, paramArrayOfByte.length);
      decode(paramArrayOfByte, 0, -1);
      paramArrayOfByte = new byte[this.pos];
      readResults(paramArrayOfByte, 0, paramArrayOfByte.length);
      return paramArrayOfByte;
    }
    return paramArrayOfByte;
  }

  public Object encode(Object paramObject)
  {
    if (!(paramObject instanceof byte[]))
      throw new RuntimeException("Parameter supplied to Base64 encode is not a byte[]");
    return encode((byte[])paramObject);
  }

  void encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.eof)
      return;
    if (paramInt2 < 0)
    {
      this.eof = true;
      if ((this.buffer == null) || (this.buffer.length - this.pos < this.encodeSize))
        resizeBuffer();
      switch (this.modulus)
      {
      default:
        break;
      case 2:
        paramArrayOfByte = this.buffer;
        paramInt1 = this.pos;
        this.pos = (paramInt1 + 1);
        paramArrayOfByte[paramInt1] = this.encodeTable[(this.x >> 10 & 0x3F)];
        paramArrayOfByte = this.buffer;
        paramInt1 = this.pos;
        this.pos = (paramInt1 + 1);
        paramArrayOfByte[paramInt1] = this.encodeTable[(this.x >> 4 & 0x3F)];
        paramArrayOfByte = this.buffer;
        paramInt1 = this.pos;
        this.pos = (paramInt1 + 1);
        paramArrayOfByte[paramInt1] = this.encodeTable[(this.x << 2 & 0x3F)];
        if (this.encodeTable == STANDARD_ENCODE_TABLE)
        {
          paramArrayOfByte = this.buffer;
          paramInt1 = this.pos;
          this.pos = (paramInt1 + 1);
          paramArrayOfByte[paramInt1] = 61;
        }
        break;
      case 1:
        paramArrayOfByte = this.buffer;
        paramInt1 = this.pos;
        this.pos = (paramInt1 + 1);
        paramArrayOfByte[paramInt1] = this.encodeTable[(this.x >> 2 & 0x3F)];
        paramArrayOfByte = this.buffer;
        paramInt1 = this.pos;
        this.pos = (paramInt1 + 1);
        paramArrayOfByte[paramInt1] = this.encodeTable[(this.x << 4 & 0x3F)];
        if (this.encodeTable == STANDARD_ENCODE_TABLE)
        {
          paramArrayOfByte = this.buffer;
          paramInt1 = this.pos;
          this.pos = (paramInt1 + 1);
          paramArrayOfByte[paramInt1] = 61;
          paramArrayOfByte = this.buffer;
          paramInt1 = this.pos;
          this.pos = (paramInt1 + 1);
          paramArrayOfByte[paramInt1] = 61;
        }
        break;
      }
      if ((this.lineLength > 0) && (this.pos > 0))
      {
        System.arraycopy(this.lineSeparator, 0, this.buffer, this.pos, this.lineSeparator.length);
        this.pos += this.lineSeparator.length;
      }
    }
    else
    {
      int i = 0;
      while (i < paramInt2)
      {
        if ((this.buffer == null) || (this.buffer.length - this.pos < this.encodeSize))
          resizeBuffer();
        int j = this.modulus + 1;
        this.modulus = j;
        this.modulus = (j % 3);
        int k = paramArrayOfByte[paramInt1];
        j = k;
        if (k < 0)
          j = k + 256;
        this.x = ((this.x << 8) + j);
        if (this.modulus == 0)
        {
          byte[] arrayOfByte = this.buffer;
          j = this.pos;
          this.pos = (j + 1);
          arrayOfByte[j] = this.encodeTable[(this.x >> 18 & 0x3F)];
          arrayOfByte = this.buffer;
          j = this.pos;
          this.pos = (j + 1);
          arrayOfByte[j] = this.encodeTable[(this.x >> 12 & 0x3F)];
          arrayOfByte = this.buffer;
          j = this.pos;
          this.pos = (j + 1);
          arrayOfByte[j] = this.encodeTable[(this.x >> 6 & 0x3F)];
          arrayOfByte = this.buffer;
          j = this.pos;
          this.pos = (j + 1);
          arrayOfByte[j] = this.encodeTable[(this.x & 0x3F)];
          this.currentLinePos += 4;
          if ((this.lineLength > 0) && (this.lineLength <= this.currentLinePos))
          {
            System.arraycopy(this.lineSeparator, 0, this.buffer, this.pos, this.lineSeparator.length);
            this.pos += this.lineSeparator.length;
            this.currentLinePos = 0;
          }
        }
        i += 1;
        paramInt1 += 1;
      }
    }
  }

  public byte[] encode(byte[] paramArrayOfByte)
  {
    reset();
    if (paramArrayOfByte != null)
    {
      if (paramArrayOfByte.length == 0)
        return paramArrayOfByte;
      byte[] arrayOfByte = new byte[(int)getEncodeLength(paramArrayOfByte, this.lineLength, this.lineSeparator)];
      setInitialBuffer(arrayOfByte, 0, arrayOfByte.length);
      encode(paramArrayOfByte, 0, paramArrayOfByte.length);
      encode(paramArrayOfByte, 0, -1);
      if (this.buffer != arrayOfByte)
        readResults(arrayOfByte, 0, arrayOfByte.length);
      if ((isUrlSafe()) && (this.pos < arrayOfByte.length))
      {
        paramArrayOfByte = new byte[this.pos];
        System.arraycopy(arrayOfByte, 0, paramArrayOfByte, 0, this.pos);
        return paramArrayOfByte;
      }
      return arrayOfByte;
    }
    return paramArrayOfByte;
  }

  public String encodeToString(byte[] paramArrayOfByte)
  {
    return newStringUtf8(encode(paramArrayOfByte));
  }

  boolean hasData()
  {
    return this.buffer != null;
  }

  public boolean isUrlSafe()
  {
    return this.encodeTable == URL_SAFE_ENCODE_TABLE;
  }

  int readResults(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.buffer != null)
    {
      paramInt2 = Math.min(avail(), paramInt2);
      if (this.buffer != paramArrayOfByte)
      {
        System.arraycopy(this.buffer, this.readPos, paramArrayOfByte, paramInt1, paramInt2);
        this.readPos += paramInt2;
        if (this.readPos >= this.pos)
        {
          this.buffer = null;
          return paramInt2;
        }
      }
      else
      {
        this.buffer = null;
      }
      return paramInt2;
    }
    if (this.eof)
      return -1;
    return 0;
  }

  void setInitialBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length == paramInt2))
    {
      this.buffer = paramArrayOfByte;
      this.pos = paramInt1;
      this.readPos = paramInt1;
    }
  }
}