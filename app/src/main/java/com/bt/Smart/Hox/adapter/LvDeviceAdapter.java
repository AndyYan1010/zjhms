package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.MoveDeviceActivity;
import com.bt.Smart.Hox.messegeInfo.HouseDeviceInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/8/27 10:04
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvDeviceAdapter extends BaseAdapter {
    private Context                                   mContext;
    private List<HouseDeviceInfo.DeviceHouseListBean> mList;

    public LvDeviceAdapter(Context context, List list) {
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
        MyViewholder viewholder;
        if (null == view) {
            viewholder = new MyViewholder();
            view = View.inflate(mContext, R.layout.adpter_dev, null);
            viewholder.img_kind = view.findViewById(R.id.img_kind);
            viewholder.img_move = view.findViewById(R.id.img_move);
            viewholder.img_onl = view.findViewById(R.id.img_onl);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.tv_onl = view.findViewById(R.id.tv_onl);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getDevice_name());
        if ("HAir(有线)".equals(mList.get(i).getDefault_device_type())) {//空气哨兵
            viewholder.img_onl.setVisibility(View.VISIBLE);
            viewholder.tv_onl.setTextColor(mContext.getResources().getColor(R.color.blue_87));
            viewholder.tv_onl.setTextSize(14f);
            viewholder.tv_onl.setBackground(null);
            if (0 == mList.get(i).getType()) {
                viewholder.tv_onl.setText("offline");
            } else {
                viewholder.tv_onl.setText("online");
            }
        } else {//灯
            viewholder.img_onl.setVisibility(View.GONE);
            viewholder.tv_onl.setTextColor(mContext.getResources().getColor(R.color.white));
            viewholder.tv_onl.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_blue_50));
            viewholder.tv_onl.setTextSize(16f);
            if ("0".equals(mList.get(i).getDevice_status())){
                viewholder.tv_onl.setText("OFF");
            }else {
                viewholder.tv_onl.setText("ON");
            }

        }
        viewholder.img_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转设备移动界面
                mContext.startActivity(new Intent(mContext, MoveDeviceActivity.class));
            }
        });
        return view;
    }

    private class MyViewholder {
        ImageView img_kind, img_onl, img_move;
        TextView tv_name, tv_onl;
    }
}
