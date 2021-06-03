package com.onix.internship.survay.db.local.tables.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.onix.internship.survay.arch.appflow.Roles

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var userId: Long = 0L,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "surname")
    var surname: String = "",
    @ColumnInfo(name = "login")
    var username: String = "",
    @ColumnInfo(name = "password")
    var passwordHash: String = "",
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