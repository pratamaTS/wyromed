package com.example.wyromed.Activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
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
    var loginCheck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //INIT VIEW
        etPassword = findViewById(R.id.et_password_login)
        btn_sign_in.isEnabled = true

        val prefs = getSharedPreferences("UserData", MODE_PRIVATE)
        val email: String? = prefs.getString("email", "")

        if(intent.hasExtra("login_check")){
            loginCheck = intent.getBooleanExtra("login_check", false)
        }

        Log.d("Email", email.toString())
        if(email!!.isNotEmpty() || loginCheck == false){
            et_identity_login.setText(email)
        }else{
            et_identity_login.setText(email)
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

            if(et_identity_login.text.length > 0 && et_password_login.text.length > 0) {
                userAuth!!.email = et_identity_login.text.toString()
                userAuth!!.password = et_password_login.text.toString()

                val prefs = getSharedPreferences("UserData", MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putString("email", userAuth!!.email)
                editor.commit()

                SignInPresenter(this@SignInActivity).signin(this@SignInActivity, userAuth!!)
            }else{
                toast("Mohon isi Email & Password terlebih dahulu!")
                btn_sign_in.isEnabled = true
            }
        }
        btn_forgot_password.onClick {
            startActivity<ForgotPasswordActivity>()
            finish()
        }
        btn_register_layout.onClick {
            startActivity<SignUpActivity>()
            finish()
        }
        btn_signup_to_layout.onClick {
            startActivity<OnBoardingActivity>()
            finish()
        }
    }

    override fun onSuccessLogin(tokenType: String?, token: String?, message: String?) {
        startActivity<MainActivity>(
            BaseActivity.TAGS.MESSAGE to "Welcome aboard!"
        )
        finish()
    }

    override fun onErrorLogin(msg: String?) {
        btn_sign_in.isEnabled = true
        btn_sign_in.setBackgroundResource(R.drawable.bg_button_green)
        btn_signup_to_layout.isEnabled = true
        btn_forgot_password.isEnabled = true
        btn_signup_to_layout.isEnabled = true
        toast(msg.toString()).show()
    }
}