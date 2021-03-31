package com.example.wyromed.Data.Model

import com.google.gson.annotations.SerializedName

data class BaseResp<T> (

    @field:SerializedName("data")
    val data: T? = null,

    @field:SerializedName("meta")
    val meta: BaseMeta? = null

)