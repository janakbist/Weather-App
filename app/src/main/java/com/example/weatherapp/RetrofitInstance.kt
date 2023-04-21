package com.example.weatherapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private var baseurl = "http://api.weatherapi.com/"
    val api: WeatherApiEndPoint by lazy {
            Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiEndPoint::class.java)
    }

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("http://api.weatherapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


}

private fun Retrofit.Builder.client(addInterceptor: OkHttpClient.Builder): Retrofit.Builder {
    TODO("Not yet implemented")
}
