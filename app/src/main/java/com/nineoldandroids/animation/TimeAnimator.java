package com.nineoldandroids.animation;

public class TimeAnimator extends ValueAnimator
{
  private TimeListener mListener;
  private long mPreviousTime = -1L;

  void animateValue(float paramFloat)
  {
  }

  boolean animationFrame(long paramLong)
  {
    int i = this.mPlayingState;
    long l1 = 0L;
    if (i == 0)
    {
      this.mPlayingState = 1;
      if (this.mSeekTime < 0L)
      {
        this.mStartTime = paramLong;
      }
      else
      {
        this.mStartTime = (paramLong - this.mSeekTime);
        this.mSeekTime = -1L;
      }
    }
    if (this.mListener != null)
    {
      long l2 = this.mStartTime;
      if (this.mPreviousTime >= 0L)
        while (true)
          l1 = paramLong - this.mPreviousTime;
      this.mPreviousTime = paramLong;
      this.mListener.onTimeUpdate(this, paramLong - l2, l1);
    }
    return false;
  }

  void initAnimation()
  {
  }

  public void setTimeListener(TimeListener paramTimeListener)
  {
    this.mListener = paramTimeListener;
  }

  public static abstract interface TimeListener
  {
    public abstract void onTimeUpdate(TimeAnimator paramTimeAnimator, long paramLong1, long paramLong2);
  }
}