package android.support.design.widget;

import android.graphics.Outline;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;

@RequiresApi(21)
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class CircularBorderDrawableLollipop extends CircularBorderDrawable
{
  public void getOutline(Outline paramOutline)
  {
    copyBounds(this.rect);
    paramOutline.setOval(this.rect);
  }
}