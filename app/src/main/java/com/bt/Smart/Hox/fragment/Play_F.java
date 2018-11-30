package com.bt.Smart.Hox.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.playActivity.PlayDetailWebActivity;
import com.bt.Smart.Hox.adapter.LvInformationAdapter;
import com.bt.Smart.Hox.messegeInfo.PlayListInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/8/18 9:58
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class Play_F extends Fragment {
    private View                            mRootView;
    private TextView                        tv_title;
    private LinearLayout                    lin_nomsg;
    private ListView                        lv_info;
    private List<PlayListInfo.PlayListBean> mData;
    private LvInformationAdapter            informationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_intelligence, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        tv_title = mRootView.findViewById(R.id.tv_title);
        lin_nomsg = mRootView.findViewById(R.id.lin_nomsg);
        lv_info = mRootView.findViewById(R.id.lv_info);
    }

    private void initData() {
        tv_title.setText("资讯");
        mData = new ArrayList();
        informationAdapter = new LvInformationAdapter(getContext(), mData);
        lv_info.setAdapter(informationAdapter);
        lv_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转详情
                Intent intent = new Intent(getActivity(), PlayDetailWebActivity.class);
                int id = mData.get(i).getId();
                intent.putExtra("playID", id);
                startActivity(intent);
            }
        });
        //获取适玩列表
        getPlayLisy();

        // 创建一个集合,装填Fragment
        //        fragmentsList = new ArrayList<>();
        //        for (int i = 0; i < contsList.length; i++) {
        //            //设备列表界面
        //            InformationFragment informationfrg = new InformationFragment();
        //            fragmentsList.add(informationfrg);
        //        }
        //        // 创建ViewPager适配器
        //        myPagerAdapter = new MyAddIntellPagerAdapter(getChildFragmentManager());
        //        myPagerAdapter.setFragments(fragmentsList);
        //        // 给ViewPager设置适配器
        //        mView_pager.setAdapter(myPagerAdapter);
        //        //设置viewpager不可滑动
        //        //        mView_pager.setCanScroll(false);
        //        //tablayout关联tablayout和viewpager实现联动
        //        mTablayout.setupWithViewPager(mView_pager);
        //        for (int i = 0; i < contsList.length; i++) {
        //            mTablayout.getTabAt(i).setText(contsList[i]);
        //        }
        //        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        //            @Override
        //            public void onTabSelected(TabLayout.Tab tab) {
        //
        //            }
        //
        //            @Override
        //            public void onTabUnselected(TabLayout.Tab tab) {
        //
        //            }
        //
        //            @Override
        //            public void onTabReselected(TabLayout.Tab tab) {
        //
        //            }
        //        });
    }

    private void getPlayLisy() {
        HttpOkhUtils.getInstance().doGet(NetConfig.PLAYLIST, new HttpOkhUtils.HttpCallBack() {
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
                PlayListInfo playListInfo = gson.fromJson(resbody, PlayListInfo.class);
                ToastUtils.showToast(getContext(), playListInfo.getMessage());
                if (1 == playListInfo.getCode()) {
                    if (playListInfo.getPlayList().size() > 0) {
                        lin_nomsg.setVisibility(View.GONE);
                        mData.addAll(playListInfo.getPlayList());
                        informationAdapter.notifyDataSetChanged();
                    } else {
                        lin_nomsg.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }
}
