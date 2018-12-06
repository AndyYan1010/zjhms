package com.bt.Smart.Hox.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bt.Smart.Hox.MyApplication;
import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.CommonInfo;
import com.bt.Smart.Hox.messegeInfo.CongKongListInfo;
import com.bt.Smart.Hox.messegeInfo.DeviceTypeAllInfo;
import com.bt.Smart.Hox.messegeInfo.DeviceTypeListInfo;
import com.bt.Smart.Hox.messegeInfo.HouseDeviceInfo;
import com.bt.Smart.Hox.messegeInfo.ZhuKongListInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.MyAlertDialogHelper;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/5/27 19:34
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvDevListAdapter extends BaseAdapter {
    private Context mContext;
    private List    mList;
    private String  mKind;//设备类别
    private String  mRoomID;

    public LvDevListAdapter(Context context, List list, String kind, String roomID) {
        this.mContext = context;
        this.mList = list;
        this.mKind = kind;
        this.mRoomID = roomID;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MyViewholder viewholder;
        if (null == view) {
            viewholder = new MyViewholder();
            view = View.inflate(mContext, R.layout.adpter_add_dev_list, null);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.img_kind = view.findViewById(R.id.img_kind);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        if ("zk".equals(mKind) || "wf".equals(mKind)) {
            viewholder.tv_name.setText(((DeviceTypeListInfo.DeviceTypeListBean) mList.get(i)).getDeviceDescibe());
            GlideLoaderUtil.showImageView(mContext, NetConfig.IMG_FOR_DEV + ((DeviceTypeListInfo.DeviceTypeListBean) mList.get(i)).getDevcieTypePic().replaceAll("\\\\", "/"), viewholder.img_kind);
        } else {
            viewholder.tv_name.setText(((DeviceTypeAllInfo.DeviceTypeListBean.DataBean) mList.get(i)).getDeviceDescibe());
            GlideLoaderUtil.showImageView(mContext, NetConfig.IMG_FOR_DEV + ((DeviceTypeAllInfo.DeviceTypeListBean.DataBean) mList.get(i)).getDevcieTypePic().replaceAll("\\\\", "/"), viewholder.img_kind);

        }

        //        if ("zk".equals(mKind)) {
        //            viewholder.tv_name.setText(((ZhuKongListInfo.HomeListBean) mList.get(i)).getMain_control_name());//
        //        } else if ("ck".equals(mKind)) {
        //            viewholder.tv_name.setText(((CongKongListInfo.SecondControlListBean) mList.get(i)).getSecond_control_name());//
        //        } else {//DeviceHouseListBean
        //            viewholder.tv_name.setText(((HouseDeviceInfo.DeviceHouseListBean) mList.get(i)).getDevice_name());//
        //        }

        //        viewholder.img_edit.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {//修改主控、从控、设备名称
        //                //弹出popupwindow
        //                openPopupWindow(viewholder.img_edit, i);
        //            }
        //        });
        //        viewholder.img_delet.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {//删除主控、从控、设备
        //                //弹出dailog提示
        //                deleteWarnning(i);
        //            }
        //        });
        return view;
    }

    private MyAlertDialogHelper dialogHelper;

    private void deleteWarnning(final int position) {
        dialogHelper = new MyAlertDialogHelper();
        View view = View.inflate(mContext, R.layout.dialog_input_pass, null);
        dialogHelper.setDIYView(mContext, view);
        dialogHelper.show();
        final EditText et_pass = view.findViewById(R.id.et_pass);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_sure = view.findViewById(R.id.tv_sure);
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
                if ("".equals(pass) || "请输入账户密码予以删除".equals(pass)) {
                    ToastUtils.showToast(mContext, "密码不能为空");
                    return;
                }
                if (pass.equals(MyApplication.pasword)) {
                    //删除主控、从控、设备
                    if ("zk".equals(mKind)) {
                        doDeleteZKDev(position);
                    } else if ("wf".equals(mKind)) {
                        doDeleteCKDev(position);
                    } else {
                        doDeleteSBDev(position);
                    }
                } else {
                    ToastUtils.showToast(mContext, "密码错误");
                    return;
                }
            }
        });

        //        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        //        builder.setTitle("温馨提示");
        //        builder.setMessage("您确定删除该设备？");
        //        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        //            @Override
        //            public void onClick(DialogInterface dialog, int which) {
        //                //删除主控、从控、设备
        //                if ("zk".equals(mKind)) {
        //                    doDeleteZKDev(position);
        //                } else if ("ck".equals(mKind)) {
        //                    doDeleteCKDev(position);
        //                } else {
        //                    doDeleteSBDev(position);
        //                }
        //            }
        //        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
        //            @Override
        //            public void onClick(DialogInterface dialog, int which) {
        //                dialog.cancel();
        //            }
        //        });
        //        alertDialog = builder.create();
        //        alertDialog.show();
    }

    private void doDeleteSBDev(final int position) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", ((HouseDeviceInfo.DeviceHouseListBean) mList.get(position)).getId());
        params.put("register_id", MyApplication.userID);
        params.put("home_id", ((HouseDeviceInfo.DeviceHouseListBean) mList.get(position)).getHome_id());
        params.put("device_type", ((HouseDeviceInfo.DeviceHouseListBean) mList.get(position)).getDeviceType());
        params.put("second_control_id", ((HouseDeviceInfo.DeviceHouseListBean) mList.get(position)).getSecond_control_id());
        HttpOkhUtils.getInstance().doDelete(NetConfig.DEVICE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(mContext, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(mContext, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(mContext, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    //                    alertDialog.dismiss();
                    dialogHelper.disMiss();
                    mList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    private void doDeleteCKDev(final int position) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", ((CongKongListInfo.SecondControlListBean) mList.get(position)).getId());
        params.put("register_id", MyApplication.userID);
        params.put("home_id", ((CongKongListInfo.SecondControlListBean) mList.get(position)).getHome_id());
        HttpOkhUtils.getInstance().doDelete(NetConfig.SECONDCONTROL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(mContext, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(mContext, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(mContext, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    //                    alertDialog.dismiss();
                    dialogHelper.disMiss();
                    mList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    private void doDeleteZKDev(final int position) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", ((ZhuKongListInfo.HomeListBean) mList.get(position)).getId());
        params.put("main_control_code", ((ZhuKongListInfo.HomeListBean) mList.get(position)).getMain_control_code());
        params.put("register_id", MyApplication.userID);
        params.put("home_id", ((ZhuKongListInfo.HomeListBean) mList.get(position)).getHome_id());
        HttpOkhUtils.getInstance().doDelete(NetConfig.MAINCONTROL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(mContext, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(mContext, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(mContext, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    //                    alertDialog.dismiss();
                    dialogHelper.disMiss();
                    mList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    private PopupWindow popupWindow;

    private void openPopupWindow(View v, int position) {
        //防止重复按按钮
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        //设置PopupWindow的View
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_change_home_name, null);
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
        setOnPopupViewClick(view, position);
        //设置背景色
        setBackgroundAlpha(0.5f);
    }

    private void setOnPopupViewClick(View view, final int position) {
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_name = view.findViewById(R.id.tv_name);
        final EditText et_name = view.findViewById(R.id.et_name);
        TextView tv_cancle = view.findViewById(R.id.tv_cancle);
        TextView tv_save = view.findViewById(R.id.tv_save);
        //初始化数据
        tv_title.setText("修改名称");
        tv_name.setText("名称:");
        et_name.setHint("请输入名称");
        et_name.setText(((ZhuKongListInfo.HomeListBean) mList.get(position)).getMain_control_name());
        et_name.setSelection(((ZhuKongListInfo.HomeListBean) mList.get(position)).getMain_control_name().length());
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//保存修改的家庭名称
                String newName = String.valueOf(et_name.getText()).trim();
                if ("".equals(newName) || "请输入名称".equals(newName)) {
                    ToastUtils.showToast(mContext, "请输入名称");
                    return;
                }
                if ("zk".equals(mKind)) {
                    editZK(position, newName);
                } else if ("wf".equals(mKind)) {
                    editCK(position, newName);
                } else {
                    editSB(position, newName);
                }
            }
        });
    }

    //设置屏幕背景透明效果
    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = alpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    private void editSB(final int position, final String newName) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", ((HouseDeviceInfo.DeviceHouseListBean) mList.get(position)).getId());
        params.put("device_name", newName);
        params.put("house_id", mRoomID);
        HttpOkhUtils.getInstance().doPut(NetConfig.DEVICE, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(mContext, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(mContext, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(mContext, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    popupWindow.dismiss();
                    ((HouseDeviceInfo.DeviceHouseListBean) mList.get(position)).setDevice_name(newName);
                    notifyDataSetChanged();
                }
            }
        });
    }

    private void editCK(final int position, final String newName) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", ((CongKongListInfo.SecondControlListBean) mList.get(position)).getId());
        params.put("second_control_name", newName);
        HttpOkhUtils.getInstance().doPut(NetConfig.SECONDCONTROL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(mContext, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(mContext, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(mContext, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    popupWindow.dismiss();
                    ((CongKongListInfo.SecondControlListBean) mList.get(position)).setSecond_control_name(newName);
                    notifyDataSetChanged();
                }
            }
        });
    }

    private void editZK(final int position, final String newName) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", ((ZhuKongListInfo.HomeListBean) mList.get(position)).getId());
        params.put("main_control_name", newName);
        HttpOkhUtils.getInstance().doPut(NetConfig.MAINCONTROL, params, new HttpOkhUtils.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                ProgressDialogUtil.hideDialog();
                ToastUtils.showToast(mContext, "网络连接错误");
            }

            @Override
            public void onSuccess(int code, String resbody) {
                ProgressDialogUtil.hideDialog();
                if (code != 200) {
                    ToastUtils.showToast(mContext, "网络错误" + code);
                    return;
                }
                Gson gson = new Gson();
                CommonInfo commonInfo = gson.fromJson(resbody, CommonInfo.class);
                ToastUtils.showToast(mContext, commonInfo.getMessage());
                if (1 == commonInfo.getCode()) {
                    popupWindow.dismiss();
                    ((ZhuKongListInfo.HomeListBean) mList.get(position)).setMain_control_name(newName);
                    notifyDataSetChanged();
                }
            }
        });
    }

    private class MyViewholder {
        TextView  tv_name;
        ImageView img_kind;
    }
}
