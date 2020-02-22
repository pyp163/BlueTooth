package com.qx.qgbox.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class TasksCompletedView extends View
{
  private int mCircleColor;
  private Paint mCirclePaint;
  private int mProgress;
  private float mRadius;
  private int mRingColor;
  private Paint mRingPaint;
  private float mRingRadius;
  private float mStrokeWidth;
  private Paint mTextPaint;
  private int mTotalProgress = 100;
  private float mTxtHeight;
  private float mTxtWidth;
  private int mXCenter;
  private int mYCenter;

  public TasksCompletedView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initAttrs(paramContext, paramAttributeSet);
    initVariable();
  }

  private void initAttrs(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.TasksCompletedView, 0, 0);
    this.mRadius = paramContext.getDimension(1, 180.0F);
    this.mStrokeWidth = paramContext.getDimension(3, 20.0F);
    this.mCircleColor = getResources().getColor(R.color.text_color);
    this.mRingColor = getResources().getColor(R.color.green);
    this.mRingRadius = (this.mRadius + this.mStrokeWidth / 2.0F);
  }

  private void initVariable()
  {
    this.mCirclePaint = new Paint();
    this.mCirclePaint.setAntiAlias(true);
    this.mCirclePaint.setColor(this.mCircleColor);
    this.mCirclePaint.setStyle(Paint.Style.FILL);
    this.mRingPaint = new Paint();
    this.mRingPaint.setAntiAlias(true);
    this.mRingPaint.setColor(this.mRingColor);
    this.mRingPaint.setStyle(Paint.Style.STROKE);
    this.mRingPaint.setStrokeWidth(this.mStrokeWidth);
    this.mTextPaint = new Paint();
    this.mTextPaint.setAntiAlias(true);
    this.mTextPaint.setStyle(Paint.Style.FILL);
    this.mTextPaint.setARGB(255, 255, 255, 255);
    this.mTextPaint.setTextSize(this.mRadius / 2.0F);
    Paint.FontMetrics localFontMetrics = this.mTextPaint.getFontMetrics();
    this.mTxtHeight = ((int)Math.ceil(localFontMetrics.descent - localFontMetrics.ascent));
  }

  protected void onDraw(Canvas paramCanvas)
  {
    this.mXCenter = (getWidth() / 2);
    this.mYCenter = (getHeight() / 2);
    paramCanvas.drawCircle(this.mXCenter, this.mYCenter, this.mRadius, this.mCirclePaint);
    if (this.mProgress > 0)
    {
      Object localObject = new RectF();
      ((RectF)localObject).left = (this.mXCenter - this.mRingRadius);
      ((RectF)localObject).top = (this.mYCenter - this.mRingRadius);
      ((RectF)localObject).right = (this.mRingRadius * 2.0F + (this.mXCenter - this.mRingRadius));
      ((RectF)localObject).bottom = (this.mRingRadius * 2.0F + (this.mYCenter - this.mRingRadius));
      paramCanvas.drawArc((RectF)localObject, -90.0F, this.mProgress / this.mTotalProgress * 360.0F, false, this.mRingPaint);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(this.mProgress);
      ((StringBuilder)localObject).append("%");
      localObject = ((StringBuilder)localObject).toString();
      this.mTxtWidth = this.mTextPaint.measureText((String)localObject, 0, ((String)localObject).length());
      paramCanvas.drawText((String)localObject, this.mXCenter - this.mTxtWidth / 2.0F, this.mYCenter + this.mTxtHeight / 4.0F, this.mTextPaint);
    }
  }

  public void setProgress(int paramInt)
  {
    this.mProgress = paramInt;
    postInvalidate();
  }
}
