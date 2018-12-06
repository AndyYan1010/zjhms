package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.UserHomeInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/8/27 10:05
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvHomeAdapter extends BaseAdapter {
    private Context                         mContext;
    private List<UserHomeInfo.HomeListBean> mList;

    public LvHomeAdapter(Context context, List<UserHomeInfo.HomeListBean> list) {
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
        final MyViewholder viewholder;
        if (null == view) {
            viewholder = new MyViewholder();
            view = View.inflate(mContext, R.layout.adpter_home_name, null);
            viewholder.img_home = view.findViewById(R.id.img_home);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        GlideLoaderUtil.showImgWithIcon(mContext, mList.get(i).getHome_pic(), R.drawable.iman, R.drawable.iman, viewholder.img_home);
        viewholder.tv_name.setText(mList.get(i).getHome_name());
        return view;
    }

    private class MyViewholder {
        ImageView img_home;
        TextView  tv_name;
    }
}
