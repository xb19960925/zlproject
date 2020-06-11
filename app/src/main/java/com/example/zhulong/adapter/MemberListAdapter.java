package com.example.zhulong.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BannerAndLiveVip;
import com.example.data.BannerLiveInfo;
import com.example.data.VipPageList;
import com.example.zhulong.R;
import com.example.zhulong.design.BannerLayout;
import com.yiyatech.utils.newAdd.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.ViewHolder> {
    List<BannerAndLiveVip.LiveBeanX.LiveBean> liveData = new ArrayList<>();
    List<BannerAndLiveVip.LunbotuBean> bannerData = new ArrayList<>();
    List<VipPageList.ListBean> bottomList=new ArrayList<>();

    public void setList(List<VipPageList.ListBean> bottomList) {
        this.bottomList.addAll(bottomList);
        notifyDataSetChanged();
    }

    private Activity mContext;

    public MemberListAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void setLiveData(List<BannerAndLiveVip.LiveBeanX.LiveBean> liveData) {
        this.liveData.addAll(liveData);
        notifyDataSetChanged();
    }

    public void setBannerData(List<BannerAndLiveVip.LunbotuBean> bannerData) {
        this.bannerData.addAll(bannerData);
        notifyDataSetChanged();
    }

    private final int BANNER = 1, LIVE = 2, GRILD = 3;

    @Override
    public int getItemViewType(int position) {
        int type = GRILD;
        if (position == 0) type = BANNER;
        else if (position == 1) type = LIVE;
        else if (liveData != null && liveData.size() != 0 && position == 1) type = LIVE;
        else {
            if (position == 2) {

                type = GRILD;

            }
        }
        return type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @LayoutRes int layoutId;
        if (viewType == BANNER) {
            layoutId = R.layout.item_homepage_ad;
        } else if (viewType == LIVE) {//recleview，
            layoutId = R.layout.live_recycler_item;
        } else {
            layoutId = R.layout.item_bottom_member_list;
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            holder.banner.attachActivity(mContext);
            ArrayList<String> urlList = new ArrayList<>();
            for (BannerAndLiveVip.LunbotuBean dd : bannerData) {
                urlList.add(dd.getImg());
            }
            if (bannerData.size() != 0) holder.banner.setViewUrls(urlList);
        } else if (getItemViewType(position) == LIVE) {
            LinearLayoutManager manager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            holder.recyclerView.setLayoutManager(manager);
            MemberLiveAdapter memberLiveAdapter = new MemberLiveAdapter(liveData, mContext);
            holder.recyclerView.setAdapter(memberLiveAdapter);
        } else {
            if (bottomList.size() == 0) return;
            holder.memberBottomRecycler.setLayoutManager(new GridLayoutManager(mContext, 2));
            MemberBottomAdapter memberBottomAdapter = new MemberBottomAdapter(mContext, bottomList);
            holder.memberBottomRecycler.setAdapter(memberBottomAdapter);
        }
    }


    @Override
    public int getItemCount() {
        return liveData != null && liveData.size() != 0 ? bottomList.size() + 2 : bottomList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BannerLayout banner;

        RecyclerView recyclerView;

        RecyclerView memberBottomRecycler;

        public ViewHolder(@NonNull View itemView, int type) {
            super(itemView);
            if (type == BANNER) {
                banner = itemView.findViewById(R.id.banner_main);
            } else if (type == LIVE) {
                recyclerView = itemView.findViewById(R.id.recyclerView);
            } else {
                memberBottomRecycler=itemView.findViewById(R.id.member_bottom_recycler);
            }
        }
    }
}
