package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.Activity.Interface.ForgotPasswordInterface
import com.example.wyromed.Activity.Presenter.ForgotPasswordPresenter
import com.example.wyromed.Model.Body.ForgotPasswordBody
import com.example.wyromed.R

class CheckEmailActivity : BaseActivity(), View.OnClickListener, ForgotPasswordInterface {
    private lateinit var tvCoundownTimerResend: TextView
    private lateinit var btnResendLink: Button
    private lateinit var btnOke: Button
    private lateinit var loadingDialog: AlertDialog
    var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_email)

        tvCoundownTimerResend = findViewById(R.id.tv_countdown_timer_resend)
        btnResendLink = findViewById(R.id.btn_resend)
        btnOke = findViewById(R.id.btn_oke)

        if(intent.hasExtra("email")){
            email = intent.getStringExtra("email")
        }

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

        countDownTimer()

        btnOke.setOnClickListener(this)
        btnResendLink.setOnClickListener(this)
    }

    private fun countDownTimer() {
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvCoundownTimerResend.visibility = View.VISIBLE
                btnResendLink?.setTextColor(getColor(R.color.colorGray))
                btnResendLink?.isEnabled = false
                tvCoundownTimerResend.setText("" + millisUntilFinished / 1000)
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                tvCoundownTimerResend.visibility = View.GONE
                btnResendLink?.isEnabled = true
                btnResendLink?.setTextColor(getColor(R.color.colorGreen))
            }
        }.start()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_resend -> {
                loadingDialog.show()
                ForgotPasswordPresenter(this).resetPassword(this, ForgotPasswordBody(email.toString()))
            }
            R.id.btn_oke -> {
                val signInActivity = Intent(this, SignInActivity::class.java)
                signInActivity.putExtra("login_check", true)
                startActivity(signInActivity)
                finish()
            }
        }
    }

    override fun onSuccessForgotPassword(msg: String?) {
        loadingDialog.dismiss()
        Toast.makeText(this, "Your password just send to your email", Toast.LENGTH_LONG).show()
        countDownTimer()
    }

    override fun onErrorForgotPassword(msg: String?) {
        loadingDialog.dismiss()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}