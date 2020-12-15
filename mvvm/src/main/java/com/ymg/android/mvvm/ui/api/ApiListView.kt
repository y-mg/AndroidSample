package com.ymg.android.mvvm.ui.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ymg.android.mvvm.R
import com.ymg.android.mvvm.databinding.FragmentApiItemBinding
import com.ymg.android.mvvm.network.response.SearchModel
import com.ymg.android.mvvm.ui.share.SharedViewModel



abstract class ApiListView {

    /// Adapter
    class Adapter(
        var items: List<SearchModel.Items> = mutableListOf(),
        private val sharedViewModel: SharedViewModel
    ): RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.fragment_api_item,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.viewDataBinding.items = items[position]
            holder.viewDataBinding.viewModel = sharedViewModel
        }

        override fun getItemCount(): Int = items.size

        fun setList(items: List<SearchModel.Items>) {
            this.items = items
            this.notifyDataSetChanged()
        }
    }

    // ViewHolder
    class ViewHolder (
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val viewDataBinding: FragmentApiItemBinding = DataBindingUtil.bind(view)!!
    }
}