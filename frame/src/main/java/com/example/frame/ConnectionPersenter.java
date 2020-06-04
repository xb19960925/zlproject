package com.example.frame;

import io.reactivex.disposables.Disposable;

public interface ConnectionPersenter<P> extends ConnectionView{

    void getData(int whichApi,P... p);

    void addObserver(Disposable pDisposable);
}
