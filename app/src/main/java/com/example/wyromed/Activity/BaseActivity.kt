package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.Response.Login.DataLogin


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(){
    object TAGS{
        val MESSAGE = "message"
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
    }

    val bundle: Bundle = Bundle()
    var user : DataLogin? = DataLogin()

}