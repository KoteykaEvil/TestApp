package com.onix.internship.survay.ui.tests

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.lifecycle.SingleLiveEvent
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs
import com.onix.internship.survay.ui.auth.AuthFragmentDirections
import kotlin.system.exitProcess

class TestListViewModel(val database: SurvayDatabase, private val sharedPrefs: SharedPrefs) :
    ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    fun onLogOutClick() {
        sharedPrefs.saveToSharedPrefs("","")
        _navigationEvent.value = TestListFragmentDirections.actionTestListFragmentPop2()
    }

}