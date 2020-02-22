package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.content.res.Resources;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import java.util.Set;
import javax.annotation.Nullable;

public class PipelineDraweeControllerBuilderSupplier
  implements Supplier<PipelineDraweeControllerBuilder>
{
  private final Set<ControllerListener> mBoundControllerListeners;
  private final Context mContext;
  private final ImagePipeline mImagePipeline;
  private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

  public PipelineDraweeControllerBuilderSupplier(Context paramContext)
  {
    this(paramContext, null);
  }

  public PipelineDraweeControllerBuilderSupplier(Context paramContext, @Nullable DraweeConfig paramDraweeConfig)
  {
    this(paramContext, ImagePipelineFactory.getInstance(), paramDraweeConfig);
  }

  public PipelineDraweeControllerBuilderSupplier(Context paramContext, ImagePipelineFactory paramImagePipelineFactory, @Nullable DraweeConfig paramDraweeConfig)
  {
    this(paramContext, paramImagePipelineFactory, null, paramDraweeConfig);
  }

  public PipelineDraweeControllerBuilderSupplier(Context paramContext, ImagePipelineFactory paramImagePipelineFactory, Set<ControllerListener> paramSet, @Nullable DraweeConfig paramDraweeConfig)
  {
    this.mContext = paramContext;
    this.mImagePipeline = paramImagePipelineFactory.getImagePipeline();
    if ((paramDraweeConfig != null) && (paramDraweeConfig.getPipelineDraweeControllerFactory() != null))
      this.mPipelineDraweeControllerFactory = paramDraweeConfig.getPipelineDraweeControllerFactory();
    else
      this.mPipelineDraweeControllerFactory = new PipelineDraweeControllerFactory();
    PipelineDraweeControllerFactory localPipelineDraweeControllerFactory = this.mPipelineDraweeControllerFactory;
    Resources localResources = paramContext.getResources();
    DeferredReleaser localDeferredReleaser = DeferredReleaser.getInstance();
    DrawableFactory localDrawableFactory = paramImagePipelineFactory.getAnimatedDrawableFactory(paramContext);
    UiThreadImmediateExecutorService localUiThreadImmediateExecutorService = UiThreadImmediateExecutorService.getInstance();
    MemoryCache localMemoryCache = this.mImagePipeline.getBitmapMemoryCache();
    paramImagePipelineFactory = null;
    if (paramDraweeConfig != null)
      paramContext = paramDraweeConfig.getCustomDrawableFactories();
    else
      paramContext = null;
    if (paramDraweeConfig != null)
      paramImagePipelineFactory = paramDraweeConfig.getDebugOverlayEnabledSupplier();
    localPipelineDraweeControllerFactory.init(localResources, localDeferredReleaser, localDrawableFactory, localUiThreadImmediateExecutorService, localMemoryCache, paramContext, paramImagePipelineFactory);
    this.mBoundControllerListeners = paramSet;
  }

  public PipelineDraweeControllerBuilder get()
  {
    return new PipelineDraweeControllerBuilder(this.mContext, this.mPipelineDraweeControllerFactory, this.mImagePipeline, this.mBoundControllerListeners);
  }
}