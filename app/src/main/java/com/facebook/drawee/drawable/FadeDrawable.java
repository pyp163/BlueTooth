package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.util.Arrays;

public class FadeDrawable extends ArrayDrawable
{

  @VisibleForTesting
  public static final int TRANSITION_NONE = 2;

  @VisibleForTesting
  public static final int TRANSITION_RUNNING = 1;

  @VisibleForTesting
  public static final int TRANSITION_STARTING = 0;

  @VisibleForTesting
  int mAlpha;

  @VisibleForTesting
  int[] mAlphas;

  @VisibleForTesting
  int mDurationMs;

  @VisibleForTesting
  boolean[] mIsLayerOn;
  private final Drawable[] mLayers;

  @VisibleForTesting
  int mPreventInvalidateCount;

  @VisibleForTesting
  int[] mStartAlphas;

  @VisibleForTesting
  long mStartTimeMs;

  @VisibleForTesting
  int mTransitionState;

  public FadeDrawable(Drawable[] paramArrayOfDrawable)
  {
    super(paramArrayOfDrawable);
    int i = paramArrayOfDrawable.length;
    boolean bool = true;
    if (i < 1)
      bool = false;
    Preconditions.checkState(bool, "At least one layer required!");
    this.mLayers = paramArrayOfDrawable;
    this.mStartAlphas = new int[paramArrayOfDrawable.length];
    this.mAlphas = new int[paramArrayOfDrawable.length];
    this.mAlpha = 255;
    this.mIsLayerOn = new boolean[paramArrayOfDrawable.length];
    this.mPreventInvalidateCount = 0;
    resetInternal();
  }

  private void drawDrawableWithAlpha(Canvas paramCanvas, Drawable paramDrawable, int paramInt)
  {
    if ((paramDrawable != null) && (paramInt > 0))
    {
      this.mPreventInvalidateCount += 1;
      paramDrawable.mutate().setAlpha(paramInt);
      this.mPreventInvalidateCount -= 1;
      paramDrawable.draw(paramCanvas);
    }
  }

  private void resetInternal()
  {
    this.mTransitionState = 2;
    Arrays.fill(this.mStartAlphas, 0);
    this.mStartAlphas[0] = 255;
    Arrays.fill(this.mAlphas, 0);
    this.mAlphas[0] = 255;
    Arrays.fill(this.mIsLayerOn, false);
    this.mIsLayerOn[0] = true;
  }

  private boolean updateAlphas(float paramFloat)
  {
    int i = 0;
    boolean bool2 = true;
    while (i < this.mLayers.length)
    {
      int j;
      if (this.mIsLayerOn[i] != 0)
        j = 1;
      else
        j = -1;
      this.mAlphas[i] = ((int)(this.mStartAlphas[i] + j * 255 * paramFloat));
      if (this.mAlphas[i] < 0)
        this.mAlphas[i] = 0;
      if (this.mAlphas[i] > 255)
        this.mAlphas[i] = 255;
      boolean bool1 = bool2;
      if (this.mIsLayerOn[i] != 0)
      {
        bool1 = bool2;
        if (this.mAlphas[i] < 255)
          bool1 = false;
      }
      bool2 = bool1;
      if (this.mIsLayerOn[i] == 0)
      {
        bool2 = bool1;
        if (this.mAlphas[i] > 0)
          bool2 = false;
      }
      i += 1;
    }
    return bool2;
  }

  public void beginBatchMode()
  {
    this.mPreventInvalidateCount += 1;
  }

  public void draw(Canvas paramCanvas)
  {
    int k = this.mTransitionState;
    int i = 2;
    int j = 0;
    boolean bool;
    switch (k)
    {
    case 2:
    default:
      bool = true;
      i = j;
      break;
    case 1:
      if (this.mDurationMs > 0)
        bool = true;
      else
        bool = false;
      Preconditions.checkState(bool);
      bool = updateAlphas((float)(getCurrentTimeMs() - this.mStartTimeMs) / this.mDurationMs);
      if (!bool)
        i = 1;
      this.mTransitionState = i;
      i = j;
      break;
    case 0:
      System.arraycopy(this.mAlphas, 0, this.mStartAlphas, 0, this.mLayers.length);
      this.mStartTimeMs = getCurrentTimeMs();
      float f;
      if (this.mDurationMs == 0)
        f = 1.0F;
      else
        f = 0.0F;
      bool = updateAlphas(f);
      if (!bool)
        i = 1;
      this.mTransitionState = i;
      i = j;
    }
    while (i < this.mLayers.length)
    {
      drawDrawableWithAlpha(paramCanvas, this.mLayers[i], this.mAlphas[i] * this.mAlpha / 255);
      i += 1;
    }
    if (!bool)
      invalidateSelf();
  }

  public void endBatchMode()
  {
    this.mPreventInvalidateCount -= 1;
    invalidateSelf();
  }

  public void fadeInAllLayers()
  {
    this.mTransitionState = 0;
    Arrays.fill(this.mIsLayerOn, true);
    invalidateSelf();
  }

  public void fadeInLayer(int paramInt)
  {
    this.mTransitionState = 0;
    this.mIsLayerOn[paramInt] = true;
    invalidateSelf();
  }

  public void fadeOutAllLayers()
  {
    this.mTransitionState = 0;
    Arrays.fill(this.mIsLayerOn, false);
    invalidateSelf();
  }

  public void fadeOutLayer(int paramInt)
  {
    this.mTransitionState = 0;
    this.mIsLayerOn[paramInt] = false;
    invalidateSelf();
  }

  public void fadeToLayer(int paramInt)
  {
    this.mTransitionState = 0;
    Arrays.fill(this.mIsLayerOn, false);
    this.mIsLayerOn[paramInt] = true;
    invalidateSelf();
  }

  public void fadeUpToLayer(int paramInt)
  {
    this.mTransitionState = 0;
    boolean[] arrayOfBoolean = this.mIsLayerOn;
    paramInt += 1;
    Arrays.fill(arrayOfBoolean, 0, paramInt, true);
    Arrays.fill(this.mIsLayerOn, paramInt, this.mLayers.length, false);
    invalidateSelf();
  }

  public void finishTransitionImmediately()
  {
    this.mTransitionState = 2;
    int i = 0;
    while (i < this.mLayers.length)
    {
      int[] arrayOfInt = this.mAlphas;
      int j;
      if (this.mIsLayerOn[i] != 0)
        j = 255;
      else
        j = 0;
      arrayOfInt[i] = j;
      i += 1;
    }
    invalidateSelf();
  }

  public int getAlpha()
  {
    return this.mAlpha;
  }

  protected long getCurrentTimeMs()
  {
    return SystemClock.uptimeMillis();
  }

  public int getTransitionDuration()
  {
    return this.mDurationMs;
  }

  @VisibleForTesting
  public int getTransitionState()
  {
    return this.mTransitionState;
  }

  public void invalidateSelf()
  {
    if (this.mPreventInvalidateCount == 0)
      super.invalidateSelf();
  }

  public boolean isLayerOn(int paramInt)
  {
    return this.mIsLayerOn[paramInt];
  }

  public void reset()
  {
    resetInternal();
    invalidateSelf();
  }

  public void setAlpha(int paramInt)
  {
    if (this.mAlpha != paramInt)
    {
      this.mAlpha = paramInt;
      invalidateSelf();
    }
  }

  public void setTransitionDuration(int paramInt)
  {
    this.mDurationMs = paramInt;
    if (this.mTransitionState == 1)
      this.mTransitionState = 0;
  }
}