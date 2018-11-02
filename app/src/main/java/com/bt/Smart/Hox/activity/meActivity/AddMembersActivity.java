package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 14:41
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddMembersActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ima_back;
    private TextView  tv_title;
    private EditText  et_phone;
    private TextView  tv_add;
    private String    homeID;
    private int REQUEST_ADD_MEMBER = 1002;//请求返回值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);
        setView();
        setData();
    }

    private void setView() {
        ima_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_add = (TextView) findViewById(R.id.tv_add);
    }

    private void setData() {
        homeID = getIntent().getStringExtra("homeID");
        ima_back.setVisibility(View.VISIBLE);
        tv_title.setText("添加成员");
        ima_back.setOnClickListener(this);
        tv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add:
                String phone = String.valueOf(et_phone.getText()).trim();
                if ("".equals(phone) || "请输入手机号".equals(phone)) {
                    ToastUtils.showToast(this, "请输入手机号");
                    return;
                } else {
                    // 账号不匹配手机号格式（11位数字且以1开头）
                    if (phone.length() != 11) {
                        ToastUtils.showToast(this, "手机号码格式不正确");
                        return;
                    }
                }
                addMember(phone);
                break;
        }
    }

    private void addMember(String phone) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("mobile", phone);
        params.put("home_id", homeID);
        HttpOkhUtils.getInstance().doPost(NetConfig.HOMEMEMBER, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddMembersActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddMembersActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo sendSMSInfo = gson.fromJson(resbody, CommonInfo.class);
                if (1 == sendSMSInfo.getCode()) {
                    setResult(REQUEST_ADD_MEMBER);
                    finish();
                }
                ToastUtils.showToast(AddMembersActivity.this, sendSMSInfo.getMessage());
            }
        });
    }
}
