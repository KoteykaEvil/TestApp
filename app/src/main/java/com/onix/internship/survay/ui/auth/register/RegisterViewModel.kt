package com.onix.internship.survay.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.error.ErrorStates
import com.onix.internship.survay.arch.lifecycle.SingleLiveEvent

class RegisterViewModel : ViewModel() {
    val model = RegisterModel()

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent


    private val _nameError = MutableLiveData(ErrorStates.NONE)
    val nameError: LiveData<ErrorStates> = _nameError

    private val _surnameError = MutableLiveData(ErrorStates.NONE)
    val surnameError: LiveData<ErrorStates> = _surnameError

    private val _usernameError = MutableLiveData(ErrorStates.NONE)
    val usernameError: LiveData<ErrorStates> = _usernameError

    private val _passwordError = MutableLiveData(ErrorStates.NONE)
    val passwordError: LiveData<ErrorStates> = _passwordError

    private val _passwordConfirmError = MutableLiveData(ErrorStates.NONE)
    val passwordConfirmError: LiveData<ErrorStates> = _passwordConfirmError

    fun onClickConfirm() {

        model.apply {
            _nameError.value = isNameCorrect()
            _surnameError.value = isSurnameCorrect()
            _usernameError.value = isUsernameCorrect()
            _passwordError.value = isPasswordCorrect()
            _passwordConfirmError.value = isPasswordConfirmCorrect()
            if (isCorrect()) {
                registerUser()
            }
        }
    }

    private fun registerUser() {

    }
}