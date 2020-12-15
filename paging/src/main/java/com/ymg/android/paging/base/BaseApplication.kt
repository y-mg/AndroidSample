package com.ymg.android.paging.base

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.ymg.android.paging.BuildConfig
import com.ymg.android.paging.di.apiModule
import com.ymg.android.paging.di.networkModule
import com.ymg.android.paging.di.viewModelModule
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin



class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * Logger
         */
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag("DEBUG")
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        /**
         * Koin
         */
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)

            modules(listOf(viewModelModule, networkModule, apiModule))
        }

        /**
         * RxError Handler
         */
        RxJavaPlugins.setErrorHandler {
            Logger.e("RxErrorHandler: $it")
        }
    }
}