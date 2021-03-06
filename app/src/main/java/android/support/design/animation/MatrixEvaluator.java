package android.support.design.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

public class MatrixEvaluator
  implements TypeEvaluator<Matrix>
{
  private final float[] tempEndValues = new float[9];
  private final Matrix tempMatrix = new Matrix();
  private final float[] tempStartValues = new float[9];

  public Matrix evaluate(float paramFloat, Matrix paramMatrix1, Matrix paramMatrix2)
  {
    paramMatrix1.getValues(this.tempStartValues);
    paramMatrix2.getValues(this.tempEndValues);
    int i = 0;
    while (i < 9)
    {
      float f1 = this.tempEndValues[i];
      float f2 = this.tempStartValues[i];
      this.tempEndValues[i] = (this.tempStartValues[i] + (f1 - f2) * paramFloat);
      i += 1;
    }
    this.tempMatrix.setValues(this.tempEndValues);
    return this.tempMatrix;
  }
}