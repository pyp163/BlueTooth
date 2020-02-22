package com.facebook.drawee.backends.pipeline.info;

import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfControllerListener;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfRequestListener;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

public class ImagePerfMonitor extends BaseRequestListener
{
  private boolean mEnabled;

  @Nullable
  private ForwardingRequestListener mForwardingRequestListener;

  @Nullable
  private ImageOriginListener mImageOriginListener;

  @Nullable
  private ImageOriginRequestListener mImageOriginRequestListener;

  @Nullable
  private ImagePerfControllerListener mImagePerfControllerListener;

  @Nullable
  private List<ImagePerfDataListener> mImagePerfDataListeners;

  @Nullable
  private ImagePerfRequestListener mImagePerfRequestListener;
  private final ImagePerfState mImagePerfState;
  private final MonotonicClock mMonotonicClock;
  private final PipelineDraweeController mPipelineDraweeController;

  public ImagePerfMonitor(MonotonicClock paramMonotonicClock, PipelineDraweeController paramPipelineDraweeController)
  {
    this.mMonotonicClock = paramMonotonicClock;
    this.mPipelineDraweeController = paramPipelineDraweeController;
    this.mImagePerfState = new ImagePerfState();
  }

  private void setupListeners()
  {
    if (this.mImagePerfControllerListener == null)
      this.mImagePerfControllerListener = new ImagePerfControllerListener(this.mMonotonicClock, this.mImagePerfState, this);
    if (this.mImagePerfRequestListener == null)
      this.mImagePerfRequestListener = new ImagePerfRequestListener(this.mMonotonicClock, this.mImagePerfState);
    if (this.mImageOriginListener == null)
      this.mImageOriginListener = new ImagePerfImageOriginListener(this.mImagePerfState, this);
    if (this.mImageOriginRequestListener == null)
      this.mImageOriginRequestListener = new ImageOriginRequestListener(this.mPipelineDraweeController.getId(), this.mImageOriginListener);
    else
      this.mImageOriginRequestListener.init(this.mPipelineDraweeController.getId());
    if (this.mForwardingRequestListener == null)
      this.mForwardingRequestListener = new ForwardingRequestListener(new RequestListener[] { this.mImagePerfRequestListener, this.mImageOriginRequestListener });
  }

  public void addImagePerfDataListener(@Nullable ImagePerfDataListener paramImagePerfDataListener)
  {
    if (paramImagePerfDataListener == null)
      return;
    if (this.mImagePerfDataListeners == null)
      this.mImagePerfDataListeners = new LinkedList();
    this.mImagePerfDataListeners.add(paramImagePerfDataListener);
  }

  public void clearImagePerfDataListeners()
  {
    if (this.mImagePerfDataListeners != null)
      this.mImagePerfDataListeners.clear();
  }

  public void notifyListeners(ImagePerfState paramImagePerfState, int paramInt)
  {
    paramImagePerfState.setImageLoadStatus(paramInt);
    if ((this.mEnabled) && (this.mImagePerfDataListeners != null))
    {
      if (this.mImagePerfDataListeners.isEmpty())
        return;
      paramImagePerfState = paramImagePerfState.snapshot();
      Iterator localIterator = this.mImagePerfDataListeners.iterator();
      while (localIterator.hasNext())
        ((ImagePerfDataListener)localIterator.next()).onImagePerfDataUpdated(paramImagePerfState, paramInt);
      return;
    }
  }

  public void removeImagePerfDataListener(ImagePerfDataListener paramImagePerfDataListener)
  {
    if (this.mImagePerfDataListeners == null)
      return;
    this.mImagePerfDataListeners.remove(paramImagePerfDataListener);
  }

  public void reset()
  {
    clearImagePerfDataListeners();
    setEnabled(false);
    this.mImagePerfState.reset();
  }

  public void setEnabled(boolean paramBoolean)
  {
    this.mEnabled = paramBoolean;
    if (paramBoolean)
    {
      setupListeners();
      if (this.mImageOriginListener != null)
        this.mPipelineDraweeController.addImageOriginListener(this.mImageOriginListener);
      if (this.mImagePerfControllerListener != null)
        this.mPipelineDraweeController.addControllerListener(this.mImagePerfControllerListener);
      if (this.mForwardingRequestListener != null)
        this.mPipelineDraweeController.addRequestListener(this.mForwardingRequestListener);
    }
    else
    {
      if (this.mImageOriginListener != null)
        this.mPipelineDraweeController.removeImageOriginListener(this.mImageOriginListener);
      if (this.mImagePerfControllerListener != null)
        this.mPipelineDraweeController.removeControllerListener(this.mImagePerfControllerListener);
      if (this.mForwardingRequestListener != null)
        this.mPipelineDraweeController.removeRequestListener(this.mForwardingRequestListener);
    }
  }
}