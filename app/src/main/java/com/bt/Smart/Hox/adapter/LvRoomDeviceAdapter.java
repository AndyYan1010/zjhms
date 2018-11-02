package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.RoomsDeviceInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 20:34
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvRoomDeviceAdapter extends BaseAdapter {
    private Context                                            mContext;
    private List<RoomsDeviceInfo.HouseListBean.DeviceListBean> mList;

    public LvRoomDeviceAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewholder viewholder;
        if (null == view) {
            viewholder = new MyViewholder();
            view = View.inflate(mContext, R.layout.adpter_room_dev, null);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.cb_choice = view.findViewById(R.id.cb_choice);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getDevice_name());
        return view;
    }

    private class MyViewholder {
        TextView tv_name;
        CheckBox cb_choice;
    }
}
