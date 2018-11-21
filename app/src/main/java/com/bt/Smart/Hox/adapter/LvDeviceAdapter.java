package com.bt.Smart.Hox.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.HAirDetailInfoActivity;
import com.bt.Smart.Hox.activity.homeActivity.MoveDeviceActivity;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
import com.bt.Smart.Hox.messegeInfo.HouseDeviceInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

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
    private Context mContext;
    private List    mList;
    private String  mKind;

    public LvDeviceAdapter(Context context, List list, String kind) {
        this.mContext = context;
        this.mList = list;
        this.mKind = kind;
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
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.lin_right = view.findViewById(R.id.lin_right);
            viewholder.img_onl = view.findViewById(R.id.img_onl);
            viewholder.tv_onl = view.findViewById(R.id.tv_onl);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }

        final String dev_pic;
        final String device_name;
        final String default_device_type;
        int type;
        String device_status;
        final String id;
        final String house_id;
        final String home_id;

        if ("all".equals(mKind)) {//显示所有设备
            //设备名称
            dev_pic = ((AllDevListInfo.DeviceHomeListBean) mList.get(i)).getDevice_img();
            device_name = ((AllDevListInfo.DeviceHomeListBean) mList.get(i)).getDevice_name();
            default_device_type = ((AllDevListInfo.DeviceHomeListBean) mList.get(i)).getDefault_device_type();
            type = ((AllDevListInfo.DeviceHomeListBean) mList.get(i)).getType();
            device_status = ((AllDevListInfo.DeviceHomeListBean) mList.get(i)).getDevice_status();
            id = ((AllDevListInfo.DeviceHomeListBean) mList.get(i)).getId();
            house_id = ((AllDevListInfo.DeviceHomeListBean) mList.get(i)).getRoomid();
            home_id = MyApplication.slecHomeID;
        } else {
            dev_pic = ((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getDevice_img();
            device_name = ((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getDevice_name();
            default_device_type = ((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getDefault_device_type();
            type = ((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getType();
            device_status = ((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getDevice_status();
            id = ((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getId();
            house_id = ((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getHouse_id();
            home_id = ((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getHome_id();
        }
        viewholder.tv_name.setText(device_name);
        GlideLoaderUtil.showImageView(mContext, NetConfig.IMG_FOR_DEV + dev_pic, viewholder.img_kind);
        if ("010".equals(default_device_type)) {

        } else if ("021".equals(default_device_type)) {//计量控制（灯控）
            setLightView(viewholder, device_status);
        } else if ("022".equals(default_device_type)) {//调光控制（灯控）
            setLightView(viewholder, device_status);
        } else if ("031".equals(default_device_type)) {//空气哨兵有线
            setKqsbView(viewholder, type);
        } else if ("032".equals(default_device_type)) {//空气哨兵WiFi
            setKqsbView(viewholder, type);
        } else if ("041".equals(default_device_type)) {//温控器

        } else if ("042".equals(default_device_type)) {//温控器(Lora)

        } else if ("051".equals(default_device_type)) {//红外遥控

        } else if ("052".equals(default_device_type)) {//红外遥控(Lora)

        } else if ("061".equals(default_device_type)) {//Lora无源无线开关

        } else if ("071".equals(default_device_type)) {//多协议转换器

        } else if ("072".equals(default_device_type)) {//多协议转换器(Lora)

        } else if ("081".equals(default_device_type)) {//智能插座

        } else if ("082".equals(default_device_type)) {//智能插座WiFi

        } else if ("091".equals(default_device_type)) {//体脂称

        } else {//其他

        }
        viewholder.img_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转设备移动界面
                Intent intent = new Intent(mContext, MoveDeviceActivity.class);
                intent.putExtra("devName", device_name);
                intent.putExtra("devID", id);
                intent.putExtra("roomID", house_id);
                intent.putExtra("homeID", home_id);
                ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE_MOVE);
            }
        });
        viewholder.lin_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("031".equals(default_device_type) || "032".equals(default_device_type)) {
                    //进入空气哨兵详情页
                    mContext.startActivity(new Intent(mContext, HAirDetailInfoActivity.class));
                }
                if ("022".equals(default_device_type)) {//灯的开关事件

                }
            }
        });
        return view;
    }

    private void setKqsbView(MyViewholder viewholder, int type) {
        viewholder.img_onl.setVisibility(View.VISIBLE);
        viewholder.tv_onl.setTextColor(mContext.getResources().getColor(R.color.blue_87));
        viewholder.tv_onl.setTextSize(14f);
        viewholder.tv_onl.setBackground(null);
        if (0 == type) {
            viewholder.tv_onl.setText("offline");
        } else {
            viewholder.tv_onl.setText("online");
        }
    }

    private void setLightView(MyViewholder viewholder, String device_status) {
        viewholder.img_onl.setVisibility(View.GONE);
        viewholder.tv_onl.setTextColor(mContext.getResources().getColor(R.color.white));
        viewholder.tv_onl.setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_blue_50));
        viewholder.tv_onl.setTextSize(16f);
        if ("0".equals(device_status)) {
            viewholder.tv_onl.setText("OFF");
        } else {
            viewholder.tv_onl.setText("ON");
        }
    }

    private int REQUEST_CODE_MOVE = 1005;

    private class MyViewholder {
        LinearLayout lin_right;
        ImageView    img_kind, img_onl, img_move;
        TextView tv_name, tv_onl;
    }
}
