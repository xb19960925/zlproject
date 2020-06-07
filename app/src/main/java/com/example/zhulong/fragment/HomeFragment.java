package com.example.zhulong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import com.example.zhulong.R;
import com.example.zhulong.SubjectActivity;
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.design.BottmTab;
import com.example.zhulong.model.MainPageModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<MainPageModel> implements NavController.OnDestinationChangedListener, BottmTab.OnBottomTabClickCallBack {


    @BindView(R.id.select_subject)
    TextView selectSubject;
    @BindView(R.id.bottom_tab)
    BottmTab bottomTab;
    private List<Integer> normalIcon = new ArrayList<>();//为选中的icon
    private List<Integer> selectedIcon = new ArrayList<>();// 选中的icon
    private List<String> tabContent = new ArrayList<>();//tab对应的内容
    private NavController navController;
    private final int MAIN_PAGE = 1, COURSE = 2, VIP = 3, DATA = 4, MINE = 5;

    @Override
    public MainPageModel setModel() {
        return null;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void setUpView() {
        BottmTab tabView = getView().findViewById(R.id.bottom_tab);
        Collections.addAll(normalIcon, R.drawable.main_page_view, R.drawable.course_view, R.drawable.vip_view, R.drawable.data_view, R.drawable.mine_view);
        Collections.addAll(selectedIcon, R.drawable.main_selected, R.drawable.course_selected, R.drawable.vip_selected, R.drawable.data_selected, R.drawable.mine_selected);
        Collections.addAll(tabContent, "主页", "课程", "VIP", "资料", "我的");
        tabView.setResource(normalIcon, selectedIcon, tabContent, 1);
        bottomTab.setOnBottomTabClickCallBack(new BottmTab.OnBottomTabClickCallBack() {
            @Override
            public void clickTab(int tab) {
                switch (tab) {
                    case MAIN_PAGE:
                        navController.navigate(R.id.homePageFragment);
                        break;
                    case COURSE:
                        navController.navigate(R.id.courseFragment);
                        break;
                    case VIP:
                        navController.navigate(R.id.memberFragment);
                        break;
                    case DATA:
                        navController.navigate(R.id.datumFragment);
                        break;
                    case MINE:
                        navController.navigate(R.id.myFragment);
                        break;
                }
            }
        });
    }

    @Override
    public void setUpData() {
        navController = Navigation.findNavController(getView().findViewById(R.id.home_fragment_container));
        navController.addOnDestinationChangedListener(this);

    }


    @Override
    public void netSuccess(int whichApi, Object[] d) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        normalIcon.clear();
        selectedIcon.clear();
        tabContent.clear();
    }

    @Override
    public void clickTab(int tab) {


    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        showLog(destination.getLabel().toString());
    }


    @OnClick(R.id.select_subject)
    public void onClick() {
        startActivity(new Intent(getActivity(), SubjectActivity.class));
    }
}
