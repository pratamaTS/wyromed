package com.example.wyromed.Model.Header

import android.os.Parcel
import android.os.Parcelable
import com.example.wyromed.Model.PurchasedItem

data class ListPurchasedItem (
    val productId: Int,
    var quantity: Int,
    val name: String?,
    val unitName: String?,
    val entity: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
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
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListPurchasedItem> {
        override fun createFromParcel(parcel: Parcel): ListPurchasedItem {
            return ListPurchasedItem(parcel)
        }

        override fun newArray(size: Int): Array<ListPurchasedItem?> {
            return arrayOfNulls(size)
        }
    }
}