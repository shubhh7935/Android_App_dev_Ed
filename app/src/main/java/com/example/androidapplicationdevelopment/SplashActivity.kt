package com.example.androidapplicationdevelopment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapplicationdevelopment.R
import com.example.androidapplicationdevelopment.Viewsandcomponents

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Optional: use a blank layout or theme without UI
        setContentView(R.layout.activity_splash)

        // Delay 2 seconds, then move to Unit1Activity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Viewsandcomponents::class.java))
            finish() // Prevent back button from returning to splash
        }, 3000)
    }
}