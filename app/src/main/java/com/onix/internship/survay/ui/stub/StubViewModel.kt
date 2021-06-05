package com.onix.internship.survay.ui.stub

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

class StubViewModel(val database: SurvayDatabase, private val sharedPrefs: SharedPrefs) :
    ViewModel() {
    private var model = User()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    init {
        getSavedData()
        if (model.login.isNotEmpty())
            login()
        else navToLoginFragment()
    }

    private fun navToLoginFragment() {
        _navigationEvent.postValue(StubFragmentDirections.actionStubFragmentToAuthFragment())
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
                Roles.ADMIN -> _navigationEvent.postValue(StubFragmentDirections.actionStubFragmentToAdminFragment())
                Roles.MANAGER -> _navigationEvent.postValue(StubFragmentDirections.actionStubFragmentToTestListFragment())
                Roles.USER -> _navigationEvent.postValue(StubFragmentDirections.actionStubFragmentToTestListFragment())
                else -> navToLoginFragment()
            }
        }
    }
}