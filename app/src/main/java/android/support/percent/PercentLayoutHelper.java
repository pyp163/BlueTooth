package android.support.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

@Deprecated
public class PercentLayoutHelper
{
  private static final boolean DEBUG = false;
  private static final String TAG = "PercentLayout";
  private static final boolean VERBOSE = false;
  private final ViewGroup mHost;

  public PercentLayoutHelper(@NonNull ViewGroup paramViewGroup)
  {
    if (paramViewGroup == null)
      throw new IllegalArgumentException("host must be non-null");
    this.mHost = paramViewGroup;
  }

  public static void fetchWidthAndHeight(ViewGroup.LayoutParams paramLayoutParams, TypedArray paramTypedArray, int paramInt1, int paramInt2)
  {
    paramLayoutParams.width = paramTypedArray.getLayoutDimension(paramInt1, 0);
    paramLayoutParams.height = paramTypedArray.getLayoutDimension(paramInt2, 0);
  }

  public static PercentLayoutInfo getPercentLayoutInfo(Context paramContext, AttributeSet paramAttributeSet)
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PercentLayout_Layout);
    float f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_widthPercent, 1, 1, -1.0F);
    if (f != -1.0F)
    {
      paramAttributeSet = new PercentLayoutInfo();
      paramAttributeSet.widthPercent = f;
    }
    else
    {
      paramAttributeSet = null;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_heightPercent, 1, 1, -1.0F);
    paramContext = paramAttributeSet;
    if (f != -1.0F)
    {
      if (paramAttributeSet != null)
        paramContext = paramAttributeSet;
      else
        paramContext = new PercentLayoutInfo();
      paramContext.heightPercent = f;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginPercent, 1, 1, -1.0F);
    paramAttributeSet = paramContext;
    if (f != -1.0F)
    {
      if (paramContext == null)
        paramContext = new PercentLayoutInfo();
      paramContext.leftMarginPercent = f;
      paramContext.topMarginPercent = f;
      paramContext.rightMarginPercent = f;
      paramContext.bottomMarginPercent = f;
      paramAttributeSet = paramContext;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginLeftPercent, 1, 1, -1.0F);
    paramContext = paramAttributeSet;
    if (f != -1.0F)
    {
      if (paramAttributeSet != null)
        paramContext = paramAttributeSet;
      else
        paramContext = new PercentLayoutInfo();
      paramContext.leftMarginPercent = f;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginTopPercent, 1, 1, -1.0F);
    paramAttributeSet = paramContext;
    if (f != -1.0F)
    {
      if (paramContext == null)
        paramContext = new PercentLayoutInfo();
      paramContext.topMarginPercent = f;
      paramAttributeSet = paramContext;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginRightPercent, 1, 1, -1.0F);
    paramContext = paramAttributeSet;
    if (f != -1.0F)
    {
      if (paramAttributeSet != null)
        paramContext = paramAttributeSet;
      else
        paramContext = new PercentLayoutInfo();
      paramContext.rightMarginPercent = f;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginBottomPercent, 1, 1, -1.0F);
    paramAttributeSet = paramContext;
    if (f != -1.0F)
    {
      if (paramContext == null)
        paramContext = new PercentLayoutInfo();
      paramContext.bottomMarginPercent = f;
      paramAttributeSet = paramContext;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginStartPercent, 1, 1, -1.0F);
    paramContext = paramAttributeSet;
    if (f != -1.0F)
    {
      if (paramAttributeSet != null)
        paramContext = paramAttributeSet;
      else
        paramContext = new PercentLayoutInfo();
      paramContext.startMarginPercent = f;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_marginEndPercent, 1, 1, -1.0F);
    paramAttributeSet = paramContext;
    if (f != -1.0F)
    {
      if (paramContext == null)
        paramContext = new PercentLayoutInfo();
      paramContext.endMarginPercent = f;
      paramAttributeSet = paramContext;
    }
    f = localTypedArray.getFraction(R.styleable.PercentLayout_Layout_layout_aspectRatio, 1, 1, -1.0F);
    paramContext = paramAttributeSet;
    if (f != -1.0F)
    {
      if (paramAttributeSet == null)
        paramAttributeSet = new PercentLayoutInfo();
      paramAttributeSet.aspectRatio = f;
      paramContext = paramAttributeSet;
    }
    localTypedArray.recycle();
    return paramContext;
  }

  private static boolean shouldHandleMeasuredHeightTooSmall(View paramView, PercentLayoutInfo paramPercentLayoutInfo)
  {
    return ((paramView.getMeasuredHeightAndState() & 0xFF000000) == 16777216) && (paramPercentLayoutInfo.heightPercent >= 0.0F) && (paramPercentLayoutInfo.mPreservedParams.height == -2);
  }

  private static boolean shouldHandleMeasuredWidthTooSmall(View paramView, PercentLayoutInfo paramPercentLayoutInfo)
  {
    return ((paramView.getMeasuredWidthAndState() & 0xFF000000) == 16777216) && (paramPercentLayoutInfo.widthPercent >= 0.0F) && (paramPercentLayoutInfo.mPreservedParams.width == -2);
  }

  public void adjustChildren(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1) - this.mHost.getPaddingLeft() - this.mHost.getPaddingRight();
    paramInt2 = View.MeasureSpec.getSize(paramInt2) - this.mHost.getPaddingTop() - this.mHost.getPaddingBottom();
    int j = this.mHost.getChildCount();
    paramInt1 = 0;
    while (paramInt1 < j)
    {
      View localView = this.mHost.getChildAt(paramInt1);
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      if ((localLayoutParams instanceof PercentLayoutParams))
      {
        PercentLayoutInfo localPercentLayoutInfo = ((PercentLayoutParams)localLayoutParams).getPercentLayoutInfo();
        if (localPercentLayoutInfo != null)
          if ((localLayoutParams instanceof ViewGroup.MarginLayoutParams))
            localPercentLayoutInfo.fillMarginLayoutParams(localView, (ViewGroup.MarginLayoutParams)localLayoutParams, i, paramInt2);
          else
            localPercentLayoutInfo.fillLayoutParams(localLayoutParams, i, paramInt2);
      }
      paramInt1 += 1;
    }
  }

  public boolean handleMeasuredStateTooSmall()
  {
    int j = this.mHost.getChildCount();
    int i = 0;
    boolean bool2;
    for (boolean bool1 = false; i < j; bool1 = bool2)
    {
      View localView = this.mHost.getChildAt(i);
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      bool2 = bool1;
      if ((localLayoutParams instanceof PercentLayoutParams))
      {
        PercentLayoutInfo localPercentLayoutInfo = ((PercentLayoutParams)localLayoutParams).getPercentLayoutInfo();
        bool2 = bool1;
        if (localPercentLayoutInfo != null)
        {
          if (shouldHandleMeasuredWidthTooSmall(localView, localPercentLayoutInfo))
          {
            localLayoutParams.width = -2;
            bool1 = true;
          }
          bool2 = bool1;
          if (shouldHandleMeasuredHeightTooSmall(localView, localPercentLayoutInfo))
          {
            localLayoutParams.height = -2;
            bool2 = true;
          }
        }
      }
      i += 1;
    }
    return bool1;
  }

  public void restoreOriginalParams()
  {
    int j = this.mHost.getChildCount();
    int i = 0;
    while (i < j)
    {
      ViewGroup.LayoutParams localLayoutParams = this.mHost.getChildAt(i).getLayoutParams();
      if ((localLayoutParams instanceof PercentLayoutParams))
      {
        PercentLayoutInfo localPercentLayoutInfo = ((PercentLayoutParams)localLayoutParams).getPercentLayoutInfo();
        if (localPercentLayoutInfo != null)
          if ((localLayoutParams instanceof ViewGroup.MarginLayoutParams))
            localPercentLayoutInfo.restoreMarginLayoutParams((ViewGroup.MarginLayoutParams)localLayoutParams);
          else
            localPercentLayoutInfo.restoreLayoutParams(localLayoutParams);
      }
      i += 1;
    }
  }

  @Deprecated
  public static class PercentLayoutInfo
  {
    public float aspectRatio;
    public float bottomMarginPercent = -1.0F;
    public float endMarginPercent = -1.0F;
    public float heightPercent = -1.0F;
    public float leftMarginPercent = -1.0F;
    final PercentLayoutHelper.PercentMarginLayoutParams mPreservedParams = new PercentLayoutHelper.PercentMarginLayoutParams(0, 0);
    public float rightMarginPercent = -1.0F;
    public float startMarginPercent = -1.0F;
    public float topMarginPercent = -1.0F;
    public float widthPercent = -1.0F;

    public void fillLayoutParams(ViewGroup.LayoutParams paramLayoutParams, int paramInt1, int paramInt2)
    {
      this.mPreservedParams.width = paramLayoutParams.width;
      this.mPreservedParams.height = paramLayoutParams.height;
      boolean bool = this.mPreservedParams.mIsWidthComputedFromAspectRatio;
      int k = 0;
      int i;
      if (((bool) || (this.mPreservedParams.width == 0)) && (this.widthPercent < 0.0F))
        i = 1;
      else
        i = 0;
      int j;
      if (!this.mPreservedParams.mIsHeightComputedFromAspectRatio)
      {
        j = k;
        if (this.mPreservedParams.height != 0);
      }
      else
      {
        j = k;
        if (this.heightPercent < 0.0F)
          j = 1;
      }
      if (this.widthPercent >= 0.0F)
        paramLayoutParams.width = Math.round(paramInt1 * this.widthPercent);
      if (this.heightPercent >= 0.0F)
        paramLayoutParams.height = Math.round(paramInt2 * this.heightPercent);
      if (this.aspectRatio >= 0.0F)
      {
        if (i != 0)
        {
          paramLayoutParams.width = Math.round(paramLayoutParams.height * this.aspectRatio);
          this.mPreservedParams.mIsWidthComputedFromAspectRatio = true;
        }
        if (j != 0)
        {
          paramLayoutParams.height = Math.round(paramLayoutParams.width / this.aspectRatio);
          this.mPreservedParams.mIsHeightComputedFromAspectRatio = true;
        }
      }
    }

    public void fillMarginLayoutParams(View paramView, ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt1, int paramInt2)
    {
      fillLayoutParams(paramMarginLayoutParams, paramInt1, paramInt2);
      this.mPreservedParams.leftMargin = paramMarginLayoutParams.leftMargin;
      this.mPreservedParams.topMargin = paramMarginLayoutParams.topMargin;
      this.mPreservedParams.rightMargin = paramMarginLayoutParams.rightMargin;
      this.mPreservedParams.bottomMargin = paramMarginLayoutParams.bottomMargin;
      MarginLayoutParamsCompat.setMarginStart(this.mPreservedParams, MarginLayoutParamsCompat.getMarginStart(paramMarginLayoutParams));
      MarginLayoutParamsCompat.setMarginEnd(this.mPreservedParams, MarginLayoutParamsCompat.getMarginEnd(paramMarginLayoutParams));
      if (this.leftMarginPercent >= 0.0F)
        paramMarginLayoutParams.leftMargin = Math.round(paramInt1 * this.leftMarginPercent);
      if (this.topMarginPercent >= 0.0F)
        paramMarginLayoutParams.topMargin = Math.round(paramInt2 * this.topMarginPercent);
      if (this.rightMarginPercent >= 0.0F)
        paramMarginLayoutParams.rightMargin = Math.round(paramInt1 * this.rightMarginPercent);
      if (this.bottomMarginPercent >= 0.0F)
        paramMarginLayoutParams.bottomMargin = Math.round(paramInt2 * this.bottomMarginPercent);
      paramInt2 = 0;
      if (this.startMarginPercent >= 0.0F)
      {
        MarginLayoutParamsCompat.setMarginStart(paramMarginLayoutParams, Math.round(paramInt1 * this.startMarginPercent));
        paramInt2 = 1;
      }
      if (this.endMarginPercent >= 0.0F)
      {
        MarginLayoutParamsCompat.setMarginEnd(paramMarginLayoutParams, Math.round(paramInt1 * this.endMarginPercent));
        paramInt2 = 1;
      }
      if ((paramInt2 != 0) && (paramView != null))
        MarginLayoutParamsCompat.resolveLayoutDirection(paramMarginLayoutParams, ViewCompat.getLayoutDirection(paramView));
    }

    @Deprecated
    public void fillMarginLayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams, int paramInt1, int paramInt2)
    {
      fillMarginLayoutParams(null, paramMarginLayoutParams, paramInt1, paramInt2);
    }

    public void restoreLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      if (!this.mPreservedParams.mIsWidthComputedFromAspectRatio)
        paramLayoutParams.width = this.mPreservedParams.width;
      if (!this.mPreservedParams.mIsHeightComputedFromAspectRatio)
        paramLayoutParams.height = this.mPreservedParams.height;
      this.mPreservedParams.mIsWidthComputedFromAspectRatio = false;
      this.mPreservedParams.mIsHeightComputedFromAspectRatio = false;
    }

    public void restoreMarginLayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      restoreLayoutParams(paramMarginLayoutParams);
      paramMarginLayoutParams.leftMargin = this.mPreservedParams.leftMargin;
      paramMarginLayoutParams.topMargin = this.mPreservedParams.topMargin;
      paramMarginLayoutParams.rightMargin = this.mPreservedParams.rightMargin;
      paramMarginLayoutParams.bottomMargin = this.mPreservedParams.bottomMargin;
      MarginLayoutParamsCompat.setMarginStart(paramMarginLayoutParams, MarginLayoutParamsCompat.getMarginStart(this.mPreservedParams));
      MarginLayoutParamsCompat.setMarginEnd(paramMarginLayoutParams, MarginLayoutParamsCompat.getMarginEnd(this.mPreservedParams));
    }

    public String toString()
    {
      return String.format("PercentLayoutInformation width: %f height %f, margins (%f, %f,  %f, %f, %f, %f)", new Object[] { Float.valueOf(this.widthPercent), Float.valueOf(this.heightPercent), Float.valueOf(this.leftMarginPercent), Float.valueOf(this.topMarginPercent), Float.valueOf(this.rightMarginPercent), Float.valueOf(this.bottomMarginPercent), Float.valueOf(this.startMarginPercent), Float.valueOf(this.endMarginPercent) });
    }
  }

  @Deprecated
  public static abstract interface PercentLayoutParams
  {
    public abstract PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo();
  }

  static class PercentMarginLayoutParams extends ViewGroup.MarginLayoutParams
  {
    boolean mIsHeightComputedFromAspectRatio;
    boolean mIsWidthComputedFromAspectRatio;

    public PercentMarginLayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
  }
}