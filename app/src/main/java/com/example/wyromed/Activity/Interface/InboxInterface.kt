package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Inbox.DataInbox

interface InboxInterface {
    fun onSuccessGetInbox(dataInbox: ArrayList<DataInbox?>?)
    fun onErrorGetInbox(msg:String?)
}