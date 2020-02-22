package com.qx.qgbox.adapters;

import android.util.SparseArray;
import android.view.View;

public class ViewHolder
{
  public static <T extends View> T get(View paramView, int paramInt)
  {
    Object localObject2 = (SparseArray)paramView.getTag();
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new SparseArray();
      paramView.setTag(localObject1);
    }
    View localView = (View)((SparseArray)localObject1).get(paramInt);
    localObject2 = localView;
    if (localView == null)
    {
      localObject2 = paramView.findViewById(paramInt);
      ((SparseArray)localObject1).put(paramInt, localObject2);
    }
    return (T) localObject2;
  }
}
