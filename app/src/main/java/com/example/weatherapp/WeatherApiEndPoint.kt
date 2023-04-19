package com.example.weatherapp
import retrofit2.Call
import  retrofit2.Response
import retrofit2.http.GET
interface WeatherApiEndPoint {
        @GET("v1/current.json?key=03f5b0053c764bf8879154504231704&q=kathmandu&aqi=no")
        fun getWeather(): Call<WeatherResponse>

}