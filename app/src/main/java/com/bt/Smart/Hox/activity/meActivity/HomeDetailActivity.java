package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvMemberAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * @创建者 AndyYan
 * @创建时间 2018/10/31 13:12
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HomeDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView    img_back;
    private TextView     tv_title;
    private LinearLayout lin_add;
    private ListView     lv_member;
    private TextView     tv_delete;//移除家庭


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        lin_add = (LinearLayout) findViewById(R.id.lin_add);
        lv_member = (ListView) findViewById(R.id.lv_member);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
    }

    private void setData() {
        List mData = new ArrayList();
        mData.add("");
        mData.add("");
        LvMemberAdapter memberAdapter = new LvMemberAdapter(this, mData);
        lv_member.setAdapter(memberAdapter);
        tv_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:

                break;
        }
    }
}
