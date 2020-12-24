package com.ymg.android.room.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
                        //.addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }



        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create New Table
                database.execSQL("""
                    CREATE TABLE newBook(
                        id INTEGER PRIMARY KEY NOT NULL,
                        thumbnail TEXT NOT NULL,
                        title TEXT NOT NULL,
                        price TEXT NOT NULL,
                        dateTime TEXT NOT NULL,
                        created INTEGER NOT NULL
                    )
                    """.trimIndent())

                // Insert Data
                database.execSQL("""
                    INSERT INTO newBook(
                        id,
                        thumbnail,
                        title,
                        price,
                        dateTime,
                        created
                    )
                    SELECT * FROM book
                    """.trimIndent())

                // Delete Old Table
                database.execSQL("DROP TABLE book")

                // Change Name NewTable
                database.execSQL("ALTER TABLE newBook RENAME TO book")
            }
        }
    }
}