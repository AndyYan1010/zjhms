package com.bt.Smart.Hox.activity.homeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvRoomAdapter;
import com.bt.Smart.Hox.messegeInfo.HouseDetailInfo;
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
 * @创建时间 2018/10/30 15:39
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RoomManagerActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                           img_back;
    private TextView                            tv_title;
    private TextView                            tv_add;//添加家
    private List<HouseDetailInfo.HouseListBean> mData;//房间列表
    private ListView                            lv_room;
    private LvRoomAdapter                       roomAdapter;
    private String                              homeID;//家的id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_manager);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_add = (TextView) findViewById(R.id.tv_add);
        lv_room = (ListView) findViewById(R.id.lv_room);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("房间管理");
        mData = new ArrayList();
        roomAdapter = new LvRoomAdapter(this, mData);
        lv_room.setAdapter(roomAdapter);
        tv_add.setOnClickListener(this);
        homeID = getIntent().getStringExtra("homeID");
        //获取家中房间列表
        getHouseList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add:
                startActivity(new Intent(this, AddRoomActivity.class));
                break;
        }
    }

    private void getHouseList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", homeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HOUSE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(RoomManagerActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(RoomManagerActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                HouseDetailInfo houseDetailInfo = gson.fromJson(resbody, HouseDetailInfo.class);
                if (1 == houseDetailInfo.getCode()) {
                    ToastUtils.showToast(RoomManagerActivity.this, "房间数查询成功");
                    if (null == mData) {
                        mData = new ArrayList<>();
                    } else {
                        mData.clear();
                    }
                    mData.addAll(houseDetailInfo.getHouseList());
                    roomAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showToast(RoomManagerActivity.this, "房间查询失败");
                }
            }
        });
    }
}
