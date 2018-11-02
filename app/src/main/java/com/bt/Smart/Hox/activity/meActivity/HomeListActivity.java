package com.bt.Smart.Hox.activity.meActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvHomeAdapter;
import com.bt.Smart.Hox.messegeInfo.UserHomeInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
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
 * @创建时间 2018/10/31 10:49
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HomeListActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                       img_back;
    private TextView                        tv_title;//标题
    private ImageView                       img_home;
    private TextView                        tv_add;//添加新的家
    private LinearLayout                    lin_nomsg;
    private List<UserHomeInfo.HomeListBean> mData;//家庭列表数据
    private ListView                        lv_home;//家列表view
    private LvHomeAdapter                   homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        img_home = (ImageView) findViewById(R.id.img_home);
        tv_add = (TextView) findViewById(R.id.tv_add);
        lin_nomsg = (LinearLayout) findViewById(R.id.lin_nomsg);
        lv_home = (ListView) findViewById(R.id.lv_home);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("家列表");
        tv_add.setOnClickListener(this);
        mData = new ArrayList();
        homeAdapter = new LvHomeAdapter(this, mData);
        lv_home.setAdapter(homeAdapter);
        lv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转家庭编辑界面
                Intent intent = new Intent(HomeListActivity.this, HomeDetailActivity.class);
                intent.putExtra("homeID", mData.get(i).getHome_id());
                intent.putExtra("isDefault", mData.get(i).getIsdefault());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取家庭列表
        getHomeList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_add:
                //跳转添加家庭界面
                startActivity(new Intent(this, AddHomeActivity.class));
                break;
        }
    }

    private void getHomeList() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("register_id", MyApplication.userID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HOME, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(HomeListActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(HomeListActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                UserHomeInfo userHomeInfo = gson.fromJson(resbody, UserHomeInfo.class);
                if (1 == userHomeInfo.getCode()) {
                    if (userHomeInfo.getHomeList().size() > 0) {
                        lin_nomsg.setVisibility(View.GONE);
                        if (null == mData) {
                            mData = new ArrayList();
                        } else {
                            mData.clear();
                        }
                        //显示默认家庭，房间信息
                        for (UserHomeInfo.HomeListBean bean : userHomeInfo.getHomeList()) {//添加到家的列表
                            mData.add(bean);
                        }
                        homeAdapter.notifyDataSetChanged();
                    } else {
                        lin_nomsg.setVisibility(View.VISIBLE);
                    }
                } else {
                    lin_nomsg.setVisibility(View.VISIBLE);
                    ToastUtils.showToast(HomeListActivity.this, "家信息获取失败");
                }
            }
        });
    }
}
