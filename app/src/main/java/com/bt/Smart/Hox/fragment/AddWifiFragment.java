package com.bt.Smart.Hox.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
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
import android.widget.Toast;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.AddWifiDeviceActivity;
import com.bt.Smart.Hox.adapter.LvWifiInfoAdapter;
import com.bt.Smart.Hox.util.EspUtils;
import com.bt.Smart.Hox.utils.PopupOpenHelper;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.espressif.iot.esptouch.EsptouchTask;
import com.espressif.iot.esptouch.IEsptouchListener;
import com.espressif.iot.esptouch.IEsptouchResult;
import com.espressif.iot.esptouch.IEsptouchTask;
import com.espressif.iot.esptouch.task.__IEsptouchTask;
import com.espressif.iot.esptouch.util.ByteUtil;
import com.espressif.iot.esptouch.util.EspNetUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
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
    private ImageView        img_show_pass;//显示密码
    private EditText         et_pass;
    private TextView         tv_next;//下一步
    private List<ScanResult> scanResults;
    private List<ScanResult> mList;
    private boolean isShowPass = false;//是否明文显示密码

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
        img_show_pass = mRootView.findViewById(R.id.img_show_pass);
        et_pass = mRootView.findViewById(R.id.et_pass);
        tv_next = mRootView.findViewById(R.id.tv_next);
    }

    private static final int REQUEST_PERMISSION = 0x01;

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("选择设备工作Wi-Fi");
        tv_next.setOnClickListener(this);
        img_more_wifi.setOnClickListener(this);
        img_show_pass.setOnClickListener(this);
        if (isSDKAtLeastP()) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = {
                        Manifest.permission.ACCESS_COARSE_LOCATION
                };

                ActivityCompat.requestPermissions((Activity) getContext(), permissions, REQUEST_PERMISSION);
            } else {
                registerBroadcastReceiver();
            }
        } else {
            registerBroadcastReceiver();
        }
        //动态申请权限

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                //连接成功
                getActivity().finish();
                break;
            case R.id.img_more_wifi:
                isHashRight();
                break;
            case R.id.img_show_pass:
                showPassWord();
                break;
            case R.id.tv_next:
                String name = String.valueOf(et_name.getText()).trim();
                String pass = String.valueOf(et_pass.getText()).trim();
                if ("".equals(name) || "请选择可用的Wi-Fi网络".equals(name)) {
                    ToastUtils.showToast(getContext(), "请选择可用的Wi-Fi网络");
                    return;
                }
                if ("".equals(pass) || "请输入Wi-Fi密码".equals(pass)) {
                    ToastUtils.showToast(getContext(), "请输入Wi-Fi密码");
                    return;
                }

                //连接wifi
                byte[] ssid = et_name.getTag() == null ? ByteUtil.getBytesByString(et_name.getText().toString())
                        : (byte[]) et_name.getTag();
                byte[] password = ByteUtil.getBytesByString(et_pass.getText().toString());
                byte[] bssid = EspNetUtil.parseBssid2bytes(mBssid);
                byte[] deviceCount = "1".getBytes();
                byte[] broadcast = {(byte) 1};

                if (mTask != null) {
                    mTask.cancelEsptouch();
                }
                mTask = new EsptouchAsyncTask4((AddWifiDeviceActivity) getActivity(), myListener, mTask);
                mTask.execute(ssid, bssid, password, deviceCount, broadcast);
                //跳转下个界面
                //                FragmentTransaction ftt = getFragmentManager().beginTransaction();
                //                SureAddDeviceFragment sureAddDevfrg = new SureAddDeviceFragment();
                //                ftt.add(R.id.frame, sureAddDevfrg, "sureAddDevfrg");
                //                ftt.addToBackStack(null);
                //                ftt.commit();
                break;
        }
    }

    private void showPassWord() {
        isShowPass = !isShowPass;
        if (isShowPass) {
            et_pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            et_pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        et_pass.setSelection(et_pass.length());
    }

    private boolean mReceiverRegistered = false;
    private boolean mDestroyed          = false;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (!mDestroyed) {
                        registerBroadcastReceiver();
                    }
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDestroyed = true;
        if (mReceiverRegistered) {
            getActivity().unregisterReceiver(mReceiver);
        }
    }

    private EsptouchAsyncTask4 mTask;
    private IEsptouchListener myListener = new IEsptouchListener() {

        @Override
        public void onEsptouchResultAdded(final IEsptouchResult result) {
            onEsptoucResultAddedPerform(result);
        }
    };

    private void onEsptoucResultAddedPerform(final IEsptouchResult result) {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                String text = result.getBssid() + " is connected to the wifi";
                Toast.makeText(getContext(), text,
                        Toast.LENGTH_LONG).show();
            }

        });
    }

    private static class EsptouchAsyncTask4 extends AsyncTask<byte[], Void, List<IEsptouchResult>> {
        private WeakReference<AddWifiDeviceActivity> mActivity;

        // without the lock, if the user tap confirm and cancel quickly enough,
        // the bug will arise. the reason is follows:
        // 0. task is starting created, but not finished
        // 1. the task is cancel for the task hasn't been created, it do nothing
        // 2. task is created
        // 3. Oops, the task should be cancelled, but it is running
        private final Object mLock = new Object();
        private ProgressDialog     mProgressDialog;
        private AlertDialog        mResultDialog;
        private IEsptouchTask      mEsptouchTask;
        private IEsptouchListener  myListener;
        private EsptouchAsyncTask4 mTask;
        private int REQUEST_SET_SEC = 20002;

        EsptouchAsyncTask4(AddWifiDeviceActivity activity, IEsptouchListener listener, EsptouchAsyncTask4 task) {
            mActivity = new WeakReference<>(activity);
            this.myListener = listener;
            this.mTask = task;
        }

        void cancelEsptouch() {
            cancel(true);
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
            if (mResultDialog != null) {
                mResultDialog.dismiss();
            }
            if (mEsptouchTask != null) {
                mEsptouchTask.interrupt();
            }
        }

        @Override
        protected void onPreExecute() {
            Activity activity = mActivity.get();
            mProgressDialog = new ProgressDialog(activity);
            //            mProgressDialog.setMessage("Esptouch is configuring, please wait for a moment...");
            mProgressDialog.setMessage("正在配置连接...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    synchronized (mLock) {
                        if (__IEsptouchTask.DEBUG) {
                            //                            Log.i(TAG, "progress dialog back pressed canceled");
                        }
                        if (mEsptouchTask != null) {
                            mEsptouchTask.interrupt();
                        }
                    }
                }
            });
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, activity.getText(android.R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            synchronized (mLock) {
                                if (__IEsptouchTask.DEBUG) {
                                    //  Log.i(TAG, "progress dialog cancel button canceled");
                                }
                                if (mEsptouchTask != null) {
                                    mEsptouchTask.interrupt();
                                }
                            }
                        }
                    });
            mProgressDialog.show();
        }

        @Override
        protected List<IEsptouchResult> doInBackground(byte[]... params) {
            AddWifiDeviceActivity activity = mActivity.get();
            int taskResultCount;
            synchronized (mLock) {
                byte[] apSsid = params[0];
                byte[] apBssid = params[1];
                byte[] apPassword = params[2];
                byte[] deviceCountData = params[3];
                byte[] broadcastData = params[4];
                taskResultCount = deviceCountData.length == 0 ? -1 : Integer.parseInt(new String(deviceCountData));
                Context context = activity.getApplicationContext();
                mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword, context);
                mEsptouchTask.setPackageBroadcast(broadcastData[0] == 1);
                mEsptouchTask.setEsptouchListener(myListener);
            }
            return mEsptouchTask.executeForResults(taskResultCount);
        }

        @Override
        protected void onPostExecute(List<IEsptouchResult> result) {
            AddWifiDeviceActivity activity = mActivity.get();
            mProgressDialog.dismiss();
            mResultDialog = new AlertDialog.Builder(activity)
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
            mResultDialog.setCanceledOnTouchOutside(false);
            if (result == null) {
                mResultDialog.setMessage("Create Esptouch task failed, the esptouch port could be used by other thread");
                mResultDialog.show();
                return;
            }

            IEsptouchResult firstResult = result.get(0);
            // check whether the task is cancelled and no results received
            if (!firstResult.isCancelled()) {
                int count = 0;
                // max results to be displayed, if it is more than maxDisplayCount,
                // just show the count of redundant ones
                final int maxDisplayCount = 5;
                // the task received some results including cancelled while
                // executing before receiving enough results
                if (firstResult.isSuc()) {
                    StringBuilder sb = new StringBuilder();
                    for (IEsptouchResult resultInList : result) {
                        sb.append("Esptouch success, bssid = ")
                                .append(resultInList.getBssid())
                                .append(", InetAddress = ")
                                .append(resultInList.getInetAddress().getHostAddress())
                                .append("\n");
                        count++;
                        if (count >= maxDisplayCount) {
                            break;
                        }
                    }
                    if (count < result.size()) {
                        sb.append("\nthere's ")
                                .append(result.size() - count)
                                .append(" more result(s) without showing\n");
                    }
                    mResultDialog.setMessage(sb.toString());
                    activity.setResult(20002);
                } else {
                    mResultDialog.setMessage("Esptouch fail");
                }

                mResultDialog.show();
            }

            mTask = null;
        }
    }

    private void registerBroadcastReceiver() {
        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        if (isSDKAtLeastP()) {
            filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION);
        }
        getActivity().registerReceiver(mReceiver, filter);
        mReceiverRegistered = true;
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }

            WifiManager wifiManager = (WifiManager) context.getApplicationContext()
                    .getSystemService(getActivity().WIFI_SERVICE);
            assert wifiManager != null;

            switch (action) {
                case WifiManager.NETWORK_STATE_CHANGED_ACTION:
                    WifiInfo wifiInfo;
                    if (intent.hasExtra(WifiManager.EXTRA_WIFI_INFO)) {
                        wifiInfo = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO);
                    } else {
                        wifiInfo = wifiManager.getConnectionInfo();
                    }
                    onWifiChanged(wifiInfo);
                    break;
                case LocationManager.PROVIDERS_CHANGED_ACTION:
                    onWifiChanged(wifiManager.getConnectionInfo());
                    onLocationChanged();
                    break;
            }
        }
    };

    private void onLocationChanged() {
        boolean enable;
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null) {
            enable = false;
        } else {
            boolean locationGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean locationNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            enable = locationGPS || locationNetwork;
        }

        if (!enable) {
            //            mMessageTV.setText(R.string.location_disable_message);
        }
    }

    private void onWifiChanged(WifiInfo info) {
        if (info == null) {
            //            mApSsidTV.setText("");
            //            mApSsidTV.setTag(null);
            //            mApBssidTV.setTag("");
            //            mMessageTV.setText("");
            //            mConfirmBtn.setEnabled(false);

            if (mTask != null) {
                mTask.cancelEsptouch();
                mTask = null;
                new AlertDialog.Builder(getContext())
                        .setMessage("Wifi disconnected or changed")
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
            }
        } else {
            String ssid = info.getSSID();
            if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                ssid = ssid.substring(1, ssid.length() - 1);
            }
            //            mApSsidTV.setText(ssid);
            //            mApSsidTV.setTag(ByteUtil.getBytesByString(ssid));
            byte[] ssidOriginalData = EspUtils.getOriginalSsidBytes(info);
            //            mApSsidTV.setTag(ssidOriginalData);

            String bssid = info.getBSSID();
            //            mApBssidTV.setText(bssid);

            //            mConfirmBtn.setEnabled(true);
            //            mMessageTV.setText("");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int frequence = info.getFrequency();
                if (frequence > 4900 && frequence < 5900) {
                    // Connected 5G wifi. Device does not support 5G
                    // mMessageTV.setText(R.string.wifi_5g_message);
                }
            }
        }
    }

    private boolean isSDKAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    private boolean isAdd;

    public void searchWifi() {
        if (null == mList) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }
        WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        wifiManager.startScan();  //开始扫描AP
        scanResults = wifiManager.getScanResults();
        for (int i = 0; i < scanResults.size(); i++) {
            isAdd = false;
            for (ScanResult result : mList) {
                if (result.SSID.equals(scanResults.get(i).SSID) || "".equals(scanResults.get(i).SSID)) {
                    isAdd = true;
                }
            }
            if (!isAdd)
                mList.add(scanResults.get(i));
        }

        //弹出popupwindow显示搜索到的wifi
        showMoreWiFi();
    }

    private PopupOpenHelper openHelper;
    private String          mBssid;

    private void showMoreWiFi() {
        openHelper = new PopupOpenHelper(getActivity(), img_more_wifi, R.layout.popup_more_wifi);
        openHelper.openPopupWindow(true, Gravity.CENTER);
        openHelper.setOnPopupViewClick(new PopupOpenHelper.ViewClickListener() {
            @Override
            public void onViewClickListener(PopupWindow popupWindow, View inflateView) {
                ListView lv_wifi = inflateView.findViewById(R.id.lv_wifi);
                LvWifiInfoAdapter wifiInfoAdapter = new LvWifiInfoAdapter(getActivity(), mList);
                lv_wifi.setAdapter(wifiInfoAdapter);
                lv_wifi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        et_name.setText(mList.get(i).SSID);//
                        mBssid = mList.get(i).BSSID;
                        openHelper.dismiss();
                    }
                });
            }
        });
    }

    private void isHashRight() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            ToastUtils.showToast(getContext(), "已开启定位权限");
            //搜索附近wifi
            searchWifi();
        }
    }
}
