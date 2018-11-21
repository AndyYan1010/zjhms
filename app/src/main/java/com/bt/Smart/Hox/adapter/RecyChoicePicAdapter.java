package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bt.Smart.Hox.NetConfig;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.fragment.ChoiceScenePicFragment;
import com.bt.Smart.Hox.messegeInfo.ScenePicListInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/20 9:08
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class RecyChoicePicAdapter extends RecyclerView.Adapter<RecyChoicePicAdapter.ViewHolder> {
    private Context                                 mContext;
    private List<ScenePicListInfo.ScenePiclistBean> mData;
    private ChoiceScenePicFragment                  mChoiceScenePicFragment;

    public RecyChoicePicAdapter(Context context, List<ScenePicListInfo.ScenePiclistBean> picData, ChoiceScenePicFragment picFragment) {
        this.mContext = context;
        this.mData = picData;
        this.mChoiceScenePicFragment = picFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_choice_scene_pic, parent, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(params);
        // 实例化viewholder
        RecyChoicePicAdapter.ViewHolder viewHolder = new RecyChoicePicAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        GlideLoaderUtil.showImageView(mContext, NetConfig.IMG_FOR_SCENE + mData.get(position).getFpic(), holder.img_pic);
        holder.img_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //选中图片，关闭fragment
                mChoiceScenePicFragment.selectItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_pic;

        public ViewHolder(View itemView) {
            super(itemView);
            img_pic = (ImageView) itemView.findViewById(R.id.img_pic);
        }
    }
}
