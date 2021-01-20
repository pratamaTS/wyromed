package com.example.wyromed.Fragment

import android.R.attr.button
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.example.wyromed.Activity.BookingActivity
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Activity.Presenter.PurchasedItemPresenter
import com.example.wyromed.Adapter.SpinnerDialogItemAdapter
import com.example.wyromed.Model.Body.BookingOrderDetails
import com.example.wyromed.Model.Body.BookingOrderHeader
import com.example.wyromed.Model.PurchasedItem
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.Response.PurchasedItem.DataPurchasedItem
import com.example.wyromedapp.Adapter.ListPurchasedItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit


class Booking3Fragment : Fragment(), PurchasedItemInterface {
    val statusFragment: Booking4Fragment = Booking4Fragment()
    var edtAmount: EditText? = null
    var spinnerPItem: Spinner? = null
    var listChoosePItem: ArrayList<PurchasedItem> = ArrayList()
    var listChooseRItem: ArrayList<RentalItem> = ArrayList()
    var bookingOrderHeader: BookingOrderHeader = BookingOrderHeader()
    var bookingOrderDetails: ArrayList<BookingOrderDetails> = ArrayList()
    var btnAddItem: Button? = null
    var btnAddPItem: Button? = null
    var btnSkip3: Button? = null
    var btnNext3: Button? = null
    var rvListPurchasedItem: RecyclerView? = null
    var purchasedAdapter: ListPurchasedItemAdapter? = null
    var pItemName: String? = null
    var mBottomSheetDialog: RoundedBottomSheetDialog? = null
    var spinnerPurchasedItemAdapter: SpinnerDialogItemAdapter? = null
    var tokenType: String? = null
    var token: String? = null
    var purchasedItem: ArrayList<DataPurchasedItem> = ArrayList()
    var productId: Int? = null
    var itemName: String? = null
    var productUnit: String? = null
    var productEntity: String? = null
    var dateStart: String? = null
    var startTime: String? = null
    val bundle: Bundle = Bundle()
    var hospitalName: String? = null
    var patientName: String? = null
    var medrecPatient: String? = null
    var paymentPatient: Boolean? = null
    var inAnimation: AlphaAnimation? = null
    var outAnimation: AlphaAnimation? = null
    var animationView: LottieAnimationView? = null
    var progressBarHolder: FrameLayout? = null
    var myLog = "myLog"
    var totalQuantity: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_booking_3, container, false)
        view.context

        //Init View
        btnAddItem = view.findViewById(R.id.btn_add_item_purchased)
        btnAddPItem = view.findViewById(R.id.btn_add_item)
        btnSkip3 = view.findViewById(R.id.btn_skip_book3)
        btnNext3 = view.findViewById(R.id.btn_next_book3)
        rvListPurchasedItem = view.findViewById(R.id.rv_purchased_item)
        progressBarHolder = view.findViewById(R.id.progressBarHolder)
        animationView = view.findViewById(R.id.animation_view)

        totalQuantity = arguments?.getInt("total_quantity")
        hospitalName = arguments?.getString("hospital_name")
        patientName = arguments?.getString("patient_name")
        medrecPatient = arguments?.getString("medrec_patient")
        paymentPatient = arguments?.getBoolean("payment_patient")
        val startDate = arguments?.getString("start_date")
        val endDate = arguments?.getString("end_date")
        dateStart = arguments?.getString("start_date_only")
        val dateEnd = arguments?.getString("end_date_only")
        startTime = arguments?.getString("start_time_only")
        val endTime = arguments?.getString("end_time_only")
        listChooseRItem = arguments?.getParcelableArrayList("rental_item")!!
        bookingOrderHeader = arguments?.getParcelable("booking_order_header")!!
        bookingOrderDetails = arguments?.getParcelableArrayList("booking_order_details")!!

        bundle.putString("hospital_name", hospitalName)
        bundle.putString("patient_name", patientName)
        bundle.putString("medrec_patient", medrecPatient)
        bundle.putBoolean("payment_patient", paymentPatient!!)
        bundle.putString("start_date", startDate)
        bundle.putString("end_date", endDate)
        bundle.putString("start_date_only", dateStart)
        bundle.putString("end_date_only", dateEnd)
        bundle.putString("start_time_only", startTime)
        bundle.putString("end_time_only", endTime)
        bundle.putParcelable("booking_order_header", bookingOrderHeader)
        bundle.putParcelableArrayList("rental_item", listChooseRItem)

        Log.d("Start Date Time", startDate.toString())
        Log.d("End Date Time", endDate.toString())
        Log.d("Start Date", dateStart.toString())
        Log.d("End Date", dateEnd.toString())
        Log.d("Start Time", startTime.toString())
        Log.d("End Time", endTime.toString())
        Log.d("list item", listChooseRItem.toString())
        Log.d("Booking Order Header3", bookingOrderHeader.toString())
        Log.d("Booking Order Details3", bookingOrderDetails.toString())

        //Bottom Sheet
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_purchased_item, null)

        spinnerPItem = sheetView.findViewById(R.id.purchased_item_id)
        edtAmount = sheetView.findViewById(R.id.edt_amount_pitem)
        btnAddPItem = sheetView.findViewById(R.id.btn_add_purchased)

        //Purchased Item
        mBottomSheetDialog = RoundedBottomSheetDialog(requireContext())
        mBottomSheetDialog!!.setContentView(sheetView)

        //Button
        initActionButton()

        return view
    }

    fun getAllPurchasedItem() {
        tokenType = arguments?.getString("token_type")
        token = arguments?.getString("token")

        bundle.putString("token_type", tokenType)
        bundle.putString("token", token)

        if(tokenType==null || token==null){
            activity?.toast("Gagal mengambil data")?.show()
            activity?.finish()
        } else {
            PurchasedItemPresenter(this@Booking3Fragment).getAllPurchasedItem(tokenType, token)
        }
    }

    private fun initActionButton() {
        btnAddItem!!.onClick {
            getAllPurchasedItem()

            mBottomSheetDialog!!.show()
        }
        btnSkip3!!.onClick {
            (activity as BookingActivity?)!!.nextStep()
            statusFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(
                R.id.fragment_booking_container,
                statusFragment
            )
                ?.commit()
        }
        btnNext3!!.onClick {
            if(listChoosePItem.isNullOrEmpty()){
                toast("There is no purchased item")
            }else {
                Loading().execute()
            }
        }
        btnAddPItem!!.onClick {
            if (edtAmount!!.text.isNullOrEmpty()) {
                toast("Amount Item cannot be empty")
            } else {
                // Purchased Item
                val check = listChoosePItem!!.find {
                    it!!.productId == productId
                }

                val checkBookingDetails = bookingOrderDetails!!.find {
                    it!!.product_id == productId
                }

                if(listChoosePItem!!.isNotEmpty()){
                    if(check != null){
                        toast("Item " + check.name + " is already exist!")
                    }else{
                        listChoosePItem!!.add(
                            PurchasedItem(
                                productId!!.toInt(),
                                edtAmount!!.text.toString().toInt(),
                                itemName.toString(),
                                productUnit.toString(),
                                productEntity.toString(),
                                dateStart,
                                startTime
                            )
                        )

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
                }else {
                    listChoosePItem!!.add(
                        PurchasedItem(
                            productId!!.toInt(),
                            edtAmount!!.text.toString().toInt(),
                            itemName.toString(),
                            productUnit.toString(),
                            productEntity.toString(),
                            dateStart,
                            startTime
                        )
                    )

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
                Log.d("total quantity3", totalQuantity.toString())

                bundle.putInt("total_quantity", totalQuantity!!)
                bundle.putParcelableArrayList("purchased_item", listChoosePItem)
                bundle.putParcelableArrayList("booking_order_details", bookingOrderDetails)

                purchasedAdapter = ListPurchasedItemAdapter(requireContext(), listChoosePItem)
                rvListPurchasedItem?.setLayoutManager(LinearLayoutManager(context))
                rvListPurchasedItem?.setAdapter(purchasedAdapter)
                rvListPurchasedItem?.setItemAnimator(DefaultItemAnimator())
                rvListPurchasedItem?.addItemDecoration(
                    DividerItemDecoration(
                        activity,
                        DividerItemDecoration.VERTICAL
                    )
                )

                mBottomSheetDialog!!.dismiss()
                edtAmount!!.text.clear()
            }
        }
    }

    override fun onSuccessGetPurchasedItem(dataPurchasedItem: ArrayList<DataPurchasedItem?>?) {
        //Set Value
        purchasedItem = dataPurchasedItem as ArrayList<DataPurchasedItem>

        //Spinner Purchased Item
        spinnerPurchasedItemAdapter = SpinnerDialogItemAdapter(requireContext(), purchasedItem)
        spinnerPItem?.adapter = spinnerPurchasedItemAdapter

        spinnerPItem?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val adapter = parent?.adapter

                if(adapter is SpinnerDialogItemAdapter){
                    val item = adapter.getItem(position)
                    productId = item!!.productId
                    itemName = item.name
                    productUnit = item.unitName
                    productEntity = item.entity
                }
            }

        }
    }

    override fun onErrorGetPurchasedItem(msg: String?) {
        toast(msg ?: "Failed to get purchased item").show()
    }

    private inner class Loading : AsyncTask<Void?, Void?, Void?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            btnNext3!!.setEnabled(false)
            inAnimation = AlphaAnimation(0f, 1f)
            inAnimation!!.setDuration(200)
            progressBarHolder!!.setAnimation(inAnimation)
            progressBarHolder!!.setVisibility(View.VISIBLE)
        }

        override fun onPostExecute(aVoid: Void?) {
            super.onPostExecute(aVoid)
            outAnimation = AlphaAnimation(1f, 0f)
            outAnimation!!.setDuration(200)
            progressBarHolder!!.setVisibility(View.GONE)
            progressBarHolder!!.setAnimation(outAnimation)
            btnNext3!!.setEnabled(true)

            (activity as BookingActivity?)!!.nextStep3()
            statusFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(
                R.id.fragment_booking_container,
                statusFragment
            )
                ?.commit()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                for (i in 0..4) {
                    Log.d(myLog, "Emulating some task... step $i")
                    TimeUnit.SECONDS.sleep(2)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }
    }
}