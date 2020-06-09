package com.example.zhulong.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.frame.ApiConfig;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.FrameApplication;
import com.example.frame.Host;
import com.example.frame.NetManger;
import com.example.frame.constants.Constants;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.R;
import com.example.zhulong.base.Application1907;
import com.example.zhulong.constants.Method;

public class HomePageModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();

    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.HOME_PAGE_DATA:
                ParamHashMap add = new ParamHashMap().add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()).add("page", params[1]).add("limit", Constants.LIMIT_NUM).add("new_banner", 1);
                mManger.netWork(NetManger.mService.getCommonList(Host.EDU_OPENAPI+ Method.GETINDEXCOMMEND,add),connectionPersenter,whichApi,params[0]);
                break;
            case ApiConfig.HOME_BANNER_LIVE:
                ParamHashMap live = new ParamHashMap().add("pro", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()).add("more_live","1").add("is_new",1).add("new_banner",1);
                mManger.netWork(NetManger.mService.getBannerLive(Host.EDU_OPENAPI+Method.GETCAROUSELPHOTO,live),connectionPersenter,whichApi,params[0]);
                break;
        }
    }
}
