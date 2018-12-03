package com.bt.Smart.Hox.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.NewApkInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;
import util.UpdateAppUtils;

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
        //设置全屏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.first_actiivty);
        MyApplication.flag = 0;
        getView();
        setData();
        verifyStoragePermissions(this);
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
                //finish();
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
                    int appVersionCode = getAppVersionCode(FirstActivity.this);
                    if (null != newApkInfo.getNewAppVersion()) {
                        if (appVersionCode < newApkInfo.getNewAppVersion().getId()) {
                            //弹出dailog，提示用户是否下载
                            showDialogToDown(newApkInfo);
                        }
                    }
                }
            }
        });
    }

    private void showDialogToDown(NewApkInfo newApkInfo) {
        MyApplication.loadUrl = NetConfig.IMG_HEAD_IP + newApkInfo.getNewAppVersion().getApk_file();
        UpdateAppUtils.from(this)
                .serverVersionCode(newApkInfo.getNewAppVersion().getId())  //服务器versionCode
                .serverVersionName(newApkInfo.getNewAppVersion().getShow_code()) //服务器versionName
                .apkPath(MyApplication.loadUrl) //最新apk下载地址
                .updateInfo(newApkInfo.getNewAppVersion().getChange_message())
                .update();
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

    private static final int      REQUEST_EXTERNAL_STORAGE = 1;
    private static       String[] PERMISSIONS_STORAGE      = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};


    public void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
