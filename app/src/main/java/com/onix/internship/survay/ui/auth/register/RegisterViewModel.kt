package com.onix.internship.survay.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.lifecycle.SingleLiveEvent

class RegisterViewModel : ViewModel(){
    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent
}