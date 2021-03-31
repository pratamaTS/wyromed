package com.example.wyromed.Api

import android.content.Context
import com.example.wyromed.Data.Model.BaseMeta
import com.example.wyromed.Data.Model.ResponseError
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

object ErrorUtils {
    fun parseError(response: retrofit2.Response<*>, context: Context): ResponseError? {
        val converter: Converter<ResponseBody, ResponseError> = NetworkConfig.getRetrofit(context)
            .responseBodyConverter(BaseMeta::class.java, arrayOfNulls<Annotation>(0))
        val error: ResponseError?
        error = try {
            converter.convert(response.errorBody())
        } catch (e: IOException) {
            return ResponseError()
        }
        return error
    }
}