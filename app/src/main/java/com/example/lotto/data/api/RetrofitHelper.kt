package com.example.lotto.data.api

class RetrofitHelper(private val retrofitService: RetrofitService) {
    private val METHOD : String = "getLottoNumber"
    private val LOTTO_ROUND = 929
    private val randomRound = (Math.random() * LOTTO_ROUND + 1).toInt().toString()

    // helper를 통해 getLottoCoroutine 호출
    // 호출된 값을 getLottoCoroutine 함수로 반환
    suspend fun getLottoCoroutines() = retrofitService.getLottoCoroutine(METHOD,randomRound)
}