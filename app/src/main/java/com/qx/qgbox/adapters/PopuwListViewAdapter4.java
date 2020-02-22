package com.qx.qgbox.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.CurrentKeys;
import com.qx.qgbox.views.NumImageView;
import java.util.ArrayList;

public class PopuwListViewAdapter4 extends BaseAdapter
{
  private LayoutInflater inflater;
  private ArrayList<CurrentKeys> mCurrentKeysList = new ArrayList();

  public PopuwListViewAdapter4(Context paramContext, ArrayList<CurrentKeys> paramArrayList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.mCurrentKeysList = paramArrayList;
  }

  public int getCount()
  {
    return this.mCurrentKeysList.size();
  }

  public CurrentKeys getItem(int paramInt)
  {
    return (CurrentKeys)this.mCurrentKeysList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.inflater.inflate(R.layout.popuw_listview_item2, paramViewGroup, false);
      paramViewGroup = new ViewHoder();
      paramViewGroup.name = ((TextView)paramView.findViewById(R.id.tv_popuw_list_view_item));
      paramViewGroup.imageview = ((ImageView)paramView.findViewById(R.id.imageview));
      paramView.setTag(paramViewGroup);
    }
    else
    {
      paramViewGroup = (ViewHoder)paramView.getTag();
    }
    TextView localTextView = paramViewGroup.name;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(((CurrentKeys)this.mCurrentKeysList.get(paramInt)).getName());
    localTextView.setText(localStringBuilder.toString());
    paramViewGroup.imageview.setImageBitmap(((BitmapDrawable)((CurrentKeys)this.mCurrentKeysList.get(paramInt)).getmNumImageView().getDrawable()).getBitmap());
    return paramView;
  }

  public void refresh(ArrayList<CurrentKeys> paramArrayList)
  {
    this.mCurrentKeysList = paramArrayList;
    notifyDataSetChanged();
  }

  class ViewHoder
  {
    ImageView imageview;
    TextView name;

    ViewHoder()
    {
    }
  }
}
