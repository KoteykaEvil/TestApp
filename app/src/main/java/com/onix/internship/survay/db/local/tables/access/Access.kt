package com.onix.internship.survay.db.local.tables.access

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access")
data class Access(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "user_id")
    var userId: Int = 0,
    @ColumnInfo(name = "test_id")
    var testId: Int = 0
)