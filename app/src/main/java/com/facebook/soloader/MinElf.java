package com.facebook.soloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public final class MinElf
{
  public static final int DT_NEEDED = 1;
  public static final int DT_NULL = 0;
  public static final int DT_STRTAB = 5;
  public static final int ELF_MAGIC = 1179403647;
  public static final int PN_XNUM = 65535;
  public static final int PT_DYNAMIC = 2;
  public static final int PT_LOAD = 1;

  public static String[] extract_DT_NEEDED(File paramFile)
    throws IOException
  {
    paramFile = new FileInputStream(paramFile);
    try
    {
      String[] arrayOfString = extract_DT_NEEDED(paramFile.getChannel());
      return arrayOfString;
    }
    finally
    {
      paramFile.close();
    }
  }

  public static String[] extract_DT_NEEDED(FileChannel paramFileChannel)
    throws IOException
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(8);
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    if (getu32(paramFileChannel, localByteBuffer, 0L) != 1179403647L)
      throw new ElfError("file is not ELF");
    int j = getu8(paramFileChannel, localByteBuffer, 4L);
    int i = 1;
    if (j != 1)
      i = 0;
    if (getu8(paramFileChannel, localByteBuffer, 5L) == 2)
      localByteBuffer.order(ByteOrder.BIG_ENDIAN);
    long l2;
    if (i != 0)
      l2 = getu32(paramFileChannel, localByteBuffer, 28L);
    else
      l2 = get64(paramFileChannel, localByteBuffer, 32L);
    if (i != 0)
      l1 = getu16(paramFileChannel, localByteBuffer, 44L);
    else
      l1 = getu16(paramFileChannel, localByteBuffer, 56L);
    if (i != 0)
      j = getu16(paramFileChannel, localByteBuffer, 42L);
    else
      j = getu16(paramFileChannel, localByteBuffer, 54L);
    long l3 = l1;
    if (l1 == 65535L)
    {
      if (i != 0)
        l1 = getu32(paramFileChannel, localByteBuffer, 32L);
      else
        l1 = get64(paramFileChannel, localByteBuffer, 40L);
      if (i != 0);
      for (l1 = getu32(paramFileChannel, localByteBuffer, l1 + 28L); ; l1 = getu32(paramFileChannel, localByteBuffer, l1 + 44L))
      {
        l3 = l1;
        break;
      }
    }
    long l4 = l2;
    for (long l1 = 0L; l1 < l3; l1 += 1L)
    {
      if (i != 0)
        l5 = getu32(paramFileChannel, localByteBuffer, l4 + 0L);
      else
        l5 = getu32(paramFileChannel, localByteBuffer, l4 + 0L);
      if (l5 == 2L)
      {
        if (i != 0)
        {
          l1 = getu32(paramFileChannel, localByteBuffer, l4 + 4L);
          break label367;
        }
        l1 = get64(paramFileChannel, localByteBuffer, l4 + 8L);
        break label367;
      }
      l4 += j;
    }
    l1 = 0L;
    label367: if (l1 == 0L)
      throw new ElfError("ELF file does not contain dynamic linking information");
    long l5 = l1;
    l4 = 0L;
    int k;
    for (int m = 0; ; m = k)
    {
      long l6;
      if (i != 0)
        l6 = getu32(paramFileChannel, localByteBuffer, l5 + 0L);
      else
        l6 = get64(paramFileChannel, localByteBuffer, l5 + 0L);
      if (l6 == 1L)
      {
        if (m == 2147483647)
          throw new ElfError("malformed DT_NEEDED section");
        k = m + 1;
      }
      else
      {
        k = m;
        if (l6 == 5L)
        {
          if (i != 0)
            l4 = getu32(paramFileChannel, localByteBuffer, l5 + 4L);
          else
            l4 = get64(paramFileChannel, localByteBuffer, l5 + 8L);
          k = m;
        }
      }
      long l7;
      if (i != 0)
        l7 = 8L;
      else
        l7 = 16L;
      l5 += l7;
      if (l6 == 0L)
      {
        if (l4 == 0L)
          throw new ElfError("Dynamic section string-table not found");
        m = 0;
        while (m < l3)
        {
          if (i != 0)
            l5 = getu32(paramFileChannel, localByteBuffer, l2 + 0L);
          else
            l5 = getu32(paramFileChannel, localByteBuffer, l2 + 0L);
          if (l5 == 1L)
          {
            if (i != 0)
              l5 = getu32(paramFileChannel, localByteBuffer, l2 + 8L);
            else
              l5 = get64(paramFileChannel, localByteBuffer, l2 + 16L);
            if (i != 0)
              l6 = getu32(paramFileChannel, localByteBuffer, l2 + 20L);
            else
              l6 = get64(paramFileChannel, localByteBuffer, l2 + 40L);
            if ((l5 <= l4) && (l4 < l6 + l5))
            {
              if (i != 0)
                l2 = getu32(paramFileChannel, localByteBuffer, l2 + 4L);
              else
                l2 = get64(paramFileChannel, localByteBuffer, l2 + 8L);
              l2 += l4 - l5;
              break label761;
            }
          }
          l2 += j;
          m += 1;
        }
        l2 = 0L;
        label761: if (l2 == 0L)
          throw new ElfError("did not find file offset of DT_STRTAB table");
        String[] arrayOfString = new String[k];
        j = 0;
        while (true)
        {
          if (i != 0)
            l3 = getu32(paramFileChannel, localByteBuffer, l1 + 0L);
          else
            l3 = get64(paramFileChannel, localByteBuffer, l1 + 0L);
          if (l3 == 1L)
          {
            if (i != 0)
              l4 = getu32(paramFileChannel, localByteBuffer, l1 + 4L);
            else
              l4 = get64(paramFileChannel, localByteBuffer, l1 + 8L);
            arrayOfString[j] = getSz(paramFileChannel, localByteBuffer, l4 + l2);
            if (j == 2147483647)
              throw new ElfError("malformed DT_NEEDED section");
            j += 1;
          }
          if (i != 0)
            l4 = 8L;
          else
            l4 = 16L;
          l1 += l4;
          if (l3 == 0L)
          {
            if (j != arrayOfString.length)
              throw new ElfError("malformed DT_NEEDED section");
            return arrayOfString;
          }
        }
      }
    }
  }

  private static long get64(FileChannel paramFileChannel, ByteBuffer paramByteBuffer, long paramLong)
    throws IOException
  {
    read(paramFileChannel, paramByteBuffer, 8, paramLong);
    return paramByteBuffer.getLong();
  }

  private static String getSz(FileChannel paramFileChannel, ByteBuffer paramByteBuffer, long paramLong)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      int i = getu8(paramFileChannel, paramByteBuffer, paramLong);
      if (i == 0)
        break;
      localStringBuilder.append((char)i);
      paramLong = 1L + paramLong;
    }
    return localStringBuilder.toString();
  }

  private static int getu16(FileChannel paramFileChannel, ByteBuffer paramByteBuffer, long paramLong)
    throws IOException
  {
    read(paramFileChannel, paramByteBuffer, 2, paramLong);
    return paramByteBuffer.getShort() & 0xFFFF;
  }

  private static long getu32(FileChannel paramFileChannel, ByteBuffer paramByteBuffer, long paramLong)
    throws IOException
  {
    read(paramFileChannel, paramByteBuffer, 4, paramLong);
    return paramByteBuffer.getInt() & 0xFFFFFFFF;
  }

  private static short getu8(FileChannel paramFileChannel, ByteBuffer paramByteBuffer, long paramLong)
    throws IOException
  {
    read(paramFileChannel, paramByteBuffer, 1, paramLong);
    return (short)(paramByteBuffer.get() & 0xFF);
  }

  private static void read(FileChannel paramFileChannel, ByteBuffer paramByteBuffer, int paramInt, long paramLong)
    throws IOException
  {
    paramByteBuffer.position(0);
    paramByteBuffer.limit(paramInt);
    while (paramByteBuffer.remaining() > 0)
    {
      paramInt = paramFileChannel.read(paramByteBuffer, paramLong);
      if (paramInt == -1)
        break;
      paramLong += paramInt;
    }
    if (paramByteBuffer.remaining() > 0)
      throw new ElfError("ELF file truncated");
    paramByteBuffer.position(0);
  }

  private static class ElfError extends RuntimeException
  {
    ElfError(String paramString)
    {
      super();
    }
  }
}