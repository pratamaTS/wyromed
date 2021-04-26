package com.example.wyromed.Activity

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.wyromed.Activity.Interface.CityInterface
import com.example.wyromed.Activity.Interface.HospitalInterface
import com.example.wyromed.Activity.Interface.ProvinceInterface
import com.example.wyromed.Activity.Interface.StorePatientInterface
import com.example.wyromed.Activity.Presenter.*
import com.example.wyromed.Adapter.SpinnerDropDownAdapter
import com.example.wyromed.Model.Body.PatientBody
import com.example.wyromed.R
import com.example.wyromed.Response.Hospital.DataHospital
import com.example.wyromed.Response.Province.DataCity
import com.example.wyromed.Response.Province.DataProvince
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onEditorAction
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

class PatientActivity: BaseActivity(), HospitalInterface, StorePatientInterface, ProvinceInterface, CityInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
    }
    var edtMedrec: EditText? = null
    var edtPatientName: EditText? = null
    var edtEmail: EditText? = null
    var edtPostalCode: EditText? = null
    var edtNoBpjs: EditText? = null
    var edtDateBirth: EditText? = null
    var edtNoHp: EditText? = null
    var edtAddress: EditText? = null
    var edtState: AutoCompleteTextView? = null
    var edtCity: AutoCompleteTextView? = null
    var showDateBirth: ImageView? = null
    var spnGender: Spinner? = null
    var spinnerHospital: Spinner? = null
    var spinnerDropDownAdapter: SpinnerDropDownAdapter? = null
    var spnPaymentStat: Spinner? = null
    var hospital: ArrayList<DataHospital> = ArrayList()
    var tokenType: String? = null
    var token: String? = null
    var listGender: ArrayList<String> = ArrayList()
    var listPaymentStat: ArrayList<String> = ArrayList()
    var patient: PatientBody? = PatientBody()
    val c = Calendar.getInstance()
    var province: ArrayList<DataProvince> = ArrayList()
    var city: ArrayList<DataCity> = ArrayList()
    var stateID: String? = null
    var stateName: ArrayList<String> = ArrayList()
    var cityName: ArrayList<String> = ArrayList()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        //INIT VIEW
//        edtMedrec = findViewById(R.id.edt_medrec)
//        edtPatientName = findViewById(R.id.edt_patient_name)
//        edtEmail = findViewById(R.id.edt_patient_email)
//        edtPostalCode = findViewById(R.id.edt_postal_code)
//        edtNoBpjs = findViewById(R.id.edt_no_bpjs)
//        edtDateBirth = findViewById(R.id.edt_date_patient)
//        edtNoHp = findViewById(R.id.edt_phone_number)
//        edtAddress = findViewById(R.id.edt_address)
//        edtState = findViewById(R.id.edt_state)
//        edtCity = findViewById(R.id.edt_city)
//        showDateBirth = findViewById(R.id.show_calendarpat_btn)
//        spnGender = findViewById(R.id.gender_id)
//        spinnerHospital = findViewById(R.id.hospital_id)
//        spnPaymentStat = findViewById(R.id.payment_status_id)

        //Calendar
        initCalendarMethod()

        //List Gender
        listGender!!.add("Select Gender")
        listGender!!.add("Male")
        listGender!!.add("Female")

        //List Payment Status
        listPaymentStat!!.add("Select Patient Status")
        listPaymentStat!!.add("BPJS")
        listPaymentStat!!.add("Non BPJS")

        //Editor Action Listener
        edtState!!.onEditorAction { v, actionId, event ->
            val selectedState: String = edtState!!.text.toString()
            for(province in province){
                if(selectedState == province.stateName){
                    stateID = province.stateID
                }
            }

            CityPresenter(this@PatientActivity).getCity(this@PatientActivity, stateID.toString())
        }

        initSpinner()

        initActionButton()

        getHospital()
    }

    fun getHospital() {
        HospitalPresenter(this).getAllHospital(this)
        ProvincePresenter(this).getProvince(this)
    }

    override fun onSuccessGetHospital(dataHospital: ArrayList<DataHospital?>?) {
        //Set Value
        hospital = dataHospital as ArrayList<DataHospital>

        //Spinner Hospital
        spinnerDropDownAdapter = SpinnerDropDownAdapter(this, hospital)
        spinnerHospital?.adapter = spinnerDropDownAdapter
        spinnerHospital?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("Select Hospital")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val adapter = parent?.adapter
                if(adapter is SpinnerDropDownAdapter){
                    val item = adapter.getItem(position)
                    val hospitalID: Int = item.id.toString().toInt()
                    val hospitalName: String = item.name.toString()

                    //insert to patient model
                    patient?.hospital_id = hospitalID
                    patient?.hospital_name = hospitalName
                    Log.d("id rs", hospitalID.toString())
                    Log.d("nama rs", hospitalName)
                }
            }

        }
    }

    override fun onErrorGetHospital(msg: String?) {
        toast(msg ?: "Failed to get Hospital Data").show()
    }

    private fun initSpinner(){
        //Spinner Gender
        spnGender?.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listGender)
        spnGender?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("Select gender")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val adapter = parent?.adapter
                var gen: String? = null
                if(adapter is ArrayAdapter<*>){
                    val item = adapter.getItem(position)

                    when(item){
                        "Male" -> gen = "M"
                        else -> gen = "F"
                    }

//                    patient?.gender = gen
//                    Log.d("gender pasien", patient?.gender.toString())
                    Log.d("gender position", item.toString())
                }
            }

        }

        //Spinner Payment Status
        spnPaymentStat?.adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listPaymentStat)
        spnPaymentStat?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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
                        true -> edtNoBpjs!!.setVisibility(View.VISIBLE)
                        false-> edtNoBpjs!!.setVisibility(View.GONE)
                    }

                    patient?.is_bpjs = isBPJS
                    Log.d("is bpjs", patient?.is_bpjs.toString())
                    Log.d("is BPJS", isBPJS.toString())
                }
            }

        }
    }

    private fun initActionButton(){
//        btn_save.onClick {
//            //Validation\
//            edtMedrec!!.validate("you haven't fill the medrec number yet") { s -> s.isEmpty() }
//            edtPatientName!!.validate("you haven't fill the name yet") { s -> s.isEmpty()}
//            edtDateBirth!!.validate("you haven't fill the date of birth yet") { s -> s.isEmpty() }
//            edtEmail!!.validate("Wrong format email") { s -> s.isValidEmail() }
//            edtNoHp!!.validate("you haven't fill the phone number yet") { s -> s.isEmpty() }
//            edtAddress!!.validate("you haven't fill the address yet") { s -> s.isEmpty() }
//            edtState!!.validate("you haven't fill the Province yet") { s -> s.isEmpty() }
//            edtCity!!.validate("you haven't fill the city yet") { s -> s.isEmpty() }
//            edtPostalCode!!.validate("you haven't fill the postal code yet") { s -> s.isEmpty() }
//
//            patient!!.medic_number = edtMedrec!!.text.toString()
//            patient!!.name = edtPatientName!!.text.toString()
//            patient!!.email = edtEmail!!.text.toString()
//            patient!!.phone = edtNoHp!!.text.toString()
//            patient!!.address = edtAddress!!.text.toString()
//            patient!!.state = edtState!!.text.toString()
//            patient!!.city = edtCity!!.text.toString()
//            patient!!.postal_code = edtPostalCode!!.text.toString()
//            patient!!.bpjs = edtNoBpjs!!.text.toString()
//
//            Log.d("medic number", patient!!.medic_number.toString())
//            Log.d("nama pasien", patient!!.name.toString())
//            Log.d("email pasien", patient!!.email.toString())
//            Log.d("nomor pasien", patient!!.phone.toString())
//            Log.d("alamat pasien", patient!!.address.toString())
//            Log.d("Kecamatan", patient!!.state.toString())
//            Log.d("Kota", patient!!.city.toString())
//            Log.d("Postal Code", patient!!.postal_code.toString())
//            Log.d("BPJS", patient!!.bpjs.toString())
//
//            if(edtMedrec!!.text.isNotEmpty() &&
//                edtPatientName!!.text.isNotEmpty() &&
//                edtDateBirth!!.text.isNotEmpty() &&
//                edtNoHp!!.text.isNotEmpty() &&
//                edtAddress!!.text.isNotEmpty() &&
//                edtState!!.text.isNotEmpty() &&
//                edtCity!!.text.isNotEmpty() &&
//                edtPostalCode!!.text.isNotEmpty()) {
//                StorePatientPresenter(this@PatientActivity).storePatient(
//                    this@PatientActivity,
//                    patient!!
//                )
//            } else {
//                toast("Please check again before submit")
//            }
//        }
        ic_back.onClick { finish() }
    }

    private fun initCalendarMethod(){
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)
        var dateValue: String? = null

        showDateBirth!!.setOnClickListener {
            val date = DatePickerDialog(this, { view, thisYear, thisMonth, thisDay ->
                // Display Selected date in TextView
                month = thisMonth + 1
                day = thisDay
                year = thisYear
                edtDateBirth!!.setText("" + thisDay + "/" + month + "/" + thisYear)

                dateValue =  year.toString() + "-" + month.toString() + "-" + day.toString()

//                patient?.date_of_birth = dateValue!!
            }, year, month, day)
            date.show()

        }
    }

    override fun onSuccessStorePatient(message: String?) {
        startActivity<BookingActivity>(BookingActivity.TAGS.MESSAGE to message)
    }

    override fun onErrorStorePatient(msg: String?) {
        toast("Failed to add data").show()
    }

    override fun onSuccessGetProvince(dataProvince: ArrayList<DataProvince?>?) {
        province = dataProvince as ArrayList<DataProvince>

        for(province in province){
            stateName.add(province.stateName.toString())
        }

        ArrayAdapter(this, android.R.layout.simple_list_item_1, stateName).also { adapter ->
            edtState!!.setAdapter(adapter)
        }
    }

    override fun onErrorGetProvince(msg: String?) {
        toast("Failed to get province").show()
    }

    override fun onSuccessGetCity(dataCity: ArrayList<DataCity?>?) {
        city = dataCity as ArrayList<DataCity>

        for(city in city){
            cityName.add(city.cityName.toString())
        }

        ArrayAdapter(this, android.R.layout.simple_list_item_1, cityName).also { adapter ->
            edtCity!!.setAdapter(adapter)
        }
    }

    override fun onErrorGetCity(msg: String?) {
        toast("Failed to get city").show()
    }

    fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    fun EditText.validate(message: String, validator: (String) -> Boolean) {
        this.afterTextChanged {
            this.error = if (validator(it)) null else message
        }
        this.error = if (validator(this.text.toString())) null else message
    }

    fun String.isValidEmail(): Boolean
            = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}