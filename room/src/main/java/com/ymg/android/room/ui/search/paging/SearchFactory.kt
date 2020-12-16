package com.ymg.android.room.ui.search.paging

import androidx.paging.DataSource
import com.ymg.android.room.data.network.response.BookModel
import com.ymg.android.room.ui.share.SharedViewModel



class SearchFactory(
    private var sharedViewModel: SharedViewModel
): DataSource.Factory<Int, BookModel.Document>() {

    private var searchDataSource: SearchDataSource? = null

    override fun create(): DataSource<Int, BookModel.Document> {
        searchDataSource = SearchDataSource(sharedViewModel)
        return searchDataSource!!
    }
}