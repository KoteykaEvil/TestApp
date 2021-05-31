package com.onix.internship.survay.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.error.ErrorStates
import com.onix.internship.survay.arch.lifecycle.SingleLiveEvent
import java.math.BigInteger
import java.security.MessageDigest

class LoginViewModel : ViewModel() {
    val model = LoginModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _loginError = MutableLiveData(ErrorStates.NONE)
    val loginError: LiveData<ErrorStates> = _loginError

    private val _passwordError = MutableLiveData(ErrorStates.NONE)
    val passwordError: LiveData<ErrorStates> = _passwordError


    fun onClickConnect() {
        model.apply {
            _loginError.value = isLoginCorrect()
            _passwordError.value = isPasswordCorrect()
            if(isCorrect()){
                login()
            }
        }
    }

    private fun login() {
        // next validation || send to server
    }

    private fun md5(password: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(password.toByteArray())).toString(16).padStart(32, '0')
    }
}