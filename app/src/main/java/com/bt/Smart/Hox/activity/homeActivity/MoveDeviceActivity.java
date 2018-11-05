package com.bt.Smart.Hox.activity.homeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvChoiceRoomAdapter;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
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
 * @创建时间 2018/10/31 9:25
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MoveDeviceActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                           img_back;
    private TextView                            tv_title;
    private TextView                            tv_dev_name;
    private TextView                            tv_room;//当前房间
    private ListView                            lv_room;//可移动房间列表
    private List<HouseDetailInfo.HouseListBean> mData;
    private LvChoiceRoomAdapter                 choiceRoomAdapter;
    private TextView                            tv_sure;//保存修改
    private String                              homeID;
    private String                              devName;
    private String                              devID;
    private String                              roomID;
    private int                                 mItem;//记录选择的条目

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_device);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_dev_name = (TextView) findViewById(R.id.tv_dev_name);
        tv_room = (TextView) findViewById(R.id.tv_room);
        lv_room = (ListView) findViewById(R.id.lv_room);
        tv_sure = (TextView) findViewById(R.id.tv_sure);
    }

    private void setData() {
        devName = getIntent().getStringExtra("devName");
        devID = getIntent().getStringExtra("devID");
        roomID = getIntent().getStringExtra("roomID");
        homeID = getIntent().getStringExtra("homeID");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("设备移动");
        mData = new ArrayList<>();
        choiceRoomAdapter = new LvChoiceRoomAdapter(this, mData, mItem);
        lv_room.setAdapter(choiceRoomAdapter);
        tv_sure.setOnClickListener(this);
        //获取所有房间
        getAllRoomList();
    }

    private void getAllRoomList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", homeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HOUSE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(MoveDeviceActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(MoveDeviceActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                HouseDetailInfo houseDetailInfo = gson.fromJson(resbody, HouseDetailInfo.class);
                ToastUtils.showToast(MoveDeviceActivity.this, houseDetailInfo.getMessage());
                if (1 == houseDetailInfo.getCode()) {
                    mData.addAll(houseDetailInfo.getHouseList());
                    choiceRoomAdapter.notifyDataSetChanged();
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
            case R.id.tv_sure:
                //更改设备房间
                changeDevRoom();
                break;
        }
    }

    private void changeDevRoom() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", devID);
        params.put("house_id", mData.get(mItem).getId());
        HttpOkhUtils.getInstance().doPut(NetConfig.DEVICEHOUSE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(MoveDeviceActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(MoveDeviceActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(MoveDeviceActivity.this, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    setResult(REQUEST_CODE_MOVE);
                    finish();
                }
            }
        });
    }
    private int REQUEST_CODE_MOVE=1005;
    public void setItem(int item) {
        mItem = item;
    }
}
