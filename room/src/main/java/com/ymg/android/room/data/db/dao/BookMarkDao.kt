package com.ymg.android.room.data.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.ymg.android.room.data.db.entity.BookMark



@Dao
interface BookMarkDao {

    @Query("SELECT * FROM bookmark ORDER BY created ASC")
    fun findAll(): DataSource.Factory<Int, BookMark>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookMark: BookMark)


    @Delete
    fun delete(bookMark: BookMark)
}