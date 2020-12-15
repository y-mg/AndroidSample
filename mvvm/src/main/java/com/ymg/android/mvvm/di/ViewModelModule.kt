package com.ymg.android.mvvm.di

import com.ymg.android.mvvm.ui.share.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {

    viewModel {
        SharedViewModel(get())
    }
}