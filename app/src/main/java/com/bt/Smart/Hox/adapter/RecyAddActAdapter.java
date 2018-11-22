package com.bt.Smart.Hox.adapter;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.SceneDevListInfo;
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

public class RecyAddActAdapter extends BaseQuickAdapter<SceneDevListInfo, BaseViewHolder> {
    public RecyAddActAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SceneDevListInfo item) {
        helper.setText(R.id.tv_name, item.getDevice_name());
        if ("0".equals(item.getDevice_status())) {
            helper.setText(R.id.tv_state, "关闭");
        } else {
            helper.setText(R.id.tv_state, "打开");
        }
        if ("0001".equals(item.getDevice_value())) {
            helper.setText(R.id.tv_ld, "调灯50%亮");
        } else if ("0002".equals(item.getDevice_value())) {
            helper.setText(R.id.tv_ld, "调灯70%亮");
        } else {
            helper.setText(R.id.tv_ld, "调灯100%亮");
        }
        helper.addOnClickListener(R.id.tv_ld).addOnClickListener(R.id.tv_state).addOnClickListener(R.id.img_delet);
    }
}
