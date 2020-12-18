package com.ymg.android.mvvm.ui.share

import androidx.lifecycle.MutableLiveData
import com.ymg.android.mvvm.base.BaseViewModel
import com.ymg.android.mvvm.network.GithubClient
import com.ymg.android.mvvm.network.response.SearchModel
import com.ymg.android.mvvm.ui.api.ApiNavigator
import com.ymg.android.mvvm.util.event.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class SharedViewModel(
    private val githubClient: GithubClient,
) : BaseViewModel() {

    // Navigator
    val apiNavigator = MutableLiveData<Event<ApiNavigator>>()

    // User Items
    var userItems = MutableLiveData<List<SearchModel.Items>>().apply { value = arrayListOf() }

    // Good Items
    var goods = mutableListOf<SearchModel.Items>()
    var goodItems = MutableLiveData<List<SearchModel.Items>>().apply { value = arrayListOf() }



    // 검색
    fun fetchSearchUsers(query: String) {
        val parameter = mutableMapOf<String, Any>().apply {
            this["q"] = query
        }

        val call = githubClient.fetchSearchUsers(parameter)

        addDisposable(call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                apiNavigator.postValue(Event(ApiNavigator.CHANGE_STATE_LOADING))
            }
            .doOnError {
                apiNavigator.value = Event(ApiNavigator.CHANGE_STATE_ERROR)
            }
            .subscribe {
                it?.items?.let { item ->
                    if (item.isNotEmpty()) {
                        userItems.value = it.items
                        apiNavigator.value = Event(ApiNavigator.CHANGE_STATE_CONTENT)
                    } else {
                        apiNavigator.value = Event(ApiNavigator.CHANGE_STATE_EMPTY)
                    }
                }
            }
        )
    }



    // 초기화
    fun initialization() {
        userItems.postValue(arrayListOf())
        goods.clear()
        goodItems.postValue(arrayListOf())
        apiNavigator.postValue(Event(ApiNavigator.CHANGE_STATE_CONTENT))
    }
}