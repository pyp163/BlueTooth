package com.qx.qgbox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.SunyesMaxGamePreset;
import java.util.ArrayList;

public class PopuwListViewAdapter3 extends BaseAdapter
{
  private LayoutInflater inflater;
  private ArrayList<SunyesMaxGamePreset> mSunyesMaxGamePresetList = new ArrayList();

  public PopuwListViewAdapter3(Context paramContext, ArrayList<SunyesMaxGamePreset> paramArrayList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.mSunyesMaxGamePresetList = paramArrayList;
  }

  public int getCount()
  {
    return this.mSunyesMaxGamePresetList.size();
  }

  public SunyesMaxGamePreset getItem(int paramInt)
  {
    return (SunyesMaxGamePreset)this.mSunyesMaxGamePresetList.get(paramInt);
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
    String str = ((SunyesMaxGamePreset)this.mSunyesMaxGamePresetList.get(paramInt)).getAppName();
    paramViewGroup = paramViewGroup.name;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("");
    paramViewGroup.setText(localStringBuilder.toString());
    return paramView;
  }

  public void refresh(ArrayList<SunyesMaxGamePreset> paramArrayList)
  {
    this.mSunyesMaxGamePresetList = paramArrayList;
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
