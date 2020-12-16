package com.ymg.android.room.ui.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ymg.android.room.data.db.entity.BookMark
import com.ymg.android.room.databinding.FragmentBookMarkItemBinding
import com.ymg.android.room.ui.share.SharedViewModel



abstract class BookMarkPagedListView {

    // Adapter
    class Adapter(
        private val sharedViewModel: SharedViewModel
    ): PagedListAdapter<BookMark, ViewHolder>(DIFF_CALLBACK) {

        companion object {
            private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BookMark>() {
                override fun areItemsTheSame(oldItem: BookMark, newItem: BookMark): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: BookMark, newItem: BookMark): Boolean =
                    oldItem == newItem
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val viewBinding = FragmentBookMarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(viewBinding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            getItem(position)?.run {
                holder.viewDataBinding.bookMark = this
                holder.viewDataBinding.viewModel = sharedViewModel
            }
        }

        fun setPagedList(items: PagedList<BookMark>?) {
            items?.let {
                this.submitList(items)
            }
        }
    }

    // ViewHolder
    class ViewHolder (
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val viewDataBinding: FragmentBookMarkItemBinding = DataBindingUtil.bind(view)!!
    }
}