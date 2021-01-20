package com.example.wyromed.Model.Header

data class Header (
    val Authorization: String,
    val ContentLength: String,
    val ContentType: String,
    val Host: String
)