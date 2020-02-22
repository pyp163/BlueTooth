package com.facebook.widget.text.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BetterImageSpan extends ReplacementSpan
{
  public static final int ALIGN_BASELINE = 1;
  public static final int ALIGN_BOTTOM = 0;
  public static final int ALIGN_CENTER = 2;
  private final int mAlignment;
  private Rect mBounds;
  private final Drawable mDrawable;
  private final Paint.FontMetricsInt mFontMetricsInt = new Paint.FontMetricsInt();
  private int mHeight;
  private int mWidth;

  public BetterImageSpan(Drawable paramDrawable)
  {
    this(paramDrawable, 1);
  }

  public BetterImageSpan(Drawable paramDrawable, int paramInt)
  {
    this.mDrawable = paramDrawable;
    this.mAlignment = paramInt;
    updateBounds();
  }

  private int getOffsetAboveBaseline(Paint.FontMetricsInt paramFontMetricsInt)
  {
    int i = this.mAlignment;
    if (i != 0)
    {
      if (i != 2)
        return -this.mHeight;
      i = (paramFontMetricsInt.descent - paramFontMetricsInt.ascent - this.mHeight) / 2;
      return paramFontMetricsInt.ascent + i;
    }
    return paramFontMetricsInt.descent - this.mHeight;
  }

  public static final int normalizeAlignment(int paramInt)
  {
    if (paramInt != 0)
    {
      if (paramInt != 2)
        return 1;
      return 2;
    }
    return 0;
  }

  public void draw(Canvas paramCanvas, CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, Paint paramPaint)
  {
    paramPaint.getFontMetricsInt(this.mFontMetricsInt);
    paramInt1 = paramInt4 + getOffsetAboveBaseline(this.mFontMetricsInt);
    paramCanvas.translate(paramFloat, paramInt1);
    this.mDrawable.draw(paramCanvas);
    paramCanvas.translate(-paramFloat, -paramInt1);
  }

  public Drawable getDrawable()
  {
    return this.mDrawable;
  }

  public int getSize(Paint paramPaint, CharSequence paramCharSequence, int paramInt1, int paramInt2, Paint.FontMetricsInt paramFontMetricsInt)
  {
    updateBounds();
    if (paramFontMetricsInt == null)
      return this.mWidth;
    paramInt1 = getOffsetAboveBaseline(paramFontMetricsInt);
    paramInt2 = this.mHeight + paramInt1;
    if (paramInt1 < paramFontMetricsInt.ascent)
      paramFontMetricsInt.ascent = paramInt1;
    if (paramInt1 < paramFontMetricsInt.top)
      paramFontMetricsInt.top = paramInt1;
    if (paramInt2 > paramFontMetricsInt.descent)
      paramFontMetricsInt.descent = paramInt2;
    if (paramInt2 > paramFontMetricsInt.bottom)
      paramFontMetricsInt.bottom = paramInt2;
    return this.mWidth;
  }

  public void updateBounds()
  {
    this.mBounds = this.mDrawable.getBounds();
    this.mWidth = this.mBounds.width();
    this.mHeight = this.mBounds.height();
  }

  @Retention(RetentionPolicy.SOURCE)
  public static @interface BetterImageSpanAlignment
  {
  }
}