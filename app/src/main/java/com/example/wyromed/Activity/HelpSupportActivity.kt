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
        setContentView(R.layout.activity_help_support)

        //INIT VIEW
        back = findViewById(R.id.ic_back)


    }
}