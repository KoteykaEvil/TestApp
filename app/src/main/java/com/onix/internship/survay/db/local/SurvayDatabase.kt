package com.onix.internship.survay.db.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onix.internship.survay.db.local.tables.users.User
import com.onix.internship.survay.db.local.tables.users.UsersDao


@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class SurvayDatabase : RoomDatabase() {

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
                        "survay_database_1"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}