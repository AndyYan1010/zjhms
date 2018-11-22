package com.bt.Smart.Hox;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bt.Smart.Hox.utils.MyCloseKeyBoardUtil;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/22 16:29
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        MyCloseKeyBoardUtil.hintKeyBoard((Activity) getContext());
    }
}
