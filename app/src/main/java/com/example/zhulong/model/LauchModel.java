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


public class LauchModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();


    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.ADVERT:
                ParamHashMap map = new ParamHashMap().add("w", params[1]).add("h", params[2]).add("positions_id", "APP_QD_01").add("is_show", 0);
                if (!TextUtils.isEmpty((String) params[0])) map.add("specialty_id", params[0]);
                mManger.netWork(mManger.getService(mContext.getString(R.string.ad_openapi)).getAdvert(map), connectionPersenter, whichApi);
                break;
            case ApiConfig.SUBJECT:
                mManger.netWork(mManger.getService(mContext.getString(R.string.edu_openapi)).getSubjectList(), connectionPersenter, whichApi);
                break;
        }
    }
}
