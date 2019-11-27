package com.example.myapplication

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("coords")
    var coords : Coord?=null

    @SerializedName("weather")
    var weather = ArrayList<Weather>()

    @SerializedName("main")
    var main : Main?=null

    @SerializedName("wind")
    var wind : Wind?=null

    @SerializedName("clouds")
    var clouds : Clouds?=null

    @SerializedName("dt")
    var dt : Float = 0.toFloat()

    @SerializedName("sys")
    var sys : Sys?=null



    @SerializedName("cod")
    var cod : Float = 0.toFloat()
}



class Sys {

    @SerializedName("country")
    var country : String ?= null

    @SerializedName("sunrise")
    var sunrise: Long = 0

    @SerializedName("sunset")
    var sunset: Long = 0

}

class Clouds {

    @SerializedName("all")
    var all : Float = 0.toFloat()
}

class Wind {

    @SerializedName("speed")
    var speed : Float = 0.toFloat()

    @SerializedName("deg")
    var deg : Float = 0.toFloat()

    @SerializedName("gust")
    var gust : Float = 0.toFloat()
}

class Main {
    @SerializedName("temp")
    var temp : Float = 0.toFloat()

    @SerializedName("pressure")
    var pressure : Float = 0.toFloat()

    @SerializedName("humidity")
    var humidity : Float = 0.toFloat()

    @SerializedName("temp_min")
    var temp_min : Float = 0.toFloat()

    @SerializedName("temp_max")
    var temp_max : Float = 0.toFloat()

}



class Weather {

    @SerializedName("id")
    var id : Int = 0

    @SerializedName("main")
    var main : String?=null

    @SerializedName("description")
    var description : String ?= null

    @SerializedName("icon")
    var icon : String ?=null
}

class Coord {

    @SerializedName("lon")
    var lon : Float = 0.toFloat()

    @SerializedName("lat")
    var lat : Float = 0.toFloat()
}
