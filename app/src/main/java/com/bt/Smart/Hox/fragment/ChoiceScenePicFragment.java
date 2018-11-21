package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.RecyChoicePicAdapter;
import com.bt.Smart.Hox.messegeInfo.ScenePicListInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/20 9:00
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ChoiceScenePicFragment extends Fragment implements View.OnClickListener {
    private View                                    mRootView;
    private ImageView                               img_back;
    private TextView                                tv_title;
    private RecyclerView                            recy_pic;
    private RecyChoicePicAdapter                    choicePicAdapter;
    private List<ScenePicListInfo.ScenePiclistBean> mData;
    private AddSceneFragment                        mAddSceneFragment;//上一个fragment

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_choice_scene_pic, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        recy_pic = mRootView.findViewById(R.id.recy_pic);

    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("选择封面");
        mData = new ArrayList();
        recy_pic.setLayoutManager(new GridLayoutManager(getContext(), 2));
        choicePicAdapter = new RecyChoicePicAdapter(getContext(), mData, this);
        recy_pic.setAdapter(choicePicAdapter);
        //获取场景默认所有图片
        getScenePicBg();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
        }
    }

    private void getScenePicBg() {
        HttpOkhUtils.getInstance().doGet(NetConfig.QUERYDEFAULTPIC, new HttpOkhUtils.HttpCallBack() {
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
                ScenePicListInfo scenePicListInfo = gson.fromJson(resbody, ScenePicListInfo.class);
                ToastUtils.showToast(getContext(), scenePicListInfo.getMessage());
                if (1 == scenePicListInfo.getCode()) {
                    mData.addAll(scenePicListInfo.getScenePiclist());
                    choicePicAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void selectItem(int item) {
        //选中的图片，关闭页面，把信息带给前一个fragment
        mAddSceneFragment.changeBgPic(NetConfig.IMG_FOR_SCENE + mData.get(item).getFpic(), mData.get(item).getId());
        MyFragmentManagerUtil.closeTopFragment(this);
    }

    public void setUpFragment(AddSceneFragment addSceneFragment) {
        mAddSceneFragment = addSceneFragment;
    }
}
