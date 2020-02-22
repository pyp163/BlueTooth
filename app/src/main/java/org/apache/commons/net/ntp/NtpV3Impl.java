package org.apache.commons.net.ntp;

import java.net.DatagramPacket;

public class NtpV3Impl
  implements NtpV3Packet
{
  private static final int LI_INDEX = 0;
  private static final int LI_SHIFT = 6;
  private static final int MODE_INDEX = 0;
  private static final int MODE_SHIFT = 0;
  private static final int ORIGINATE_TIMESTAMP_INDEX = 24;
  private static final int POLL_INDEX = 2;
  private static final int PRECISION_INDEX = 3;
  private static final int RECEIVE_TIMESTAMP_INDEX = 32;
  private static final int REFERENCE_ID_INDEX = 12;
  private static final int REFERENCE_TIMESTAMP_INDEX = 16;
  private static final int ROOT_DELAY_INDEX = 4;
  private static final int ROOT_DISPERSION_INDEX = 8;
  private static final int STRATUM_INDEX = 1;
  private static final int TRANSMIT_TIMESTAMP_INDEX = 40;
  private static final int VERSION_INDEX = 0;
  private static final int VERSION_SHIFT = 3;
  private final byte[] buf = new byte[48];
  private volatile DatagramPacket dp;

  private int getInt(int paramInt)
  {
    int i = ui(this.buf[paramInt]);
    int j = ui(this.buf[(paramInt + 1)]);
    int k = ui(this.buf[(paramInt + 2)]);
    return ui(this.buf[(paramInt + 3)]) | (i << 24 | j << 16 | k << 8);
  }

  private long getLong(int paramInt)
  {
    return ul(this.buf[paramInt]) << 56 | ul(this.buf[(paramInt + 1)]) << 48 | ul(this.buf[(paramInt + 2)]) << 40 | ul(this.buf[(paramInt + 3)]) << 32 | ul(this.buf[(paramInt + 4)]) << 24 | ul(this.buf[(paramInt + 5)]) << 16 | ul(this.buf[(paramInt + 6)]) << 8 | ul(this.buf[(paramInt + 7)]);
  }

  private TimeStamp getTimestamp(int paramInt)
  {
    return new TimeStamp(getLong(paramInt));
  }

  private String idAsHex()
  {
    return Integer.toHexString(getReferenceId());
  }

  private String idAsIPAddress()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(ui(this.buf[12]));
    localStringBuilder.append(".");
    localStringBuilder.append(ui(this.buf[13]));
    localStringBuilder.append(".");
    localStringBuilder.append(ui(this.buf[14]));
    localStringBuilder.append(".");
    localStringBuilder.append(ui(this.buf[15]));
    return localStringBuilder.toString();
  }

  private String idAsString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i <= 3)
    {
      char c = (char)this.buf[(i + 12)];
      if (c == 0)
        break;
      localStringBuilder.append(c);
      i += 1;
    }
    return localStringBuilder.toString();
  }

  private void setTimestamp(int paramInt, TimeStamp paramTimeStamp)
  {
    long l;
    if (paramTimeStamp == null)
      l = 0L;
    else
      l = paramTimeStamp.ntpValue();
    int i = 7;
    while (i >= 0)
    {
      this.buf[(paramInt + i)] = ((byte)(int)(0xFF & l));
      l >>>= 8;
      i -= 1;
    }
  }

  protected static final int ui(byte paramByte)
  {
    return paramByte & 0xFF;
  }

  protected static final long ul(byte paramByte)
  {
    return paramByte & 0xFF;
  }

  public DatagramPacket getDatagramPacket()
  {
    try
    {
      if (this.dp == null)
      {
        this.dp = new DatagramPacket(this.buf, this.buf.length);
        this.dp.setPort(123);
      }
      DatagramPacket localDatagramPacket = this.dp;
      return localDatagramPacket;
    }
    finally
    {
    }
  }

  public int getLeapIndicator()
  {
    return ui(this.buf[0]) >> 6 & 0x3;
  }

  public int getMode()
  {
    return ui(this.buf[0]) >> 0 & 0x7;
  }

  public String getModeName()
  {
    return NtpUtils.getModeName(getMode());
  }

  public TimeStamp getOriginateTimeStamp()
  {
    return getTimestamp(24);
  }

  public int getPoll()
  {
    return this.buf[2];
  }

  public int getPrecision()
  {
    return this.buf[3];
  }

  public TimeStamp getReceiveTimeStamp()
  {
    return getTimestamp(32);
  }

  public int getReferenceId()
  {
    return getInt(12);
  }

  public String getReferenceIdString()
  {
    int i = getVersion();
    int j = getStratum();
    if ((i == 3) || (i == 4))
    {
      if ((j == 0) || (j == 1))
        break label57;
      if (i == 4)
        return idAsHex();
    }
    if (j >= 2)
      return idAsIPAddress();
    return idAsHex();
    label57: return idAsString();
  }

  public TimeStamp getReferenceTimeStamp()
  {
    return getTimestamp(16);
  }

  public int getRootDelay()
  {
    return getInt(4);
  }

  public double getRootDelayInMillisDouble()
  {
    return getRootDelay() / 65.536000000000001D;
  }

  public int getRootDispersion()
  {
    return getInt(8);
  }

  public long getRootDispersionInMillis()
  {
    return getRootDispersion() * 1000L / 65536L;
  }

  public double getRootDispersionInMillisDouble()
  {
    return getRootDispersion() / 65.536000000000001D;
  }

  public int getStratum()
  {
    return ui(this.buf[1]);
  }

  public TimeStamp getTransmitTimeStamp()
  {
    return getTimestamp(40);
  }

  public String getType()
  {
    return "NTP";
  }

  public int getVersion()
  {
    return ui(this.buf[0]) >> 3 & 0x7;
  }

  public void setDatagramPacket(DatagramPacket paramDatagramPacket)
  {
    byte[] arrayOfByte = paramDatagramPacket.getData();
    int j = paramDatagramPacket.getLength();
    int i = j;
    if (j > this.buf.length)
      i = this.buf.length;
    System.arraycopy(arrayOfByte, 0, this.buf, 0, i);
  }

  public void setLeapIndicator(int paramInt)
  {
    this.buf[0] = ((byte)((paramInt & 0x3) << 6 | this.buf[0] & 0x3F));
  }

  public void setMode(int paramInt)
  {
    this.buf[0] = ((byte)(paramInt & 0x7 | this.buf[0] & 0xF8));
  }

  public void setOriginateTimeStamp(TimeStamp paramTimeStamp)
  {
    setTimestamp(24, paramTimeStamp);
  }

  public void setPoll(int paramInt)
  {
    this.buf[2] = ((byte)(paramInt & 0xFF));
  }

  public void setPrecision(int paramInt)
  {
    this.buf[3] = ((byte)(paramInt & 0xFF));
  }

  public void setReceiveTimeStamp(TimeStamp paramTimeStamp)
  {
    setTimestamp(32, paramTimeStamp);
  }

  public void setReferenceId(int paramInt)
  {
    int j = 3;
    int i = paramInt;
    paramInt = j;
    while (paramInt >= 0)
    {
      this.buf[(paramInt + 12)] = ((byte)(i & 0xFF));
      i >>>= 8;
      paramInt -= 1;
    }
  }

  public void setReferenceTime(TimeStamp paramTimeStamp)
  {
    setTimestamp(16, paramTimeStamp);
  }

  public void setStratum(int paramInt)
  {
    this.buf[1] = ((byte)(paramInt & 0xFF));
  }

  public void setTransmitTime(TimeStamp paramTimeStamp)
  {
    setTimestamp(40, paramTimeStamp);
  }

  public void setVersion(int paramInt)
  {
    this.buf[0] = ((byte)((paramInt & 0x7) << 3 | this.buf[0] & 0xC7));
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[version:");
    localStringBuilder.append(getVersion());
    localStringBuilder.append(", mode:");
    localStringBuilder.append(getMode());
    localStringBuilder.append(", poll:");
    localStringBuilder.append(getPoll());
    localStringBuilder.append(", precision:");
    localStringBuilder.append(getPrecision());
    localStringBuilder.append(", delay:");
    localStringBuilder.append(getRootDelay());
    localStringBuilder.append(", dispersion(ms):");
    localStringBuilder.append(getRootDispersionInMillisDouble());
    localStringBuilder.append(", id:");
    localStringBuilder.append(getReferenceIdString());
    localStringBuilder.append(", xmitTime:");
    localStringBuilder.append(getTransmitTimeStamp().toDateString());
    localStringBuilder.append(" ]");
    return localStringBuilder.toString();
  }
}