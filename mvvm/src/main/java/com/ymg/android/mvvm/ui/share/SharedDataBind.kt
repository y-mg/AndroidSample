package com.ymg.android.mvvm.ui.share

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ymg.android.mvvm.R



// 이미지
@BindingAdapter("setBindImage")
fun ImageView.setBindImage(thumbnail: String) {
    Glide.with(context)
        .load(thumbnail)
        .placeholder(R.drawable.image_holder)
        .error(R.drawable.image_error)
        .into(this)
}