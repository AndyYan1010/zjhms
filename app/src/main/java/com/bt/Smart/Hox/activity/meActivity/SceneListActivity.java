package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.fragment.AddIntelligentFragment;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 15:16
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_frame);
        setView();
        setData();
    }

    private void setView() {
        AddIntelligentFragment addIntelligentFgt = new AddIntelligentFragment();
        FragmentTransaction ftt = getSupportFragmentManager().beginTransaction();
        ftt.add(R.id.frame, addIntelligentFgt, "addIntelligentFgt");
        ftt.commit();
    }

    private void setData() {

    }
}
