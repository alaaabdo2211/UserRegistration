package com.code.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.code.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private var isLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        actionBar?.hide()
        val sharedPref = this.getSharedPreferences("name", MODE_PRIVATE)
        isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)


        if (isLoggedIn) {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)

        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, AuthenticationActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }
}