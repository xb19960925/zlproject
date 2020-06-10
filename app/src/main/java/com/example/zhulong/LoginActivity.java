package com.example.zhulong;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.example.data.BaseInfo;
import com.example.data.LoginInfo;
import com.example.data.PersonHeader;
import com.example.frame.ApiConfig;
import com.example.frame.constants.ConstantKey;
import com.example.zhulong.base.BaseMvpActivity;
import com.example.zhulong.design.LoginView;
import com.example.zhulong.model.AccountModel;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.zhulong.constants.JumpConstant.*;

public class LoginActivity extends BaseMvpActivity<AccountModel> implements LoginView.LoginViewCallBack {


    @BindView(R.id.login_view)
    LoginView loginView;
    private Disposable mSubscribe;
    private String phoneNum;
    private String mFromType;
    @Override
    protected AccountModel setModel() {
        return new AccountModel();
    }

    @Override
    protected void initData() {
        mFromType = getIntent().getStringExtra(JUMP_KEY);
        loginView.setLoginViewCallBack(this);
    }

    @Override
    protected void initView() {
        loginView.setLoginViewCallBack(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void netSuccess(int whichApi, Object[] d) {
        switch (whichApi) {

            case ApiConfig.SEND_VERIFY:
                BaseInfo<String> info = (BaseInfo<String>) d[0];
                if (info.isSuccess()){
                    showToast(info.result);
                    goTime();
                } else showToast("验证码发送太频繁，请稍后重试");
                break;
            case ApiConfig.VERIFY_LOGIN:
                BaseInfo<LoginInfo> baseInfo = (BaseInfo<LoginInfo>) d[0];
                LoginInfo loginInfo = baseInfo.result;
                loginInfo.login_name = phoneNum;
                application.setLoginInfo(loginInfo);
                persenter.getData(ApiConfig.GET_HEADER_INFO);
                break;
            case ApiConfig.GET_HEADER_INFO:
                PersonHeader personHeader = ((BaseInfo<PersonHeader>) d[0]).result;
                application.getLoginInfo().personHeader = personHeader;
                SharedPrefrenceUtils.putObject(this, ConstantKey.LOGIN_INFO, application.getLoginInfo());
                jump();
                break;
        }
    }

    private void jump() {
        if (mFromType.equals(SPLASH_TO_LOGIN) || mFromType.equals(SUB_TO_LOGIN))
            startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    private long time = 60;

    private void goTime() {
        mSubscribe = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(goTime -> {
            loginView.getVerifyCode.setText(time - goTime + "s");
            if (time - goTime < 1) doPre();
        });
    }
    //取消绑定，并将获取验证码重新设置为“获取验证码”
    private void doPre() {
        if (mSubscribe != null && !mSubscribe.isDisposed()) mSubscribe.dispose();
        loginView.getVerifyCode.setText("获取验证码");
    }

    //发送验证码
    @Override
    public void sendVerifyCode(String phoneNum) {
        this.phoneNum = phoneNum;
        persenter.getData(ApiConfig.SEND_VERIFY, phoneNum);
    }

    @Override
    public void loginPress(int type, String userName, String pwd) {
        doPre();

        if (loginView.mCurrentLoginType == loginView.VERIFY_TYPE)
            persenter.getData(ApiConfig.VERIFY_LOGIN, userName, pwd);
    }

    @OnClick({R.id.close_login, R.id.logo_image, R.id.login_view, R.id.register_press, R.id.forgot_pwd, R.id.base_line, R.id.login_by_qq, R.id.login_by_wx, R.id.third_login_desc})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_login:
                if (!TextUtils.isEmpty(mFromType)&&(mFromType.equals(SUB_TO_LOGIN) || mFromType.equals(SPLASH_TO_LOGIN))){
                    startActivity(new Intent(this,MainActivity.class));
                }
                finish();
                break;
            case R.id.register_press:
                break;
            case R.id.forgot_pwd:
                break;
            case R.id.login_by_qq:
                break;
            case R.id.login_by_wx:
                break;
        }
    }
    //获取登录之后的接口
    @Override
    protected void onDestroy() {
        super.onDestroy();
        doPre();
    }
}
