package com.qx.qgbox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qx.qgbox.R;

import java.util.ArrayList;

public class PopuwListViewAdapter extends BaseAdapter
{
  private LayoutInflater inflater;
  private ArrayList<String> mName = new ArrayList();

  public PopuwListViewAdapter(Context paramContext, ArrayList<String> paramArrayList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.mName = paramArrayList;
  }

  public int getCount()
  {
    return this.mName.size();
  }

  public String getItem(int paramInt)
  {
    return (String)this.mName.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.inflater.inflate(R.layout.popuw_listview_item, paramViewGroup, false);
      paramViewGroup = new ViewHoder();
      paramViewGroup.name = ((TextView)paramView.findViewById(R.id.tv_popuw_list_view_item));
      paramView.setTag(paramViewGroup);
    }
    else
    {
      paramViewGroup = (ViewHoder)paramView.getTag();
    }
    String str = (String)this.mName.get(paramInt);
    paramViewGroup = paramViewGroup.name;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("");
    paramViewGroup.setText(localStringBuilder.toString());
    return paramView;
  }

  public void refresh(ArrayList<String> paramArrayList)
  {
    this.mName = paramArrayList;
    notifyDataSetChanged();
  }

  class ViewHoder
  {
    TextView name;

    ViewHoder()
    {
    }
  }
}
