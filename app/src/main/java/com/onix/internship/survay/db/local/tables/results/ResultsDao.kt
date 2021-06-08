package com.onix.internship.survay.db.local.tables.results

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ResultsDao {

    @Insert
    fun insert(result: Result)

    @Update
    fun update(result: Result)

    @Query("SELECT * FROM results WHERE user_id = :userId AND test_id = :testId")
    fun getResultByUserId(userId: Int, testId: Int): List<Result>

}