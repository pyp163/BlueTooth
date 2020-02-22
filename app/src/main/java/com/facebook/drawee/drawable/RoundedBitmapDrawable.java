package com.facebook.drawee.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.common.internal.VisibleForTesting;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

public class RoundedBitmapDrawable extends RoundedDrawable
{

  @Nullable
  private final Bitmap mBitmap;
  private final Paint mBorderPaint = new Paint(1);
  private WeakReference<Bitmap> mLastBitmap;
  private final Paint mPaint = new Paint();

  public RoundedBitmapDrawable(Resources paramResources, Bitmap paramBitmap)
  {
    this(paramResources, paramBitmap, null);
  }

  public RoundedBitmapDrawable(Resources paramResources, @Nullable Bitmap paramBitmap, @Nullable Paint paramPaint)
  {
    super(new BitmapDrawable(paramResources, paramBitmap));
    this.mBitmap = paramBitmap;
    if (paramPaint != null)
      this.mPaint.set(paramPaint);
    this.mPaint.setFlags(1);
    this.mBorderPaint.setStyle(Paint.Style.STROKE);
  }

  public static RoundedBitmapDrawable fromBitmapDrawable(Resources paramResources, BitmapDrawable paramBitmapDrawable)
  {
    return new RoundedBitmapDrawable(paramResources, paramBitmapDrawable.getBitmap(), paramBitmapDrawable.getPaint());
  }

  private void updatePaint()
  {
    if ((this.mLastBitmap == null) || (this.mLastBitmap.get() != this.mBitmap))
    {
      this.mLastBitmap = new WeakReference(this.mBitmap);
      this.mPaint.setShader(new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
      this.mIsShaderTransformDirty = true;
    }
    if (this.mIsShaderTransformDirty)
    {
      this.mPaint.getShader().setLocalMatrix(this.mTransform);
      this.mIsShaderTransformDirty = false;
    }
  }

  public void draw(Canvas paramCanvas)
  {
    if (!shouldRound())
    {
      super.draw(paramCanvas);
      return;
    }
    updateTransform();
    updatePath();
    updatePaint();
    int i = paramCanvas.save();
    paramCanvas.concat(this.mInverseParentTransform);
    paramCanvas.drawPath(this.mPath, this.mPaint);
    if (this.mBorderWidth > 0.0F)
    {
      this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
      this.mBorderPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.mPaint.getAlpha()));
      paramCanvas.drawPath(this.mBorderPath, this.mBorderPaint);
    }
    paramCanvas.restoreToCount(i);
  }

  Paint getPaint()
  {
    return this.mPaint;
  }

  public void setAlpha(int paramInt)
  {
    super.setAlpha(paramInt);
    if (paramInt != this.mPaint.getAlpha())
    {
      this.mPaint.setAlpha(paramInt);
      super.setAlpha(paramInt);
      invalidateSelf();
    }
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    super.setColorFilter(paramColorFilter);
    this.mPaint.setColorFilter(paramColorFilter);
  }

  @VisibleForTesting
  boolean shouldRound()
  {
    return (super.shouldRound()) && (this.mBitmap != null);
  }
}