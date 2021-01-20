package com.example.wyromed.Model.Body

import android.os.Parcel
import android.os.Parcelable
import com.example.wyromed.Model.StockRequestItem
import com.google.gson.annotations.SerializedName

data class StockRequestDetails(

	@field:SerializedName("product_id")
	val productId: Int,

	@field:SerializedName("quantity")
	var quantity: Int,

	@field:SerializedName("product_name")
	val productName: String?,

	@field:SerializedName("product_unit")
	val productUnit: String?,

	@field:SerializedName("product_entity")
	val productEntity: String?

) : Parcelable {

	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readInt(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

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

	companion object CREATOR : Parcelable.Creator<StockRequestDetails> {
		override fun createFromParcel(parcel: Parcel): StockRequestDetails {
			return StockRequestDetails(parcel)
		}

		override fun newArray(size: Int): Array<StockRequestDetails?> {
			return arrayOfNulls(size)
		}

	}
}
