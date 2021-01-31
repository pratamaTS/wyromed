package com.example.wyromed.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import com.example.wyromed.Activity.StockActivity.*
import com.example.wyromed.R
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class EditProfileActivity: BaseActivity() {
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

        if(message != null){
            toast(message.toString()).show()
        }


        // init Button
        initActionButton()

    }

    private fun initActionButton() {
        back!!.onClick {
            finish()
        }
        btnEdit!!.onClick {

        }
    }
}