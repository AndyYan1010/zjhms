package com.bt.Smart.Hox.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvSceneSelcDevAdapter;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/14 9:36
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class DevGroupFragment extends Fragment implements View.OnClickListener {
    private View      mRootView;
    private ImageView img_back;
    private TextView  tv_title;
    private TextView  tv_add;
    private TextView  tv_sure;
    private ListView  lv_dev;
    private List      mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dev_gruop, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_add = mRootView.findViewById(R.id.tv_add);
        tv_sure = mRootView.findViewById(R.id.tv_sure);
        lv_dev = mRootView.findViewById(R.id.lv_dev);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("选定设备");
        tv_add.setOnClickListener(this);
        tv_sure.setOnClickListener(this);

        options1ItemsAct = new ArrayList<>();
        options1ItemsAct.add("打开");
        options1ItemsAct.add("关闭");
        options2ItemsAct = new ArrayList<>();
        List timeData = new ArrayList();
        timeData.add("立即");
        timeData.add("1分钟");
        timeData.add("2分钟");
        timeData.add("3分钟");
        timeData.add("4分钟");
        timeData.add("5分钟");
        for (int i = 0; i < options1ItemsAct.size(); i++) {
            options2ItemsAct.add(timeData);
        }

        mData = new ArrayList();
        mData.add("");
        mData.add("");
        LvSceneSelcDevAdapter selcDevAdapter = new LvSceneSelcDevAdapter(getContext(), mData);
        lv_dev.setAdapter(selcDevAdapter);
        lv_dev.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //弹出选择器，选择时间和开关
                selectTimeAndStatue(i);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_add://跳转添加设备
                SceneChoiceDevFragment sceneChoiceDevfrg = new SceneChoiceDevFragment();
                FragmentTransaction ftt = getFragmentManager().beginTransaction();
                ftt.add(R.id.frame, sceneChoiceDevfrg, "sceneChoiceDevfrg");
                ftt.addToBackStack(null);
                ftt.commit();
                break;
            case R.id.tv_sure:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
        }
    }

    private ArrayList<String> options1ItemsAct;
    private ArrayList<List>   options2ItemsAct;

    private void selectTimeAndStatue(int i) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ToastUtils.showToast(getContext(), options1ItemsAct.get(options1) + options2ItemsAct.get(options2));
            }
        })
                .setTitleText("选择")
                .setDividerColor(Color.BLUE)
                .setTitleBgColor(Color.WHITE)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(16)
                .build();
        pvOptions.setPicker(options1ItemsAct, options2ItemsAct);//二级选择器
        pvOptions.show();
    }
}
