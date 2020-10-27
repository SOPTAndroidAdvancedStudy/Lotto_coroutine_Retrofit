package com.example.lotto.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        private val URL = "https://www.dhlottery.co.kr"

        // Retrofit 객체
        private val retrofit =
            Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        // RetrofitService 인터페이스를 retrofit 객체와 연결
        fun getService() : RetrofitService = retrofit.create(RetrofitService::class.java)
    }
}