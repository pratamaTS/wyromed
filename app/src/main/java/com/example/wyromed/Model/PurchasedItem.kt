package com.example.wyromed.Model

import android.os.Parcel
import android.os.Parcelable

class PurchasedItem(
    val productId: Int,
    var quantity: Int,
    val name: String?,
    val unitName: String?,
    val entity: String?,
    val start_date: String?,
    val start_time: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(productId)
        dest?.writeInt(quantity)
        dest?.writeString(name)
        dest?.writeString(unitName)
        dest?.writeString(entity)
        dest?.writeString(start_date)
        dest?.writeString(start_time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PurchasedItem> {
        override fun createFromParcel(parcel: Parcel): PurchasedItem {
            return PurchasedItem(parcel)
        }

        override fun newArray(size: Int): Array<PurchasedItem?> {
            return arrayOfNulls(size)
        }
    }
}