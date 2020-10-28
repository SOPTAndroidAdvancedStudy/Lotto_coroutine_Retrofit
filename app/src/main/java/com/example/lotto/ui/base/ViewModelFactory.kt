package com.example.lotto.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lotto.data.api.RetrofitHelper
import com.example.lotto.data.repository.MainRepository
import com.example.lotto.ui.main.viewmodel.MainViewModel

// ViewModel을 Custom 하기 위한 ViewModelFactory
// 매개변수로 retrofitHelper를 받는다.
// 이 helper클래스를 통해 MainRepository를 생성하고 ViewModel이 생성과 동시에 MainRepository class의 네트워킹이 호출되어
// 서버의 값을 받아낸다.
// 이는 ViewModel이 생성되는 단 한번만 호출이 되므로 호출이 한번만 필요한 경우에만 사용하는 것이 바람직
class ViewModelFactory(private val retrofitHelper: RetrofitHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(retrofitHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}