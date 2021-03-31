package com.example.wyromed.Api

import android.content.Context
import com.example.wyromed.Api.Interceptor.AuthInterceptor
import com.example.wyromed.BuildConfig
import com.example.wyromed.Data.Connection.Interceptor
import com.example.wyromed.Data.Connection.SessionManager
import com.example.wyromed.Data.Connection.WyromedService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConfig {
    //untuk melakukan logging untuk melihat logcat
    private fun getInterceptor(context: Context): OkHttpClient {
        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return  OkHttpClient().newBuilder()
            .addInterceptor(Interceptor(SessionManager(context)))
            .addInterceptor(logginInterceptor)
            .build()
    }

    //mengirim dan menerima response dari server
    fun getRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(getInterceptor(context))
            .addConverterFactory(GsonConverterFactory.create()) //convert data Json yang diterima dari server menjadi objek
            .build()
    }

    fun service(context: Context) = getRetrofit(context).create(WyromedService::class.java)
}