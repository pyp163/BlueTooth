package org.apache.commons.net.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public final class Util
{
  public static final int DEFAULT_COPY_BUFFER_SIZE = 1024;

  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
    }
  }

  public static void closeQuietly(Socket paramSocket)
  {
    if (paramSocket != null);
    try
    {
      paramSocket.close();
      return;
    }
    catch (IOException paramSocket)
    {
    }
  }

  public static final long copyReader(Reader paramReader, Writer paramWriter)
    throws CopyStreamException
  {
    return copyReader(paramReader, paramWriter, 1024);
  }

  public static final long copyReader(Reader paramReader, Writer paramWriter, int paramInt)
    throws CopyStreamException
  {
    return copyReader(paramReader, paramWriter, paramInt, -1L, null);
  }

  public static final long copyReader(Reader paramReader, Writer paramWriter, int paramInt, long paramLong, CopyStreamListener paramCopyStreamListener)
    throws CopyStreamException
  {
    char[] arrayOfChar = new char[paramInt];
    long l1 = 0L;
    try
    {
      label136: 
      while (true)
      {
        paramInt = paramReader.read(arrayOfChar);
        if (paramInt == -1)
          break;
        long l2;
        if (paramInt == 0)
        {
          paramInt = paramReader.read();
          if (paramInt < 0)
            return l1;
          paramWriter.write(paramInt);
          paramWriter.flush();
          l2 = l1 + 1L;
          l1 = l2;
          if (paramCopyStreamListener != null)
            l1 = l2;
        }
        else
        {
          try
          {
            paramCopyStreamListener.bytesTransferred(l2, paramInt, paramLong);
            l1 = l2;
            break label136;
            paramWriter.write(arrayOfChar, 0, paramInt);
            paramWriter.flush();
            l2 = l1 + paramInt;
            l1 = l2;
            if (paramCopyStreamListener != null)
            {
              l1 = l2;
              paramCopyStreamListener.bytesTransferred(l2, paramInt, paramLong);
              l1 = l2;
            }
          }
          catch (IOException paramReader)
          {
            break label143;
          }
        }
      }
      return l1;
    }
    catch (IOException paramReader)
    {
    }
    label143: throw new CopyStreamException("IOException caught while copying.", l1, paramReader);
  }

  public static final long copyStream(InputStream paramInputStream, OutputStream paramOutputStream)
    throws CopyStreamException
  {
    return copyStream(paramInputStream, paramOutputStream, 1024);
  }

  public static final long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt)
    throws CopyStreamException
  {
    return copyStream(paramInputStream, paramOutputStream, paramInt, -1L, null);
  }

  public static final long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt, long paramLong, CopyStreamListener paramCopyStreamListener)
    throws CopyStreamException
  {
    return copyStream(paramInputStream, paramOutputStream, paramInt, paramLong, paramCopyStreamListener, true);
  }

  public static final long copyStream(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt, long paramLong, CopyStreamListener paramCopyStreamListener, boolean paramBoolean)
    throws CopyStreamException
  {
    byte[] arrayOfByte = new byte[paramInt];
    long l1 = 0L;
    try
    {
      label146: 
      while (true)
      {
        paramInt = paramInputStream.read(arrayOfByte);
        if (paramInt == -1)
          break;
        long l2;
        if (paramInt == 0)
        {
          paramInt = paramInputStream.read();
          if (paramInt < 0)
            return l1;
          paramOutputStream.write(paramInt);
          if (paramBoolean)
            paramOutputStream.flush();
          l2 = l1 + 1L;
          l1 = l2;
          if (paramCopyStreamListener != null)
            l1 = l2;
        }
        else
        {
          try
          {
            paramCopyStreamListener.bytesTransferred(l2, 1, paramLong);
            l1 = l2;
            break label146;
            paramOutputStream.write(arrayOfByte, 0, paramInt);
            if (paramBoolean)
              paramOutputStream.flush();
            l2 = l1 + paramInt;
            l1 = l2;
            if (paramCopyStreamListener != null)
            {
              l1 = l2;
              paramCopyStreamListener.bytesTransferred(l2, paramInt, paramLong);
              l1 = l2;
            }
          }
          catch (IOException paramInputStream)
          {
            break label153;
          }
        }
      }
      return l1;
    }
    catch (IOException paramInputStream)
    {
    }
    label153: throw new CopyStreamException("IOException caught while copying.", l1, paramInputStream);
  }
}