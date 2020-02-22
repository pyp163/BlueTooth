package me.relex.photodraweeview;

import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnLongClickListener;

public abstract interface IAttacher
{
  public static final float DEFAULT_MAX_SCALE = 3.0F;
  public static final float DEFAULT_MID_SCALE = 1.75F;
  public static final float DEFAULT_MIN_SCALE = 1.0F;
  public static final long ZOOM_DURATION = 200L;

  public abstract float getMaximumScale();

  public abstract float getMediumScale();

  public abstract float getMinimumScale();

  public abstract OnPhotoTapListener getOnPhotoTapListener();

  public abstract OnViewTapListener getOnViewTapListener();

  public abstract float getScale();

  public abstract void setAllowParentInterceptOnEdge(boolean paramBoolean);

  public abstract void setMaximumScale(float paramFloat);

  public abstract void setMediumScale(float paramFloat);

  public abstract void setMinimumScale(float paramFloat);

  public abstract void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener);

  public abstract void setOnLongClickListener(View.OnLongClickListener paramOnLongClickListener);

  public abstract void setOnPhotoTapListener(OnPhotoTapListener paramOnPhotoTapListener);

  public abstract void setOnScaleChangeListener(OnScaleChangeListener paramOnScaleChangeListener);

  public abstract void setOnViewTapListener(OnViewTapListener paramOnViewTapListener);

  public abstract void setOrientation(int paramInt);

  public abstract void setScale(float paramFloat);

  public abstract void setScale(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean);

  public abstract void setScale(float paramFloat, boolean paramBoolean);

  public abstract void setZoomTransitionDuration(long paramLong);

  public abstract void update(int paramInt1, int paramInt2);
}