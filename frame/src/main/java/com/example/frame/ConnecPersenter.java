package com.example.frame;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class ConnecPersenter<V extends ConnectionView,M extends ConnectionModel> implements ConnectionPersenter {
    private SoftReference<V> view;
    private  SoftReference<M> connectionModel;
    private List<Disposable> mDisposableList;

        public ConnecPersenter(V view,M connectionModel) {
            this.view=new SoftReference<>(view);
            this.connectionModel=new SoftReference<>(connectionModel);
            mDisposableList=new ArrayList<>();
    }

    @Override
    public void getData(int whichApi, Object... p) {
        if (connectionModel!=null&&connectionModel.get()!=null)connectionModel.get().getNetData(this, whichApi, p);
    }

    @Override
    public void addObserver(Disposable pDisposable) {
        if (mDisposableList == null) return;
        mDisposableList.add(pDisposable);
    }

    @Override
    public void success(int whichApi, Object... d) {
       if (view!=null&&view.get()!=null)view.get().success(whichApi, d);
    }

    @Override
    public void error(int whichApi, Throwable pThrowable) {
        if (view!=null&&view.get()!=null)view.get().error(whichApi, pThrowable);
    }
    public void clear(){
        for (Disposable dis:mDisposableList) {
            if (dis != null && !dis.isDisposed())dis.dispose();
        }
        if (view != null){
            view.clear();
            view = null;
        }
        if (connectionModel != null){
            connectionModel.clear();
            connectionModel = null;
        }
    }
}
