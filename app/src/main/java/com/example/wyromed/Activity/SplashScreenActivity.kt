package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.R
import java.util.Timer
import kotlin.concurrent.schedule



class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Timer("SplashScreen", false).schedule(5000) {
            val onBoarding = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
            startActivity(onBoarding)
            finish()
        }
//        Handler().postDelayed({
//            val onBoarding = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
//            startActivity(onBoarding)
//            finish()
//        }, SPLASH_SCREEN.toLong())
    }

    companion object {
        private const val SPLASH_SCREEN = 5000
    }
}