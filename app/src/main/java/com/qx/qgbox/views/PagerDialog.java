package com.qx.qgbox.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qx.qgbox.adapters.NoticePagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class PagerDialog extends Dialog
{
  private Context context;
  private ArrayList<String> urlList;
  private ViewPager viewPager = null;

  public PagerDialog(@NonNull Context paramContext, ArrayList<String> paramArrayList)
  {
    super(paramContext);
    this.context = paramContext;
    this.urlList = paramArrayList;
  }

  private PagerAdapter createAdapter()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < this.urlList.size())
    {
      int j = (int)TypedValue.applyDimension(1, 7.0F, getContext().getResources().getDisplayMetrics());
      TypedValue.applyDimension(1, 5.0F, getContext().getResources().getDisplayMetrics());
      new LinearLayout.LayoutParams(j, j);
      View localView = LayoutInflater.from(getContext()).inflate(R.layout.view_pager_item, null);
      SimpleDraweeView localSimpleDraweeView = (SimpleDraweeView)localView.findViewById(R.id.simpledraweeview);
      localSimpleDraweeView.setImageURI(Uri.parse((String)this.urlList.get(i)));
      localSimpleDraweeView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PagerDialog.this.dismiss();
        }
      });
      localArrayList.add(localView);
      i += 1;
    }
    return new NoticePagerAdapter(localArrayList);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.custom_big_image_dialog, null), new WindowManager.LayoutParams(-2, -2));
    getWindow().setBackgroundDrawableResource(17170445);
    this.viewPager = ((ViewPager)findViewById(R.id.view_pager));
    this.viewPager.setAdapter(createAdapter());
    this.viewPager.setOffscreenPageLimit(this.urlList.size());
  }

  public void show()
  {
    if (this.urlList.size() > 0)
      super.show();
  }
}
