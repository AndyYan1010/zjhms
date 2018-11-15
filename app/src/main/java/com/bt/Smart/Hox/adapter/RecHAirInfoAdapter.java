package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/14 17:10
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RecHAirInfoAdapter extends RecyclerView.Adapter<RecHAirInfoAdapter.ViewHolder> {
    private Context mContext;
    private List    mData;

    public RecHAirInfoAdapter(Context context, List maindata) {
        this.mContext = context;
        this.mData = maindata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_hair_info_item, parent, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(params);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (0 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pm2d5));
            holder.tv_title.setText("PM2.5");
            holder.tv_cont.setText("57.00μg/m³");
        } else if (1 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pm10));
            holder.tv_title.setText("PM10");
            holder.tv_cont.setText("62.00μg/m³");
        } else if (2 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.co2));
            holder.tv_title.setText("CO₂");
            holder.tv_cont.setText("656.00ppm");
        } else if (3 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.co));
            holder.tv_title.setText("CO");
            holder.tv_cont.setText("656.00ppm");
        } else if (4 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hcho));
            holder.tv_title.setText("甲醛");
            holder.tv_cont.setText("0.00ppm");
        } else if (5 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.temperature));
            holder.tv_title.setText("温度");
            holder.tv_cont.setText("27.88℃");
        } else if (6 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.humidity));
            holder.tv_title.setText("湿度");
            holder.tv_cont.setText("48.48%RH");
        } else {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.steamer));
            holder.tv_title.setText("VOCs");
            holder.tv_cont.setText("0.00");
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_cont;
        TextView  tv_title, tv_cont;

        public ViewHolder(View itemView) {
            super(itemView);
            img_cont = (ImageView) itemView.findViewById(R.id.img_cont);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_cont = (TextView) itemView.findViewById(R.id.tv_cont);
        }
    }
}
