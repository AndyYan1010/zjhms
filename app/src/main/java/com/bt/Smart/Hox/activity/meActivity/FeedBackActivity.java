package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.fragment.FeedBackListFragment;

/**
 * @创建者 AndyYan
 * @创建时间 2018/10/31 16:14
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class FeedBackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_frame);
        setView();
        setData();
    }

    private void setView() {
        FeedBackListFragment feedbacklListFt = new FeedBackListFragment();
        FragmentTransaction ftt = getSupportFragmentManager().beginTransaction();
        ftt.add(R.id.frame, feedbacklListFt, "feedbacklListFt");
        ftt.commit();
    }

    private void setData() {

    }
}
