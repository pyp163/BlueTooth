package com.qx.qgbox.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qx.qgbox.R;
import com.qx.qgbox.entitys.NewBluetoothDevice;

import java.util.ArrayList;

public class LeDeviceListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<NewBluetoothDevice> mLeDevices = new ArrayList();

    public LeDeviceListAdapter(Context paramContext, ArrayList<NewBluetoothDevice> paramArrayList) {
        this.mLeDevices = paramArrayList;
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    public void clear() {
        this.mLeDevices.clear();
    }

    public int getCount() {
        return this.mLeDevices.size();
    }

    public NewBluetoothDevice getDevice(int paramInt) {
        return (NewBluetoothDevice) this.mLeDevices.get(paramInt);
    }

    public Object getItem(int paramInt) {
        return this.mLeDevices.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {

        Holder holder;
        if (paramView == null) {
            paramView = this.mInflater.inflate(R.layout.listitem_device, null);
            holder = new Holder();
            holder.deviceAddress = ((TextView) ViewHolder.get(paramView, R.id.device_address));
            holder.deviceName = ((TextView) ViewHolder.get(paramView, R.id.device_name));
            paramView.setTag(holder);
        } else {
            holder = (Holder) paramView.getTag();
        }
        NewBluetoothDevice localNewBluetoothDevice = (NewBluetoothDevice) this.mLeDevices.get(paramInt);
        String str = localNewBluetoothDevice.getName();
        if ((str != null) && (str.length() > 0))
            holder.deviceName.setText(str);
        else
            holder.deviceName.setText(this.mContext.getString(R.string.unknown_service));
        holder.deviceAddress.setText(localNewBluetoothDevice.getMacAddress());
        return paramView;
    }

    public void refresh(ArrayList<NewBluetoothDevice> paramArrayList) {
        this.mLeDevices = paramArrayList;
        notifyDataSetChanged();
    }

    static class Holder {
        TextView deviceAddress;
        TextView deviceName;
    }
}
