package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bt.Smart.Hox.R;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 8:32
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class Shopp_F extends Fragment {
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_intelligence, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {

    }

    private void initData() {

    }
}
