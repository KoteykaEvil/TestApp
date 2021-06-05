package com.onix.internship.survay.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onix.internship.survay.db.local.tables.access.Access
import com.onix.internship.survay.db.local.tables.access.AccessDao
import com.onix.internship.survay.db.local.tables.answers.Answer
import com.onix.internship.survay.db.local.tables.answers.AnswersDao
import com.onix.internship.survay.db.local.tables.questions.Question
import com.onix.internship.survay.db.local.tables.questions.QuestionsDao
import com.onix.internship.survay.db.local.tables.results.Result
import com.onix.internship.survay.db.local.tables.results.ResultsDao
import com.onix.internship.survay.db.local.tables.tests.Test
import com.onix.internship.survay.db.local.tables.tests.TestsDao
import com.onix.internship.survay.db.local.tables.users.User
import com.onix.internship.survay.db.local.tables.users.UsersDao


@Database(
    entities = [Answer::class, Question::class, Result::class, Test::class, User::class],//Access::class,
    version = 1,
    exportSchema = false
)
abstract class SurvayDatabase : RoomDatabase() {

//    abstract val accessDao: AccessDao
    abstract val answersDao: AnswersDao
    abstract val questionDao: QuestionsDao
    abstract val resultDao: ResultsDao
    abstract val testDao: TestsDao
    abstract val userDao: UsersDao

    companion object {

        @Volatile
        private var INSTANCE: SurvayDatabase? = null

        fun getInstance(context: Context): SurvayDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SurvayDatabase::class.java,
                        "survay_database"
                    )
                        .createFromAsset("database/survay.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}