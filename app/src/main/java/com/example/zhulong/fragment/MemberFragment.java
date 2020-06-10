package com.example.zhulong.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BannerLiveInfo;
import com.example.data.BaseInfo;
import com.example.data.IndexCommondEntity;
import com.example.data.VipPageList;
import com.example.frame.ApiConfig;
import com.example.frame.LoadTypeConfig;
import com.example.zhulong.R;
import com.example.zhulong.adapter.MemberListAdapter;
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.interfaces.DataListener;
import com.example.zhulong.model.MemberModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends BaseMvpFragment<MemberModel> implements DataListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MemberListAdapter memberListAdapter;

    @Override
    public MemberModel setModel() {
        return new MemberModel();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_member;
    }

    @Override
    public void setUpView() {
        initRecyclerView(recyclerView, refreshLayout, new LinearLayoutManager(getContext()),this);
        memberListAdapter = new MemberListAdapter(getActivity());
        recyclerView.setAdapter(memberListAdapter);
    }

    @Override
    public void setUpData() {
//        persenter.getData(ApiConfig.VIP_BANNER_LIVE,LoadTypeConfig.NORMAL);
//        persenter.getData(ApiConfig.VIP_PAGE_DATA,LoadTypeConfig.NORMAL);
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.VIP_BANNER_LIVE:
                BaseInfo<BannerLiveInfo> bannerLiveInfoBaseInfo = (BaseInfo<BannerLiveInfo>) pD[0];
                List<BannerLiveInfo.Carousel> carousel = bannerLiveInfoBaseInfo.result.Carousel;
                List<BannerLiveInfo.Live> live = bannerLiveInfoBaseInfo.result.live;
                memberListAdapter.setCarousel(carousel);
                memberListAdapter.setLive(live);
                break;
            case ApiConfig.VIP_PAGE_DATA:
                BaseInfo<VipPageList> vipPageListBaseInfo = (BaseInfo<VipPageList>) pD[0];
                List<VipPageList.ResultBean.ListBean> list = vipPageListBaseInfo.result.getResult().getList();
                memberListAdapter.setList(list);
                break;
        }
    }

    @Override
    public void dataType(int mode) {

    }
}
