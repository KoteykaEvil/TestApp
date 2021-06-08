package com.onix.internship.survay.ui.tests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.lifecycle.SingleLiveEvent
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.local.tables.tests.Test
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestListViewModel(val database: SurvayDatabase, private val sharedPrefs: SharedPrefs) :
    ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _dbData = MutableLiveData<List<Test>>()
    val dbData: LiveData<List<Test>> = _dbData

    fun onLogOutClick() {
        sharedPrefs.saveToSharedPrefs("", "")
        _navigationEvent.value = TestListFragmentDirections.actionTestListFragmentPop2()
    }

    fun getDbData(){
        viewModelScope.launch(Dispatchers.IO) {
            _dbData.postValue(database.testDao.getAllTests())
        }
        //_dbData.value = listOf("hi","hello","meow","hello","meow","hello","meow","hello","meow","hello","meow","hello","meow","hello","meow")
    }

}