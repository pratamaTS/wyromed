package com.example.wyromed.Activity

import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.wyromed.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.io.File
import java.lang.System.load
import java.net.URI

class AcceptSignatureActivity: BaseActivity() {
    object TAGS{
        val URLDOCTOR = "url_doctor"
        val URLNURSE = "url_nurse"
        val URLSALES = "url_sales"
    }

    var doctorSignature: LinearLayout? = null
    var nurseSignature: LinearLayout? = null
    var salesmanSignature: LinearLayout? = null
    var btnAcceptSign: Button? = null
    var url: String? = null
    var imDoctorSign: ImageView? = null
    var imNurseSign: ImageView? = null
    var imSalesSign: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accept_signature)

        //INIT VIEW
        doctorSignature = findViewById(R.id.layout_image_doctor_signature)
        nurseSignature = findViewById(R.id.layout_image_nurse_signature)
        salesmanSignature = findViewById(R.id.layout_image_salesman_signature)
        imDoctorSign = findViewById(R.id.im_doctor_signature)
        imNurseSign = findViewById(R.id.im_nurse_signature)
        imSalesSign = findViewById(R.id.im_salesman_signature)
        btnAcceptSign = findViewById(R.id.btn_accept_signature)

        if(intent.hasExtra("url_doctor")) {
            url = intent.getStringExtra("url_doctor")
            Picasso.get().load(File(url)).into(imDoctorSign);
        }

        if(intent.hasExtra("url_nurse")) {
            url = intent.getStringExtra("url_nurse")
            Picasso.get().load(File(url)).into(imNurseSign);
        }

        if(intent.hasExtra("url_sales")) {
            url = intent.getStringExtra("url_sales")
            Picasso.get().load(File(url)).into(imSalesSign);
        }

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
            finish()
        }
        nurseSignature?.onClick {
            val nurseSign =
                Intent(this@AcceptSignatureActivity, ConfirmSignatureActivity::class.java)
            nurseSign.putExtra("sign", 2)
            startActivity(nurseSign)
            finish()
        }
        salesmanSignature?.onClick {
            val salesmanSign = Intent(
                this@AcceptSignatureActivity,
                ConfirmSignatureActivity::class.java
            )
            salesmanSign.putExtra("sign", 3)
            startActivity(salesmanSign)
            finish()
        }

        btnAcceptSign?.onClick {
            startActivity<SalesActivity>()

            finish()
        }
    }
}