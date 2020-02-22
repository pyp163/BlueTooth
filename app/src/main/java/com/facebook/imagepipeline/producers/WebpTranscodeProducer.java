package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.nativecode.WebpTranscoder;
import com.facebook.imagepipeline.nativecode.WebpTranscoderFactory;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class WebpTranscodeProducer
  implements Producer<EncodedImage>
{
  private static final int DEFAULT_JPEG_QUALITY = 80;
  public static final String PRODUCER_NAME = "WebpTranscodeProducer";
  private final Executor mExecutor;
  private final Producer<EncodedImage> mInputProducer;
  private final PooledByteBufferFactory mPooledByteBufferFactory;

  public WebpTranscodeProducer(Executor paramExecutor, PooledByteBufferFactory paramPooledByteBufferFactory, Producer<EncodedImage> paramProducer)
  {
    this.mExecutor = ((Executor)Preconditions.checkNotNull(paramExecutor));
    this.mPooledByteBufferFactory = ((PooledByteBufferFactory)Preconditions.checkNotNull(paramPooledByteBufferFactory));
    this.mInputProducer = ((Producer)Preconditions.checkNotNull(paramProducer));
  }

  private static void doTranscode(EncodedImage paramEncodedImage, PooledByteBufferOutputStream paramPooledByteBufferOutputStream)
    throws Exception
  {
    InputStream localInputStream = paramEncodedImage.getInputStream();
    ImageFormat localImageFormat = ImageFormatChecker.getImageFormat_WrapIOException(localInputStream);
    if ((localImageFormat != DefaultImageFormats.WEBP_SIMPLE) && (localImageFormat != DefaultImageFormats.WEBP_EXTENDED))
    {
      if ((localImageFormat != DefaultImageFormats.WEBP_LOSSLESS) && (localImageFormat != DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA))
        throw new IllegalArgumentException("Wrong image format");
      WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToPng(localInputStream, paramPooledByteBufferOutputStream);
      paramEncodedImage.setImageFormat(DefaultImageFormats.PNG);
      return;
    }
    WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToJpeg(localInputStream, paramPooledByteBufferOutputStream, 80);
    paramEncodedImage.setImageFormat(DefaultImageFormats.JPEG);
  }

  private static TriState shouldTranscode(EncodedImage paramEncodedImage)
  {
    Preconditions.checkNotNull(paramEncodedImage);
    paramEncodedImage = ImageFormatChecker.getImageFormat_WrapIOException(paramEncodedImage.getInputStream());
    if (DefaultImageFormats.isStaticWebpFormat(paramEncodedImage))
    {
      WebpTranscoder localWebpTranscoder = WebpTranscoderFactory.getWebpTranscoder();
      if (localWebpTranscoder == null)
        return TriState.NO;
      return TriState.valueOf(localWebpTranscoder.isWebpNativelySupported(paramEncodedImage) ^ true);
    }
    if (paramEncodedImage == ImageFormat.UNKNOWN)
      return TriState.UNSET;
    return TriState.NO;
  }

  private void transcodeLastResult(final EncodedImage paramEncodedImage, Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    Preconditions.checkNotNull(paramEncodedImage);
    paramEncodedImage = EncodedImage.cloneOrNull(paramEncodedImage);
    paramEncodedImage = new StatefulProducerRunnable(paramConsumer, paramProducerContext.getListener(), "WebpTranscodeProducer", paramProducerContext.getId())
    {
      protected void disposeResult(EncodedImage paramAnonymousEncodedImage)
      {
        EncodedImage.closeSafely(paramAnonymousEncodedImage);
      }

      protected EncodedImage getResult()
        throws Exception
      {
        PooledByteBufferOutputStream localPooledByteBufferOutputStream = WebpTranscodeProducer.this.mPooledByteBufferFactory.newOutputStream();
        try
        {
          WebpTranscodeProducer.doTranscode(paramEncodedImage, localPooledByteBufferOutputStream);
          CloseableReference localCloseableReference = CloseableReference.of(localPooledByteBufferOutputStream.toByteBuffer());
          try
          {
            EncodedImage localEncodedImage = new EncodedImage(localCloseableReference);
            localEncodedImage.copyMetaDataFrom(paramEncodedImage);
            CloseableReference.closeSafely(localCloseableReference);
            return localEncodedImage;
          }
          finally
          {
            CloseableReference.closeSafely(localCloseableReference);
          }
        }
        finally
        {
          localPooledByteBufferOutputStream.close();
        }
      }

      protected void onCancellation()
      {
        EncodedImage.closeSafely(paramEncodedImage);
        super.onCancellation();
      }

      protected void onFailure(Exception paramAnonymousException)
      {
        EncodedImage.closeSafely(paramEncodedImage);
        super.onFailure(paramAnonymousException);
      }

      protected void onSuccess(EncodedImage paramAnonymousEncodedImage)
      {
        EncodedImage.closeSafely(paramEncodedImage);
        super.onSuccess(paramAnonymousEncodedImage);
      }
    };
    this.mExecutor.execute(paramEncodedImage);
  }

  public void produceResults(Consumer<EncodedImage> paramConsumer, ProducerContext paramProducerContext)
  {
    this.mInputProducer.produceResults(new WebpTranscodeConsumer(paramConsumer, paramProducerContext), paramProducerContext);
  }

  private class WebpTranscodeConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
  {
    private final ProducerContext mContext;
    private TriState mShouldTranscodeWhenFinished;

    public WebpTranscodeConsumer(ProducerContext arg2)
    {
      super();
      Object localObject;
      this.mContext = localObject;
      this.mShouldTranscodeWhenFinished = TriState.UNSET;
    }

    protected void onNewResultImpl(@Nullable EncodedImage paramEncodedImage, int paramInt)
    {
      if ((this.mShouldTranscodeWhenFinished == TriState.UNSET) && (paramEncodedImage != null))
        this.mShouldTranscodeWhenFinished = WebpTranscodeProducer.shouldTranscode(paramEncodedImage);
      if (this.mShouldTranscodeWhenFinished == TriState.NO)
      {
        getConsumer().onNewResult(paramEncodedImage, paramInt);
        return;
      }
      if (isLast(paramInt))
      {
        if ((this.mShouldTranscodeWhenFinished == TriState.YES) && (paramEncodedImage != null))
        {
          WebpTranscodeProducer.this.transcodeLastResult(paramEncodedImage, getConsumer(), this.mContext);
          return;
        }
        getConsumer().onNewResult(paramEncodedImage, paramInt);
      }
    }
  }
}