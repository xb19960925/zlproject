package com.example.zhulong.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.frame.ApiConfig;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.NetManger;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.R;
import com.example.zhulong.base.Application1907;

public class DatumModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();
    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {
        switch (whichApi){

        }
    }
}
