package com.bt.Smart.Hox.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.viewmodle.MyListView;

import java.util.ArrayList;
import java.util.Date;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 16:13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoShowFragment extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private ImageView      img_back;
    private TextView       tv_title;
    private TextView       et_name;//自动化名称
    private ImageView      img_add_term;//添加自动化条件
    private LinearLayout   lin_add_term;//添加自动化条件
    private RelativeLayout rlt_time;//自动化条件
    private TextView       tv_become;//变为、立即
    private LinearLayout   lin_time_slot;//时间段
    private TextView       tv_start;//起始时间
    private TextView       tv_end;//结束时间
    private ImageView      img_add_act;//添加自动化动作
    private LinearLayout   lin_add_action;//添加自动化动作
    private MyListView     lv_action;
    private TextView       tv_save;
    private TextView       tv_delete;
    private String mKind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_auto_show, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        et_name = mRootView.findViewById(R.id.et_name);
        img_add_term = mRootView.findViewById(R.id.img_add_term);
        lin_add_term = mRootView.findViewById(R.id.lin_add_term);
        rlt_time = mRootView.findViewById(R.id.rlt_time);
        tv_become = mRootView.findViewById(R.id.tv_become);
        lin_time_slot = mRootView.findViewById(R.id.lin_time_slot);
        tv_start = mRootView.findViewById(R.id.tv_start);
        tv_end = mRootView.findViewById(R.id.tv_end);
        img_add_act = mRootView.findViewById(R.id.img_add_act);
        lin_add_action = mRootView.findViewById(R.id.lin_add_action);
        lv_action = mRootView.findViewById(R.id.lv_action);
        tv_save = mRootView.findViewById(R.id.tv_save);
        tv_delete = mRootView.findViewById(R.id.tv_delete);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("自动化");

        if ("0".equals(mKind)) {
            tv_delete.setVisibility(View.GONE);
            img_add_term.setVisibility(View.GONE);
            img_add_act.setVisibility(View.GONE);
            rlt_time.setVisibility(View.GONE);
            lin_add_term.setOnClickListener(this);
            lin_add_action.setOnClickListener(this);
        } else {

        }
        options1Items = new ArrayList<>();
        options1Items.add("变为");
        options1Items.add("此时正好");
        tv_become.setOnClickListener(this);
        lin_time_slot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                //弹出回退栈最上面的fragment
                getFragmentManager().popBackStackImmediate(null, 0);
                break;
            case R.id.lin_add_term://跳转添加条件
                FragmentTransaction ftt = getFragmentManager().beginTransaction();
                ConditionFragment conditionFt = new ConditionFragment();
                conditionFt.setAutoFrg(this);
                ftt.add(R.id.frame, conditionFt, "conditionFt");
                ftt.addToBackStack(null);
                ftt.commit();
                break;
            case R.id.tv_become:
                //弹出条件状态选择框
                showTimeStatusView();
                break;
            case R.id.lin_time_slot://设置时间段
                isStartTime = !isStartTime;
                if (isStartTime) {
                    selectTimer("设置开始时间");
                } else {
                    selectTimer("设置结束时间");
                }
                break;
            case R.id.lin_add_action://跳转添加动作界面
                FragmentTransaction ftt2 = getFragmentManager().beginTransaction();
                ChoiceAotuActionFragment choiceAotuFt = new ChoiceAotuActionFragment();
                ftt2.add(R.id.frame, choiceAotuFt, "choiceAotuFt");
                ftt2.addToBackStack(null);
                ftt2.commit();
                break;
        }
    }

    private boolean isStartTime;

    private void selectTimer(String title) {
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (isStartTime) {
                    tv_start.setText(date.toString().substring(11, 16));
                } else {
                    tv_end.setText(date.toString().substring(11, 16));
                }
            }
        })
                .setType(new boolean[]{false, false, false, true, true, false})
                .setTitleText(title)
                .build();
        pvTime.show();
    }

    private ArrayList<String> options1Items;
    private int               slecteOption;

    private void showTimeStatusView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                slecteOption = options1;
                tv_become.setText(options1Items.get(options1));
            }
        })
                .setTitleText("条件选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.show();
    }

    public void showTimeView() {//显示时间条件view
        rlt_time.setVisibility(View.VISIBLE);
    }

    public void setKind(String kind) {
        mKind = kind;
    }
}
