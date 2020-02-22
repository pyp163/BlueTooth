package com.qx.qgbox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.InstalledApp;

import java.util.ArrayList;

public class InstalledAppListAdapter extends BaseAdapter {
    private ArrayList<InstalledApp> installedAppList;
    private Callback mCallback;
    private Context mContext;
    private LayoutInflater mInflater;

    public InstalledAppListAdapter(Context paramContext, ArrayList<InstalledApp> paramArrayList, Callback paramCallback) {
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.installedAppList = paramArrayList;
        this.mCallback = paramCallback;
    }

    public int getCount() {
        return this.installedAppList.size();
    }

    public Object getItem(int paramInt) {
        return Integer.valueOf(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup) {

        Holder holder;

        if (paramView == null) {
            paramView = this.mInflater.inflate(R.layout.installed_app_list_item, null);
            holder = new Holder();
            holder.item_app_icon = ((ImageView) ViewHolder.get(paramView, R.id.item_app_icon));
            holder.item_app_name = ((TextView) ViewHolder.get(paramView, R.id.item_app_name));
            holder.btn_add = ((Button) ViewHolder.get(paramView, R.id.btn_add));
            paramView.setTag(paramViewGroup);
        } else {
            holder = (Holder) paramView.getTag();
        }
        holder.item_app_name.setText(((InstalledApp) this.installedAppList.get(paramInt)).getAppName());
        holder.item_app_icon.setImageDrawable(((InstalledApp) this.installedAppList.get(paramInt)).getIcon());
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                InstalledAppListAdapter.this.mCallback.click((InstalledApp) InstalledAppListAdapter.this.installedAppList.get(paramInt));
            }
        });
        return paramView;
    }

    public void refresh(ArrayList<InstalledApp> paramArrayList, Callback paramCallback) {
        this.installedAppList = paramArrayList;
        notifyDataSetChanged();
        this.mCallback = paramCallback;
    }

    public static abstract interface Callback {
        public abstract void click(InstalledApp paramInstalledApp);
    }

    static class Holder {
        Button btn_add;
        ImageView item_app_icon;
        TextView item_app_name;
    }
}
