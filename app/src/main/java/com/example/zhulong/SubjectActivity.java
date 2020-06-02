package com.example.zhulong;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BaseInfo;
import com.example.data.SpecialtyChooseEntity;
import com.example.frame.ApiConfig;
import com.example.frame.constants.ConstantKey;
import com.example.zhulong.adapter.SubjectAdapter;
import com.example.zhulong.base.BaseMvpActivity;
import com.example.zhulong.model.LauchModel;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubjectActivity extends BaseMvpActivity<LauchModel> {
    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private SubjectAdapter subjectAdapter;
    private List<SpecialtyChooseEntity> mListData = new ArrayList<>();
    @Override
    protected LauchModel setModel() {
        return new LauchModel();
    }

    @Override
    protected void initData() {
        if (SharedPrefrenceUtils.getSerializableList(this, ConstantKey.SUBJECT_LIST) != null) {
            mListData.addAll(SharedPrefrenceUtils.getSerializableList(this, ConstantKey.SUBJECT_LIST));
            subjectAdapter.notifyDataSetChanged();
        } else
            persenter.getData(ApiConfig.SUBJECT);
    }

    @Override
    protected void initView() {
        titleContent.setText("选择感兴趣的专业");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjectAdapter = new SubjectAdapter(mListData, this);
        recyclerView.setAdapter(subjectAdapter);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_subject;
    }

    @Override
    public void netSuccess(int whichApi, Object[] d) {
        switch (whichApi) {
            case ApiConfig.SUBJECT:
                BaseInfo<List<SpecialtyChooseEntity>> info = (BaseInfo<List<SpecialtyChooseEntity>>) d[0];
                mListData.addAll(info.result);
                subjectAdapter.notifyDataSetChanged();
                SharedPrefrenceUtils.putSerializableList(this, ConstantKey.SUBJECT_LIST,mListData);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPrefrenceUtils.putObject(this,ConstantKey.SUBJECT_SELECT,application.getSelectedInfo());
    }

    @OnClick(R.id.back_image)
    public void onViewClicked() {
        finish();
    }
}
