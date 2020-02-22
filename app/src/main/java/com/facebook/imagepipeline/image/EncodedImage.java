package com.facebook.imagepipeline.image;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.SharedReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imageutils.JfifUtil;
import com.facebook.imageutils.WebpUtil;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class EncodedImage
  implements Closeable
{
  public static final int DEFAULT_SAMPLE_SIZE = 1;
  public static final int UNKNOWN_HEIGHT = -1;
  public static final int UNKNOWN_ROTATION_ANGLE = -1;
  public static final int UNKNOWN_STREAM_SIZE = -1;
  public static final int UNKNOWN_WIDTH = -1;

  @Nullable
  private BytesRange mBytesRange;
  private int mExifOrientation = 0;
  private int mHeight = -1;
  private ImageFormat mImageFormat = ImageFormat.UNKNOWN;

  @Nullable
  private final Supplier<FileInputStream> mInputStreamSupplier;

  @Nullable
  private final CloseableReference<PooledByteBuffer> mPooledByteBufferRef;
  private int mRotationAngle = -1;
  private int mSampleSize = 1;
  private int mStreamSize = -1;
  private int mWidth = -1;

  public EncodedImage(Supplier<FileInputStream> paramSupplier)
  {
    Preconditions.checkNotNull(paramSupplier);
    this.mPooledByteBufferRef = null;
    this.mInputStreamSupplier = paramSupplier;
  }

  public EncodedImage(Supplier<FileInputStream> paramSupplier, int paramInt)
  {
    this(paramSupplier);
    this.mStreamSize = paramInt;
  }

  public EncodedImage(CloseableReference<PooledByteBuffer> paramCloseableReference)
  {
    Preconditions.checkArgument(CloseableReference.isValid(paramCloseableReference));
    this.mPooledByteBufferRef = paramCloseableReference.clone();
    this.mInputStreamSupplier = null;
  }

  public static EncodedImage cloneOrNull(EncodedImage paramEncodedImage)
  {
    if (paramEncodedImage != null)
      return paramEncodedImage.cloneOrNull();
    return null;
  }

  public static void closeSafely(@Nullable EncodedImage paramEncodedImage)
  {
    if (paramEncodedImage != null)
      paramEncodedImage.close();
  }

  public static boolean isMetaDataAvailable(EncodedImage paramEncodedImage)
  {
    return (paramEncodedImage.mRotationAngle >= 0) && (paramEncodedImage.mWidth >= 0) && (paramEncodedImage.mHeight >= 0);
  }

  public static boolean isValid(@Nullable EncodedImage paramEncodedImage)
  {
    return (paramEncodedImage != null) && (paramEncodedImage.isValid());
  }

  // ERROR //
  private Pair<Integer, Integer> readImageSize()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 113	com/facebook/imagepipeline/image/EncodedImage:getInputStream	()Ljava/io/InputStream;
    //   4: astore_2
    //   5: aload_2
    //   6: invokestatic 119	com/facebook/imageutils/BitmapUtil:decodeDimensions	(Ljava/io/InputStream;)Landroid/util/Pair;
    //   9: astore_1
    //   10: aload_1
    //   11: ifnull +31 -> 42
    //   14: aload_0
    //   15: aload_1
    //   16: getfield 125	android/util/Pair:first	Ljava/lang/Object;
    //   19: checkcast 127	java/lang/Integer
    //   22: invokevirtual 131	java/lang/Integer:intValue	()I
    //   25: putfield 50	com/facebook/imagepipeline/image/EncodedImage:mWidth	I
    //   28: aload_0
    //   29: aload_1
    //   30: getfield 134	android/util/Pair:second	Ljava/lang/Object;
    //   33: checkcast 127	java/lang/Integer
    //   36: invokevirtual 131	java/lang/Integer:intValue	()I
    //   39: putfield 52	com/facebook/imagepipeline/image/EncodedImage:mHeight	I
    //   42: aload_2
    //   43: ifnull +7 -> 50
    //   46: aload_2
    //   47: invokevirtual 137	java/io/InputStream:close	()V
    //   50: aload_1
    //   51: areturn
    //   52: astore_1
    //   53: goto +6 -> 59
    //   56: astore_1
    //   57: aconst_null
    //   58: astore_2
    //   59: aload_2
    //   60: ifnull +7 -> 67
    //   63: aload_2
    //   64: invokevirtual 137	java/io/InputStream:close	()V
    //   67: aload_1
    //   68: athrow
    //   69: astore_2
    //   70: aload_1
    //   71: areturn
    //   72: astore_2
    //   73: goto -6 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   5	10	52	finally
    //   14	42	52	finally
    //   0	5	56	finally
    //   46	50	69	java/io/IOException
    //   63	67	72	java/io/IOException
  }

  private Pair<Integer, Integer> readWebPImageSize()
  {
    Pair localPair = WebpUtil.getSize(getInputStream());
    if (localPair != null)
    {
      this.mWidth = ((Integer)localPair.first).intValue();
      this.mHeight = ((Integer)localPair.second).intValue();
    }
    return localPair;
  }

  public EncodedImage cloneOrNull()
  {
    EncodedImage localEncodedImage;
    CloseableReference localCloseableReference;
    if (this.mInputStreamSupplier != null)
    {
      localEncodedImage = new EncodedImage(this.mInputStreamSupplier, this.mStreamSize);
    }
    else
    {
      localCloseableReference = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
      if (localCloseableReference == null)
        localEncodedImage = null;
    }
    try
    {
      localEncodedImage = new EncodedImage(localCloseableReference);
      CloseableReference.closeSafely(localCloseableReference);
      if (localEncodedImage != null)
        localEncodedImage.copyMetaDataFrom(this);
      return localEncodedImage;
    }
    finally
    {
      CloseableReference.closeSafely(localCloseableReference);
    }
  }

  public void close()
  {
    CloseableReference.closeSafely(this.mPooledByteBufferRef);
  }

  public void copyMetaDataFrom(EncodedImage paramEncodedImage)
  {
    this.mImageFormat = paramEncodedImage.getImageFormat();
    this.mWidth = paramEncodedImage.getWidth();
    this.mHeight = paramEncodedImage.getHeight();
    this.mRotationAngle = paramEncodedImage.getRotationAngle();
    this.mExifOrientation = paramEncodedImage.getExifOrientation();
    this.mSampleSize = paramEncodedImage.getSampleSize();
    this.mStreamSize = paramEncodedImage.getSize();
    this.mBytesRange = paramEncodedImage.getBytesRange();
  }

  public CloseableReference<PooledByteBuffer> getByteBufferRef()
  {
    return CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
  }

  @Nullable
  public BytesRange getBytesRange()
  {
    return this.mBytesRange;
  }

  public int getExifOrientation()
  {
    return this.mExifOrientation;
  }

  public String getFirstBytesAsHexString(int paramInt)
  {
    Object localObject1 = getByteBufferRef();
    if (localObject1 == null)
      return "";
    paramInt = Math.min(getSize(), paramInt);
    byte[] arrayOfByte = new byte[paramInt];
    try
    {
      PooledByteBuffer localPooledByteBuffer = (PooledByteBuffer)((CloseableReference)localObject1).get();
      if (localPooledByteBuffer == null)
        return "";
      localPooledByteBuffer.read(0, arrayOfByte, 0, paramInt);
      ((CloseableReference)localObject1).close();
      localObject1 = new StringBuilder(arrayOfByte.length * 2);
      int i = arrayOfByte.length;
      paramInt = 0;
      while (paramInt < i)
      {
        ((StringBuilder)localObject1).append(String.format("%02X", new Object[] { Byte.valueOf(arrayOfByte[paramInt]) }));
        paramInt += 1;
      }
      return ((StringBuilder)localObject1).toString();
    }
    finally
    {
      ((CloseableReference)localObject1).close();
    }
  }

  public int getHeight()
  {
    return this.mHeight;
  }

  public ImageFormat getImageFormat()
  {
    return this.mImageFormat;
  }

  public InputStream getInputStream()
  {
    if (this.mInputStreamSupplier != null)
      return (InputStream)this.mInputStreamSupplier.get();
    CloseableReference localCloseableReference = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
    if (localCloseableReference != null)
      try
      {
        PooledByteBufferInputStream localPooledByteBufferInputStream = new PooledByteBufferInputStream((PooledByteBuffer)localCloseableReference.get());
        return localPooledByteBufferInputStream;
      }
      finally
      {
        CloseableReference.closeSafely(localCloseableReference);
      }
    return null;
  }

  public int getRotationAngle()
  {
    return this.mRotationAngle;
  }

  public int getSampleSize()
  {
    return this.mSampleSize;
  }

  public int getSize()
  {
    if ((this.mPooledByteBufferRef != null) && (this.mPooledByteBufferRef.get() != null))
      return ((PooledByteBuffer)this.mPooledByteBufferRef.get()).size();
    return this.mStreamSize;
  }

  @VisibleForTesting
  public SharedReference<PooledByteBuffer> getUnderlyingReferenceTestOnly()
  {
    try
    {
      SharedReference localSharedReference;
      if (this.mPooledByteBufferRef != null)
        localSharedReference = this.mPooledByteBufferRef.getUnderlyingReferenceTestOnly();
      else
        localSharedReference = null;
      return localSharedReference;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getWidth()
  {
    return this.mWidth;
  }

  public boolean isCompleteAt(int paramInt)
  {
    if (this.mImageFormat != DefaultImageFormats.JPEG)
      return true;
    if (this.mInputStreamSupplier != null)
      return true;
    Preconditions.checkNotNull(this.mPooledByteBufferRef);
    PooledByteBuffer localPooledByteBuffer = (PooledByteBuffer)this.mPooledByteBufferRef.get();
    return (localPooledByteBuffer.read(paramInt - 2) == -1) && (localPooledByteBuffer.read(paramInt - 1) == -39);
  }

  public boolean isValid()
  {
    try
    {
      if (!CloseableReference.isValid(this.mPooledByteBufferRef))
      {
        Supplier localSupplier = this.mInputStreamSupplier;
        if (localSupplier == null)
        {
          bool = false;
          break label31;
        }
      }
      boolean bool = true;
      label31: return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void parseMetaData()
  {
    ImageFormat localImageFormat = ImageFormatChecker.getImageFormat_WrapIOException(getInputStream());
    this.mImageFormat = localImageFormat;
    Pair localPair;
    if (DefaultImageFormats.isWebpFormat(localImageFormat))
      localPair = readWebPImageSize();
    else
      localPair = readImageSize();
    if ((localImageFormat == DefaultImageFormats.JPEG) && (this.mRotationAngle == -1))
    {
      if (localPair != null)
      {
        this.mExifOrientation = JfifUtil.getOrientation(getInputStream());
        this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(this.mExifOrientation);
      }
    }
    else
      this.mRotationAngle = 0;
  }

  public void setBytesRange(@Nullable BytesRange paramBytesRange)
  {
    this.mBytesRange = paramBytesRange;
  }

  public void setExifOrientation(int paramInt)
  {
    this.mExifOrientation = paramInt;
  }

  public void setHeight(int paramInt)
  {
    this.mHeight = paramInt;
  }

  public void setImageFormat(ImageFormat paramImageFormat)
  {
    this.mImageFormat = paramImageFormat;
  }

  public void setRotationAngle(int paramInt)
  {
    this.mRotationAngle = paramInt;
  }

  public void setSampleSize(int paramInt)
  {
    this.mSampleSize = paramInt;
  }

  public void setStreamSize(int paramInt)
  {
    this.mStreamSize = paramInt;
  }

  public void setWidth(int paramInt)
  {
    this.mWidth = paramInt;
  }
}