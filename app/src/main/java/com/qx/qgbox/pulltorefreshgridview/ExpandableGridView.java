package com.qx.qgbox.pulltorefreshgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class ExpandableGridView extends GridView
{
  boolean expanded = true;

  public ExpandableGridView(Context paramContext)
  {
    super(paramContext);
  }

  public ExpandableGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public ExpandableGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean isExpanded()
  {
    return this.expanded;
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    if (isExpanded())
    {
      super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(536870911, -2147483648));
      getLayoutParams().height = getMeasuredHeight();
      return;
    }
    super.onMeasure(paramInt1, paramInt2);
  }

  public void setExpanded(boolean paramBoolean)
  {
    this.expanded = paramBoolean;
  }
}
