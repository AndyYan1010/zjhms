package com.bt.Smart.Hox.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.RecyAddAutoActAdapter;
import com.bt.Smart.Hox.adapter.RecyAddCondAdapter;
import com.bt.Smart.Hox.adapter.RecyItemDragAdapter;
import com.bt.Smart.Hox.messegeInfo.AutoCondInfo;
import com.bt.Smart.Hox.messegeInfo.AutoDetailInfoNew;
import com.bt.Smart.Hox.messegeInfo.AutoDevListInfo;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.DateStatueInfo;
import com.bt.Smart.Hox.messegeInfo.Ha3ValueInfo;
import com.bt.Smart.Hox.messegeInfo.InsertAutoInfo;
import com.bt.Smart.Hox.messegeInfo.NotHA3ListInfo;
import com.bt.Smart.Hox.messegeInfo.UpAutoInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

import static com.chad.library.adapter.base.BaseQuickAdapter.SLIDEIN_RIGHT;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/18 20:49
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddAutoFragment extends Fragment implements View.OnClickListener {
    private View                  mRootView;
    private ImageView             img_back;
    private TextView              tv_title;
    private TextView              tv_save;
    private TextView              tv_name;
    private TextView              tv_cont;//选择的星期起止时间
    private LinearLayout          lin_term;//选择更多条件
    private TextView              tv_term;//条件类型
    private ImageView             img_edit;
    private ImageView             img_add_time;
    private ImageView             img_add_con;
    private ImageView             img_add_act;
    private List<AutoCondInfo>    mConData;//条件数据
    private RecyAddCondAdapter    addConAdapter;
    private RecyclerView          recy_con;
    private List<AutoDevListInfo> mActData;//动作数据
    private RecyAddAutoActAdapter addActAdapter;
    private RecyclerView          recy_act;
    private CardView              cv_delt;//删除
    private String mAutoType = "0";//选择的自动化满足条件类别

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_auto, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_save = mRootView.findViewById(R.id.tv_save);
        tv_name = mRootView.findViewById(R.id.tv_name);
        tv_cont = mRootView.findViewById(R.id.tv_cont);
        lin_term = mRootView.findViewById(R.id.lin_term);
        tv_term = mRootView.findViewById(R.id.tv_term);
        img_edit = mRootView.findViewById(R.id.img_edit);
        img_add_time = mRootView.findViewById(R.id.img_add_time);
        img_add_con = mRootView.findViewById(R.id.img_add_con);
        img_add_act = mRootView.findViewById(R.id.img_add_act);
        recy_con = mRootView.findViewById(R.id.recy_con);
        recy_act = mRootView.findViewById(R.id.recy_act);
        cv_delt = mRootView.findViewById(R.id.cv_delt);
    }

    private void initData() {
        tv_title.setText("智能设置");
        img_back.setVisibility(View.VISIBLE);
        tv_save.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        img_add_time.setOnClickListener(this);
        lin_term.setOnClickListener(this);
        img_add_con.setOnClickListener(this);
        img_add_act.setOnClickListener(this);
        cv_delt.setOnClickListener(this);
        //设置recyclerview
        initRecyView();
        //星期信息初始化
        initXingQiData();
        //设置选择器数据
        setSelectItemInfo();
        if ("1".equals(mKind)) {
            cv_delt.setVisibility(View.VISIBLE);
            //获取自动化详情
            getAutoDetailInfo();
        }
        //获取空气哨兵的值
        mValueData = new ArrayList<>();
        getKQSBValue();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_save://保存
                String name = String.valueOf(tv_name.getText()).trim();
                if ("".equals(name) || "编辑名称".equals(name)) {
                    ToastUtils.showToast(getContext(), "请输入名称");
                    return;
                }
                if ("1".equals(mKind)) {
                    //更新自动化
                    upDateAuto(name);
                } else {
                    //添加自动化
                    addNewAuto(name);
                }
                break;
            case R.id.img_add_time:
                totimerFragment();
                break;
            case R.id.lin_term://选择满足条件
                selectIfType();
                break;
            case R.id.img_add_con://添加条件
                toAddConFragment();
                break;
            case R.id.img_edit:
                //编辑场景名字
                showEditName();
                break;
            case R.id.img_add_act://跳转添加动作界面
                toAddActFragment();
                break;
            case R.id.cv_delt://删除自动化
                deleteAuto();
                break;
        }
    }

    private void deleteAuto() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", thisAutoID);
        HttpOkhUtils.getInstance().doDelete(NetConfig.DELETEAUTO, params, new HttpOkhUtils.HttpCallBack() {
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
                MyFragmentManagerUtil.closeTopFragment(AddAutoFragment.this);
                if (1 == commonInfo.getResult()) {
                }
            }
        });
    }

    private void upDateAuto(String name) {
        UpAutoInfo upAutoInfo = new UpAutoInfo();
        //执行设备
        List<InsertAutoInfo.AutoExecuteDeviceBean> auto_execute_device = new ArrayList<>();
        for (AutoDevListInfo devListInfo : mActData) {
            if (!"sce".equals(devListInfo.getDeviceType())) {
                InsertAutoInfo.AutoExecuteDeviceBean atExtDevBean = new InsertAutoInfo.AutoExecuteDeviceBean();
                atExtDevBean.setDevice_id(devListInfo.getDevice_id());
                atExtDevBean.setDevice_status(devListInfo.getDevice_status());
                atExtDevBean.setDevice_value(devListInfo.getDevice_value());
                atExtDevBean.setDeviceType(devListInfo.getDeviceType());
                auto_execute_device.add(atExtDevBean);
            }
        }
        upAutoInfo.setAuto_execute_device(auto_execute_device);
        //执行场景
        List<InsertAutoInfo.AutoExecuteSceneBean> auto_execute_scene = new ArrayList<>();
        for (AutoDevListInfo devListInfo : mActData) {
            if ("sce".equals(devListInfo.getDeviceType())) {
                InsertAutoInfo.AutoExecuteSceneBean sceneBean = new InsertAutoInfo.AutoExecuteSceneBean();
                sceneBean.setScene_id(devListInfo.getDevice_id());
                sceneBean.setScene_status(devListInfo.getDevice_status());
                auto_execute_scene.add(sceneBean);
            }
        }
        upAutoInfo.setAuto_execute_scene(auto_execute_scene);
        //设备条件
        List<InsertAutoInfo.AutoIfha3ListBean> auto_ifha3_list = new ArrayList<>();
        for (AutoCondInfo autoCondInfo : mConData) {
            InsertAutoInfo.AutoIfha3ListBean ifha3ListBean = new InsertAutoInfo.AutoIfha3ListBean();
            ifha3ListBean.setIf_ha3_code(autoCondInfo.getHa3_code());
            ifha3ListBean.setIf_ha3_id(autoCondInfo.getDevice_id());
            ifha3ListBean.setIf_select(autoCondInfo.getSelect_if());
            ifha3ListBean.setIf_select_type(autoCondInfo.getSelect_type());
            ifha3ListBean.setIf_type("1");
            ifha3ListBean.setIf_value(autoCondInfo.getValue());
            auto_ifha3_list.add(ifha3ListBean);
        }
        upAutoInfo.setAuto_ifha3_list(auto_ifha3_list);
        //时间段条件
        List<InsertAutoInfo.AutoIftimeListBean> auto_iftime_list = new ArrayList<>();
        InsertAutoInfo.AutoIftimeListBean iftimeListBean = new InsertAutoInfo.AutoIftimeListBean();
        iftimeListBean.setIf_begin_time(mStartTime);
        iftimeListBean.setIf_end_time(mEndTime);
        iftimeListBean.setIf_type("0");
        auto_iftime_list.add(iftimeListBean);
        upAutoInfo.setAuto_iftime_list(auto_iftime_list);
        List<InsertAutoInfo.AutoTimeListBean> auto_time_list = new ArrayList<>();
        for (int i = 0; i < mDateData.size(); i++) {
            if (mDateData.get(i).isIsSlec()) {
                InsertAutoInfo.AutoTimeListBean timeListBean = new InsertAutoInfo.AutoTimeListBean();
                timeListBean.setFtime("" + i);
                auto_time_list.add(timeListBean);
            }
        }
        //星期条件
        upAutoInfo.setAuto_time_list(auto_time_list);
        upAutoInfo.setHome_id(mThisSelectHomeID);
        upAutoInfo.setAuto_name(name);
        upAutoInfo.setAuto_type(mAutoType);
        upAutoInfo.setId(mAutoID);
        upAutoInfo.setAuto_status(thisAutoStatue);
        String auto = JSON.toJSONString(upAutoInfo);
        RequestParamsFM params = new RequestParamsFM();
        params.put("auto", auto);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.UPDATEAUTO, params, new HttpOkhUtils.HttpCallBack() {
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
                    mAddIntelligentFragment.refreshDataList();
                    MyFragmentManagerUtil.closeTopFragment(AddAutoFragment.this);
                }
            }
        });
    }

    private void addNewAuto(String name) {
        InsertAutoInfo insertAutoInfo = new InsertAutoInfo();
        //执行设备
        List<InsertAutoInfo.AutoExecuteDeviceBean> auto_execute_device = new ArrayList<>();
        for (AutoDevListInfo devListInfo : mActData) {
            if (!"sce".equals(devListInfo.getDeviceType())) {
                InsertAutoInfo.AutoExecuteDeviceBean atExtDevBean = new InsertAutoInfo.AutoExecuteDeviceBean();
                atExtDevBean.setDevice_id(devListInfo.getDevice_id());
                atExtDevBean.setDevice_status(devListInfo.getDevice_status());
                atExtDevBean.setDevice_value(devListInfo.getDevice_value());
                atExtDevBean.setDeviceType(devListInfo.getDeviceType());
                auto_execute_device.add(atExtDevBean);
            }
        }
        insertAutoInfo.setAuto_execute_device(auto_execute_device);
        //执行场景
        List<InsertAutoInfo.AutoExecuteSceneBean> auto_execute_scene = new ArrayList<>();
        for (AutoDevListInfo devListInfo : mActData) {
            if ("sce".equals(devListInfo.getDeviceType())) {
                InsertAutoInfo.AutoExecuteSceneBean sceneBean = new InsertAutoInfo.AutoExecuteSceneBean();
                sceneBean.setScene_id(devListInfo.getDevice_id());
                sceneBean.setScene_status(devListInfo.getDevice_status());
                auto_execute_scene.add(sceneBean);
            }
        }
        insertAutoInfo.setAuto_execute_scene(auto_execute_scene);
        //设备条件
        List<InsertAutoInfo.AutoIfha3ListBean> auto_ifha3_list = new ArrayList<>();
        for (AutoCondInfo autoCondInfo : mConData) {
            InsertAutoInfo.AutoIfha3ListBean ifha3ListBean = new InsertAutoInfo.AutoIfha3ListBean();
            ifha3ListBean.setIf_ha3_code(autoCondInfo.getHa3_code());
            ifha3ListBean.setIf_ha3_id(autoCondInfo.getDevice_id());
            ifha3ListBean.setIf_select(autoCondInfo.getSelect_if());
            ifha3ListBean.setIf_select_type(autoCondInfo.getSelect_type());
            ifha3ListBean.setIf_type("1");
            ifha3ListBean.setIf_value(autoCondInfo.getValue());
            auto_ifha3_list.add(ifha3ListBean);
        }
        insertAutoInfo.setAuto_ifha3_list(auto_ifha3_list);
        //时间段条件
        List<InsertAutoInfo.AutoIftimeListBean> auto_iftime_list = new ArrayList<>();
        InsertAutoInfo.AutoIftimeListBean iftimeListBean = new InsertAutoInfo.AutoIftimeListBean();
        iftimeListBean.setIf_begin_time(mStartTime);
        iftimeListBean.setIf_end_time(mEndTime);
        iftimeListBean.setIf_type("0");
        auto_iftime_list.add(iftimeListBean);
        insertAutoInfo.setAuto_iftime_list(auto_iftime_list);
        List<InsertAutoInfo.AutoTimeListBean> auto_time_list = new ArrayList<>();
        for (int i = 0; i < mDateData.size(); i++) {
            if (mDateData.get(i).isIsSlec()) {
                InsertAutoInfo.AutoTimeListBean timeListBean = new InsertAutoInfo.AutoTimeListBean();
                timeListBean.setFtime("" + i);
                auto_time_list.add(timeListBean);
            }
        }
        //星期条件
        insertAutoInfo.setAuto_time_list(auto_time_list);
        insertAutoInfo.setHome_id(mThisSelectHomeID);
        insertAutoInfo.setAuto_name(name);
        insertAutoInfo.setAuto_type(mAutoType);
        RequestParamsFM params = new RequestParamsFM();
        String auto = JSON.toJSONString(insertAutoInfo);
        params.put("auto", auto);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.INSERTAUTO, params, new HttpOkhUtils.HttpCallBack() {
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
                    mAddIntelligentFragment.refreshDataList();
                    MyFragmentManagerUtil.closeTopFragment(AddAutoFragment.this);
                }
            }
        });
    }

    private List<String> ifTypeItems;

    private void selectIfType() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tv_term.setText(ifTypeItems.get(options1));
                if (0 == options1) {
                    mAutoType = "0";
                } else {
                    mAutoType = "1";
                }
            }
        })
                .setTitleText("请选择满足类型件")
                .setBgColor(getResources().getColor(R.color.white))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(ifTypeItems);//一级选择器
        pvOptions.show();
    }

    private void initXingQiData() {
        mDateData = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            DateStatueInfo dateStatueInfo = new DateStatueInfo();
            if (i == 0) {
                dateStatueInfo.setTitle("单");
            } else if (i == 1) {
                dateStatueInfo.setTitle("一");
            } else if (i == 2) {
                dateStatueInfo.setTitle("二");
            } else if (i == 3) {
                dateStatueInfo.setTitle("三");
            } else if (i == 4) {
                dateStatueInfo.setTitle("四");
            } else if (i == 5) {
                dateStatueInfo.setTitle("五");
            } else if (i == 6) {
                dateStatueInfo.setTitle("六");
            } else if (i == 7) {
                dateStatueInfo.setTitle("日");
            }
            dateStatueInfo.setIsSlec(false);
            mDateData.add(dateStatueInfo);
        }
    }

    private void initRecyView() {
        //条件列表
        mConData = new ArrayList();
        recy_con.setLayoutManager(new LinearLayoutManager(getContext()));
        addConAdapter = new RecyAddCondAdapter(R.layout.adapter_auto_add_cond, mConData);
        addConAdapter.openLoadAnimation(SLIDEIN_RIGHT);
        recy_con.setAdapter(addConAdapter);
        RecyItemDragAdapter itemDragAdapter01 = new RecyItemDragAdapter(mConData);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback01 = new ItemDragAndSwipeCallback(itemDragAdapter01);
        ItemTouchHelper itemTouchHelper01 = new ItemTouchHelper(itemDragAndSwipeCallback01);
        itemTouchHelper01.attachToRecyclerView(recy_con);
        itemDragAdapter01.enableSwipeItem();
        itemDragAdapter01.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                mConData.remove(pos);
                addConAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
        addConAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_kind:
                        //打开选择器，选择设置类别
                        OpenSelectTypeView(position);
                        break;
                    case R.id.tv_dx:
                        //打开选择器，选择开关状态
                        OpenSelectIfView(position);
                        break;
                    case R.id.tv_value:
                        //弹出popupwindow，输入具体数值
                        toWriteValue(position);
                        break;
                    case R.id.img_delet:
                        mConData.remove(position);
                        addConAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });


        //动作列表
        mActData = new ArrayList();
        recy_act.setLayoutManager(new LinearLayoutManager(getContext()));
        addActAdapter = new RecyAddAutoActAdapter(R.layout.adapter_scene_add_dev, mActData);
        addActAdapter.openLoadAnimation(SLIDEIN_RIGHT);
        recy_act.setAdapter(addActAdapter);
        RecyItemDragAdapter itemDragAdapter = new RecyItemDragAdapter(mActData);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(itemDragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recy_act);
        itemDragAdapter.enableSwipeItem();
        itemDragAdapter.setOnItemSwipeListener(new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                mActData.remove(pos);
                addActAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

            }
        });
        //设置item childview点击事件
        addActAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_state:
                        //打开选择器，选择开关状态
                        OpenSelectStateView(position);
                        break;
                    case R.id.tv_ld:
                        //打开选择器，选择开关状态
                        OpenSelectValueView(position);
                        break;
                    case R.id.img_delet:
                        mActData.remove(position);
                        addActAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    private void toWriteValue(final int position) {
        final MyAlertDialogHelper dialogHelper = new MyAlertDialogHelper();
        View view = View.inflate(getContext(), R.layout.dialog_input_pass, null);
        dialogHelper.setDIYView(getContext(), view);
        dialogHelper.show();
        LinearLayout lin_title = view.findViewById(R.id.lin_title);
        TextView tv_warning = view.findViewById(R.id.tv_warning);
        final EditText et_pass = view.findViewById(R.id.et_pass);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_sure = view.findViewById(R.id.tv_sure);
        lin_title.setVisibility(View.GONE);
        tv_warning.setText("请输入数值");
        et_pass.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        et_pass.setHint("请输入数值");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHelper.disMiss();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cont = String.valueOf(et_pass.getText()).trim();
                if ("".equals(cont) || "请输入数值".equals(cont)) {
                    ToastUtils.showToast(getContext(), "请输入数值");
                    return;
                }
                mConData.get(position).setValue(cont);
                addConAdapter.notifyDataSetChanged();
                dialogHelper.disMiss();
            }
        });
    }

    private List<Ha3ValueInfo.Ha3TypeVlaueListBean> mValueData;

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
                    for (Ha3ValueInfo.Ha3TypeVlaueListBean bean : mValueData) {
                        typeItems.add(bean.getFname());
                    }
                }
            }
        });
    }

    private void OpenSelectTypeView(final int position) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mConData.get(position).setSelect_type(typeItems.get(options1));
                addConAdapter.notifyDataSetChanged();
            }
        })
                .setTitleText("请选择")
                .setBgColor(getResources().getColor(R.color.white))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(typeItems);//一级选择器
        pvOptions.show();
    }

    private void OpenSelectIfView(final int position) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mConData.get(position).setSelect_if(ifItems.get(options1));
                addConAdapter.notifyDataSetChanged();
            }
        })
                .setTitleText("请选择")
                .setBgColor(getResources().getColor(R.color.white))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(ifItems);//一级选择器
        pvOptions.show();
    }

    private void OpenSelectValueView(final int position) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if (0 == options1) {
                    mActData.get(position).setDevice_value("0001");
                } else if (1 == options1) {
                    mActData.get(position).setDevice_value("0002");
                } else {
                    mActData.get(position).setDevice_value("0003");
                }
                addActAdapter.notifyDataSetChanged();
            }
        })
                .setTitleText("亮度")
                .setBgColor(getResources().getColor(R.color.white))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(valueItems);//一级选择器
        pvOptions.show();
    }

    private void OpenSelectStateView(final int position) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if (0 == options1) {
                    mActData.get(position).setDevice_status("1");
                } else {
                    mActData.get(position).setDevice_status("0");
                }
                addActAdapter.notifyDataSetChanged();
            }
        })
                .setTitleText("状态")
                .setBgColor(getResources().getColor(R.color.white))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLUE) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(stateItems);//一级选择器
        pvOptions.show();
    }

    private List<String> stateItems;
    private List<String> valueItems;
    private List<String> typeItems;
    private List<String> ifItems;

    private void setSelectItemInfo() {
        ifTypeItems = new ArrayList();
        ifTypeItems.add("当满足所有条件时");
        ifTypeItems.add("当满足任一条件时");
        stateItems = new ArrayList();
        stateItems.add("打开");
        stateItems.add("关闭");
        valueItems = new ArrayList<>();
        valueItems.add("调灯50%亮");
        valueItems.add("调灯70%亮");
        valueItems.add("调灯100%亮");
        typeItems = new ArrayList<>();
        ifItems = new ArrayList<>();
        ifItems.add("大于");
        ifItems.add("等于");
        ifItems.add("小于");
    }

    private void getAutoDetailInfo() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mAutoID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.QUERYAUTODETIAL, params, new HttpOkhUtils.HttpCallBack() {
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
                AutoDetailInfoNew autoDetailInfoNew = gson.fromJson(resbody, AutoDetailInfoNew.class);
                ToastUtils.showToast(getContext(), autoDetailInfoNew.getMessage());
                if (1 == autoDetailInfoNew.getCode()) {
                    //展示自动化详情
                    tv_name.setText(autoDetailInfoNew.getAutodetail().get(0).getAuto_name());
                    if ("0".equals(autoDetailInfoNew.getAutodetail().get(0).getAuto_type())) {
                        mAutoType = "0";
                        tv_term.setText("当满足所有条件时");
                    } else {
                        mAutoType = "1";
                        tv_term.setText("当满足任一条件时");
                    }
                    mThisSelectHomeID = autoDetailInfoNew.getAutodetail().get(0).getHome_id();
                    thisAutoID = autoDetailInfoNew.getAutodetail().get(0).getId();
                    thisAutoStatue = autoDetailInfoNew.getAutodetail().get(0).getAuto_status();
                    //满足条件信息
                    for (AutoDetailInfoNew.AutoIfha3ListBean ifha3ListBean : autoDetailInfoNew.getAuto_ifha3_list()) {
                        AutoCondInfo autoCondInfo = new AutoCondInfo();
                        autoCondInfo.setDevice_id(ifha3ListBean.getIf_ha3_id());
                        autoCondInfo.setDevice_name(ifha3ListBean.getDevice_name());
                        autoCondInfo.setSelect_type(ifha3ListBean.getIf_select_type());
                        autoCondInfo.setSelect_if(ifha3ListBean.getIf_select());
                        autoCondInfo.setValue(ifha3ListBean.getIf_value());
                        autoCondInfo.setHa3_code(ifha3ListBean.getDevice_code());
                        mConData.add(autoCondInfo);
                    }
                    addConAdapter.notifyDataSetChanged();
                    //执行设备信息
                    for (AutoDetailInfoNew.AutoExecuteDeviceListBean deviceListBean : autoDetailInfoNew.getAuto_execute_device_list()) {
                        AutoDevListInfo devListInfo = new AutoDevListInfo();
                        devListInfo.setDevice_id(deviceListBean.getDevice_id());
                        devListInfo.setDevice_name(deviceListBean.getDevice_name());
                        devListInfo.setDevice_value(deviceListBean.getDevice_value());
                        devListInfo.setDevice_status(deviceListBean.getDevice_status());
                        devListInfo.setDeviceType(deviceListBean.getDeviceType());
                        mActData.add(devListInfo);
                    }
                    //执行场景信息
                    for (AutoDetailInfoNew.AutoExecuteSceneListBean sceneListBean : autoDetailInfoNew.getAuto_execute_scene_list()) {
                        AutoDevListInfo devListInfo = new AutoDevListInfo();
                        devListInfo.setDevice_id(sceneListBean.getId());
                        devListInfo.setDevice_name(sceneListBean.getScene_name());
                        devListInfo.setDevice_status(sceneListBean.getScene_status());
                        devListInfo.setDevice_id(sceneListBean.getId());
                        devListInfo.setDevice_value("0");
                        devListInfo.setDeviceType("sce");
                        mActData.add(devListInfo);
                    }
                    addActAdapter.notifyDataSetChanged();
                    //时间段信息
                    mStartTime = autoDetailInfoNew.getAuto_iftime_list().get(0).getIf_begin_time();
                    mEndTime = autoDetailInfoNew.getAuto_iftime_list().get(0).getIf_end_time();

                    for (AutoDetailInfoNew.AutoTimeListBean timeListBean : autoDetailInfoNew.getAuto_time_list()) {
                        if ("0".equals(timeListBean.getFval())) {
                            mDateData.get(0).setIsSlec(true);
                        } else if ("1".equals(timeListBean.getFval())) {
                            mDateData.get(1).setIsSlec(true);
                        } else if ("2".equals(timeListBean.getFval())) {
                            mDateData.get(2).setIsSlec(true);
                        } else if ("3".equals(timeListBean.getFval())) {
                            mDateData.get(3).setIsSlec(true);
                        } else if ("4".equals(timeListBean.getFval())) {
                            mDateData.get(4).setIsSlec(true);
                        } else if ("5".equals(timeListBean.getFval())) {
                            mDateData.get(5).setIsSlec(true);
                        } else if ("6".equals(timeListBean.getFval())) {
                            mDateData.get(6).setIsSlec(true);
                        } else if ("7".equals(timeListBean.getFval())) {
                            mDateData.get(7).setIsSlec(true);
                        }
                    }
                    String date = "";
                    for (DateStatueInfo info : mDateData) {
                        if (info.isIsSlec()) {
                            if ("".equals(date)) {
                                date = date + info.getTitle();
                            } else {
                                date = date + "、" + info.getTitle();
                            }
                        }
                    }
                    tv_cont.setText(date + "  " + mStartTime + "—" + mEndTime);

                }
            }
        });
    }

    private String mThisSelectHomeID = MyApplication.slecHomeID;//当前自动化的家庭id
    private String thisAutoID;//自动化的id
    private String thisAutoStatue;//自动化状态？

    //跳转选择时间
    private void totimerFragment() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        SceneTimingFragment sceneTimingFt = new SceneTimingFragment();
        sceneTimingFt.setUpFragment(this);
        ftt.add(R.id.frame, sceneTimingFt, "sceneTimingFt");
        ftt.addToBackStack(null);
        ftt.commit();
    }

    //跳转选择设备
    private void toAddConFragment() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        AddConditionFragment addConFt = new AddConditionFragment();
        addConFt.setUpFragment(this);
        ftt.add(R.id.frame, addConFt, "addConFt");
        ftt.addToBackStack(null);
        ftt.commit();
    }

    private void toAddActFragment() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        SelectDevSceFragment seltDevSceFt = new SelectDevSceFragment();
        seltDevSceFt.setUpFragment(this);
        ftt.add(R.id.frame, seltDevSceFt, "seltDevSceFt");
        ftt.addToBackStack(null);
        ftt.commit();

        //        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        //        AddActFragment addActFt = new AddActFragment();
        //        ftt.add(R.id.frame, addActFt, "addActFt");
        //        ftt.addToBackStack(null);
        //        ftt.commit();
    }

    private void showEditName() {
        final MyAlertDialogHelper dialogHelper = new MyAlertDialogHelper();
        View view = View.inflate(getContext(), R.layout.dialog_input_pass, null);
        dialogHelper.setDIYView(getContext(), view);
        dialogHelper.show();
        LinearLayout lin_title = view.findViewById(R.id.lin_title);
        TextView tv_warning = view.findViewById(R.id.tv_warning);
        final EditText et_pass = view.findViewById(R.id.et_pass);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_sure = view.findViewById(R.id.tv_sure);
        lin_title.setVisibility(View.GONE);
        tv_warning.setText("编辑名称");
        et_pass.setHint("输入自动化的名称");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHelper.disMiss();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(et_pass.getText()).trim();
                if ("".equals(name) || "输入自动化的名称".equals(name)) {
                    ToastUtils.showToast(getContext(), "名称不能为空");
                    return;
                }
                tv_name.setText(name);
                dialogHelper.disMiss();
            }
        });
    }

    private String               mStartTime;
    private String               mEndTime;
    private List<DateStatueInfo> mDateData;

    public void changeTimingUI(String cont, List<DateStatueInfo> data, String start, String end) {
        tv_cont.setText(cont);
        mStartTime = start;
        mEndTime = end;
        if (null == mDateData) {
            mDateData = new ArrayList<>();
        } else {
            mDateData.clear();
        }
        mDateData.addAll(data);
    }

    public void changeActUI(List<NotHA3ListInfo.NotHA3listBean> devList, List<NotHA3ListInfo.NotHA3listBean> sceList) {
        if (null != mActData && null != devList) {
            for (NotHA3ListInfo.NotHA3listBean bean : devList) {
                AutoDevListInfo devListInfo = new AutoDevListInfo();
                devListInfo.setDevice_name(bean.getDevice_name());
                devListInfo.setDevice_id(bean.getId());
                devListInfo.setDevice_status(bean.getDevice_status());
                devListInfo.setDevice_value("10");
                devListInfo.setDeviceType(bean.getDeviceType());
                mActData.add(devListInfo);
            }
        }
        if (null != mActData && null != sceList) {
            for (NotHA3ListInfo.NotHA3listBean bean : sceList) {
                AutoDevListInfo devListInfo = new AutoDevListInfo();
                devListInfo.setDevice_name(bean.getDevice_name());
                devListInfo.setDevice_id(bean.getId());
                devListInfo.setDevice_status(bean.getDevice_status());
                devListInfo.setDeviceType("sce");
                mActData.add(devListInfo);
            }
        }
        addActAdapter.notifyDataSetChanged();
    }

    private String mKind;
    private String mAutoID;

    public void setKind(String kind, String autoID) {
        mKind = kind;
        mAutoID = autoID;
    }

    public void changeCondUI(AutoCondInfo condInfo) {
        mConData.add(condInfo);
        addConAdapter.notifyDataSetChanged();
    }

    AddIntelligentFragment mAddIntelligentFragment;

    public void setUpFragment(AddIntelligentFragment intelligentFragment) {
        mAddIntelligentFragment = intelligentFragment;
    }
}
