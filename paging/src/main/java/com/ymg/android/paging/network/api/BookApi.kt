package com.ymg.android.paging.network.api

import com.ymg.android.paging.network.response.BookModel
import io.reactivex.Observable
import retrofit2.http.*



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