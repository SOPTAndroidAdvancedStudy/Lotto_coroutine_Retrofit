package com.example.lotto.data.model

// 서버에서 주는 값대로 변수명을 만들긴 했는데
// 좀 너무 더럽지 않나 생각이 들긴함
// serielizedName annotation을 사용을 해야하나 생각 중
data class LottoData(
    var totSellamnt: String,
    var returnValue: String,
    var drwNoDate: String,
    var firstWinamnt: String,
    var drwtNo6: String,
    var drwtNo4: String,
    var firstPrzwnerCo: String,
    var drwtNo5: String,
    var bnusNo: String,
    var firstAccumamnt: String,
    var drwNo: String,
    var drwtNo2: String,
    var drwtNo3: String,
    var drwtNo1: String
) {
}