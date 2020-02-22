package com.facebook.imagepipeline.nativecode;

import com.facebook.imageformat.ImageFormat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface WebpTranscoder
{
  public abstract boolean isWebpNativelySupported(ImageFormat paramImageFormat);

  public abstract void transcodeWebpToJpeg(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt)
    throws IOException;

  public abstract void transcodeWebpToPng(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException;
}