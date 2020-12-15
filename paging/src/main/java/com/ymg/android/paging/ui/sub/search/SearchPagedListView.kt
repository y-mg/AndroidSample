package com.ymg.android.paging.ui.sub.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ymg.android.paging.data.network.kakao.response.book.BookModel
import com.ymg.android.paging.databinding.FragmentSearchItemBinding
import com.ymg.android.paging.ui.vm.SharedViewModel


abstract class SearchPagedListView {

    // Adapter
    class Adapter(
        private val sharedViewModel: SharedViewModel
    ): PagedListAdapter<BookModel.Document, ViewHolder>(DIFF_CALLBACK) {

        companion object {
            private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BookModel.Document>() {
                override fun areItemsTheSame(oldItem: BookModel.Document, newItem: BookModel.Document): Boolean =
                    oldItem.isbn == newItem.isbn

                override fun areContentsTheSame(oldItem: BookModel.Document, newItem: BookModel.Document): Boolean =
                    oldItem == newItem
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val viewBinding = FragmentSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(viewBinding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            getItem(position)?.run {
                holder.viewDataBinding.document = this
                holder.viewDataBinding.viewModel = sharedViewModel
            }
        }

        fun setPagedList(items: PagedList<BookModel.Document>?) {
            items?.let {
                this.submitList(items)
            }
        }
    }

    // ViewHolder
    class ViewHolder (
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val viewDataBinding: FragmentSearchItemBinding = DataBindingUtil.bind(view)!!
    }
}