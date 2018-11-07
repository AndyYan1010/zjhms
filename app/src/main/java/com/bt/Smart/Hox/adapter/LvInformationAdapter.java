package com.bt.Smart.Hox.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.PlayListInfo;
import com.bt.Smart.Hox.util.GlideLoaderUtil;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/11/6 15:38
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvInformationAdapter extends BaseAdapter {
    private Context                         mContext;
    private List<PlayListInfo.PlayListBean> mList;

    public LvInformationAdapter(Context context, List<PlayListInfo.PlayListBean> list) {
        this.mContext = context;
        this.mList = list;
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
            view = View.inflate(mContext, R.layout.adpter_all_info, null);
            viewholder.img_cont = view.findViewById(R.id.img_cont);
            viewholder.tv_title = view.findViewById(R.id.tv_title);
            viewholder.tv_subtitle = view.findViewById(R.id.tv_subtitle);
            viewholder.tv_look = view.findViewById(R.id.tv_look);
            viewholder.tv_praise = view.findViewById(R.id.tv_praise);
            view.setTag(viewholder);
        } else {
            viewholder = (MyViewholder) view.getTag();
        }
        GlideLoaderUtil.showImgWithIcon(mContext, mList.get(i).getPlay_pic(), R.drawable.msg_empty, R.drawable.msg_empty, viewholder.img_cont);
        viewholder.tv_title.setText(mList.get(i).getPlay_title());
        viewholder.tv_subtitle.setText(mList.get(i).getPlay_introduce());
        viewholder.tv_look.setText(mList.get(i).getPlay_label());
        viewholder.tv_praise.setText(mList.get(i).getPlay_label());
        return view;
    }

    private class MyViewholder {
        ImageView img_cont;
        TextView  tv_title, tv_subtitle, tv_look, tv_praise;
    }
}
