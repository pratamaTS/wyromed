package com.example.wyromed.Activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.wyromed.Activity.Interface.StoreSalesOrderInterface
import com.example.wyromed.Activity.Presenter.StoreSalesOrderPresenter
import com.example.wyromed.Data.Model.SalesOrderDetail
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.io.File


class AcceptSignatureActivity: BaseActivity(), StoreSalesOrderInterface {
    object TAGS{
        val URLDOCTOR = "url_doctor"
        val URLNURSE = "url_nurse"
        val URLSALES = "url_sales"
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val SODETAIL = "so_detail"
        val SOHEADER = "so_header"
    }

    var doctorSignature: LinearLayout? = null
    var nurseSignature: LinearLayout? = null
    var salesmanSignature: LinearLayout? = null
    var btnAcceptSign: Button? = null
    var urlDoctor: String? = null
    var urlNurse: String? = null
    var urlSales: String? = null

    var salesOrderHeader: SalesOrderHeader = SalesOrderHeader()
    var salesOrderDetails: ArrayList<SalesOrderDetail> = ArrayList()

    private lateinit var imDoctorSign: ImageView
    private lateinit var imNurseSign: ImageView
    private lateinit var imSalesSign: ImageView
    private lateinit var loadingDialog: AlertDialog

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

        salesOrderDetails = intent.getParcelableArrayListExtra<SalesOrderDetail>("so_detail") as ArrayList<SalesOrderDetail>
        salesOrderHeader = intent.getParcelableExtra("so_header")!!

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            window?.setGravity(Gravity.CENTER)
        }

        if(intent.hasExtra("url_doctor")) {
            urlDoctor = intent.getStringExtra("url_doctor")

            val signatureImage = BitmapFactory.decodeFile(urlDoctor)
            imDoctorSign.setImageBitmap(signatureImage)
        }

        if(intent.hasExtra("url_nurse")) {
            urlNurse = intent.getStringExtra("url_nurse")

            val signatureImage = BitmapFactory.decodeFile(urlNurse)
            imNurseSign.setImageBitmap(signatureImage)
        }

        if(intent.hasExtra("url_sales")) {
            urlSales = intent.getStringExtra("url_sales")

            val signatureImage = BitmapFactory.decodeFile(urlSales)
            imSalesSign.setImageBitmap(signatureImage)
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
            doctorSign.putExtra("so_header", salesOrderHeader)
            doctorSign.putParcelableArrayListExtra("so_detail", salesOrderDetails)
            startActivity(doctorSign)
            finish()
        }
        nurseSignature?.onClick {
            val nurseSign =
                Intent(this@AcceptSignatureActivity, ConfirmSignatureActivity::class.java)
            nurseSign.putExtra("sign", 2)
            nurseSign.putExtra("url_doctor", urlDoctor)
            nurseSign.putExtra("so_header", salesOrderHeader)
            nurseSign.putParcelableArrayListExtra("so_detail", salesOrderDetails)
            startActivity(nurseSign)
            finish()
        }
        salesmanSignature?.onClick {
            val salesmanSign = Intent(
                this@AcceptSignatureActivity,
                ConfirmSignatureActivity::class.java
            )
            salesmanSign.putExtra("sign", 3)
            salesmanSign.putExtra("url_doctor", urlDoctor)
            salesmanSign.putExtra("url_nurse", urlNurse)
            salesmanSign.putExtra("so_header", salesOrderHeader)
            salesmanSign.putParcelableArrayListExtra("so_detail", salesOrderDetails)
            startActivity(salesmanSign)
            finish()
        }

        btnAcceptSign?.onClick {
            if(urlDoctor.isNullOrEmpty() && urlNurse.isNullOrEmpty() && urlSales.isNullOrEmpty()) {
                Toast.makeText(
                    this@AcceptSignatureActivity,
                    "There is an uncomplete sign, plesae complete the sign first",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                createSO()
                startActivity<SalesActivity>()
                finish()
            }
        }
    }

    private fun createSO(){
        val sourceFileDoctor = File(urlDoctor)
        val sourceFileNurse = File(urlNurse)
        val sourceFileSales = File(urlSales)

        val requestFileDoctor: RequestBody = sourceFileDoctor
            .asRequestBody("multipart/form-data".toMediaTypeOrNull())

        val requestFileNurse: RequestBody = sourceFileNurse
            .asRequestBody("multipart/form-data".toMediaTypeOrNull())


        val requestFileSales: RequestBody = sourceFileSales
            .asRequestBody("multipart/form-data".toMediaTypeOrNull())


        val bodyDoctor = MultipartBody.Part.createFormData(
            "doctor_sign",
            sourceFileDoctor.name,
            requestFileDoctor
        )

        val bodyNurse = MultipartBody.Part.createFormData(
            "nurse_sign_after",
            sourceFileNurse.name,
            requestFileNurse
        )

        val bodySales = MultipartBody.Part.createFormData(
            "seller_sign",
            sourceFileSales.name,
            requestFileSales
        )

        salesOrderHeader?.let {
            StoreSalesOrderPresenter(this).createSO(this,
                it, salesOrderDetails, bodyDoctor, bodyNurse, bodySales)
        }

        loadingDialog.show()
    }

    override fun onSuccessSalesOrder(message: String?) {
        loadingDialog.dismiss()

        Toast.makeText(
            this@AcceptSignatureActivity,
            "Create Sales Order Success",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onErrorSalesOrder(msg: String?) {
        loadingDialog.dismiss()

        Toast.makeText(
            this@AcceptSignatureActivity,
            msg,
            Toast.LENGTH_LONG
        ).show()
    }
}