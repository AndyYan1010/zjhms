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
import com.bt.Smart.Hox.adapter.LvChoiceDevAdapter;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
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
 * @创建时间 2018/11/14 10:26
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneChoiceDevFragment extends Fragment implements View.OnClickListener {
    private View                                    mRootView;
    private ImageView                               img_back;
    private TextView                                tv_title;
    private TextView                                tv_sure;
    private ListView                                lv_dev;
    private List<AllDevListInfo.DeviceHomeListBean> mData;
    private LvChoiceDevAdapter                      choiceDevAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_choice_auto, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_sure = mRootView.findViewById(R.id.tv_sure);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("添加设备");
        tv_sure.setOnClickListener(this);
        mData = new ArrayList();
        choiceDevAdapter = new LvChoiceDevAdapter(getContext(), mData);
        lv_dev.setAdapter(choiceDevAdapter);
        //获取家下所有设备
        getAllDevList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_sure:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
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
                    mData.addAll(allDevListInfo.getDeviceHomeList());
                    choiceDevAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
