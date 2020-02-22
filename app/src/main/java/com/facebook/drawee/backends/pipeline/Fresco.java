package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.soloader.SoLoader;
import java.io.IOException;
import javax.annotation.Nullable;

public class Fresco
{
  private static final Class<?> TAG = Fresco.class;
  private static PipelineDraweeControllerBuilderSupplier sDraweeControllerBuilderSupplier;
  private static volatile boolean sIsInitialized = false;

  public static PipelineDraweeControllerBuilderSupplier getDraweeControllerBuilderSupplier()
  {
    return sDraweeControllerBuilderSupplier;
  }

  public static ImagePipeline getImagePipeline()
  {
    return getImagePipelineFactory().getImagePipeline();
  }

  public static ImagePipelineFactory getImagePipelineFactory()
  {
    return ImagePipelineFactory.getInstance();
  }

  public static boolean hasBeenInitialized()
  {
    return sIsInitialized;
  }

  public static void initialize(Context paramContext)
  {
    initialize(paramContext, null, null);
  }

  public static void initialize(Context paramContext, @Nullable ImagePipelineConfig paramImagePipelineConfig)
  {
    initialize(paramContext, paramImagePipelineConfig, null);
  }

  public static void initialize(Context paramContext, @Nullable ImagePipelineConfig paramImagePipelineConfig, @Nullable DraweeConfig paramDraweeConfig)
  {
    if (sIsInitialized)
      FLog.w(TAG, "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!");
    else
      sIsInitialized = true;
    try
    {
      SoLoader.init(paramContext, 0);
      paramContext = paramContext.getApplicationContext();
      if (paramImagePipelineConfig == null)
        ImagePipelineFactory.initialize(paramContext);
      else
        ImagePipelineFactory.initialize(paramImagePipelineConfig);
      initializeDrawee(paramContext, paramDraweeConfig);
      return;
    }
    catch (IOException paramContext)
    {
    }
    throw new RuntimeException("Could not initialize SoLoader", paramContext);
  }

  private static void initializeDrawee(Context paramContext, @Nullable DraweeConfig paramDraweeConfig)
  {
    sDraweeControllerBuilderSupplier = new PipelineDraweeControllerBuilderSupplier(paramContext, paramDraweeConfig);
    SimpleDraweeView.initialize(sDraweeControllerBuilderSupplier);
  }

  public static PipelineDraweeControllerBuilder newDraweeControllerBuilder()
  {
    return sDraweeControllerBuilderSupplier.get();
  }

  public static void shutDown()
  {
    sDraweeControllerBuilderSupplier = null;
    SimpleDraweeView.shutDown();
    ImagePipelineFactory.shutDown();
  }
}