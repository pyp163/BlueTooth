package android.support.transition;

import android.animation.LayoutTransition;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewGroupUtilsApi14
{
  private static final int LAYOUT_TRANSITION_CHANGING = 4;
  private static final String TAG = "ViewGroupUtilsApi14";
  private static Method sCancelMethod;
  private static boolean sCancelMethodFetched;
  private static LayoutTransition sEmptyLayoutTransition;
  private static Field sLayoutSuppressedField;
  private static boolean sLayoutSuppressedFieldFetched;

  private static void cancelLayoutTransition(LayoutTransition paramLayoutTransition)
  {
    if (!sCancelMethodFetched);
    try
    {
      sCancelMethod = LayoutTransition.class.getDeclaredMethod("cancel", new Class[0]);
      sCancelMethod.setAccessible(true);
      break label38;
      Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
      label38: sCancelMethodFetched = true;
      if (sCancelMethod == null);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        sCancelMethod.invoke(paramLayoutTransition, new Object[0]);
        return;
        label61: Log.i("ViewGroupUtilsApi14", "Failed to invoke cancel method by reflection");
        return;
        label70: Log.i("ViewGroupUtilsApi14", "Failed to access cancel method by reflection");
        return;
        localNoSuchMethodException = localNoSuchMethodException;
      }
      catch (IllegalAccessException paramLayoutTransition)
      {
        break label70;
      }
      catch (InvocationTargetException paramLayoutTransition)
      {
        break label61;
      }
    }
  }

  static void suppressLayout(@NonNull ViewGroup paramViewGroup, boolean paramBoolean)
  {
    LayoutTransition localLayoutTransition = sEmptyLayoutTransition;
    boolean bool2 = false;
    boolean bool1 = false;
    if (localLayoutTransition == null)
    {
      sEmptyLayoutTransition = new LayoutTransition()
      {
        public boolean isChangingLayout()
        {
          return true;
        }
      };
      sEmptyLayoutTransition.setAnimator(2, null);
      sEmptyLayoutTransition.setAnimator(0, null);
      sEmptyLayoutTransition.setAnimator(1, null);
      sEmptyLayoutTransition.setAnimator(3, null);
      sEmptyLayoutTransition.setAnimator(4, null);
    }
    if (paramBoolean)
    {
      localLayoutTransition = paramViewGroup.getLayoutTransition();
      if (localLayoutTransition != null)
      {
        if (localLayoutTransition.isRunning())
          cancelLayoutTransition(localLayoutTransition);
        if (localLayoutTransition != sEmptyLayoutTransition)
          paramViewGroup.setTag(R.id.transition_layout_save, localLayoutTransition);
      }
      paramViewGroup.setLayoutTransition(sEmptyLayoutTransition);
      return;
    }
    paramViewGroup.setLayoutTransition(null);
    if (!sLayoutSuppressedFieldFetched);
    try
    {
      sLayoutSuppressedField = ViewGroup.class.getDeclaredField("mLayoutSuppressed");
      sLayoutSuppressedField.setAccessible(true);
      break label156;
      Log.i("ViewGroupUtilsApi14", "Failed to access mLayoutSuppressed field by reflection");
      label156: sLayoutSuppressedFieldFetched = true;
      paramBoolean = bool2;
      if (sLayoutSuppressedField == null);
    }
    catch (NoSuchFieldException localIllegalAccessException1)
    {
      try
      {
        paramBoolean = sLayoutSuppressedField.getBoolean(paramViewGroup);
        if (!paramBoolean);
      }
      catch (IllegalAccessException localIllegalAccessException1)
      {
        try
        {
          sLayoutSuppressedField.setBoolean(paramViewGroup, false);
          break label194;
          while (true)
          {
            label191: label194: Log.i("ViewGroupUtilsApi14", "Failed to get mLayoutSuppressed field by reflection");
            if (paramBoolean)
              paramViewGroup.requestLayout();
            localLayoutTransition = (LayoutTransition)paramViewGroup.getTag(R.id.transition_layout_save);
            if (localLayoutTransition != null)
            {
              paramViewGroup.setTag(R.id.transition_layout_save, null);
              paramViewGroup.setLayoutTransition(localLayoutTransition);
            }
            return;
            localNoSuchFieldException = localNoSuchFieldException;
            break;
            localIllegalAccessException1 = localIllegalAccessException1;
            paramBoolean = bool1;
          }
        }
        catch (IllegalAccessException localIllegalAccessException2)
        {
          break label191;
        }
      }
    }
  }
}