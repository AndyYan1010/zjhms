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
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.PopupOpenHelper;
import com.espressif.iot.esptouch.EsptouchTask;
import com.espressif.iot.esptouch.IEsptouchListener;
import com.espressif.iot.esptouch.IEsptouchResult;
import com.espressif.iot.esptouch.IEsptouchTask;
import com.espressif.iot.esptouch.task.__IEsptouchTask;
import com.espressif.iot.esptouch.util.ByteUtil;
import com.espressif.iot.esptouch.util.EspNetUtil;

import java.lang.ref.WeakReference;
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
    private List<ScanResult> scanResults;
//    private Context mContext;
    private static final String TAG = "AddWifiFragment";

    private static final int REQUEST_PERMISSION = 0x01;

    private IEsptouchListener myListener = new IEsptouchListener() {

        @Override
        public void onEsptouchResultAdded(final IEsptouchResult result) {
            onEsptoucResultAddedPerform(result);
        }
    };

    private EsptouchAsyncTask4 mTask;

    private boolean mReceiverRegistered = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }

            WifiManager wifiManager = (WifiManager) context.getApplicationContext()
                    .getSystemService(Context.WIFI_SERVICE);
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

    private boolean mDestroyed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_wifi, null);
        initView();
        initData();
        if (isSDKAtLeastP()) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = {
                        Manifest.permission.ACCESS_COARSE_LOCATION
                };

                ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_PERMISSION);
            } else {
                registerBroadcastReceiver();
            }

        } else {
            registerBroadcastReceiver();
        }
        return mRootView;
    }

    private void initView() {
//        mContext = getContext();
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
                //连接wifi
                byte[] ssid = et_name.getTag() == null ? ByteUtil.getBytesByString(et_name.getText().toString())
                        : (byte[]) et_name.getTag();
                byte[] password = ByteUtil.getBytesByString(et_pass.getText().toString());
//                byte [] bssid = EspNetUtil.parseBssid2bytes(mApBssidTV.getText().toString());
//                byte[] deviceCount = mDeviceCountET.getText().toString().getBytes();
//                byte[] broadcast = {(byte) (mPackageModeGroup.getCheckedRadioButtonId() == R.id.package_broadcast
//                        ? 1 : 0)};

                if(mTask != null) {
                    mTask.cancelEsptouch();
                }
                mTask = new EsptouchAsyncTask4(this);
                mTask.execute(ssid, password);
                //跳转下个界面
//                FragmentTransaction ftt = getFragmentManager().beginTransaction();
//                SureAddDeviceFragment sureAddDevfrg = new SureAddDeviceFragment();
//                ftt.add(R.id.frame, sureAddDevfrg, "sureAddDevfrg");
//                ftt.addToBackStack(null);
//                ftt.commit();
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
        openHelper = new PopupOpenHelper(getActivity(), img_more_wifi, R.layout.popup_more_wifi);
        openHelper.openPopupWindow(true, Gravity.CENTER);
        openHelper.setOnPopupViewClick(new PopupOpenHelper.ViewClickListener() {
            @Override
            public void onViewClickListener(PopupWindow popupWindow, View inflateView) {
                ListView lv_wifi = inflateView.findViewById(R.id.lv_wifi);
                LvWifiInfoAdapter wifiInfoAdapter = new LvWifiInfoAdapter(getActivity(), scanResults);
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

    private void onEsptoucResultAddedPerform(final IEsptouchResult result) {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                String text = result.getBssid() + " 已连接到wifi";
                Toast.makeText(getActivity(), text,
                        Toast.LENGTH_LONG).show();
            }

        });
    }

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
    public void onDestroyView() {
        super.onDestroyView();

        mDestroyed = true;
        if (mReceiverRegistered) {
            getActivity().unregisterReceiver(mReceiver);
        }
    }

    private boolean isSDKAtLeastP() {
        return Build.VERSION.SDK_INT >= 28;
    }

    private void registerBroadcastReceiver() {
        IntentFilter filter = new IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        if (isSDKAtLeastP()) {
            filter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION);
        }
        getActivity().registerReceiver(mReceiver, filter);
        mReceiverRegistered = true;
    }

    private void onWifiChanged(WifiInfo info) {
        if (info == null) {
            et_name.setTag(null);
            tv_next.setEnabled(false);

            if (mTask != null) {
                mTask.cancelEsptouch();
                mTask = null;
                new AlertDialog.Builder(getActivity())
                        .setMessage("Wifi断开连接或改变")
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
            }
        } else {
            String ssid = info.getSSID();
            if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                ssid = ssid.substring(1, ssid.length() - 1);
            }
            et_name.setText(ssid);
            et_name.setTag(ByteUtil.getBytesByString(ssid));
            byte[] ssidOriginalData = EspUtils.getOriginalSsidBytes(info);
            et_name.setTag(ssidOriginalData);

//            String bssid = info.getBSSID();
//            mApBssidTV.setText(bssid);

            tv_next.setEnabled(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                int frequence = info.getFrequency();
                if (frequence > 4900 && frequence < 5900) {
                    // Connected 5G wifi. Device does not support 5G
                    Toast.makeText(getActivity(), "设备不支持5G网络", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

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
            Toast.makeText(getActivity(),"GPS不可用",Toast.LENGTH_SHORT).show();
        }
    }

    private class EsptouchAsyncTask4 extends AsyncTask<byte[], Void, List<IEsptouchResult>> {
        private AddWifiFragment mFragment;
        // without the lock, if the user tap confirm and cancel quickly enough,
        // the bug will arise. the reason is follows:
        // 0. task is starting created, but not finished
        // 1. the task is cancel for the task hasn't been created, it do nothing
        // 2. task is created
        // 3. Oops, the task should be cancelled, but it is running
        private final Object mLock = new Object();
//        private MyAlertDialogHelper mProgressDialog;
        private ProgressDialog mProgressDialog;
        private AlertDialog mResultDialog;
        private IEsptouchTask mEsptouchTask;

        EsptouchAsyncTask4(AddWifiFragment fragment){
            this.mFragment = fragment;
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
//             mProgressDialog=new MyAlertDialogHelper();
//            mProgressDialog.setDataNoView(mContext,"链接","Esptouch is configuring, please wait for a moment...");
//            mProgressDialog.setDialogClicker("", "", new MyAlertDialogHelper.DialogClickListener() {
//                @Override
//                public void onPositive() {
//                    synchronized (mLock) {
//                        if (__IEsptouchTask.DEBUG) {
//                            Log.i(TAG, "progress dialog cancel button canceled");
//                        }
//                        if (mEsptouchTask != null) {
//                            mEsptouchTask.interrupt();
//                        }
//                    }
//                }
//
//                @Override
//                public void onNegative() {
//                    synchronized (mLock) {
//                        if (__IEsptouchTask.DEBUG) {
//                            Log.i(TAG, "progress dialog back pressed canceled");
//                        }
//                        if (mEsptouchTask != null) {
//                            mEsptouchTask.interrupt();
//                        }
//                    }
//                }
//            });
//            mProgressDialog.show();
            mProgressDialog = new ProgressDialog(mFragment.getContext());
            mProgressDialog.setMessage("Esptouch is configuring, please wait for a moment...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    synchronized (mLock) {
                        if (__IEsptouchTask.DEBUG) {
                            Log.i(TAG, "progress dialog back pressed canceled");
                        }
                        if (mEsptouchTask != null) {
                            mEsptouchTask.interrupt();
                        }
                    }
                }
            });
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, mFragment.getContext().getText(android.R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            synchronized (mLock) {
                                if (__IEsptouchTask.DEBUG) {
                                    Log.i(TAG, "progress dialog cancel button canceled");
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
            int taskResultCount;
            synchronized (mLock) {
                byte[] apSsid = params[0];
                byte[] apBssid = params[1];
                byte[] apPassword = params[2];
                byte[] deviceCountData = params[3];
                byte[] broadcastData = params[4];
                taskResultCount = deviceCountData.length == 0 ? -1 : Integer.parseInt(new String(deviceCountData));
                Context context = mFragment.getContext().getApplicationContext();
                mEsptouchTask = new EsptouchTask(apSsid, apBssid, apPassword, context);
                mEsptouchTask.setPackageBroadcast(broadcastData[0] == 1);
                mEsptouchTask.setEsptouchListener(myListener);
            }
            return mEsptouchTask.executeForResults(taskResultCount);
        }

        @Override
        protected void onPostExecute(List<IEsptouchResult> result) {
            mProgressDialog.dismiss();
            mResultDialog = new AlertDialog.Builder(mFragment.getContext())
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
                } else {
                    mResultDialog.setMessage("Esptouch fail");
                }

                mResultDialog.show();
            }

            mTask = null;
        }
    }
}
