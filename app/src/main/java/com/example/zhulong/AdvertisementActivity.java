package com.example.zhulong;

import android.content.Intent;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import com.example.data.BaseInfo;
import com.example.data.MainAdEntity;
import com.example.data.SpecialtyChooseEntity;
import com.example.frame.ApiConfig;
import com.example.frame.constants.ConstantKey;
import com.example.frame.secret.SystemUtils;
import com.example.zhulong.base.Application1907;
import com.example.zhulong.base.BaseAdvertMvpActivity;
import com.example.zhulong.model.AdertModel;
import com.yiyatech.utils.newAdd.GlideUtil;
import com.yiyatech.utils.newAdd.SharedPrefrenceUtils;
import java.util.concurrent.TimeUnit;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdvertisementActivity extends BaseAdvertMvpActivity {

    private int num = 5;
    private BaseInfo<MainAdEntity> info;
    private Disposable subscribe;
    private SpecialtyChooseEntity.DataBean selectedInfo;

    @Override
    protected AdertModel setModel() {
        return new AdertModel();
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
                    if (num - pLong > -1) tvSkip.setText(num - pLong + "s");
                    else {
                        skipActivity();
                        subscribe.dispose();
                    }
                });
    }

    public void skipActivity() {
        subscribe.dispose();
        startActivity(new Intent(this,selectedInfo != null && !TextUtils.isEmpty(selectedInfo.getSpecialty_id()) ? application.isLogin() ? MainActivity.class : LoginActivity.class : SubjectActivity.class ));
        finish();
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
                break;
            case R.id.tv_skip:
                skipActivity();
                break;
        }
    }
}
