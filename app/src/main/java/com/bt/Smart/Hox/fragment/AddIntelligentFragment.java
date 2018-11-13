package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvSceneAutoAdapter;
import com.bt.Smart.Hox.messegeInfo.AutoListInfo;
import com.bt.Smart.Hox.messegeInfo.SceneInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 9:05
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddIntelligentFragment extends Fragment implements View.OnClickListener {
    private View               mRootView;
    private ImageView          img_back;
    private TextView           tv_title;
    private List               mData;
    private LvSceneAutoAdapter sceneAutoAdapter;
    private ListView           lv_scene;
    private LinearLayout       lin_nomsg;//没有场景
    private LinearLayout       lin_add;//添加
    private String             mKind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_intell, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        lin_nomsg = mRootView.findViewById(R.id.lin_nomsg);
        lv_scene = mRootView.findViewById(R.id.lv_scene);
        lin_add = mRootView.findViewById(R.id.lin_add);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        mKind = getActivity().getIntent().getStringExtra("kind");
        tv_title.setText(mKind);
        mData = new ArrayList();
        sceneAutoAdapter = new LvSceneAutoAdapter(getContext(), mData, mKind);
        lv_scene.setAdapter(sceneAutoAdapter);
        lv_scene.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转详情页面
                if ("场景".equals(mKind)) {//跳转场景详情
                    FragmentTransaction ftt = getFragmentManager().beginTransaction();
                    SceneShowFragment sceneShowFt = new SceneShowFragment();
                    sceneShowFt.setKind("1",((SceneInfo.ScenariolistBean)mData.get(i)).getId());
                    ftt.add(R.id.frame, sceneShowFt, "sceneShowFt");
                    ftt.addToBackStack(null);
                    ftt.commit();
                } else {//跳转自动化详情
                    FragmentTransaction ftt = getFragmentManager().beginTransaction();
                    AutoShowFragment autoShowFt = new AutoShowFragment();
                    //传递数据
                    autoShowFt.setKind("1",((AutoListInfo.AutomationlistBean)mData.get(i)).getId());
                    ftt.add(R.id.frame, autoShowFt, "autoShowFt");
                    ftt.addToBackStack(null);
                    ftt.commit();
                }
            }
        });
        lin_add.setOnClickListener(this);
        //获取列表信息
        getSenceAutoList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.lin_add://跳转添加界面
                if ("场景".equals(mKind)) {
                    FragmentTransaction ftt = getFragmentManager().beginTransaction();
                    SceneShowFragment sceneShowFt = new SceneShowFragment();
                    sceneShowFt.setKind("0",null);
                    ftt.add(R.id.frame, sceneShowFt, "sceneShowFt");
                    ftt.addToBackStack(null);
                    ftt.commit();
                } else {
                    FragmentTransaction ftt = getFragmentManager().beginTransaction();
                    AutoShowFragment autoShowFt = new AutoShowFragment();
                    autoShowFt.setKind("0",null);
                    ftt.add(R.id.frame, autoShowFt, "autoShowFt");
                    ftt.addToBackStack(null);
                    ftt.commit();
                }
                break;
        }
    }

    private void getSenceAutoList() {
        if ("场景".equals(mKind)) {//获取场景列表
            getSenceList();
        } else {//获取自动化列表
            getAutoList();
        }
    }

    private void getAutoList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SELECTALLAUTOMATION, params, new HttpOkhUtils.HttpCallBack() {
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
                AutoListInfo autoInfo = gson.fromJson(resbody, AutoListInfo.class);
                ToastUtils.showToast(getContext(), autoInfo.getMessage());
                if (1 == autoInfo.getResult()) {
                    mData.clear();
                    if (autoInfo.getAutomationlist().size() > 0) {
                        lin_nomsg.setVisibility(View.GONE);
                    } else {
                        lin_nomsg.setVisibility(View.VISIBLE);
                    }
                    mData.addAll(autoInfo.getAutomationlist());
                    sceneAutoAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getSenceList() {
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
                SceneInfo sceneInfo = gson.fromJson(resbody, SceneInfo.class);
                ToastUtils.showToast(getContext(), sceneInfo.getMessage());
                if (1 == sceneInfo.getResult()) {
                    mData.clear();
                    if (sceneInfo.getScenariolist().size() > 0) {
                        lin_nomsg.setVisibility(View.GONE);
                    } else {
                        lin_nomsg.setVisibility(View.VISIBLE);
                    }
                    mData.addAll(sceneInfo.getScenariolist());
                    sceneAutoAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public void refreshInfo() {
        //获取列表信息
        getSenceAutoList();
    }
}
