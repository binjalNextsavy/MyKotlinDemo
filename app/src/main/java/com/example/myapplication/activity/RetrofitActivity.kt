package com.example.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.forJson.DemoForJson
import com.example.myapplication.R
import com.example.myapplication.WeatherResponse
import com.example.myapplication.rest.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {

    private var weatherData : TextView ?= null
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        weatherData = findViewById(R.id.textViewForRetrofit)

        findViewById<Button>(R.id.buttonForRetrofit).setOnClickListener { getCurrentdata() }

        findViewById<Button>(R.id.jsonfetch).setOnClickListener {
            intent = Intent(this,DemoForJson::class.java)
            startActivity(intent)
        }


        findViewById<Button>(R.id.gsonFetch).setOnClickListener {
            intent = Intent(this,DemoForGson2::class.java)
            startActivity(intent)
        }
    }

    private fun getCurrentdata() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(
            lat,
            lon,
            AppId
       )
        call.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>){

                if(response.code() == 200){

                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country:  "+ weatherResponse.sys!!.country + "\n" +
                    "Temperature:  "+ weatherResponse.main!!.temp + "\n" +
                    "Min_Temp:  "+ weatherResponse.main!!.temp_min + "\n" +
                    "Max_Temp:  "+ weatherResponse.main!!.temp_max + "\n" +
                    "Humidity:  "+ weatherResponse.main!!.humidity + "\n" +
                    "Pressure:  "+ weatherResponse.main!!.pressure + "\n"

                    weatherData!!.text = stringBuilder


                }
            }

            override fun onFailure(call : Call<WeatherResponse>, t:Throwable){
                weatherData!!.text = t.message
            }

        })

    }

    companion object{

       // var BaseUrl = "http://api.openweathermap.org/"
        var BaseUrl = "http://api.openweathermap.org/"
        //var AppId = "2e65127e909e178d0af311a81f39948c"
        var AppId = "2e65127e909e178d0af311a81f39948c"
        var lat = "20"
        var lon = "78"
    }
}
