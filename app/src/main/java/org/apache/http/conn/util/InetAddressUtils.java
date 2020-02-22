package org.apache.http.conn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.annotation.Immutable;

@Immutable
public class InetAddressUtils
{
  private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
  private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
  private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

  public static boolean isIPv4Address(String paramString)
  {
    return IPV4_PATTERN.matcher(paramString).matches();
  }

  public static boolean isIPv6Address(String paramString)
  {
    return (isIPv6StdAddress(paramString)) || (isIPv6HexCompressedAddress(paramString));
  }

  public static boolean isIPv6HexCompressedAddress(String paramString)
  {
    return IPV6_HEX_COMPRESSED_PATTERN.matcher(paramString).matches();
  }

  public static boolean isIPv6StdAddress(String paramString)
  {
    return IPV6_STD_PATTERN.matcher(paramString).matches();
  }
}