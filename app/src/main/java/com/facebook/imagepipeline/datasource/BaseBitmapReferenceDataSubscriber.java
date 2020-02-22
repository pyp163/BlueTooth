package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import javax.annotation.Nullable;

public abstract class BaseBitmapReferenceDataSubscriber extends BaseDataSubscriber<CloseableReference<CloseableImage>>
{
  protected abstract void onNewResultImpl(@Nullable CloseableReference<Bitmap> paramCloseableReference);

  public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> paramDataSource)
  {
    if (!paramDataSource.isFinished())
      return;
    CloseableReference localCloseableReference = (CloseableReference)paramDataSource.getResult();
    Object localObject1 = null;
    paramDataSource = localObject1;
    if (localCloseableReference != null)
    {
      paramDataSource = localObject1;
      if ((localCloseableReference.get() instanceof CloseableStaticBitmap))
        paramDataSource = ((CloseableStaticBitmap)localCloseableReference.get()).convertToBitmapReference();
    }
    try
    {
      onNewResultImpl(paramDataSource);
      return;
    }
    finally
    {
      CloseableReference.closeSafely(paramDataSource);
      CloseableReference.closeSafely(localCloseableReference);
    }
  }
}