package com.bt.Smart.Hox.fragment;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvWifiInfoAdapter;
import com.bt.Smart.Hox.utils.PopupOpenHelper;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/16 14:08
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddWifiFragment extends Fragment implements View.OnClickListener {
    private View             mRootView;
    private ImageView        img_back;
    private TextView         tv_title;
    private EditText         et_name;
    private ImageView        img_more_wifi;
    private EditText         et_pass;
    private TextView         tv_next;//下一步
//    private List<ScanResult> mList;
    private List<ScanResult> scanResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_wifi, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        et_name = mRootView.findViewById(R.id.et_name);
        img_more_wifi = mRootView.findViewById(R.id.img_more_wifi);
        et_pass = mRootView.findViewById(R.id.et_pass);
        tv_next = mRootView.findViewById(R.id.tv_next);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("选择设备工作Wi-Fi");
        tv_next.setOnClickListener(this);
        img_more_wifi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.img_more_wifi:
                //搜索附近wifi
                searchWifi();
                break;
            case R.id.tv_next:
                //跳转下个界面
                FragmentTransaction ftt = getFragmentManager().beginTransaction();
                SureAddDeviceFragment sureAddDevfrg = new SureAddDeviceFragment();
                ftt.add(R.id.frame, sureAddDevfrg, "sureAddDevfrg");
                ftt.addToBackStack(null);
                ftt.commit();
                break;
        }
    }

    private boolean isAdd;

    private void searchWifi() {
        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        wifiManager.startScan();  //开始扫描AP
        scanResults= wifiManager.getScanResults();
//        for (int i = 0; i < scanResults.size(); i++) {
//            isAdd = false;
//            for (ScanResult result : mList) {
//                if (result.SSID.equals(scanResults.get(i))) {
//                    isAdd = true;
//                }
//            }
//            if (!isAdd)
//                mList.add(scanResults.get(i));
//        }

        //弹出popupwindow显示搜索到的wifi
        showMoreWiFi();
    }

    private PopupOpenHelper openHelper;

    private void showMoreWiFi() {
        openHelper = new PopupOpenHelper(getContext(), img_more_wifi, R.layout.popup_more_wifi);
        openHelper.openPopupWindow(true, Gravity.CENTER);
        openHelper.setOnPopupViewClick(new PopupOpenHelper.ViewClickListener() {
            @Override
            public void onViewClickListener(PopupWindow popupWindow, View inflateView) {
                ListView lv_wifi = inflateView.findViewById(R.id.lv_wifi);
                LvWifiInfoAdapter wifiInfoAdapter = new LvWifiInfoAdapter(getContext(), scanResults);
                lv_wifi.setAdapter(wifiInfoAdapter);
                lv_wifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        et_name.setText(scanResults.get(i).SSID);//
                        openHelper.dismiss();
                    }
                });
            }
        });
    }
}
