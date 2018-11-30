package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvSelectDevAdapter;
import com.bt.Smart.Hox.messegeInfo.NotHA3ListInfo;
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
 * @创建时间 2018/11/18 20:00
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddActFragment extends Fragment implements View.OnClickListener {
    private View                                mRootView;
    private ImageView                           img_back;
    private TextView                            tv_title;
    private TextView                            tv_save;//下一步
    private RelativeLayout                      rlt_choice_auto;
    private ListView                            lv_dev;
    private List<NotHA3ListInfo.NotHA3listBean> mData;
    private LvSelectDevAdapter                  selectDevAdapter;
    private AddSceneFragment                    mAddSceneFragment;//上一个fragment
    private List<NotHA3ListInfo.NotHA3listBean> mSelectDevList;//选择的设备

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_act, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_save = mRootView.findViewById(R.id.tv_save);
        rlt_choice_auto = mRootView.findViewById(R.id.rlt_choice_auto);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
    }

    private void initData() {
        tv_title.setText("选择动作");
        tv_save.setText("确定");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setVisibility(View.VISIBLE);
        tv_save.setOnClickListener(this);
        rlt_choice_auto.setOnClickListener(this);
        mData = new ArrayList();
        selectDevAdapter = new LvSelectDevAdapter(getContext(), mData,"dev");
        lv_dev.setAdapter(selectDevAdapter);
        //获取家下设备
        getHomeDeviceList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.rlt_choice_auto:

                break;
            case R.id.tv_save://确定
                if (null == mSelectDevList) {
                    mSelectDevList = new ArrayList<>();
                } else {
                    mSelectDevList.clear();
                }
                //将选择的设备信息添加到上一个fragment
                for (NotHA3ListInfo.NotHA3listBean bean : mData) {
                    if (bean.isIsSelect()) {
                        mSelectDevList.add(bean);
                    }
                }
                mAddSceneFragment.addActListInfo(mSelectDevList);
                //关闭页面，
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
        }
    }

    private void getHomeDeviceList() {
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
                    mData.addAll(notHA3ListInfo.getNotHA3list());
                    selectDevAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void setUpFragment(AddSceneFragment addSceneFragment) {
        mAddSceneFragment = addSceneFragment;
    }
}
