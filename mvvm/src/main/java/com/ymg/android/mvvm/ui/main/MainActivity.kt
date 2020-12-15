package com.ymg.android.mvvm.ui.main

import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.rxbinding4.widget.textChanges
import com.ymg.android.mvvm.R
import com.ymg.android.mvvm.BR
import com.ymg.android.mvvm.base.BaseActivity
import com.ymg.android.mvvm.databinding.ActivityMainBinding
import com.ymg.android.mvvm.ui.api.ApiFragment
import com.ymg.android.mvvm.ui.local.LocalFragment
import com.ymg.android.mvvm.ui.share.SharedViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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
            .add(getViewDataBinding().container.id, ApiFragment()).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.api -> {
                supportFragmentManager.beginTransaction()
                    .replace(getViewDataBinding().container.id, ApiFragment())
                    .commitAllowingStateLoss()
                return true
            }

            R.id.local -> {
                supportFragmentManager.beginTransaction()
                    .replace(getViewDataBinding().container.id, LocalFragment())
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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isNotEmpty()) {
                    sharedViewModel.fetchSearchUsers(it)
                } else {
                    sharedViewModel.apply {
                        userItems.value = arrayListOf()
                        goods.clear()
                        goodItems.value = arrayListOf()
                    }
                }
            }
    }
}