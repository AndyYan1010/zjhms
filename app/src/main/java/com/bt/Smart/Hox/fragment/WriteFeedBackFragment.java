package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/3 20:10
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class WriteFeedBackFragment extends Fragment implements View.OnClickListener {
    private View      mRootView;
    private ImageView img_back;
    private TextView  tv_title;
    private EditText  et_cont;//内容
    private TextView  tv_submit;//提交

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.frg_write_feedback, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = (ImageView) mRootView.findViewById(R.id.img_back);
        tv_title = (TextView) mRootView.findViewById(R.id.tv_title);
        et_cont = (EditText) mRootView.findViewById(R.id.et_cont);
        tv_submit = (TextView) mRootView.findViewById(R.id.tv_submit);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("填写反馈");
        tv_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                //弹出回退栈最上面的fragment
                getFragmentManager().popBackStackImmediate(null, 0);
                break;
            case R.id.tv_submit:
                String content = String.valueOf(et_cont.getText()).trim();
                if ("".equals(content)||"请详细描述您的问题和不满，例如您在做什么时遇到了问题，希望我们有什么功能等。(最多150个字)".equals(content)){
                    ToastUtils.showToast(getContext(),"请填写内容");
                    return;
                }
                sendContent(content);
                break;
        }
    }

    private void sendContent(String content) {
        RequestParamsFM params=new RequestParamsFM();
        params.put("mobile", MyApplication.userPhone);
        params.put("complaint",content);
        HttpOkhUtils.getInstance().doPost(NetConfig.COMPLAINTFEEDBACK, params, new HttpOkhUtils.HttpCallBack() {
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
                ToastUtils.showToast(getContext(),commonInfo.getMessage());
            }
        });
    }
}
