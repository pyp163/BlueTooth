package android.support.design.shape;

import android.support.design.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
public class TriangleEdgeTreatment extends EdgeTreatment
{
  private final boolean inside;
  private final float size;

  public TriangleEdgeTreatment(float paramFloat, boolean paramBoolean)
  {
    this.size = paramFloat;
    this.inside = paramBoolean;
  }

  public void getEdgePath(float paramFloat1, float paramFloat2, ShapePath paramShapePath)
  {
    float f2 = paramFloat1 / 2.0F;
    paramShapePath.lineTo(f2 - this.size * paramFloat2, 0.0F);
    if (this.inside);
    for (float f1 = this.size; ; f1 = -this.size)
      break;
    paramShapePath.lineTo(f2, f1 * paramFloat2);
    paramShapePath.lineTo(f2 + this.size * paramFloat2, 0.0F);
    paramShapePath.lineTo(paramFloat1, 0.0F);
  }
}