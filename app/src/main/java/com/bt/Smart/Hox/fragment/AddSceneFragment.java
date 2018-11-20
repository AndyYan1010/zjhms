package com.bt.Smart.Hox.fragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.RecyAddActAdapter;
import com.bt.Smart.Hox.adapter.RecyItemDragAdapter;
import com.bt.Smart.Hox.util.GlideLoaderUtil;
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

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
    private View         mRootView;
    private ImageView    img_back;
    private TextView     tv_title;
    private TextView     tv_save;//保存
    private ImageView    img_bg;
    private TextView     tv_name;
    private TextView     tv_warn;//提示
    private ImageView    img_chbg;//更换背景
    private ImageView    img_edit;//编辑场景名字
    private ImageView    img_add;//添加动作
    private RecyclerView recy_act;//动作列表
    private SwitchCompat swc_show;//是否首页展示
    private int          mSelectPicID;//记录选择的背景图ID
    private List         mData;

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
        mData = new ArrayList();
        mData.add("删除");
        mData.add("删除");
        mData.add("删除");
        //设置recyclerview
        initRecyView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.tv_save://添加场景

                break;
            case R.id.img_edit:
                //编辑场景名字
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

    private void initRecyView() {
        recy_act.setLayoutManager(new LinearLayoutManager(getContext()));
        final RecyAddActAdapter addActAdapter = new RecyAddActAdapter(R.layout.adapter_scene_add_dev, mData);
        addActAdapter.openLoadAnimation(SLIDEIN_RIGHT);
        recy_act.setAdapter(addActAdapter);

        RecyItemDragAdapter itemDragAdapter = new RecyItemDragAdapter(mData);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(itemDragAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recy_act);
        // 开启拖拽
        //         itemDragAdapter.enableDragItem(itemTouchHelper, R.id.textView, true);
        //         itemDragAdapter.setOnItemDragListener(onItemDragListener);
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
    }
}
