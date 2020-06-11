package com.example.zhulong.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BannerAndLiveVip;
import com.example.data.BannerLiveInfo;
import com.example.zhulong.R;
import com.example.zhulong.design.RoundImage;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.TimeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberLiveAdapter extends RecyclerView.Adapter<MemberLiveAdapter.ViewHolder> {
    private List<BannerAndLiveVip.LiveBeanX.LiveBean> liveData;
    private Context mContext;

    public MemberLiveAdapter(List<BannerAndLiveVip.LiveBeanX.LiveBean> pLiveData, Context pContext) {
        liveData = pLiveData;
        mContext = pContext;
    }

    @NonNull
    @Override
    public MemberLiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MemberLiveAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.live_member_list_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MemberLiveAdapter.ViewHolder holder, int position) {
        BannerAndLiveVip.LiveBeanX.LiveBean live = liveData.get(position);
        GlideUtil.loadImage(holder.roundPhoto,live.getCoverImgUrl());
        holder.title.setText(live.getActivityName());
        if (!TextUtils.isEmpty(live.getStart_date()))holder.time.setText(live.getStart_date());
    }

    @Override
    public int getItemCount() {
        return liveData != null ? liveData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.round_photo)
        RoundImage roundPhoto;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
