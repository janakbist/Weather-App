package com.example.weatherapp
import retrofit2.Call
import  retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherApiEndPoint {
        @GET("v1/current.json?key=03f5b0053c764bf8879154504231704&q=kathmandu&aqi=no")
        fun getWeather(): Call<WeatherResponse>

}
interface WeatherApiEndPointByInput {
        @GET("current.json")
         fun getCurrentWeather(
                @Query("key") apiKey: String,
                @Query("q") location: String,
                @Query("aqi") aqi: String = "no"
        ): Call <WeatherResponse>
}