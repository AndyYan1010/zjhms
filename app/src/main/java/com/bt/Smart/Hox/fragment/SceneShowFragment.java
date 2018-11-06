package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.RequestParamsFM;

import java.io.IOException;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 13:49
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneShowFragment extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private ImageView      img_back;
    private TextView       tv_title;
    private EditText       et_name;
    private RelativeLayout rlt_timing;//定时
    private RelativeLayout rlt_group;//设备组合
    private RelativeLayout rlt_auto;//自动化组合
    private TextView       tv_timing;
    private TextView       tv_grpnum;
    private TextView       tv_autogroup;
    private TextView       tv_save;//保存
    private TextView       tv_delete;//删除
    private String         mKind;//显示哪种界面//0是添加//1是修改

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_scene_show, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = (ImageView) mRootView.findViewById(R.id.img_back);
        tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
        et_name = (EditText) mRootView.findViewById(R.id.et_name);
        rlt_timing = (RelativeLayout) mRootView.findViewById(R.id.rlt_timing);
        rlt_group = (RelativeLayout) mRootView.findViewById(R.id.rlt_group);
        rlt_auto = (RelativeLayout) mRootView.findViewById(R.id.rlt_auto);
        tv_timing = (TextView) mRootView.findViewById(R.id.tv_timing);
        tv_grpnum = (TextView) mRootView.findViewById(R.id.tv_grpnum);
        tv_autogroup = (TextView) mRootView.findViewById(R.id.tv_autogroup);
        tv_save = (TextView) mRootView.findViewById(R.id.tv_save);
        tv_delete = (TextView) mRootView.findViewById(R.id.tv_delete);
    }

    private void initData() {
        mKind = getActivity().getIntent().getStringExtra("kind");
        if ("0".equals(mKind)) {
            tv_delete.setVisibility(View.GONE);
        } else if ("1".equals(mKind)) {

        }
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("场景");
        rlt_timing.setOnClickListener(this);
        rlt_group.setOnClickListener(this);
        rlt_auto.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                //弹出回退栈最上面的fragment
                getFragmentManager().popBackStackImmediate(null, 0);
                break;
            case R.id.rlt_timing://跳转定时界面
                SceneTimingFragment sceneTimingfrg = new SceneTimingFragment();
                FragmentTransaction ftt = getFragmentManager().beginTransaction();
                ftt.add(R.id.frame, sceneTimingfrg, "sceneTimingfrg");
                ftt.addToBackStack(null);
                ftt.commit();
                break;
            case R.id.rlt_group://跳转设备组合界面

                break;
            case R.id.rlt_auto://跳转自动化组合界面

                break;
            case R.id.tv_save://保存
                if ("0".equals(mKind)) {//添加
                    addSceneShow();
                } else if ("1".equals(mKind)) {//修改

                }
                break;
            case R.id.tv_delete://删除

                break;
        }
    }

    private void addSceneShow() {
        RequestParamsFM params=new RequestParamsFM();
//        params.put("house_id",);
//        params.put("id",);
//        params.put("scenarios",);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.INSERTSCENARIO, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {

            }

            @Override
            public void onSuccess(int code, String resbody) {

            }
        });
    }

    public void setKind(String kind) {
        mKind = kind;
    }
}
