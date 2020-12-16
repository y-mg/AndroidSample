package com.ymg.android.room.ui.search

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.ymg.android.room.data.network.response.BookModel
import com.ymg.android.room.ui.share.SharedViewModel



// Recyclerview Adapter 등록
@BindingAdapter(value = ["searchAdapter1", "searchAdapter2"])
fun searchAdapter(
    recyclerView: RecyclerView,
    items: PagedList<BookModel.Document>?,
    sharedViewModel: SharedViewModel
) {
    SearchPagedListView.Adapter(sharedViewModel).apply {
        recyclerView.adapter = this
        this.setPagedList(items)
    }
}