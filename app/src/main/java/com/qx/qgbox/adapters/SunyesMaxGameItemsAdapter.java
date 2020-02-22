package com.qx.qgbox.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.SunyesMaxGamePreset;
import java.io.File;
import java.util.ArrayList;

public class SunyesMaxGameItemsAdapter extends BaseAdapter
{
  private Context mContext;
  private LayoutInflater mInflater;
  private ArrayList<SunyesMaxGamePreset> sunyesMaxGamePresetList;

  public SunyesMaxGameItemsAdapter(Context paramContext, ArrayList<SunyesMaxGamePreset> paramArrayList)
  {
    this.mContext = paramContext;
    this.mInflater = LayoutInflater.from(this.mContext);
    this.sunyesMaxGamePresetList = paramArrayList;
  }

  public int getCount()
  {
    return this.sunyesMaxGamePresetList.size();
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(R.layout.sunyes_max_game_list_item, null);
      paramViewGroup = new Holder();
      paramViewGroup.item_app_icon = ((ImageView)ViewHolder.get(paramView, R.id.item_app_icon));
      paramViewGroup.item_app_name = ((TextView)ViewHolder.get(paramView, R.id.item_app_name));
      paramView.setTag(paramViewGroup);
    }
    else
    {
      paramViewGroup = (Holder)paramView.getTag();
    }
    paramViewGroup.item_app_name.setText(((SunyesMaxGamePreset)this.sunyesMaxGamePresetList.get(paramInt)).getAppName());
    if ((((SunyesMaxGamePreset)this.sunyesMaxGamePresetList.get(paramInt)).getIconPath() != null) && (paramInt != this.sunyesMaxGamePresetList.size() - 1))
      paramViewGroup.item_app_icon.setImageURI(Uri.fromFile(new File(((SunyesMaxGamePreset)this.sunyesMaxGamePresetList.get(paramInt)).getIconPath())));
    if (paramInt == this.sunyesMaxGamePresetList.size() - 1)
      paramViewGroup.item_app_icon.setImageDrawable(((SunyesMaxGamePreset)this.sunyesMaxGamePresetList.get(paramInt)).getIcon());
    return paramView;
  }

  public void refresh(ArrayList<SunyesMaxGamePreset> paramArrayList)
  {
    this.sunyesMaxGamePresetList = paramArrayList;
    notifyDataSetChanged();
  }

  static class Holder
  {
    ImageView item_app_icon;
    TextView item_app_name;
  }
}
