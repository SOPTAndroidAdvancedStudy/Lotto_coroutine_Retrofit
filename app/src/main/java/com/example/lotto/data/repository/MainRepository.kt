package com.example.lotto.data.repository

import com.example.lotto.data.api.RetrofitHelper

class MainRepository(private val retrofitHelper : RetrofitHelper) {
    // getLottoCoroutines 함수는 helper에서의 getLottoCoroutine함수의 값을 반환
    // Repository pattern 사용 이유
    // 네트워킹을 viewModel에서 호출하는 것은 viewModel에게 과도한 업무를 시키는 결과를 초래한다.
    // 그래서 그런 업무를 부담하기 위해 repository pattern을 통해 repository에서 네트워크를 호출하고 이를 필요할 때 반환한다.
    suspend fun getLottoCoroutines() = retrofitHelper.getLottoCoroutines()
}