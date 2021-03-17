package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.Api.SessionManager
import com.example.wyromed.R
import java.util.Timer
import kotlin.concurrent.schedule



class SplashScreenActivity : AppCompatActivity() {
    var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sessionManager = SessionManager(this)

        sessionManager.fetchAuthToken()?.let {
            token = it
        }

        Timer("SplashScreen", false).schedule(5000) {
            if(token != null) {
                val mainActivity = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(mainActivity)
                finish()
            }else{
                val onBoarding = Intent(this@SplashScreenActivity, OnBoardingActivity::class.java)
                startActivity(onBoarding)
                finish()
            }
        }
    }

    companion object {
        private const val SPLASH_SCREEN = 5000
    }
}