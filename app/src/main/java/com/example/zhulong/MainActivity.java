package com.example.zhulong;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.example.frame.ConnectionModel;
import com.example.zhulong.base.BaseMvpActivity;
import com.example.zhulong.fragment.CourseFragment;
import com.example.zhulong.fragment.DatumFragment;
import com.example.zhulong.fragment.HomeFragment;
import com.example.zhulong.fragment.MemberFragment;
import com.example.zhulong.fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class MainActivity extends BaseMvpActivity {


    private NavController navController;

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
