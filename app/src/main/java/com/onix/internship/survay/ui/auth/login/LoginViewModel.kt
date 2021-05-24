package com.onix.internship.survay.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.lifecycle.SingleLiveEvent

class LoginViewModel: ViewModel() {
    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent


}