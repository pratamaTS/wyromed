package com.example.wyromed.Model

import android.os.Parcel
import android.os.Parcelable

class StockRequestItem(
    val productId: Int,
    var quantity: Int,
    val productName: String?,
    val productUnit: String?,
    val productEntity: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(productId)
        dest?.writeInt(quantity)
        dest?.writeString(productName)
        dest?.writeString(productUnit)
        dest?.writeString(productEntity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StockRequestItem> {
        override fun createFromParcel(parcel: Parcel): StockRequestItem {
            return StockRequestItem(parcel)
        }

        override fun newArray(size: Int): Array<StockRequestItem?> {
            return arrayOfNulls(size)
        }
    }
}