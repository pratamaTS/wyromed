package com.example.wyromed.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import com.example.wyromed.Activity.StockActivity.*
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HelpSupportActivity: BaseActivity() {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var back: ImageButton? = null
    var message: String?  = null
    var menuProfile: LinearLayout? = null
    var menuNotification:LinearLayout? = null
    var menuChangePassword:LinearLayout? = null
    var menuPrivacyPolicy:LinearLayout? = null
    var menuTermsConditions:LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        menuProfile = findViewById(R.id.menu_profile_settings)
//        menuNotification = findViewById(R.id.menu_notification_settings)
        menuChangePassword = findViewById(R.id.menu_change_password_settings)
        menuPrivacyPolicy = findViewById(R.id.menu_privacy_policy_settings)
        menuTermsConditions = findViewById(R.id.menu_terms_conditions_settings)

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
        menuProfile!!.onClick {
            startActivity<EditProfileActivity>(
                EditProfileActivity.TAGS.MESSAGE to message
            )
        }
//        menuNotification!!.onClick {
//            startActivity<NotificationActivity>(
//                NotificationActivity.TAGS.MESSAGE to message
//            )
//        }
        menuNotification!!.onClick {
            startActivity<NotificationActivity>(
                NotificationActivity.TAGS.MESSAGE to message
            )
        }
    }
}