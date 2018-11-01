package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.bt.Smart.Hox.R;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/4/18 16:13
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class ChoiceRoomAdapter extends RecyclerView.Adapter<ChoiceRoomAdapter.ViewHolder> {
    private Context      mContext;
    private List<String> mData;

    public ChoiceRoomAdapter(Context context, List data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gridview, parent, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(params);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_room.setText(mData.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox cb_choice;
        TextView tv_room;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_choice = (CheckBox) itemView.findViewById(R.id.cb_choice);
            tv_room = (TextView) itemView.findViewById(R.id.tv_room);
        }
    }
}
