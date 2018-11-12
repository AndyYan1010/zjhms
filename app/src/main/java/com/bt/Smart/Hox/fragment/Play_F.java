package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.MyAddIntellPagerAdapter;
import com.bt.Smart.Hox.viewmodle.MyFixedViewpager;

import java.util.ArrayList;

/**
 * @创建者 AndyYan
 * @创建时间 2018/8/18 9:58
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class Play_F extends Fragment {
    private View                           mRootView;
    private TabLayout                      mTablayout;//导航标签
    private MyFixedViewpager               mView_pager;//自我viewpager可实现禁止滑动
    private MyAddIntellPagerAdapter        myPagerAdapter;//pager设配器
    private ArrayList<InformationFragment> fragmentsList;//fragment集合
    private String[] contsList = {"资讯"};//tablayout的标题

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_intelligence, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        mTablayout = mRootView.findViewById(R.id.tablayout);
        mView_pager = mRootView.findViewById(R.id.view_pager);
    }

    private void initData() {
        // 创建一个集合,装填Fragment
        fragmentsList = new ArrayList<>();
        for (int i = 0; i < contsList.length; i++) {
            //设备列表界面
            InformationFragment informationfrg = new InformationFragment();
            fragmentsList.add(informationfrg);
        }
        // 创建ViewPager适配器
        myPagerAdapter = new MyAddIntellPagerAdapter(getChildFragmentManager());
        myPagerAdapter.setFragments(fragmentsList);
        // 给ViewPager设置适配器
        mView_pager.setAdapter(myPagerAdapter);
        //设置viewpager不可滑动
        //        mView_pager.setCanScroll(false);
        //tablayout关联tablayout和viewpager实现联动
        mTablayout.setupWithViewPager(mView_pager);
        for (int i = 0; i < contsList.length; i++) {
            mTablayout.getTabAt(i).setText(contsList[i]);
        }
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
