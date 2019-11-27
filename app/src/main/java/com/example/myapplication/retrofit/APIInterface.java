package com.example.myapplication.retrofit;


import retrofit2.Call;
import retrofit2.http.*;

public interface APIInterface {
    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("/api/users")
    Call<User2> createUser(@Body User2 user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
