package com.example.wyromed.Activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.example.wyromed.Activity.Interface.UserInterface
import com.example.wyromed.Activity.Presenter.ChangePasswordPresenter
import com.example.wyromed.Activity.Presenter.UserPresenter
import com.example.wyromed.Activity.StockActivity.*
import com.example.wyromed.Data.Model.ChangePasswordBody
import com.example.wyromed.R
import com.example.wyromed.Response.Login.DataLogin
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class EditProfileActivity: BaseActivity(), UserInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var back: ImageButton? = null
    var message: String?  = null
    var imgProfile: CircleImageView? = null
    var fullname: EditText? = null
    var email: EditText? = null
    var noHp: EditText? = null
    var btnEdit: Button? = null
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        imgProfile = findViewById(R.id.iv_foto_profile)
        fullname = findViewById(R.id.et_fullname_edit)
        email = findViewById(R.id.et_email_edit)
        noHp = findViewById(R.id.et_nohp_edit)
        btnEdit = findViewById(R.id.btn_edit_profile)

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

        if(message != null){
            toast(message.toString()).show()
        }

        getUserInfo()

        // init Button
        initActionButton()

    }

    private fun getUserInfo(){
        UserPresenter(this).getUser(this)
    }

    private fun editProfile(){
//        ChangePasswordPresenter(this).changePassword(this, ChangePasswordBody(edtoldPass?.text!!.toString(), edtChangePass?.text!!.toString(), edtCfChangePass?.text!!.toString()))
    }

    private fun initActionButton() {
        back!!.onClick {
            finish()
        }
        btnEdit!!.onClick {
            loadingDialog.show()
            editProfile()
        }
    }

    override fun onSuccessUser(dataLogin: DataLogin?) {
        fullname?.setText(dataLogin?.fullname.toString())
        email?.setText(dataLogin?.email.toString())
        noHp?.setText(dataLogin?.phone.toString())
    }

    override fun onErrorUser(msg: String?) {
        toast("Failed to get data").show()
    }
}