package com.example.wyromed.Data.Model

import com.google.gson.annotations.SerializedName

data class BasePagingResp<T> (

    @SerializedName("data")
    val data: List<T>? = listOf(),

    @SerializedName("size")
    val size: Int? = 0,

    @SerializedName("page")
    val page: Int? = 0,

    @SerializedName("total")
    val total: Int? = 0,

    @field:SerializedName("meta")
    val meta: BaseMeta? = null

)