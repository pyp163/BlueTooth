package com.qx.qgbox.gamepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qx.qgbox.R;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSpinerAdapter<T> extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mObjects = new ArrayList();
    private int mSelectItem = 0;

    public AbstractSpinerAdapter(Context paramContext) {
        init(paramContext);
    }

    private void init(Context paramContext) {
        this.mContext = paramContext;
        this.mInflater = ((LayoutInflater) paramContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
    }

    public int getCount() {
        return this.mObjects.size();
    }

    public Object getItem(int paramInt) {
        return this.mObjects.get(paramInt).toString();
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {

        ViewHolder viewHolder;
        if (paramView == null) {
            paramView = this.mInflater.inflate(R.layout.gpspiner_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = ((TextView) paramView.findViewById(R.id.textView));
            paramView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) paramView.getTag();
        }
        String str = (String) getItem(paramInt);
        viewHolder.mTextView.setText(str);
        return paramView;
    }

    public void refreshData(List<T> paramList, int paramInt) {
        this.mObjects = paramList;
        int i = paramInt;
        if (paramInt < 0)
            i = 0;
        paramInt = i;
        if (i >= this.mObjects.size())
            paramInt = this.mObjects.size() - 1;
        this.mSelectItem = paramInt;
    }

    public static abstract interface IOnItemSelectListener {
        public abstract void onItemClick(int paramInt);
    }

    public static class ViewHolder {
        public TextView mTextView;
    }
}
