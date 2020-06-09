package com.example.zhulong.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.frame.ApiConfig;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.Host;
import com.example.frame.NetManger;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.R;
import com.example.zhulong.base.Application1907;
import com.example.zhulong.constants.Method;


public class LauchModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();


    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.ADVERT:
                ParamHashMap map = new ParamHashMap().add("w", params[1]).add("h", params[2]).add("positions_id", "APP_QD_01").add("is_show", 0);
                if (!TextUtils.isEmpty((String) params[0])) map.add("specialty_id", params[0]);
                mManger.netWork(NetManger.mService.getAdvert(Host.AD_OPENAPI+ Method.ADVERT_PATH,map), connectionPersenter, whichApi);
                break;
            case ApiConfig.SUBJECT:
                mManger.netWork(NetManger.mService.getSubjectList(Host.EDU_OPENAPI+ Method.GETALLSPECIALTY), connectionPersenter, whichApi);
                break;
        }
    }
}
