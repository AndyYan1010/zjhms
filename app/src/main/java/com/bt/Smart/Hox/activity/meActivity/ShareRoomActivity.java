package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvShareAdapter;
import com.bt.Smart.Hox.messegeInfo.RoomsDeviceInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.bt.Smart.Hox.viewmodle.MyListView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 13:36
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ShareRoomActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                           img_back;
    private TextView                            tv_title;
    private ImageView                           img_head;
    private TextView                            tv_name;
    private TextView                            tv_phone;
    private MyListView                          lv_room_device;
    private List<RoomsDeviceInfo.HouseListBean> mData;
    private LvShareAdapter                      shareAdapter;

    private String homeID;
    private String memberID;
    private String name;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_room);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        img_head = (ImageView) findViewById(R.id.img_head);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        lv_room_device = (MyListView) findViewById(R.id.lv_room_device);

    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("共享设备");
        mData = new ArrayList();
        shareAdapter = new LvShareAdapter(this, mData);
        lv_room_device.setAdapter(shareAdapter);
        homeID = getIntent().getStringExtra("homeID");
        memberID = getIntent().getStringExtra("memberID");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        tv_name.setText(name);
        tv_phone.setText(phone);
        //获取该房间下所有设备信息
        getAllInfoOfRoom();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    private void getAllInfoOfRoom() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", homeID);
        params.put("register_id", MyApplication.userID);
        params.put("member_id", memberID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.AUTHORIZATIONEDIT, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(ShareRoomActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(ShareRoomActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                RoomsDeviceInfo roomsDeviceInfo = gson.fromJson(resbody, RoomsDeviceInfo.class);
                if (1 == roomsDeviceInfo.getCode()) {
                    mData.addAll(roomsDeviceInfo.getHouseList());
                    shareAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showToast(ShareRoomActivity.this, "房间设备获取失败");
                }
            }
        });
    }


}
