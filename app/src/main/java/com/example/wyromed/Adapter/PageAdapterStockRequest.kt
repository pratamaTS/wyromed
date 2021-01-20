package com.example.wyromed.Adapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.wyromed.Activity.StockRequestActivity
import com.example.wyromed.Fragment.PenambahanStockFragment
import com.example.wyromed.Fragment.PenguranganStockFragment
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.StockRequestItem

class PageAdapterStockRequest(
    val tokenType: String,
    val token: String,
    val stockTambah: ArrayList<StockRequestDetails?>?,
    val stockMinus: ArrayList<StockRequestDetails?>?,
    stockRequestActivity: StockRequestActivity?,
    fm: FragmentManager?,
    var counttab: Int
) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        //Declare Var
        var bundle: Bundle = Bundle()
        var checkTambah: Boolean = false
        var checkMinus: Boolean = false
        bundle.putString("token_type", tokenType)
        bundle.putString("token", token)
        bundle.putParcelableArrayList("stock_request_add", stockTambah)
        bundle.putParcelableArrayList("stock_request_min", stockMinus)

        if(stockTambah!!.isNotEmpty() && stockMinus!!.isNotEmpty()){
            checkTambah = true
            checkMinus = true
            bundle.putBoolean("check_tambah", checkTambah)
            bundle.putBoolean("check_minus", checkMinus)
        }else if(stockTambah!!.isNotEmpty()){
            checkMinus = true
            bundle.putBoolean("check_tambah", checkTambah)
            bundle.putBoolean("check_minus", checkMinus)
        }else if (stockMinus!!.isNotEmpty()){
            checkTambah = true
            bundle.putBoolean("check_tambah", checkTambah)
            bundle.putBoolean("check_minus", checkMinus)
        }else{
            bundle.putBoolean("check_tambah", checkTambah)
            bundle.putBoolean("check_minus", checkMinus)
        }

        var penambahanStockFragment: PenambahanStockFragment = PenambahanStockFragment()
        var penguranganStockFragment: PenguranganStockFragment = PenguranganStockFragment()

        return when (position) {
            0 -> {
                penambahanStockFragment.apply {
                    penambahanStockFragment.arguments = bundle
                }
            }
            1 -> {
                penguranganStockFragment.apply {
                    penguranganStockFragment.arguments = bundle
                }
            }
            else -> penambahanStockFragment.apply {
                penambahanStockFragment.arguments = bundle
            }
        }
    }

    override fun getCount(): Int {
        return counttab
    }

    interface CallbackStockTambahInterface {
        fun passDataTambahCallback(stockAdd: ArrayList<StockRequestItem?>?)
    }
}