package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.Activity.Interface.ForgotPasswordInterface
import com.example.wyromed.Activity.Presenter.ForgotPasswordPresenter
import com.example.wyromed.Model.Body.ForgotPasswordBody
import com.example.wyromed.R

class ForgotPasswordActivity : BaseActivity(), View.OnClickListener, ForgotPasswordInterface {
    private lateinit var edtEmail: EditText
    private lateinit var loadingDialog: AlertDialog
    var btnSend: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        //INIT VIEW
        edtEmail = findViewById(R.id.et_email_forgot)
        btnSend = findViewById(R.id.btn_send)

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

        //SET LISTENER
        btnSend?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_send -> {
                if(edtEmail?.text!!.isNotEmpty()) {
                    loadingDialog.show()
                    ForgotPasswordPresenter(this).resetPassword(this, ForgotPasswordBody(edtEmail?.text.toString()))
                }else {
                    Toast.makeText(this, "Email is still empty", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSuccessForgotPassword(msg: String?) {
        loadingDialog.dismiss()
        Toast.makeText(this, "Your password just send to your email", Toast.LENGTH_LONG).show()

        val sendEmail = Intent(this@ForgotPasswordActivity, CheckEmailActivity::class.java)
        sendEmail.putExtra("email", edtEmail.text.toString())
        startActivity(sendEmail)
    }

    override fun onErrorForgotPassword(msg: String?) {
        loadingDialog.dismiss()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}