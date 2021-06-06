package com.onix.internship.survay.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs

class SplashViewModelFactory(
    private val database: SurvayDatabase,
    private val sharedPrefs: SharedPrefs
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(database, sharedPrefs) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
