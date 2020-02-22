package android.support.design.animation;

import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;

public class ImageMatrixProperty extends Property<ImageView, Matrix>
{
  private final Matrix matrix = new Matrix();

  public ImageMatrixProperty()
  {
    super(Matrix.class, "imageMatrixProperty");
  }

  public Matrix get(ImageView paramImageView)
  {
    this.matrix.set(paramImageView.getImageMatrix());
    return this.matrix;
  }

  public void set(ImageView paramImageView, Matrix paramMatrix)
  {
    paramImageView.setImageMatrix(paramMatrix);
  }
}