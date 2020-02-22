package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import javax.annotation.Nullable;

public abstract interface ControllerListener<INFO>
{
  public abstract void onFailure(String paramString, Throwable paramThrowable);

  public abstract void onFinalImageSet(String paramString, @Nullable INFO paramINFO, @Nullable Animatable paramAnimatable);

  public abstract void onIntermediateImageFailed(String paramString, Throwable paramThrowable);

  public abstract void onIntermediateImageSet(String paramString, @Nullable INFO paramINFO);

  public abstract void onRelease(String paramString);

  public abstract void onSubmit(String paramString, Object paramObject);
}