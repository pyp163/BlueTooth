package com.facebook.drawee.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.R.styleable;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.SimpleDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

public class SimpleDraweeView extends GenericDraweeView
{
  private static Supplier<? extends AbstractDraweeControllerBuilder> sDraweecontrollerbuildersupplier;
  private AbstractDraweeControllerBuilder mControllerBuilder;

  public SimpleDraweeView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }

  public SimpleDraweeView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public SimpleDraweeView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }

  @TargetApi(21)
  public SimpleDraweeView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    init(paramContext, paramAttributeSet);
  }

  public SimpleDraweeView(Context paramContext, GenericDraweeHierarchy paramGenericDraweeHierarchy)
  {
    super(paramContext, paramGenericDraweeHierarchy);
    init(paramContext, null);
  }

  private void init(Context paramContext, @Nullable AttributeSet paramAttributeSet)
  {
    if (isInEditMode())
      return;
    Preconditions.checkNotNull(sDraweecontrollerbuildersupplier, "SimpleDraweeView was not initialized!");
    this.mControllerBuilder = ((AbstractDraweeControllerBuilder)sDraweecontrollerbuildersupplier.get());
    if (paramAttributeSet != null)
    {
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SimpleDraweeView);
      try
      {
        if (paramContext.hasValue(R.styleable.SimpleDraweeView_actualImageUri))
        {
          setImageURI(Uri.parse(paramContext.getString(R.styleable.SimpleDraweeView_actualImageUri)), null);
        }
        else if (paramContext.hasValue(R.styleable.SimpleDraweeView_actualImageResource))
        {
          int i = paramContext.getResourceId(R.styleable.SimpleDraweeView_actualImageResource, -1);
          if (i != -1)
            setActualImageResource(i);
        }
        return;
      }
      finally
      {
        paramContext.recycle();
      }
    }
  }

  public static void initialize(Supplier<? extends AbstractDraweeControllerBuilder> paramSupplier)
  {
    sDraweecontrollerbuildersupplier = paramSupplier;
  }

  public static void shutDown()
  {
    sDraweecontrollerbuildersupplier = null;
  }

  protected AbstractDraweeControllerBuilder getControllerBuilder()
  {
    return this.mControllerBuilder;
  }

  public void setActualImageResource(@DrawableRes int paramInt)
  {
    setActualImageResource(paramInt, null);
  }

  public void setActualImageResource(@DrawableRes int paramInt, @Nullable Object paramObject)
  {
    setImageURI(UriUtil.getUriForResourceId(paramInt), paramObject);
  }

  public void setImageRequest(ImageRequest paramImageRequest)
  {
    setController(this.mControllerBuilder.setImageRequest(paramImageRequest).setOldController(getController()).build());
  }

  public void setImageResource(int paramInt)
  {
    super.setImageResource(paramInt);
  }

  public void setImageURI(Uri paramUri)
  {
    setImageURI(paramUri, null);
  }

  public void setImageURI(Uri paramUri, @Nullable Object paramObject)
  {
    setController(this.mControllerBuilder.setCallerContext(paramObject).setUri(paramUri).setOldController(getController()).build());
  }

  public void setImageURI(@Nullable String paramString)
  {
    setImageURI(paramString, null);
  }

  public void setImageURI(@Nullable String paramString, @Nullable Object paramObject)
  {
    if (paramString != null)
      paramString = Uri.parse(paramString);
    else
      paramString = null;
    setImageURI(paramString, paramObject);
  }
}