package com.ymg.android.paging.ui.sub.search

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ymg.android.paging.R
import com.ymg.android.paging.network.response.BookModel
import com.ymg.android.paging.ui.vm.SharedViewModel



//* Spinner Adapter 등록
@BindingAdapter("filterAdapter")
fun Spinner.filterAdapter(sharedViewModel: SharedViewModel) {
    val arrayAdapter = ArrayAdapter.createFromResource(context, R.array.filter, android.R.layout.simple_spinner_dropdown_item)
    adapter = arrayAdapter

    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, item: Int, id: Long) {
            if (item == 0) {
                sharedViewModel.setUpdateSort("accuracy")
            } else if (item == 1) {
                sharedViewModel.setUpdateSort("recency")
            }
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) = Unit
    }
}



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



// Click Item
@BindingAdapter(value = ["bookItem1", "bookItem2"])
fun MaterialCardView.bookItem(sharedViewModel: SharedViewModel, document: BookModel.Document) {
    setOnClickListener {
        sharedViewModel.itemDocument = document
        findNavController().navigate(R.id.action_search_to_details)
    }
}
