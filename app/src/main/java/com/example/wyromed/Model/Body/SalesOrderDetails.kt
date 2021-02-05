package com.example.wyromed.Model.Body

import android.os.Parcel
import android.os.Parcelable
import com.example.wyromed.Model.RentalItem
import com.google.gson.annotations.SerializedName

class SalesOrderDetails (
    val product_id: Int,
	val price_id: Int,
    var quantity: Int,
    val product_name: String?,
    val product_unit: String?,
    val product_entity: String?,
	val product_price: Double?,
	val subtotal_price: Double?) : Parcelable {

	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readInt(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readDouble(),
		parcel.readDouble()) {
	}

	override fun writeToParcel(dest: Parcel?, flags: Int) {
		dest?.writeInt(product_id)
		dest?.writeInt(quantity)
		dest?.writeString(product_name)
		dest?.writeString(product_unit)
		dest?.writeString(product_entity)
		dest?.writeDouble(product_price!!)
		dest?.writeDouble(subtotal_price!!)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<SalesOrderDetails> {
		override fun createFromParcel(parcel: Parcel): SalesOrderDetails {
			return SalesOrderDetails(parcel)
		}

		override fun newArray(size: Int): Array<SalesOrderDetails?> {
			return arrayOfNulls(size)
		}
	}
}


