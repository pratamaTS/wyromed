package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.Response.Login.DataLogin
import org.jetbrains.anko.toast

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(){
    object TAGS{
        val MESSAGE = "message"
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
    }

    val bundle: Bundle = Bundle()
    var user : DataLogin? = DataLogin()

    fun cekSession(activity: Activity){
        val token = intent.getSerializableExtra("token")

        if(token==null){
            activity.toast("Anda Belum Melakukan Login").show()
            activity.finish()
        } else {
            val tokenType = intent.getStringExtra("token_type")
            val message = intent.getStringExtra("message")

            user?.token_type = tokenType
            user?.token = token.toString()

            bundle.putString("token_type", user?.token_type)
            bundle.putString("token", user?.token)

            activity.toast(message.toString()).show()
        }
    }
}