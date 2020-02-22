package com.qx.qgbox.views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MarqueeTextView extends AppCompatTextView
{
  private boolean isStop = false;

  public MarqueeTextView(Context paramContext)
  {
    super(paramContext);
  }

  public MarqueeTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public MarqueeTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean isFocused()
  {
    if (this.isStop)
      return super.isFocused();
    return true;
  }

  protected void onDetachedFromWindow()
  {
    stopScroll();
    super.onDetachedFromWindow();
  }

  public void start()
  {
    this.isStop = false;
  }

  public void stopScroll()
  {
    this.isStop = true;
  }
}
