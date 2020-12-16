package com.ymg.android.room.di

import com.ymg.android.room.ui.share.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {

    viewModel {
        SharedViewModel(get(), get())
    }
}