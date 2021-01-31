package com.example.wyromed.Api.Interceptor

import android.content.Context
import com.example.wyromed.Api.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        requestBuilder.addHeader("Host", "absdigital.id")
        requestBuilder.addHeader("Accept-Encoding", "gzip, deflate, br")

        return chain.proceed(requestBuilder.build())
    }
}