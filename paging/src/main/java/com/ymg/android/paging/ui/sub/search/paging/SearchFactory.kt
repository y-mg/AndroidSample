package com.ymg.android.paging.ui.sub.search.paging

import androidx.paging.DataSource
import com.ymg.android.paging.network.response.BookModel
import com.ymg.android.paging.ui.vm.SharedViewModel



class SearchFactory(
    private var sharedViewModel: SharedViewModel
): DataSource.Factory<Int, BookModel.Document>() {

    private var searchDataSource: SearchDataSource? = null

    override fun create(): DataSource<Int, BookModel.Document> {
        searchDataSource = SearchDataSource(sharedViewModel)
        return searchDataSource!!
    }
}