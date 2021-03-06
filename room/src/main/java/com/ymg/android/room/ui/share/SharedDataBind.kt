package com.ymg.android.room.ui.share

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ymg.android.room.R
import java.text.NumberFormat
import java.util.*



// 이미지
@BindingAdapter("setBindImage")
fun ImageView.setBindImage(thumbnail: String) {
    Glide.with(context)
        .load(thumbnail)
        .placeholder(R.drawable.image_holder)
        .error(R.drawable.image_error)
        .into(this)
}



// 돈
@BindingAdapter("setBindMoney")
fun AppCompatTextView.setBindMoney(price: String) {
    val moneyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA)
    text = moneyFormat.format(price.toInt())
}