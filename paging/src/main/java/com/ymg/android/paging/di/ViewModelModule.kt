package com.ymg.android.paging.di

import com.ymg.android.paging.ui.vm.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val viewModelModule = module {

    /**
     * SharedViewModel 의 ViewModel 인스턴스
     * get() 은 Repository 를 가져옴
     */
    viewModel {
        SharedViewModel(get())
    }
}