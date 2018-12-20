package com.bt.Smart.Hox.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.NewApkInfo;
import com.bt.Smart.Hox.util.UpApkDataFile.UpdateAppUtil;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.SoundPoolUtil;
import com.bt.Smart.Hox.utils.SpUtils;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/12 9:13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SettingFragment extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private ImageView      img_back;
    private TextView       tv_title;
    private SwitchCompat   swc_yy;
    private RelativeLayout rlt_upgrade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_setting, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        swc_yy = mRootView.findViewById(R.id.swc_yy);
        rlt_upgrade = mRootView.findViewById(R.id.rlt_upgrade);

    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("设置");
        rlt_upgrade.setOnClickListener(this);

        Boolean isOpenVoice = SpUtils.getBoolean(getContext(), "isOpenVoice", false);
        if (isOpenVoice){
            swc_yy.setChecked(true);
        }else {
            swc_yy.setChecked(false);
        }
        swc_yy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    SpUtils.putBoolean(getContext(),"isOpenVoice",true);
                    SoundPoolUtil.openSoundPlay();
                    ToastUtils.showToast(getContext(),"按键声音已开启。");
                }else {
                    SpUtils.putBoolean(getContext(),"isOpenVoice",false);
                    SoundPoolUtil.closeSoundPlay();
                    ToastUtils.showToast(getContext(),"按键声音已关闭。");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.rlt_upgrade:
                //获取最新的版本
                getNewApkInfo();
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
                ToastUtils.showToast(getContext(), newApkInfo.getMessage());
                if (1 == newApkInfo.getCode()) {
                    int appVersionCode = getAppVersionCode(getContext());
                    if (null != newApkInfo.getNewAppVersion()) {
                        if (appVersionCode < newApkInfo.getNewAppVersion().getId()) {
                            //弹出dailog，提示用户是否下载
                            showDialogToDown(newApkInfo);
                        } else {
                            ToastUtils.showToast(getContext(), "您已安装最新版本，无需更新。");
                        }
                    }
                }
            }
        });
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

    private void showDialogToDown(NewApkInfo newApkInfo) {
        MyApplication.loadUrl = NetConfig.IMG_HEAD_IP + newApkInfo.getNewAppVersion().getApk_file();
        UpdateAppUtil.from(getActivity())
                .serverVersionCode(newApkInfo.getNewAppVersion().getId())  //服务器versionCode
                .serverVersionName(newApkInfo.getNewAppVersion().getShow_code()) //服务器versionName
                .apkPath(MyApplication.loadUrl) //最新apk下载地址
                .updateInfo(newApkInfo.getNewAppVersion().getChange_message())
                .update();
    }
}
