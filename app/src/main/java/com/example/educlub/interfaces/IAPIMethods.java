package com.example.educlub.interfaces;

import com.example.educlub.pojo.Company;
import com.example.educlub.pojo.Group;
import com.example.educlub.pojo.UserInfo;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface IAPIMethods {
    @GET("/v1/additional/companies-list")
    Call<ArrayList<Company>> getCompanyList();

    @GET("/v1/additional/group-list")
    Call<ArrayList<Group>> getGroupList();

    @POST("/v1/login/login")
    @FormUrlEncoded
    Call<UserInfo> signIn(@Field("username") String login,@Field("password") String password);

    @POST("/v1/login/registration")
    @FormUrlEncoded
    Call<UserInfo> registration(@Field("login") String login,@Field("password") String password,@Field("fio") String fio,@Field("phone") String phone);

    @POST("/v1/app/joined-groups")
    @FormUrlEncoded
    Call<ArrayList<Group>> getJoinedGroupList(@Field("userId") int userId, @Header("accessToken") String token);

    @POST("/v1/app/joined-groups")
    @FormUrlEncoded
    Call<UserInfo> sendFireBaseToken(@Header("accessToken") String accessToken, @Field("fireBaseToken") String fireBaseToken);


}
