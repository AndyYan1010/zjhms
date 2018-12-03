package com.bt.Smart.Hox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.meActivity.DeviceManagerActivity;
import com.bt.Smart.Hox.activity.meActivity.FeedBackActivity;
import com.bt.Smart.Hox.activity.meActivity.HomeListActivity;
import com.bt.Smart.Hox.activity.meActivity.MineMsgCenterActivity;
import com.bt.Smart.Hox.activity.meActivity.PersonalActivity;
import com.bt.Smart.Hox.activity.meActivity.SceneListActivity;
import com.bt.Smart.Hox.activity.meActivity.SettingActivity;
import com.bt.Smart.Hox.util.GlideLoaderUtil;


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
    private ImageView      img_head;
    private CardView       card;
    private TextView       tv_name;//用户名称
    private LinearLayout   lin_scene;//场景
    private LinearLayout   lin_auto;//自动化
    private LinearLayout   lin_shop;//商城
    private RelativeLayout rtv_person;//个人信息编辑
    private RelativeLayout rtv_home;//家庭管理
    private RelativeLayout rtv_dev;//设备管理
    private RelativeLayout rtv_msg_center;//消息中心
    private RelativeLayout rtv_setting;//通用设置
    private RelativeLayout rtv_feedback;//意见反馈

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.user_f, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_head = mRootView.findViewById(R.id.img_head);
        tv_name = mRootView.findViewById(R.id.tv_name);
        card = mRootView.findViewById(R.id.card);
        lin_scene = mRootView.findViewById(R.id.lin_scene);
        lin_auto = mRootView.findViewById(R.id.lin_auto);
        lin_shop = mRootView.findViewById(R.id.lin_shop);
        rtv_person = mRootView.findViewById(R.id.rtv_person);
        rtv_home = mRootView.findViewById(R.id.rtv_home);
        rtv_dev = mRootView.findViewById(R.id.rtv_dev);
        rtv_msg_center = mRootView.findViewById(R.id.rtv_msg_center);
        rtv_setting = mRootView.findViewById(R.id.rtv_setting);
        rtv_feedback = mRootView.findViewById(R.id.rtv_feedback);
    }

    private void initData() {
        lin_scene.setOnClickListener(this);
        lin_auto.setOnClickListener(this);
        rtv_home.setOnClickListener(this);
        card.setOnClickListener(this);
        rtv_setting.setOnClickListener(this);
        rtv_dev.setOnClickListener(this);
        rtv_msg_center.setOnClickListener(this);
        rtv_feedback.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        GlideLoaderUtil.showImgWithIcon(getContext(), MyApplication.userHeadPic, R.drawable.iman, R.drawable.iman, img_head);
        tv_name.setText(MyApplication.userName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_scene://跳转场景列表
                Intent intent = new Intent(getContext(), SceneListActivity.class);
                intent.putExtra("kind", "场景");
                startActivity(intent);
                break;
            case R.id.lin_auto://跳转自动化列表
                Intent autoIntent = new Intent(getContext(), SceneListActivity.class);
                autoIntent.putExtra("kind", "自动化");
                startActivity(autoIntent);
                break;
            case R.id.lin_shop://跳转商城

                break;
            case R.id.card://跳转个人中心
                startActivity(new Intent(getContext(), PersonalActivity.class));
                break;
            case R.id.rtv_home: //跳转家管理界面
                startActivity(new Intent(getContext(), HomeListActivity.class));
                break;
            case R.id.rtv_dev://跳转设备管理
                startActivity(new Intent(getContext(), DeviceManagerActivity.class));
                break;
            case R.id.rtv_msg_center://跳转消息中心
                startActivity(new Intent(getContext(), MineMsgCenterActivity.class));
                break;
            case R.id.rtv_setting://跳转通用设置
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.rtv_feedback://跳转反馈界面
                startActivity(new Intent(getContext(), FeedBackActivity.class));
                break;
        }
    }


}
