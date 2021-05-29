package com.onix.internship.survay.ui.auth.login

import com.onix.internship.survay.arch.error.ErrorStates

data class LoginModel(var username: String = "", var password: String = "") {

    fun isLoginCorrect(): ErrorStates {
        return if (username.isNotEmpty()) ErrorStates.NONE else ErrorStates.EMPTY_FIELD
    }

    fun isPasswordCorrect(): ErrorStates {
        return if (password.isNotEmpty()) ErrorStates.NONE else ErrorStates.EMPTY_FIELD
    }

    fun isCorrect(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }

}