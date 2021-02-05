package com.example.wyromed.Model.Body

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class SalesOrderHeader(
    var note: String? = null,
    var totalPrice: Double? = null,
    var bookingDate: String? = null,
    var endTime: String? = null,
    var hospitalId: Int? = 0,
    var patientNumber: String? = null,
    var bookingId: Int? = 0,
    var startTime: String? = null,
    var bookingNumber: String? = null,
    var patientId: Int? = 0,
    var patientName: String? = null,
    var bpjs: Boolean? = false,
    var hospitalName: String? = null
) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readBoolean(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(note)
        parcel.writeValue(totalPrice)
        parcel.writeString(bookingDate)
        parcel.writeString(endTime)
        parcel.writeValue(bpjs)
        parcel.writeValue(hospitalId)
        parcel.writeString(patientNumber)
        parcel.writeValue(bookingId)
        parcel.writeString(startTime)
        parcel.writeString(bookingNumber)
        parcel.writeValue(patientId)
        parcel.writeString(patientName)
        parcel.writeValue(bpjs)
        parcel.writeString(hospitalName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SalesOrderHeader> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): SalesOrderHeader {
            return SalesOrderHeader(parcel)
        }

        override fun newArray(size: Int): Array<SalesOrderHeader?> {
            return arrayOfNulls(size)
        }
    }
}