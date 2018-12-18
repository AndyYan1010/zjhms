package com.bt.Smart.Hox.activity.meActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvDevListManagerAdapter;
import com.bt.Smart.Hox.messegeInfo.AllDevListInfo;
import com.bt.Smart.Hox.messegeInfo.CongKongListInfo;
import com.bt.Smart.Hox.messegeInfo.ZhuKongListInfo;
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
    private ImageView               img_back;
    private ImageView               img_more;//扫描
    private TextView                tv_title;//标题
    private TextView                tv_zk;//主控
    private TextView                tv_ck;//从控
    private TextView                tv_sb;//设备
    private LinearLayout            lin_zk;
    private LinearLayout            lin_ck;
    private LinearLayout            lin_sb;
    private ImageView               img_loading;
    private LinearLayout            lin_nomsg;
    private List                    mDataZk;//主控列表数据
    private List                    mDataCk;//从控列表数据
    private List                    mDataSb;//设备列表数据
    private LvDevListManagerAdapter zkListAdapter;
    private LvDevListManagerAdapter ckListAdapter;
    private LvDevListManagerAdapter devListAdapter;
    private ListView                lv_devzk;//主控列表
    private ListView                lv_devck;//从控列表
    private ListView                lv_devsb;//设备列表

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
        tv_zk = (TextView) findViewById(R.id.tv_zk);
        tv_ck = (TextView) findViewById(R.id.tv_ck);
        tv_sb = (TextView) findViewById(R.id.tv_sb);
        img_loading = (ImageView) findViewById(R.id.img_loading);
        lin_nomsg = (LinearLayout) findViewById(R.id.lin_nomsg);
        lin_zk = (LinearLayout) findViewById(R.id.lin_zk);
        lin_ck = (LinearLayout) findViewById(R.id.lin_ck);
        lin_sb = (LinearLayout) findViewById(R.id.lin_sb);
        lv_devzk = (ListView) findViewById(R.id.lv_devzk);
        lv_devck = (ListView) findViewById(R.id.lv_devck);
        lv_devsb = (ListView) findViewById(R.id.lv_devsb);
    }

    private void setData() {
        tv_title.setText("主控设备列表");
        img_back.setVisibility(View.VISIBLE);
        //        Glide.with(this).load(R.drawable.loadgif).into(img_loading);
        mDataZk = new ArrayList();
        mDataCk = new ArrayList();
        mDataSb = new ArrayList();
        zkListAdapter = new LvDevListManagerAdapter(this, mDataZk, 0);
        ckListAdapter = new LvDevListManagerAdapter(this, mDataCk, 1);
        devListAdapter = new LvDevListManagerAdapter(this, mDataSb, 2);
        lv_devzk.setAdapter(zkListAdapter);
        lv_devck.setAdapter(ckListAdapter);
        lv_devsb.setAdapter(devListAdapter);

        img_back.setOnClickListener(this);
        lin_zk.setOnClickListener(this);
        lin_ck.setOnClickListener(this);
        lin_sb.setOnClickListener(this);
        tv_zk.setTextColor(Color.BLUE);
        getZkList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.lin_zk://获取主控列表
                tv_zk.setTextColor(Color.BLUE);
                tv_ck.setTextColor(getResources().getColor(R.color.word_black));
                tv_sb.setTextColor(getResources().getColor(R.color.word_black));
                getZkList();
                break;
            case R.id.lin_ck://获取从控列表
                tv_zk.setTextColor(getResources().getColor(R.color.word_black));
                tv_ck.setTextColor(Color.BLUE);
                tv_sb.setTextColor(getResources().getColor(R.color.word_black));
                getCkList();
                break;
            case R.id.lin_sb://获取设备列表
                tv_zk.setTextColor(getResources().getColor(R.color.word_black));
                tv_ck.setTextColor(getResources().getColor(R.color.word_black));
                tv_sb.setTextColor(Color.BLUE);
                getDeviceOfRoom();
                break;

        }
    }

    private void getZkList() {
        tv_title.setText("主控列表");
        ProgressDialogUtil.startShow(this, "正在查询主控列表...");
        mDataZk.clear();
        mDataCk.clear();
        mDataSb.clear();
        lv_devzk.setVisibility(View.VISIBLE);
        lv_devck.setVisibility(View.GONE);
        lv_devsb.setVisibility(View.GONE);
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.MAINCONTROL, params, new HttpOkhUtils.HttpCallBack() {
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
                ZhuKongListInfo zhuKongListInfo = gson.fromJson(resbody, ZhuKongListInfo.class);
                ToastUtils.showToast(DeviceManagerActivity.this, zhuKongListInfo.getMessage());
                if (1 == zhuKongListInfo.getCode()) {
                    mDataZk.addAll(zhuKongListInfo.getHomeList());
                    zkListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getCkList() {
        tv_title.setText("从控列表");
        ProgressDialogUtil.startShow(this, "正在查询从控列表...");
        mDataZk.clear();
        mDataCk.clear();
        mDataSb.clear();
        lv_devzk.setVisibility(View.GONE);
        lv_devck.setVisibility(View.VISIBLE);
        lv_devsb.setVisibility(View.GONE);
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SECONDCONTROL, params, new HttpOkhUtils.HttpCallBack() {
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
                CongKongListInfo congKongListInfo = gson.fromJson(resbody, CongKongListInfo.class);
                ToastUtils.showToast(DeviceManagerActivity.this, congKongListInfo.getMessage());
                if (1 == congKongListInfo.getCode()) {
                    mDataCk.addAll(congKongListInfo.getSecondControlList());
                    ckListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getDeviceOfRoom() {
        tv_title.setText("设备列表");
        ProgressDialogUtil.startShow(this, "正在查询设备列表...");
        mDataZk.clear();
        mDataCk.clear();
        mDataSb.clear();
        lv_devzk.setVisibility(View.GONE);
        lv_devck.setVisibility(View.GONE);
        lv_devsb.setVisibility(View.VISIBLE);
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
                    mDataSb.addAll(allDeviceInfo.getDeviceHomeList());
                    devListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
