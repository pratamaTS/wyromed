package com.example.wyromed.Model.Body

import java.io.Serializable

data class ProfileBody(val fullname: String,
                       val email: String,
                       val profilepict: String,
                       val phone: String): Serializable