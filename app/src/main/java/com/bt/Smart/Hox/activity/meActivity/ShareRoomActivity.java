package com.bt.Smart.Hox.activity.meActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvShareAdapter;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.RoomsDeviceInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.bt.Smart.Hox.viewmodle.MyListView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private TextView                            tv_delete;//移除成员
    private TextView                            tv_save;//保存修改

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
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_save = (TextView) findViewById(R.id.tv_save);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("共享设备");
        mData = new ArrayList();
        shareAdapter = new LvShareAdapter(this, mData, -1);
        lv_room_device.setAdapter(shareAdapter);
        homeID = getIntent().getStringExtra("homeID");
        memberID = getIntent().getStringExtra("memberID");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        tv_name.setText(name);
        tv_phone.setText(phone);
        //获取该房间下所有设备信息
        getAllInfoOfRoom();
        tv_delete.setOnClickListener(this);
        tv_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_delete://移除成员
                deleteMember();
                break;
            case R.id.tv_save://保存修改
                saveChange();
                break;
        }
    }

    private void doDeleteMember() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("register_id", memberID);
        params.put("home_id", homeID);
        HttpOkhUtils.getInstance().doDelete(NetConfig.HOME, params, new HttpOkhUtils.HttpCallBack() {
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
                CommonInfo sendSMSInfo = gson.fromJson(resbody, CommonInfo.class);
                if (1 == sendSMSInfo.getCode()) {
                    ToastUtils.showToast(ShareRoomActivity.this, "删除成功");
                    finish();
                } else {
                    ToastUtils.showToast(ShareRoomActivity.this, "删除失败");
                }
            }
        });

    }

    private AlertDialog alertDialog;

    private void deleteMember() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("温馨提示");
        builder.setMessage("您确定删除该成员？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
                //删除家
                doDeleteMember();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void saveChange() {
        ProgressDialogUtil.startShow(ShareRoomActivity.this, "正在提交...");
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < mData.size(); i++) {
            try {
                if (mData.get(i).isMineIschoice()) {//该房间有选中
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("home_id", homeID);
                    jsonObject.put("house_id", mData.get(i).getHouse_id());
                    jsonObject.put("register_id", MyApplication.userID);
                    //设备数组
                    JSONArray jsonArray1 = new JSONArray();
                    for (RoomsDeviceInfo.HouseListBean.DeviceListBean bean : mData.get(i).getDeviceList()) {
                        if (bean.isMeChoice()) {//该房间的设备有选中
                            JSONObject jsonObject1 = new JSONObject();
                            jsonObject1.put("second_control_id", bean.getSecond_control_id());
                            jsonObject1.put("main_control_code", bean.getMain_control_code());
                            jsonObject1.put("register_id", MyApplication.userID);
                            jsonObject1.put("second_control_device_id", bean.getSecond_control_id());//TODO？？？
                            jsonObject1.put("home_id", homeID);
                            jsonObject1.put("house_id", mData.get(i).getHouse_id());
                            jsonArray1.put(jsonObject1);
                        }
                    }
                    jsonObject.put("device_member", jsonArray1);
                    jsonArray.put(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_info", jsonArray.toString());
        params.put("home_id_member", homeID);
        params.put("register_id_member", memberID);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.AUTHORIZATION, params, new HttpOkhUtils.HttpCallBack() {
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
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(ShareRoomActivity.this, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    finish();
                }
            }
        });
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
