package com.bt.Smart.Hox.activity.homeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvChoiceRoomAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/31 9:25
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MoveDeviceActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private TextView  tv_title;
    private TextView  tv_dev_name;
    private TextView  tv_room;//当前房间
    private ListView  lv_room;//可移动房间列表
    private TextView  tv_sure;//保存修改

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_device);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_dev_name = (TextView) findViewById(R.id.tv_dev_name);
        tv_room = (TextView) findViewById(R.id.tv_room);
        lv_room = (ListView) findViewById(R.id.lv_room);
        tv_sure = (TextView) findViewById(R.id.tv_sure);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("设备移动");
        List<String> mData = new ArrayList<>();
        mData.add("次卧");
        mData.add("书房");
        mData.add("主卧");
        mData.add("厨房");
        //        ChoiceRoomAdapter roomAdapter = new ChoiceRoomAdapter(this, mData);
        LvChoiceRoomAdapter choiceRoomAdapter = new LvChoiceRoomAdapter(this, mData);
        lv_room.setAdapter(choiceRoomAdapter);
        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_sure:
                //提交移动到新的房间

                break;
        }
    }
}
