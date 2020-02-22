package com.facebook.imagepipeline.nativecode;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@DoNotStrip
public class JpegTranscoder
{
  public static final int MAX_QUALITY = 100;
  public static final int MAX_SCALE_NUMERATOR = 16;
  public static final int MIN_QUALITY = 0;
  public static final int MIN_SCALE_NUMERATOR = 1;
  public static final int SCALE_DENOMINATOR = 8;

  static
  {
    ImagePipelineNativeLoader.load();
  }

  public static boolean isExifOrientationAllowed(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return false;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    }
    return true;
  }

  public static boolean isRotationAngleAllowed(int paramInt)
  {
    return (paramInt >= 0) && (paramInt <= 270) && (paramInt % 90 == 0);
  }

  @DoNotStrip
  private static native void nativeTranscodeJpeg(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt1, int paramInt2, int paramInt3)
    throws IOException;

  @DoNotStrip
  private static native void nativeTranscodeJpegWithExifOrientation(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt1, int paramInt2, int paramInt3)
    throws IOException;

  public static void transcodeJpeg(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramInt2 >= 1)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt2 <= 16)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt3 >= 0)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt3 <= 100)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    Preconditions.checkArgument(isRotationAngleAllowed(paramInt1));
    if (paramInt2 == 8)
    {
      bool1 = bool2;
      if (paramInt1 == 0);
    }
    else
    {
      bool1 = true;
    }
    Preconditions.checkArgument(bool1, "no transformation requested");
    nativeTranscodeJpeg((InputStream)Preconditions.checkNotNull(paramInputStream), (OutputStream)Preconditions.checkNotNull(paramOutputStream), paramInt1, paramInt2, paramInt3);
  }

  public static void transcodeJpegWithExifOrientation(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramInt2 >= 1)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt2 <= 16)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt3 >= 0)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    if (paramInt3 <= 100)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    Preconditions.checkArgument(isExifOrientationAllowed(paramInt1));
    if (paramInt2 == 8)
    {
      bool1 = bool2;
      if (paramInt1 == 1);
    }
    else
    {
      bool1 = true;
    }
    Preconditions.checkArgument(bool1, "no transformation requested");
    nativeTranscodeJpegWithExifOrientation((InputStream)Preconditions.checkNotNull(paramInputStream), (OutputStream)Preconditions.checkNotNull(paramOutputStream), paramInt1, paramInt2, paramInt3);
  }
}