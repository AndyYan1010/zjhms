package com.bt.Smart.Hox.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.R;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/29 15:37
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class FirstActivity extends Activity implements View.OnClickListener {
    private TextView tv_new;
    private TextView tv_old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_actiivty);
        MyApplication.flag = 0;
        getView();
        setData();
    }

    private void getView() {
        tv_new = (TextView) findViewById(R.id.tv_new);
        tv_old = (TextView) findViewById(R.id.tv_old);
    }

    private void setData() {
        tv_new.setOnClickListener(this);
        tv_old.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_new:
                //跳转注册界面
                Intent intent1 = new Intent(this, RegisterActivity.class);
                intent1.putExtra("kind", "rgs");
                startActivity(intent1);
//                finish();
                break;
            case R.id.tv_old:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
