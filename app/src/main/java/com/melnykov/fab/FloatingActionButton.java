package com.melnykov.fab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageButton;
import android.widget.ScrollView;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FloatingActionButton extends ImageButton
{
  private static final int TRANSLATE_DURATION_MILLIS = 200;
  public static final int TYPE_MINI = 1;
  public static final int TYPE_NORMAL = 0;
  private int mColorDisabled;
  private int mColorNormal;
  private int mColorPressed;
  private int mColorRipple;
  private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
  private boolean mMarginsSet;
  private int mScrollThreshold;
  private boolean mShadow;
  private int mShadowSize;
  private int mType;
  private boolean mVisible;

  public FloatingActionButton(Context paramContext)
  {
    this(paramContext, null);
  }

  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public FloatingActionButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }

  private Drawable createDrawable(int paramInt)
  {
    Object localObject = new ShapeDrawable(new OvalShape());
    ((ShapeDrawable)localObject).getPaint().setColor(paramInt);
    if ((this.mShadow) && (!hasLollipopApi()))
    {
      Resources localResources = getResources();
      if (this.mType == 0)
        paramInt = R.drawable.fab_shadow;
      else
        paramInt = R.drawable.fab_shadow_mini;
      localObject = new LayerDrawable(new Drawable[] { localResources.getDrawable(paramInt), localObject });
      ((LayerDrawable)localObject).setLayerInset(1, this.mShadowSize, this.mShadowSize, this.mShadowSize, this.mShadowSize);
      return localObject;
    }
    return localObject;
  }

  private static int darkenColor(int paramInt)
  {
    float[] arrayOfFloat = new float[3];
    Color.colorToHSV(paramInt, arrayOfFloat);
    arrayOfFloat[2] *= 0.9F;
    return Color.HSVToColor(arrayOfFloat);
  }

  private int getColor(int paramInt)
  {
    return getResources().getColor(paramInt);
  }

  private int getDimension(int paramInt)
  {
    return getResources().getDimensionPixelSize(paramInt);
  }

  private int getMarginBottom()
  {
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    if ((localLayoutParams instanceof ViewGroup.MarginLayoutParams))
      return ((ViewGroup.MarginLayoutParams)localLayoutParams).bottomMargin;
    return 0;
  }

  private TypedArray getTypedArray(Context paramContext, AttributeSet paramAttributeSet, int[] paramArrayOfInt)
  {
    return paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt, 0, 0);
  }

  private boolean hasHoneycombApi()
  {
    return Build.VERSION.SDK_INT >= 11;
  }

  private boolean hasJellyBeanApi()
  {
    return Build.VERSION.SDK_INT >= 16;
  }

  private boolean hasLollipopApi()
  {
    return Build.VERSION.SDK_INT >= 21;
  }

  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    this.mVisible = true;
    this.mColorNormal = getColor(R.color.material_blue_500);
    this.mColorPressed = darkenColor(this.mColorNormal);
    this.mColorRipple = lightenColor(this.mColorNormal);
    this.mColorDisabled = getColor(17170432);
    this.mType = 0;
    this.mShadow = true;
    this.mScrollThreshold = getResources().getDimensionPixelOffset(R.dimen.fab_scroll_threshold);
    this.mShadowSize = getDimension(R.dimen.fab_shadow_size);
    if (paramAttributeSet != null)
      initAttributes(paramContext, paramAttributeSet);
    updateBackground();
  }

  private void initAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = getTypedArray(paramContext, paramAttributeSet, R.styleable.FloatingActionButton);
    if (paramContext != null)
      try
      {
        this.mColorNormal = paramContext.getColor(R.styleable.FloatingActionButton_fab_colorNormal, getColor(R.color.material_blue_500));
        this.mColorPressed = paramContext.getColor(R.styleable.FloatingActionButton_fab_colorPressed, darkenColor(this.mColorNormal));
        this.mColorRipple = paramContext.getColor(R.styleable.FloatingActionButton_fab_colorRipple, lightenColor(this.mColorNormal));
        this.mColorDisabled = paramContext.getColor(R.styleable.FloatingActionButton_fab_colorDisabled, this.mColorDisabled);
        this.mShadow = paramContext.getBoolean(R.styleable.FloatingActionButton_fab_shadow, true);
        this.mType = paramContext.getInt(R.styleable.FloatingActionButton_fab_type, 0);
        return;
      }
      finally
      {
        paramContext.recycle();
      }
  }

  private static int lightenColor(int paramInt)
  {
    float[] arrayOfFloat = new float[3];
    Color.colorToHSV(paramInt, arrayOfFloat);
    arrayOfFloat[2] *= 1.1F;
    return Color.HSVToColor(arrayOfFloat);
  }

  @SuppressLint({"NewApi"})
  private void setBackgroundCompat(Drawable paramDrawable)
  {
    if (hasLollipopApi())
    {
      boolean bool = this.mShadow;
      float f = 0.0F;
      if (bool)
      {
        if (getElevation() > 0.0F);
        for (f = getElevation(); ; f = getDimension(R.dimen.fab_elevation_lollipop))
          break;
      }
      setElevation(f);
      int i = this.mColorRipple;
      paramDrawable = new RippleDrawable(new ColorStateList(new int[][] { new int[0] }, new int[] { i }), paramDrawable, null);
      setOutlineProvider(new ViewOutlineProvider()
      {
        public void getOutline(View paramAnonymousView, Outline paramAnonymousOutline)
        {
          paramAnonymousView = FloatingActionButton.this;
          if (FloatingActionButton.this.mType == 0)
            i = R.dimen.fab_size_normal;
          else
            i = R.dimen.fab_size_mini;
          int i = paramAnonymousView.getDimension(i);
          paramAnonymousOutline.setOval(0, 0, i, i);
        }
      });
      setClipToOutline(true);
      setBackground(paramDrawable);
      return;
    }
    if (hasJellyBeanApi())
    {
      setBackground(paramDrawable);
      return;
    }
    setBackgroundDrawable(paramDrawable);
  }

  private void setMarginsWithoutShadow()
  {
    if ((!this.mMarginsSet) && ((getLayoutParams() instanceof ViewGroup.MarginLayoutParams)))
    {
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)getLayoutParams();
      localMarginLayoutParams.setMargins(localMarginLayoutParams.leftMargin - this.mShadowSize, localMarginLayoutParams.topMargin - this.mShadowSize, localMarginLayoutParams.rightMargin - this.mShadowSize, localMarginLayoutParams.bottomMargin - this.mShadowSize);
      requestLayout();
      this.mMarginsSet = true;
    }
  }

  private void toggle(final boolean paramBoolean1, final boolean paramBoolean2, boolean paramBoolean3)
  {
    if ((this.mVisible != paramBoolean1) || (paramBoolean3))
    {
      this.mVisible = paramBoolean1;
      int i = getHeight();
      if ((i == 0) && (!paramBoolean3))
      {
        ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
        if (localViewTreeObserver.isAlive())
        {
          localViewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
          {
            public boolean onPreDraw()
            {
              ViewTreeObserver localViewTreeObserver = FloatingActionButton.this.getViewTreeObserver();
              if (localViewTreeObserver.isAlive())
                localViewTreeObserver.removeOnPreDrawListener(this);
              FloatingActionButton.this.toggle(paramBoolean1, paramBoolean2, true);
              return true;
            }
          });
          return;
        }
      }
      if (paramBoolean1)
        i = 0;
      else
        i = getMarginBottom() + i;
      if (paramBoolean2)
        ViewPropertyAnimator.animate(this).setInterpolator(this.mInterpolator).setDuration(200L).translationY(i);
      else
        ViewHelper.setTranslationY(this, i);
      if (!hasHoneycombApi())
        setClickable(paramBoolean1);
    }
  }

  private void updateBackground()
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    Drawable localDrawable = createDrawable(this.mColorPressed);
    localStateListDrawable.addState(new int[] { 16842919 }, localDrawable);
    localDrawable = createDrawable(this.mColorDisabled);
    localStateListDrawable.addState(new int[] { -16842910 }, localDrawable);
    localDrawable = createDrawable(this.mColorNormal);
    localStateListDrawable.addState(new int[0], localDrawable);
    setBackgroundCompat(localStateListDrawable);
  }

  public void attachToListView(@NonNull AbsListView paramAbsListView)
  {
    attachToListView(paramAbsListView, null, null);
  }

  public void attachToListView(@NonNull AbsListView paramAbsListView, ScrollDirectionListener paramScrollDirectionListener)
  {
    attachToListView(paramAbsListView, paramScrollDirectionListener, null);
  }

  public void attachToListView(@NonNull AbsListView paramAbsListView, ScrollDirectionListener paramScrollDirectionListener, AbsListView.OnScrollListener paramOnScrollListener)
  {
    AbsListViewScrollDetectorImpl localAbsListViewScrollDetectorImpl = new AbsListViewScrollDetectorImpl(null);
    localAbsListViewScrollDetectorImpl.setScrollDirectionListener(paramScrollDirectionListener);
    localAbsListViewScrollDetectorImpl.setOnScrollListener(paramOnScrollListener);
    localAbsListViewScrollDetectorImpl.setListView(paramAbsListView);
    localAbsListViewScrollDetectorImpl.setScrollThreshold(this.mScrollThreshold);
    paramAbsListView.setOnScrollListener(localAbsListViewScrollDetectorImpl);
  }

  public void attachToRecyclerView(@NonNull RecyclerView paramRecyclerView)
  {
    attachToRecyclerView(paramRecyclerView, null, null);
  }

  public void attachToRecyclerView(@NonNull RecyclerView paramRecyclerView, ScrollDirectionListener paramScrollDirectionListener)
  {
    attachToRecyclerView(paramRecyclerView, paramScrollDirectionListener, null);
  }

  public void attachToRecyclerView(@NonNull RecyclerView paramRecyclerView, ScrollDirectionListener paramScrollDirectionListener, RecyclerView.OnScrollListener paramOnScrollListener)
  {
    RecyclerViewScrollDetectorImpl localRecyclerViewScrollDetectorImpl = new RecyclerViewScrollDetectorImpl(null);
    localRecyclerViewScrollDetectorImpl.setScrollDirectionListener(paramScrollDirectionListener);
    localRecyclerViewScrollDetectorImpl.setOnScrollListener(paramOnScrollListener);
    localRecyclerViewScrollDetectorImpl.setScrollThreshold(this.mScrollThreshold);
    paramRecyclerView.setOnScrollListener(localRecyclerViewScrollDetectorImpl);
  }

  public void attachToScrollView(@NonNull ObservableScrollView paramObservableScrollView)
  {
    attachToScrollView(paramObservableScrollView, null, null);
  }

  public void attachToScrollView(@NonNull ObservableScrollView paramObservableScrollView, ScrollDirectionListener paramScrollDirectionListener)
  {
    attachToScrollView(paramObservableScrollView, paramScrollDirectionListener, null);
  }

  public void attachToScrollView(@NonNull ObservableScrollView paramObservableScrollView, ScrollDirectionListener paramScrollDirectionListener, ObservableScrollView.OnScrollChangedListener paramOnScrollChangedListener)
  {
    ScrollViewScrollDetectorImpl localScrollViewScrollDetectorImpl = new ScrollViewScrollDetectorImpl(null);
    localScrollViewScrollDetectorImpl.setScrollDirectionListener(paramScrollDirectionListener);
    localScrollViewScrollDetectorImpl.setOnScrollChangedListener(paramOnScrollChangedListener);
    localScrollViewScrollDetectorImpl.setScrollThreshold(this.mScrollThreshold);
    paramObservableScrollView.setOnScrollChangedListener(localScrollViewScrollDetectorImpl);
  }

  public int getColorNormal()
  {
    return this.mColorNormal;
  }

  public int getColorPressed()
  {
    return this.mColorPressed;
  }

  public int getColorRipple()
  {
    return this.mColorRipple;
  }

  public int getType()
  {
    return this.mType;
  }

  public boolean hasShadow()
  {
    return this.mShadow;
  }

  public void hide()
  {
    hide(true);
  }

  public void hide(boolean paramBoolean)
  {
    toggle(false, paramBoolean, false);
  }

  public boolean isVisible()
  {
    return this.mVisible;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mType == 0)
      paramInt1 = R.dimen.fab_size_normal;
    else
      paramInt1 = R.dimen.fab_size_mini;
    paramInt2 = getDimension(paramInt1);
    paramInt1 = paramInt2;
    if (this.mShadow)
    {
      paramInt1 = paramInt2;
      if (!hasLollipopApi())
      {
        paramInt1 = paramInt2 + this.mShadowSize * 2;
        setMarginsWithoutShadow();
      }
    }
    setMeasuredDimension(paramInt1, paramInt1);
  }

  public void setColorNormal(int paramInt)
  {
    if (paramInt != this.mColorNormal)
    {
      this.mColorNormal = paramInt;
      updateBackground();
    }
  }

  public void setColorNormalResId(int paramInt)
  {
    setColorNormal(getColor(paramInt));
  }

  public void setColorPressed(int paramInt)
  {
    if (paramInt != this.mColorPressed)
    {
      this.mColorPressed = paramInt;
      updateBackground();
    }
  }

  public void setColorPressedResId(int paramInt)
  {
    setColorPressed(getColor(paramInt));
  }

  public void setColorRipple(int paramInt)
  {
    if (paramInt != this.mColorRipple)
    {
      this.mColorRipple = paramInt;
      updateBackground();
    }
  }

  public void setColorRippleResId(int paramInt)
  {
    setColorRipple(getColor(paramInt));
  }

  public void setShadow(boolean paramBoolean)
  {
    if (paramBoolean != this.mShadow)
    {
      this.mShadow = paramBoolean;
      updateBackground();
    }
  }

  public void setType(int paramInt)
  {
    if (paramInt != this.mType)
    {
      this.mType = paramInt;
      updateBackground();
    }
  }

  public void show()
  {
    show(true);
  }

  public void show(boolean paramBoolean)
  {
    toggle(true, paramBoolean, false);
  }

  private class AbsListViewScrollDetectorImpl extends AbsListViewScrollDetector
  {
    private AbsListView.OnScrollListener mOnScrollListener;
    private ScrollDirectionListener mScrollDirectionListener;

    private AbsListViewScrollDetectorImpl()
    {
    }

    private void setScrollDirectionListener(ScrollDirectionListener paramScrollDirectionListener)
    {
      this.mScrollDirectionListener = paramScrollDirectionListener;
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
      if (this.mOnScrollListener != null)
        this.mOnScrollListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
      super.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
    }

    public void onScrollDown()
    {
      FloatingActionButton.this.show();
      if (this.mScrollDirectionListener != null)
        this.mScrollDirectionListener.onScrollDown();
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
      if (this.mOnScrollListener != null)
        this.mOnScrollListener.onScrollStateChanged(paramAbsListView, paramInt);
      super.onScrollStateChanged(paramAbsListView, paramInt);
    }

    public void onScrollUp()
    {
      FloatingActionButton.this.hide();
      if (this.mScrollDirectionListener != null)
        this.mScrollDirectionListener.onScrollUp();
    }

    public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
    {
      this.mOnScrollListener = paramOnScrollListener;
    }
  }

  private class RecyclerViewScrollDetectorImpl extends RecyclerViewScrollDetector
  {
    private RecyclerView.OnScrollListener mOnScrollListener;
    private ScrollDirectionListener mScrollDirectionListener;

    private RecyclerViewScrollDetectorImpl()
    {
    }

    private void setScrollDirectionListener(ScrollDirectionListener paramScrollDirectionListener)
    {
      this.mScrollDirectionListener = paramScrollDirectionListener;
    }

    public void onScrollDown()
    {
      FloatingActionButton.this.show();
      if (this.mScrollDirectionListener != null)
        this.mScrollDirectionListener.onScrollDown();
    }

    public void onScrollStateChanged(RecyclerView paramRecyclerView, int paramInt)
    {
      if (this.mOnScrollListener != null)
        this.mOnScrollListener.onScrollStateChanged(paramRecyclerView, paramInt);
      super.onScrollStateChanged(paramRecyclerView, paramInt);
    }

    public void onScrollUp()
    {
      FloatingActionButton.this.hide();
      if (this.mScrollDirectionListener != null)
        this.mScrollDirectionListener.onScrollUp();
    }

    public void onScrolled(RecyclerView paramRecyclerView, int paramInt1, int paramInt2)
    {
      if (this.mOnScrollListener != null)
        this.mOnScrollListener.onScrolled(paramRecyclerView, paramInt1, paramInt2);
      super.onScrolled(paramRecyclerView, paramInt1, paramInt2);
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener paramOnScrollListener)
    {
      this.mOnScrollListener = paramOnScrollListener;
    }
  }

  private class ScrollViewScrollDetectorImpl extends ScrollViewScrollDetector
  {
    private ObservableScrollView.OnScrollChangedListener mOnScrollChangedListener;
    private ScrollDirectionListener mScrollDirectionListener;

    private ScrollViewScrollDetectorImpl()
    {
    }

    private void setScrollDirectionListener(ScrollDirectionListener paramScrollDirectionListener)
    {
      this.mScrollDirectionListener = paramScrollDirectionListener;
    }

    public void onScrollChanged(ScrollView paramScrollView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (this.mOnScrollChangedListener != null)
        this.mOnScrollChangedListener.onScrollChanged(paramScrollView, paramInt1, paramInt2, paramInt3, paramInt4);
      super.onScrollChanged(paramScrollView, paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public void onScrollDown()
    {
      FloatingActionButton.this.show();
      if (this.mScrollDirectionListener != null)
        this.mScrollDirectionListener.onScrollDown();
    }

    public void onScrollUp()
    {
      FloatingActionButton.this.hide();
      if (this.mScrollDirectionListener != null)
        this.mScrollDirectionListener.onScrollUp();
    }

    public void setOnScrollChangedListener(ObservableScrollView.OnScrollChangedListener paramOnScrollChangedListener)
    {
      this.mOnScrollChangedListener = paramOnScrollChangedListener;
    }
  }

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({0L, 1L})
  public static @interface TYPE
  {
  }
}