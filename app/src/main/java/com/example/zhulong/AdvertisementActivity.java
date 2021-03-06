package com.example.zhulong;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.example.data.BaseInfo;
import com.example.data.LoginInfo;
import com.example.data.MainAdEntity;
import com.example.data.SpecialtyChooseEntity;
import com.example.frame.ApiConfig;
import com.example.frame.constants.ConstantKey;
import com.example.frame.secret.SystemUtils;
import com.example.zhulong.base.BaseAdvertMvpActivity;
import com.example.zhulong.model.LauchModel;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;

import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.zhulong.constants.JumpConstant.*;

public class AdvertisementActivity extends BaseAdvertMvpActivity {

    private int num = 5;
    private BaseInfo<MainAdEntity> info;
    private Disposable subscribe;
    private SpecialtyChooseEntity.DataBean selectedInfo;

    @Override
    protected LauchModel setModel() {
        return new LauchModel();
    }

    @Override
    protected void initData() {
        selectedInfo = SharedPrefrenceUtils.getObject(this, ConstantKey.SUBJECT_SELECT);
        String specialtyId = "";
        if (selectedInfo != null && !TextUtils.isEmpty(selectedInfo.getSpecialty_id())) {
            application.setSelectedInfo(selectedInfo);
            specialtyId = selectedInfo.getSpecialty_id();
        }
        Point realSize = SystemUtils.getRealSize(this);
        persenter.getData(ApiConfig.ADVERT, specialtyId, realSize.x, realSize.y);
        new Handler().postDelayed(() -> {
            if (info == null) skipActivity();
        }, 3000);
        LoginInfo loginInfo = SharedPrefrenceUtils.getObject(this, ConstantKey.LOGIN_INFO);
        if (loginInfo != null && !TextUtils.isEmpty(loginInfo.getUid()))
            application.setLoginInfo(loginInfo);
    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initDevice();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_advertisement;
    }

    @Override
    public void netSuccess(int whichApi, Object[] d) {
        info = (BaseInfo<MainAdEntity>) d[0];
        GlideUtil.loadImage(advertImage, info.result.getInfo_url());
        tvSkip.setVisibility(View.VISIBLE);
        goTime();
    }

    private void goTime() {
        subscribe = Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(pLong -> {
                    if (num - pLong > 0) tvSkip.setText(num - pLong + "s");
                    else skipActivity();
                });
    }

    public void skipActivity() {
        if (subscribe != null)subscribe.dispose();
        Observable.just("我是防抖动").debounce(20, TimeUnit.MILLISECONDS).subscribe(p->{
            if (selectedInfo != null && !TextUtils.isEmpty(selectedInfo.getSpecialty_id())) {
                if (application.isLogin()) {
                    startActivity(new Intent(AdvertisementActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(AdvertisementActivity.this, LoginActivity.class).putExtra(JUMP_KEY, SPLASH_TO_LOGIN));
                }
            } else {
                startActivity(new Intent(AdvertisementActivity.this, SubjectActivity.class).putExtra(JUMP_KEY, SPLASH_TO_SUB));
            }
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe != null && !subscribe.isDisposed()) subscribe.dispose();
    }

    @OnClick({R.id.advert_image, R.id.tv_skip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.advert_image:
                if (info != null) {
//                    mInfo.result.getJump_url();
                }
                break;
            case R.id.tv_skip:
                skipActivity();
                break;
        }
    }
}
