package android.support.v4.widget;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract interface AutoSizeableTextView
{

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final boolean PLATFORM_SUPPORTS_AUTOSIZE = bool;

  static
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 27)
      bool = true;
    else
      bool = false;
  }

  public abstract int getAutoSizeMaxTextSize();

  public abstract int getAutoSizeMinTextSize();

  public abstract int getAutoSizeStepGranularity();

  public abstract int[] getAutoSizeTextAvailableSizes();

  public abstract int getAutoSizeTextType();

  public abstract void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IllegalArgumentException;

  public abstract void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfInt, int paramInt)
    throws IllegalArgumentException;

  public abstract void setAutoSizeTextTypeWithDefaults(int paramInt);
}