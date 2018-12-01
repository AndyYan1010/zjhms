package com.bt.Smart.Hox.activity.homeActivity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.RecLightInfoAdapter;
import com.bt.Smart.Hox.messegeInfo.LightCurrentInfo;
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
 * @创建时间 2018/11/30 16:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LightMeasureInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                         img_back;
    private TextView                          tv_title;
    private TextView                          tv_cont;
    private RecyclerView                      recy_hair_info;
    private RecLightInfoAdapter               lightInfoAdapter;
    private List<LightCurrentInfo.EnergyBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_detail);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_cont = (TextView) findViewById(R.id.tv_cont);
        recy_hair_info = (RecyclerView) findViewById(R.id.recy_hair_info);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("计量灯控");
        tv_cont.setText("当前监控数值");

        String dev_id = getIntent().getStringExtra("dev_ID");
        mData = new ArrayList();
        recy_hair_info.setLayoutManager(new GridLayoutManager(this, 2));
        lightInfoAdapter = new RecLightInfoAdapter(this, mData, "0211800001");
        recy_hair_info.setAdapter(lightInfoAdapter);

        //获取空气哨兵的检测值
        getLightMeasureInfo("0211800001");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void getLightMeasureInfo(String dev_id) {
        ProgressDialogUtil.startShow(this, "正在加载");
        RequestParamsFM params = new RequestParamsFM();
        params.put("device_id", dev_id);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.ENERGY_CURRENT, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(LightMeasureInfoActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(LightMeasureInfoActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                LightCurrentInfo lightCurrentInfo = gson.fromJson(resbody, LightCurrentInfo.class);
                ToastUtils.showToast(LightMeasureInfoActivity.this, lightCurrentInfo.getMessage());
                if (1 == lightCurrentInfo.getCode()) {
                    mData.add(lightCurrentInfo.getEnergy());
                    lightInfoAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
