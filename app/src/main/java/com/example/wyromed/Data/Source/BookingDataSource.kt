package com.example.wyromed.Data.Source

import com.example.wyromed.Data.Model.BasePagingResp
import com.example.wyromed.Data.Model.BaseResp
import com.example.wyromed.Data.Model.Hospital
import com.example.wyromed.Data.Model.RentalItem
import io.reactivex.rxjava3.core.Single

interface BookingDataSource {
    fun getHospital() : Single<BasePagingResp<Hospital>>
    fun getRentalItem(queryMap: MutableMap<String, String>) : Single<BasePagingResp<RentalItem>>
//    fun getFitting(fittingId: String) : Single<BaseResp<Fitting>>
//    fun saveFitting(fitting: Fitting) : Single<BaseResp<Fitting>>
//    fun getProductUnavailableDates(productId: String) : Single<BasePagingResp<UnavailableDate>>
//    fun createBooking(createBooking: ReqCreateBooking) : Single<BaseResp<Booking>>
//    fun cancelBooking(reqCancel: ReqCancelBooking) : Single<BaseResp<Booking>>
//    fun getMyBookings() : Single<BasePagingResp<Booking>>
//    fun getMyBookingsHistory() : Single<BasePagingResp<Booking>>
//    fun confirmPayment(transactionId: String, reqConfirmPayment: ReqConfirmPayment) : Single<BaseResp<Booking>>
//    fun confirm2ndPayment(transactionId: String, reqConfirmPayment: ReqConfirm2ndPayment) : Single<BaseResp<Booking>>
//    fun reviewBooking(reqCreateReviewBooking: ReqReviewBooking) : Single<BaseResp<ProductReview>>
//    fun getMyInvoices() : Single<BasePagingResp<InvoiceHistory>>
//    fun getInvoiceDetail(invoiceId: String) : Single<BaseResp<Invoice>>
}