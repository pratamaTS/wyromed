package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick

class PasswordSuccessActivity: BaseActivity() {
    var btnBacktoLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_success)

        //INIT VIEW
        btnBacktoLogin = findViewById(R.id.btn_back_to_login)

        //SET LISTENER
        initActionButton()
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_back_to_login -> {
                val back = Intent(this@PasswordSuccessActivity, SignInActivity::class.java)
                startActivity(back)
            }
        }
    }

    private fun initActionButton(){
        btnBacktoLogin!!.onClick { this }
    }
}