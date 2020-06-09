package com.example.zhulong.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.util.LogTime;
import com.example.data.BaseInfo;
import com.example.data.CourseBean;
import com.example.data.CourseTypeEvent;
import com.example.data.SpecialtyChooseEntity;
import com.example.frame.ApiConfig;
import com.example.frame.constants.ConstantKey;
import com.example.zhulong.MainActivity;
import com.example.zhulong.R;
import com.example.zhulong.adapter.MyFragmentAdapter;
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.model.CourseModel;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends BaseMvpFragment<CourseModel> {


    @BindView(R.id.slide_layout)
    SlidingTabLayout slideLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<String> titleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private MyFragmentAdapter mFragmentAdapter;

    public static final int TRAINTAB = 3;
    public static final int BESTTAB = 1;
    public static final int PUBLICTAB = 2;

    @Override
    public CourseModel setModel() {
        return new CourseModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_course;
    }

    @Override
    public void setUpView() {
        mFragmentAdapter = new MyFragmentAdapter(getChildFragmentManager(), mFragments, titleList);
        viewPager.setAdapter(mFragmentAdapter);
        slideLayout.setViewPager(viewPager);
    }

    @Override
    public void setUpData() {
        Collections.addAll(titleList, "训练营", "精品课", "公开课");
        Collections.addAll(mFragments,CourseChildFragment.getInstance(TRAINTAB),CourseChildFragment.getInstance(BESTTAB),CourseChildFragment.getInstance(PUBLICTAB));
        mFragmentAdapter.notifyDataSetChanged();
        slideLayout.notifyDataSetChanged();
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {

    }

}
