package com.ymg.android.room.ui.share

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ymg.android.room.base.BaseViewModel
import com.ymg.android.room.data.db.dao.BookMarkDao
import com.ymg.android.room.data.db.entity.BookMark
import com.ymg.android.room.data.network.KakaoClient
import com.ymg.android.room.data.network.response.BookModel
import com.ymg.android.room.ui.bookmark.BookMarkNavigator
import com.ymg.android.room.ui.search.SearchNavigator
import com.ymg.android.room.ui.search.paging.SearchFactory
import com.ymg.android.room.util.event.Event
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



class SharedViewModel(
    val kakaoClient: KakaoClient,
    private val bookMarkDao: BookMarkDao
) : BaseViewModel() {

    val searchNavigator = MutableLiveData<Event<SearchNavigator>>()
    val bookMarkNavigator = MutableLiveData<Event<BookMarkNavigator>>()

    private val searchFactory = SearchFactory(this)

    // 도서 정보
    private var _bookItems: LiveData<PagedList<BookModel.Document>>? = null
    val bookItems: LiveData<PagedList<BookModel.Document>>?
        get() = _bookItems

    // 검색어(DataSource 에서 검색어를 알 수 있게 하기 위해 2-way-binding 사용)
    var query: String = ""

    // 북마크 정보
    private var _bookMarkItems: LiveData<PagedList<BookMark>>? = null
    val bookMarkItems: LiveData<PagedList<BookMark>>?
        get() = _bookMarkItems



    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setInitialLoadSizeHint(50) // 최초 로드 사이즈 50
            .setPageSize(50) // 페이징 당 50개
            .setPrefetchDistance(50) // 50개 마다 미리 로드
            .setEnablePlaceholders(false) // 데이터가 완전히 로드될때까지의 자리 표시자 비활성화
            .build()

        _bookItems = LivePagedListBuilder(searchFactory, pagedListConfig).build()
        _bookMarkItems = LivePagedListBuilder(bookMarkDao.findAll(), pagedListConfig).build()
    }



    // 책 검색 시작
    fun onStartSearch() {
        searchNavigator.postValue(Event(SearchNavigator.CHANGE_STATE_CONTENT))
        _bookItems?.value?.dataSource?.invalidate()
    }



    // 북마크 저장
    fun onSaveBookMark(document: BookModel.Document) {
        addDisposable(
            Completable.fromAction {
                bookMarkDao.insert(BookMark.createBookMark(document))
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    searchNavigator.value = Event(SearchNavigator.SAVE_FAIL)
                }
                .subscribe {
                    searchNavigator.value = Event(SearchNavigator.SAVE_SUCCESS)
                }
        )
    }



    // 삭제
    fun onDeleteBookMark(bookMark: BookMark) {
        addDisposable(
            Completable.fromAction {
                bookMarkDao.delete(bookMark)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    bookMarkNavigator.value = Event(BookMarkNavigator.DELETE_FAIL)
                }
                .subscribe {
                    bookMarkNavigator.value = Event(BookMarkNavigator.DELETE_SUCCESS)
                }
        )
    }
}