package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvAutoSelcDevAdapter;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/14 15:29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoGroupFragment extends Fragment implements View.OnClickListener {
    private View      mRootView;
    private ImageView img_back;
    private TextView  tv_title;
    private TextView  tv_add;
    private TextView  tv_sure;
    private ListView  lv_dev;
    private List      mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_auto_gruop, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_add = mRootView.findViewById(R.id.tv_add);
        tv_sure = mRootView.findViewById(R.id.tv_sure);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("选定设备");
        tv_add.setOnClickListener(this);
        tv_sure.setOnClickListener(this);

        mData = new ArrayList();
        mData.add("");
        mData.add("");
        LvAutoSelcDevAdapter selcAutoAdapter = new LvAutoSelcDevAdapter(getContext(), mData);
        lv_dev.setAdapter(selcAutoAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_add://跳转添加自动化
                SceneChoiceAutoFragment sceneChoiceAutofrg = new SceneChoiceAutoFragment();
                FragmentTransaction ftt = getFragmentManager().beginTransaction();
                ftt.add(R.id.frame, sceneChoiceAutofrg, "sceneChoiceAutofrg");
                ftt.addToBackStack(null);
                ftt.commit();
                break;
            case R.id.tv_sure:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
        }
    }
}
