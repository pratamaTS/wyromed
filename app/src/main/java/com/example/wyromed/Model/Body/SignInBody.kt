package com.example.wyromed.Model.Body

import java.io.Serializable

data class SignInBody(var email: String? = null,
                      var password: String? = null): Serializable