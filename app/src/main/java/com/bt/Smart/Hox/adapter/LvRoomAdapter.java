package com.bt.Smart.Hox.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.bt.Smart.Hox.messegeInfo.HouseDetailInfo;
import com.bt.Smart.Hox.utils.HttpOkhUtils;
import com.bt.Smart.Hox.utils.ProgressDialogUtil;
import com.bt.Smart.Hox.utils.RequestParamsFM;
import com.bt.Smart.Hox.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

/**
 * @创建者 AndyYan
 * @创建时间 2018/8/27 10:07
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvRoomAdapter extends BaseAdapter {
    private Context                             mContext;
    private List<HouseDetailInfo.HouseListBean> mList;

    public LvRoomAdapter(Context context, List<HouseDetailInfo.HouseListBean> list) {
        this.mContext = context;
        this.mList = list;
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
            view = View.inflate(mContext, R.layout.adpter_dev_list, null);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.img_edit = view.findViewById(R.id.img_edit);
            viewholder.img_delet = view.findViewById(R.id.img_delet);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getHouse_name());
        viewholder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改房间名称
                openPopupWindow(viewholder.img_edit, i);
            }
        });
        viewholder.img_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRoom(i);
            }
        });
        return view;
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
        tv_title.setText("修改房间名称");
        tv_name.setText("房间名称:");
        et_name.setHint("请输入房间名称");
        et_name.setText(mList.get(position).getHouse_name());
        et_name.setSelection(mList.get(position).getHouse_name().length());
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//保存修改的家庭名称
                String roomName = String.valueOf(et_name.getText()).trim();
                if ("".equals(roomName) || "请输入房间名称".equals(roomName)) {
                    ToastUtils.showToast(mContext, "roomName");
                    return;
                }
                saveRoomNameInfo(roomName, position);
            }
        });
    }

    private AlertDialog alertDialog;

    private void deleteRoom(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("温馨提示");
        builder.setMessage("该操作会将该房间下的所有设备一起删除，确定删除？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //删除房间
                doDeleteRoom(position);
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

    private void doDeleteRoom(final int position) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("register_id", MyApplication.userID);
        params.put("house_id", mList.get(position).getId());
        HttpOkhUtils.getInstance().doDelete(NetConfig.HOUSE, params, new HttpOkhUtils.HttpCallBack() {
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
                    //关闭dailog
                    if (null != alertDialog) {
                        alertDialog.dismiss();
                    }
                    //修改界面
                    mList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    private void saveRoomNameInfo(final String roomName, final int position) {
        RequestParamsFM params = new RequestParamsFM();
        params.put("id", mList.get(position).getId());
        params.put("house_name", roomName);
        params.put("home_id", mList.get(position).getHome_id());
        HttpOkhUtils.getInstance().doPut(NetConfig.HOUSE, params, new HttpOkhUtils.HttpCallBack() {
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
                    //关闭popupwindow
                    if (null != popupWindow)
                        popupWindow.dismiss();
                    //修改界面
                    mList.get(position).setHouse_name(roomName);
                    notifyDataSetChanged();
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

    private class MyViewholder {
        TextView  tv_name;
        ImageView img_delet, img_edit;
    }
}
