package com.ymg.android.room.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ymg.android.room.data.db.converter.DateConverter
import com.ymg.android.room.data.network.response.BookModel
import java.io.Serializable
import java.util.*



@Entity(tableName = "book")
@TypeConverters(DateConverter::class)
data class BookMark(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "dateTime")
    val dateTime: String,

    @ColumnInfo(name = "created")
    val created: Date
): Serializable {
    companion object {
        fun createBookMark(document: BookModel.Document): BookMark {
            return BookMark(
                thumbnail = document.thumbnail,
                title = document.title,
                price = document.sale_price,
                dateTime = document.datetime,
                created = Date()
            )
        }
    }
}