package com.qx.qgbox.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qx.qgbox.R;
import com.qx.qgbox.entitys.OfficialGamePreset;
import java.io.File;
import java.util.ArrayList;

public class OfficialGameItemsAdapter extends BaseAdapter
{
  private Context mContext;
  private LayoutInflater mInflater;
  private ArrayList<OfficialGamePreset> officialGameList;

  public OfficialGameItemsAdapter(Context paramContext, ArrayList<OfficialGamePreset> paramArrayList)
  {
    this.mContext = paramContext;
    this.mInflater = LayoutInflater.from(this.mContext);
    this.officialGameList = paramArrayList;
  }

  public int getCount()
  {
    return this.officialGameList.size();
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
      paramView = this.mInflater.inflate(R.layout.official_game_list_item, null);
      paramViewGroup = new Holder();
      paramViewGroup.item_app_icon = ((SimpleDraweeView)ViewHolder.get(paramView, R.id.item_app_icon));
      paramViewGroup.item_app_icon2 = ((ImageView)ViewHolder.get(paramView, R.id.item_app_icon2));
      paramViewGroup.item_app_name = ((TextView)ViewHolder.get(paramView, R.id.item_app_name));
      paramView.setTag(paramViewGroup);
    }
    else
    {
      paramViewGroup = (Holder)paramView.getTag();
    }
    if (((OfficialGamePreset)this.officialGameList.get(paramInt)).getDataOrg() == 0)
    {
      paramViewGroup.item_app_name.setText(((OfficialGamePreset)this.officialGameList.get(paramInt)).getGameName());
      paramViewGroup.item_app_icon.setImageURI(Uri.parse(((OfficialGamePreset)this.officialGameList.get(paramInt)).getGameLogoUrl()));
      paramViewGroup.item_app_icon.setVisibility(0);
      paramViewGroup.item_app_icon2.setVisibility(8);
      return paramView;
    }
    if (((OfficialGamePreset)this.officialGameList.get(paramInt)).getDataOrg() == 1)
    {
      paramViewGroup.item_app_name.setText(((OfficialGamePreset)this.officialGameList.get(paramInt)).getAppName());
      if ((((OfficialGamePreset)this.officialGameList.get(paramInt)).getIconPath() != null) && (paramInt != this.officialGameList.size() - 1))
        paramViewGroup.item_app_icon2.setImageURI(Uri.fromFile(new File(((OfficialGamePreset)this.officialGameList.get(paramInt)).getIconPath())));
      if (paramInt == this.officialGameList.size() - 1)
        paramViewGroup.item_app_icon2.setImageDrawable(((OfficialGamePreset)this.officialGameList.get(paramInt)).getIcon());
      paramViewGroup.item_app_icon.setVisibility(8);
      paramViewGroup.item_app_icon2.setVisibility(0);
    }
    return paramView;
  }

  public void refresh(ArrayList<OfficialGamePreset> paramArrayList)
  {
    this.officialGameList = paramArrayList;
    notifyDataSetChanged();
  }

  static class Holder
  {
    SimpleDraweeView item_app_icon;
    ImageView item_app_icon2;
    TextView item_app_name;
  }
}
