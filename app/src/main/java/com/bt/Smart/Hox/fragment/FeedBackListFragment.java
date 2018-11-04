package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvFeedBackAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/3 18:46
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class FeedBackListFragment extends Fragment implements View.OnClickListener {
    private View      mRootView;
    private ImageView img_back;
    private TextView  tv_title;
    private ListView  lv_fb;
    private TextView  tv_write;//填写反馈信息

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.frg_feedback_list, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = (ImageView) mRootView.findViewById(R.id.img_back);
        tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
        lv_fb = (ListView) mRootView.findViewById(R.id.lv_fb);
        tv_write = (TextView) mRootView.findViewById(R.id.tv_write);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("反馈列表");
        List mData = new ArrayList();
        mData.add("");
        mData.add("");
        mData.add("");
        LvFeedBackAdapter feedBackAdapter =new LvFeedBackAdapter(getContext(),mData);
        lv_fb.setAdapter(feedBackAdapter);
        tv_write.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.tv_write://跳转填写反馈信息frg
                FragmentTransaction ftt = getFragmentManager().beginTransaction();
                WriteFeedBackFragment writeFBfrg = new WriteFeedBackFragment();
                ftt.add(R.id.frame, writeFBfrg, "writeFBfrg");
                ftt.addToBackStack(null);
                ftt.commit();
                break;
        }
    }
}
