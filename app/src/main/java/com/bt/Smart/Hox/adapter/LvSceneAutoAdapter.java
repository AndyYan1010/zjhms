package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AutoListInfo;
import com.bt.Smart.Hox.messegeInfo.SceneInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 9:26
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvSceneAutoAdapter extends BaseAdapter {
    private Context mContext;
    private List    mList;
    private String  mKind;

    public LvSceneAutoAdapter(Context context, List list, String kind) {
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
        final MyViewholder viewholder;
        if (null == view) {
            viewholder = new MyViewholder();
            view = View.inflate(mContext, R.layout.adpter_scene_auto, null);
            viewholder.img_scene = view.findViewById(R.id.img_scene);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.swc_scene = view.findViewById(R.id.swc_scene);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        if ("场景".equals(mKind)) {
            GlideLoaderUtil.showImageView(mContext, ((SceneInfo.ScenariolistBean) mList.get(i)).getScenario_img(), viewholder.img_scene);
            viewholder.tv_name.setText(((SceneInfo.ScenariolistBean) mList.get(i)).getFname());
            if ("0".equals(((SceneInfo.ScenariolistBean) mList.get(i)).getOn_off_status())) {
                viewholder.swc_scene.setChecked(false);
            } else {
                viewholder.swc_scene.setChecked(true);
            }
        } else {
            viewholder.tv_name.setText(((AutoListInfo.AutomationlistBean) mList.get(i)).getFname());
            if ("0".equals(((AutoListInfo.AutomationlistBean) mList.get(i)).getOn_off_status())) {
                viewholder.swc_scene.setChecked(false);
            } else {
                viewholder.swc_scene.setChecked(true);
            }
        }
        return view;
    }

    private class MyViewholder {
        SwitchCompat swc_scene;
        ImageView img_scene;
        TextView  tv_name;
    }
}
