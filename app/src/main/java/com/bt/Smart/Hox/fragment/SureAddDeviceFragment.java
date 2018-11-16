package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/16 14:39
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SureAddDeviceFragment extends Fragment implements View.OnClickListener {
    private View      mRootView;
    private ImageView img_back;
    private TextView  tv_title;
    private TextView  tv_sure;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_sure_add, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_sure = mRootView.findViewById(R.id.tv_sure);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("设备状态确认");
        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_sure:
                //跳转至搜索界面
                FragmentTransaction ftt = getFragmentManager().beginTransaction();
                WiFiSearchFragment wifiSearchfrg = new WiFiSearchFragment();
                ftt.add(R.id.frame, wifiSearchfrg, "wifiSearchfrg");
                ftt.addToBackStack(null);
                ftt.commit();
                break;
        }
    }
}
