package com.ymg.android.mvvm.ui.api

import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ymg.android.mvvm.R
import com.ymg.android.mvvm.network.response.SearchModel
import com.ymg.android.mvvm.ui.share.SharedViewModel



// Recyclerview Adapter 등록
@BindingAdapter(value = ["apiAdapter1", "apiAdapter2"])
fun apiAdapter(
    recyclerView: RecyclerView,
    items: List<SearchModel.Items>,
    sharedViewModel: SharedViewModel
) {
    ApiListView.Adapter(items, sharedViewModel).apply {
        recyclerView.adapter = this
        this.setList(items)
    }
}



// 좋아요 버튼
@BindingAdapter(value = ["btnApiGood1", "btnApiGood2"])
fun AppCompatImageButton.btnApiGood(items: SearchModel.Items, sharedViewModel: SharedViewModel) {
    when (items.isCheckGood) {
        true -> {
            setColorFilter(ContextCompat.getColor(context, R.color.black_000000))
        }

        else -> {
            setColorFilter(ContextCompat.getColor(context, R.color.gray_dddddd))
        }
    }

    setOnClickListener {
        when (items.isCheckGood) {
            true -> {
                items.isCheckGood = false
                setColorFilter(ContextCompat.getColor(context, R.color.gray_dddddd))
                sharedViewModel.goods.remove(items)
            }

            else -> {
                items.isCheckGood = true
                setColorFilter(ContextCompat.getColor(context, R.color.black_000000))
                sharedViewModel.goods.add(items)
            }
        }
        sharedViewModel.goodItems.value = sharedViewModel.goods
    }
}