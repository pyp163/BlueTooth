package com.qx.qgbox.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView
{
  public MyScrollView(Context paramContext)
  {
    this(paramContext, null);
  }

  public MyScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public MyScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    getParent().requestDisallowInterceptTouchEvent(true);
    return super.onInterceptTouchEvent(paramMotionEvent);
  }
}
