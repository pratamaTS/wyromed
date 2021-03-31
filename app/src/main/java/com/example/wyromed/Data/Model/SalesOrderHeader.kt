package com.example.wyromed.Data.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SalesOrderHeader(

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("total_price")
	val totalPrice: Double? = null,

	@field:SerializedName("booking_date")
	val bookingDate: String? = null,

	@field:SerializedName("end_time")
	val endTime: String? = null,

	@field:SerializedName("hospital_id")
	var hospitalId: Int? = null,

	@field:SerializedName("patient_number")
	val patientNumber: String? = null,

	@field:SerializedName("booking_id")
	val bookingId: Int? = null,

	@field:SerializedName("start_time")
	var startTime: String? = null,

	@field:SerializedName("booking_number")
	var bookingNumber: String? = null,

	@field:SerializedName("payment_type")
	val paymentType: String? = null,

	@field:SerializedName("patient_type")
	val patientType: Int? = null,

	@field:SerializedName("patient_id")
	val patientId: Int? = null,

	@field:SerializedName("so_due_date")
	val soDueDate: String? = null,

	@field:SerializedName("patient_name")
	val patientName: String? = null,

	@field:SerializedName("hospital_name")
	var hospitalName: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Double::class.java.classLoader) as? Double,
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(note)
		parcel.writeValue(totalPrice)
		parcel.writeString(bookingDate)
		parcel.writeString(endTime)
		parcel.writeValue(hospitalId)
		parcel.writeString(patientNumber)
		parcel.writeValue(bookingId)
		parcel.writeString(startTime)
		parcel.writeString(bookingNumber)
		parcel.writeString(paymentType)
		parcel.writeValue(patientType)
		parcel.writeValue(patientId)
		parcel.writeString(soDueDate)
		parcel.writeString(patientName)
		parcel.writeString(hospitalName)
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
