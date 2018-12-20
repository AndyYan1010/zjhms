package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AutoListInfo;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.SceneInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

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
            GlideLoaderUtil.showImageView(mContext, ((SceneInfo.ScenelistBean) mList.get(i)).getScene_pic(), viewholder.img_scene);
            viewholder.tv_name.setText(((SceneInfo.ScenelistBean) mList.get(i)).getScene_name());
            if ("0".equals(((SceneInfo.ScenelistBean) mList.get(i)).getScene_status())) {
                viewholder.swc_scene.setChecked(false);
            } else {
                viewholder.swc_scene.setChecked(true);
            }
            viewholder.swc_scene.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String statue;
                    if (viewholder.swc_scene.isChecked()) {
                        statue = "0";
                    } else {
                        statue = "1";
                    }
                    //更新场景
                    upDataScene(((SceneInfo.ScenelistBean) mList.get(i)).getId(), statue);
                }
            });
        } else {//自动化
            viewholder.tv_name.setText(((AutoListInfo.AutolistBean) mList.get(i)).getAuto_name());
            if ("0".equals(((AutoListInfo.AutolistBean) mList.get(i)).getAuto_status())) {
                viewholder.swc_scene.setChecked(false);
            } else {
                viewholder.swc_scene.setChecked(true);
            }
            viewholder.swc_scene.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String statue;
                    if (viewholder.swc_scene.isChecked()) {
                        statue = "0";
                    } else {
                        statue = "1";
                    }
                    //更新自动化
                    upDataAuto(((AutoListInfo.AutolistBean) mList.get(i)).getId(), ((AutoListInfo.AutolistBean) mList.get(i)).getIf_ha3_code(), statue);
                }
            });
        }
        return view;
    }

    private void upDataAuto(String id, String main_control_code, String statue) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", id);
        params.put("main_control_code", main_control_code);
        params.put("status", statue);
        HttpOkhUtils.getInstance().doPut(NetConfig.UPDATEAUTOSTATUS, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(mContext, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(mContext, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(mContext, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {

                }
            }
        });
    }

    private void upDataScene(String id, String statue) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", id);
        params.put("status", statue);
        HttpOkhUtils.getInstance().doPut(NetConfig.UPDATESTATUS, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(mContext, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(mContext, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(mContext, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {

                }
            }
        });
    }

    private class MyViewholder {
        SwitchCompat swc_scene;
        ImageView    img_scene;
        TextView     tv_name;
    }
}
