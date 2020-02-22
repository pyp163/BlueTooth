package com.facebook.soloader;

import javax.annotation.Nullable;

class MergedSoMapping
{
  static void invokeJniOnload(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unknown library: ");
    localStringBuilder.append(paramString);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  @Nullable
  static String mapLibName(String paramString)
  {
    return null;
  }
}