package com.example.wyromed.Api

import com.example.wyromed.Model.Body.*
import com.example.wyromed.Response.Booking.ResponseBooking
import com.example.wyromed.Response.Booking.ResponseTotalBookingOrder
import com.example.wyromed.Response.Booking.ResponseUpdateStatusBooking
import com.example.wyromed.Response.DetailMessageBooking.ResponseDetailMessageBooking
import com.example.wyromed.Response.Handover.ResponseHandoverDetail
import com.example.wyromed.Response.Handover.ResponseHandoverHeader
import com.example.wyromed.Response.HeaderMessageBooking.ResponseHeaderMessageBooking
import com.example.wyromed.Response.Hospital.ResponseHospital
import com.example.wyromed.Response.Inbox.ResponseInbox
import com.example.wyromed.Response.Login.DataLogin
import com.example.wyromed.Response.Login.ResponseLogin
import com.example.wyromed.Response.Order.ResponseOrder
import com.example.wyromed.Response.Patient.ResponsePatient
import com.example.wyromed.Response.Patient.ResponseStorePatient
import com.example.wyromed.Response.Province.ResponseCity
import com.example.wyromed.Response.Province.ResponseProvince
import com.example.wyromed.Response.PurchasedItem.ResponsePurchasedItem
import com.example.wyromed.Response.QuantityAvailableStock.ResponseTotalQtyStock
import com.example.wyromed.Response.RentalItem.ResponseRentalItem
import com.example.wyromed.Response.SalesOrder.ResponseTotalSalesOrder
import com.example.wyromed.Response.Stock.ResponseStock
import com.example.wyromed.Response.StockRequest.ResponseGetStockRequest
import com.example.wyromed.Response.StockRequest.ResponseStockRequest
import retrofit2.Call
import retrofit2.http.*

interface WyromedService {
    //Sign In
    @POST("login")
    fun signin(@HeaderMap map: MutableMap<String, String>?, @Body signingBody: SignInBody): Call<DataLogin>

    //Sing Up
    @POST("users")
    fun signup(@HeaderMap map: MutableMap<String, String>?, @Body signupBody: RegisterBody): Call<DataLogin>

    //Get User
    @GET("login")
    fun getUser(@HeaderMap map: MutableMap<String, String>?): Call<ResponseLogin>

    //Get All Hospital
    @GET("master_hospital")
    fun getAllHospital(@HeaderMap map: MutableMap<String, String>?): Call<ResponseHospital>

    //Get All Patient
    @GET("master_patients")
    fun getAllPatient(@HeaderMap map: MutableMap<String, String>?): Call<ResponsePatient>

    //Post Patient
    @POST("patients")
    fun storePatient(@HeaderMap map: MutableMap<String, String>?, @Body patientBody: PatientBody): Call<ResponseStorePatient>

    //Get Rental Stock
    @GET("stock/bo")
    fun getAllRentalItem(@QueryMap queryMap: MutableMap<String, String>?, @HeaderMap map: MutableMap<String, String>?): Call<ResponseRentalItem>

    //Get Purchased Stock
    @GET("stock")
    fun getAllPurchasedItem(@QueryMap queryMap: MutableMap<String, String>?, @HeaderMap map: MutableMap<String, String>?): Call<ResponsePurchasedItem>

    //Get All Stock
    @GET("stock")
    fun getAllStockItem(@QueryMap queryMap: MutableMap<String, String>?, @HeaderMap map: MutableMap<String, String>?): Call<ResponseStock>

    //Get Total Stock Available
    @GET("stock/totalavailable")
    fun getTotalStockItem(@QueryMap queryMap: MutableMap<String, String>?, @HeaderMap map: MutableMap<String, String>?): Call<ResponseTotalQtyStock>

    @GET("soandroid/total/order")
    fun getTotalSalesOrder(@HeaderMap map: MutableMap<String, String>?): Call<ResponseTotalSalesOrder>

    @GET("bookingorder/total/order")
    fun getTotalBookingOrder(@HeaderMap map: MutableMap<String, String>?): Call<ResponseTotalBookingOrder>

    //Store Booking
    @Multipart
    @POST("bookingorder")
    fun storeBooking(@HeaderMap map: MutableMap<String, String>?, @PartMap bodyMap: HashMap<String, Any>?) : Call<ResponseBooking>

    //Stock Request
    @Multipart
    @POST("stockrequest")
    fun storeStockRequest(@HeaderMap map: MutableMap<String, String>?, @PartMap bodyMap: HashMap<String, Any>?) : Call<ResponseStockRequest>

    //Get Stock Requested
    @GET("stockrequest")
    fun getAllStockRequest(@HeaderMap map: MutableMap<String, String>?): Call<ResponseGetStockRequest>

    //Get Booking Ordered
    @GET("bookingorder")
    fun getBookingOrdered(@HeaderMap map: MutableMap<String, String>?): Call<ResponseOrder>

    //Get All Province
    @GET("states/102")
    fun getProvince(@HeaderMap map: MutableMap<String, String>?): Call<ResponseProvince>

    //Get All City
    @GET
    fun getCity(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseCity>

    //Get All Inbox
    @GET("inbox")
    fun getAllInbox(@HeaderMap map: MutableMap<String, String>?): Call<ResponseInbox>

    //Get Header Message Booking
    @GET
    fun getHeaderMessageBooking(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseHeaderMessageBooking>

    //Get Detail Message Booking
    @GET
    fun getDetailMessageBooking(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseDetailMessageBooking>

    //Get Header Handover Booking
    @GET
    fun getHeaderHandoverBooking(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseHandoverHeader>

    //Get Detail Handover Booking
    @GET
    fun getDetailHandoverBooking(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseHandoverDetail>

    //Update Status Booking
    @POST
    fun updateBookingStatus(@Url url: String, @QueryMap queryMap: MutableMap<String, String>?, @HeaderMap map: MutableMap<String, String>?): Call<ResponseUpdateStatusBooking>
}