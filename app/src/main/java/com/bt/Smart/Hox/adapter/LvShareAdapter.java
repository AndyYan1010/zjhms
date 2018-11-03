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
    private int                                 mItem;//哪个条目展示

    public LvShareAdapter(Context context, List<RoomsDeviceInfo.HouseListBean> list, int item) {
        this.mContext = context;
        this.mList = list;
        this.mItem = item;
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
        final MyViewholder viewholder;
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
        //
        final LvRoomDeviceAdapter roomDeviceAdapter = new LvRoomDeviceAdapter(mContext, mList.get(i).getDeviceList(), mList, this, viewholder.cb_room);
        viewholder.lv_device.setAdapter(roomDeviceAdapter);
        if (i == mItem) {
            viewholder.lv_device.setVisibility(View.VISIBLE);
            viewholder.img_down.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_up));
        } else {
            viewholder.lv_device.setVisibility(View.GONE);
            viewholder.img_down.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_down));
        }
        viewholder.img_down.setTag(i);
        viewholder.img_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//展开与闭合
                //用 currentItem 记录点击位置s
                int tag = (Integer) view.getTag();
                if (tag == mItem) { //再次点击
                    mItem = -1; //给 currentItem 一个无效值
                    viewholder.img_down.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_down));
                } else {
                    //展开
                    mItem = tag;
                    viewholder.img_down.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_up));
                }
                //通知adapter数据改变需要重新加载
                notifyDataSetChanged();
            }
        });
        if (mList.get(i).getDeviceList().size() == 0) {//房间设备为空时，不能选中
            viewholder.cb_room.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewholder.cb_room.setChecked(false);
                }
            });
        } else {
            viewholder.cb_room.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (viewholder.cb_room.isChecked()) {
                        //取消选中/并更改所有子表选中状态
                        for (RoomsDeviceInfo.HouseListBean.DeviceListBean bean : mList.get(i).getDeviceList()) {
                            bean.setMeChoice(true);
                        }
                    } else {
                        for (RoomsDeviceInfo.HouseListBean.DeviceListBean bean : mList.get(i).getDeviceList()) {
                            bean.setMeChoice(false);
                        }
                    }
                    roomDeviceAdapter.notifyDataSetChanged();
                }
            });
        }
        return view;
    }

    private class MyViewholder {
        CheckBox   cb_room;
        ImageView  img_down;
        MyListView lv_device;
    }

    public int mineSelectItem() {
        return mItem;
    }
}
