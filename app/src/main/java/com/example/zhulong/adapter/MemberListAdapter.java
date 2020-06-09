package com.example.zhulong.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BannerLiveInfo;
import com.example.data.VipPageList;
import com.example.zhulong.R;
import com.example.zhulong.design.BannerLayout;
import com.yiyatech.utils.ext.StringUtils;
import com.yiyatech.utils.newAdd.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.ViewHolder> {
    List<BannerLiveInfo.Carousel> bannerData = new ArrayList<>();
    List<BannerLiveInfo.Live> liveData = new ArrayList<>();
    List<VipPageList.ResultBean.ListBean> bottomList=new ArrayList<>();
    private Activity mContext;

    public MemberListAdapter(Activity mContext) {
        this.mContext = mContext;
    }

    public void setCarousel(List<BannerLiveInfo.Carousel> bannerData) {
        this.bannerData.addAll(bannerData);
    }

    public void setLive(List<BannerLiveInfo.Live> liveData) {
        this.liveData.addAll(liveData);
    }

    public void setList(List<VipPageList.ResultBean.ListBean> bottomList) {
        this.bottomList.addAll(bottomList);
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
            if (position==2) {

                    type = GRILD;

            }
        }
        return type;
    }

    @NonNull
    @Override
    public MemberListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @LayoutRes int layoutId;
        if (viewType == BANNER) {
            layoutId = R.layout.item_homepage_ad;
        }
        else if (viewType == LIVE) {//recleview，
            layoutId = R.layout.live_recycler_item;
        } else {
            layoutId = R.layout.item_homepage_post;
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberListAdapter.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            holder.banner.attachActivity(mContext);
            ArrayList<String> urlList = new ArrayList<>();
            for (BannerLiveInfo.Carousel dd:bannerData) {
                urlList.add(dd.url);
            }
            if (bannerData.size() != 0) holder.banner.setViewUrls(urlList);
        } else if (getItemViewType(position) == LIVE) {
            LinearLayoutManager manager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            holder.recyclerView.setLayoutManager(manager);
            LiveAdapter adapter = new LiveAdapter(liveData, mContext);
            holder.recyclerView.setAdapter(adapter);
        } else {
            if (bottomList.size() == 0) return;
//            VipPageList.ResultBean.ListBean listBean = bottomList.get(liveData == null || liveData.size() == 0 ? position - 2 : position - 3);
//            GlideUtil.loadCornerImage(holder.image, entity.pic, null, 6f);
//            holder.tvCommentNum.setText((TextUtils.isEmpty(entity.reply_num) ? 0 : entity.reply_num) + "人跟帖");
//            holder.tvBrowseNum.setText(entity.view_num + "人浏览");
            }
        }



    @Override
    public int getItemCount() {
        return liveData != null && liveData.size() != 0 ? bottomList.size() + 2 : bottomList.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        BannerLayout banner;

        RecyclerView recyclerView;



//        TextView title;
//        TextView tvBrowseNum;
//        TextView tvCommentNum;
//        ImageView ivPhoto;
//        TextView tvAuthorAndTime;
        public ViewHolder(@NonNull View itemView,int type) {
            super(itemView);
            if (type == BANNER) {
                banner = itemView.findViewById(R.id.banner_main);
            } else if (type == LIVE) {
                recyclerView = itemView.findViewById(R.id.recyclerView);
            } else{
//                title = itemView.findViewById(R.id.tv_title_left);
//                tvBrowseNum = itemView.findViewById(R.id.tv_browse_num);
//                tvCommentNum = itemView.findViewById(R.id.tv_comment_num);
//                ivPhoto = itemView.findViewById(R.id.iv_photo);
//                tvAuthorAndTime = itemView.findViewById(R.id.tv_author_and_time);
            }
        }
    }
}
