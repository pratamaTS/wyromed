package com.example.wyromed.Api

import android.content.Context
import com.example.wyromed.Api.Interceptor.AuthInterceptor
import com.example.wyromed.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConfig {
    //untuk melakukan logging untuk melihat logcat
    private fun getInterceptor(context: Context): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return  OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(context))
            .addInterceptor(interceptor)
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