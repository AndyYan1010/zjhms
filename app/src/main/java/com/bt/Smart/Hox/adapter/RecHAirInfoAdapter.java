package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.activity.homeActivity.HAirMeasureTypeActivity;
import com.bt.Smart.Hox.messegeInfo.HairCurrentInfo;

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
    private Context                        mContext;
    private List<HairCurrentInfo.HairBean> mData;
    private String                         mDevID;

    public RecHAirInfoAdapter(Context context, List<HairCurrentInfo.HairBean> maindata, String dev_id) {
        this.mContext = context;
        this.mData = maindata;
        this.mDevID = dev_id;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (0 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pm2d5));
            holder.tv_title.setText("PM2.5");
            holder.tv_cont.setText(mData.get(0).getPm25() + "μg/m³");
        } else if (1 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.pm10));
            holder.tv_title.setText("PM100");
            holder.tv_cont.setText(mData.get(0).getPm100() + "μg/m³");
        } else if (2 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.co2));
            holder.tv_title.setText("CO₂");
            holder.tv_cont.setText(mData.get(0).getCo2() + "ppm");
        } else if (3 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.co));
            holder.tv_title.setText("CO");
            holder.tv_cont.setText(mData.get(0).getCo() + "ppm");
        } else if (4 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.hcho));
            holder.tv_title.setText("甲醛");
            holder.tv_cont.setText(mData.get(0).getFormaldehyde() + "ppm");
        } else if (5 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.temperature));
            holder.tv_title.setText("温度");
            holder.tv_cont.setText(mData.get(0).getTemperature() + "℃");
        } else if (6 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.humidity));
            holder.tv_title.setText("湿度");
            holder.tv_cont.setText(mData.get(0).getHumidity() + "%RH");
        } else {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.steamer));
            holder.tv_title.setText("VOCs");
            holder.tv_cont.setText(mData.get(0).getVoc() + "mg/m³");
        }
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, HAirMeasureTypeActivity.class);
                intent.putExtra("dev_ID", mDevID);
                if (0 == position) {//跳转PM2.5数据界面
                    intent.putExtra("type", "pm25");
                } else if (1 == position) {//跳转PM100数据界面
                    intent.putExtra("type", "pm100");
                } else if (2 == position) {//跳转CO₂数据界面
                    intent.putExtra("type", "co2");
                } else if (3 == position) {//跳转CO数据界面
                    intent.putExtra("type", "co");
                } else if (4 == position) {//跳转甲醛数据界面
                    intent.putExtra("type", "formaldehyde");
                } else if (5 == position) {//跳转温度数据界面
                    intent.putExtra("type", "temperature");
                } else if (6 == position) {//跳转湿度数据界面
                    intent.putExtra("type", "humidity");
                } else if (7 == position) {//跳转VOCs数据界面
                    intent.putExtra("type", "voc");
                }
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView  card_view;
        ImageView img_cont;
        TextView  tv_title, tv_cont;

        public ViewHolder(View itemView) {
            super(itemView);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
            img_cont = (ImageView) itemView.findViewById(R.id.img_cont);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_cont = (TextView) itemView.findViewById(R.id.tv_cont);
        }
    }
}
