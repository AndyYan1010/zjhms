package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.Ha3ValueInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/23 8:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvHa3ValueAdapter extends BaseAdapter {
    private List<Ha3ValueInfo.Ha3TypeVlaueListBean> mList;
    private Context                                 mContext;

    public LvHa3ValueAdapter(Context context, List<Ha3ValueInfo.Ha3TypeVlaueListBean> list) {
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
            view = View.inflate(mContext, R.layout.adpter_ha3_value, null);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getFname());
        return view;
    }

    private class MyViewholder {
        TextView tv_name;
    }
}
