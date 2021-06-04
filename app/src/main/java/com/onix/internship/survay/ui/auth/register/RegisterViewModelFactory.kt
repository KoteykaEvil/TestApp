package com.onix.internship.survay.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs

class RegisterViewModelFactory(
    private val database: SurvayDatabase,
    private val sharedPrefs: SharedPrefs
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(database, sharedPrefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}