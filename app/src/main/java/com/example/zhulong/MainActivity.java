package com.example.zhulong;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.example.frame.ConnectionModel;
import com.example.zhulong.base.BaseMvpActivity;
import com.example.zhulong.fragment.CourseFragment;

public class MainActivity extends BaseMvpActivity implements NavController.OnDestinationChangedListener {


    public NavController navController;


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
        navController.addOnDestinationChangedListener(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void netSuccess(int whichApi, Object[] d) {
    }


    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        String label = destination.getLabel().toString();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
