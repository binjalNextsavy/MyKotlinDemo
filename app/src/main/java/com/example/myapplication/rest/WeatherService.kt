package com.example.myapplication.rest

import com.example.myapplication.model.UserLogin
import com.example.myapplication.WeatherResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit.ResponseCallback

import retrofit2.http.Query

interface WeatherService {

    //https://openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22
    //https://openweathermap.org/data/2.5/weather?lat=20&lon=78&appid=b6907d289e10d714a6e88b30761fae22    //India


    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String): Call<WeatherResponse>


    //https://reqres.in/

    //https://reqres.in/api/login
    @POST("api/login")
    fun userSignIn(@Body userLogin: UserLogin, mCallback: ResponseCallback): Call<UserLogin>//, mCallback: ResponseCallback)

}