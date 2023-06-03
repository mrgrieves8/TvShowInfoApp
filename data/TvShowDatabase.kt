package com.example.tvshowsinfo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tvshowsinfo.domain.TvShow

@Database(entities = [TvShow::class], version = 1, exportSchema = false)
abstract class TvShowDatabase : RoomDatabase() {

    abstract fun tvShowDao(): TvShowDao

    companion object {
        @Volatile
        private var INSTANCE: TvShowDatabase? = null

        fun getDatabase(context: Context): TvShowDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TvShowDatabase::class.java,
                    "tv_show_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
