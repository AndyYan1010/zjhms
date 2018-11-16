package com.bt.Smart.Hox.activity.homeActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.fragment.AddWifiFragment;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/16 14:06
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AddWifiDeviceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_frame);
        getView();
        setData();
    }

    private void setData() {
        AddWifiFragment addWifiFt = new AddWifiFragment();
        FragmentTransaction ftt = getSupportFragmentManager().beginTransaction();
        ftt.add(R.id.frame, addWifiFt, "addWifiFt");
        ftt.commit();
    }

    private void getView() {

    }
}
