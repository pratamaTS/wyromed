package com.example.wyromed.Model.Body

import java.io.Serializable

data class SignatureBody(val doctorsignature: String,
                         val nursesignatur: String,
                         val salessignature: String): Serializable