package com.example.zhulong.base;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.example.frame.ConnecPersenter;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionView;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity<M extends ConnectionModel> extends BaseActivity implements ConnectionView {
    private M model;
    public ConnecPersenter persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        ButterKnife.bind(this);
        model=setModel();
        persenter = new ConnecPersenter<>(this, model);
        initView();
        initData();
    }

    protected abstract M setModel();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int setLayout();

    public abstract void netSuccess(int whichApi, Object[] d);

    public  void netFailed(int whichApi, Throwable pThrowable){}

    @Override
    public void success(int whichApi, Object[] d) {
        netSuccess(whichApi, d);
    }

    @Override
    public void error(int whichApi, Throwable pThrowable) {
        showLog("net work error:"+whichApi+"error content:"+pThrowable!=null&& !TextUtils.isEmpty(pThrowable.getMessage())?pThrowable.getMessage():"不名错误类型");
        netFailed(whichApi, pThrowable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.clear();
    }
}
