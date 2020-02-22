package org.apache.http.impl.io;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import org.apache.http.io.EofSensor;
import org.apache.http.params.HttpParams;

public class SocketInputBuffer extends AbstractSessionInputBuffer
  implements EofSensor
{
  private static final Class SOCKET_TIMEOUT_CLASS = SocketTimeoutExceptionClass();
  private boolean eof;
  private final Socket socket;

  public SocketInputBuffer(Socket paramSocket, int paramInt, HttpParams paramHttpParams)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("Socket may not be null");
    this.socket = paramSocket;
    this.eof = false;
    int i = paramInt;
    if (paramInt < 0)
      i = paramSocket.getReceiveBufferSize();
    paramInt = i;
    if (i < 1024)
      paramInt = 1024;
    init(paramSocket.getInputStream(), paramInt, paramHttpParams);
  }

  private static Class SocketTimeoutExceptionClass()
  {
    try
    {
      Class localClass = Class.forName("java.net.SocketTimeoutException");
      return localClass;
      label8: return null;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      break label8;
    }
  }

  private static boolean isSocketTimeoutException(InterruptedIOException paramInterruptedIOException)
  {
    if (SOCKET_TIMEOUT_CLASS != null)
      return SOCKET_TIMEOUT_CLASS.isInstance(paramInterruptedIOException);
    return true;
  }

  protected int fillBuffer()
    throws IOException
  {
    int i = super.fillBuffer();
    boolean bool;
    if (i == -1)
      bool = true;
    else
      bool = false;
    this.eof = bool;
    return i;
  }

  // ERROR //
  public boolean isDataAvailable(int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 80	org/apache/http/impl/io/SocketInputBuffer:hasBufferedData	()Z
    //   4: istore_3
    //   5: iload_3
    //   6: ifne +81 -> 87
    //   9: aload_0
    //   10: getfield 36	org/apache/http/impl/io/SocketInputBuffer:socket	Ljava/net/Socket;
    //   13: invokevirtual 83	java/net/Socket:getSoTimeout	()I
    //   16: istore_2
    //   17: aload_0
    //   18: getfield 36	org/apache/http/impl/io/SocketInputBuffer:socket	Ljava/net/Socket;
    //   21: iload_1
    //   22: invokevirtual 87	java/net/Socket:setSoTimeout	(I)V
    //   25: aload_0
    //   26: invokevirtual 88	org/apache/http/impl/io/SocketInputBuffer:fillBuffer	()I
    //   29: pop
    //   30: aload_0
    //   31: invokevirtual 80	org/apache/http/impl/io/SocketInputBuffer:hasBufferedData	()Z
    //   34: istore 4
    //   36: aload_0
    //   37: getfield 36	org/apache/http/impl/io/SocketInputBuffer:socket	Ljava/net/Socket;
    //   40: iload_2
    //   41: invokevirtual 87	java/net/Socket:setSoTimeout	(I)V
    //   44: iload 4
    //   46: ireturn
    //   47: astore 5
    //   49: goto +27 -> 76
    //   52: astore 5
    //   54: aload 5
    //   56: invokestatic 90	org/apache/http/impl/io/SocketInputBuffer:isSocketTimeoutException	(Ljava/io/InterruptedIOException;)Z
    //   59: ifne +6 -> 65
    //   62: aload 5
    //   64: athrow
    //   65: aload_0
    //   66: getfield 36	org/apache/http/impl/io/SocketInputBuffer:socket	Ljava/net/Socket;
    //   69: iload_2
    //   70: invokevirtual 87	java/net/Socket:setSoTimeout	(I)V
    //   73: goto +14 -> 87
    //   76: aload_0
    //   77: getfield 36	org/apache/http/impl/io/SocketInputBuffer:socket	Ljava/net/Socket;
    //   80: iload_2
    //   81: invokevirtual 87	java/net/Socket:setSoTimeout	(I)V
    //   84: aload 5
    //   86: athrow
    //   87: iload_3
    //   88: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   17	36	47	finally
    //   54	65	47	finally
    //   17	36	52	java/io/InterruptedIOException
  }

  public boolean isEof()
  {
    return this.eof;
  }
}