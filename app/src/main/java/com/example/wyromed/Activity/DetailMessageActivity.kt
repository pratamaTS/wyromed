package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.DetailMessageBookingInterface
import com.example.wyromed.Activity.Interface.HeaderMessageBookingInterface
import com.example.wyromed.Activity.Interface.UpdateStatusBookingInterface
import com.example.wyromed.Activity.Presenter.DetailMessageBookingPresenter
import com.example.wyromed.Activity.Presenter.HeaderMessageBookingPresenter
import com.example.wyromed.Adapter.DetailTablePesananAdapter
import com.example.wyromed.Model.DetailTablePesanan
import com.example.wyromed.R
import com.example.wyromed.Response.DetailMessageBooking.DataDetailMessageBooking
import com.example.wyromed.Response.HeaderMessageBooking.DataHeaderMessageBooking
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

class DetailMessageActivity: BaseActivity(), HeaderMessageBookingInterface, DetailMessageBookingInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val ID = "id"
        val CHOOSE = "choose"
        val TITLE = "title"
    }
    var headerMessageBooking: DataHeaderMessageBooking? = null
    var detailListBooking: ArrayList<DataDetailMessageBooking> = ArrayList()
    var rvTableBarangPesanan: RecyclerView? = null
    var tvJudulPesanan: TextView? = null
    var tvWaktuPesanan: TextView? = null
    var tvTanggalPesanan: TextView? = null
    var tvNoPesanan: TextView? = null
    var tvStatusPesanan: TextView? = null
    var adapter: DetailTablePesananAdapter? = null
    var toolbar: Toolbar? = null
    var btnCancelPesanan: Button? = null
    var btnConfirmPesanan: Button? = null
    var btnCheckPesanan: Button? = null
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

        user?.token_type = intent.getStringExtra("token_type")
        user?.token = intent.getStringExtra("token")
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

        if(choose == 1){
            HeaderMessageBookingPresenter(this).getHeaderMessageBooking(user?.token_type, user?.token, id)
            DetailMessageBookingPresenter(this).getDetailMessageBooking(user?.token_type, user?.token, id)
        }else {
            toast("error, something wrong")
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
                HandoverActivity.TAGS.TOKENTYPE to user?.token_type,
                HandoverActivity.TAGS.TOKEN to user?.token,
                HandoverActivity.TAGS.MESSAGE to message,
                HandoverActivity.TAGS.ID to id)
        }
        btnCheckPesanan!!.onClick {
            startActivity<InUseActivity>(
                InUseActivity.TAGS.TOKENTYPE to user?.token_type,
                InUseActivity.TAGS.TOKEN to user?.token,
                InUseActivity.TAGS.MESSAGE to message,
                InUseActivity.TAGS.ID to id
            )
        }
    }

    private fun setVisibility(){
        btnCancelPesanan?.visibility = View.GONE
        btnConfirmPesanan?.text = "Check"
    }

    override fun onSuccessHeaderMessageBooking(
        message: String?,
        dataHeaderMessageBooking: DataHeaderMessageBooking?
    ) {
        when(dataHeaderMessageBooking?.statusInt){
            1 -> btnCancelPesanan?.visibility = View.VISIBLE
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
    }

    override fun onErrorDetailMessageBooking(msg: String?) {
        toast("Failed to get data details booking")
    }
}