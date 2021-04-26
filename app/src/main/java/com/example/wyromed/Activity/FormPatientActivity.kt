package com.example.wyromed.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.chinodev.androidneomorphframelayout.NeomorphFrameLayout
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.example.wyromed.Activity.Interface.HospitalInterface
import com.example.wyromed.Activity.Interface.StorePatientInterface
import com.example.wyromed.Activity.Presenter.HospitalPresenter
import com.example.wyromed.Activity.Presenter.PatientPresenter
import com.example.wyromed.Activity.Presenter.StorePatientPresenter
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Model.Body.PatientBody
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.Response.Hospital.DataHospital
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class FormPatientActivity: BaseActivity(), HospitalInterface, StorePatientInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val HOURSOPS = "hours_ops"
        val MINUTESOPS = "minutes_ops"
        val SECONDSOPS = "seconds_ops"
        val RENTAL = "rental"
        val BMHP = "bmhp"
        val SOHEADER = "so_header"
    }

    private lateinit var back: ImageButton
    private lateinit var etPatientName: EditText
    private lateinit var etMedrec: EditText
    private lateinit var etBpjsNumber: EditText
    private lateinit var layoutBpjs: NeomorphFrameLayout
    private lateinit var btnSave: Button

    var spnHospital: SmartMaterialSpinner<String>?? = null
    var spnBpjsStatus: SmartMaterialSpinner<String>?? = null
    var spnPaymentStatus: SmartMaterialSpinner<String>?? = null
    var hospitalId: Int = 0
    var hospitalName: String? = null
    var paymentStatus: Int = 0

    var salesRentItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var salesPurchasedItemList: ArrayList<HandoverPurchasedItem> = ArrayList()
    var totalRental: Int = 0
    var totalPurchased: Int = 0
    var hoursOperation: Long = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0
    var salesOrderHeader: SalesOrderHeader? = null
    var patient: PatientBody? = PatientBody()
    var listBpjsStat: ArrayList<String> = ArrayList()
    var listPaymentStat: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_patient)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        spnHospital = findViewById(R.id.form_hospital_id)
        spnPaymentStatus = findViewById(R.id.form_payment_status_id)
        spnBpjsStatus = findViewById(R.id.form_bpjs_id)
        etPatientName = findViewById(R.id.edt_form_patient_name)
        etMedrec = findViewById(R.id.edt_form_medrec)
        etBpjsNumber = findViewById(R.id.edt_form_bpjs)
        layoutBpjs = findViewById(R.id.layout_form_bpjs)
        btnSave = findViewById(R.id.btn_save_new_patient)

        hoursOperation = intent.getLongExtra("hours_ops", 0)
        minutesOperation = intent.getLongExtra("minutes_ops", 0)
        secondsOperation = intent.getLongExtra("seconds_ops", 0)
        salesOrderHeader = intent.getParcelableExtra("so_header")

        listBpjsStat.add("BPJS")
        listBpjsStat.add("Non BPJS")

        listPaymentStat.add("Umum")
        listPaymentStat.add("Asuransi Umum")
        listPaymentStat.add("Asuransi BPJS")

        getHospital()

        initSpinner()

        initActionButton()

        //SET LISTENER
        back.setOnClickListener({ finish() })
    }

    private fun initSpinner(){
        //Spinner Payment Status
        spnPaymentStatus?.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listPaymentStat)
        spnPaymentStatus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("Select Patient Status")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val adapter = parent?.adapter
                var paymentStatus: Int = 0
                if(adapter is ArrayAdapter<*>){
                    val item = adapter.getItem(position)

                    when(item){
                        "Umum" -> paymentStatus = 1
                        "Asuransi Umum" -> paymentStatus = 2
                        else -> paymentStatus = 3
                    }

                    patient?.patient_type = paymentStatus
                }
            }

        }

        //Spinner BPJS Status
        spnBpjsStatus?.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listBpjsStat)
        spnBpjsStatus?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("Select Patient Status")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val adapter = parent?.adapter
                var isBPJS: Boolean = false
                if(adapter is ArrayAdapter<*>){
                    val item = adapter.getItem(position)

                    when(item){
                        "BPJS" -> isBPJS = true
                        else -> isBPJS = false
                    }

                    when(isBPJS){
                        true -> layoutBpjs.setVisibility(View.VISIBLE)
                        false-> layoutBpjs.setVisibility(View.INVISIBLE)
                    }

                    patient?.is_bpjs = isBPJS
                }
            }

        }
    }

    private fun insertPatient(){
        StorePatientPresenter(this).storePatient(
            this,
            patient!!
        )
    }

    private fun initActionButton(){
        btnSave.onClick {
            if(patient?.hospital_id == null && patient?.hospital_name == null){
                toast("Hospital is still empty").show()
            }else if(etPatientName.text.isEmpty()){
                toast("Patient name is still empty").show()
            }else if(etMedrec.text.isEmpty()){
                toast("Medical record is still empty").show()
            }else if(patient?.patient_type == 0){
                toast("Payment status is still empty").show()
            }else{
                patient!!.medic_number = etMedrec.text.toString()
                patient!!.name = etPatientName.text.toString()
                patient!!.bpjs = etBpjsNumber.text.toString()
                insertPatient()
            }
        }
    }

    fun getHospital() {
        HospitalPresenter(this).getAllHospital(this)
    }

    override fun onSuccessGetHospital(dataHospital: ArrayList<DataHospital?>?) {
        //Set Value
        val itemList: ArrayList<String> = ArrayList()
        if (dataHospital != null) {
            for( i in dataHospital ){
                itemList.add(i?.name.toString())
            }
        }

        // Spinner Rental Item
        spnHospital?.setItem(itemList)
        spnHospital?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = dataHospital?.get(position)
                patient?.hospital_id = item?.id?.toInt()!!
                patient?.hospital_name = item?.name
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                toast("There is no selected hospital")
            }
        })
    }

    override fun onErrorGetHospital(msg: String?) {
        toast(msg ?: "Failed to get Hospital").show()
    }

    override fun onSuccessStorePatient(message: String?) {
        startActivity<SalesOrderActivity>(
            SalesOrderActivity.TAGS.HOURSOPS to hoursOperation,
            SalesOrderActivity.TAGS.MINUTESOPS to minutesOperation,
            SalesOrderActivity.TAGS.SECONDSOPS to secondsOperation,
            SalesOrderActivity.TAGS.RENTAL to salesRentItemList,
            SalesOrderActivity.TAGS.BMHP to salesPurchasedItemList,
            SalesOrderActivity.TAGS.SOHEADER to salesOrderHeader
        )
        Toast.makeText(this,"Patient has been created!", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onErrorStorePatient(msg: String?) {
        toast("Failed to add data").show()
    }
}