package com.example.zhulong.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.frame.ApiConfig;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.NetManger;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.R;
import com.example.zhulong.base.Application1907;

public class HomePageModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();
    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.HOME_BANNER_LIVE:
                //final int loadType = (int) params[1];
                ParamHashMap map = new ParamHashMap().add("more_live", 1).add("new_banner", 1);
                if (!TextUtils.isEmpty((String) params[0])) map.add("pro", 1);
                mManger.netWork(mManger.getService(mContext.getString(R.string.edu_openapi)).getHomeBannerOrLiveList(map), connectionPersenter, whichApi);
                break;
            case ApiConfig.HOME_PAGE_DATA:
                ParamHashMap map1 = new ParamHashMap().add("page", 0).add("limit", 10);
                if (!TextUtils.isEmpty((String) params[0])) map1.add("specialty_id", "1");
                mManger.netWork(mManger.getService(mContext.getString(R.string.edu_openapi)).getHomePageData(map1), connectionPersenter, whichApi);
                break;
        }
    }
}
