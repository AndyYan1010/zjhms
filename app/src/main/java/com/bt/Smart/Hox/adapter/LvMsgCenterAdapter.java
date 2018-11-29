package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.MineMsgInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/29 19:02
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvMsgCenterAdapter extends BaseAdapter {
    private Context                                     mContext;
    private List<MineMsgInfo.DevListBean.DevStatusBean> mList;

    public LvMsgCenterAdapter(Context context, List<MineMsgInfo.DevListBean.DevStatusBean> list) {
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
            view = View.inflate(mContext, R.layout.adpter_msg_center, null);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.tv_time = view.findViewById(R.id.tv_time);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getDevice_name());
        viewholder.tv_time.setText(mList.get(i).getFaddtime());
        return view;
    }

    private class MyViewholder {
        TextView tv_name, tv_time;
    }
}
