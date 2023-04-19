package com.example.weatherapp

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.search.SearchBar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val search =findViewById<androidx.appcompat.widget.SearchView>(R.id.search)
        getWeatherData()

        search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity,"$query",Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        //control+ shift +space =  for suggestion


    }

    private fun getWeatherData() {
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Loading..")
        progressDialog.show()
            RetrofitInstance.api.getWeather().enqueue(object : Callback<WeatherResponse?> {
                override fun onResponse(
                    call: Call<WeatherResponse?>,
                    response: Response<WeatherResponse?>
                ) {
                    val temp = findViewById<TextView>(R.id.temp)
                    val city = findViewById<TextView>(R.id.city)
                    val condition = findViewById<TextView>(R.id.condition)
                    val weatherPic = findViewById<ImageView>(R.id.weatherpic)

                    progressDialog.dismiss()
                    temp.text = "Temperature : " + response.body()?.current?.temp_c.toString() + " deg Celcius"
                    city.text  = "Condition : " + response.body()?.location?.name.toString()
                    condition.text = "Weather : " + response.body()?.current?.condition?.text.toString()
                    Picasso.get().load(response.body()?.current?.condition?.icon).into(weatherPic)

                }

                override fun onFailure(call: Call<WeatherResponse?>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"${t.localizedMessage}",Toast.LENGTH_LONG).show()

                }
            })


    }
}