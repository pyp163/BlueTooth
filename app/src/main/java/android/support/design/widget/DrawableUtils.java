package android.support.design.widget;

import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.lang.reflect.Method;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class DrawableUtils
{
  private static final String LOG_TAG = "DrawableUtils";
  private static Method setConstantStateMethod;
  private static boolean setConstantStateMethodFetched;

  public static boolean setContainerConstantState(DrawableContainer paramDrawableContainer, Drawable.ConstantState paramConstantState)
  {
    return setContainerConstantStateV9(paramDrawableContainer, paramConstantState);
  }

  private static boolean setContainerConstantStateV9(DrawableContainer paramDrawableContainer, Drawable.ConstantState paramConstantState)
  {
    if (!setConstantStateMethodFetched);
    try
    {
      setConstantStateMethod = DrawableContainer.class.getDeclaredMethod("setConstantState", new Class[] { DrawableContainer.DrawableContainerState.class });
      setConstantStateMethod.setAccessible(true);
      break label43;
      Log.e("DrawableUtils", "Could not fetch setConstantState(). Oh well.");
      label43: setConstantStateMethodFetched = true;
      if (setConstantStateMethod == null);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        setConstantStateMethod.invoke(paramDrawableContainer, new Object[] { paramConstantState });
        return true;
        label71: Log.e("DrawableUtils", "Could not invoke setConstantState(). Oh well.");
        return false;
        localNoSuchMethodException = localNoSuchMethodException;
      }
      catch (Exception paramDrawableContainer)
      {
        break label71;
      }
    }
  }
}