package com.example.zhulong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.data.BaseInfo;
import com.example.frame.ApiConfig;
import com.example.zhulong.base.BaseMvpActivity;
import com.example.zhulong.interfaces.MyTextWatcher;
import com.example.zhulong.model.AccountModel;
import com.example.zhulong.utils.CheckUserNameAndPwd;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.zhulong.constants.JumpConstant.*;

public class RegisterPhoneActivity extends BaseMvpActivity<AccountModel> {
    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.clearAccount)
    ImageView clearAccount;
    @BindView(R.id.accountContent)
    EditText accountContent;
    @BindView(R.id.visibleImage)
    ImageView visibleImage;
    @BindView(R.id.accountSecret)
    EditText accountSecret;
    @BindView(R.id.next_page)
    TextView nextPage;
    private String mPhoneNum;

    @Override
    protected AccountModel setModel() {
        return new AccountModel();
    }

    //设置标题和获取手机号
    @Override
    protected void initData() {
        titleContent.setText("创建账号");
        mPhoneNum = getIntent().getStringExtra("phoneNum");
    }

    @Override
    protected void initView() {
        //用户名的监听
        accountContent.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                //根据三目判断用户名长度大于0时，图标的显示和隐藏
                clearAccount.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
                //根据三木判断下一步的获取焦点
                nextPage.setSelected(s.length() > 0 && accountSecret.getText().length() > 0);
            }
        });
        //密码的监听
        accountSecret.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                visibleImage.setVisibility(s.length() > 0 ? View.VISIBLE : View.INVISIBLE);
                nextPage.setSelected(s.length() > 0 && accountContent.getText().length() > 0);
            }
        });
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_register_phone;
    }

    @Override
    public void netSuccess(int whichApi, Object[] pD) {
        switch (whichApi) {
            //判断用户名是否重复的回调
            case ApiConfig.NET_CHECK_USERNAME:
                BaseInfo baseInfo = (BaseInfo) pD[0];
                if (baseInfo.isSuccess()) {
                    //发送完成注册的接口
                    persenter.getData(ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT, accountContent.getText().toString(), accountSecret.getText().toString(), mPhoneNum);
                } else if (baseInfo.errNo == 114) {
                    showToast("该用户名不可用");
                } else showToast(baseInfo.msg);
                break;
                //完成注册的接口
            case ApiConfig.COMPLETE_REGISTER_WITH_SUBJECT:
                BaseInfo base = (BaseInfo) pD[0];
                //成功
                if (base.errNo == 24 || base.errNo == 114 || base.isSuccess()) {
                    showToast("注册成功");
                    //跳转到登录页面
                    startActivity(new Intent(this, LoginActivity.class).putExtra(JUMP_KEY, REGISTER_TO_LOGIN));
                    finish();
                } else showToast(base.msg);
                break;
        }
    }
    //几个点击事件
    @OnClick({R.id.back_image, R.id.clearAccount, R.id.visibleImage, R.id.next_page})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回图标的点击事件
            case R.id.back_image:
                finish();
                break;
                //清除用户名的图标点击事件
            case R.id.clearAccount:
                accountContent.setText("");
                break;
                //密码框图标的点击事件
            case R.id.visibleImage:
                //根据三目判断密码栏是否清除
                accountSecret.setTransformationMethod(visibleImage.isSelected() ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                //初始化图标
                visibleImage.setSelected(!visibleImage.isSelected());
                break;
                //下一步的点击事件
            case R.id.next_page:
                //用户名和密码的正则判断
                if (CheckUserNameAndPwd.verificationUsername(this, accountContent.getText().toString(), accountSecret.getText().toString()))
                    //发起检查用户名的接口
                    persenter.getData(ApiConfig.NET_CHECK_USERNAME, accountContent.getText().toString());
                break;
        }
    }
}
