package com.example.wyromed.Data.Remote

import com.example.wyromed.Data.Connection.ApiServices
import com.example.wyromed.Data.Connection.WyromedService
import com.example.wyromed.Data.Model.BasePagingResp
import com.example.wyromed.Data.Model.Hospital
import com.example.wyromed.Data.Model.RentalItem
import com.example.wyromed.Data.Source.BookingDataSource
import io.reactivex.rxjava3.core.Single

//class BookingRemoteRepository(private val apiService: WyromedService) : BookingDataSource {
//    override fun getHospital(): Single<BasePagingResp<Hospital>> {
//        return apiService.getAllHospital()
//    }
//
//    override fun getRentalItem(queryMap: MutableMap<String, String>): Single<BasePagingResp<RentalItem>> {
//        return apiService.getAllRentalItem(queryMap)
//    }
//}