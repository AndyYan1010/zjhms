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
import com.bt.Smart.Hox.adapter.LvMsgCenterAdapter;
import com.bt.Smart.Hox.messegeInfo.MineMsgInfo;
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
 * @创建时间 2018/11/29 18:58
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MineMsgCenterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                                   img_back;
    private TextView                                    tv_title;//标题
    private List<MineMsgInfo.DevListBean.DevStatusBean> mData;//消息数据
    private ListView                                    lv_dev;//列表
    private LvMsgCenterAdapter                          msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_msg_center);
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
        msgAdapter = new LvMsgCenterAdapter(this, mData);
        lv_dev.setAdapter(msgAdapter);
        //获取消息
        getMsgInfo();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void getMsgInfo() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.MYMESSAGE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(MineMsgCenterActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(MineMsgCenterActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                MineMsgInfo mineMsgInfo = gson.fromJson(resbody, MineMsgInfo.class);
                ToastUtils.showToast(MineMsgCenterActivity.this, mineMsgInfo.getMessage());
                if (1 == mineMsgInfo.getCode()) {
                    if (mineMsgInfo.getDevList().size() > 0) {
                        mData.addAll(mineMsgInfo.getDevList().get(0).getDevStatus());
                        msgAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }
}
