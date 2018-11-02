package com.bt.Smart.Hox.activity.meActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 13:36
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ShareRoomActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_room);
        setView();
        setData();
    }

    private void setView() {
        ImageView img_back = (ImageView) findViewById(R.id.img_back);
        ListView lv_room_device = (ListView) findViewById(R.id.lv_room_device);

    }

    private void setData() {

    }
}
