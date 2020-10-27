package com.example.lotto.ui.main.viewmodel

import android.app.PendingIntent.getService
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotto.data.api.RetrofitClient
import com.example.lotto.data.model.LottoData
import com.example.lotto.utills.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(

) : ViewModel() {
    private val METHOD : String = "getLottoNumber"
    private val LOTTO_ROUND = 929


    private val _lottoNumber = MutableLiveData<Resource<LottoData>>() // ViewModel에서 접근 가능한 데이터
    val lottoNumber : MutableLiveData<Resource<LottoData>> // UI에서 접근이 가능한 데이터
    get() = _lottoNumber

    // TODO Coroutine으로 Retrofit 처리하기

    fun getLottoNumber() : LiveData<Resource<LottoData>>{
        return lottoNumber
    }

    fun fetchLottoNormal(){
        val randomRound = (Math.random() * LOTTO_ROUND + 1).toInt().toString()
        RetrofitClient.getService().getLotto(METHOD,randomRound).enqueue(object : Callback<LottoData> {
            override fun onResponse(call: Call<LottoData>, response: Response<LottoData>) {
                _lottoNumber.value = Resource.success(response.body())
                Log.i("MainViewModel",randomRound)
                Log.i("MainViewModel",_lottoNumber.value.toString())
            }

            override fun onFailure(call: Call<LottoData>, t: Throwable) {
                _lottoNumber.value = Resource.error("Something Wrong",null)
                Log.i("MainViewModel",t.message.toString())
            }
        })
    }
}