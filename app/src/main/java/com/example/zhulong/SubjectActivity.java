package com.example.zhulong;


import android.content.Intent;
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

import static com.example.zhulong.constants.JumpConstant.*;

public class SubjectActivity extends BaseMvpActivity<LauchModel> {
    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.more_content)
    TextView moreContent;
    private SubjectAdapter subjectAdapter;
    private List<SpecialtyChooseEntity> mListData = new ArrayList<>();
    private String mFrom;

    @Override
    protected LauchModel setModel() {
        return new LauchModel();
    }

    @Override
    protected void initData() {
        List<SpecialtyChooseEntity> info = SharedPrefrenceUtils.getSerializableList(this, ConstantKey.SUBJECT_LIST);
        if (info != null) {
            mListData.addAll(info);
            subjectAdapter.notifyDataSetChanged();
        } else
            persenter.getData(ApiConfig.SUBJECT);
    }

    @Override
    protected void initView() {
        mFrom = getIntent().getStringExtra(JUMP_KEY);
        titleContent.setText("选择感兴趣的专业");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjectAdapter = new SubjectAdapter(mListData, this);
        recyclerView.setAdapter(subjectAdapter);
        moreContent.setText("完成");
        moreContent.setOnClickListener(v->{
            if (application.getSelectedInfo() == null){
                showToast("请选择专业");
                return;
            }
            if (mFrom.equals(SPLASH_TO_SUB)){
                if (application.isLogin()){
                    startActivity(new Intent(SubjectActivity.this,MainActivity.class));
                } else {
                    startActivity(new Intent(SubjectActivity.this,LoginActivity.class).putExtra(JUMP_KEY,SUB_TO_LOGIN));
                }
            }
            finish();
        });
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
                SharedPrefrenceUtils.putSerializableList(this, ConstantKey.SUBJECT_LIST, mListData);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPrefrenceUtils.putObject(this, ConstantKey.SUBJECT_SELECT, application.getSelectedInfo());
    }

    @OnClick(R.id.back_image)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
