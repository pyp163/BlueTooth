package android.support.design.animation;

import android.support.design.R.id;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

public class ChildrenAlphaProperty extends Property<ViewGroup, Float>
{
  public static final Property<ViewGroup, Float> CHILDREN_ALPHA = new ChildrenAlphaProperty("childrenAlpha");

  private ChildrenAlphaProperty(String paramString)
  {
    super(Float.class, paramString);
  }

  public Float get(ViewGroup paramViewGroup)
  {
    paramViewGroup = (Float)paramViewGroup.getTag(R.id.mtrl_internal_children_alpha_tag);
    if (paramViewGroup != null)
      return paramViewGroup;
    return Float.valueOf(1.0F);
  }

  public void set(ViewGroup paramViewGroup, Float paramFloat)
  {
    float f = paramFloat.floatValue();
    paramViewGroup.setTag(R.id.mtrl_internal_children_alpha_tag, Float.valueOf(f));
    int j = paramViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      paramViewGroup.getChildAt(i).setAlpha(f);
      i += 1;
    }
  }
}