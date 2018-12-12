package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/20 14:07
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RecyDevAdapter extends BaseQuickAdapter<AllDevListInfo.DeviceHomeListBean, BaseViewHolder> {
    private Context mContext;

    public RecyDevAdapter(int layoutResId, Context context, List data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AllDevListInfo.DeviceHomeListBean item) {
        final String dev_pic;
        final String device_name;
        final String device_type;
        final String default_device_type;
        int type;
        final String device_status;
        final String id;
        final String house_id;
        final String room_name;
        final String home_id;
        final String main_control_name;
        final String second_control_name;
        final String main_control_code;
        final String device_code;
        final String control_type;

        //设备名称
        dev_pic = item.getDevice_img();
        device_name = item.getDevice_name();
        device_type = item.getDeviceType();
        room_name = item.getHouse_name();
        default_device_type = item.getDefault_device_type();
        type = item.getType();
        device_status = item.getDevice_status();
        id = item.getId();
        home_id = MyApplication.slecHomeID;
        house_id = item.getRoomid();
        main_control_name = item.getMain_control_name();
        second_control_name = item.getSecond_control_name();
        main_control_code = item.getMaster_control();
        device_code = item.getDevice_code();
        control_type = item.getDeviceType();

        helper.setText(R.id.tv_name, device_name);
        helper.setText(R.id.tv_room, room_name);
        helper.setText(R.id.tv_code, device_code);
        helper.setText(R.id.tv_main, main_control_name);
        helper.setText(R.id.tv_second, second_control_name);
        GlideLoaderUtil.showImgWithIcon(mContext, NetConfig.IMG_FOR_DEV + dev_pic.replaceAll("\\\\", "/"), R.drawable.single_production, R.drawable.single_production, (ImageView) helper.getView(R.id.img_kind));

        if ("2".equals(device_type)) {
            helper.getView(R.id.lin_zk).setVisibility(View.GONE);
            helper.getView(R.id.lin_ck).setVisibility(View.GONE);
        }

        if ("010".equals(default_device_type)) {

        } else if ("021".equals(default_device_type)) {//计量控制（灯控）
            setLightView(helper, device_status);
        } else if ("022".equals(default_device_type)) {//调光控制（灯控）
            setLightView(helper, device_status);
        } else if ("031".equals(default_device_type)) {//空气哨兵有线
            setKqsbView(helper, type);
        } else if ("032".equals(default_device_type)) {//空气哨兵WiFi
            setKqsbView(helper, type);
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
        helper.addOnClickListener(R.id.img_move).addOnClickListener(R.id.lin_right).addOnClickListener(R.id.tv_onl);
    }

    private void setKqsbView(BaseViewHolder helper, int type) {
        helper.getView(R.id.img_onl).setVisibility(View.VISIBLE);
        ((TextView) helper.getView(R.id.tv_onl)).setTextColor(mContext.getResources().getColor(R.color.blue_87));
        ((TextView) helper.getView(R.id.tv_onl)).setTextSize(14f);
        ((TextView) helper.getView(R.id.tv_onl)).setBackground(null);
        if (0 == type) {
            ((TextView) helper.getView(R.id.tv_onl)).setText("offline");
        } else {
            ((TextView) helper.getView(R.id.tv_onl)).setText("online");
        }
    }

    private void setLightView(BaseViewHolder helper, String device_status) {

        ((TextView) helper.getView(R.id.tv_onl)).setTextColor(mContext.getResources().getColor(R.color.white));
        ((TextView) helper.getView(R.id.tv_onl)).setBackground(mContext.getResources().getDrawable(R.drawable.bg_round_blue_50));
        ((TextView) helper.getView(R.id.tv_onl)).setTextSize(16f);
        if ("0".equals(device_status)) {
            ((TextView) helper.getView(R.id.tv_onl)).setText("OFF");
        } else {
            ((TextView) helper.getView(R.id.tv_onl)).setText("ON");
        }
    }
}
