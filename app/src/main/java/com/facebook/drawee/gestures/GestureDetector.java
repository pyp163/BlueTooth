package com.facebook.drawee.gestures;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.facebook.common.internal.VisibleForTesting;

public class GestureDetector
{

  @VisibleForTesting
  long mActionDownTime;

  @VisibleForTesting
  float mActionDownX;

  @VisibleForTesting
  float mActionDownY;

  @VisibleForTesting
  ClickListener mClickListener;

  @VisibleForTesting
  boolean mIsCapturingGesture;

  @VisibleForTesting
  boolean mIsClickCandidate;

  @VisibleForTesting
  final float mSingleTapSlopPx;

  public GestureDetector(Context paramContext)
  {
    this.mSingleTapSlopPx = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    init();
  }

  public static GestureDetector newInstance(Context paramContext)
  {
    return new GestureDetector(paramContext);
  }

  public void init()
  {
    this.mClickListener = null;
    reset();
  }

  public boolean isCapturingGesture()
  {
    return this.mIsCapturingGesture;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
      return true;
    case 3:
      this.mIsCapturingGesture = false;
      this.mIsClickCandidate = false;
      return true;
    case 2:
      if ((Math.abs(paramMotionEvent.getX() - this.mActionDownX) > this.mSingleTapSlopPx) || (Math.abs(paramMotionEvent.getY() - this.mActionDownY) > this.mSingleTapSlopPx))
      {
        this.mIsClickCandidate = false;
        return true;
      }
      break;
    case 1:
      this.mIsCapturingGesture = false;
      if ((Math.abs(paramMotionEvent.getX() - this.mActionDownX) > this.mSingleTapSlopPx) || (Math.abs(paramMotionEvent.getY() - this.mActionDownY) > this.mSingleTapSlopPx))
        this.mIsClickCandidate = false;
      if ((this.mIsClickCandidate) && (paramMotionEvent.getEventTime() - this.mActionDownTime <= ViewConfiguration.getLongPressTimeout()) && (this.mClickListener != null))
        this.mClickListener.onClick();
      this.mIsClickCandidate = false;
      return true;
    case 0:
      this.mIsCapturingGesture = true;
      this.mIsClickCandidate = true;
      this.mActionDownTime = paramMotionEvent.getEventTime();
      this.mActionDownX = paramMotionEvent.getX();
      this.mActionDownY = paramMotionEvent.getY();
    }
    return true;
  }

  public void reset()
  {
    this.mIsCapturingGesture = false;
    this.mIsClickCandidate = false;
  }

  public void setClickListener(ClickListener paramClickListener)
  {
    this.mClickListener = paramClickListener;
  }

  public static abstract interface ClickListener
  {
    public abstract boolean onClick();
  }
}