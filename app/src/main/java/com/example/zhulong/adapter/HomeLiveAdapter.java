package com.example.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.HomePageBannerBean;
import com.example.zhulong.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeLiveAdapter extends RecyclerView.Adapter<HomeLiveAdapter.ViewHolder> {
    Context context;
    List<HomePageBannerBean.ResultBean.NoliveBean> nolive = new ArrayList<>();


    public HomeLiveAdapter(Context context) {
        this.context = context;
    }

    public void setNolive(List<HomePageBannerBean.ResultBean.NoliveBean> nolive) {
        this.nolive.addAll(nolive);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.live_list_layout, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomePageBannerBean.ResultBean.NoliveBean noliveBean = nolive.get(position);
        Glide.with(context).load(noliveBean.getCoverImgUrl()).into(holder.ivLiveHeadPhoto);
        holder.tvLiveCourseName.setText(noliveBean.getActivityName());
    }

    @Override
    public int getItemCount() {
        return nolive.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_live_head_photo)
        ImageView ivLiveHeadPhoto;
        @BindView(R.id.tv_live_course_name)
        TextView tvLiveCourseName;
        @BindView(R.id.iv_live_photo)
        ImageView ivLivePhoto;
        @BindView(R.id.tv_date_live)
        TextView tvDateLive;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
