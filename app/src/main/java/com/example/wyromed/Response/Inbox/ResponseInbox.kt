package com.example.wyromed.Response.Inbox

import com.google.gson.annotations.SerializedName

open class ResponseInbox {

	@field:SerializedName("data")
	val data: ArrayList<DataInbox?> = ArrayList()

	@field:SerializedName("meta")
	val meta: MetaInbox? = null
}
