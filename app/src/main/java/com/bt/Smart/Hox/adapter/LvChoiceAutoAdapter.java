package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AutoListInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/14 15:51
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvChoiceAutoAdapter extends BaseAdapter {
    private Context                               mContext;
    private List<AutoListInfo.AutolistBean> mList;

    public LvChoiceAutoAdapter(Context context, List<AutoListInfo.AutolistBean> list) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyViewholder viewholder;
        if (null == view) {
            viewholder = new MyViewholder();
            view = View.inflate(mContext, R.layout.adpter_all_scene, null);
            viewholder.img_scene = view.findViewById(R.id.img_scene);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.cb_choice = view.findViewById(R.id.cb_choice);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.img_scene.setVisibility(View.INVISIBLE);
        viewholder.tv_name.setText(mList.get(i).getAuto_name());
        return view;
    }

    private class MyViewholder {
        ImageView img_scene;
        CheckBox  cb_choice;
        TextView  tv_name;
    }
}
