package com.bt.Smart.Hox.activity.homeActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.SaomiaoUIActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/30 10:35
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddDevDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private TextView  tv_title;
    private EditText  et_name;
    private EditText  et_kcode;
    private ImageView img_scan;
    private EditText  et_ycode;
    private TextView  tv_warn;
    private TextView  tv_add;
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 1001;//申请照相机权限结果
    private int REQUEST_CODE                       = 1003;//接收扫描结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dev_detail_actiivty);
        initView();
        setData();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_name = (EditText) findViewById(R.id.et_name);
        et_kcode = (EditText) findViewById(R.id.et_kcode);
        img_scan = (ImageView) findViewById(R.id.img_scan);
        et_ycode = (EditText) findViewById(R.id.et_ycode);
        tv_warn = (TextView) findViewById(R.id.tv_warn);
        tv_add = (TextView) findViewById(R.id.tv_add);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("添加");
        img_scan.setOnClickListener(this);
        tv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_scan:
                //扫描二维码
                //动态申请照相机权限,开启照相机
                scanningCode();
                break;
            case R.id.tv_add:

                break;
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
                    //获取扫描信息，填写到对应et
                    if (requestCode == REQUEST_CODE) {
                        et_kcode.setText(result);
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
