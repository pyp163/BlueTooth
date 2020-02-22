package me.relex.photodraweeview;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class ScaleDragDetector
  implements ScaleGestureDetector.OnScaleGestureListener
{
  private static final int INVALID_POINTER_ID = -1;
  private int mActivePointerId = -1;
  private int mActivePointerIndex = 0;
  private boolean mIsDragging;
  float mLastTouchX;
  float mLastTouchY;
  private final float mMinimumVelocity;
  private final ScaleGestureDetector mScaleDetector = new ScaleGestureDetector(paramContext, this);
  private final OnScaleDragGestureListener mScaleDragGestureListener;
  private final float mTouchSlop;
  private VelocityTracker mVelocityTracker;

  public ScaleDragDetector(Context paramContext, OnScaleDragGestureListener paramOnScaleDragGestureListener)
  {
    this.mScaleDragGestureListener = paramOnScaleDragGestureListener;
    paramContext = ViewConfiguration.get(paramContext);
    this.mMinimumVelocity = paramContext.getScaledMinimumFlingVelocity();
    this.mTouchSlop = paramContext.getScaledTouchSlop();
  }

  private float getActiveX(MotionEvent paramMotionEvent)
  {
    try
    {
      float f = MotionEventCompat.getX(paramMotionEvent, this.mActivePointerIndex);
      return f;
      label11: return paramMotionEvent.getX();
    }
    catch (Exception localException)
    {
      break label11;
    }
  }

  private float getActiveY(MotionEvent paramMotionEvent)
  {
    try
    {
      float f = MotionEventCompat.getY(paramMotionEvent, this.mActivePointerIndex);
      return f;
      label11: return paramMotionEvent.getY();
    }
    catch (Exception localException)
    {
      break label11;
    }
  }

  private void onTouchActivePointer(int paramInt, MotionEvent paramMotionEvent)
  {
    int i = 0;
    if (paramInt != 3)
    {
      if (paramInt != 6);
      switch (paramInt)
      {
      default:
        break;
      case 0:
        this.mActivePointerId = paramMotionEvent.getPointerId(0);
        break;
        paramInt = MotionEventCompat.getActionIndex(paramMotionEvent);
        if (MotionEventCompat.getPointerId(paramMotionEvent, paramInt) != this.mActivePointerId)
          break;
        if (paramInt == 0)
          paramInt = 1;
        else
          paramInt = 0;
        this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, paramInt);
        this.mLastTouchX = MotionEventCompat.getX(paramMotionEvent, paramInt);
        this.mLastTouchY = MotionEventCompat.getY(paramMotionEvent, paramInt);
        break;
      case 1:
      }
    }
    else
    {
      this.mActivePointerId = -1;
    }
    paramInt = i;
    if (this.mActivePointerId != -1)
      paramInt = this.mActivePointerId;
    this.mActivePointerIndex = MotionEventCompat.findPointerIndex(paramMotionEvent, paramInt);
  }

  private void onTouchDragEvent(int paramInt, MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    float f1;
    float f2;
    switch (paramInt)
    {
    default:
    case 3:
      if (this.mVelocityTracker != null)
      {
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = null;
        return;
      }
      break;
    case 2:
      f1 = getActiveX(paramMotionEvent);
      f2 = getActiveY(paramMotionEvent);
      float f3 = f1 - this.mLastTouchX;
      float f4 = f2 - this.mLastTouchY;
      if (!this.mIsDragging)
      {
        if (Math.sqrt(f3 * f3 + f4 * f4) >= this.mTouchSlop)
          bool = true;
        this.mIsDragging = bool;
      }
      if (this.mIsDragging)
      {
        this.mScaleDragGestureListener.onDrag(f3, f4);
        this.mLastTouchX = f1;
        this.mLastTouchY = f2;
        if (this.mVelocityTracker != null)
        {
          this.mVelocityTracker.addMovement(paramMotionEvent);
          return;
        }
      }
      break;
    case 1:
      if ((this.mIsDragging) && (this.mVelocityTracker != null))
      {
        this.mLastTouchX = getActiveX(paramMotionEvent);
        this.mLastTouchY = getActiveY(paramMotionEvent);
        this.mVelocityTracker.addMovement(paramMotionEvent);
        this.mVelocityTracker.computeCurrentVelocity(1000);
        f1 = this.mVelocityTracker.getXVelocity();
        f2 = this.mVelocityTracker.getYVelocity();
        if (Math.max(Math.abs(f1), Math.abs(f2)) >= this.mMinimumVelocity)
          this.mScaleDragGestureListener.onFling(this.mLastTouchX, this.mLastTouchY, -f1, -f2);
      }
      if (this.mVelocityTracker != null)
      {
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = null;
        return;
      }
      break;
    case 0:
      this.mVelocityTracker = VelocityTracker.obtain();
      if (this.mVelocityTracker != null)
        this.mVelocityTracker.addMovement(paramMotionEvent);
      this.mLastTouchX = getActiveX(paramMotionEvent);
      this.mLastTouchY = getActiveY(paramMotionEvent);
      this.mIsDragging = false;
    }
  }

  public boolean isDragging()
  {
    return this.mIsDragging;
  }

  public boolean isScaling()
  {
    return this.mScaleDetector.isInProgress();
  }

  public boolean onScale(ScaleGestureDetector paramScaleGestureDetector)
  {
    float f = paramScaleGestureDetector.getScaleFactor();
    if ((!Float.isNaN(f)) && (!Float.isInfinite(f)))
    {
      this.mScaleDragGestureListener.onScale(f, paramScaleGestureDetector.getFocusX(), paramScaleGestureDetector.getFocusY());
      return true;
    }
    return false;
  }

  public boolean onScaleBegin(ScaleGestureDetector paramScaleGestureDetector)
  {
    return true;
  }

  public void onScaleEnd(ScaleGestureDetector paramScaleGestureDetector)
  {
    this.mScaleDragGestureListener.onScaleEnd();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    this.mScaleDetector.onTouchEvent(paramMotionEvent);
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    onTouchActivePointer(i, paramMotionEvent);
    onTouchDragEvent(i, paramMotionEvent);
    return true;
  }
}