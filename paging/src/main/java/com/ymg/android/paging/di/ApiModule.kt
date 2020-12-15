package com.ymg.android.paging.di

import com.ymg.android.paging.BuildConfig
import com.ymg.android.paging.network.KakaoClient
import com.ymg.android.paging.network.api.BookApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit



val apiModule = module {

    /**
     * Kakao Client
     *
     *  - 싱글톤
     *  특정 클래스에 대한 인스턴스를 단 한 번만 Static 메모리 영역에 할당하고
     *  해당 클래스에 대한 생성자를 여러 번 호출하더라도 최초에 생성된 객체를 반환하는 디자인 패턴
     */
    single {
        get<Retrofit>(named(BuildConfig.SCOPE)).create(BookApi::class.java)
    }

    single {
        KakaoClient(get())
    }
}