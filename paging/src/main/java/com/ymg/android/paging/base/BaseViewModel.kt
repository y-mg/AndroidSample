package com.ymg.android.paging.base


import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable



abstract class BaseViewModel : ViewModel() {

    // CompositeDisposable 클래스를 이용하면 생성된 모든 Observable 을 라이프사이클에 맞춰 한 번에 모두 해제할 수 있음
    private val compositeDisposable = CompositeDisposable()



    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }



    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}