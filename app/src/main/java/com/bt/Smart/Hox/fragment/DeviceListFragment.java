package com.bt.Smart.Hox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.AddDeviceActivity;
import com.bt.Smart.Hox.activity.homeActivity.HAirDetailInfoActivity;
import com.bt.Smart.Hox.activity.homeActivity.LightMeasureInfoActivity;
import com.bt.Smart.Hox.activity.homeActivity.MoveDeviceActivity;
import com.bt.Smart.Hox.adapter.LvDeviceAdapter;
import com.bt.Smart.Hox.adapter.RecyDevAdapter;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
import com.bt.Smart.Hox.messegeInfo.DeviceControlInfo;
import com.bt.Smart.Hox.messegeInfo.DeviceSequenceInfo;
import com.bt.Smart.Hox.messegeInfo.HouseDeviceInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.bt.Smart.Hox.viewmodle.MyListView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

import static com.chad.library.adapter.base.BaseQuickAdapter.SLIDEIN_RIGHT;

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
    private RecyclerView    rec_dev;
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
        rec_dev = mRootView.findViewById(R.id.rec_dev);
        img_loading = mRootView.findViewById(R.id.img_loading);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
        lin_add = mRootView.findViewById(R.id.lin_add);
    }

    private RecyDevAdapter addActAdapter;

    private void initData() {
        mData = new ArrayList();
        lin_add.setOnClickListener(this);
        Glide.with(getContext()).load(R.drawable.loadgif).into(img_loading);

        if ("all".equals(mRoomID)) {//获取家下所有设备
            rec_dev.setLayoutManager(new LinearLayoutManager(getContext()));
            //            //解决数据加载不完的问题
            //            rec_dev.setNestedScrollingEnabled(false);
            //            rec_dev.setHasFixedSize(true);
            //            //解决数据加载完成后, 没有停留在顶部的问题
            //            rec_dev.setFocusable(false);
            //            rec_dev.setNestedScrollingEnabled(false);
            addActAdapter = new RecyDevAdapter(R.layout.adpter_dev, getContext(), mData);
            addActAdapter.openLoadAnimation(SLIDEIN_RIGHT);
            rec_dev.setAdapter(addActAdapter);
            addActAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.img_move:
                            String device_name = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getDevice_name();
                            String id = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getId();
                            String house_id = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getRoomid();
                            String home_id = MyApplication.slecHomeID;
                            toMovePage(device_name, id, house_id, home_id);
                            break;
                        case R.id.lin_right:
                            String default_device_type = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getDefault_device_type();
                            String dev_id = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getDevice_code();
                            toDevDetail(default_device_type, dev_id, position);
                            break;
                        case R.id.tv_onl:
                            String default_device_type1 = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getDefault_device_type();
                            String main_control_code = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getMaster_control();
                            String device_status = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getDevice_status();
                            String device_code = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getDevice_code();
                            String control_type = ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).getControltype();
                            toOpenCloseLight(default_device_type1, main_control_code, device_status, device_code, control_type, position);
                            break;
                    }
                }
            });
        } else {
            deviceAdapter = new LvDeviceAdapter(getContext(), mData, mRoomID, mRoomName);
            lv_dev.setAdapter(deviceAdapter);
        }
    }

    private void toOpenCloseLight(String default_device_type, String main_control_code, String device_status, String device_code, String control_type, int i) {
        if ("022".equals(default_device_type) || "021".equals(default_device_type)) {//灯的开关事件
            //获取命令序列号
            getDeviceSequence(main_control_code, device_status, device_code, control_type, i);
        }
    }

    private void getDeviceSequence(final String main_control_code, final String device_status, final String device_code, final String control_type, final int position) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("main_control_code", main_control_code);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.DEVICESEQUENCE, params, new HttpOkhUtils.HttpCallBack() {
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
                DeviceSequenceInfo deviceSequenceInfo = gson.fromJson(resbody, DeviceSequenceInfo.class);
                ToastUtils.showToast(getContext(), deviceSequenceInfo.getMessage());
                if (1 == deviceSequenceInfo.getCode()) {
                    if ("0".equals(device_status)) {
                        openOrClose(deviceSequenceInfo.getSequence_number(), main_control_code, device_code, control_type, "01", "00000000", position);
                    } else {
                        openOrClose(deviceSequenceInfo.getSequence_number(), main_control_code, device_code, control_type, "01", "447a0000", position);
                    }
                }
            }
        });
    }

    private void openOrClose(String sequence_number, String main_control_code, String device_code, String control_type, String control_number, final String control_data, final int position) {
        ProgressDialogUtil.startShow(getContext(), "正在加载开关灯...");
        RequestParamsFM params = new RequestParamsFM();
        params.put("sequence_number", sequence_number);
        params.put("main_control_code", main_control_code);
        params.put("device_code", device_code);
        params.put("control_type", control_type);
        params.put("control_number", control_number);
        params.put("control_data", control_data);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.DEVICECONTROL, params, new HttpOkhUtils.HttpCallBack() {
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
                DeviceControlInfo deviceControlInfo = gson.fromJson(resbody, DeviceControlInfo.class);
                ToastUtils.showToast(getContext(), deviceControlInfo.getMessage());
                if (1 == deviceControlInfo.getCode()) {
                    if ("00000000".equals(control_data)) {//打开
                        ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).setDevice_status("1");
                    } else {
                        ((AllDevListInfo.DeviceHomeListBean) mData.get(position)).setDevice_status("0");
                    }
                    addActAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void toDevDetail(String default_device_type, String dev_id, int i) {
        if ("031".equals(default_device_type) || "032".equals(default_device_type)) {
            //进入空气哨兵详情页
            Intent intent = new Intent(getContext(), HAirDetailInfoActivity.class);
            intent.putExtra("dev_ID", dev_id);
            getContext().startActivity(intent);
        }
        if ("022".equals(default_device_type) || "021".equals(default_device_type)) {
            //进入灯控详情页
            Intent intent = new Intent(getContext(), LightMeasureInfoActivity.class);
            intent.putExtra("dev_ID", dev_id);
            getContext().startActivity(intent);
        }
    }

    private int REQUEST_CODE_MOVE = 1005;

    private void toMovePage(String device_name, String id, String house_id, String home_id) {
        //跳转设备移动界面
        Intent intent = new Intent(getContext(), MoveDeviceActivity.class);
        intent.putExtra("devName", device_name);
        intent.putExtra("devID", id);
        intent.putExtra("roomID", house_id);
        intent.putExtra("homeID", home_id);
        getActivity().startActivityForResult(intent, REQUEST_CODE_MOVE);
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

    @Override
    public void onResume() {
        super.onResume();
        if (MyApplication.isLoading) {
            MyApplication.isLoading = false;
            refreshInfo();
        }
    }

    public void refreshInfo() {
        //        if ("all".equals(mRoomID)) {//所有设备界面不显示添加按钮
        //            //lin_add.setVisibility(View.GONE);
        //        }
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
                        //                        if (null != deviceAdapter) {
                        //                            deviceAdapter.notifyDataSetChanged();
                        //                        }
                        if (null != addActAdapter) {
                            addActAdapter.notifyDataSetChanged();
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

    public static void setListViewHeight(ListView listView) {
        //获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            //为空没没做任何事情
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            //listAdapter.getCount()过的适配器的item行数
            View listItem = listAdapter.getView(i, null, listView);
            //计算子项View 的宽高
            listItem.measure(0, 0);
            //统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        //获取listView布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //listView.getDividerHeight()获取子项间分隔符占用的高度
        //params.height最后得到整个ListView完整显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
