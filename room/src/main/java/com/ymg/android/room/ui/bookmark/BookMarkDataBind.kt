package com.ymg.android.room.ui.bookmark

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.ymg.android.room.data.db.entity.BookMark
import com.ymg.android.room.ui.share.SharedViewModel



// Recyclerview Adapter 등록
@BindingAdapter(value = ["bookMarkAdapter1", "bookMarkAdapter2"])
fun bookMarkAdapter(
    recyclerView: RecyclerView,
    items: PagedList<BookMark>?,
    sharedViewModel: SharedViewModel
) {
    BookMarkPagedListView.Adapter(sharedViewModel).apply {
        recyclerView.adapter = this
        this.setPagedList(items)
    }
}