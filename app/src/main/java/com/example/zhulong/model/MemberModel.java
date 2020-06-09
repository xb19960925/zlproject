package com.example.zhulong.model;

import com.example.frame.ApiConfig;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.FrameApplication;
import com.example.frame.Host;
import com.example.frame.NetManger;
import com.example.frame.constants.Constants;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.constants.Method;

public class MemberModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();
    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {

        switch (whichApi){
            case ApiConfig.VIP_BANNER_LIVE:
                ParamHashMap add = new ParamHashMap().add("pro", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()).add("uid", FrameApplication.getFrameApplication().getLoginInfo().getUid());
                mManger.netWork(NetManger.mService.getMemberPageData(Host.EDU_OPENAPI+ Method.GET_NEW_VIP,add),connectionPersenter,whichApi,params[0]);
                break;
            case ApiConfig.VIP_PAGE_DATA:
                ParamHashMap live = new ParamHashMap().add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id()).add("sort","1").add("page",1);
                mManger.netWork(NetManger.mService.getMemberListData(Host.EDU_OPENAPI+Method.GETVIPSMALLLESSONLIST,live),connectionPersenter,whichApi,params[0]);
                break;
        }
    }
}
