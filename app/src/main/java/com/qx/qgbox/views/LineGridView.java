package com.qx.qgbox.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class LineGridView extends GridView
{
  public LineGridView(Context paramContext)
  {
    super(paramContext);
  }

  public LineGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public LineGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    int i = 0;
    Object localObject = getChildAt(0);
    int j = getWidth() / ((View)localObject).getWidth();
    int k = getChildCount();
    localObject = new Paint();
    ((Paint)localObject).setStyle(Paint.Style.STROKE);
    ((Paint)localObject).setColor(getContext().getResources().getColor(R.color.gray_line));
    while (i < k)
    {
      View localView = getChildAt(i);
      i += 1;
      if (i % j == 0)
      {
        paramCanvas.drawLine(localView.getLeft(), localView.getBottom(), localView.getRight(), localView.getBottom(), (Paint)localObject);
      }
      else if (i > k - k % j)
      {
        paramCanvas.drawLine(localView.getRight(), localView.getTop(), localView.getRight(), localView.getBottom(), (Paint)localObject);
      }
      else
      {
        paramCanvas.drawLine(localView.getRight(), localView.getTop(), localView.getRight(), localView.getBottom(), (Paint)localObject);
        paramCanvas.drawLine(localView.getLeft(), localView.getBottom(), localView.getRight(), localView.getBottom(), (Paint)localObject);
      }
    }
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(536870911, -2147483648));
  }
}
