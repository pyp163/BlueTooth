package com.facebook.imageformat;

import com.facebook.common.internal.Ints;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.webp.WebpSupportStatus;
import javax.annotation.Nullable;

public class DefaultImageFormatChecker
  implements ImageFormat.FormatChecker
{
  private static final byte[] BMP_HEADER;
  private static final int BMP_HEADER_LENGTH;
  private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
  private static final byte[] GIF_HEADER_87A;
  private static final byte[] GIF_HEADER_89A;
  private static final int GIF_HEADER_LENGTH = 6;
  private static final int HEIF_HEADER_LENGTH = ImageFormatCheckerUtils.asciiBytes(localStringBuilder.toString()).length;
  private static final String HEIF_HEADER_PREFIX = "ftyp";
  private static final String[] HEIF_HEADER_SUFFIXES;
  private static final byte[] JPEG_HEADER = { -1, -40, -1 };
  private static final int JPEG_HEADER_LENGTH = JPEG_HEADER.length;
  private static final byte[] PNG_HEADER = { -119, 80, 78, 71, 13, 10, 26, 10 };
  private static final int PNG_HEADER_LENGTH = PNG_HEADER.length;
  private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
  final int MAX_HEADER_LENGTH = Ints.max(new int[] { 21, 20, JPEG_HEADER_LENGTH, PNG_HEADER_LENGTH, 6, BMP_HEADER_LENGTH, HEIF_HEADER_LENGTH });

  static
  {
    GIF_HEADER_87A = ImageFormatCheckerUtils.asciiBytes("GIF87a");
    GIF_HEADER_89A = ImageFormatCheckerUtils.asciiBytes("GIF89a");
    BMP_HEADER = ImageFormatCheckerUtils.asciiBytes("BM");
    BMP_HEADER_LENGTH = BMP_HEADER.length;
    HEIF_HEADER_SUFFIXES = new String[] { "heic", "heix", "hevc", "hevx" };
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ftyp");
    localStringBuilder.append(HEIF_HEADER_SUFFIXES[0]);
  }

  private static ImageFormat getWebpFormat(byte[] paramArrayOfByte, int paramInt)
  {
    Preconditions.checkArgument(WebpSupportStatus.isWebpHeader(paramArrayOfByte, 0, paramInt));
    if (WebpSupportStatus.isSimpleWebpHeader(paramArrayOfByte, 0))
      return DefaultImageFormats.WEBP_SIMPLE;
    if (WebpSupportStatus.isLosslessWebpHeader(paramArrayOfByte, 0))
      return DefaultImageFormats.WEBP_LOSSLESS;
    if (WebpSupportStatus.isExtendedWebpHeader(paramArrayOfByte, 0, paramInt))
    {
      if (WebpSupportStatus.isAnimatedWebpHeader(paramArrayOfByte, 0))
        return DefaultImageFormats.WEBP_ANIMATED;
      if (WebpSupportStatus.isExtendedWebpHeaderWithAlpha(paramArrayOfByte, 0))
        return DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA;
      return DefaultImageFormats.WEBP_EXTENDED;
    }
    return ImageFormat.UNKNOWN;
  }

  private static boolean isBmpHeader(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt < BMP_HEADER.length)
      return false;
    return ImageFormatCheckerUtils.startsWithPattern(paramArrayOfByte, BMP_HEADER);
  }

  private static boolean isGifHeader(byte[] paramArrayOfByte, int paramInt)
  {
    boolean bool = false;
    if (paramInt < 6)
      return false;
    if ((ImageFormatCheckerUtils.startsWithPattern(paramArrayOfByte, GIF_HEADER_87A)) || (ImageFormatCheckerUtils.startsWithPattern(paramArrayOfByte, GIF_HEADER_89A)))
      bool = true;
    return bool;
  }

  private static boolean isHeifHeader(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramInt < HEIF_HEADER_LENGTH)
      return false;
    if (paramArrayOfByte[3] < 8)
      return false;
    String[] arrayOfString = HEIF_HEADER_SUFFIXES;
    int i = arrayOfString.length;
    paramInt = 0;
    while (paramInt < i)
    {
      String str = arrayOfString[paramInt];
      int j = paramArrayOfByte.length;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ftyp");
      localStringBuilder.append(str);
      if (ImageFormatCheckerUtils.indexOfPattern(paramArrayOfByte, j, ImageFormatCheckerUtils.asciiBytes(localStringBuilder.toString()), HEIF_HEADER_LENGTH) > -1)
        return true;
      paramInt += 1;
    }
    return false;
  }

  private static boolean isJpegHeader(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramInt >= JPEG_HEADER.length) && (ImageFormatCheckerUtils.startsWithPattern(paramArrayOfByte, JPEG_HEADER));
  }

  private static boolean isPngHeader(byte[] paramArrayOfByte, int paramInt)
  {
    return (paramInt >= PNG_HEADER.length) && (ImageFormatCheckerUtils.startsWithPattern(paramArrayOfByte, PNG_HEADER));
  }

  @Nullable
  public final ImageFormat determineFormat(byte[] paramArrayOfByte, int paramInt)
  {
    Preconditions.checkNotNull(paramArrayOfByte);
    if (WebpSupportStatus.isWebpHeader(paramArrayOfByte, 0, paramInt))
      return getWebpFormat(paramArrayOfByte, paramInt);
    if (isJpegHeader(paramArrayOfByte, paramInt))
      return DefaultImageFormats.JPEG;
    if (isPngHeader(paramArrayOfByte, paramInt))
      return DefaultImageFormats.PNG;
    if (isGifHeader(paramArrayOfByte, paramInt))
      return DefaultImageFormats.GIF;
    if (isBmpHeader(paramArrayOfByte, paramInt))
      return DefaultImageFormats.BMP;
    if (isHeifHeader(paramArrayOfByte, paramInt))
      return DefaultImageFormats.HEIF;
    return ImageFormat.UNKNOWN;
  }

  public int getHeaderSize()
  {
    return this.MAX_HEADER_LENGTH;
  }
}