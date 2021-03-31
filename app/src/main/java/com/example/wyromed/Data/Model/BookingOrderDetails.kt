package com.example.wyromed.Data.Model

import android.os.Parcel
import android.os.Parcelable

data class BookingOrderDetails (
    val product_id: Int,
    var quantity: Int,
    val product_name: String?,
    val product_unit: String?,
    val product_entity: String?) : Parcelable {

	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readInt(),
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
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<BookingOrderDetails> {
		override fun createFromParcel(parcel: Parcel): BookingOrderDetails {
			return BookingOrderDetails(parcel)
		}

		override fun newArray(size: Int): Array<BookingOrderDetails?> {
			return arrayOfNulls(size)
		}
	}
}


