package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;

public class ScaleTypeDrawable extends ForwardingDrawable
{

  @VisibleForTesting
  Matrix mDrawMatrix;

  @VisibleForTesting
  PointF mFocusPoint = null;

  @VisibleForTesting
  ScalingUtils.ScaleType mScaleType;

  @VisibleForTesting
  Object mScaleTypeState;
  private Matrix mTempMatrix = new Matrix();

  @VisibleForTesting
  int mUnderlyingHeight = 0;

  @VisibleForTesting
  int mUnderlyingWidth = 0;

  public ScaleTypeDrawable(Drawable paramDrawable, ScalingUtils.ScaleType paramScaleType)
  {
    super((Drawable)Preconditions.checkNotNull(paramDrawable));
    this.mScaleType = paramScaleType;
  }

  private void configureBoundsIfUnderlyingChanged()
  {
    boolean bool = this.mScaleType instanceof ScalingUtils.StatefulScaleType;
    int k = 1;
    int i;
    if (bool)
    {
      Object localObject = ((ScalingUtils.StatefulScaleType)this.mScaleType).getState();
      if ((localObject != null) && (localObject.equals(this.mScaleTypeState)))
        i = 0;
      else
        i = 1;
      this.mScaleTypeState = localObject;
    }
    else
    {
      i = 0;
    }
    int j = k;
    if (this.mUnderlyingWidth == getCurrent().getIntrinsicWidth())
      if (this.mUnderlyingHeight != getCurrent().getIntrinsicHeight())
        j = k;
      else
        j = 0;
    if ((j != 0) || (i != 0))
      configureBounds();
  }

  @VisibleForTesting
  void configureBounds()
  {
    Object localObject = getCurrent();
    Rect localRect = getBounds();
    int i = localRect.width();
    int j = localRect.height();
    int k = ((Drawable)localObject).getIntrinsicWidth();
    this.mUnderlyingWidth = k;
    int m = ((Drawable)localObject).getIntrinsicHeight();
    this.mUnderlyingHeight = m;
    if ((k > 0) && (m > 0))
    {
      if ((k == i) && (m == j))
      {
        ((Drawable)localObject).setBounds(localRect);
        this.mDrawMatrix = null;
        return;
      }
      if (this.mScaleType == ScalingUtils.ScaleType.FIT_XY)
      {
        ((Drawable)localObject).setBounds(localRect);
        this.mDrawMatrix = null;
        return;
      }
      ((Drawable)localObject).setBounds(0, 0, k, m);
      localObject = this.mScaleType;
      Matrix localMatrix = this.mTempMatrix;
      float f1;
      if (this.mFocusPoint != null)
        f1 = this.mFocusPoint.x;
      else
        f1 = 0.5F;
      float f2;
      if (this.mFocusPoint != null)
        f2 = this.mFocusPoint.y;
      else
        f2 = 0.5F;
      ((ScalingUtils.ScaleType)localObject).getTransform(localMatrix, localRect, k, m, f1, f2);
      this.mDrawMatrix = this.mTempMatrix;
      return;
    }
    ((Drawable)localObject).setBounds(localRect);
    this.mDrawMatrix = null;
  }

  public void draw(Canvas paramCanvas)
  {
    configureBoundsIfUnderlyingChanged();
    if (this.mDrawMatrix != null)
    {
      int i = paramCanvas.save();
      paramCanvas.clipRect(getBounds());
      paramCanvas.concat(this.mDrawMatrix);
      super.draw(paramCanvas);
      paramCanvas.restoreToCount(i);
      return;
    }
    super.draw(paramCanvas);
  }

  public PointF getFocusPoint()
  {
    return this.mFocusPoint;
  }

  public ScalingUtils.ScaleType getScaleType()
  {
    return this.mScaleType;
  }

  public void getTransform(Matrix paramMatrix)
  {
    getParentTransform(paramMatrix);
    configureBoundsIfUnderlyingChanged();
    if (this.mDrawMatrix != null)
      paramMatrix.preConcat(this.mDrawMatrix);
  }

  protected void onBoundsChange(Rect paramRect)
  {
    configureBounds();
  }

  public Drawable setCurrent(Drawable paramDrawable)
  {
    paramDrawable = super.setCurrent(paramDrawable);
    configureBounds();
    return paramDrawable;
  }

  public void setFocusPoint(PointF paramPointF)
  {
    if (Objects.equal(this.mFocusPoint, paramPointF))
      return;
    if (this.mFocusPoint == null)
      this.mFocusPoint = new PointF();
    this.mFocusPoint.set(paramPointF);
    configureBounds();
    invalidateSelf();
  }

  public void setScaleType(ScalingUtils.ScaleType paramScaleType)
  {
    if (Objects.equal(this.mScaleType, paramScaleType))
      return;
    this.mScaleType = paramScaleType;
    this.mScaleTypeState = null;
    configureBounds();
    invalidateSelf();
  }
}