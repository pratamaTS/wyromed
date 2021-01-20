package com.example.wyromed.Activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.Activity.Interface.SignUpInterface
import com.example.wyromed.Activity.Presenter.SignUpPresenter
import com.example.wyromed.Model.Body.RegisterBody
import com.example.wyromed.Model.User
import com.example.wyromed.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignUpActivity : AppCompatActivity(), SignUpInterface {
    var etFullname: EditText? = null
    var etEmail: EditText? = null
    var etNoHandphone: EditText? = null
    var etPassword: EditText? = null
    var etCPassword: EditText? = null
    var btnBack: ImageButton? = null
    var btnSignUp: Button? = null
    var btnToLoginLayout: Button? = null
    var registerBody: RegisterBody? = RegisterBody()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //INIT VIEW
        etFullname = findViewById(R.id.et_fullname_register)
        etEmail = findViewById(R.id.et_email_register)
        etNoHandphone = findViewById(R.id.et_nohp_register)
        etPassword = findViewById(R.id.et_password_register)
        etCPassword = findViewById(R.id.et_cpassword_register)
        btnBack = findViewById(R.id.btn_back_to_layout)
        btnSignUp = findViewById(R.id.btn_sign_up)
        btnToLoginLayout = findViewById(R.id.btn_login_layout)

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
        }else if(view.id == R.id.show_cpass_btn) {
            if (etCPassword!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Show Password
                etCPassword!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Hide Password
                etCPassword!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    private fun  initActionButton() {
        btn_sign_up.onClick {
            registerBody!!.password = et_password_register.text.toString()
            registerBody!!.fullname = et_fullname_register.text.toString()
            registerBody!!.email = et_email_register.text.toString()
            registerBody!!.phone = et_nohp_register.text.toString()
            registerBody!!.repassword = et_cpassword_register.text.toString()

            SignUpPresenter(this@SignUpActivity).signup(registerBody!!)
        }
        btn_back_to_layout.onClick {
            startActivity<OnBoardingActivity>()
        }
        btn_login_layout.onClick {
            startActivity<SignInActivity>()
        }
    }

    override fun onSuccessSignUp(message: String?) {
        toast(message.toString()).show()

        startActivity<OnBoardingActivity>(OnBoardingActivity.TAGS.MESSAGE to message)
    }

    override fun onErrorSignUp(msg: String?) {
        toast(msg ?: "There is/are empty input, input can not be empty!").show()
    }
}