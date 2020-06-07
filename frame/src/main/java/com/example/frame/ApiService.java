package com.example.frame;


import com.example.data.BaseInfo;
import com.example.data.CourseBean;
import com.example.data.HomePageBannerBean;
import com.example.data.HomePageBean;
import com.example.data.LoginInfo;
import com.example.data.MainAdEntity;
import com.example.data.PersonHeader;
import com.example.data.SpecialtyChooseEntity;
import com.example.data.TestInfo;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("ad/getAd")
    Observable<BaseInfo<MainAdEntity>> getAdvert(@QueryMap Map<String,Object> pMap);

    @GET("lesson/getAllspecialty")
    Observable<BaseInfo<List<SpecialtyChooseEntity>>> getSubjectList();

    @GET("loginByMobileCode")
    Observable<BaseInfo<String>> getLoginVerify(@Query("mobile") String mobile);

    @GET("loginByMobileCode")
    Observable<BaseInfo<LoginInfo>> loginByVerify(@QueryMap Map<String, Object> params);

    @POST("getUserHeaderForMobile")
    @FormUrlEncoded
    Observable<BaseInfo<PersonHeader>> getHeaderInfo(@FieldMap Map<String,Object> params);

    @POST("lesson/getCarouselphoto")
    @FormUrlEncoded
    Observable<HomePageBannerBean> getHomeBannerOrLiveList(@FieldMap Map<String,Object> params);

    @POST("lesson/getIndexCommend")
    @FormUrlEncoded
    Observable<BaseInfo<HomePageBean>> getHomePageData(@FieldMap Map<String,Object> params);

    @POST("lesson/getLessonListForApi")
    @FormUrlEncoded
    Observable<CourseBean> getCourseData(@FieldMap Map<String,Object> params);

    @POST("lesson/get_new_vip ")
    @FormUrlEncoded
    Observable<CourseBean> getMemberBannerData(@FieldMap Map<String,Object> params);

    @POST("lesson/getVipSmallLessonList")
    @FormUrlEncoded
    Observable<CourseBean> getMemberListData(@FieldMap Map<String,Object> params);
}
