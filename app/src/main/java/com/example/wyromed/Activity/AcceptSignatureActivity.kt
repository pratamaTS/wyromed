package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class AcceptSignatureActivity: AppCompatActivity() {
    var doctorSignature: LinearLayout? = null
    var nurseSignature: LinearLayout? = null
    var salesmanSignature: LinearLayout? = null
    var btnAcceptSign: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accept_signature)

        //INIT VIEW
        doctorSignature = findViewById(R.id.layout_image_doctor_signature)
        nurseSignature = findViewById(R.id.layout_image_nurse_signature)
        salesmanSignature = findViewById(R.id.layout_image_salesman_signature)
        btnAcceptSign = findViewById(R.id.btn_accept_signature)

        initActionButton()
    }

    private fun initActionButton() {

        doctorSignature?.onClick {
            val doctorSign = Intent(
                this@AcceptSignatureActivity,
                ConfirmSignatureActivity::class.java
            )
            doctorSign.putExtra("sign", 1)
            startActivity(doctorSign)
        }
        nurseSignature?.onClick {
            val nurseSign =
                Intent(this@AcceptSignatureActivity, ConfirmSignatureActivity::class.java)
            nurseSign.putExtra("sign", 1)
            startActivity(nurseSign)
        }
        salesmanSignature?.onClick {
            val salesmanSign = Intent(
                this@AcceptSignatureActivity,
                ConfirmSignatureActivity::class.java
            )
            salesmanSign.putExtra("sign", 1)
            startActivity(salesmanSign)
        }

        btnAcceptSign?.onClick {
            startActivity<SalesActivity>()
        }
        finish()
    }
}