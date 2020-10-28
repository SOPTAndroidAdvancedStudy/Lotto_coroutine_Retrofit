package com.example.lotto.data.repository

import com.example.lotto.data.api.RetrofitHelper

class MainRepository(private val retrofitHelper : RetrofitHelper) {

    suspend fun getLottoCoroutines() = retrofitHelper.getLottoCoroutines()

}