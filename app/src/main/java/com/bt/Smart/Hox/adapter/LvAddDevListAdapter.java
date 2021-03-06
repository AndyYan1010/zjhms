package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.DeviceTypeInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/5 15:00
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvAddDevListAdapter extends BaseAdapter {
    private Context                                          mContext;
    private List<DeviceTypeInfo.DeviceTypeListBean.DataBean> mList;

    public LvAddDevListAdapter(Context context, List<DeviceTypeInfo.DeviceTypeListBean.DataBean> list) {
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
            view = View.inflate(mContext, R.layout.adpter_add_dev_list, null);
            viewholder.img_kind = view.findViewById(R.id.img_kind);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getDeviceDescibe());
        GlideLoaderUtil.showImageView(mContext, NetConfig.IMG_FOR_DEV + mList.get(i).getDevcieTypePic().replaceAll("\\\\","/"), viewholder.img_kind);
        return view;
    }

    private class MyViewholder {
        ImageView img_kind;
        TextView  tv_name;
    }
}
