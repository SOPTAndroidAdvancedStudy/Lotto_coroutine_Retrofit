package com.example.lotto.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val URL = "https://www.dhlottery.co.kr"

    // Retrofit 객체
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitService : RetrofitService = getRetrofit().create(RetrofitService::class.java)
}