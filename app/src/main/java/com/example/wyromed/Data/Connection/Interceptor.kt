package com.example.wyromed.Data.Connection

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor(private val sessionManager: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Accept-Encoding", "gzip, deflate, br")
        requestBuilder.addHeader("Content-Type", "application/json")
        requestBuilder.addHeader("Host", "absdigital.id")

        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}