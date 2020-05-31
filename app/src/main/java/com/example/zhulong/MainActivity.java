package com.example.zhulong;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.zhulong.fragment.CourseFragment;
import com.example.zhulong.fragment.DatumFragment;
import com.example.zhulong.fragment.HomeFragment;
import com.example.zhulong.fragment.MemberFragment;
import com.example.zhulong.fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TabLayout tablayout;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CourseFragment());
        fragments.add(new MemberFragment());
        fragments.add(new DatumFragment());
        fragments.add(new MyFragment());


        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        tablayout.setupWithViewPager(vp);

        tablayout.getTabAt(0).setCustomView(getCustomView(R.drawable.home_tab_selector,"主页"));
        tablayout.getTabAt(1).setCustomView(getCustomView(R.drawable.course_tab_selector,"课程"));
        tablayout.getTabAt(2).setCustomView(getCustomView(R.drawable.member_tab_selector,"VIP"));
        tablayout.getTabAt(3).setCustomView(getCustomView(R.drawable.datum_tab_selector,"资料"));
        tablayout.getTabAt(4).setCustomView(getCustomView(R.drawable.my_tab_selector,"我的"));
    }

    public View getCustomView(int drawer,String title){
        View inflate = LayoutInflater.from(this).inflate(R.layout.vp_item, null);
        ImageView iv_tab_item_photo = inflate.findViewById(R.id.iv_tab_item_photo);
        TextView tv_tab_item_text = inflate.findViewById(R.id.tv_tab_item_text);
        iv_tab_item_photo.setBackgroundResource(drawer);
        tv_tab_item_text.setText(title);
        return inflate;
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tablayout = (TabLayout) findViewById(R.id.tablayout);


    }
}
