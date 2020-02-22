package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class PostprocessorProducer
  implements Producer<CloseableReference<CloseableImage>>
{
  public static final String NAME = "PostprocessorProducer";

  @VisibleForTesting
  static final String POSTPROCESSOR = "Postprocessor";
  private final PlatformBitmapFactory mBitmapFactory;
  private final Executor mExecutor;
  private final Producer<CloseableReference<CloseableImage>> mInputProducer;

  public PostprocessorProducer(Producer<CloseableReference<CloseableImage>> paramProducer, PlatformBitmapFactory paramPlatformBitmapFactory, Executor paramExecutor)
  {
    this.mInputProducer = ((Producer)Preconditions.checkNotNull(paramProducer));
    this.mBitmapFactory = paramPlatformBitmapFactory;
    this.mExecutor = ((Executor)Preconditions.checkNotNull(paramExecutor));
  }

  public void produceResults(Consumer<CloseableReference<CloseableImage>> paramConsumer, ProducerContext paramProducerContext)
  {
    ProducerListener localProducerListener = paramProducerContext.getListener();
    Postprocessor localPostprocessor = paramProducerContext.getImageRequest().getPostprocessor();
    paramConsumer = new PostprocessorConsumer(paramConsumer, localProducerListener, paramProducerContext.getId(), localPostprocessor, paramProducerContext);
    if ((localPostprocessor instanceof RepeatedPostprocessor))
      paramConsumer = new RepeatedPostprocessorConsumer(paramConsumer, (RepeatedPostprocessor)localPostprocessor, paramProducerContext, null);
    else
      paramConsumer = new SingleUsePostprocessorConsumer(paramConsumer, null);
    this.mInputProducer.produceResults(paramConsumer, paramProducerContext);
  }

  private class PostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>
  {

    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsClosed;

    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsDirty = false;

    @GuardedBy("PostprocessorConsumer.this")
    private boolean mIsPostProcessingRunning = false;
    private final ProducerListener mListener;
    private final Postprocessor mPostprocessor;
    private final String mRequestId;

    @Nullable
    @GuardedBy("PostprocessorConsumer.this")
    private CloseableReference<CloseableImage> mSourceImageRef = null;

    @GuardedBy("PostprocessorConsumer.this")
    private int mStatus = 0;

    public PostprocessorConsumer(ProducerListener paramString, String paramPostprocessor, Postprocessor paramProducerContext, ProducerContext arg5)
    {
      super();
      this.mListener = paramPostprocessor;
      this.mRequestId = paramProducerContext;
      Object localObject1;
      this.mPostprocessor = localObject1;
      Object localObject2;
      localObject2.addCallbacks(new BaseProducerContextCallbacks()
      {
        public void onCancellationRequested()
        {
          PostprocessorProducer.PostprocessorConsumer.this.maybeNotifyOnCancellation();
        }
      });
    }

    private void clearRunningAndStartIfDirty()
    {
      try
      {
        this.mIsPostProcessingRunning = false;
        boolean bool = setRunningIfDirtyAndNotRunning();
        if (bool)
          submitPostprocessing();
        return;
      }
      finally
      {
      }
    }

    private boolean close()
    {
      try
      {
        if (this.mIsClosed)
          return false;
        CloseableReference localCloseableReference = this.mSourceImageRef;
        this.mSourceImageRef = null;
        this.mIsClosed = true;
        CloseableReference.closeSafely(localCloseableReference);
        return true;
      }
      finally
      {
      }
    }

    // ERROR //
    private void doPostprocessing(CloseableReference<CloseableImage> paramCloseableReference, int paramInt)
    {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic 112	com/facebook/common/references/CloseableReference:isValid	(Lcom/facebook/common/references/CloseableReference;)Z
      //   4: invokestatic 118	com/facebook/common/internal/Preconditions:checkArgument	(Z)V
      //   7: aload_0
      //   8: aload_1
      //   9: invokevirtual 122	com/facebook/common/references/CloseableReference:get	()Ljava/lang/Object;
      //   12: checkcast 124	com/facebook/imagepipeline/image/CloseableImage
      //   15: invokespecial 128	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:shouldPostprocess	(Lcom/facebook/imagepipeline/image/CloseableImage;)Z
      //   18: ifne +10 -> 28
      //   21: aload_0
      //   22: aload_1
      //   23: iload_2
      //   24: invokespecial 131	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:maybeNotifyOnNewResult	(Lcom/facebook/common/references/CloseableReference;I)V
      //   27: return
      //   28: aload_0
      //   29: getfield 50	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mListener	Lcom/facebook/imagepipeline/producers/ProducerListener;
      //   32: aload_0
      //   33: getfield 52	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mRequestId	Ljava/lang/String;
      //   36: ldc 133
      //   38: invokeinterface 139 3 0
      //   43: aconst_null
      //   44: astore_3
      //   45: aload_0
      //   46: aload_1
      //   47: invokevirtual 122	com/facebook/common/references/CloseableReference:get	()Ljava/lang/Object;
      //   50: checkcast 124	com/facebook/imagepipeline/image/CloseableImage
      //   53: invokespecial 143	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:postprocessInternal	(Lcom/facebook/imagepipeline/image/CloseableImage;)Lcom/facebook/common/references/CloseableReference;
      //   56: astore_1
      //   57: aload_0
      //   58: getfield 50	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mListener	Lcom/facebook/imagepipeline/producers/ProducerListener;
      //   61: aload_0
      //   62: getfield 52	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mRequestId	Ljava/lang/String;
      //   65: ldc 133
      //   67: aload_0
      //   68: aload_0
      //   69: getfield 50	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mListener	Lcom/facebook/imagepipeline/producers/ProducerListener;
      //   72: aload_0
      //   73: getfield 52	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mRequestId	Ljava/lang/String;
      //   76: aload_0
      //   77: getfield 54	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mPostprocessor	Lcom/facebook/imagepipeline/request/Postprocessor;
      //   80: invokespecial 147	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:getExtraMap	(Lcom/facebook/imagepipeline/producers/ProducerListener;Ljava/lang/String;Lcom/facebook/imagepipeline/request/Postprocessor;)Ljava/util/Map;
      //   83: invokeinterface 151 4 0
      //   88: aload_0
      //   89: aload_1
      //   90: iload_2
      //   91: invokespecial 131	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:maybeNotifyOnNewResult	(Lcom/facebook/common/references/CloseableReference;I)V
      //   94: aload_1
      //   95: invokestatic 106	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
      //   98: return
      //   99: astore_3
      //   100: goto +56 -> 156
      //   103: astore 4
      //   105: aload_3
      //   106: astore_1
      //   107: aload 4
      //   109: astore_3
      //   110: goto +46 -> 156
      //   113: astore_1
      //   114: aload_0
      //   115: getfield 50	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mListener	Lcom/facebook/imagepipeline/producers/ProducerListener;
      //   118: aload_0
      //   119: getfield 52	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mRequestId	Ljava/lang/String;
      //   122: ldc 133
      //   124: aload_1
      //   125: aload_0
      //   126: aload_0
      //   127: getfield 50	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mListener	Lcom/facebook/imagepipeline/producers/ProducerListener;
      //   130: aload_0
      //   131: getfield 52	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mRequestId	Ljava/lang/String;
      //   134: aload_0
      //   135: getfield 54	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:mPostprocessor	Lcom/facebook/imagepipeline/request/Postprocessor;
      //   138: invokespecial 147	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:getExtraMap	(Lcom/facebook/imagepipeline/producers/ProducerListener;Ljava/lang/String;Lcom/facebook/imagepipeline/request/Postprocessor;)Ljava/util/Map;
      //   141: invokeinterface 155 5 0
      //   146: aload_0
      //   147: aload_1
      //   148: invokespecial 159	com/facebook/imagepipeline/producers/PostprocessorProducer$PostprocessorConsumer:maybeNotifyOnFailure	(Ljava/lang/Throwable;)V
      //   151: aconst_null
      //   152: invokestatic 106	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
      //   155: return
      //   156: aload_1
      //   157: invokestatic 106	com/facebook/common/references/CloseableReference:closeSafely	(Lcom/facebook/common/references/CloseableReference;)V
      //   160: aload_3
      //   161: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   57	94	99	finally
      //   45	57	103	finally
      //   114	151	103	finally
      //   45	57	113	java/lang/Exception
    }

    private Map<String, String> getExtraMap(ProducerListener paramProducerListener, String paramString, Postprocessor paramPostprocessor)
    {
      if (!paramProducerListener.requiresExtraMap(paramString))
        return null;
      return ImmutableMap.of("Postprocessor", paramPostprocessor.getName());
    }

    private boolean isClosed()
    {
      try
      {
        boolean bool = this.mIsClosed;
        return bool;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    private void maybeNotifyOnCancellation()
    {
      if (close())
        getConsumer().onCancellation();
    }

    private void maybeNotifyOnFailure(Throwable paramThrowable)
    {
      if (close())
        getConsumer().onFailure(paramThrowable);
    }

    private void maybeNotifyOnNewResult(CloseableReference<CloseableImage> paramCloseableReference, int paramInt)
    {
      boolean bool = isLast(paramInt);
      if (((!bool) && (!isClosed())) || ((bool) && (close())))
        getConsumer().onNewResult(paramCloseableReference, paramInt);
    }

    private CloseableReference<CloseableImage> postprocessInternal(CloseableImage paramCloseableImage)
    {
      CloseableStaticBitmap localCloseableStaticBitmap = (CloseableStaticBitmap)paramCloseableImage;
      Object localObject = localCloseableStaticBitmap.getUnderlyingBitmap();
      localObject = this.mPostprocessor.process((Bitmap)localObject, PostprocessorProducer.this.mBitmapFactory);
      int i = localCloseableStaticBitmap.getRotationAngle();
      int j = localCloseableStaticBitmap.getExifOrientation();
      try
      {
        paramCloseableImage = CloseableReference.of(new CloseableStaticBitmap((CloseableReference)localObject, paramCloseableImage.getQualityInfo(), i, j));
        return paramCloseableImage;
      }
      finally
      {
        CloseableReference.closeSafely((CloseableReference)localObject);
      }
      throw paramCloseableImage;
    }

    private boolean setRunningIfDirtyAndNotRunning()
    {
      try
      {
        if ((!this.mIsClosed) && (this.mIsDirty) && (!this.mIsPostProcessingRunning) && (CloseableReference.isValid(this.mSourceImageRef)))
        {
          this.mIsPostProcessingRunning = true;
          return true;
        }
        return false;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    private boolean shouldPostprocess(CloseableImage paramCloseableImage)
    {
      return paramCloseableImage instanceof CloseableStaticBitmap;
    }

    private void submitPostprocessing()
    {
      PostprocessorProducer.this.mExecutor.execute(new Runnable()
      {
        public void run()
        {
          synchronized (PostprocessorProducer.PostprocessorConsumer.this)
          {
            CloseableReference localCloseableReference = PostprocessorProducer.PostprocessorConsumer.this.mSourceImageRef;
            int i = PostprocessorProducer.PostprocessorConsumer.this.mStatus;
            PostprocessorProducer.PostprocessorConsumer.access$302(PostprocessorProducer.PostprocessorConsumer.this, null);
            PostprocessorProducer.PostprocessorConsumer.access$502(PostprocessorProducer.PostprocessorConsumer.this, false);
            if (CloseableReference.isValid(localCloseableReference));
            try
            {
              PostprocessorProducer.PostprocessorConsumer.this.doPostprocessing(localCloseableReference, i);
              CloseableReference.closeSafely(localCloseableReference);
            }
            finally
            {
              CloseableReference.closeSafely(localCloseableReference);
            }
            return;
          }
        }
      });
    }

    private void updateSourceImageRef(@Nullable CloseableReference<CloseableImage> paramCloseableReference, int paramInt)
    {
      try
      {
        if (this.mIsClosed)
          return;
        CloseableReference localCloseableReference = this.mSourceImageRef;
        this.mSourceImageRef = CloseableReference.cloneOrNull(paramCloseableReference);
        this.mStatus = paramInt;
        this.mIsDirty = true;
        boolean bool = setRunningIfDirtyAndNotRunning();
        CloseableReference.closeSafely(localCloseableReference);
        if (bool)
          submitPostprocessing();
        return;
      }
      finally
      {
      }
      throw paramCloseableReference;
    }

    protected void onCancellationImpl()
    {
      maybeNotifyOnCancellation();
    }

    protected void onFailureImpl(Throwable paramThrowable)
    {
      maybeNotifyOnFailure(paramThrowable);
    }

    protected void onNewResultImpl(CloseableReference<CloseableImage> paramCloseableReference, int paramInt)
    {
      if (!CloseableReference.isValid(paramCloseableReference))
      {
        if (isLast(paramInt))
          maybeNotifyOnNewResult(null, paramInt);
        return;
      }
      updateSourceImageRef(paramCloseableReference, paramInt);
    }
  }

  class RepeatedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>
    implements RepeatedPostprocessorRunner
  {

    @GuardedBy("RepeatedPostprocessorConsumer.this")
    private boolean mIsClosed = false;

    @Nullable
    @GuardedBy("RepeatedPostprocessorConsumer.this")
    private CloseableReference<CloseableImage> mSourceImageRef = null;

    private RepeatedPostprocessorConsumer(PostprocessorProducer.PostprocessorConsumer paramRepeatedPostprocessor, RepeatedPostprocessor paramProducerContext, ProducerContext arg4)
    {
      super();
      paramProducerContext.setCallback(this);
      Object localObject;
      localObject.addCallbacks(new BaseProducerContextCallbacks()
      {
        public void onCancellationRequested()
        {
          if (PostprocessorProducer.RepeatedPostprocessorConsumer.this.close())
            PostprocessorProducer.RepeatedPostprocessorConsumer.this.getConsumer().onCancellation();
        }
      });
    }

    private boolean close()
    {
      try
      {
        if (this.mIsClosed)
          return false;
        CloseableReference localCloseableReference = this.mSourceImageRef;
        this.mSourceImageRef = null;
        this.mIsClosed = true;
        CloseableReference.closeSafely(localCloseableReference);
        return true;
      }
      finally
      {
      }
    }

    private void setSourceImageRef(CloseableReference<CloseableImage> paramCloseableReference)
    {
      try
      {
        if (this.mIsClosed)
          return;
        CloseableReference localCloseableReference = this.mSourceImageRef;
        this.mSourceImageRef = CloseableReference.cloneOrNull(paramCloseableReference);
        CloseableReference.closeSafely(localCloseableReference);
        return;
      }
      finally
      {
      }
      throw paramCloseableReference;
    }

    private void updateInternal()
    {
      try
      {
        if (this.mIsClosed)
          return;
        CloseableReference localCloseableReference = CloseableReference.cloneOrNull(this.mSourceImageRef);
        try
        {
          getConsumer().onNewResult(localCloseableReference, 0);
          return;
        }
        finally
        {
          CloseableReference.closeSafely(localCloseableReference);
        }
      }
      finally
      {
      }
    }

    protected void onCancellationImpl()
    {
      if (close())
        getConsumer().onCancellation();
    }

    protected void onFailureImpl(Throwable paramThrowable)
    {
      if (close())
        getConsumer().onFailure(paramThrowable);
    }

    protected void onNewResultImpl(CloseableReference<CloseableImage> paramCloseableReference, int paramInt)
    {
      if (isNotLast(paramInt))
        return;
      setSourceImageRef(paramCloseableReference);
      updateInternal();
    }

    public void update()
    {
      try
      {
        updateInternal();
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
  }

  class SingleUsePostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>
  {
    private SingleUsePostprocessorConsumer(PostprocessorProducer.PostprocessorConsumer arg2)
    {
      super();
    }

    protected void onNewResultImpl(CloseableReference<CloseableImage> paramCloseableReference, int paramInt)
    {
      if (isNotLast(paramInt))
        return;
      getConsumer().onNewResult(paramCloseableReference, paramInt);
    }
  }
}