package com.facebook.common.internal;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public final class ByteStreams
{
  private static final int BUF_SIZE = 4096;

  public static long copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    Preconditions.checkNotNull(paramInputStream);
    Preconditions.checkNotNull(paramOutputStream);
    byte[] arrayOfByte = new byte[4096];
    int i;
    for (long l = 0L; ; l += i)
    {
      i = paramInputStream.read(arrayOfByte);
      if (i == -1)
        return l;
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }

  public static int read(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    Preconditions.checkNotNull(paramInputStream);
    Preconditions.checkNotNull(paramArrayOfByte);
    if (paramInt2 < 0)
      throw new IndexOutOfBoundsException("len is negative");
    int i = 0;
    while (i < paramInt2)
    {
      int j = paramInputStream.read(paramArrayOfByte, paramInt1 + i, paramInt2 - i);
      if (j == -1)
        return i;
      i += j;
    }
    return i;
  }

  public static void readFully(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt1 = read(paramInputStream, paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt1 != paramInt2)
    {
      paramInputStream = new StringBuilder();
      paramInputStream.append("reached end of stream after reading ");
      paramInputStream.append(paramInt1);
      paramInputStream.append(" bytes; ");
      paramInputStream.append(paramInt2);
      paramInputStream.append(" bytes expected");
      throw new EOFException(paramInputStream.toString());
    }
  }

  public static byte[] toByteArray(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    copy(paramInputStream, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static byte[] toByteArray(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = paramInt;
    while (i > 0)
    {
      int j = paramInt - i;
      int k = paramInputStream.read(arrayOfByte, j, i);
      if (k == -1)
        return Arrays.copyOf(arrayOfByte, j);
      i -= k;
    }
    paramInt = paramInputStream.read();
    if (paramInt == -1)
      return arrayOfByte;
    FastByteArrayOutputStream localFastByteArrayOutputStream = new FastByteArrayOutputStream(null);
    localFastByteArrayOutputStream.write(paramInt);
    copy(paramInputStream, localFastByteArrayOutputStream);
    paramInputStream = new byte[arrayOfByte.length + localFastByteArrayOutputStream.size()];
    System.arraycopy(arrayOfByte, 0, paramInputStream, 0, arrayOfByte.length);
    localFastByteArrayOutputStream.writeTo(paramInputStream, arrayOfByte.length);
    return paramInputStream;
  }

  private static final class FastByteArrayOutputStream extends ByteArrayOutputStream
  {
    void writeTo(byte[] paramArrayOfByte, int paramInt)
    {
      System.arraycopy(this.buf, 0, paramArrayOfByte, paramInt, this.count);
    }
  }
}