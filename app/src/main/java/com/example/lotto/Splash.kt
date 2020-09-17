package com.example.lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.postDelayed
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {

    private val time = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this@Splash,MainActivity::class.java))
            finish()
        }, time.toLong())
    }


}