package com.ymg.android.mvvm.network.api

import com.ymg.android.mvvm.network.response.SearchModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap



interface SearchApi {

    /**
     * 유저 정보 요청
     */
    @GET("/search/users")
    fun fetchSearchUsers(
        @Header("Authorization")
        authorization: String,
        @QueryMap
        parameter: MutableMap<String, Any>
    ): Observable<SearchModel>
}