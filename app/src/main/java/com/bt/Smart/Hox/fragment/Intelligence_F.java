package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvSceneAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/8/18 9:58
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class Intelligence_F extends Fragment implements View.OnClickListener {
    private View               mRootView;
    private SmartRefreshLayout smt_refresh;
    private List               mData;
    private ListView           lv_search;
    private LvSceneAdapter     orderAdapter;
    private ImageView          img_no_msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_intelligence, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        smt_refresh = mRootView.findViewById(R.id.smt_refresh);
        lv_search = mRootView.findViewById(R.id.lv_search);
        img_no_msg = mRootView.findViewById(R.id.img_no_msg);
    }

    private void initData() {
        mData = new ArrayList();
        orderAdapter = new LvSceneAdapter(getContext(), mData);
        lv_search.setAdapter(orderAdapter);
        smt_refresh.setEnableLoadMore(false);
        smt_refresh.setEnableRefresh(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
