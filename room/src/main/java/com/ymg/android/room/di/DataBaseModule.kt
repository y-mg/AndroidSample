package com.ymg.android.room.di

import com.ymg.android.room.data.db.BookMarkDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module



val databaseModule = module {
    single {
        BookMarkDB.getInstance(androidApplication())
    }

    single(createdAtStart = false) {
        get<BookMarkDB>().bookMarkDao()
    }
}