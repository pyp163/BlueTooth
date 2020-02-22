package com.facebook.imagepipeline.common;

import android.graphics.Bitmap.Config;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import java.util.Locale;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class ImageDecodeOptions
{
  private static final ImageDecodeOptions DEFAULTS = newBuilder().build();
  public final Bitmap.Config bitmapConfig;

  @Nullable
  public final ImageDecoder customImageDecoder;
  public final boolean decodeAllFrames;
  public final boolean decodePreviewFrame;
  public final boolean forceStaticImage;
  public final int minDecodeIntervalMs;
  public final boolean useLastFrameForPreview;

  public ImageDecodeOptions(ImageDecodeOptionsBuilder paramImageDecodeOptionsBuilder)
  {
    this.minDecodeIntervalMs = paramImageDecodeOptionsBuilder.getMinDecodeIntervalMs();
    this.decodePreviewFrame = paramImageDecodeOptionsBuilder.getDecodePreviewFrame();
    this.useLastFrameForPreview = paramImageDecodeOptionsBuilder.getUseLastFrameForPreview();
    this.decodeAllFrames = paramImageDecodeOptionsBuilder.getDecodeAllFrames();
    this.forceStaticImage = paramImageDecodeOptionsBuilder.getForceStaticImage();
    this.bitmapConfig = paramImageDecodeOptionsBuilder.getBitmapConfig();
    this.customImageDecoder = paramImageDecodeOptionsBuilder.getCustomImageDecoder();
  }

  public static ImageDecodeOptions defaults()
  {
    return DEFAULTS;
  }

  public static ImageDecodeOptionsBuilder newBuilder()
  {
    return new ImageDecodeOptionsBuilder();
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass())
        return false;
      paramObject = (ImageDecodeOptions)paramObject;
      if (this.decodePreviewFrame != paramObject.decodePreviewFrame)
        return false;
      if (this.useLastFrameForPreview != paramObject.useLastFrameForPreview)
        return false;
      if (this.decodeAllFrames != paramObject.decodeAllFrames)
        return false;
      if (this.forceStaticImage != paramObject.forceStaticImage)
        return false;
      if (this.bitmapConfig != paramObject.bitmapConfig)
        return false;
      return this.customImageDecoder == paramObject.customImageDecoder;
    }
    return false;
  }

  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }

  public String toString()
  {
    return String.format((Locale)null, "%d-%b-%b-%b-%b-%s-%s", new Object[] { Integer.valueOf(this.minDecodeIntervalMs), Boolean.valueOf(this.decodePreviewFrame), Boolean.valueOf(this.useLastFrameForPreview), Boolean.valueOf(this.decodeAllFrames), Boolean.valueOf(this.forceStaticImage), this.bitmapConfig.name(), this.customImageDecoder });
  }
}