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
import com.example.data.CourseBean;
import com.example.zhulong.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    List<CourseBean.ResultBean.ListsBean> lists  = new ArrayList<>();
    Context context;


    public CourseAdapter(Context context) {
        this.context = context;
    }

    public void setResult(List<CourseBean.ResultBean.ListsBean> lists ) {
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.course_list_layout, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseBean.ResultBean.ListsBean listsBean = lists.get(position);
        Glide.with(context).load(listsBean.getThumb()).into(holder.ivCoursePhoto);
        holder.tvCourseTitle.setText(listsBean.getLesson_name());
        holder.tvStudy.setText(listsBean.getStudentnum()+"人学习");
        holder.tvGood.setText("好评率"+listsBean.getRate());
        holder. tvCourseMoney.setText(listsBean.getPrice());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_course_photo)
        ImageView ivCoursePhoto;
        @BindView(R.id.tv_course_title)
        TextView tvCourseTitle;
        @BindView(R.id.tv_study)
        TextView tvStudy;
        @BindView(R.id.tv_good)
        TextView tvGood;
        @BindView(R.id.tv_course_money)
        TextView tvCourseMoney;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
