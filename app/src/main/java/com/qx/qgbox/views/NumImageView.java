package com.qx.qgbox.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint({"AppCompatCustomView"})
public class NumImageView extends ImageView
{
  private int num = 0;
  private int paddingRight;
  private int paddingTop;
  private float radius;
  private float textSize;

  public NumImageView(Context paramContext)
  {
    super(paramContext);
  }

  public NumImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public NumImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.num > 0)
    {
      this.radius = 30.0F;
      float f;
      if (this.num < 10)
        f = this.radius + 5.0F;
      else
        f = this.radius;
      this.textSize = f;
      this.paddingRight = getPaddingRight();
      this.paddingTop = getPaddingTop();
      Paint localPaint = new Paint();
      localPaint.setAntiAlias(true);
      localPaint.setColor(-48060);
      localPaint.setStyle(Paint.Style.FILL);
      localPaint.setColor(-1);
      localPaint.setTextSize(this.textSize);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      int j = this.num;
      int i = 99;
      if (j < 99)
        i = this.num;
      ((StringBuilder)localObject).append(i);
      localObject = ((StringBuilder)localObject).toString();
      if (this.num < 10)
        f = getWidth() / 2 - this.textSize / 4.0F - this.paddingRight / 2;
      else
        f = getWidth() / 2 - this.textSize / 2.0F - this.paddingRight / 2;
      paramCanvas.drawText((String)localObject, f, getHeight() - this.textSize / 2.0F, localPaint);
    }
  }

  public void setNum(int paramInt)
  {
    this.num = paramInt;
    invalidate();
  }
}
