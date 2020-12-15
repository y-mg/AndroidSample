package com.ymg.android.paging.ui.vm

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ymg.android.paging.R
import java.text.NumberFormat
import java.util.*



// 이미지
@BindingAdapter("setBindImage")
fun ImageView.setBindImage(thumbnail: String) {
    Glide.with(context)
            .load(thumbnail)
            .placeholder(R.drawable.icon_image_holder)
            .error(R.drawable.icon_image_error)
            .into(this)
}



// 돈
@BindingAdapter("setBindMoney")
fun AppCompatTextView.setBindMoney(price: String) {
    val moneyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA)
    text = moneyFormat.format(price.toInt())
}