package com.bt.Smart.Hox.activity.meActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.AddRoomActivity;
import com.bt.Smart.Hox.adapter.LvAddRoomAdapter;
import com.bt.Smart.Hox.messegeInfo.RoomChoiceInfo;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
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
 * @创建时间 2018/10/31 15:16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddHomeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView            img_back;
    private TextView             tv_title;
    private EditText             et_name;//填写家庭名称
    private TextView             tv_choice;//选择
    private LinearLayout         lin_add;//添加其他房间
    private List<RoomChoiceInfo> mData;//可添加房间数据
    private ListView             lv_room;//房间列表
    private LvAddRoomAdapter     addRoomAdapter;
    private TextView             tv_setup;//确认创建

    private int REQUEST_CODE = 1001;//添加房间返回值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_choice = (TextView) findViewById(R.id.tv_choice);
        lin_add = (LinearLayout) findViewById(R.id.lin_add);
        lv_room = (ListView) findViewById(R.id.lv_room);
        tv_setup = (TextView) findViewById(R.id.tv_setup);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("添加家庭");
        tv_choice.setOnClickListener(this);
        lin_add.setOnClickListener(this);
        tv_setup.setOnClickListener(this);

        mData = new ArrayList();
        mData.add(new RoomChoiceInfo("客厅", true));
        mData.add(new RoomChoiceInfo("主卧", true));
        mData.add(new RoomChoiceInfo("次卧", true));
        mData.add(new RoomChoiceInfo("书房", true));
        mData.add(new RoomChoiceInfo("厨房", true));
        addRoomAdapter = new LvAddRoomAdapter(this, mData);
        lv_room.setAdapter(addRoomAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_choice://选择位置


                break;
            case R.id.lin_add://添加其他房间
                Intent intent = new Intent(this, AddRoomActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_setup://创建家庭/关闭页面
                String home_name = String.valueOf(et_name.getText()).trim();
                String address = String.valueOf(tv_choice.getText()).trim();
                if ("".equals(home_name) || "填写家庭名称".equals(home_name)) {
                    ToastUtils.showToast(this, "请填写家庭名称");
                    return;
                }
                if ("".equals(address) || "请选择".equals(address)) {
                    ToastUtils.showToast(this, "请选择地址");
                    return;
                }
                boolean notnull = false;//选择的房间数为0
                for (RoomChoiceInfo choiceInfo : mData) {
                    if (choiceInfo.isIsChoice()) {
                        notnull = true;
                    }
                }
                if (!notnull) {
                    ToastUtils.showToast(this, "您未选择房间");
                    return;
                }
                creatHome(home_name, address);
                break;
        }
    }

    private void creatHome(String home_name, String address) {
        String homecanshu = "[{\"home_name\": \"" + home_name + "\",\"faddress\":\"" + address + "\",\"register_id\":\"" + MyApplication.userID + "\"}]";
        String house_member = "[";
        for (int i = 0; i < mData.size(); i++) {
            if (mData.get(i).isIsChoice()) {
                if (i <= mData.size() - 2) {
                    house_member = house_member + "{\"house_name\": \"" + mData.get(i).getRoom_name() + "\"},";
                } else {
                    house_member = house_member + "{\"house_name\": \"" + mData.get(i).getRoom_name() + "\"}";
                }
            }
        }
        house_member = house_member + "]";
        RequestParamsFM params = new RequestParamsFM();
        params.put("home", homecanshu);
        params.put("house_member", house_member);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPost(NetConfig.HOME, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddHomeActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddHomeActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo sendSMSInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(AddHomeActivity.this, sendSMSInfo.getMessage());
                if (1 == sendSMSInfo.getCode()) {
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            mData.add(new RoomChoiceInfo(data.getStringExtra("roomName"), true));
            addRoomAdapter.notifyDataSetChanged();
        }
    }
}
