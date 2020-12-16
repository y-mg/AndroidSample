package com.ymg.android.room.data.network.api

import com.ymg.android.room.data.network.response.BookModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap



// Book API Interface
interface BookApi {
    companion object {
        // 도서 정보 요청
        const val SEARCH_BOOK = "v3/search/book"
    }

    /**
     * 도서 정보 요청
     */
    @GET(SEARCH_BOOK)
    fun fetchBookInfo(
        @Header("Authorization")
        authorization: String,
        @QueryMap
        parameter: MutableMap<String, Any>
    ): Observable<BookModel>
}