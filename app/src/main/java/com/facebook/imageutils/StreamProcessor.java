package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;

class StreamProcessor
{
  public static int readPackedInt(InputStream paramInputStream, int paramInt, boolean paramBoolean)
    throws IOException
  {
    int j = 0;
    int i = 0;
    while (j < paramInt)
    {
      int k = paramInputStream.read();
      if (k == -1)
        throw new IOException("no more bytes");
      if (paramBoolean)
        i |= (k & 0xFF) << j * 8;
      else
        i = i << 8 | k & 0xFF;
      j += 1;
    }
    return i;
  }
}