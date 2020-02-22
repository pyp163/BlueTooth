package org.apache.commons.net.tftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.commons.net.io.FromNetASCIIOutputStream;
import org.apache.commons.net.io.ToNetASCIIInputStream;

public class TFTPClient extends TFTP
{
  public static final int DEFAULT_MAX_TIMEOUTS = 5;
  private int __maxTimeouts = 5;

  public int getMaxTimeouts()
  {
    return this.__maxTimeouts;
  }

  public int receiveFile(String paramString1, int paramInt, OutputStream paramOutputStream, String paramString2)
    throws UnknownHostException, IOException
  {
    return receiveFile(paramString1, paramInt, paramOutputStream, InetAddress.getByName(paramString2), 69);
  }

  public int receiveFile(String paramString1, int paramInt1, OutputStream paramOutputStream, String paramString2, int paramInt2)
    throws UnknownHostException, IOException
  {
    return receiveFile(paramString1, paramInt1, paramOutputStream, InetAddress.getByName(paramString2), paramInt2);
  }

  public int receiveFile(String paramString, int paramInt, OutputStream paramOutputStream, InetAddress paramInetAddress)
    throws IOException
  {
    return receiveFile(paramString, paramInt, paramOutputStream, paramInetAddress, 69);
  }

  public int receiveFile(String paramString, int paramInt1, OutputStream paramOutputStream, InetAddress paramInetAddress, int paramInt2)
    throws IOException
  {
    TFTPAckPacket localTFTPAckPacket = new TFTPAckPacket(paramInetAddress, paramInt2, 0);
    beginBufferedOps();
    Object localObject1;
    if (paramInt1 == 0)
      localObject1 = new FromNetASCIIOutputStream(paramOutputStream);
    else
      localObject1 = paramOutputStream;
    paramString = new TFTPReadRequestPacket(paramInetAddress, paramInt2, paramString, paramInt1);
    paramOutputStream = null;
    int k = 0;
    int i = 1;
    paramInt2 = 0;
    paramInt1 = 0;
    int j = 0;
    while (true)
    {
      bufferedSend(paramString);
      OutputStream localOutputStream = paramOutputStream;
      int m = paramInt2;
      Object localObject2 = paramInetAddress;
      label84: paramInt2 = 0;
      label87: paramOutputStream = localOutputStream;
      if (paramInt2 < this.__maxTimeouts);
      try
      {
        try
        {
          paramOutputStream = bufferedReceive();
        }
        catch (TFTPPacketException paramString)
        {
          endBufferedOps();
          paramOutputStream = new StringBuilder();
          paramOutputStream.append("Bad packet: ");
          paramOutputStream.append(paramString.getMessage());
          throw new IOException(paramOutputStream.toString());
        }
        label148: int n = paramInt2 + 1;
        paramInt2 = n;
        if (n < this.__maxTimeouts)
          break label87;
        endBufferedOps();
        throw new IOException("Connection timed out.");
        label181: n = paramInt2 + 1;
        paramInt2 = n;
        if (n < this.__maxTimeouts)
          break label87;
        endBufferedOps();
        throw new IOException("Connection timed out.");
        paramInetAddress = (InetAddress)localObject2;
        paramInt2 = m;
        if (k == 0)
        {
          m = paramOutputStream.getPort();
          localTFTPAckPacket.setPort(m);
          paramInetAddress = (InetAddress)localObject2;
          paramInt2 = m;
          if (!((InetAddress)localObject2).equals(paramOutputStream.getAddress()))
          {
            paramInetAddress = paramOutputStream.getAddress();
            localTFTPAckPacket.setAddress(paramInetAddress);
            paramString.setAddress(paramInetAddress);
            paramInt2 = m;
          }
        }
        if ((paramInetAddress.equals(paramOutputStream.getAddress())) && (paramOutputStream.getPort() == paramInt2))
        {
          j = paramOutputStream.getType();
          if (j != 3)
          {
            if (j != 5)
            {
              endBufferedOps();
              throw new IOException("Received unexpected packet type.");
            }
            paramString = (TFTPErrorPacket)paramOutputStream;
            endBufferedOps();
            paramOutputStream = new StringBuilder();
            paramOutputStream.append("Error code ");
            paramOutputStream.append(paramString.getError());
            paramOutputStream.append(" received: ");
            paramOutputStream.append(paramString.getMessage());
            throw new IOException(paramOutputStream.toString());
          }
          localObject2 = (TFTPDataPacket)paramOutputStream;
          k = ((TFTPDataPacket)localObject2).getDataLength();
          j = ((TFTPDataPacket)localObject2).getBlockNumber();
          m = 65535;
          if (j == i)
          {
            try
            {
              ((OutputStream)localObject1).write(((TFTPDataPacket)localObject2).getData(), ((TFTPDataPacket)localObject2).getDataOffset(), k);
              m = i + 1;
              i = m;
              if (m > 65535)
                i = 0;
              localTFTPAckPacket.setBlockNumber(j);
              paramInt1 += k;
              paramString = localTFTPAckPacket;
            }
            catch (IOException paramString)
            {
              bufferedSend(new TFTPErrorPacket(paramInetAddress, paramInt2, 3, "File write failed."));
              endBufferedOps();
              throw paramString;
            }
          }
          else
          {
            discardPackets();
            if (i != 0)
              m = i - 1;
            if (j != m)
              break label548;
          }
          m = k;
          k = j;
          j = m;
          break label596;
          label548: n = k;
          k = j;
          localObject2 = paramInetAddress;
          m = paramInt2;
          localOutputStream = paramOutputStream;
          j = n;
          break label84;
        }
        bufferedSend(new TFTPErrorPacket(paramOutputStream.getAddress(), paramOutputStream.getPort(), 5, "Unexpected host or port."));
        label596: if (j != 512)
        {
          bufferedSend(paramString);
          endBufferedOps();
          return paramInt1;
        }
      }
      catch (SocketException paramOutputStream)
      {
        break label181;
      }
      catch (InterruptedIOException paramOutputStream)
      {
        break label148;
      }
    }
  }

  public void sendFile(String paramString1, int paramInt, InputStream paramInputStream, String paramString2)
    throws UnknownHostException, IOException
  {
    sendFile(paramString1, paramInt, paramInputStream, InetAddress.getByName(paramString2), 69);
  }

  public void sendFile(String paramString1, int paramInt1, InputStream paramInputStream, String paramString2, int paramInt2)
    throws UnknownHostException, IOException
  {
    sendFile(paramString1, paramInt1, paramInputStream, InetAddress.getByName(paramString2), paramInt2);
  }

  public void sendFile(String paramString, int paramInt, InputStream paramInputStream, InetAddress paramInetAddress)
    throws IOException
  {
    sendFile(paramString, paramInt, paramInputStream, paramInetAddress, 69);
  }

  public void sendFile(String paramString, int paramInt1, InputStream paramInputStream, InetAddress paramInetAddress, int paramInt2)
    throws IOException
  {
    TFTPDataPacket localTFTPDataPacket = new TFTPDataPacket(paramInetAddress, paramInt2, 0, this._sendBuffer, 4, 0);
    beginBufferedOps();
    if (paramInt1 == 0)
      paramInputStream = new ToNetASCIIInputStream(paramInputStream);
    Object localObject = new TFTPWriteRequestPacket(paramInetAddress, paramInt2, paramString, paramInt1);
    paramString = null;
    paramInt2 = 1;
    paramInt1 = 0;
    int k = 0;
    int j = 0;
    int i = 0;
    InputStream localInputStream = paramInputStream;
    while (true)
    {
      bufferedSend((TFTPPacket)localObject);
      String str = paramString;
      int m = paramInt2;
      paramInputStream = paramInetAddress;
      int n = i;
      label94: paramInt2 = 0;
      label97: paramString = str;
      if (paramInt2 < this.__maxTimeouts);
      try
      {
        try
        {
          paramString = bufferedReceive();
        }
        catch (TFTPPacketException paramString)
        {
          endBufferedOps();
          paramInputStream = new StringBuilder();
          paramInputStream.append("Bad packet: ");
          paramInputStream.append(paramString.getMessage());
          throw new IOException(paramInputStream.toString());
        }
        label158: i = paramInt2 + 1;
        paramInt2 = i;
        if (i < this.__maxTimeouts)
          break label97;
        endBufferedOps();
        throw new IOException("Connection timed out.");
        label191: i = paramInt2 + 1;
        paramInt2 = i;
        if (i < this.__maxTimeouts)
          break label97;
        endBufferedOps();
        throw new IOException("Connection timed out.");
        i = n;
        paramInetAddress = paramInputStream;
        paramInt2 = m;
        if (m != 0)
        {
          i = paramString.getPort();
          localTFTPDataPacket.setPort(i);
          paramInetAddress = paramInputStream;
          if (!paramInputStream.equals(paramString.getAddress()))
          {
            paramInetAddress = paramString.getAddress();
            localTFTPDataPacket.setAddress(paramInetAddress);
            ((TFTPPacket)localObject).setAddress(paramInetAddress);
          }
          paramInt2 = 0;
        }
        if ((paramInetAddress.equals(paramString.getAddress())) && (paramString.getPort() == i))
        {
          switch (paramString.getType())
          {
          default:
            endBufferedOps();
            throw new IOException("Received unexpected packet type.");
          case 5:
            paramString = (TFTPErrorPacket)paramString;
            endBufferedOps();
            paramInputStream = new StringBuilder();
            paramInputStream.append("Error code ");
            paramInputStream.append(paramString.getError());
            paramInputStream.append(" received: ");
            paramInputStream.append(paramString.getMessage());
            throw new IOException(paramInputStream.toString());
          case 4:
          }
          int i2 = ((TFTPAckPacket)paramString).getBlockNumber();
          int i1 = 65535;
          if (i2 == k)
          {
            k += 1;
            j = k;
            if (k > 65535)
              j = 0;
            if (paramInt1 != 0)
              break label639;
            k = 0;
            m = 512;
            n = 4;
            while (m > 0)
            {
              i1 = localInputStream.read(this._sendBuffer, n, m);
              if (i1 <= 0)
                break;
              n += i1;
              m -= i1;
              k += i1;
            }
            if (k < 512)
              paramInt1 = 1;
            localTFTPDataPacket.setBlockNumber(j);
            localTFTPDataPacket.setData(this._sendBuffer, 4, k);
            m = k;
            localObject = localTFTPDataPacket;
            k = j;
            j = m;
            break label630;
          }
          discardPackets();
          if (k != 0)
            i1 = k - 1;
          n = i;
          paramInputStream = paramInetAddress;
          m = paramInt2;
          str = paramString;
          if (i2 != i1)
            break label94;
          break label630;
        }
        bufferedSend(new TFTPErrorPacket(paramString.getAddress(), paramString.getPort(), 5, "Unexpected host or port."));
        label630: if ((j <= 0) && (paramInt1 == 0))
        {
          label639: endBufferedOps();
          return;
        }
      }
      catch (SocketException paramString)
      {
        break label191;
      }
      catch (InterruptedIOException paramString)
      {
        break label158;
      }
    }
  }

  public void setMaxTimeouts(int paramInt)
  {
    if (paramInt < 1)
    {
      this.__maxTimeouts = 1;
      return;
    }
    this.__maxTimeouts = paramInt;
  }
}