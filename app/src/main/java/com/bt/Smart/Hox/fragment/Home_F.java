package com.bt.Smart.Hox.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.AddDeviceActivity;
import com.bt.Smart.Hox.activity.homeActivity.CreateHomeActivity;
import com.bt.Smart.Hox.activity.homeActivity.RoomManagerActivity;
import com.bt.Smart.Hox.activity.homeActivity.WeatherDetailActivity;
import com.bt.Smart.Hox.activity.meActivity.HomeListActivity;
import com.bt.Smart.Hox.adapter.LvSetHomeAdapter;
import com.bt.Smart.Hox.adapter.MyPagerAdapter;
import com.bt.Smart.Hox.adapter.RecSceneAdapter;
import com.bt.Smart.Hox.messegeInfo.HouseDetailInfo;
import com.bt.Smart.Hox.messegeInfo.SceneInfo;
import com.bt.Smart.Hox.messegeInfo.UserHomeInfo;
import com.bt.Smart.Hox.messegeInfo.WeatherInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.LocationUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.bt.Smart.Hox.viewmodle.MyFixedViewpager;
import com.bt.Smart.Hox.viewmodle.MyListView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/5/22 16:41
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class Home_F extends Fragment implements View.OnClickListener {
    private View                            mRootView;
    private TextView                        tv_mine;
    private LinearLayout                    lin_mine;
    private LinearLayout                    lin_weather;
    private ImageView                       img_weather;//天气图片
    private TextView                        tv_weather;
    private TextView                        tv_temp;
    private TextView                        tv_pm2d5;
    private TextView                        tv_aqi;
    private ImageView                       img_add;//添加设备
    private List<SceneInfo.ScenelistBean>   mData;//场景数据列表
    private RecSceneAdapter                 recSceneAdapter;
    private RecyclerView                    rec_scene;//当前家下场景图标
    private ImageView                       img_more;//设置更多
    private TabLayout                       mTablayout;//导航标签
    private MyFixedViewpager                mView_pager;//自我viewpager可实现禁止滑动
    private List<String>                    contsList;//tablayout的标题
    private ArrayList<DeviceListFragment>   fragmentsList;//fragment集合
    private MyPagerAdapter                  myPagerAdapter;//pager设配器
    private String                          hDefID;//记录默认家的id
    private String                          mHouseInfo;//家的信息
    private List<UserHomeInfo.HomeListBean> mHomeList;//家列表数据
    private int REQUEST_HOME_F           = 1003;//修改了家后的响应值
    private int REQUESTCODE_ROOM_MANAGER = 1004;//修改了房间后的响应值
    private String weatherString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.home_f, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_more = mRootView.findViewById(R.id.img_more);
        img_add = mRootView.findViewById(R.id.img_add);
        tv_mine = mRootView.findViewById(R.id.tv_mine);
        lin_weather = mRootView.findViewById(R.id.lin_weather);
        img_weather = mRootView.findViewById(R.id.img_weather);
        tv_weather = mRootView.findViewById(R.id.tv_weather);
        tv_temp = mRootView.findViewById(R.id.tv_temp);
        tv_pm2d5 = mRootView.findViewById(R.id.tv_pm2d5);
        tv_aqi = mRootView.findViewById(R.id.tv_aqi);
        lin_mine = mRootView.findViewById(R.id.lin_mine);
        rec_scene = mRootView.findViewById(R.id.rec_scene);
        mTablayout = mRootView.findViewById(R.id.tablayout);
        mView_pager = mRootView.findViewById(R.id.view_pager);
    }

    private void initData() {
        img_more.setOnClickListener(this);
        img_add.setOnClickListener(this);
        lin_weather.setOnClickListener(this);
        tv_mine.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        //获取天气信息
        //申请定位权限
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            Toast.makeText(getContext(), "未开启定位权限,不能获取到天气信息", Toast.LENGTH_LONG).show();
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOACTION);
        } else {
            getWeatherInfo();
        }

        //设置场景图标
        rec_scene.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mData = new ArrayList();
        recSceneAdapter = new RecSceneAdapter(getContext(), mData);
        rec_scene.setAdapter(recSceneAdapter);


        //房间和设备填充
        //        contsList = new ArrayList<>();
        //        contsList.add("所有设备");
        //        contsList.add("主卧");
        //        contsList.add("客厅");
        //        contsList.add("餐厅");
        //        contsList.add("次卧");
        //        contsList.add("书房");
        //        // 创建一个集合,装填Fragment
        //        fragmentsList = new ArrayList<>();
        //        for (int i = 0; i < contsList.size(); i++) {
        //            //设备列表界面
        //            DeviceListFragment deviceFragment = new DeviceListFragment();
        //            fragmentsList.add(deviceFragment);
        //        }
        //        // 创建ViewPager适配器
        //        myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        //        myPagerAdapter.setFragments(fragmentsList);
        //        // 给ViewPager设置适配器
        //        mView_pager.setAdapter(myPagerAdapter);
        //        //mView_pager.setOffscreenPageLimit(4);
        //        //设置viewpager不可滑动
        //        //mView_pager_space.setCanScroll(false);
        //        //tablayout关联tablayout和viewpager实现联动
        //        mTablayout.setupWithViewPager(mView_pager);
        //        for (int i = 0; i < contsList.size(); i++) {
        //            mTablayout.getTabAt(i).setText(contsList.get(i));
        //        }
        lin_mine.setOnClickListener(this);
        //获取账户下所有家数目
        getHomes();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_mine:
                //弹出popupwindow展示家列表
                openPopupWindow(tv_mine, mHomeList);
                break;
            case R.id.img_add://添加设备
                Intent intentAdd = new Intent(getContext(), AddDeviceActivity.class);
                intentAdd.putExtra("homeID", hDefID);
                intentAdd.putExtra("roomID", "all");
                intentAdd.putExtra("roomInfo", mHouseInfo);
                startActivity(intentAdd);
                break;
            case R.id.img_more:
                if (null == hDefID || "".equals(hDefID)) {
                    ToastUtils.showToast(getContext(), "请先创建家，再添加设备");
                    return;
                }
                //跳转房间管理界面
                Intent intent = new Intent(getContext(), RoomManagerActivity.class);
                intent.putExtra("homeID", hDefID);
                startActivityForResult(intent, REQUESTCODE_ROOM_MANAGER);
                break;
            case R.id.lin_weather://跳转天气详情界面
                if (null == weatherString || "".equals(weatherString)) {
                    ToastUtils.showToast(getContext(), "未获取到天气信息");
                    return;
                }
                Intent weatherIntent = new Intent(getContext(), WeatherDetailActivity.class);
                weatherIntent.putExtra("weatherInfo", weatherString);
                startActivity(weatherIntent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != popupWindow) {
            popupWindow.dismiss();
        }
        if (MyApplication.sceneRefresh) {
            getHomeScene(hDefID);
        }
    }

    public void getWeatherInfo() {
        Location location = LocationUtils.getInstance(getContext()).showLocation();
        String weatherUrl = "https://jingshangs.com/hky_JK/selecttemperature";
        RequestParamsFM params = new RequestParamsFM();
        //获取经纬度
        if (null != location) {
            params.put("latitude", location.getLatitude());
            params.put("longitude", location.getLongitude());
            LocationUtils.getInstance(getContext()).removeLocationUpdatesListener();
        } else {
            params.put("latitude", 31.896255);
            params.put("longitude", 121.182962);
        }
        params.setUseJsonStreamer(true);
        HttpOkhUtils.getInstance().doPostBean(weatherUrl, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ToastUtils.showToast(getContext(), "天气获取失败");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(getContext(), "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                WeatherInfo weatherInfo = gson.fromJson(resbody, WeatherInfo.class);
                ToastUtils.showToast(getContext(), weatherInfo.getMessage());
                if (1 == weatherInfo.getResult()) {
                    weatherString = resbody;
                    String weather = weatherInfo.getTemperaturelist().getWeather();
                    tv_weather.setText(weather);
                    if (weather.contains("雨")) {
                        img_weather.setImageResource(R.drawable.icon_rain);
                    } else if (weather.contains("雪")) {
                        img_weather.setImageResource(R.drawable.icon_xue);
                    } else if (weather.contains("阴")) {
                        img_weather.setImageResource(R.drawable.icon_yin);
                    } else if (weather.contains("多云")) {
                        img_weather.setImageResource(R.drawable.icon_yin);
                    } else {
                        img_weather.setImageResource(R.drawable.clear);
                    }
                    tv_temp.setText(weatherInfo.getTemperaturelist().getTemp());
                    tv_pm2d5.setText(weatherInfo.getTemperaturelist().getPm25());
                    tv_aqi.setText(weatherInfo.getTemperaturelist().getAqi_levnm());
                }
            }
        });
    }

    private int REQUEST_CODE_MOVE     = 1005;//刷新房间信息识别符号
    private int REQUEST_FINE_LOACTION = 2001;//申请定位权限的识别码

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_HOME_F) {//从家庭管理界面返回
            //获取账户下所有家数目
            getHomes();
        }
        if (requestCode == REQUESTCODE_ROOM_MANAGER) {//从房间管理界面返回
            //刷新房间信息
            showRoomsInfo(hDefID);
        }
        if (requestCode == REQUEST_CODE_MOVE) {
            //刷新房间信息
            showRoomsInfo(hDefID);
        }
    }

    //获取当前家下的，首页展示场景
    private void getHomeScene(String homeID) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("show_status", "1");
        params.put("home_id", homeID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.QUERYSCENELIST, params, new HttpOkhUtils.HttpCallBack() {
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
                SceneInfo sceneInfo = gson.fromJson(resbody, SceneInfo.class);
                ToastUtils.showToast(getContext(), sceneInfo.getMessage());
                if (1 == sceneInfo.getCode()) {
                    MyApplication.sceneRefresh = false;
                    mData.clear();
                    if (sceneInfo.getScenelist().size() > 0) {
                        mData.addAll(sceneInfo.getScenelist());
                    }
                    recSceneAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getHomes() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("register_id", MyApplication.userID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HOME, params, new HttpOkhUtils.HttpCallBack() {
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
                UserHomeInfo userHomeInfo = gson.fromJson(resbody, UserHomeInfo.class);
                if (1 == userHomeInfo.getCode()) {
                    if (userHomeInfo.getHomeList().size() > 0) {
                        if (null == mHomeList) {
                            mHomeList = new ArrayList();
                        } else {
                            mHomeList.clear();
                        }
                        boolean hDefault = false;
                        String deName = "";
                        //显示默认家庭，房间信息
                        for (UserHomeInfo.HomeListBean bean : userHomeInfo.getHomeList()) {//添加到家的列表
                            mHomeList.add(bean);
                            if ("1".equals(bean.getIsdefault())) {
                                hDefault = true;
                                hDefID = bean.getHome_id();
                                deName = bean.getHome_name();
                            }
                        }
                        if (!hDefault) {//没有设置默认家
                            hDefID = userHomeInfo.getHomeList().get(0).getHome_id();
                            deName = userHomeInfo.getHomeList().get(0).getHome_name();
                        }
                        //显示，显示的是哪个家
                        tv_mine.setText(deName);
                        if (null == hDefID || "".equals(hDefID)) {
                            hDefID = "";
                            ToastUtils.showToast(getContext(), "家信息查询失败");
                            return;
                        }
                        MyApplication.slecHomeID = hDefID;
                        getHomeScene(hDefID);
                        showRoomsInfo(hDefID);//展示房间信息
                    } else {//账号下没有家庭信息，让创建家庭
                        Intent intent = new Intent(getContext(), CreateHomeActivity.class);
                        startActivityForResult(intent, REQUEST_HOME_F);
                    }
                } else {
                    ToastUtils.showToast(getContext(), "家信息获取失败");
                }
            }
        });
    }

    private void showRoomsInfo(final String hDefID) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("home_id", hDefID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.HOUSE, params, new HttpOkhUtils.HttpCallBack() {
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
                mHouseInfo = resbody;
                Gson gson = new Gson();
                HouseDetailInfo houseDetailInfo = gson.fromJson(resbody, HouseDetailInfo.class);
                if (1 == houseDetailInfo.getCode()) {
                    ToastUtils.showToast(getContext(), "房间数查询成功");
                    if (houseDetailInfo.getHouseList().size() > 0) {//房间数大于1才刷新界面
                        if (null == fragmentsList) {
                            fragmentsList = new ArrayList<>();
                        } else {
                            fragmentsList.clear();
                        }
                        if (null == contsList) {
                            contsList = new ArrayList<>();
                        } else {
                            contsList.clear();
                        }
                        //添加所有设备界面
                        DeviceListFragment deviceFragmentAll = new DeviceListFragment();
                        deviceFragmentAll.setRoomID(hDefID, "all", "all");
                        deviceFragmentAll.setHouseInfo(resbody);
                        contsList.add("所有设备");
                        fragmentsList.add(deviceFragmentAll);
                        for (int i = 0; i < houseDetailInfo.getHouseList().size(); i++) {
                            //创建设备列表界面
                            DeviceListFragment deviceFragment = new DeviceListFragment();
                            deviceFragment.setRoomID(hDefID, houseDetailInfo.getHouseList().get(i).getId(), houseDetailInfo.getHouseList().get(i).getHouse_name());
                            deviceFragment.setHouseInfo(resbody);
                            contsList.add(houseDetailInfo.getHouseList().get(i).getHouse_name());
                            fragmentsList.add(deviceFragment);
                        }
                        //刷新界面
                        // 创建ViewPager适配器
                        myPagerAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());//getChildFragmentManager()
                        myPagerAdapter.setFragments(fragmentsList);
                        // 给ViewPager设置适配器
                        mView_pager.setAdapter(myPagerAdapter);
                        mTablayout.setupWithViewPager(mView_pager);
                        for (int i = 0; i < contsList.size(); i++) {
                            mTablayout.getTabAt(i).setText(contsList.get(i));
                        }
                        mTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                mView_pager.resetHeight(tab.getPosition());
                                fragmentsList.get(tab.getPosition()).refreshInfo();
                                MyApplication.slecRoomID = fragmentsList.get(tab.getPosition()).getRoomID();
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {
                                mView_pager.resetHeight(tab.getPosition());
                                fragmentsList.get(tab.getPosition()).refreshInfo();
                                MyApplication.slecRoomID = fragmentsList.get(tab.getPosition()).getRoomID();
                            }
                        });
                        mTablayout.getTabAt(0).select();
                        mView_pager.setCurrentItem(0);
                    }
                } else {
                    ToastUtils.showToast(getContext(), "房间查询失败");
                }
            }
        });
    }

    private PopupWindow popupWindow;

    private void openPopupWindow(View v, List<UserHomeInfo.HomeListBean> homeList) {
        //防止重复按按钮
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        //设置PopupWindow的View
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_popupwindow, null);
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
        popupWindow.showAtLocation(v, Gravity.TOP, 0, 0);
        //设置消失监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //设置背景色
                setBackgroundAlpha(1.0f);
            }
        });
        //设置PopupWindow的View点击事件
        setOnPopupViewClick(view, homeList);
        //设置背景色
        setBackgroundAlpha(0.5f);
    }

    private void setOnPopupViewClick(View view, List<UserHomeInfo.HomeListBean> homeList) {
        MyListView lv_cshome = view.findViewById(R.id.lv_cshome);
        RelativeLayout rtv_sthome = view.findViewById(R.id.rtv_sthome);
        LvSetHomeAdapter setHomeAdapter = new LvSetHomeAdapter(getContext(), homeList);
        lv_cshome.setAdapter(setHomeAdapter);
        lv_cshome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //勾选选中的家,更新下面的信息，并消失popupwindow
                showRoomsInfo(mHomeList.get(i).getHome_id());//展示房间信息
                MyApplication.slecHomeID = mHomeList.get(i).getHome_id();
                for (UserHomeInfo.HomeListBean bean : mHomeList) {//修改选中状态
                    bean.setIsdefault("0");
                }
                mHomeList.get(i).setIsdefault("1");
                popupWindow.dismiss();
                //显示，显示的是哪个家
                tv_mine.setText(mHomeList.get(i).getHome_name());
            }
        });
        rtv_sthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转家庭管理界面
                Intent intent = new Intent(getContext(), HomeListActivity.class);
                startActivityForResult(intent, REQUEST_HOME_F);
            }
        });
    }

    //设置屏幕背景透明效果
    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = ((Activity) getContext()).getWindow().getAttributes();
        lp.alpha = alpha;
        ((Activity) getContext()).getWindow().setAttributes(lp);
    }

    public void refreshDevFragment(int position) {
        mTablayout.getTabAt(position).select();
        mView_pager.setCurrentItem(position);
    }
}
