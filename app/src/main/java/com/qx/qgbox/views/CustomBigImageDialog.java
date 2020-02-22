package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsListView.LayoutParams;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.qx.qgbox.adapters.CarImagesBrowseAdapter;
import com.qx.qgbox.entitys.ImageCarousel2;
import java.util.ArrayList;
import java.util.List;

public class CustomBigImageDialog extends Dialog
{
  private Context context;
  private ImageCarousel2 imageCarousel;
  private List<View> listViews = new ArrayList();
  private CarImagesBrowseAdapter mCarImagesBrowseAdapter;
  private ArrayList<String> urlList;
  private ViewPager view_pager;

  public CustomBigImageDialog(Context paramContext, ArrayList<String> paramArrayList)
  {
    super(paramContext);
    this.context = paramContext;
    this.urlList = paramArrayList;
  }

  private void imageStart()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < this.urlList.size())
    {
      SimpleDraweeView localSimpleDraweeView = new SimpleDraweeView(this.context);
      localSimpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(this.context.getResources()).setPlaceholderImage(ContextCompat.getDrawable(this.context, R.mipmap.ic_launcher), ScalingUtils.ScaleType.FIT_CENTER).build());
      localSimpleDraweeView.setLayoutParams(new AbsListView.LayoutParams(-1, 400));
      Object localObject = ImageRequestBuilder.newBuilderWithSource(Uri.parse((String)this.urlList.get(i))).setResizeOptions(new ResizeOptions(1280, 720)).build();
      localObject = (PipelineDraweeController)((PipelineDraweeControllerBuilder)((PipelineDraweeControllerBuilder)Fresco.newDraweeControllerBuilder().setImageRequest(localObject)).setOldController(localSimpleDraweeView.getController())).build();
      localSimpleDraweeView.setImageURI(Uri.parse((String)this.urlList.get(i)));
      localSimpleDraweeView.setId(new int[] { R.id.pager_image1, R.id.pager_image2, R.id.pager_image3, R.id.pager_image4, R.id.pager_image5 }[i]);
      localSimpleDraweeView.setTag(this.urlList.get(i));
      localSimpleDraweeView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          CustomBigImageDialog.this.dismiss();
        }
      });
      localArrayList.add(localSimpleDraweeView);
      i += 1;
    }
    this.imageCarousel = new ImageCarousel2(this.context, this.view_pager, 5000);
    this.imageCarousel.init(localArrayList).startAutoPlay();
    this.imageCarousel.start();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.custom_big_image_dialog, null);
    setContentView(paramBundle, new WindowManager.LayoutParams(-2, -2));
    getWindow().setBackgroundDrawableResource(17170445);
    this.view_pager = ((ViewPager)findViewById(R.id.view_pager));
    int i = 0;
    while (i < this.urlList.size())
    {
      ((SimpleDraweeView)LayoutInflater.from(this.context.getApplicationContext()).inflate(R.layout.view_pager_item, null).findViewById(R.id.simpledraweeview)).setImageURI(Uri.parse((String)this.urlList.get(i)));
      this.listViews.add(paramBundle);
      PhotoPagerAdapter localPhotoPagerAdapter = new PhotoPagerAdapter(this.listViews);
      this.view_pager.setAdapter(localPhotoPagerAdapter);
      this.view_pager.setCurrentItem(0);
      localPhotoPagerAdapter.notifyDataSetChanged();
      i += 1;
    }
  }

  public void show()
  {
    super.show();
  }

  class PhotoPagerAdapter extends PagerAdapter
  {
    private List<View> list;

    public PhotoPagerAdapter()
    {
      Object localObject;
      this.list = localObject;
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup.removeView((View)paramObject);
    }

    public int getCount()
    {
      if ((this.list != null) && (this.list.size() > 0))
        return this.list.size();
      return 0;
    }

    public int getItemPosition(Object paramObject)
    {
      return -2;
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      paramViewGroup.addView((View)this.list.get(paramInt));
      return this.list.get(paramInt);
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
  }
}
