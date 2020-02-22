package android.support.design.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class DrawableUtils
{
  @Nullable
  public static PorterDuffColorFilter updateTintFilter(Drawable paramDrawable, @Nullable ColorStateList paramColorStateList, @Nullable PorterDuff.Mode paramMode)
  {
    if ((paramColorStateList != null) && (paramMode != null))
      return new PorterDuffColorFilter(paramColorStateList.getColorForState(paramDrawable.getState(), 0), paramMode);
    return null;
  }
}