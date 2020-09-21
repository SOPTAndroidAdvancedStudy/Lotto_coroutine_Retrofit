package com.example.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.lotto.Model.LottoData
import com.example.lotto.Retrofit.SearchRetrofit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * 번호가 출력되면 그 번호가 당첨번호와 얼마나 유사한지를 알려주도록 하자
 */

class MainActivity : AppCompatActivity() {
    var METHOD : String = "getLottoNumber"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cnt = 0
        val lotto_number = arrayListOf(number1,number2,number3,number4,number5,number6)

        val countDownTimer = object : CountDownTimer(3000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                lotto_number.forEach{
                    val randomNumber = (Math.random() * 45 + 1).toInt()
                    it.text = "${randomNumber}"
                }
            }

            override fun onFinish() {
                callPreLottoNumber()
            }
        }
        lottie_wheel.setOnClickListener {
            if(lottie_wheel.isAnimating){
                lottie_wheel.cancelAnimation()
                countDownTimer.cancel()
                callPreLottoNumber()
            }else{
                lottie_wheel.playAnimation()
                countDownTimer.start()
            }
        }
    }

    fun callPreLottoNumber(){
        val randomNumber = (Math.random() * 929 +1).toInt()
        val response = SearchRetrofit.getService().getLotto(METHOD,randomNumber.toString()).enqueue(object:retrofit2.Callback<LottoData> {
            override fun onResponse(call: Call<LottoData>, response: Response<LottoData>) {
                if(response != null){
                    var lottoData = response.body()
                    sec_number1.setText(lottoData?.drwtNo1)
                    sec_number2.setText(lottoData?.drwtNo2)
                    sec_number3.setText(lottoData?.drwtNo3)
                    sec_number4.setText(lottoData?.drwtNo4)
                    sec_number5.setText(lottoData?.drwtNo5)
                    sec_number6.setText(lottoData?.drwtNo6)
                    Toast.makeText(this@MainActivity,"이전 당첨번호 출력",Toast.LENGTH_SHORT)
                }
                else {
                    Toast.makeText(this@MainActivity,"reeponse 비어있음",Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<LottoData>, t: Throwable) {

            }
        })
    }
}