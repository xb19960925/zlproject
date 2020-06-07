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
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.model.CourseModel;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends BaseMvpFragment<CourseModel> implements View.OnClickListener {


    @BindView(R.id.course_tab)
    TabLayout courseTab;
    @BindView(R.id.course_vp)
    ViewPager courseVp;


    private static final int CLASSICAL=1;
    private static final int OPEN=2;
    private static final int TRAINING=3;
    int course_type=CLASSICAL;
    private MainActivity activity;

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
        activity = (MainActivity) getActivity();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ClassicalFragment());
        fragments.add(new OpenCourseFragment());
        fragments.add(new TrainingCourseFragment());
        courseVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        courseTab.setupWithViewPager(courseVp);
        courseTab.getTabAt(0).setCustomView(getCustomView("精品课"));
        courseTab.getTabAt(1).setCustomView(getCustomView("公开课"));
        courseTab.getTabAt(2).setCustomView(getCustomView("训练营"));
        courseTab.setOnClickListener(this);
    }

    public View getCustomView(String title){
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.tab_course_tab, null);
        TextView course_tab_tv = inflate.findViewById(R.id.course_tab_tv);
        course_tab_tv.setText(title);
        return inflate;
    }

    @Override
    public void setUpData() {

    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case 0:
                course_type=CLASSICAL;
                break;
            case 1:
                course_type=OPEN;
                break;
            case 2:
                course_type=TRAINING;
                break;
        }
        CourseTypeEvent courseTypeEvent = new CourseTypeEvent(course_type);
        EventBus.getDefault().post(courseTypeEvent);
    }

}
