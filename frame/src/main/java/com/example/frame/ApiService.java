package com.example.frame;


import com.example.data.BannerAndLiveVip;
import com.example.data.BaseInfo;
import com.example.data.CourseBean;
import com.example.data.CourseListInfo;
import com.example.data.DataGroupListEntity;
import com.example.data.GroupDetailEntity;
import com.example.data.IndexCommondEntity;
import com.example.data.LoginInfo;
import com.example.data.MainAdEntity;
import com.example.data.PersonHeader;
import com.example.data.SpecialtyChooseEntity;
import com.example.data.VipPageList;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

    @GET
    Observable<BaseInfo<MainAdEntity>> getAdvert(@Url String url, @QueryMap Map<String,Object> pMap);

    @GET
    Observable<BaseInfo<List<SpecialtyChooseEntity>>> getSubjectList(@Url String url);

    @GET
    Observable<BaseInfo<String>> getLoginVerify(@Url String url,@Query("mobile") String mobile);

    @GET("loginByMobileCode")
    Observable<BaseInfo<LoginInfo>> loginByVerify(@QueryMap Map<String, Object> params);


    @POST
    @FormUrlEncoded
    Observable<BaseInfo<PersonHeader>> getHeaderInfo(@Url String url,@FieldMap Map<String,Object> params);

    @GET
    Observable<BaseInfo<List<IndexCommondEntity>>> getCommonList(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    Observable<JsonObject> getBannerLive(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    Observable<BaseInfo<CourseListInfo>> getCourseChildData(@Url String url, @QueryMap Map<String,Object> params);
    @GET
    Observable<BaseInfo<BannerAndLiveVip>> getMemberPageData(@Url String url, @QueryMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo<VipPageList>> getMemberListData(@Url String url, @FieldMap Map<String,Object> params);

    @GET
    Observable<BaseInfo<List<DataGroupListEntity>>> getGroupList(@Url String url, @QueryMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo> removeFocus(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo> focus(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo> checkVerifyCode(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo> checkPhoneIsUsed(@Url String url, @Field("mobile")Object mobile);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo> sendRegisterVerify(@Url String url, @Field("mobile")Object mobile);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo> checkName(@Url String url, @Field("username")Object mobile);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo> registerCompleteWithSubject(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo<LoginInfo>> loginByAccount(@Url String url, @FieldMap Map<String,Object> params);

    @GET
    Observable<JsonObject> getWechatToken(@Url String url, @QueryMap Map<String,Object> parmas);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo<LoginInfo>> loginByWechat(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Observable<BaseInfo> bindThirdAccount(@Url String url, @FieldMap Map<String,Object> params);

    @GET
    Observable<BaseInfo<GroupDetailEntity>> getGroupDetail(@Url String url, @Query("gid") Object object);

    @GET
    Observable<BaseInfo<GroupDetailEntity>> getGroupDetailFooterData(@Url String url, @QueryMap Map<String,Object> parmas);
}
