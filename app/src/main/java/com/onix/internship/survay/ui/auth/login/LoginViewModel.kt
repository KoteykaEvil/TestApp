package com.onix.internship.survay.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.onix.internship.survay.arch.appflow.Roles
import com.onix.internship.survay.arch.error.ErrorStates
import com.onix.internship.survay.arch.lifecycle.SingleLiveEvent
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.local.tables.users.User
import com.onix.internship.survay.db.login.Login
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs
import com.onix.internship.survay.ui.auth.AuthFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val database: SurvayDatabase, private val sharedPrefs: SharedPrefs) :
    ViewModel() {
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
            if (isCorrect()) {
                login()
            }
        }
    }


    private fun login() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = Login().login(
                User(
                    login = model.login,
                    password = model.password
                ), database
            )
            if(res.index >= 0) sharedPrefs.saveToSharedPrefs(model.login,model.password)
            when (res) {
                Roles.ADMIN -> _navigationEvent.postValue(AuthFragmentDirections.actionAuthFragmentToAdminFragment())
                Roles.MANAGER -> _navigationEvent.postValue(AuthFragmentDirections.actionAuthFragmentToTestListFragment())
                Roles.USER -> _navigationEvent.postValue(AuthFragmentDirections.actionAuthFragmentToTestListFragment())
                else -> incorrectLoginOrPassword() // problem with registration or incorrect data
            }
        }
    }

    private fun incorrectLoginOrPassword() {
        _loginError.postValue(ErrorStates.INCORRECT_DATA)
        _passwordError.postValue(ErrorStates.INCORRECT_DATA)
    }

}