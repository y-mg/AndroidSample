package com.ymg.android.paging.ui.vm.paging

import androidx.paging.DataSource
import com.ymg.android.paging.data.network.kakao.response.book.BookModel
import com.ymg.android.paging.ui.vm.SharedViewModel



class PagingFactory(
    private var sharedViewModel: SharedViewModel
): DataSource.Factory<Int, BookModel.Document>() {

    private var pagingDataSource: PagingDataSource? = null

    override fun create(): DataSource<Int, BookModel.Document> {
        pagingDataSource = PagingDataSource(sharedViewModel)
        return pagingDataSource!!
    }
}