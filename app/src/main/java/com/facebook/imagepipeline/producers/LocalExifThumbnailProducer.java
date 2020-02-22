package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Pair;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class LocalExifThumbnailProducer
  implements ThumbnailProducer<EncodedImage>
{
  private static final int COMMON_EXIF_THUMBNAIL_MAX_DIMENSION = 512;

  @VisibleForTesting
  static final String CREATED_THUMBNAIL = "createdThumbnail";
  public static final String PRODUCER_NAME = "LocalExifThumbnailProducer";
  private final ContentResolver mContentResolver;
  private final Executor mExecutor;
  private final PooledByteBufferFactory mPooledByteBufferFactory;

  public LocalExifThumbnailProducer(Executor paramExecutor, PooledByteBufferFactory paramPooledByteBufferFactory, ContentResolver paramContentResolver)
  {
    this.mExecutor = paramExecutor;
    this.mPooledByteBufferFactory = paramPooledByteBufferFactory;
    this.mContentResolver = paramContentResolver;
  }

  private EncodedImage buildEncodedImage(PooledByteBuffer paramPooledByteBuffer, ExifInterface paramExifInterface)
  {
    Pair localPair = BitmapUtil.decodeDimensions(new PooledByteBufferInputStream(paramPooledByteBuffer));
    int k = getRotationAngle(paramExifInterface);
    int j = -1;
    int i;
    if (localPair != null)
      i = ((Integer)localPair.first).intValue();
    else
      i = -1;
    if (localPair != null)
      j = ((Integer)localPair.second).intValue();
    paramPooledByteBuffer = CloseableReference.of(paramPooledByteBuffer);
    try
    {
      paramExifInterface = new EncodedImage(paramPooledByteBuffer);
      CloseableReference.closeSafely(paramPooledByteBuffer);
      paramExifInterface.setImageFormat(DefaultImageFormats.JPEG);
      paramExifInterface.setRotationAngle(k);
      paramExifInterface.setWidth(i);
      paramExifInterface.setHeight(j);
      return paramExifInterface;
    }
    finally
    {
      CloseableReference.closeSafely(paramPooledByteBuffer);
    }
    throw paramExifInterface;
  }

  private int getRotationAngle(ExifInterface paramExifInterface)
  {
    return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt(paramExifInterface.getAttribute("Orientation")));
  }

  public boolean canProvideImageForSize(ResizeOptions paramResizeOptions)
  {
    return ThumbnailSizeChecker.isImageBigEnough(512, 512, paramResizeOptions);
  }

  @VisibleForTesting
  boolean canReadAsFile(String paramString)
    throws IOException
  {
    boolean bool2 = false;
    if (paramString == null)
      return false;
    paramString = new File(paramString);
    boolean bool1 = bool2;
    if (paramString.exists())
    {
      bool1 = bool2;
      if (paramString.canRead())
        bool1 = true;
    }
    return bool1;
  }

  @Nullable
  @VisibleForTesting
  ExifInterface getExifInterface(Uri paramUri)
  {
    paramUri = UriUtil.getRealPathFromUri(this.mContentResolver, paramUri);
    try
    {
      if (canReadAsFile(paramUri))
      {
        paramUri = new ExifInterface(paramUri);
        return paramUri;
        label28: FLog.e(LocalExifThumbnailProducer.class, "StackOverflowError in ExifInterface constructor");
      }
      label35: return null;
    }
    catch (IOException paramUri)
    {
      break label35;
    }
    catch (StackOverflowError paramUri)
    {
      break label28;
    }
  }

  public void produceResults(final Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    paramConsumer = new StatefulProducerRunnable(paramConsumer, paramProducerContext.getListener(), "LocalExifThumbnailProducer", paramProducerContext.getId())
    {
      protected void disposeResult(EncodedImage paramAnonymousEncodedImage)
      {
        EncodedImage.closeSafely(paramAnonymousEncodedImage);
      }

      protected Map<String, String> getExtraMapOnSuccess(EncodedImage paramAnonymousEncodedImage)
      {
        boolean bool;
        if (paramAnonymousEncodedImage != null)
          bool = true;
        else
          bool = false;
        return ImmutableMap.of("createdThumbnail", Boolean.toString(bool));
      }

      protected EncodedImage getResult()
        throws Exception
      {
        Object localObject1 = this.val$imageRequest.getSourceUri();
        localObject1 = LocalExifThumbnailProducer.this.getExifInterface((Uri)localObject1);
        if ((localObject1 != null) && (((ExifInterface)localObject1).hasThumbnail()))
        {
          Object localObject2 = ((ExifInterface)localObject1).getThumbnail();
          localObject2 = LocalExifThumbnailProducer.this.mPooledByteBufferFactory.newByteBuffer((byte[])localObject2);
          return LocalExifThumbnailProducer.this.buildEncodedImage((PooledByteBuffer)localObject2, (ExifInterface)localObject1);
        }
        return null;
      }
    };
    paramProducerContext.addCallbacks(new BaseProducerContextCallbacks()
    {
      public void onCancellationRequested()
      {
        paramConsumer.cancel();
      }
    });
    this.mExecutor.execute(paramConsumer);
  }
}