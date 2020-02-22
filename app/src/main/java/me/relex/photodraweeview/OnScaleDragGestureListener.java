package me.relex.photodraweeview;

public abstract interface OnScaleDragGestureListener
{
  public abstract void onDrag(float paramFloat1, float paramFloat2);

  public abstract void onFling(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);

  public abstract void onScale(float paramFloat1, float paramFloat2, float paramFloat3);

  public abstract void onScaleEnd();
}