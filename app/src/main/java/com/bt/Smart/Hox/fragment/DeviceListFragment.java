package com.bt.Smart.Hox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.AddDeviceActivity;
import com.bt.Smart.Hox.adapter.LvDeviceAdapter;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
import com.bt.Smart.Hox.messegeInfo.HouseDeviceInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.bt.Smart.Hox.viewmodle.MyListView;
import com.bumptech.glide.Glide;
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
    private View            mRootView;
    private LinearLayout    lin_nomsg;//没有信息
    private ImageView       img_loading;//加载设备信息
    private LinearLayout    lin_add;//添加设备
    private MyListView      lv_dev;//设备列表
    private String          mHomeID;//家的ID
    private String          mRoomID;//房间的ID
    private String          mRoomName;//房间的名称
    private List            mData;//设备列表
    private LvDeviceAdapter deviceAdapter;
    private String          mHouseInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_device_list, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        lin_nomsg = mRootView.findViewById(R.id.lin_nomsg);
        img_loading = mRootView.findViewById(R.id.img_loading);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
        lin_add = mRootView.findViewById(R.id.lin_add);
    }

    private void initData() {
        mData = new ArrayList();
        deviceAdapter = new LvDeviceAdapter(getContext(), mData, mRoomID, mRoomName);
        lv_dev.setAdapter(deviceAdapter);
        lin_add.setOnClickListener(this);
        Glide.with(getContext()).load(R.drawable.loadgif).into(img_loading);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_add://跳转设备列表界面
                Intent intent = new Intent(getContext(), AddDeviceActivity.class);
                intent.putExtra("homeID", mHomeID);
                intent.putExtra("roomID", mRoomID);
                intent.putExtra("roomInfo", mHouseInfo);
                startActivity(intent);
                break;
        }
    }

    public void refreshInfo() {
        if ("all".equals(mRoomID)) {//所有设备界面不显示添加按钮
            //lin_add.setVisibility(View.GONE);
        }
        //获取房间设备
        getDeviceOfRoom();
    }

    private void getDeviceOfRoom() {
        if (null == mRoomID || "".equals(mRoomID)) {
            System.out.println(mRoomID);
            ToastUtils.showToast(getContext(), "未查找到房间信息");
            return;
        }
        if (null != lin_nomsg) {
            lin_nomsg.setVisibility(View.VISIBLE);
            if (null != img_loading) {
                img_loading.setVisibility(View.VISIBLE);
            }
        }
        //清空数据
        if (null == mData) {
            mData = new ArrayList();
        } else {
            mData.clear();
        }
        if (null != deviceAdapter)
            deviceAdapter.notifyDataSetChanged();
        //先判断显示的是所有设备还是某个房间的设备
        if ("all".equals(mRoomID)) {//获取家下所有设备
            getAllDevInHome();
        } else {//获取某个房间所有设备
            getAllDevInRoom();
        }
    }

    private void getAllDevInHome() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", mHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SELECTEQLIST, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                img_loading.setVisibility(View.GONE);
                ToastUtils.showToast(getContext(), "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                img_loading.setVisibility(View.GONE);
                if (code != 200) {
                    ToastUtils.showToast(getContext(), "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                AllDevListInfo allDeviceInfo = gson.fromJson(resbody, AllDevListInfo.class);
                if (1 == allDeviceInfo.getCode()) {
                    if (null != allDeviceInfo.getDeviceHomeList() && allDeviceInfo.getDeviceHomeList().size() > 0) {
                        //ps:>0说明房间下有设备
                        lin_nomsg.setVisibility(View.GONE);
                        mData.clear();
                        int num = allDeviceInfo.getDeviceHomeList().size();
                        mData.addAll(allDeviceInfo.getDeviceHomeList());
                        if (null != deviceAdapter) {
                            deviceAdapter.notifyDataSetChanged();
                        }
                        MyApplication.devNum = num;
                    } else {
                        lin_nomsg.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtils.showToast(getContext(), "设备信息查询失败");
                }
            }
        });
    }

    private void getAllDevInRoom() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("house_id", mRoomID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.DEVICE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                img_loading.setVisibility(View.GONE);
                ToastUtils.showToast(getContext(), "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                img_loading.setVisibility(View.GONE);
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
                        mData.clear();
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

    public void setRoomID(String homeID, String roomID, String roomName) {
        mHomeID = homeID;
        mRoomID = roomID;
        mRoomName = roomName;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getRoomID() {
        return mRoomID;
    }

    public void setHouseInfo(String houseInfo) {
        mHouseInfo = houseInfo;
    }
}
