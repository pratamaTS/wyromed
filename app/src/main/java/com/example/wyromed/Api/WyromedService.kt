package com.example.wyromed.Api

import com.example.wyromed.Api.Url.Constants
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
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.ResponseDetailMessageSalesOrder
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.ResponseDetailMessageStockReq
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.ResponseHeaderMessageSalesOrder
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.ResponseHeaderMessageStockReq
import com.example.wyromed.Response.StockRequest.ResponseGetStockRequest
import com.example.wyromed.Response.StockRequest.ResponseStockRequest
import retrofit2.Call
import retrofit2.http.*

interface WyromedService {
    //Sign In
    @POST(Constants.LOGIN_URL)
    fun signin(@Body signingBody: SignInBody): Call<DataLogin>

    //Sing Up
    @POST(Constants.SIGNUP_URL)
    fun signup(@QueryMap queryMap: MutableMap<String, String>?, @Body signupBody: RegisterBody): Call<DataLogin>

    //Get User
    @GET(Constants.USER_URL)
    fun getUser(): Call<ResponseLogin>

    //Get All Hospital
    @GET(Constants.HOSPITAL_URL)
    fun getAllHospital(): Call<ResponseHospital>

    //Get All Patient
    @GET(Constants.PATIENT_URL)
    fun getAllPatient(): Call<ResponsePatient>

    //Post Patient
    @POST(Constants.POST_PATIENT_URL)
    fun storePatient(@Body patientBody: PatientBody): Call<ResponseStorePatient>

    //Get Rental Stock
    @GET(Constants.RENTAL_STOCK_URL)
    fun getAllRentalItem(@QueryMap queryMap: MutableMap<String, String>?): Call<ResponseRentalItem>

    //Get Purchased Stock
    @GET(Constants.BMHP_STOCK_URL)
    fun getAllPurchasedItem(@QueryMap queryMap: MutableMap<String, String>?): Call<ResponsePurchasedItem>

    //Get All Stock
    @GET(Constants.ALL_STOCK_URL)
    fun getAllStockItem(@QueryMap queryMap: MutableMap<String, String>?): Call<ResponseStock>

    //Get Total Stock Available
    @GET(Constants.TOTAL_STOCK_AVAILABLE_URL)
    fun getTotalStockItem(@QueryMap queryMap: MutableMap<String, String>?): Call<ResponseTotalQtyStock>

    @GET(Constants.TOTAL_SO_URL)
    fun getTotalSalesOrder(): Call<ResponseTotalSalesOrder>

    @GET(Constants.TOTAL_BO_URL)
    fun getTotalBookingOrder(): Call<ResponseTotalBookingOrder>

    //Store Booking
    @Multipart
    @POST(Constants.STORE_BO_URL)
    fun storeBooking(@PartMap bodyMap: HashMap<String, Any>?) : Call<ResponseBooking>

    //Stock Request
    @Multipart
    @POST(Constants.STORE_SR_URL)
    fun storeStockRequest(@PartMap bodyMap: HashMap<String, Any>?) : Call<ResponseStockRequest>

    //Get Stock Requested
    @GET(Constants.GET_SR_URL)
    fun getAllStockRequest(): Call<ResponseGetStockRequest>

    //Get Booking Ordered
    @GET(Constants.GET_BO_URL)
    fun getBookingOrdered(): Call<ResponseOrder>

    //Get All Province
    @GET(Constants.GET_PROVINCE_URL)
    fun getProvince(): Call<ResponseProvince>

    //Get All City
    @GET
    fun getCity(@Url url: String): Call<ResponseCity>

    //Get All Inbox
    @GET(Constants.GET_INBOX_URL)
    fun getAllInbox(): Call<ResponseInbox>

    //Get Header Message Booking
    @GET
    fun getHeaderMessageBooking(@Url url: String): Call<ResponseHeaderMessageBooking>

    //Get Detail Message Booking
    @GET
    fun getDetailMessageBooking(@Url url: String): Call<ResponseDetailMessageBooking>

    //Get Header Message Stock request
    @GET
    fun getHeaderMessageSR(@Url url: String): Call<ResponseHeaderMessageStockReq>

    //Get Detail Message Stock Request
    @GET
    fun getDetailMessageSR(@Url url: String): Call<ResponseDetailMessageStockReq>

    //Get Header Message Sales order
    @GET
    fun getHeaderMessageSO(@Url url: String): Call<ResponseHeaderMessageSalesOrder>

    //Get Detail Message Sales order
    @GET
    fun getDetailMessageSO(@Url url: String): Call<ResponseDetailMessageSalesOrder>

    //Get Header Handover Booking
    @GET
    fun getHeaderHandoverBooking(@Url url: String): Call<ResponseHandoverHeader>

    //Get Detail Handover Booking
    @GET
    fun getDetailHandoverBooking(@Url url: String): Call<ResponseHandoverDetail>

    //Update Status Booking
    @POST
    fun updateBookingStatus(@Url url: String, @QueryMap queryMap: MutableMap<String, String>?): Call<ResponseUpdateStatusBooking>
}