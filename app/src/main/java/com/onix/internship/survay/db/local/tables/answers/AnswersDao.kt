package com.onix.internship.survay.db.local.tables.answers

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnswersDao {

    @Insert
    fun insert(answer: Answer)

    @Update
    fun update(answer: Answer)

    @Query("SELECT * FROM answers WHERE question_id = :questionId")
    fun getAnswersByQuestion(questionId: Int): List<Answer>

}