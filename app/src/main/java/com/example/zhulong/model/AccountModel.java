package com.example.zhulong.model;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.frame.ApiConfig;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.FrameApplication;
import com.example.frame.Host;
import com.example.frame.NetManger;
import com.example.frame.secret.RsaUtil;
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
            case ApiConfig.REGISTER_PHONE:
                mManger.netWork(NetManger.mService.checkVerifyCode(Host.PASSPORT_API+Method.CHECKMOBILECODE,new ParamHashMap().add("mobile",params[0]).add("code",params[1])),connectionPersenter,whichApi);
                break;
            case ApiConfig.CHECK_PHONE_IS_USED:
                mManger.netWork(NetManger.mService.checkPhoneIsUsed(Host.PASSPORT_API+Method.CHECKMOBILEISUSE,params[0]),connectionPersenter,whichApi);
                break;
            case ApiConfig.SEND_REGISTER_VERIFY:
                mManger.netWork(NetManger.mService.sendRegisterVerify(Host.PASSPORT_API+Method.SENDMOBILECODE,params[0]),connectionPersenter,whichApi);
                break;
            case ApiConfig.NET_CHECK_USERNAME:
                mManger.netWork(NetManger.mService.checkName(Host.PASSPORT+Method.USERNAMEISEXIST,params[0]),connectionPersenter,whichApi);
                break;
            case ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT:
                ParamHashMap param = new ParamHashMap().add("username", params[0]).add("password", RsaUtil.encryptByPublic((String) params[1]))
                        .add("tel", params[2]).add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id())
                        .add("province_id", 0).add("city_id", 0).add("sex", 0).add("from_reg_name", 0).add("from_reg", 0);
                mManger.netWork(NetManger.mService.registerCompleteWithSubject(Host.PASSPORT_API+Method.USERREGFORSIMPLE,param),connectionPersenter,whichApi);
                break;
        }
    }
}
