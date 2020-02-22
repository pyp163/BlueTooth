package com.facebook.drawee.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.graphics.PointF;
import android.graphics.Rect;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.drawable.ScalingUtils.InterpolatingScaleType;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import java.util.Map;
import javax.annotation.Nullable;

@TargetApi(19)
public class DraweeTransition extends Transition
{
  private static final String PROPNAME_BOUNDS = "draweeTransition:bounds";

  @Nullable
  private final PointF mFromFocusPoint;
  private final ScalingUtils.ScaleType mFromScale;

  @Nullable
  private final PointF mToFocusPoint;
  private final ScalingUtils.ScaleType mToScale;

  public DraweeTransition(ScalingUtils.ScaleType paramScaleType1, ScalingUtils.ScaleType paramScaleType2)
  {
    this(paramScaleType1, paramScaleType2, null, null);
  }

  public DraweeTransition(ScalingUtils.ScaleType paramScaleType1, ScalingUtils.ScaleType paramScaleType2, @Nullable PointF paramPointF1, @Nullable PointF paramPointF2)
  {
    this.mFromScale = paramScaleType1;
    this.mToScale = paramScaleType2;
    this.mFromFocusPoint = paramPointF1;
    this.mToFocusPoint = paramPointF2;
  }

  private void captureValues(TransitionValues paramTransitionValues)
  {
    if ((paramTransitionValues.view instanceof GenericDraweeView))
      paramTransitionValues.values.put("draweeTransition:bounds", new Rect(0, 0, paramTransitionValues.view.getWidth(), paramTransitionValues.view.getHeight()));
  }

  public static TransitionSet createTransitionSet(ScalingUtils.ScaleType paramScaleType1, ScalingUtils.ScaleType paramScaleType2)
  {
    return createTransitionSet(paramScaleType1, paramScaleType2, null, null);
  }

  public static TransitionSet createTransitionSet(ScalingUtils.ScaleType paramScaleType1, ScalingUtils.ScaleType paramScaleType2, @Nullable PointF paramPointF1, @Nullable PointF paramPointF2)
  {
    TransitionSet localTransitionSet = new TransitionSet();
    localTransitionSet.addTransition(new ChangeBounds());
    localTransitionSet.addTransition(new DraweeTransition(paramScaleType1, paramScaleType2, paramPointF1, paramPointF2));
    return localTransitionSet;
  }

  public void captureEndValues(TransitionValues paramTransitionValues)
  {
    captureValues(paramTransitionValues);
  }

  public void captureStartValues(TransitionValues paramTransitionValues)
  {
    captureValues(paramTransitionValues);
  }

  public Animator createAnimator(final ViewGroup paramViewGroup, final TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    if (paramTransitionValues1 != null)
    {
      if (paramTransitionValues2 == null)
        return null;
      paramViewGroup = (Rect)paramTransitionValues1.values.get("draweeTransition:bounds");
      paramTransitionValues2 = (Rect)paramTransitionValues2.values.get("draweeTransition:bounds");
      if (paramViewGroup != null)
      {
        if (paramTransitionValues2 == null)
          return null;
        if ((this.mFromScale == this.mToScale) && (this.mFromFocusPoint == this.mToFocusPoint))
          return null;
        paramTransitionValues1 = (GenericDraweeView)paramTransitionValues1.view;
        paramViewGroup = new ScalingUtils.InterpolatingScaleType(this.mFromScale, this.mToScale, paramViewGroup, paramTransitionValues2, this.mFromFocusPoint, this.mToFocusPoint);
        ((GenericDraweeHierarchy)paramTransitionValues1.getHierarchy()).setActualImageScaleType(paramViewGroup);
        paramTransitionValues2 = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
        paramTransitionValues2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
          public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
          {
            float f = ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue();
            paramViewGroup.setValue(f);
          }
        });
        paramTransitionValues2.addListener(new AnimatorListenerAdapter()
        {
          public void onAnimationEnd(Animator paramAnonymousAnimator)
          {
            ((GenericDraweeHierarchy)paramTransitionValues1.getHierarchy()).setActualImageScaleType(DraweeTransition.this.mToScale);
            if (DraweeTransition.this.mToFocusPoint != null)
              ((GenericDraweeHierarchy)paramTransitionValues1.getHierarchy()).setActualImageFocusPoint(DraweeTransition.this.mToFocusPoint);
          }
        });
        return paramTransitionValues2;
      }
      return null;
    }
    return null;
  }
}