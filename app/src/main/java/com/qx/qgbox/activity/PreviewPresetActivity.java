package com.qx.qgbox.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.qx.qgbox.R;

import me.relex.photodraweeview.PhotoDraweeView;

public class PreviewPresetActivity extends Activity
{
  private String effect_preview_url = "";
  private PhotoDraweeView mPhotoDraweeView;
  private TextView tv_back;

  protected void onCreate(Bundle paramBundle)
  {
    getWindow().setFlags(1024, 1024);
    super.onCreate(paramBundle);
    setContentView(R.layout.preview_preset_dialog);
    this.effect_preview_url = getIntent().getStringExtra("effect_preview_url");
    this.mPhotoDraweeView = ((PhotoDraweeView)findViewById(R.id.photo_view));
    this.tv_back = ((TextView)findViewById(R.id.tv_back));
    this.mPhotoDraweeView.setMaximumScale(3.0F);
    paramBundle = new StringBuilder();
    paramBundle.append("---------effect_preview_url = ");
    paramBundle.append(this.effect_preview_url);
    Log.i("my_tag", paramBundle.toString());
    paramBundle = Fresco.newDraweeControllerBuilder();
    paramBundle.setUri(this.effect_preview_url);
    paramBundle.setOldController(this.mPhotoDraweeView.getController());
    paramBundle.setControllerListener(new BaseControllerListener()
    {
      public void onFinalImageSet(String paramAnonymousString, ImageInfo paramAnonymousImageInfo, Animatable paramAnonymousAnimatable)
      {
        super.onFinalImageSet(paramAnonymousString, paramAnonymousImageInfo, paramAnonymousAnimatable);
        if (paramAnonymousImageInfo != null)
        {
          if (PreviewPresetActivity.this.mPhotoDraweeView == null)
            return;
          PreviewPresetActivity.this.mPhotoDraweeView.update(paramAnonymousImageInfo.getWidth(), paramAnonymousImageInfo.getHeight());
          return;
        }
      }
    });
    this.mPhotoDraweeView.setController(paramBundle.build());
    this.tv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PreviewPresetActivity.this.finish();
      }
    });
  }
}
