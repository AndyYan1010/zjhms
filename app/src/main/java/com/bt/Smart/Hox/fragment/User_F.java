package com.bt.Smart.Hox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.meActivity.FeedBackActivity;
import com.bt.Smart.Hox.activity.meActivity.HomeListActivity;


/**
 * @创建者 AndyYan
 * @创建时间 2018/5/22 16:42
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class User_F extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private TextView       tv_title;
    private RelativeLayout rtv_setName;//设置名称
    private ImageView      img_head;
    private TextView       tv_id;
    private RelativeLayout rtv_home;//家庭管理
    private RelativeLayout rtv_feedback;//意见反馈

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.user_f, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        tv_title = mRootView.findViewById(R.id.tv_title);
        rtv_setName = mRootView.findViewById(R.id.rtv_setName);
        img_head = mRootView.findViewById(R.id.img_head);
        tv_id = mRootView.findViewById(R.id.tv_id);
        rtv_home = mRootView.findViewById(R.id.rtv_home);
        rtv_feedback = mRootView.findViewById(R.id.rtv_feedback);
    }

    private void initData() {
        tv_title.setText("我");
        rtv_home.setOnClickListener(this);
        rtv_feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rtv_home: //跳转家管理界面
                startActivity(new Intent(getContext(), HomeListActivity.class));
                break;
            case R.id.rtv_feedback://跳转反馈界面
                startActivity(new Intent(getContext(), FeedBackActivity.class));
                break;
        }
    }


}
