package com.example.wyromed.Activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.Activity.Interface.SignInInterface
import com.example.wyromed.Activity.Presenter.SignInPresenter
import com.example.wyromed.Model.Body.SignInBody
import com.example.wyromed.Model.User
import com.example.wyromed.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class SignInActivity : AppCompatActivity(), SignInInterface {
    var etPassword: EditText? = null
    var userAuth: SignInBody? = SignInBody()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //INIT VIEW
        etPassword = findViewById(R.id.et_password_login)

        val prefs = getSharedPreferences("UserData", MODE_PRIVATE)
        val email: String? = prefs.getString("email", "")
        val pwd: String? = prefs.getString("password", "")

        if(email!!.isNotEmpty() && pwd!!.isNotEmpty()){
            et_identity_login.setText(email)
            etPassword!!.setText(pwd)
        }

        initActionButton()
    }

    fun ShowHidePass(view: View) {
        if (view.id == R.id.show_pass_btn) {
            if (etPassword!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Show Password
                etPassword!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Hide Password
                etPassword!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    private fun  initActionButton() {
        btn_sign_in.onClick {
            btn_sign_in.isEnabled = false
            btn_sign_in.setBackgroundResource(R.drawable.bg_button_gray)
            btn_signup_to_layout.isEnabled = false
            btn_forgot_password.isEnabled = false
            btn_signup_to_layout.isEnabled = false

            val user = User()
            if(et_identity_login.text.length > 0 && et_password_login.text.length > 0) {
                userAuth!!.email = et_identity_login.text.toString()
                userAuth!!.password = et_password_login.text.toString()

                val prefs = getSharedPreferences(userAuth!!.email, MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("email", userAuth!!.email)
                editor.putString("password", userAuth!!.password)
                editor.commit()

                SignInPresenter(this@SignInActivity).signin(userAuth!!)
            }else{
                toast("Mohon isi Email & Password terlebih dahulu!")
            }
        }
        btn_forgot_password.onClick {
            startActivity<ForgotPasswordActivity>()
        }
        btn_register_layout.onClick {
            startActivity<SignUpActivity>()
        }
        btn_signup_to_layout.onClick {
            startActivity<OnBoardingActivity>()
        }
    }

    override fun onSuccessLogin(tokenType: String?, token: String?, message: String?) {
        startActivity<MainActivity>(
            BaseActivity.TAGS.TOKENTYPE to tokenType,
            BaseActivity.TAGS.TOKEN to token,
            BaseActivity.TAGS.MESSAGE to message
        )
    }

    override fun onErrorLogin(msg: String?) {
        btn_sign_in.isEnabled = true
        btn_sign_in.setBackgroundResource(R.drawable.bg_button_green)
        btn_signup_to_layout.isEnabled = true
        btn_forgot_password.isEnabled = true
        btn_signup_to_layout.isEnabled = true
        toast("1) Your data identification(email/username) doesn't exist or didn't match. OR 2) Your account is NOT VALID yet! Please contact your admin to validate your account").show()
    }
}