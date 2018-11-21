package com.bt.Smart.Hox.adapter;

import com.bt.Smart.Hox.messegeInfo.NotHA3ListInfo;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/20 17:20
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RecyItemDragAdapter extends BaseItemDraggableAdapter<NotHA3ListInfo.NotHA3listBean, BaseViewHolder> {
    public RecyItemDragAdapter(List data) {
        super(data);

    }

    @Override
    protected void convert(BaseViewHolder helper, NotHA3ListInfo.NotHA3listBean item) {

    }
}
