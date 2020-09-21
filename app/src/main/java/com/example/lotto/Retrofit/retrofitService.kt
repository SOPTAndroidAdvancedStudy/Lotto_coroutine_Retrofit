package com.example.lotto.Retrofit

import android.telecom.Call
import com.example.lotto.Model.LottoData
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface retrofitService {

    @GET("common.do/")
    fun getLotto(
        @Query("method") method:String,
        @Query("drwNo") drwNo : String
    ) : retrofit2.Call<LottoData>
}