package com.bt.Smart.Hox.adapter;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AutoCondInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/23 9:16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RecyAddCondAdapter extends BaseQuickAdapter<AutoCondInfo, BaseViewHolder> {
    private List<AutoCondInfo> mData;

    public RecyAddCondAdapter(int layoutResId, List<AutoCondInfo> data) {
        super(layoutResId, data);
        this.mData = data;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final AutoCondInfo item) {
        helper.setText(R.id.tv_name, item.getDevice_name());
        helper.setText(R.id.tv_kind, item.getSelect_type());
        helper.setText(R.id.tv_dx, item.getSelect_if());
        helper.setText(R.id.tv_value, item.getValue());
        helper.addOnClickListener(R.id.tv_kind).addOnClickListener(R.id.tv_dx).addOnClickListener(R.id.tv_value).addOnClickListener(R.id.img_delet);
    }
}
