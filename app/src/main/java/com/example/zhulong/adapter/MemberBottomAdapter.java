package com.example.zhulong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.VipPageList;
import com.example.zhulong.R;
import com.yiyatech.utils.newAdd.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberBottomAdapter extends RecyclerView.Adapter<MemberBottomAdapter.ViewHolder> {
    Context context;
    List<VipPageList.ListBean> bottomList=new ArrayList<>();


    public MemberBottomAdapter(Context context, List<VipPageList.ListBean> bottomList) {
        this.context = context;
        this.bottomList = bottomList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.bottom_list_item_member, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VipPageList.ListBean listBean = bottomList.get(position);
        holder.tvTitle.setText(listBean.getLesson_name());
        GlideUtil.loadCornerImage(holder.image, listBean.getThumb(), null, 6f);
        holder.studyNum.setText(listBean.getStudentnum()+"人学习");
        holder.goodPercent.setText(listBean.getComment_rate()+"好评");
    }

    @Override
    public int getItemCount() {
        return bottomList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.study_num)
        TextView studyNum;
        @BindView(R.id.good_percent)
        TextView goodPercent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
