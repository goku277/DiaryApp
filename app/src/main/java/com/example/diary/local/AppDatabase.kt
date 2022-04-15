package com.example.diary.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diary.dao.DataEntryDao
import com.example.diary.models.DataEntry

@Database(entities = [DataEntry::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataEntryDao(): DataEntryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var appDatabaseInstance: AppDatabase? = null
        private const val DB_NAME = "diary_db"

        fun getDatabase(
            context: Context
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return appDatabaseInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                appDatabaseInstance = instance
                instance
            }
        }

        fun closeDB() {
            appDatabaseInstance?.close()
            appDatabaseInstance = null
        }
    }
}