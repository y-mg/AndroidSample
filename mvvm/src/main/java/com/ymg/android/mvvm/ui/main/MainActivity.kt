package com.ymg.android.mvvm.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.jakewharton.rxbinding4.widget.textChanges
import com.ymg.android.mvvm.R
import com.ymg.android.mvvm.BR
import com.ymg.android.mvvm.base.BaseActivity
import com.ymg.android.mvvm.databinding.ActivityMainBinding
import com.ymg.android.mvvm.ui.share.SharedViewModel
import com.ymg.android.mvvm.util.nav.setupWithNavController
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
        val navGraphIds = listOf(R.navigation.nav_api, R.navigation.nav_local)

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
                if (it.isNotEmpty()) {
                    sharedViewModel.fetchSearchUsers(it)
                } else {
                    sharedViewModel.initialization()
                }
            }
    }
}