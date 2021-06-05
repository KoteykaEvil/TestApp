package com.onix.internship.survay.db.local.tables.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onix.internship.survay.arch.appflow.Roles

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "login")
    var login: String = "",
    @ColumnInfo(name = "password")
    var password: String = "",
    @ColumnInfo(name = "first_name")
    var name: String = "",
    @ColumnInfo(name = "last_name")
    var surname: String = "",
    @ColumnInfo(name = "role")
    var role: Int = -1
){
    fun getRoleState(): Roles {
        return when (role) {
            0 -> Roles.ADMIN
            1 -> Roles.MANAGER
            2 -> Roles.USER
            else -> Roles.DEFAULT
        }
    }
}