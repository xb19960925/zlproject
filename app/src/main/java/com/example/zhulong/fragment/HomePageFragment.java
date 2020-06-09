package com.example.zhulong.fragment;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.BannerLiveInfo;
import com.example.data.BaseInfo;
import com.example.data.HomePageBean;
import com.example.data.IndexCommondEntity;
import com.example.frame.ApiConfig;
import com.example.frame.LoadTypeConfig;
import com.example.zhulong.R;
import com.example.zhulong.adapter.HomeLiveAdapter;
import com.example.zhulong.base.BaseMvpFragment;
import com.example.zhulong.interfaces.DataListener;
import com.example.zhulong.model.HomePageModel;
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
public class HomePageFragment extends BaseMvpFragment<HomePageModel> implements DataListener {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int currentPage = 1;
    private List<IndexCommondEntity> bottomList = new ArrayList<>();
    private List<String> bannerData = new ArrayList<>();
    private List<BannerLiveInfo.Live> liveData = new ArrayList<>();
    private HomeLiveAdapter homeLiveAdapter;


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
        initRecyclerView(recyclerView, refreshLayout, new LinearLayoutManager(getActivity()),this);
        homeLiveAdapter = new HomeLiveAdapter(getActivity(), bottomList, bannerData, liveData);
        recyclerView.setAdapter(homeLiveAdapter);
    }

    @Override
    public void setUpData() {
        persenter.getData(ApiConfig.HOME_PAGE_DATA, LoadTypeConfig.NORMAL, currentPage);
        persenter.getData(ApiConfig.HOME_BANNER_LIVE, LoadTypeConfig.NORMAL);
    }
    private boolean mainList = false, banLive = false;
    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.HOME_PAGE_DATA:
//                int loadMode = (int) ((Object[]) pD[1])[0];
                int loadMode = (int) pD[1];
                BaseInfo<List<IndexCommondEntity>> baseInfo = (BaseInfo<List<IndexCommondEntity>>) pD[0];
                if (baseInfo.isSuccess()) {
                    if (loadMode == LoadTypeConfig.MORE) refreshLayout.finishLoadMore();
                    if (loadMode == LoadTypeConfig.REFRESH) {
                        bottomList.clear();
                        refreshLayout.finishRefresh();
                    }
                    bottomList.addAll(baseInfo.result);
                    mainList = true;
                    if (banLive) {
                        homeLiveAdapter.notifyDataSetChanged();
                        mainList = false;
                    }
                } else showToast("列表加载错误");
                break;
            case ApiConfig.HOME_BANNER_LIVE:
                JsonObject jsonObject = (JsonObject) pD[0];
                try {
                    JSONObject object = new JSONObject(jsonObject.toString());
                    if (object.getString("errNo").equals("0")) {
//                        int load = (int) ((Object[]) pD[1])[0];
                        int load = (int)pD[1];
                        if (load == LoadTypeConfig.REFRESH) {
                            bannerData.clear();liveData.clear();
                        }
                        String result = object.getString("result");
                        JSONObject resultObject = new JSONObject(result);
                        String live = resultObject.getString("live");
                        //如果该字段是以boolean类型返回的，有两种处理方式 1：写两个实体类，一个live类型是Boolean，一个是list 2：当这个字段为Boolean类型时，将其干掉
                        if (live.equals("true") || live.equals("false")) {
                            resultObject.remove("live");
                        }
                        result = resultObject.toString();
                        Gson gson = new Gson();
                        BannerLiveInfo info = gson.fromJson(result, BannerLiveInfo.class);
                        for (BannerLiveInfo.Carousel data : info.Carousel) {
                            bannerData.add(data.thumb);
                        }
                        if(info.live != null )liveData.addAll(info.live);
                        banLive = true;
                        if (mainList) {
                            homeLiveAdapter.notifyDataSetChanged();
                            banLive = false;
                        }
                    }
                } catch (JSONException pE) {
                    pE.printStackTrace();
                }
                break;
        }
    }


    @Override
    public void dataType(int mode) {
        if (mode == LoadTypeConfig.REFRESH) {
            mainList = false;
            banLive = false;
            persenter.getData(ApiConfig.HOME_PAGE_DATA, LoadTypeConfig.REFRESH, 1);
            persenter.getData(ApiConfig.HOME_BANNER_LIVE, LoadTypeConfig.REFRESH);
        } else {
            currentPage++;
            persenter.getData(ApiConfig.HOME_PAGE_DATA, LoadTypeConfig.MORE, currentPage);
        }
    }
}
