package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.SpUtils;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/27 13:48
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ChangePassWordFragment extends Fragment implements View.OnClickListener {
    private View      mRootView;
    private ImageView img_back;
    private TextView  tv_title;
    private EditText  et_old;
    private EditText  et_new01;
    private EditText  et_new02;
    private boolean   isOldShow;
    private boolean   isNew01Show;
    private boolean   isNew02Show;
    private ImageView img_old;
    private ImageView img_new01;
    private ImageView img_new02;
    private Button    bt_submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_change_pasd, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        et_old = mRootView.findViewById(R.id.et_old);
        et_new01 = mRootView.findViewById(R.id.et_new01);
        et_new02 = mRootView.findViewById(R.id.et_new02);
        img_old = mRootView.findViewById(R.id.img_old);
        img_new01 = mRootView.findViewById(R.id.img_new01);
        img_new02 = mRootView.findViewById(R.id.img_new02);
        bt_submit = mRootView.findViewById(R.id.bt_submit);

    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("修改密码");
        img_old.setOnClickListener(this);
        img_new01.setOnClickListener(this);
        img_new02.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
            case R.id.img_old:
                isOldShow = !isOldShow;
                showPassWord(et_old, isOldShow);
                break;
            case R.id.img_new01:
                isNew01Show = !isNew01Show;
                showPassWord(et_new01, isNew01Show);
                break;
            case R.id.img_new02:
                isNew02Show = !isNew02Show;
                showPassWord(et_new02, isNew02Show);
                break;
            case R.id.bt_submit:
                String psdOld = String.valueOf(et_old.getText()).trim();
                String psdNew01 = String.valueOf(et_new01.getText()).trim();
                String psdNew02 = String.valueOf(et_new02.getText()).trim();
                if ("".equals(psdOld) || "填写原密码".equals(psdOld)) {
                    ToastUtils.showToast(getContext(), "填写原密码");
                    return;
                }
                if ("".equals(psdNew01) || "填写新密码".equals(psdNew01)) {
                    ToastUtils.showToast(getContext(), "填写新密码");
                    return;
                }
                if ("".equals(psdNew02) || "再次填写确认".equals(psdNew02)) {
                    ToastUtils.showToast(getContext(), "请再次填写确认");
                    return;
                }
                if (!psdNew01.equals(psdNew02)) {
                    ToastUtils.showToast(getContext(), "两次密码不一致，请重新输入");
                    return;
                }
                //修改密码
                changePasd(psdOld, psdNew01);
                break;
        }
    }

    private void showPassWord(EditText et, boolean isShow) {
        if (isShow) {
            et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        et.setSelection(et.length());
    }

    private void changePasd(String psdOld, final String psdNew01) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("mobile", MyApplication.userPhone);
        params.put("fpasswordOld", psdOld);
        params.put("fpasswordNew", psdNew01);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.BACKFPASSWORD, params, new HttpOkhUtils.HttpCallBack() {
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
                    MyApplication.pasword = psdNew01;
                    SpUtils.putString(getContext(), "psd", psdNew01);
                    MyFragmentManagerUtil.closeTopFragment(ChangePassWordFragment.this);
                }
            }
        });
    }
}
