package com.bt.Smart.Hox.activity.meActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvHomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/31 10:49
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HomeListActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private TextView  tv_title;//标题
    private ImageView img_home;
    private TextView  tv_add;//添加新的家
    private ListView  lv_home;//家列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        img_home = (ImageView) findViewById(R.id.img_home);
        tv_add = (TextView) findViewById(R.id.tv_add);
        lv_home = (ListView) findViewById(R.id.lv_home);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("家列表");
        tv_add.setOnClickListener(this);
        List mData = new ArrayList();
        mData.add("");
        mData.add("");
        mData.add("");
        LvHomeAdapter homeAdapter = new LvHomeAdapter(this, mData);
        lv_home.setAdapter(homeAdapter);
        lv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转家庭编辑界面
                startActivity(new Intent(HomeListActivity.this, HomeDetailActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add:
                //跳转添加家庭界面

                break;
        }
    }
}
