package me.relex.photodraweeview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

public class Attacher
  implements IAttacher, View.OnTouchListener, OnScaleDragGestureListener
{
  private static final int EDGE_BOTH = 2;
  private static final int EDGE_BOTTOM = 1;
  private static final int EDGE_LEFT = 0;
  private static final int EDGE_NONE = -1;
  private static final int EDGE_RIGHT = 1;
  private static final int EDGE_TOP = 0;
  public static final int HORIZONTAL = 0;
  public static final int VERTICAL = 1;
  private boolean mAllowParentInterceptOnEdge = true;
  private boolean mBlockParentIntercept = false;
  private FlingRunnable mCurrentFlingRunnable;
  private final RectF mDisplayRect = new RectF();
  private WeakReference<DraweeView<GenericDraweeHierarchy>> mDraweeView;
  private GestureDetectorCompat mGestureDetector;
  private int mImageInfoHeight = -1;
  private int mImageInfoWidth = -1;
  private View.OnLongClickListener mLongClickListener;
  private final Matrix mMatrix = new Matrix();
  private final float[] mMatrixValues = new float[9];
  private float mMaxScale = 3.0F;
  private float mMidScale = 1.75F;
  private float mMinScale = 1.0F;
  private int mOrientation = 0;
  private OnPhotoTapListener mPhotoTapListener;
  private OnScaleChangeListener mScaleChangeListener;
  private ScaleDragDetector mScaleDragDetector;
  private int mScrollEdgeX = 2;
  private int mScrollEdgeY = 2;
  private OnViewTapListener mViewTapListener;
  private long mZoomDuration = 200L;
  private final Interpolator mZoomInterpolator = new AccelerateDecelerateInterpolator();

  public Attacher(DraweeView<GenericDraweeHierarchy> paramDraweeView)
  {
    this.mDraweeView = new WeakReference(paramDraweeView);
    ((GenericDraweeHierarchy)paramDraweeView.getHierarchy()).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
    paramDraweeView.setOnTouchListener(this);
    this.mScaleDragDetector = new ScaleDragDetector(paramDraweeView.getContext(), this);
    this.mGestureDetector = new GestureDetectorCompat(paramDraweeView.getContext(), new GestureDetector.SimpleOnGestureListener()
    {
      public void onLongPress(MotionEvent paramAnonymousMotionEvent)
      {
        super.onLongPress(paramAnonymousMotionEvent);
        if (Attacher.this.mLongClickListener != null)
          Attacher.this.mLongClickListener.onLongClick(Attacher.this.getDraweeView());
      }
    });
    this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
  }

  private void cancelFling()
  {
    if (this.mCurrentFlingRunnable != null)
    {
      this.mCurrentFlingRunnable.cancelFling();
      this.mCurrentFlingRunnable = null;
    }
  }

  private void checkMinScale()
  {
    DraweeView localDraweeView = getDraweeView();
    if (localDraweeView == null)
      return;
    if (getScale() < this.mMinScale)
    {
      RectF localRectF = getDisplayRect();
      if (localRectF != null)
        localDraweeView.post(new AnimatedZoomRunnable(getScale(), this.mMinScale, localRectF.centerX(), localRectF.centerY()));
    }
  }

  private static void checkZoomLevels(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 >= paramFloat2)
      throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
    if (paramFloat2 >= paramFloat3)
      throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
  }

  private RectF getDisplayRect(Matrix paramMatrix)
  {
    DraweeView localDraweeView = getDraweeView();
    if ((localDraweeView != null) && ((this.mImageInfoWidth != -1) || (this.mImageInfoHeight != -1)))
    {
      this.mDisplayRect.set(0.0F, 0.0F, this.mImageInfoWidth, this.mImageInfoHeight);
      ((GenericDraweeHierarchy)localDraweeView.getHierarchy()).getActualImageBounds(this.mDisplayRect);
      paramMatrix.mapRect(this.mDisplayRect);
      return this.mDisplayRect;
    }
    return null;
  }

  private float getMatrixValue(Matrix paramMatrix, int paramInt)
  {
    paramMatrix.getValues(this.mMatrixValues);
    return this.mMatrixValues[paramInt];
  }

  private int getViewHeight()
  {
    DraweeView localDraweeView = getDraweeView();
    if (localDraweeView != null)
      return localDraweeView.getHeight() - localDraweeView.getPaddingTop() - localDraweeView.getPaddingBottom();
    return 0;
  }

  private int getViewWidth()
  {
    DraweeView localDraweeView = getDraweeView();
    if (localDraweeView != null)
      return localDraweeView.getWidth() - localDraweeView.getPaddingLeft() - localDraweeView.getPaddingRight();
    return 0;
  }

  private void postOnAnimation(View paramView, Runnable paramRunnable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      paramView.postOnAnimation(paramRunnable);
      return;
    }
    paramView.postDelayed(paramRunnable, 16L);
  }

  private void resetMatrix()
  {
    this.mMatrix.reset();
    checkMatrixBounds();
    DraweeView localDraweeView = getDraweeView();
    if (localDraweeView != null)
      localDraweeView.invalidate();
  }

  private void updateBaseMatrix()
  {
    if ((this.mImageInfoWidth == -1) && (this.mImageInfoHeight == -1))
      return;
    resetMatrix();
  }

  public void checkMatrixAndInvalidate()
  {
    DraweeView localDraweeView = getDraweeView();
    if (localDraweeView == null)
      return;
    if (checkMatrixBounds())
      localDraweeView.invalidate();
  }

  public boolean checkMatrixBounds()
  {
    RectF localRectF = getDisplayRect(getDrawMatrix());
    if (localRectF == null)
      return false;
    float f1 = localRectF.height();
    float f3 = localRectF.width();
    float f4 = getViewHeight();
    float f2 = 0.0F;
    if (f1 <= f4)
    {
      f1 = (f4 - f1) / 2.0F - localRectF.top;
      this.mScrollEdgeY = 2;
    }
    else if (localRectF.top > 0.0F)
    {
      f1 = -localRectF.top;
      this.mScrollEdgeY = 0;
    }
    else if (localRectF.bottom < f4)
    {
      f1 = f4 - localRectF.bottom;
      this.mScrollEdgeY = 1;
    }
    else
    {
      this.mScrollEdgeY = -1;
      f1 = 0.0F;
    }
    f4 = getViewWidth();
    if (f3 <= f4)
    {
      f2 = (f4 - f3) / 2.0F - localRectF.left;
      this.mScrollEdgeX = 2;
    }
    else if (localRectF.left > 0.0F)
    {
      f2 = -localRectF.left;
      this.mScrollEdgeX = 0;
    }
    else if (localRectF.right < f4)
    {
      f2 = f4 - localRectF.right;
      this.mScrollEdgeX = 1;
    }
    else
    {
      this.mScrollEdgeX = -1;
    }
    this.mMatrix.postTranslate(f2, f1);
    return true;
  }

  public RectF getDisplayRect()
  {
    checkMatrixBounds();
    return getDisplayRect(getDrawMatrix());
  }

  public Matrix getDrawMatrix()
  {
    return this.mMatrix;
  }

  @Nullable
  public DraweeView<GenericDraweeHierarchy> getDraweeView()
  {
    return (DraweeView)this.mDraweeView.get();
  }

  public float getMaximumScale()
  {
    return this.mMaxScale;
  }

  public float getMediumScale()
  {
    return this.mMidScale;
  }

  public float getMinimumScale()
  {
    return this.mMinScale;
  }

  public OnPhotoTapListener getOnPhotoTapListener()
  {
    return this.mPhotoTapListener;
  }

  public OnViewTapListener getOnViewTapListener()
  {
    return this.mViewTapListener;
  }

  public float getScale()
  {
    return (float)Math.sqrt((float)Math.pow(getMatrixValue(this.mMatrix, 0), 2.0D) + (float)Math.pow(getMatrixValue(this.mMatrix, 3), 2.0D));
  }

  protected void onDetachedFromWindow()
  {
    cancelFling();
  }

  public void onDrag(float paramFloat1, float paramFloat2)
  {
    Object localObject = getDraweeView();
    if ((localObject != null) && (!this.mScaleDragDetector.isScaling()))
    {
      this.mMatrix.postTranslate(paramFloat1, paramFloat2);
      checkMatrixAndInvalidate();
      localObject = ((DraweeView)localObject).getParent();
      if (localObject == null)
        return;
      if ((this.mAllowParentInterceptOnEdge) && (!this.mScaleDragDetector.isScaling()) && (!this.mBlockParentIntercept))
      {
        if ((this.mOrientation == 0) && ((this.mScrollEdgeX == 2) || ((this.mScrollEdgeX == 0) && (paramFloat1 >= 1.0F)) || ((this.mScrollEdgeX == 1) && (paramFloat1 <= -1.0F))))
        {
          ((ViewParent)localObject).requestDisallowInterceptTouchEvent(false);
          return;
        }
        if ((this.mOrientation == 1) && ((this.mScrollEdgeY == 2) || ((this.mScrollEdgeY == 0) && (paramFloat2 >= 1.0F)) || ((this.mScrollEdgeY == 1) && (paramFloat2 <= -1.0F))))
          ((ViewParent)localObject).requestDisallowInterceptTouchEvent(false);
      }
      else
      {
        ((ViewParent)localObject).requestDisallowInterceptTouchEvent(true);
      }
    }
  }

  public void onFling(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    DraweeView localDraweeView = getDraweeView();
    if (localDraweeView == null)
      return;
    this.mCurrentFlingRunnable = new FlingRunnable(localDraweeView.getContext());
    this.mCurrentFlingRunnable.fling(getViewWidth(), getViewHeight(), (int)paramFloat3, (int)paramFloat4);
    localDraweeView.post(this.mCurrentFlingRunnable);
  }

  public void onScale(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if ((getScale() < this.mMaxScale) || (paramFloat1 < 1.0F))
    {
      if (this.mScaleChangeListener != null)
        this.mScaleChangeListener.onScaleChange(paramFloat1, paramFloat2, paramFloat3);
      this.mMatrix.postScale(paramFloat1, paramFloat1, paramFloat2, paramFloat3);
      checkMatrixAndInvalidate();
    }
  }

  public void onScaleEnd()
  {
    checkMinScale();
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionMasked(paramMotionEvent);
    boolean bool3 = false;
    if (i != 3)
    {
      switch (i)
      {
      default:
        break;
      case 0:
        paramView = paramView.getParent();
        if (paramView != null)
          paramView.requestDisallowInterceptTouchEvent(true);
        cancelFling();
        break;
      case 1:
      }
    }
    else
    {
      paramView = paramView.getParent();
      if (paramView != null)
        paramView.requestDisallowInterceptTouchEvent(false);
    }
    boolean bool1 = this.mScaleDragDetector.isScaling();
    boolean bool4 = this.mScaleDragDetector.isDragging();
    boolean bool2 = this.mScaleDragDetector.onTouchEvent(paramMotionEvent);
    if ((!bool1) && (!this.mScaleDragDetector.isScaling()))
      i = 1;
    else
      i = 0;
    int j;
    if ((!bool4) && (!this.mScaleDragDetector.isDragging()))
      j = 1;
    else
      j = 0;
    bool1 = bool3;
    if (i != 0)
    {
      bool1 = bool3;
      if (j != 0)
        bool1 = true;
    }
    this.mBlockParentIntercept = bool1;
    bool1 = bool2;
    if (this.mGestureDetector.onTouchEvent(paramMotionEvent))
      bool1 = true;
    return bool1;
  }

  public void setAllowParentInterceptOnEdge(boolean paramBoolean)
  {
    this.mAllowParentInterceptOnEdge = paramBoolean;
  }

  public void setMaximumScale(float paramFloat)
  {
    checkZoomLevels(this.mMinScale, this.mMidScale, paramFloat);
    this.mMaxScale = paramFloat;
  }

  public void setMediumScale(float paramFloat)
  {
    checkZoomLevels(this.mMinScale, paramFloat, this.mMaxScale);
    this.mMidScale = paramFloat;
  }

  public void setMinimumScale(float paramFloat)
  {
    checkZoomLevels(paramFloat, this.mMidScale, this.mMaxScale);
    this.mMinScale = paramFloat;
  }

  public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener paramOnDoubleTapListener)
  {
    if (paramOnDoubleTapListener != null)
    {
      this.mGestureDetector.setOnDoubleTapListener(paramOnDoubleTapListener);
      return;
    }
    this.mGestureDetector.setOnDoubleTapListener(new DefaultOnDoubleTapListener(this));
  }

  public void setOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    this.mLongClickListener = paramOnLongClickListener;
  }

  public void setOnPhotoTapListener(OnPhotoTapListener paramOnPhotoTapListener)
  {
    this.mPhotoTapListener = paramOnPhotoTapListener;
  }

  public void setOnScaleChangeListener(OnScaleChangeListener paramOnScaleChangeListener)
  {
    this.mScaleChangeListener = paramOnScaleChangeListener;
  }

  public void setOnViewTapListener(OnViewTapListener paramOnViewTapListener)
  {
    this.mViewTapListener = paramOnViewTapListener;
  }

  public void setOrientation(int paramInt)
  {
    this.mOrientation = paramInt;
  }

  public void setScale(float paramFloat)
  {
    setScale(paramFloat, false);
  }

  public void setScale(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean)
  {
    DraweeView localDraweeView = getDraweeView();
    if ((localDraweeView != null) && (paramFloat1 >= this.mMinScale))
    {
      if (paramFloat1 > this.mMaxScale)
        return;
      if (paramBoolean)
      {
        localDraweeView.post(new AnimatedZoomRunnable(getScale(), paramFloat1, paramFloat2, paramFloat3));
        return;
      }
      this.mMatrix.setScale(paramFloat1, paramFloat1, paramFloat2, paramFloat3);
      checkMatrixAndInvalidate();
      return;
    }
  }

  public void setScale(float paramFloat, boolean paramBoolean)
  {
    DraweeView localDraweeView = getDraweeView();
    if (localDraweeView != null)
      setScale(paramFloat, localDraweeView.getRight() / 2, localDraweeView.getBottom() / 2, false);
  }

  public void setZoomTransitionDuration(long paramLong)
  {
    long l = paramLong;
    if (paramLong < 0L)
      l = 200L;
    this.mZoomDuration = l;
  }

  public void update(int paramInt1, int paramInt2)
  {
    this.mImageInfoWidth = paramInt1;
    this.mImageInfoHeight = paramInt2;
    updateBaseMatrix();
  }

  private class AnimatedZoomRunnable
    implements Runnable
  {
    private final float mFocalX;
    private final float mFocalY;
    private final long mStartTime;
    private final float mZoomEnd;
    private final float mZoomStart;

    public AnimatedZoomRunnable(float paramFloat1, float paramFloat2, float paramFloat3, float arg5)
    {
      this.mFocalX = paramFloat3;
      Object localObject;
      this.mFocalY = localObject;
      this.mStartTime = System.currentTimeMillis();
      this.mZoomStart = paramFloat1;
      this.mZoomEnd = paramFloat2;
    }

    private float interpolate()
    {
      float f = Math.min(1.0F, (float)(System.currentTimeMillis() - this.mStartTime) * 1.0F / (float)Attacher.this.mZoomDuration);
      return Attacher.this.mZoomInterpolator.getInterpolation(f);
    }

    public void run()
    {
      DraweeView localDraweeView = Attacher.this.getDraweeView();
      if (localDraweeView == null)
        return;
      float f1 = interpolate();
      float f2 = (this.mZoomStart + (this.mZoomEnd - this.mZoomStart) * f1) / Attacher.this.getScale();
      Attacher.this.onScale(f2, this.mFocalX, this.mFocalY);
      if (f1 < 1.0F)
        Attacher.this.postOnAnimation(localDraweeView, this);
    }
  }

  private class FlingRunnable
    implements Runnable
  {
    private int mCurrentX;
    private int mCurrentY;
    private final ScrollerCompat mScroller;

    public FlingRunnable(Context arg2)
    {
      Context localContext;
      this.mScroller = ScrollerCompat.create(localContext);
    }

    public void cancelFling()
    {
      this.mScroller.abortAnimation();
    }

    public void fling(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      RectF localRectF = Attacher.this.getDisplayRect();
      if (localRectF == null)
        return;
      int k = Math.round(-localRectF.left);
      float f = paramInt1;
      int j;
      int i;
      if (f < localRectF.width())
      {
        j = Math.round(localRectF.width() - f);
        i = 0;
      }
      else
      {
        paramInt1 = k;
        j = paramInt1;
        i = paramInt1;
      }
      int m = Math.round(-localRectF.top);
      f = paramInt2;
      if (f < localRectF.height())
      {
        paramInt2 = Math.round(localRectF.height() - f);
        paramInt1 = 0;
      }
      else
      {
        paramInt1 = m;
        paramInt2 = paramInt1;
      }
      this.mCurrentX = k;
      this.mCurrentY = m;
      if ((k != j) || (m != paramInt2))
        this.mScroller.fling(k, m, paramInt3, paramInt4, i, j, paramInt1, paramInt2, 0, 0);
    }

    public void run()
    {
      if (this.mScroller.isFinished())
        return;
      DraweeView localDraweeView = Attacher.this.getDraweeView();
      if ((localDraweeView != null) && (this.mScroller.computeScrollOffset()))
      {
        int i = this.mScroller.getCurrX();
        int j = this.mScroller.getCurrY();
        Attacher.this.mMatrix.postTranslate(this.mCurrentX - i, this.mCurrentY - j);
        localDraweeView.invalidate();
        this.mCurrentX = i;
        this.mCurrentY = j;
        Attacher.this.postOnAnimation(localDraweeView, this);
      }
    }
  }

  @Retention(RetentionPolicy.SOURCE)
  public static @interface OrientationMode
  {
  }
}