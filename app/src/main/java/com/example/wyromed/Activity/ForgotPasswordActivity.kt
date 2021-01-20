package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.R

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {
    var btnSend: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        //INIT VIEW
        btnSend = findViewById(R.id.btn_send)

        //SET LISTENER
        btnSend?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_send -> {
                val sendEmail = Intent(this@ForgotPasswordActivity, CheckEmailActivity::class.java)
                startActivity(sendEmail)
            }
        }
    }
}