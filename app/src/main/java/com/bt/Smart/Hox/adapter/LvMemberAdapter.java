package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.HomeMembersInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/9/3 14:45
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvMemberAdapter extends BaseAdapter {
    private List<HomeMembersInfo.HomeListBean> mList;
    private Context                            mContext;

    public LvMemberAdapter(Context context, List<HomeMembersInfo.HomeListBean> list) {
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
            view = View.inflate(mContext, R.layout.adpter_home_member, null);
            viewholder.img_head = view.findViewById(R.id.img_head);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.tv_phone = view.findViewById(R.id.tv_phone);
            viewholder.tv_power = view.findViewById(R.id.tv_power);
            viewholder.img_next = view.findViewById(R.id.img_next);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        if (null == mList.get(i).getWx_name() || "".equals(mList.get(i).getWx_name())) {
            viewholder.tv_name.setText(mList.get(i).getWx_name());
        } else {
            viewholder.tv_name.setText(mList.get(i).getFtelephone());
        }
        GlideLoaderUtil.showImgWithIcon(mContext, mList.get(i).getWx_pic(), R.drawable.iman, R.drawable.iman, viewholder.img_head);
        viewholder.tv_phone.setText(mList.get(i).getFtelephone());
        if ("1".equals(mList.get(i).getIsmanager())) {
            viewholder.img_next.setVisibility(View.GONE);
            viewholder.tv_power.setText("管理员");
        } else {
            viewholder.tv_power.setText("");
            viewholder.img_next.setVisibility(View.VISIBLE);
        }
        return view;
    }

    private class MyViewholder {
        ImageView img_head, img_next;
        TextView tv_name, tv_phone, tv_power;
    }
}
