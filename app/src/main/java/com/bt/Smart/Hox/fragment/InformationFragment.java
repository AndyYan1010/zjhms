package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvInformationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 15:30
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class InformationFragment extends Fragment {
    private View     mRootView;
    private ListView lv_info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_information, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        lv_info = mRootView.findViewById(R.id.lv_info);

    }

    private void initData() {
        List mData =new ArrayList();
        mData.add("");
        mData.add("");
        mData.add("");
        LvInformationAdapter informationAdapter = new LvInformationAdapter(getContext(),mData);
        lv_info.setAdapter(informationAdapter);
    }

}
