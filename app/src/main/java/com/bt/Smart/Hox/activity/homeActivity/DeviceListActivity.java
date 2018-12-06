package com.bt.Smart.Hox.activity.homeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvDevListAdapter;
import com.bt.Smart.Hox.messegeInfo.DeviceTypeAllInfo;
import com.bt.Smart.Hox.messegeInfo.DeviceTypeListInfo;
import com.bt.Smart.Hox.messegeInfo.HouseDeviceInfo;
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
 * @创建时间 2018/10/30 13:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceListActivity extends BaseActivity implements View.OnClickListener {
    private ImageView        img_back;
    private TextView         tv_title;
    private LinearLayout     lin_nomsg;
    private List             mData;//设备列表数据
    private LvDevListAdapter devListAdapter;
    private ListView         lv_dev;//设备列表
    private TextView         tv_add;//添加红外设备
    private String           mKind;//应该获取显示哪种设备数据
    private String           mHomeID;
    private String           mRoomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev_list_actiivty);
        getView();
        setData();
    }

    private void getView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        lin_nomsg = (LinearLayout) findViewById(R.id.lin_nomsg);
        lv_dev = (ListView) findViewById(R.id.lv_dev);
        tv_add = (TextView) findViewById(R.id.tv_add);

    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_add.setVisibility(View.GONE);
        mKind = getIntent().getStringExtra("devKind");
        mHomeID = getIntent().getStringExtra("homeID");
        mRoomID = getIntent().getStringExtra("roomID");

        tv_add.setOnClickListener(this);
        if (null == mKind || "".equals(mKind)) {
            ToastUtils.showToast(this, "获取数据失败，请退出当前界面或重新登录");
            return;
        }

        mData = new ArrayList();
        devListAdapter = new LvDevListAdapter(this, mData, mKind, mRoomID);
        lv_dev.setAdapter(devListAdapter);
        lv_dev.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if ("zk".equals(mKind)) {
                    Intent intent = new Intent(DeviceListActivity.this, AddDevDetailActivity.class);
                    intent.putExtra("homeID", mHomeID);
                    intent.putExtra("roomID", mRoomID);
                    intent.putExtra("allRoomInfo", getIntent().getStringExtra("allRoomInfo"));
                    intent.putExtra("devType", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getDevcieType());//主控/从控/单品
                    intent.putExtra("name", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getDeviceDescibe());
                    intent.putExtra("control_type", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getDevcieType());
                    intent.putExtra("device_type_id", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getId());
                    intent.putExtra("devcieTypePic", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getDevcieTypePic());
                    startActivity(intent);
                } else if ("wf".equals(mKind)) {
                    Intent intent = new Intent(DeviceListActivity.this, AddDevDetailActivity.class);
                    intent.putExtra("homeID", mHomeID);
                    intent.putExtra("roomID", mRoomID);
                    intent.putExtra("allRoomInfo", getIntent().getStringExtra("allRoomInfo"));
                    intent.putExtra("devType", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getDevcieType());//主控/从控/单品
                    intent.putExtra("fromLin", "1");
                    intent.putExtra("name", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getDeviceDescibe());
                    intent.putExtra("control_type", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getDevcieType());
                    intent.putExtra("device_type_id", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getId());
                    intent.putExtra("devcieTypePic", ((DeviceTypeListInfo.DeviceTypeListBean) mData.get(i)).getDevcieTypePic());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(DeviceListActivity.this, AddDevDetailActivity.class);
                    intent.putExtra("homeID", mHomeID);
                    intent.putExtra("roomID", mRoomID);
                    intent.putExtra("allRoomInfo", getIntent().getStringExtra("allRoomInfo"));
                    intent.putExtra("devType", ((DeviceTypeAllInfo.DeviceTypeListBean.DataBean) mData.get(i)).getDevcieType());//主控/从控/单品
                    intent.putExtra("name", ((DeviceTypeAllInfo.DeviceTypeListBean.DataBean) mData.get(i)).getDeviceDescibe());
                    intent.putExtra("control_type", ((DeviceTypeAllInfo.DeviceTypeListBean.DataBean) mData.get(i)).getDevcieType());
                    intent.putExtra("device_type_id", ((DeviceTypeAllInfo.DeviceTypeListBean.DataBean) mData.get(i)).getId());
                    intent.putExtra("devcieTypePic", ((DeviceTypeAllInfo.DeviceTypeListBean.DataBean) mData.get(i)).getDevcieTypePic());
                    startActivity(intent);
                }
            }
        });

        if ("zk".equals(mKind)) {//显示主控设备列表
            tv_title.setText("主控设备列表");
            //获取主控
            getZkOrWiFiList("0");
        } else if ("wf".equals(mKind)) {//显示WiFi设备列表
            tv_title.setText("无线设备列表");
            getZkOrWiFiList("1");
        } else {//设备列表
            tv_title.setText("所有设备列表");
            //获取所有设备列表
            getZkOrWiFiList("2");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add://添加红外设备

                break;
        }
    }

    private void getZkOrWiFiList(final String flag) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("flag", flag);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.DEVICETYPENEW, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(DeviceListActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(DeviceListActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                if ("0".equals(flag) || "1".equals(flag)) {
                    DeviceTypeListInfo deviceTypeListInfo = gson.fromJson(resbody, DeviceTypeListInfo.class);
                    ToastUtils.showToast(DeviceListActivity.this, deviceTypeListInfo.getMessage());
                    if (1 == deviceTypeListInfo.getCode()) {
                        mData.addAll(deviceTypeListInfo.getDeviceTypeList());
                        devListAdapter.notifyDataSetChanged();
                    }
                } else {
                    DeviceTypeAllInfo deviceTypeAllInfo = gson.fromJson(resbody, DeviceTypeAllInfo.class);
                    ToastUtils.showToast(DeviceListActivity.this, deviceTypeAllInfo.getMessage());
                    if (1 == deviceTypeAllInfo.getCode()) {
                        mData.addAll(deviceTypeAllInfo.getDeviceTypeList().getData());
                        devListAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
    }

    private void getDeviceOfRoom() {
        if (null == mRoomID || "".equals(mRoomID)) {
            System.out.println(mRoomID);
            ToastUtils.showToast(this, "未查找到房间信息");
            return;
        }
        ProgressDialogUtil.startShow(this, "正在查询...");
        RequestParamsFM params = new RequestParamsFM();
        params.put("house_id", mRoomID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.DEVICE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(DeviceListActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(DeviceListActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                HouseDeviceInfo houseDeviceInfo = gson.fromJson(resbody, HouseDeviceInfo.class);
                if (1 == houseDeviceInfo.getCode()) {
                    if (null != houseDeviceInfo.getDeviceHouseList() && houseDeviceInfo.getDeviceHouseList().size() > 0) {
                        //ps:>0说明房间下有设备
                        if (houseDeviceInfo.getDeviceHouseList().size() > 0) {
                            lin_nomsg.setVisibility(View.GONE);
                            mData.addAll(houseDeviceInfo.getDeviceHouseList());
                            if (null != devListAdapter) {
                                devListAdapter.notifyDataSetChanged();
                            }
                        }
                    } else {
                        lin_nomsg.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtils.showToast(DeviceListActivity.this, "设备信息查询失败");
                }
            }
        });
    }
}
