package com.onix.internship.survay.db.local.tables.questions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuestionsDao {

    @Insert
    fun insert(question: Question)

    @Update
    fun update(question: Question)

    @Query("SELECT * FROM questions WHERE test_id = :testId")
    fun getQuestionsByTest(testId: Int): List<Question>

}