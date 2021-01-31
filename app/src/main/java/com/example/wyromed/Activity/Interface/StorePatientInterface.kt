package com.example.wyromed.Activity.Interface

interface StorePatientInterface {
    // Store Patient
    fun onSuccessStorePatient(message: String?)
    fun onErrorStorePatient(msg:String?)
}