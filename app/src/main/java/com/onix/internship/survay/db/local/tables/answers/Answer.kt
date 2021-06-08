package com.onix.internship.survay.db.local.tables.answers

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answers")
data class Answer(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "text")
    var text: String = "",
    @ColumnInfo(name = "score")
    var score: Float = 0f,
    @ColumnInfo(name = "question_id")
    var questionId: Int = 0
)