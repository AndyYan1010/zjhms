package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AutoDetailInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/9 9:42
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvAddActionAdapter extends BaseAdapter {
    private List<AutoDetailInfo.AutomationBean.AutomationsBean.ActionListBean> mList;
    private Context                                                            mContext;

    public LvAddActionAdapter(Context context, List<AutoDetailInfo.AutomationBean.AutomationsBean.ActionListBean> list) {
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
            view = View.inflate(mContext, R.layout.adpter_add_act, null);
            viewholder.img_act = view.findViewById(R.id.img_act);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.tv_time = view.findViewById(R.id.tv_time);
            viewholder.tv_act = view.findViewById(R.id.tv_act);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        GlideLoaderUtil.showImageView(mContext, mList.get(i).getDevice_img(), viewholder.img_act);
        viewholder.tv_name.setText(mList.get(i).getFname());
        if (0 == mList.get(i).getStatus()) {
            viewholder.tv_time.setText("立即");
        } else {
            viewholder.tv_time.setText(mList.get(i).getStatus() + "分钟");
        }
        if (0 == mList.get(i).getWhen()) {
            viewholder.tv_act.setText("执行");
        }
        return view;
    }

    private class MyViewholder {
        ImageView img_act;
        TextView  tv_name, tv_time, tv_act;
    }
}
