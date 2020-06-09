package com.example.zhulong.model;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.frame.ApiConfig;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.FrameApplication;
import com.example.frame.Host;
import com.example.frame.NetManger;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.R;
import com.example.zhulong.base.Application1907;
import com.example.zhulong.constants.Method;


public class AccountModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();


    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.SEND_VERIFY:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user)).getLoginVerify((String) params[0]), connectionPersenter, whichApi);
                break;
            case ApiConfig.VERIFY_LOGIN:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user)).loginByVerify(new ParamHashMap().add("mobile",params[0]).add("code",params[1])),connectionPersenter,whichApi);
                break;
            case ApiConfig.GET_HEADER_INFO:
                String uid = FrameApplication.getFrameApplication().getLoginInfo().getUid();
                mManger.netWork(NetManger.mService.getHeaderInfo(Host.PASSPORT_API+ Method.GETUSERHEADERFORMOBILE,new ParamHashMap().add("zuid",uid).add("uid",uid)),connectionPersenter,whichApi);
                break;
        }
    }
}
