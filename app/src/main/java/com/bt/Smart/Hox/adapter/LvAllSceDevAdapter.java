package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
import com.bt.Smart.Hox.messegeInfo.AllSceneListInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/8 15:07
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvAllSceDevAdapter extends BaseAdapter {
    private List    mList;//AllSceneListInfo.ScenariolistBean    //
    private Context mContext;
    private String  mKind;

    public LvAllSceDevAdapter(Context context, List list, String kind) {
        this.mContext = context;
        this.mList = list;
        this.mKind = kind;
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
        if ("Sce".equals(mKind)){
            viewholder.tv_name.setText(((AllSceneListInfo.ScenariolistBean)mList.get(i)).getFname());
        }else {
            viewholder.tv_name.setText(((AllDevListInfo.DeviceHomeListBean)mList.get(i)).getDevice_name());
        }

        return view;
    }

    private class MyViewholder {
        ImageView img_scene;
        CheckBox  cb_choice;
        TextView  tv_name;
    }
}
