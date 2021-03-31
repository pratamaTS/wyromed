package com.example.wyromed.Ui.Booking.RentItem

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.example.wyromed.Base.BaseWyromedFragment
import com.example.wyromed.Base.BaseWyromedPresenter
import com.example.wyromed.Data.Connection.ApiServices
import com.example.wyromed.Data.Connection.SessionManager
import com.example.wyromed.Data.Connection.WyromedService
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.Data.Model.Hospital
import com.example.wyromed.Data.Model.RentalItem
import com.example.wyromed.Data.Remote.RemoteRepositoryLocator
import com.example.wyromed.Data.Source.BookingDataSource
import com.example.wyromed.R
import com.example.wyromed.Ui.Booking.RentItem.Item.RentItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.addClickListener
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import org.jetbrains.anko.support.v4.toast
import java.util.*
import kotlin.collections.ArrayList

class RentalItemFragment : BaseWyromedFragment<RentalItemContract.Presenter>(), RentalItemContract.View,
    View.OnClickListener {

    companion object {

        fun newInstance(): Fragment {
            return RentalItemFragment()
        }

    }

    override val layoutId: Int = R.layout.fragment_booking_1_v2
    override val TAG: String = "RentalItemFragment"

    override lateinit var presenter: RentalItemContract.Presenter

//    private lateinit var rentalItemAdapter: ModelAdapter<Hospital, ProductItem>

    private lateinit var spinnerHospital: SmartMaterialSpinner<String>
    private lateinit var spinnerRentalItem: SmartMaterialSpinner<String>
    private lateinit var mBottomSheetDialog: RoundedBottomSheetDialog
    private lateinit var txtFormDetailDate: TextView
    private lateinit var txtFormDetailTime: TextView
    private lateinit var tvClear: TextView
    private lateinit var edtAmount: EditText
    private lateinit var rvItem: RecyclerView
    private lateinit var bookingDetails: ArrayList<BookingOrderDetails>

    private lateinit var itemAdapter: ModelAdapter<BookingOrderDetails, RentItem>

//    private lateinit var containerBookingStartDate: View
//    private lateinit var containerBookingEndDate: View

    private lateinit var btnAddItem: Button
    private lateinit var btnAdd: Button

    private var selectedStartDate: Calendar? = null
    private var selectedEndDate: Calendar? = null
    private var token: String? = null
    private var hospitalId: Int? = null
    private var hospitalName: String? = null
    private var productId: Int? = null
    private var itemName: String? = null
    private var productUnit: String? = null
    private var productEntity: String? = null


    override fun init() {
        super.init()
//
//        presenter = RentalItemPresenter(
//            RepositoryLocator
//                .getInstance(
//                    RemoteRepositoryLocator
//                        .getInstance(ApiServices.apiService(requireActivity())))
//                .bookingRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onHospitalLoaded()
        presenter.onRentalItemLoaded()

    }

    override fun setupWidgets(v: View) {
        super.setupWidgets(v)

        txtFormDetailDate = v.findViewById(R.id.form_detail_date)
        txtFormDetailTime = v.findViewById(R.id.form_detail_time)
        spinnerHospital = v.findViewById(R.id.spn_hospital)

        rvItem = v.findViewById(R.id.rv_rental_item)

        btnAddItem = v.findViewById(R.id.btn_add_item)

        //Bottom Sheet
        mBottomSheetDialog = RoundedBottomSheetDialog(requireContext())
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_item, null)

        spinnerRentalItem = sheetView.findViewById(R.id.item_id)
        edtAmount = sheetView.findViewById(R.id.edt_amount_ritem)
        tvClear = sheetView.findViewById(R.id.tv_clear_ritem)
        btnAdd = sheetView.findViewById(R.id.btn_add)

        //Rental Item
        mBottomSheetDialog!!.setContentView(sheetView)

        btnAddItem.setOnClickListener(this)
        btnAdd.setOnClickListener(this)

    }

    override fun setDataHospitaltoSpinner(hospital: List<Hospital>) {
        val itemList: ArrayList<String> = ArrayList()
        for( i in hospital ){
            itemList.add(i.name.toString())
        }

        // Spinner Rental Item
        spinnerHospital?.setItem(itemList)
        spinnerHospital?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = hospital[position]
                hospitalId = item.id?.toInt()
                hospitalName = item.name
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                toast("There is no selected hospital")
            }
        })
    }

    override fun setDataRentalItemtoSpinner(stock: List<RentalItem>) {

        val itemList: ArrayList<String> = ArrayList()
        for( i in stock ){
            itemList.add(i.name.toString())
        }

        // Spinner Rental Item
        spinnerRentalItem?.setItem(itemList)
        spinnerRentalItem?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = stock[position]
                productId = item.productId
                itemName = item.name
                productUnit = item.unitName
                productEntity = item.entity
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                toast("There is no selected item")
            }
        })
    }

    override fun showRentalItem(stock: ArrayList<BookingOrderDetails>) {
        itemAdapter.set(stock)
    }

    override fun setBookingDatesValue(bookingStartDate: Calendar?, bookingEndDate: Calendar?) {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add -> presenter.onBtnAdd(productId!!.toInt(), edtAmount.text.toString().toInt(), itemName.toString(), productUnit.toString(), productEntity.toString())
            R.id.btn_add_item -> mBottomSheetDialog.show()
        }
    }
}