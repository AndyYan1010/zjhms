package com.bt.Smart.Hox.activity.homeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.ToastUtils;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/31 8:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddRoomActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private TextView  tv_title;
    private EditText  et_name;
    private TextView  tv_recom1;
    private TextView  tv_recom2;
    private TextView  tv_submit;
    private int ROOM_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_recom1 = (TextView) findViewById(R.id.tv_recom1);
        tv_recom2 = (TextView) findViewById(R.id.tv_recom2);
        tv_submit = (TextView) findViewById(R.id.tv_submit);

    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("添加房间");
        tv_recom1.setOnClickListener(this);
        tv_recom2.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_recom1:
                et_name.setText("餐厅");
                break;
            case R.id.tv_recom2:
                et_name.setText("衣帽间");
                break;
            case R.id.tv_submit://提交房间
                String room_name = String.valueOf(et_name.getText()).trim();
                if ("".equals(room_name)||"房间名称".equals(room_name)) {
                    ToastUtils.showToast(this,"请填写房间名称");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("roomName", room_name);
                setResult(ROOM_REQUEST_CODE, intent);
                finish();
                break;
        }
    }
}
