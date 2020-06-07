package com.example.zhulong.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.frame.ApiConfig;
import com.example.frame.ApiService;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.NetManger;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.R;
import com.example.zhulong.base.Application1907;

public class CourseModel implements ConnectionModel {
    private NetManger mManger = NetManger.getInstance();
    private Context mContext = Application1907.get07ApplicationContext();
    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.GET_COURSE_INFO:
                //final int loadType = (int) params[1];
                ParamHashMap map = new ParamHashMap().add("page", 1).add("limit", 15).add("course_type", 1);
                if (!TextUtils.isEmpty((String) params[0])) map.add("specialty_id", "1");
                mManger.netWork(mManger.getService(mContext.getString(R.string.edu_openapi)).getCourseData(map), connectionPersenter, whichApi);
                break;
        }
    }
}
