package com.example.tycoon_client.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tycoon_client.data.model.Region

@Database(entities = [Region::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun regionDao(): RegionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "tycoon_client_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}