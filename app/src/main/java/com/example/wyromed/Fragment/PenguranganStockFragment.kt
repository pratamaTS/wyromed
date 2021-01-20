package com.example.wyromed.Fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chinodev.androidneomorphframelayout.NeomorphFrameLayout
import com.example.wyromed.Activity.HistoryReqStockActivity
import com.example.wyromed.Activity.Interface.StoreStockRequestInterface
import com.example.wyromed.Activity.Presenter.StoreStockRequestPresenter
import com.example.wyromed.Adapter.PenguranganRequestAdapter
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.Body.StockRequestHeader
import com.example.wyromed.Model.StockRequestItem
import com.example.wyromed.R
import com.example.wyromed.Response.StockRequest.DataStockRequest
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.w3c.dom.Text
import java.util.*

class PenguranganStockFragment : Fragment(), StoreStockRequestInterface {

    var rvStockRequest: RecyclerView? = null
    var adapter: PenguranganRequestAdapter? = null
    var btnSubmit: Button? = null
    var tokenType: String? = null
    var token: String? = null
    var tvNoItem: TextView? = null
    var stockRequestHeader: StockRequestHeader = StockRequestHeader()
    var stockRequestItem: ArrayList<StockRequestDetails?> = ArrayList()
    var checkDifReq: Boolean? = null
    var stockRequested: DataStockRequest = DataStockRequest()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_pengurangan_stock, container, false)

        //INIT VIEW
        rvStockRequest = view.findViewById(R.id.rv_pengurangan_stock)
        btnSubmit = view.findViewById(R.id.btn_submit_pengurangan)
        val layoutBtnSubmit = view.findViewById<NeomorphFrameLayout>(R.id.layout_btn_submit_pengurangan)
        tvNoItem = view.findViewById(R.id.empty_view_pengurangan)

        //List Request
        tokenType = arguments?.getString("token_type")
        token = arguments?.getString("token")
        checkDifReq = arguments?.getBoolean("check_tambah")
        stockRequestItem = arguments?.getParcelableArrayList("stock_request_min")!!

        if(stockRequestItem.isNotEmpty()){
            layoutBtnSubmit.visibility = View.VISIBLE
        }else{
            rvStockRequest?.visibility = View.GONE
            tvNoItem!!.visibility = View.VISIBLE
        }

        adapter = PenguranganRequestAdapter(context, stockRequestItem)
        rvStockRequest?.setLayoutManager(LinearLayoutManager(context))
        rvStockRequest?.setAdapter(adapter)
        rvStockRequest?.setHasFixedSize(false)

        //Set Listener
        initActionButton()

        return view
    }

    private fun initActionButton() {
        btnSubmit!!.onClick {
            btnSubmit!!.isEnabled = false

            // Insert Header
            stockRequestHeader!!.warehousePusatId = 1
            stockRequestHeader!!.type = "SUBTRACTION"
            stockRequestHeader!!.note = "Pengajuan Pengurangan Stock sukses!"

            StoreStockRequestPresenter(this@PenguranganStockFragment).storeStockRequest(tokenType, token, stockRequestHeader, stockRequestItem)
        }
    }

    override fun onSuccessStockRequest(message: String?, dataStockReq: DataStockRequest?) {
        stockRequested = dataStockReq as DataStockRequest

        val openDialog = Dialog(requireActivity())
        openDialog.setContentView(R.layout.item_dialog_request_success)
        val tvNoRequest = openDialog.findViewById<TextView>(R.id.tv_dialog_no_request)
        val btnOk = openDialog.findViewById<Button>(R.id.dialog_btn_oke)
        val submitDifReqLayout = openDialog.findViewById<NeomorphFrameLayout>(R.id.layout_dialog_btn_submit_again)
        val btnSubmitDifReq = openDialog.findViewById<Button>(R.id.dialog_btn_submit_again)

        // Check different item
        if(checkDifReq == true){
            submitDifReqLayout.visibility = View.VISIBLE
        }

        // Set Value
        tvNoRequest.text = stockRequested!!.number

        // Open Dialog
        openDialog.setCanceledOnTouchOutside(true)

        btnSubmitDifReq.onClick {
            stockRequestItem.clear()
            openDialog.dismiss()
            rvStockRequest?.visibility = View.GONE
            tvNoItem!!.visibility = View.VISIBLE
            btnSubmit!!.visibility = View.GONE
        }

        btnOk.onClick {
            openDialog.dismiss()
            startActivity<HistoryReqStockActivity>(
                HistoryReqStockActivity.TAGS.TOKENTYPE to tokenType,
                HistoryReqStockActivity.TAGS.TOKEN to token,
                HistoryReqStockActivity.TAGS.MESSAGE to message) }

        openDialog.show()
    }

    override fun onErrorStockRequest(msg: String?) {
        toast("Failed to request stock")
    }
}