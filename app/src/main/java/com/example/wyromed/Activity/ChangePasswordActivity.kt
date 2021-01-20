package com.example.wyromed.Activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.example.wyromed.Activity.StockActivity.*
import com.example.wyromed.R
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ChangePasswordActivity: BaseActivity() {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var back: ImageButton? = null
    var message: String? = null
    var edtChangePass: EditText?  = null
    var edtCfChangePass: EditText? = null
    var btnSave: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        edtChangePass = findViewById(R.id.et_change_new_password)
        edtCfChangePass = findViewById(R.id.et_change_repeat_password)
        btnSave = findViewById(R.id.btn_save_password)

        //Insert Token to fragment
        user?.token_type = intent.getStringExtra("token_type")
        user?.token = intent.getStringExtra("token")

        if(message != null){
            toast(message.toString()).show()
        }


        // init Button
        initActionButton()

    }

    fun ShowHidePass(view: View) {
        if (view.id == R.id.show_new_pass_btn) {
            if (edtChangePass!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Show Password
                edtChangePass!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Hide Password
                edtChangePass!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }else if(view.id == R.id.show_repeat_pass_btn) {
            if (edtCfChangePass!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Show Password
                edtCfChangePass!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Hide Password
                edtCfChangePass!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
    }

    private fun initActionButton() {
        back!!.onClick {
            finish()
        }
    }
}