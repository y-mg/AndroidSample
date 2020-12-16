package com.ymg.android.room.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ymg.android.room.data.db.BookMarkDB.Companion.DB_VERSION
import com.ymg.android.room.data.db.dao.BookMarkDao
import com.ymg.android.room.data.db.entity.BookMark



@Database(entities = [BookMark::class], version = DB_VERSION, exportSchema = false)
abstract class BookMarkDB: RoomDatabase() {

    abstract fun bookMarkDao(): BookMarkDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "bookMark.db"

        private var INSTANCE: BookMarkDB? = null

        fun getInstance(context: Context): BookMarkDB? {
            if (INSTANCE == null) {
                synchronized(BookMarkDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookMarkDB::class.java,
                        DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}