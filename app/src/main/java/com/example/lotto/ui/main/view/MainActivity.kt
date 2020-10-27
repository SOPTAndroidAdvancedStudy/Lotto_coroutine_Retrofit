package com.example.lotto.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lotto.R
import com.example.lotto.data.model.LottoData
import com.example.lotto.databinding.ActivityMainBinding
import com.example.lotto.ui.main.viewmodel.MainViewModel
import com.example.lotto.utills.Status
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 번호가 출력되면 그 번호가 당첨번호와 얼마나 유사한지를 알려주도록 하자
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewModel()
        setUpActivity()
        setupObserver()

        // TODO : This part is not good
        val lotto_number = arrayListOf(number1,number2,number3,number4,number5,number6)

        val countDownTimer = object : CountDownTimer(3000, 100) { // done
            override fun onTick(millisUntilFinished: Long) {
                lotto_number.forEach {
                    val randomNumber = (Math.random() * 45 + 1).toInt().toString()
                    it.text = randomNumber
                }
            }

            override fun onFinish() {
                mainViewModel.fetchLottoNormal()
            }
        }

        lottie_wheel.setOnClickListener {
            if(lottie_wheel.isAnimating){
                lottie_wheel.cancelAnimation()
                countDownTimer.cancel()
                mainViewModel.fetchLottoNormal()
            }else{
                lottie_wheel.playAnimation()
                countDownTimer.start()
            }
        }
    }



    private fun setupObserver(){
        mainViewModel.getLottoNumber().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {data ->
                        setNumber(data)
                    }
                }
                Status.LOADING -> {
                    // ProgressBar같은 걸로 처리해야함
                }
                Status.ERROR -> {
                    Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setNumber(data : LottoData) {
        sec_number1.text = data.drwtNo1
        sec_number2.text = data.drwtNo2
        sec_number3.text = data.drwtNo3
        sec_number4.text = data.drwtNo4
        sec_number5.text = data.drwtNo5
        sec_number6.text = data.drwtNo6
    }

    private fun setUpActivity(){
        binding.mainActivity = this
    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.mainviewmodel = mainViewModel
    }
}