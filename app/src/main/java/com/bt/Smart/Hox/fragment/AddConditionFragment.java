package com.bt.Smart.Hox.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AutoOnlyCGQListInfo;
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
 * @创建时间 2018/11/18 21:23
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddConditionFragment extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private ImageView      img_back;
    private TextView       tv_title;
    private TextView       tv_save;
    private TextView       tv_name;
    private RelativeLayout rlt_sb;//选择设备
    private RelativeLayout rlt_wd;//温度
    private RelativeLayout rlt_sd;//湿度
    private RelativeLayout rlt_pm2d5;//pm2.5
    private RelativeLayout rlt_pm10;//pm10
    private RelativeLayout rlt_jq;//甲醛
    private RelativeLayout rlt_vocs;//VOCs
    private RelativeLayout rlt_co2;//CO2
    private RelativeLayout rlt_co;//CO
    private List           mQgqData;//存放搜索到的传感器

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_cond, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_save = mRootView.findViewById(R.id.tv_save);
        tv_name = mRootView.findViewById(R.id.tv_name);
        rlt_sb = mRootView.findViewById(R.id.rlt_sb);
        rlt_wd = mRootView.findViewById(R.id.rlt_wd);
        rlt_sd = mRootView.findViewById(R.id.rlt_sd);
        rlt_pm2d5 = mRootView.findViewById(R.id.rlt_pm2d5);
        rlt_pm10 = mRootView.findViewById(R.id.rlt_pm10);
        rlt_jq = mRootView.findViewById(R.id.rlt_jq);
        rlt_vocs = mRootView.findViewById(R.id.rlt_vocs);
        rlt_co2 = mRootView.findViewById(R.id.rlt_co2);
        rlt_co = mRootView.findViewById(R.id.rlt_co);
    }

    private void initData() {
        tv_title.setText("选择条件");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        rlt_sb.setOnClickListener(this);
        rlt_wd.setOnClickListener(this);
        rlt_sd.setOnClickListener(this);
        rlt_pm2d5.setOnClickListener(this);
        rlt_pm10.setOnClickListener(this);
        rlt_jq.setOnClickListener(this);
        rlt_vocs.setOnClickListener(this);
        rlt_co2.setOnClickListener(this);
        rlt_co.setOnClickListener(this);
        //获取传感器设备//默认选中第一个
        qgqItems = new ArrayList();
        mQgqData = new ArrayList();
        getQGQDevList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_save://确定

                break;
            case R.id.rlt_sb://选择设备
                selectQgQ();
                break;
            case R.id.rlt_wd://温度选择
                toSelectWD();
                break;
            case R.id.rlt_pm2d5://温度选择PM2.5选择
                toSelectPM2d5();
                break;
        }
    }

    private List<String> qgqItems;

    private void selectQgQ() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tv_name.setText(qgqItems.get(options1));
            }
        })
                .setTitleText("设备")
                .setBgColor(Color.WHITE)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(qgqItems);//一级选择器
        pvOptions.show();
    }

    private void getQGQDevList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", MyApplication.slecHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.QUERYHA3LIST, params, new HttpOkhUtils.HttpCallBack() {
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
                AutoOnlyCGQListInfo autoOnlyCGQListInfo = gson.fromJson(resbody, AutoOnlyCGQListInfo.class);
                ToastUtils.showToast(getContext(), autoOnlyCGQListInfo.getMessage());
                if (1 == autoOnlyCGQListInfo.getCode()) {
                    mQgqData.addAll(autoOnlyCGQListInfo.getHA3list());
                    if (null != autoOnlyCGQListInfo.getHA3list() && autoOnlyCGQListInfo.getHA3list().size() > 0) {
                        tv_name.setText(autoOnlyCGQListInfo.getHA3list().get(0).getDevice_name());
                    }
                    for (AutoOnlyCGQListInfo.HA3listBean bean : autoOnlyCGQListInfo.getHA3list()) {
                        qgqItems.add(bean.getDevice_name());
                    }
                }
            }
        });
    }

    private void toSelectPM2d5() {
        final List<String> pmItemList = new ArrayList();
        pmItemList.add("优");
        pmItemList.add("良");
        pmItemList.add("污染");
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ToastUtils.showToast(getContext(), pmItemList.get(options1));
            }
        })
                .setTitleText("PM2.5选择")
                .setDividerColor(Color.BLUE)
                .setTitleBgColor(Color.WHITE)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(16)
                .build();
        pvOptions.setPicker(pmItemList);//一级选择器
        //pvOptions.setPicker(eqItemList, wdItemsList);//二级选择器
        pvOptions.show();
    }

    private void toSelectWD() {
        final List<String> eqItemList = new ArrayList();
        eqItemList.add("小于");
        eqItemList.add("等于");
        eqItemList.add("大于");
        final List<List<String>> wdItemsList = new ArrayList();
        List wdNum = new ArrayList();
        for (int i = 0; i <= 80; i++) {
            wdNum.add((-40 + i) + "℃");
        }
        wdItemsList.add(wdNum);
        wdItemsList.add(wdNum);
        wdItemsList.add(wdNum);
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ToastUtils.showToast(getContext(), eqItemList.get(options1) + wdItemsList.get(options2));
            }
        })
                .setTitleText("温度选择")
                .setDividerColor(Color.BLUE)
                .setTitleBgColor(Color.WHITE)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(16)
                .build();
        //        pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(eqItemList, wdItemsList);//二级选择器
        pvOptions.show();
    }
}
