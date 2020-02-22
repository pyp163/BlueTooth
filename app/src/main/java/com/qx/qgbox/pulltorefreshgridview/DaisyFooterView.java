package com.qx.qgbox.pulltorefreshgridview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qx.qgbox.contract.FootContract;

public class DaisyFooterView extends RelativeLayout
  implements FootContract
{
  private ImageView mImgDaisy;
  private ObjectAnimator mRotation;
  private TextView mTxtLoading;

  public DaisyFooterView(Context paramContext)
  {
    this(paramContext, null);
  }

  public DaisyFooterView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  public void initView(Context paramContext)
  {
    LayoutInflater.from(paramContext).inflate(R.layout.layout_daisy, this);
    this.mTxtLoading = ((TextView)findViewById(R.id.txt_loading));
    this.mTxtLoading.setText("上拉加载更多...");
    this.mImgDaisy = ((ImageView)findViewById(R.id.img_daisy));
    this.mRotation = ObjectAnimator.ofFloat(this.mImgDaisy, "rotation", new float[] { 0.0F, 360.0F }).setDuration(800L);
    this.mRotation.setRepeatCount(-1);
    this.mRotation.setInterpolator(new LinearInterpolator());
  }

  public void onLoadMore()
  {
    this.mTxtLoading.setText("正在加载...");
    this.mRotation.start();
  }

  public void onPushEnable(boolean paramBoolean)
  {
    TextView localTextView = this.mTxtLoading;
    String str;
    if (paramBoolean)
      str = "松开加载";
    else
      str = "上拉加载";
    localTextView.setText(str);
  }

  public void setLoadMore(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mRotation.start();
      return;
    }
    this.mRotation.pause();
  }
}
