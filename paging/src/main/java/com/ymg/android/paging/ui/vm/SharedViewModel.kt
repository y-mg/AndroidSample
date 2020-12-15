package com.ymg.android.paging.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ymg.android.paging.data.network.kakao.KakaoClient
import com.ymg.android.paging.data.network.kakao.response.book.BookModel
import com.ymg.android.paging.base.BaseViewModel
import com.ymg.android.paging.ui.sub.search.SearchNavigator
import com.ymg.android.paging.ui.vm.paging.PagingFactory
import com.ymg.android.paging.util.event.Event



class SharedViewModel(
    val kakaoClient: KakaoClient,
) : BaseViewModel() {

    val searchNavigator = MutableLiveData<Event<SearchNavigator>>()
    var errorNavigator = MutableLiveData<Event<String?>>()

    private val pagingFactory = PagingFactory(this)

    // 도서 정보
    private var _bookItems: LiveData<PagedList<BookModel.Document>>? = null
    val bookItems: LiveData<PagedList<BookModel.Document>>?
        get() = _bookItems

    // 검색어(DataSource 에서 검색어를 알 수 있게 하기 위해 2-way-binding 사용)
    var query: String = ""

    // 정렬 방식
    var sort: String = ""

    // 상세 페이지 데이터
    lateinit var itemDocument: BookModel.Document



    init {
        setUpdateSort()

        val pagedListConfig = PagedList.Config.Builder()
                .setInitialLoadSizeHint(50) // 최초 로드 사이즈 50
                .setPageSize(50) // 페이징 당 50개
                .setPrefetchDistance(50) // 50개 마다 미리 로드
                .setEnablePlaceholders(false) // 데이터가 완전히 로드될때까지의 자리 표시자 비활성화
                .build()

        _bookItems = LivePagedListBuilder(pagingFactory, pagedListConfig).build()
    }



    // 정렬 방식 업데이트
    fun setUpdateSort(sort: String = "accuracy") {
        this.sort = sort
    }

    // 책 검색 시작
    fun onStartSearch() {
        _bookItems?.value?.dataSource?.invalidate()
    }
}