package com.onix.internship.survay.db.local.tables.questions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "text")
    var text: String = "",
    @ColumnInfo(name = "type")
    var type: Int = 0,
    @ColumnInfo(name = "test_id")
    var testId: Int = 0
)