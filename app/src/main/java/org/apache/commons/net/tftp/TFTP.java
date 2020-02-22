package org.apache.commons.net.tftp;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import org.apache.commons.net.DatagramSocketClient;

public class TFTP extends DatagramSocketClient
{
  public static final int ASCII_MODE = 0;
  public static final int BINARY_MODE = 1;
  public static final int DEFAULT_PORT = 69;
  public static final int DEFAULT_TIMEOUT = 5000;
  public static final int IMAGE_MODE = 1;
  public static final int NETASCII_MODE = 0;
  public static final int OCTET_MODE = 1;
  static final int PACKET_SIZE = 516;
  private byte[] __receiveBuffer;
  private DatagramPacket __receiveDatagram;
  private DatagramPacket __sendDatagram;
  byte[] _sendBuffer;

  public TFTP()
  {
    setDefaultTimeout(5000);
    this.__receiveBuffer = null;
    this.__receiveDatagram = null;
  }

  public static final String getModeName(int paramInt)
  {
    return TFTPRequestPacket._modeStrings[paramInt];
  }

  public final void beginBufferedOps()
  {
    this.__receiveBuffer = new byte[516];
    this.__receiveDatagram = new DatagramPacket(this.__receiveBuffer, this.__receiveBuffer.length);
    this._sendBuffer = new byte[516];
    this.__sendDatagram = new DatagramPacket(this._sendBuffer, this._sendBuffer.length);
  }

  public final TFTPPacket bufferedReceive()
    throws IOException, InterruptedIOException, SocketException, TFTPPacketException
  {
    this.__receiveDatagram.setData(this.__receiveBuffer);
    this.__receiveDatagram.setLength(this.__receiveBuffer.length);
    this._socket_.receive(this.__receiveDatagram);
    return TFTPPacket.newTFTPPacket(this.__receiveDatagram);
  }

  public final void bufferedSend(TFTPPacket paramTFTPPacket)
    throws IOException
  {
    this._socket_.send(paramTFTPPacket._newDatagram(this.__sendDatagram, this._sendBuffer));
  }

  public final void discardPackets()
    throws IOException
  {
    DatagramPacket localDatagramPacket = new DatagramPacket(new byte[516], 516);
    int i = getSoTimeout();
    setSoTimeout(1);
    try
    {
      while (true)
        this._socket_.receive(localDatagramPacket);
      label37: setSoTimeout(i);
      return;
    }
    catch (SocketException|InterruptedIOException localSocketException)
    {
      break label37;
    }
  }

  public final void endBufferedOps()
  {
    this.__receiveBuffer = null;
    this.__receiveDatagram = null;
    this._sendBuffer = null;
    this.__sendDatagram = null;
  }

  public final TFTPPacket receive()
    throws IOException, InterruptedIOException, SocketException, TFTPPacketException
  {
    DatagramPacket localDatagramPacket = new DatagramPacket(new byte[516], 516);
    this._socket_.receive(localDatagramPacket);
    return TFTPPacket.newTFTPPacket(localDatagramPacket);
  }

  public final void send(TFTPPacket paramTFTPPacket)
    throws IOException
  {
    this._socket_.send(paramTFTPPacket.newDatagram());
  }
}