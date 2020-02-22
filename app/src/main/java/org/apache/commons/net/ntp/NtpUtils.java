package org.apache.commons.net.ntp;

public final class NtpUtils
{
  public static String getHostAddress(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt >>> 24 & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >>> 16 & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >>> 8 & 0xFF);
    localStringBuilder.append(".");
    localStringBuilder.append(paramInt >>> 0 & 0xFF);
    return localStringBuilder.toString();
  }

  public static String getModeName(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "Unknown";
    case 7:
      return "Private";
    case 6:
      return "Control";
    case 5:
      return "Broadcast";
    case 4:
      return "Server";
    case 3:
      return "Client";
    case 2:
      return "Symmetric Passive";
    case 1:
      return "Symmetric Active";
    case 0:
    }
    return "Reserved";
  }

  public static String getRefAddress(NtpV3Packet paramNtpV3Packet)
  {
    int i;
    if (paramNtpV3Packet == null)
      i = 0;
    else
      i = paramNtpV3Packet.getReferenceId();
    return getHostAddress(i);
  }

  public static String getReferenceClock(NtpV3Packet paramNtpV3Packet)
  {
    if (paramNtpV3Packet == null)
      return "";
    int j = paramNtpV3Packet.getReferenceId();
    if (j == 0)
      return "";
    paramNtpV3Packet = new StringBuilder(4);
    int i = 24;
    while (i >= 0)
    {
      char c = (char)(j >>> i & 0xFF);
      if (c == 0)
        break;
      if (!Character.isLetterOrDigit(c))
        return "";
      paramNtpV3Packet.append(c);
      i -= 8;
    }
    return paramNtpV3Packet.toString();
  }
}