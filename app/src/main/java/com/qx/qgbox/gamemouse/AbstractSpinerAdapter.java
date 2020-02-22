package com.qx.qgbox.gamemouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qx.qgbox.R;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSpinerAdapter<T> extends BaseAdapter
{
  private Context mContext;
  private LayoutInflater mInflater;
  private List<T> mObjects = new ArrayList();
  private int mSelectItem = 0;

  public AbstractSpinerAdapter(Context paramContext)
  {
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  public int getCount()
  {
    return this.mObjects.size();
  }

  public Object getItem(int paramInt)
  {
    return this.mObjects.get(paramInt).toString();
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(R.layout.spiner_item_layout, null);
      paramViewGroup = new ViewHolder();
      paramViewGroup.mTextView = ((TextView)paramView.findViewById(R.id.textView));
      paramView.setTag(paramViewGroup);
    }
    else
    {
      paramViewGroup = (ViewHolder)paramView.getTag();
    }
    String str = (String)getItem(paramInt);
    paramViewGroup.mTextView.setText(str);
    return paramView;
  }

  public void refreshData(List<T> paramList, int paramInt)
  {
    this.mObjects = paramList;
    int i = paramInt;
    if (paramInt < 0)
      i = 0;
    paramInt = i;
    if (i >= this.mObjects.size())
      paramInt = this.mObjects.size() - 1;
    this.mSelectItem = paramInt;
  }

  public static abstract interface IOnItemSelectListener
  {
    public abstract void onItemClick(int paramInt);
  }

  public static class ViewHolder
  {
    public TextView mTextView;
  }
}
