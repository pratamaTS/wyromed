package com.example.wyromed.Data.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SalesOrderHeader(

	@field:SerializedName("note")
    var note: String? = null,

	@field:SerializedName("total_price")
	var totalPrice: Double? = null,

	@field:SerializedName("booking_date")
	var bookingDate: String? = null,

	@field:SerializedName("duration")
	var duration: String? = null,

	@field:SerializedName("end_time")
    var endTime: String? = null,

	@field:SerializedName("hospital_id")
	var hospitalId: Int? = null,

	@field:SerializedName("patient_number")
	var patientNumber: String? = null,

	@field:SerializedName("booking_id")
	var bookingId: Int? = null,

	@field:SerializedName("start_time")
	var startTime: String? = null,

	@field:SerializedName("booking_number")
	var bookingNumber: String? = null,

	@field:SerializedName("payment_type")
    var paymentType: String? = null,

	@field:SerializedName("patient_type")
    var patientType: Int? = null,

	@field:SerializedName("patient_id")
	var patientId: Int? = null,

	@field:SerializedName("so_due_date")
	var soDueDate: String? = null,

	@field:SerializedName("patient_name")
	var patientName: String? = null,

	@field:SerializedName("hospital_name")
	var hospitalName: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readValue(Double::class.java.classLoader) as? Double,
		parcel.readString(),
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
		parcel.writeString(duration)
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

	companion object CREATOR : Parcelable.Creator<SalesOrderHeader> {
		override fun createFromParcel(parcel: Parcel): SalesOrderHeader {
			return SalesOrderHeader(parcel)
		}

		override fun newArray(size: Int): Array<SalesOrderHeader?> {
			return arrayOfNulls(size)
		}
	}
}
