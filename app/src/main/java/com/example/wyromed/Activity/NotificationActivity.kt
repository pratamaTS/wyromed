package com.example.wyromed.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.widget.SwitchCompat
import com.example.wyromed.Activity.StockActivity.*
import com.example.wyromed.R
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class NotificationActivity: BaseActivity() {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var back: ImageButton? = null
    var message: String? = null
    var switchPushNotif: SwitchCompat?  = null
    var switchPushEmail: SwitchCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        switchPushNotif = findViewById(R.id.toogle_switch_push_notification)
        switchPushEmail = findViewById(R.id.toogle_switch_push_email)

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
    }
}