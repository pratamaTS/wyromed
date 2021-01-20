package com.example.wyromed.Response.Notification

import com.google.gson.annotations.SerializedName

class DataNotification: ResponseNotification() {

    @field:SerializedName("inbox")
    val inbox: Int? = null

    @field:SerializedName("booking")
    val booking: Int? = null

    @field:SerializedName("sales_order")
    val salesOrder: Int? = null

    @field:SerializedName("handover")
    val handover: Int? = null
}