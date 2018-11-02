package com.bt.Smart.Hox.activity.meActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvMemberAdapter;
import com.bt.Smart.Hox.messegeInfo.HomeDetailInfo;
import com.bt.Smart.Hox.messegeInfo.HomeMembersInfo;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
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
 * @创建时间 2018/10/31 13:12
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class HomeDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView                          img_back;
    private TextView                           tv_title;
    private RelativeLayout                     rtv_name;//家庭名称
    private TextView                           tv_name;//名称
    private TextView                           tv_num;//房间数
    private RelativeLayout                     rtv_address;//家庭地址
    private TextView                           tv_address;//地址
    private LinearLayout                       lin_add;
    private List<HomeMembersInfo.HomeListBean> mData;//成员信息数据列表
    private ListView                           lv_member;
    private LvMemberAdapter                    memberAdapter;
    private TextView                           tv_delete;//移除家庭
    private String                             homeID;//家庭id
    private String                             isDefault;//是否是默认家庭
    private int REQUEST_ADD_MEMBER = 1002;//请求返回值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rtv_name = (RelativeLayout) findViewById(R.id.rtv_name);
        rtv_address = (RelativeLayout) findViewById(R.id.rtv_address);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_address = (TextView) findViewById(R.id.tv_address);

        lin_add = (LinearLayout) findViewById(R.id.lin_add);
        lv_member = (ListView) findViewById(R.id.lv_member);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("家庭信息");

        homeID = getIntent().getStringExtra("homeID");
        isDefault = getIntent().getStringExtra("isDefault");
        mData = new ArrayList();
        memberAdapter = new LvMemberAdapter(this, mData);
        lv_member.setAdapter(memberAdapter);
        lv_member.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!"1".equals(mData.get(i).getIsmanager())) {//共享房间设置
                    Intent intent = new Intent(HomeDetailActivity.this, ShareRoomActivity.class);
                    intent.putExtra("userID", mData.get(i).getId());//成员id//TODO
                    intent.putExtra("homeID", homeID);//成员id//TODO
                    startActivity(intent);
                }
            }
        });
        //获取家庭信息
        getHomeDetailInfo();
        //获取家庭成员信息
        getHomeMembers();

        rtv_name.setOnClickListener(this);
        rtv_address.setOnClickListener(this);
        lin_add.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.rtv_name://更改家庭名称
                openPopupWindow(rtv_name);
                break;
            case R.id.rtv_address://更改地址

                break;
            case R.id.lin_add://添加成员
                addMembers();
                break;
            case R.id.tv_delete://移除家庭
                deleteHome();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_ADD_MEMBER == requestCode) {
            //获取家庭成员信息
            getHomeMembers();
        }
    }

    private void addMembers() {
        //跳到添加成员界面
        Intent intent = new Intent(this, AddMembersActivity.class);
        intent.putExtra("homeID", homeID);
        startActivityForResult(intent, REQUEST_ADD_MEMBER);
    }

    private PopupWindow popupWindow;

    private void openPopupWindow(View v) {
        //防止重复按按钮
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        //设置PopupWindow的View
        View view = LayoutInflater.from(this).inflate(R.layout.popup_change_home_name, null);
        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置动画
        // popupWindow.setAnimationStyle(R.style.PopupWindow);
        //设置位置
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        //设置消失监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //设置背景色
                setBackgroundAlpha(1.0f);
            }
        });
        //设置PopupWindow的View点击事件
        setOnPopupViewClick(view);
        //设置背景色
        setBackgroundAlpha(0.5f);
    }

    //设置屏幕背景透明效果
    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }

    private void setOnPopupViewClick(View view) {
        final EditText et_name = view.findViewById(R.id.et_name);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_save = view.findViewById(R.id.tv_save);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//保存修改的家庭名称
                String homeName = String.valueOf(et_name.getText()).trim();
                if ("".equals(homeName) || "".equals(homeName)) {
                    ToastUtils.showToast(HomeDetailActivity.this, "请输入家庭名称");
                    return;
                }
                String address = String.valueOf(tv_address.getText()).trim();
                saveHomeName(homeName, address);
            }
        });
    }

    private void saveHomeName(final String homeName, String address) {//更新家
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", homeID);
        params.put("home_name", homeName);
        params.put("faddress", address);
        params.put("isdefault", isDefault);
        params.put("register_id", MyApplication.userID);
        HttpOkhUtils.getInstance().doPut(NetConfig.HOME, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(HomeDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(HomeDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo sendSMSInfo = gson.fromJson(resbody, CommonInfo.class);
                if (1 == sendSMSInfo.getCode()) {
                    ToastUtils.showToast(HomeDetailActivity.this, "更新成功");
                    popupWindow.dismiss();
                    tv_name.setText(homeName);
                } else {
                    ToastUtils.showToast(HomeDetailActivity.this, "更新失败");
                }
            }
        });
    }

    private AlertDialog alertDialog;

    private void deleteHome() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("温馨提示");
        builder.setMessage("您确定删除该家？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //删除家
                doDeleteHome();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    private void doDeleteHome() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("register_id", MyApplication.userID);
        params.put("home_id", homeID);
        HttpOkhUtils.getInstance().doDelete(NetConfig.HOME, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(HomeDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(HomeDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo sendSMSInfo = gson.fromJson(resbody, CommonInfo.class);
                if (1 == sendSMSInfo.getCode()) {
                    ToastUtils.showToast(HomeDetailActivity.this, "删除成功");
                    if (null != alertDialog) {
                        alertDialog.dismiss();
                    }
                    finish();
                } else {
                    ToastUtils.showToast(HomeDetailActivity.this, "删除失败");
                }
            }
        });
    }

    private void getHomeMembers() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", homeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HOMEMEMBER, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(HomeDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(HomeDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                HomeMembersInfo homeMembersInfo = gson.fromJson(resbody, HomeMembersInfo.class);
                if (1 == homeMembersInfo.getCode()) {
                    if (0 < homeMembersInfo.getHomeList().size()) {
                        if (null == mData) {
                            mData = new ArrayList();
                        } else {
                            mData.clear();
                        }
                        mData.addAll(homeMembersInfo.getHomeList());
                        memberAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showToast(HomeDetailActivity.this, "家庭成员未查找到");
                    }
                } else {
                    ToastUtils.showToast(HomeDetailActivity.this, "家庭成员查询失败");
                }
            }
        });
    }

    private void getHomeDetailInfo() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", homeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HOMEDETAIL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(HomeDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(HomeDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                HomeDetailInfo homeDetailInfo = gson.fromJson(resbody, HomeDetailInfo.class);
                if (1 == homeDetailInfo.getCode()) {
                    tv_num.setText("" + homeDetailInfo.getHouseCount());
                    if (0 != homeDetailInfo.getHouseCount()) {
                        tv_name.setText(homeDetailInfo.getHome().getHome_name());
                        tv_address.setText(homeDetailInfo.getHome().getFaddress());
                    }
                } else {
                    ToastUtils.showToast(HomeDetailActivity.this, "家庭信息查询失败");
                }
            }
        });

    }
}
