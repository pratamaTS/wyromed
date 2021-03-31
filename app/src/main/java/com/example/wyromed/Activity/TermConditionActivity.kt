package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.Activity.Interface.ForgotPasswordInterface
import com.example.wyromed.Activity.Presenter.ForgotPasswordPresenter
import com.example.wyromed.Model.Body.ForgotPasswordBody
import com.example.wyromed.R

class TermConditionActivity : BaseActivity(), View.OnClickListener {
    private lateinit var edtEmail: EditText
    private lateinit var loadingDialog: AlertDialog
    var btnBack: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term_condition)

        btnBack = findViewById(R.id.ic_back)

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            window?.setGravity(Gravity.CENTER)
        }

        //SET LISTENER
        btnBack?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ic_back -> {
                finish()
            }
        }
    }
}