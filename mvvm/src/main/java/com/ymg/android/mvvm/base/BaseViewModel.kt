package com.ymg.android.mvvm.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable



abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()



    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}