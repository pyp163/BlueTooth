package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

final class Http2Writer
  implements Closeable
{
  private static final Logger logger = Logger.getLogger(Http2.class.getName());
  private final boolean client;
  private boolean closed;
  private final Buffer hpackBuffer;
  final Hpack.Writer hpackWriter;
  private int maxFrameSize;
  private final BufferedSink sink;

  Http2Writer(BufferedSink paramBufferedSink, boolean paramBoolean)
  {
    this.sink = paramBufferedSink;
    this.client = paramBoolean;
    this.hpackBuffer = new Buffer();
    this.hpackWriter = new Hpack.Writer(this.hpackBuffer);
    this.maxFrameSize = 16384;
  }

  private void writeContinuationFrames(int paramInt, long paramLong)
    throws IOException
  {
    while (paramLong > 0L)
    {
      int i = (int)Math.min(this.maxFrameSize, paramLong);
      long l = i;
      paramLong -= l;
      byte b;
      if (paramLong == 0L)
        b = 4;
      else
        b = 0;
      frameHeader(paramInt, i, (byte)9, b);
      this.sink.write(this.hpackBuffer, l);
    }
  }

  private static void writeMedium(BufferedSink paramBufferedSink, int paramInt)
    throws IOException
  {
    paramBufferedSink.writeByte(paramInt >>> 16 & 0xFF);
    paramBufferedSink.writeByte(paramInt >>> 8 & 0xFF);
    paramBufferedSink.writeByte(paramInt & 0xFF);
  }

  public void applyAndAckSettings(Settings paramSettings)
    throws IOException
  {
    try
    {
      if (this.closed)
        throw new IOException("closed");
      this.maxFrameSize = paramSettings.getMaxFrameSize(this.maxFrameSize);
      if (paramSettings.getHeaderTableSize() != -1)
        this.hpackWriter.setHeaderTableSizeSetting(paramSettings.getHeaderTableSize());
      frameHeader(0, 0, (byte)4, (byte)1);
      this.sink.flush();
      return;
    }
    finally
    {
    }
    throw paramSettings;
  }

  public void close()
    throws IOException
  {
    try
    {
      this.closed = true;
      this.sink.close();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void connectionPreface()
    throws IOException
  {
    try
    {
      if (this.closed)
        throw new IOException("closed");
      boolean bool = this.client;
      if (!bool)
        return;
      if (logger.isLoggable(Level.FINE))
        logger.fine(Util.format(">> CONNECTION %s", new Object[] { Http2.CONNECTION_PREFACE.hex() }));
      this.sink.write(Http2.CONNECTION_PREFACE.toByteArray());
      this.sink.flush();
      return;
    }
    finally
    {
    }
  }

  public void data(boolean paramBoolean, int paramInt1, Buffer paramBuffer, int paramInt2)
    throws IOException
  {
    while (true)
    {
      try
      {
        if (this.closed)
        {
          throw new IOException("closed");
          dataFrame(paramInt1, b, paramBuffer, paramInt2);
          return;
        }
      }
      finally
      {
      }
      byte b = 0;
      if (paramBoolean)
        b = (byte)1;
    }
  }

  void dataFrame(int paramInt1, byte paramByte, Buffer paramBuffer, int paramInt2)
    throws IOException
  {
    frameHeader(paramInt1, paramInt2, (byte)0, paramByte);
    if (paramInt2 > 0)
      this.sink.write(paramBuffer, paramInt2);
  }

  public void flush()
    throws IOException
  {
    try
    {
      if (this.closed)
        throw new IOException("closed");
      this.sink.flush();
      return;
    }
    finally
    {
    }
  }

  public void frameHeader(int paramInt1, int paramInt2, byte paramByte1, byte paramByte2)
    throws IOException
  {
    if (logger.isLoggable(Level.FINE))
      logger.fine(Http2.frameLog(false, paramInt1, paramInt2, paramByte1, paramByte2));
    if (paramInt2 > this.maxFrameSize)
      throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", new Object[] { Integer.valueOf(this.maxFrameSize), Integer.valueOf(paramInt2) });
    if ((0x80000000 & paramInt1) != 0)
      throw Http2.illegalArgument("reserved bit set: %s", new Object[] { Integer.valueOf(paramInt1) });
    writeMedium(this.sink, paramInt2);
    this.sink.writeByte(paramByte1 & 0xFF);
    this.sink.writeByte(paramByte2 & 0xFF);
    this.sink.writeInt(paramInt1 & 0x7FFFFFFF);
  }

  public void goAway(int paramInt, ErrorCode paramErrorCode, byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      if (this.closed)
        throw new IOException("closed");
      if (paramErrorCode.httpCode == -1)
        throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
      frameHeader(0, paramArrayOfByte.length + 8, (byte)7, (byte)0);
      this.sink.writeInt(paramInt);
      this.sink.writeInt(paramErrorCode.httpCode);
      if (paramArrayOfByte.length > 0)
        this.sink.write(paramArrayOfByte);
      this.sink.flush();
      return;
    }
    finally
    {
    }
    throw paramErrorCode;
  }

  public void headers(boolean paramBoolean, int paramInt, List<Header> paramList)
    throws IOException
  {
    while (true)
    {
      try
      {
        if (this.closed)
          throw new IOException("closed");
        this.hpackWriter.writeHeaders(paramList);
        long l1 = this.hpackBuffer.size();
        int i = (int)Math.min(this.maxFrameSize, l1);
        long l2 = i;
        boolean bool = l1 < l2;
        if (!bool)
        {
          b1 = 4;
          break label123;
          frameHeader(paramInt, i, (byte)1, b2);
          this.sink.write(this.hpackBuffer, l2);
          if (bool)
            writeContinuationFrames(paramInt, l1 - l2);
          return;
        }
      }
      finally
      {
      }
      byte b1 = 0;
      label123: byte b2 = b1;
      if (paramBoolean)
        b2 = (byte)(b1 | 0x1);
    }
  }

  public int maxDataLength()
  {
    return this.maxFrameSize;
  }

  public void ping(boolean paramBoolean, int paramInt1, int paramInt2)
    throws IOException
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }

  public void pushPromise(int paramInt1, int paramInt2, List<Header> paramList)
    throws IOException
  {
    while (true)
    {
      try
      {
        if (this.closed)
          throw new IOException("closed");
        this.hpackWriter.writeHeaders(paramList);
        long l1 = this.hpackBuffer.size();
        int i = (int)Math.min(this.maxFrameSize - 4, l1);
        long l2 = i;
        boolean bool = l1 < l2;
        if (!bool)
        {
          b = 4;
          frameHeader(paramInt1, i + 4, (byte)5, b);
          this.sink.writeInt(paramInt2 & 0x7FFFFFFF);
          this.sink.write(this.hpackBuffer, l2);
          if (bool)
            writeContinuationFrames(paramInt1, l1 - l2);
          return;
        }
      }
      finally
      {
      }
      byte b = 0;
    }
  }

  public void rstStream(int paramInt, ErrorCode paramErrorCode)
    throws IOException
  {
    try
    {
      if (this.closed)
        throw new IOException("closed");
      if (paramErrorCode.httpCode == -1)
        throw new IllegalArgumentException();
      frameHeader(paramInt, 4, (byte)3, (byte)0);
      this.sink.writeInt(paramErrorCode.httpCode);
      this.sink.flush();
      return;
    }
    finally
    {
    }
    throw paramErrorCode;
  }

  public void settings(Settings paramSettings)
    throws IOException
  {
    while (true)
    {
      int j;
      int i;
      try
      {
        if (this.closed)
          throw new IOException("closed");
        j = paramSettings.size();
        i = 0;
        frameHeader(0, j * 6, (byte)4, (byte)0);
        if (i < 10)
        {
          if (!paramSettings.isSet(i))
          {
            break label126;
            this.sink.writeShort(j);
            this.sink.writeInt(paramSettings.get(i));
          }
        }
        else
        {
          this.sink.flush();
          return;
        }
      }
      finally
      {
      }
      if (i == 4)
      {
        j = 3;
      }
      else if (i == 7)
      {
        j = 4;
      }
      else
      {
        j = i;
        continue;
        label126: i += 1;
      }
    }
  }

  public void windowUpdate(int paramInt, long paramLong)
    throws IOException
  {
    while (true)
    {
      try
      {
        if (this.closed)
        {
          throw new IOException("closed");
          frameHeader(paramInt, 4, (byte)8, (byte)0);
          this.sink.writeInt((int)paramLong);
          this.sink.flush();
          return;
          throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[] { Long.valueOf(paramLong) });
        }
      }
      finally
      {
      }
      if (paramLong != 0L)
        if (paramLong <= 2147483647L);
    }
  }
}