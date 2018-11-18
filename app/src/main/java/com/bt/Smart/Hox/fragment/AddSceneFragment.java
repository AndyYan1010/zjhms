package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ToastUtils;

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
    private TextView     tv_save;
    private TextView     tv_name;
    private TextView     tv_warn;
    private ImageView    img_edit;
    private ImageView    img_add;
    private ListView     lv_scene;
    private SwitchCompat swc_show;

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
        tv_name = mRootView.findViewById(R.id.tv_name);
        img_edit = mRootView.findViewById(R.id.img_edit);
        tv_warn = mRootView.findViewById(R.id.tv_warn);
        img_add = mRootView.findViewById(R.id.img_add);
        lv_scene = mRootView.findViewById(R.id.lv_scene);
        swc_show = mRootView.findViewById(R.id.swc_show);

    }

    private void initData() {
        tv_title.setText("智能设置");
        img_back.setVisibility(View.VISIBLE);
        tv_save.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        img_add.setOnClickListener(this);
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
            case R.id.img_add://跳转添加动作界面
                toAddActFragment();
                break;
        }
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
}
