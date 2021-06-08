package com.onix.internship.survay.db.local.tables.results

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "results")
data class Result(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "date")
    var date: String = "",
    @ColumnInfo(name = "score")
    var score: Float = 0f,
    @ColumnInfo(name = "user_id")
    var userId: Int = 0,
    @ColumnInfo(name = "test_id")
    var testId: Int = 0
)