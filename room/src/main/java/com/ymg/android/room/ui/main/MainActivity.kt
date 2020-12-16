package com.ymg.android.room.ui.main

import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxbinding4.widget.textChanges
import com.ymg.android.room.R
import com.ymg.android.room.BR
import com.ymg.android.room.base.BaseActivity
import com.ymg.android.room.databinding.ActivityMainBinding
import com.ymg.android.room.ui.bookmark.BookMarkFragment
import com.ymg.android.room.ui.search.SearchFragment
import com.ymg.android.room.ui.share.SharedViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit



class MainActivity : BaseActivity<ActivityMainBinding, SharedViewModel>(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val sharedViewModel: SharedViewModel by viewModel()



    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SharedViewModel {
        return sharedViewModel
    }

    override fun bindView() {
        getViewDataBinding().view = this
        observe()

        getViewDataBinding().navigation.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction()
            .add(getViewDataBinding().container.id, SearchFragment()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                supportFragmentManager.beginTransaction()
                    .replace(getViewDataBinding().container.id, SearchFragment())
                    .commitAllowingStateLoss()
                return true
            }

            R.id.bookMark -> {
                supportFragmentManager.beginTransaction()
                    .replace(getViewDataBinding().container.id, BookMarkFragment())
                    .commitAllowingStateLoss()
                return true
            }

            else -> {
                return false
            }
        }
    }



    private fun observe() {
        getViewDataBinding().editSearch.textChanges()
            .debounce(1000, TimeUnit.MILLISECONDS)
            .map {
                it.toString()
            }
            .subscribeOn(Schedulers.io())
            .subscribe {
                sharedViewModel.onStartSearch()
            }
    }
}