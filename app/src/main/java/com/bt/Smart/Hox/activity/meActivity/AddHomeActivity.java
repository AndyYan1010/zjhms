package com.bt.Smart.Hox.activity.meActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.AddRoomActivity;
import com.bt.Smart.Hox.adapter.LvAddRoomAdapter;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.RoomChoiceInfo;
import com.bt.Smart.Hox.util.GetJsonDataUtil;
import com.bt.Smart.Hox.util.JsonBean;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.bt.Smart.Hox.viewmodle.MyListView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/31 15:16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddHomeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView            img_back;
    private TextView             tv_title;
    private EditText             et_name;//填写家庭名称
    private TextView             tv_choice;//选择
    private LinearLayout         lin_add;//添加其他房间
    private List<RoomChoiceInfo> mData;//可添加房间数据
    private MyListView           lv_room;//房间列表
    private LvAddRoomAdapter     addRoomAdapter;
    private TextView             tv_setup;//确认创建
    private int REQUEST_CODE        = 1001;//添加房间返回值
    private int REQUEST_CREATE_HOME = 1008;//创建房间界面返回值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home);
        setView();
        setData();
    }

    private void setView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_name = (EditText) findViewById(R.id.et_name);
        tv_choice = (TextView) findViewById(R.id.tv_choice);
        lin_add = (LinearLayout) findViewById(R.id.lin_add);
        lv_room = (MyListView) findViewById(R.id.lv_room);
        tv_setup = (TextView) findViewById(R.id.tv_setup);
    }

    private void setData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("添加家庭");
        tv_choice.setOnClickListener(this);
        lin_add.setOnClickListener(this);
        tv_setup.setOnClickListener(this);

        mData = new ArrayList();
        mData.add(new RoomChoiceInfo("客厅", true));
        mData.add(new RoomChoiceInfo("主卧", true));
        mData.add(new RoomChoiceInfo("次卧", true));
        mData.add(new RoomChoiceInfo("书房", true));
        mData.add(new RoomChoiceInfo("厨房", true));
        addRoomAdapter = new LvAddRoomAdapter(this, mData);
        lv_room.setAdapter(addRoomAdapter);
        //开始解析地址数据
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_choice://选择位置
                openAddressWindow(tv_choice);
                break;
            case R.id.lin_add://添加其他房间
                Intent intent = new Intent(this, AddRoomActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.tv_setup://创建家庭/关闭页面
                String home_name = String.valueOf(et_name.getText()).trim();
                String address = String.valueOf(tv_choice.getText()).trim();
                if ("".equals(home_name) || "填写家庭名称".equals(home_name)) {
                    ToastUtils.showToast(this, "请填写家庭名称");
                    return;
                }
                if ("".equals(address) || "".equals(address)) {
                    ToastUtils.showToast(this, "请选择地址");
                    return;
                }
                address = "江苏省南通市海门市";
                boolean notnull = false;//选择的房间数为0
                for (RoomChoiceInfo choiceInfo : mData) {
                    if (choiceInfo.isIsChoice()) {
                        notnull = true;
                    }
                }
                if (!notnull) {
                    ToastUtils.showToast(this, "您未选择房间");
                    return;
                }
                creatHome(home_name, address);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
    private void openAddressWindow(TextView tv_choice) {
        //弹出地址选择器
        if (isLoaded) {
            showPickerView(tv_choice);
        } else {
            Toast.makeText(AddHomeActivity.this, "Please waiting until the data is parsed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPickerView(final TextView tv_chname) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                if (options1Items.get(options1).getPickerViewText().equals(options2Items.get(options1).get(options2))) {
                    tv_chname.setText(options1Items.get(options1).getPickerViewText() +
                            options3Items.get(options1).get(options2).get(options3));
                } else {
                    tv_chname.setText(tx);
                }
                Toast.makeText(AddHomeActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private boolean isLoaded = false;
    private Thread thread;
    private static final int     MSG_LOAD_DATA    = 0x0001;
    private static final int     MSG_LOAD_SUCCESS = 0x0002;
    private static final int     MSG_LOAD_FAILED  = 0x0003;
    private              Handler mHandler         = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        //                        Toast.makeText(HomeDetailActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    //                    Toast.makeText(HomeDetailActivity.this, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    Toast.makeText(AddHomeActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void initJsonData() {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体* */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            /**添加城市数据*/
            options2Items.add(CityList);
            /*** 添加地区数据*/
            options3Items.add(Province_AreaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private ArrayList<JsonBean>                     options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>>            options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private void creatHome(String home_name, String address) {
        JSONArray jsonArray1 = new JSONArray();
        try {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("home_name", home_name);
            jsonObject1.put("faddress", address);
            jsonObject1.put("register_id", MyApplication.userID);
            jsonArray1.put(jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray2 = new JSONArray();
        for (int i = 0; i < mData.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                if (mData.get(i).isIsChoice()) {
                    jsonObject.put("house_name", mData.get(i).getRoom_name());
                    jsonArray2.put(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        RequestParamsFM params = new RequestParamsFM();
        params.put("home", jsonArray1);
        params.put("house_member", jsonArray2);
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.HOME, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(AddHomeActivity.this, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(AddHomeActivity.this, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo sendSMSInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(AddHomeActivity.this, sendSMSInfo.getMessage());
                if (1 == sendSMSInfo.getCode()) {
                    setResult(REQUEST_CREATE_HOME);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            mData.add(new RoomChoiceInfo(data.getStringExtra("roomName"), true));
            addRoomAdapter.notifyDataSetChanged();
        }
    }
}
