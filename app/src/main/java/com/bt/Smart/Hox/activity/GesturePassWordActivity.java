package com.bt.Smart.Hox.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.SpUtils;
import com.syd.oden.gesturelock.view.GestureLockViewGroup;
import com.syd.oden.gesturelock.view.listener.GestureEventListener;
import com.syd.oden.gesturelock.view.listener.GesturePasswordSettingListener;
import com.syd.oden.gesturelock.view.listener.GestureUnmatchedExceedListener;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/16 9:26
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class GesturePassWordActivity extends BaseActivity {
    private GestureLockViewGroup mGestureLockViewGroup;
    private TextView             tv_state, tv_top;
    private boolean isReset;
    private String  mKind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_pass);
        setView();
        setData();
    }

    private void setView() {
        tv_top = (TextView) findViewById(R.id.tv_top);
        tv_state = (TextView) findViewById(R.id.tv_state);
        mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.gesturelock);
    }

    private void setData() {
        mKind = getIntent().getStringExtra("gseture_kind");
        if ("0".equals(mKind)) {
            //设置手势密码
            setGesPass();
        } else if ("1".equals(mKind)) {
            //输入手势密码
            inputGesturePass();
        }
    }

    private void inputGesturePass() {
        gestureEventListener();//手势密码监听事件
        gestureRetryLimitListener();//重试次数超过限制监听
    }

    private void setGesPass() {
        if (mGestureLockViewGroup.isSetPassword()) {
            mGestureLockViewGroup.removePassword();
            isReset = true;
        }
        gesturePasswordSettingListener();//手势密码设置
    }

    //手势密码监听事件
    private void gestureEventListener() {
        mGestureLockViewGroup.setGestureEventListener(new GestureEventListener() {
            @Override
            public void onGestureEvent(boolean matched) {
                if (!matched) {
                    tv_state.setTextColor(Color.RED);
                    tv_state.setText("手势密码错误");
                } else {
                    if (isReset) {//已设置过密码
                        isReset = false;
                        Toast.makeText(GesturePassWordActivity.this, "清除成功!", Toast.LENGTH_SHORT).show();
                        //清楚原手势密码
                        resetGesturePattern();
                    } else {
                        tv_state.setTextColor(Color.WHITE);
                        tv_state.setText("手势密码正确");
                    }
                }
            }
        });
    }

    //手势密码设置
    private void gesturePasswordSettingListener() {
        mGestureLockViewGroup.setGesturePasswordSettingListener(new GesturePasswordSettingListener() {
            @Override
            public boolean onFirstInputComplete(int len) {
                if (len > 3) {
                    tv_state.setTextColor(Color.BLUE);
                    tv_state.setText("再次绘制手势密码");
                    return true;
                } else {
                    tv_state.setTextColor(Color.RED);
                    tv_state.setText("最少连接4个点，请重新输入!");
                    return false;
                }
            }

            @Override
            public void onSuccess() {
                tv_state.setTextColor(Color.BLUE);
                String password = mGestureLockViewGroup.getPassword();
                SpUtils.putString(GesturePassWordActivity.this,"hasGes","1");
                Toast.makeText(GesturePassWordActivity.this, "密码设置成功!" + password, Toast.LENGTH_SHORT).show();
                tv_state.setText("密码设置成功");
                finish();
            }

            @Override
            public void onFail() {
                tv_state.setTextColor(Color.RED);
                tv_state.setText("与上一次绘制不一致，请重新绘制");
            }
        });
    }

    //重试次数超过限制监听
    private void gestureRetryLimitListener() {
        mGestureLockViewGroup.setGestureUnmatchedExceedListener(5, new GestureUnmatchedExceedListener() {
            @Override
            public void onUnmatchedExceedBoundary() {
                tv_state.setTextColor(Color.RED);
                tv_state.setText("错误次数过多，请稍后再试!");
            }
        });
    }

    private void resetGesturePattern() {
        mGestureLockViewGroup.removePassword();
    }
}
