package com.example.zhulong.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.BaseInfo;
import com.example.data.HomePageBannerBean;
import com.example.data.HomePageBean;
import com.example.data.SpecialtyChooseEntity;
import com.example.frame.ApiConfig;
import com.example.frame.LoadTypeConfig;
import com.example.frame.constants.ConstantKey;
import com.example.zhulong.R;
import com.example.zhulong.adapter.HomeListAdapter;
import com.example.zhulong.adapter.HomeLiveAdapter;
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.design.BottmTab;
import com.example.zhulong.model.HomePageModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends BaseMvpFragment<HomePageModel> {

    @BindView(R.id.home_page_banner)
    Banner homePageBanner;
    @BindView(R.id.bottom_tab)
    BottmTab bottomTab;
    @BindView(R.id.home_page_live_broadcast_list)
    RecyclerView homePageLiveBroadcastList;
    @BindView(R.id.home_page_line)
    View homePageLine;
    @BindView(R.id.home_page_list)
    RecyclerView homePageList;
    @BindView(R.id.home_page_samrt)
    SmartRefreshLayout homePageSamrt;
    @BindView(R.id.tv_live_home_all)
    TextView tvLiveHomeAll;
    private List<Integer> normalIcon = new ArrayList<>();//为选中的icon
    private List<Integer> selectedIcon = new ArrayList<>();// 选中的icon
    private List<String> tabContent = new ArrayList<>();//tab对应的内容


    private SpecialtyChooseEntity.DataBean selectedInfo;
    int page = 0;
    private HomePageBannerBean.ResultBean result;
    private HomeLiveAdapter homeLiveAdapter;
    private HomeListAdapter homeListAdapter;

    @Override
    public HomePageModel setModel() {
        return new HomePageModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_home_page;
    }

    @Override
    public void setUpView() {
        initRecyclerView(homePageLiveBroadcastList, homePageSamrt, new GridLayoutManager(getActivity(), 2), null);
        initRecyclerView(homePageList, homePageSamrt, new LinearLayoutManager(getActivity()), mode -> {
            if (mode == LoadTypeConfig.REFRESH) {
                page = 0;
                persenter.getData(ApiConfig.HOME_PAGE_DATA, selectedInfo.getSpecialty_id(), page, 10);
            } else {
                page++;
                persenter.getData(ApiConfig.HOME_PAGE_DATA, selectedInfo.getSpecialty_id(), page, 10);
            }
        });
        setBottomView();
        homeLiveAdapter = new HomeLiveAdapter(getActivity());
        homeListAdapter = new HomeListAdapter(getActivity());
        homePageLiveBroadcastList.setAdapter(homeLiveAdapter);
        homePageList.setAdapter(homeListAdapter);
    }

    private void setBottomView() {
        BottmTab tabView = getView().findViewById(R.id.bottom_tab);
        Collections.addAll(normalIcon, R.drawable.ic_shortcuts_1, R.drawable.ic_shortcuts_6, R.drawable.ic_shortcuts_3, R.drawable.ic_shortcuts_4, R.drawable.ic_shortcuts_5);
        Collections.addAll(selectedIcon, R.drawable.ic_shortcuts_1, R.drawable.ic_shortcuts_6, R.drawable.ic_shortcuts_3, R.drawable.ic_shortcuts_4, R.drawable.ic_shortcuts_5);
        Collections.addAll(tabContent, "训练营", "图片库", "论坛", "问答", "礼品");
        tabView.setResource(normalIcon, selectedIcon, tabContent, 0);
//        bottomTab.setOnBottomTabClickCallBack(new BottmTab.OnBottomTabClickCallBack() {
//            @Override
//            public void clickTab(int tab) {
//                switch (tab) {
//                    case MAIN_PAGE:
//                        navController.navigate(R.id.homePageFragment);
//                        break;
//                    case COURSE:
//                        navController.navigate(R.id.courseFragment);
//                        break;
//                    case VIP:
//                        navController.navigate(R.id.memberFragment);
//                        break;
//                    case DATA:
//                        navController.navigate(R.id.datumFragment);
//                        break;
//                    case MINE:
//                        navController.navigate(R.id.myFragment);
//                        break;
//                }
//            }
//        });
    }

    @Override
    public void setUpData() {
        selectedInfo = SharedPrefrenceUtils.getObject(getActivity(), ConstantKey.SUBJECT_SELECT);
        
        persenter.getData(ApiConfig.HOME_BANNER_LIVE, selectedInfo.getSpecialty_id(), 1, 1, 1);
        persenter.getData(ApiConfig.HOME_PAGE_DATA, selectedInfo.getSpecialty_id(), page, 10);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.HOME_BANNER_LIVE:
                HomePageBannerBean homePageBannerBean = (HomePageBannerBean) pD[0];
                result = homePageBannerBean.getResult();
                homePageBanner.setImages(result.getCarousel()).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomePageBannerBean.ResultBean.CarouselBean imgPath = (HomePageBannerBean.ResultBean.CarouselBean) path;
                        Glide.with(context).load(imgPath.getImg()).into(imageView);
                    }
                }).start();
                List<HomePageBannerBean.ResultBean.NoliveBean> nolive = homePageBannerBean.getResult().getNolive();
                homeLiveAdapter.setNolive(nolive);
                break;
            case ApiConfig.HOME_PAGE_DATA:
                BaseInfo<HomePageBean> homePageBeanBaseInfo = (BaseInfo<HomePageBean>) pD[0];
                List<HomePageBean.ResultBean> result = homePageBeanBaseInfo.result.getResult();
                //                if (loadType == LoadTypeConfig.MORE) {
//                    courseAdapter.setResult(result);
//                    smart.finishLoadMore();
//                } else if (loadType == LoadTypeConfig.REFRESH) {
//                    courseAdapter.setResult(result);
//                    smart.finishRefresh();
//                } else {
                homeListAdapter.setResult(result);
//                }
                break;
        }
    }


}
