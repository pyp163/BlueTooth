package com.facebook.imagepipeline.nativecode;

public class WebpTranscoderFactory
{
  private static WebpTranscoder sWebpTranscoder;
  public static boolean sWebpTranscoderPresent = false;

  static
  {
    try
    {
      sWebpTranscoder = (WebpTranscoder)Class.forName("com.facebook.imagepipeline.nativecode.WebpTranscoderImpl").newInstance();
      sWebpTranscoderPresent = true;
      return;
      label19: sWebpTranscoderPresent = false;
      return;
    }
    catch (Throwable localThrowable)
    {
      break label19;
    }
  }

  public static WebpTranscoder getWebpTranscoder()
  {
    return sWebpTranscoder;
  }
}