package com.qx.qgbox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.Device;

import java.util.ArrayList;

public class DeviceItemsAdapter extends BaseAdapter {
    private ArrayList<Device> deviceList;
    private Context mContext;
    private LayoutInflater mInflater;

    public DeviceItemsAdapter(Context paramContext, ArrayList<Device> paramArrayList) {
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.deviceList = paramArrayList;
    }

    public int getCount() {
        return this.deviceList.size();
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
            paramView = this.mInflater.inflate(R.layout.gv_device_items, null);
            holder = new Holder();
            holder.deviceName = ((TextView) ViewHolder.get(paramView, R.id.tv_device_name));
            holder.cb_all_device = ((CheckBox) ViewHolder.get(paramView, R.id.cb_all_device));
            holder.ll_item = ((LinearLayout) ViewHolder.get(paramView, R.id.ll_item));
            paramView.setTag(paramViewGroup);
        } else {
            holder = (Holder) paramView.getTag();
        }
        holder.deviceName.setText(((Device) this.deviceList.get(paramInt)).getDeviceName());
        holder.cb_all_device.setChecked(((Device) this.deviceList.get(paramInt)).isChecked());
        holder.cb_all_device.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
                ((Device) DeviceItemsAdapter.this.deviceList.get(paramInt)).setChecked(paramAnonymousBoolean);
            }
        });
        return paramView;
    }

    public void refresh(ArrayList<Device> paramArrayList) {
        this.deviceList = paramArrayList;
        notifyDataSetChanged();
    }

    static class Holder {
        CheckBox cb_all_device;
        TextView deviceName;
        LinearLayout ll_item;
    }
}
