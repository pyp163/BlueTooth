package com.qx.qgbox.gamemouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.qx.qgbox.R;

import java.util.List;

public class SpinerPopWindow extends PopupWindow
  implements AdapterView.OnItemClickListener
{
  private NormalSpinerAdapter mAdapter;
  private Context mContext;
  private AbstractSpinerAdapter.IOnItemSelectListener mItemSelectListener;
  private ListView mListView;

  public SpinerPopWindow(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  private void init()
  {
    View localView = LayoutInflater.from(this.mContext).inflate(R.layout.spiner_window_layout, null);
    setContentView(localView);
    setWidth(-2);
    setHeight(-2);
    setFocusable(false);
    setOutsideTouchable(true);
    this.mListView = ((ListView)localView.findViewById(R.id.listview));
    this.mAdapter = new NormalSpinerAdapter(this.mContext);
    this.mListView.setAdapter(this.mAdapter);
    this.mListView.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    dismiss();
    if (this.mItemSelectListener != null)
      this.mItemSelectListener.onItemClick(paramInt);
  }

  public void refreshData(List<String> paramList, int paramInt)
  {
    if ((paramList != null) && (paramInt != -1))
      this.mAdapter.refreshData(paramList, paramInt);
  }

  public void setItemListener(AbstractSpinerAdapter.IOnItemSelectListener paramIOnItemSelectListener)
  {
    this.mItemSelectListener = paramIOnItemSelectListener;
  }
}
