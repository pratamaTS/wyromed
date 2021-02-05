package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chinodev.androidneomorphframelayout.NeomorphFrameLayout
import com.example.wyromed.Activity.Interface.*
import com.example.wyromed.Activity.Presenter.*
import com.example.wyromed.Adapter.DetailTablePesananAdapter
import com.example.wyromed.Adapter.DetailTablePesananSOAdapter
import com.example.wyromed.Adapter.DetailTablePesananSRAdapter
import com.example.wyromed.Model.Body.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.Response.DetailMessageBooking.DataDetailMessageBooking
import com.example.wyromed.Response.HeaderMessageBooking.DataHeaderMessageBooking
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.DataDetailMessageSalesOrder
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.DataDetailMessageStockReq
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.DataHeaderMessageSalesOrder
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.DataHeaderMessageStockReq
import com.example.wyromedapp.Adapter.ListHandoverPItemAdapter
import com.example.wyromedapp.Adapter.ListHandoverRItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

class DetailMessageActivity: BaseActivity(), HeaderMessageBookingInterface,
    DetailMessageBookingInterface,
    HeaderMessageStockRequestInterface,
    DetailMessageStockRequestInterface,
    HeaderMessageSalesOrderInterface,
    DetailMessageSalesOrderInterface
{
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val ID = "id"
        val CHOOSE = "choose"
        val TITLE = "title"
    }

    var detailListBooking: ArrayList<DataDetailMessageBooking> = ArrayList()
    var detailListSR: ArrayList<DataDetailMessageStockReq> = ArrayList()
    var detailListSO: ArrayList<DataDetailMessageSalesOrder> = ArrayList()
    var orderRentalItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var orderPurchasedItemList: ArrayList<HandoverPurchasedItem> = ArrayList()
    var rvTableBarangPesanan: RecyclerView? = null
    var tvJudulPesanan: TextView? = null
    var tvWaktuPesanan: TextView? = null
    var tvTanggalPesanan: TextView? = null
    var tvNoPesanan: TextView? = null
    var tvStatusPesanan: TextView? = null
    var adapter: DetailTablePesananAdapter? = null
    var adapterSR: DetailTablePesananSRAdapter? = null
    var adapterSO: DetailTablePesananSOAdapter? = null
    var toolbar: Toolbar? = null
    var btnCancelPesanan: Button? = null
    var btnConfirmPesanan: Button? = null
    var btnCheckPesanan: Button? = null
    var layoutBtnCancelPesanan: NeomorphFrameLayout? = null
    var layoutBtnConfirmPesanan: NeomorphFrameLayout? = null
    var layoutBtnCheckPesanan: NeomorphFrameLayout? = null
    var salesOrderHeader: SalesOrderHeader = SalesOrderHeader()
    var choose: Int = 0
    var id: Int = 0
    var title: String? = null
    var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_message)

        //INIT VIEW
        rvTableBarangPesanan = findViewById(R.id.rv_table_barang_pesanan_detail_message)
        toolbar = findViewById(R.id.toolbar_detail_message)
        btnCancelPesanan = findViewById(R.id.btn_cancel_pesanan)
        btnConfirmPesanan = findViewById(R.id.btn_confirm_pesanan)
        btnCheckPesanan = findViewById(R.id.btn_check_pesanan)
        tvJudulPesanan = findViewById(R.id.tv_judul_keterangan_pesanan)
        tvWaktuPesanan = findViewById(R.id.tv_waktu_pesanan)
        tvTanggalPesanan = findViewById(R.id.tv_tgl_pesanan)
        tvNoPesanan = findViewById(R.id.tv_nomor_pesanan)
        tvStatusPesanan = findViewById(R.id.tv_status_pesanan)
        layoutBtnCancelPesanan = findViewById(R.id.layout_btn_cancel_pesanan)
        layoutBtnConfirmPesanan = findViewById(R.id.layout_btn_confirm_pesanan)
        layoutBtnCheckPesanan = findViewById(R.id.layout_btn_check_pesanan)
        layoutBtnCancelPesanan?.visibility = View.GONE
        layoutBtnConfirmPesanan?.visibility = View.GONE
        layoutBtnCheckPesanan?.visibility = View.GONE

        choose = intent.getIntExtra("choose", 0)
        id = intent.getIntExtra("id", 0)
        title = intent.getStringExtra("title")

        setSupportActionBar(toolbar)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)
        toolbar!!.setNavigationIcon(R.drawable.ic_back_white)
        toolbar!!.setNavigationOnClickListener { finish() }

        getPesanan()

        //SET LISTENER
        initActionButton()
    }

    private fun getPesanan(){

        when(choose){
            1 -> {
                HeaderMessageBookingPresenter(this).getHeaderMessageBooking(this, id)
                DetailMessageBookingPresenter(this).getDetailMessageBooking(this, id)
            }
            2-> {
                HeaderMessageSalesOrderPresenter(this).getHeaderMessageSO(this, id)
                DetailMessageSalesOrderPresenter(this).getDetailMessageSO(this, id)
            }
            3-> {
                HeaderMessageStockRequestPresenter(this).getHeaderMessageSR(this, id)
                DetailMessageStockRequestPresenter(this).getDetailMessageSR(this, id)
            }
            else -> toast("error, something wrong")
        }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_confirm_pesanan -> {
                val confim = Intent(this@DetailMessageActivity, HandoverActivity::class.java)
                startActivity(confim)
            }
        }
    }

    private fun initActionButton(){
        btnConfirmPesanan!!.onClick {
            startActivity<HandoverActivity>(
                HandoverActivity.TAGS.MESSAGE to message,
                HandoverActivity.TAGS.ID to id
            )
            finish()
        }
        btnCheckPesanan!!.onClick {
            startActivity<InUseActivity>(
                InUseActivity.TAGS.MESSAGE to message,
                InUseActivity.TAGS.ID to id,
                InUseActivity.TAGS.RENTAL to orderRentalItemList,
                InUseActivity.TAGS.BMHP to orderPurchasedItemList
            )
        }
    }

    private fun setVisibility(){
        layoutBtnCancelPesanan?.visibility = View.GONE
        layoutBtnConfirmPesanan?.visibility = View.GONE
        layoutBtnCheckPesanan?.visibility = View.VISIBLE
    }

    override fun onSuccessHeaderMessageBooking(
        message: String?,
        dataHeaderMessageBooking: DataHeaderMessageBooking?
    ) {
        when(dataHeaderMessageBooking?.statusInt){
            1 -> {
                layoutBtnCancelPesanan?.visibility = View.VISIBLE
                layoutBtnConfirmPesanan?.visibility = View.VISIBLE
            }
            else -> setVisibility()
        }

        tvJudulPesanan!!.text = title.toString()
        tvWaktuPesanan!!.text = dataHeaderMessageBooking!!.day.toString() + " " + dataHeaderMessageBooking!!.month + " " + dataHeaderMessageBooking!!.year.toString() + "  " + dataHeaderMessageBooking!!.time
        tvNoPesanan!!.text = dataHeaderMessageBooking!!.number
        tvTanggalPesanan!!.text = dataHeaderMessageBooking!!.day.toString() + " " + dataHeaderMessageBooking!!.month + " " + dataHeaderMessageBooking!!.year.toString()
        tvStatusPesanan!!.text = dataHeaderMessageBooking!!.status
    }

    override fun onErrorHeaderMessageBooking(msg: String?) {
        toast("Failed to get data header booking")
    }

    override fun onSuccessDetailMessageBooking(
        message: String?,
        dataDetailMessageBooking: ArrayList<DataDetailMessageBooking?>?
    ) {
        detailListBooking = dataDetailMessageBooking as ArrayList<DataDetailMessageBooking>

        rvTableBarangPesanan!!.setHasFixedSize(true)
        rvTableBarangPesanan!!.layoutManager = LinearLayoutManager(this)
        adapter = DetailTablePesananAdapter(this, detailListBooking)
        rvTableBarangPesanan!!.adapter = adapter

        for(itemDetail in detailListBooking){
            if(itemDetail.productEntity == "TINDAKAN"){
                orderRentalItemList.add(
                    HandoverRentalItem(
                        itemDetail.productId!!.toInt(),
                        itemDetail.quantity!!.toInt(),
                        itemDetail.productName,
                        itemDetail.productUnit,
                        itemDetail.productEntity
                    )
                )
            }else{
                orderPurchasedItemList.add(
                    HandoverPurchasedItem(
                        itemDetail.productId!!.toInt(),
                        itemDetail.quantity!!.toInt(),
                        itemDetail.productName,
                        itemDetail.productUnit,
                        itemDetail.productEntity
                    )
                )
            }
        }

    }

    override fun onErrorDetailMessageBooking(msg: String?) {
        toast("Failed to get data details booking")
    }

    override fun onSuccessHeaderMessageSR(
        message: String?,
        dataHeaderMessageStockReq: DataHeaderMessageStockReq?
    ) {

        layoutBtnCancelPesanan?.visibility = View.GONE
        layoutBtnConfirmPesanan?.visibility = View.GONE
        layoutBtnCheckPesanan?.visibility = View.GONE
        tvJudulPesanan!!.text = title.toString()
        tvWaktuPesanan!!.text = dataHeaderMessageStockReq!!.day.toString() + " " + dataHeaderMessageStockReq!!.month + " " + dataHeaderMessageStockReq!!.year.toString() + "  "
        tvNoPesanan!!.text = dataHeaderMessageStockReq!!.number
        tvTanggalPesanan!!.text = dataHeaderMessageStockReq!!.day.toString() + " " + dataHeaderMessageStockReq!!.month + " " + dataHeaderMessageStockReq!!.year.toString()
        tvStatusPesanan!!.text = dataHeaderMessageStockReq!!.status
    }

    override fun onErrorHeaderMessageSR(msg: String?) {
        toast("Failed to get data Stock Request header")
    }

    override fun onSuccessDetailMessageSR(
        message: String?,
        dataDetailMessageStockReq: ArrayList<DataDetailMessageStockReq?>?
    ) {
        detailListSR = dataDetailMessageStockReq as ArrayList<DataDetailMessageStockReq>

        rvTableBarangPesanan!!.setHasFixedSize(true)
        rvTableBarangPesanan!!.layoutManager = LinearLayoutManager(this)
        adapterSR = DetailTablePesananSRAdapter(this, detailListSR)
        rvTableBarangPesanan!!.adapter = adapterSR
    }

    override fun onErrorDetailMessageSR(msg: String?) {
        toast("Failed to get data Stock Request detail")
    }

    override fun onSuccessHeaderMessageSO(
        message: String?,
        dataHeaderMessageSalesOrder: DataHeaderMessageSalesOrder?
    ) {
        layoutBtnCancelPesanan?.visibility = View.GONE
        layoutBtnConfirmPesanan?.visibility = View.GONE
        layoutBtnCheckPesanan?.visibility = View.GONE
        tvJudulPesanan!!.text = title.toString()
        tvWaktuPesanan!!.text = dataHeaderMessageSalesOrder!!.day.toString() + " " + dataHeaderMessageSalesOrder!!.month + " " + dataHeaderMessageSalesOrder!!.year.toString() + "  "
        tvNoPesanan!!.text = dataHeaderMessageSalesOrder!!.number
        tvTanggalPesanan!!.text = dataHeaderMessageSalesOrder!!.day.toString() + " " + dataHeaderMessageSalesOrder!!.month + " " + dataHeaderMessageSalesOrder!!.year.toString()
        tvStatusPesanan!!.text = dataHeaderMessageSalesOrder!!.status
    }

    override fun onErrorHeaderMessageSO(msg: String?) {
        toast("Failed to get data header message SO")
    }

    override fun onSuccessDetailMessageSO(
        message: String?,
        dataDetailMessageSalesOrder: ArrayList<DataDetailMessageSalesOrder?>?
    ) {
        detailListSO = dataDetailMessageSalesOrder as ArrayList<DataDetailMessageSalesOrder>

        rvTableBarangPesanan!!.setHasFixedSize(true)
        rvTableBarangPesanan!!.layoutManager = LinearLayoutManager(this)
        adapterSO = DetailTablePesananSOAdapter(this, detailListSO)
        rvTableBarangPesanan!!.adapter = adapterSO
    }

    override fun onErrorDetailMessageSO(msg: String?) {
        toast("Failed to get data detail message SO")
    }
}