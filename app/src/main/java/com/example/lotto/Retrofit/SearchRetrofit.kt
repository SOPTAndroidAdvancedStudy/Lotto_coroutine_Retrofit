package com.example.lotto.Retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchRetrofit {

    var URL = "https://www.dhlottery.co.kr/"

    fun getService() : retrofitService = retrofit.create(retrofitService::class.java)

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
    .build()
}