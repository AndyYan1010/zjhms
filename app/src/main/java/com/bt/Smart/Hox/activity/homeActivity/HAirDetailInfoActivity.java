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
import com.bt.Smart.Hox.adapter.RecHAirInfoAdapter;
import com.bt.Smart.Hox.messegeInfo.HairMeasureWithHoursInfo;
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
 * @创建时间 2018/11/14 16:34
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HAirDetailInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                                   img_back;
    private TextView                                    tv_title;
    private RecyclerView                                recy_hair_info;
    private RecHAirInfoAdapter                          hAirInfoAdapter;
    private List<HairMeasureWithHoursInfo.HairListBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_detail);
        getView();
        setData();
    }

    private void getView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        recy_hair_info = (RecyclerView) findViewById(R.id.recy_hair_info);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("当前监控数值");

        String dev_id = getIntent().getStringExtra("dev_ID");
        mData = new ArrayList();
        recy_hair_info.setLayoutManager(new GridLayoutManager(this, 2));
        hAirInfoAdapter = new RecHAirInfoAdapter(this, mData, dev_id);
        recy_hair_info.setAdapter(hAirInfoAdapter);

        //获取空气哨兵的检测值
        getHairMeasureInfo("0311800001", 1);
    }

    private void getHairMeasureInfo(String dev_id, int hh) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("device_id", dev_id);
        params.put("hh", hh);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HAIRLIST, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(HAirDetailInfoActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(HAirDetailInfoActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                HairMeasureWithHoursInfo hairMeasureWithHoursInfo = gson.fromJson(resbody, HairMeasureWithHoursInfo.class);
                ToastUtils.showToast(HAirDetailInfoActivity.this, hairMeasureWithHoursInfo.getMessage());
                if (1 == hairMeasureWithHoursInfo.getCode()) {
                    HairMeasureWithHoursInfo.HairListBean hairListBean = hairMeasureWithHoursInfo.getHairList().get(0);
                    mData.add(hairListBean);
                    hAirInfoAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
