package com.example.wyromed.Model

import android.os.Parcel
import android.os.Parcelable

data class RentalItem(
    val product_id: Int,
    var quantity: Int,
    val product_name: String?,
    val product_unit: String?,
    val product_entity: String?,
    val start_date: String?,
    val start_time: String?) : Parcelable{

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
        dest?.writeInt(product_id)
        dest?.writeInt(quantity)
        dest?.writeString(product_name)
        dest?.writeString(product_unit)
        dest?.writeString(product_entity)
        dest?.writeString(start_date)
        dest?.writeString(start_time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RentalItem> {
        override fun createFromParcel(parcel: Parcel): RentalItem {
            return RentalItem(parcel)
        }

        override fun newArray(size: Int): Array<RentalItem?> {
            return arrayOfNulls(size)
        }
    }
}
