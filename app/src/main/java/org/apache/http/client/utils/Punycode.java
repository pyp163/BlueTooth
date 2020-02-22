package org.apache.http.client.utils;

import org.apache.http.annotation.Immutable;

@Immutable
public class Punycode
{
  private static final Idn impl;

  static
  {
    try
    {
      Object localObject = new JdkIdn();
      break label19;
      label11: localObject = new Rfc3492Idn();
      label19: impl = (Idn)localObject;
      return;
    }
    catch (Exception localException)
    {
      break label11;
    }
  }

  public static String toUnicode(String paramString)
  {
    return impl.toUnicode(paramString);
  }
}