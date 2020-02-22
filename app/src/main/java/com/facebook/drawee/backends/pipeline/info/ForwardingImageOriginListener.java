package com.facebook.drawee.backends.pipeline.info;

import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ForwardingImageOriginListener
  implements ImageOriginListener
{
  private static final String TAG = "ForwardingImageOriginListener";
  private final List<ImageOriginListener> mImageOriginListeners;

  public ForwardingImageOriginListener(Set<ImageOriginListener> paramSet)
  {
    this.mImageOriginListeners = new ArrayList(paramSet);
  }

  public ForwardingImageOriginListener(ImageOriginListener[] paramArrayOfImageOriginListener)
  {
    this.mImageOriginListeners = new ArrayList(paramArrayOfImageOriginListener.length);
    Collections.addAll(this.mImageOriginListeners, paramArrayOfImageOriginListener);
  }

  public void addImageOriginListener(ImageOriginListener paramImageOriginListener)
  {
    try
    {
      this.mImageOriginListeners.add(paramImageOriginListener);
      return;
    }
    finally
    {
      paramImageOriginListener = finally;
    }
    throw paramImageOriginListener;
  }

  public void onImageLoaded(String paramString, int paramInt, boolean paramBoolean)
  {
    try
    {
      int j = this.mImageOriginListeners.size();
      int i = 0;
      while (i < j)
      {
        ImageOriginListener localImageOriginListener = (ImageOriginListener)this.mImageOriginListeners.get(i);
        try
        {
          localImageOriginListener.onImageLoaded(paramString, paramInt, paramBoolean);
        }
        catch (Exception localException)
        {
          FLog.e("ForwardingImageOriginListener", "InternalListener exception in onImageLoaded", localException);
        }
        i += 1;
      }
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  public void removeImageOriginListener(ImageOriginListener paramImageOriginListener)
  {
    try
    {
      this.mImageOriginListeners.remove(paramImageOriginListener);
      return;
    }
    finally
    {
      paramImageOriginListener = finally;
    }
    throw paramImageOriginListener;
  }
}