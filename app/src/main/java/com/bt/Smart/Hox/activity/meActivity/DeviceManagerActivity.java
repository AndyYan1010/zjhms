package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvDevListManagerAdapter;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
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
 * @创建时间 2018/11/29 18:10
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceManagerActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                               img_back;
    private ImageView                               img_more;//扫描
    private TextView                                tv_title;//标题
    private List<AllDevListInfo.DeviceHomeListBean> mData;//设备列表数据
    private LvDevListManagerAdapter                 devListAdapter;
    private ListView                                lv_dev;//设备列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_dev_manager);
        getView();
        setData();
    }

    private void getView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        lv_dev = (ListView) findViewById(R.id.lv_dev);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("设备列表");
        mData = new ArrayList();
        devListAdapter = new LvDevListManagerAdapter(this, mData);
        lv_dev.setAdapter(devListAdapter);
        //获取设备数据
        getDeviceOfRoom();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void getDeviceOfRoom() {
        ProgressDialogUtil.startShow(this, "正在查询...");
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SELECTEQLIST, params, new HttpOkhUtils.HttpCallBack() {//QUERYNOTHA3LIST
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(DeviceManagerActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(DeviceManagerActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                AllDevListInfo allDeviceInfo = gson.fromJson(resbody, AllDevListInfo.class);
                ToastUtils.showToast(DeviceManagerActivity.this, allDeviceInfo.getMessage());
                if (1 == allDeviceInfo.getCode()) {
                    mData.addAll(allDeviceInfo.getDeviceHomeList());
                    devListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
