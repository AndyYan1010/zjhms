package com.bt.Smart.Hox.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bt.Smart.Hox.fragment.InformationFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/5/26 21:31
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class MyAddIntellPagerAdapter extends FragmentPagerAdapter {
    private List<InformationFragment> mFragmentList;

    public MyAddIntellPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(ArrayList<InformationFragment> fragments) {
        mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
