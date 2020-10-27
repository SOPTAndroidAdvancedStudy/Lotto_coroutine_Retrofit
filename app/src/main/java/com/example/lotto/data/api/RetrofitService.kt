package com.example.lotto.data.api

import com.example.lotto.data.model.LottoData
import kotlinx.coroutines.Deferred
import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("common.do/")
    fun getLotto(
        @Query("method") method:String,
        @Query("drwNo") drwNo : String
    ) : Call<LottoData>

    // Coroutine
    suspend fun getLottos(
        @Query("method") method : String,
        @Query("drwNo")drwNo: String
    ) : Call<LottoData>

}