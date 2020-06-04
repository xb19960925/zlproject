package com.example.frame;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManger {
    private NetManger() {
    }

    private static volatile NetManger sNetManger;

    public static NetManger getInstance() {
        if (sNetManger == null) {
            synchronized (NetManger.class) {
                sNetManger = new NetManger();
            }
        }
        return sNetManger;
    }

    public <T> ApiService getService(T... t) {
        String baseUrl = ServerAddressConfig.BASE_URL;
        if (t != null && t.length != 0) {
            baseUrl = (String) t[0];
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initInterceptor())
                .build()
                .create(ApiService.class);
    }

    private OkHttpClient initInterceptor() {
        return new OkHttpClient.Builder()
                .addInterceptor(new CommonParamsInterceptor())
                .addInterceptor(new CommonHeadersInterceptor())
                .addInterceptor(initLogIntentceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15,TimeUnit.SECONDS)
                .build();
    }

    private Interceptor initLogIntentceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public <T, O> void netWork(Observable<T> localTestInfo, final ConnectionPersenter pPresenter, final int whichApi, O... o) {
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseOberver() {
                    @Override
                    public void onSuccess(Object value) {
                        pPresenter.success(whichApi, value, o);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        pPresenter.error(whichApi, e);
                    }
                });
    }
    public <T, O> void netWorkByConsumer(Observable<T> localTestInfo, final ConnectionPersenter pPresenter, final int whichApi, O... o) {
        Disposable subscribe = localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pT -> pPresenter.success(whichApi,pT,o), pThrowable -> pPresenter.error(whichApi,pThrowable));
        pPresenter.addObserver(subscribe);
    }
}
