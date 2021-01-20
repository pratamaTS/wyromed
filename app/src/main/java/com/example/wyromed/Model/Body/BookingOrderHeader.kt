package com.example.wyromed.Model.Body

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.io.Serializable

data class BookingOrderHeader(
	var hospital_id: Int? = 0,
	var patient_id: Int? = 0,
	var start_date: String? = null,
	var end_date: String? = null,
	var bpjs: Boolean? = false,
	var hospital_name: String? = null,
	var patient_name: String? = null,
	var patient_number: String? = null,
	var total_quantity: Int? = 0,
	var note: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeValue(hospital_id)
		parcel.writeValue(patient_id)
		parcel.writeString(start_date)
		parcel.writeString(end_date)
		parcel.writeValue(bpjs)
		parcel.writeString(hospital_name)
		parcel.writeString(patient_name)
		parcel.writeString(patient_number)
		parcel.writeValue(total_quantity)
		parcel.writeString(note)
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

