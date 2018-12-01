package com.bt.Smart.Hox.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
import com.bt.Smart.Hox.adapter.RecyAddSceActAdapter;
import com.bt.Smart.Hox.adapter.RecyItemDragAdapter;
import com.bt.Smart.Hox.messegeInfo.AddSceneResultInfo;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.NotHA3ListInfo;
import com.bt.Smart.Hox.messegeInfo.ParamSceneInfo;
import com.bt.Smart.Hox.messegeInfo.ParamSceneInfoWithID;
import com.bt.Smart.Hox.messegeInfo.SceneDetailInfoNew;
import com.bt.Smart.Hox.messegeInfo.SceneDevListInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;
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
 * @创建时间 2018/11/18 19:03
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddSceneFragment extends Fragment implements View.OnClickListener {
    private View                   mRootView;
    private ImageView              img_back;
    private TextView               tv_title;
    private TextView               tv_save;//保存
    private CardView               cv_delt;//删除场景
    private CardView               cv_showhome;//出现在首页
    private ImageView              img_bg;
    private TextView               tv_name;
    private TextView               tv_warn;//提示
    private ImageView              img_chbg;//更换背景
    private ImageView              img_edit;//编辑场景名字
    private ImageView              img_add;//添加动作
    private List<SceneDevListInfo> mData;//选择的设备动作数据
    private RecyAddSceActAdapter   addActAdapter;
    private RecyclerView           recy_act;//动作列表
    private SwitchCompat           swc_show;//是否开启
    private SwitchCompat           swc_showhome;//是否首页展示
    private int                    mSelectPicID;//记录选择的背景图ID
    private String mSelectPicUrl = "http://www.smart-hox.com:8081/upFiles/upload/files/20181108/vmw-hp-hero-vsan-innovations_1541681849443.jpg";//记录选择的背景图url
    private boolean isOpen;
    private boolean isShow;
    private String mThisSceHomeID = MyApplication.slecHomeID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_scene, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        tv_save = mRootView.findViewById(R.id.tv_save);
        img_bg = mRootView.findViewById(R.id.img_bg);
        tv_name = mRootView.findViewById(R.id.tv_name);
        img_edit = mRootView.findViewById(R.id.img_edit);
        tv_warn = mRootView.findViewById(R.id.tv_warn);
        img_chbg = mRootView.findViewById(R.id.img_chbg);
        img_add = mRootView.findViewById(R.id.img_add);
        recy_act = mRootView.findViewById(R.id.recy_act);
        swc_show = mRootView.findViewById(R.id.swc_show);
        cv_showhome = mRootView.findViewById(R.id.cv_showhome);
        swc_showhome = mRootView.findViewById(R.id.swc_showhome);
        cv_delt = mRootView.findViewById(R.id.cv_delt);
    }

    private void initData() {
        tv_title.setText("智能设置");
        img_back.setVisibility(View.VISIBLE);
        tv_save.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        img_chbg.setOnClickListener(this);
        img_add.setOnClickListener(this);
        cv_delt.setOnClickListener(this);

        //设置recyclerview
        initRecyView();
        //设置选择器数据
        setSelectItemInfo();
        if ("1".equals(mKind)) {//显示详情，修改场景
            cv_showhome.setVisibility(View.VISIBLE);
            swc_showhome.setOnClickListener(this);
            cv_delt.setVisibility(View.VISIBLE);
            //获取详情
            getSceneDetailInfo();
        }
        swc_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isOpen = true;
                } else {
                    isOpen = false;
                }
            }
        });
        swc_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("1".equals(mKind)) {
                    if (isOpen) {
                        //开启或关闭
                        upDateSceneStatus();
                    }
                } else {
                    swc_show.setChecked(false);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                if (null != mFrom && "recy".equals(mFrom)) {
                    getActivity().finish();
                } else {
                    MyFragmentManagerUtil.closeTopFragment(this);
                }
                break;
            case R.id.tv_save://添加场景
                String name = String.valueOf(tv_name.getText()).trim();
                if ("".equals(name) || "编辑名字".equals(name)) {
                    ToastUtils.showToast(getContext(), "场景名称不能为空");
                    return;
                }
                if ("1".equals(mKind)) {//显示详情，修改场景
                    //修改场景
                    upDataScene(name);
                } else {
                    //新增场景
                    saveScene(name);
                }
                break;
            case R.id.swc_showhome://首页展示场景
                if (isShow) {
                    showOnHomePage("0");
                } else {
                    showOnHomePage("1");
                }
                MyApplication.sceneRefresh = true;
                break;
            case R.id.cv_delt://删除场景
                deleteScene();
                MyApplication.sceneRefresh = true;
                break;
            case R.id.img_edit://编辑场景名字
                showEditName();
                break;
            case R.id.img_chbg://更换场景背景图
                toChoiceScenePic();
                break;
            case R.id.img_add://跳转添加动作界面
                toAddActFragment();
                break;
        }
    }

    private void showOnHomePage(String nShow) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mSceneID);
        params.put("show_status", nShow);
        HttpOkhUtils.getInstance().doPut(NetConfig.UPDATESHOWSTATUS, params, new HttpOkhUtils.HttpCallBack() {
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
                if (1 == commonInfo.getCode()) {
                    isShow = !isShow;
                    swc_showhome.setChecked(isShow);
                }
            }
        });
    }

    private void deleteScene() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mSceneID);
        HttpOkhUtils.getInstance().doDelete(NetConfig.DELETESCENE, params, new HttpOkhUtils.HttpCallBack() {
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
                    MyFragmentManagerUtil.closeTopFragment(AddSceneFragment.this);
                }
            }
        });
    }

    private void upDateSceneStatus() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mSceneID);
        if (isOpen) {
            params.put("status", "1");
        } else {
            params.put("status", "0");
        }
        HttpOkhUtils.getInstance().doPut(NetConfig.UPDATESTATUS, params, new HttpOkhUtils.HttpCallBack() {
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
            }
        });
    }

    private void upDataScene(String name) {
        ParamSceneInfoWithID paramSceneInfo = new ParamSceneInfoWithID();
        List<ParamSceneInfo.DevicelistBean> mSceList = new ArrayList<>();
        for (SceneDevListInfo devListInfo : mData) {
            ParamSceneInfo.DevicelistBean bean = new ParamSceneInfo.DevicelistBean();
            bean.setDevice_id(devListInfo.getDevice_id());
            bean.setDevice_status(devListInfo.getDevice_status());
            bean.setDevice_value(devListInfo.getDevice_value());
            bean.setMain_control_code(devListInfo.getMain_control_code());
//            bean.setDeviceType(devListInfo.getDeviceType());
            mSceList.add(bean);
        }
        paramSceneInfo.setDevicelist(mSceList);
        paramSceneInfo.setHome_id(mThisSceHomeID);
        paramSceneInfo.setScene_name(name);
        paramSceneInfo.setId(mSceneID);
        paramSceneInfo.setScene_pic(mSelectPicUrl);
        String scene = JSON.toJSONString(paramSceneInfo);
        RequestParamsFM params = new RequestParamsFM();
        params.put("scene", scene);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.UPDATESCENE, params, new HttpOkhUtils.HttpCallBack() {
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
                    if (null != mAddIntelligentFragment) {
                        mAddIntelligentFragment.refreshDataList();
                    }
                    MyFragmentManagerUtil.closeTopFragment(AddSceneFragment.this);
                }
            }
        });
    }

    private void getSceneDetailInfo() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mSceneID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.QUERYSCENEDETAIL, params, new HttpOkhUtils.HttpCallBack() {
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
                SceneDetailInfoNew sceneDetailInfoNew = gson.fromJson(resbody, SceneDetailInfoNew.class);
                ToastUtils.showToast(getContext(), sceneDetailInfoNew.getMessage());
                if (1 == sceneDetailInfoNew.getCode()) {
                    mThisSceHomeID = sceneDetailInfoNew.getSceneDetail().get(0).getHome_id();
                    tv_name.setText(sceneDetailInfoNew.getSceneDetail().get(0).getScene_name());
                    tv_warn.setVisibility(View.VISIBLE);
                    tv_warn.setText("当点击\"" + sceneDetailInfoNew.getSceneDetail().get(0).getScene_name() + "\"" + "场景");
                    mSelectPicUrl = sceneDetailInfoNew.getSceneDetail().get(0).getScene_pic();
                    GlideLoaderUtil.showImageView(getContext(), mSelectPicUrl, img_bg);
                    if ("0".equals(sceneDetailInfoNew.getSceneDetail().get(0).getScene_status())) {
                        swc_show.setChecked(false);
                        isOpen = false;
                    } else {
                        swc_show.setChecked(true);
                        isOpen = true;
                    }
                    if ("0".equals(sceneDetailInfoNew.getSceneDetail().get(0).getShow_status())) {
                        swc_showhome.setChecked(false);
                        isShow = false;
                    } else {
                        swc_showhome.setChecked(true);
                        isShow = true;
                    }
                    List<SceneDetailInfoNew.SceneDeviceDetailBean> sceneDeviceDetail = sceneDetailInfoNew.getSceneDeviceDetail();
                    for (SceneDetailInfoNew.SceneDeviceDetailBean bean : sceneDeviceDetail) {
                        SceneDevListInfo devListInfo = new SceneDevListInfo();
                        devListInfo.setDevice_id(bean.getDevice_id());
                        devListInfo.setDevice_name(bean.getDevice_name());
                        devListInfo.setDevice_status(bean.getDevice_status());
                        devListInfo.setDevice_value(bean.getDevice_value());
//                        devListInfo.setDeviceType(bean.getDefault_device_type());
                        devListInfo.setMain_control_code(bean.getMain_control_code());
                        mData.add(devListInfo);
                    }
                    addActAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void saveScene(String name) {
        ParamSceneInfo paramSceneInfo = new ParamSceneInfo();
        paramSceneInfo.setHome_id(mThisSceHomeID);
        List<ParamSceneInfo.DevicelistBean> mSceList = new ArrayList<>();
        for (SceneDevListInfo devListInfo : mData) {
            ParamSceneInfo.DevicelistBean bean = new ParamSceneInfo.DevicelistBean();
            bean.setDevice_id(devListInfo.getDevice_id());
            bean.setDevice_status(devListInfo.getDevice_status());
            bean.setDevice_value(devListInfo.getDevice_value());
//            bean.setDeviceType(devListInfo.getDeviceType());
            bean.setMain_control_code(devListInfo.getMain_control_code());
            mSceList.add(bean);
        }
        paramSceneInfo.setDevicelist(mSceList);
        paramSceneInfo.setScene_name(name);
        paramSceneInfo.setScene_pic(mSelectPicUrl);
        RequestParamsFM params = new RequestParamsFM();
        String scene = JSON.toJSONString(paramSceneInfo);
        params.put("scene", scene);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.INSERTSCENE, params, new HttpOkhUtils.HttpCallBack() {
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
                AddSceneResultInfo addSceneResultInfo = gson.fromJson(resbody, AddSceneResultInfo.class);
                ToastUtils.showToast(getContext(), addSceneResultInfo.getMessage());
                if (1 == addSceneResultInfo.getResult()) {
                    if (null != mAddIntelligentFragment) {
                        mAddIntelligentFragment.refreshDataList();
                    }
                    MyFragmentManagerUtil.closeTopFragment(AddSceneFragment.this);
                }
            }
        });
    }

    private void setSelectItemInfo() {
        stateItems = new ArrayList();
        stateItems.add("打开");
        stateItems.add("关闭");
        valueItems = new ArrayList<>();
        valueItems.add("调灯50%亮");
        valueItems.add("调灯70%亮");
        valueItems.add("调灯100%亮");
    }

    private void initRecyView() {
        mData = new ArrayList();
        recy_act.setLayoutManager(new LinearLayoutManager(getContext()));
        addActAdapter = new RecyAddSceActAdapter(R.layout.adapter_scene_add_dev, mData);
        addActAdapter.openLoadAnimation(SLIDEIN_RIGHT);
        recy_act.setAdapter(addActAdapter);

        RecyItemDragAdapter itemDragAdapter = new RecyItemDragAdapter(mData);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(itemDragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recy_act);
        // 开启拖拽
        // itemDragAdapter.enableDragItem(itemTouchHelper, R.id.textView, true);
        // itemDragAdapter.setOnItemDragListener(onItemDragListener);
        // 开启滑动删除
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
                mData.remove(pos);
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
                        mData.remove(position);
                        addActAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    private List<String> stateItems;
    private List<String> valueItems;

    private void OpenSelectStateView(final int position) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if (0 == options1) {
                    mData.get(position).setDevice_status("1");
                } else {
                    mData.get(position).setDevice_status("0");
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

    private void OpenSelectValueView(final int position) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if (0 == options1) {
                    mData.get(position).setDevice_value("0001");
                } else if (1 == options1) {
                    mData.get(position).setDevice_value("0002");
                } else {
                    mData.get(position).setDevice_value("0003");
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

    private void toChoiceScenePic() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        ChoiceScenePicFragment choPicFt = new ChoiceScenePicFragment();
        choPicFt.setUpFragment(this);
        ftt.add(R.id.frame, choPicFt, "choPicFt");
        ftt.addToBackStack(null);
        ftt.commit();
    }

    private void toAddActFragment() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        AddActFragment addActFt = new AddActFragment();
        addActFt.setUpFragment(this);
        ftt.add(R.id.frame, addActFt, "addActFt");
        ftt.addToBackStack(null);
        ftt.commit();
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
        et_pass.setHint("输入该智能的名称");
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
                if ("".equals(name) || "输入该智能的名称".equals(name)) {
                    ToastUtils.showToast(getContext(), "名称不能为空");
                    return;
                }
                tv_name.setText(name);
                tv_warn.setVisibility(View.VISIBLE);
                tv_warn.setText("当点击\"" + name + "\"" + "场景");
                dialogHelper.disMiss();
            }
        });
    }

    public void changeBgPic(String picUrl, int id) {
        GlideLoaderUtil.showImageView(getContext(), picUrl, img_bg);
        mSelectPicID = id;
        mSelectPicUrl = picUrl;
    }

    public void addActListInfo(List<NotHA3ListInfo.NotHA3listBean> actList) {
        if (null != mData && null != actList) {
            for (NotHA3ListInfo.NotHA3listBean bean : actList) {
                SceneDevListInfo devListInfo = new SceneDevListInfo();
                devListInfo.setDevice_name(bean.getDevice_name());
                devListInfo.setDevice_id(bean.getId());
                devListInfo.setDevice_status("1");
                devListInfo.setDevice_value("0001");
                devListInfo.setMain_control_code(bean.getMain_control_code());
//                devListInfo.setDeviceType(bean.getDefault_device_type());
                mData.add(devListInfo);
            }
        }
        addActAdapter.notifyDataSetChanged();
    }

    private String mKind;//是添加还是详情
    private String mSceneID;//场景id

    public void setKind(String kind, String sceneID) {
        mKind = kind;
        mSceneID = sceneID;
    }

    private AddIntelligentFragment mAddIntelligentFragment;

    public void setUpFragment(AddIntelligentFragment intelligentFragment) {
        mAddIntelligentFragment = intelligentFragment;
    }

    private String mFrom;

    public void setFrom(String from) {
        mFrom = from;
    }
}
