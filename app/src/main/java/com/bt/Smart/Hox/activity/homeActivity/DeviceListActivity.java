package com.bt.Smart.Hox.activity.homeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvDevListAdapter;
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
 * @创建时间 2018/10/30 13:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceListActivity extends BaseActivity implements View.OnClickListener {
    private ImageView        img_back;
    private TextView         tv_title;
    private List             mData;//设备列表数据
    private LvDevListAdapter devListAdapter;
    private ListView         lv_dev;//设备列表
    private TextView         tv_add;//添加红外设备
    private String           mKind;//应该获取显示哪种设备数据
    private String           mHomeID;

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
        lv_dev = (ListView) findViewById(R.id.lv_dev);
        tv_add = (TextView) findViewById(R.id.tv_add);

    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("设备列表");
        tv_add.setVisibility(View.GONE);

        mKind = getIntent().getStringExtra("devKind");
        mHomeID = getIntent().getStringExtra("homeID");

        mData = new ArrayList();
        devListAdapter = new LvDevListAdapter(this, mData, mKind);
        lv_dev.setAdapter(devListAdapter);

        tv_add.setOnClickListener(this);

        if (null == mKind || "".equals(mKind)) {
            ToastUtils.showToast(this, "获取数据失败，请退出当前界面或重新登录");
            return;
        }
        if ("zk".equals(mKind)) {//显示主控设备列表
            //获取主控
            getZkList();
        } else if ("ck".equals(mKind)) {//显示从控设备列表
            getCkList();
        } else {//设备列表

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

    private void getCkList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", mHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SECONDCONTROL, params, new HttpOkhUtils.HttpCallBack() {
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
                CongKongListInfo congKongListInfo = gson.fromJson(resbody, CongKongListInfo.class);
                if (1 == congKongListInfo.getCode()) {
                    ToastUtils.showToast(DeviceListActivity.this, congKongListInfo.getMessage());
                    mData.addAll(congKongListInfo.getSecondControlList());
                    devListAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showToast(DeviceListActivity.this, "查询失败");
                }
            }
        });
    }

    private void getZkList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", mHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.MAINCONTROL, params, new HttpOkhUtils.HttpCallBack() {
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
                ZhuKongListInfo zhuKongListInfo = gson.fromJson(resbody, ZhuKongListInfo.class);
                if (1 == zhuKongListInfo.getCode()) {
                    ToastUtils.showToast(DeviceListActivity.this, zhuKongListInfo.getMessage());
                    mData.addAll(zhuKongListInfo.getHomeList());
                    devListAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showToast(DeviceListActivity.this, "查询失败");
                }
            }
        });
    }
}
