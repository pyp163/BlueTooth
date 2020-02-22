package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.util.Arrays;

public abstract class RoundedDrawable extends Drawable
  implements Rounded, TransformAwareDrawable
{

  @VisibleForTesting
  final RectF mBitmapBounds = new RectF();
  protected int mBorderColor = 0;
  protected final Path mBorderPath = new Path();

  @VisibleForTesting
  final float[] mBorderRadii = new float[8];
  protected float mBorderWidth = 0.0F;

  @VisibleForTesting
  final Matrix mBoundsTransform = new Matrix();
  private final float[] mCornerRadii = new float[8];
  private final Drawable mDelegate;

  @VisibleForTesting
  final RectF mDrawableBounds = new RectF();

  @Nullable
  @VisibleForTesting
  RectF mInsideBorderBounds;

  @Nullable
  @VisibleForTesting
  float[] mInsideBorderRadii;

  @Nullable
  @VisibleForTesting
  Matrix mInsideBorderTransform;

  @VisibleForTesting
  final Matrix mInverseParentTransform = new Matrix();
  protected boolean mIsCircle = false;
  private boolean mIsPathDirty = true;
  protected boolean mIsShaderTransformDirty = true;
  private float mPadding = 0.0F;

  @VisibleForTesting
  final Matrix mParentTransform = new Matrix();
  protected final Path mPath = new Path();

  @VisibleForTesting
  final Matrix mPrevBoundsTransform = new Matrix();

  @Nullable
  @VisibleForTesting
  Matrix mPrevInsideBorderTransform;

  @VisibleForTesting
  final Matrix mPrevParentTransform = new Matrix();

  @VisibleForTesting
  final RectF mPrevRootBounds = new RectF();
  protected boolean mRadiiNonZero = false;

  @VisibleForTesting
  final RectF mRootBounds = new RectF();
  private boolean mScaleDownInsideBorders = false;

  @VisibleForTesting
  final Matrix mTransform = new Matrix();

  @Nullable
  private TransformCallback mTransformCallback;

  RoundedDrawable(Drawable paramDrawable)
  {
    this.mDelegate = paramDrawable;
  }

  public void clearColorFilter()
  {
    this.mDelegate.clearColorFilter();
  }

  public void draw(@NonNull Canvas paramCanvas)
  {
    this.mDelegate.draw(paramCanvas);
  }

  @RequiresApi(api=19)
  public int getAlpha()
  {
    return this.mDelegate.getAlpha();
  }

  public int getBorderColor()
  {
    return this.mBorderColor;
  }

  public float getBorderWidth()
  {
    return this.mBorderWidth;
  }

  @Nullable
  @RequiresApi(api=21)
  public ColorFilter getColorFilter()
  {
    return this.mDelegate.getColorFilter();
  }

  public int getIntrinsicHeight()
  {
    return this.mDelegate.getIntrinsicHeight();
  }

  public int getIntrinsicWidth()
  {
    return this.mDelegate.getIntrinsicWidth();
  }

  public int getOpacity()
  {
    return this.mDelegate.getOpacity();
  }

  public float getPadding()
  {
    return this.mPadding;
  }

  public float[] getRadii()
  {
    return this.mCornerRadii;
  }

  public boolean getScaleDownInsideBorders()
  {
    return this.mScaleDownInsideBorders;
  }

  public boolean isCircle()
  {
    return this.mIsCircle;
  }

  protected void onBoundsChange(Rect paramRect)
  {
    this.mDelegate.setBounds(paramRect);
  }

  public void setAlpha(int paramInt)
  {
    this.mDelegate.setAlpha(paramInt);
  }

  public void setBorder(int paramInt, float paramFloat)
  {
    if ((this.mBorderColor != paramInt) || (this.mBorderWidth != paramFloat))
    {
      this.mBorderColor = paramInt;
      this.mBorderWidth = paramFloat;
      this.mIsPathDirty = true;
      invalidateSelf();
    }
  }

  public void setCircle(boolean paramBoolean)
  {
    this.mIsCircle = paramBoolean;
    this.mIsPathDirty = true;
    invalidateSelf();
  }

  public void setColorFilter(int paramInt, @NonNull PorterDuff.Mode paramMode)
  {
    this.mDelegate.setColorFilter(paramInt, paramMode);
  }

  public void setColorFilter(@Nullable ColorFilter paramColorFilter)
  {
    this.mDelegate.setColorFilter(paramColorFilter);
  }

  public void setPadding(float paramFloat)
  {
    if (this.mPadding != paramFloat)
    {
      this.mPadding = paramFloat;
      this.mIsPathDirty = true;
      invalidateSelf();
    }
  }

  public void setRadii(float[] paramArrayOfFloat)
  {
    if (paramArrayOfFloat == null)
    {
      Arrays.fill(this.mCornerRadii, 0.0F);
      this.mRadiiNonZero = false;
    }
    else
    {
      boolean bool2;
      if (paramArrayOfFloat.length == 8)
        bool2 = true;
      else
        bool2 = false;
      Preconditions.checkArgument(bool2, "radii should have exactly 8 values");
      System.arraycopy(paramArrayOfFloat, 0, this.mCornerRadii, 0, 8);
      this.mRadiiNonZero = false;
      int i = 0;
      while (i < 8)
      {
        bool2 = this.mRadiiNonZero;
        boolean bool1;
        if (paramArrayOfFloat[i] > 0.0F)
          bool1 = true;
        else
          bool1 = false;
        this.mRadiiNonZero = (bool2 | bool1);
        i += 1;
      }
    }
    this.mIsPathDirty = true;
    invalidateSelf();
  }

  public void setRadius(float paramFloat)
  {
    boolean bool1 = paramFloat < 0.0F;
    boolean bool3 = false;
    if (!bool1)
      bool2 = true;
    else
      bool2 = false;
    Preconditions.checkState(bool2);
    Arrays.fill(this.mCornerRadii, paramFloat);
    boolean bool2 = bool3;
    if (bool1)
      bool2 = true;
    this.mRadiiNonZero = bool2;
    this.mIsPathDirty = true;
    invalidateSelf();
  }

  public void setScaleDownInsideBorders(boolean paramBoolean)
  {
    if (this.mScaleDownInsideBorders != paramBoolean)
    {
      this.mScaleDownInsideBorders = paramBoolean;
      this.mIsPathDirty = true;
      invalidateSelf();
    }
  }

  public void setTransformCallback(@Nullable TransformCallback paramTransformCallback)
  {
    this.mTransformCallback = paramTransformCallback;
  }

  @VisibleForTesting
  boolean shouldRound()
  {
    return (this.mIsCircle) || (this.mRadiiNonZero) || (this.mBorderWidth > 0.0F);
  }

  protected void updatePath()
  {
    if (this.mIsPathDirty)
    {
      this.mBorderPath.reset();
      this.mRootBounds.inset(this.mBorderWidth / 2.0F, this.mBorderWidth / 2.0F);
      int i;
      if (this.mIsCircle)
      {
        f1 = Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0F;
        this.mBorderPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), f1, Path.Direction.CW);
      }
      else
      {
        i = 0;
        while (i < this.mBorderRadii.length)
        {
          this.mBorderRadii[i] = (this.mCornerRadii[i] + this.mPadding - this.mBorderWidth / 2.0F);
          i += 1;
        }
        this.mBorderPath.addRoundRect(this.mRootBounds, this.mBorderRadii, Path.Direction.CW);
      }
      this.mRootBounds.inset(-this.mBorderWidth / 2.0F, -this.mBorderWidth / 2.0F);
      this.mPath.reset();
      float f2 = this.mPadding;
      if (this.mScaleDownInsideBorders)
        f1 = this.mBorderWidth;
      else
        f1 = 0.0F;
      float f1 = f2 + f1;
      this.mRootBounds.inset(f1, f1);
      if (this.mIsCircle)
      {
        this.mPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0F, Path.Direction.CW);
      }
      else if (this.mScaleDownInsideBorders)
      {
        if (this.mInsideBorderRadii == null)
          this.mInsideBorderRadii = new float[8];
        i = 0;
        while (i < this.mBorderRadii.length)
        {
          this.mInsideBorderRadii[i] = (this.mCornerRadii[i] - this.mBorderWidth);
          i += 1;
        }
        this.mPath.addRoundRect(this.mRootBounds, this.mInsideBorderRadii, Path.Direction.CW);
      }
      else
      {
        this.mPath.addRoundRect(this.mRootBounds, this.mCornerRadii, Path.Direction.CW);
      }
      RectF localRectF = this.mRootBounds;
      f1 = -f1;
      localRectF.inset(f1, f1);
      this.mPath.setFillType(Path.FillType.WINDING);
      this.mIsPathDirty = false;
    }
  }

  protected void updateTransform()
  {
    if (this.mTransformCallback != null)
    {
      this.mTransformCallback.getTransform(this.mParentTransform);
      this.mTransformCallback.getRootBounds(this.mRootBounds);
    }
    else
    {
      this.mParentTransform.reset();
      this.mRootBounds.set(getBounds());
    }
    this.mBitmapBounds.set(0.0F, 0.0F, getIntrinsicWidth(), getIntrinsicHeight());
    this.mDrawableBounds.set(this.mDelegate.getBounds());
    this.mBoundsTransform.setRectToRect(this.mBitmapBounds, this.mDrawableBounds, Matrix.ScaleToFit.FILL);
    if (this.mScaleDownInsideBorders)
    {
      if (this.mInsideBorderBounds == null)
        this.mInsideBorderBounds = new RectF(this.mRootBounds);
      else
        this.mInsideBorderBounds.set(this.mRootBounds);
      this.mInsideBorderBounds.inset(this.mBorderWidth, this.mBorderWidth);
      if (this.mInsideBorderTransform == null)
        this.mInsideBorderTransform = new Matrix();
      this.mInsideBorderTransform.setRectToRect(this.mRootBounds, this.mInsideBorderBounds, Matrix.ScaleToFit.FILL);
    }
    else if (this.mInsideBorderTransform != null)
    {
      this.mInsideBorderTransform.reset();
    }
    if ((!this.mParentTransform.equals(this.mPrevParentTransform)) || (!this.mBoundsTransform.equals(this.mPrevBoundsTransform)) || ((this.mInsideBorderTransform != null) && (!this.mInsideBorderTransform.equals(this.mPrevInsideBorderTransform))))
    {
      this.mIsShaderTransformDirty = true;
      this.mParentTransform.invert(this.mInverseParentTransform);
      this.mTransform.set(this.mParentTransform);
      if (this.mScaleDownInsideBorders)
        this.mTransform.postConcat(this.mInsideBorderTransform);
      this.mTransform.preConcat(this.mBoundsTransform);
      this.mPrevParentTransform.set(this.mParentTransform);
      this.mPrevBoundsTransform.set(this.mBoundsTransform);
      if (this.mScaleDownInsideBorders)
      {
        if (this.mPrevInsideBorderTransform == null)
          this.mPrevInsideBorderTransform = new Matrix(this.mInsideBorderTransform);
        else
          this.mPrevInsideBorderTransform.set(this.mInsideBorderTransform);
      }
      else if (this.mPrevInsideBorderTransform != null)
        this.mPrevInsideBorderTransform.reset();
    }
    if (!this.mRootBounds.equals(this.mPrevRootBounds))
    {
      this.mIsPathDirty = true;
      this.mPrevRootBounds.set(this.mRootBounds);
    }
  }
}