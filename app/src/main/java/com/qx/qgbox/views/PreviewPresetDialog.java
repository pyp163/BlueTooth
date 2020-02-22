package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import me.relex.photodraweeview.PhotoDraweeView;

public class PreviewPresetDialog extends Dialog
{
  private ClickListenerInterface clickListenerInterface;
  private Context context;
  private String imageUrl;
  private PhotoDraweeView mPhotoDraweeView;

  public PreviewPresetDialog(Context paramContext, String paramString)
  {
    super(paramContext);
    this.context = paramContext;
    this.imageUrl = paramString;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.preview_preset_dialog, null), new WindowManager.LayoutParams(-1, -1));
    getWindow().setBackgroundDrawableResource(17170445);
    this.mPhotoDraweeView = ((PhotoDraweeView)findViewById(R.id.photo_view));
    paramBundle = new StringBuilder();
    paramBundle.append("---------imageUrl = ");
    paramBundle.append(this.imageUrl);
    Log.i("my_tag", paramBundle.toString());
    paramBundle = Fresco.newDraweeControllerBuilder();
    paramBundle.setUri(this.imageUrl);
    paramBundle.setOldController(this.mPhotoDraweeView.getController());
    paramBundle.setControllerListener(new BaseControllerListener()
    {
      public void onFinalImageSet(String paramAnonymousString, ImageInfo paramAnonymousImageInfo, Animatable paramAnonymousAnimatable)
      {
        super.onFinalImageSet(paramAnonymousString, paramAnonymousImageInfo, paramAnonymousAnimatable);
        if (paramAnonymousImageInfo != null)
        {
          if (PreviewPresetDialog.this.mPhotoDraweeView == null)
            return;
          PreviewPresetDialog.this.mPhotoDraweeView.update(paramAnonymousImageInfo.getWidth(), paramAnonymousImageInfo.getHeight());
          return;
        }
      }
    });
    this.mPhotoDraweeView.setController(paramBundle.build());
    this.mPhotoDraweeView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Log.i("my_tag", "---------doConfirm");
        PreviewPresetDialog.this.clickListenerInterface.doConfirm();
      }
    });
    this.mPhotoDraweeView.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        PreviewPresetDialog.this.clickListenerInterface.doLongConfirm();
        return true;
      }
    });
  }

  public void setClicklistener(ClickListenerInterface paramClickListenerInterface)
  {
    this.clickListenerInterface = paramClickListenerInterface;
  }

  public void show()
  {
    super.show();
  }

  public static abstract interface ClickListenerInterface
  {
    public abstract void doConfirm();

    public abstract void doLongConfirm();
  }
}
