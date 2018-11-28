package com.bt.Smart.Hox.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.GesturePassWordActivity;
import com.bt.Smart.Hox.activity.LoginActivity;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.UserDetailInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.MyCloseKeyBoardUtil;
import com.bt.Smart.Hox.utils.PopupOpenHelper;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.SpUtils;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;
import com.nanchen.compresshelper.CompressHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.Request;

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
    private ImageView      img_head;
    private LinearLayout   lin_name;
    private TextView       tv_name;
    private TextView       tv_phone;
    private TextView       tv_exit;
    private Switch         swc_gesture;
    private RelativeLayout rlt_change_gesture;
    private ImageView      img_changepsd;//修改登录密码
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 10087;//相册权限申请码
    private int IMAGE                              = 10086;//调用相册requestcode
    private int SHOT_CODE                          = 20;//调用系统相册-选择图片
    private String mFilePath;//拍照记录的uri地址
    private String mImgUrl;//上传图片后，服务器返回的url
    private String mNickName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_person_center, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        img_head = mRootView.findViewById(R.id.img_head);
        img_changepsd = mRootView.findViewById(R.id.img_changepsd);
        tv_title = mRootView.findViewById(R.id.tv_title);
        lin_name = mRootView.findViewById(R.id.lin_name);
        tv_name = mRootView.findViewById(R.id.tv_name);
        tv_phone = mRootView.findViewById(R.id.tv_phone);
        tv_exit = mRootView.findViewById(R.id.tv_exit);
        swc_gesture = mRootView.findViewById(R.id.swc_gesture);
        rlt_change_gesture = mRootView.findViewById(R.id.rlt_change_gesture);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("个人中心");
        mImgUrl = MyApplication.userHeadPic;
        mNickName = MyApplication.userName;
        GlideLoaderUtil.showImgWithIcon(getContext(), MyApplication.userHeadPic, R.drawable.iman, R.drawable.iman, img_head);
        tv_name.setText(mNickName);
        tv_phone.setText(MyApplication.userPhone);

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

        img_head.setOnClickListener(this);
        lin_name.setOnClickListener(this);
        rlt_change_gesture.setOnClickListener(this);
        img_changepsd.setOnClickListener(this);
        tv_exit.setOnClickListener(this);
        //获取个人信息
        //        getPersonalInfo();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                getActivity().finish();
                break;
            case R.id.img_head://修改头像
                MyCloseKeyBoardUtil.hintKeyBoard(getActivity());
                sendPersonalHeadImg();
                break;
            case R.id.lin_name://设置昵称
                openPopupWindow(tv_name);
                break;
            case R.id.rlt_change_gesture://修改手势密码
                //先弹出窗口，提示输入账户密码
                showDialogForPassWord();
                break;
            case R.id.img_changepsd://修改登录密码
                changePassWord();
                break;
            case R.id.tv_exit://退出登录
                exitLogin();
                break;
        }
    }

    private void changePassWord() {
        FragmentTransaction ftt = getFragmentManager().beginTransaction();
        ChangePassWordFragment changePassWordFragment = new ChangePassWordFragment();
        ftt.add(R.id.frame, changePassWordFragment, "changePassWordFragment");
        ftt.addToBackStack(null);
        ftt.commit();
    }

    private void openPopupWindow(TextView tv_name) {
        final PopupOpenHelper openHelper = new PopupOpenHelper(getContext(), tv_name, R.layout.popup_change_home_name);
        openHelper.openPopupWindow(true, Gravity.CENTER);
        openHelper.setOnPopupViewClick(new PopupOpenHelper.ViewClickListener() {
            @Override
            public void onViewClickListener(PopupWindow popupWindow, View inflateView) {
                TextView tv_title = inflateView.findViewById(R.id.tv_title);
                TextView tv_name = inflateView.findViewById(R.id.tv_name);
                final EditText et_name = inflateView.findViewById(R.id.et_name);
                TextView tv_cancle = inflateView.findViewById(R.id.tv_cancle);
                TextView tv_save = inflateView.findViewById(R.id.tv_save);
                tv_title.setText("修改昵称");
                tv_name.setText("昵称");
                et_name.setHint("请输入昵称");
                tv_cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openHelper.dismiss();
                    }
                });
                tv_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = String.valueOf(et_name.getText()).trim();
                        if ("".equals(name) || "请输入昵称".equals(name)) {
                            ToastUtils.showToast(getContext(), "请输入昵称");
                            return;
                        }
                        mNickName = name;
                        upDateUserInfo();
                        openHelper.dismiss();
                    }
                });
            }
        });
    }

    private void exitLogin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("温馨提示");
        builder.setMessage("您确定要退出当前登录帐号吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SpUtils.putBoolean(getContext(), "isRem", false);
                MyApplication.isLogin = 0;
                Intent intent = new Intent();
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
                ((Activity) getContext()).finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
    }

    private void getPersonalInfo() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("registerid", MyApplication.userID);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.REGISTERINFO, params, new HttpOkhUtils.HttpCallBack() {
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
                UserDetailInfo userDetailInfo = gson.fromJson(resbody, UserDetailInfo.class);
                ToastUtils.showToast(getContext(), userDetailInfo.getMessage());
                if (1 == userDetailInfo.getCode()) {

                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //相册返回，获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
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
        GlideLoaderUtil.showImageView(getContext(), imgPath, img_head);
        //压缩图片
        File file = new File(imgPath);
        if (null != file) {
            File newFile = new CompressHelper.Builder(getContext())
                    .setMaxWidth(720)  // 默认最大宽度为720
                    .setMaxHeight(960) // 默认最大高度为960
                    .setQuality(40)    // 默认压缩质量为80
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
            ToastUtils.showToast(getContext(), "未获取到源文件，请查看原图片是否存在");
        }
    }

    private void sendImgToService(Bitmap bm) {
        String strByBase64 = Bitmap2StrByBase64(bm);
        RequestParamsFM params = new RequestParamsFM();
        params.put("imgStr", strByBase64);
        HttpOkhUtils.getInstance().doGetWithParams(NetConfig.UPLOADBASE64ANDROID, params, new HttpOkhUtils.HttpCallBack() {
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
                if (1 == commonInfo.getResult()) {
                    ToastUtils.showToast(getContext(), "上传成功");
                    mImgUrl = NetConfig.IMG_HEAD_IP + commonInfo.getFileName();
                    //更新用户头像
                    upDateUserInfo();
                } else {
                    ToastUtils.showToast(getContext(), "上传失败");
                }
            }
        });
    }

    private void upDateUserInfo() {
        RequestParamsFM params = new RequestParamsFM();
        params.put("mobile", MyApplication.userPhone);
        params.put("head_pic", mImgUrl);
        params.put("fnickname", mNickName);
        HttpOkhUtils.getInstance().doPostBeanToString(NetConfig.UPDATEPC, params, new HttpOkhUtils.HttpCallBack() {
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
                    GlideLoaderUtil.showImgWithIcon(getContext(), mImgUrl, R.drawable.iman, R.drawable.iman, img_head);
                    tv_name.setText(mNickName);
                    MyApplication.userName = mNickName;
                    MyApplication.userHeadPic = mImgUrl;
                }
            }
        });
    }

    private void sendPersonalHeadImg() {
        //弹出popupwindow选择拍照还是上传图片
        final PopupOpenHelper openHelper = new PopupOpenHelper(getContext(), img_head, R.layout.popup_choice_pic_photo);
        openHelper.openPopupWindow(true, Gravity.BOTTOM);
        openHelper.setOnPopupViewClick(new PopupOpenHelper.ViewClickListener() {
            @Override
            public void onViewClickListener(final PopupWindow popupWindow, View inflateView) {
                TextView tv_xc = inflateView.findViewById(R.id.tv_xc);
                TextView tv_pz = inflateView.findViewById(R.id.tv_pz);
                TextView tv_cancel = inflateView.findViewById(R.id.tv_cancel);
                tv_xc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //第二个参数是需要申请的权限
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //权限还没有授予，需要在这里写申请权限的代码
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);
                        } else {
                            //权限已经被授予，在这里直接写要执行的相应方法即可
                            //调用相册
                            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            (getActivity()).startActivityForResult(intent, IMAGE);
                            openHelper.dismiss();
                        }
                    }
                });
                tv_pz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //第二个参数是需要申请的权限
                        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            //权限还没有授予，需要在这里写申请权限的代码
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
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
                            (PersonCenterFragment.this).setPtRote(mFilePath);
                            (getActivity()).startActivityForResult(intent, SHOT_CODE);
                            popupWindow.dismiss();
                        }
                    }
                });
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openHelper.dismiss();
                    }
                });
            }
        });
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
                    if (et_pass.isFocused()) {
                        et_pass.setFocusable(false);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    } else {
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

    public void setPtRote(String filePath) {
        mFilePath = filePath;
    }

    public String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 30, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        //        return Base64.encodeToString(bytes, Base64.DEFAULT);
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }
}
