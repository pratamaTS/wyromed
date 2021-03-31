package com.example.wyromed.Data.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SalesOrderDetail(

	@field:SerializedName("subtotal_price")
	val subtotalPrice: Double? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("price_id")
	val priceId: Int? = null,

	@field:SerializedName("product_entity")
	val productEntity: String? = null,

	@field:SerializedName("product_price")
	val productPrice: Double? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("product_unit")
	val productUnit: String? = null
): Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Double::class.java.classLoader) as? Double,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Double::class.java.classLoader) as? Double,
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(subtotalPrice)
		parcel.writeValue(quantity)
		parcel.writeValue(productId)
		parcel.writeValue(priceId)
		parcel.writeString(productEntity)
		parcel.writeValue(productPrice)
		parcel.writeString(productName)
		parcel.writeString(productUnit)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<BookingOrderHeader> {
		override fun createFromParcel(parcel: Parcel): BookingOrderHeader {
			return BookingOrderHeader(parcel)
		}

		override fun newArray(size: Int): Array<BookingOrderHeader?> {
			return arrayOfNulls(size)
		}
	}
}
