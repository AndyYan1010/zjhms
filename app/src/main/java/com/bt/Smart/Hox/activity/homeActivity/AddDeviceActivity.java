package com.bt.Smart.Hox.activity.homeActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.SaomiaoUIActivity;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/30 9:55
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddDeviceActivity extends BaseActivity implements View.OnClickListener {
    private ImageView      img_back;
    private ImageView      img_more;//扫描
    private TextView       tv_title;//标题
    private LinearLayout   lin_zk;
    private LinearLayout   lin_ck;
    private LinearLayout   lin_sb;
    private RelativeLayout rtv_zk;
    private RelativeLayout rtv_sb;
    private RelativeLayout rtv_light;
    private String         roomID;//房间id
    private String         homeID;//家id
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 1001;//申请照相机权限结果
    private int REQUEST_CODE                       = 1003;//接收扫描结果


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
        tv_title = (TextView) findViewById(R.id.tv_title);
        lin_zk = (LinearLayout) findViewById(R.id.lin_zk);
        lin_ck = (LinearLayout) findViewById(R.id.lin_ck);
        lin_sb = (LinearLayout) findViewById(R.id.lin_sb);
        rtv_zk = (RelativeLayout) findViewById(R.id.rtv_zk);
        rtv_sb = (RelativeLayout) findViewById(R.id.rtv_sb);
        rtv_light = (RelativeLayout) findViewById(R.id.rtv_light);
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
        rtv_zk.setOnClickListener(this);
        rtv_sb.setOnClickListener(this);
        rtv_light.setOnClickListener(this);
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
                intentDevZ.putExtra("devKind","zk");
                intentDevZ.putExtra("homeID",homeID);
                startActivity(intentDevZ);
                break;
            case R.id.lin_ck://跳转从控列表
                Intent intentDevC = new Intent(this, DeviceListActivity.class);
                intentDevC.putExtra("devKind","ck");
                intentDevC.putExtra("homeID",homeID);
                startActivity(intentDevC);
                break;
            case R.id.lin_sb://跳转设备列表
                Intent intentDevS = new Intent(this, DeviceListActivity.class);
                intentDevS.putExtra("devKind","sb");
                intentDevS.putExtra("homeID",homeID);
                startActivity(intentDevS);
                break;
            case R.id.rtv_zk:
                startActivity(new Intent(this, AddDevDetailActivity.class));
                break;
            case R.id.rtv_sb:
                startActivity(new Intent(this, AddDevDetailActivity.class));
                break;
            case R.id.rtv_light:
                startActivity(new Intent(this, AddDevDetailActivity.class));
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
