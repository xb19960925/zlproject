package com.example.zhulong;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.data.BaseInfo;
import com.example.frame.ApiConfig;
import com.example.zhulong.base.BaseMvpActivity;
import com.example.zhulong.interfaces.MyTextWatcher;
import com.example.zhulong.model.AccountModel;
import com.yiyatech.utils.newAdd.RegexUtil;
import com.yiyatech.utils.newAdd.SoftInputControl;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import razerdp.design.DialogPopup;

public class RegisterActivity extends BaseMvpActivity<AccountModel> implements DialogPopup.DialogClickCallBack {

    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.telephone_desc)
    TextView telephoneDesc;
    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.getVerification)
    TextView getVerification;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.next_page)
    TextView nextPage;
    private Disposable mTimeObserver;
    private DialogPopup mConfirmDialog;


    @Override
    protected AccountModel setModel() {
        return new AccountModel();
    }

    @Override
    protected void initData() {

    }
    //初始化页面
    @Override
    protected void initView() {
        //设置标题
        titleContent.setText("填写手机号码");
        //设置”下一步“无法点击
        nextPage.setEnabled(false);
        //验证码的监听
        password.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                //如果通过三目判断验证码是否是6位，以此来改变下一步是否可以获取焦点  注：只有TextView可以使用
                nextPage.setEnabled(s.length() == 6 ? true : false);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            //判断手机号是否可以使用的成功接口回调
            case ApiConfig.CHECK_PHONE_IS_USED:
                BaseInfo checkInfo = (BaseInfo) pD[0];
                if (checkInfo.isSuccess()){
                    //弹出一个pop告知用户是否继续
                    mConfirmDialog = new DialogPopup(this, true);
                    mConfirmDialog.setContent(userName.getText().toString()+"\n"+"是否将短信发送到该手机");
                    mConfirmDialog.setDialogClickCallBack(this);
                    mConfirmDialog.showPopupWindow();
                } else {
                    //吐丝
                    showToast("该手机已注册");
                }
                break;
                //发送验证成功的回调
            case ApiConfig.SEND_REGISTER_VERIFY:
                BaseInfo sendResult = (BaseInfo) pD[0];
                if (sendResult.isSuccess()){
                    showToast("验证码发送成功");
                    //进行验证码倒计时：使用rxjava
                    goTime();
                    //log打印错误信息
                }else showLog(sendResult.msg);
                break;
                //注册手机号成功的接口回调
            case ApiConfig.REGISTER_PHONE:
                BaseInfo info = (BaseInfo) pD[0];
                if (info.isSuccess()){
                    showToast("验证码验证成功");
                    //跳转下一个页面并让本页面出栈，同时将手机号+86以后传到下一个页面
                    startActivity(new Intent(this,RegisterPhoneActivity.class).putExtra("phoneNum",telephoneDesc.getText().toString() + userName.getText().toString().trim()));
                    finish();
                } else showLog(info.msg);
                break;
        }
    }

    @OnClick({R.id.back_image, R.id.getVerification, R.id.next_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击返回图标，直接让本页面出栈
            case R.id.back_image:
                finish();
                break;
                //点击获取验证码时：判断手机号是否为空，为空直接吐丝
            case R.id.getVerification:
                if (userName.getText() == null) {
                    showToast("请输入手机号");
                    return;
                }
                //根据正则判断手机号
                boolean phone = RegexUtil.isPhone(userName.getText().toString().trim());
                if (phone) {
                    //关闭软键盘
                    SoftInputControl.hideSoftInput(this,userName);
                    //发送判断手机号是否使用的接口
                    persenter.getData(ApiConfig.CHECK_PHONE_IS_USED,telephoneDesc.getText().toString() + userName.getText().toString().trim());
                }
                //否则吐丝
                else showToast("手机号格式错误");
                break;
            case R.id.next_page:
                //注册手机号的接口
                persenter.getData(ApiConfig.REGISTER_PHONE, telephoneDesc.getText().toString() + userName.getText().toString().trim(), password.getText().toString().trim());
                break;
        }
    }

    private int preTime = 60;

    //使用rxjava中interval操作符实现倒计时
    private void goTime() {
        mTimeObserver = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(l -> {
                    if (preTime - l > 0) {
                        getVerification.setText(preTime - l + "s");
                    } else {
                        getVerification.setText("获取验证码");
                        disPose();
                    }
                });
    }

    private void disPose() {
        //在验证码结束以后，进行解绑操作。避免出现负值
        if (mTimeObserver != null && !mTimeObserver.isDisposed()) mTimeObserver.dispose();
    }

    //在跳转到其他页面以后本页面停止的生命周期方法中，进行解绑
    @Override
    protected void onStop() {
        super.onStop();
        disPose();
    }


    //pop的确认监听
    @Override
    public void onClick(int type) {
        if (type == mConfirmDialog.OK){
            //发送验证码的接口
            persenter.getData(ApiConfig.SEND_REGISTER_VERIFY,telephoneDesc.getText().toString() + userName.getText().toString().trim());
        }
        //不管是确认还是取消，都取消pop
        mConfirmDialog.dismiss();
    }
}
