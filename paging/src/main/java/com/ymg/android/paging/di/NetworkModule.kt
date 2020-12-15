package com.ymg.android.paging.di

import com.google.gson.GsonBuilder
import com.ymg.android.paging.BuildConfig
import com.ymg.android.paging.util.network.NetworkLogger
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

val networkModule = module {

    // HttpCache 크기 10MB 설정
    single {
        Cache(androidApplication().cacheDir, 10L * 1024 * 1024)
    }

    // Java Object 를 JSON 으로 또는, JSON 을 Java Object 로 변환을 도와주는 라이브러리
    single {
        GsonBuilder().create()
    }

    single(named(BuildConfig.SCOPE)) {
        val kakaoClient = OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 연결 실패 시 재시도
            addInterceptor(HttpLoggingInterceptor(NetworkLogger()).apply { // 네트워크 요청/응답을 로그에 표시하는 Interceptor 객체를 생성
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
        }.build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .addConverterFactory(GsonConverterFactory.create(get())) // 서버에서 JSON 형식으로 데이터를 보내고 이를 파싱해서 받아옴
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 받은 응답을 옵저버블 형태로 변환해 줍니다.
            .client(kakaoClient)
            .build()
    }
}