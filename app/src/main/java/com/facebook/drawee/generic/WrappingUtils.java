package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.MatrixDrawable;
import com.facebook.drawee.drawable.Rounded;
import com.facebook.drawee.drawable.RoundedBitmapDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.drawable.RoundedNinePatchDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import javax.annotation.Nullable;

public class WrappingUtils
{
  private static final String TAG = "WrappingUtils";
  private static final Drawable sEmptyDrawable = new ColorDrawable(0);

  private static Drawable applyLeafRounding(Drawable paramDrawable, RoundingParams paramRoundingParams, Resources paramResources)
  {
    if ((paramDrawable instanceof BitmapDrawable))
    {
      paramDrawable = (BitmapDrawable)paramDrawable;
      paramDrawable = new RoundedBitmapDrawable(paramResources, paramDrawable.getBitmap(), paramDrawable.getPaint());
      applyRoundingParams(paramDrawable, paramRoundingParams);
      return paramDrawable;
    }
    if ((paramDrawable instanceof NinePatchDrawable))
    {
      paramDrawable = new RoundedNinePatchDrawable((NinePatchDrawable)paramDrawable);
      applyRoundingParams(paramDrawable, paramRoundingParams);
      return paramDrawable;
    }
    if (((paramDrawable instanceof ColorDrawable)) && (Build.VERSION.SDK_INT >= 11))
    {
      paramDrawable = RoundedColorDrawable.fromColorDrawable((ColorDrawable)paramDrawable);
      applyRoundingParams(paramDrawable, paramRoundingParams);
      return paramDrawable;
    }
    FLog.w("WrappingUtils", "Don't know how to round that drawable: %s", new Object[] { paramDrawable });
    return paramDrawable;
  }

  static void applyRoundingParams(Rounded paramRounded, RoundingParams paramRoundingParams)
  {
    paramRounded.setCircle(paramRoundingParams.getRoundAsCircle());
    paramRounded.setRadii(paramRoundingParams.getCornersRadii());
    paramRounded.setBorder(paramRoundingParams.getBorderColor(), paramRoundingParams.getBorderWidth());
    paramRounded.setPadding(paramRoundingParams.getPadding());
    paramRounded.setScaleDownInsideBorders(paramRoundingParams.getScaleDownInsideBorders());
  }

  static DrawableParent findDrawableParentForLeaf(DrawableParent paramDrawableParent)
  {
    while (true)
    {
      Drawable localDrawable = paramDrawableParent.getDrawable();
      if (localDrawable == paramDrawableParent)
        break;
      if (!(localDrawable instanceof DrawableParent))
        return paramDrawableParent;
      paramDrawableParent = (DrawableParent)localDrawable;
    }
    return paramDrawableParent;
  }

  static Drawable maybeApplyLeafRounding(@Nullable Drawable paramDrawable, @Nullable RoundingParams paramRoundingParams, Resources paramResources)
  {
    if ((paramDrawable != null) && (paramRoundingParams != null))
    {
      if (paramRoundingParams.getRoundingMethod() != RoundingParams.RoundingMethod.BITMAP_ONLY)
        return paramDrawable;
      if ((paramDrawable instanceof ForwardingDrawable))
      {
        DrawableParent localDrawableParent = findDrawableParentForLeaf((ForwardingDrawable)paramDrawable);
        localDrawableParent.setDrawable(applyLeafRounding(localDrawableParent.setDrawable(sEmptyDrawable), paramRoundingParams, paramResources));
        return paramDrawable;
      }
      return applyLeafRounding(paramDrawable, paramRoundingParams, paramResources);
    }
    return paramDrawable;
  }

  @Nullable
  static Drawable maybeWrapWithMatrix(@Nullable Drawable paramDrawable, @Nullable Matrix paramMatrix)
  {
    if (paramDrawable != null)
    {
      if (paramMatrix == null)
        return paramDrawable;
      return new MatrixDrawable(paramDrawable, paramMatrix);
    }
    return paramDrawable;
  }

  static Drawable maybeWrapWithRoundedOverlayColor(@Nullable Drawable paramDrawable, @Nullable RoundingParams paramRoundingParams)
  {
    if ((paramDrawable != null) && (paramRoundingParams != null))
    {
      if (paramRoundingParams.getRoundingMethod() != RoundingParams.RoundingMethod.OVERLAY_COLOR)
        return paramDrawable;
      paramDrawable = new RoundedCornersDrawable(paramDrawable);
      applyRoundingParams(paramDrawable, paramRoundingParams);
      paramDrawable.setOverlayColor(paramRoundingParams.getOverlayColor());
      return paramDrawable;
    }
    return paramDrawable;
  }

  @Nullable
  static Drawable maybeWrapWithScaleType(@Nullable Drawable paramDrawable, @Nullable ScalingUtils.ScaleType paramScaleType)
  {
    return maybeWrapWithScaleType(paramDrawable, paramScaleType, null);
  }

  @Nullable
  static Drawable maybeWrapWithScaleType(@Nullable Drawable paramDrawable, @Nullable ScalingUtils.ScaleType paramScaleType, @Nullable PointF paramPointF)
  {
    if (paramDrawable != null)
    {
      if (paramScaleType == null)
        return paramDrawable;
      paramDrawable = new ScaleTypeDrawable(paramDrawable, paramScaleType);
      if (paramPointF != null)
        paramDrawable.setFocusPoint(paramPointF);
      return paramDrawable;
    }
    return paramDrawable;
  }

  static void resetRoundingParams(Rounded paramRounded)
  {
    paramRounded.setCircle(false);
    paramRounded.setRadius(0.0F);
    paramRounded.setBorder(0, 0.0F);
    paramRounded.setPadding(0.0F);
    paramRounded.setScaleDownInsideBorders(false);
  }

  static void updateLeafRounding(DrawableParent paramDrawableParent, @Nullable RoundingParams paramRoundingParams, Resources paramResources)
  {
    paramDrawableParent = findDrawableParentForLeaf(paramDrawableParent);
    Drawable localDrawable = paramDrawableParent.getDrawable();
    if ((paramRoundingParams != null) && (paramRoundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.BITMAP_ONLY))
    {
      if ((localDrawable instanceof Rounded))
      {
        applyRoundingParams((Rounded)localDrawable, paramRoundingParams);
        return;
      }
      if (localDrawable != null)
      {
        paramDrawableParent.setDrawable(sEmptyDrawable);
        paramDrawableParent.setDrawable(applyLeafRounding(localDrawable, paramRoundingParams, paramResources));
      }
    }
    else if ((localDrawable instanceof Rounded))
    {
      resetRoundingParams((Rounded)localDrawable);
    }
  }

  static void updateOverlayColorRounding(DrawableParent paramDrawableParent, @Nullable RoundingParams paramRoundingParams)
  {
    Drawable localDrawable = paramDrawableParent.getDrawable();
    if ((paramRoundingParams != null) && (paramRoundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.OVERLAY_COLOR))
    {
      if ((localDrawable instanceof RoundedCornersDrawable))
      {
        paramDrawableParent = (RoundedCornersDrawable)localDrawable;
        applyRoundingParams(paramDrawableParent, paramRoundingParams);
        paramDrawableParent.setOverlayColor(paramRoundingParams.getOverlayColor());
        return;
      }
      paramDrawableParent.setDrawable(maybeWrapWithRoundedOverlayColor(paramDrawableParent.setDrawable(sEmptyDrawable), paramRoundingParams));
      return;
    }
    if ((localDrawable instanceof RoundedCornersDrawable))
    {
      paramDrawableParent.setDrawable(((RoundedCornersDrawable)localDrawable).setCurrent(sEmptyDrawable));
      sEmptyDrawable.setCallback(null);
    }
  }

  static ScaleTypeDrawable wrapChildWithScaleType(DrawableParent paramDrawableParent, ScalingUtils.ScaleType paramScaleType)
  {
    paramScaleType = maybeWrapWithScaleType(paramDrawableParent.setDrawable(sEmptyDrawable), paramScaleType);
    paramDrawableParent.setDrawable(paramScaleType);
    Preconditions.checkNotNull(paramScaleType, "Parent has no child drawable!");
    return (ScaleTypeDrawable)paramScaleType;
  }
}