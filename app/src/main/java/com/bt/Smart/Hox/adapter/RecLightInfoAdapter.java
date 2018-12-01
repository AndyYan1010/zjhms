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
import com.bt.Smart.Hox.activity.homeActivity.TypeMeasureLightDetailActivity;
import com.bt.Smart.Hox.messegeInfo.LightCurrentInfo;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/14 17:10
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RecLightInfoAdapter extends RecyclerView.Adapter<RecLightInfoAdapter.ViewHolder> {
    private Context                           mContext;
    private List<LightCurrentInfo.EnergyBean> mData;
    private String                            mDevID;

    public RecLightInfoAdapter(Context context, List<LightCurrentInfo.EnergyBean> maindata, String dev_id) {
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
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.power));
            holder.tv_title.setText("功率");
            holder.tv_cont.setText(mData.get(0).getPower() + "kw/h");
        } else if (1 == position) {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.electric));
            holder.tv_title.setText("电量");
            holder.tv_cont.setText(mData.get(0).getElectric_quantity() + "kw·h");
        } else {
            holder.img_cont.setImageDrawable(mContext.getResources().getDrawable(R.drawable.voltage));
            holder.tv_title.setText("电压");
            holder.tv_cont.setText(mData.get(0).getVoltage() + "V");
        }
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TypeMeasureLightDetailActivity.class);
                intent.putExtra("dev_ID", mDevID);
                if (0 == position) {//跳转功率Power数据界面
                    intent.putExtra("type", "power");
                } else if (1 == position) {//跳转电量ElectricQuantity数据界面
                    intent.putExtra("type", "electric_quantity");
                } else {//跳转电压Voltage数据界面
                    intent.putExtra("type", "voltage");
                }
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : 3;
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
