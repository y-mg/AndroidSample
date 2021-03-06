package com.ymg.android.room.ui.search.paging

import androidx.paging.PageKeyedDataSource
import com.ymg.android.room.data.network.response.BookModel
import com.ymg.android.room.ui.search.SearchNavigator
import com.ymg.android.room.ui.share.SharedViewModel
import com.ymg.android.room.util.event.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class SearchDataSource(
    private var sharedViewModel: SharedViewModel
) : PageKeyedDataSource<Int, BookModel.Document>() {

    // PagedList 가 처음 데이터를 가져올 때 호출되는 함수
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, BookModel.Document>) {
        // 검색어가 있는 경우에 동작
        if (sharedViewModel.query.isNotEmpty()) {
            val pageNumber = 1
            val nextPageNumber = pageNumber + 1

            val parameter = mutableMapOf<String, Any>().apply {
                this["query"] = sharedViewModel.query
                this["size"] = params.requestedLoadSize
                this["page"] = pageNumber
            }

            val call = sharedViewModel.kakaoClient.fetchBookInfo(parameter)

            sharedViewModel.addDisposable(call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    sharedViewModel.searchNavigator.postValue(Event(SearchNavigator.CHANGE_STATE_LOADING))
                }
                .doOnError {
                    sharedViewModel.searchNavigator.value = Event(SearchNavigator.CHANGE_STATE_ERROR)
                }
                .subscribe {
                    it?.documents?.let { document ->
                        if (document.isNotEmpty()) {
                            callback.onResult(document, null, nextPageNumber)
                            sharedViewModel.searchNavigator.value = Event(SearchNavigator.CHANGE_STATE_CONTENT)
                        } else {
                            sharedViewModel.searchNavigator.value = Event(SearchNavigator.CHANGE_STATE_EMPTY)
                        }
                    }
                }
            )
        }
    }

    // 다음 페이지의 데이터를 로드할 때 호출
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BookModel.Document>) {
        // 검색어가 있는 경우에 동작
        if (sharedViewModel.query.isNotEmpty()) {
            val parameter = mutableMapOf<String, Any>().apply {
                this["query"] = sharedViewModel.query
                this["size"] = params.requestedLoadSize
                this["page"] = params.key
            }

            val call = sharedViewModel.kakaoClient.fetchBookInfo(parameter)

            sharedViewModel.addDisposable(call
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        sharedViewModel.searchNavigator.value = Event(SearchNavigator.CHANGE_STATE_ERROR)
                    }
                    .subscribe {
                        it?.documents?.let { document ->
                            val nextPageNumber = params.key + 1
                            callback.onResult(document, nextPageNumber)
                        }
                    }
            )
        }
    }

    // 이전 페이지의 데이터를 로드할 때 호출
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BookModel.Document>) = Unit
}