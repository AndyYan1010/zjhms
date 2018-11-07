package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/7 9:34
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ConditionFragment extends Fragment implements View.OnClickListener {
    private View      mRootView;
    private ImageView img_back;
    private TextView  tv_title;
    private TextView  tv_sure;//确定
    private CheckBox  cb_choice;//选择
    private AutoShowFragment mAutoFrg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_condition, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_sure = mRootView.findViewById(R.id.tv_sure);
        cb_choice = mRootView.findViewById(R.id.cb_choice);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("条件");
        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_sure:
                if (cb_choice.isChecked()) {
                    MyFragmentManagerUtil.closeTopFragment(this);
                    mAutoFrg.showTimeView();
                } else {
                    MyFragmentManagerUtil.closeTopFragment(this);
                }
                break;
        }
    }
    public void setAutoFrg(AutoShowFragment autoFrg){
        mAutoFrg=autoFrg;
    }
}
