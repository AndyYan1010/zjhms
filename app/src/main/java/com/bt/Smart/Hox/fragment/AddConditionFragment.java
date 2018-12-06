package com.bt.Smart.Hox.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvHa3ValueAdapter;
import com.bt.Smart.Hox.messegeInfo.AutoCondInfo;
import com.bt.Smart.Hox.messegeInfo.AutoOnlyCGQListInfo;
import com.bt.Smart.Hox.messegeInfo.Ha3ValueInfo;
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
    private View                                    mRootView;
    private ImageView                               img_back;
    private TextView                                tv_title;
    private TextView                                tv_save;
    private TextView                                tv_name;
    private RelativeLayout                          rlt_sb;//选择设备
    private List<Ha3ValueInfo.Ha3TypeVlaueListBean> mValueData;
    private LvHa3ValueAdapter                       ha3ValueAdapter;
    private ListView                                lv_h3value;
    private List<AutoOnlyCGQListInfo.HA3listBean>   mQgqData;//存放搜索到的传感器
    private String                                  devID;
    private String                                  devCode;
    private String                                  main_Code;

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
        lv_h3value = mRootView.findViewById(R.id.lv_h3value);
    }

    private void initData() {
        tv_title.setText("选择条件");
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        rlt_sb.setOnClickListener(this);
        //存储传感器数据
        qgqItems = new ArrayList();
        mQgqData = new ArrayList();
        //设置空气哨兵的value
        mValueData = new ArrayList();
        ha3ValueAdapter = new LvHa3ValueAdapter(getContext(), mValueData);
        lv_h3value.setAdapter(ha3ValueAdapter);
        lv_h3value.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = String.valueOf(tv_name.getText()).trim();
                if ("正在加载设备...".equals(name) || "未查找到设备".equals(name)) {
                    ToastUtils.showToast(getContext(), "未查找到设备...");
                    return;
                }
                //选择空气哨兵的值
                //关闭当前界面，给上级界面传入数据
                changeUpFragmentUI(i);
            }
        });
        //获取传感器设备//默认选中第一个
        getQGQDevList();
        //获取空气哨兵的值
        getKQSBValue();
    }

    @Override
    public void onClick(View view) {
        String name = String.valueOf(tv_name.getText()).trim();
        if ("正在加载设备...".equals(name)) {
            ToastUtils.showToast(getContext(), "正在加载设备...");
            return;
        }
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_save://确定
                break;
            case R.id.rlt_sb://选择设备
                selectQgQ();
                break;
        }
    }

    private void changeUpFragmentUI(int position) {
        AutoCondInfo condInfo = new AutoCondInfo();
        condInfo.setDevice_name(String.valueOf(tv_name.getText()).trim());
        condInfo.setSelect_type(mValueData.get(position).getFname());
        condInfo.setHa3_code(devCode);
        condInfo.setSelect_if("等于");
        condInfo.setValue("0");
        condInfo.setDevice_id(devID);
        condInfo.setMain_control_code(main_Code);
        mAddAutoFragment.changeCondUI(condInfo);
        MyFragmentManagerUtil.closeTopFragment(this);
    }

    private void getKQSBValue() {
        HttpOkhUtils.getInstance().doGet(NetConfig.QUERYHA3TYPEVLAUELIST, new HttpOkhUtils.HttpCallBack() {
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
                Ha3ValueInfo ha3ValueInfo = gson.fromJson(resbody, Ha3ValueInfo.class);
                ToastUtils.showToast(getContext(), ha3ValueInfo.getMessage());
                if (1 == ha3ValueInfo.getCode()) {
                    mValueData.addAll(ha3ValueInfo.getHa3TypeVlaueList());
                    ha3ValueAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private List<String> qgqItems;

    private void selectQgQ() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tv_name.setText(qgqItems.get(options1));
                devID = mQgqData.get(options1).getId();
                main_Code = mQgqData.get(options1).getMain_control_code();
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
                        devID = autoOnlyCGQListInfo.getHA3list().get(0).getId();
                        devCode = autoOnlyCGQListInfo.getHA3list().get(0).getDevice_code();
                        main_Code = autoOnlyCGQListInfo.getHA3list().get(0).getMain_control_code();
                    } else {
                        tv_name.setText("未查找到设备");
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

    private AddAutoFragment mAddAutoFragment;

    public void setUpFragment(AddAutoFragment addAutoFragment) {
        mAddAutoFragment = addAutoFragment;
    }
}
