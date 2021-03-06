package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.fragment.AddSceneFragment;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/30 8:54
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class SceneDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_frame);
        setView();
        setData();
    }

    private void setView() {
        android.support.v4.app.FragmentTransaction ftt = getSupportFragmentManager().beginTransaction();
        AddSceneFragment sceneShowFt = new AddSceneFragment();
        sceneShowFt.setKind("1", getIntent().getStringExtra("sceneID"));
        sceneShowFt.setFrom(getIntent().getStringExtra("from"));
        ftt.add(R.id.frame, sceneShowFt, "sceneShowFt");
        ftt.commit();
    }

    private void setData() {

    }
}
