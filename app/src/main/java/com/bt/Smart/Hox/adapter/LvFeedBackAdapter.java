package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bt.Smart.Hox.R;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/3 20:01
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvFeedBackAdapter extends BaseAdapter {

    private List<String> mList;
    private Context      mContext;

    public LvFeedBackAdapter(Context context, List<String> list) {
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
            view = View.inflate(mContext, R.layout.adpter_feed_back, null);
            viewholder.tv_date = view.findViewById(R.id.tv_date);
            viewholder.tv_cont = view.findViewById(R.id.tv_cont);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }

        return view;
    }

    private class MyViewholder {
        TextView tv_date, tv_cont;
    }
}
