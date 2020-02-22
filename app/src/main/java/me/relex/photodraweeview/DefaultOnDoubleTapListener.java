package me.relex.photodraweeview;

import android.graphics.RectF;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import com.facebook.drawee.view.DraweeView;

public class DefaultOnDoubleTapListener
  implements GestureDetector.OnDoubleTapListener
{
  protected Attacher mAttacher;

  public DefaultOnDoubleTapListener(Attacher paramAttacher)
  {
    setPhotoDraweeViewAttacher(paramAttacher);
  }

  public boolean onDoubleTap(MotionEvent paramMotionEvent)
  {
    if (this.mAttacher == null)
      return false;
    try
    {
      float f1 = this.mAttacher.getScale();
      float f2 = paramMotionEvent.getX();
      float f3 = paramMotionEvent.getY();
      if (f1 < this.mAttacher.getMediumScale())
      {
        this.mAttacher.setScale(this.mAttacher.getMediumScale(), f2, f3, true);
        return true;
      }
      if ((f1 >= this.mAttacher.getMediumScale()) && (f1 < this.mAttacher.getMaximumScale()))
      {
        this.mAttacher.setScale(this.mAttacher.getMaximumScale(), f2, f3, true);
        return true;
      }
      this.mAttacher.setScale(this.mAttacher.getMinimumScale(), f2, f3, true);
      return true;
    }
    catch (Exception paramMotionEvent)
    {
    }
    return true;
  }

  public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
  {
    if (this.mAttacher == null)
      return false;
    DraweeView localDraweeView = this.mAttacher.getDraweeView();
    if (localDraweeView == null)
      return false;
    if (this.mAttacher.getOnPhotoTapListener() != null)
    {
      RectF localRectF = this.mAttacher.getDisplayRect();
      if (localRectF != null)
      {
        float f2 = paramMotionEvent.getX();
        float f1 = paramMotionEvent.getY();
        if (localRectF.contains(f2, f1))
        {
          f2 = (f2 - localRectF.left) / localRectF.width();
          f1 = (f1 - localRectF.top) / localRectF.height();
          this.mAttacher.getOnPhotoTapListener().onPhotoTap(localDraweeView, f2, f1);
          return true;
        }
      }
    }
    if (this.mAttacher.getOnViewTapListener() != null)
    {
      this.mAttacher.getOnViewTapListener().onViewTap(localDraweeView, paramMotionEvent.getX(), paramMotionEvent.getY());
      return true;
    }
    return false;
  }

  public void setPhotoDraweeViewAttacher(Attacher paramAttacher)
  {
    this.mAttacher = paramAttacher;
  }
}