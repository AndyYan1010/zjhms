package com.bt.Smart.Hox.activity.homeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/31 8:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddRoomActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private TextView  tv_title;
    private EditText  et_name;
    private TextView  tv_recom1;
    private TextView  tv_recom2;
    private TextView  tv_submit;
    private int ROOM_REQUEST_CODE = 1001;
    private int REQUEST_CODE      = 1001;//添加房间返回值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_recom1 = (TextView) findViewById(R.id.tv_recom1);
        tv_recom2 = (TextView) findViewById(R.id.tv_recom2);
        tv_submit = (TextView) findViewById(R.id.tv_submit);

    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("添加房间");
        tv_recom1.setOnClickListener(this);
        tv_recom2.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                //                Intent intent = new Intent();
                //                setResult(ROOM_REQUEST_CODE, intent);
                finish();
                break;
            case R.id.tv_recom1:
                et_name.setText("餐厅");
                break;
            case R.id.tv_recom2:
                et_name.setText("衣帽间");
                break;
            case R.id.tv_submit://提交房间
                String room_name = String.valueOf(et_name.getText()).trim();
                if ("".equals(room_name) || "房间名称".equals(room_name)) {
                    ToastUtils.showToast(this, "请填写房间名称");
                    return;
                }
                //添加房间
                addRoom(room_name);
                break;
        }
    }

    private void addRoom(final String room_name) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 1; i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("house_name", room_name);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", getIntent().getStringExtra("homeID"));
        params.put("register_id", MyApplication.userID);
        params.put("house_member", jsonArray);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.HOUSE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddRoomActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddRoomActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo sendSMSInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(AddRoomActivity.this, sendSMSInfo.getMessage());
                if (1 == sendSMSInfo.getCode()) {
                    Intent intent = getIntent();
                    intent.putExtra("roomName", room_name);
                    setResult(REQUEST_CODE,intent);
                    finish();
                }
            }
        });
    }
}
