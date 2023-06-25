package com.wahidhasyim.movieapp.data.local.prefs

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun saveToken(token: String) = sharedPreferences.edit().putString("token", token).apply()
    fun getToken() = sharedPreferences.getString("token", null)

}