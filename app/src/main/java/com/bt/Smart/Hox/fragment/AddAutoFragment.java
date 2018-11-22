package com.bt.Smart.Hox.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.RecyAddActAdapter;
import com.bt.Smart.Hox.adapter.RecyItemDragAdapter;
import com.bt.Smart.Hox.messegeInfo.AutoDetailInfoNew;
import com.bt.Smart.Hox.messegeInfo.NotHA3ListInfo;
import com.bt.Smart.Hox.messegeInfo.SceneDevListInfo;
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
    private View                   mRootView;
    private ImageView              img_back;
    private TextView               tv_title;
    private TextView               tv_save;
    private TextView               tv_name;
    private TextView               tv_cont;//选择的星期起止时间
    private TextView               tv_term;//条件类型
    private ImageView              img_edit;
    private ImageView              img_add_time;
    private ImageView              img_add_con;
    private ImageView              img_add_act;
    private List                   mConData;
    private RecyAddActAdapter      addConAdapter;
    private RecyclerView           recy_con;
    private List<SceneDevListInfo> mActData;
    private RecyAddActAdapter      addActAdapter;
    private RecyclerView           recy_act;

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
        tv_term = mRootView.findViewById(R.id.tv_term);
        img_edit = mRootView.findViewById(R.id.img_edit);
        img_add_time = mRootView.findViewById(R.id.img_add_time);
        img_add_con = mRootView.findViewById(R.id.img_add_con);
        img_add_act = mRootView.findViewById(R.id.img_add_act);
        recy_con = mRootView.findViewById(R.id.recy_con);
        recy_act = mRootView.findViewById(R.id.recy_act);
    }

    private void initData() {
        tv_title.setText("智能设置");
        img_back.setVisibility(View.VISIBLE);
        tv_save.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        img_add_time.setOnClickListener(this);
        img_add_con.setOnClickListener(this);
        img_add_act.setOnClickListener(this);
        //设置recyclerview
        initRecyView();
        //设置选择器数据
        setSelectItemInfo();
        if ("1".equals(mKind)) {
            //获取自动化详情
            getAutoDetailInfo();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_save://保存

                break;
            case R.id.img_add_time:
                totimerFragment();
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
        }
    }

    private void initRecyView() {
        //条件列表
        mConData = new ArrayList();
        recy_con.setLayoutManager(new LinearLayoutManager(getContext()));
        addConAdapter = new RecyAddActAdapter(R.layout.adapter_scene_add_dev, mConData);
        addConAdapter.openLoadAnimation(SLIDEIN_RIGHT);
        recy_con.setAdapter(addConAdapter);


        //动作列表
        mActData = new ArrayList();
        recy_act.setLayoutManager(new LinearLayoutManager(getContext()));
        addActAdapter = new RecyAddActAdapter(R.layout.adapter_scene_add_dev, mActData);
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

    private void setSelectItemInfo() {
        stateItems = new ArrayList();
        stateItems.add("打开");
        stateItems.add("关闭");
        valueItems = new ArrayList<>();
        valueItems.add("调灯50%亮");
        valueItems.add("调灯70%亮");
        valueItems.add("调灯100%亮");
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
                    //TODO:展示自动化详情

                }
            }
        });
    }

    private void totimerFragment() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        SceneTimingFragment sceneTimingFt = new SceneTimingFragment();
        sceneTimingFt.setUpFragment(this);
        ftt.add(R.id.frame, sceneTimingFt, "sceneTimingFt");
        ftt.addToBackStack(null);
        ftt.commit();
    }

    private void toAddConFragment() {
        //跳转选择设备
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        AddConditionFragment addConFt = new AddConditionFragment();
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
                dialogHelper.disMiss();
            }
        });
    }

    public void changeTimingUI(String cont) {
        //TODO:往条件list中添加数据
        tv_cont.setText(cont);
    }

    public void changeActUI(List<NotHA3ListInfo.NotHA3listBean> devSceList) {
        if (null != mActData && null != devSceList) {
            for (NotHA3ListInfo.NotHA3listBean bean : devSceList) {
                SceneDevListInfo devListInfo = new SceneDevListInfo();
                devListInfo.setDevice_name(bean.getDevice_name());
                devListInfo.setDevice_id(bean.getId());
                devListInfo.setDevice_status("1");
                devListInfo.setDevice_value("0001");
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

}
