package com.ymg.android.room.di

import com.ymg.android.room.BuildConfig
import com.ymg.android.room.data.network.KakaoClient
import com.ymg.android.room.data.network.api.BookApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit



val apiModule = module {

    single {
        get<Retrofit>(named(BuildConfig.SCOPE)).create(BookApi::class.java)
    }

    single {
        KakaoClient(get())
    }
}