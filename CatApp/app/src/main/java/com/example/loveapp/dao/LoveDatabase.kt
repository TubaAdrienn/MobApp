package com.example.loveapp.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [LoveResultSave::class], version = 4, exportSchema = false)
abstract class LoveDatabase : RoomDatabase() {

    abstract val loveDao: LoveDao

    companion object {

        @Volatile
        private var INSTANCE: LoveDatabase? = null

        fun getInstance(context: Context): LoveDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LoveDatabase::class.java,
                        "love_database"
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