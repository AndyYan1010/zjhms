package com.bt.Smart.Hox.activity.homeActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.SaomiaoUIActivity;
import com.bt.Smart.Hox.adapter.LvAddDevListAdapter;
import com.bt.Smart.Hox.messegeInfo.DeviceTypeInfo;
import com.bt.Smart.Hox.messegeInfo.ZhuKongListInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/30 9:55
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddDeviceActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                                        img_back;
    private ImageView                                        img_more;//扫描
    private TextView                                         tv_title;//标题
    private LinearLayout                                     lin_zk;
    private LinearLayout                                     lin_ck;
    private LinearLayout                                     lin_sb;
    private List<DeviceTypeInfo.DeviceTypeListBean.DataBean> mData;//可添加设备数据
    private ListView                                         lv_device;//可添加设备列表
    private LvAddDevListAdapter                              addDevListAdapter;
    private String                                           roomID;//房间id
    private String                                           homeID;//家id
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 1001;//申请照相机权限结果
    private int REQUEST_CODE                       = 1003;//接收扫描结果
    private int zknum;

    private RelativeLayout rlt_add_wifi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dev_actiivty);
        getView();
        setData();
    }

    private void getView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        img_more = (ImageView) findViewById(R.id.img_more);
        rlt_add_wifi = (RelativeLayout) findViewById(R.id.rlt_add_wifi);
        tv_title = (TextView) findViewById(R.id.tv_title);
        lin_zk = (LinearLayout) findViewById(R.id.lin_zk);
        lin_ck = (LinearLayout) findViewById(R.id.lin_ck);
        lin_sb = (LinearLayout) findViewById(R.id.lin_sb);
        lv_device = (ListView) findViewById(R.id.lv_device);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        tv_title.setText("选择设备类型");
        img_more.setVisibility(View.VISIBLE);
        roomID = getIntent().getStringExtra("roomID");
        homeID = getIntent().getStringExtra("homeID");
        img_back.setOnClickListener(this);
        img_more.setOnClickListener(this);
        lin_zk.setOnClickListener(this);
        lin_ck.setOnClickListener(this);
        lin_sb.setOnClickListener(this);

        rlt_add_wifi.setOnClickListener(this);
        //获取主控列表
        //        getZKDevList();
        mData = new ArrayList();
        addDevListAdapter = new LvAddDevListAdapter(this, mData);
        lv_device.setAdapter(addDevListAdapter);
        lv_device.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //先判断该家下是否有主控
                if (!"0".equals(mData.get(i).getDevcieType())) {
                    if (0 == zknum) {
                        //提示没有主控，先添加主控
                        showNoZkDialog();
                        return;
                    }
                }
                Intent intent = new Intent(AddDeviceActivity.this, AddDevDetailActivity.class);
                intent.putExtra("homeID", homeID);
                intent.putExtra("roomID", roomID);
                intent.putExtra("devType", mData.get(i).getDevcieType());//主控/从控/单品
                intent.putExtra("name", mData.get(i).getDeviceDescibe());
                intent.putExtra("control_type", mData.get(i).getDeviceTypeName());
                intent.putExtra("device_type_id", mData.get(i).getId());
                intent.putExtra("devcieTypePic", mData.get(i).getDevcieTypePic());
                startActivity(intent);
            }
        });
        //获取可添加设备列表
        getAllAddDevList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取主控列表
        getZKDevList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_more://打开二维码扫描界面
                //扫描二维码
                //动态申请照相机权限,开启照相机
                scanningCode();
                break;
            case R.id.lin_zk://跳转主控列表
                Intent intentDevZ = new Intent(this, DeviceListActivity.class);
                intentDevZ.putExtra("devKind", "zk");
                intentDevZ.putExtra("homeID", homeID);
                startActivity(intentDevZ);
                break;
            case R.id.lin_ck://跳转从控列表
                Intent intentDevC = new Intent(this, DeviceListActivity.class);
                intentDevC.putExtra("devKind", "ck");
                intentDevC.putExtra("homeID", homeID);
                startActivity(intentDevC);
                break;
            case R.id.lin_sb://跳转设备列表
                Intent intentDevS = new Intent(this, DeviceListActivity.class);
                intentDevS.putExtra("devKind", "sb");
                intentDevS.putExtra("homeID", homeID);
                intentDevS.putExtra("roomID", roomID);
                startActivity(intentDevS);
                break;
            case R.id.rlt_add_wifi:
                startActivity(new Intent(this, AddWifiDeviceActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //获取扫描信息
                    if (requestCode == REQUEST_CODE) {
                        ToastUtils.showToast(this, result);
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private MyAlertDialogHelper openHelper;

    private void showNoZkDialog() {
        openHelper = new MyAlertDialogHelper();
        View view = View.inflate(this, R.layout.dialog_input_pass, null);
        openHelper.setDIYView(this, view);
        openHelper.show();
        ImageView img_title = view.findViewById(R.id.img_title);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_warning = view.findViewById(R.id.tv_warning);
        EditText et_pass = view.findViewById(R.id.et_pass);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_sure = view.findViewById(R.id.tv_sure);
        img_title.setVisibility(View.GONE);
        tv_title.setText("无可用Lora主控");
        tv_warning.setText("添加从控设备需要配置主控，请先添加主控！");
        et_pass.setVisibility(View.GONE);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHelper.disMiss();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHelper.disMiss();
            }
        });
    }

    private void getZKDevList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.MAINCONTROL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddDeviceActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddDeviceActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                ZhuKongListInfo zhuKongListInfo = gson.fromJson(resbody, ZhuKongListInfo.class);
                if (1 == zhuKongListInfo.getCode()) {
                    ToastUtils.showToast(AddDeviceActivity.this, zhuKongListInfo.getMessage());
                    zknum = zhuKongListInfo.getHomeList().size();

                } else {
                    ToastUtils.showToast(AddDeviceActivity.this, "查询失败");
                }
            }
        });
    }

    private void getAllAddDevList() {
        HttpOkhUtils.getInstance().doGet(NetConfig.DEVICETYPE, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddDeviceActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddDeviceActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                DeviceTypeInfo deviceTypeInfo = gson.fromJson(resbody, DeviceTypeInfo.class);
                ToastUtils.showToast(AddDeviceActivity.this, deviceTypeInfo.getMessage());
                if (1 == deviceTypeInfo.getCode()) {
                    mData.addAll(deviceTypeInfo.getDeviceTypeList().getData());
                    addDevListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void scanningCode() {
        //第二个参数是需要申请的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);
        } else {
            Intent intent = new Intent(this, SaomiaoUIActivity.class);//这是一个自定义的扫描界面，扫描UI框放大了。
            // Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
}
