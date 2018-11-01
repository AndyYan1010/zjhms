package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.UserHomeInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/1 13:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvSetHomeAdapter extends BaseAdapter {
    private Context                         mContext;
    private List<UserHomeInfo.HomeListBean> mList;

    public LvSetHomeAdapter(Context context, List<UserHomeInfo.HomeListBean> list) {
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
            view = View.inflate(mContext, R.layout.adpter_set_home, null);
            viewholder.img_dg = view.findViewById(R.id.img_dg);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        if ("1".equals(mList.get(i).getIsdefault())) {
            viewholder.img_dg.setVisibility(View.VISIBLE);
        } else {
            viewholder.img_dg.setVisibility(View.INVISIBLE);
        }
        viewholder.tv_name.setText(mList.get(i).getHome_name());
        return view;
    }

    private class MyViewholder {
        TextView  tv_name;
        ImageView img_dg;
    }
}
