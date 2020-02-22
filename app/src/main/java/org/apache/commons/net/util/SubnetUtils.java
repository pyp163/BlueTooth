package org.apache.commons.net.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubnetUtils
{
  private static final String IP_ADDRESS = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
  private static final int NBITS = 32;
  private static final String SLASH_FORMAT = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,3})";
  private static final Pattern addressPattern = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");
  private static final Pattern cidrPattern = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,3})");
  private int address = 0;
  private int broadcast = 0;
  private boolean inclusiveHostCount = false;
  private int netmask = 0;
  private int network = 0;

  public SubnetUtils(String paramString)
  {
    calculate(paramString);
  }

  public SubnetUtils(String paramString1, String paramString2)
  {
    calculate(toCidrNotation(paramString1, paramString2));
  }

  private void calculate(String paramString)
  {
    Object localObject = cidrPattern.matcher(paramString);
    if (((Matcher)localObject).matches())
    {
      this.address = matchAddress((Matcher)localObject);
      int j = Integer.parseInt(((Matcher)localObject).group(5));
      int i = 0;
      j = rangeCheck(j, 0, 32);
      while (i < j)
      {
        this.netmask |= 1 << 31 - i;
        i += 1;
      }
      this.network = (this.address & this.netmask);
      this.broadcast = (this.network | this.netmask);
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Could not parse [");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("]");
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }

  private String format(int[] paramArrayOfInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfInt.length)
    {
      localStringBuilder.append(paramArrayOfInt[i]);
      if (i != paramArrayOfInt.length - 1)
        localStringBuilder.append(".");
      i += 1;
    }
    return localStringBuilder.toString();
  }

  private int matchAddress(Matcher paramMatcher)
  {
    int i = 1;
    int j = 0;
    while (i <= 4)
    {
      j |= (rangeCheck(Integer.parseInt(paramMatcher.group(i)), -1, 255) & 0xFF) << (4 - i) * 8;
      i += 1;
    }
    return j;
  }

  private int rangeCheck(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 > paramInt2) && (paramInt1 <= paramInt3))
      return paramInt1;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Value [");
    localStringBuilder.append(paramInt1);
    localStringBuilder.append("] not in range (");
    localStringBuilder.append(paramInt2);
    localStringBuilder.append(",");
    localStringBuilder.append(paramInt3);
    localStringBuilder.append("]");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  private int[] toArray(int paramInt)
  {
    int[] arrayOfInt = new int[4];
    int i = 3;
    while (i >= 0)
    {
      arrayOfInt[i] |= paramInt >>> (3 - i) * 8 & 0xFF;
      i -= 1;
    }
    return arrayOfInt;
  }

  private String toCidrNotation(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString1);
    localStringBuilder.append("/");
    localStringBuilder.append(pop(toInteger(paramString2)));
    return localStringBuilder.toString();
  }

  private int toInteger(String paramString)
  {
    Object localObject = addressPattern.matcher(paramString);
    if (((Matcher)localObject).matches())
      return matchAddress((Matcher)localObject);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Could not parse [");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("]");
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }

  public final SubnetInfo getInfo()
  {
    return new SubnetInfo(null);
  }

  public boolean isInclusiveHostCount()
  {
    return this.inclusiveHostCount;
  }

  int pop(int paramInt)
  {
    paramInt -= (paramInt >>> 1 & 0x55555555);
    paramInt = (paramInt & 0x33333333) + (paramInt >>> 2 & 0x33333333);
    paramInt = 0xF0F0F0F & paramInt + (paramInt >>> 4);
    paramInt += (paramInt >>> 8);
    return paramInt + (paramInt >>> 16) & 0x3F;
  }

  public void setInclusiveHostCount(boolean paramBoolean)
  {
    this.inclusiveHostCount = paramBoolean;
  }

  public final class SubnetInfo
  {
    private SubnetInfo()
    {
    }

    private int address()
    {
      return SubnetUtils.this.address;
    }

    private int broadcast()
    {
      return SubnetUtils.this.broadcast;
    }

    private int high()
    {
      if (SubnetUtils.this.isInclusiveHostCount())
        return broadcast();
      if (broadcast() - network() > 1)
        return broadcast() - 1;
      return 0;
    }

    private boolean isInRange(int paramInt)
    {
      paramInt -= low();
      return (paramInt >= 0) && (paramInt <= high() - low());
    }

    private int low()
    {
      if (SubnetUtils.this.isInclusiveHostCount())
        return network();
      if (broadcast() - network() > 1)
        return network() + 1;
      return 0;
    }

    private int netmask()
    {
      return SubnetUtils.this.netmask;
    }

    private int network()
    {
      return SubnetUtils.this.network;
    }

    public int asInteger(String paramString)
    {
      return SubnetUtils.this.toInteger(paramString);
    }

    public String getAddress()
    {
      return SubnetUtils.this.format(SubnetUtils.access$500(SubnetUtils.this, address()));
    }

    public int getAddressCount()
    {
      int j = broadcast();
      int k = network();
      if (SubnetUtils.this.isInclusiveHostCount())
        i = 1;
      else
        i = -1;
      j = j - k + i;
      int i = j;
      if (j < 0)
        i = 0;
      return i;
    }

    public String[] getAllAddresses()
    {
      String[] arrayOfString = new String[getAddressCount()];
      int j = low();
      int i = 0;
      while (j <= high())
      {
        arrayOfString[i] = SubnetUtils.this.format(SubnetUtils.access$500(SubnetUtils.this, j));
        j += 1;
        i += 1;
      }
      return arrayOfString;
    }

    public String getBroadcastAddress()
    {
      return SubnetUtils.this.format(SubnetUtils.access$500(SubnetUtils.this, broadcast()));
    }

    public String getCidrSignature()
    {
      return SubnetUtils.this.toCidrNotation(SubnetUtils.access$600(SubnetUtils.this, SubnetUtils.access$500(SubnetUtils.this, address())), SubnetUtils.access$600(SubnetUtils.this, SubnetUtils.access$500(SubnetUtils.this, netmask())));
    }

    public String getHighAddress()
    {
      return SubnetUtils.this.format(SubnetUtils.access$500(SubnetUtils.this, high()));
    }

    public String getLowAddress()
    {
      return SubnetUtils.this.format(SubnetUtils.access$500(SubnetUtils.this, low()));
    }

    public String getNetmask()
    {
      return SubnetUtils.this.format(SubnetUtils.access$500(SubnetUtils.this, netmask()));
    }

    public String getNetworkAddress()
    {
      return SubnetUtils.this.format(SubnetUtils.access$500(SubnetUtils.this, network()));
    }

    public boolean isInRange(String paramString)
    {
      return isInRange(SubnetUtils.this.toInteger(paramString));
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("CIDR Signature:\t[");
      localStringBuilder.append(getCidrSignature());
      localStringBuilder.append("]");
      localStringBuilder.append(" Netmask: [");
      localStringBuilder.append(getNetmask());
      localStringBuilder.append("]\n");
      localStringBuilder.append("Network:\t[");
      localStringBuilder.append(getNetworkAddress());
      localStringBuilder.append("]\n");
      localStringBuilder.append("Broadcast:\t[");
      localStringBuilder.append(getBroadcastAddress());
      localStringBuilder.append("]\n");
      localStringBuilder.append("First Address:\t[");
      localStringBuilder.append(getLowAddress());
      localStringBuilder.append("]\n");
      localStringBuilder.append("Last Address:\t[");
      localStringBuilder.append(getHighAddress());
      localStringBuilder.append("]\n");
      localStringBuilder.append("# Addresses:\t[");
      localStringBuilder.append(getAddressCount());
      localStringBuilder.append("]\n");
      return localStringBuilder.toString();
    }
  }
}