package com.example.wyromed.Data.Connection

import android.content.Context
import com.example.wyromed.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServices {
    companion object {
        @Volatile private var apiService: WyromedService? = null

        private val gson: Gson by lazy {
            GsonBuilder()
                .create()
        }

        private fun httpClient(context: Context): OkHttpClient {
            val logginInterceptor = HttpLoggingInterceptor()
            logginInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient().newBuilder()
                .addInterceptor(Interceptor(SessionManager(context)))
                .addInterceptor(logginInterceptor)
                .build()
        }

        fun retrofit(context: Context): Retrofit {
            val adapter = RxJava3CallAdapterFactory.create()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(httpClient(context))
                .addCallAdapterFactory(adapter)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        fun apiService(context: Context): WyromedService {
            if(apiService == null) {
                apiService = retrofit(context).create(WyromedService::class.java)
            }

            return apiService!!
        }
    }
}