package com.qx.qgbox.views;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qx.qgbox.utils.MyLog;
import java.util.ArrayList;

public class PopMenu
{
  private Context context;
  private ArrayList<Item> itemList;
  private ListView listView;
  private PopupWindow popupWindow;

  public PopMenu(Context paramContext)
  {
    this.context = paramContext;
    this.itemList = new ArrayList(6);
    paramContext = LayoutInflater.from(paramContext).inflate(R.layout.popmenu, null);
    this.listView = ((ListView)paramContext.findViewById(R.id.popup_view_listView));
    this.listView.setAdapter(new PopAdapter());
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

  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.listView.setOnItemClickListener(paramOnItemClickListener);
  }

  public void showAsDropDown(View paramView, int paramInt1, int paramInt2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("---------getMaxWidth = ");
    localStringBuilder.append(getMaxWidth(this.listView));
    MyLog.i("my_tag", localStringBuilder.toString());
    if (getMaxWidth(this.listView) < 450)
      this.popupWindow.setWidth(450);
    else
      this.popupWindow.setWidth(getMaxWidth(this.listView));
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
      return PopMenu.this.itemList.size();
    }

    public Object getItem(int paramInt)
    {
      return PopMenu.this.itemList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = LayoutInflater.from(PopMenu.this.context).inflate(R.layout.popmenu_item, null);
        paramViewGroup = new ViewHolder(null);
        paramView.setTag(paramViewGroup);
        paramViewGroup.groupItem = ((TextView)paramView.findViewById(R.id.pop_item_header));
        paramViewGroup.groupImg = ((ImageView)paramView.findViewById(R.id.pop_item_img));
      }
      else
      {
        paramViewGroup = (ViewHolder)paramView.getTag();
      }
      paramViewGroup.groupItem.setText(((PopMenu.Item)PopMenu.this.itemList.get(paramInt)).text);
      paramViewGroup.groupImg.setBackgroundResource(((PopMenu.Item)PopMenu.this.itemList.get(paramInt)).id);
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
