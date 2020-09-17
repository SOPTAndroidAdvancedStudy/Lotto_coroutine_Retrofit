package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lotto_number = arrayListOf(number1,number2,number3,number4,number5,number6)

        val countDownTimer = object : CountDownTimer(3000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                lotto_number.forEach{
                    val randomNumber = (Math.random() * 45 + 1).toInt()
                    it.text = "${randomNumber}"
                }
            }

            override fun onFinish() {

            }
        }
        lottie_wheel.setOnClickListener {
            if(lottie_wheel.isAnimating){
                lottie_wheel.cancelAnimation()
                countDownTimer.cancel()
            }else{
                lottie_wheel.playAnimation()
                countDownTimer.start()
            }
        }
    }
}