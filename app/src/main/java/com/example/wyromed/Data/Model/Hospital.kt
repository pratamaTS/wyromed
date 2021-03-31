package com.example.wyromed.Data.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Hospital(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("format_number")
	val formatNumber: String? = null,

	@field:SerializedName("coa_name")
	val coaName: Any? = null,

	@field:SerializedName("contract_start_date")
	val contractStartDate: String? = null,

	@field:SerializedName("contract_end_date")
	val contractEndDate: String? = null,

	@field:SerializedName("number")
	val number: Any? = null,

	@field:SerializedName("pic_email")
	val picEmail: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("state")
	val state: Any? = null,

	@field:SerializedName("coa_id")
	val coaId: Any? = null,

	@field:SerializedName("isdel")
	val isdel: String? = null,

	@field:SerializedName("tax_journal")
	val taxJournal: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("pic_name")
	val picName: Any? = null,

	@field:SerializedName("pic_phone")
	val picPhone: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("increment_number")
	val incrementNumber: String? = null,

	@field:SerializedName("postal_code")
	val postalCode: String? = null,

	@field:SerializedName("account_piutang")
	val accountPiutang: String? = null
): BaseModel(), Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Any::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Any::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Any::class.java.classLoader),
		parcel.readValue(Any::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Any::class.java.classLoader),
		parcel.readValue(Any::class.java.classLoader),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(country)
		parcel.writeString(city)
		parcel.writeString(formatNumber)
		parcel.writeValue(coaName)
		parcel.writeString(contractStartDate)
		parcel.writeString(contractEndDate)
		parcel.writeValue(number)
		parcel.writeString(picEmail)
		parcel.writeString(id)
		parcel.writeValue(state)
		parcel.writeValue(coaId)
		parcel.writeString(isdel)
		parcel.writeString(taxJournal)
		parcel.writeString(address)
		parcel.writeValue(picName)
		parcel.writeValue(picPhone)
		parcel.writeString(name)
		parcel.writeString(incrementNumber)
		parcel.writeString(accountPiutang)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Hospital> {
		override fun createFromParcel(parcel: Parcel): Hospital {
			return Hospital(parcel)
		}

		override fun newArray(size: Int): Array<Hospital?> {
			return arrayOfNulls(size)
		}
	}
}
