package com.example.wyromed.Response.Message

import com.google.gson.annotations.SerializedName

class DataMessage: ResponseMessage() {

    @field:SerializedName("message_id")
    val messageID: Int? = null

    @field:SerializedName("title_message")
    val titleMessage: String? = null

    @field:SerializedName("detail_message")
    val detailMessage: String? = null

    @field:SerializedName("time_message")
    val timeMessage: String? = null
}