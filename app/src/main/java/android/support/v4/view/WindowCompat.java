package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;

public final class WindowCompat
{
  public static final int FEATURE_ACTION_BAR = 8;
  public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
  public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

  @NonNull
  public static <T extends View> T requireViewById(@NonNull Window paramWindow, @IdRes int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 28)
      return paramWindow.requireViewById(paramInt);
    paramWindow = paramWindow.findViewById(paramInt);
    if (paramWindow == null)
      throw new IllegalArgumentException("ID does not reference a View inside this Window");
    return paramWindow;
  }
}