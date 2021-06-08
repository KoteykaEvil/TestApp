package com.onix.internship.survay.db.login

import com.onix.internship.survay.arch.appflow.Roles
import com.onix.internship.survay.db.local.SurvayDatabase
import com.onix.internship.survay.db.local.tables.users.User
import com.onix.internship.survay.db.security.md5

class Login() {
    suspend fun login(model: User, database: SurvayDatabase): Roles {
        model.apply {
            val userList = database.userDao.get(login, md5(password))
            userList.apply {
                return try {
                    first().getRoleState()
                } catch (e: NoSuchElementException) {
                    Roles.DEFAULT
                }
            }
        }
    }
}