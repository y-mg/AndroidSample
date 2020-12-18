package com.ymg.android.room.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.jakewharton.rxbinding4.widget.textChanges
import com.ymg.android.room.R
import com.ymg.android.room.BR
import com.ymg.android.room.base.BaseActivity
import com.ymg.android.room.databinding.ActivityMainBinding
import com.ymg.android.room.ui.share.SharedViewModel
import com.ymg.android.room.util.nav.setupWithNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit



class MainActivity : BaseActivity<ActivityMainBinding, SharedViewModel>() {

    private val sharedViewModel: SharedViewModel by viewModel()

    private var currentNavController: LiveData<NavController>? = null



    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SharedViewModel {
        return sharedViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun bindView() {
        getViewDataBinding().view = this
        observe()
    }



    private fun setBottomNavigationBar() {
        val navGraphIds = listOf(R.navigation.nav_search, R.navigation.nav_book_mark)

        val controller = getViewDataBinding().bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = getViewDataBinding().fragmentContainerView.id,
            intent = intent
        )
        currentNavController = controller
    }



    private fun observe() {
        getViewDataBinding().editSearch.textChanges()
            .debounce(1000, TimeUnit.MILLISECONDS)
            .map {
                it.toString()
            }
            .subscribe {
                sharedViewModel.onStartSearch()
            }
    }
}