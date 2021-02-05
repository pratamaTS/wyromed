package com.example.wyromed.Fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.example.wyromed.Activity.BookingActivity
import com.example.wyromed.Activity.Interface.PatientInterface
import com.example.wyromed.Activity.PatientActivity
import com.example.wyromed.Activity.Presenter.PatientPresenter
import com.example.wyromed.Adapter.SpinnerDialogAdapter
import com.example.wyromed.R
import com.example.wyromed.Response.Patient.DataPatient
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList


class Booking1Fragment : Fragment(), PatientInterface {
    val rentalFragment: Booking2Fragment = Booking2Fragment()
    val bundle: Bundle = Bundle()
    private var spinnerPatient: SmartMaterialSpinner<String>? = null
    var txtHospital: TextView? = null
    var txtMedicalRecord: TextView? = null
    var txtPaymentStat: TextView? = null
    var txtFormDetailDate: TextView? = null
    var txtFormDetailTime: TextView? = null
    var tvListPatient: TextView? = null
    var btnNext1: Button? = null
    var btnAddPatient: Button? = null
    var patient: ArrayList<DataPatient> = ArrayList()
    var patientId: String? = null
    var patientPosition: Int? = null
    @RequiresApi(Build.VERSION_CODES.O)
    val currentDateTime: LocalDateTime = LocalDateTime.now()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_booking_1, container, false)
        view.context

        //Init View
        txtMedicalRecord = view.findViewById(R.id.medical_record)
        txtPaymentStat = view.findViewById(R.id.payment_status)
        txtFormDetailDate = view.findViewById(R.id.form_detail_date)
        txtFormDetailTime = view.findViewById(R.id.form_detail_time)
        btnNext1 = view.findViewById(R.id.btn_next_book1)
        btnAddPatient = view.findViewById(R.id.btn_add_patient)
        txtHospital = view.findViewById(R.id.tv_hospital)
        spinnerPatient = view.findViewById(R.id.patient_id)
        tvListPatient = view.findViewById(R.id.list_patient)

        txtFormDetailDate!!.text = currentDateTime.format(
            DateTimeFormatter.ofLocalizedDate(
                FormatStyle.LONG
            )
        )
        txtFormDetailTime!!.text = currentDateTime.format(
            DateTimeFormatter.ofLocalizedTime(
                FormatStyle.MEDIUM
            )
        )

        //Button
        initActionButton()

        //Get Master
        getAllMaster()

        return view
    }

    private fun initActionButton() {
        btnAddPatient!!.onClick { startActivity<PatientActivity>() }
        btnNext1!!.onClick {
            if(patientId.isNullOrEmpty() || patientPosition == 0){
                toast("There is no patient data")
            }else {
                (activity as BookingActivity?)!!.nextStep()
                rentalFragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_booking_container,
                    rentalFragment
                )
                    ?.commit()
            }
        }
    }

    fun getAllMaster() {
        PatientPresenter(this@Booking1Fragment).getAllPatient(requireContext())
    }

    override fun onSuccessGetPatient(dataPatient: ArrayList<DataPatient?>?) {
        //Set Value
        patient = dataPatient as ArrayList<DataPatient>

        val patientName: ArrayList<String> = ArrayList()
        for( p in patient ){
            patientName.add(p.name.toString())
        }

        //Spinner Patient
        spinnerPatient?.setItem(patientName)
        spinnerPatient?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = patient[position]
                patientId = item.id
                patientPosition = position
                val patientName = item.name
                val hospitalID = item.hospitalId
                val hospital = item.hospitalName
                val medrecPatient = item.medicNumber
                val paymentPatient = item.isBpjs

                if (position != 0) {
                    txtHospital!!.text = hospital
                    txtMedicalRecord!!.text = medrecPatient
                    if (paymentPatient == "1") {
                        txtPaymentStat!!.text = "BPJS"
                        bundle.putBoolean("payment_patient", true)
                    } else {
                        txtPaymentStat!!.text = "Non BPJS"
                        bundle.putBoolean("payment_patient", false)
                    }
                }

                bundle.putString("patient_id", patientId)
                bundle.putString("hospital_id", hospitalID)
                bundle.putString("hospital_name", hospital)
                bundle.putString("patient_name", patientName)
                bundle.putString("medrec_patient", medrecPatient)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                toast("There is no patient")
            }
        })

    }

    override fun onErrorGetPatient(msg: String?) {
        toast(msg ?: "Gagal Mengambil Data Pasien").show()
    }
}