package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;

public class RoundedNinePatchDrawable extends RoundedDrawable
{
  public RoundedNinePatchDrawable(NinePatchDrawable paramNinePatchDrawable)
  {
    super(paramNinePatchDrawable);
  }

  public void draw(Canvas paramCanvas)
  {
    if (!shouldRound())
    {
      super.draw(paramCanvas);
      return;
    }
    updateTransform();
    updatePath();
    paramCanvas.clipPath(this.mPath);
    super.draw(paramCanvas);
  }
}