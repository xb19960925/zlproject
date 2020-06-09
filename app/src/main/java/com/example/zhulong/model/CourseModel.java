package com.example.zhulong.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.frame.ApiConfig;
import com.example.frame.ApiService;
import com.example.frame.ConnectionModel;
import com.example.frame.ConnectionPersenter;
import com.example.frame.FrameApplication;
import com.example.frame.Host;
import com.example.frame.NetManger;
import com.example.frame.constants.Constants;
import com.example.zhulong.ParamHashMap;
import com.example.zhulong.R;
import com.example.zhulong.base.Application1907;
import com.example.zhulong.constants.Method;

public class CourseModel implements ConnectionModel {
    private String subjectId = FrameApplication.getFrameApplication().getSelectedInfo().getSpecialty_id();
    @Override
    public void getNetData(ConnectionPersenter connectionPersenter, int whichApi, Object[] params) {
        switch (whichApi){
            case ApiConfig.COURSE_CHILD:
                //final int loadType = (int) params[1];
                ParamHashMap add = new ParamHashMap().add("specialty_id", subjectId).add("page", params[2]).add("limit", Constants.LIMIT_NUM).add("course_type", params[1]);
                NetManger.getInstance().netWork(NetManger.mService.getCourseChildData(Host.EDU_OPENAPI+ Method.GETLESSONLISTFORAPI,add),connectionPersenter,whichApi,params[0]);
                break;
        }
    }
}
