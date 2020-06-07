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
import com.example.data.HomePageBean;
import com.example.zhulong.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeListAdapter extends RecyclerView.Adapter {
    Context context;
    List<HomePageBean.ResultBean> result = new ArrayList<>();

    private static final int ONE = 0;
    private static final int TWO = 1;



    public HomeListAdapter(Context context) {
        this.context = context;
    }

    public void setResult(List<HomePageBean.ResultBean> result) {
        this.result.addAll(result);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int type = result.get(position).getType();
        if (type == ONE) {
            return ONE;
        } else {
            return TWO;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ONE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_list_one, parent, false);
            return new OneViewHolder(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.home_list_two_layout, parent, false);
            return new TwoViewHolder(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        HomePageBean.ResultBean resultBean = result.get(position);
        if (itemViewType == ONE) {
            OneViewHolder oneViewHolder = (OneViewHolder) holder;
            Glide.with(context).load(resultBean.getThumb()).into(oneViewHolder.ivHomePhoto);
            oneViewHolder.tvHomeTitle.setText(resultBean.getTitle());
            oneViewHolder.tvHomeLook.setText(resultBean.getView_num()+"");
            oneViewHolder.tvHomeUpLoad.setText(resultBean.getReply_num()+"");
            oneViewHolder.tvHomeTime.setText(resultBean.getDate());
        } else {
            TwoViewHolder twoViewHolder = (TwoViewHolder) holder;
            Glide.with(context).load(resultBean.getThumb()).into(twoViewHolder.ivBigPhoto);
            twoViewHolder.tvBigTitle.setText(resultBean.getTitle());
            twoViewHolder.tvStudy.setText(resultBean.getView_num()+"");
            twoViewHolder.tvGood.setText(resultBean.getReply_num()+"");

        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class OneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_home_title)
        TextView tvHomeTitle;
        @BindView(R.id.iv_home_photo)
        ImageView ivHomePhoto;
        @BindView(R.id.tv_home_look)
        TextView tvHomeLook;
        @BindView(R.id.tv_home_up_load)
        TextView tvHomeUpLoad;
        @BindView(R.id.tv_home_time)
        TextView tvHomeTime;

        public OneViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }
    }

    public class TwoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_big_title)
        TextView tvBigTitle;
        @BindView(R.id.iv_big_photo)
        ImageView ivBigPhoto;
        @BindView(R.id.tv_study)
        TextView tvStudy;
        @BindView(R.id.tv_good)
        TextView tvGood;
        public TwoViewHolder(View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }
    }
}
