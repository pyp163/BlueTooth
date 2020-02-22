package com.qx.qgbox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.OfficialGame;
import java.util.ArrayList;

public class PopuwListViewAdapter2 extends BaseAdapter
{
  private LayoutInflater inflater;
  private ArrayList<OfficialGame> mOfficialGameList = new ArrayList();

  public PopuwListViewAdapter2(Context paramContext, ArrayList<OfficialGame> paramArrayList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.mOfficialGameList = paramArrayList;
  }

  public int getCount()
  {
    return this.mOfficialGameList.size();
  }

  public OfficialGame getItem(int paramInt)
  {
    return (OfficialGame)this.mOfficialGameList.get(paramInt);
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
    String str = ((OfficialGame)this.mOfficialGameList.get(paramInt)).getName();
    paramViewGroup = paramViewGroup.name;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append("");
    paramViewGroup.setText(localStringBuilder.toString());
    return paramView;
  }

  public void refresh(ArrayList<OfficialGame> paramArrayList)
  {
    this.mOfficialGameList = paramArrayList;
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
