package com.bt.Smart.Hox.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/18 21:23
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddConditionFragment extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private ImageView      img_back;
    private TextView       tv_title;
    private TextView       tv_save;
    private RelativeLayout rlt_wd;
    private RelativeLayout rlt_pm25;
    private RelativeLayout rlt_ds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_cond, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_save = mRootView.findViewById(R.id.tv_save);
        rlt_wd = mRootView.findViewById(R.id.rlt_wd);
        rlt_pm25 = mRootView.findViewById(R.id.rlt_pm25);
        rlt_ds = mRootView.findViewById(R.id.rlt_ds);

    }

    private void initData() {
        tv_title.setText("选择条件");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        rlt_wd.setOnClickListener(this);
        rlt_pm25.setOnClickListener(this);
        rlt_ds.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.rlt_wd://温度选择
                toSelectWD();
                break;
            case R.id.rlt_pm25://温度选择PM2.5选择
                toSelectPM2d5();
                break;
            case R.id.rlt_ds://跳转选择时间界面
                totimerFragment();
                break;
        }
    }

    private void totimerFragment() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        SceneTimingFragment sceneTimingFt = new SceneTimingFragment();
        sceneTimingFt.setUpFragment((AddAutoFragment)getFragmentManager().findFragmentByTag("addAutoFt"));
        ftt.add(R.id.frame, sceneTimingFt, "sceneTimingFt");
        ftt.addToBackStack(null);
        ftt.commit();
    }

    private void toSelectPM2d5() {
        final List<String> pmItemList = new ArrayList();
        pmItemList.add("优");
        pmItemList.add("良");
        pmItemList.add("污染");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ToastUtils.showToast(getContext(), pmItemList.get(options1));
            }
        })
                .setTitleText("PM2.5选择")
                .setDividerColor(Color.BLUE)
                .setTitleBgColor(Color.WHITE)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(16)
                .build();
        pvOptions.setPicker(pmItemList);//一级选择器
        //pvOptions.setPicker(eqItemList, wdItemsList);//二级选择器
        pvOptions.show();
    }

    private void toSelectWD() {
        final List<String> eqItemList = new ArrayList();
        eqItemList.add("小于");
        eqItemList.add("等于");
        eqItemList.add("大于");
        final List<List<String>> wdItemsList = new ArrayList();
        List wdNum = new ArrayList();
        for (int i = 0; i <= 80; i++) {
            wdNum.add((-40 + i) + "℃");
        }
        wdItemsList.add(wdNum);
        wdItemsList.add(wdNum);
        wdItemsList.add(wdNum);
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ToastUtils.showToast(getContext(), eqItemList.get(options1) + wdItemsList.get(options2));
            }
        })
                .setTitleText("温度选择")
                .setDividerColor(Color.BLUE)
                .setTitleBgColor(Color.WHITE)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(16)
                .build();
        //        pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(eqItemList, wdItemsList);//二级选择器
        pvOptions.show();
    }
}
