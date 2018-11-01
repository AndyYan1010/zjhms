package com.bt.Smart.Hox.activity.meActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.AddRoomActivity;
import com.bt.Smart.Hox.adapter.LvAddRoomAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/31 15:16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddHomeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView    img_back;
    private TextView     tv_title;
    private EditText     et_name;//填写家庭名称
    private TextView     tv_choice;//选择
    private LinearLayout lin_add;//添加其他房间
    private ListView     lv_room;//房间列表
    private TextView     tv_setup;//确认创建

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_choice = (TextView) findViewById(R.id.tv_choice);
        lin_add = (LinearLayout) findViewById(R.id.lin_add);
        lv_room = (ListView) findViewById(R.id.lv_room);
        tv_setup = (TextView) findViewById(R.id.tv_setup);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("添加家庭");
        tv_choice.setOnClickListener(this);
        lin_add.setOnClickListener(this);
        tv_setup.setOnClickListener(this);
        List mData = new ArrayList();
        mData.add("");
        mData.add("");
        mData.add("");
        LvAddRoomAdapter addRoomAdapter = new LvAddRoomAdapter(this, mData);
        lv_room.setAdapter(addRoomAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_choice://选择位置


                break;
            case R.id.lin_add://添加房间
                startActivity(new Intent(this, AddRoomActivity.class));
                break;
            case R.id.tv_setup://返回数据并关闭页面

                break;
        }
    }
}
