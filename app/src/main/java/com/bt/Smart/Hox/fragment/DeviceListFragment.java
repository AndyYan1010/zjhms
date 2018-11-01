package com.bt.Smart.Hox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.AddDeviceActivity;
import com.bt.Smart.Hox.adapter.LvDeviceAdapter;
import com.bt.Smart.Hox.messegeInfo.HouseDeviceInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
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
 * @创建时间 2018/10/30 8:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceListFragment extends Fragment implements View.OnClickListener {
    private View                                      mRootView;
    private LinearLayout                              lin_nomsg;//没有信息
    private LinearLayout                              lin_add;//添加设备
    private MyListView                                lv_dev;//设备列表
    private String                                    mRoomID;//房间的ID
    private List<HouseDeviceInfo.DeviceHouseListBean> mData;//设备列表
    private LvDeviceAdapter                           deviceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_device_list, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        lin_nomsg = mRootView.findViewById(R.id.lin_nomsg);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
        lin_add = mRootView.findViewById(R.id.lin_add);
    }

    private void initData() {
        //        lin_nomsg.setVisibility(View.GONE);
        mData = new ArrayList();
        deviceAdapter = new LvDeviceAdapter(getContext(), mData);
        lv_dev.setAdapter(deviceAdapter);
        lin_add.setOnClickListener(this);

    }

    public void refreshInfo() {
        getDeviceOfRoom();
    }

    private void getDeviceOfRoom() {
        if (null == mRoomID || "".equals(mRoomID)) {
            System.out.println(mRoomID);
            ToastUtils.showToast(getContext(), "未查找到房间信息");
            return;
        }
        ProgressDialogUtil.startShow(getContext(), "正在加载数据...");
        RequestParamsFM params = new RequestParamsFM();
        params.put("house_id", mRoomID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.DEVICE, params, new HttpOkhUtils.HttpCallBack() {
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
                HouseDeviceInfo houseDeviceInfo = gson.fromJson(resbody, HouseDeviceInfo.class);
                if (1 == houseDeviceInfo.getCode()) {
                    if (null != houseDeviceInfo.getDeviceHouseList() && houseDeviceInfo.getDeviceHouseList().size() > 0) {
                        //ps:>0说明房间下有设备
                        lin_nomsg.setVisibility(View.GONE);
                        if (null == mData) {
                            mData = new ArrayList();
                        } else {
                            mData.clear();
                        }
                        mData.addAll(houseDeviceInfo.getDeviceHouseList());
                        if (null != deviceAdapter) {
                            deviceAdapter.notifyDataSetChanged();
                        }
                    } else {
                        lin_nomsg.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtils.showToast(getContext(), "设备信息查询失败");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_add://跳转设备列表界面
                startActivity(new Intent(getContext(), AddDeviceActivity.class));
                break;
        }
    }

    public void setRoomID(String roomID) {
        mRoomID = roomID;
    }
}
