package com.bt.Smart.Hox.activity.meActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
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
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.adapter.LvMemberAdapter;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.HomeDetailInfo;
import com.bt.Smart.Hox.messegeInfo.HomeMembersInfo;
import com.bt.Smart.Hox.util.GetJsonDataUtil;
import com.bt.Smart.Hox.util.GlideLoaderUtil;
import com.bt.Smart.Hox.util.JsonBean;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.MyCloseKeyBoardUtil;
import com.bt.Smart.Hox.utils.PopupOpenHelper;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;
import com.nanchen.compresshelper.CompressHelper;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private TextView                           tv_save;
    private ImageView                          img_head;//家庭照片
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
    private int REQUEST_ADD_MEMBER    = 1002;//请求返回值
    private int REQUEST_DELETE_MEMBER = 1003;//请求返回值

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
        tv_save = (TextView) findViewById(R.id.tv_save);
        img_head = (ImageView) findViewById(R.id.img_head);
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
        tv_save.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_save.setOnClickListener(this);
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
                    intent.putExtra("memberID", mData.get(i).getId());//成员id
                    intent.putExtra("homeID", homeID);//家id
                    intent.putExtra("name", mData.get(i).getFname());//成员名称
                    intent.putExtra("phone", mData.get(i).getFtelephone());//成员电话
                    intent.putExtra("headPic", mData.get(i).getHead_pic());//成员头像
                    startActivityForResult(intent, REQUEST_DELETE_MEMBER);
                }
            }
        });
        //获取家庭信息
        getHomeDetailInfo();
        //获取家庭成员信息
        getHomeMembers();
        //开始解析地址数据
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
        img_head.setOnClickListener(this);
        rtv_name.setOnClickListener(this);
        rtv_address.setOnClickListener(this);
        lin_add.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                String homeName = String.valueOf(tv_name.getText()).trim();
                String address = String.valueOf(tv_address.getText()).trim();
                if ("".equals(homeName) || "请输入家庭名称".equals(homeName)) {
                    ToastUtils.showToast(HomeDetailActivity.this, "家名称不能为空");
                    return;
                }
                if ("".equals(address) || "请选择家庭地址".equals(address)) {
                    ToastUtils.showToast(HomeDetailActivity.this, "家地址不能为空");
                    return;
                }
                saveHomeName(homeName, address);
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.img_head://更改家庭照片
                MyCloseKeyBoardUtil.hintKeyBoard(this);
                sendHomeHeadImg();
                break;
            case R.id.rtv_name://更改家庭名称
                openPopupWindow(rtv_name);
                break;
            case R.id.rtv_address://更改地址
                openAddressPopupWindow(rtv_address);
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
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_ADD_MEMBER == requestCode) {
            MyCloseKeyBoardUtil.hintKeyBoard(this);
            //获取家庭成员信息
            getHomeMembers();
        }
        if (REQUEST_DELETE_MEMBER == requestCode) {
            //获取家庭成员信息
            getHomeMembers();
        }
        //相册返回，获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
        if (requestCode == SHOT_CODE && resultCode == Activity.RESULT_OK) {
            showImage(mFilePath);
        }
    }

    //加载图片
    private void showImage(String imgPath) {
        GlideLoaderUtil.showImageView(HomeDetailActivity.this, imgPath, img_head);
        //        Bitmap bm = BitmapFactory.decodeFile(imgPath);
        //添加到bitmap集合中
        //        mBitmapList.add(bm);
        //上传图片
        //压缩图片
        File file = new File(imgPath);
        if (null != file) {
            File newFile = new CompressHelper.Builder(this)
                    .setMaxWidth(720)  // 默认最大宽度为720
                    .setMaxHeight(960) // 默认最大高度为960
                    .setQuality(100)    // 默认压缩质量为80
                    .setFileName("sendPic") // 设置你需要修改的文件名
                    .setCompressFormat(Bitmap.CompressFormat.JPEG) // 设置默认压缩为jpg格式
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(file);
            Bitmap bm = BitmapFactory.decodeFile(newFile.getPath());
            //上传图片
            sendImgToService(bm);
        } else {
            ToastUtils.showToast(this, "未获取到源文件，请查看原图片是否存在");
        }
    }

    private void sendImgToService(Bitmap bm) {
        String strByBase64 = Bitmap2StrByBase64(bm).replace("/", "%2F").replace("+", "%2B");
        RequestParamsFM params = new RequestParamsFM();
        params.put("imgStr", strByBase64);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.UPLOADBASE64ANDROID, params, new HttpOkhUtils.HttpCallBack() {
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
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                if (1 == commonInfo.getResult()) {
                    ToastUtils.showToast(HomeDetailActivity.this, "上传成功");
                    mImgUrl = NetConfig.IMG_HEAD_IP + commonInfo.getFileName();
                } else {
                    ToastUtils.showToast(HomeDetailActivity.this, "上传失败");
                }
            }
        });
    }

    public String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 10087;//相册权限申请码
    private int IMAGE                              = 10086;//调用相册requestcode
    private int SHOT_CODE                          = 20;//调用系统相册-选择图片
    private String mFilePath;//拍照记录的uri地址
    private String mImgUrl = "aa.jpg";//上传图片后，服务器返回的url

    private void sendHomeHeadImg() {
        //弹出popupwindow选择拍照还是上传图片
        final PopupOpenHelper openHelper = new PopupOpenHelper(this, img_head, R.layout.popup_choice_pic_photo);
        openHelper.openPopupWindow(true, Gravity.BOTTOM);
        openHelper.setOnPopupViewClick(new PopupOpenHelper.ViewClickListener() {
            @Override
            public void onViewClickListener(final PopupWindow popupWindow, View inflateView) {
                TextView tv_xc = inflateView.findViewById(R.id.tv_xc);
                TextView tv_pz = inflateView.findViewById(R.id.tv_pz);
                tv_xc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //第二个参数是需要申请的权限
                        if (ContextCompat.checkSelfPermission(HomeDetailActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //权限还没有授予，需要在这里写申请权限的代码
                            ActivityCompat.requestPermissions(HomeDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                        } else {
                            //权限已经被授予，在这里直接写要执行的相应方法即可
                            //调用相册
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            (HomeDetailActivity.this).startActivityForResult(intent, IMAGE);
                            openHelper.dismiss();
                        }
                    }
                });
                tv_pz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //第二个参数是需要申请的权限
                        if (ContextCompat.checkSelfPermission(HomeDetailActivity.this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            //权限还没有授予，需要在这里写申请权限的代码
                            ActivityCompat.requestPermissions(HomeDetailActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                        } else {
                            String mFilePath = Environment.getExternalStorageDirectory().getPath();//获取SD卡路径
                            long photoTime = System.currentTimeMillis();
                            mFilePath = mFilePath + "/temp" + photoTime + ".jpg";//指定路径
                            //权限已经被授予，在这里直接写要执行的相应方法即可
                            //调用相机
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            Uri photoUri = Uri.fromFile(new File(mFilePath)); // 传递路径
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);// 更改系统默认存储路径
                            //把指定路径传递给需保存的字段
                            (HomeDetailActivity.this).setPtRote(mFilePath);
                            (HomeDetailActivity.this).startActivityForResult(intent, SHOT_CODE);
                            popupWindow.dismiss();
                        }
                    }
                });
            }
        });
    }

    public void setPtRote(String filePath) {
        mFilePath = filePath;
    }

    private void openAddressPopupWindow(RelativeLayout rtv_address) {
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
        //设置点击弹窗外不隐藏自身
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        //设置动画
        // popupWindow.setAnimationStyle(R.style.PopupWindow);
        //设置位置
        popupWindow.showAtLocation(rtv_address, Gravity.CENTER, 0, 0);
        //设置消失监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //设置背景色
                setBackgroundAlpha(1.0f);
            }
        });
        //设置PopupWindow的View点击事件
        setOnAdPopupViewClick(view);
        //设置背景色
        setBackgroundAlpha(0.5f);
    }

    private void setOnAdPopupViewClick(View view) {
        TextView tv_title = view.findViewById(R.id.tv_title);
        final EditText et_name1 = view.findViewById(R.id.et_name);
        final TextView tv_opname = view.findViewById(R.id.tv_name);
        final TextView tv_chname = view.findViewById(R.id.tv_chname);//家地址
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_save = view.findViewById(R.id.tv_save);
        tv_title.setText("修改家地址");
        tv_opname.setText("家地址:");
        et_name1.setVisibility(View.GONE);
        tv_chname.setVisibility(View.VISIBLE);
        tv_chname.setText(tv_address.getText());
        tv_chname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //弹出地址选择器
                if (isLoaded) {
                    showPickerView(tv_chname);
                } else {
                    Toast.makeText(HomeDetailActivity.this, "Please waiting until the data is parsed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//保存修改的家庭名称
                String homeName = String.valueOf(tv_name.getText()).trim();
                if ("".equals(tv_chname) || "请选择家地址".equals(tv_chname)) {
                    ToastUtils.showToast(HomeDetailActivity.this, "请选择家地址");
                    return;
                }
                String address = String.valueOf(tv_chname.getText()).trim();
                saveHomeName(homeName, address);
            }
        });
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
                Toast.makeText(HomeDetailActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })
                .setTitleText("城市选择")
                .setBgColor(getResources().getColor(R.color.white))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private boolean isLoaded = false;
    private Thread thread;
    private static final int                                     MSG_LOAD_DATA    = 0x0001;
    private static final int                                     MSG_LOAD_SUCCESS = 0x0002;
    private static final int                                     MSG_LOAD_FAILED  = 0x0003;
    private              Handler                                 mHandler         = new Handler() {
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
                    Toast.makeText(HomeDetailActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private              ArrayList<JsonBean>                     options1Items    = new ArrayList<>();
    private              ArrayList<ArrayList<String>>            options2Items    = new ArrayList<>();
    private              ArrayList<ArrayList<ArrayList<String>>> options3Items    = new ArrayList<>();

    private void initJsonData() {//解析数据
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
        et_name.setText(tv_name.getText());
        et_name.setSelection(tv_name.getText().length());
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
                if ("".equals(homeName) || "请输入家名称".equals(homeName)) {
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
        params.put("home_pic", mImgUrl);
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
                    if (null != popupWindow)
                        popupWindow.dismiss();
                    tv_name.setText(homeName);
                } else {
                    ToastUtils.showToast(HomeDetailActivity.this, "更新失败");
                }
            }
        });
    }

    private MyAlertDialogHelper openHelper;

    private void deleteHome() {
        openHelper = new MyAlertDialogHelper();
        View view = View.inflate(this, R.layout.dialog_input_pass, null);
        openHelper.setDIYView(this, view);
        openHelper.show();
        final EditText et_pass = view.findViewById(R.id.et_pass);
        TextView tv_warning = view.findViewById(R.id.tv_warning);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_sure = view.findViewById(R.id.tv_sure);
        tv_warning.setText("该操作会将该家庭下的所有设备一起删除！");
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHelper.disMiss();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先比对下账户密码
                String pass = String.valueOf(et_pass.getText()).trim();
                if ("".equals(pass) || "请输入账户密码予以删除".equals(pass)) {
                    ToastUtils.showToast(HomeDetailActivity.this, "密码不能为空");
                    return;
                }
                if (pass.equals(MyApplication.pasword)) {
                    //删除家
                    doDeleteHome();
                    openHelper.disMiss();
                } else {
                    ToastUtils.showToast(HomeDetailActivity.this, "密码错误");
                    return;
                }
            }
        });
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
                        mImgUrl = homeDetailInfo.getHome().getHome_pic();
                        GlideLoaderUtil.showImgWithIcon(HomeDetailActivity.this, mImgUrl, R.drawable.iman, R.drawable.iman, img_head);
                    }
                } else {
                    ToastUtils.showToast(HomeDetailActivity.this, "家庭信息查询失败");
                }
            }
        });
    }
}
