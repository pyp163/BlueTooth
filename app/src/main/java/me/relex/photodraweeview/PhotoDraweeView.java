package me.relex.photodraweeview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import android.view.View.OnLongClickListener;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

public class PhotoDraweeView extends SimpleDraweeView
  implements IAttacher
{
  private Attacher mAttacher;
  private boolean mEnableDraweeMatrix = true;

  public PhotoDraweeView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public PhotoDraweeView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public PhotoDraweeView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  public PhotoDraweeView(Context paramContext, GenericDraweeHierarchy paramGenericDraweeHierarchy)
  {
    super(paramContext, paramGenericDraweeHierarchy);
    init();
  }

  public Attacher getAttacher()
  {
    return this.mAttacher;
  }

  public float getMaximumScale()
  {
    return this.mAttacher.getMaximumScale();
  }

  public float getMediumScale()
  {
    return this.mAttacher.getMediumScale();
  }

  public float getMinimumScale()
  {
    return this.mAttacher.getMinimumScale();
  }

  public OnPhotoTapListener getOnPhotoTapListener()
  {
    return this.mAttacher.getOnPhotoTapListener();
  }

  public OnViewTapListener getOnViewTapListener()
  {
    return this.mAttacher.getOnViewTapListener();
  }

  public float getScale()
  {
    return this.mAttacher.getScale();
  }

  protected void init()
  {
    if ((this.mAttacher == null) || (this.mAttacher.getDraweeView() == null))
      this.mAttacher = new Attacher(this);
  }

  public boolean isEnableDraweeMatrix()
  {
    return this.mEnableDraweeMatrix;
  }

  protected void onAttachedToWindow()
  {
    init();
    super.onAttachedToWindow();
  }

  protected void onDetachedFromWindow()
  {
    this.mAttacher.onDetachedFromWindow();
    super.onDetachedFromWindow();
  }

  protected void onDraw(@NonNull Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    if (this.mEnableDraweeMatrix)
      paramCanvas.concat(this.mAttacher.getDrawMatrix());
    super.onDraw(paramCanvas);
    paramCanvas.restoreToCount(i);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setAllowParentInterceptOnEdge(boolean paramBoolean)
  {
    this.mAttacher.setAllowParentInterceptOnEdge(paramBoolean);
  }

  public void setEnableDraweeMatrix(boolean paramBoolean)
  {
    this.mEnableDraweeMatrix = paramBoolean;
  }

  public void setMaximumScale(float paramFloat)
  {
    this.mAttacher.setMaximumScale(paramFloat);
  }

  public void setMediumScale(float paramFloat)
  {
    this.mAttacher.setMediumScale(paramFloat);
  }

  public void setMinimumScale(float paramFloat)
  {
    this.mAttacher.setMinimumScale(paramFloat);
  }

  public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener)
  {
    this.mAttacher.setOnDoubleTapListener(paramOnDoubleTapListener);
  }

  public void setOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    this.mAttacher.setOnLongClickListener(paramOnLongClickListener);
  }

  public void setOnPhotoTapListener(OnPhotoTapListener paramOnPhotoTapListener)
  {
    this.mAttacher.setOnPhotoTapListener(paramOnPhotoTapListener);
  }

  public void setOnScaleChangeListener(OnScaleChangeListener paramOnScaleChangeListener)
  {
    this.mAttacher.setOnScaleChangeListener(paramOnScaleChangeListener);
  }

  public void setOnViewTapListener(OnViewTapListener paramOnViewTapListener)
  {
    this.mAttacher.setOnViewTapListener(paramOnViewTapListener);
  }

  public void setOrientation(int paramInt)
  {
    this.mAttacher.setOrientation(paramInt);
  }

  public void setPhotoUri(Uri paramUri)
  {
    setPhotoUri(paramUri, null);
  }

  public void setPhotoUri(Uri paramUri, @Nullable Context paramContext)
  {
    this.mEnableDraweeMatrix = false;
    setController(((PipelineDraweeControllerBuilder)((PipelineDraweeControllerBuilder)((PipelineDraweeControllerBuilder)Fresco.newDraweeControllerBuilder().setCallerContext(paramContext)).setUri(paramUri).setOldController(getController())).setControllerListener(new BaseControllerListener()
    {
      public void onFailure(String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousString, paramAnonymousThrowable);
        PhotoDraweeView.access$002(PhotoDraweeView.this, false);
      }

      public void onFinalImageSet(String paramAnonymousString, ImageInfo paramAnonymousImageInfo, Animatable paramAnonymousAnimatable)
      {
        super.onFinalImageSet(paramAnonymousString, paramAnonymousImageInfo, paramAnonymousAnimatable);
        PhotoDraweeView.access$002(PhotoDraweeView.this, true);
        if (paramAnonymousImageInfo != null)
          PhotoDraweeView.this.update(paramAnonymousImageInfo.getWidth(), paramAnonymousImageInfo.getHeight());
      }

      public void onIntermediateImageFailed(String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        super.onIntermediateImageFailed(paramAnonymousString, paramAnonymousThrowable);
        PhotoDraweeView.access$002(PhotoDraweeView.this, false);
      }

      public void onIntermediateImageSet(String paramAnonymousString, ImageInfo paramAnonymousImageInfo)
      {
        super.onIntermediateImageSet(paramAnonymousString, paramAnonymousImageInfo);
        PhotoDraweeView.access$002(PhotoDraweeView.this, true);
        if (paramAnonymousImageInfo != null)
          PhotoDraweeView.this.update(paramAnonymousImageInfo.getWidth(), paramAnonymousImageInfo.getHeight());
      }
    })).build());
  }

  public void setScale(float paramFloat)
  {
    this.mAttacher.setScale(paramFloat);
  }

  public void setScale(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean)
  {
    this.mAttacher.setScale(paramFloat1, paramFloat2, paramFloat3, paramBoolean);
  }

  public void setScale(float paramFloat, boolean paramBoolean)
  {
    this.mAttacher.setScale(paramFloat, paramBoolean);
  }

  public void setZoomTransitionDuration(long paramLong)
  {
    this.mAttacher.setZoomTransitionDuration(paramLong);
  }

  public void update(int paramInt1, int paramInt2)
  {
    this.mAttacher.update(paramInt1, paramInt2);
  }
}