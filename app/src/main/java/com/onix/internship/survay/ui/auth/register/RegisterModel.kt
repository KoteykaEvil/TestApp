package com.onix.internship.survay.ui.auth.register

import com.onix.internship.survay.arch.error.ErrorStates
import com.onix.internship.survay.db.local.tables.users.User
import com.onix.internship.survay.db.security.md5

data class RegisterModel(
    var name: String = "",
    var surname: String = "",
    var username: String = "",
    var password: String = "",
    var passwordConfirm: String = ""
) {
    fun isNameCorrect(): ErrorStates {
        return if (name.isNotEmpty()) {
            ErrorStates.NONE
        } else {
            ErrorStates.EMPTY_FIELD
        }
    }

    fun isSurnameCorrect(): ErrorStates {
        return if (surname.isNotEmpty()) {
            ErrorStates.NONE
        } else {
            ErrorStates.EMPTY_FIELD
        }
    }

    fun isUsernameCorrect(): ErrorStates {
        return if (username.isNotEmpty()) {
            ErrorStates.NONE
        } else {
            ErrorStates.EMPTY_FIELD
        }
    }

    fun isPasswordCorrect(): ErrorStates {
        return if (password.isNotEmpty()) {
            ErrorStates.NONE
        } else {
            ErrorStates.EMPTY_FIELD
        }
    }

    fun isPasswordConfirmCorrect(): ErrorStates {
        return when {
            passwordConfirm.isEmpty() -> ErrorStates.EMPTY_FIELD
            passwordConfirm == password -> ErrorStates.NONE
            else -> ErrorStates.UNCONFIRMED_PASSWORD
        }
    }

    fun isCorrect(): Boolean {
        return name.isNotEmpty() &&
                surname.isNotEmpty() &&
                username.isNotEmpty() &&
                password.isNotEmpty() &&
                password == passwordConfirm
    }

    fun toUser(): User {
        return User(
            name = name,
            surname = surname,
            login = username,
            password = md5(password)
        )
    }


}