package com.bt.Smart.Hox.activity.homeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.meActivity.AddHomeActivity;
import com.bt.Smart.Hox.utils.SpUtils;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/12 15:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class CreateHomeActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_create;
    private TextView tv_loginout;
    private int REQUEST_CREATE_HOME = 1008;//创建家后的响应值
    private int REQUEST_HOME_F      = 1003;//修改了家后的响应值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_home);
        setView();
        setData();
    }

    private void setView() {
        tv_create = (TextView) findViewById(R.id.tv_create);
        tv_loginout = (TextView) findViewById(R.id.tv_loginout);
    }

    private void setData() {
        tv_create.setOnClickListener(this);
        tv_loginout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_create://跳转创建家界面
                startActivity(new Intent(this, AddHomeActivity.class));
                break;
            case R.id.tv_loginout://退出登录
                loginOut();
                break;
        }
    }

    private void loginOut() {
        //退出账号，再退出App
        SpUtils.putBoolean(CreateHomeActivity.this, "isRem", false);
        MyApplication.exit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CREATE_HOME) {
            setResult(REQUEST_HOME_F);
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime = 0;//记录点击物理返回键的时间

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            MyApplication.exit();
        }
    }
}
