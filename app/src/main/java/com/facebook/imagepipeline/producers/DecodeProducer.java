package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.ExceptionWithNoStacktrace;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.decoder.DecodeException;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class DecodeProducer
  implements Producer<CloseableReference<CloseableImage>>
{
  public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
  public static final String EXTRA_BITMAP_SIZE = "bitmapSize";
  public static final String EXTRA_HAS_GOOD_QUALITY = "hasGoodQuality";
  public static final String EXTRA_IMAGE_FORMAT_NAME = "imageFormat";
  public static final String EXTRA_IS_FINAL = "isFinal";
  public static final String PRODUCER_NAME = "DecodeProducer";
  public static final String REQUESTED_IMAGE_SIZE = "requestedImageSize";
  public static final String SAMPLE_SIZE = "sampleSize";
  private final ByteArrayPool mByteArrayPool;
  private final boolean mDecodeCancellationEnabled;
  private final boolean mDownsampleEnabled;
  private final boolean mDownsampleEnabledForNetwork;
  private final Executor mExecutor;
  private final ImageDecoder mImageDecoder;
  private final Producer<EncodedImage> mInputProducer;
  private final ProgressiveJpegConfig mProgressiveJpegConfig;

  public DecodeProducer(ByteArrayPool paramByteArrayPool, Executor paramExecutor, ImageDecoder paramImageDecoder, ProgressiveJpegConfig paramProgressiveJpegConfig, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Producer<EncodedImage> paramProducer)
  {
    this.mByteArrayPool = ((ByteArrayPool)Preconditions.checkNotNull(paramByteArrayPool));
    this.mExecutor = ((Executor)Preconditions.checkNotNull(paramExecutor));
    this.mImageDecoder = ((ImageDecoder)Preconditions.checkNotNull(paramImageDecoder));
    this.mProgressiveJpegConfig = ((ProgressiveJpegConfig)Preconditions.checkNotNull(paramProgressiveJpegConfig));
    this.mDownsampleEnabled = paramBoolean1;
    this.mDownsampleEnabledForNetwork = paramBoolean2;
    this.mInputProducer = ((Producer)Preconditions.checkNotNull(paramProducer));
    this.mDecodeCancellationEnabled = paramBoolean3;
  }

  public void produceResults(Consumer<CloseableReference<CloseableImage>> paramConsumer, ProducerContext paramProducerContext)
  {
    if (!UriUtil.isNetworkUri(paramProducerContext.getImageRequest().getSourceUri()))
      paramConsumer = new LocalImagesProgressiveDecoder(paramConsumer, paramProducerContext, this.mDecodeCancellationEnabled);
    else
      paramConsumer = new NetworkImagesProgressiveDecoder(paramConsumer, paramProducerContext, new ProgressiveJpegParser(this.mByteArrayPool), this.mProgressiveJpegConfig, this.mDecodeCancellationEnabled);
    this.mInputProducer.produceResults(paramConsumer, paramProducerContext);
  }

  private class LocalImagesProgressiveDecoder extends DecodeProducer.ProgressiveDecoder
  {
    public LocalImagesProgressiveDecoder(ProducerContext paramBoolean, boolean arg3)
    {
      super(paramBoolean, localProducerContext, bool);
    }

    protected int getIntermediateImageEndOffset(EncodedImage paramEncodedImage)
    {
      return paramEncodedImage.getSize();
    }

    protected QualityInfo getQualityInfo()
    {
      return ImmutableQualityInfo.of(0, false, false);
    }

    protected boolean updateDecodeJob(EncodedImage paramEncodedImage, int paramInt)
    {
      try
      {
        boolean bool = isNotLast(paramInt);
        if (bool)
          return false;
        bool = super.updateDecodeJob(paramEncodedImage, paramInt);
        return bool;
      }
      finally
      {
      }
      throw paramEncodedImage;
    }
  }

  private class NetworkImagesProgressiveDecoder extends DecodeProducer.ProgressiveDecoder
  {
    private int mLastScheduledScanNumber;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final ProgressiveJpegParser mProgressiveJpegParser;

    public NetworkImagesProgressiveDecoder(ProducerContext paramProgressiveJpegParser, ProgressiveJpegParser paramProgressiveJpegConfig, ProgressiveJpegConfig paramBoolean, boolean arg5)
    {
      super(paramProgressiveJpegParser, paramProgressiveJpegConfig, bool);
      this.mProgressiveJpegParser = ((ProgressiveJpegParser)Preconditions.checkNotNull(paramBoolean));
      Object localObject;
      this.mProgressiveJpegConfig = ((ProgressiveJpegConfig)Preconditions.checkNotNull(localObject));
      this.mLastScheduledScanNumber = 0;
    }

    protected int getIntermediateImageEndOffset(EncodedImage paramEncodedImage)
    {
      return this.mProgressiveJpegParser.getBestScanEndOffset();
    }

    protected QualityInfo getQualityInfo()
    {
      return this.mProgressiveJpegConfig.getQualityInfo(this.mProgressiveJpegParser.getBestScanNumber());
    }

    protected boolean updateDecodeJob(EncodedImage paramEncodedImage, int paramInt)
    {
      try
      {
        boolean bool1 = super.updateDecodeJob(paramEncodedImage, paramInt);
        if (((isNotLast(paramInt)) || (statusHasFlag(paramInt, 8))) && (!statusHasFlag(paramInt, 4)) && (EncodedImage.isValid(paramEncodedImage)) && (paramEncodedImage.getImageFormat() == DefaultImageFormats.JPEG))
        {
          boolean bool2 = this.mProgressiveJpegParser.parseMoreData(paramEncodedImage);
          if (!bool2)
            return false;
          paramInt = this.mProgressiveJpegParser.getBestScanNumber();
          int i = this.mLastScheduledScanNumber;
          if (paramInt <= i)
            return false;
          if (paramInt < this.mProgressiveJpegConfig.getNextScanNumberToDecode(this.mLastScheduledScanNumber))
          {
            bool2 = this.mProgressiveJpegParser.isEndMarkerRead();
            if (!bool2)
              return false;
          }
          this.mLastScheduledScanNumber = paramInt;
        }
        return bool1;
      }
      finally
      {
      }
      throw paramEncodedImage;
    }
  }

  private abstract class ProgressiveDecoder extends DelegatingConsumer<EncodedImage, CloseableReference<CloseableImage>>
  {
    private static final int DECODE_EXCEPTION_MESSAGE_NUM_HEADER_BYTES = 10;
    private final String TAG = "ProgressiveDecoder";
    private final ImageDecodeOptions mImageDecodeOptions;

    @GuardedBy("this")
    private boolean mIsFinished;
    private final JobScheduler mJobScheduler;
    private final ProducerContext mProducerContext;
    private final ProducerListener mProducerListener;

    public ProgressiveDecoder(ProducerContext paramBoolean, boolean arg3)
    {
      super();
      final ProducerContext localProducerContext;
      this.mProducerContext = localProducerContext;
      this.mProducerListener = localProducerContext.getListener();
      this.mImageDecodeOptions = localProducerContext.getImageRequest().getImageDecodeOptions();
      this.mIsFinished = false;
      paramBoolean = new JobScheduler.JobRunnable()
      {
        public void run(EncodedImage paramAnonymousEncodedImage, int paramAnonymousInt)
        {
          if (paramAnonymousEncodedImage != null)
          {
            if ((DecodeProducer.this.mDownsampleEnabled) || (!BaseConsumer.statusHasFlag(paramAnonymousInt, 16)))
            {
              ImageRequest localImageRequest = localProducerContext.getImageRequest();
              if ((DecodeProducer.this.mDownsampleEnabledForNetwork) || (!UriUtil.isNetworkUri(localImageRequest.getSourceUri())))
                paramAnonymousEncodedImage.setSampleSize(DownsampleUtil.determineSampleSize(localImageRequest, paramAnonymousEncodedImage));
            }
            DecodeProducer.ProgressiveDecoder.this.doDecode(paramAnonymousEncodedImage, paramAnonymousInt);
          }
        }
      };
      this.mJobScheduler = new JobScheduler(DecodeProducer.this.mExecutor, paramBoolean, this.mImageDecodeOptions.minDecodeIntervalMs);
      final boolean bool;
      this.mProducerContext.addCallbacks(new BaseProducerContextCallbacks()
      {
        public void onCancellationRequested()
        {
          if (bool)
            DecodeProducer.ProgressiveDecoder.this.handleCancellation();
        }

        public void onIsIntermediateResultExpectedChanged()
        {
          if (DecodeProducer.ProgressiveDecoder.this.mProducerContext.isIntermediateResultExpected())
            DecodeProducer.ProgressiveDecoder.this.mJobScheduler.scheduleJob();
        }
      });
    }

    private void doDecode(EncodedImage paramEncodedImage, int paramInt)
    {
      int i = paramInt;
      if ((paramEncodedImage.getImageFormat() != DefaultImageFormats.JPEG) && (isNotLast(paramInt)))
        return;
      Object localObject1;
      Object localObject3;
      String str1;
      String str2;
      boolean bool1;
      boolean bool2;
      Object localObject4;
      if (!isFinished())
      {
        if (!EncodedImage.isValid(paramEncodedImage))
          return;
        localObject1 = paramEncodedImage.getImageFormat();
        if (localObject1 != null);
        for (localObject1 = ((ImageFormat)localObject1).getName(); ; localObject1 = "unknown")
          break;
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(paramEncodedImage.getWidth());
        ((StringBuilder)localObject3).append("x");
        ((StringBuilder)localObject3).append(paramEncodedImage.getHeight());
        str1 = ((StringBuilder)localObject3).toString();
        str2 = String.valueOf(paramEncodedImage.getSampleSize());
        bool1 = isLast(paramInt);
        if ((bool1) && (!statusHasFlag(i, 8)))
          paramInt = 1;
        else
          paramInt = 0;
        bool2 = statusHasFlag(i, 4);
        localObject3 = this.mProducerContext.getImageRequest().getResizeOptions();
        if (localObject3 != null)
        {
          localObject4 = new StringBuilder();
          ((StringBuilder)localObject4).append(((ResizeOptions)localObject3).width);
          ((StringBuilder)localObject4).append("x");
          ((StringBuilder)localObject4).append(((ResizeOptions)localObject3).height);
          localObject3 = ((StringBuilder)localObject4).toString();
        }
        else
        {
          localObject3 = "unknown";
        }
      }
      label565: label577: 
      while (true)
      {
        try
        {
          long l = this.mJobScheduler.getQueuedTime();
          String str3 = String.valueOf(this.mProducerContext.getImageRequest().getSourceUri());
          if ((paramInt == 0) && (!bool2))
          {
            j = getIntermediateImageEndOffset(paramEncodedImage);
            break label565;
          }
          int j = paramEncodedImage.getSize();
          break label565;
          localObject4 = getQualityInfo();
          break label577;
          localObject4 = ImmutableQualityInfo.FULL_QUALITY;
          break label577;
          this.mProducerListener.onProducerStart(this.mProducerContext.getId(), "DecodeProducer");
          try
          {
            ImageDecoder localImageDecoder = DecodeProducer.this.mImageDecoder;
            localObject5 = this.mImageDecodeOptions;
            try
            {
              localObject5 = localImageDecoder.decode(paramEncodedImage, j, (QualityInfo)localObject4, (ImageDecodeOptions)localObject5);
              try
              {
                j = paramEncodedImage.getSampleSize();
                paramInt = i;
                if (j != 1)
                  paramInt = i | 0x10;
                localObject1 = getExtraMap((CloseableImage)localObject5, l, (QualityInfo)localObject4, bool1, (String)localObject1, str1, (String)localObject3, str2);
                this.mProducerListener.onProducerFinishWithSuccess(this.mProducerContext.getId(), "DecodeProducer", (Map)localObject1);
                handleResult((CloseableImage)localObject5, paramInt);
                return;
              }
              catch (Exception localException1)
              {
              }
            }
            catch (DecodeException localDecodeException1)
            {
            }
          }
          catch (Exception localException2)
          {
            localObject5 = null;
          }
          catch (DecodeException localDecodeException2)
          {
          }
          Object localObject5 = localDecodeException2.getEncodedImage();
          FLog.w("ProgressiveDecoder", "%s, {uri: %s, firstEncodedBytes: %s, length: %d}", new Object[] { localDecodeException2.getMessage(), str3, ((EncodedImage)localObject5).getFirstBytesAsHexString(10), Integer.valueOf(((EncodedImage)localObject5).getSize()) });
          throw localDecodeException2;
          localObject1 = getExtraMap((CloseableImage)localObject5, l, (QualityInfo)localObject4, bool1, (String)localObject1, str1, (String)localObject3, str2);
          this.mProducerListener.onProducerFinishWithFailure(this.mProducerContext.getId(), "DecodeProducer", localDecodeException2, (Map)localObject1);
          handleError(localDecodeException2);
          return;
        }
        finally
        {
          EncodedImage.closeSafely(paramEncodedImage);
        }
        return;
        if (paramInt == 0)
          if (bool2);
      }
    }

    private Map<String, String> getExtraMap(@Nullable CloseableImage paramCloseableImage, long paramLong, QualityInfo paramQualityInfo, boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4)
    {
      if (!this.mProducerListener.requiresExtraMap(this.mProducerContext.getId()))
        return null;
      String str1 = String.valueOf(paramLong);
      paramQualityInfo = String.valueOf(paramQualityInfo.isOfGoodEnoughQuality());
      String str2 = String.valueOf(paramBoolean);
      if ((paramCloseableImage instanceof CloseableStaticBitmap))
      {
        paramCloseableImage = ((CloseableStaticBitmap)paramCloseableImage).getUnderlyingBitmap();
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramCloseableImage.getWidth());
        ((StringBuilder)localObject).append("x");
        ((StringBuilder)localObject).append(paramCloseableImage.getHeight());
        paramCloseableImage = ((StringBuilder)localObject).toString();
        localObject = new HashMap(8);
        ((Map)localObject).put("bitmapSize", paramCloseableImage);
        ((Map)localObject).put("queueTime", str1);
        ((Map)localObject).put("hasGoodQuality", paramQualityInfo);
        ((Map)localObject).put("isFinal", str2);
        ((Map)localObject).put("encodedImageSize", paramString2);
        ((Map)localObject).put("imageFormat", paramString1);
        ((Map)localObject).put("requestedImageSize", paramString3);
        ((Map)localObject).put("sampleSize", paramString4);
        return ImmutableMap.copyOf((Map)localObject);
      }
      paramCloseableImage = new HashMap(7);
      paramCloseableImage.put("queueTime", str1);
      paramCloseableImage.put("hasGoodQuality", paramQualityInfo);
      paramCloseableImage.put("isFinal", str2);
      paramCloseableImage.put("encodedImageSize", paramString2);
      paramCloseableImage.put("imageFormat", paramString1);
      paramCloseableImage.put("requestedImageSize", paramString3);
      paramCloseableImage.put("sampleSize", paramString4);
      return ImmutableMap.copyOf(paramCloseableImage);
    }

    private void handleCancellation()
    {
      maybeFinish(true);
      getConsumer().onCancellation();
    }

    private void handleError(Throwable paramThrowable)
    {
      maybeFinish(true);
      getConsumer().onFailure(paramThrowable);
    }

    private void handleResult(CloseableImage paramCloseableImage, int paramInt)
    {
      paramCloseableImage = CloseableReference.of(paramCloseableImage);
      try
      {
        maybeFinish(isLast(paramInt));
        getConsumer().onNewResult(paramCloseableImage, paramInt);
        return;
      }
      finally
      {
        CloseableReference.closeSafely(paramCloseableImage);
      }
    }

    private boolean isFinished()
    {
      try
      {
        boolean bool = this.mIsFinished;
        return bool;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    private void maybeFinish(boolean paramBoolean)
    {
      if (paramBoolean)
        try
        {
          if (!this.mIsFinished)
          {
            getConsumer().onProgressUpdate(1.0F);
            this.mIsFinished = true;
            this.mJobScheduler.clearJob();
            return;
          }
        }
        finally
        {
          break label48;
        }
      return;
      label48: throw localObject;
    }

    protected abstract int getIntermediateImageEndOffset(EncodedImage paramEncodedImage);

    protected abstract QualityInfo getQualityInfo();

    public void onCancellationImpl()
    {
      handleCancellation();
    }

    public void onFailureImpl(Throwable paramThrowable)
    {
      handleError(paramThrowable);
    }

    public void onNewResultImpl(EncodedImage paramEncodedImage, int paramInt)
    {
      boolean bool1 = isLast(paramInt);
      if ((bool1) && (!EncodedImage.isValid(paramEncodedImage)))
      {
        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
        return;
      }
      if (!updateDecodeJob(paramEncodedImage, paramInt))
        return;
      boolean bool2 = statusHasFlag(paramInt, 4);
      if ((bool1) || (bool2) || (this.mProducerContext.isIntermediateResultExpected()))
        this.mJobScheduler.scheduleJob();
    }

    protected void onProgressUpdateImpl(float paramFloat)
    {
      super.onProgressUpdateImpl(paramFloat * 0.99F);
    }

    protected boolean updateDecodeJob(EncodedImage paramEncodedImage, int paramInt)
    {
      return this.mJobScheduler.updateJob(paramEncodedImage, paramInt);
    }
  }
}