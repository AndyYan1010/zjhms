package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.viewmodle.MyListView;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/7 16:59
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ChoiceAotuActionFragment extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private ImageView      img_back;
    private TextView       tv_title;
    private RelativeLayout rlt_allSce;
    private ImageView      img_more_sce;
    private MyListView     lv_scene;
    private RelativeLayout rlt_allDev;
    private ImageView      img_more_dev;
    private MyListView     lv_dev;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_choice_auto_act, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        rlt_allSce = mRootView.findViewById(R.id.rlt_allSce);
        img_more_sce = mRootView.findViewById(R.id.img_more_sce);
        lv_scene = mRootView.findViewById(R.id.lv_scene);
        rlt_allDev = mRootView.findViewById(R.id.rlt_allDev);
        img_more_dev = mRootView.findViewById(R.id.img_more_dev);
        lv_dev = mRootView.findViewById(R.id.lv_dev);

    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("自动化动作");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
        }
    }
}
