package com.onix.internship.survay.ui.auth.register

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
import com.onix.internship.survay.db.sharedpreferences.SharedPrefs
import com.onix.internship.survay.ui.auth.AuthFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val database: SurvayDatabase, private val sharedPrefs: SharedPrefs) : ViewModel() {
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
        viewModelScope.launch(Dispatchers.IO) {
            model.apply {
                if (database.userDao.getUsers(username).isNotEmpty()) usernameIsAlreadyExist()
                else {
                    val user : User = toUser()
                    user.role = if (database.userDao.getAllUsers().isEmpty()) Roles.ADMIN.index else Roles.USER.index
                    sharedPrefs.saveToSharedPrefs(user.username,user.passwordHash)
                    database.userDao.insert(user)
                    _navigationEvent.postValue(AuthFragmentDirections.actionAuthFragmentToTestListFragment())
                }
            }
        }
    }

    private fun usernameIsAlreadyExist() {
        _usernameError.postValue(ErrorStates.USER_ALREADY_REGISTERED)
    }
}