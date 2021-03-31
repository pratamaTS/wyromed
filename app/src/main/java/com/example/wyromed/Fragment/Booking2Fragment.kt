package com.example.wyromed.Fragment

import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.example.wyromed.Activity.BookingActivity
import com.example.wyromed.Activity.Interface.HospitalInterface
import com.example.wyromed.Activity.Interface.RentalItemInterface
import com.example.wyromed.Activity.Presenter.HospitalPresenter
import com.example.wyromed.Activity.Presenter.RentalItemPresenter
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.Response.Hospital.DataHospital
import com.example.wyromed.Response.RentalItem.DataRentalItem
import com.example.wyromedapp.Adapter.ListRentalItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.collections.ArrayList

class Booking2Fragment : Fragment(), HospitalInterface, RentalItemInterface {
    val purchasedItemFragment: Booking4Fragment = Booking4Fragment()
    var txtFormDetailDate: TextView? = null
    var txtFormDetailTime: TextView? = null
    var spnHospital: SmartMaterialSpinner<String>?? = null
    var edtAmount: EditText? = null
    var spnRitem: SmartMaterialSpinner<String>?? = null
    var rentalItem: ArrayList<DataRentalItem> = ArrayList()
    var btnAdd: Button? = null
    var btnAddRItem: Button? = null
    var btnNext2: Button? = null
    var rvListRentalItem: RecyclerView? = null
    var listChooseRitem: ArrayList<RentalItem> = ArrayList()
    var bookingOrderHeader: BookingOrderHeader = BookingOrderHeader()
    var bookingOrderDetails: ArrayList<BookingOrderDetails> = ArrayList()
    var rentalAdapter: ListRentalItemAdapter? = null
    var mBottomSheetDialog: RoundedBottomSheetDialog? = null
    var productId: Int? = null
    var itemName: String? = null
    var productUnit: String? = null
    var productEntity: String? = null
    var calendar: Calendar? = null
    var year: Int? = null
    var month: Int? = null
    var day: Int? = null
    var dateStartValue: String? = null
    var timeStartValue: String? = null
    var dateEndValue: String? = null
    var timeEndValue: String? = null
    val bundle: Bundle = Bundle()
    var hospitalID: String? = null
    var hospitalName: String? = null
    var startDateTime: String? = null
    var endDateTime: String? = null
    var totalQuantity: Int? = 0

    @RequiresApi(Build.VERSION_CODES.O)
    val currentDateTime: LocalDateTime = LocalDateTime.now()

//    companion object {
//        fun newInstance(myList : ArrayList<RentalItem>): Booking2Fragment {
//            val args = Bundle()
//            args.putParcelableArrayList("list_rental_item", myList);
//            val fragment = Booking2Fragment()
//            fragment.arguments = args
//            return fragment
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_booking_1_v2, container, false)
        view.context

        //Init View
        txtFormDetailDate = view.findViewById(R.id.form_detail_date)
        txtFormDetailTime = view.findViewById(R.id.form_detail_time)
        spnHospital = view.findViewById(R.id.spn_hospital)
        btnAddRItem = view.findViewById(R.id.btn_add_item)
        rvListRentalItem = view.findViewById(R.id.rv_rental_item)

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

        //Bottom Sheet
        mBottomSheetDialog = RoundedBottomSheetDialog(requireContext())
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_item, null)

        spnRitem = sheetView.findViewById(R.id.item_id)
        edtAmount = sheetView.findViewById(R.id.edt_amount_ritem)
        btnAdd = sheetView.findViewById(R.id.btn_add)

        //Rental Item
        mBottomSheetDialog!!.setContentView(sheetView)

        getAllRentalItem()

        // init Button
        initActionButton()

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAllRentalItem() {
        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val dateTimeNow: String = currentDateTime.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )

        bookingOrderHeader.start_date = dateTimeNow
        bookingOrderHeader.end_date = dateTimeNow

        HospitalPresenter(this@Booking2Fragment).getAllHospital(requireContext())
        RentalItemPresenter(this@Booking2Fragment).getAllRentalItem(requireContext(), dateTimeNow)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initActionButton() {
        //Button
        btnAddRItem!!.onClick {
            mBottomSheetDialog!!.show()
        }

        btnAdd!!.onClick {
            if (edtAmount!!.text.isNullOrEmpty()) {
                toast("Amount Item cannot be empty")
            } else {
                // Rental Item
                val check = listChooseRitem!!.find {
                    it!!.product_id == productId
                }

                val checkBookingDetails = bookingOrderDetails!!.find {
                    it!!.product_id == productId
                }

                if(listChooseRitem!!.isNotEmpty()){
                    if(check != null) {
                        listChooseRitem!!.find {
                            it!!.product_id == productId
                        }?.quantity = edtAmount!!.text.toString().toInt()
                    }else{
                        listChooseRitem!!.add(
                            RentalItem(
                                productId!!.toInt(),
                                edtAmount!!.text.toString().toInt(),
                                itemName.toString(),
                                productUnit.toString(),
                                productEntity.toString(),
                                dateStartValue,
                                timeStartValue
                            )
                        )
                    }
                }else{
                    listChooseRitem!!.add(
                        RentalItem(
                            productId!!.toInt(),
                            edtAmount!!.text.toString().toInt(),
                            itemName.toString(),
                            productUnit.toString(),
                            productEntity.toString(),
                            dateStartValue,
                            timeStartValue
                        )
                    )
                }

                if(bookingOrderDetails!!.isNotEmpty()){
                    if(checkBookingDetails != null) {
                        bookingOrderDetails!!.find {
                            it!!.product_id == productId
                        }?.quantity = edtAmount!!.text.toString().toInt()
                    }else{
                        bookingOrderDetails!!.add(
                            BookingOrderDetails(
                                productId!!.toInt(),
                                edtAmount!!.text.toString().toInt(),
                                itemName.toString(),
                                productUnit.toString(),
                                productEntity.toString()
                            )
                        )
                    }
                }else{
                    bookingOrderDetails!!.add(
                        BookingOrderDetails(
                            productId!!.toInt(),
                            edtAmount!!.text.toString().toInt(),
                            itemName.toString(),
                            productUnit.toString(),
                            productEntity.toString()
                        )
                    )
                }

                totalQuantity = totalQuantity!! + edtAmount!!.text.toString().toInt()

                bookingOrderHeader.total_quantity = totalQuantity

                (activity as BookingActivity?)!!.addBooking(
                    bookingOrderHeader = bookingOrderHeader,
                    bookingOrderDetails = bookingOrderDetails
                )

                rentalAdapter = ListRentalItemAdapter(requireContext(), listChooseRitem)
                rvListRentalItem?.setLayoutManager(LinearLayoutManager(context))
                rvListRentalItem?.setAdapter(rentalAdapter)
                rvListRentalItem?.setItemAnimator(DefaultItemAnimator())

                mBottomSheetDialog!!.dismiss()
                edtAmount!!.text.clear()
            }
        }
    }

    override fun onSuccessGetRentalItem(dataRentalItem: ArrayList<DataRentalItem?>?) {
        //Set Value
        rentalItem = dataRentalItem as ArrayList<DataRentalItem>

        val itemList: ArrayList<String> = ArrayList()
        for( i in rentalItem ){
            itemList.add(i.name.toString())
        }

        // Spinner Rental Item
        spnRitem?.setItem(itemList)
        spnRitem?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = rentalItem[position]
                productId = item!!.productId
                itemName = item.name
                productUnit = item.unitName
                productEntity = item.entity
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                toast("There is no selected item")
            }
        })
    }

    override fun onErrorGetRentalItem(msg: String?) {
        toast(msg ?: "Failed to get rental item").show()
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
                bookingOrderHeader.hospital_id = item?.id?.toInt()
                bookingOrderHeader.hospital_name = item?.name
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                toast("There is no selected hospital")
            }
        })
    }

    override fun onErrorGetHospital(msg: String?) {
        toast(msg ?: "Failed to get Hospital").show()
    }

}