package com.example.lotto.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lotto.data.api.RetrofitHelper
import com.example.lotto.data.repository.MainRepository
import com.example.lotto.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val retrofitHelper: RetrofitHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(retrofitHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}