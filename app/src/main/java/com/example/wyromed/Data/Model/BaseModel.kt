package com.example.wyromed.Data.Model

import com.google.gson.annotations.SerializedName

open class BaseModel(
    @SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("created_by")
    val createdBy: String? = null,

    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("updated_by")
    val updatedBy: String? = null,

    @SerializedName("deleted_at")
    val deletedAt: String? = null,

    @field:SerializedName("deleted_by")
    val deletedBy: Any? = null,

)