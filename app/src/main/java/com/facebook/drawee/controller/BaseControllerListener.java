package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

public class BaseControllerListener<INFO>
  implements ControllerListener<INFO>
{
  private static final ControllerListener<Object> NO_OP_LISTENER = new BaseControllerListener();

  public static <INFO> ControllerListener<INFO> getNoOpListener()
  {
    return NO_OP_LISTENER;
  }

  public void onFailure(String paramString, Throwable paramThrowable)
  {
  }

  public void onFinalImageSet(String paramString, @Nullable INFO paramINFO, @Nullable Animatable paramAnimatable)
  {
  }

  public void onIntermediateImageFailed(String paramString, Throwable paramThrowable)
  {
  }

  public void onIntermediateImageSet(String paramString, @Nullable INFO paramINFO)
  {
  }

  public void onRelease(String paramString)
  {
  }

  public void onSubmit(String paramString, Object paramObject)
  {
  }
}