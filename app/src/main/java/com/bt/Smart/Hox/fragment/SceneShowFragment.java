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
import com.bt.Smart.Hox.messegeInfo.SceneDetailInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

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
    private ImageView      img_icon;
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
    private String         mSceneID;

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
        img_icon = (ImageView) mRootView.findViewById(R.id.img_icon);
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
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("场景");

        if ("0".equals(mKind)) {
            tv_delete.setVisibility(View.GONE);
        } else if ("1".equals(mKind)) {
            //获取场景详情
            getSceneDetail();
        }

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
                    changeScene();
                }
                break;
            case R.id.tv_delete://删除

                break;
        }
    }

    private void getSceneDetail() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mSceneID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SELECTNOSCENARIO, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(getContext(), "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(getContext(), "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                SceneDetailInfo sceneDetailInfo = gson.fromJson(resbody, SceneDetailInfo.class);
                ToastUtils.showToast(getContext(), sceneDetailInfo.getMessage());
                if (1 == sceneDetailInfo.getResult()) {
                    et_name.setText(sceneDetailInfo.getScenario().getScenarios().getScene_name());
                    SceneDetailInfo.ScenarioBean.ScenariosBean.SceneTimingBean.TimeControlBean time_control = sceneDetailInfo.getScenario().getScenarios().getScene_timing().getTime_control();
                    if (null == time_control.getSingleTime() || "".equals(time_control.getSingleTime())) {
                        tv_timing.setText("");
                    } else if ("1".equals(time_control.getSingleTime())) {
                        tv_timing.setText("一、三、五、七    " + sceneDetailInfo.getScenario().getScenarios().getScene_timing().getTime_start() + "—" + sceneDetailInfo.getScenario().getScenarios().getScene_timing().getTime_end());
                    } else {
                        String date = "";
                        if ("1".equals(time_control.getMonday())) {
                            date = date + "一";
                        } else if ("1".equals(time_control.getThursday())) {
                            if ("".equals(date)) {
                                date = date + "二";
                            } else {
                                date = date + "、二";
                            }
                        } else if ("1".equals(time_control.getWednesday())) {
                            if ("".equals(date)) {
                                date = date + "三";
                            } else {
                                date = date + "、三";
                            }

                        } else if ("1".equals(time_control.getThursday())) {
                            if ("".equals(date)) {
                                date = date + "四";
                            } else {
                                date = date + "、四";
                            }
                        } else if ("1".equals(time_control.getFriday())) {
                            if ("".equals(date)) {
                                date = date + "五";
                            } else {
                                date = date + "、五";
                            }
                        } else if ("1".equals(time_control.getSaturday())) {
                            if ("".equals(date)) {
                                date = date + "六";
                            } else {
                                date = date + "、六";
                            }
                        } else if ("1".equals(time_control.getSunday())) {
                            if ("".equals(date)) {
                                date = date + "七";
                            } else {
                                date = date + "、七";
                            }
                        }
                        if ("".equals(date)) {
                            tv_timing.setText("");
                        } else {
                            tv_timing.setText(date + "  " + sceneDetailInfo.getScenario().getScenarios().getScene_timing().getTime_start() + "—" + sceneDetailInfo.getScenario().getScenarios().getScene_timing().getTime_end());
                        }
                    }
                    GlideLoaderUtil.showImageView(getContext(), sceneDetailInfo.getScenario().getScenarios().getScene_Room_Icon(), img_icon);
                    tv_grpnum.setText(sceneDetailInfo.getScenario().getScenarios().getScene_EQList().size() + "个");
                    tv_autogroup.setText(sceneDetailInfo.getScenario().getScenarios().getScene_AutomaticList().size() + "个");
                }
            }
        });
    }

    private void changeScene() {


    }

    private void addSceneShow() {
        RequestParamsFM params = new RequestParamsFM();
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

    public void setKind(String kind, String sceneID) {
        mKind = kind;
        mSceneID = sceneID;
    }
}
