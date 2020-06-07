package com.example.zhulong.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.frame.ConnecPersenter;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseMvpFragment<M extends ConnectionModel> extends BaseFragment  implements ConnectionView {
    private M model;
    public ConnecPersenter persenter;
    private Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(setLayoutId(), container, false);
        mBind = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = setModel();
        persenter = new ConnecPersenter<>(this, model);
        setUpView();
        setUpData();
    }

    public abstract M setModel();

    public abstract int setLayoutId();

    public abstract void setUpView();

    public abstract void setUpData();

    public abstract void netSuccess(int whichApi, Object[] pD);

    public void netFailed(int whichApi, Throwable pThrowable){}

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
    public void onDestroyView() {
        super.onDestroyView();
        persenter.clear();
        if (mBind != null)mBind.unbind();
    }
}
