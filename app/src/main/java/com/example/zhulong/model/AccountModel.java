package com.example.zhulong.model;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.data.LoginInfo;
import com.example.data.ThirdLoginData;
import com.example.frame.ApiConfig;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.FrameApplication;
import com.example.frame.Host;
import com.example.frame.NetManger;
import com.example.frame.constants.ConstantKey;
import com.example.frame.secret.RsaUtil;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.R;
import com.example.zhulong.base.Application1907;
import com.example.zhulong.constants.Method;


public class AccountModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();


    @Override
    public void getNetData(ConnectionPersenter pPresenter, int whichApi, Object[] params) {
        switch (whichApi) {
            case ApiConfig.SEND_VERIFY:
                mManger.netWork(NetManger.mService.getLoginVerify(Host.PASSPORT_OPENAPI_USER + Method.LOGINBYMOBILECODE, (String) params[0]), pPresenter, whichApi);
                break;
            case ApiConfig.VERIFY_LOGIN:
                mManger.netWork(mManger.getService(mContext.getString(R.string.passport_openapi_user)).loginByVerify(new ParamHashMap().add("mobile", params[0]).add("code", params[1])), pPresenter, whichApi);
                break;
            case ApiConfig.GET_HEADER_INFO:
                LoginInfo loginInfo = FrameApplication.getFrameApplication().getLoginInfo();
                ParamHashMap paramHashMap = new ParamHashMap();
                if(loginInfo!=null){
                    String uid = loginInfo.getUid();
                    if(uid!=null) paramHashMap.add("zuid",uid).add("uid",uid);
                }
                mManger.netWork(NetManger.mService.getHeaderInfo(Host.PASSPORT_API+ Method.GETUSERHEADERFORMOBILE,paramHashMap),pPresenter,whichApi);

//                String uid = FrameApplication.getFrameApplication().getLoginInfo().getUid();
//                mManger.netWork(NetManger.mService.getHeaderInfo(Host.PASSPORT_API + Method.GETUSERHEADERFORMOBILE, new ParamHashMap().add("zuid", uid).add("uid", uid)), pPresenter, whichApi);
                break;
            case ApiConfig.REGISTER_PHONE:
                mManger.netWork(NetManger.mService.checkVerifyCode(Host.PASSPORT_API + Method.CHECKMOBILECODE, new ParamHashMap().add("mobile", params[0]).add("code", params[1])), pPresenter, whichApi);
                break;
            case ApiConfig.CHECK_PHONE_IS_USED:
                mManger.netWork(NetManger.mService.checkPhoneIsUsed(Host.PASSPORT_API + Method.CHECKMOBILEISUSE, params[0]), pPresenter, whichApi);
                break;
            case ApiConfig.SEND_REGISTER_VERIFY:
                mManger.netWork(NetManger.mService.sendRegisterVerify(Host.PASSPORT_API + Method.SENDMOBILECODE, params[0]), pPresenter, whichApi);
                break;
            case ApiConfig.NET_CHECK_USERNAME:
                mManger.netWork(NetManger.mService.checkName(Host.PASSPORT + Method.USERNAMEISEXIST, params[0]), pPresenter, whichApi);
                break;
            case ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT:
                ParamHashMap param = new ParamHashMap().add("username", params[0]).add("password", RsaUtil.encryptByPublic((String) params[1]))
                        .add("tel", params[2]).add("specialty_id", FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id())
                        .add("province_id", 0).add("city_id", 0).add("sex", 0).add("from_reg_name", 0).add("from_reg", 0);
                mManger.netWork(NetManger.mService.registerCompleteWithSubject(Host.PASSPORT_API + Method.USERREGFORSIMPLE, param), pPresenter, whichApi);
                break;
            case ApiConfig.ACCOUNT_LOGIN:
                ParamHashMap add = new ParamHashMap().add("ZLSessionID", "").add("seccode", "").add("loginName", params[0])
                        .add("passwd", RsaUtil.encryptByPublic((String) params[1])).add("cookieday", "")
                        .add("fromUrl", "android").add("ignoreMobile", "0");
                mManger.netWork(NetManger.mService.loginByAccount(Host.PASSPORT_OPENAPI + Method.USERLOGINNEWAUTH, add), pPresenter, whichApi);
                break;
            case ApiConfig.GET_WE_CHAT_TOKEN:
                ParamHashMap wxParams = new ParamHashMap().add("appid", ConstantKey.WX_APP_ID).add("secret", ConstantKey.WX_APP_SECRET).add("code", params[0]).add("grant_type", "authorization_code");
                mManger.netWork(NetManger.mService.getWechatToken(Host.WX_OAUTH + Method.ACCESS_TOKEN, wxParams), pPresenter, whichApi);
                break;
            case ApiConfig.POST_WE_CHAT_LOGIN_INFO:
                ThirdLoginData data = (ThirdLoginData) params[0];
                ParamHashMap add1 = new ParamHashMap().add("openid", data.openid).add("type", data.type).add("url", "android");
                mManger.netWork(NetManger.mService.loginByWechat(Host.PASSPORT_API + Method.THIRDLOGIN, add1), pPresenter, whichApi);
                break;
        }
    }
}
