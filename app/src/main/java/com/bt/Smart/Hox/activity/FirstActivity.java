package com.bt.Smart.Hox.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.NewApkInfo;
import com.bt.Smart.Hox.service.LoadingApkService;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/29 15:37
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class FirstActivity extends Activity implements View.OnClickListener {
    private TextView tv_new;
    private TextView tv_old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_actiivty);
        MyApplication.flag = 0;
        getView();
        setData();
        //获取最新的版本
        getNewApkInfo();
    }

    private void getView() {
        tv_new = (TextView) findViewById(R.id.tv_new);
        tv_old = (TextView) findViewById(R.id.tv_old);
    }

    private void setData() {
        tv_new.setOnClickListener(this);
        tv_old.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_new:
                //跳转注册界面
                Intent intent1 = new Intent(this, RegisterActivity.class);
                intent1.putExtra("kind", "rgs");
                startActivity(intent1);
                //                finish();
                break;
            case R.id.tv_old:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void getNewApkInfo() {
        HttpOkhUtils.getInstance().doGet(NetConfig.GETNEWAPPVERSION, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {

            }

            @Override
            public void onSuccess(int code, String resbody) {
                Gson gson = new Gson();
                NewApkInfo newApkInfo = gson.fromJson(resbody, NewApkInfo.class);
                if (1 == newApkInfo.getCode()) {
                    MyApplication.version_code = newApkInfo.getNewAppVersion().getVersion_code();
                    int appVersionCode = getAppVersionCode(FirstActivity.this);
                    if (appVersionCode < MyApplication.version_code) {
                        MyApplication.loadUrl = newApkInfo.getNewAppVersion().getApk_file();
                        //当前版本不是最新版本
                        //去下载最新版本
                        downLoadNewApk();
                    }
                }
            }
        });
    }

    private LoadingApkService apkService;

    private void downLoadNewApk() {
        Intent intent = new Intent(this, LoadingApkService.class);
        intent.putExtra("info", "downLoad");
        startService(intent);
    }

    //获取当前版本号
    private int getAppVersionCode(Context context) {
        int versionCode = 0;
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    //获取当前版本名
    private String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.bt.Smart.Hox", 0);
            versionName = packageInfo.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
