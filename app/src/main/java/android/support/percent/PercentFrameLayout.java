package android.support.percent;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

@Deprecated
public class PercentFrameLayout extends FrameLayout
{
  private final PercentLayoutHelper mHelper = new PercentLayoutHelper(this);

  public PercentFrameLayout(Context paramContext)
  {
    super(paramContext);
  }

  public PercentFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public PercentFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams(-1, -1);
  }

  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mHelper.restoreOriginalParams();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mHelper.adjustChildren(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
    if (this.mHelper.handleMeasuredStateTooSmall())
      super.onMeasure(paramInt1, paramInt2);
  }

  @Deprecated
  public static class LayoutParams extends FrameLayout.LayoutParams
    implements PercentLayoutHelper.PercentLayoutParams
  {
    private PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;

    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2, paramInt3);
    }

    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      this.mPercentLayoutInfo = PercentLayoutHelper.getPercentLayoutInfo(paramContext, paramAttributeSet);
    }

    @RequiresApi(19)
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      this(paramLayoutParams);
      this.mPercentLayoutInfo = paramLayoutParams.mPercentLayoutInfo;
    }

    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }

    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }

    public LayoutParams(FrameLayout.LayoutParams paramLayoutParams)
    {
      super();
      this.gravity = paramLayoutParams.gravity;
    }

    public PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo()
    {
      if (this.mPercentLayoutInfo == null)
        this.mPercentLayoutInfo = new PercentLayoutHelper.PercentLayoutInfo();
      return this.mPercentLayoutInfo;
    }

    protected void setBaseAttributes(TypedArray paramTypedArray, int paramInt1, int paramInt2)
    {
      PercentLayoutHelper.fetchWidthAndHeight(this, paramTypedArray, paramInt1, paramInt2);
    }
  }
}