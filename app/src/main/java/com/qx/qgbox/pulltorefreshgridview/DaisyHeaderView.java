package com.qx.qgbox.pulltorefreshgridview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qx.qgbox.contract.HeadContract;

public class DaisyHeaderView extends RelativeLayout
  implements HeadContract
{
  private ImageView mImgDaisy;
  private ObjectAnimator mRotation;
  private TextView mTxtLoading;

  public DaisyHeaderView(Context paramContext)
  {
    this(paramContext, null);
  }

  public DaisyHeaderView(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  public void initView(Context paramContext)
  {
    LayoutInflater.from(paramContext).inflate(R.layout.layout_daisy, this);
    this.mTxtLoading = ((TextView)findViewById(R.id.txt_loading));
    this.mTxtLoading.setText(R.string.pulltorefreshgridview);
    this.mImgDaisy = ((ImageView)findViewById(R.id.img_daisy));
    this.mRotation = ObjectAnimator.ofFloat(this.mImgDaisy, "rotation", new float[] { 0.0F, 360.0F }).setDuration(1000L);
    this.mRotation.setRepeatCount(-1);
    this.mRotation.setInterpolator(new LinearInterpolator());
  }

  public void onPullEnable(boolean paramBoolean)
  {
    TextView localTextView = this.mTxtLoading;
    int i;
    if (paramBoolean)
      i = R.string.pulltorefreshgridview1;
    else
      i = R.string.pulltorefreshgridview;
    localTextView.setText(i);
  }

  public void onRefresh()
  {
    this.mTxtLoading.setText(R.string.pulltorefreshgridview2);
    this.mRotation.start();
  }

  public void setRefreshing(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.mRotation.start();
      return;
    }
    if (Build.VERSION.SDK_INT >= 19)
      this.mRotation.pause();
  }
}
