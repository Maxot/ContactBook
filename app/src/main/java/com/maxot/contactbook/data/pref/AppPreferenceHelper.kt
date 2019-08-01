package com.maxot.contactbook.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.gson.Gson


class AppPreferenceHelper(val context: Context) {
    private val PREF_NAME = "preference"
    private val KEY_PROFILE = "profile"
    private val KEY_EMAIL = "email"

    val preference: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveProfile(profile: GoogleSignInAccount?) {
        val editor: SharedPreferences.Editor = preference.edit()
        val gson = Gson()
        val json = gson.toJson(profile)
        editor.putString(KEY_PROFILE, json)
        editor.apply()
    }

    fun getProfile(): GoogleSignInAccount {
        val gson = Gson()
        val json = preference.getString(KEY_PROFILE, null)
        val obj = gson.fromJson<GoogleSignInAccount>(json, GoogleSignInAccount::class.java)
        return obj
    }

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