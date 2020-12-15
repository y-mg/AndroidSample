package com.ymg.android.mvvm.network

import com.ymg.android.mvvm.BuildConfig
import com.ymg.android.mvvm.network.api.SearchApi
import com.ymg.android.mvvm.network.response.SearchModel
import io.reactivex.Observable



class GithubClient (private val searchApi: SearchApi) {

    fun fetchSearchUsers(parameter: MutableMap<String, Any>): Observable<SearchModel> {
        return searchApi.fetchSearchUsers("token ${BuildConfig.TOKEN}", parameter)
    }
}