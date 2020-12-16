package com.ymg.android.room.base

import androidx.lifecycle.ViewModel
import com.ymg.android.room.data.db.BookMarkDB.Companion.destroyInstance
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
        destroyInstance()
    }
}