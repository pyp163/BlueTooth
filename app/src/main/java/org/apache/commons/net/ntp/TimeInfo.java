package org.apache.commons.net.ntp;

import java.util.ArrayList;
import java.util.List;

public class TimeInfo
{
  private List<String> _comments;
  private Long _delay;
  private boolean _detailsComputed;
  private final NtpV3Packet _message;
  private Long _offset;
  private final long _returnTime;

  public TimeInfo(NtpV3Packet paramNtpV3Packet, long paramLong)
  {
    this(paramNtpV3Packet, paramLong, null, true);
  }

  public TimeInfo(NtpV3Packet paramNtpV3Packet, long paramLong, List<String> paramList)
  {
    this(paramNtpV3Packet, paramLong, paramList, true);
  }

  public TimeInfo(NtpV3Packet paramNtpV3Packet, long paramLong, List<String> paramList, boolean paramBoolean)
  {
    if (paramNtpV3Packet == null)
      throw new IllegalArgumentException("message cannot be null");
    this._returnTime = paramLong;
    this._message = paramNtpV3Packet;
    this._comments = paramList;
    if (paramBoolean)
      computeDetails();
  }

  public TimeInfo(NtpV3Packet paramNtpV3Packet, long paramLong, boolean paramBoolean)
  {
    this(paramNtpV3Packet, paramLong, null, paramBoolean);
  }

  public void addComment(String paramString)
  {
    if (this._comments == null)
      this._comments = new ArrayList();
    this._comments.add(paramString);
  }

  public void computeDetails()
  {
    if (this._detailsComputed)
      return;
    this._detailsComputed = true;
    if (this._comments == null)
      this._comments = new ArrayList();
    TimeStamp localTimeStamp1 = this._message.getOriginateTimeStamp();
    long l3 = localTimeStamp1.getTime();
    TimeStamp localTimeStamp2 = this._message.getReceiveTimeStamp();
    long l4 = localTimeStamp2.getTime();
    TimeStamp localTimeStamp3 = this._message.getTransmitTimeStamp();
    long l5 = localTimeStamp3.getTime();
    long l2 = localTimeStamp1.ntpValue();
    long l1 = 0L;
    if (l2 == 0L)
    {
      if (localTimeStamp3.ntpValue() != 0L)
      {
        this._offset = Long.valueOf(l5 - this._returnTime);
        this._comments.add("Error: zero orig time -- cannot compute delay");
        return;
      }
      this._comments.add("Error: zero orig time -- cannot compute delay/offset");
      return;
    }
    if ((localTimeStamp2.ntpValue() != 0L) && (localTimeStamp3.ntpValue() != 0L))
    {
      l2 = this._returnTime - l3;
      if (l5 < l4)
      {
        this._comments.add("Error: xmitTime < rcvTime");
      }
      else
      {
        long l6 = l5 - l4;
        if (l6 <= l2)
        {
          l1 = l2 - l6;
          break label269;
        }
        if (l6 - l2 == 1L)
        {
          if (l2 != 0L)
          {
            this._comments.add("Info: processing time > total network time by 1 ms -> assume zero delay");
            break label269;
          }
        }
        else
          this._comments.add("Warning: processing time > total network time");
      }
      l1 = l2;
      label269: this._delay = Long.valueOf(l1);
      if (l3 > this._returnTime)
        this._comments.add("Error: OrigTime > DestRcvTime");
      this._offset = Long.valueOf((l4 - l3 + (l5 - this._returnTime)) / 2L);
      return;
    }
    this._comments.add("Warning: zero rcvNtpTime or xmitNtpTime");
    if (l3 > this._returnTime)
      this._comments.add("Error: OrigTime > DestRcvTime");
    else
      this._delay = Long.valueOf(this._returnTime - l3);
    if (localTimeStamp2.ntpValue() != 0L)
    {
      this._offset = Long.valueOf(l4 - l3);
      return;
    }
    if (localTimeStamp3.ntpValue() != 0L)
      this._offset = Long.valueOf(l5 - this._returnTime);
  }

  public List<String> getComments()
  {
    return this._comments;
  }

  public Long getDelay()
  {
    return this._delay;
  }

  public NtpV3Packet getMessage()
  {
    return this._message;
  }

  public Long getOffset()
  {
    return this._offset;
  }

  public long getReturnTime()
  {
    return this._returnTime;
  }
}