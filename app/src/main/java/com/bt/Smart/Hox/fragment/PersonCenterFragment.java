package com.bt.Smart.Hox.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.GesturePassWordActivity;
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.SpUtils;
import com.bt.Smart.Hox.utils.ToastUtils;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/15 16:13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class PersonCenterFragment extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private ImageView      img_back;
    private TextView       tv_title;
    private Switch         swc_gesture;
    private RelativeLayout rlt_change_gesture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_person_center, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        swc_gesture = mRootView.findViewById(R.id.swc_gesture);
        rlt_change_gesture = mRootView.findViewById(R.id.rlt_change_gesture);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("个人中心");
        swc_gesture.setChecked("1".equals(SpUtils.getString(getContext(), "isOpenGes")) ? true : false);
        swc_gesture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (swc_gesture.isChecked()) {
                    if ("1".equals(SpUtils.getString(getContext(), "hasGes"))) {
                        SpUtils.putString(getContext(), "isOpenGes", "1");
                    } else {
                        ToastUtils.showToast(getContext(), "请设置手势密码");
                        Intent intent = new Intent(getContext(), GesturePassWordActivity.class);
                        intent.putExtra("gseture_kind", "0");
                        startActivity(intent);
                    }
                } else {
                    SpUtils.putString(getContext(), "isOpenGes", "0");
                }
            }
        });
        rlt_change_gesture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.rlt_change_gesture://修改手势密码
                //先弹出窗口，提示输入账户密码
                showDialogForPassWord();
                break;
        }
    }

    private void showDialogForPassWord() {
        final MyAlertDialogHelper dialogHelper = new MyAlertDialogHelper();
        View view = View.inflate(getContext(), R.layout.dialog_input_pass, null);
        dialogHelper.setDIYView(getContext(), view);
        dialogHelper.show();
        final EditText et_pass = view.findViewById(R.id.et_pass);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_warning = view.findViewById(R.id.tv_warning);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_sure = view.findViewById(R.id.tv_sure);
        tv_title.setText("提示");
        tv_warning.setText("该操作会重置手势密码！");
        et_pass.setHint("请输入账户密码");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogHelper.disMiss();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先比对下账户密码
                String pass = String.valueOf(et_pass.getText()).trim();
                if ("".equals(pass) || "请输入账户密码".equals(pass)) {
                    ToastUtils.showToast(getContext(), "密码不能为空");
                    return;
                }
                if (pass.equals(MyApplication.pasword)) {
                    //先关闭输入法，在跳转。否则有bug
                    //拿到InputMethodManager
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (et_pass.isFocused()){
                        et_pass.setFocusable(false);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }else {
                        dialogHelper.disMiss();
                        //跳转手势密码界面
                        Intent intent1 = new Intent(getContext(), GesturePassWordActivity.class);
                        intent1.putExtra("gseture_kind", "0");
                        startActivity(intent1);
                    }
                } else {
                    ToastUtils.showToast(getContext(), "密码错误");
                    return;
                }
            }
        });
    }

    public void hintKeyBoard() {
        //拿到InputMethodManager
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        //如果window上view获取焦点 && view不为空
        if (imm.isActive() && getActivity().getCurrentFocus() != null) {
            //拿到view的token 不为空
            if (getActivity().getCurrentFocus().getWindowToken() != null) {
                //表示软键盘窗口总是隐藏，除非开始时以SHOW_FORCED显示。
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
