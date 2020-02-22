package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Objects.ToStringHelper;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.datasource.DataSource;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.backends.pipeline.info.ForwardingImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImageOriginRequestListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.debug.DebugControllerOverlayDrawable;
import com.facebook.drawee.debug.listener.ImageLoadingTimeControllerListener;
import com.facebook.drawee.debug.listener.ImageLoadingTimeListener;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class PipelineDraweeController extends AbstractDraweeController<CloseableReference<CloseableImage>, ImageInfo>
{
  private static final Class<?> TAG = PipelineDraweeController.class;
  private CacheKey mCacheKey;

  @Nullable
  private ImmutableList<DrawableFactory> mCustomDrawableFactories;
  private Supplier<DataSource<CloseableReference<CloseableImage>>> mDataSourceSupplier;
  private final DrawableFactory mDefaultDrawableFactory;
  private boolean mDrawDebugOverlay;

  @Nullable
  private final ImmutableList<DrawableFactory> mGlobalDrawableFactories;

  @Nullable
  @GuardedBy("this")
  private ImageOriginListener mImageOriginListener;

  @Nullable
  private ImagePerfMonitor mImagePerfMonitor;

  @Nullable
  private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

  @Nullable
  @GuardedBy("this")
  private Set<RequestListener> mRequestListeners;
  private final Resources mResources;

  public PipelineDraweeController(Resources paramResources, DeferredReleaser paramDeferredReleaser, DrawableFactory paramDrawableFactory, Executor paramExecutor, @Nullable MemoryCache<CacheKey, CloseableImage> paramMemoryCache, @Nullable ImmutableList<DrawableFactory> paramImmutableList)
  {
    super(paramDeferredReleaser, paramExecutor, null, null);
    this.mResources = paramResources;
    this.mDefaultDrawableFactory = new DefaultDrawableFactory(paramResources, paramDrawableFactory);
    this.mGlobalDrawableFactories = paramImmutableList;
    this.mMemoryCache = paramMemoryCache;
  }

  private void init(Supplier<DataSource<CloseableReference<CloseableImage>>> paramSupplier)
  {
    this.mDataSourceSupplier = paramSupplier;
    maybeUpdateDebugOverlay(null);
  }

  private Drawable maybeCreateDrawableFromFactories(@Nullable ImmutableList<DrawableFactory> paramImmutableList, CloseableImage paramCloseableImage)
  {
    if (paramImmutableList == null)
      return null;
    paramImmutableList = paramImmutableList.iterator();
    while (paramImmutableList.hasNext())
    {
      Object localObject = (DrawableFactory)paramImmutableList.next();
      if (((DrawableFactory)localObject).supportsImageType(paramCloseableImage))
      {
        localObject = ((DrawableFactory)localObject).createDrawable(paramCloseableImage);
        if (localObject != null)
          return localObject;
      }
    }
    return null;
  }

  private void maybeUpdateDebugOverlay(@Nullable CloseableImage paramCloseableImage)
  {
    if (!this.mDrawDebugOverlay)
      return;
    Object localObject1;
    if (getControllerOverlay() == null)
    {
      localObject1 = new DebugControllerOverlayDrawable();
      addControllerListener(new ImageLoadingTimeControllerListener((ImageLoadingTimeListener)localObject1));
      setControllerOverlay((Drawable)localObject1);
    }
    if ((getControllerOverlay() instanceof DebugControllerOverlayDrawable))
    {
      DebugControllerOverlayDrawable localDebugControllerOverlayDrawable = (DebugControllerOverlayDrawable)getControllerOverlay();
      localDebugControllerOverlayDrawable.setControllerId(getId());
      Object localObject3 = getHierarchy();
      Object localObject2 = null;
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject3 = ScalingUtils.getActiveScaleTypeDrawable(((DraweeHierarchy)localObject3).getTopLevelDrawable());
        localObject1 = localObject2;
        if (localObject3 != null)
          localObject1 = ((ScaleTypeDrawable)localObject3).getScaleType();
      }
      localDebugControllerOverlayDrawable.setScaleType((ScalingUtils.ScaleType)localObject1);
      if (paramCloseableImage != null)
      {
        localDebugControllerOverlayDrawable.setDimensions(paramCloseableImage.getWidth(), paramCloseableImage.getHeight());
        localDebugControllerOverlayDrawable.setImageSize(paramCloseableImage.getSizeInBytes());
        return;
      }
      localDebugControllerOverlayDrawable.reset();
    }
  }

  public void addImageOriginListener(ImageOriginListener paramImageOriginListener)
  {
    try
    {
      if ((this.mImageOriginListener instanceof ForwardingImageOriginListener))
        ((ForwardingImageOriginListener)this.mImageOriginListener).addImageOriginListener(paramImageOriginListener);
      else if (this.mImageOriginListener != null)
        this.mImageOriginListener = new ForwardingImageOriginListener(new ImageOriginListener[] { this.mImageOriginListener, paramImageOriginListener });
      else
        this.mImageOriginListener = paramImageOriginListener;
      return;
    }
    finally
    {
    }
    throw paramImageOriginListener;
  }

  public void addRequestListener(RequestListener paramRequestListener)
  {
    try
    {
      if (this.mRequestListeners == null)
        this.mRequestListeners = new HashSet();
      this.mRequestListeners.add(paramRequestListener);
      return;
    }
    finally
    {
    }
    throw paramRequestListener;
  }

  protected void clearImageOriginListeners()
  {
    try
    {
      this.mImageOriginListener = null;
      return;
    }
    finally
    {
    }
  }

  protected Drawable createDrawable(CloseableReference<CloseableImage> paramCloseableReference)
  {
    Preconditions.checkState(CloseableReference.isValid(paramCloseableReference));
    paramCloseableReference = (CloseableImage)paramCloseableReference.get();
    maybeUpdateDebugOverlay(paramCloseableReference);
    Object localObject = maybeCreateDrawableFromFactories(this.mCustomDrawableFactories, paramCloseableReference);
    if (localObject != null)
      return localObject;
    localObject = maybeCreateDrawableFromFactories(this.mGlobalDrawableFactories, paramCloseableReference);
    if (localObject != null)
      return localObject;
    localObject = this.mDefaultDrawableFactory.createDrawable(paramCloseableReference);
    if (localObject != null)
      return localObject;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Unrecognized image class: ");
    ((StringBuilder)localObject).append(paramCloseableReference);
    throw new UnsupportedOperationException(((StringBuilder)localObject).toString());
  }

  protected CacheKey getCacheKey()
  {
    return this.mCacheKey;
  }

  protected CloseableReference<CloseableImage> getCachedImage()
  {
    if (this.mMemoryCache != null)
    {
      if (this.mCacheKey == null)
        return null;
      CloseableReference localCloseableReference = this.mMemoryCache.get(this.mCacheKey);
      if ((localCloseableReference != null) && (!((CloseableImage)localCloseableReference.get()).getQualityInfo().isOfFullQuality()))
      {
        localCloseableReference.close();
        return null;
      }
      return localCloseableReference;
    }
    return null;
  }

  protected DataSource<CloseableReference<CloseableImage>> getDataSource()
  {
    if (FLog.isLoggable(2))
      FLog.v(TAG, "controller %x: getDataSource", Integer.valueOf(System.identityHashCode(this)));
    return (DataSource)this.mDataSourceSupplier.get();
  }

  protected Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier()
  {
    return this.mDataSourceSupplier;
  }

  protected int getImageHash(@Nullable CloseableReference<CloseableImage> paramCloseableReference)
  {
    if (paramCloseableReference != null)
      return paramCloseableReference.getValueHash();
    return 0;
  }

  protected ImageInfo getImageInfo(CloseableReference<CloseableImage> paramCloseableReference)
  {
    Preconditions.checkState(CloseableReference.isValid(paramCloseableReference));
    return (ImageInfo)paramCloseableReference.get();
  }

  @Nullable
  public RequestListener getRequestListener()
  {
    ImageOriginRequestListener localImageOriginRequestListener = null;
    try
    {
      if (this.mImageOriginListener != null)
        localImageOriginRequestListener = new ImageOriginRequestListener(getId(), this.mImageOriginListener);
      if (this.mRequestListeners != null)
      {
        ForwardingRequestListener localForwardingRequestListener = new ForwardingRequestListener(this.mRequestListeners);
        if (localImageOriginRequestListener != null)
          localForwardingRequestListener.addRequestListener(localImageOriginRequestListener);
        return localForwardingRequestListener;
      }
      return localImageOriginRequestListener;
    }
    finally
    {
    }
  }

  protected Resources getResources()
  {
    return this.mResources;
  }

  public void initialize(Supplier<DataSource<CloseableReference<CloseableImage>>> paramSupplier, String paramString, CacheKey paramCacheKey, Object paramObject, @Nullable ImmutableList<DrawableFactory> paramImmutableList, @Nullable ImageOriginListener paramImageOriginListener)
  {
    super.initialize(paramString, paramObject);
    init(paramSupplier);
    this.mCacheKey = paramCacheKey;
    setCustomDrawableFactories(paramImmutableList);
    clearImageOriginListeners();
    addImageOriginListener(paramImageOriginListener);
  }

  protected void initializePerformanceMonitoring(@Nullable ImagePerfDataListener paramImagePerfDataListener)
  {
    try
    {
      if (this.mImagePerfMonitor != null)
        this.mImagePerfMonitor.reset();
      if (paramImagePerfDataListener != null)
      {
        if (this.mImagePerfMonitor == null)
          this.mImagePerfMonitor = new ImagePerfMonitor(RealtimeSinceBootClock.get(), this);
        this.mImagePerfMonitor.addImagePerfDataListener(paramImagePerfDataListener);
        this.mImagePerfMonitor.setEnabled(true);
      }
      return;
    }
    finally
    {
    }
    throw paramImagePerfDataListener;
  }

  public boolean isSameImageRequest(@Nullable DraweeController paramDraweeController)
  {
    if ((this.mCacheKey != null) && ((paramDraweeController instanceof PipelineDraweeController)))
      return Objects.equal(this.mCacheKey, ((PipelineDraweeController)paramDraweeController).getCacheKey());
    return false;
  }

  protected void onImageLoadedFromCacheImmediately(String paramString, CloseableReference<CloseableImage> paramCloseableReference)
  {
    super.onImageLoadedFromCacheImmediately(paramString, paramCloseableReference);
    try
    {
      if (this.mImageOriginListener != null)
        this.mImageOriginListener.onImageLoaded(paramString, 3, true);
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  protected void releaseDrawable(@Nullable Drawable paramDrawable)
  {
    if ((paramDrawable instanceof DrawableWithCaches))
      ((DrawableWithCaches)paramDrawable).dropCaches();
  }

  protected void releaseImage(@Nullable CloseableReference<CloseableImage> paramCloseableReference)
  {
    CloseableReference.closeSafely(paramCloseableReference);
  }

  public void removeImageOriginListener(ImageOriginListener paramImageOriginListener)
  {
    try
    {
      if ((this.mImageOriginListener instanceof ForwardingImageOriginListener))
        ((ForwardingImageOriginListener)this.mImageOriginListener).removeImageOriginListener(paramImageOriginListener);
      else if (this.mImageOriginListener != null)
        this.mImageOriginListener = new ForwardingImageOriginListener(new ImageOriginListener[] { this.mImageOriginListener, paramImageOriginListener });
      else
        this.mImageOriginListener = paramImageOriginListener;
      return;
    }
    finally
    {
    }
    throw paramImageOriginListener;
  }

  public void removeRequestListener(RequestListener paramRequestListener)
  {
    try
    {
      Set localSet = this.mRequestListeners;
      if (localSet == null)
        return;
      this.mRequestListeners.remove(paramRequestListener);
      return;
    }
    finally
    {
    }
    throw paramRequestListener;
  }

  public void setCustomDrawableFactories(@Nullable ImmutableList<DrawableFactory> paramImmutableList)
  {
    this.mCustomDrawableFactories = paramImmutableList;
  }

  public void setDrawDebugOverlay(boolean paramBoolean)
  {
    this.mDrawDebugOverlay = paramBoolean;
  }

  public void setHierarchy(@Nullable DraweeHierarchy paramDraweeHierarchy)
  {
    super.setHierarchy(paramDraweeHierarchy);
    maybeUpdateDebugOverlay(null);
  }

  public String toString()
  {
    return Objects.toStringHelper(this).add("super", super.toString()).add("dataSourceSupplier", this.mDataSourceSupplier).toString();
  }
}