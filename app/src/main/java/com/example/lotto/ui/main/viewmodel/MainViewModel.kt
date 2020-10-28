package com.example.lotto.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.lotto.data.api.RetrofitBuilder
import com.example.lotto.data.api.RetrofitHelper
import com.example.lotto.data.model.LottoData
import com.example.lotto.data.repository.MainRepository
import com.example.lotto.utills.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(
    private val mainRepository: MainRepository
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

    /**
     * 일반적인 retrofit 호출방법
     */
    fun fetchLottoNormal(){
        /*val randomRound = (Math.random() * LOTTO_ROUND + 1).toInt().toString()
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
        })*/
    }

    /**
     * ViewModelFactory pattern을 이용해 custom ViewModel을 생성했습니다.
     * 매개변수로 받아오는 것은 MainRepository이며 이 repo를 사용해 네트워킹을 대신 해줄 수 있습니다.
     * 단점으로는 MainViewModel이 호출되는 단 한번에만 호출되기 때문에 데이터를 단 한번만 가져와야 되는 경우에
     * 사용하는 것이 바람직해 보입니다.
     */
    fun onlyOneCall() = viewModelScope.launch {
        _lottoNumber.value = Resource.success(mainRepository.getLottoCoroutines())
    }

    /**
     * repository pattern을 이용해 네트워킹을 사용했다.
     * 단점 : Repository의 크기가 커진다면 오히려 이 경우가 효과가 떨어지지 않을까라고 생각한다.
     */
    fun getLottoCoroutineMine() = viewModelScope.launch {
        val repo = MainRepository(RetrofitHelper(RetrofitBuilder.retrofitService))
        _lottoNumber.value = Resource.success(repo.getLottoCoroutines())
    }

    /**
     * Builder를 통해 직접 호출했다.
     * 단점 : ViewModel에서 네트워크를 직접 호출하기 때문에 의존성의 문제가 생긴다.
     */
    fun fetchLottoCoroutine() = viewModelScope.launch {
        val randomRound = (Math.random() * LOTTO_ROUND + 1).toInt().toString()
        _lottoNumber.value = Resource.success(RetrofitBuilder.retrofitService.getLottoCoroutine(METHOD,randomRound))
    }
}