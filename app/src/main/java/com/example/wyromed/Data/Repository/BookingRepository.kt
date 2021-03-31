package com.example.wyromed.Data.Repository

import com.example.wyromed.Data.Model.BasePagingResp
import com.example.wyromed.Data.Model.Hospital
import com.example.wyromed.Data.Model.RentalItem
import com.example.wyromed.Data.Source.BookingDataSource
import io.reactivex.rxjava3.core.Single

class BookingRepository(private val remoteRepository: BookingDataSource) : BookingDataSource {
    override fun getHospital(): Single<BasePagingResp<Hospital>> {
        return remoteRepository.getHospital()
    }

    override fun getRentalItem(queryMap: MutableMap<String, String>): Single<BasePagingResp<RentalItem>> {
        return remoteRepository.getRentalItem(queryMap)
    }
}