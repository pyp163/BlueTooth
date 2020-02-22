package com.qx.qgbox.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.FWInfo763;

import java.util.ArrayList;
import java.util.Locale;

public class Fw763ListItemsAdapter extends BaseAdapter {
    private String locale = null;
    private Context mContext;
    private ArrayList<FWInfo763> mFWInfo763List;
    private LayoutInflater mInflater;
    private ArrayList<Boolean> stateChecked;

    public Fw763ListItemsAdapter(Context paramContext, ArrayList<FWInfo763> paramArrayList, ArrayList<Boolean> paramArrayList1) {
        this.mContext = paramContext;
        this.locale = Locale.getDefault().toString();
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mFWInfo763List = paramArrayList;
        this.stateChecked = paramArrayList1;
    }

    public int getCount() {
        return this.mFWInfo763List.size();
    }

    public Object getItem(int paramInt) {
        return Integer.valueOf(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {

        Holder holder;
        if (paramView == null) {
            paramView = this.mInflater.inflate(R.layout.fw_list_items, null);
            holder = new Holder();
            holder.tv_version_list_items = ((TextView) ViewHolder.get(paramView, R.id.tv_version_list_items));
            holder.tv_content_list_items = ((TextView) ViewHolder.get(paramView, R.id.tv_content_list_items));
            holder.cb_fw = ((ImageView) ViewHolder.get(paramView, R.id.cb_fw));
            paramView.setTag(holder);
        } else {
            holder = (Holder) paramView.getTag();
        }
        TextView localTextView = holder.tv_version_list_items;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(this.mContext.getResources().getString(R.string.fw_list_version));
        localStringBuilder.append(":");
        localStringBuilder.append(((FWInfo763) this.mFWInfo763List.get(paramInt)).getVersionCode());
        localTextView.setText(localStringBuilder.toString());
        if ((!this.locale.contains("zh_CN")) && (!this.locale.contains("zh_HK")) && (!this.locale.contains("zh_TW"))) {
            localTextView = holder.tv_content_list_items;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(this.mContext.getResources().getString(R.string.update_fw_tip4));
            localStringBuilder.append(":\n");
            localStringBuilder.append(((FWInfo763) this.mFWInfo763List.get(paramInt)).getEnglish_content());
            localTextView.setText(localStringBuilder.toString());
        } else {
            localTextView = holder.tv_content_list_items;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(this.mContext.getResources().getString(R.string.update_fw_tip4));
            localStringBuilder.append(":\n");
            localStringBuilder.append(((FWInfo763) this.mFWInfo763List.get(paramInt)).getContent());
            localTextView.setText(localStringBuilder.toString());
        }
        if (((Boolean) this.stateChecked.get(paramInt)).booleanValue()) {
            holder.cb_fw.setImageResource(R.mipmap.checked);
            return paramView;
        }
        holder.cb_fw.setImageResource(R.mipmap.unchecked);
        return paramView;
    }

    public void refresh(ArrayList<FWInfo763> paramArrayList, ArrayList<Boolean> paramArrayList1) {
        this.mFWInfo763List = paramArrayList;
        this.stateChecked = paramArrayList1;
        notifyDataSetChanged();
    }

    public void setItemsChecked(int paramInt) {
        int i = 0;
        while (i < this.stateChecked.size()) {
            if (i != paramInt)
                this.stateChecked.set(i, Boolean.valueOf(false));
            else
                this.stateChecked.set(i, Boolean.valueOf(true));
            i += 1;
        }
        notifyDataSetChanged();
    }

    static class Holder {
        ImageView cb_fw;
        TextView tv_content_list_items;
        TextView tv_version_list_items;
    }
}
