package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.NotHA3ListInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/20 13:38
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvSelectDevAdapter extends BaseAdapter {
    private List<NotHA3ListInfo.NotHA3listBean> mList;
    private Context                             mContext;
    private String                              mType;

    public LvSelectDevAdapter(Context context, List<NotHA3ListInfo.NotHA3listBean> list, String type) {
        this.mContext = context;
        this.mList = list;
        this.mType = type;
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
            view = View.inflate(mContext, R.layout.adpter_choice_dev, null);
            viewholder.img_dev = view.findViewById(R.id.img_dev);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.cb_choice = view.findViewById(R.id.cb_choice);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        if ("dev".equals(mType)){
            GlideLoaderUtil.showImgWithIcon(mContext, NetConfig.IMG_FOR_DEV + mList.get(i).getDevice_img().replaceAll("\\\\", "/"), R.drawable.single_production, R.drawable.single_production, viewholder.img_dev);
        }else {
            GlideLoaderUtil.showImgWithIcon(mContext, mList.get(i).getDevice_img(), R.drawable.single_production, R.drawable.single_production, viewholder.img_dev);
        }
        viewholder.tv_name.setText(mList.get(i).getDevice_name());
        viewholder.cb_choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mList.get(i).setIsSelect(true);
                } else {
                    mList.get(i).setIsSelect(false);
                }
            }
        });
        return view;
    }

    private class MyViewholder {
        ImageView img_dev;
        TextView  tv_name;
        CheckBox  cb_choice;
    }
}
