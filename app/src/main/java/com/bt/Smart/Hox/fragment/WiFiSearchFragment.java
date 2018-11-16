package com.bt.Smart.Hox.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.utils.MyFragmentManagerUtil;
import com.github.ring.CircleProgress;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/16 14:53
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class WiFiSearchFragment extends Fragment implements View.OnClickListener {
    private View           mRootView;
    private ImageView      img_back;
    private TextView       tv_title;
    private CircleProgress circleprogress;
    private Handler        handler;
    private int count = 60;//搜索时间、单位秒

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_wifi_search, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        img_back = mRootView.findViewById(R.id.img_back);
        tv_title = mRootView.findViewById(R.id.tv_title);
        circleprogress = mRootView.findViewById(R.id.circleprogress);
    }

    private void initData() {
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(this);
        tv_title.setText("搜索并连接设备");
        handler = new Handler();
        circleprogress.setProgress(0.0f);
        circleprogress.setMaxProgress(60);
        handler.postDelayed(new Runnable() {
            public void run() {
                handler.postDelayed(this, 1000);//递归执行，一秒执行一次
                count--;
                circleprogress.setProgress(60 - count);
                if (count == 0) {
                    circleprogress.setProgress(60);
                    handler.removeCallbacks(this);
                }
            }
        }, 1000);    //第一次执行，一秒之后。第一次执行完就没关系了
        //        circleprogress.setOnCircleProgressInter(new CircleProgress.OnCircleProgressInter() {
        //            @Override
        //            public void progress(float scaleProgress, float progress, float max) {
        //                //总进度max,当前进度:progress,动画执行进度:scaleProgress
        //
        //            }
        //        });
        //        circleprogress.post(new Runnable() {
        //            @Override
        //            public void run() {
        //                LinearGradient linearGradient = new LinearGradient(0,0,
        //                        circleprogress.getWidth(),circleprogress.getHeight(),
        //                        circleprogress.getRingProgressColor(), ContextCompat.getColor(getContext(),R.color.green),
        //                        Shader.TileMode.MIRROR);
        //                circleprogress.setProgressShader(linearGradient);
        //            }
        //        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MyFragmentManagerUtil.closeTopFragment(this);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
        handler = null;
    }
}
