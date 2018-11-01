package com.bt.Smart.Hox.activity.homeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;

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
        img_back.setOnClickListener(this);
        tv_title.setText("选择设备类型");
        img_more.setVisibility(View.VISIBLE);
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
            case R.id.lin_zk://跳转主控列表
                startActivity(new Intent(this, DeviceListActivity.class));
                break;
            case R.id.lin_ck://跳转从控列表
                startActivity(new Intent(this, DeviceListActivity.class));
                break;
            case R.id.lin_sb://跳转设备列表
                startActivity(new Intent(this, DeviceListActivity.class));
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
}
