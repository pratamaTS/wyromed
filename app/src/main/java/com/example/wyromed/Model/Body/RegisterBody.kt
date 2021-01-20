package com.example.wyromed.Model.Body

import java.io.Serializable

data class RegisterBody(var email: String? = null,
                        var fullname: String? = null,
                        var phone: String? = null,
                        var password: String? = null,
                        var repassword: String? = null): Serializable