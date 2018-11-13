package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.AutoDetailInfo;

import java.util.Date;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/13 14:35
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvAddTimeAdapter extends BaseAdapter {
    private List<AutoDetailInfo.AutomationBean.AutomationsBean.ConditionListBean> mList;
    private Context                                                               mContext;
    private List<String>                                                          mOptions1Items;

    public LvAddTimeAdapter(Context context, List<AutoDetailInfo.AutomationBean.AutomationsBean.ConditionListBean> list, List options1Items) {
        this.mContext = context;
        this.mList = list;
        this.mOptions1Items = options1Items;
    }

    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
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
            view = View.inflate(mContext, R.layout.adpter_add_time, null);
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.tv_become = view.findViewById(R.id.tv_become);
            viewholder.lin_time_slot = view.findViewById(R.id.lin_time_slot);
            viewholder.tv_start = view.findViewById(R.id.tv_start);
            viewholder.tv_end = view.findViewById(R.id.tv_end);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.tv_name.setText(mList.get(i).getKind_name());
        if (0 == mList.get(i).getWhen()) {
            viewholder.tv_become.setText("变为");
        } else {
            viewholder.tv_become.setText("此时正好");
        }
        viewholder.tv_start.setText(mList.get(i).getTime_start());
        viewholder.tv_end.setText(mList.get(i).getTime_end());
        viewholder.tv_become.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeStatusView(viewholder.tv_become);
            }
        });
        viewholder.lin_time_slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStartTime = !isStartTime;
                if (isStartTime) {
                    selectTimer("设置开始时间", viewholder.tv_start);
                } else {
                    selectTimer("设置结束时间", viewholder.tv_end);
                }
            }
        });
        return view;
    }

    private void selectTimer(String title, final TextView tv) {
        //时间选择器
        TimePickerView pvTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tv.setText(date.toString().substring(11, 16));
            }
        })
                .setType(new boolean[]{false, false, false, true, true, false})
                .setTitleText(title)
                .build();
        pvTime.show();
    }

    private void showTimeStatusView(final TextView tv_become) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tv_become.setText(mOptions1Items.get(options1));
            }
        })
                .setTitleText("条件选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(mOptions1Items);//一级选择器
        pvOptions.show();
    }

    private boolean isStartTime;

    private class MyViewholder {
        LinearLayout lin_time_slot;
        TextView     tv_name, tv_become, tv_start, tv_end;
    }
}
