package com.maxot.contactbook.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.gson.Gson


class AppPreferenceHelper(val context: Context) {
    private val PREF_NAME = "preference"
    private val KEY_EMAIL = "email"

    val preference: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveEmail(email: String?) {
        val editor: SharedPreferences.Editor = preference.edit()
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    fun getEmail(): String? {
        return preference.getString(KEY_EMAIL, null)
    }

    fun clearAll(){
        preference.edit().clear().apply()
    }

}