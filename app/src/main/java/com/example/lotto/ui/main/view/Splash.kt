package com.example.lotto.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.lotto.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Splash : AppCompatActivity() {

    private val time : Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch(Dispatchers.IO) {
            delay(time)
            startActivity(Intent(this@Splash, MainActivity::class.java))
            finish()
        }

        /*Handler(Looper.getMainLooper).postDelayed({ // Runnble 객체와 time을 파라미터로 받는다
            startActivity(Intent(this@Splash,MainActivity::class.java))
            finish()
        }, time)*/
    }
}