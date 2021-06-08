package com.onix.internship.survay.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.appflow.Roles
import com.onix.internship.survay.arch.lifecycle.SingleLiveEvent
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.local.tables.users.User
import com.onix.internship.survay.db.login.Login
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(val database: SurvayDatabase, private val sharedPrefs: SharedPrefs) :
    ViewModel() {
    private var model = User()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    init {
        tryToLogin()
    }

    fun tryToLogin(){
        getSavedData()
        if (model.login.isNotEmpty())
            login()
        else navToLoginFragment()
    }

    private fun navToLoginFragment() {
        _navigationEvent.postValue(SplashFragmentDirections.actionSplashFragmentToAuthFragment())
    }

    private fun getSavedData() {
        model = sharedPrefs.getFromSharedPrefs()
    }

    fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val savedUser = sharedPrefs.getFromSharedPrefs()
            val res = Login().login(
                savedUser, database
            )

            when (res) {
                Roles.ADMIN -> _navigationEvent.postValue(SplashFragmentDirections.actionSplashFragmentToAdminFragment())
                Roles.MANAGER -> _navigationEvent.postValue(SplashFragmentDirections.actionSplashFragmentToTestListFragment())
                Roles.USER -> _navigationEvent.postValue(SplashFragmentDirections.actionSplashFragmentToTestListFragment())
                else -> navToLoginFragment()
            }
        }
    }
}