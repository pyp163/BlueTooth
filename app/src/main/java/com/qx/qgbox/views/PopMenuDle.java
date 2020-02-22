package com.qx.qgbox.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;

public class PopMenuDle
{
  private Context context;
  private ArrayList<Item> itemList;
  private ListView listView;
  private PopupWindow popupWindow;

  public PopMenuDle(Context paramContext, int paramInt)
  {
    this.context = paramContext;
    this.itemList = new ArrayList(1);
    paramContext = LayoutInflater.from(paramContext).inflate(R.layout.popmenu, null);
    this.listView = ((ListView)paramContext.findViewById(R.id.popup_view_listView));
    this.listView.setAdapter(new PopAdapter(null));
    this.listView.setFocusableInTouchMode(true);
    this.listView.setFocusable(true);
    this.popupWindow = new PopupWindow(paramContext, 30, -2);
    this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
  }

  private int getMaxWidth(ListView paramListView)
  {
    if (paramListView.getAdapter() == null)
      return 220;
    int m = paramListView.getAdapter().getCount();
    int i = 0;
    int k;
    for (int j = 220; i < m; j = k)
    {
      View localView = paramListView.getAdapter().getView(i, null, paramListView);
      localView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
      k = j;
      if (localView.getMeasuredWidth() > j)
        k = localView.getMeasuredWidth();
      i += 1;
    }
    if (this.context.getResources().getDisplayMetrics().widthPixels < j)
      return this.context.getResources().getDisplayMetrics().widthPixels - 50;
    return j;
  }

  private int getetListViewHeight(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return 0;
    int k = localListAdapter.getCount();
    int i = 0;
    int j = 0;
    while (i < k)
    {
      View localView = localListAdapter.getView(i, null, paramListView);
      localView.measure(0, 0);
      j += localView.getMeasuredHeight();
      i += 1;
    }
    return j;
  }

  public void addItem(Item paramItem)
  {
    this.itemList.add(paramItem);
  }

  public void addItems(Item[] paramArrayOfItem)
  {
    int j = paramArrayOfItem.length;
    int i = 0;
    while (i < j)
    {
      Item localItem = paramArrayOfItem[i];
      this.itemList.add(localItem);
      i += 1;
    }
  }

  public void dismiss()
  {
    this.popupWindow.dismiss();
  }

  public int getCount()
  {
    return this.itemList.size();
  }

  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.listView.setOnItemClickListener(paramOnItemClickListener);
  }

  public void showAsDropDown(View paramView, int paramInt1, int paramInt2)
  {
    this.popupWindow.setWidth(getMaxWidth(this.listView));
    this.popupWindow.setHeight(getetListViewHeight(this.listView) + 8);
    this.popupWindow.showAsDropDown(paramView, paramInt1, paramInt2);
    this.popupWindow.setFocusable(true);
    this.popupWindow.setOutsideTouchable(true);
    this.popupWindow.update();
  }

  public static class Item
  {
    public int id;
    public String text;

    public Item(String paramString, int paramInt)
    {
      this.text = paramString;
      this.id = paramInt;
    }
  }

  private final class PopAdapter extends BaseAdapter
  {
    private PopAdapter()
    {
    }

    public int getCount()
    {
      return PopMenuDle.this.itemList.size();
    }

    public Object getItem(int paramInt)
    {
      return PopMenuDle.this.itemList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = LayoutInflater.from(PopMenuDle.this.context).inflate(R.layout.popmenu_item1, null);
        paramViewGroup = new ViewHolder(null);
        paramView.setTag(paramViewGroup);
        paramViewGroup.groupItem = ((TextView)paramView.findViewById(R.id.pop_item_header));
        paramViewGroup.groupImg = ((ImageView)paramView.findViewById(R.id.pop_item_img));
      }
      else
      {
        paramViewGroup = (ViewHolder)paramView.getTag();
      }
      paramViewGroup.groupItem.setText(((PopMenuDle.Item)PopMenuDle.this.itemList.get(paramInt)).text);
      paramViewGroup.groupImg.setBackgroundResource(((PopMenuDle.Item)PopMenuDle.this.itemList.get(paramInt)).id);
      return paramView;
    }

    private final class ViewHolder
    {
      ImageView groupImg;
      TextView groupItem;

      private ViewHolder()
      {
      }
    }
  }
}
