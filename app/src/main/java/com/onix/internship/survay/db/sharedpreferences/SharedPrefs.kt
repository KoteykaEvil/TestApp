package com.onix.internship.survay.db.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.onix.internship.survay.db.local.tables.users.User

class SharedPrefs(context: Context) {

    private val key = "KEY_SHARED_PREFS"

    private val keyLogin = "login"
    private val keyPassword = "password"


    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(key, Context.MODE_PRIVATE)

    fun saveToSharedPrefs(login: String, passwordHash: String) {
        with(sharedPref.edit()) {
            putString(keyLogin, login)
            putString(keyPassword, passwordHash)
            apply()
        }
    }

    fun getFromSharedPrefs(): User {
        val name = sharedPref.getString(keyLogin, "") ?: ""
        val title = sharedPref.getString(keyPassword, "") ?: ""
        return User(
            login = name,
            password = title,
        )
    }


}