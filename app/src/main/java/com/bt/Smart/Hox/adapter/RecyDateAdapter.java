package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.DateStatueInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/14 8:38
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RecyDateAdapter extends RecyclerView.Adapter<RecyDateAdapter.ViewHolder> {
    private Context              mContext;
    private List<DateStatueInfo> mData;

    public RecyDateAdapter(Context context, List<DateStatueInfo> maindata) {
        this.mContext = context;
        this.mData = maindata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_date_item, parent, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(params);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_date.setText(mData.get(position).getTitle());
        if (mData.get(0).isIsSlec()) {
            if (position == 0) {
                holder.tv_date.setBackgroundResource(R.color.blue_87);
                holder.tv_date.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                holder.tv_date.setBackgroundResource(R.drawable.bg_line_blue_10);
                holder.tv_date.setTextColor(mContext.getResources().getColor(R.color.blue_87));
            }
        } else {
            if (mData.get(position).isIsSlec()) {
                holder.tv_date.setBackgroundResource(R.color.blue_87);
                holder.tv_date.setTextColor(mContext.getResources().getColor(R.color.white));
            } else {
                holder.tv_date.setBackgroundResource(R.drawable.bg_line_blue_10);
                holder.tv_date.setTextColor(mContext.getResources().getColor(R.color.blue_87));
            }
        }
        holder.tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.get(position).setIsSlec(!mData.get(position).isIsSlec());
                if (position == 0) {
                    for (int i = 1; i < 8; i++) {
                        mData.get(i).setIsSlec(false);
                    }
                } else {
                    mData.get(0).setIsSlec(false);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
