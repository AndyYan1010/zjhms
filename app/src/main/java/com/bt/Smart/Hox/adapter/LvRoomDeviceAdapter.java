package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.RoomsDeviceInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/2 20:34
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvRoomDeviceAdapter extends BaseAdapter {
    private Context                                            mContext;
    private List<RoomsDeviceInfo.HouseListBean.DeviceListBean> mList;
    private List<RoomsDeviceInfo.HouseListBean>                mTopList;
    private LvShareAdapter                                     mTopAdapter;
    private CheckBox                                           mTopCheckBox;

    public LvRoomDeviceAdapter(Context context, List<RoomsDeviceInfo.HouseListBean.DeviceListBean> deviceList, List topList, LvShareAdapter lvShareAdapter, CheckBox checkBox) {
        this.mContext = context;
        this.mList = deviceList;
        this.mTopList = topList;
        this.mTopAdapter = lvShareAdapter;
        this.mTopCheckBox = checkBox;
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
            view = View.inflate(mContext, R.layout.adpter_room_dev, null);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.cb_choice = view.findViewById(R.id.cb_choice);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getDevice_name());
        if (mList.get(i).isMeChoice()) {
            viewholder.cb_choice.setChecked(true);
        } else {
            viewholder.cb_choice.setChecked(false);
        }
        viewholder.cb_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewholder.cb_choice.isChecked()) {
                    mList.get(i).setMeChoice(true);
                    mTopList.get(mTopAdapter.mineSelectItem()).setMineIschoice(true);
                    mTopCheckBox.setChecked(true);
                } else {
                    mList.get(i).setMeChoice(false);
                    boolean hasChoice = false;
                    for (RoomsDeviceInfo.HouseListBean.DeviceListBean bean : mList) {
                        if (bean.isMeChoice()) {
                            hasChoice = true;
                        }
                    }
                    if (hasChoice) {
                        mTopList.get(mTopAdapter.mineSelectItem()).setMineIschoice(true);
                        mTopCheckBox.setChecked(true);
                    } else {
                        mTopList.get(mTopAdapter.mineSelectItem()).setMineIschoice(false);
                        mTopCheckBox.setChecked(false);
                    }
                }
            }
        });
        return view;
    }

    private class MyViewholder {
        TextView tv_name;
        CheckBox cb_choice;
    }
}
