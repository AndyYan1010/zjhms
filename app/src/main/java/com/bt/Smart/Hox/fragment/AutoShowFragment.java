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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvAddActionAdapter;
import com.bt.Smart.Hox.adapter.LvAddTimeAdapter;
import com.bt.Smart.Hox.messegeInfo.AutoDetailInfo;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.bt.Smart.Hox.viewmodle.MyListView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 16:13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AutoShowFragment extends Fragment implements View.OnClickListener {
    private View                                                                  mRootView;
    private ImageView                                                             img_back;
    private TextView                                                              tv_title;
    private TextView                                                              et_name;//自动化名称
    private TextView                                                              tv_warn;//提示
    private ImageView                                                             img_add_term;//添加自动化条件
    private LinearLayout                                                          lin_add_term;//添加自动化条件
    private ImageView                                                             img_add_act;//添加自动化动作
    private LinearLayout                                                          lin_add_action;//添加自动化动作
    private MyListView                                                            lv_time;//时间条件
    private MyListView                                                            lv_action;//动作列表
    private TextView                                                              tv_save;
    private TextView                                                              tv_delete;
    private String                                                                mKind;
    private String                                                                mAutoID;//自动化id
    private List<AutoDetailInfo.AutomationBean.AutomationsBean.ConditionListBean> timeList;//时间条件数据
    private List<AutoDetailInfo.AutomationBean.AutomationsBean.ActionListBean>    actList;//选择的动作数据
    private LvAddTimeAdapter                                                      timeAdapter;
    private LvAddActionAdapter                                                    actionAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_auto_show, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        et_name = mRootView.findViewById(R.id.et_name);
        img_add_term = mRootView.findViewById(R.id.img_add_term);
        lin_add_term = mRootView.findViewById(R.id.lin_add_term);
        tv_warn = mRootView.findViewById(R.id.tv_warn);
        lv_time = mRootView.findViewById(R.id.lv_time);
        img_add_act = mRootView.findViewById(R.id.img_add_act);
        lin_add_action = mRootView.findViewById(R.id.lin_add_action);
        lv_action = mRootView.findViewById(R.id.lv_action);
        tv_save = mRootView.findViewById(R.id.tv_save);
        tv_delete = mRootView.findViewById(R.id.tv_delete);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("自动化");
        img_add_term.setOnClickListener(this);
        img_add_act.setOnClickListener(this);
        if ("1".equals(mKind)) {
            //获取某个自动化详情
            getAutoDetail();
            tv_save.setText("确定");
            tv_warn.setVisibility(View.GONE);
            lin_add_term.setVisibility(View.GONE);
            lin_add_action.setVisibility(View.GONE);
            img_add_term.setOnClickListener(this);
            img_add_act.setOnClickListener(this);
        } else {
            tv_delete.setVisibility(View.GONE);
            img_add_term.setVisibility(View.GONE);
            img_add_act.setVisibility(View.GONE);
            lin_add_term.setOnClickListener(this);
            lin_add_action.setOnClickListener(this);
        }
        //时间选择数据
        options1Items = new ArrayList<>();
        options1Items.add("变为");
        options1Items.add("此时正好");
        //动作选择数据
        options1ItemsAct = new ArrayList<>();
        options1ItemsAct.add("立即");
        options1ItemsAct.add("1分钟");
        options1ItemsAct.add("2分钟");
        options1ItemsAct.add("3分钟");
        options1ItemsAct.add("4分钟");
        options1ItemsAct.add("5分钟");
        options2ItemsAct = new ArrayList<>();
        List actData = new ArrayList();
        actData.add("执行");
        for (int i = 0; i < options1ItemsAct.size(); i++) {
            options2ItemsAct.add(actData);
        }

        timeList = new ArrayList();
        timeAdapter = new LvAddTimeAdapter(getContext(), timeList, options1Items);
        lv_time.setAdapter(timeAdapter);

        actList = new ArrayList();
        actionAdapter = new LvAddActionAdapter(getContext(), actList);
        lv_action.setAdapter(actionAdapter);
        lv_action.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //弹出选择器，选择时间和开关
                selectTimeAndStatue(i);
            }
        });
        tv_save.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                //弹出回退栈最上面的fragment
                getFragmentManager().popBackStackImmediate(null, 0);
                break;
            case R.id.img_add_term://跳转添加条件界面
                toAddTermFragment();
                break;
            case R.id.lin_add_term://跳转添加条件界面
                toAddTermFragment();
                break;
            case R.id.img_add_act://跳转添加动作界面
                toAddActFragment();
                break;
            case R.id.lin_add_action://跳转添加动作界面
                toAddActFragment();
                break;
            case R.id.tv_save://保存提交
                if ("1".equals(mKind)) {//修改
                    changeAuto();
                } else {//新增
                    createAuto();
                }
                break;
            case R.id.tv_delete://删除某个自动化
                deleteAuto();
                break;
        }
    }

    private void createAuto() {
        
    }

    private void changeAuto() {

    }

    private void deleteAuto() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mAutoID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.DELETEAUTOMATION, params, new HttpOkhUtils.HttpCallBack() {
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
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(getContext(), commonInfo.getMessage());
                if (1 == commonInfo.getResult()) {
                    MyFragmentManagerUtil.closeTopFragment(AutoShowFragment.this);
                }
            }
        });
    }

    private void getAutoDetail() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mAutoID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.SELECTNOAUTOMATION, params, new HttpOkhUtils.HttpCallBack() {
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
                AutoDetailInfo autoDetailInfo = gson.fromJson(resbody, AutoDetailInfo.class);
                ToastUtils.showToast(getContext(), autoDetailInfo.getMessage());
                if (1 == autoDetailInfo.getResult()) {
                    et_name.setText(autoDetailInfo.getAutomation().getAutomations().getAutomaticName());
                    timeList.addAll(autoDetailInfo.getAutomation().getAutomations().getConditionList());
                    timeAdapter.notifyDataSetChanged();
                    actList.addAll(autoDetailInfo.getAutomation().getAutomations().getActionList());
                    actionAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void toAddActFragment() {
        FragmentTransaction ftt2 = getFragmentManager().beginTransaction();
        ChoiceAotuActionFragment choiceAotuFt = new ChoiceAotuActionFragment();
        choiceAotuFt.setAutoShowFragment(this);
        ftt2.add(R.id.frame, choiceAotuFt, "choiceAotuFt");
        ftt2.addToBackStack(null);
        ftt2.commit();
    }

    private void selectTimeAndStatue(int item) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                ToastUtils.showToast(getContext(), options1ItemsAct.get(options1) + options2ItemsAct.get(options2));
            }
        })
                .setTitleText("动作选择")
                .setDividerColor(Color.BLUE)
                .setTitleBgColor(Color.WHITE)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(16)
                .build();
        //        pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1ItemsAct, options2ItemsAct);//二级选择器
        pvOptions.show();
    }

    private void toAddTermFragment() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        ConditionFragment conditionFt = new ConditionFragment();
        conditionFt.setAutoFrg(this);
        ftt.add(R.id.frame, conditionFt, "conditionFt");
        ftt.addToBackStack(null);
        ftt.commit();
    }

    private ArrayList<String> options1Items;
    private ArrayList<String> options1ItemsAct;
    private ArrayList<List>   options2ItemsAct;

    public void showTimeView() {//显示时间条件view
        //隐藏提示
        img_add_term.setVisibility(View.VISIBLE);
        lin_add_term.setVisibility(View.GONE);
        tv_warn.setVisibility(View.GONE);
        timeList.clear();
        AutoDetailInfo.AutomationBean.AutomationsBean.ConditionListBean bean = new AutoDetailInfo.AutomationBean.AutomationsBean.ConditionListBean();
        bean.setKind_name("时间");
        bean.setKind_code("time");
        bean.setWhen(0);
        bean.setTime_start("00:00");
        bean.setTime_end("23:59");
        timeList.add(bean);
        timeAdapter.notifyDataSetChanged();
    }

    public void setKind(String kind, String autoID) {
        mKind = kind;
        mAutoID = autoID;
    }

    public void showActionView() {
        lin_add_action.setVisibility(View.GONE);
        actList.clear();
        AutoDetailInfo.AutomationBean.AutomationsBean.ActionListBean bean = new AutoDetailInfo.AutomationBean.AutomationsBean.ActionListBean();
        bean.setDevice_img("");
        bean.setFname("测试");
        bean.setWhen(0);
        bean.setStatus(0);
        actList.add(bean);
        actionAdapter.notifyDataSetChanged();
        img_add_act.setVisibility(View.VISIBLE);
    }
}
