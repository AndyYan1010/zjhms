package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.RecyDateAdapter;
import com.bt.Smart.Hox.messegeInfo.DateStatueInfo;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 14:04
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneTimingFragment extends Fragment implements View.OnClickListener {
    private View                 mRootView;
    private ImageView            img_back;
    private TextView             tv_title;
    private TextView             tv_start;
    private TextView             tv_end;
    private RecyclerView         recy_date;
    private List<DateStatueInfo> mData;
    private TextView             tv_sure;
    private AddAutoFragment      mSceneShowFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_scene_timing, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_start = mRootView.findViewById(R.id.tv_start);
        tv_end = mRootView.findViewById(R.id.tv_end);
        recy_date = mRootView.findViewById(R.id.recy_date);
        tv_sure = mRootView.findViewById(R.id.tv_sure);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("定时");
        tv_start.setOnClickListener(this);
        tv_end.setOnClickListener(this);
        mData = new ArrayList();
        //填充日期数据
        writeDateData();
        recy_date.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        RecyDateAdapter recyDateAdapter = new RecyDateAdapter(getContext(), mData);
        recy_date.setAdapter(recyDateAdapter);
        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                //弹出回退栈最上面的fragment
                getFragmentManager().popBackStackImmediate(null, 0);
                break;
            case R.id.tv_start:
                selectTimer("设置开始时间", tv_start);
                break;
            case R.id.tv_end:
                selectTimer("设置结束时间", tv_end);
                break;
            case R.id.tv_sure:
                String start = String.valueOf(tv_start.getText());
                String end = String.valueOf(tv_end.getText());
                String date = "";
                for (DateStatueInfo info : mData) {
                    if (info.isIsSlec()) {
                        if ("".equals(date)) {
                            date = date + info.getTitle();
                        } else {
                            date = date + "、" + info.getTitle();
                        }
                    }
                }
                //传递数据，关闭页面
                completeAndClose(date + "  " + start + "—" + end);
                break;
        }
    }

    private void writeDateData() {
        DateStatueInfo info = new DateStatueInfo();
        info.setTitle("单");
        info.setIsSlec(false);
        mData.add(info);
        DateStatueInfo info1 = new DateStatueInfo();
        info1.setTitle("一");
        info1.setIsSlec(false);
        mData.add(info1);
        DateStatueInfo info2 = new DateStatueInfo();
        info2.setTitle("二");
        info2.setIsSlec(false);
        mData.add(info2);
        DateStatueInfo info3 = new DateStatueInfo();
        info3.setTitle("三");
        info3.setIsSlec(false);
        mData.add(info3);
        DateStatueInfo info4 = new DateStatueInfo();
        info4.setTitle("四");
        info4.setIsSlec(false);
        mData.add(info4);
        DateStatueInfo info5 = new DateStatueInfo();
        info5.setTitle("五");
        info5.setIsSlec(false);
        mData.add(info5);
        DateStatueInfo info6 = new DateStatueInfo();
        info6.setTitle("六");
        info6.setIsSlec(false);
        mData.add(info6);
        DateStatueInfo info7 = new DateStatueInfo();
        info7.setTitle("七");
        info7.setIsSlec(false);
        mData.add(info7);
    }

    private void completeAndClose(String cont) {
        //修改上级界面时间选择
        mSceneShowFragment.changeTimingUI(cont);
        MyFragmentManagerUtil.closeTopFragment(this);
    }

    private void selectTimer(String title, final TextView tv) {
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv.setText(date.toString().substring(11, 16));
            }
        })
                .setType(new boolean[]{false, false, false, true, true, false})
                .setTitleText(title)
                .build();
        pvTime.show();
    }

    public void setUpFragment(AddAutoFragment sceneShowFragment) {
        mSceneShowFragment = sceneShowFragment;
    }
}
