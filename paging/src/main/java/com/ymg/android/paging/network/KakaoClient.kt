package com.ymg.android.paging.network

import com.ymg.android.paging.BuildConfig
import com.ymg.android.paging.network.api.BookApi
import com.ymg.android.paging.network.response.BookModel
import io.reactivex.Observable



class KakaoClient (private val bookApi: BookApi) {

    fun fetchBookInfo(parameter: MutableMap<String, Any>): Observable<BookModel> {
        return bookApi.fetchBookInfo("KakaoAK ${BuildConfig.API_KEY}", parameter)
    }
}