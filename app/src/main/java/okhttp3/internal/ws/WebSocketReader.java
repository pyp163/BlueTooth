package okhttp3.internal.ws;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.Buffer.UnsafeCursor;
import okio.BufferedSource;
import okio.ByteString;
import okio.Timeout;

final class WebSocketReader
{
  boolean closed;
  private final Buffer controlFrameBuffer = new Buffer();
  final FrameCallback frameCallback;
  long frameLength;
  final boolean isClient;
  boolean isControlFrame;
  boolean isFinalFrame;
  private final Buffer.UnsafeCursor maskCursor;
  private final byte[] maskKey;
  private final Buffer messageFrameBuffer = new Buffer();
  int opcode;
  final BufferedSource source;

  WebSocketReader(boolean paramBoolean, BufferedSource paramBufferedSource, FrameCallback paramFrameCallback)
  {
    if (paramBufferedSource == null)
      throw new NullPointerException("source == null");
    if (paramFrameCallback == null)
      throw new NullPointerException("frameCallback == null");
    this.isClient = paramBoolean;
    this.source = paramBufferedSource;
    this.frameCallback = paramFrameCallback;
    paramFrameCallback = null;
    if (paramBoolean)
      paramBufferedSource = null;
    else
      paramBufferedSource = new byte[4];
    this.maskKey = paramBufferedSource;
    if (paramBoolean)
      paramBufferedSource = paramFrameCallback;
    else
      paramBufferedSource = new Buffer.UnsafeCursor();
    this.maskCursor = paramBufferedSource;
  }

  private void readControlFrame()
    throws IOException
  {
    if (this.frameLength > 0L)
    {
      this.source.readFully(this.controlFrameBuffer, this.frameLength);
      if (!this.isClient)
      {
        this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
        this.maskCursor.seek(0L);
        WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
        this.maskCursor.close();
      }
    }
    switch (this.opcode)
    {
    default:
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unknown control opcode: ");
      ((StringBuilder)localObject).append(Integer.toHexString(this.opcode));
      throw new ProtocolException(((StringBuilder)localObject).toString());
    case 10:
      this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
      return;
    case 9:
      this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
      return;
    case 8:
    }
    int i = 1005;
    Object localObject = "";
    long l = this.controlFrameBuffer.size();
    if (l == 1L)
      throw new ProtocolException("Malformed close payload length of 1.");
    if (l != 0L)
    {
      i = this.controlFrameBuffer.readShort();
      localObject = this.controlFrameBuffer.readUtf8();
      String str = WebSocketProtocol.closeCodeExceptionMessage(i);
      if (str != null)
        throw new ProtocolException(str);
    }
    this.frameCallback.onReadClose(i, (String)localObject);
    this.closed = true;
  }

  private void readHeader()
    throws IOException
  {
    if (this.closed)
      throw new IOException("closed");
    long l = this.source.timeout().timeoutNanos();
    this.source.timeout().clearTimeout();
    try
    {
      int i = this.source.readByte();
      int k = i & 0xFF;
      this.source.timeout().timeout(l, TimeUnit.NANOSECONDS);
      this.opcode = (k & 0xF);
      boolean bool2 = false;
      boolean bool1;
      if ((k & 0x80) != 0)
        bool1 = true;
      else
        bool1 = false;
      this.isFinalFrame = bool1;
      if ((k & 0x8) != 0)
        bool1 = true;
      else
        bool1 = false;
      this.isControlFrame = bool1;
      if ((this.isControlFrame) && (!this.isFinalFrame))
        throw new ProtocolException("Control frames must be final.");
      if ((k & 0x40) != 0)
        i = 1;
      else
        i = 0;
      int j;
      if ((k & 0x20) != 0)
        j = 1;
      else
        j = 0;
      if ((k & 0x10) != 0)
        k = 1;
      else
        k = 0;
      if ((i == 0) && (j == 0) && (k == 0))
      {
        i = this.source.readByte() & 0xFF;
        bool1 = bool2;
        if ((i & 0x80) != 0)
          bool1 = true;
        Object localObject1;
        if (bool1 == this.isClient)
        {
          if (this.isClient)
            localObject1 = "Server-sent frames must not be masked.";
          else
            localObject1 = "Client-sent frames must be masked.";
          throw new ProtocolException((String)localObject1);
        }
        this.frameLength = (i & 0x7F);
        if (this.frameLength == 126L)
        {
          this.frameLength = (this.source.readShort() & 0xFFFF);
        }
        else if (this.frameLength == 127L)
        {
          this.frameLength = this.source.readLong();
          if (this.frameLength < 0L)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Frame length 0x");
            ((StringBuilder)localObject1).append(Long.toHexString(this.frameLength));
            ((StringBuilder)localObject1).append(" > 0x7FFFFFFFFFFFFFFF");
            throw new ProtocolException(((StringBuilder)localObject1).toString());
          }
        }
        if ((this.isControlFrame) && (this.frameLength > 125L))
          throw new ProtocolException("Control frame must be less than 125B.");
        if (bool1)
          this.source.readFully(this.maskKey);
        return;
      }
      throw new ProtocolException("Reserved flags are unsupported.");
    }
    finally
    {
      this.source.timeout().timeout(l, TimeUnit.NANOSECONDS);
    }
  }

  private void readMessage()
    throws IOException
  {
    do
    {
      if (this.closed)
        throw new IOException("closed");
      if (this.frameLength > 0L)
      {
        this.source.readFully(this.messageFrameBuffer, this.frameLength);
        if (!this.isClient)
        {
          this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
          this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
          WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
          this.maskCursor.close();
        }
      }
      if (this.isFinalFrame)
        return;
      readUntilNonControlFrame();
    }
    while (this.opcode == 0);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Expected continuation opcode. Got: ");
    localStringBuilder.append(Integer.toHexString(this.opcode));
    throw new ProtocolException(localStringBuilder.toString());
  }

  private void readMessageFrame()
    throws IOException
  {
    int i = this.opcode;
    if ((i != 1) && (i != 2))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unknown opcode: ");
      localStringBuilder.append(Integer.toHexString(i));
      throw new ProtocolException(localStringBuilder.toString());
    }
    readMessage();
    if (i == 1)
    {
      this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
      return;
    }
    this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
  }

  private void readUntilNonControlFrame()
    throws IOException
  {
    while (!this.closed)
    {
      readHeader();
      if (!this.isControlFrame)
        return;
      readControlFrame();
    }
  }

  void processNextFrame()
    throws IOException
  {
    readHeader();
    if (this.isControlFrame)
    {
      readControlFrame();
      return;
    }
    readMessageFrame();
  }

  public static abstract interface FrameCallback
  {
    public abstract void onReadClose(int paramInt, String paramString);

    public abstract void onReadMessage(String paramString)
      throws IOException;

    public abstract void onReadMessage(ByteString paramByteString)
      throws IOException;

    public abstract void onReadPing(ByteString paramByteString);

    public abstract void onReadPong(ByteString paramByteString);
  }
}