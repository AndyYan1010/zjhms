package com.bt.Smart.Hox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MainActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.LoginInfo;
import com.bt.Smart.Hox.messegeInfo.QQLoginInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.SpUtils;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.io.IOException;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/5/22 9:05
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private EditText  edit_num, edit_psd;
    private CheckBox       ck_remPas;//记住密码
    private CheckBox       cb_agree;//是否同意协议
    private Button         bt_login;//登录按钮
    private LinearLayout   lin_qq;
    private LinearLayout   lin_wx;
    private TextView       tv_fgt;//忘记密码
    private Tencent        mTencent;
    private BaseUiListener mIUiListener;
    private boolean isRem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_actiivty);
        getView();
        setData();
    }

    private void getView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        edit_num = (EditText) findViewById(R.id.edit_num);
        edit_psd = (EditText) findViewById(R.id.edit_psd);
        ck_remPas = (CheckBox) findViewById(R.id.ck_remPas);
        bt_login = (Button) findViewById(R.id.bt_login);
        cb_agree = findViewById(R.id.cb_agree);
        lin_qq = (LinearLayout) findViewById(R.id.lin_qq);
        lin_wx = (LinearLayout) findViewById(R.id.lin_wx);
        tv_fgt = (TextView) findViewById(R.id.tv_fgt);
    }

    private void setData() {
        cb_agree.setChecked(true);
        Boolean isRemem = SpUtils.getBoolean(LoginActivity.this, "isRem", false);
        if (isRemem) {
            isRem = true;
            ck_remPas.setChecked(true);
            String name = SpUtils.getString(LoginActivity.this, "name");
            String psd = SpUtils.getString(LoginActivity.this, "psd");
            edit_num.setText(name);
            edit_num.setSelection(name.length());
            edit_psd.setText(psd);
            edit_psd.setSelection(psd.length());
        }
        ck_remPas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isRem = b;
            }
        });
        img_back.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        lin_qq.setOnClickListener(this);
        lin_wx.setOnClickListener(this);
        tv_fgt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_fgt://跳转忘记密码界面
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra("kind", "fgt");
                startActivity(intent);
                break;
            case R.id.bt_login://登录
                String phone = String.valueOf(edit_num.getText()).trim();
                String psd = String.valueOf(edit_psd.getText()).trim();
                if ("".equals(phone) || "手机号".equals(phone)) {
                    ToastUtils.showToast(LoginActivity.this, "请输入手机号");
                    return;
                } else {
                    // 账号不匹配手机号格式（11位数字且以1开头）
                    if (phone.length() != 11) {
                        ToastUtils.showToast(this, "手机号码格式不正确");
                        return;
                    }
                }
                if ("".equals(psd) || "请输入密码".equals(psd)) {
                    ToastUtils.showToast(LoginActivity.this, "请输入密码");
                    return;
                }

                //是否记住账号密码
                isNeedRem(phone, psd);
                //登录
                loginToService(phone, psd);
                break;
            case R.id.lin_qq://qq登录
                ProgressDialogUtil.startShow(this, "正在登录请稍等...");
                //初始化QQ
                mTencent = Tencent.createInstance("101519054", this);
                mIUiListener = new BaseUiListener();
                if (!mTencent.isSessionValid()) {
                    mTencent.login(this, "all", mIUiListener);//all/get_user_info
                }
                break;
            case R.id.lin_wx://微信登录

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            //成功获取到信息
            Gson gson = new Gson();
            ToastUtils.showToast(LoginActivity.this, o.toString());
            QQLoginInfo qqLoginInfo = gson.fromJson(o.toString(), QQLoginInfo.class);
            ProgressDialogUtil.hideDialog();
        }

        @Override
        public void onError(UiError uiError) {
            ProgressDialogUtil.hideDialog();
            //未获取到
            ToastUtils.showToast(LoginActivity.this, "QQ授权登录失败，未获取到信息");
        }

        @Override
        public void onCancel() {
            ProgressDialogUtil.hideDialog();
            ToastUtils.showToast(LoginActivity.this, "取消QQ授权登录");
        }
    }

    private void loginToService(String phone, final String psd) {
        ProgressDialogUtil.startShow(LoginActivity.this, "正在登录请稍后");
        RequestParamsFM params = new RequestParamsFM();
        params.put("mobile", phone);
        params.put("fpassword", psd);
        HttpOkhUtils.getInstance().doPost(NetConfig.LOGINURL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(LoginActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(LoginActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                LoginInfo loginInfo = gson.fromJson(resbody, LoginInfo.class);
                if (loginInfo.getCode() == 1) {
                    LoginInfo.MemberInfoBean memberInfo = loginInfo.getMemberInfo();
                    MyApplication.userID = memberInfo.getId();
                    MyApplication.pasword = psd;
                    MyApplication.userName = memberInfo.getWx_name();
                    MyApplication.userPhone = memberInfo.getFtelephone();
                    //跳转主页面
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    ToastUtils.showToast(LoginActivity.this, loginInfo.getMessage());
                }
            }
        });
    }

    private void isNeedRem(String name, String psd) {
        SpUtils.putBoolean(LoginActivity.this, "isRem", isRem);
        if (isRem) {
            SpUtils.putString(LoginActivity.this, "name", name);
            SpUtils.putString(LoginActivity.this, "psd", psd);
        }
    }
    //    private void setAlias(String id) {
    //        //        String alias = id;//用户id
    //        String alias = "9527";
    //        // 调用 Handler 来异步设置别名
    //        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
    //    }

    //    private final        TagAliasCallback mAliasCallback = new TagAliasCallback() {
    //        @Override
    //        public void gotResult(int code, String alias, Set<String> tags) {
    //            String logs;
    //            switch (code) {
    //                case 0:
    //                    logs = "Set tag and alias success";
    //                    // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
    //                    ToastUtils.showToast(LoginActivity.this, "success");
    //                    SpUtils.putString(LoginActivity.this, "IsAlias", "1");
    //                    break;
    //                case 6002:
    //                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
    //                    // 延迟 60 秒来调用 Handler 设置别名
    //                    mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
    //                    ToastUtils.showToast(LoginActivity.this, "延迟 60 秒");
    //                    break;
    //                default:
    //                    logs = "Failed with errorCode = " + code;
    //            }
    //        }
    //    };
    //    private static final int              MSG_SET_ALIAS  = 1001;
    //    private final        Handler          mHandler       = new Handler() {
    //        @Override
    //        public void handleMessage(android.os.Message msg) {
    //            super.handleMessage(msg);
    //            switch (msg.what) {
    //                case MSG_SET_ALIAS:
    //                    // 调用 JPush 接口来设置别名。
    //                    JPushInterface.setAliasAndTags(getApplicationContext(),
    //                            (String) msg.obj,
    //                            null,
    //                            mAliasCallback);
    //                    break;
    //                default:
    //            }
    //        }
    //    };
}
