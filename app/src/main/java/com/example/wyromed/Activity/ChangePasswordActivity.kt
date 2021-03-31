package com.example.wyromed.Activity

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import com.example.wyromed.Activity.Interface.ChangePasswordInterface
import com.example.wyromed.Activity.Presenter.ChangePasswordPresenter
import com.example.wyromed.Activity.StockActivity.*
import com.example.wyromed.Data.Model.ChangePasswordBody
import com.example.wyromed.R
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ChangePasswordActivity: BaseActivity(), ChangePasswordInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var back: ImageButton? = null
    var message: String? = null
    var edtoldPass: EditText?  = null
    var edtChangePass: EditText?  = null
    var edtCfChangePass: EditText? = null
    var btnSave: Button? = null
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        edtoldPass = findViewById(R.id.et_change_old_password)
        edtChangePass = findViewById(R.id.et_change_new_password)
        edtCfChangePass = findViewById(R.id.et_change_repeat_password)
        btnSave = findViewById(R.id.btn_save_password)

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

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
        if (view.id == R.id.show_old_pass_btn) {
            if (edtoldPass!!.transformationMethod == PasswordTransformationMethod.getInstance()) {
                (view as ImageView).setImageResource(R.drawable.ic_hide)
                //Show Password
                edtoldPass!!.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                (view as ImageView).setImageResource(R.drawable.ic_show)
                //Hide Password
                edtoldPass!!.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }else if (view.id == R.id.show_new_pass_btn) {
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

    private fun insertNewPassword(){
        ChangePasswordPresenter(this).changePassword(this, ChangePasswordBody(edtoldPass?.text!!.toString(), edtChangePass?.text!!.toString(), edtCfChangePass?.text!!.toString()))
    }

    private fun initActionButton() {
        back!!.onClick {
            finish()
            insertNewPassword()
        }

        btnSave!!.onClick {
            if(edtoldPass?.text!!.isNotEmpty() && edtChangePass?.text!!.isNotEmpty() && edtCfChangePass?.text!!.isNotEmpty()){
                loadingDialog.show()
            }else if(edtoldPass?.text!!.isEmpty()){
                toast("Old Password still empty").show()
            }else if(edtChangePass?.text!!.isEmpty()){
                toast("New Password still empty").show()
            }else if(edtCfChangePass?.text!!.isEmpty()){
                toast("Repeat Password still empty").show()
            }
        }
    }

    override fun onSuccessChangePassword(msg: String?) {
        loadingDialog.dismiss()
        edtoldPass?.text?.clear()
        edtChangePass?.text?.clear()
        edtCfChangePass?.clearFocus()
        Toast.makeText(this, "Your password just changed!", Toast.LENGTH_LONG).show()
    }

    override fun onErrorChangePassword(msg: String?) {
        loadingDialog.dismiss()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}