package com.ymg.android.mvvm.di

import com.ymg.android.mvvm.BuildConfig
import com.ymg.android.mvvm.network.GithubClient
import com.ymg.android.mvvm.network.api.SearchApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit



val apiModule = module {

    single {
        get<Retrofit>(named(BuildConfig.SCOPE)).create(SearchApi::class.java)
    }

    single {
        GithubClient(get())
    }
}