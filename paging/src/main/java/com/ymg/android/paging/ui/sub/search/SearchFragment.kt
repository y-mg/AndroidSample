package com.ymg.android.paging.ui.sub.search

import com.jakewharton.rxbinding4.widget.textChanges
import com.kennyc.view.MultiStateView
import com.ymg.android.paging.R
import com.ymg.android.paging.BR
import com.ymg.android.paging.databinding.FragmentSearchBinding
import com.ymg.android.paging.base.BaseFragment
import com.ymg.android.paging.ui.vm.SharedViewModel
import com.ymg.android.paging.util.event.EventObserver
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.concurrent.TimeUnit



class SearchFragment : BaseFragment<FragmentSearchBinding, SharedViewModel>() {

    private val sharedViewModel: SharedViewModel by sharedViewModel()

    // 상세 화면에서 돌아올 때 초기화 방지용
    private var checkQuery: String = ""



    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_search
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
    }

    private fun observe() {
        sharedViewModel.searchNavigator.observe(this, EventObserver {
            when (it) {
                // 데이터 표시
                SearchNavigator.CHANGE_STATE_CONTENT -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.CONTENT
                }

                // 로딩 표시
                SearchNavigator.CHANGE_STATE_LOADING -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.LOADING
                }

                // 빈 데이터 표시
                SearchNavigator.CHANGE_STATE_EMPTY -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.EMPTY
                }

                // 에러 표시
                SearchNavigator.CHANGE_STATE_ERROR -> {
                    getViewDataBinding().stateView.viewState = MultiStateView.ViewState.ERROR
                }

                else -> Unit
            }
        })


        getViewDataBinding().editSearch.textChanges()
            .debounce(1000, TimeUnit.MILLISECONDS)
            .map {
                it.toString()
            }
            .filter {
                it != checkQuery
            }
            .subscribe {
                sharedViewModel.onStartSearch()
                checkQuery = it
            }
    }
}