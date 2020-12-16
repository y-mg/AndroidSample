package com.ymg.android.room.data.network

import com.ymg.android.room.BuildConfig
import com.ymg.android.room.data.network.api.BookApi
import com.ymg.android.room.data.network.response.BookModel
import io.reactivex.Observable



class KakaoClient (private val bookApi: BookApi) {

    fun fetchBookInfo(parameter: MutableMap<String, Any>): Observable<BookModel> {
        return bookApi.fetchBookInfo("KakaoAK ${BuildConfig.API_KEY}", parameter)
    }
}