package android.support.design.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.math.MathUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import java.util.List;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View>
{
  private int overlayTop;
  final Rect tempRect1 = new Rect();
  final Rect tempRect2 = new Rect();
  private int verticalLayoutGap = 0;

  public HeaderScrollingViewBehavior()
  {
  }

  public HeaderScrollingViewBehavior(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private static int resolveGravity(int paramInt)
  {
    int i = paramInt;
    if (paramInt == 0)
      i = 8388659;
    return i;
  }

  abstract View findFirstDependency(List<View> paramList);

  final int getOverlapPixelsForOffset(View paramView)
  {
    if (this.overlayTop == 0)
      return 0;
    return MathUtils.clamp((int)(getOverlapRatioForOffset(paramView) * this.overlayTop), 0, this.overlayTop);
  }

  float getOverlapRatioForOffset(View paramView)
  {
    return 1.0F;
  }

  public final int getOverlayTop()
  {
    return this.overlayTop;
  }

  int getScrollRange(View paramView)
  {
    return paramView.getMeasuredHeight();
  }

  final int getVerticalLayoutGap()
  {
    return this.verticalLayoutGap;
  }

  protected void layoutChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt)
  {
    View localView = findFirstDependency(paramCoordinatorLayout.getDependencies(paramView));
    if (localView != null)
    {
      CoordinatorLayout.LayoutParams localLayoutParams = (CoordinatorLayout.LayoutParams)paramView.getLayoutParams();
      Rect localRect = this.tempRect1;
      localRect.set(paramCoordinatorLayout.getPaddingLeft() + localLayoutParams.leftMargin, localView.getBottom() + localLayoutParams.topMargin, paramCoordinatorLayout.getWidth() - paramCoordinatorLayout.getPaddingRight() - localLayoutParams.rightMargin, paramCoordinatorLayout.getHeight() + localView.getBottom() - paramCoordinatorLayout.getPaddingBottom() - localLayoutParams.bottomMargin);
      WindowInsetsCompat localWindowInsetsCompat = paramCoordinatorLayout.getLastWindowInsets();
      if ((localWindowInsetsCompat != null) && (ViewCompat.getFitsSystemWindows(paramCoordinatorLayout)) && (!ViewCompat.getFitsSystemWindows(paramView)))
      {
        localRect.left += localWindowInsetsCompat.getSystemWindowInsetLeft();
        localRect.right -= localWindowInsetsCompat.getSystemWindowInsetRight();
      }
      paramCoordinatorLayout = this.tempRect2;
      GravityCompat.apply(resolveGravity(localLayoutParams.gravity), paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), localRect, paramCoordinatorLayout, paramInt);
      paramInt = getOverlapPixelsForOffset(localView);
      paramView.layout(paramCoordinatorLayout.left, paramCoordinatorLayout.top - paramInt, paramCoordinatorLayout.right, paramCoordinatorLayout.bottom - paramInt);
      this.verticalLayoutGap = (paramCoordinatorLayout.top - localView.getBottom());
      return;
    }
    super.layoutChild(paramCoordinatorLayout, paramView, paramInt);
    this.verticalLayoutGap = 0;
  }

  public boolean onMeasureChild(CoordinatorLayout paramCoordinatorLayout, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = paramView.getLayoutParams().height;
    if ((j == -1) || (j == -2))
    {
      View localView2 = findFirstDependency(paramCoordinatorLayout.getDependencies(paramView));
      if (localView2 != null)
      {
        if ((ViewCompat.getFitsSystemWindows(localView2)) && (!ViewCompat.getFitsSystemWindows(paramView)))
        {
          View localView1 = paramView;
          ViewCompat.setFitsSystemWindows(localView1, true);
          if (ViewCompat.getFitsSystemWindows(localView1))
          {
            localView1.requestLayout();
            return true;
          }
        }
        int i = View.MeasureSpec.getSize(paramInt3);
        paramInt3 = i;
        if (i == 0)
          paramInt3 = paramCoordinatorLayout.getHeight();
        int k = localView2.getMeasuredHeight();
        int m = getScrollRange(localView2);
        if (j == -1)
          i = 1073741824;
        else
          i = -2147483648;
        paramCoordinatorLayout.onMeasureChild(paramView, paramInt1, paramInt2, View.MeasureSpec.makeMeasureSpec(paramInt3 - k + m, i), paramInt4);
        return true;
      }
    }
    return false;
  }

  public final void setOverlayTop(int paramInt)
  {
    this.overlayTop = paramInt;
  }
}