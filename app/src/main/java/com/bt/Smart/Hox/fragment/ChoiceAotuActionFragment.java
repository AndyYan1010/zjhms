package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvAllSceDevAdapter;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
import com.bt.Smart.Hox.messegeInfo.AllSceneListInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.bt.Smart.Hox.viewmodle.MyListView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/7 16:59
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ChoiceAotuActionFragment extends Fragment implements View.OnClickListener {
    private View               mRootView;
    private ImageView          img_back;
    private TextView           tv_title;
    private RelativeLayout     rlt_allSce;
    private ImageView          img_more_sce;
    private List               mSceneData;//场景数据
    private List               mDevData;//设备数据
    private MyListView         lv_scene;
    private LvAllSceDevAdapter allSceneAdapter;
    private LvAllSceDevAdapter allDevAdapter;
    private RelativeLayout     rlt_allDev;
    private ImageView          img_more_dev;
    private MyListView         lv_dev;
    private TextView           tv_sure;//确定选择
    private boolean            isAllSceCho;
    private boolean            isAllDevCho;
    private AutoShowFragment   mAutoShowFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_choice_auto_act, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        rlt_allSce = mRootView.findViewById(R.id.rlt_allSce);
        img_more_sce = mRootView.findViewById(R.id.img_more_sce);
        lv_scene = mRootView.findViewById(R.id.lv_scene);
        rlt_allDev = mRootView.findViewById(R.id.rlt_allDev);
        img_more_dev = mRootView.findViewById(R.id.img_more_dev);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
        tv_sure = mRootView.findViewById(R.id.tv_sure);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("自动化动作");
        mSceneData = new ArrayList();
        mDevData = new ArrayList();
        allSceneAdapter = new LvAllSceDevAdapter(getContext(), mSceneData, "Sce");
        lv_scene.setAdapter(allSceneAdapter);
        allDevAdapter = new LvAllSceDevAdapter(getContext(), mDevData, "Dev");
        lv_dev.setAdapter(allDevAdapter);
        img_more_sce.setOnClickListener(this);
        img_more_dev.setOnClickListener(this);
        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.img_more_sce://展示更多场景
                showMoreScene();
                break;
            case R.id.img_more_dev:
                showMoreDev();
                break;
            case R.id.tv_sure:
                //关闭当前界面，并把选择的场景或设备传过去
                closeAndChangeUI();
                break;
        }
    }

    private void closeAndChangeUI() {
        MyFragmentManagerUtil.closeTopFragment(this);
        mAutoShowFragment.showActionView();
    }

    private void showMoreDev() {
        isAllDevCho = !isAllDevCho;
        if (isAllDevCho) {
            img_more_dev.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_up));
            if (mDevData.size() == 0) {
                //获取全部场景
                getAllDevList();
            } else {
                lv_dev.setVisibility(View.VISIBLE);
            }
        } else {
            img_more_dev.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_down));
            lv_dev.setVisibility(View.GONE);
        }
    }

    private void getAllDevList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SELECTEQLIST, params, new HttpOkhUtils.HttpCallBack() {
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
                AllDevListInfo allDevListInfo = gson.fromJson(resbody, AllDevListInfo.class);
                if (1 == allDevListInfo.getCode()) {
                    mDevData.addAll(allDevListInfo.getDeviceHomeList());
                    allDevAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void showMoreScene() {
        isAllSceCho = !isAllSceCho;
        if (isAllSceCho) {
            img_more_sce.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_up));
            if (mSceneData.size() == 0) {
                //获取全部场景
                getAllSceneList();
            } else {
                lv_scene.setVisibility(View.VISIBLE);
            }
        } else {
            img_more_sce.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_down));
            lv_scene.setVisibility(View.GONE);
        }
    }

    private void getAllSceneList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SELECTALLSCENARIO, params, new HttpOkhUtils.HttpCallBack() {
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
                AllSceneListInfo allSceneListInfo = gson.fromJson(resbody, AllSceneListInfo.class);
                if (1 == allSceneListInfo.getResult()) {
                    mSceneData.addAll(allSceneListInfo.getScenariolist());
                    allSceneAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void setAutoShowFragment(AutoShowFragment autoShowFragment) {
        mAutoShowFragment = autoShowFragment;
    }
}
