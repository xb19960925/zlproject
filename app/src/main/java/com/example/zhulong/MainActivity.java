package com.example.zhulong;


import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.frame.ConnectionModel;
import com.example.zhulong.base.BaseMvpActivity;
import com.example.zhulong.fragment.ClassicalFragment;
import com.example.zhulong.fragment.CourseFragment;

public class MainActivity extends BaseMvpActivity  {


    private NavController navController;
    private CourseFragment courseFragment;

    @Override
    protected ConnectionModel setModel() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        navController = Navigation.findNavController(this, R.id.project_fragment_control);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void netSuccess(int whichApi, Object[] d) {
    }


}
