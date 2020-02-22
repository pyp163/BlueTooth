package com.facebook.drawee.components;

public class RetryManager
{
  private static final int MAX_TAP_TO_RETRY_ATTEMPTS = 4;
  private int mMaxTapToRetryAttempts;
  private int mTapToRetryAttempts;
  private boolean mTapToRetryEnabled;

  public RetryManager()
  {
    init();
  }

  public static RetryManager newInstance()
  {
    return new RetryManager();
  }

  public void init()
  {
    this.mTapToRetryEnabled = false;
    this.mMaxTapToRetryAttempts = 4;
    reset();
  }

  public boolean isTapToRetryEnabled()
  {
    return this.mTapToRetryEnabled;
  }

  public void notifyTapToRetry()
  {
    this.mTapToRetryAttempts += 1;
  }

  public void reset()
  {
    this.mTapToRetryAttempts = 0;
  }

  public void setMaxTapToRetryAttemps(int paramInt)
  {
    this.mMaxTapToRetryAttempts = paramInt;
  }

  public void setTapToRetryEnabled(boolean paramBoolean)
  {
    this.mTapToRetryEnabled = paramBoolean;
  }

  public boolean shouldRetryOnTap()
  {
    return (this.mTapToRetryEnabled) && (this.mTapToRetryAttempts < this.mMaxTapToRetryAttempts);
  }
}