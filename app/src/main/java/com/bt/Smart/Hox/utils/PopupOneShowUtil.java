package com.bt.Smart.Hox.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/7 10:02
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class PopupOneShowUtil {
    private static PopViewClick mPopViewClick;
    private static PopupWindow  popupWindow;
    private static PopupBuilder popupBuilder;

    public static PopupBuilder getInstance() {
        if (popupBuilder == null) {
            synchronized (PopupBuilder.class) {
                if (popupBuilder == null)
                    popupBuilder = new PopupBuilder();
            }
        }
        return popupBuilder;
    }

    private static class PopupBuilder {
        private static Context mContext;
        private static View    mView;
        private static int     mLayout;

        public static PopupBuilder setInitData(final Context context, View v, int layout) {
            mContext = context;
            mView = v;
            mLayout = layout;
            return popupBuilder;
        }

        public static PopupBuilder initPopupWindow(boolean isFocusable, boolean isOutsideTouchable) {
            //防止重复按按钮
            if (popupWindow != null && popupWindow.isShowing()) {
                return popupBuilder;
            }
            //设置PopupWindow的View
            View view = LayoutInflater.from(mContext).inflate(mLayout, null);
            popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            //设置背景,这个没什么效果，不添加会报错
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            //设置点击弹窗外隐藏自身
            popupWindow.setFocusable(isFocusable);
            popupWindow.setOutsideTouchable(isOutsideTouchable);
            //设置动画
            //popupWindow.setAnimationStyle(R.style.PopupWindow);
            //设置消失监听
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    //设置背景色
                    setBackgroundAlpha(mContext, 1.0f);
                }
            });
            //设置PopupWindow的View点击事件
            setOnPopupViewClick(popupWindow, view);
            //设置背景色
            setBackgroundAlpha(mContext, 0.5f);
            return popupBuilder;
        }

        public static void showPopup(int posiotion) {//CENTER-17//BOTTOM-80//TOP-48//RIGHT-5//LEFT-3
            //设置位置
            popupWindow.showAtLocation(mView, posiotion, 0, 0);
        }

        public static void setOnPopupViewClick(PopupWindow popupWindow, View view) {
            mPopViewClick.onViewClickListener(popupWindow, view);
        }

        //设置屏幕背景透明效果
        private static void setBackgroundAlpha(Context context, float alpha) {
            WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
            lp.alpha = alpha;
            ((Activity) context).getWindow().setAttributes(lp);
        }
    }

    private interface PopViewClick {
        void onViewClickListener(PopupWindow popupWindow, View view);
    }
}
