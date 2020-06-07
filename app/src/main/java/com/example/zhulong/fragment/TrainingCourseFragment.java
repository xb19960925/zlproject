package com.example.zhulong.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.data.CourseBean;
import com.example.data.CourseTypeEvent;
import com.example.data.SpecialtyChooseEntity;
import com.example.frame.ApiConfig;
import com.example.frame.LoadTypeConfig;
import com.example.frame.constants.ConstantKey;
import com.example.zhulong.R;
import com.example.zhulong.adapter.CourseAdapter;
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.model.CourseModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingCourseFragment extends BaseMvpFragment<CourseModel> {


    @BindView(R.id.recycler_classical)
    RecyclerView recyclerClassical;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    int page=0;
    int tabType;
    private SpecialtyChooseEntity.DataBean selectedInfo;
    private CourseAdapter courseAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCourseType(CourseTypeEvent courseTypeEvent){
        tabType=courseTypeEvent.getType();
    }

    @Override
    public CourseModel setModel() {
        return new CourseModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_training_course;
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerClassical, smart, new LinearLayoutManager(getActivity()), mode -> {
            if (mode == LoadTypeConfig.REFRESH){
                page = 0;
                persenter.getData(ApiConfig.GET_COURSE_INFO, selectedInfo.getSpecialty_id(),page,15,tabType);
            } else {
                page++;
                persenter.getData(ApiConfig.GET_COURSE_INFO, selectedInfo.getSpecialty_id(),page,15,tabType);
            }
        });
        courseAdapter = new CourseAdapter(getActivity());
        recyclerClassical.setAdapter(courseAdapter);
    }

    @Override
    public void setUpData() {

        selectedInfo = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.SUBJECT_SELECT);
        persenter.getData(ApiConfig.GET_COURSE_INFO, selectedInfo.getSpecialty_id(),page,15,tabType);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.GET_COURSE_INFO:
                CourseBean courseBean = (CourseBean) pD[0];
                List<CourseBean.ResultBean.ListsBean> lists = courseBean.getResult().getLists();

//                if (loadType == LoadTypeConfig.MORE) {
//                    courseAdapter.setResult(result);
//                    smart.finishLoadMore();
//                } else if (loadType == LoadTypeConfig.REFRESH) {
//                    courseAdapter.setResult(result);
//                    smart.finishRefresh();
//                } else {
                courseAdapter.setResult(lists);
//                }
//                break;
        }
    }

    @OnClick({R.id.recycler_classical})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recycler_classical:
                break;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
