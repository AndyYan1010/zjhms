package com.bt.Smart.Hox.activity.homeActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.SaomiaoUIActivity;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.HouseDetailInfo;
import com.bt.Smart.Hox.messegeInfo.ZhuKongListInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/30 10:35
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddDevDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView      img_back;
    private TextView       tv_title;
    private RelativeLayout rlt_choiceroom;
    private LinearLayout   lin_choiceroom;
    private TextView       tv_roomname;
    private RelativeLayout rlt_choicezk;
    private LinearLayout   lin_choicezk;
    private TextView       tv_ttname;
    private TextView       tv_zkname;
    private TextView       tv_ttcode;
    private EditText       et_name;//主控、从控名称
    private EditText       et_kcode;
    private ImageView      img_scan;
    private LinearLayout   lin_ycode;
    private EditText       et_ycode;
    private TextView       tv_warn;
    private TextView       tv_add;
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 1001;//申请照相机权限结果
    private int REQUEST_CODE                       = 1003;//接收扫描结果
    private String                                  mHomeID;
    private String                                  mRoomID;
    private String                                  mKind;
    private ArrayList<ZhuKongListInfo.HomeListBean> mZkList;//记录家下面的已有主控
    private ArrayList<String>                       options1Items;
    private ArrayList<String>                       roomItems;
    private int                                     choiceItem;
    private String                                  mHouseInfo;
    private List<HouseDetailInfo.HouseListBean>     roomList;
    private int REQUEST_NO_SET = 20001;//跳转wifi配置界面请求值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dev_detail_actiivty);
        initView();
        setData();
    }

    private void initView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rlt_choiceroom = (RelativeLayout) findViewById(R.id.rlt_choiceroom);
        lin_choiceroom = (LinearLayout) findViewById(R.id.lin_choiceroom);
        tv_roomname = (TextView) findViewById(R.id.tv_roomname);
        rlt_choicezk = (RelativeLayout) findViewById(R.id.rlt_choicezk);
        lin_choicezk = (LinearLayout) findViewById(R.id.lin_choicezk);
        tv_ttname = (TextView) findViewById(R.id.tv_ttname);
        tv_zkname = (TextView) findViewById(R.id.tv_zkname);
        tv_ttcode = (TextView) findViewById(R.id.tv_ttcode);
        et_name = (EditText) findViewById(R.id.et_name);
        et_kcode = (EditText) findViewById(R.id.et_kcode);
        img_scan = (ImageView) findViewById(R.id.img_scan);
        lin_ycode = (LinearLayout) findViewById(R.id.lin_ycode);
        et_ycode = (EditText) findViewById(R.id.et_ycode);
        tv_warn = (TextView) findViewById(R.id.tv_warn);
        tv_add = (TextView) findViewById(R.id.tv_add);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("添加");
        img_scan.setOnClickListener(this);
        tv_add.setOnClickListener(this);
        //数据
        mHomeID = getIntent().getStringExtra("homeID");
        mRoomID = getIntent().getStringExtra("roomID");
        mHouseInfo = getIntent().getStringExtra("allRoomInfo");
        mKind = getIntent().getStringExtra("devType");
        et_name.setText(getIntent().getStringExtra("name"));
        //填充房间信息
        roomList = new ArrayList<>();
        roomItems = new ArrayList<>();
        Gson gson = new Gson();
        HouseDetailInfo houseDetailInfo = gson.fromJson(mHouseInfo, HouseDetailInfo.class);
        roomList.addAll(houseDetailInfo.getHouseList());
        for (HouseDetailInfo.HouseListBean bean : roomList) {
            roomItems.add(bean.getHouse_name());
        }
        //初始房间id
        tv_roomname.setText(roomList.get(0).getHouse_name());
        mRoomID = roomList.get(0).getId();
        //页面分类显示
        if ("0".equals(mKind)) {//主控
            rlt_choicezk.setVisibility(View.GONE);
            tv_warn.setVisibility(View.GONE);
        } else if ("1".equals(mKind)) {//有线设备
            options1Items = new ArrayList<>();
            mZkList = new ArrayList<>();
            tv_ttname.setText("从控名称");
            tv_ttcode.setText("从控码    ");
            if (null != getIntent().getStringExtra("fromLin") && "1".equals(getIntent().getStringExtra("fromLin"))) {
                Intent intent = new Intent(AddDevDetailActivity.this, AddWifiDeviceActivity.class);
                startActivityForResult(intent, REQUEST_NO_SET);
            }
            //获取家下面的主控列表
            getZKDevList();
        } else if ("2".equals(mKind)) {
            rlt_choicezk.setVisibility(View.GONE);
            tv_ttname.setText("单品名称");
            tv_ttcode.setText("单品码    ");
            if (null != getIntent().getStringExtra("fromLin") && "1".equals(getIntent().getStringExtra("fromLin"))) {
                Intent intent = new Intent(AddDevDetailActivity.this, AddWifiDeviceActivity.class);
                startActivityForResult(intent, REQUEST_NO_SET);
            }
        } else {//红外
            rlt_choicezk.setVisibility(View.GONE);
            lin_ycode.setVisibility(View.GONE);
            tv_ttname.setText("红外名称名称");
            tv_ttcode.setText("红外设备码");
        }
        lin_choicezk.setOnClickListener(this);
        lin_choiceroom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.lin_choiceroom:
                showRoomPickerView();
                break;
            case R.id.lin_choicezk:
                showPickerView();
                break;
            case R.id.img_scan:
                //扫描二维码
                //动态申请照相机权限,开启照相机
                scanningCode();
                break;
            case R.id.tv_add://确认添加
                String name = String.valueOf(et_name.getText()).trim();
                String kcode = String.valueOf(et_kcode.getText()).trim();
                String ycode = String.valueOf(et_ycode.getText()).trim();
                if ("".equals(ycode) || "请输入设备验证码".equals(ycode)) {
                    ToastUtils.showToast(this, "请输入设备验证码");
                    return;
                }
                MyApplication.isLoading = true;
                if ("0".equals(mKind)) {//添加主控
                    if ("".equals(name)) {
                        ToastUtils.showToast(this, "请填写主控名称");
                        return;
                    }
                    if ("".equals(kcode) || "点击右侧图标扫描填入".equals(kcode)) {
                        ToastUtils.showToast(this, "请填写主控码");
                        return;
                    }
                    //添加主控
                    addZK(name, kcode, ycode, getIntent().getStringExtra("control_type"), getIntent().getStringExtra("device_type_id"));
                } else if ("1".equals(mKind)) {//添加从控
                    if (mZkList.size() == 0) {
                        ToastUtils.showToast(this, "未查询到主控，请先添加主控");
                        return;
                    }
                    if ("".equals(name)) {
                        ToastUtils.showToast(this, "请填写从控名称");
                        return;
                    }
                    if ("".equals(kcode) || "点击右侧图标扫描填入".equals(kcode)) {
                        ToastUtils.showToast(this, "请填写从控码");
                        return;
                    }
                    addCK(name, kcode, ycode, getIntent().getStringExtra("control_type"), mKind, getIntent().getStringExtra("device_type_id"));
                } else if ("2".equals(mKind)) {//添加单品
                    if ("".equals(name)) {
                        ToastUtils.showToast(this, "请填写从控名称");
                        return;
                    }
                    if ("".equals(kcode) || "点击右侧图标扫描填入".equals(kcode)) {
                        ToastUtils.showToast(this, "请填写从控码");
                        return;
                    }
                    addSingleDev(name, kcode, ycode, getIntent().getStringExtra("control_type"), mKind);
                } else {//添加红外
                    if ("".equals(name)) {
                        ToastUtils.showToast(this, "请填写单品名称");
                        return;
                    }
                    if ("".equals(kcode) || "点击右侧图标扫描填入".equals(kcode)) {
                        ToastUtils.showToast(this, "请填写单品码");
                        return;
                    }
                    ToastUtils.showToast(this, "暂无单品");
                }
                break;
        }
    }

    private void addSingleDev(final String name, final String kcode, String ycode, final String second_control_category, final String deviceType) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("register_id", MyApplication.userID);
        params.put("home_id", mHomeID);
        params.put("house_id", mRoomID);
        params.put("main_control_code", kcode);
        params.put("main_control_id", "danpin");
        params.put("second_contrl_code", kcode);
        params.put("second_control_id", "danpin");
        params.put("default_device_type", second_control_category);
        params.put("deviceType", deviceType);
        params.put("device_code", kcode);
        params.put("device_type_id", getIntent().getStringExtra("device_type_id"));
        params.put("device_config", 99);
        params.put("device_img", getIntent().getStringExtra("devcieTypePic"));
        params.put("device_name", name);
        params.put("device_status", 0);
        params.put("randomCode", ycode);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPostBean(NetConfig.DEVICE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddDevDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddDevDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(AddDevDetailActivity.this, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    finish();
                }
            }
        });
    }

    private void getZKDevList() {
        ProgressDialogUtil.startShow(this, "正在加载主控信息...");
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", mHomeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.MAINCONTROL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddDevDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddDevDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                ZhuKongListInfo zhuKongListInfo = gson.fromJson(resbody, ZhuKongListInfo.class);
                if (1 == zhuKongListInfo.getCode()) {
                    ToastUtils.showToast(AddDevDetailActivity.this, zhuKongListInfo.getMessage());
                    mZkList.addAll(zhuKongListInfo.getHomeList());
                    for (ZhuKongListInfo.HomeListBean bean : mZkList) {
                        options1Items.add(bean.getMain_control_name());
                    }
                    choiceItem = 0;
                    tv_zkname.setText(mZkList.get(0).getMain_control_name());
                } else {
                    ToastUtils.showToast(AddDevDetailActivity.this, "查询失败");
                }
            }
        });
    }

    private void showRoomPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_roomname.setText(roomList.get(options1).getHouse_name());
                mRoomID = roomList.get(options1).getId();
            }
        })
                .setTitleText("房间选择")
                .setBgColor(Color.WHITE)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(roomItems);//一级选择器
        pvOptions.show();
    }

    private void showPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                choiceItem = options1;
                tv_zkname.setText(mZkList.get(choiceItem).getMain_control_name());
            }
        })
                .setTitleText("主控选择")
                .setBgColor(Color.WHITE)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.show();
    }

    private void addCK(final String name, final String kcode, String ycode, final String second_control_category, final String deviceType, String device_type_id) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", mHomeID);
        params.put("main_control_code", mZkList.get(choiceItem).getMain_control_code());
        params.put("main_control_id", mZkList.get(choiceItem).getId());
        params.put("second_control_name", name);
        params.put("second_contrl_code", kcode);
        params.put("second_control_category", second_control_category);
        params.put("deviceType", deviceType);
        params.put("device_type_id", device_type_id);
        params.put("randomCode", ycode);
        HttpOkhUtils.getInstance().doPost(NetConfig.SECONDCONTROL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddDevDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddDevDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(AddDevDetailActivity.this, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    //自动添加设备
                    autoAddDev(mZkList.get(choiceItem).getMain_control_code(), mZkList.get(choiceItem).getId(), kcode, commonInfo.getId(), second_control_category, deviceType, kcode, name);
                }
            }
        });
    }

    private void autoAddDev(String main_control_code, String main_control_id, String second_contrl_code, String second_control_id, String default_device_type, String deviceType, String device_code, String device_name) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("register_id", MyApplication.userID);
        params.put("home_id", mHomeID);
        params.put("house_id", mRoomID);
        params.put("main_control_code", main_control_code);
        params.put("main_control_id", main_control_id);
        params.put("second_contrl_code", second_contrl_code);
        params.put("second_control_id", second_control_id);
        params.put("default_device_type", default_device_type);
        params.put("deviceType", deviceType);
        params.put("device_code", device_code);
        params.put("device_config", 99);
        params.put("device_img", getIntent().getStringExtra("devcieTypePic"));
        params.put("device_name", device_name);
        params.put("device_status", 0);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPost(NetConfig.DEVICE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddDevDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddDevDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(AddDevDetailActivity.this, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    finish();
                }
            }
        });
    }

    private void addZK(String name, String kcode, String ycode, String main_control_type, String device_type_id) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("main_control_code", kcode);
        params.put("main_control_name", name);
        params.put("home_id", mHomeID);
        params.put("main_control_type", main_control_type);
        params.put("device_type_id", device_type_id);
        params.put("randomCode", ycode);
        HttpOkhUtils.getInstance().doPost(NetConfig.MAINCONTROL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddDevDetailActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddDevDetailActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(AddDevDetailActivity.this, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    finish();
                }
            }
        });
    }

    private void scanningCode() {
        //第二个参数是需要申请的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);
        } else {
            Intent intent = new Intent(this, SaomiaoUIActivity.class);//这是一个自定义的扫描界面，扫描UI框放大了。
            // Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //获取扫描信息，填写到对应et
                    if (requestCode == REQUEST_CODE) {
                        et_kcode.setText(result);
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
        if (requestCode == REQUEST_NO_SET) {
            if (resultCode != 20002) {
                finish();
            }
        }
    }
}
