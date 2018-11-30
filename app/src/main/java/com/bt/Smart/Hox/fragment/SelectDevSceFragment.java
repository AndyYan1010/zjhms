package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvSelectDevAdapter;
import com.bt.Smart.Hox.messegeInfo.NotHA3ListInfo;
import com.bt.Smart.Hox.messegeInfo.SceneListInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
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
 * @创建时间 2018/11/22 16:06
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SelectDevSceFragment extends Fragment implements View.OnClickListener {
    private View                                mRootView;
    private ImageView                           img_back;
    private TextView                            tv_title;
    private TextView                            tv_save;//下一步
    private List<NotHA3ListInfo.NotHA3listBean> mDevData;
    private LvSelectDevAdapter                  selectDevAdapter;
    private ListView                            lv_dev;
    private List<NotHA3ListInfo.NotHA3listBean> mSceData;
    private LvSelectDevAdapter                  selectSceAdapter;
    private ListView                            lv_sce;
    private AddAutoFragment                     mAddAutoFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_select_condev, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_save = mRootView.findViewById(R.id.tv_save);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
        lv_sce = mRootView.findViewById(R.id.lv_sce);
    }

    private void initData() {
        tv_title.setText("选择动作");
        tv_save.setText("确定");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setVisibility(View.VISIBLE);
        tv_save.setOnClickListener(this);
        mDevData = new ArrayList();
        mSceData = new ArrayList();
        selectDevAdapter = new LvSelectDevAdapter(getContext(), mDevData,"dev");
        lv_dev.setAdapter(selectDevAdapter);
        selectSceAdapter = new LvSelectDevAdapter(getContext(), mSceData,"sce");
        lv_sce.setAdapter(selectSceAdapter);
        //获取家下传感设备
        getHomeDevList();
        //获取场景
        getHomeSceList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_save://保存
                //选择完设备或场景
                saveSelectDevAndSce();
                break;
        }
    }

    private void saveSelectDevAndSce() {
        List<NotHA3ListInfo.NotHA3listBean> devList = new ArrayList();
        List<NotHA3ListInfo.NotHA3listBean> sceList = new ArrayList();
        for (NotHA3ListInfo.NotHA3listBean bean : mDevData) {
            if (bean.isIsSelect())
                devList.add(bean);
        }
        for (NotHA3ListInfo.NotHA3listBean bean : mSceData) {
            if (bean.isIsSelect())
                sceList.add(bean);
        }
        mAddAutoFragment.changeActUI(devList,sceList);
        MyFragmentManagerUtil.closeTopFragment(this);
    }

    private void getHomeDevList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.QUERYNOTHA3LIST, params, new HttpOkhUtils.HttpCallBack() {
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
                NotHA3ListInfo notHA3ListInfo = gson.fromJson(resbody, NotHA3ListInfo.class);
                ToastUtils.showToast(getContext(), notHA3ListInfo.getMessage());
                if (1 == notHA3ListInfo.getCode()) {
                    for (NotHA3ListInfo.NotHA3listBean bean : notHA3ListInfo.getNotHA3list()) {
                        NotHA3ListInfo.NotHA3listBean notHA3listBean = new NotHA3ListInfo.NotHA3listBean();
                        notHA3listBean.setIsSelect(false);
                        notHA3listBean.setDevice_name(bean.getDevice_name());
                        notHA3listBean.setId(bean.getId());
                        notHA3listBean.setDevice_status(bean.getDevice_status());
                        notHA3listBean.setDeviceType(bean.getDeviceType());
                        notHA3listBean.setDevice_img(bean.getDevice_img());
                        mDevData.add(notHA3listBean);
                    }
                    selectDevAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getHomeSceList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.QUERYSCENELIST, params, new HttpOkhUtils.HttpCallBack() {
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
                SceneListInfo sceneListInfo = gson.fromJson(resbody, SceneListInfo.class);
                ToastUtils.showToast(getContext(), sceneListInfo.getMessage());
                if (1 == sceneListInfo.getCode()) {
                    for (SceneListInfo.ScenelistBean bean : sceneListInfo.getScenelist()) {
                        NotHA3ListInfo.NotHA3listBean notHA3listBean = new NotHA3ListInfo.NotHA3listBean();
                        notHA3listBean.setIsSelect(false);
                        notHA3listBean.setDevice_name(bean.getScene_name());
                        notHA3listBean.setId(bean.getId());
                        notHA3listBean.setDevice_status(bean.getScene_status());
                        notHA3listBean.setDevice_img(bean.getScene_pic());
                        mSceData.add(notHA3listBean);
                    }
                    selectSceAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void setUpFragment(AddAutoFragment addAutoFragment) {
        mAddAutoFragment = addAutoFragment;
    }
}
