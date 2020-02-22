package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder.CacheLevel;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Set;
import javax.annotation.Nullable;

public class PipelineDraweeControllerBuilder extends AbstractDraweeControllerBuilder<PipelineDraweeControllerBuilder, ImageRequest, CloseableReference<CloseableImage>, ImageInfo>
{

  @Nullable
  private ImmutableList<DrawableFactory> mCustomDrawableFactories;

  @Nullable
  private ImageOriginListener mImageOriginListener;

  @Nullable
  private ImagePerfDataListener mImagePerfDataListener;
  private final ImagePipeline mImagePipeline;
  private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

  public PipelineDraweeControllerBuilder(Context paramContext, PipelineDraweeControllerFactory paramPipelineDraweeControllerFactory, ImagePipeline paramImagePipeline, Set<ControllerListener> paramSet)
  {
    super(paramContext, paramSet);
    this.mImagePipeline = paramImagePipeline;
    this.mPipelineDraweeControllerFactory = paramPipelineDraweeControllerFactory;
  }

  public static ImageRequest.RequestLevel convertCacheLevelToRequestLevel(AbstractDraweeControllerBuilder.CacheLevel paramCacheLevel)
  {
    switch (1.$SwitchMap$com$facebook$drawee$controller$AbstractDraweeControllerBuilder$CacheLevel[paramCacheLevel.ordinal()])
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Cache level");
      localStringBuilder.append(paramCacheLevel);
      localStringBuilder.append("is not supported. ");
      throw new RuntimeException(localStringBuilder.toString());
    case 3:
      return ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE;
    case 2:
      return ImageRequest.RequestLevel.DISK_CACHE;
    case 1:
    }
    return ImageRequest.RequestLevel.FULL_FETCH;
  }

  private CacheKey getCacheKey()
  {
    ImageRequest localImageRequest = (ImageRequest)getImageRequest();
    CacheKeyFactory localCacheKeyFactory = this.mImagePipeline.getCacheKeyFactory();
    if ((localCacheKeyFactory != null) && (localImageRequest != null))
    {
      if (localImageRequest.getPostprocessor() != null)
        return localCacheKeyFactory.getPostprocessedBitmapCacheKey(localImageRequest, getCallerContext());
      return localCacheKeyFactory.getBitmapCacheKey(localImageRequest, getCallerContext());
    }
    return null;
  }

  protected DataSource<CloseableReference<CloseableImage>> getDataSourceForRequest(DraweeController paramDraweeController, String paramString, ImageRequest paramImageRequest, Object paramObject, AbstractDraweeControllerBuilder.CacheLevel paramCacheLevel)
  {
    return this.mImagePipeline.fetchDecodedImage(paramImageRequest, paramObject, convertCacheLevelToRequestLevel(paramCacheLevel), getRequestListener(paramDraweeController));
  }

  @Nullable
  protected RequestListener getRequestListener(DraweeController paramDraweeController)
  {
    if ((paramDraweeController instanceof PipelineDraweeController))
      return ((PipelineDraweeController)paramDraweeController).getRequestListener();
    return null;
  }

  protected PipelineDraweeController obtainController()
  {
    FrescoSystrace.beginSection("obtainController");
    try
    {
      Object localObject1 = getOldController();
      String str = generateUniqueControllerId();
      if ((localObject1 instanceof PipelineDraweeController))
        localObject1 = (PipelineDraweeController)localObject1;
      else
        localObject1 = this.mPipelineDraweeControllerFactory.newController();
      ((PipelineDraweeController)localObject1).initialize(obtainDataSourceSupplier((DraweeController)localObject1, str), str, getCacheKey(), getCallerContext(), this.mCustomDrawableFactories, this.mImageOriginListener);
      ((PipelineDraweeController)localObject1).initializePerformanceMonitoring(this.mImagePerfDataListener);
      return localObject1;
    }
    finally
    {
      FrescoSystrace.endSection();
    }
  }

  public PipelineDraweeControllerBuilder setCustomDrawableFactories(@Nullable ImmutableList<DrawableFactory> paramImmutableList)
  {
    this.mCustomDrawableFactories = paramImmutableList;
    return (PipelineDraweeControllerBuilder)getThis();
  }

  public PipelineDraweeControllerBuilder setCustomDrawableFactories(DrawableFactory[] paramArrayOfDrawableFactory)
  {
    Preconditions.checkNotNull(paramArrayOfDrawableFactory);
    return setCustomDrawableFactories(ImmutableList.of(paramArrayOfDrawableFactory));
  }

  public PipelineDraweeControllerBuilder setCustomDrawableFactory(DrawableFactory paramDrawableFactory)
  {
    Preconditions.checkNotNull(paramDrawableFactory);
    return setCustomDrawableFactories(ImmutableList.of(new DrawableFactory[] { paramDrawableFactory }));
  }

  public PipelineDraweeControllerBuilder setImageOriginListener(@Nullable ImageOriginListener paramImageOriginListener)
  {
    this.mImageOriginListener = paramImageOriginListener;
    return (PipelineDraweeControllerBuilder)getThis();
  }

  public PipelineDraweeControllerBuilder setPerfDataListener(@Nullable ImagePerfDataListener paramImagePerfDataListener)
  {
    this.mImagePerfDataListener = paramImagePerfDataListener;
    return (PipelineDraweeControllerBuilder)getThis();
  }

  public PipelineDraweeControllerBuilder setUri(@Nullable Uri paramUri)
  {
    if (paramUri == null)
      return (PipelineDraweeControllerBuilder)super.setImageRequest(null);
    return (PipelineDraweeControllerBuilder)super.setImageRequest(ImageRequestBuilder.newBuilderWithSource(paramUri).setRotationOptions(RotationOptions.autoRotateAtRenderTime()).build());
  }

  public PipelineDraweeControllerBuilder setUri(@Nullable String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
      return setUri(Uri.parse(paramString));
    return (PipelineDraweeControllerBuilder)super.setImageRequest(ImageRequest.fromUri(paramString));
  }
}