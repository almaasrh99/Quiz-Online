package com.almaas.quizonline


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_splash.*
import android.os.Handler as Handler1

class SplashActivity : AppCompatActivity() {

    lateinit var tvSplash: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tvSplash = (findViewById(R.id.tvSplash))

        val handler = android.os.Handler()
        handler.postDelayed(Runnable {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }, 3000L);// = 3 detik
    }
}
