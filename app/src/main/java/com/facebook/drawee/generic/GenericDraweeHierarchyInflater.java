package com.facebook.drawee.generic;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import com.facebook.drawee.R.styleable;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.infer.annotation.ReturnsOwnership;
import javax.annotation.Nullable;

public class GenericDraweeHierarchyInflater
{
  @Nullable
  private static Drawable getDrawable(Context paramContext, TypedArray paramTypedArray, int paramInt)
  {
    paramInt = paramTypedArray.getResourceId(paramInt, 0);
    if (paramInt == 0)
      return null;
    return paramContext.getResources().getDrawable(paramInt);
  }

  @ReturnsOwnership
  private static RoundingParams getRoundingParams(GenericDraweeHierarchyBuilder paramGenericDraweeHierarchyBuilder)
  {
    if (paramGenericDraweeHierarchyBuilder.getRoundingParams() == null)
      paramGenericDraweeHierarchyBuilder.setRoundingParams(new RoundingParams());
    return paramGenericDraweeHierarchyBuilder.getRoundingParams();
  }

  @Nullable
  private static ScalingUtils.ScaleType getScaleTypeFromXml(TypedArray paramTypedArray, int paramInt)
  {
    switch (paramTypedArray.getInt(paramInt, -2))
    {
    default:
      throw new RuntimeException("XML attribute not specified!");
    case 8:
      return ScalingUtils.ScaleType.FIT_BOTTOM_START;
    case 7:
      return ScalingUtils.ScaleType.FOCUS_CROP;
    case 6:
      return ScalingUtils.ScaleType.CENTER_CROP;
    case 5:
      return ScalingUtils.ScaleType.CENTER_INSIDE;
    case 4:
      return ScalingUtils.ScaleType.CENTER;
    case 3:
      return ScalingUtils.ScaleType.FIT_END;
    case 2:
      return ScalingUtils.ScaleType.FIT_CENTER;
    case 1:
      return ScalingUtils.ScaleType.FIT_START;
    case 0:
      return ScalingUtils.ScaleType.FIT_XY;
    case -1:
    }
    return null;
  }

  public static GenericDraweeHierarchyBuilder inflateBuilder(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    return updateBuilder(new GenericDraweeHierarchyBuilder(paramContext.getResources()), paramContext, paramAttributeSet);
  }

  public static GenericDraweeHierarchy inflateHierarchy(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    return inflateBuilder(paramContext, paramAttributeSet).build();
  }

  public static GenericDraweeHierarchyBuilder updateBuilder(GenericDraweeHierarchyBuilder paramGenericDraweeHierarchyBuilder, Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
      paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.GenericDraweeHierarchy);
    int k;
    int j;
    int i;
    try
    {
      k = paramAttributeSet.getIndexCount();
      boolean bool8 = true;
      boolean bool7 = true;
      m = 0;
      boolean bool6 = true;
      boolean bool5 = true;
      boolean bool4 = true;
      boolean bool3 = true;
      boolean bool2 = true;
      boolean bool1 = true;
      j = 0;
      i = 0;
      while (true)
        if (m < k)
          try
          {
            n = paramAttributeSet.getIndex(m);
            if (n == R.styleable.GenericDraweeHierarchy_actualImageScaleType)
            {
              paramGenericDraweeHierarchyBuilder.setActualImageScaleType(getScaleTypeFromXml(paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_placeholderImage)
            {
              paramGenericDraweeHierarchyBuilder.setPlaceholderImage(getDrawable(paramContext, paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_pressedStateOverlayImage)
            {
              paramGenericDraweeHierarchyBuilder.setPressedStateOverlay(getDrawable(paramContext, paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_progressBarImage)
            {
              paramGenericDraweeHierarchyBuilder.setProgressBarImage(getDrawable(paramContext, paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_fadeDuration)
            {
              paramGenericDraweeHierarchyBuilder.setFadeDuration(paramAttributeSet.getInt(n, 0));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_viewAspectRatio)
            {
              paramGenericDraweeHierarchyBuilder.setDesiredAspectRatio(paramAttributeSet.getFloat(n, 0.0F));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_placeholderImageScaleType)
            {
              paramGenericDraweeHierarchyBuilder.setPlaceholderImageScaleType(getScaleTypeFromXml(paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_retryImage)
            {
              paramGenericDraweeHierarchyBuilder.setRetryImage(getDrawable(paramContext, paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_retryImageScaleType)
            {
              paramGenericDraweeHierarchyBuilder.setRetryImageScaleType(getScaleTypeFromXml(paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_failureImage)
            {
              paramGenericDraweeHierarchyBuilder.setFailureImage(getDrawable(paramContext, paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_failureImageScaleType)
            {
              paramGenericDraweeHierarchyBuilder.setFailureImageScaleType(getScaleTypeFromXml(paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_progressBarImageScaleType)
            {
              paramGenericDraweeHierarchyBuilder.setProgressBarImageScaleType(getScaleTypeFromXml(paramAttributeSet, n));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_progressBarAutoRotateInterval)
            {
              j = paramAttributeSet.getInteger(n, j);
              break label1103;
            }
            if (n == R.styleable.GenericDraweeHierarchy_backgroundImage)
            {
              paramGenericDraweeHierarchyBuilder.setBackground(getDrawable(paramContext, paramAttributeSet, n));
              break label1106;
            }
            if (n == R.styleable.GenericDraweeHierarchy_overlayImage)
            {
              paramGenericDraweeHierarchyBuilder.setOverlay(getDrawable(paramContext, paramAttributeSet, n));
              break label1106;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundAsCircle)
            {
              getRoundingParams(paramGenericDraweeHierarchyBuilder).setRoundAsCircle(paramAttributeSet.getBoolean(n, false));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundedCornerRadius)
            {
              i = paramAttributeSet.getDimensionPixelSize(n, i);
              break label1109;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundTopLeft)
            {
              bool6 = paramAttributeSet.getBoolean(n, bool6);
              break label1112;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundTopRight)
            {
              bool3 = paramAttributeSet.getBoolean(n, bool3);
              break label1112;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundBottomLeft)
            {
              bool7 = paramAttributeSet.getBoolean(n, bool7);
              break label1112;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundBottomRight)
            {
              bool2 = paramAttributeSet.getBoolean(n, bool2);
              break label1112;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundTopStart)
            {
              bool5 = paramAttributeSet.getBoolean(n, bool5);
              break label1112;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundTopEnd)
            {
              bool4 = paramAttributeSet.getBoolean(n, bool4);
              break label1112;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundBottomStart)
            {
              bool8 = paramAttributeSet.getBoolean(n, bool8);
              break label1112;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundBottomEnd)
            {
              bool1 = paramAttributeSet.getBoolean(n, bool1);
              break label1112;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundWithOverlayColor)
            {
              getRoundingParams(paramGenericDraweeHierarchyBuilder).setOverlayColor(paramAttributeSet.getColor(n, 0));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundingBorderWidth)
            {
              getRoundingParams(paramGenericDraweeHierarchyBuilder).setBorderWidth(paramAttributeSet.getDimensionPixelSize(n, 0));
              break label1100;
            }
            if (n == R.styleable.GenericDraweeHierarchy_roundingBorderColor)
            {
              getRoundingParams(paramGenericDraweeHierarchyBuilder).setBorderColor(paramAttributeSet.getColor(n, 0));
              break label1100;
            }
            if (n != R.styleable.GenericDraweeHierarchy_roundingBorderPadding)
              break label1100;
            getRoundingParams(paramGenericDraweeHierarchyBuilder).setPadding(paramAttributeSet.getDimensionPixelSize(n, 0));
            m += 1;
          }
          finally
          {
            break label941;
          }
      paramAttributeSet.recycle();
      int i2;
      if ((Build.VERSION.SDK_INT >= 17) && (paramContext.getResources().getConfiguration().getLayoutDirection() == 1))
      {
        if ((bool6) && (bool4))
          k = 1;
        else
          k = 0;
        if ((bool3) && (bool5))
          m = 1;
        else
          m = 0;
        if ((bool2) && (bool8))
          n = 1;
        else
          n = 0;
        if ((bool7) && (bool1))
          i2 = 1;
        else
          i2 = 0;
        i1 = m;
        m = i2;
      }
      else
      {
        if ((bool6) && (bool5))
          k = 1;
        else
          k = 0;
        if ((bool3) && (bool4))
          m = 1;
        else
          m = 0;
        if ((bool2) && (bool1))
          n = 1;
        else
          n = 0;
        if ((bool7) && (bool8))
          i1 = 1;
        else
          i1 = 0;
        i2 = m;
        m = i1;
        i1 = i2;
      }
    }
    finally
    {
      label941: paramAttributeSet.recycle();
      if (Build.VERSION.SDK_INT >= 17)
        paramContext.getResources().getConfiguration().getLayoutDirection();
    }
    int i1 = 1;
    int n = 1;
    int m = 1;
    if ((paramGenericDraweeHierarchyBuilder.getProgressBarImage() != null) && (j > 0))
      paramGenericDraweeHierarchyBuilder.setProgressBarImage(new AutoRotateDrawable(paramGenericDraweeHierarchyBuilder.getProgressBarImage(), j));
    if (i > 0)
    {
      paramContext = getRoundingParams(paramGenericDraweeHierarchyBuilder);
      float f1;
      if (k != 0)
        f1 = i;
      else
        f1 = 0.0F;
      float f2;
      if (i1 != 0)
        f2 = i;
      else
        f2 = 0.0F;
      float f3;
      if (n != 0)
        f3 = i;
      else
        f3 = 0.0F;
      float f4;
      if (m != 0)
        f4 = i;
      else
        f4 = 0.0F;
      paramContext.setCornersRadii(f1, f2, f3, f4);
    }
    return paramGenericDraweeHierarchyBuilder;
    label1100: label1103: label1106: 
    while (true)
    {
      break;
      break;
    }
    label1109: label1112: 
    while (true)
      break;
  }
}