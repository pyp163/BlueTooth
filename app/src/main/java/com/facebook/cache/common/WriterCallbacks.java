package com.facebook.cache.common;

import com.facebook.common.internal.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WriterCallbacks
{
  public static WriterCallback from(InputStream paramInputStream)
  {
    return new WriterCallback()
    {
      public void write(OutputStream paramAnonymousOutputStream)
        throws IOException
      {
        ByteStreams.copy(this.val$is, paramAnonymousOutputStream);
      }
    };
  }

  public static WriterCallback from(byte[] paramArrayOfByte)
  {
    return new WriterCallback()
    {
      public void write(OutputStream paramAnonymousOutputStream)
        throws IOException
      {
        paramAnonymousOutputStream.write(this.val$data);
      }
    };
  }
}