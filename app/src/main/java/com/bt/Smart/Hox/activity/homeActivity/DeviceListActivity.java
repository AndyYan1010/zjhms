package com.bt.Smart.Hox.activity.homeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvDevListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/30 13:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DeviceListActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private TextView  tv_title;
    private ListView  lv_dev;
    private TextView  tv_add;//添加红外设备
    private String    kindName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev_list_actiivty);
        getView();
        setData();
    }

    private void getView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        lv_dev = (ListView) findViewById(R.id.lv_dev);
        tv_add = (TextView) findViewById(R.id.tv_add);

    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("设备列表");
        tv_add.setOnClickListener(this);
        tv_add.setVisibility(View.GONE);
        List mData = new ArrayList();
        mData.add("");
        mData.add("");
        mData.add("");
        mData.add("");
        LvDevListAdapter devListAdapter = new LvDevListAdapter(this, mData);
        lv_dev.setAdapter(devListAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add://添加红外设备

                break;
        }
    }
}
