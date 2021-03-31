package com.example.wyromed.Data.Connection

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.wyromed.R

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    /**
     * Function to save auth token
     */
    fun deleteAuthToken() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }


    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun isLoggedIn() = !prefs.getString(USER_TOKEN, null).isNullOrBlank()

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
        Log.d("token login get shared", USER_TOKEN)
    }
}