package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick

class NewPasswordActivity: BaseActivity() {
    var etNewPassword: EditText? = null
    var etRepeatPassword:EditText? = null
    var btnCreatePassword: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)

        //INIT VIEW
        etNewPassword = findViewById(R.id.et_new_password_forgot)
        etRepeatPassword = findViewById(R.id.et_repeat_password_forgot)
        btnCreatePassword = findViewById(R.id.btn_create_password)

        //SET LISTENER
        initActionButton()
    }

    fun ShowHidePass(view: View) {
        if (view.id == R.id.show_new_pass_btn) {
            if (etNewPassword!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Show Password
                etNewPassword!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Hide Password
                etNewPassword!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        if (view.id == R.id.show_repeat_pass_btn) {
            if (etRepeatPassword!!.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Show Password
                etRepeatPassword!!.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Hide Password
                etRepeatPassword!!.setTransformationMethod(PasswordTransformationMethod.getInstance())
            }
        }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_create_password -> {
                val createPassword =
                    Intent(this@NewPasswordActivity, PasswordSuccessActivity::class.java)
                startActivity(createPassword)
            }
        }
    }

    private fun initActionButton() {
        btnCreatePassword!!.onClick { this }
    }
}