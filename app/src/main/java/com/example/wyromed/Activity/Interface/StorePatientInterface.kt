package com.example.wyromed.Activity.Interface

interface StorePatientInterface {
    // Store Patient
    fun onSuccessStorePatient(tokenType: String?, token: String?, message: String?)
    fun onErrorStorePatient(msg:String?)
}