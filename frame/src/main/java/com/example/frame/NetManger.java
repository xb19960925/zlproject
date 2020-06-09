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
    public static ApiService mService;

    private NetManger() {
    }

    private static volatile NetManger sNetManger;

    public static NetManger getInstance() {
        if (sNetManger == null) {
            synchronized (NetManger.class) {
                sNetManger = new NetManger();
                mService = getService();
            }
        }
        return sNetManger;
    }

    private static ApiService getService() {
        return new Retrofit.Builder()
                .baseUrl(Host.AD_OPENAPI)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initClient())
                .build()
                .create(ApiService.class);
    }

    /**
     * @param t   如果传baseURL，用传过来，没传的话用默认
     * @param <T>
     * @return
     */
    public <T> ApiService getService(T... t) {
        String baseUrl = Host.AD_OPENAPI;
        if (t != null && t.length != 0) {
            baseUrl = (String) t[0];
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initClient())
                .build()
                .create(ApiService.class);
    }

    private static OkHttpClient initClient() {
        return new OkHttpClient().newBuilder()
                .addInterceptor(new CommonHeadersInterceptor())
                .addInterceptor(new CommonParamsInterceptor())
                .addInterceptor(initLogInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private static Interceptor initLogInterceptor() {
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);
        return log;
    }

    /**
     * 使用observer观察者，抽取出网络请求及切换线程的过程
     *
     * @param localTestInfo
     * @param pPresenter
     * @param whichApi
     * @param o
     * @param <T>
     * @param <O>
     */
    public <T, O> void netWork(Observable<T> localTestInfo, final ConnectionPersenter pPresenter, final int whichApi, O... o) {
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseOberver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        pPresenter.addObserver(d);
                    }

                    @Override
                    public void onSuccess(Object value) {
                        pPresenter.success(whichApi, value, o != null && o.length == 1 ? o[0] : o);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        pPresenter.error(whichApi, e);
                    }
                });
    }

    /**
     * 使用consumer观察者，抽取出网络请求及切换线程的过程
     *
     * @param localTestInfo
     * @param pPresenter
     * @param whichApi
     * @param dataType
     * @param o
     * @param <T>
     * @param <O>
     */
    public <T, O> void netWorkByConsumer(Observable<T> localTestInfo, final ConnectionPersenter pPresenter, final int whichApi, final int dataType, O... o) {
        Disposable subscribe = localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pT -> pPresenter.success(whichApi, dataType, pT, o), pThrowable -> pPresenter.error(whichApi, pThrowable));
        pPresenter.addObserver(subscribe);
    }
}
