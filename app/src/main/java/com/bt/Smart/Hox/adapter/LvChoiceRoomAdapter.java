package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.MoveDeviceActivity;
import com.bt.Smart.Hox.messegeInfo.HouseDetailInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/9/3 14:45
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvChoiceRoomAdapter extends BaseAdapter {
    private List<HouseDetailInfo.HouseListBean> mList;
    private Context                             mContext;
    private int                                 mItem;

    public LvChoiceRoomAdapter(Context context, List<HouseDetailInfo.HouseListBean> list, int item) {
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
            view = View.inflate(mContext, R.layout.item_gridview, null);
            viewholder.cb_choice = view.findViewById(R.id.cb_choice);
            viewholder.tv_room = view.findViewById(R.id.tv_room);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_room.setText(mList.get(i).getHouse_name());
        if (mItem == i) {
            viewholder.cb_choice.setChecked(true);
        } else {
            viewholder.cb_choice.setChecked(false);
        }
        viewholder.cb_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItem = i;
                ((MoveDeviceActivity)mContext).setItem(i);
                viewholder.cb_choice.setChecked(true);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    private class MyViewholder {
        CheckBox cb_choice;
        TextView tv_room;
    }
}
