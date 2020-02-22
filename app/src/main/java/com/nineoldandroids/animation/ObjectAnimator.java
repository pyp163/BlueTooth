package com.nineoldandroids.animation;

import android.view.View;
import com.nineoldandroids.util.Property;
import com.nineoldandroids.view.animation.AnimatorProxy;
import java.util.HashMap;
import java.util.Map;

public final class ObjectAnimator extends ValueAnimator
{
  private static final boolean DBG = false;
  private static final Map<String, Property> PROXY_PROPERTIES = new HashMap();
  private Property mProperty;
  private String mPropertyName;
  private Object mTarget;

  static
  {
    PROXY_PROPERTIES.put("alpha", PreHoneycombCompat.ALPHA);
    PROXY_PROPERTIES.put("pivotX", PreHoneycombCompat.PIVOT_X);
    PROXY_PROPERTIES.put("pivotY", PreHoneycombCompat.PIVOT_Y);
    PROXY_PROPERTIES.put("translationX", PreHoneycombCompat.TRANSLATION_X);
    PROXY_PROPERTIES.put("translationY", PreHoneycombCompat.TRANSLATION_Y);
    PROXY_PROPERTIES.put("rotation", PreHoneycombCompat.ROTATION);
    PROXY_PROPERTIES.put("rotationX", PreHoneycombCompat.ROTATION_X);
    PROXY_PROPERTIES.put("rotationY", PreHoneycombCompat.ROTATION_Y);
    PROXY_PROPERTIES.put("scaleX", PreHoneycombCompat.SCALE_X);
    PROXY_PROPERTIES.put("scaleY", PreHoneycombCompat.SCALE_Y);
    PROXY_PROPERTIES.put("scrollX", PreHoneycombCompat.SCROLL_X);
    PROXY_PROPERTIES.put("scrollY", PreHoneycombCompat.SCROLL_Y);
    PROXY_PROPERTIES.put("x", PreHoneycombCompat.X);
    PROXY_PROPERTIES.put("y", PreHoneycombCompat.Y);
  }

  public ObjectAnimator()
  {
  }

  private <T> ObjectAnimator(T paramT, Property<T, ?> paramProperty)
  {
    this.mTarget = paramT;
    setProperty(paramProperty);
  }

  private ObjectAnimator(Object paramObject, String paramString)
  {
    this.mTarget = paramObject;
    setPropertyName(paramString);
  }

  public static <T> ObjectAnimator ofFloat(T paramT, Property<T, Float> paramProperty, float[] paramArrayOfFloat)
  {
    paramT = new ObjectAnimator(paramT, paramProperty);
    paramT.setFloatValues(paramArrayOfFloat);
    return paramT;
  }

  public static ObjectAnimator ofFloat(Object paramObject, String paramString, float[] paramArrayOfFloat)
  {
    paramObject = new ObjectAnimator(paramObject, paramString);
    paramObject.setFloatValues(paramArrayOfFloat);
    return paramObject;
  }

  public static <T> ObjectAnimator ofInt(T paramT, Property<T, Integer> paramProperty, int[] paramArrayOfInt)
  {
    paramT = new ObjectAnimator(paramT, paramProperty);
    paramT.setIntValues(paramArrayOfInt);
    return paramT;
  }

  public static ObjectAnimator ofInt(Object paramObject, String paramString, int[] paramArrayOfInt)
  {
    paramObject = new ObjectAnimator(paramObject, paramString);
    paramObject.setIntValues(paramArrayOfInt);
    return paramObject;
  }

  public static <T, V> ObjectAnimator ofObject(T paramT, Property<T, V> paramProperty, TypeEvaluator<V> paramTypeEvaluator, V[] paramArrayOfV)
  {
    paramT = new ObjectAnimator(paramT, paramProperty);
    paramT.setObjectValues(paramArrayOfV);
    paramT.setEvaluator(paramTypeEvaluator);
    return paramT;
  }

  public static ObjectAnimator ofObject(Object paramObject, String paramString, TypeEvaluator paramTypeEvaluator, Object[] paramArrayOfObject)
  {
    paramObject = new ObjectAnimator(paramObject, paramString);
    paramObject.setObjectValues(paramArrayOfObject);
    paramObject.setEvaluator(paramTypeEvaluator);
    return paramObject;
  }

  public static ObjectAnimator ofPropertyValuesHolder(Object paramObject, PropertyValuesHolder[] paramArrayOfPropertyValuesHolder)
  {
    ObjectAnimator localObjectAnimator = new ObjectAnimator();
    localObjectAnimator.mTarget = paramObject;
    localObjectAnimator.setValues(paramArrayOfPropertyValuesHolder);
    return localObjectAnimator;
  }

  void animateValue(float paramFloat)
  {
    super.animateValue(paramFloat);
    int j = this.mValues.length;
    int i = 0;
    while (i < j)
    {
      this.mValues[i].setAnimatedValue(this.mTarget);
      i += 1;
    }
  }

  public ObjectAnimator clone()
  {
    return (ObjectAnimator)super.clone();
  }

  public String getPropertyName()
  {
    return this.mPropertyName;
  }

  public Object getTarget()
  {
    return this.mTarget;
  }

  void initAnimation()
  {
    if (!this.mInitialized)
    {
      if ((this.mProperty == null) && (AnimatorProxy.NEEDS_PROXY) && ((this.mTarget instanceof View)) && (PROXY_PROPERTIES.containsKey(this.mPropertyName)))
        setProperty((Property)PROXY_PROPERTIES.get(this.mPropertyName));
      int j = this.mValues.length;
      int i = 0;
      while (i < j)
      {
        this.mValues[i].setupSetterAndGetter(this.mTarget);
        i += 1;
      }
      super.initAnimation();
    }
  }

  public ObjectAnimator setDuration(long paramLong)
  {
    super.setDuration(paramLong);
    return this;
  }

  public void setFloatValues(float[] paramArrayOfFloat)
  {
    if ((this.mValues != null) && (this.mValues.length != 0))
    {
      super.setFloatValues(paramArrayOfFloat);
      return;
    }
    if (this.mProperty != null)
    {
      setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(this.mProperty, paramArrayOfFloat) });
      return;
    }
    setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofFloat(this.mPropertyName, paramArrayOfFloat) });
  }

  public void setIntValues(int[] paramArrayOfInt)
  {
    if ((this.mValues != null) && (this.mValues.length != 0))
    {
      super.setIntValues(paramArrayOfInt);
      return;
    }
    if (this.mProperty != null)
    {
      setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofInt(this.mProperty, paramArrayOfInt) });
      return;
    }
    setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofInt(this.mPropertyName, paramArrayOfInt) });
  }

  public void setObjectValues(Object[] paramArrayOfObject)
  {
    if ((this.mValues != null) && (this.mValues.length != 0))
    {
      super.setObjectValues(paramArrayOfObject);
      return;
    }
    if (this.mProperty != null)
    {
      setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofObject(this.mProperty, (TypeEvaluator)null, paramArrayOfObject) });
      return;
    }
    setValues(new PropertyValuesHolder[] { PropertyValuesHolder.ofObject(this.mPropertyName, (TypeEvaluator)null, paramArrayOfObject) });
  }

  public void setProperty(Property paramProperty)
  {
    if (this.mValues != null)
    {
      PropertyValuesHolder localPropertyValuesHolder = this.mValues[0];
      String str = localPropertyValuesHolder.getPropertyName();
      localPropertyValuesHolder.setProperty(paramProperty);
      this.mValuesMap.remove(str);
      this.mValuesMap.put(this.mPropertyName, localPropertyValuesHolder);
    }
    if (this.mProperty != null)
      this.mPropertyName = paramProperty.getName();
    this.mProperty = paramProperty;
    this.mInitialized = false;
  }

  public void setPropertyName(String paramString)
  {
    if (this.mValues != null)
    {
      PropertyValuesHolder localPropertyValuesHolder = this.mValues[0];
      String str = localPropertyValuesHolder.getPropertyName();
      localPropertyValuesHolder.setPropertyName(paramString);
      this.mValuesMap.remove(str);
      this.mValuesMap.put(paramString, localPropertyValuesHolder);
    }
    this.mPropertyName = paramString;
    this.mInitialized = false;
  }

  public void setTarget(Object paramObject)
  {
    if (this.mTarget != paramObject)
    {
      Object localObject = this.mTarget;
      this.mTarget = paramObject;
      if ((localObject != null) && (paramObject != null) && (localObject.getClass() == paramObject.getClass()))
        return;
      this.mInitialized = false;
    }
  }

  public void setupEndValues()
  {
    initAnimation();
    int j = this.mValues.length;
    int i = 0;
    while (i < j)
    {
      this.mValues[i].setupEndValue(this.mTarget);
      i += 1;
    }
  }

  public void setupStartValues()
  {
    initAnimation();
    int j = this.mValues.length;
    int i = 0;
    while (i < j)
    {
      this.mValues[i].setupStartValue(this.mTarget);
      i += 1;
    }
  }

  public void start()
  {
    super.start();
  }

  public String toString()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("ObjectAnimator@");
    ((StringBuilder)localObject1).append(Integer.toHexString(hashCode()));
    ((StringBuilder)localObject1).append(", target ");
    ((StringBuilder)localObject1).append(this.mTarget);
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = localObject1;
    if (this.mValues != null)
    {
      int i = 0;
      while (true)
      {
        localObject2 = localObject1;
        if (i >= this.mValues.length)
          break;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("\n    ");
        ((StringBuilder)localObject2).append(this.mValues[i].toString());
        localObject1 = ((StringBuilder)localObject2).toString();
        i += 1;
      }
    }
    return localObject2;
  }
}