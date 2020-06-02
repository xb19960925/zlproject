package com.example.zhulong.base;

import android.content.Context;

import com.example.frame.FrameApplication;

public class Application1907 extends FrameApplication {
    private static Application1907 mApplication1907;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication1907 = this;

    }


    public static Application1907 getApplication(){
        return getApplication();
    }

   public static Context get07ApplicationContext(){
        return mApplication1907.getApplicationContext();
    }
}
