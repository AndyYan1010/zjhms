package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bt.Smart.Hox.R;

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
    private List<String> mList;
    private Context      mContext;

    public LvChoiceRoomAdapter(Context context, List<String> list) {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewholder viewholder;
        if (null == view) {
            viewholder = new MyViewholder();
            view = View.inflate(mContext, R.layout.item_gridview, null);
            viewholder.cb_choice = view.findViewById(R.id.cb_choice);
            viewholder.tv_room = view.findViewById(R.id.tv_room);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_room.setText(mList.get(i));
        return view;
    }

    private class MyViewholder {
        CheckBox cb_choice;
        TextView tv_room;
    }
}
