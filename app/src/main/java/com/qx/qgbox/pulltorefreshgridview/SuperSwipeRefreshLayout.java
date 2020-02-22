package com.qx.qgbox.pulltorefreshgridview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;

@SuppressLint({"ClickableViewAccessibility"})
public class SuperSwipeRefreshLayout extends ViewGroup
{
  private static final int ANIMATE_TO_START_DURATION = 200;
  private static final int ANIMATE_TO_TRIGGER_DURATION = 200;
  private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0F;
  private static final int DEFAULT_CIRCLE_TARGET = 64;
  private static final float DRAG_RATE = 0.5F;
  private static final int HEADER_VIEW_HEIGHT = 50;
  private static final int INVALID_POINTER = -1;
  private static final int[] LAYOUT_ATTRS = { 16842766 };
  private static final String LOG_TAG = "CustomeSwipeRefreshLayout";
  private static final int SCALE_DOWN_DURATION = 150;
  private CircleProgressView defaultProgressView = null;
  private float density = 1.0F;
  private boolean isProgressEnable = true;
  private int mActivePointerId = -1;
  private final Animation mAnimateToCorrectPosition = new Animation()
  {
    public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
    {
      if (!SuperSwipeRefreshLayout.this.mUsingCustomStart)
        i = (int)(SuperSwipeRefreshLayout.this.mSpinnerFinalOffset - Math.abs(SuperSwipeRefreshLayout.this.mOriginalOffsetTop));
      else
        i = (int)SuperSwipeRefreshLayout.this.mSpinnerFinalOffset;
      int j = SuperSwipeRefreshLayout.this.mFrom;
      int i = (int)((i - SuperSwipeRefreshLayout.this.mFrom) * paramAnonymousFloat);
      int k = SuperSwipeRefreshLayout.this.mHeadViewContainer.getTop();
      SuperSwipeRefreshLayout.this.setTargetOffsetTopAndBottom(j + i - k, false);
    }

    public void setAnimationListener(Animation.AnimationListener paramAnonymousAnimationListener)
    {
      super.setAnimationListener(paramAnonymousAnimationListener);
    }
  };
  private final Animation mAnimateToStartPosition = new Animation()
  {
    public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
    {
      SuperSwipeRefreshLayout.this.moveToStart(paramAnonymousFloat);
    }
  };
  private int mCurrentTargetOffsetTop;
  private final DecelerateInterpolator mDecelerateInterpolator;
  private RelativeLayout mFooterViewContainer;
  private int mFooterViewHeight;
  private int mFooterViewIndex = -1;
  private int mFooterViewWidth;
  protected int mFrom;
  private HeadViewContainer mHeadViewContainer;
  private int mHeaderViewHeight;
  private int mHeaderViewIndex = -1;
  private int mHeaderViewWidth;
  private float mInitialMotionY;
  private boolean mIsBeingDragged;
  private OnPullRefreshListener mListener;
  private boolean mLoadMore = false;
  private int mMediumAnimationDuration;
  private boolean mNotify;
  private OnPushLoadMoreListener mOnPushLoadMoreListener;
  private boolean mOriginalOffsetCalculated = false;
  protected int mOriginalOffsetTop;
  private Animation.AnimationListener mRefreshListener = new Animation.AnimationListener()
  {
    public void onAnimationEnd(Animation paramAnonymousAnimation)
    {
      SuperSwipeRefreshLayout.access$502(SuperSwipeRefreshLayout.this, true);
      if (SuperSwipeRefreshLayout.this.mRefreshing)
      {
        if (SuperSwipeRefreshLayout.this.mNotify)
        {
          if (SuperSwipeRefreshLayout.this.usingDefaultHeader)
          {
            ViewCompat.setAlpha(SuperSwipeRefreshLayout.this.defaultProgressView, 1.0F);
            SuperSwipeRefreshLayout.this.defaultProgressView.setOnDraw(true);
            new Thread(SuperSwipeRefreshLayout.this.defaultProgressView).start();
          }
          if (SuperSwipeRefreshLayout.this.mListener != null)
            SuperSwipeRefreshLayout.this.mListener.onRefresh();
        }
      }
      else
      {
        SuperSwipeRefreshLayout.this.mHeadViewContainer.setVisibility(8);
        if (SuperSwipeRefreshLayout.this.mScale)
          SuperSwipeRefreshLayout.this.setAnimationProgress(0.0F);
        else
          SuperSwipeRefreshLayout.this.setTargetOffsetTopAndBottom(SuperSwipeRefreshLayout.this.mOriginalOffsetTop - SuperSwipeRefreshLayout.this.mCurrentTargetOffsetTop, true);
      }
      SuperSwipeRefreshLayout.access$1302(SuperSwipeRefreshLayout.this, SuperSwipeRefreshLayout.this.mHeadViewContainer.getTop());
      SuperSwipeRefreshLayout.this.updateListenerCallBack();
    }

    public void onAnimationRepeat(Animation paramAnonymousAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnonymousAnimation)
    {
      SuperSwipeRefreshLayout.access$502(SuperSwipeRefreshLayout.this, false);
    }
  };
  private boolean mRefreshing = false;
  private boolean mReturningToStart;
  private boolean mScale;
  private Animation mScaleAnimation;
  private Animation mScaleDownAnimation;
  private Animation mScaleDownToStartAnimation;
  private float mSpinnerFinalOffset;
  private float mStartingScale;
  private View mTarget;
  private float mTotalDragDistance = -1.0F;
  private int mTouchSlop;
  private boolean mUsingCustomStart;
  private int pushDistance = 0;
  private boolean targetScrollWithLayout = true;
  private boolean usingDefaultHeader = true;

  public SuperSwipeRefreshLayout(Context paramContext)
  {
    this(paramContext, null);
  }

  public SuperSwipeRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    this.mMediumAnimationDuration = getResources().getInteger(17694721);
    setWillNotDraw(false);
    this.mDecelerateInterpolator = new DecelerateInterpolator(2.0F);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, LAYOUT_ATTRS);
    setEnabled(paramAttributeSet.getBoolean(0, true));
    paramAttributeSet.recycle();
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    paramAttributeSet = getResources().getDisplayMetrics();
    this.mHeaderViewWidth = paramContext.getWidth();
    this.mFooterViewWidth = paramContext.getWidth();
    this.mHeaderViewHeight = ((int)(paramAttributeSet.density * 50.0F));
    this.mFooterViewHeight = ((int)(paramAttributeSet.density * 50.0F));
    this.defaultProgressView = new CircleProgressView(getContext());
    createHeaderViewContainer();
    createFooterViewContainer();
    ViewCompat.setChildrenDrawingOrderEnabled(this, true);
    this.mSpinnerFinalOffset = (paramAttributeSet.density * 64.0F);
    this.density = paramAttributeSet.density;
    this.mTotalDragDistance = this.mSpinnerFinalOffset;
  }

  private void animateOffsetToCorrectPosition(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    this.mFrom = paramInt;
    this.mAnimateToCorrectPosition.reset();
    this.mAnimateToCorrectPosition.setDuration(200L);
    this.mAnimateToCorrectPosition.setInterpolator(this.mDecelerateInterpolator);
    if (paramAnimationListener != null)
      this.mHeadViewContainer.setAnimationListener(paramAnimationListener);
    this.mHeadViewContainer.clearAnimation();
    this.mHeadViewContainer.startAnimation(this.mAnimateToCorrectPosition);
  }

  private void animateOffsetToStartPosition(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    if (this.mScale)
    {
      startScaleDownReturnToStartAnimation(paramInt, paramAnimationListener);
    }
    else
    {
      this.mFrom = paramInt;
      this.mAnimateToStartPosition.reset();
      this.mAnimateToStartPosition.setDuration(200L);
      this.mAnimateToStartPosition.setInterpolator(this.mDecelerateInterpolator);
      if (paramAnimationListener != null)
        this.mHeadViewContainer.setAnimationListener(paramAnimationListener);
      this.mHeadViewContainer.clearAnimation();
      this.mHeadViewContainer.startAnimation(this.mAnimateToStartPosition);
    }
    resetTargetLayoutDelay(200);
  }

  @TargetApi(11)
  private void animatorFooterToBottom(int paramInt1, final int paramInt2)
  {
    ValueAnimator localValueAnimator = ValueAnimator.ofInt(new int[] { paramInt1, paramInt2 });
    localValueAnimator.setDuration(150L);
    localValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        SuperSwipeRefreshLayout.access$1602(SuperSwipeRefreshLayout.this, ((Integer)paramAnonymousValueAnimator.getAnimatedValue()).intValue());
        SuperSwipeRefreshLayout.this.updateFooterViewPosition();
      }
    });
    localValueAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        if ((paramInt2 > 0) && (SuperSwipeRefreshLayout.this.mOnPushLoadMoreListener != null))
        {
          SuperSwipeRefreshLayout.access$1902(SuperSwipeRefreshLayout.this, true);
          SuperSwipeRefreshLayout.this.mOnPushLoadMoreListener.onLoadMore();
          return;
        }
        SuperSwipeRefreshLayout.this.resetTargetLayout();
        SuperSwipeRefreshLayout.access$1902(SuperSwipeRefreshLayout.this, false);
      }
    });
    localValueAnimator.setInterpolator(this.mDecelerateInterpolator);
    localValueAnimator.start();
  }

  private void createFooterViewContainer()
  {
    this.mFooterViewContainer = new RelativeLayout(getContext());
    this.mFooterViewContainer.setVisibility(8);
    addView(this.mFooterViewContainer);
  }

  private void createHeaderViewContainer()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams((int)(this.mHeaderViewHeight * 0.8D), (int)(this.mHeaderViewHeight * 0.8D));
    localLayoutParams.addRule(14);
    localLayoutParams.addRule(12);
    this.mHeadViewContainer = new HeadViewContainer(getContext());
    this.mHeadViewContainer.setVisibility(8);
    this.defaultProgressView.setVisibility(0);
    this.defaultProgressView.setOnDraw(false);
    this.mHeadViewContainer.addView(this.defaultProgressView, localLayoutParams);
    addView(this.mHeadViewContainer);
  }

  private void ensureTarget()
  {
    if (this.mTarget == null)
    {
      int i = 0;
      while (i < getChildCount())
      {
        View localView = getChildAt(i);
        if ((!localView.equals(this.mHeadViewContainer)) && (!localView.equals(this.mFooterViewContainer)))
        {
          this.mTarget = localView;
          return;
        }
        i += 1;
      }
    }
  }

  private float getMotionEventY(MotionEvent paramMotionEvent, int paramInt)
  {
    paramInt = MotionEventCompat.findPointerIndex(paramMotionEvent, paramInt);
    if (paramInt < 0)
      return -1.0F;
    return MotionEventCompat.getY(paramMotionEvent, paramInt);
  }

  private void moveToStart(float paramFloat)
  {
    setTargetOffsetTopAndBottom(this.mFrom + (int)((this.mOriginalOffsetTop - this.mFrom) * paramFloat) - this.mHeadViewContainer.getTop(), false);
  }

  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == this.mActivePointerId)
    {
      if (i == 0)
        i = 1;
      else
        i = 0;
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
    }
  }

  private void setAnimationProgress(float paramFloat)
  {
    if (!this.usingDefaultHeader)
      paramFloat = 1.0F;
    ViewCompat.setScaleX(this.mHeadViewContainer, paramFloat);
    ViewCompat.setScaleY(this.mHeadViewContainer, paramFloat);
  }

  private void setRefreshing(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mRefreshing != paramBoolean1)
    {
      this.mNotify = paramBoolean2;
      ensureTarget();
      this.mRefreshing = paramBoolean1;
      if (this.mRefreshing)
      {
        animateOffsetToCorrectPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
        return;
      }
      animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, this.mRefreshListener);
    }
  }

  private void setTargetOffsetTopAndBottom(int paramInt, boolean paramBoolean)
  {
    this.mHeadViewContainer.bringToFront();
    this.mHeadViewContainer.offsetTopAndBottom(paramInt);
    this.mCurrentTargetOffsetTop = this.mHeadViewContainer.getTop();
    if ((paramBoolean) && (Build.VERSION.SDK_INT < 11))
      invalidate();
    updateListenerCallBack();
  }

  private void startScaleDownAnimation(Animation.AnimationListener paramAnimationListener)
  {
    this.mScaleDownAnimation = new Animation()
    {
      public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        SuperSwipeRefreshLayout.this.setAnimationProgress(1.0F - paramAnonymousFloat);
      }
    };
    this.mScaleDownAnimation.setDuration(150L);
    this.mHeadViewContainer.setAnimationListener(paramAnimationListener);
    this.mHeadViewContainer.clearAnimation();
    this.mHeadViewContainer.startAnimation(this.mScaleDownAnimation);
  }

  private void startScaleDownReturnToStartAnimation(int paramInt, Animation.AnimationListener paramAnimationListener)
  {
    this.mFrom = paramInt;
    this.mStartingScale = ViewCompat.getScaleX(this.mHeadViewContainer);
    this.mScaleDownToStartAnimation = new Animation()
    {
      public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        float f1 = SuperSwipeRefreshLayout.this.mStartingScale;
        float f2 = -SuperSwipeRefreshLayout.this.mStartingScale;
        SuperSwipeRefreshLayout.this.setAnimationProgress(f1 + f2 * paramAnonymousFloat);
        SuperSwipeRefreshLayout.this.moveToStart(paramAnonymousFloat);
      }
    };
    this.mScaleDownToStartAnimation.setDuration(150L);
    if (paramAnimationListener != null)
      this.mHeadViewContainer.setAnimationListener(paramAnimationListener);
    this.mHeadViewContainer.clearAnimation();
    this.mHeadViewContainer.startAnimation(this.mScaleDownToStartAnimation);
  }

  private void startScaleUpAnimation(Animation.AnimationListener paramAnimationListener)
  {
    this.mHeadViewContainer.setVisibility(0);
    this.mScaleAnimation = new Animation()
    {
      public void applyTransformation(float paramAnonymousFloat, Transformation paramAnonymousTransformation)
      {
        SuperSwipeRefreshLayout.this.setAnimationProgress(paramAnonymousFloat);
      }
    };
    this.mScaleAnimation.setDuration(this.mMediumAnimationDuration);
    if (paramAnimationListener != null)
      this.mHeadViewContainer.setAnimationListener(paramAnimationListener);
    this.mHeadViewContainer.clearAnimation();
    this.mHeadViewContainer.startAnimation(this.mScaleAnimation);
  }

  private void updateFooterViewPosition()
  {
    this.mFooterViewContainer.setVisibility(0);
    this.mFooterViewContainer.bringToFront();
    if (Build.VERSION.SDK_INT < 19)
      this.mFooterViewContainer.getParent().requestLayout();
    this.mFooterViewContainer.offsetTopAndBottom(-this.pushDistance);
    updatePushDistanceListener();
  }

  private void updateListenerCallBack()
  {
    int i = this.mCurrentTargetOffsetTop + this.mHeadViewContainer.getHeight();
    if (this.mListener != null)
      this.mListener.onPullDistance(i);
    if ((this.usingDefaultHeader) && (this.isProgressEnable))
      this.defaultProgressView.setPullDistance(i);
  }

  private void updatePushDistanceListener()
  {
    if (this.mOnPushLoadMoreListener != null)
      this.mOnPushLoadMoreListener.onPushDistance(this.pushDistance);
  }

  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    if ((this.mHeaderViewIndex < 0) && (this.mFooterViewIndex < 0))
      return paramInt2;
    if (paramInt2 == paramInt1 - 2)
      return this.mHeaderViewIndex;
    if (paramInt2 == paramInt1 - 1)
      return this.mFooterViewIndex;
    if (this.mFooterViewIndex > this.mHeaderViewIndex)
      paramInt1 = this.mFooterViewIndex;
    else
      paramInt1 = this.mHeaderViewIndex;
    int i;
    if (this.mFooterViewIndex < this.mHeaderViewIndex)
      i = this.mFooterViewIndex;
    else
      i = this.mHeaderViewIndex;
    if ((paramInt2 >= i) && (paramInt2 < paramInt1 - 1))
      return paramInt2 + 1;
    if ((paramInt2 < paramInt1) && (paramInt2 != paramInt1 - 1))
      return paramInt2;
    return paramInt2 + 2;
  }

  protected boolean handlerPullTouchEvent(MotionEvent paramMotionEvent, int paramInt)
  {
    float f1;
    float f2;
    switch (paramInt)
    {
    case 4:
    default:
      return true;
    case 6:
      onSecondaryPointerUp(paramMotionEvent);
      return true;
    case 5:
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, MotionEventCompat.getActionIndex(paramMotionEvent));
      return true;
    case 2:
      paramInt = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
      if (paramInt < 0)
        return false;
      float f3 = (MotionEventCompat.getY(paramMotionEvent, paramInt) - this.mInitialMotionY) * 0.5F;
      if (this.mIsBeingDragged)
      {
        f1 = f3 / this.mTotalDragDistance;
        if (f1 < 0.0F)
          return false;
        f2 = Math.min(1.0F, Math.abs(f1));
        float f4 = Math.abs(f3);
        float f5 = this.mTotalDragDistance;
        if (this.mUsingCustomStart)
          f1 = this.mSpinnerFinalOffset - this.mOriginalOffsetTop;
        else
          f1 = this.mSpinnerFinalOffset;
        double d = Math.max(0.0F, Math.min(f4 - f5, f1 * 2.0F) / f1) / 4.0F;
        f4 = (float)(d - Math.pow(d, 2.0D));
        paramInt = this.mOriginalOffsetTop;
        int i = (int)(f1 * f2 + f4 * 2.0F * f1 * 2.0F);
        if (this.mHeadViewContainer.getVisibility() != 0)
          this.mHeadViewContainer.setVisibility(0);
        if (!this.mScale)
        {
          ViewCompat.setScaleX(this.mHeadViewContainer, 1.0F);
          ViewCompat.setScaleY(this.mHeadViewContainer, 1.0F);
        }
        if (this.usingDefaultHeader)
        {
          f2 = f3 / this.mTotalDragDistance;
          f1 = f2;
          if (f2 >= 1.0F)
            f1 = 1.0F;
          ViewCompat.setScaleX(this.defaultProgressView, f1);
          ViewCompat.setScaleY(this.defaultProgressView, f1);
          ViewCompat.setAlpha(this.defaultProgressView, f1);
        }
        if (f3 < this.mTotalDragDistance)
        {
          if (this.mScale)
            setAnimationProgress(f3 / this.mTotalDragDistance);
          if (this.mListener != null)
            this.mListener.onPullEnable(false);
        }
        else if (this.mListener != null)
        {
          this.mListener.onPullEnable(true);
        }
        setTargetOffsetTopAndBottom(paramInt + i - this.mCurrentTargetOffsetTop, true);
        return true;
      }
      break;
    case 1:
    case 3:
      if (this.mActivePointerId == -1)
        return false;
      f1 = MotionEventCompat.getY(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId));
      f2 = this.mInitialMotionY;
      this.mIsBeingDragged = false;
      if ((f1 - f2) * 0.5F > this.mTotalDragDistance)
      {
        setRefreshing(true, true);
      }
      else
      {
        this.mRefreshing = false;
        paramMotionEvent = null;
        if (!this.mScale)
          paramMotionEvent = new Animation.AnimationListener()
          {
            public void onAnimationEnd(Animation paramAnonymousAnimation)
            {
              if (!SuperSwipeRefreshLayout.this.mScale)
                SuperSwipeRefreshLayout.this.startScaleDownAnimation(null);
            }

            public void onAnimationRepeat(Animation paramAnonymousAnimation)
            {
            }

            public void onAnimationStart(Animation paramAnonymousAnimation)
            {
            }
          };
        animateOffsetToStartPosition(this.mCurrentTargetOffsetTop, paramMotionEvent);
      }
      this.mActivePointerId = -1;
      return false;
    case 0:
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      this.mIsBeingDragged = false;
    }
    return true;
  }

  protected boolean handlerPushTouchEvent(MotionEvent paramMotionEvent, int paramInt)
  {
    boolean bool = false;
    float f1;
    switch (paramInt)
    {
    case 4:
    default:
      return true;
    case 6:
      onSecondaryPointerUp(paramMotionEvent);
      return true;
    case 5:
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, MotionEventCompat.getActionIndex(paramMotionEvent));
      return true;
    case 2:
      paramInt = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
      if (paramInt < 0)
        return false;
      f1 = MotionEventCompat.getY(paramMotionEvent, paramInt);
      float f2 = this.mInitialMotionY;
      if (this.mIsBeingDragged)
      {
        this.pushDistance = ((int)((f2 - f1) * 0.5F));
        updateFooterViewPosition();
        if (this.mOnPushLoadMoreListener != null)
        {
          paramMotionEvent = this.mOnPushLoadMoreListener;
          if (this.pushDistance >= this.mFooterViewHeight)
            bool = true;
          paramMotionEvent.onPushEnable(bool);
          return true;
        }
      }
      break;
    case 1:
    case 3:
      if (this.mActivePointerId == -1)
        return false;
      f1 = MotionEventCompat.getY(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId));
      f1 = (this.mInitialMotionY - f1) * 0.5F;
      this.mIsBeingDragged = false;
      this.mActivePointerId = -1;
      if ((f1 >= this.mFooterViewHeight) && (this.mOnPushLoadMoreListener != null))
        this.pushDistance = this.mFooterViewHeight;
      else
        this.pushDistance = 0;
      if (Build.VERSION.SDK_INT < 11)
      {
        updateFooterViewPosition();
        if ((this.pushDistance == this.mFooterViewHeight) && (this.mOnPushLoadMoreListener != null))
        {
          this.mLoadMore = true;
          this.mOnPushLoadMoreListener.onLoadMore();
          return false;
        }
      }
      else
      {
        animatorFooterToBottom((int)f1, this.pushDistance);
      }
      return false;
    case 0:
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      this.mIsBeingDragged = false;
    }
    return true;
  }

  public boolean isChildScrollToBottom()
  {
    if (isChildScrollToTop())
      return false;
    Object localObject1;
    Object localObject2;
    int i;
    if ((this.mTarget instanceof RecyclerView))
    {
      localObject1 = (RecyclerView)this.mTarget;
      localObject2 = ((RecyclerView)localObject1).getLayoutManager();
      i = ((RecyclerView)localObject1).getAdapter().getItemCount();
      if (((localObject2 instanceof LinearLayoutManager)) && (i > 0))
      {
        if (((LinearLayoutManager)localObject2).findLastCompletelyVisibleItemPosition() == i - 1)
          return true;
      }
      else if ((localObject2 instanceof StaggeredGridLayoutManager))
      {
        localObject1 = (StaggeredGridLayoutManager)localObject2;
        localObject2 = new int[2];
        ((StaggeredGridLayoutManager)localObject1).findLastCompletelyVisibleItemPositions((int[])localObject2);
        if (Math.max(localObject2[0], localObject2[1]) == i - 1)
          return true;
      }
      return false;
    }
    if ((this.mTarget instanceof AbsListView))
    {
      localObject1 = (AbsListView)this.mTarget;
      i = ((ListAdapter)((AbsListView)localObject1).getAdapter()).getCount();
      if ((((AbsListView)localObject1).getFirstVisiblePosition() == 0) && (((AbsListView)localObject1).getChildAt(0).getTop() >= ((AbsListView)localObject1).getPaddingTop()))
        return false;
      int j = ((AbsListView)localObject1).getLastVisiblePosition();
      return (j > 0) && (i > 0) && (j == i - 1);
    }
    if ((this.mTarget instanceof ScrollView))
    {
      localObject1 = (ScrollView)this.mTarget;
      localObject2 = ((ScrollView)localObject1).getChildAt(((ScrollView)localObject1).getChildCount() - 1);
      if ((localObject2 != null) && (((View)localObject2).getBottom() - (((ScrollView)localObject1).getHeight() + ((ScrollView)localObject1).getScrollY()) == 0))
        return true;
    }
    else if ((this.mTarget instanceof NestedScrollView))
    {
      localObject1 = (NestedScrollView)this.mTarget;
      localObject2 = ((NestedScrollView)localObject1).getChildAt(((NestedScrollView)localObject1).getChildCount() - 1);
      if ((localObject2 != null) && (((View)localObject2).getBottom() - (((NestedScrollView)localObject1).getHeight() + ((NestedScrollView)localObject1).getScrollY()) == 0))
        return true;
    }
    return false;
  }

  public boolean isChildScrollToTop()
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool = true;
    if (i < 14)
    {
      if ((this.mTarget instanceof AbsListView))
      {
        AbsListView localAbsListView = (AbsListView)this.mTarget;
        if (localAbsListView.getChildCount() > 0)
        {
          if ((localAbsListView.getFirstVisiblePosition() <= 0) && (localAbsListView.getChildAt(0).getTop() >= localAbsListView.getPaddingTop()))
            return true;
          bool = false;
        }
        return bool;
      }
      return this.mTarget.getScrollY() <= 0;
    }
    return ViewCompat.canScrollVertically(this.mTarget, -1) ^ true;
  }

  public boolean isRefreshing()
  {
    return this.mRefreshing;
  }

  public boolean isTargetScrollWithLayout()
  {
    return this.targetScrollWithLayout;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    ensureTarget();
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((this.mReturningToStart) && (i == 0))
      this.mReturningToStart = false;
    if ((isEnabled()) && (!this.mReturningToStart) && (!this.mRefreshing) && (!this.mLoadMore))
    {
      if ((!isChildScrollToTop()) && (!isChildScrollToBottom()))
        return false;
      if (i != 6)
      {
        float f;
        switch (i)
        {
        default:
          break;
        case 1:
        case 3:
          this.mIsBeingDragged = false;
          this.mActivePointerId = -1;
          break;
        case 0:
          setTargetOffsetTopAndBottom(this.mOriginalOffsetTop - this.mHeadViewContainer.getTop(), true);
          this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
          this.mIsBeingDragged = false;
          f = getMotionEventY(paramMotionEvent, this.mActivePointerId);
          if (f == -1.0F)
            return false;
          this.mInitialMotionY = f;
        case 2:
          if (this.mActivePointerId == -1)
            return false;
          f = getMotionEventY(paramMotionEvent, this.mActivePointerId);
          if (f == -1.0F)
            return false;
          if (isChildScrollToBottom())
          {
            if (this.mInitialMotionY - f > this.mTouchSlop)
              boolean bool = this.mIsBeingDragged;
          }
          else
          {
            f -= this.mInitialMotionY;
            if ((f > this.mTouchSlop) && (f < 640.0F) && (!this.mIsBeingDragged))
              this.mIsBeingDragged = true;
          }
          break;
        }
      }
      else
      {
        onSecondaryPointerUp(paramMotionEvent);
      }
      return this.mIsBeingDragged;
    }
    return false;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt3 = getMeasuredWidth();
    paramInt2 = getMeasuredHeight();
    if (getChildCount() == 0)
      return;
    if (this.mTarget == null)
      ensureTarget();
    if (this.mTarget == null)
      return;
    paramInt1 = this.mCurrentTargetOffsetTop + this.mHeadViewContainer.getMeasuredHeight();
    if (!this.targetScrollWithLayout)
      paramInt1 = 0;
    Object localObject = this.mTarget;
    paramInt4 = getPaddingLeft();
    paramInt1 = getPaddingTop() + paramInt1 - this.pushDistance;
    ((View)localObject).layout(paramInt4, paramInt1, paramInt3 - getPaddingLeft() - getPaddingRight() + paramInt4, paramInt2 - getPaddingTop() - getPaddingBottom() + paramInt1);
    int i = this.mHeadViewContainer.getMeasuredWidth();
    paramInt4 = this.mHeadViewContainer.getMeasuredHeight();
    localObject = this.mHeadViewContainer;
    paramInt1 = paramInt3 / 2;
    paramInt3 = i / 2;
    ((HeadViewContainer)localObject).layout(paramInt1 - paramInt3, this.mCurrentTargetOffsetTop, paramInt3 + paramInt1, this.mCurrentTargetOffsetTop + paramInt4);
    paramInt4 = this.mFooterViewContainer.getMeasuredWidth();
    paramInt3 = this.mFooterViewContainer.getMeasuredHeight();
    localObject = this.mFooterViewContainer;
    paramInt4 /= 2;
    ((RelativeLayout)localObject).layout(paramInt1 - paramInt4, paramInt2 - this.pushDistance, paramInt1 + paramInt4, paramInt2 + paramInt3 - this.pushDistance);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mTarget == null)
      ensureTarget();
    if (this.mTarget == null)
      return;
    this.mTarget.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), 1073741824));
    this.mHeadViewContainer.measure(View.MeasureSpec.makeMeasureSpec(this.mHeaderViewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mHeaderViewHeight * 3, 1073741824));
    this.mFooterViewContainer.measure(View.MeasureSpec.makeMeasureSpec(this.mFooterViewWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mFooterViewHeight, 1073741824));
    if ((!this.mUsingCustomStart) && (!this.mOriginalOffsetCalculated))
    {
      this.mOriginalOffsetCalculated = true;
      paramInt1 = -this.mHeadViewContainer.getMeasuredHeight();
      this.mOriginalOffsetTop = paramInt1;
      this.mCurrentTargetOffsetTop = paramInt1;
      updateListenerCallBack();
    }
    this.mHeaderViewIndex = -1;
    paramInt2 = 0;
    paramInt1 = 0;
    while (paramInt1 < getChildCount())
    {
      if (getChildAt(paramInt1) == this.mHeadViewContainer)
      {
        this.mHeaderViewIndex = paramInt1;
        break;
      }
      paramInt1 += 1;
    }
    this.mFooterViewIndex = -1;
    paramInt1 = paramInt2;
    while (paramInt1 < getChildCount())
    {
      if (getChildAt(paramInt1) == this.mFooterViewContainer)
      {
        this.mFooterViewIndex = paramInt1;
        return;
      }
      paramInt1 += 1;
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((this.mReturningToStart) && (i == 0))
      this.mReturningToStart = false;
    if ((isEnabled()) && (!this.mReturningToStart))
    {
      if ((!isChildScrollToTop()) && (!isChildScrollToBottom()))
        return false;
      if (isChildScrollToBottom())
        return handlerPushTouchEvent(paramMotionEvent, i);
      return handlerPullTouchEvent(paramMotionEvent, i);
    }
    return false;
  }

  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
  }

  public void resetTargetLayout()
  {
    int j = getMeasuredWidth();
    int i = getMeasuredHeight();
    Object localObject = this.mTarget;
    int k = getPaddingLeft();
    int m = getPaddingTop();
    ((View)localObject).layout(k, m, ((View)localObject).getWidth() - getPaddingLeft() - getPaddingRight() + k, ((View)localObject).getHeight() - getPaddingTop() - getPaddingBottom() + m);
    m = this.mHeadViewContainer.getMeasuredWidth();
    k = this.mHeadViewContainer.getMeasuredHeight();
    localObject = this.mHeadViewContainer;
    j /= 2;
    m /= 2;
    ((HeadViewContainer)localObject).layout(j - m, -k, m + j, 0);
    m = this.mFooterViewContainer.getMeasuredWidth();
    k = this.mFooterViewContainer.getMeasuredHeight();
    localObject = this.mFooterViewContainer;
    m /= 2;
    ((RelativeLayout)localObject).layout(j - m, i, j + m, k + i);
  }

  public void resetTargetLayoutDelay(int paramInt)
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        SuperSwipeRefreshLayout.this.resetTargetLayout();
      }
    }
    , paramInt);
  }

  public void setDefaultCircleBackgroundColor(int paramInt)
  {
    if (this.usingDefaultHeader)
      this.defaultProgressView.setCircleBackgroundColor(paramInt);
  }

  public void setDefaultCircleProgressColor(int paramInt)
  {
    if (this.usingDefaultHeader)
      this.defaultProgressView.setProgressColor(paramInt);
  }

  public void setDefaultCircleShadowColor(int paramInt)
  {
    if (this.usingDefaultHeader)
      this.defaultProgressView.setShadowColor(paramInt);
  }

  public void setDistanceToTriggerSync(int paramInt)
  {
    this.mTotalDragDistance = paramInt;
  }

  public void setFooterView(View paramView)
  {
    if (paramView == null)
      return;
    if (this.mFooterViewContainer == null)
      return;
    this.mFooterViewContainer.removeAllViews();
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.mFooterViewWidth, this.mFooterViewHeight);
    this.mFooterViewContainer.addView(paramView, localLayoutParams);
  }

  public void setHeaderView(View paramView)
  {
    if (paramView == null)
      return;
    if (this.mHeadViewContainer == null)
      return;
    this.usingDefaultHeader = false;
    this.mHeadViewContainer.removeAllViews();
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.mHeaderViewWidth, this.mHeaderViewHeight);
    localLayoutParams.addRule(12);
    this.mHeadViewContainer.addView(paramView, localLayoutParams);
  }

  public void setHeaderViewBackgroundColor(int paramInt)
  {
    this.mHeadViewContainer.setBackgroundColor(paramInt);
  }

  public void setLoadMore(boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.mLoadMore))
    {
      if (Build.VERSION.SDK_INT < 11)
      {
        this.mLoadMore = false;
        this.pushDistance = 0;
        updateFooterViewPosition();
        return;
      }
      animatorFooterToBottom(this.mFooterViewHeight, 0);
    }
  }

  public void setOnPullRefreshListener(OnPullRefreshListener paramOnPullRefreshListener)
  {
    this.mListener = paramOnPullRefreshListener;
  }

  public void setOnPushLoadMoreListener(OnPushLoadMoreListener paramOnPushLoadMoreListener)
  {
    this.mOnPushLoadMoreListener = paramOnPushLoadMoreListener;
  }

  public void setRefreshing(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.mRefreshing != paramBoolean))
    {
      this.mRefreshing = paramBoolean;
      int i;
      if (!this.mUsingCustomStart)
        i = (int)(this.mSpinnerFinalOffset + this.mOriginalOffsetTop);
      else
        i = (int)this.mSpinnerFinalOffset;
      setTargetOffsetTopAndBottom(i - this.mCurrentTargetOffsetTop, true);
      this.mNotify = false;
      startScaleUpAnimation(this.mRefreshListener);
      return;
    }
    setRefreshing(paramBoolean, false);
    if (this.usingDefaultHeader)
      this.defaultProgressView.setOnDraw(false);
  }

  public void setTargetScrollWithLayout(boolean paramBoolean)
  {
    this.targetScrollWithLayout = paramBoolean;
  }

  public class CircleProgressView extends View
    implements Runnable
  {
    private static final int PEROID = 16;
    private Paint bgPaint;
    private RectF bgRect = null;
    private int circleBackgroundColor = -1;
    private int height;
    private boolean isOnDraw = false;
    private boolean isRunning = false;
    private RectF ovalRect = null;
    private int progressColor = -3355444;
    private Paint progressPaint;
    private int shadowColor = -6710887;
    private int speed = 8;
    private int startAngle = 0;
    private int swipeAngle;
    private int width;

    public CircleProgressView(Context arg2)
    {
      super();
    }

    public CircleProgressView(Context paramAttributeSet, AttributeSet arg3)
    {
      super(localAttributeSet);
    }

    public CircleProgressView(Context paramAttributeSet, AttributeSet paramInt, int arg4)
    {
      super(paramInt, i);
    }

    private Paint createBgPaint()
    {
      if (this.bgPaint == null)
      {
        this.bgPaint = new Paint();
        this.bgPaint.setColor(this.circleBackgroundColor);
        this.bgPaint.setStyle(Paint.Style.FILL);
        this.bgPaint.setAntiAlias(true);
        if (Build.VERSION.SDK_INT >= 11)
          setLayerType(1, this.bgPaint);
        this.bgPaint.setShadowLayer(4.0F, 0.0F, 2.0F, this.shadowColor);
      }
      return this.bgPaint;
    }

    private Paint createPaint()
    {
      if (this.progressPaint == null)
      {
        this.progressPaint = new Paint();
        this.progressPaint.setStrokeWidth((int)(SuperSwipeRefreshLayout.this.density * 3.0F));
        this.progressPaint.setStyle(Paint.Style.STROKE);
        this.progressPaint.setAntiAlias(true);
      }
      this.progressPaint.setColor(this.progressColor);
      return this.progressPaint;
    }

    private RectF getBgRect()
    {
      this.width = getWidth();
      this.height = getHeight();
      if (this.bgRect == null)
      {
        int i = (int)(SuperSwipeRefreshLayout.this.density * 2.0F);
        float f = i;
        this.bgRect = new RectF(f, f, this.width - i, this.height - i);
      }
      return this.bgRect;
    }

    private RectF getOvalRect()
    {
      this.width = getWidth();
      this.height = getHeight();
      if (this.ovalRect == null)
      {
        int i = (int)(SuperSwipeRefreshLayout.this.density * 8.0F);
        float f = i;
        this.ovalRect = new RectF(f, f, this.width - i, this.height - i);
      }
      return this.ovalRect;
    }

    public boolean isRunning()
    {
      return this.isRunning;
    }

    protected void onDetachedFromWindow()
    {
      this.isOnDraw = false;
      super.onDetachedFromWindow();
    }

    protected void onDraw(Canvas paramCanvas)
    {
      super.onDraw(paramCanvas);
      paramCanvas.drawArc(getBgRect(), 0.0F, 360.0F, false, createBgPaint());
      if (this.startAngle / 360 % 2 == 0)
        this.swipeAngle = (this.startAngle % 720 / 2);
      else
        this.swipeAngle = (360 - this.startAngle % 720 / 2);
      paramCanvas.drawArc(getOvalRect(), this.startAngle, this.swipeAngle, false, createPaint());
    }

    public void onWindowFocusChanged(boolean paramBoolean)
    {
      super.onWindowFocusChanged(paramBoolean);
    }

    public void run()
    {
      while (this.isOnDraw)
      {
        this.isRunning = true;
        long l = System.currentTimeMillis();
        this.startAngle += this.speed;
        postInvalidate();
        l = System.currentTimeMillis() - l;
        if (l < 16L)
          try
          {
            Thread.sleep(16L - l);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
      }
    }

    public void setCircleBackgroundColor(int paramInt)
    {
      this.circleBackgroundColor = paramInt;
    }

    public void setOnDraw(boolean paramBoolean)
    {
      this.isOnDraw = paramBoolean;
    }

    public void setProgressColor(int paramInt)
    {
      this.progressColor = paramInt;
    }

    public void setPullDistance(int paramInt)
    {
      this.startAngle = (paramInt * 2);
      postInvalidate();
    }

    public void setShadowColor(int paramInt)
    {
      this.shadowColor = paramInt;
    }

    public void setSpeed(int paramInt)
    {
      this.speed = paramInt;
    }
  }

  private class HeadViewContainer extends RelativeLayout
  {
    private Animation.AnimationListener mListener;

    public HeadViewContainer(Context arg2)
    {
      super();
    }

    public void onAnimationEnd()
    {
      super.onAnimationEnd();
      if (this.mListener != null)
        this.mListener.onAnimationEnd(getAnimation());
    }

    public void onAnimationStart()
    {
      super.onAnimationStart();
      if (this.mListener != null)
        this.mListener.onAnimationStart(getAnimation());
    }

    public void setAnimationListener(Animation.AnimationListener paramAnimationListener)
    {
      this.mListener = paramAnimationListener;
    }
  }

  public static abstract interface OnPullRefreshListener
  {
    public abstract void onPullDistance(int paramInt);

    public abstract void onPullEnable(boolean paramBoolean);

    public abstract void onRefresh();
  }

  public class OnPullRefreshListenerAdapter
    implements SuperSwipeRefreshLayout.OnPullRefreshListener
  {
    public OnPullRefreshListenerAdapter()
    {
    }

    public void onPullDistance(int paramInt)
    {
    }

    public void onPullEnable(boolean paramBoolean)
    {
    }

    public void onRefresh()
    {
    }
  }

  public static abstract interface OnPushLoadMoreListener
  {
    public abstract void onLoadMore();

    public abstract void onPushDistance(int paramInt);

    public abstract void onPushEnable(boolean paramBoolean);
  }

  public class OnPushLoadMoreListenerAdapter
    implements SuperSwipeRefreshLayout.OnPushLoadMoreListener
  {
    public OnPushLoadMoreListenerAdapter()
    {
    }

    public void onLoadMore()
    {
    }

    public void onPushDistance(int paramInt)
    {
    }

    public void onPushEnable(boolean paramBoolean)
    {
    }
  }
}
