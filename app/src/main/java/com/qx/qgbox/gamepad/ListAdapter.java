package com.qx.qgbox.gamepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.List;

public class ListAdapter extends BaseAdapter
{
  List<Setupitem> Msetupitem;
  Context context;
  ViewHolder holder;
  LayoutInflater mInflater;
  Setupitem msetup;

  public ListAdapter(Context paramContext)
  {
    this.context = paramContext;
    this.mInflater = LayoutInflater.from(paramContext);
  }

  public int getCount()
  {
    return this.Msetupitem.size();
  }

  public Object getItem(int paramInt)
  {
    return this.Msetupitem.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      this.holder = new ViewHolder();
      paramView = this.mInflater.inflate(R.layout.gpsetup_item, null);
      this.holder.radioBtn = ((RadioButton)paramView.findViewById(R.id.mradioButton));
      this.holder.radioBtn.setClickable(false);
      this.holder.textView = ((TextView)paramView.findViewById(R.id.textViewname));
      paramView.setTag(this.holder);
    }
    else
    {
      this.holder = ((ViewHolder)paramView.getTag());
    }
    this.msetup = ((Setupitem)getItem(paramInt));
    this.holder.radioBtn.setChecked(this.msetup.isSelected());
    this.holder.textView.setText(this.msetup.getitemName());
    return paramView;
  }

  public void select(int paramInt)
  {
    if (!((Setupitem)this.Msetupitem.get(paramInt)).isSelected())
    {
      ((Setupitem)this.Msetupitem.get(paramInt)).setSelected(true);
      int i = 0;
      while (i < this.Msetupitem.size())
      {
        if (i != paramInt)
          ((Setupitem)this.Msetupitem.get(i)).setSelected(false);
        i += 1;
      }
    }
    notifyDataSetChanged();
  }

  public void setList(List<Setupitem> paramList)
  {
    this.Msetupitem = paramList;
  }

  class ViewHolder
  {
    RadioButton radioBtn;
    TextView textView;

    ViewHolder()
    {
    }
  }
}
