package android.support.v7.widget;

import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class DrawableUtils
{
  public static final Rect INSETS_NONE = new Rect();
  private static final String TAG = "DrawableUtils";
  private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
  private static Class<?> sInsetsClazz;

  static
  {
    if (Build.VERSION.SDK_INT >= 18);
    try
    {
      sInsetsClazz = Class.forName("android.graphics.Insets");
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
  }

  public static boolean canSafelyMutateDrawable(@NonNull Drawable paramDrawable)
  {
    if ((Build.VERSION.SDK_INT < 15) && ((paramDrawable instanceof InsetDrawable)))
      return false;
    if ((Build.VERSION.SDK_INT < 15) && ((paramDrawable instanceof GradientDrawable)))
      return false;
    if ((Build.VERSION.SDK_INT < 17) && ((paramDrawable instanceof LayerDrawable)))
      return false;
    if ((paramDrawable instanceof DrawableContainer))
    {
      paramDrawable = paramDrawable.getConstantState();
      if ((paramDrawable instanceof DrawableContainer.DrawableContainerState))
      {
        paramDrawable = ((DrawableContainer.DrawableContainerState)paramDrawable).getChildren();
        int j = paramDrawable.length;
        int i = 0;
        while (i < j)
        {
          if (!canSafelyMutateDrawable(paramDrawable[i]))
            return false;
          i += 1;
        }
      }
    }
    else
    {
      if ((paramDrawable instanceof WrappedDrawable))
        return canSafelyMutateDrawable(((WrappedDrawable)paramDrawable).getWrappedDrawable());
      if ((paramDrawable instanceof DrawableWrapper))
        return canSafelyMutateDrawable(((DrawableWrapper)paramDrawable).getWrappedDrawable());
      if ((paramDrawable instanceof ScaleDrawable))
        return canSafelyMutateDrawable(((ScaleDrawable)paramDrawable).getDrawable());
    }
    return true;
  }

  static void fixDrawable(@NonNull Drawable paramDrawable)
  {
    if ((Build.VERSION.SDK_INT == 21) && ("android.graphics.drawable.VectorDrawable".equals(paramDrawable.getClass().getName())))
      fixVectorDrawableTinting(paramDrawable);
  }

  private static void fixVectorDrawableTinting(Drawable paramDrawable)
  {
    int[] arrayOfInt = paramDrawable.getState();
    if ((arrayOfInt != null) && (arrayOfInt.length != 0))
      paramDrawable.setState(ThemeUtils.EMPTY_STATE_SET);
    else
      paramDrawable.setState(ThemeUtils.CHECKED_STATE_SET);
    paramDrawable.setState(arrayOfInt);
  }

  public static Rect getOpticalBounds(Drawable paramDrawable)
  {
    if (sInsetsClazz != null);
    try
    {
      paramDrawable = DrawableCompat.unwrap(paramDrawable);
      paramDrawable = paramDrawable.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(paramDrawable, new Object[0]);
      if (paramDrawable != null)
      {
        Rect localRect = new Rect();
        Field[] arrayOfField = sInsetsClazz.getFields();
        int k = arrayOfField.length;
        int j = 0;
        while (j < k)
        {
          Field localField = arrayOfField[j];
          String str = localField.getName();
          i = str.hashCode();
          if (i != -1383228885)
          {
            if (i != 115029)
            {
              if (i != 3317767)
              {
                if ((i != 108511772) || (!str.equals("right")))
                  break label250;
                i = 2;
                break label252;
              }
              if (!str.equals("left"))
                break label250;
              i = 0;
              break label252;
            }
            if (!str.equals("top"))
              break label250;
            i = 1;
            break label252;
          }
          if (!str.equals("bottom"))
            break label250;
          i = 3;
          break label252;
          localRect.bottom = localField.getInt(paramDrawable);
          break label224;
          localRect.right = localField.getInt(paramDrawable);
          break label224;
          localRect.top = localField.getInt(paramDrawable);
          break label224;
          localRect.left = localField.getInt(paramDrawable);
          label224: j += 1;
        }
        return localRect;
        Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
      }
      return INSETS_NONE;
    }
    catch (Exception paramDrawable)
    {
      while (true)
      {
        continue;
        label250: int i = -1;
        label252: switch (i)
        {
        case 3:
        case 2:
        case 1:
        case 0:
        }
      }
    }
  }

  public static PorterDuff.Mode parseTintMode(int paramInt, PorterDuff.Mode paramMode)
  {
    if (paramInt != 3)
    {
      if (paramInt != 5)
      {
        if (paramInt != 9)
        {
          switch (paramInt)
          {
          default:
            return paramMode;
          case 16:
            return PorterDuff.Mode.ADD;
          case 15:
            return PorterDuff.Mode.SCREEN;
          case 14:
          }
          return PorterDuff.Mode.MULTIPLY;
        }
        return PorterDuff.Mode.SRC_ATOP;
      }
      return PorterDuff.Mode.SRC_IN;
    }
    return PorterDuff.Mode.SRC_OVER;
  }
}