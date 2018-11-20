package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvSelectDevAdapter;
import com.bt.Smart.Hox.messegeInfo.NotHA3ListInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/18 20:00
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddActFragment extends Fragment implements View.OnClickListener {
    private View                                mRootView;
    private ImageView                           img_back;
    private TextView                            tv_title;
    private RelativeLayout                      rlt_choice_auto;
    private ListView                            lv_dev;
    private List<NotHA3ListInfo.NotHA3listBean> mData;
    private LvSelectDevAdapter                  selectDevAdapter;
    //    private TabLayout                     mTablayout;//导航标签
    //    private MyFixedViewpager              mView_pager;//自我viewpager可实现禁止滑动
    //    private List<String>                  contsList;//tablayout的标题
    //    private ArrayList<DeviceListFragment> fragmentsList;//fragment集合
    //    private MyPagerAdapter                myPagerAdapter;//pager设配器

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_act, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        rlt_choice_auto = mRootView.findViewById(R.id.rlt_choice_auto);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
    }

    private void initData() {
        tv_title.setText("选择动作");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        rlt_choice_auto.setOnClickListener(this);
        mData = new ArrayList();
        selectDevAdapter = new LvSelectDevAdapter(getContext(), mData);
        lv_dev.setAdapter(selectDevAdapter);
        //获取家下设备
        getHomeDeviceList();
        //        contsList = new ArrayList<>();
        //        contsList.add("所有设备");
        //        contsList.add("主卧");
        //        contsList.add("客厅");
        //        contsList.add("餐厅");
        //        contsList.add("次卧");
        //        contsList.add("书房");
        //        // 创建一个集合,装填Fragment
        //        fragmentsList = new ArrayList<>();
        //        for (int i = 0; i < contsList.size(); i++) {
        //            //设备列表界面
        //            DeviceListFragment deviceFragment = new DeviceListFragment();
        //            fragmentsList.add(deviceFragment);
        //        }
        //        // 创建ViewPager适配器
        //        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        //        myPagerAdapter.setFragments(fragmentsList);
        //        // 给ViewPager设置适配器
        //        mView_pager.setAdapter(myPagerAdapter);
        //        //mView_pager.setOffscreenPageLimit(4);
        //        //设置viewpager不可滑动
        //        mView_pager.setCanScroll(false);
        //        //tablayout关联tablayout和viewpager实现联动
        //        mTablayout.setupWithViewPager(mView_pager);
        //        for (int i = 0; i < contsList.size(); i++) {
        //            mTablayout.getTabAt(i).setText(contsList.get(i));
        //        }
        //        showRoomsInfo(MyApplication.slecHomeID);//展示房间信息
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.rlt_choice_auto:

                break;
        }
    }

    private void getHomeDeviceList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.QUERYNOTHA3LIST, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(getContext(), "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(getContext(), "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                NotHA3ListInfo notHA3ListInfo = gson.fromJson(resbody, NotHA3ListInfo.class);
                ToastUtils.showToast(getContext(), notHA3ListInfo.getMessage());
                if (1 == notHA3ListInfo.getCode()) {
                    mData.addAll(notHA3ListInfo.getNotHA3list());
                    selectDevAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    //    private void showRoomsInfo(final String slecHomeID) {
    //        RequestParamsFM params = new RequestParamsFM();
    //        params.put("home_id", slecHomeID);
    //        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HOUSE, params, new HttpOkhUtils.HttpCallBack() {
    //            @Override
    //            public void onError(Request request, IOException e) {
    //                ProgressDialogUtil.hideDialog();
    //                ToastUtils.showToast(getContext(), "网络连接错误");
    //            }
    //
    //            @Override
    //            public void onSuccess(int code, String resbody) {
    //                ProgressDialogUtil.hideDialog();
    //                if (code != 200) {
    //                    ToastUtils.showToast(getContext(), "网络错误" + code);
    //                    return;
    //                }
    //                Gson gson = new Gson();
    //                HouseDetailInfo houseDetailInfo = gson.fromJson(resbody, HouseDetailInfo.class);
    //                if (1 == houseDetailInfo.getCode()) {
    //                    ToastUtils.showToast(getContext(), "房间数查询成功");
    //                    if (houseDetailInfo.getHouseList().size() > 0) {//房间数大于1才刷新界面
    //                        if (null == fragmentsList) {
    //                            fragmentsList = new ArrayList<>();
    //                        } else {
    //                            fragmentsList.clear();
    //                        }
    //                        if (null == contsList) {
    //                            contsList = new ArrayList<>();
    //                        } else {
    //                            contsList.clear();
    //                        }
    //                        //添加所有设备界面
    //                        DeviceListFragment deviceFragmentAll = new DeviceListFragment();
    //                        contsList.add("所有设备");
    //                        deviceFragmentAll.setRoomID(slecHomeID, "all");
    //                        fragmentsList.add(deviceFragmentAll);
    //                        for (int i = 0; i < houseDetailInfo.getHouseList().size(); i++) {
    //                            //创建设备列表界面
    //                            DeviceListFragment deviceFragment = new DeviceListFragment();
    //                            deviceFragment.setRoomID(slecHomeID, houseDetailInfo.getHouseList().get(i).getId());
    //                            contsList.add(houseDetailInfo.getHouseList().get(i).getHouse_name());
    //                            fragmentsList.add(deviceFragment);
    //                        }
    //                        //刷新界面
    //                        // 创建ViewPager适配器
    //                        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
    //                        myPagerAdapter.setFragments(fragmentsList);
    //                        // 给ViewPager设置适配器
    //                        mView_pager.setAdapter(myPagerAdapter);
    //                        mTablayout.setupWithViewPager(mView_pager);
    //                        for (int i = 0; i < contsList.size(); i++) {
    //                            mTablayout.getTabAt(i).setText(contsList.get(i));
    //                        }
    //                        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    //                            @Override
    //                            public void onTabSelected(TabLayout.Tab tab) {
    //                                fragmentsList.get(tab.getPosition()).refreshInfo();
    //                                MyApplication.slecRoomID = fragmentsList.get(tab.getPosition()).getRoomID();
    //                            }
    //
    //                            @Override
    //                            public void onTabUnselected(TabLayout.Tab tab) {
    //
    //                            }
    //
    //                            @Override
    //                            public void onTabReselected(TabLayout.Tab tab) {
    //                                fragmentsList.get(tab.getPosition()).refreshInfo();
    //                                MyApplication.slecRoomID = fragmentsList.get(tab.getPosition()).getRoomID();
    //                            }
    //                        });
    //                        mTablayout.getTabAt(0).select();
    //                        mView_pager.setCurrentItem(0);
    //                    }
    //                } else {
    //                    ToastUtils.showToast(getContext(), "房间查询失败");
    //                }
    //            }
    //        });
    //    }
}
