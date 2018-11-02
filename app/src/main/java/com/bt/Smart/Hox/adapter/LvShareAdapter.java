package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.RoomsDeviceInfo;
import com.bt.Smart.Hox.viewmodle.MyListView;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 20:02
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvShareAdapter extends BaseAdapter {
    private Context                             mContext;
    private List<RoomsDeviceInfo.HouseListBean> mList;

    public LvShareAdapter(Context context, List<RoomsDeviceInfo.HouseListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
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
            view = View.inflate(mContext, R.layout.adpter_share_room, null);
            viewholder.cb_room = view.findViewById(R.id.cb_room);
            viewholder.img_down = view.findViewById(R.id.img_down);
            viewholder.lv_device = view.findViewById(R.id.lv_device);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.cb_room.setText(mList.get(i).getHouse_name());
        viewholder.img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展开与闭合
            }
        });
        LvRoomDeviceAdapter roomDeviceAdapter = new LvRoomDeviceAdapter(mContext, mList.get(i).getDeviceList());
        viewholder.lv_device.setAdapter(roomDeviceAdapter);
        return view;
    }

    private class MyViewholder {
        CheckBox   cb_room;
        ImageView  img_down;
        MyListView lv_device;
    }
}
