package com.ymg.android.paging.ui.sub.details

import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ymg.android.paging.R
import com.ymg.android.paging.network.response.BookModel



// 좋아요 버튼
@BindingAdapter("btnGood")
fun AppCompatImageButton.btnGood(document: BookModel.Document) {
    when (document.isCheckGood) {
        true -> {
            setColorFilter(ContextCompat.getColor(context, R.color.black_000000))
        }

        else -> {
            setColorFilter(ContextCompat.getColor(context, R.color.gray_eeeeee))
        }
    }

    setOnClickListener {
        when(document.isCheckGood) {
            true -> {
                document.isCheckGood = false
                setColorFilter(ContextCompat.getColor(context, R.color.gray_eeeeee))
            }

            else -> {
                document.isCheckGood = true
                setColorFilter(ContextCompat.getColor(context, R.color.black_000000))
            }
        }
    }
}